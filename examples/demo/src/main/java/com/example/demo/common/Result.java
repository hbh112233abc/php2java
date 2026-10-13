package com.example.demo.common;

import java.io.Serializable;

import lombok.Data;

@Data
public class Result implements Serializable {
    private static final long serialVersionUID = 1L;
    //状态码
    private int code;
    //消息
    private String msg;
    //数据
    private Object data;

    /**
     * 直接返回成功结果
     * @param data
     * @return Result
     */
    public static Result success(Object data) {
        return success(200,"操作成功",data);
    }

    /**
     * 自定义返回成功结果
     * @param code
     * @param msg
     * @param data
     * @return Result
     */
    public static Result success(int code,String msg,Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    /**
     * 不带结果直接返回成功
     * @return Result
     */
    public static Result success() {
        Result r = new Result();
        r.setCode(200);
        r.setMsg("操作成功");
        return r;
    }

    /**
     * 直接返回失败结果
     * @param msg
     * @return Result
     */
    public static Result error(String msg) {
        return error(400, msg);
    }

    /**
     * 自定义返回失败结果
     * @param code
     * @param msg
     * @param data
     * @return Result
     */
    public static Result error(int code,String msg) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    /**
     * 不带结果直接返回失败
     * @return Result
     */
    public static Result error() {
        Result r = new Result();
        r.setCode(400);
        r.setMsg("操作失败");
        return r;
    }
}
