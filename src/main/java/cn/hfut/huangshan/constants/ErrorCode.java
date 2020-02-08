package cn.hfut.huangshan.constants;

/**
 * 用于后端接口返回 返回码 和 返回消息 给前端
 * @author pcy
 */
public class ErrorCode {

    //只要是成功，统一为800，只有其他错误情况才有错误码
    public static final int OK = 800;
    public static final String OK_MSG = "成功";

    public static final int QUERY_NULL= 801;
    public static final String QUERY_NULL_MSG= "查询结果为空";

    public static final int ADD_FAIL= 802;
    public static final String ADD_FAIL_MSG= "添加失败";

    public static final int UPDATE_FAIL= 803;
    public static final String UPDATE_FAIL_MSG= "修改失败";

    public static final int DELETE_FAIL= 804;
    public static final String DELETE_FAIL_MSG= "删除失败";

    public static final int LOGIN_DATA_WRONG= 805;
    public static final String LOGIN_DATA_WRONG_MSG= "账号或密码错误";



}
