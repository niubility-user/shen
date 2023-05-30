package com.jd.lib.productdetail.mainimage.comment;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.fragment.app.FragmentManager;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.lang.reflect.Method;

/* loaded from: classes15.dex */
public class PdMCommentActivity extends BaseActivity {

    /* renamed from: g  reason: collision with root package name */
    public PdMInfoCommentFragment f4629g;

    /* renamed from: h  reason: collision with root package name */
    public boolean f4630h;

    /* renamed from: i  reason: collision with root package name */
    public FrameLayout f4631i;

    /* renamed from: j  reason: collision with root package name */
    public FragmentManager f4632j;

    /* renamed from: k  reason: collision with root package name */
    public String f4633k;

    /* renamed from: l  reason: collision with root package name */
    public String f4634l;

    /* renamed from: m  reason: collision with root package name */
    public String f4635m;

    /* renamed from: n  reason: collision with root package name */
    public String f4636n;

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        FragmentManager fragmentManager;
        Bundle extras;
        FrameLayout frameLayout;
        super.onCreate(bundle);
        setContentView(R.layout.lib_pd_mainimage_comment_activity);
        int i2 = R.id.lib_pd_main_comment_container;
        this.f4631i = (FrameLayout) findViewById(i2);
        ((SimpleDraweeView) findViewById(R.id.lib_pd_main_comment_close)).setOnClickListener(new a(this));
        int statusBarHeight = UnStatusBarTintUtil.isEnable((Activity) this) ? UnStatusBarTintUtil.getStatusBarHeight((Activity) this) : 0;
        if (statusBarHeight != 0 && (frameLayout = this.f4631i) != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) frameLayout.getLayoutParams();
            layoutParams.topMargin = -statusBarHeight;
            this.f4631i.setLayoutParams(layoutParams);
        }
        this.f4632j = getSupportFragmentManager();
        if (getIntent() != null && (extras = getIntent().getExtras()) != null) {
            this.f4633k = extras.getString("skuId");
            this.f4634l = extras.getString("isShadowSku");
            this.f4635m = extras.getString("categroy");
            this.f4636n = extras.getString("isXnzt");
        }
        Bundle bundle2 = new Bundle();
        bundle2.putLong("id", PDUtils.stringToLong(this.f4633k));
        bundle2.putString("sku", this.f4633k);
        bundle2.putBoolean("isFromPD", true);
        bundle2.putString("isShadowSku", this.f4634l);
        bundle2.putString("categroy", this.f4635m);
        bundle2.putString("isXnzt", this.f4636n);
        PdMInfoCommentFragment pdMInfoCommentFragment = new PdMInfoCommentFragment();
        pdMInfoCommentFragment.setArguments(bundle2);
        this.f4629g = pdMInfoCommentFragment;
        try {
            if (!pdMInfoCommentFragment.isAdded() && (fragmentManager = this.f4632j) != null) {
                fragmentManager.beginTransaction().add(i2, this.f4629g).commitAllowingStateLoss();
            }
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
        PdMInfoCommentFragment pdMInfoCommentFragment2 = this.f4629g;
        pdMInfoCommentFragment2.getClass();
        if (Log.D) {
            Log.d("PdInfoCommentFragment", "setCurVisible()");
        }
        Bundle bundle3 = new Bundle();
        bundle3.putBoolean(XView2Constants.ISVISIBLE, true);
        try {
            Method method = pdMInfoCommentFragment2.o;
            if (method != null) {
                method.invoke(pdMInfoCommentFragment2.f4637g, bundle3);
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        if (Log.D) {
            Log.d("PdInfoCommentFragment", "restore()");
        }
        try {
            Method method2 = pdMInfoCommentFragment2.f4640j;
            if (method2 != null) {
                method2.invoke(pdMInfoCommentFragment2.f4637g, 0);
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        PdMInfoCommentFragment pdMInfoCommentFragment3 = this.f4629g;
        pdMInfoCommentFragment3.getClass();
        if (Log.D) {
            Log.d("PdInfoCommentFragment", "initData()");
        }
        try {
            Method method3 = pdMInfoCommentFragment3.f4638h;
            if (method3 != null) {
                method3.invoke(pdMInfoCommentFragment3.f4637g, new Object[0]);
            }
        } catch (Exception e5) {
            e5.printStackTrace();
        }
        post(new b(this), 300);
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.f4630h = true;
    }
}
