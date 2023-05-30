package com.jingdong.common.search.isv;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.sdk.aac.ui.JDLifecycleBaseFragment;

/* loaded from: classes6.dex */
public abstract class ISVTabBaseFragment extends JDLifecycleBaseFragment {
    private ISVTabInterface isvInterface;

    public ISVTabBaseFragment(ISVTabInterface iSVTabInterface) {
        this.isvInterface = iSVTabInterface;
    }

    public ISVTabInterface getIsvInterface() {
        return this.isvInterface;
    }

    public void handleTabFragmentIfNeed(int i2, JDJSONObject jDJSONObject) {
    }

    @Override // com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    public void setIsvInterface(ISVTabInterface iSVTabInterface) {
        this.isvInterface = iSVTabInterface;
    }

    public abstract void viewDidScrollIfNeeded(float f2);

    public ISVTabBaseFragment() {
    }
}
