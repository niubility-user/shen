package com.tencent.connect.auth;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.facebook.react.uimanager.ViewProps;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.open.b.e;
import com.tencent.open.log.SLog;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.g;
import com.tencent.open.utils.i;
import com.tencent.open.utils.j;
import com.tencent.open.utils.k;
import com.tencent.open.utils.l;
import com.tencent.open.utils.m;
import com.tencent.tauth.DefaultUiListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class AuthAgent extends BaseApi {
    public static final String KEY_FORCE_QR_LOGIN = "KEY_FORCE_QR_LOGIN";
    public static final String SECURE_LIB_ARM64_FILE_NAME = "libwbsafeedit_64";
    public static final String SECURE_LIB_ARM_FILE_NAME = "libwbsafeedit";
    public static String SECURE_LIB_FILE_NAME = "libwbsafeedit";
    public static String SECURE_LIB_NAME = null;
    public static final int SECURE_LIB_VERSION = 5;
    public static final String SECURE_LIB_X86_64_FILE_NAME = "libwbsafeedit_x86_64";
    public static final String SECURE_LIB_X86_FILE_NAME = "libwbsafeedit_x86";
    private IUiListener a;
    private String d;

    /* renamed from: e */
    private WeakReference<Activity> f16098e;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public class a extends DefaultUiListener {
        IUiListener a;

        public a(IUiListener iUiListener) {
            AuthAgent.this = r1;
            this.a = iUiListener;
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onCancel() {
            IUiListener iUiListener = this.a;
            if (iUiListener != null) {
                iUiListener.onCancel();
            }
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onComplete(Object obj) {
            if (obj == null) {
                SLog.e("openSDK_LOG.AuthAgent", "CheckLoginListener response data is null");
                return;
            }
            JSONObject jSONObject = (JSONObject) obj;
            try {
                int i2 = jSONObject.getInt("ret");
                String string = i2 == 0 ? "success" : jSONObject.getString("msg");
                IUiListener iUiListener = this.a;
                if (iUiListener != null) {
                    iUiListener.onComplete(new JSONObject().put("ret", i2).put("msg", string));
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
                SLog.e("openSDK_LOG.AuthAgent", "CheckLoginListener response data format error");
            }
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            IUiListener iUiListener = this.a;
            if (iUiListener != null) {
                iUiListener.onError(uiError);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class c extends DefaultUiListener {
        private final IUiListener b;

        /* renamed from: c */
        private final boolean f16106c;
        private final Context d;

        public c(Context context, IUiListener iUiListener, boolean z, boolean z2) {
            AuthAgent.this = r1;
            this.d = context;
            this.b = iUiListener;
            this.f16106c = z;
            SLog.d("openSDK_LOG.AuthAgent", "OpenUi, TokenListener()");
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onCancel() {
            SLog.d("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onCancel");
            this.b.onCancel();
            SLog.release();
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onComplete(Object obj) {
            SLog.d("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onComplete");
            JSONObject jSONObject = (JSONObject) obj;
            try {
                String string = jSONObject.getString(Constants.PARAM_ACCESS_TOKEN);
                String string2 = jSONObject.getString(Constants.PARAM_EXPIRES_IN);
                String string3 = jSONObject.getString("openid");
                if (string != null && ((BaseApi) AuthAgent.this).f16153c != null && string3 != null) {
                    ((BaseApi) AuthAgent.this).f16153c.setAccessToken(string, string2);
                    ((BaseApi) AuthAgent.this).f16153c.setOpenId(string3);
                    com.tencent.connect.a.a.d(this.d, ((BaseApi) AuthAgent.this).f16153c);
                }
                String string4 = jSONObject.getString(Constants.PARAM_PLATFORM_ID);
                if (string4 != null) {
                    try {
                        this.d.getSharedPreferences(Constants.PREFERENCE_PF, 0).edit().putString(Constants.PARAM_PLATFORM_ID, string4).commit();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        SLog.e("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onComplete error", e2);
                    }
                }
                if (this.f16106c) {
                    CookieSyncManager.getInstance().sync();
                }
            } catch (JSONException e3) {
                e3.printStackTrace();
                SLog.e("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onComplete error", e3);
            }
            this.b.onComplete(jSONObject);
            AuthAgent.this.releaseResource();
            SLog.release();
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            SLog.d("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onError");
            this.b.onError(uiError);
            SLog.release();
        }
    }

    static {
        SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
        String str = Build.CPU_ABI;
        if (str != null && !str.equals("")) {
            if (str.equalsIgnoreCase("arm64-v8a")) {
                SECURE_LIB_FILE_NAME = SECURE_LIB_ARM64_FILE_NAME;
                SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
                SLog.i("openSDK_LOG.AuthAgent", "is arm64-v8a architecture");
                return;
            } else if (str.equalsIgnoreCase("x86")) {
                SECURE_LIB_FILE_NAME = SECURE_LIB_X86_FILE_NAME;
                SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
                SLog.i("openSDK_LOG.AuthAgent", "is x86 architecture");
                return;
            } else if (str.equalsIgnoreCase("x86_64")) {
                SECURE_LIB_FILE_NAME = SECURE_LIB_X86_64_FILE_NAME;
                SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
                SLog.i("openSDK_LOG.AuthAgent", "is x86_64 architecture");
                return;
            } else {
                SECURE_LIB_FILE_NAME = SECURE_LIB_ARM_FILE_NAME;
                SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
                SLog.i("openSDK_LOG.AuthAgent", "is arm(default) architecture");
                return;
            }
        }
        SECURE_LIB_FILE_NAME = SECURE_LIB_ARM_FILE_NAME;
        SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
        SLog.i("openSDK_LOG.AuthAgent", "is arm(default) architecture");
    }

    public AuthAgent(QQToken qQToken) {
        super(qQToken);
    }

    public int doLogin(Activity activity, String str, IUiListener iUiListener, boolean z, Fragment fragment, boolean z2, Map<String, Object> map) {
        if (com.tencent.connect.a.a("openSDK_LOG.AuthAgent", iUiListener)) {
            return -1;
        }
        this.d = str;
        this.f16098e = new WeakReference<>(activity);
        this.a = iUiListener;
        Object[] objArr = new Object[2];
        boolean booleanExtra = activity.getIntent().getBooleanExtra(KEY_FORCE_QR_LOGIN, false);
        boolean b2 = i.a(activity, this.f16153c.getAppId()).b("C_LoginWeb");
        SLog.i("openSDK_LOG.AuthAgent", "doLogin needForceQrLogin=" + booleanExtra + ", toWebLogin=" + b2);
        if (!booleanExtra && !b2 && a(activity, fragment, map, z, objArr)) {
            SLog.i("openSDK_LOG.AuthAgent", "OpenUi, showUi, return Constants.UI_ACTIVITY");
            e.a().a(this.f16153c.getOpenId(), this.f16153c.getAppId(), "2", "1", "5", (String) objArr[0], "0", "0");
            return ((Integer) objArr[1]).intValue();
        }
        e.a().a(this.f16153c.getOpenId(), this.f16153c.getAppId(), "2", "1", "5", "1", "0", "0");
        SLog.w("openSDK_LOG.AuthAgent", "doLogin startActivity fail show dialog.");
        b bVar = new b(this.a);
        this.a = bVar;
        return a(z, bVar, z2, map);
    }

    @Override // com.tencent.connect.common.BaseApi
    public void releaseResource() {
        this.a = null;
    }

    private String b(Map<String, Object> map) {
        if (map == null) {
            return "";
        }
        Object obj = map.get(Constants.KEY_ADD_LOGIN_ACCOUNT);
        return obj instanceof String ? ((String) obj).trim() : "";
    }

    public int a(Activity activity, String str, IUiListener iUiListener, boolean z, Fragment fragment, boolean z2) {
        return doLogin(activity, str, iUiListener, z, fragment, z2, null);
    }

    private int a(boolean z, IUiListener iUiListener, boolean z2, Map<String, Object> map) {
        CookieSyncManager.createInstance(g.a());
        Bundle a2 = a();
        if (z) {
            a2.putString("isadd", "1");
        }
        a2.putString(Constants.PARAM_SCOPE, this.d);
        a2.putString(Constants.PARAM_CLIENT_ID, this.f16153c.getAppId());
        if (BaseApi.isOEM) {
            a2.putString(Constants.PARAM_PLATFORM_ID, "desktop_m_qq-" + BaseApi.installChannel + "-android-" + BaseApi.registerChannel + "-" + BaseApi.businessId);
        } else {
            a2.putString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF);
        }
        String str = (System.currentTimeMillis() / 1000) + "";
        a2.putString("sign", k.b(g.a(), str));
        a2.putString("time", str);
        a2.putString(ViewProps.DISPLAY, "mobile");
        a2.putString("response_type", "token");
        a2.putString("redirect_uri", "auth://tauth.qq.com/");
        a2.putString("cancel_display", "1");
        a2.putString("switch", "1");
        a2.putString("compat_v", "1");
        if (z2) {
            a2.putString(DeeplinkProductDetailHelper.LAYER_STYLE, "qr");
        }
        String b2 = b(map);
        SLog.i("openSDK_LOG.AuthAgent", "OpenUI, showDialog addLoginAccount: " + b2);
        if (b2 != null && !b2.isEmpty()) {
            a2.putString("uin", b2);
        }
        boolean a3 = a(map);
        a2.putString("show_download_ui", Boolean.toString(a3));
        SLog.i("openSDK_LOG.AuthAgent", "OpenUi, showDialog -- start, isShowDownloadUi=" + a3);
        final String str2 = j.a().a(g.a(), "https://openmobile.qq.com/oauth2.0/m_authorize?") + HttpUtils.encodeUrl(a2);
        new c(g.a(), iUiListener, true, false);
        SLog.d("openSDK_LOG.AuthAgent", "OpenUi, showDialog TDialog");
        l.b(new Runnable
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x013a: INVOKE 
              (wrap: java.lang.Runnable : 0x0137: CONSTRUCTOR 
              (r8v0 'this' com.tencent.connect.auth.AuthAgent A[IMMUTABLE_TYPE, THIS])
              (r9v18 'str2' java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r11 I:com.tencent.tauth.IUiListener A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[MD:(com.tencent.connect.auth.AuthAgent, java.lang.String, com.tencent.tauth.IUiListener):void (m), WRAPPED] (LINE:36) call: com.tencent.connect.auth.AuthAgent.1.<init>(com.tencent.connect.auth.AuthAgent, java.lang.String, com.tencent.tauth.IUiListener):void type: CONSTRUCTOR)
             type: STATIC call: com.tencent.open.utils.l.b(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (m)] (LINE:36) in method: com.tencent.connect.auth.AuthAgent.a(boolean, com.tencent.tauth.IUiListener, boolean, java.util.Map<java.lang.String, java.lang.Object>):int, file: classes9.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
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
            */
        /*
            Method dump skipped, instructions count: 324
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.connect.auth.AuthAgent.a(boolean, com.tencent.tauth.IUiListener, boolean, java.util.Map):int");
    }

    public void b(IUiListener iUiListener) {
        Bundle a2 = a();
        a2.putString("reqType", "checkLogin");
        HttpUtils.requestAsync(this.f16153c, g.a(), "https://openmobile.qq.com/v3/user/get_info", a2, "GET", new BaseApi.TempRequestListener(new a(iUiListener)));
    }

    /* loaded from: classes9.dex */
    public class b extends DefaultUiListener {
        WeakReference<IUiListener> a;

        /* renamed from: c */
        private final String f16100c = "sendinstall";
        private final String d = "installwording";

        /* renamed from: e */
        private final String f16101e = "https://appsupport.qq.com/cgi-bin/qzapps/mapp_addapp.cgi";

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes9.dex */
        public abstract class a implements View.OnClickListener {
            Dialog d;

            a(Dialog dialog) {
                b.this = r1;
                this.d = dialog;
            }
        }

        public b(IUiListener iUiListener) {
            AuthAgent.this = r1;
            this.a = new WeakReference<>(iUiListener);
        }

        private void a(String str, final IUiListener iUiListener, final Object obj) {
            PackageInfo packageInfo;
            if (AuthAgent.this.f16098e != null) {
                Activity activity = (Activity) AuthAgent.this.f16098e.get();
                if (activity == null) {
                    SLog.i("openSDK_LOG.AuthAgent", "showFeedConfrimDialog mActivity.get() null and return");
                    return;
                }
                Dialog dialog = new Dialog(activity);
                dialog.requestWindowFeature(1);
                PackageManager packageManager = activity.getPackageManager();
                try {
                    packageInfo = packageManager.getPackageInfo(activity.getPackageName(), 0);
                } catch (PackageManager.NameNotFoundException e2) {
                    SLog.e("openSDK_LOG.AuthAgent", "showFeedConfrimDialog exception:" + e2.getStackTrace().toString());
                    packageInfo = null;
                }
                Drawable loadIcon = packageInfo != null ? packageInfo.applicationInfo.loadIcon(packageManager) : null;
                View.OnClickListener onClickListener = new a(dialog) { // from class: com.tencent.connect.auth.AuthAgent.b.1
                    {
                        b.this = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        b.this.a();
                        Dialog dialog2 = this.d;
                        if (dialog2 != null && dialog2.isShowing()) {
                            this.d.dismiss();
                        }
                        IUiListener iUiListener2 = iUiListener;
                        if (iUiListener2 != null) {
                            iUiListener2.onComplete(obj);
                        }
                    }
                };
                View.OnClickListener onClickListener2 = new a(dialog) { // from class: com.tencent.connect.auth.AuthAgent.b.2
                    {
                        b.this = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        Dialog dialog2 = this.d;
                        if (dialog2 != null && dialog2.isShowing()) {
                            this.d.dismiss();
                        }
                        IUiListener iUiListener2 = iUiListener;
                        if (iUiListener2 != null) {
                            iUiListener2.onComplete(obj);
                        }
                    }
                };
                ColorDrawable colorDrawable = new ColorDrawable();
                colorDrawable.setAlpha(0);
                dialog.getWindow().setBackgroundDrawable(colorDrawable);
                dialog.setContentView(a(activity, loadIcon, str, onClickListener, onClickListener2));
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.tencent.connect.auth.AuthAgent.b.3
                    {
                        b.this = this;
                    }

                    @Override // android.content.DialogInterface.OnCancelListener
                    public void onCancel(DialogInterface dialogInterface) {
                        IUiListener iUiListener2 = iUiListener;
                        if (iUiListener2 != null) {
                            iUiListener2.onComplete(obj);
                        }
                    }
                });
                if (activity == null || activity.isFinishing()) {
                    return;
                }
                dialog.show();
                return;
            }
            SLog.i("openSDK_LOG.AuthAgent", "showFeedConfrimDialog mActivity null and return");
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onCancel() {
            if (this.a.get() != null) {
                this.a.get().onCancel();
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:65:0x0067  */
        /* JADX WARN: Removed duplicated region for block: B:71:0x00a0  */
        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onComplete(java.lang.Object r8) {
            /*
                r7 = this;
                java.lang.String r0 = "openSDK_LOG.AuthAgent"
                if (r8 == 0) goto La5
                r1 = r8
                org.json.JSONObject r1 = (org.json.JSONObject) r1
                if (r1 == 0) goto La5
                r2 = 0
                java.lang.String r3 = "sendinstall"
                int r3 = r1.getInt(r3)     // Catch: org.json.JSONException -> L1c
                r4 = 1
                if (r3 != r4) goto L14
                goto L15
            L14:
                r4 = 0
            L15:
                java.lang.String r3 = "installwording"
                java.lang.String r3 = r1.getString(r3)     // Catch: org.json.JSONException -> L1d
                goto L24
            L1c:
                r4 = 0
            L1d:
                java.lang.String r3 = "FeedConfirmListener onComplete There is no value for sendinstall."
                com.tencent.open.log.SLog.w(r0, r3)
                java.lang.String r3 = ""
            L24:
                java.lang.String r3 = java.net.URLDecoder.decode(r3)
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                java.lang.String r6 = " WORDING = "
                r5.append(r6)
                r5.append(r3)
                java.lang.String r6 = "xx,showConfirmDialog="
                r5.append(r6)
                r5.append(r4)
                java.lang.String r5 = r5.toString()
                com.tencent.open.log.SLog.i(r0, r5)
                if (r4 == 0) goto L5d
                boolean r4 = android.text.TextUtils.isEmpty(r3)
                if (r4 != 0) goto L5d
                java.lang.ref.WeakReference<com.tencent.tauth.IUiListener> r1 = r7.a
                java.lang.Object r1 = r1.get()
                com.tencent.tauth.IUiListener r1 = (com.tencent.tauth.IUiListener) r1
                r7.a(r3, r1, r8)
                java.lang.String r8 = " WORDING is not empty and return"
                com.tencent.open.log.SLog.i(r0, r8)
                return
            L5d:
                java.lang.ref.WeakReference<com.tencent.tauth.IUiListener> r3 = r7.a
                java.lang.Object r3 = r3.get()
                com.tencent.tauth.IUiListener r3 = (com.tencent.tauth.IUiListener) r3
                if (r3 == 0) goto La0
                com.tencent.connect.auth.AuthAgent r4 = com.tencent.connect.auth.AuthAgent.this
                com.tencent.connect.auth.QQToken r4 = com.tencent.connect.auth.AuthAgent.h(r4)
                if (r4 == 0) goto L8d
                com.tencent.connect.auth.AuthAgent r2 = com.tencent.connect.auth.AuthAgent.this
                com.tencent.connect.auth.QQToken r2 = com.tencent.connect.auth.AuthAgent.i(r2)
                boolean r2 = r2.saveSession(r1)
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r4 = " saveSession saveSuccess="
                r1.append(r4)
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                com.tencent.open.log.SLog.i(r0, r1)
            L8d:
                if (r2 == 0) goto L93
                r3.onComplete(r8)
                goto La5
            L93:
                com.tencent.tauth.UiError r8 = new com.tencent.tauth.UiError
                r0 = -6
                r1 = 0
                java.lang.String r2 = "\u6301\u4e45\u5316\u5931\u8d25!"
                r8.<init>(r0, r2, r1)
                r3.onError(r8)
                goto La5
            La0:
                java.lang.String r8 = " userListener is null"
                com.tencent.open.log.SLog.i(r0, r8)
            La5:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.connect.auth.AuthAgent.b.onComplete(java.lang.Object):void");
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            if (this.a.get() != null) {
                this.a.get().onError(uiError);
            }
        }

        private Drawable a(String str, Context context) {
            InputStream open;
            Bitmap bitmap;
            Drawable drawable = null;
            try {
                open = context.getApplicationContext().getAssets().open(str);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            if (open == null) {
                return null;
            }
            if (str.endsWith(".9.png")) {
                try {
                    bitmap = BitmapFactory.decodeStream(open);
                } catch (OutOfMemoryError e3) {
                    e3.printStackTrace();
                    bitmap = null;
                }
                if (bitmap != null) {
                    byte[] ninePatchChunk = bitmap.getNinePatchChunk();
                    NinePatch.isNinePatchChunk(ninePatchChunk);
                    return new NinePatchDrawable(bitmap, ninePatchChunk, new Rect(), null);
                }
                return null;
            }
            drawable = Drawable.createFromStream(open, str);
            open.close();
            return drawable;
        }

        private View a(Context context, Drawable drawable, String str, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            float f2 = displayMetrics.density;
            RelativeLayout relativeLayout = new RelativeLayout(context);
            ImageView imageView = new ImageView(context);
            imageView.setImageDrawable(drawable);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setId(1);
            int i2 = (int) (60.0f * f2);
            int i3 = (int) (f2 * 14.0f);
            int i4 = (int) (18.0f * f2);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i2, i2);
            layoutParams.addRule(9);
            layoutParams.setMargins(0, i4, (int) (6.0f * f2), i4);
            relativeLayout.addView(imageView, layoutParams);
            TextView textView = new TextView(context);
            textView.setText(str);
            textView.setTextSize(14.0f);
            textView.setGravity(3);
            textView.setIncludeFontPadding(false);
            textView.setPadding(0, 0, 0, 0);
            textView.setLines(2);
            textView.setId(5);
            textView.setMinWidth((int) (185.0f * f2));
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams2.addRule(1, 1);
            layoutParams2.addRule(6, 1);
            float f3 = 5.0f * f2;
            layoutParams2.setMargins(0, 0, (int) f3, 0);
            relativeLayout.addView(textView, layoutParams2);
            View view = new View(context);
            view.setBackgroundColor(Color.rgb(214, 214, 214));
            view.setId(3);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, 2);
            layoutParams3.addRule(3, 1);
            layoutParams3.addRule(5, 1);
            layoutParams3.addRule(7, 5);
            int i5 = (int) (12.0f * f2);
            layoutParams3.setMargins(0, 0, 0, i5);
            relativeLayout.addView(view, layoutParams3);
            LinearLayout linearLayout = new LinearLayout(context);
            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams4.addRule(5, 1);
            layoutParams4.addRule(7, 5);
            layoutParams4.addRule(3, 3);
            Button button = new Button(context);
            button.setText("\u8df3\u8fc7");
            button.setBackgroundDrawable(a("buttonNegt.png", context));
            button.setTextColor(Color.rgb(36, 97, 131));
            button.setTextSize(20.0f);
            button.setOnClickListener(onClickListener2);
            button.setId(4);
            int i6 = (int) (45.0f * f2);
            LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(0, i6);
            layoutParams5.rightMargin = i3;
            int i7 = (int) (4.0f * f2);
            layoutParams5.leftMargin = i7;
            layoutParams5.weight = 1.0f;
            linearLayout.addView(button, layoutParams5);
            Button button2 = new Button(context);
            button2.setText("\u786e\u5b9a");
            button2.setTextSize(20.0f);
            button2.setTextColor(Color.rgb(255, 255, 255));
            button2.setBackgroundDrawable(a("buttonPost.png", context));
            button2.setOnClickListener(onClickListener);
            LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(0, i6);
            layoutParams6.weight = 1.0f;
            layoutParams6.rightMargin = i7;
            linearLayout.addView(button2, layoutParams6);
            relativeLayout.addView(linearLayout, layoutParams4);
            ViewGroup.LayoutParams layoutParams7 = new FrameLayout.LayoutParams((int) (279.0f * f2), (int) (f2 * 163.0f));
            relativeLayout.setPadding(i3, 0, i5, i5);
            relativeLayout.setLayoutParams(layoutParams7);
            relativeLayout.setBackgroundColor(Color.rgb(247, 251, 247));
            PaintDrawable paintDrawable = new PaintDrawable(Color.rgb(247, 251, 247));
            paintDrawable.setCornerRadius(f3);
            relativeLayout.setBackgroundDrawable(paintDrawable);
            return relativeLayout;
        }

        protected void a() {
            Activity activity;
            Bundle b = AuthAgent.this.b();
            if (AuthAgent.this.f16098e == null || (activity = (Activity) AuthAgent.this.f16098e.get()) == null) {
                return;
            }
            HttpUtils.requestAsync(((BaseApi) AuthAgent.this).f16153c, activity, "https://appsupport.qq.com/cgi-bin/qzapps/mapp_addapp.cgi", b, "POST", null);
        }
    }

    private boolean a(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return true;
        }
        Object obj = map.get(Constants.KEY_ENABLE_SHOW_DOWNLOAD_URL);
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return true;
    }

    private boolean a(Activity activity, Fragment fragment, Map<String, Object> map, boolean z, Object[] objArr) {
        SLog.i("openSDK_LOG.AuthAgent", "startActionActivity() -- start");
        Intent c2 = c();
        if (c2 != null) {
            Bundle a2 = a();
            if (z) {
                a2.putString("isadd", "1");
            }
            a2.putString(Constants.PARAM_SCOPE, this.d);
            a2.putString(Constants.PARAM_CLIENT_ID, this.f16153c.getAppId());
            if (BaseApi.isOEM) {
                a2.putString(Constants.PARAM_PLATFORM_ID, "desktop_m_qq-" + BaseApi.installChannel + "-android-" + BaseApi.registerChannel + "-" + BaseApi.businessId);
            } else {
                a2.putString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF);
            }
            a2.putString("need_pay", "1");
            a(a2, map);
            a2.putString(Constants.KEY_APP_NAME, k.a(g.a()));
            c2.putExtra(Constants.KEY_ACTION, "action_login");
            c2.putExtra(Constants.KEY_PARAMS, a2);
            c2.putExtra("appid", this.f16153c.getAppId());
            a2.putString(Constants.KEY_PPSTS, k.a(activity, a(a2)));
            try {
                this.a = new b(this.a);
                UIListenerManager.getInstance().setListenerWithRequestcode(11101, this.a);
                if (fragment != null) {
                    SLog.d("openSDK_LOG.AuthAgent", "startAssitActivity fragment");
                    a(fragment, c2, 11101, map);
                } else {
                    SLog.d("openSDK_LOG.AuthAgent", "startAssitActivity activity");
                    a(activity, c2, 11101, map);
                }
                SLog.i("openSDK_LOG.AuthAgent", "startActionActivity() -- end, found activity for loginIntent");
                e.a().a(0, "LOGIN_CHECK_SDK", Constants.DEFAULT_UIN, this.f16153c.getAppId(), "", Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "");
                objArr[0] = "0";
                objArr[1] = 1;
                return true;
            } catch (Exception e2) {
                SLog.e("openSDK_LOG.AuthAgent", "startActionActivity() exception", e2);
            }
        }
        e.a().a(1, "LOGIN_CHECK_SDK", Constants.DEFAULT_UIN, this.f16153c.getAppId(), "", Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "startActionActivity fail");
        SLog.i("openSDK_LOG.AuthAgent", "startActionActivity() -- end, no target activity for loginIntent");
        return false;
    }

    private void a(Bundle bundle, Map<String, Object> map) {
        String b2 = b(map);
        if (b2 == null || b2.isEmpty()) {
            return;
        }
        bundle.putString(Constants.KEY_ADD_LOGIN_ACCOUNT, b2);
    }

    private String a(Bundle bundle) {
        String string = bundle.getString("status_os");
        String string2 = bundle.getString("status_machine");
        String string3 = bundle.getString("status_version");
        String string4 = bundle.getString("sdkv");
        String string5 = bundle.getString(Constants.PARAM_CLIENT_ID);
        String string6 = bundle.getString("need_pay");
        String string7 = bundle.getString(Constants.PARAM_PLATFORM_ID);
        SLog.d("openSDK_LOG.AuthAgent", "os=" + string + ", machine=" + string2 + ", version=" + string3 + ", sdkv=" + string4 + ", appId=" + string5 + ", needPay=" + string6 + ", pf=" + string7);
        StringBuilder sb = new StringBuilder();
        if (string == null) {
            string = "";
        }
        sb.append(string);
        if (string2 == null) {
            string2 = "";
        }
        sb.append(string2);
        if (string3 == null) {
            string3 = "";
        }
        sb.append(string3);
        if (string4 == null) {
            string4 = "";
        }
        sb.append(string4);
        if (string5 == null) {
            string5 = "";
        }
        sb.append(string5);
        if (string6 == null) {
            string6 = "";
        }
        sb.append(string6);
        if (string7 == null) {
            string7 = "";
        }
        sb.append(string7);
        return sb.toString();
    }

    public void a(IUiListener iUiListener) {
        String str;
        SLog.i("openSDK_LOG.AuthAgent", "reportDAU() -- start");
        String accessToken = this.f16153c.getAccessToken();
        String openId = this.f16153c.getOpenId();
        String appId = this.f16153c.getAppId();
        if (TextUtils.isEmpty(accessToken) || TextUtils.isEmpty(openId) || TextUtils.isEmpty(appId)) {
            str = "";
        } else {
            str = m.g("tencent&sdk&qazxc***14969%%" + accessToken + appId + openId + "qzone3.4");
        }
        if (TextUtils.isEmpty(str)) {
            SLog.e("openSDK_LOG.AuthAgent", "reportDAU -- encrytoken is null");
            return;
        }
        Bundle a2 = a();
        a2.putString("encrytoken", str);
        HttpUtils.requestAsync(this.f16153c, g.a(), "https://openmobile.qq.com/user/user_login_statis", a2, "POST", null);
        SLog.i("openSDK_LOG.AuthAgent", "reportDAU() -- end");
    }
}
