package com.jingdong.common.web.util;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.util.WebNaviSettings;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.common.utils.DeepDarkUtils;
import com.jingdong.common.utils.WebViewHelper;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes12.dex */
public class JDAppearanceHelper implements IJavaInterface {
    private Observer<Integer> darkModeObserver;
    private int uiMode;
    private JDWebView webView;
    private final String TAG = getClass().getSimpleName();
    private boolean firstAddObserver = true;
    private boolean naviIconStyleCustomized = false;
    private boolean naviBgCustomized = false;
    private boolean customListenForNavi = false;
    private boolean customListenForWeb = false;

    public JDAppearanceHelper(JDWebView jDWebView) {
        this.uiMode = -1;
        this.webView = jDWebView;
        this.uiMode = DeepDarkChangeManager.getInstance().getUIMode() == 1 ? 1 : 0;
        WebNaviSettings.bgDarkModeColor = DeepDarkUtils.getDarkColor_F2F2F2_bg1();
        if (isDarkMode()) {
            setDarkModeForWebView(true);
        }
        DeepDarkChangeManager deepDarkChangeManager = DeepDarkChangeManager.getInstance();
        Observer<Integer> observer = new Observer<Integer>() { // from class: com.jingdong.common.web.util.JDAppearanceHelper.1
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable Integer num) {
                int i2 = 0;
                if (JDAppearanceHelper.this.firstAddObserver) {
                    JDAppearanceHelper.this.firstAddObserver = false;
                    return;
                }
                if (Log.D) {
                    Log.d(JDAppearanceHelper.this.TAG, "UI mode changed. new mode = " + num);
                }
                if (num == null || num.intValue() != 0) {
                    i2 = (num == null || num.intValue() != 1) ? -1 : 1;
                }
                JDAppearanceHelper.this.changeUiMode(i2);
            }
        };
        this.darkModeObserver = observer;
        deepDarkChangeManager.addDeepDarkChangeListener(jDWebView, observer);
    }

    private void notifyWebAppearanceChanged(int i2) {
        if (this.webView != null) {
            if (Log.D) {
                Log.d(this.TAG, "Notify web UI mode changed. new mode = " + i2);
            }
            this.webView.injectJs("javascript:window.jdAppearanceDidChangedNotification && jdAppearanceDidChangedNotification(" + i2 + ");");
        }
    }

    private void setDarkModeForWebView(boolean z) {
        JDWebView jDWebView = this.webView;
        if (jDWebView == null || jDWebView.getWebView() == null) {
            return;
        }
        if (z) {
            if (this.webView.getPullToRefreshView() != null) {
                this.webView.getPullToRefreshView().setBackgroundColor(WebNaviSettings.bgDarkModeColor);
            }
            if (this.webView.getWebView() != null) {
                this.webView.getWebView().setBackgroundColor(WebNaviSettings.bgDarkModeColor);
                return;
            }
            return;
        }
        if (this.webView.getPullToRefreshView() != null) {
            this.webView.getPullToRefreshView().setBackgroundColor(0);
        }
        if (this.webView.getWebView() != null) {
            this.webView.getWebView().setBackgroundColor(0);
        }
    }

    private boolean validateUiMode(int i2) {
        if (i2 == 0 || i2 == 1) {
            return true;
        }
        if (Log.E) {
            Log.e(this.TAG, "Illegal UI Mode = " + i2);
            return false;
        }
        return false;
    }

    public void changeUiMode(int i2) {
        if (validateUiMode(i2)) {
            this.uiMode = i2;
            JDWebView jDWebView = this.webView;
            if (jDWebView != null) {
                WebViewHelper.changeJdUaForDarkMode(jDWebView.getWebView(), i2);
                if (!this.customListenForNavi) {
                    boolean z = i2 == 1;
                    if (!this.webView.isTopBarGone() && this.webView.getNavigatorHolder() != null) {
                        boolean z2 = this.naviBgCustomized;
                        if (z2 && this.naviIconStyleCustomized) {
                            this.webView.getNavigatorHolder().setDarkModeWithoutChangeUi(z);
                        } else if (z2) {
                            this.webView.getNavigatorHolder().setDarkModeForIconTitle(z);
                        } else if (this.naviIconStyleCustomized) {
                            this.webView.getNavigatorHolder().setDarkModeForBackground(z);
                        } else {
                            this.webView.getNavigatorHolder().setDarkMode(z);
                        }
                    }
                    setDarkModeForWebView(z);
                }
                if (this.customListenForWeb) {
                    return;
                }
                notifyWebAppearanceChanged(i2);
            }
        }
    }

    public void destroy() {
        this.webView = null;
        DeepDarkChangeManager.getInstance().removeDeepDarkChangeListener(this.darkModeObserver);
        this.darkModeObserver = null;
    }

    @Override // com.jingdong.common.web.javainterface.IJavaInterface
    public String getName() {
        return WebUiConstans.JavaInterfaceNames.JDAPPEARANCE;
    }

    @JavascriptInterface
    public void getUiState(String str) {
        JDJSONObject parseObject = !TextUtils.isEmpty(str) ? JDJSON.parseObject(str) : null;
        String optString = parseObject != null ? parseObject.optString("callBackName") : null;
        if (TextUtils.isEmpty(optString)) {
            return;
        }
        if (DeepDarkChangeManager.getInstance().getUIMode() == 1) {
            this.uiMode = 1;
        } else {
            this.uiMode = 0;
        }
        if (this.webView != null) {
            String str2 = "javascript:" + optString + "('" + WebUtils.stringfyJSonData("0", Integer.valueOf(this.uiMode), "") + "');";
            this.webView.injectJs(str2);
            if (Log.D) {
                Log.d(this.TAG, "sendJSONToM, injectJs--> " + str2);
            }
        }
    }

    public boolean isDarkMode() {
        return this.uiMode == 1;
    }

    public void setCustomListenDarkMode(boolean z, boolean z2) {
        this.customListenForNavi = z;
        this.customListenForWeb = z2;
    }

    public void setNaviBackgroundCustomized(boolean z) {
        this.naviBgCustomized = z;
    }

    public void setNaviIconStyleCustomized(boolean z) {
        this.naviIconStyleCustomized = z;
    }
}
