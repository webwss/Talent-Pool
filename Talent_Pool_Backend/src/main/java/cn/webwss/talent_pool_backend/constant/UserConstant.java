package cn.webwss.talent_pool_backend.constant;

public interface UserConstant {

    /**
     * 验证码缓存键
     */
    String CODE_KEY = "sms:code:";
    /**
     * 验证码冷却键
     */
    String CODE_DOWN_KEY = "sms:coolDown:";

    /**
     * 员工登录态键
     */
    String STAFF_LOGIN_STATE = "login:staff:login";




    //  region 权限

    /**
     * 默认角色
     */
    String DEFAULT_ROLE = "hr";

    /**
     * 管理员角色
     */
    String ADMIN_ROLE = "admin";

    // endregion
}
