package com.jingdong.common.XView;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.jd.framework.json.JDJSON;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.kepler.DragView;
import com.jingdong.common.kepler.KeplerJumpUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes5.dex */
public class RetainXViewHelper {
    private static XView mXView;

    /* loaded from: classes5.dex */
    private static class XViewRequestBean {
        public String action;
        public String des;

        private XViewRequestBean() {
        }
    }

    public static boolean closeRetainXView() {
        XView xView = mXView;
        if (xView != null) {
            xView.destroyXView();
            mXView = null;
            return true;
        }
        return false;
    }

    public static void onResume() {
        if (mXView != null) {
            if (Log.D) {
                Log.d("RetainXViewHelper", "onResume()");
            }
            mXView.onResume();
        }
    }

    public static void onStop() {
        if (mXView != null) {
            if (Log.D) {
                Log.d("RetainXViewHelper", "onStop()");
            }
            mXView.onStop();
        }
    }

    public static void postUrl(BaseActivity baseActivity, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUrl(str);
        httpSetting.setPost(false);
        httpSetting.setCacheMode(2);
        httpSetting.setEffect(0);
        baseActivity.getHttpGroupaAsynPool().add(httpSetting);
    }

    public static void showRetainXView(final BaseActivity baseActivity, final ViewGroup viewGroup, final DragView dragView, final String str, final String str2) {
        if (baseActivity == null) {
            return;
        }
        XView xView = mXView;
        if (xView == null || !xView.isXViewShow()) {
            if (Log.D) {
                Log.d("RetainXViewHelper", "showRetainXView");
            }
            XViewEntity xViewEntity = new XViewEntity();
            xViewEntity.url = str;
            xViewEntity.needCloseButton = false;
            xViewEntity.needAutoDisplay = false;
            XViewCallBackAdapter xViewCallBackAdapter = new XViewCallBackAdapter() { // from class: com.jingdong.common.XView.RetainXViewHelper.1
                @Override // com.jingdong.common.XView.XViewCallBackAdapter, com.jingdong.common.XView.XViewCallBack
                public void onError(int i2) {
                    super.onError(i2);
                    JDMtaUtils.sendCommonData(BaseActivity.this, "KeplerBack_onError", "", "", "", "", "", "", "MbackFlow");
                }

                @Override // com.jingdong.common.XView.XViewCallBackAdapter, com.jingdong.common.XView.XViewCallBack
                public void onXViewDisplayed() {
                    if (Log.D) {
                        Log.d("RetainXViewHelper", "showRetainXView---onXViewDisplayed");
                    }
                    KeplerJumpUtils.setIsRequestOpenRetainView(false);
                    KeplerJumpUtils.setHasShownRetainView(true);
                    RetainXViewHelper.postUrl(BaseActivity.this, str2);
                    JDMtaUtils.sendExposureData(BaseActivity.this, "", "MbackFlow", "", "MbackFlow_BackPopupExpo", str, "", "", "");
                }

                @Override // com.jingdong.common.XView.XViewCallBackAdapter, com.jingdong.common.XView.XViewCallBack
                public void onXViewReady() {
                    if (Log.D) {
                        Log.d("RetainXViewHelper", "showRetainXView---onXViewReady");
                    }
                    JDMtaUtils.sendCommonData(BaseActivity.this, "KeplerBack_onXViewReady", "", "", "", "", "", "", "MbackFlow");
                    if (RetainXViewHelper.mXView != null) {
                        RetainXViewHelper.mXView.displayXView();
                    }
                }

                @Override // com.jingdong.common.XView.XViewCallBackAdapter, com.jingdong.common.XView.XViewCallBack
                public void onXViewRequest(XViewRequest xViewRequest) {
                    XViewRequestBean xViewRequestBean;
                    if (Log.D) {
                        Log.d("RetainXViewHelper", "showRetainXView---onXViewRequest");
                    }
                    if (xViewRequest == null || TextUtils.isEmpty(xViewRequest.requestParams) || (xViewRequestBean = (XViewRequestBean) JDJSON.parseObject(xViewRequest.requestParams, XViewRequestBean.class)) == null || !TextUtils.equals(xViewRequestBean.action, "click") || !TextUtils.equals(xViewRequestBean.des, "detentionExit")) {
                        return;
                    }
                    if (RetainXViewHelper.mXView != null) {
                        RetainXViewHelper.mXView.destroyXView();
                    }
                    JDMtaUtils.sendCommonData(BaseActivity.this, "KeplerBack_onXViewRequest", "", "", "", "", "", "", "MbackFlow");
                    KeplerJumpUtils.backToThirdApp(BaseActivity.this, viewGroup, dragView);
                }

                @Override // com.jingdong.common.XView.XViewCallBackAdapter, com.jingdong.common.XView.XViewCallBack
                public void onXVivewClosed() {
                    if (Log.D) {
                        Log.d("RetainXViewHelper", "showRetainXView---onXVivewClosed");
                    }
                    KeplerJumpUtils.setIsRequestOpenRetainView(false);
                    if (RetainXViewHelper.mXView != null) {
                        XView unused = RetainXViewHelper.mXView = null;
                    }
                    JDMtaUtils.sendCommonData(BaseActivity.this, "KeplerBack_onXVivewClosed", "", "", "", "", "", "", "MbackFlow");
                }
            };
            View peekDecorView = baseActivity.getWindow().peekDecorView();
            if (peekDecorView == null || !(peekDecorView instanceof ViewGroup)) {
                return;
            }
            if (Log.D) {
                Log.d("RetainXViewHelper", "showRetainXView---createXView");
            }
            XView createXView = XViewHelper.createXView(baseActivity, (ViewGroup) peekDecorView, baseActivity.getClass().getSimpleName(), xViewEntity, xViewCallBackAdapter);
            mXView = createXView;
            if (createXView != null) {
                createXView.startXView();
                JDMtaUtils.sendCommonData(baseActivity, "KeplerBack_createXView", "", "", "", "", "", "", "MbackFlow");
            }
        }
    }
}
