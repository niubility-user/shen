package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.export.external.libwebp;
import com.tencent.smtt.utils.TbsLog;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class s {
    private Context a;
    private Context b;

    /* renamed from: c */
    private String f17861c;
    private String[] d;

    /* renamed from: e */
    private DexLoader f17862e;

    /* renamed from: f */
    private String f17863f;

    /* renamed from: g */
    private String f17864g = null;

    public s(Context context, Context context2, String str, String str2, String[] strArr, String str3) throws Exception {
        this.a = null;
        this.b = null;
        this.f17861c = null;
        this.d = null;
        this.f17862e = null;
        this.f17863f = "TbsDexOpt";
        TbsLog.i("TbsWizard", "construction start...");
        if (context == null || context2 == null || TextUtils.isEmpty(str) || strArr == null || strArr.length == 0) {
            throw new Exception("TbsWizard paramter error:-1callerContext:" + context + "hostcontext" + context2 + CartConstant.KEY_GLOBAL_IS_EMPTY + TextUtils.isEmpty(str) + "dexfileList" + strArr);
        }
        this.a = context.getApplicationContext();
        if (context2.getApplicationContext() != null) {
            this.b = context2.getApplicationContext();
        } else {
            this.b = context2;
        }
        this.f17861c = str;
        this.d = strArr;
        this.f17863f = str2;
        for (int i2 = 0; i2 < this.d.length; i2++) {
            TbsLog.i("TbsWizard", "#2 mDexFileList[" + i2 + "]: " + this.d[i2]);
        }
        TbsLog.i("TbsWizard", "new DexLoader #2 libraryPath is " + str3 + " mCallerAppContext is " + this.a + " dexOutPutDir is " + str2);
        this.f17862e = new DexLoader(str3, this.a, this.d, str2, QbSdk.o);
        System.currentTimeMillis();
        a(context);
        libwebp.loadWepLibraryIfNeed(context2, this.f17861c);
        Map<String, Object> map = QbSdk.o;
        if (map == null || !map.containsKey(TbsCoreSettings.MULTI_PROCESS_ENABLE)) {
            HashMap hashMap = new HashMap();
            hashMap.put(TbsCoreSettings.MULTI_PROCESS_ENABLE, 0);
            QbSdk.initTbsSettings(hashMap);
            TbsLog.i("[MultiProcess]", "default not enable multi process!");
        }
        Map<String, Object> map2 = QbSdk.o;
        if (map2 != null) {
            this.f17862e.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "initTbsSettings", new Class[]{Map.class}, map2);
            b(context);
        }
        int c2 = c(context);
        if (c2 >= 0) {
            TbsLog.i("TbsWizard", "construction end...");
            return;
        }
        throw new Exception("TbsWizard init error: " + c2 + "; msg: " + this.f17864g);
    }

    private void b(Context context) {
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences("tbss", 0).edit();
            Map<String, Object> map = QbSdk.o;
            for (String str : map.keySet()) {
                Object obj = map.get(str);
                if (obj instanceof String) {
                    edit.putString(str, (String) obj);
                    TbsLog.i("TbsWizard", "initTbsSettingsBySP key is " + str + " value is " + ((String) obj));
                }
            }
            edit.commit();
        } catch (Throwable th) {
            TbsLog.i("TbsWizard", "stack is " + Log.getStackTraceString(th));
        }
    }

    private int c(Context context) {
        int i2;
        String str;
        TbsLog.i("TbsWizard", "initTesRuntimeEnvironment callerContext is " + context + " mHostContext is " + this.b + " mDexLoader is " + this.f17862e + " mtbsInstallLocation is " + this.f17861c + " mDexOptPath is " + this.f17863f);
        DexLoader dexLoader = this.f17862e;
        Object invokeStaticMethod = dexLoader.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "initTesRuntimeEnvironment", new Class[]{Context.class, Context.class, DexLoader.class, String.class, String.class, String.class, Integer.TYPE, String.class}, context, this.b, dexLoader, this.f17861c, this.f17863f, TbsConfig.TBS_SDK_VERSIONNAME, 44286, QbSdk.a());
        if (invokeStaticMethod == null) {
            d();
            e();
            DexLoader dexLoader2 = this.f17862e;
            invokeStaticMethod = dexLoader2.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "initTesRuntimeEnvironment", new Class[]{Context.class, Context.class, DexLoader.class, String.class, String.class}, context, this.b, dexLoader2, this.f17861c, this.f17863f);
        }
        if (invokeStaticMethod == null) {
            i2 = -3;
        } else if (invokeStaticMethod instanceof Integer) {
            i2 = ((Integer) invokeStaticMethod).intValue();
        } else if (invokeStaticMethod instanceof Throwable) {
            TbsCoreLoadStat.getInstance().a(this.a, 321, (Throwable) invokeStaticMethod);
            i2 = -5;
        } else {
            i2 = -4;
        }
        if (i2 < 0) {
            Object invokeStaticMethod2 = this.f17862e.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "getLoadFailureDetails", new Class[0], new Object[0]);
            if (invokeStaticMethod2 instanceof Throwable) {
                Throwable th = (Throwable) invokeStaticMethod2;
                this.f17864g = "#" + th.getMessage() + "; cause: " + th.getCause() + "; th: " + th;
            }
            str = invokeStaticMethod2 instanceof String ? (String) invokeStaticMethod2 : null;
            return i2;
        }
        this.f17864g = str;
        return i2;
    }

    private void d() {
        this.f17862e.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "setTesSdkVersionName", new Class[]{String.class}, TbsConfig.TBS_SDK_VERSIONNAME);
    }

    private void e() {
        this.f17862e.setStaticField("com.tencent.tbs.tbsshell.TBSShell", "VERSION", 44286);
    }

    public String a() {
        String str = null;
        Object invokeStaticMethod = this.f17862e.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "invokeStaticMethod", new Class[]{Boolean.TYPE, String.class, String.class, Class[].class, Object[].class}, Boolean.TRUE, "com.tencent.smtt.util.CrashTracker", "getCrashExtraInfo", null, new Object[0]);
        if (invokeStaticMethod == null) {
            invokeStaticMethod = this.f17862e.invokeStaticMethod("com.tencent.smtt.util.CrashTracker", "getCrashExtraInfo", null, new Object[0]);
        }
        if (invokeStaticMethod != null) {
            str = String.valueOf(invokeStaticMethod) + " ReaderPackName=" + TbsReaderView.gReaderPackName + " ReaderPackVersion=" + TbsReaderView.gReaderPackVersion;
        }
        return str == null ? "X5 core get nothing..." : str;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void a(Context context) {
        boolean z;
        Map<String, Object> map = QbSdk.o;
        if (map != null) {
            Object obj = map.get(TbsCoreSettings.TBS_SETTINGS_CHECK_TBS_VALIDITY);
            if (obj instanceof Boolean) {
                z = ((Boolean) obj).booleanValue();
                if (z) {
                    return;
                }
                com.tencent.smtt.utils.n.b(context);
                return;
            }
        }
        z = true;
        if (z) {
        }
    }

    public void a(Context context, Context context2, String str, String str2, String[] strArr, String str3) throws Exception {
        this.a = context.getApplicationContext();
        if (this.b.getApplicationContext() != null) {
            this.b = this.b.getApplicationContext();
        }
        this.f17861c = str;
        this.d = strArr;
        this.f17863f = str2;
        libwebp.loadWepLibraryIfNeed(context2, str);
        Map<String, Object> map = QbSdk.o;
        if (map != null) {
            this.f17862e.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "initTbsSettings", new Class[]{Map.class}, map);
        }
        int c2 = c(context);
        if (c2 >= 0) {
            return;
        }
        throw new Exception("continueInit init error: " + c2 + "; msg: " + this.f17864g);
    }

    public boolean a(Context context, String str, String str2, Bundle bundle) {
        Object invokeStaticMethod = this.f17862e.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "installLocalQbApk", new Class[]{Context.class, String.class, String.class, Bundle.class}, context, str, str2, bundle);
        if (invokeStaticMethod == null) {
            return false;
        }
        return ((Boolean) invokeStaticMethod).booleanValue();
    }

    public String b() {
        String str = null;
        Object invokeStaticMethod = this.f17862e.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "invokeStaticMethod", new Class[]{Boolean.TYPE, String.class, String.class, Class[].class, Object[].class}, Boolean.TRUE, "com.tencent.smtt.util.CrashTracker", "getCrashExtraCacheInfo", null, new Object[0]);
        if (invokeStaticMethod == null) {
            invokeStaticMethod = this.f17862e.invokeStaticMethod("com.tencent.smtt.util.CrashTracker", "getCrashExtraCacheInfo", null, new Object[0]);
        }
        if (invokeStaticMethod != null) {
            str = String.valueOf(invokeStaticMethod) + " ReaderPackName=" + TbsReaderView.gReaderPackName + " ReaderPackVersion=" + TbsReaderView.gReaderPackVersion;
        }
        return str == null ? "X5 core get cache nothing..." : str;
    }

    public DexLoader c() {
        return this.f17862e;
    }
}
