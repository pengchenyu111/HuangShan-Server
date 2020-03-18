package cn.hfut.huangshan.controller;

import cn.hfut.huangshan.constants.ErrorCode;
import cn.hfut.huangshan.pojo.Scenic;
import cn.hfut.huangshan.response.ResultObj;
import cn.hfut.huangshan.service.ScenicService;
import cn.hfut.huangshan.utils.IdWorker;
import cn.hfut.huangshan.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 景点
 * @author pcy
 */
@RestController
@RequestMapping("scenics")
public class ScenicController {

    @Autowired
    ScenicService scenicService;

    /**
     * 全查询
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResultObj getAllScenes(){
        List<Scenic> scenes = scenicService.getAllScenes();
        if (scenes.size() > 0) {
            return ResponseUtil.success(scenes);
        } else {
            return ResponseUtil.error(ErrorCode.QUERY_FAIL,ErrorCode.QUERY_FAIL_MSG,null);
        }
    }

    /**
     * 按id查询
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResultObj getById(@PathVariable("id") long id){
        Scenic scenic = scenicService.getById(id);
        if (scenic != null) {
            return ResponseUtil.success(scenic);
        } else {
            return ResponseUtil.error(ErrorCode.QUERY_FAIL,ErrorCode.QUERY_FAIL_MSG,null);
        }
    }


    /**
     * 按id删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResultObj deleteOne(@PathVariable("id") long id){
        boolean isSuccess = scenicService.deleteOne(id);
        if (isSuccess){
            return ResponseUtil.success(null);
        }else {
            return ResponseUtil.error(ErrorCode.DELETE_FAIL,ErrorCode.DELETE_FAIL_MSG,null);
        }
    }

    /**
     * 增加一个
     * 注意 经纬度最多设置小数点后6位
     * @param scenic
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResultObj addOne(@RequestBody Scenic scenic){
        IdWorker idWorker = new IdWorker(0,0);
        long id = idWorker.nextId();
        scenic.setId(id);
        boolean isSuccess = scenicService.addOne(scenic);
        if (isSuccess){
            Scenic byId = scenicService.getById(id);
            return ResponseUtil.success(byId);
        }else {
            return ResponseUtil.error(ErrorCode.ADD_FAIL,ErrorCode.ADD_FAIL_MSG,null);
        }
    }

    /**
     * 更新一个
     * 注意 经纬度最多设置小数点后6位
     * @param scenic
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResultObj updateOne(@PathVariable("id") long id, @RequestBody Scenic scenic){
        boolean isSuccess = scenicService.updateOne(scenic);
        if (isSuccess){
            Scenic byId = scenicService.getById(id);
            return ResponseUtil.success(byId);
        }else {
            return ResponseUtil.error(ErrorCode.UPDATE_FAIL,ErrorCode.UPDATE_FAIL_MSG,null);
        }
    }
}
