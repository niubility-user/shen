package com.jingdong.common.XView2.strategy;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.XView2.XView2;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.web.ui.CommonMFragment;
import com.jingdong.common.web.ui.FollowMFragment;
import java.lang.ref.SoftReference;

/* loaded from: classes5.dex */
public class WebPopStrategy extends BasePopStrategy {
    protected final String FRAGMENT_TAG;
    protected FragmentManager mFragmentManager;

    public WebPopStrategy(XViewConfigEntity.TargetsEntity targetsEntity, SoftReference softReference, XView2 xView2) {
        super(targetsEntity, softReference, xView2);
        this.FRAGMENT_TAG = "TAG_CommonMFragment";
        if (softReference == null || softReference.get() == null || !(softReference.get() instanceof BaseActivity)) {
            return;
        }
        this.mFragmentManager = ((BaseActivity) softReference.get()).getSupportFragmentManager();
    }

    private CommonMFragment getFragment() {
        Fragment findFragmentByTag = this.mFragmentManager.findFragmentByTag("TAG_CommonMFragment");
        if (findFragmentByTag != null && (findFragmentByTag instanceof CommonMFragment)) {
            return (CommonMFragment) findFragmentByTag;
        }
        CommonMFragment newFragment = newFragment();
        this.activity.get().getIntent().putExtra("canUseDarkMode", true);
        newFragment.setArguments(this.activity.get().getIntent().getExtras());
        return newFragment;
    }

    @Override // com.jingdong.common.XView2.strategy.BasePopStrategy
    boolean checkIsCanPop() {
        return true;
    }

    protected CommonMFragment newFragment() {
        if (this.activity.get().getIntent() != null && JumpUtil.VALUE_DES_GUANZHU.equals(this.activity.get().getIntent().getStringExtra("des"))) {
            return new FollowMFragment();
        }
        return new CommonMFragment();
    }
}
