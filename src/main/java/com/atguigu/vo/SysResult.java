package com.atguigu.vo;

import lombok.*;

/**
 * @Author: ljg
 * @Date: 2025/9/9 3:45 PM Tuesday
 * @Description:
 */
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SysResult {
    private Boolean flag;
    private String message;
    private Object data;


    public static SysResult success() {
        return new SysResult(true, "业务调用成功", null);
    }
    public static SysResult success(Object data) {
        return new SysResult(true, "业务调用成功", data);
    }
    public static SysResult success(String message, Object data) {
        return new SysResult(true, message, data);
    }
    public static SysResult fail() {
        return new SysResult(false, "业务调用失败", null);
    }

    public static SysResult fail(Object data) {
        return new SysResult(false, "业务调用失败", data);
    }

    public static SysResult fail(String message, Object data) {
        return new SysResult(false, message, data);
    }
}
