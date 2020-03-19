package cn.hfut.huangshan.controller;


import cn.hfut.huangshan.constants.ErrorCode;
import cn.hfut.huangshan.pojo.ScenicHot;
import cn.hfut.huangshan.response.ResultObj;
import cn.hfut.huangshan.service.ScenicHotService;
import cn.hfut.huangshan.service.ScenicService;
import cn.hfut.huangshan.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("scenic_hot")
public class ScenicHotController {

    @Autowired
    ScenicHotService scenicHotService;

    /**
     * 全查询
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResultObj getAll(){
        List<ScenicHot> scenicHots = scenicHotService.getAll();
        if (scenicHots.size() > 0){
            return ResponseUtil.success(scenicHots);
        }else {
            return ResponseUtil.error(ErrorCode.QUERY_FAIL,ErrorCode.QUERY_FAIL_MSG,null);
        }
    }

    /**
     * 按景点的id查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResultObj getByScenicId(@PathVariable("id") long id){
        ScenicHot scenicHot = scenicHotService.getByScenicId(id);
        if (scenicHot != null){
            return ResponseUtil.success(scenicHot);
        }else {
            return ResponseUtil.error(ErrorCode.QUERY_FAIL,ErrorCode.QUERY_FAIL_MSG,null);
        }
    }

    /**
     * 删除某个景点的实时热度
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResultObj deleteOne(@PathVariable("id") long id){
        boolean isSuccess = scenicHotService.deleteOne(id);
        if (isSuccess){
            return ResponseUtil.success(null);
        }else {
            return ResponseUtil.error(ErrorCode.DELETE_FAIL, ErrorCode.DELETE_FAIL_MSG, null);
        }
    }

    /**
     * 增加一个
     * 前提是 你要保证你加的这个景点在Scenic中有，因此ScenicHot的经纬度字段就可以在Scenic中找到了
     * @param scenicHot
     * @return
     */
    @RequestMapping(method = RequestMethod.POST )
    public ResultObj addOne(@RequestBody ScenicHot scenicHot){
        boolean isSuccess = scenicHotService.addOne(scenicHot);
        if (isSuccess){
            ScenicHot byScenicId = scenicHotService.getByScenicId(scenicHot.getId());
            return ResponseUtil.success(byScenicId);
        }else {
            return ResponseUtil.error(ErrorCode.ADD_FAIL, ErrorCode.ADD_FAIL_MSG, null);
        }
    }


    /**
     * 更新一个
     * @param id
     * @param scenicHot
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResultObj updateOne(@PathVariable("id") long id, @RequestBody ScenicHot scenicHot){
        boolean isSuccess = scenicHotService.updateOne(scenicHot);
        if (isSuccess){
            ScenicHot byScenicId = scenicHotService.getByScenicId(id);
            return ResponseUtil.success(byScenicId);
        }else {
            return ResponseUtil.error(ErrorCode.UPDATE_FAIL, ErrorCode.UPDATE_FAIL_MSG, null);
        }
    }
}
