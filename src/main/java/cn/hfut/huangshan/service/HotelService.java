package cn.hfut.huangshan.service;

import cn.hfut.huangshan.mapper.HotelMapper;
import cn.hfut.huangshan.pojo.Hotel;
import cn.hfut.huangshan.utils.RedisUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    RedisUtil redisUtil;
    @Autowired
    HotelMapper hotelMapper;

    /**
     * 全查询
     * @return
     */
    public List<Hotel> getAll() {
        List<Hotel> hotels = null;
        if ( redisUtil.exists("huangshan:hotels")){
            //查redis
            String hotelsInRedis = redisUtil.get("huangshan:hotels", 0);
            hotels = JSON.parseArray(hotelsInRedis, Hotel.class);
        }else {
            //没有则从数据库查，并存入redis
            hotels = hotelMapper.getAll();
            if (hotels.size() > 0){
                String hotelStr = JSON.toJSONString(hotels);
                redisUtil.set("huangshan:hotels",hotelStr,0);
                redisUtil.persist("huangshan:hotels");
            }
        }
        return hotels;
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public Hotel getById(long id) {
        Hotel hotel = null;
        String key = "huangshan:hotel:" + id;
        if ( redisUtil.exists(key)){
            //查redis
            String hotelInRedis = redisUtil.get(key, 0);
            hotel = JSON.parseObject(hotelInRedis, Hotel.class);
        }else {
            //没有则从数据库查，并存入redis
            hotel = hotelMapper.getById(id);
            if (hotel != null) {
                String hotelStr = JSON.toJSONString(hotel);
                redisUtil.set(key,hotelStr,0);
                redisUtil.persist(key);
            }
        }
        return hotel;
    }

    /**
     * 根据id删除一个
     * @param id
     * @return
     */
    @Transactional
    public boolean deleteOne(long id) {
        String key = "huangshan:hotel:" + id;
        Integer dbRows = hotelMapper.deleteOne(id);
        if (dbRows > 0){
            //更新redis
            Long redisRows = redisUtil.del(0, key);
            List<Hotel> hotels = hotelMapper.getAll();
            if (hotels.size() > 0 ){
                String hotelStr = JSON.toJSONString(hotels);
                redisUtil.set("huangshan:hotels",hotelStr,0);
                redisUtil.persist("huangshan:hotels");
            }else {
                redisUtil.del(0,"huangshan:hotels");
            }
            return true;
        }
        return false;
    }

    /**
     * 增加一个
     * @param hotel
     * @return
     */
    @Transactional
    public boolean addOne(Hotel hotel) {
        Integer rows = hotelMapper.addOne(hotel);
        if (rows > 0){
            //更新redis
            List<Hotel> hotels = hotelMapper.getAll();
            String hotelStr = JSON.toJSONString(hotels);
            redisUtil.set("huangshan:hotels",hotelStr,0);
            redisUtil.persist("huangshan:hotels");
            return true;
        }
        return false;
    }

    /**
     * 更新一个
     * @param hotel
     * @return
     */
    @Transactional
    public boolean updateOne(Hotel hotel) {
        Integer rows = hotelMapper.updateOne(hotel);
        if (rows > 0){
            //更新redis
            String key = "huangshan:hotel:" + hotel.getId();
            String hotelStr = JSON.toJSONString(hotelMapper.getById(hotel.getId()));
            redisUtil.set(key,hotelStr,0);
            List<Hotel> hotels = hotelMapper.getAll();
            String hotelsStr = JSON.toJSONString(hotels);
            redisUtil.set("huangshan:hotels",hotelsStr,0);
            redisUtil.persist("huangshan:hotels");
            return true;
        }
        return false;
    }
}
