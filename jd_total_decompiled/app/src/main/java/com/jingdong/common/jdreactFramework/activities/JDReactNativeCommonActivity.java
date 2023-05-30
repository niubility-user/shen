package com.jingdong.common.jdreactFramework.activities;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.jingdong.app.mall.utils.q;
import com.jingdong.common.XView.IXView;
import com.jingdong.common.XView.XView;
import com.jingdong.common.XView.XViewCallBackAdapter;
import com.jingdong.common.XView.XViewHelper;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.JDReactExtendPackage;
import com.jingdong.common.jdreactFramework.JDReactPackage;
import com.jingdong.common.jdreactFramework.download.ReactNativeUpdate;
import com.jingdong.common.jdreactFramework.utils.RNSoftHideKeyBoardUtil;

/* loaded from: classes5.dex */
public class JDReactNativeCommonActivity extends JDReactNativeBaseCommonActivity {
    private boolean mIsShown = false;
    private IXView mXView;
    private int mXViewShowTime;

    static /* synthetic */ int access$008(JDReactNativeCommonActivity jDReactNativeCommonActivity) {
        int i2 = jDReactNativeCommonActivity.mXViewShowTime;
        jDReactNativeCommonActivity.mXViewShowTime = i2 + 1;
        return i2;
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity, com.jingdong.common.jdreactFramework.activities.OnXViewActionListener
    public void closeXView() {
        IXView iXView = this.mXView;
        if (iXView != null) {
            iXView.onStop();
        }
        IXView iXView2 = this.mXView;
        if (iXView2 != null) {
            iXView2.closeXView();
        }
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity, com.jingdong.common.jdreactFramework.activities.OnXViewActionListener
    public void destroyXView() {
        IXView iXView = this.mXView;
        if (iXView != null) {
            iXView.destroyXView();
        }
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity
    public boolean forceCloseXView() {
        if (this.mXView == null || !this.mIsShown) {
            return false;
        }
        closeXView();
        destroyXView();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseCommonActivity, com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        RNSoftHideKeyBoardUtil.assistActivity(this);
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity, com.jingdong.common.jdreactFramework.helper.LoadListener
    public void onLoadFinish(String str, String str2, String str3) {
        super.onLoadFinish(str, str2, str3);
        q.c().d(new Runnable() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeCommonActivity.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ReactNativeUpdate.getInstance().checkUpdate();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity, com.jingdong.common.jdreactFramework.activities.OnXViewActionListener
    public void showXView(int i2, String str, boolean z, long j2, boolean z2, final JDCallback jDCallback, final JDCallback jDCallback2) {
        super.showXView(i2, str, z, j2, z2, jDCallback, jDCallback2);
        JDReactXViewEntity jDReactXViewEntity = new JDReactXViewEntity();
        jDReactXViewEntity.count = i2;
        jDReactXViewEntity.url = str;
        jDReactXViewEntity.isIntercepted = z;
        jDReactXViewEntity.autoRemoveDelayTime = j2;
        jDReactXViewEntity.needCloseButton = true;
        jDReactXViewEntity.needAutoClose = z2;
        if (this.mXViewShowTime >= i2) {
            return;
        }
        View findViewById = findViewById(16908290);
        if (findViewById instanceof ViewGroup) {
            if (this.mXView == null || !this.mIsShown) {
                XView createXView = XViewHelper.createXView(this, (ViewGroup) findViewById, getClass().getSimpleName(), jDReactXViewEntity, new XViewCallBackAdapter() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeCommonActivity.2
                    boolean success = false;
                    boolean error = false;

                    @Override // com.jingdong.common.XView.XViewCallBackAdapter, com.jingdong.common.XView.XViewCallBack
                    public void onError(int i3) {
                        super.onError(i3);
                        JDCallback jDCallback3 = jDCallback2;
                        if (jDCallback3 != null && !this.error) {
                            jDCallback3.invoke("onError");
                            this.error = true;
                        }
                        JDReactNativeCommonActivity.this.mIsShown = false;
                    }

                    @Override // com.jingdong.common.XView.XViewCallBackAdapter, com.jingdong.common.XView.XViewCallBack
                    public void onXViewDisplayed() {
                        super.onXViewDisplayed();
                        JDCallback jDCallback3 = jDCallback;
                        if (jDCallback3 != null && !this.success) {
                            jDCallback3.invoke("onSuccess");
                            this.success = true;
                        }
                        JDReactNativeCommonActivity.access$008(JDReactNativeCommonActivity.this);
                    }

                    @Override // com.jingdong.common.XView.XViewCallBackAdapter, com.jingdong.common.XView.XViewCallBack
                    public void onXVivewClosed() {
                        super.onXVivewClosed();
                        JDReactNativeCommonActivity.this.mIsShown = false;
                        JDReactNativeCommonActivity.this.sendEvent("JDReactNativeXViewCloseEvent", null);
                    }
                });
                this.mXView = createXView;
                if (createXView != null) {
                    createXView.autoShowXView();
                    this.mIsShown = true;
                }
                IXView iXView = this.mXView;
                if (iXView != null) {
                    iXView.onResume();
                }
            }
        }
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity
    public JDReactPackage getReactPackage() {
        return new JDReactExtendPackage();
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity, com.jingdong.common.jdreactFramework.activities.OnXViewActionListener
    public void showXView(int i2, String str, boolean z, long j2, final JDCallback jDCallback, final JDCallback jDCallback2) {
        super.showXView(i2, str, z, j2, jDCallback, jDCallback2);
        JDReactXViewEntity jDReactXViewEntity = new JDReactXViewEntity();
        jDReactXViewEntity.count = i2;
        jDReactXViewEntity.url = str;
        jDReactXViewEntity.isIntercepted = z;
        jDReactXViewEntity.autoRemoveDelayTime = j2;
        jDReactXViewEntity.needCloseButton = true;
        if (this.mXViewShowTime >= i2) {
            return;
        }
        View findViewById = findViewById(16908290);
        if (findViewById instanceof ViewGroup) {
            if (this.mXView == null || !this.mIsShown) {
                XView createXView = XViewHelper.createXView(this, (ViewGroup) findViewById, getClass().getSimpleName(), jDReactXViewEntity, new XViewCallBackAdapter() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeCommonActivity.3
                    boolean success = false;
                    boolean error = false;

                    @Override // com.jingdong.common.XView.XViewCallBackAdapter, com.jingdong.common.XView.XViewCallBack
                    public void onError(int i3) {
                        super.onError(i3);
                        JDCallback jDCallback3 = jDCallback2;
                        if (jDCallback3 != null && !this.error) {
                            jDCallback3.invoke("onError");
                            this.error = true;
                        }
                        JDReactNativeCommonActivity.this.mIsShown = false;
                    }

                    @Override // com.jingdong.common.XView.XViewCallBackAdapter, com.jingdong.common.XView.XViewCallBack
                    public void onXViewDisplayed() {
                        super.onXViewDisplayed();
                        JDCallback jDCallback3 = jDCallback;
                        if (jDCallback3 != null && !this.success) {
                            jDCallback3.invoke("onSuccess");
                            this.success = true;
                        }
                        JDReactNativeCommonActivity.access$008(JDReactNativeCommonActivity.this);
                    }

                    @Override // com.jingdong.common.XView.XViewCallBackAdapter, com.jingdong.common.XView.XViewCallBack
                    public void onXVivewClosed() {
                        super.onXVivewClosed();
                        JDReactNativeCommonActivity.this.mIsShown = false;
                        JDReactNativeCommonActivity.this.sendEvent("JDReactNativeXViewCloseEvent", null);
                    }
                });
                this.mXView = createXView;
                if (createXView != null) {
                    createXView.autoShowXView();
                    this.mIsShown = true;
                }
                IXView iXView = this.mXView;
                if (iXView != null) {
                    iXView.onResume();
                }
            }
        }
    }
}
