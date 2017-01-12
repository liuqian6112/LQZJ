package com.lqzj.common.exception;

import org.springframework.http.HttpStatus;

public enum  ErrorCode {

    // * 0-1000 system error
    UNKNOWN(0, HttpStatus.BAD_REQUEST, "系统未知错误"),
    SYSTEM_ERROR(1, HttpStatus.INTERNAL_SERVER_ERROR, "系统错误"),
    DATABASE_ERROR(2, HttpStatus.INTERNAL_SERVER_ERROR, "数据库错误"),
    NOT_PERMITTED(3, HttpStatus.FORBIDDEN, "不允许访问"),
    UNAUTHORIZED(4, HttpStatus.UNAUTHORIZED, "未授权的访问"),
    SERVER_UPLOAD_PATH_NOT_READY(5, HttpStatus.INTERNAL_SERVER_ERROR, "服务器文件上传路径不存在"),
    BEAN_VALIDATION_ERROR(6, HttpStatus.UNPROCESSABLE_ENTITY, "数据校验错误"),
    PARAMETER_NOT_NULL(7, HttpStatus.BAD_REQUEST, "参数不能为空"),

    UPLOAD_FILE_EMPTY(10001, HttpStatus.BAD_REQUEST, "上传文件不能为空"),
    UPLOAD_FILE_ERROR(10002, HttpStatus.BAD_REQUEST, "上传文件出错"),
    ILLEGAL_UPLOAD_NAME(10003, HttpStatus.BAD_REQUEST, "非法文件模块名");

    private final int code;
    private final HttpStatus status;
    private final String message;

    ErrorCode(int code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorCode{"
                + "code=" + code
                + ", status=" + status
                + ", message='" + message + '\''
                + '}';
    }
}
