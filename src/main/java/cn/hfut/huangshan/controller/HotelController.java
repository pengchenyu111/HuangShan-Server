package cn.hfut.huangshan.controller;

import cn.hfut.huangshan.constants.ErrorCode;
import cn.hfut.huangshan.pojo.Hotel;
import cn.hfut.huangshan.response.ResultObj;
import cn.hfut.huangshan.service.HotelService;
import cn.hfut.huangshan.utils.IdWorker;
import cn.hfut.huangshan.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 酒店
 * @author pcy
 */
@RestController
@RequestMapping("hotels")
public class HotelController {

    @Autowired
    HotelService hotelService;


    /**
     * 全查询
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResultObj getAll(){
        List<Hotel> hotels = hotelService.getAll();
        if (hotels.size() > 0){
            return ResponseUtil.success(hotels);
        }else {
            return ResponseUtil.error(ErrorCode.QUERY_FAIL,ErrorCode.QUERY_FAIL_MSG,null);
        }
    }


    /**
     * 根据id查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResultObj getById(@PathVariable("id") long id){
        Hotel hotel = hotelService.getById(id);
        if (hotel != null){
            return ResponseUtil.success(hotel);
        }else {
            return ResponseUtil.error(ErrorCode.QUERY_FAIL,ErrorCode.QUERY_FAIL_MSG,null);
        }
    }

    /**
     * 根据id删除一个
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResultObj deleteOne(@PathVariable("id") long id){
        boolean isSuccess = hotelService.deleteOne(id);
        if (isSuccess){
            return ResponseUtil.success(null);
        }else {
            return ResponseUtil.error(ErrorCode.DELETE_FAIL,ErrorCode.DELETE_FAIL_MSG,null);
        }
    }

    /**
     * 增加一个
     * @param hotel
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResultObj addOne(@RequestBody Hotel hotel){
        IdWorker idWorker = new IdWorker(0,0);
        long id = idWorker.nextId();
        hotel.setId(id);
        boolean isSuccess = hotelService.addOne(hotel);
        if (isSuccess){
            Hotel byId = hotelService.getById(id);
            return ResponseUtil.success(byId);
        }else {
            return ResponseUtil.error(ErrorCode.ADD_FAIL,ErrorCode.ADD_FAIL_MSG,null);
        }
    }

    /**
     * 更新一个
     * @param hotel
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResultObj updateOne(@PathVariable("id")long id, @RequestBody Hotel hotel){
        boolean isSuccess = hotelService.updateOne(hotel);
        if (isSuccess){
            Hotel byId = hotelService.getById(id);
            return ResponseUtil.success(byId);
        }else {
            return ResponseUtil.error(ErrorCode.UPDATE_FAIL,ErrorCode.UPDATE_FAIL_MSG,null);
        }
    }
}
