package com.study.R;

/**
 * 结果构造
 */
public class ResultBuilder {

    public static Result success(Object data) {
        return Result.build().buildData(data).buildCode(ResultCode.OK.code).buildMessage(ResultCode.OK.desc);
    }

    public static Result success() {
        return Result.build().buildCode(ResultCode.OK.code).buildMessage(ResultCode.OK.desc);
    }

    public static Result success(String msg) {
        return Result.build().buildCode(ResultCode.OK.code).buildMessage(msg);
    }

    public static Result failed() {
        return Result.build().buildCode(ResultCode.ERROR.code).buildMessage(ResultCode.ERROR.desc);
    }

    public static Result failed(String msg) {
        return Result.build().buildCode(ResultCode.ERROR.code).buildMessage(msg);
    }

    public static Result failed(Integer code, String msg) {
        return Result.build().buildCode(code).buildMessage(msg);
    }
}
