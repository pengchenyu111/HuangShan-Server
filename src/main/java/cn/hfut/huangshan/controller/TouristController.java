package cn.hfut.huangshan.controller;

import cn.hfut.huangshan.constants.ErrorCode;
import cn.hfut.huangshan.pojo.DB.DBTourist;
import cn.hfut.huangshan.pojo.Tourist;
import cn.hfut.huangshan.response.ResultObj;
import cn.hfut.huangshan.service.TouristService;
import cn.hfut.huangshan.utils.IdWorker;
import cn.hfut.huangshan.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 游客接口
 * @author pcy
 */
@RestController
@RequestMapping("tourists")
public class TouristController {

    @Autowired
    TouristService touristService;

    /**
     * 查询所有游客
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResultObj getAllTourists(){
        List<Tourist> tourists = touristService.getAllTourists();
        if (tourists.size() > 0){
            return ResponseUtil.success(tourists);
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
        Tourist tourist = touristService.getById(id);
        if (tourist != null){
            return ResponseUtil.success(tourist);
        }else {
            return ResponseUtil.error(ErrorCode.QUERY_FAIL,ErrorCode.QUERY_FAIL_MSG,null);
        }
    }


    /**
     * 注册一个
     * 这里要发验证码，之前一定要先调用验证码接口
     * @param map
     * @return
     */
    @RequestMapping(value = "/registers", method = RequestMethod.POST)
    public ResultObj registerOne(@RequestBody Map<String, String> map){
        String phone = map.get("phone");
        String password = map.get("password");
        String verificationCode = map.get("verificationCode");
        long id = new IdWorker().nextId();
        String tag = touristService.registerOne(id,phone,password,verificationCode);
        if (tag.equals("0")){
            //注册成功
            Tourist registeredTourist = touristService.getById(id);
            return ResponseUtil.success(registeredTourist);
        }else if (tag.equals("1")){
            //验证码过期
            return ResponseUtil.error(ErrorCode.VERIFICATION_CODE_OUT_DATE,ErrorCode.VERIFICATION_CODE_OUT_DATE_MSG,null);
        }else if (tag.equals("2")){
            //验证码错误
            return ResponseUtil.error(ErrorCode.VERIFICATION_CODE_WRONG,ErrorCode.VERIFICATION_CODE_WRONG_MSG,null);
        }else if (tag.equals("4")){
            //该账号已注册
            return ResponseUtil.error(ErrorCode.PHONE_HAS_REGISTERED,ErrorCode.PHONE_HAS_REGISTERED_MSG,null);
        }else {
            return ResponseUtil.error(ErrorCode.ADD_FAIL,ErrorCode.ADD_FAIL_MSG,null);
        }
    }

    /**
     * 增加一个
     * 这个不用发验证码，可用于管理员直接加一个游客
     * @param dbTourist
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResultObj addOne(@RequestBody DBTourist dbTourist){
        IdWorker idWorker = new IdWorker();
        long id = idWorker.nextId();
        dbTourist.setId(id);
        boolean isSuccess = touristService.addOne(dbTourist);
        if (isSuccess){
            Tourist tourist = touristService.getById(id);
            return ResponseUtil.success(tourist);
        }else {
            return ResponseUtil.error(ErrorCode.ADD_FAIL,ErrorCode.ADD_FAIL_MSG,null);
        }
    }


    /**
     * 更新个人资料
     * @param id
     * @param tourist
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResultObj updateOne(@PathVariable("id") long id, @RequestBody Tourist tourist){
        boolean isSuccess = touristService.updateOne(tourist);
        if (isSuccess){
            Tourist updatedTourist = touristService.getById(id);
            return ResponseUtil.success(updatedTourist);
        }else {
            return ResponseUtil.error(ErrorCode.UPDATE_FAIL,ErrorCode.UPDATE_FAIL_MSG,null);
        }
    }

    /**
     * 修改密码
     * @param id
     * @return
     */
    @RequestMapping(value = "/passwords/{id}", method = RequestMethod.PUT)
    public ResultObj changePassword(@PathVariable("id") long id, @RequestBody Map<String, String> map){
        //两次输入不一致之类的在前端校验
        String newPassword = map.get("password");
        boolean isSuccess = touristService.changePassword(id,newPassword);
        if (isSuccess){
            Tourist updatedTourist = touristService.getById(id);
            return ResponseUtil.success(updatedTourist);
        }else {
            return ResponseUtil.error(ErrorCode.UPDATE_FAIL,ErrorCode.UPDATE_FAIL_MSG,null);
        }
    }


    /**
     * 修改头像
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/headicons/{id}", method = RequestMethod.PUT)
    public ResultObj changeHeadIcon(@PathVariable("id") long id, @RequestBody Map<String, String> map){
        String headIconUrl = map.get("headIcon");
        boolean isSuccess = touristService.changeHeadIcon(id,headIconUrl);
        if (isSuccess){
            return ResponseUtil.success(null);
        }else {
            return ResponseUtil.error(ErrorCode.DELETE_FAIL,ErrorCode.DELETE_FAIL_MSG,null);
        }
    }

    /**
     * 删除一个
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResultObj deleteOne(@PathVariable("id") long id){
        boolean isSuccess = touristService.deleteOne(id);
        if (isSuccess){
            return ResponseUtil.success(null);
        }else {
            return ResponseUtil.error(ErrorCode.DELETE_FAIL,ErrorCode.DELETE_FAIL_MSG,null);
        }
    }
}
