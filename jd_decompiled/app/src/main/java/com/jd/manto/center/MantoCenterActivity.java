package com.jd.manto.center;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentTransaction;
import com.jd.dynamic.DYConstants;
import com.jingdong.manto.ui.MantoBaseActivity;
import com.jingdong.manto.utils.MantoTrack;
import java.util.HashMap;

/* loaded from: classes17.dex */
public class MantoCenterActivity extends MantoBaseActivity {

    /* renamed from: g  reason: collision with root package name */
    private MantoCenterFragment f6250g;

    /* renamed from: h  reason: collision with root package name */
    private ImageView f6251h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MantoTrack.sendCommonDataWithExt(MantoCenterActivity.this, "\u4e2d\u5fc3\u9875\u8fd4\u56de", "Applets_Center_Back", "", "", "", "", "", null);
            MantoCenterActivity.this.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MantoCenterActivity.this.startActivity(new Intent(MantoCenterActivity.this, MantoCenterAboutActivity.class));
        }
    }

    private final void initStatusBar() {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 21) {
            getWindow().setStatusBarColor(Color.parseColor("#FCB4AE"));
        } else if (i2 >= 19) {
            getWindow().addFlags(67108864);
        }
    }

    private final void initView() {
        x(0);
        w();
        u();
    }

    private final void v(FragmentTransaction fragmentTransaction) {
        MantoCenterFragment mantoCenterFragment = this.f6250g;
        if (mantoCenterFragment != null) {
            fragmentTransaction.hide(mantoCenterFragment);
        }
    }

    private final void w() {
        findViewById(R.id.actionbar_back).setVisibility(8);
        ((TextView) findViewById(com.jingdong.manto.R.id.manto_actionbar_title)).setText(R.string.manto_center_name);
        findViewById(com.jingdong.manto.R.id.manto_actionbar_home).setOnClickListener(new a());
        findViewById(R.id.manto_actionbar_option).setOnClickListener(new b());
    }

    private final void x(int i2) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        v(beginTransaction);
        if (i2 == 0) {
            MantoCenterFragment mantoCenterFragment = (MantoCenterFragment) getSupportFragmentManager().findFragmentByTag(DYConstants.DY_CENTER);
            this.f6250g = mantoCenterFragment;
            if (mantoCenterFragment == null) {
                MantoCenterFragment s = MantoCenterFragment.s();
                this.f6250g = s;
                beginTransaction.add(R.id.container_fg, s);
            } else {
                beginTransaction.show(mantoCenterFragment);
            }
        }
        beginTransaction.commitAllowingStateLoss();
    }

    @Override // com.jingdong.manto.ui.MantoBaseActivity
    public int getLayoutId() {
        return R.layout.manto_center_activity;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.manto.ui.MantoBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initStatusBar();
        initView();
        HashMap hashMap = new HashMap();
        hashMap.put("vapp", "0");
        hashMap.put("vapp_type", "0");
        MantoTrack.sendPagePv(this, "\u5c0f\u7a0b\u5e8f\u4e2d\u5fc3\u9875", "", "Applets_Center", hashMap);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.manto.ui.MantoBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    public final void u() {
        ImageView imageView = (ImageView) findViewById(R.id.manto_center_top_bg);
        this.f6251h = imageView;
        com.jd.manto.center.k.d.e(imageView, "2830");
    }
}
