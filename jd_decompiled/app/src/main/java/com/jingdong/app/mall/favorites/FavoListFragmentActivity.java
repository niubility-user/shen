package com.jingdong.app.mall.favorites;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.jingdong.app.mall.R;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.cleanmvp.ui.BaseFragment;
import com.jingdong.common.deeplinkhelper.DeepLinkFavouritesHelper;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.auraSetting.AuraFragmentHelper;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import de.greenrobot.event.EventBus;

/* loaded from: classes3.dex */
public class FavoListFragmentActivity extends FavoBaseActivity {

    /* renamed from: i  reason: collision with root package name */
    public int f8411i = 0;

    /* renamed from: j  reason: collision with root package name */
    private Fragment f8412j;

    /* renamed from: k  reason: collision with root package name */
    private Fragment f8413k;

    /* renamed from: l  reason: collision with root package name */
    private Fragment f8414l;

    /* renamed from: m  reason: collision with root package name */
    private Fragment f8415m;

    /* renamed from: n  reason: collision with root package name */
    private boolean f8416n;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.favorites.FavoBaseActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        Fragment fragment;
        super.onActivityResult(i2, i3, intent);
        if (this.f8408g || this.f8411i != 1 || (fragment = this.f8414l) == null) {
            return;
        }
        fragment.onActivityResult(i2, i3, intent);
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void onAttachFragment(Fragment fragment) {
        if (Log.D) {
            Log.d("FavoListFragmentActivity", "FavoListFragment onAttachFragment.. -->> " + fragment.getClass());
        }
        super.onAttachFragment(fragment);
        try {
            String simpleName = fragment.getClass().getSimpleName();
            if ("FavoProductFragment".equals(simpleName) || "FavoShopFragment".equals(simpleName)) {
                this.f8412j = fragment;
            }
        } catch (Exception e2) {
            if (Log.D) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.favorites.FavoBaseActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        if (Log.D) {
            Log.d("FavoListFragmentActivity", "FavoListFragment onCreate.. -->> ");
        }
        requestWindowFeature(1);
        getWindow().requestFeature(12);
        getWindow().requestFeature(13);
        super.onCreate(bundle);
        EventBus.getDefault().register(this);
        setUseBasePV(false);
        setContentView(R.layout.favo_jshop_list_fragment_activity);
        if (getIntent() != null) {
            try {
                Intent intent = getIntent();
                int intExtra = intent.getIntExtra(DeepLinkFavouritesHelper.CURRENT_TAB, -1);
                this.f8411i = intExtra;
                if (intExtra < 0) {
                    if (!TextUtils.isEmpty(intent.getStringExtra(DeepLinkFavouritesHelper.CURRENT_TAB))) {
                        this.f8411i = Integer.valueOf(intent.getStringExtra(DeepLinkFavouritesHelper.CURRENT_TAB)).intValue();
                    } else {
                        this.f8411i = 0;
                    }
                }
            } catch (Exception unused) {
                this.f8411i = 0;
            }
        } else {
            this.f8411i = 0;
        }
        if (getIntent() != null && ((SourceEntity) getIntent().getSerializableExtra("source")) == null) {
            new SourceEntity(SourceEntity.SOURCE_TYPE_MYJD_FAVORITE, "");
        }
        Bundle extras = getIntent() != null ? getIntent().getExtras() : null;
        if (this.f8411i == 0) {
            Fragment newFragment = AuraFragmentHelper.getInstance().newFragment(this, "com.jd.lib.favourites.view.fragment.FavoProductFragment");
            this.f8413k = newFragment;
            if (newFragment != null) {
                newFragment.setArguments(extras);
                getSupportFragmentManager().beginTransaction().add(R.id.favo_fragment, this.f8413k, "FavoListFragment").commitAllowingStateLoss();
                return;
            }
            return;
        }
        Fragment newFragment2 = AuraFragmentHelper.getInstance().newFragment(this, "com.jd.lib.shopattention.favoshopmvp.FavoShopFragment");
        this.f8414l = newFragment2;
        if (newFragment2 != null) {
            newFragment2.setArguments(extras);
            getSupportFragmentManager().beginTransaction().add(R.id.favo_fragment, this.f8414l, "ShopListFragment").commitAllowingStateLoss();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        this.f8412j = null;
        this.f8413k = null;
        this.f8414l = null;
        this.f8415m = null;
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        if (Log.D) {
            Log.d("FavoListFragmentActivity", "onEventMainThread\u6536\u5230\u4e86\u6d88\u606f\uff1a" + baseEvent.getMessage());
        }
        String type = baseEvent.getType();
        type.hashCode();
        char c2 = '\uffff';
        switch (type.hashCode()) {
            case -2040206877:
                if (type.equals("favo_fragment_switch_show_search")) {
                    c2 = 0;
                    break;
                }
                break;
            case 1380267613:
                if (type.equals("favo_shop_switch_fragment")) {
                    c2 = 1;
                    break;
                }
                break;
            case 1630708942:
                if (type.equals("is_content_show_back")) {
                    c2 = 2;
                    break;
                }
                break;
            case 1746190334:
                if (type.equals("favo_fragment_switch_hide_search")) {
                    c2 = 3;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                v(baseEvent.getBundle());
                return;
            case 1:
                w(baseEvent.getMessage(), baseEvent.getBundle());
                return;
            case 2:
                Fragment fragment = this.f8415m;
                if (fragment != null && fragment.isVisible()) {
                    getSupportFragmentManager().beginTransaction().remove(this.f8415m).commitAllowingStateLoss();
                }
                if (this.f8415m != null) {
                    EventBus.getDefault().post(new BaseEvent("notify_data_set"));
                    return;
                }
                return;
            case 3:
                u();
                return;
            default:
                return;
        }
    }

    @Override // com.jingdong.common.BaseActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        Fragment fragment;
        if (i2 != 4 || (fragment = this.f8412j) == null || !(fragment instanceof BaseFragment) || ((BaseFragment) fragment).onKeyDown(i2, keyEvent)) {
            return super.onKeyDown(i2, keyEvent);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.favorites.FavoBaseActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        Fragment fragment = this.f8412j;
        if (fragment != null && fragment.equals(this.f8413k)) {
            JDMtaUtils.sendPagePv(this, this.f8412j, "", RecommendMtaUtils.MyFollow_PageId, "");
            return;
        }
        Fragment fragment2 = this.f8412j;
        if (fragment2 != null) {
            fragment2.equals(this.f8414l);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity
    public void onResumeFragments() {
        super.onResumeFragments();
        this.f8416n = false;
        EventBus.getDefault().post(new BaseEvent("is_content_show"));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
    }

    public void u() {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        this.f8412j = this.f8413k;
        this.f8416n = false;
        Fragment fragment = this.f8415m;
        if (fragment != null) {
            beginTransaction.remove(fragment).commitAllowingStateLoss();
        }
    }

    public void v(Bundle bundle) {
        if (this.f8416n) {
            return;
        }
        this.f8416n = true;
        Fragment newFragment = AuraFragmentHelper.getInstance().newFragment(this, "com.jd.lib.favourites.view.fragment.SearchFavoListFragment");
        this.f8415m = newFragment;
        newFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.favo_fragment, this.f8415m).commitAllowingStateLoss();
        this.f8412j = this.f8415m;
    }

    public void w(String str, Bundle bundle) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        if ("FAVOSHOP".equals(str)) {
            if (this.f8413k == null) {
                return;
            }
            Fragment fragment = this.f8414l;
            if (fragment == null) {
                Fragment newFragment = AuraFragmentHelper.getInstance().newFragment(this, "com.jd.lib.shopattention.favoshopmvp.FavoShopFragment");
                this.f8414l = newFragment;
                newFragment.setArguments(bundle);
            } else {
                fragment.setArguments(bundle);
            }
            if (!this.f8414l.isAdded()) {
                beginTransaction.hide(this.f8413k).add(R.id.favo_fragment, this.f8414l).commitAllowingStateLoss();
            } else {
                beginTransaction.hide(this.f8413k).show(this.f8414l).commitAllowingStateLoss();
            }
            this.f8412j = this.f8414l;
            this.f8411i = 1;
        } else if (!"FAVOPRODUCT".equals(str) || this.f8414l == null) {
        } else {
            Fragment fragment2 = this.f8413k;
            if (fragment2 == null) {
                Fragment newFragment2 = AuraFragmentHelper.getInstance().newFragment(this, "com.jd.lib.favourites.view.fragment.FavoProductFragment");
                this.f8413k = newFragment2;
                newFragment2.setArguments(bundle);
            } else {
                fragment2.setArguments(bundle);
            }
            if (!this.f8413k.isAdded()) {
                beginTransaction.hide(this.f8414l).add(R.id.favo_fragment, this.f8413k).commitAllowingStateLoss();
            } else {
                beginTransaction.hide(this.f8414l).show(this.f8413k).commitAllowingStateLoss();
            }
            Fragment fragment3 = this.f8413k;
            this.f8412j = fragment3;
            this.f8411i = 0;
            JDMtaUtils.sendPagePv(this, fragment3, "", RecommendMtaUtils.MyFollow_PageId, "");
        }
    }
}
