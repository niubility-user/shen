package com.jd.dynamic.base.interfaces;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.jd.dynamic.base.IFunctionFactory;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public interface IAppRouter {

    /* loaded from: classes13.dex */
    public static class DialogBaseConfig {
        public String bizField;
        public JSONObject businessData;
        public int containerHeight = -1;
        public int containerWidth = -1;
        public IFunctionFactory functionFactory;
        public String systemCode;
    }

    /* loaded from: classes13.dex */
    public static class DialogConfig extends DialogBaseConfig {
        public boolean canCanceledOnTouchOutside = false;
        public String cancelText;
        public String confirmText;
        public String contentText;
        public View.OnClickListener onCancelClick;
        public View.OnClickListener onConfirmClick;
        public String titleText;
    }

    /* loaded from: classes13.dex */
    public static class PopViewConfig extends DialogBaseConfig {
        public String animEffect;
        public String bottom;
        public View.OnClickListener bottomClick;
        public String contentText;
        public String direction;
        public int duration;
        public String titleText;
        public boolean canCanceledOnTouchOutside = true;
        public boolean bgTransparent = true;
        public float heightPercent = 0.6f;
    }

    boolean isHasLogin();

    void jumpToH5(Context context, String str);

    void jumpWithParams(Context context, JSONObject jSONObject);

    void jumpWithUrl(Context context, String str);

    void openJDRouter(String str, IRouterCallBackListener iRouterCallBackListener);

    void showCustomToast(Context context, JSONObject jSONObject);

    Dialog showDialog(Context context, DialogConfig dialogConfig);

    Dialog showPopView(Context context, PopViewConfig popViewConfig);

    void startLoginActivity(Context context, Bundle bundle, ICancelLogin iCancelLogin, String str);
}
