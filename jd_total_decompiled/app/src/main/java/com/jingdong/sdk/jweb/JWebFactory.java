package com.jingdong.sdk.jweb;

import android.content.Context;
import com.jingdong.sdk.jweb.x5.e;

/* loaded from: classes7.dex */
public class JWebFactory {
    private static JSContextType curContextType;

    /* loaded from: classes7.dex */
    public interface InitCallback {
        void onFinish(boolean z);
    }

    /* loaded from: classes7.dex */
    public enum JSContextType {
        CT_TYPE_SYS,
        CT_TYPE_X5,
        CT_TYPE_DUMMY
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[JSContextType.values().length];
            a = iArr;
            try {
                iArr[JSContextType.CT_TYPE_SYS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[JSContextType.CT_TYPE_X5.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[JSContextType.CT_TYPE_DUMMY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static JSContext createJSContext(Context context, JSContextType jSContextType) {
        int i2 = a.a[jSContextType.ordinal()];
        JSContext a2 = i2 != 1 ? i2 != 2 ? null : e.a().a(context) : com.jingdong.sdk.jweb.sys.c.a().a(context);
        if (a2 == null) {
            a2 = com.jingdong.sdk.jweb.sys.c.a().a(context);
            jSContextType = JSContextType.CT_TYPE_SYS;
        }
        a2.init();
        curContextType = jSContextType;
        return a2;
    }

    public static void initJSContextCore(Context context, JSContextType jSContextType, InitCallback initCallback) {
        if (a.a[jSContextType.ordinal()] != 1) {
            e.a().a(context, initCallback);
        } else {
            com.jingdong.sdk.jweb.sys.c.a().a(context, initCallback);
        }
    }

    public static boolean isCoreReady(JSContextType jSContextType) {
        int i2 = a.a[jSContextType.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    return false;
                }
                return com.jingdong.sdk.jweb.e.a.a().b();
            }
            return e.a().b();
        }
        return com.jingdong.sdk.jweb.sys.c.a().b();
    }

    public JSContextType getCurContextType() {
        return curContextType;
    }
}
