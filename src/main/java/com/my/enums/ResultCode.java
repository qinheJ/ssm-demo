package com.my.enums;

/**
 * @author QinHe at 2019-08-01
 * 枚举了一些常用API操作码
 */
public enum ResultCode {
    SUCCESS("success", "操作成功"),
    FAILED("failed", "操作失败"),
    VALIDATE_FAILED("param_validate_failed", "参数检验失败"),
    UNAUTHORIZED("unauthorized", "暂未登录或session已经过期"),
    FORBIDDEN("forbidden", "没有相关权限");
    private String code;
    private String message;

    private ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
