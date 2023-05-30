package com.jingdong.common.unification.router.builder;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.CallBackWithReturnListener;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.common.unification.router.OnCallBackListener;
import com.jingdong.common.unification.router.builder.RouterBuilder;
import com.jingdong.common.unification.router.builder.RouterEntry;

/* loaded from: classes6.dex */
public abstract class RouterBuilder<T extends RouterBuilder, V extends RouterEntry> extends BaseBundleBuilder<T, String> {
    private static final String TAG = "RouterBuilder";
    private String defaultMethodName;
    private OnCallBackListener mOnCallBackListener;
    protected V mRouterEntry;
    private String mRouterUrl;
    private String moduleName;

    public RouterBuilder(String str, String str2) {
        this.defaultMethodName = "show";
        createRouterEntry();
        this.moduleName = str;
        this.defaultMethodName = str2;
    }

    private void createRouterEntry() {
        if (this.mRouterEntry == null) {
            this.mRouterEntry = initRouterEntry();
        }
    }

    public T callBackListener(CallBackListener callBackListener) {
        this.mRouterEntry.callBackListener(callBackListener);
        return this;
    }

    public T extraData(Object obj) {
        this.mRouterEntry.extraData(obj);
        return this;
    }

    public T extraObject(String str, Object obj) {
        this.mRouterEntry.extraObject(str, obj);
        return this;
    }

    protected abstract V initRouterEntry();

    public T intentFlag(int i2) {
        this.mRouterEntry.intentFlag(i2);
        return this;
    }

    @Override // com.jingdong.common.unification.router.builder.IBuilder
    public void jump(Context context) {
        String build = build();
        this.mRouterUrl = build;
        JDRouter.to(context, build).setRouterEntry(this.mRouterEntry).open();
    }

    @Deprecated
    public T onCallBackListener(OnCallBackListener onCallBackListener) {
        this.mOnCallBackListener = onCallBackListener;
        this.mRouterEntry.callBackListener(new CallBackListener() { // from class: com.jingdong.common.unification.router.builder.RouterBuilder.1
            @Override // com.jingdong.common.unification.router.CallBackListener
            public void onComplete() {
                if (RouterBuilder.this.mOnCallBackListener != null) {
                    RouterBuilder.this.mOnCallBackListener.onComplete();
                }
            }

            @Override // com.jingdong.common.unification.router.CallBackListener
            public void onError(int i2) {
                if (RouterBuilder.this.mOnCallBackListener != null) {
                    RouterBuilder.this.mOnCallBackListener.onError(i2, RouterBuilder.this.mRouterUrl);
                }
            }
        });
        return this;
    }

    public T requestCode(int i2) {
        this.mRouterEntry.requestCode(i2);
        return this;
    }

    protected T setMethodName(String str) {
        this.defaultMethodName = str;
        return this;
    }

    protected T setModuleName(String str) {
        this.moduleName = str;
        return this;
    }

    @Override // com.jingdong.common.unification.router.builder.IBuilder
    public String build() {
        if (TextUtils.isEmpty(this.moduleName)) {
            return "";
        }
        StringBuilder sb = new StringBuilder("router://");
        sb.append(this.moduleName);
        sb.append("/");
        sb.append(this.defaultMethodName);
        sb.append("?");
        for (String str : this.mBundle.keySet()) {
            sb.append(str);
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(String.valueOf(this.mBundle.get(str)));
            sb.append(ContainerUtils.FIELD_DELIMITER);
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public T callBackListener(CallBackWithReturnListener callBackWithReturnListener) {
        this.mRouterEntry.callBackListener(callBackWithReturnListener);
        return this;
    }

    public RouterBuilder() {
        this.defaultMethodName = "show";
        createRouterEntry();
    }
}
