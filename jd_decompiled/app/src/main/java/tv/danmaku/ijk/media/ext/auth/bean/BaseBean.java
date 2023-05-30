package tv.danmaku.ijk.media.ext.auth.bean;

import java.io.Serializable;

/* loaded from: classes11.dex */
public class BaseBean<T> implements Serializable {
    public String bizCode;
    public String code;
    public T data;
    public String message;

    public boolean isSuccess() {
        return "0".equals(this.code);
    }
}
