package com.jingdong.common.web.javainterface.impl;

import android.os.Handler;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.libs.jdmbridge.base.proxy.share.IShareAdapter;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.utils.ShareUtil;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.entity.JsBridgeEntity;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.util.WebUnifiedMtaUtil;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.common.web.xrender.ICallback;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.ClickConstant;
import java.lang.reflect.Method;

/* loaded from: classes12.dex */
public final class ShareHelper extends BaseWebComponent implements IJavaInterface {
    private final String TAG;
    private boolean isIgnoreShare;
    private boolean isPreRender;
    private JsBridgeEntity jsBridgeEntity;
    private Handler mHandler;
    private JDWebView webView;

    public ShareHelper(IWebUiBinder iWebUiBinder, boolean z, Handler handler) {
        super(iWebUiBinder);
        this.TAG = ShareHelper.class.getSimpleName();
        this.jsBridgeEntity = iWebUiBinder.getWebEntity().jsBridgeEntity;
        this.isIgnoreShare = z;
        this.mHandler = handler;
    }

    private void setShareCallbackListener(boolean z) {
        if (z) {
            this.jsBridgeEntity.shareCallbackListener = new ShareUtil.CallbackListener() { // from class: com.jingdong.common.web.javainterface.impl.ShareHelper.2
                @Override // com.jingdong.common.utils.ShareUtil.CallbackListener
                public void onCancel() {
                    try {
                        if (((BaseWebComponent) ShareHelper.this).webUiBinder.getJdWebView() != null) {
                            ((BaseWebComponent) ShareHelper.this).webUiBinder.getJdWebView().injectJs("javascript:jdappShareRes({'shareChannel': '', 'shareResult':'2'});");
                        }
                    } catch (Exception e2) {
                        if (Log.E) {
                            e2.printStackTrace();
                        }
                    }
                }

                @Override // com.jingdong.common.utils.ShareUtil.CallbackListener
                public void onComplete(Object obj) {
                    String str = "{\"shareChannel\": \"" + obj.toString() + "\", \"shareResult\":\"0\"}";
                    try {
                        if (((BaseWebComponent) ShareHelper.this).webUiBinder.getJdWebView() != null) {
                            ((BaseWebComponent) ShareHelper.this).webUiBinder.getJdWebView().injectJs("javascript:jdappShareRes(" + str + ");");
                        }
                    } catch (Exception e2) {
                        if (Log.E) {
                            e2.printStackTrace();
                        }
                    }
                }

                @Override // com.jingdong.common.utils.ShareUtil.CallbackListener
                public void onError(String str) {
                    if (Log.E) {
                        Log.e(ShareHelper.this.TAG, "Shared failed:" + str);
                    }
                    try {
                        if (((BaseWebComponent) ShareHelper.this).webUiBinder.getJdWebView() != null) {
                            ((BaseWebComponent) ShareHelper.this).webUiBinder.getJdWebView().injectJs("javascript:jdappShareRes({\"shareChannel\": \"\", \"shareResult\":\"1\"});");
                        }
                    } catch (Exception e2) {
                        if (Log.E) {
                            e2.printStackTrace();
                        }
                    }
                }
            };
            return;
        }
        this.jsBridgeEntity.shareCallbackListener = null;
    }

    private void setShareClickCallbackListener(boolean z) {
        if (z) {
            this.jsBridgeEntity.shareClickCallbackListener = new ShareUtil.ClickCallbackListener() { // from class: com.jingdong.common.web.javainterface.impl.ShareHelper.3
                @Override // com.jingdong.common.utils.ShareUtil.ClickCallbackListener
                public void onClick(String str) {
                    String str2 = "{\"shareChannel\": \"" + str + "\"}";
                    try {
                        if (((BaseWebComponent) ShareHelper.this).webUiBinder.getJdWebView() != null) {
                            ((BaseWebComponent) ShareHelper.this).webUiBinder.getJdWebView().injectJs("javascript:jdappShareRes(" + str2 + ");");
                        }
                    } catch (Exception e2) {
                        if (Log.E) {
                            e2.printStackTrace();
                        }
                    }
                }
            };
            return;
        }
        this.jsBridgeEntity.shareClickCallbackListener = null;
    }

    @JavascriptInterface
    /* renamed from: androidShare  reason: merged with bridge method [inline-methods] */
    public void b(final String str, final String str2, final String str3, final String str4) {
        JDWebView jDWebView = this.webView;
        if (jDWebView != null && this.isPreRender && !jDWebView.isAttached()) {
            this.webView.getXUiBinder().addCacheJsBridgeInterface(new ICallback() { // from class: com.jingdong.common.web.javainterface.impl.v
                @Override // com.jingdong.common.web.xrender.ICallback
                public final void execute() {
                    ShareHelper.this.b(str, str2, str3, str4);
                }
            }, getName() + ".androidShare");
            return;
        }
        setShareInfo(str, str2, str3, str4, false);
        if (Log.D) {
            Log.d(this.TAG, "share title from H5: " + str);
            Log.d(this.TAG, "share content from H5: " + str2);
            Log.d(this.TAG, "share url from H5: " + this.webUiBinder.getJdWebView().getFinalUrl());
            Log.d(this.TAG, "share iconUrl from H5: " + str4);
        }
        BaseActivity baseActivity = this.webUiBinder.getBaseActivity();
        JsBridgeEntity jsBridgeEntity = this.jsBridgeEntity;
        ShareUtil.showShareDialog(baseActivity, jsBridgeEntity.shareInfo, jsBridgeEntity.shareCallbackListener, jsBridgeEntity.shareClickCallbackListener);
        WebUtils.makeShareMta(this.webUiBinder, str3);
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "ShareHelper-androidShare");
    }

    @JavascriptInterface
    /* renamed from: callShare  reason: merged with bridge method [inline-methods] */
    public void d(final String str, final String str2, final String str3, final String str4, final String str5) {
        JDWebView jDWebView = this.webView;
        if (jDWebView != null && this.isPreRender && !jDWebView.isAttached()) {
            this.webView.getXUiBinder().addCacheJsBridgeInterface(new ICallback() { // from class: com.jingdong.common.web.javainterface.impl.r
                @Override // com.jingdong.common.web.xrender.ICallback
                public final void execute() {
                    ShareHelper.this.d(str, str2, str3, str4, str5);
                }
            }, getName() + ".callShare");
            return;
        }
        setShareInfo(str, str2, str3, str4, false);
        if (Log.D) {
            Log.d(this.TAG, "share title from H5: " + str);
            Log.d(this.TAG, "share content from H5: " + str2);
            Log.d(this.TAG, "share url from H5: " + this.webUiBinder.getJdWebView().getFinalUrl());
            Log.d(this.TAG, "share iconUrl from H5: " + str4);
        }
        setShareCallbackListener("Y".equals(str5));
        BaseActivity baseActivity = this.webUiBinder.getBaseActivity();
        JsBridgeEntity jsBridgeEntity = this.jsBridgeEntity;
        ShareUtil.showShareDialog(baseActivity, jsBridgeEntity.shareInfo, jsBridgeEntity.shareCallbackListener, jsBridgeEntity.shareClickCallbackListener);
        WebUtils.makeShareMta(this.webUiBinder, str3);
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "ShareHelper-callShare");
    }

    @Override // com.jingdong.common.web.javainterface.IJavaInterface
    public String getName() {
        return WebUiConstans.JavaInterfaceNames.SHARE_HELPER;
    }

    @JavascriptInterface
    /* renamed from: initShare  reason: merged with bridge method [inline-methods] */
    public void f(final String str) {
        JDWebView jDWebView = this.webView;
        if (jDWebView != null && this.isPreRender && !jDWebView.isAttached()) {
            this.webView.getXUiBinder().addCacheJsBridgeInterface(new ICallback() { // from class: com.jingdong.common.web.javainterface.impl.u
                @Override // com.jingdong.common.web.xrender.ICallback
                public final void execute() {
                    ShareHelper.this.f(str);
                }
            }, getName() + ".initShare");
            return;
        }
        if (Log.D) {
            Log.d(this.TAG, str);
        }
        if (this.isIgnoreShare) {
            return;
        }
        if (str.startsWith("\"")) {
            str = str.substring(1);
        }
        if (str.endsWith("\"")) {
            str = str.substring(0, str.length() - 1);
        }
        JDJSONObject jDJSONObject = null;
        try {
            jDJSONObject = JDJSON.parseObject(str);
        } catch (Exception e2) {
            if (Log.E) {
                e2.printStackTrace();
            }
        }
        if (jDJSONObject == null || jDJSONObject.isEmpty()) {
            return;
        }
        String optString = jDJSONObject.optString("eventId", "");
        if (!TextUtils.isEmpty(optString)) {
            this.jsBridgeEntity.event_id = optString;
        }
        ShareInfo parseShareInfoFromJson = ShareUtil.parseShareInfoFromJson(jDJSONObject);
        if (parseShareInfoFromJson == null) {
            return;
        }
        parseShareInfoFromJson.setEventName(ClickConstant.CLICK_SHARE_VALUE_MPAGE);
        setShareCallbackListener(jDJSONObject.optString("callback").equals("Y"));
        setShareClickCallbackListener(jDJSONObject.optString("clickcallback").equals("Y"));
        String action = parseShareInfoFromJson.getAction();
        if (IShareAdapter.SHARE_ACTION_OPEN.equals(action)) {
            ShareUtil.sendShare(this.webUiBinder.getBaseActivity(), parseShareInfoFromJson, this.jsBridgeEntity.shareCallbackListener);
            WebUtils.makeShareMta(this.webUiBinder, this.jsBridgeEntity.shareInfo.getUrl());
        } else if (IShareAdapter.SHARE_ACTION_PANE.equals(action)) {
            if (parseShareInfoFromJson.isShareGift()) {
                ShareUtil.lottery(this.webUiBinder.getBaseActivity(), parseShareInfoFromJson, parseShareInfoFromJson.getIncentiveBizType(), parseShareInfoFromJson.getIncentiveBizId());
            } else {
                BaseActivity baseActivity = this.webUiBinder.getBaseActivity();
                JsBridgeEntity jsBridgeEntity = this.jsBridgeEntity;
                ShareUtil.showShareDialog(baseActivity, parseShareInfoFromJson, jsBridgeEntity.shareCallbackListener, jsBridgeEntity.shareClickCallbackListener);
            }
            WebUtils.makeShareMta(this.webUiBinder, this.jsBridgeEntity.shareInfo.getUrl());
        } else if ("S".equals(action)) {
            this.jsBridgeEntity.shareInfo = parseShareInfoFromJson;
            setShareInfo(parseShareInfoFromJson.getTitle(), parseShareInfoFromJson.getSummary(), parseShareInfoFromJson.getUrl(), parseShareInfoFromJson.getIconUrl(), true);
        }
    }

    @JavascriptInterface
    /* renamed from: sendShare  reason: merged with bridge method [inline-methods] */
    public void h(final String str, final String str2, final String str3, final String str4, final String str5, final String str6) {
        JDWebView jDWebView = this.webView;
        if (jDWebView != null && this.isPreRender && !jDWebView.isAttached()) {
            this.webView.getXUiBinder().addCacheJsBridgeInterface(new ICallback() { // from class: com.jingdong.common.web.javainterface.impl.q
                @Override // com.jingdong.common.web.xrender.ICallback
                public final void execute() {
                    ShareHelper.this.h(str, str2, str3, str4, str5, str6);
                }
            }, getName() + ".sendShare");
            return;
        }
        setShareInfo(str, str2, str3, str4, false);
        this.jsBridgeEntity.shareInfo.setChannels(str5);
        if (Log.D) {
            Log.d(this.TAG, "share title from H5: " + str);
            Log.d(this.TAG, "share content from H5: " + str2);
            Log.d(this.TAG, "share url from H5: " + this.webUiBinder.getJdWebView().getFinalUrl());
            Log.d(this.TAG, "share iconUrl from H5: " + str4);
        }
        setShareCallbackListener("Y".equals(str6));
        BaseActivity baseActivity = this.webUiBinder.getBaseActivity();
        JsBridgeEntity jsBridgeEntity = this.jsBridgeEntity;
        ShareUtil.sendShare(baseActivity, jsBridgeEntity.shareInfo, jsBridgeEntity.shareCallbackListener);
        WebUtils.makeShareMta(this.webUiBinder, str3);
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "ShareHelper-sendShare");
    }

    @JavascriptInterface
    /* renamed from: setShareInfo  reason: merged with bridge method [inline-methods] */
    public void j(final String str, final String str2, final String str3, final String str4) {
        JDWebView jDWebView = this.webView;
        if (jDWebView != null && this.isPreRender && !jDWebView.isAttached()) {
            this.webView.getXUiBinder().addCacheJsBridgeInterface(new ICallback() { // from class: com.jingdong.common.web.javainterface.impl.t
                @Override // com.jingdong.common.web.xrender.ICallback
                public final void execute() {
                    ShareHelper.this.j(str, str2, str3, str4);
                }
            }, getName() + ".setShareInfo");
        } else if (this.isIgnoreShare) {
        } else {
            setShareInfo(str, str2, str3, str4, true);
            if (Log.D) {
                Log.d(this.TAG, "share title from H5: " + str);
                Log.d(this.TAG, "share content from H5: " + str2);
                Log.d(this.TAG, "share url from H5: " + this.webUiBinder.getJdWebView().getFinalUrl());
                Log.d(this.TAG, "share iconUrl from H5: " + str4);
            }
        }
    }

    @JavascriptInterface
    /* renamed from: setShareInfoCallback  reason: merged with bridge method [inline-methods] */
    public void l(final String str, final String str2, final String str3, final String str4, final String str5) {
        JDWebView jDWebView = this.webView;
        if (jDWebView != null && this.isPreRender && !jDWebView.isAttached()) {
            this.webView.getXUiBinder().addCacheJsBridgeInterface(new ICallback() { // from class: com.jingdong.common.web.javainterface.impl.s
                @Override // com.jingdong.common.web.xrender.ICallback
                public final void execute() {
                    ShareHelper.this.l(str, str2, str3, str4, str5);
                }
            }, getName() + ".setShareInfoCallback");
            return;
        }
        setShareInfo(str, str2, str3, str4, true);
        if (Log.D) {
            Log.d(this.TAG, "share title from H5: " + str);
            Log.d(this.TAG, "share content from H5: " + str2);
            Log.d(this.TAG, "share url from H5: " + this.webUiBinder.getJdWebView().getFinalUrl());
            Log.d(this.TAG, "share iconUrl from H5: " + str4);
        }
        setShareCallbackListener("Y".equals(str5));
    }

    @Override // com.jingdong.common.web.BaseWebComponent
    public void setWebUiBinder(IWebUiBinder iWebUiBinder) {
        super.setWebUiBinder(iWebUiBinder);
        this.jsBridgeEntity = iWebUiBinder.getWebEntity().jsBridgeEntity;
        this.isIgnoreShare = iWebUiBinder.getWebEntity().isIgnoreShare;
    }

    public ShareHelper(JDWebView jDWebView) {
        this.TAG = ShareHelper.class.getSimpleName();
        this.webView = jDWebView;
        this.isPreRender = true;
    }

    private void setShareInfo(String str, String str2, String str3, String str4, boolean z) {
        Method method;
        this.jsBridgeEntity.shareInfo.setTitle(str);
        this.jsBridgeEntity.shareInfo.setSummary(str2);
        this.jsBridgeEntity.shareInfo.setWxcontent(str2);
        this.jsBridgeEntity.shareInfo.setWxMomentsContent(str2);
        this.jsBridgeEntity.shareInfo.setUrl(str3);
        this.jsBridgeEntity.shareInfo.setIconUrl(str4);
        this.jsBridgeEntity.shareInfo.setEventFrom(ClickConstant.CLICK_SHARE_VALUE_MPAGE);
        try {
            Class<?> cls = Class.forName("com.jingdong.common.XView.XView");
            if (cls != null && cls.isInstance(this.webUiBinder.getUi()) && (method = this.webUiBinder.getUi().getClass().getMethod("setXViewShareInfo", ShareInfo.class)) != null) {
                if (((Boolean) method.invoke(this.webUiBinder.getUi(), this.jsBridgeEntity.shareInfo)).booleanValue()) {
                    return;
                }
            }
        } catch (Throwable unused) {
        }
        if (TextUtils.isEmpty(str3) || !z) {
            return;
        }
        this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.ShareHelper.1
            @Override // java.lang.Runnable
            public void run() {
                ShareHelper.this.jsBridgeEntity.isNeedShare = true;
                ((BaseWebComponent) ShareHelper.this).webUiBinder.getJdWebView().setShareBtnState(true, ShareHelper.this.jsBridgeEntity.shareInfo.isShareGift());
            }
        });
    }
}
