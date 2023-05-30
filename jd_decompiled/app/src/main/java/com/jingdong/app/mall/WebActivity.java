package com.jingdong.app.mall;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.utils.WebViewHelper;
import com.jingdong.common.web.WebJumpUtils;
import com.jingdong.common.web.ui.CommonMFragment;
import com.jingdong.common.web.ui.FollowMFragment;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.perfmonitor.IJankCustomInfo;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes19.dex */
public class WebActivity extends MyActivity implements IJankCustomInfo {
    private static final String TAG = WebActivity.class.getSimpleName();
    protected CommonMFragment mFragment;
    protected FragmentManager mFragmentManager;
    protected final String FRAGMENT_TAG = "TAG_CommonMFragment";
    protected boolean pvFirstEnter = true;

    private void addMFragment() {
        if (findViewById(R.id.dp) != null) {
            CommonMFragment fragment = getFragment();
            this.mFragment = fragment;
            if (fragment.isAdded()) {
                return;
            }
            getSupportFragmentManager().beginTransaction().add(R.id.dp, this.mFragment, "TAG_CommonMFragment").commitAllowingStateLoss();
        }
    }

    private CommonMFragment getFragment() {
        Fragment findFragmentByTag = this.mFragmentManager.findFragmentByTag("TAG_CommonMFragment");
        if (findFragmentByTag != null && (findFragmentByTag instanceof CommonMFragment)) {
            return (CommonMFragment) findFragmentByTag;
        }
        CommonMFragment newFragment = newFragment();
        getIntent().putExtra("canUseDarkMode", true);
        newFragment.setArguments(getIntent().getExtras());
        return newFragment;
    }

    private String getUrlHistory() {
        List<String> urlHistory;
        CommonMFragment commonMFragment = this.mFragment;
        if (commonMFragment == null || commonMFragment.getJdWebView() == null || this.mFragment.getJdWebView().getWebView() == null || (urlHistory = this.mFragment.getJdWebView().getUrlHistory()) == null || urlHistory.size() <= 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = urlHistory.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append("\u2192");
        }
        if (OKLog.D) {
            OKLog.d(TAG, sb.toString());
        }
        return sb.toString();
    }

    private void setBgColorForUrl() {
        View findViewById;
        try {
            String switchStringValue = SwitchQueryFetcher.getSwitchStringValue("webActWhiteBg", null);
            if (TextUtils.isEmpty(switchStringValue)) {
                return;
            }
            String urlFromBundleURLDecode = WebJumpUtils.getUrlFromBundleURLDecode(getIntent().getExtras());
            if (TextUtils.isEmpty(urlFromBundleURLDecode) || !WebUtils.hostListWithKeyWord(urlFromBundleURLDecode, Uri.parse(urlFromBundleURLDecode).getHost(), switchStringValue.split(";")) || (findViewById = findViewById(R.id.dp)) == null) {
                return;
            }
            findViewById.setBackgroundColor(-1);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    protected void configStatusBar() {
        getWindow().setFormat(-3);
        this.statusBarTransparentEnable = true;
    }

    @Override // com.jingdong.sdk.perfmonitor.IJankCustomInfo
    public Map<String, String> getJankCustomInfo() {
        return WebViewHelper.getWebViewJankInfo();
    }

    @Override // com.jingdong.common.BaseActivity
    public String getPageParam() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"sign\":\"");
        sb.append(this.pvFirstEnter ? "0" : "1");
        sb.append("\",\"url\":\"");
        CommonMFragment commonMFragment = this.mFragment;
        sb.append(commonMFragment != null ? commonMFragment.getUrl() : "");
        sb.append("\"}");
        return sb.toString();
    }

    protected CommonMFragment newFragment() {
        if (getIntent() != null && JumpUtil.VALUE_DES_GUANZHU.equals(getIntent().getStringExtra("des"))) {
            return new FollowMFragment();
        }
        return new CommonMFragment();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        CommonMFragment commonMFragment = this.mFragment;
        if (commonMFragment != null) {
            commonMFragment.onActivityResult(i2, i3, intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        configStatusBar();
        super.onCreate(bundle);
        setContentView(R.layout.app_webview);
        setBgColorForUrl();
        setPageId("InlineWebView_H5PV");
        this.mFragmentManager = getSupportFragmentManager();
        addMFragment();
    }

    @Override // com.jingdong.common.BaseActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        CommonMFragment commonMFragment = this.mFragment;
        if (commonMFragment == null || !commonMFragment.onKeyDown(i2, keyEvent)) {
            return super.onKeyDown(i2, keyEvent);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.pvFirstEnter = false;
    }

    public void stopLoading() {
        CommonMFragment commonMFragment = this.mFragment;
        if (commonMFragment != null) {
            commonMFragment.stopLoading();
        }
    }
}
