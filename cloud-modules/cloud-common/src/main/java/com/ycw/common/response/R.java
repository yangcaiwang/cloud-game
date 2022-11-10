package com.ycw.common.response;

import com.ycw.common.constant.Constants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Classname R
 * @Description 响应信息主体
 */
@Setter
@Getter
@ToString
@Accessors(chain = true)
public class R implements Serializable {
    private static final long serialVersionUID = 1L;
    private int code;
    private String msg;
    private Object data;

    public static R ok() {
        R r = new R();
        r.setCode(Constants.SUCCESS);
        r.setMsg("操作成功");
        return r;
    }

    public static R ok(Object data) {
        R r = new R();
        r.setCode(Constants.SUCCESS);
        r.setMsg("操作成功");
        r.setData(data);
        return r;
    }

    public static R error() {
        return error(Constants.FAIL, "未知异常，请联系管理员");
    }

    public static R error(String msg) {
        return error(Constants.FAIL, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }
}
