package com.jingdong.sdk.platform.lib.ui;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.jingdong.sdk.lib.compact.CompactBaseFragment;
import com.jingdong.sdk.platform.lib.openapi.OpenApiHelper;
import com.jingdong.sdk.platform.lib.openapi.ui.AbsFragmentCompact;

/* loaded from: classes10.dex */
public class CompactFragment extends CompactBaseFragment {
    private AbsFragmentCompact fragmentCompact;

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        AbsFragmentCompact fragmentCompact = OpenApiHelper.getFragmentCompact(this);
        this.fragmentCompact = fragmentCompact;
        if (fragmentCompact != null) {
            fragmentCompact.onCreate(bundle);
        }
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        AbsFragmentCompact absFragmentCompact = this.fragmentCompact;
        if (absFragmentCompact != null) {
            absFragmentCompact.onDestroy();
        }
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        AbsFragmentCompact absFragmentCompact = this.fragmentCompact;
        if (absFragmentCompact != null) {
            absFragmentCompact.onPause();
        }
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        AbsFragmentCompact absFragmentCompact = this.fragmentCompact;
        if (absFragmentCompact != null) {
            absFragmentCompact.onResume();
        }
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        AbsFragmentCompact absFragmentCompact = this.fragmentCompact;
        if (absFragmentCompact != null) {
            absFragmentCompact.onStart();
        }
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        AbsFragmentCompact absFragmentCompact = this.fragmentCompact;
        if (absFragmentCompact != null) {
            absFragmentCompact.onStop();
        }
    }
}
