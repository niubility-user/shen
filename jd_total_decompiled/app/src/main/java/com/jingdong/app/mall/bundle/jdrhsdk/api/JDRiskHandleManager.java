package com.jingdong.app.mall.bundle.jdrhsdk.api;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.jingdong.app.mall.bundle.jdrhsdk.JDRiskHandleActivity;
import com.jingdong.app.mall.bundle.jdrhsdk.JDRiskHandleWebActivity;
import com.jingdong.app.mall.bundle.jdrhsdk.a.b;
import com.jingdong.app.mall.bundle.jdrhsdk.b.a;
import com.jingdong.app.mall.bundle.jdrhsdk.d.c;
import com.jingdong.app.mall.bundle.jdrhsdk.d.d;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import jd.wjlogin_sdk.common.listener.OnCommonCallback;
import jd.wjlogin_sdk.model.ErrorResult;
import jd.wjlogin_sdk.model.FailResult;

/* loaded from: classes.dex */
public class JDRiskHandleManager {
    private static final String TAG = "RiskHandle.JDRiskHandleManager";
    private static JDRiskHandleManager instance;
    private int checkA2Count;
    private a curColorData;
    private b curRiskHandle;
    private CopyOnWriteArrayList<JDRiskHandleListener> handleListenerList = new CopyOnWriteArrayList<>();
    private String handleToken;
    private long lastCheckA2Millis;

    static /* synthetic */ int access$108(JDRiskHandleManager jDRiskHandleManager) {
        int i2 = jDRiskHandleManager.checkA2Count;
        jDRiskHandleManager.checkA2Count = i2 + 1;
        return i2;
    }

    private b getCurRiskHandle() {
        return this.curRiskHandle;
    }

    private String getHandleToken() {
        return TextUtils.isEmpty(this.handleToken) ? "" : this.handleToken;
    }

    public static JDRiskHandleManager getInstance() {
        JDRiskHandleManager jDRiskHandleManager;
        JDRiskHandleManager jDRiskHandleManager2 = instance;
        if (jDRiskHandleManager2 != null) {
            return jDRiskHandleManager2;
        }
        synchronized (JDRiskHandleManager.class) {
            if (instance == null) {
                instance = new JDRiskHandleManager();
            }
            jDRiskHandleManager = instance;
        }
        return jDRiskHandleManager;
    }

    private void loginHandle(final Context context, final JDRiskHandleOption jDRiskHandleOption, final a aVar) {
        if (aVar != null) {
            try {
                d.a(TAG, "loginHandle getRpId=" + aVar.d());
            } catch (Exception e2) {
                onHandleFail(-1001, JDRiskHandleError.MSG_JAVA_EXCEPTION);
                e2.printStackTrace();
                return;
            }
        }
        d.a(TAG, "loginHandle haslogin=" + com.jingdong.app.mall.bundle.jdrhsdk.d.a.F());
        if (!com.jingdong.app.mall.bundle.jdrhsdk.d.a.F()) {
            this.checkA2Count = 0;
            startLoginRiskHandle(context, jDRiskHandleOption, aVar);
        } else if (this.checkA2Count > 4 && System.currentTimeMillis() - this.lastCheckA2Millis < 120000) {
            onHandleFail(JDRiskHandleError.CODE_605_AND_CHECK_A2, JDRiskHandleError.MSG_605_AND_CHECK_A2);
        } else {
            d.a(TAG, "loginHandle CheckA2");
            com.jingdong.app.mall.bundle.jdrhsdk.d.a.o(new OnCommonCallback() { // from class: com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleManager.1
                {
                    JDRiskHandleManager.this = this;
                }

                @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
                public void onError(ErrorResult errorResult) {
                    d.a(JDRiskHandleManager.TAG, "loginHandle CheckA2 onError");
                    JDRiskHandleManager.this.checkA2Count = 0;
                    com.jingdong.app.mall.bundle.jdrhsdk.d.a.c(context);
                    JDRiskHandleManager.this.startLoginRiskHandle(context, jDRiskHandleOption, aVar);
                }

                @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
                public void onFail(FailResult failResult) {
                    d.a(JDRiskHandleManager.TAG, "loginHandle CheckA2 onFail");
                    JDRiskHandleManager.this.checkA2Count = 0;
                    com.jingdong.app.mall.bundle.jdrhsdk.d.a.c(context);
                    JDRiskHandleManager.this.startLoginRiskHandle(context, jDRiskHandleOption, aVar);
                }

                @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
                public void onSuccess() {
                    d.a(JDRiskHandleManager.TAG, "loginHandle CheckA2 onSuccess");
                    JDRiskHandleManager.this.lastCheckA2Millis = System.currentTimeMillis();
                    JDRiskHandleManager.access$108(JDRiskHandleManager.this);
                    JDRiskHandleData jDRiskHandleData = new JDRiskHandleData();
                    jDRiskHandleData.setCode(0);
                    jDRiskHandleData.setData(com.jingdong.app.mall.bundle.jdrhsdk.d.a.f8172f);
                    JDRiskHandleManager.this.onHandleSuccess(jDRiskHandleData);
                }
            });
        }
    }

    private void setCurColorData(a aVar) {
        this.curColorData = aVar;
    }

    private void setHandleToken(String str) {
        this.handleToken = str;
    }

    public void startLoginRiskHandle(Context context, JDRiskHandleOption jDRiskHandleOption, a aVar) {
        if (com.jingdong.app.mall.bundle.jdrhsdk.d.a.H()) {
            onHandleFail(JDRiskHandleError.CODE_ENTER_LOGIN, JDRiskHandleError.MSG_ENTER_LOGIN);
        } else {
            startRiskHandleActivity(context, jDRiskHandleOption, aVar);
        }
    }

    private void startRiskHandleActivity(Context context, JDRiskHandleOption jDRiskHandleOption, a aVar) {
        String response;
        com.jingdong.app.mall.bundle.jdrhsdk.c.d a;
        int i2;
        String str;
        String str2;
        if (jDRiskHandleOption != null) {
            try {
                response = jDRiskHandleOption.getResponse();
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        } else {
            response = "";
        }
        String a2 = aVar != null ? aVar.a() : "";
        if (com.jingdong.app.mall.bundle.jdrhsdk.d.a.G()) {
            Intent intent = new Intent(context, JDRiskHandleActivity.class);
            intent.putExtra("response", response);
            context.startActivity(intent);
            a = com.jingdong.app.mall.bundle.jdrhsdk.c.d.a();
            i2 = 0;
            str = "jumpToRiskHandle";
            str2 = "";
        } else {
            onHandleFail(-1002, JDRiskHandleError.MSG_APP_BACKGROUND);
            a = com.jingdong.app.mall.bundle.jdrhsdk.c.d.a();
            i2 = -1002;
            str = JDRiskHandleError.MSG_APP_BACKGROUND;
            str2 = "";
        }
        a.b(i2, str, a2, response, str2);
    }

    public a getCurColorData() {
        return this.curColorData;
    }

    public String getRiskHandleToken() {
        return getHandleToken();
    }

    public String getRiskHandleVersion() {
        return com.jingdong.app.mall.bundle.jdrhsdk.d.a.A();
    }

    public JDRiskHandleManager init(Context context, JDRiskHandleInfoHelper jDRiskHandleInfoHelper) {
        c.b(context);
        com.jingdong.app.mall.bundle.jdrhsdk.d.a.h(jDRiskHandleInfoHelper);
        return this;
    }

    public void jumpToRiskHandle(Context context, JDRiskHandleOption jDRiskHandleOption, JDRiskHandleListener jDRiskHandleListener) {
        try {
            d.a(TAG, "jumpToRiskHandle");
            if (context != null && jDRiskHandleOption != null && jDRiskHandleListener != null) {
                if (c.a() == null) {
                    c.b(context.getApplicationContext());
                }
                this.handleListenerList.add(jDRiskHandleListener);
                d.a(TAG, "jumpToRiskHandle listener size=" + this.handleListenerList.size());
                if (this.handleListenerList.size() > 1) {
                    return;
                }
                String response = jDRiskHandleOption.getResponse();
                d.a(TAG, "jumpToRiskHandle response=" + response);
                a aVar = new a(response);
                setCurColorData(aVar);
                int c2 = aVar.c();
                if (c2 == 100) {
                    d.a(TAG, "jumpToRiskHandle handleType Login");
                    loginHandle(context, jDRiskHandleOption, aVar);
                    return;
                } else if (c2 == 101) {
                    d.a(TAG, "jumpToRiskHandle handleType VERIFY");
                    startRiskHandleActivity(context, jDRiskHandleOption, aVar);
                    return;
                } else {
                    d.a(TAG, "jumpToRiskHandle handleType Error");
                    onHandleFail(-2001, JDRiskHandleError.MSG_CHECK_HANDLE_TYPE_ERROR);
                    com.jingdong.app.mall.bundle.jdrhsdk.c.d.a().b(-2001, JDRiskHandleError.MSG_CHECK_HANDLE_TYPE_ERROR, aVar.a(), response, "");
                    return;
                }
            }
            d.a(TAG, "jumpToRiskHandle context or option or listener is null");
        } catch (Exception e2) {
            d.a(TAG, "jumpToRiskHandle Exception=" + e2.getMessage());
            onHandleFail(-1001, JDRiskHandleError.MSG_JAVA_EXCEPTION);
        }
    }

    public void loadUrl(Context context, String str) {
        if (context == null) {
            try {
                context = c.a();
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        if (context != null && !TextUtils.isEmpty(str)) {
            Intent intent = new Intent(context, JDRiskHandleWebActivity.class);
            intent.putExtra("url", str);
            context.startActivity(intent);
        }
    }

    public void onHandleFail(int i2, String str) {
        JDRiskHandleError jDRiskHandleError = new JDRiskHandleError();
        jDRiskHandleError.setCode(i2);
        jDRiskHandleError.setMsg(str);
        onHandleFail(jDRiskHandleError);
    }

    public void onHandleFail(JDRiskHandleError jDRiskHandleError) {
        if (jDRiskHandleError != null) {
            try {
                if (jDRiskHandleError.getCode() == 0) {
                    jDRiskHandleError.setCode(JDRiskHandleError.CODE_CHECK_ERROR_FIND_ZERO);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        CopyOnWriteArrayList<JDRiskHandleListener> copyOnWriteArrayList = this.handleListenerList;
        if (copyOnWriteArrayList != null && jDRiskHandleError != null) {
            Iterator<JDRiskHandleListener> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                it.next().onHandleFail(jDRiskHandleError);
            }
            this.handleListenerList.clear();
        }
        this.curRiskHandle = null;
        this.curColorData = null;
    }

    public void onHandleSuccess(JDRiskHandleData jDRiskHandleData) {
        try {
            if (this.handleListenerList != null && jDRiskHandleData != null) {
                setHandleToken(jDRiskHandleData.getData());
                Iterator<JDRiskHandleListener> it = this.handleListenerList.iterator();
                while (it.hasNext()) {
                    it.next().onHandleSuccess(jDRiskHandleData);
                }
                this.handleListenerList.clear();
            }
            this.curRiskHandle = null;
            this.curColorData = null;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void onLoginSuccess(String str) {
        try {
            if (getCurColorData() != null && !TextUtils.isEmpty(com.jingdong.app.mall.bundle.jdrhsdk.d.a.p()) && getCurColorData().d().equals(com.jingdong.app.mall.bundle.jdrhsdk.d.a.p())) {
                if (getCurRiskHandle() == null || !(getCurRiskHandle() instanceof com.jingdong.app.mall.bundle.jdrhsdk.a.a)) {
                    d.a(TAG, "onLoginSuccess getCurRiskHandle() ==null or not instanceof LoginRiskHandle");
                    onHandleFail(-2001, JDRiskHandleError.MSG_CHECK_HANDLE_TYPE_ERROR);
                    return;
                }
                d.a(TAG, "onLoginSuccess getCurRiskHandle() =" + getCurRiskHandle().a());
                ((com.jingdong.app.mall.bundle.jdrhsdk.a.a) getCurRiskHandle()).s(str);
                return;
            }
            if (getCurColorData() != null) {
                d.a(TAG, "onLoginSuccess getCurColorData rpid=" + getCurColorData().d());
            }
            d.a(TAG, "onLoginSuccess DeviceInfoUtil.getAntiRpId() rpid=" + com.jingdong.app.mall.bundle.jdrhsdk.d.a.p());
        } catch (Exception e2) {
            d.a(TAG, "onLoginSuccess Exception +" + e2.getMessage());
            onHandleFail(-1001, JDRiskHandleError.MSG_JAVA_EXCEPTION);
        }
    }

    public void setCurRiskHandle(b bVar) {
        this.curRiskHandle = bVar;
    }

    public JDRiskHandleManager setDebugHost(boolean z) {
        com.jingdong.app.mall.bundle.jdrhsdk.c.a.h().g(z);
        return this;
    }

    public JDRiskHandleManager setDebugLog(boolean z) {
        d.d(z);
        return this;
    }

    public JDRiskHandleManager setJumpHelper(JDRiskHandleJumpHelper jDRiskHandleJumpHelper) {
        com.jingdong.app.mall.bundle.jdrhsdk.d.a.i(jDRiskHandleJumpHelper);
        return this;
    }

    public JDRiskHandleManager setLoginHelper(JDRiskHandleLoginHelper jDRiskHandleLoginHelper) {
        com.jingdong.app.mall.bundle.jdrhsdk.d.a.j(jDRiskHandleLoginHelper);
        return this;
    }

    public JDRiskHandleManager setMtaHelper(JDRiskHandleMtaHelper jDRiskHandleMtaHelper) {
        com.jingdong.app.mall.bundle.jdrhsdk.d.a.k(jDRiskHandleMtaHelper);
        return this;
    }

    public JDRiskHandleManager setVerifyHelper(JDRiskHandleVerifyHelper jDRiskHandleVerifyHelper) {
        com.jingdong.app.mall.bundle.jdrhsdk.d.a.l(jDRiskHandleVerifyHelper);
        return this;
    }
}
