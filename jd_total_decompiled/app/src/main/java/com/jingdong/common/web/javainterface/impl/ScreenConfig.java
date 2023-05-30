package com.jingdong.common.web.javainterface.impl;

import android.webkit.JavascriptInterface;
import androidx.annotation.Keep;
import com.jd.lib.un.utils.UnAndroidUtils;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

@Keep
/* loaded from: classes12.dex */
public class ScreenConfig extends BaseWebComponent implements IJavaInterface {
    public static final String SWITCH_NAME = "wvMultiScreen";
    private final AtomicBoolean listenChange;

    public ScreenConfig() {
        this.listenChange = new AtomicBoolean(false);
    }

    @Override // com.jingdong.common.web.javainterface.IJavaInterface
    public String getName() {
        return WebUiConstans.JavaInterfaceNames.JD_SCREEN_CONFIG;
    }

    @JavascriptInterface
    public boolean isMultiScreen() {
        IWebUiBinder iWebUiBinder = this.webUiBinder;
        BaseActivity baseActivity = iWebUiBinder != null ? iWebUiBinder.getBaseActivity() : null;
        if (baseActivity == null) {
            return false;
        }
        return UnAndroidUtils.mateXEasyClient(baseActivity);
    }

    @JavascriptInterface
    public void listenScreenChange() {
        this.listenChange.set(true);
    }

    public void onConfigurationChanged() {
        if (this.listenChange.get()) {
            try {
                IWebUiBinder iWebUiBinder = this.webUiBinder;
                BaseActivity baseActivity = iWebUiBinder != null ? iWebUiBinder.getBaseActivity() : null;
                if (baseActivity == null) {
                    return;
                }
                boolean mateXEasyClient = UnAndroidUtils.mateXEasyClient(baseActivity);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("multiScreen", mateXEasyClient);
                this.webUiBinder.getJdWebView().injectJs("window.onJDScreenChanged && window.onJDScreenChanged('" + jSONObject.toString() + "')");
            } catch (Exception unused) {
            }
        }
    }

    public void unregisterListener() {
        this.listenChange.set(false);
    }

    public ScreenConfig(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
        this.listenChange = new AtomicBoolean(false);
    }
}
