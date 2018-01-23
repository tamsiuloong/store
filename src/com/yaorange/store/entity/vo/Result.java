package com.yaorange.store.entity.vo;

import java.io.Serializable;

/**
 * @author coach tam
 * @date 2018/1/23
 */
public class Result implements Serializable {
    private Boolean ok;
    private String msg;
    private Object data;

    public Result(Boolean ok, Object data) {
        this.ok = ok;
        this.data = data;
    }

    public Result() {
    }

    public Result(Boolean ok) {
        this.ok = ok;
    }

    public Result(Boolean ok, String msg) {
        this.ok = ok;
        this.msg = msg;
    }

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
