package cn.hfut.huangshan.constants;

/**
 * 用于后端接口返回 返回码 和 返回消息 给前端
 * @author pcy
 */
public class ErrorCode {

    //只要是成功，统一为800，只有其他错误情况才有错误码
    public static final int OK = 800;
    public static final String OK_MSG = "成功";

    public static final int QUERY_FAIL = 801;
    public static final String QUERY_FAIL_MSG = "查询失败";

    public static final int ADD_FAIL= 802;
    public static final String ADD_FAIL_MSG= "添加失败";

    public static final int UPDATE_FAIL= 803;
    public static final String UPDATE_FAIL_MSG= "修改失败";

    public static final int DELETE_FAIL= 804;
    public static final String DELETE_FAIL_MSG= "删除失败";

    public static final int LOGIN_DATA_WRONG = 805;
    public static final String LOGIN_DATA_WRONG_MSG = "账号或密码错误";

    public static final int IDENTITY_INVALIDATION = 806;
    public static final String IDENTITY_INVALIDATION_MSG = "身份失效，请重新登陆";

    public static final int IDENTITY_WRONG = 807;
    public static final String IDENTITY_WRONG_MSG = "身份错误，请重新登陆";

    public static final int NO_LOGIN = 808;
    public static final String NO_LOGIN_MSG= "未登录";

    public static final int ADMIN_LOGIN = 809;
    public static final String ADMIN_LOGIN_MSG= "管理员登录";

    public static final int TOURIST_LOGIN = 810;
    public static final String TOURIST_LOGIN_MSG= "游客登录";

    public static final int VERIFICATION_CODE_GET_FAIL = 811;
    public static final String VERIFICATION_CODE_GET_FAIL_MSG= "验证码获取失败";

    public static final int VERIFICATION_CODE_OUT_DATE = 812;
    public static final String VERIFICATION_CODE_OUT_DATE_MSG= "验证码已过期";

    public static final int VERIFICATION_CODE_WRONG = 812;
    public static final String VERIFICATION_CODE_WRONG_MSG= "验证码错误";


}
