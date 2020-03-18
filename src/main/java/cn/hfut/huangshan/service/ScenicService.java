package cn.hfut.huangshan.service;

import cn.hfut.huangshan.mapper.ScenicMapper;
import cn.hfut.huangshan.pojo.Scenic;
import cn.hfut.huangshan.utils.RedisUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 景点
 * @author pcy
 */
@Service
public class ScenicService {

    @Autowired
    RedisUtil redisUtil;
    @Autowired
    ScenicMapper scenicMapper;

    /**
     * 全查询
     * @return
     */
    @Transactional
    public List<Scenic> getAllScenes(){

        List<Scenic> scenics = null;
        if ( redisUtil.exists("huangshan:scenics")){
            //查redis
            String scenicsInRedis = redisUtil.get("huangshan:scenics", 0);
            scenics = JSON.parseArray(scenicsInRedis, Scenic.class);
        }else {
            //没有则从数据库查，并存入redis
            scenics = scenicMapper.getAllScenes();
            if (scenics.size() > 0){
                String hotelStr = JSON.toJSONString(scenics);
                redisUtil.set("huangshan:scenics",hotelStr,0);
                redisUtil.persist("huangshan:scenics");
            }
        }
        return scenics;
    }

    /**
     * id查询
     * @param id
     * @return
     */
    public Scenic getById(long id) {
        Scenic scenic = null;
        String key = "huangshan:scenic:" + id;
        if (redisUtil.exists(key)){
            String scenicInRedis = redisUtil.get(key,0);
            scenic = JSON.parseObject(scenicInRedis,Scenic.class);
        }else {
            scenic = scenicMapper.getById(id);
            if (scenic != null){
                String scenicStr = JSON.toJSONString(scenic);
                redisUtil.set(key,scenicStr,0);
                redisUtil.persist(key);
            }
        }
        return scenic;
    }

    /**
     * 按id删除
     * @param id
     * @return
     */
    @Transactional
    public boolean deleteOne(long id) {
        String key = "huangshan:scenic:" + id;
        Integer rows = scenicMapper.deleteOne(id);
        if (rows > 0) {
            redisUtil.del(0,key);
            List<Scenic> scenes = scenicMapper.getAllScenes();
            if (scenes.size() > 0){
                String scenicsStr = JSON.toJSONString(scenes);
                redisUtil.set("huangshan:scenics",scenicsStr,0);
                redisUtil.persist("huangshan:scenics");
            }else {
                redisUtil.del(0,"huangshan:scenics");
            }
            return true;
        }
        return false;
    }

    /**
     * 增加一个
     * @param scenic
     * @return
     */
    public boolean addOne(Scenic scenic) {
        Integer rows = scenicMapper.addOne(scenic);
        if (rows > 0){
            List<Scenic> scenics = scenicMapper.getAllScenes();
            String scenicsStr = JSON.toJSONString(scenics);
            redisUtil.set("huangshan:scenics",scenicsStr,0);
            redisUtil.persist("huangshan:scenics");
            return true;
        }
        return false;
    }

    /**
     * 更新一个
     * @param scenic
     * @return
     */
    public boolean updateOne(Scenic scenic) {
        Integer rows = scenicMapper.updateOne(scenic);
        if (rows > 0){
            String key = "huangshan:scenic:" + scenic.getId();
            String scenicStr = JSON.toJSONString(scenicMapper.getById(scenic.getId()));
            redisUtil.set(key, scenicStr, 0);
            List<Scenic> scenics = scenicMapper.getAllScenes();
            String scenicsStr = JSON.toJSONString(scenics);
            redisUtil.set("huangshan:scenics",scenicsStr,0);
            redisUtil.persist("huangshan:scenics");
            return true;
        }
        return false;
    }
}
