package com.ldx.encrypt.result;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Res<T> {
    private int code;
    private String msg;
    private T data;
    @JsonIgnore
    public boolean isSuccess() {
        return code == 200;
    }

    public static <T> Res<T> success(T t) {
        return new Res<T>()
                .setCode(200)
                .setMsg("")
                .setData(t);
    }

    public static <T> Res<T> success() {
        return new Res<T>()
                .setCode(200)
                .setMsg("");
    }

    public static <T> Res<T> error(String error) {
        return error(500, error);
    }

    public static <T> Res<T> error(int code, String error) {
        return new Res<T>()
                .setCode(code)
                .setMsg(error);
    }

    public static <T> Res<T> error(int code, String error, Object... format) {
        return new Res<T>()
                .setCode(code)
                .setMsg(String.format(error, format));
    }

    public static <T> Res<T> error(ServiceException e) {
        return new Res<T>()
                .setCode(e.getCode())
                .setMsg(e.getMessage());
    }

    public static <T> Res<T> error(Exception e) {
        return new Res<T>()
                .setCode(500)
                .setMsg(e.getMessage());
    }


    public static <T> Res<T> error(int code) {
        return new Res<T>()
                .setCode(code)
                .setMsg("Unknown Error");

    }


    public int getCode() {
        return code;
    }

    public Res<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Res<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public Res<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "Res{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

}
