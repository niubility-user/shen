package com.jd.manto.album;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.R;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;

/* loaded from: classes17.dex */
public class AlbumProxyActivity extends BaseActivity {
    public static void u(Context context, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        Intent intent = new Intent(context, AlbumProxyActivity.class);
        intent.putExtra("manto_extra_album_bundle", bundle);
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        context.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        UnStatusBarTintUtil.setStatusBar4Base(this, 1);
        UnStatusBarTintUtil.setStatusBarLightMode(this);
        int i2 = R.anim.nothing;
        overridePendingTransition(i2, i2);
        if (getIntent() != null && getIntent().getExtras() != null) {
            DeepLinkCommonHelper.startActivityDirect(this, DeepLinkCommonHelper.HOST_IMAGE_ACTIVITY, getIntent().getBundleExtra("manto_extra_album_bundle"));
            finish();
            return;
        }
        finish();
    }
}
