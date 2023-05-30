package com.jingdong.common.unification.router;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.unification.router.builder.RouterEntry;
import com.jingdong.common.unification.router.builderimpl.DefaultRouterBuilder;
import com.jingdong.common.unification.router.config.JDRouterConfig;
import com.jingdong.common.unification.router.config.MoudleClassConfig;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDRouter {
    private static final String TAG = "JDRouter";
    private Context context;
    private RouterEntry mRouterEntry = new RouterEntry();
    private String url;

    private JDRouter(Context context, String str) {
        this.context = context;
        this.url = str;
    }

    public static JDRouter build(Context context, String str) {
        return new JDRouter(context, str);
    }

    public static <T> T to(Class<T> cls) {
        if (cls != null) {
            try {
                return cls.newInstance();
            } catch (IllegalAccessException e2) {
                if (JDRouterConfig.isDebug()) {
                    JDRouterConfig.e(TAG, e2);
                }
            } catch (InstantiationException e3) {
                if (JDRouterConfig.isDebug()) {
                    JDRouterConfig.e(TAG, e3);
                }
            }
        }
        throw new IllegalArgumentException(cls.getName() + "can not be instantiated");
    }

    public JDRouter callBackListener(CallBackListener callBackListener) {
        this.mRouterEntry.callBackListener = callBackListener;
        return this;
    }

    public JDRouter extraObject(String str, Object obj) {
        this.mRouterEntry.extraObject(str, obj);
        return this;
    }

    public JDRouter intentFlag(int i2) {
        this.mRouterEntry.intentFlag(i2);
        return this;
    }

    public void open() {
        if (!TextUtils.isEmpty(this.url)) {
            Uri parse = Uri.parse(this.url);
            if (JDRouterConfig.isDebug()) {
                JDRouterConfig.d(TAG, "url:" + this.url);
            }
            if (parse != null && !TextUtils.isEmpty(parse.getScheme()) && JDRouterConfig.getRouterProtocol().equals(parse.getScheme().toLowerCase())) {
                String host = parse.getHost();
                String lastPathSegment = parse.getLastPathSegment();
                JDJSONObject parseParamToJDJson = JDRouterUtil.parseParamToJDJson(this.url);
                try {
                    Class<?> cls = MoudleClassConfig.getClass(host);
                    Object newInstance = cls.newInstance();
                    try {
                        cls.getMethod(lastPathSegment, Context.class, JDJSONObject.class, RouterEntry.class).invoke(newInstance, this.context, parseParamToJDJson, this.mRouterEntry);
                        return;
                    } catch (Exception e2) {
                        if (JDRouterConfig.isDebug()) {
                            JDRouterConfig.e(TAG, e2);
                        }
                        try {
                            cls.getMethod(lastPathSegment, Context.class, JSONObject.class, Bundle.class, CallBackListener.class).invoke(newInstance, this.context, JDRouterUtil.parseParam(this.url), this.mRouterEntry.preAllData(), this.mRouterEntry.callBackListener);
                            return;
                        } catch (Exception e3) {
                            if (JDRouterConfig.isDebug()) {
                                JDRouterConfig.e(TAG, e3);
                            }
                            JDRouterUtil.callBackError(this.mRouterEntry.callBackListener, 702);
                            return;
                        }
                    }
                } catch (Exception e4) {
                    if (JDRouterConfig.isDebug()) {
                        JDRouterConfig.e(TAG, e4);
                    }
                    JDRouterUtil.callBackError(this.mRouterEntry.callBackListener, 702);
                    return;
                }
            }
            JDRouterUtil.callBackError(this.mRouterEntry.callBackListener, 701);
            return;
        }
        JDRouterUtil.callBackError(this.mRouterEntry.callBackListener, 700);
    }

    public JDRouter requestCode(int i2) {
        this.mRouterEntry.requestCode(i2);
        return this;
    }

    public JDRouter setRouterEntry(RouterEntry routerEntry) {
        if (routerEntry != null) {
            this.mRouterEntry = routerEntry;
        }
        return this;
    }

    public JDRouter callBackListener(CallBackWithReturnListener callBackWithReturnListener) {
        this.mRouterEntry.callBackListener = callBackWithReturnListener;
        return this;
    }

    public static DefaultRouterBuilder to(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            if (TextUtils.isEmpty(str2)) {
                str2 = "show";
            }
            return new DefaultRouterBuilder(str, str2);
        }
        throw new IllegalArgumentException("moudleName and methodName cannot be empty!");
    }

    public static JDRouter to(Context context, String str) {
        return new JDRouter(context, str);
    }
}
