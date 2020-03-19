package cn.hfut.huangshan.service;

import cn.hfut.huangshan.mapper.ScenicHotMapper;
import cn.hfut.huangshan.pojo.ScenicHot;
import cn.hfut.huangshan.utils.RedisUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScenicHotService {
    
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    ScenicHotMapper scenicHotMapper;

    public static final int DEFAULT_EXPIRE = 60 * 60;


    /**
     * 获取所有景点的实时热度
     * 实时热度我想的是 1小时 更新一次，所以redis里至少设置过期时间为 一小时： 60*60
     * @return
     */
    public List<ScenicHot> getAll() {
        List<ScenicHot> scenicHots = null;
        String key = "huangshan:scenicHots";
        if (redisUtil.exists(key)){
            String hotsInRedis = redisUtil.get(key, 0);
            scenicHots = JSON.parseArray(hotsInRedis,ScenicHot.class);
        }else {
            scenicHots = scenicHotMapper.getAll();
            if (scenicHots.size() > 0){
                String hotsStr = JSON.toJSONString(scenicHots);
                redisUtil.set(key,hotsStr,0);
                redisUtil.expire(key,DEFAULT_EXPIRE,0);
            }
        }
        return scenicHots;
    }

    /**
     * 按景点的id查询
     * @param id 景点id
     * @return
     */
    public ScenicHot getByScenicId(long id) {
        ScenicHot scenicHot = null;
        String key = "huangshan:scenicHot:" + id;
        if ( redisUtil.exists(key)){
            //查redis
            String hotInRedis = redisUtil.get(key, 0);
            scenicHot = JSON.parseObject(hotInRedis, ScenicHot.class);
        }else {
            //没有则从数据库查，并存入redis
            scenicHot = scenicHotMapper.getByScenicId(id);
            if (scenicHot != null) {
                String scenicHotStr = JSON.toJSONString(scenicHot);
                redisUtil.set(key,scenicHotStr,0);
                redisUtil.expire(key,DEFAULT_EXPIRE,0);
            }
        }
        return scenicHot;
    }

    /**
     * 删除某个景点的实时热度
     * @param id
     * @return
     */
    @Transactional
    public boolean deleteOne(long id) {
        String key = "huangshan:scenicHot:" + id;
        Integer dbRows = scenicHotMapper.deleteOne(id);
        if ( dbRows > 0){
            redisUtil.del(0, key);
            List<ScenicHot> scenicHots = scenicHotMapper.getAll();
            if (scenicHots.size() > 0){
                String hotsStr = JSON.toJSONString(scenicHots);
                redisUtil.set("huangshan:scenicHots", hotsStr, 0);
                redisUtil.expire("huangshan:scenicHots",DEFAULT_EXPIRE, 0);
            }else {
                redisUtil.del(0,"huangshan:scenicHots");
            }
            return true;
        }
        return false;
    }

    /**
     * 增加一个
     * @param scenicHot
     * @return
     */
    @Transactional
    public boolean addOne(ScenicHot scenicHot) {
        Integer rows = scenicHotMapper.addOne(scenicHot);
        if (rows > 0){
            List<ScenicHot> scenicHots = scenicHotMapper.getAll();
            String scenicHotsStr = JSON.toJSONString(scenicHots);
            redisUtil.set("huangshan:scenicHots", scenicHotsStr, 0);
            redisUtil.expire("huangshan:scenicHots",DEFAULT_EXPIRE, 0);
            return true;
        }
        return false;
    }

    /**
     * 更新一个
     * @param scenicHot
     * @return
     */
    public boolean updateOne(ScenicHot scenicHot) {
        Integer rows = scenicHotMapper.updateOne(scenicHot);
        if (rows > 0){
            String key = "huangshan:scenicHot:" + scenicHot.getId();
            String hotStr = JSON.toJSONString(scenicHotMapper.getByScenicId(scenicHot.getId()));
            redisUtil.set(key,hotStr,0);
            redisUtil.expire(key, DEFAULT_EXPIRE,0);
            List<ScenicHot> hots = scenicHotMapper.getAll();
            String hotsStr = JSON.toJSONString(hots);
            redisUtil.set("huangshan:scenicHots", hotsStr,0);
            redisUtil.expire("huangshan:scenicHots", DEFAULT_EXPIRE,0);
            return true;
        }
        return false;
    }
}
