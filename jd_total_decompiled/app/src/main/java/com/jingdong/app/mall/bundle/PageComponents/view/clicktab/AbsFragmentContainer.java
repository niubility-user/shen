package com.jingdong.app.mall.bundle.PageComponents.view.clicktab;

import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.util.List;

/* loaded from: classes19.dex */
public abstract class AbsFragmentContainer implements IFragmentContainer {
    protected final String TAG = getClass().getSimpleName();
    protected final FragmentManager fm;
    protected ITab selectedTab;

    public AbsFragmentContainer(FragmentManager fragmentManager) {
        this.fm = fragmentManager;
    }

    public abstract void addFragment(int i2);

    protected String getTag(int i2) {
        return this.TAG + i2;
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.view.clicktab.IFragmentContainer
    public void reset() {
        this.selectedTab = null;
        FragmentManager fragmentManager = this.fm;
        if (fragmentManager == null) {
            return;
        }
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null) {
            FragmentTransaction beginTransaction = this.fm.beginTransaction();
            for (int i2 = 0; i2 < fragments.size(); i2++) {
                Fragment fragment = fragments.get(i2);
                if (fragment != null && !TextUtils.isEmpty(fragment.getTag()) && fragment.getTag().startsWith(this.TAG)) {
                    beginTransaction.remove(fragment);
                }
            }
            beginTransaction.commitNowAllowingStateLoss();
        }
        this.fm.executePendingTransactions();
    }

    protected void selectedFragment(int i2) {
        FragmentManager fragmentManager = this.fm;
        if (fragmentManager == null) {
            return;
        }
        Fragment findFragmentByTag = fragmentManager.findFragmentByTag(this.TAG + i2);
        if (findFragmentByTag == null) {
            addFragment(i2);
        } else {
            this.fm.beginTransaction().show(findFragmentByTag).commitNowAllowingStateLoss();
        }
        if (this.selectedTab != null) {
            Fragment findFragmentByTag2 = this.fm.findFragmentByTag(this.TAG + this.selectedTab.getPosition());
            if (findFragmentByTag2 != null) {
                this.fm.beginTransaction().hide(findFragmentByTag2).commitNowAllowingStateLoss();
            }
        }
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.view.clicktab.IFragmentContainer
    public void selectedTab(ITab iTab) {
        ITab iTab2 = this.selectedTab;
        if (iTab2 != null) {
            iTab2.unSelect();
        }
        iTab.selected();
        selectedFragment(iTab.getPosition());
        this.selectedTab = iTab;
    }
}
