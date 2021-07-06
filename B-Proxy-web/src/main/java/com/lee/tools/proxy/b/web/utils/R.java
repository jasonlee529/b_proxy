package com.lee.tools.proxy.b.web.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @Title: R
 * @Description:
 * @author: libo
 * @date: 2021/7/7 0007 1:18
 * @Version: 1.0
 */
@Slf4j
@Data
public class R<T> implements Serializable {

    private static final long serialVersionUID = 9208045119919441860L;
    /**
     * 返回请求编码：0成功；其他失败
     */
    private Integer code = 0;
    /**
     * 错误提示消息，正确返回null
     */
    private String msg = "ok";
    /**
     * 返回具体的业务数据
     */
    private T data;

    /**
     * 成功应答
     *
     * @return 成功应答
     */
    public static <T> R<T> ok(T data) {
        R<T> response = new R<>();
        response.setCode(0);
        response.setData(data);
        return response;
    }

    /**
     * 成功应答
     *
     * @return 成功应答
     */
    public static <T> R<T> ok() {
        R<T> response = new R<>();
        response.setCode(0);
        return response;
    }

    /**
     * 失败应答
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> R<T> fail(String message) {
        R<T> response = new R<>();
        response.setCode(1);
        response.setMsg(message);
        return response;
    }

}
