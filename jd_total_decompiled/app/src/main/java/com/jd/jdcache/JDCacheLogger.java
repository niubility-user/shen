package com.jd.jdcache;

import androidx.annotation.Keep;
import com.jingdong.app.mall.e;
import com.jingdong.jdsdk.constant.JshopConst;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H&\u00a2\u0006\u0004\b\u0005\u0010\u0006J#\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H&\u00a2\u0006\u0004\b\u0005\u0010\bJ#\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\n\u001a\u0004\u0018\u00010\tH&\u00a2\u0006\u0004\b\u0005\u0010\u000bJ-\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\n\u001a\u0004\u0018\u00010\tH&\u00a2\u0006\u0004\b\u0005\u0010\fJ\u0019\u0010\r\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H&\u00a2\u0006\u0004\b\r\u0010\u0006J#\u0010\r\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H&\u00a2\u0006\u0004\b\r\u0010\bJ#\u0010\r\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\n\u001a\u0004\u0018\u00010\tH&\u00a2\u0006\u0004\b\r\u0010\u000bJ-\u0010\r\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\n\u001a\u0004\u0018\u00010\tH&\u00a2\u0006\u0004\b\r\u0010\fJ\u0019\u0010\u000e\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H&\u00a2\u0006\u0004\b\u000e\u0010\u0006J#\u0010\u000e\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H&\u00a2\u0006\u0004\b\u000e\u0010\bJ#\u0010\u000e\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\n\u001a\u0004\u0018\u00010\tH&\u00a2\u0006\u0004\b\u000e\u0010\u000bJ-\u0010\u000e\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\n\u001a\u0004\u0018\u00010\tH&\u00a2\u0006\u0004\b\u000e\u0010\f\u00a8\u0006\u000f"}, d2 = {"Lcom/jd/jdcache/JDCacheLogger;", "", "", "msg", "", "d", "(Ljava/lang/String;)V", "tag", "(Ljava/lang/String;Ljava/lang/String;)V", "", "t", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V", JshopConst.JSHOP_PROMOTIO_W, e.a, "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public interface JDCacheLogger {
    void d(@Nullable String msg);

    void d(@Nullable String tag, @Nullable String msg);

    void d(@Nullable String tag, @Nullable String msg, @Nullable Throwable t);

    void d(@Nullable String tag, @Nullable Throwable t);

    void e(@Nullable String msg);

    void e(@Nullable String tag, @Nullable String msg);

    void e(@Nullable String tag, @Nullable String msg, @Nullable Throwable t);

    void e(@Nullable String tag, @Nullable Throwable t);

    void w(@Nullable String msg);

    void w(@Nullable String tag, @Nullable String msg);

    void w(@Nullable String tag, @Nullable String msg, @Nullable Throwable t);

    void w(@Nullable String tag, @Nullable Throwable t);
}
