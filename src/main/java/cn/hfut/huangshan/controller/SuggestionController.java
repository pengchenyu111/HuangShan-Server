package cn.hfut.huangshan.controller;


import cn.hfut.huangshan.constants.ErrorCode;
import cn.hfut.huangshan.pojo.Suggestion;
import cn.hfut.huangshan.response.ResultObj;
import cn.hfut.huangshan.service.SuggestionService;
import cn.hfut.huangshan.utils.IdWorker;
import cn.hfut.huangshan.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("suggestions")
public class SuggestionController {

    @Autowired
    SuggestionService suggestionService;

    /**
     * 全查询
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResultObj getAll(){
        List<Suggestion> suggestions =  suggestionService.getAll();
        if (suggestions.size() > 0) {
            return ResponseUtil.success(suggestions);
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
        Suggestion suggestion = suggestionService.getById(id);
        if (suggestion != null) {
            return ResponseUtil.success(suggestion);
        }else {
            return ResponseUtil.error(ErrorCode.QUERY_FAIL,ErrorCode.QUERY_FAIL_MSG,null);
        }
    }

    /**
     * 增加一个
     * @param suggestion
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResultObj addOne(@RequestBody Suggestion suggestion){
        IdWorker idWorker = new IdWorker(0,0);
        long id = idWorker.nextId();
        suggestion.setId(id);
        boolean isSuccess = suggestionService.addOne(suggestion);
        if (isSuccess) {
            Suggestion insertedSuggestion = suggestionService.getById(id);
            return ResponseUtil.success(insertedSuggestion);
        }else {
            return ResponseUtil.error(ErrorCode.ADD_FAIL,ErrorCode.ADD_FAIL_MSG,null);
        }
    }

    /**
     * 全更新一个
     * @param suggestion
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResultObj updateOne(@PathVariable("id") long id, @RequestBody Suggestion suggestion){
        boolean isSuccess = suggestionService.updateOne(suggestion);
        if (isSuccess) {
            Suggestion updatedSuggestion = suggestionService.getById(id);
            return ResponseUtil.success(updatedSuggestion);
        }else {
            return ResponseUtil.error(ErrorCode.UPDATE_FAIL,ErrorCode.UPDATE_FAIL_MSG,null);
        }
    }

    /**
     * 删除一个
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResultObj deleteOne(@PathVariable("id") long id){
        boolean isSuccess = suggestionService.deleteOne(id);
        if (isSuccess) {
            return ResponseUtil.success(null);
        }else {
            return ResponseUtil.error(ErrorCode.DELETE_FAIL,ErrorCode.DELETE_FAIL_MSG,null);
        }
    }
}
