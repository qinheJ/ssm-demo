package com.my.model;

import com.my.enums.ResultCode;
import lombok.Data;

/**
 * @author QinHe at 2019-08-01
 */
@Data
public class CommonResult<T> {
    private boolean success;
    private String code;
    private String message;
    private T data;

    protected CommonResult() {
    }

    private CommonResult(boolean success, String code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     */
    public static <T> CommonResult<T> success() {
        return new CommonResult<T>(true, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }

    /**
     * 成功返回结果
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(true, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     */
    public static <T> CommonResult<T> success(String code, T data) {
        return new CommonResult<T>(true, code, ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     */
    public static <T> CommonResult<T> success(String code, String message, T data) {
        return new CommonResult<T>(true, code, message, data);
    }

    /**
     * 失败返回结果
     */
    public static <T> CommonResult<T> failed() {
        return new CommonResult<T>(false, ResultCode.FAILED.getCode(), ResultCode.FAILED.getMessage(), null);
    }

    /**
     * 失败返回结果
     */
    public static <T> CommonResult<T> failed(ResultCode errorCode) {
        return new CommonResult<T>(false, errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     */
    public static <T> CommonResult<T> failed(ResultCode errorCode, T data) {
        return new CommonResult<T>(false, errorCode.getCode(), errorCode.getMessage(), data);
    }

    /**
     * 失败返回结果
     */
    public static <T> CommonResult<T> failed(String message, T data) {
        return new CommonResult<T>(false, ResultCode.FAILED.getCode(), message, data);
    }

    /**
     * 失败返回结果
     */
    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<T>(false, ResultCode.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> CommonResult<T> failed(String code, String message, T data) {
        return new CommonResult<T>(false, code, message, data);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> CommonResult<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> CommonResult<T> validateFailed(String message) {
        return new CommonResult<T>(false, ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> CommonResult<T> unauthorized() {
        return failed(ResultCode.UNAUTHORIZED);
    }

    /**
     * 未授权返回结果
     */
    public static <T> CommonResult<T> forbidden(T data) {
        return failed(ResultCode.FORBIDDEN);
    }

}
