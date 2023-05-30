package com.unicom.xiaowo.login;

import android.content.Context;
import android.net.Network;
import android.os.Build;
import android.text.TextUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.unicom.xiaowo.login.b.a;
import com.unicom.xiaowo.login.d.b;
import com.unicom.xiaowo.login.d.e;
import com.unicom.xiaowo.login.d.f;
import com.unicom.xiaowo.login.d.g;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class UniAuthHelper {
    private static volatile UniAuthHelper s_instance;
    private Context mContext;

    private UniAuthHelper() {
    }

    public static UniAuthHelper getInstance() {
        if (s_instance == null) {
            synchronized (UniAuthHelper.class) {
                if (s_instance == null) {
                    s_instance = new UniAuthHelper();
                }
            }
        }
        return s_instance;
    }

    private void initFail(ResultListener resultListener, String str) {
        e.b(str);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("resultCode", 1);
            jSONObject.put(CartConstant.KEY_CART_RESULTMSG, str);
            jSONObject.put("resultData", "");
            jSONObject.put("traceId", "");
            jSONObject.put("operatorType", "CU");
            if (resultListener != null) {
                resultListener.onResult(jSONObject.toString());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void enableLogger(boolean z) {
        e.a(z);
    }

    public void getAccessCode(int i2, ResultListener resultListener) {
        if (this.mContext != null && !TextUtils.isEmpty(f.a()) && !TextUtils.isEmpty(f.b())) {
            f.a(i2);
            new a().a(this.mContext, i2, 3, resultListener);
            return;
        }
        initFail(resultListener, "sdk\u672a\u521d\u59cb\u5316");
    }

    public void init(Context context, String str, String str2) {
        if (context != null) {
            try {
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                    if (!TextUtils.isEmpty(f.a())) {
                        e.b("\u4e0d\u53ef\u91cd\u590d\u521d\u59cb\u5316");
                        return;
                    }
                    this.mContext = context.getApplicationContext();
                    f.a(str);
                    f.b(str2);
                    f.f(g.b(this.mContext));
                    f.g(com.unicom.xiaowo.login.d.a.a(this.mContext));
                    if (Build.VERSION.SDK_INT >= 21) {
                        b.a().a((Network) null, "opencloud.wostore.cn");
                        return;
                    }
                    return;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        e.b("\u521d\u59cb\u5316\u53c2\u6570\u4e0d\u80fd\u4e3a\u7a7a");
    }

    public void mobileAuth(int i2, ResultListener resultListener) {
        if (this.mContext != null && !TextUtils.isEmpty(f.a()) && !TextUtils.isEmpty(f.b())) {
            f.a(i2);
            new a().a(this.mContext, i2, 2, resultListener);
            return;
        }
        initFail(resultListener, "sdk\u672a\u521d\u59cb\u5316");
    }

    public void releaseNetwork() {
        b.a().b();
    }
}
