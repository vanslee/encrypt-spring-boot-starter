package com.ldx.encrypt.result;

public abstract  class  Result<T,E> {


public abstract String getMsg();

public abstract<T> T setMsg(String msg);

public abstract<T> T getData();

public abstract T setData(E data);
        }
