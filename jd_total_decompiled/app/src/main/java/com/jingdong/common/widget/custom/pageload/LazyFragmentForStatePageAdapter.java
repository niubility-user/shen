package com.jingdong.common.widget.custom.pageload;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jingdong.cleanmvp.ui.BaseFragment;
import com.jingdong.sdk.log.Log;

/* loaded from: classes12.dex */
public abstract class LazyFragmentForStatePageAdapter extends BaseFragment {
    private boolean mCreated;
    private boolean mIsVisibleToUser;
    private boolean mLoaded;

    private void reShow() {
        if (this.mIsVisibleToUser && this.mCreated) {
            realResume();
        }
    }

    public abstract void lazyLoad();

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mCreated = true;
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mCreated = true;
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mCreated = false;
        this.mIsVisibleToUser = false;
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mCreated = false;
        this.mIsVisibleToUser = false;
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        return false;
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        if (this.mIsVisibleToUser && this.mCreated) {
            realStop();
        }
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        reShow();
    }

    public void realResume() {
        Log.d("888", "realResume: " + toString());
        if (this.mLoaded) {
            return;
        }
        this.mLoaded = true;
        lazyLoad();
        Log.d("888", "lazyLoad: " + toString());
    }

    public void realStop() {
        Log.d("888", "realStop: " + toString());
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.mIsVisibleToUser = z;
        reShow();
        if (this.mIsVisibleToUser || !this.mCreated) {
            return;
        }
        realStop();
    }
}
