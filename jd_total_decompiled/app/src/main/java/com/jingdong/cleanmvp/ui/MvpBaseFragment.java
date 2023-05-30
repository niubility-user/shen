package com.jingdong.cleanmvp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.cleanmvp.presenter.BaseNavigator;
import com.jingdong.cleanmvp.presenter.BasePresenter;
import com.jingdong.cleanmvp.presenter.IBaseUI;
import com.jingdong.sdk.platform.lib.ui.CompactFragment;
import de.greenrobot.event.EventBus;

/* loaded from: classes4.dex */
public abstract class MvpBaseFragment<P extends BasePresenter, N extends BaseNavigator> extends CompactFragment implements IBaseUI {
    private N navigator;
    private final String TAG = MvpBaseFragment.class.getSimpleName();
    protected P presenter = null;

    private void initDependency() {
        if (this.presenter == null) {
            this.presenter = createPresenter();
        }
        if (this.navigator == null) {
            this.navigator = createNavigator();
        }
    }

    protected abstract N createNavigator();

    @Nullable
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

    /* JADX INFO: Access modifiers changed from: protected */
    public P getPresenter() {
        if (this.presenter == null) {
            this.presenter = createPresenter();
        }
        P p = this.presenter;
        if (p != null) {
            return p;
        }
        throw new NullPointerException("presenter is null ! you should create a presenter.");
    }

    @Override // com.jingdong.cleanmvp.presenter.IBaseUI
    public boolean isRetain() {
        return false;
    }

    @Override // com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initDependency();
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        P p = this.presenter;
        if (p != null) {
            p.detachUI(this);
        }
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onEvent(BaseEvent baseEvent) {
    }

    @Override // com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
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

    @Override // com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        P p = this.presenter;
        if (p != null) {
            p.suspend();
        }
        EventBus.getDefault().unregister(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setPresenter(@NonNull P p) {
        this.presenter = p;
    }

    @Override // com.jingdong.cleanmvp.presenter.IBaseUI
    public void showToast(String str) {
        Toast.makeText(getActivity(), str, 0).show();
    }
}
