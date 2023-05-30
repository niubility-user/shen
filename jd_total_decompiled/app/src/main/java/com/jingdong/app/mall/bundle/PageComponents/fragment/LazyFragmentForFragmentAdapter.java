package com.jingdong.app.mall.bundle.PageComponents.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/* loaded from: classes19.dex */
public abstract class LazyFragmentForFragmentAdapter extends Fragment {
    private boolean mCreated;
    private boolean mLoaded;

    private void reShow() {
        if (getUserVisibleHint() && this.mCreated) {
            realResume();
            if (this.mLoaded) {
                return;
            }
            this.mLoaded = true;
            lazyLoad();
        }
    }

    private void reStop() {
        if (this.mCreated) {
            realStop();
        }
    }

    public abstract void lazyLoad();

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mCreated = true;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        this.mCreated = true;
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mCreated = false;
        this.mLoaded = false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        reShow();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        if (getUserVisibleHint()) {
            reStop();
        }
    }

    public void realResume() {
    }

    public void realStop() {
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        reShow();
        if (z) {
            return;
        }
        reStop();
    }
}
