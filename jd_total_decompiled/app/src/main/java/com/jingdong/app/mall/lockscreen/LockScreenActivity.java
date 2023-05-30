package com.jingdong.app.mall.lockscreen;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.lockscreen.slider.b;
import com.jingdong.app.mall.lockscreen.slider.e;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.app.util.image.listener.JDImageLoadingProgressListener;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class LockScreenActivity extends MyActivity {

    /* renamed from: g */
    private SimpleDraweeView f11154g;

    /* renamed from: h */
    private SimpleDraweeView f11155h;

    /* renamed from: i */
    private String f11156i;

    /* renamed from: j */
    private String f11157j = "openapp.jdmobile://virtual?params={\"category\":\"jump\",\"des\":\"jdreactcommon\",\"appname\":\"JDReactSkinTheme\",\"modulename\":\"JDReactSkinTheme\",\"ishidden\":true,\"needLogin\":true,\"param\":{\"screen\":\"Channel\",\"sourceFrom\":\"-1\",\"transparentenable\": true}}";

    /* loaded from: classes4.dex */
    public class a implements JDImageLoadingListener {
        a() {
            LockScreenActivity.this = r1;
        }

        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingCancelled(String str, View view) {
        }

        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            if (Log.D) {
                Log.e("LockScreen", "image-->onLoadingComplete");
            }
        }

        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
            LockScreenActivity.this.finish();
            if (Log.D) {
                Log.e("LockScreen", "image-->onLoadingFailed");
            }
        }

        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingStarted(String str, View view) {
        }
    }

    /* loaded from: classes4.dex */
    public class b implements JDImageLoadingProgressListener {
        b(LockScreenActivity lockScreenActivity) {
        }

        @Override // com.jingdong.app.util.image.listener.JDImageLoadingProgressListener
        public void onProgressUpdate(String str, View view, int i2, int i3) {
            if (Log.D) {
                Log.e("LockScreen", "image-->Progress");
            }
        }
    }

    /* loaded from: classes4.dex */
    public class c implements View.OnClickListener {
        c() {
            LockScreenActivity.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            try {
                new OpenAppJumpBuilder.Builder(Uri.parse(LockScreenActivity.this.f11157j)).build().jump(LockScreenActivity.this);
            } catch (Exception e2) {
                if (Log.D) {
                    Log.e("LockScreen", "image-->jump" + e2.toString());
                }
            }
        }
    }

    private void initView() {
        this.f11154g = (SimpleDraweeView) findViewById(R.id.image_lock);
        this.f11155h = (SimpleDraweeView) findViewById(R.id.screen_lock_setting);
    }

    private void v() {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 28) {
            attributes.layoutInDisplayCutoutMode = 1;
        }
        window.setAttributes(attributes);
        window.getDecorView().setSystemUiVisibility(R2.attr.lineSpacing);
        if (i2 >= 21) {
            getWindow().addFlags(Integer.MIN_VALUE);
        } else {
            getWindow().addFlags(67108864);
        }
        getWindow().addFlags(4718592);
    }

    private void w() {
        String str = this.f11156i;
        if (str != null && !"".equals(str)) {
            JDImageUtils.displayImage(this.f11156i, this.f11154g, null, false, new a(), new b(this));
        } else {
            finish();
        }
        SimpleDraweeView simpleDraweeView = this.f11155h;
        if (simpleDraweeView != null) {
            simpleDraweeView.setOnClickListener(new c());
        }
    }

    private void x() {
        try {
            b.C0344b c0344b = new b.C0344b();
            c0344b.c(e.LEFT);
            c0344b.g(1.0f);
            c0344b.d(-16777216);
            c0344b.f(0.8f);
            c0344b.e(0.0f);
            c0344b.h(2400.0f);
            c0344b.b(0.5f);
            com.jingdong.app.mall.lockscreen.slider.a.a(this, c0344b.a());
        } catch (Exception unused) {
            if (Log.D) {
                Log.e("LockScreen", "image-->initSlider");
            }
        }
    }

    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        v();
        setContentView(R.layout.activity_lock_screen);
        this.f11156i = getIntent().getStringExtra("imageUrl");
        x();
        initView();
        w();
    }

    @Override // android.app.Activity
    protected void onUserLeaveHint() {
        finish();
        super.onUserLeaveHint();
    }
}
