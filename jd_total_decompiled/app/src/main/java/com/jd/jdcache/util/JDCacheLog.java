package com.jd.jdcache.util;

import androidx.annotation.Keep;
import com.jd.jdcache.JDCacheLogger;
import com.jd.jdcache.JDCacheParamsProvider;
import com.jd.jdcache.JDCacheSetting;
import com.jingdong.app.mall.e;
import com.jingdong.jdsdk.constant.JshopConst;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\b\b\u00c7\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0019\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J#\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\u0005\u0010\bJ#\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016\u00a2\u0006\u0004\b\u0005\u0010\u000bJ-\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016\u00a2\u0006\u0004\b\u0005\u0010\fJ\u0019\u0010\r\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\r\u0010\u0006J#\u0010\r\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\r\u0010\bJ#\u0010\r\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016\u00a2\u0006\u0004\b\r\u0010\u000bJ-\u0010\r\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016\u00a2\u0006\u0004\b\r\u0010\fJ\u0019\u0010\u000e\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\u000e\u0010\u0006J#\u0010\u000e\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\u000e\u0010\bJ#\u0010\u000e\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000bJ-\u0010\u000e\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016\u00a2\u0006\u0004\b\u000e\u0010\fR$\u0010\u000f\u001a\u0004\u0018\u00010\u00018\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0015\u001a\u00020\u00028\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016R$\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00178F@BX\u0086\u000e\u00a2\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\u00a8\u0006\u001f"}, d2 = {"Lcom/jd/jdcache/util/JDCacheLog;", "Lcom/jd/jdcache/JDCacheLogger;", "", "msg", "", "d", "(Ljava/lang/String;)V", "tag", "(Ljava/lang/String;Ljava/lang/String;)V", "", "t", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V", JshopConst.JSHOP_PROMOTIO_W, e.a, "myLogger", "Lcom/jd/jdcache/JDCacheLogger;", "getMyLogger", "()Lcom/jd/jdcache/JDCacheLogger;", "setMyLogger", "(Lcom/jd/jdcache/JDCacheLogger;)V", "LOG_HYBRID", "Ljava/lang/String;", "", "<set-?>", "canLog", "Z", "getCanLog", "()Z", "<init>", "()V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public final class JDCacheLog implements JDCacheLogger {
    public static final JDCacheLog INSTANCE = new JDCacheLog();
    private static final String LOG_HYBRID = "JDCache";
    private static boolean canLog;
    @Nullable
    private static JDCacheLogger myLogger;

    private JDCacheLog() {
    }

    @Override // com.jd.jdcache.JDCacheLogger
    public void d(@Nullable String msg) {
        JDCacheLogger jDCacheLogger;
        if (msg == null || (jDCacheLogger = myLogger) == null) {
            return;
        }
        jDCacheLogger.d(msg);
    }

    @Override // com.jd.jdcache.JDCacheLogger
    public void e(@Nullable String msg) {
        JDCacheLogger jDCacheLogger;
        if (msg == null || (jDCacheLogger = myLogger) == null) {
            return;
        }
        jDCacheLogger.e(msg);
    }

    public final boolean getCanLog() {
        JDCacheSetting jDCacheSetting = JDCacheSetting.INSTANCE;
        if (jDCacheSetting.getDebug()) {
            return true;
        }
        JDCacheParamsProvider paramsProvider = jDCacheSetting.getParamsProvider();
        return paramsProvider != null && paramsProvider.showLog();
    }

    @Nullable
    public final JDCacheLogger getMyLogger() {
        return myLogger;
    }

    public final void setMyLogger(@Nullable JDCacheLogger jDCacheLogger) {
        myLogger = jDCacheLogger;
    }

    @Override // com.jd.jdcache.JDCacheLogger
    public void w(@Nullable String msg) {
        JDCacheLogger jDCacheLogger;
        if (msg == null || (jDCacheLogger = myLogger) == null) {
            return;
        }
        jDCacheLogger.w(msg);
    }

    @Override // com.jd.jdcache.JDCacheLogger
    public void d(@Nullable String tag, @Nullable String msg) {
        if (msg != null) {
            JDCacheLogger jDCacheLogger = myLogger;
            if (jDCacheLogger != null) {
                jDCacheLogger.d(tag, msg);
                return;
            }
            String str = "JDCache-" + tag;
        }
    }

    @Override // com.jd.jdcache.JDCacheLogger
    public void e(@Nullable String tag, @Nullable String msg) {
        if (msg != null) {
            JDCacheLogger jDCacheLogger = myLogger;
            if (jDCacheLogger != null) {
                jDCacheLogger.e(tag, msg);
                return;
            }
            String str = "JDCache-" + tag;
        }
    }

    @Override // com.jd.jdcache.JDCacheLogger
    public void w(@Nullable String tag, @Nullable String msg) {
        if (msg != null) {
            JDCacheLogger jDCacheLogger = myLogger;
            if (jDCacheLogger != null) {
                jDCacheLogger.w(tag, msg);
                return;
            }
            String str = "JDCache-" + tag;
        }
    }

    @Override // com.jd.jdcache.JDCacheLogger
    public void d(@Nullable String tag, @Nullable Throwable t) {
        if (t != null) {
            JDCacheLogger jDCacheLogger = myLogger;
            if (jDCacheLogger != null) {
                jDCacheLogger.d(tag, t);
                return;
            }
            String str = "JDCache-" + tag;
            t.getMessage();
        }
    }

    @Override // com.jd.jdcache.JDCacheLogger
    public void e(@Nullable String tag, @Nullable Throwable t) {
        if (t != null) {
            JDCacheLogger jDCacheLogger = myLogger;
            if (jDCacheLogger != null) {
                jDCacheLogger.e(tag, t);
                return;
            }
            String str = "JDCache-" + tag;
            t.getMessage();
        }
    }

    @Override // com.jd.jdcache.JDCacheLogger
    public void w(@Nullable String tag, @Nullable Throwable t) {
        JDCacheLogger jDCacheLogger = myLogger;
        if (jDCacheLogger != null) {
            jDCacheLogger.w(tag, t);
            return;
        }
        String str = "JDCache-" + tag;
    }

    @Override // com.jd.jdcache.JDCacheLogger
    public void d(@Nullable String tag, @Nullable String msg, @Nullable Throwable t) {
        JDCacheLogger jDCacheLogger = myLogger;
        if (jDCacheLogger != null) {
            jDCacheLogger.d(tag, msg, t);
            return;
        }
        String str = "JDCache-" + tag;
    }

    @Override // com.jd.jdcache.JDCacheLogger
    public void e(@Nullable String tag, @Nullable String msg, @Nullable Throwable t) {
        JDCacheLogger jDCacheLogger = myLogger;
        if (jDCacheLogger != null) {
            jDCacheLogger.e(tag, msg, t);
            return;
        }
        String str = "JDCache-" + tag;
    }

    @Override // com.jd.jdcache.JDCacheLogger
    public void w(@Nullable String tag, @Nullable String msg, @Nullable Throwable t) {
        JDCacheLogger jDCacheLogger = myLogger;
        if (jDCacheLogger != null) {
            jDCacheLogger.w(tag, msg, t);
            return;
        }
        String str = "JDCache-" + tag;
    }
}
