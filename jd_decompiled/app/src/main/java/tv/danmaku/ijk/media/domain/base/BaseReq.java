package tv.danmaku.ijk.media.domain.base;

import java.util.Map;

/* loaded from: classes11.dex */
public abstract class BaseReq {
    public static final int REQUEST_GET = 1;
    public static final int REQUEST_POST = 2;

    /* loaded from: classes.dex */
    public @interface ReqMethod {
    }

    public abstract Map<String, Object> getParams();

    @ReqMethod
    public abstract int getRequestMethod();
}
