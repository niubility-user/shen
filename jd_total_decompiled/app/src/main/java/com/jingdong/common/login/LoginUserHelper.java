package com.jingdong.common.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.jdjr.mobilecert.MobileCertConstants;
import com.jingdong.common.ActivityManagerUtil;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.widget.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes.dex */
public class LoginUserHelper {
    private static final String TAG = "LoginUserHelper";
    private static LoginUserHelper loginUserHelper;

    private LoginUserHelper() {
    }

    public static String addAppUpTypeToUrl(String str) {
        boolean isExsitMianActivity = isExsitMianActivity();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        if (isExsitMianActivity) {
            sb.append("&appup_type=2");
            return sb.toString();
        }
        sb.append("&appup_type=1");
        return sb.toString();
    }

    public static void afterSuccess(BaseActivity baseActivity) {
        if (baseActivity == null) {
            return;
        }
        if (!TextUtils.isEmpty(UserUtil.getWJLoginHelper().getPin()) && !TextUtils.isEmpty(UserUtil.getWJLoginHelper().getA2())) {
            Intent intent = new Intent();
            intent.putExtra(MobileCertConstants.USERNAME, LoginUserBase.getLoginUserName());
            if (TextUtils.isEmpty(HttpGroup.getCookie())) {
                intent.putExtra("cookie", HttpGroup.getCookie());
            } else {
                intent.putExtra("cookie", HttpGroup.getCookie());
            }
            intent.putExtra("commonParams", StatisticsReportUtil.getDeviceInfoStr());
            baseActivity.setResult(-1, intent);
            baseActivity.finish();
            LoginObserverManager.getInstance().notifyLoginSuccess();
            return;
        }
        ToastUtils.showToast("\u767b\u5f55\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5");
    }

    public static synchronized LoginUserHelper getInstance() {
        LoginUserHelper loginUserHelper2;
        synchronized (LoginUserHelper.class) {
            if (loginUserHelper == null) {
                loginUserHelper = new LoginUserHelper();
            }
            loginUserHelper2 = loginUserHelper;
        }
        return loginUserHelper2;
    }

    private static boolean isExsitMianActivity() {
        return ActivityManagerUtil.getScreenManager().isExsitActivity("com.jingdong.app.mall.MainFrameActivity");
    }

    public void startLoginActivity(final IMyActivity iMyActivity, final String str, final boolean z, final int i2) {
        if (iMyActivity == null) {
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            iMyActivity.post(new Runnable() { // from class: com.jingdong.common.login.LoginUserHelper.1
                {
                    LoginUserHelper.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    Toast.makeText(iMyActivity.getThisActivity(), str, 0).show();
                }
            });
        }
        if (OKLog.D) {
            OKLog.d(TAG, "startLoginActivity -->> context : " + iMyActivity);
        }
        new Bundle();
        iMyActivity.post(new Runnable
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x003b: INVOKE 
              (r8v0 'iMyActivity' com.jingdong.common.frame.IMyActivity)
              (wrap: java.lang.Runnable : 0x0038: CONSTRUCTOR 
              (r7v0 'this' com.jingdong.common.login.LoginUserHelper A[IMMUTABLE_TYPE, THIS])
              (r11v0 'i2' int A[DONT_INLINE])
              (r8v0 'iMyActivity' com.jingdong.common.frame.IMyActivity A[DONT_INLINE])
              (r5 I:android.os.Bundle A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r10v0 'z' boolean A[DONT_INLINE])
             A[MD:(com.jingdong.common.login.LoginUserHelper, int, com.jingdong.common.frame.IMyActivity, android.os.Bundle, boolean):void (m), WRAPPED] (LINE:6) call: com.jingdong.common.login.LoginUserHelper.2.<init>(com.jingdong.common.login.LoginUserHelper, int, com.jingdong.common.frame.IMyActivity, android.os.Bundle, boolean):void type: CONSTRUCTOR)
             type: INTERFACE call: com.jingdong.common.frame.IMyActivity.post(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (m)] (LINE:6) in method: com.jingdong.common.login.LoginUserHelper.startLoginActivity(com.jingdong.common.frame.IMyActivity, java.lang.String, boolean, int):void, file: classes.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
            Caused by: java.lang.NullPointerException
            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
            	... 19 more
            */
        /*
            this = this;
            if (r8 != 0) goto L3
            return
        L3:
            boolean r0 = android.text.TextUtils.isEmpty(r9)
            if (r0 != 0) goto L11
            com.jingdong.common.login.LoginUserHelper$1 r0 = new com.jingdong.common.login.LoginUserHelper$1
            r0.<init>()
            r8.post(r0)
        L11:
            boolean r9 = com.jingdong.sdk.oklog.OKLog.D
            if (r9 == 0) goto L2c
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r0 = "startLoginActivity -->> context : "
            r9.append(r0)
            r9.append(r8)
            java.lang.String r9 = r9.toString()
            java.lang.String r0 = "LoginUserHelper"
            com.jingdong.sdk.oklog.OKLog.d(r0, r9)
        L2c:
            android.os.Bundle r5 = new android.os.Bundle
            r5.<init>()
            com.jingdong.common.login.LoginUserHelper$2 r9 = new com.jingdong.common.login.LoginUserHelper$2
            r1 = r9
            r2 = r7
            r3 = r11
            r4 = r8
            r6 = r10
            r1.<init>()
            r8.post(r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.login.LoginUserHelper.startLoginActivity(com.jingdong.common.frame.IMyActivity, java.lang.String, boolean, int):void");
    }

    public void executeLoginRunnable(IMyActivity iMyActivity, Runnable runnable, String str) {
        executeLoginRunnable(iMyActivity, runnable, str, true, 0);
    }

    public void executeLoginRunnable(IMyActivity iMyActivity, Runnable runnable) {
        executeLoginRunnable(iMyActivity, runnable, null, true, 0);
    }

    public void executeLoginRunnable(IMyActivity iMyActivity, Runnable runnable, int i2) {
        executeLoginRunnable(iMyActivity, runnable, null, true, i2);
    }

    public void executeLoginRunnable(IMyActivity iMyActivity, Runnable runnable, String str, boolean z) {
        executeLoginRunnable(iMyActivity, runnable, str, z, 0);
    }

    private void executeLoginRunnable(final IMyActivity iMyActivity, final Runnable runnable, final String str, final boolean z, final int i2) {
        if (iMyActivity == null) {
            return;
        }
        if (!LoginUserBase.hasLogin()) {
            iMyActivity.post(new Runnable() { // from class: com.jingdong.common.login.LoginUserHelper.3
                {
                    LoginUserHelper.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    LoginUserHelper.this.startLoginActivity(iMyActivity, str, z, i2);
                    LoginUserBase.loginCallback(iMyActivity, runnable);
                }
            });
            return;
        }
        if (Thread.currentThread() != BaseApplication.getUiThread()) {
            iMyActivity.post(runnable);
        } else if (runnable != null) {
            runnable.run();
        }
    }
}
