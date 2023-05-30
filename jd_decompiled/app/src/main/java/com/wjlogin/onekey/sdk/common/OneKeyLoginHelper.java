package com.wjlogin.onekey.sdk.common;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.cmic.sso.sdk.auth.AuthnHelper;
import com.unicom.xiaowo.login.UniAuthHelper;
import com.wjlogin.onekey.sdk.common.listener.OnResponseCallback;
import com.wjlogin.onekey.sdk.model.OneKeyInfo;
import com.wjlogin.onekey.sdk.util.Constans;
import com.wjlogin.onekey.sdk.util.LogUtil;
import com.wjlogin.onekey.sdk.util.MobileDeviceUtil;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class OneKeyLoginHelper {
    private static final String a = "WJLogin.OneKey.OneKeyLoginHelper";
    static OneKeyLoginHelper b = null;

    /* renamed from: c  reason: collision with root package name */
    private static OneKeyInfo f18320c = null;
    private static int d = 5000;

    /* renamed from: e  reason: collision with root package name */
    private Handler f18321e = new Handler(Looper.getMainLooper());

    private void c(OnResponseCallback onResponseCallback) {
        if (f18320c == null) {
            a(onResponseCallback, com.wjlogin.onekey.sdk.util.a.a("-1", "\u4f20\u5165\u53c2\u6570\u9519\u8bef", "CM"));
            return;
        }
        JSONObject jSONObject = Constans.CU_PREGETMOBILE;
        if (jSONObject != null) {
            b(onResponseCallback, jSONObject);
            return;
        }
        AuthnHelper authnHelper = AuthnHelper.getInstance(com.wjlogin.onekey.sdk.model.a.a());
        authnHelper.setOverTime(3000L);
        authnHelper.getPhoneInfo(f18320c.getCmAppId(), f18320c.getCmAppKey(), new a(this, onResponseCallback));
    }

    public static void clearLocalData() {
        Constans.CM_PREGETMOBILE = null;
        Constans.CU_PREGETMOBILE = null;
        Constans.CT_PREGETMOBILE = null;
        Constans.LOGIN_OPERATETYPE_CURRENT = null;
    }

    public static synchronized OneKeyLoginHelper createInstance(Context context, OneKeyInfo oneKeyInfo, boolean z) {
        OneKeyLoginHelper oneKeyLoginHelper;
        synchronized (OneKeyLoginHelper.class) {
            if (context == null) {
                try {
                    if (LogUtil.enableLog) {
                        LogUtil.LogE(a, "createInstance happened fatal cause,context is null!!!!!!!");
                    }
                } catch (Throwable th) {
                    if (z) {
                        try {
                            LogUtil.LogE(a, "createInstance happened something wrong!");
                            th.printStackTrace();
                        } catch (Throwable th2) {
                            throw th2;
                        }
                    }
                    return b;
                }
            }
            if (com.wjlogin.onekey.sdk.model.a.a() == null) {
                if (context instanceof Application) {
                    com.wjlogin.onekey.sdk.model.a.a = context;
                    if (z) {
                        LogUtil.LogE(a, "createInstance use Application");
                    }
                } else if (context != null) {
                    com.wjlogin.onekey.sdk.model.a.a = context.getApplicationContext();
                    if (z) {
                        LogUtil.LogE(a, "createInstance use getApplicationContext");
                    }
                }
            }
            if (b == null) {
                b = new OneKeyLoginHelper();
                LogUtil.setOpenLog(z);
                if (z) {
                    LogUtil.LogE(a, "createInstance ok");
                }
            }
            f18320c = oneKeyInfo;
            oneKeyLoginHelper = b;
        }
        return oneKeyLoginHelper;
    }

    private void d(OnResponseCallback onResponseCallback) {
        if (f18320c == null) {
            a(onResponseCallback, com.wjlogin.onekey.sdk.util.a.a("-1", "\u4f20\u5165\u53c2\u6570\u9519\u8bef", "CU"));
            return;
        }
        JSONObject jSONObject = Constans.CU_PREGETMOBILE;
        if (jSONObject != null) {
            b(onResponseCallback, jSONObject);
            return;
        }
        UniAuthHelper.getInstance().init(com.wjlogin.onekey.sdk.model.a.a(), f18320c.getCuClientId(), f18320c.getCuClientSecret());
        UniAuthHelper.getInstance().getAccessCode(d, new b(this, onResponseCallback));
    }

    public void getAccessToken(OnResponseCallback onResponseCallback) {
        if (onResponseCallback != null) {
            if (com.wjlogin.onekey.sdk.model.a.a() == null) {
                a(onResponseCallback, com.wjlogin.onekey.sdk.util.a.a("-1", "\u4f20\u5165\u53c2\u6570\u9519\u8bef", "CM"));
                return;
            }
            String str = Constans.LOGIN_OPERATETYPE_CURRENT;
            if (TextUtils.isEmpty(str)) {
                str = MobileDeviceUtil.getOperateType(com.wjlogin.onekey.sdk.model.a.a());
            }
            if ("CM".equals(str)) {
                a(onResponseCallback);
            } else if ("CU".equals(str)) {
                b(onResponseCallback);
            } else if ("CT".equals(str)) {
                OneKeyInfo oneKeyInfo = f18320c;
                if (oneKeyInfo == null) {
                    a(onResponseCallback, com.wjlogin.onekey.sdk.util.a.a("-1", "\u4f20\u5165\u53c2\u6570\u9519\u8bef", "CM"));
                } else {
                    com.wjlogin.onekey.sdk.common.a.c.a(oneKeyInfo.getCtAppId(), f18320c.getCtAppSecret()).a(com.wjlogin.onekey.sdk.model.a.a(), onResponseCallback);
                }
            } else {
                a(onResponseCallback, com.wjlogin.onekey.sdk.util.a.a("-3", Constans.cardIsNullMsg, ""));
            }
        }
    }

    public void preGetMobile(OnResponseCallback onResponseCallback) {
        if (onResponseCallback != null) {
            if (com.wjlogin.onekey.sdk.model.a.a() == null) {
                a(onResponseCallback, com.wjlogin.onekey.sdk.util.a.a("-1", "\u4f20\u5165\u53c2\u6570\u9519\u8bef", "CM"));
                return;
            }
            String operateType = MobileDeviceUtil.getOperateType(com.wjlogin.onekey.sdk.model.a.a());
            Constans.LOGIN_OPERATETYPE_CURRENT = operateType;
            if ("CM".equals(operateType)) {
                c(onResponseCallback);
            } else if ("CU".equals(operateType)) {
                d(onResponseCallback);
            } else if ("CT".equals(operateType)) {
                OneKeyInfo oneKeyInfo = f18320c;
                if (oneKeyInfo == null) {
                    a(onResponseCallback, com.wjlogin.onekey.sdk.util.a.a("-1", "\u4f20\u5165\u53c2\u6570\u9519\u8bef", "CM"));
                } else {
                    com.wjlogin.onekey.sdk.common.a.c.a(oneKeyInfo.getCtAppId(), f18320c.getCtAppSecret()).b(com.wjlogin.onekey.sdk.model.a.a(), onResponseCallback);
                }
            } else {
                a(onResponseCallback, com.wjlogin.onekey.sdk.util.a.a("-3", Constans.cardIsNullMsg, ""));
            }
        }
    }

    static OneKeyLoginHelper a() {
        return b;
    }

    private void b(OnResponseCallback onResponseCallback) {
        if (f18320c == null) {
            a(onResponseCallback, com.wjlogin.onekey.sdk.util.a.a("-1", "\u4f20\u5165\u53c2\u6570\u9519\u8bef", "CU"));
        } else {
            UniAuthHelper.getInstance().getAccessCode(d, new d(this, onResponseCallback));
        }
    }

    private void a(OnResponseCallback onResponseCallback) {
        if (f18320c == null) {
            a(onResponseCallback, com.wjlogin.onekey.sdk.util.a.a("-1", "\u4f20\u5165\u53c2\u6570\u9519\u8bef", "CM"));
        } else {
            AuthnHelper.getInstance(com.wjlogin.onekey.sdk.model.a.a()).loginAuth(f18320c.getCmAppId(), f18320c.getCmAppKey(), new c(this, onResponseCallback));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(OnResponseCallback onResponseCallback, JSONObject jSONObject) {
        this.f18321e.post(new f(this, onResponseCallback, jSONObject));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(OnResponseCallback onResponseCallback, JSONObject jSONObject) {
        this.f18321e.post(new e(this, onResponseCallback, jSONObject));
    }
}
