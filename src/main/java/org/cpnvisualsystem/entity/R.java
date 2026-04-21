package org.cpnvisualsystem.entity;
import lombok.Data;

@Data
public class R<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <T> R<T> ok(T data) {
        R<T> r = new R<>();
        r.setCode(200);
        r.setMsg("获取成功");
        r.setData(data);
        return r;
    }

    public static R<?> error(Integer code, String msg) {
        R<?> r = new R<>();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }
}