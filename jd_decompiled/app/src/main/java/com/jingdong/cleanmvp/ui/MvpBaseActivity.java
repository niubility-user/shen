package com.jingdong.cleanmvp.ui;

import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.cleanmvp.presenter.BaseNavigator;
import com.jingdong.cleanmvp.presenter.BasePresenter;
import com.jingdong.cleanmvp.presenter.IBaseUI;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.lib.ui.CompactActivity;
import de.greenrobot.event.EventBus;

/* loaded from: classes4.dex */
public abstract class MvpBaseActivity<P extends BasePresenter, N extends BaseNavigator> extends CompactActivity implements IBaseUI {
    protected N navigator;
    private final String TAG = MvpBaseActivity.class.getSimpleName();
    protected P presenter = null;

    protected abstract int createLayout();

    protected abstract N createNavigator();

    protected abstract P createPresenter();

    protected N getNavigator() {
        if (this.navigator == null) {
            this.navigator = createNavigator();
        }
        N n2 = this.navigator;
        if (n2 != null) {
            return n2;
        }
        throw new NullPointerException("navigator is null ! you should create a navigator.");
    }

    protected P getPresenter() {
        if (this.presenter == null) {
            this.presenter = createPresenter();
        }
        P p = this.presenter;
        if (p != null) {
            return p;
        }
        throw new NullPointerException("presenter is null ! you should create a presenter.");
    }

    protected void initDependency() {
        if (this.presenter == null) {
            this.presenter = createPresenter();
        }
        if (this.navigator == null) {
            this.navigator = createNavigator();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.sdk.platform.lib.ui.CompactActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initDependency();
        if (createLayout() == 0) {
            OKLog.d(this.TAG, "createLayout() returned 0, If you don't want to use createLayout(), but implement your own view,you have to override setContentView();");
        } else {
            setContentView(createLayout());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.sdk.platform.lib.ui.CompactActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        P p = this.presenter;
        if (p != null) {
            p.detachUI(this);
        }
    }

    public void onEvent(BaseEvent baseEvent) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.sdk.platform.lib.ui.CompactActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        P p = this.presenter;
        if (p != null) {
            p.attachUI(this);
        }
        if (EventBus.getDefault().isRegistered(this)) {
            return;
        }
        EventBus.getDefault().register(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.sdk.platform.lib.ui.CompactActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        P p = this.presenter;
        if (p != null) {
            p.suspend();
        }
        EventBus.getDefault().unregister(this);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks2
    public void onTrimMemory(int i2) {
        super.onTrimMemory(i2);
        P p = this.presenter;
        if (p != null) {
            p.onTrimMemory(i2);
        }
    }

    @Override // com.jingdong.common.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity
    @Deprecated
    public void setContentView(int i2) {
        super.setContentView(i2);
    }

    protected void setPresenter(@NonNull P p) {
        this.presenter = p;
    }

    @Override // com.jingdong.cleanmvp.presenter.IBaseUI
    public void showToast(String str) {
        Toast.makeText(this, str, 0).show();
    }
}
