package com.study.R;

import lombok.Data;

import java.io.Serializable;

@Data
public  class  Result<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    public static Result build() {
        return new Result();
    }
    public Result buildData(T data) {
        this.setData(data);
        return this;
    }
    public Result buildMessage(String message) {
        this.setMessage(message);
        return this;
    }
    public Result buildCode(Integer code) {
        this.setCode(code);
        return this;
    }
}
