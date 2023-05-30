package com.jingdong.app.mall.g;

import android.os.AsyncTask;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.threadpool.utils.LogUtil;
import java.lang.reflect.Field;

/* loaded from: classes19.dex */
public class f {
    private static boolean a;
    private static int b;

    public static String a() {
        StringBuilder sb = new StringBuilder("{");
        sb.append("successHookVivoAsyncTask:" + a);
        sb.append(a.c());
        sb.append(c.c());
        sb.append("}");
        return sb.toString();
    }

    private static void b() {
        boolean equals = JDMobileConfig.getInstance().getConfig(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, "hookVivoAsyncTask", "hookVivoAsyncTaskEnable", "0").equals("1");
        LogUtil.e("SysBugHelper", "hookVivoAsyncTaskEnable:" + equals);
        if (equals && g.c()) {
            try {
                d(AsyncTask.class.getDeclaredField("SERIAL_EXECUTOR"), new e());
                Field declaredField = AsyncTask.class.getDeclaredField("sDefaultExecutor");
                declaredField.setAccessible(true);
                declaredField.set(null, AsyncTask.SERIAL_EXECUTOR);
                LogUtil.e("SysBugHelper", "handleVivoAbsListViewIssue successfully!!!");
                a = true;
            } catch (Exception e2) {
                LogUtil.e("SysBugHelper", "handleVivoAbsListViewIssue failed!!!", LogUtil.extractThrowableInfo(e2));
                a = false;
            }
        }
    }

    public static synchronized void c() {
        int i2;
        synchronized (f.class) {
            try {
                if (!a && (i2 = b) < 2) {
                    b = i2 + 1;
                    b();
                }
            } catch (Exception e2) {
                LogUtil.i("SysBugHelper", "init()", LogUtil.extractThrowableInfo(e2));
            }
            a.b();
            c.b(JdSdk.getInstance().getApplicationContext());
        }
    }

    private static void d(Field field, Object obj) throws Exception {
        try {
            Field declaredField = Field.class.getDeclaredField("artField");
            declaredField.setAccessible(true);
            Object obj2 = declaredField.get(field);
            Field declaredField2 = obj2.getClass().getDeclaredField("accessFlags");
            declaredField2.setAccessible(true);
            declaredField2.setInt(obj2, field.getModifiers() & (-17));
            field.set(null, obj);
        } catch (Exception unused) {
            field.setAccessible(true);
            Field declaredField3 = field.getClass().getDeclaredField("accessFlags");
            declaredField3.setAccessible(true);
            declaredField3.setInt(field, field.getModifiers() & (-17));
            field.set(null, obj);
        }
    }
}
