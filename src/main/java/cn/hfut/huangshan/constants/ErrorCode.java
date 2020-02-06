package cn.hfut.huangshan.constants;

/**
 * 用于后端接口返回 返回码 和 返回消息 给前端
 * @author pcy
 */
public class ErrorCode {

    //只要是成功，统一为800，只有其他错误情况才有错误码
    public static final int OK = 800;
    public static final String OK_MSG = "成功";

    public static final int LOGIN_DATA_WRONG= 801;
    public static final String LOGIN_DATA_WRONG_MSG= "账号或密码错误";

    public static final int LOGIN_DATA_NULL= 802;
    public static final String LOGIN_DATA_NULL_MSG= "账号或密码不能为空";

    public static final int LOGIN_ACCOUNT_ILLEGAL= 803;
    public static final String LOGIN_ACCOUNT_ILLEGAL_MSG= "账号含有非法字符";

    public static final int LOGIN_PASSWORD_ILLEGAL= 804;
    public static final String LOGIN_PASSWORD_ILLEGAL_MSG= "密码含有非法字符";

    public static final int DELETE_ADMIN_FAIL= 805;
    public static final String DELETE_ADMIN_FAIL_MSG= "该管理员删除失败";

    public static final int ADD_NOTIFICATION_FAIL= 806;
    public static final String ADD_NOTIFICATION_FAIL_MSG= "添加通知失败";

}
