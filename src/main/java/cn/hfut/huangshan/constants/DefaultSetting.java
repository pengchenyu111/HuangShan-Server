package cn.hfut.huangshan.constants;

/**
 * 默认的设置
 * @author pcy
 */
public class DefaultSetting {

    //测试用管理员默认的明文密码，之后应该是账号后六位，这样安全一点
    public static final String DEFAULT_PASSWORD = "hs123456";
    //默认盐值
    public static final String DEFAULT_PASSWORD_SALT = "hfuthuangshan";

    //管理员默认头像路径
    public static final  String DEFAULT_ADMIN_HEAD_ICON = "http://101.37.173.73:8000/user/default-headicon.png";

    //登录路径
    public static final String LOGIN_URL = "http://localhost:8080/login";

    //游客注册路径
    public static final String TOURIST_REGISTER_URL = "http://localhost:8080/tourist/register";

}
