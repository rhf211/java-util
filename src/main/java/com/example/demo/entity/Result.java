package com.example.demo.entity;



import java.io.Serializable;



public class Result<T> implements Serializable {

    private static final long serialVersionUID = -8782333365744933352L;
    /**
     * code:响应编码
     */
     int code;

    /**
     * code:响应信息
     */
     String message;

    /**
     * code:结果
     */
     boolean success = true;

    /**
     * code:响应数据
     */
     T data;

    public Result() {

    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(int code, T data, String message) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> error() {
        return error("未知异常，请联系管理员");
    }

    public static <T> Result<T> error(String message) {
        return error(500, message);
    }

    public static <T> Result<T> error(int code, String message) {
        Result result = new Result(code, message);
        result.setSuccess(false);
        return result;
    }

    public static <T> Result<T> error(int code, T data, String message) {
        Result result = new Result(code, data, message);
        result.setSuccess(false);
        return result;
    }

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        return success(data, "处理成功");
    }

    public  static <T> Result<T> success(T data, String message) {
        return new Result(0, data, message);
    }

    public  int getCode() {
        return code;
    }

    public  void setCode(int code) {
        this.code = code;
    }

    public  String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public  boolean getSuccess() {
        return success;
    }

    public  void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public  void setData(T data) {
        this.data = data;
    }
}

