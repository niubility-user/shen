package com.jingdong.common.jdreactFramework.activities;

import android.view.View;
import android.view.ViewGroup;
import com.jingdong.common.XView.IXView;
import com.jingdong.common.XView.XView;
import com.jingdong.common.XView.XViewCallBackAdapter;
import com.jingdong.common.XView.XViewHelper;
import com.jingdong.common.jdreactFramework.JDCallback;

/* loaded from: classes5.dex */
public abstract class JDReactAbstractNativeBaseActivity extends JDReactNativeBaseActivity {
    private boolean mIsShown = false;
    private IXView mXView;
    private int mXViewShowTime;

    static /* synthetic */ int access$008(JDReactAbstractNativeBaseActivity jDReactAbstractNativeBaseActivity) {
        int i2 = jDReactAbstractNativeBaseActivity.mXViewShowTime;
        jDReactAbstractNativeBaseActivity.mXViewShowTime = i2 + 1;
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

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity, com.jingdong.common.jdreactFramework.activities.OnXViewActionListener
    public void showXView(int i2, String str, boolean z, long j2, boolean z2, final JDCallback jDCallback, final JDCallback jDCallback2) {
        JDReactXViewEntity jDReactXViewEntity = new JDReactXViewEntity();
        jDReactXViewEntity.count = i2;
        jDReactXViewEntity.url = str;
        jDReactXViewEntity.isIntercepted = z;
        jDReactXViewEntity.autoRemoveDelayTime = j2;
        jDReactXViewEntity.needAutoClose = z2;
        if (this.mXViewShowTime >= i2) {
            return;
        }
        View findViewById = findViewById(16908290);
        if (findViewById instanceof ViewGroup) {
            if (this.mXView == null || !this.mIsShown) {
                XView createXView = XViewHelper.createXView(this, (ViewGroup) findViewById, getClass().getSimpleName(), jDReactXViewEntity, new XViewCallBackAdapter() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactAbstractNativeBaseActivity.1
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
                        JDReactAbstractNativeBaseActivity.this.mIsShown = false;
                    }

                    @Override // com.jingdong.common.XView.XViewCallBackAdapter, com.jingdong.common.XView.XViewCallBack
                    public void onXViewDisplayed() {
                        super.onXViewDisplayed();
                        JDCallback jDCallback3 = jDCallback;
                        if (jDCallback3 != null && !this.success) {
                            jDCallback3.invoke("onSuccess");
                            this.success = true;
                        }
                        JDReactAbstractNativeBaseActivity.access$008(JDReactAbstractNativeBaseActivity.this);
                    }

                    @Override // com.jingdong.common.XView.XViewCallBackAdapter, com.jingdong.common.XView.XViewCallBack
                    public void onXVivewClosed() {
                        super.onXVivewClosed();
                        JDReactAbstractNativeBaseActivity.this.mIsShown = false;
                        JDReactAbstractNativeBaseActivity.this.sendEvent("JDReactNativeXViewCloseEvent", null);
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

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity, com.jingdong.common.jdreactFramework.activities.OnXViewActionListener
    public void showXView(int i2, String str, boolean z, long j2, final JDCallback jDCallback, final JDCallback jDCallback2) {
        JDReactXViewEntity jDReactXViewEntity = new JDReactXViewEntity();
        jDReactXViewEntity.count = i2;
        jDReactXViewEntity.url = str;
        jDReactXViewEntity.isIntercepted = z;
        jDReactXViewEntity.autoRemoveDelayTime = j2;
        if (this.mXViewShowTime >= i2) {
            return;
        }
        View findViewById = findViewById(16908290);
        if (findViewById instanceof ViewGroup) {
            if (this.mXView == null || !this.mIsShown) {
                XView createXView = XViewHelper.createXView(this, (ViewGroup) findViewById, getClass().getSimpleName(), jDReactXViewEntity, new XViewCallBackAdapter() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactAbstractNativeBaseActivity.2
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
                        JDReactAbstractNativeBaseActivity.this.mIsShown = false;
                    }

                    @Override // com.jingdong.common.XView.XViewCallBackAdapter, com.jingdong.common.XView.XViewCallBack
                    public void onXViewDisplayed() {
                        super.onXViewDisplayed();
                        JDCallback jDCallback3 = jDCallback;
                        if (jDCallback3 != null && !this.success) {
                            jDCallback3.invoke("onSuccess");
                            this.success = true;
                        }
                        JDReactAbstractNativeBaseActivity.access$008(JDReactAbstractNativeBaseActivity.this);
                    }

                    @Override // com.jingdong.common.XView.XViewCallBackAdapter, com.jingdong.common.XView.XViewCallBack
                    public void onXVivewClosed() {
                        super.onXVivewClosed();
                        JDReactAbstractNativeBaseActivity.this.mIsShown = false;
                        JDReactAbstractNativeBaseActivity.this.sendEvent("JDReactNativeXViewCloseEvent", null);
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
