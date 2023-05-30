package com.jdcloud.mcdnsdk;

import android.os.Build;
import android.os.Process;
import android.util.Pair;
import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import tv.danmaku.ijk.media.JDPlayerSdk;
import tv.danmaku.ijk.media.ext.config.DynamicLibInfoBean;
import tv.danmaku.ijk.media.ext.dynamic.RemoteSoLoader;

/* loaded from: classes18.dex */
public class McdnManager {
    private static final Pair<String, List<String>> a = new Pair<>("mcdn", Arrays.asList("libcrypto.so", "libssl.so", "libevent.so", "libmcdnsdk.so"));
    private static b b = b.IDLE;

    /* renamed from: c  reason: collision with root package name */
    private static final McdnManager f7271c = new McdnManager();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes18.dex */
    public class a implements RemoteSoLoader.RemoteSoLoaderCallback {
        final /* synthetic */ String a;

        a(String str) {
            this.a = str;
        }

        @Override // tv.danmaku.ijk.media.ext.dynamic.RemoteSoLoader.RemoteSoLoaderCallback
        public void onLoadResult(boolean z) {
            if (McdnManager.b != b.INIT && z) {
                try {
                    String str = JDPlayerSdk.getInstance().getApplicationContext().getDir("libs", 0).getAbsolutePath() + File.separator;
                    Iterator it = ((List) McdnManager.a.second).iterator();
                    while (it.hasNext()) {
                        System.load(str + ((String) it.next()));
                    }
                    int JniInit = McdnManager.this.JniInit(this.a);
                    String str2 = "McdnManager JniInit, ret=" + JniInit;
                    if (JniInit == 0) {
                        b unused = McdnManager.b = b.INIT;
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes18.dex */
    public enum b {
        IDLE,
        INIT,
        RESET
    }

    public static boolean b() {
        if (Build.VERSION.SDK_INT >= 23) {
            return Process.is64Bit();
        }
        return false;
    }

    public static McdnManager g() {
        return f7271c;
    }

    public native String JniGetSourceInfo(String str);

    public native int JniInit(String str);

    public native String JniMcdnGenerateUrl(String str);

    public native void JniRelease();

    public native void JniSetEnv(String str);

    public void a(String str, DynamicLibInfoBean dynamicLibInfoBean) {
        if (Build.VERSION.SDK_INT < 23 || !b() || b == b.INIT || dynamicLibInfoBean == null || dynamicLibInfoBean.getArchInfo() == null) {
            return;
        }
        try {
            RemoteSoLoader.getInstance().loadDeclareSo(JDPlayerSdk.getInstance().getApplicationContext(), a, dynamicLibInfoBean, new a(str));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public String c(String str) {
        String JniMcdnGenerateUrl;
        return (b == b.INIT && Build.VERSION.SDK_INT >= 23 && b() && (JniMcdnGenerateUrl = JniMcdnGenerateUrl(str)) != null) ? JniMcdnGenerateUrl : str;
    }
}
