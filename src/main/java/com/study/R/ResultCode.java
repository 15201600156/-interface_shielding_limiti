package com.study.R;

/**
 * @author zyf
 */
public enum ResultCode {
    /**
     * 返回成功时
     */
    OK(200, "success"),
    /**
     * 返回失败时
     */
    ERROR(500, "error"),

    /**
     * 用户未登录
     */
    NOT_LOGIN(401, "用户未登录"),

    /**
     * 无数据
     */
    NOT_DATA(001, "无数据");
    /**
     * 返回得Code编码值
     */
    public int code;
    /**
     * 编码值的描述
     */
    public String desc;

    /**
     * 默认构造函数
     *
     * @param code 编码值
     * @param desc 编码值的描述
     */
    private ResultCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
