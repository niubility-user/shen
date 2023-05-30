package com.jingdong.common.XView;

import android.app.Activity;
import android.view.ViewGroup;
import com.jingdong.common.BaseActivity;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes5.dex */
public class XViewHelper {
    static final String TAG = "XViewHelper";

    public static XView createXView(Activity activity, ViewGroup viewGroup, String str, XViewEntity xViewEntity, XViewCallBack xViewCallBack) {
        if (xViewEntity == null || viewGroup == null || !(activity instanceof BaseActivity)) {
            return null;
        }
        Log.d(TAG, "createXView:" + str + "  " + xViewEntity.toString());
        XView xView = new XView(activity);
        xView.configXView(viewGroup, xViewEntity, xViewCallBack);
        return xView;
    }
}
