package com.jd.manto.center;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.jd.dynamic.DYConstants;
import com.jd.manto.center.k.h;

/* loaded from: classes17.dex */
public class MantoNewCenterActivity extends BaseMantoCenterActivity {

    /* renamed from: h  reason: collision with root package name */
    private MantoCenterMineFragment f6363h;

    /* renamed from: i  reason: collision with root package name */
    private MantoDiscoveryFragment f6364i;

    /* renamed from: j  reason: collision with root package name */
    private ImageView f6365j;

    /* renamed from: k  reason: collision with root package name */
    private ImageView f6366k;

    /* renamed from: l  reason: collision with root package name */
    private TextView f6367l;

    /* renamed from: m  reason: collision with root package name */
    public String f6368m;

    /* renamed from: n  reason: collision with root package name */
    private View f6369n;
    private LinearLayout o;
    private LinearLayout p;
    private int q;
    private LinearLayout r;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MantoNewCenterActivity mantoNewCenterActivity = MantoNewCenterActivity.this;
            com.jd.manto.center.h.b.q(mantoNewCenterActivity, "J_Applets_Top_Search", mantoNewCenterActivity.q == 0 ? "J_Applets_My" : "J_Applets_Find");
            Intent intent = new Intent(MantoNewCenterActivity.this, MiniProgramSearchActivity.class);
            intent.putExtra("searchKeyword", MantoNewCenterActivity.this.f6367l != null ? MantoNewCenterActivity.this.f6367l.getText() : "");
            MantoNewCenterActivity.this.startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MantoNewCenterActivity mantoNewCenterActivity = MantoNewCenterActivity.this;
            com.jd.manto.center.h.b.q(mantoNewCenterActivity, "J_Applets_Top_Close", mantoNewCenterActivity.q == 0 ? "J_Applets_My" : "J_Applets_Find");
            MantoNewCenterActivity.this.sendBroadcast(new Intent("action.exit"));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MantoNewCenterActivity mantoNewCenterActivity = MantoNewCenterActivity.this;
            com.jd.manto.center.h.b.q(mantoNewCenterActivity, "J_Applets_Top_More", mantoNewCenterActivity.q == 0 ? "J_Applets_My" : "J_Applets_Find");
            Intent intent = new Intent(MantoNewCenterActivity.this, MantoCenterAboutActivity.class);
            intent.putExtra("tinyAppIntroduction", MantoNewCenterActivity.this.f6368m);
            MantoNewCenterActivity.this.startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class d implements View.OnClickListener {
        d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (MantoNewCenterActivity.this.q == 1) {
                return;
            }
            MantoNewCenterActivity.this.y(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class e implements View.OnClickListener {
        e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (MantoNewCenterActivity.this.q == 0) {
                return;
            }
            MantoNewCenterActivity.this.y(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class f implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f6375g;

        f(String str) {
            this.f6375g = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            MantoNewCenterActivity.this.f6367l.setText(this.f6375g);
        }
    }

    private final void initStatusBar() {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 21 && i2 >= 19) {
            getWindow().addFlags(67108864);
        }
    }

    private void initView() {
        setContentView(R.layout.manto_center_new_mian_activity);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.new_center_title);
        this.r = linearLayout;
        com.jd.manto.center.k.d.e(linearLayout, "2833");
        this.f6365j = (ImageView) findViewById(R.id.bottom_mine_tab_img);
        TextView textView = (TextView) findViewById(R.id.bottom_mine_tab_txt);
        this.f6366k = (ImageView) findViewById(R.id.bottom_find_tab_img);
        TextView textView2 = (TextView) findViewById(R.id.bottom_find_tab_txt);
        this.o = (LinearLayout) findViewById(R.id.bottom_mine_container);
        this.p = (LinearLayout) findViewById(R.id.bottom_find_container);
        initStatusBar();
        this.f6367l = (TextView) findViewById(R.id.common_title__name);
        View findViewById = findViewById(R.id.common_title_search_container);
        this.f6369n = findViewById;
        findViewById.setOnClickListener(new a());
        y(0);
        x();
    }

    private void w(FragmentTransaction fragmentTransaction) {
        MantoCenterMineFragment mantoCenterMineFragment = this.f6363h;
        if (mantoCenterMineFragment != null) {
            fragmentTransaction.hide(mantoCenterMineFragment);
        }
        MantoDiscoveryFragment mantoDiscoveryFragment = this.f6364i;
        if (mantoDiscoveryFragment != null) {
            fragmentTransaction.hide(mantoDiscoveryFragment);
        }
    }

    private void x() {
        findViewById(R.id.common_title_home).setOnClickListener(new b());
        findViewById(R.id.common_title_option).setOnClickListener(new c());
        this.p.setOnClickListener(new d());
        this.o.setOnClickListener(new e());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.manto.center.BaseMantoCenterActivity, com.jingdong.sdk.platform.lib.ui.CompactActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.statusBarTransparentEnable = true;
        super.onCreate(bundle);
        initView();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.manto.center.BaseMantoCenterActivity, com.jingdong.sdk.platform.lib.ui.CompactActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    public void y(int i2) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        w(beginTransaction);
        if (i2 == 0) {
            h.k(this.f6365j, R.drawable.common_bottom_tab_mine);
            h.k(this.f6366k, R.drawable.common_bottom_tab_find_unchecked);
            if (getSupportFragmentManager().findFragmentByTag(DYConstants.DY_CENTER) instanceof MantoCenterMineFragment) {
                this.f6363h = (MantoCenterMineFragment) getSupportFragmentManager().findFragmentByTag(DYConstants.DY_CENTER);
            }
            MantoCenterMineFragment mantoCenterMineFragment = this.f6363h;
            if (mantoCenterMineFragment != null && mantoCenterMineFragment.isAdded()) {
                beginTransaction.show(this.f6363h);
            } else {
                MantoCenterMineFragment mantoCenterMineFragment2 = new MantoCenterMineFragment();
                this.f6363h = mantoCenterMineFragment2;
                mantoCenterMineFragment2.t(this);
                beginTransaction.add(R.id.container_fragment_Container, this.f6363h, DYConstants.DY_CENTER);
            }
        } else {
            h.k(this.f6365j, R.drawable.common_bottom_tab_mine_unchecked);
            h.k(this.f6366k, R.drawable.common_bottom_tab_find_checked);
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            String str = MantoDiscoveryFragment.f6356l;
            if (supportFragmentManager.findFragmentByTag(str) instanceof MantoDiscoveryFragment) {
                this.f6364i = (MantoDiscoveryFragment) getSupportFragmentManager().findFragmentByTag(str);
            }
            MantoDiscoveryFragment mantoDiscoveryFragment = this.f6364i;
            if (mantoDiscoveryFragment != null && mantoDiscoveryFragment.isAdded()) {
                beginTransaction.show(this.f6364i);
            } else {
                MantoDiscoveryFragment mantoDiscoveryFragment2 = new MantoDiscoveryFragment();
                this.f6364i = mantoDiscoveryFragment2;
                beginTransaction.add(R.id.container_fragment_Container, mantoDiscoveryFragment2, str);
            }
        }
        this.q = i2;
        beginTransaction.commitAllowingStateLoss();
    }

    public void z(String str, String str2) {
        this.f6368m = str2;
        post(new f(str));
    }
}
