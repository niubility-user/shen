package com.jingdong.app.mall.bundle.PageComponents.fragment;

import androidx.fragment.app.Fragment;

/* loaded from: classes19.dex */
public abstract class LazyFragmentForShowHide extends Fragment {
    private boolean loaded;
    private boolean resumed;

    private void resume() {
        if (isHidden()) {
            return;
        }
        this.resumed = true;
        realResume();
        if (this.loaded) {
            return;
        }
        this.loaded = true;
        autoLazyLoaded();
    }

    private void stop() {
        if (this.resumed) {
            this.resumed = false;
            realStop();
        }
    }

    protected void autoLazyLoaded() {
    }

    protected boolean isLoaded() {
        return this.loaded;
    }

    protected void manualLoad() {
        if (this.loaded) {
            return;
        }
        this.loaded = true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.loaded = false;
        this.resumed = false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        stop();
        resume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        resume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        stop();
    }

    protected void realResume() {
    }

    protected void realStop() {
    }
}
