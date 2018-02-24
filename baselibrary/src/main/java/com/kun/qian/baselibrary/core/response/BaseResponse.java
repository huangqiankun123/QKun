package com.kun.qian.baselibrary.core.response;

import java.io.Serializable;

/**
 * 返回数据的基类
 * @param <T>
 */

public class BaseResponse<T> implements Serializable{
    private String msg;
    private int code;
    private T rows;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getRows() {
        return rows;
    }

    public void setRows(T rows) {
        this.rows = rows;
    }

    /**
     * 判断请求是否成功
     * @return
     */
    public boolean isSuccess(){
        return code == 0;
    }

    @Override
    public String toString() {
        return "BeanModule{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", rows=" + rows +
                '}';
    }
}
