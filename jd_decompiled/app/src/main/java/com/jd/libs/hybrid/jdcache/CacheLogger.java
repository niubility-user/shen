package com.jd.libs.hybrid.jdcache;

import androidx.annotation.Keep;
import com.jd.jdcache.JDCacheLogger;
import com.jd.libs.hybrid.base.util.Log;
import com.jingdong.app.mall.e;
import com.jingdong.jdsdk.constant.JshopConst;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0019\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J#\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\u0005\u0010\bJ-\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016\u00a2\u0006\u0004\b\u0005\u0010\u000bJ#\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016\u00a2\u0006\u0004\b\u0005\u0010\fJ\u0019\u0010\r\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\r\u0010\u0006J#\u0010\r\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\r\u0010\bJ-\u0010\r\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016\u00a2\u0006\u0004\b\r\u0010\u000bJ#\u0010\r\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016\u00a2\u0006\u0004\b\r\u0010\fJ\u0019\u0010\u000e\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\u000e\u0010\u0006J#\u0010\u000e\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\u000e\u0010\bJ-\u0010\u000e\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000bJ#\u0010\u000e\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016\u00a2\u0006\u0004\b\u000e\u0010\f\u00a8\u0006\u0011"}, d2 = {"Lcom/jd/libs/hybrid/jdcache/CacheLogger;", "Lcom/jd/jdcache/JDCacheLogger;", "", "msg", "", "d", "(Ljava/lang/String;)V", "tag", "(Ljava/lang/String;Ljava/lang/String;)V", "", "t", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V", "(Ljava/lang/String;Ljava/lang/Throwable;)V", e.a, JshopConst.JSHOP_PROMOTIO_W, "<init>", "()V", "hybrid_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class CacheLogger implements JDCacheLogger {
    @Override // com.jd.jdcache.JDCacheLogger
    public void d(@Nullable String msg) {
        Log.d(msg);
    }

    @Override // com.jd.jdcache.JDCacheLogger
    public void e(@Nullable String msg) {
        Log.e(msg);
    }

    @Override // com.jd.jdcache.JDCacheLogger
    public void w(@Nullable String msg) {
        Log.w(msg);
    }

    @Override // com.jd.jdcache.JDCacheLogger
    public void d(@Nullable String tag, @Nullable String msg) {
        Log.d(tag, msg);
    }

    @Override // com.jd.jdcache.JDCacheLogger
    public void e(@Nullable String tag, @Nullable String msg) {
        Log.e(tag, msg);
    }

    @Override // com.jd.jdcache.JDCacheLogger
    public void w(@Nullable String tag, @Nullable String msg) {
        Log.w(tag, msg);
    }

    @Override // com.jd.jdcache.JDCacheLogger
    public void d(@Nullable String tag, @Nullable String msg, @Nullable Throwable t) {
        Log.d(tag, msg, t);
    }

    @Override // com.jd.jdcache.JDCacheLogger
    public void e(@Nullable String tag, @Nullable String msg, @Nullable Throwable t) {
        Log.e(tag, msg, t);
    }

    @Override // com.jd.jdcache.JDCacheLogger
    public void w(@Nullable String tag, @Nullable String msg, @Nullable Throwable t) {
        Log.w(tag, msg, t);
    }

    @Override // com.jd.jdcache.JDCacheLogger
    public void d(@Nullable String tag, @Nullable Throwable t) {
        Log.d(tag, t);
    }

    @Override // com.jd.jdcache.JDCacheLogger
    public void e(@Nullable String tag, @Nullable Throwable t) {
        Log.e(tag, t);
    }

    @Override // com.jd.jdcache.JDCacheLogger
    public void w(@Nullable String tag, @Nullable Throwable t) {
        Log.w(tag, t);
    }
}
