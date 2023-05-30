package com.jingdong.common.widget.custom.liveplayer.plugin;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.Nullable;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.un.utils.UnAndroidUtils;
import com.jd.xbridge.base.IBridgeCallback;
import com.jd.xbridge.base.IBridgePlugin;
import com.jd.xbridge.base.IBridgeWebView;
import com.jd.xbridge.base.a;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.widget.custom.liveplayer.LivePlayer;
import com.jingdong.common.widget.custom.liveplayer.bean.LiveSmallWindowBean;
import com.jingdong.common.widget.custom.liveplayer.bean.TemplateFlagBean;
import com.jingdong.common.widget.custom.liveplayer.callback.LiveSmallWindowCallback;
import com.jingdong.common.widget.custom.liveplayer.videosmallwindow.LiveSmallWindow;
import com.jingdong.common.widget.custom.liveplayer.videosmallwindow.StandaloneSmallWindowManager;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes12.dex */
public class LiveSmallWindowPlugin implements IBridgePlugin, a {
    private static final String TAG = "LiveSmallWindowPlugin";
    private LiveSmallWindow mLiveSmallWindow;
    private boolean mneedLocalDestorySmallWindow = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class SmallWindowPlayerChangeEx extends StandaloneSmallWindowManager.SmallWindowPlayerChange {
        SmallWindowPlayerChangeEx() {
        }

        @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.StandaloneSmallWindowManager.SmallWindowPlayerChange, com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager.IPlayerChange
        public void onSmallClick() {
            LivePlayer livePlayerInstance;
            if (LiveSmallWindowPlugin.this.mLiveSmallWindow == null || (livePlayerInstance = LiveSmallWindowPlugin.this.mLiveSmallWindow.getLivePlayerInstance()) == null) {
                return;
            }
            if (livePlayerInstance.isJumpOut()) {
                livePlayerInstance.jump("");
                LiveSmallWindow.destoryWindow();
                return;
            }
            livePlayerInstance.retryPlay();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createAndShowLiveSmallWindow(BaseActivity baseActivity, String str, final IBridgeCallback iBridgeCallback) {
        JDJSONObject jDJSONObject;
        TemplateFlagBean templateFlagBean = null;
        try {
            jDJSONObject = JDJSON.parseObject(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            jDJSONObject = null;
        }
        if (jDJSONObject == null) {
            return;
        }
        Boolean bool = jDJSONObject.getBoolean("needLocalDestroySmallWindow");
        if (bool != null && bool.booleanValue()) {
            this.mneedLocalDestorySmallWindow = true;
        }
        try {
            templateFlagBean = (TemplateFlagBean) JDJSON.parseObject(str, TemplateFlagBean.class);
        } catch (Exception unused) {
            Log.d(TAG, "templateFlag parse error");
        }
        if (templateFlagBean == null) {
            return;
        }
        if (TextUtils.isEmpty(templateFlagBean.liveId) && TextUtils.isEmpty(templateFlagBean.sku)) {
            return;
        }
        LiveSmallWindow.Builder builder = new LiveSmallWindow.Builder(baseActivity, templateFlagBean.templateFlag);
        builder.injectParams(templateFlagBean);
        builder.setShowDecoration(false);
        builder.setShowBorder(true);
        builder.setNeedAttachSide(!UnAndroidUtils.isMatex(baseActivity));
        builder.setIPlayerChange(new SmallWindowPlayerChangeEx()).setCallbackListener(new LiveSmallWindowCallback() { // from class: com.jingdong.common.widget.custom.liveplayer.plugin.LiveSmallWindowPlugin.2
            @Override // com.jingdong.common.widget.custom.liveplayer.callback.SmallWindowUICallback
            public void onClose(boolean z) {
                LiveSmallWindow.destoryWindow();
            }

            @Override // com.jingdong.common.widget.custom.liveplayer.callback.ErrorCallback
            public void onError(int i2, String str2) {
                if (iBridgeCallback != null) {
                    JDJSONObject jDJSONObject2 = new JDJSONObject();
                    jDJSONObject2.put("errCode", (Object) Integer.valueOf(i2));
                    jDJSONObject2.put("errMsg", (Object) str2);
                    iBridgeCallback.onError(jDJSONObject2.toJSONString());
                }
            }

            @Override // com.jingdong.common.widget.custom.liveplayer.callback.LiveSmallWindowCallback
            public void onGetData(LiveSmallWindowBean liveSmallWindowBean) {
            }

            @Override // com.jingdong.common.widget.custom.liveplayer.callback.PlayCallBack
            public void onPlay() {
                IBridgeCallback iBridgeCallback2 = iBridgeCallback;
                if (iBridgeCallback2 != null) {
                    iBridgeCallback2.onSuccess("success");
                }
            }

            @Override // com.jingdong.common.widget.custom.liveplayer.callback.SmallWindowUICallback
            public void onShowSmallWindow() {
            }
        });
        LiveSmallWindow build = builder.build();
        this.mLiveSmallWindow = build;
        build.startLiveSmallWindow();
    }

    @Nullable
    private BaseActivity getBaseActivity(IBridgeWebView iBridgeWebView) {
        if (iBridgeWebView == null || iBridgeWebView.getView() == null) {
            return null;
        }
        View view = iBridgeWebView.getView();
        Context context = view.getContext();
        while (!(context instanceof BaseActivity) && view.getParent() != null) {
            try {
                view = (View) view.getParent();
                context = view.getContext();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (context instanceof BaseActivity) {
            return (BaseActivity) context;
        }
        return null;
    }

    public void closeLiveSmallWindow() {
        LiveSmallWindow.destoryWindow();
    }

    public void closeLiveSmallWindowInMainThread() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.jingdong.common.widget.custom.liveplayer.plugin.LiveSmallWindowPlugin.3
            @Override // java.lang.Runnable
            public void run() {
                LiveSmallWindowPlugin.this.closeLiveSmallWindow();
            }
        });
    }

    public void createAndShowLiveSmallWindowInMainThread(final BaseActivity baseActivity, final String str, final IBridgeCallback iBridgeCallback) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.jingdong.common.widget.custom.liveplayer.plugin.LiveSmallWindowPlugin.1
            @Override // java.lang.Runnable
            public void run() {
                LiveSmallWindowPlugin.this.createAndShowLiveSmallWindow(baseActivity, str, iBridgeCallback);
            }
        });
    }

    @Override // com.jd.xbridge.base.a
    public void destroy() {
        if (this.mneedLocalDestorySmallWindow) {
            closeLiveSmallWindowInMainThread();
        }
    }

    @Override // com.jd.xbridge.base.IBridgePlugin
    public boolean execute(@Nullable IBridgeWebView iBridgeWebView, @Nullable String str, @Nullable String str2, @Nullable IBridgeCallback iBridgeCallback) {
        if (str == null || iBridgeWebView == null || iBridgeWebView.getView() == null) {
            return false;
        }
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -643994797:
                if (str.equals("createAndShowLiveSmallWindow")) {
                    c2 = 0;
                    break;
                }
                break;
            case 779425193:
                if (str.equals("hideLiveSmallWindow")) {
                    c2 = 1;
                    break;
                }
                break;
            case 1309921843:
                if (str.equals("closeLiveSmallWindow")) {
                    c2 = 2;
                    break;
                }
                break;
            case 2103233255:
                if (str.equals("exposeLiveSmallWindow")) {
                    c2 = 3;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                BaseActivity baseActivity = getBaseActivity(iBridgeWebView);
                if (baseActivity == null) {
                    return false;
                }
                createAndShowLiveSmallWindowInMainThread(baseActivity, str2, iBridgeCallback);
                return true;
            case 1:
                hideLiveSmallWindowInMainThread();
                return true;
            case 2:
                closeLiveSmallWindowInMainThread();
                return true;
            case 3:
                exposeLiveSmallWindowInMainThread();
                return true;
            default:
                return false;
        }
    }

    public void exposeLiveSmallWindow() {
        LiveSmallWindow.exposeSmallWindow();
    }

    public void exposeLiveSmallWindowInMainThread() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.jingdong.common.widget.custom.liveplayer.plugin.LiveSmallWindowPlugin.5
            @Override // java.lang.Runnable
            public void run() {
                LiveSmallWindowPlugin.this.exposeLiveSmallWindow();
            }
        });
    }

    public void hideLiveSmallWindow() {
        LiveSmallWindow.hideSmallWindow();
    }

    public void hideLiveSmallWindowInMainThread() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.jingdong.common.widget.custom.liveplayer.plugin.LiveSmallWindowPlugin.4
            @Override // java.lang.Runnable
            public void run() {
                LiveSmallWindowPlugin.this.hideLiveSmallWindow();
            }
        });
    }
}
