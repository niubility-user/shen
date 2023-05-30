package com.jd.manto.center;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.jingdong.manto.ui.MantoBaseActivity;
import com.jingdong.manto.utils.MantoTrack;
import java.util.HashMap;

/* loaded from: classes17.dex */
public final class MantoListActivity extends MantoBaseActivity {

    /* renamed from: g */
    private boolean f6362g;

    private final void initStatusBar() {
        b.a(this, -1, true);
    }

    public static void u(String str, String str2, String str3, HashMap<String, String> hashMap) {
        hashMap.put("vapp", "1");
        hashMap.put("vapp_type", "0");
        MantoTrack.sendCommonDataWithExt(com.jingdong.a.g(), str, str2, str3, "\u5c0f\u7a0b\u5e8f\u4e2d\u5fc3\u9875", "Applets_Center", "", "", hashMap);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            if (this.f6362g) {
                MantoTrack.sendCommonDataWithExt(this, "\u63a8\u8350\u5173\u952e\u5b57", "Applets_Center_Search_Back", "", "", "", "", "", null);
            }
            getSupportFragmentManager().popBackStack();
            return;
        }
        super.onBackPressed();
    }

    @Override // com.jingdong.manto.ui.MantoBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initStatusBar();
        setContentView(R.layout.manto_center_list_activity);
        if (getIntent() != null && "search".equals(getIntent().getStringExtra("which"))) {
            this.f6362g = true;
            Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag("search");
            if (findFragmentByTag == null) {
                findFragmentByTag = MantoPkgSearchFragment.r("");
            }
            v(findFragmentByTag, "search");
            return;
        }
        Fragment findFragmentByTag2 = getSupportFragmentManager().findFragmentByTag("search");
        if (findFragmentByTag2 == null) {
            findFragmentByTag2 = MantoPkgRecentFragment.x();
        }
        v(findFragmentByTag2, "recent");
    }

    public void v(Fragment fragment, String str) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        if (fragment instanceof MantoPkgSearchFragment) {
            beginTransaction.setCustomAnimations(R.anim.manto_center_push_left_in, R.anim.manto_center_push_left_out, R.anim.manto_center_push_right_in, R.anim.manto_center_push_right_out);
            int i2 = 0;
            try {
                i2 = getSupportFragmentManager().getFragments().size();
            } catch (Exception unused) {
            }
            if (i2 > 0) {
                beginTransaction.addToBackStack(str);
            }
        }
        beginTransaction.add(R.id.container_fragment, fragment, str);
        beginTransaction.commitAllowingStateLoss();
    }
}
