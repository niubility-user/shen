package com.jingdong.app.mall.home.video;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.video.SimpleVideoView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.XView.XView;
import com.jingdong.common.XView.XViewCallBack;
import com.jingdong.common.XView.XViewEntity;
import com.jingdong.common.XView.XViewHelper;
import com.jingdong.common.XView.XViewRequest;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.oklog.OKLog;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;

/* loaded from: classes4.dex */
public class SimpleVideoActivity extends BaseActivity {
    private static String s = SimpleVideoActivity.class.getSimpleName();

    /* renamed from: g  reason: collision with root package name */
    private String f10983g;

    /* renamed from: h  reason: collision with root package name */
    private String f10984h;

    /* renamed from: i  reason: collision with root package name */
    private String f10985i;

    /* renamed from: l  reason: collision with root package name */
    private SimpleVideoView f10988l;

    /* renamed from: m  reason: collision with root package name */
    private FrameLayout f10989m;

    /* renamed from: n  reason: collision with root package name */
    private XView f10990n;
    private FrameLayout r;

    /* renamed from: j  reason: collision with root package name */
    private int f10986j = -1;

    /* renamed from: k  reason: collision with root package name */
    private int f10987k = -1;
    private boolean o = false;
    private boolean p = false;
    private String q = "Video_Activity";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements SimpleVideoView.f {
        a() {
        }

        @Override // com.jingdong.app.mall.home.video.SimpleVideoView.f
        public void a(boolean z) {
            SimpleVideoActivity simpleVideoActivity = SimpleVideoActivity.this;
            String simpleName = SimpleVideoActivity.class.getSimpleName();
            StringBuilder sb = new StringBuilder();
            sb.append((SimpleVideoActivity.this.f10990n == null || !SimpleVideoActivity.this.f10990n.isXViewShow()) ? "0" : "1");
            sb.append(z ? "_0" : "_1");
            JDMtaUtils.onClickWithPageId(simpleVideoActivity, "Video_Activity_AudioSwitch", simpleName, sb.toString(), SimpleVideoActivity.this.q);
        }

        @Override // com.jingdong.app.mall.home.video.SimpleVideoView.f
        public void onCloseClick() {
            SimpleVideoActivity.this.finish();
            JDMtaUtils.onClickWithPageId(SimpleVideoActivity.this, "Video_Activity_VideoClose", SimpleVideoActivity.class.getSimpleName(), (SimpleVideoActivity.this.f10990n == null || !SimpleVideoActivity.this.f10990n.isXViewShow()) ? "0" : "1", SimpleVideoActivity.this.q);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements IPlayerControl.OnPlayerStateListener {
        b() {
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onCompletion() {
            SimpleVideoActivity.this.O();
            SimpleVideoActivity.this.N();
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onCreatePlayer() {
            SimpleVideoActivity.this.G();
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public boolean onError(int i2, int i3) {
            return false;
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public boolean onInfo(int i2, int i3) {
            if (i2 != 3) {
                if (i2 == 702 && SimpleVideoActivity.this.p) {
                    SimpleVideoActivity.this.f10988l.u();
                    return false;
                }
                return false;
            }
            SimpleVideoActivity.this.J();
            SimpleVideoActivity.this.K();
            if (SimpleVideoActivity.this.p) {
                SimpleVideoActivity.this.f10988l.u();
                return false;
            }
            return false;
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onPrepared(long j2) {
            if (SimpleVideoActivity.this.p) {
                SimpleVideoActivity.this.f10988l.u();
            }
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onSeekComplete() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c implements XViewCallBack {
        private boolean a = false;
        private boolean b = false;

        c() {
        }

        @Override // com.jingdong.common.XView.XViewCallBack
        public void onCloseButtonClicked() {
            SimpleVideoActivity.this.finish();
        }

        @Override // com.jingdong.common.XView.XViewCallBack
        public void onError(int i2) {
            this.b = true;
        }

        @Override // com.jingdong.common.XView.XViewCallBack
        public void onStart() {
        }

        @Override // com.jingdong.common.XView.XViewCallBack
        public void onXViewDisplayed() {
        }

        @Override // com.jingdong.common.XView.XViewCallBack
        public void onXViewLoadingUrl(String str) {
        }

        @Override // com.jingdong.common.XView.XViewCallBack
        public void onXViewReady() {
            SimpleVideoActivity.this.o = true;
        }

        @Override // com.jingdong.common.XView.XViewCallBack
        public void onXViewRequest(XViewRequest xViewRequest) {
            String str;
            JDJSONObject parseObject;
            if (OKLog.D) {
                OKLog.d(SimpleVideoActivity.s, "xview onXViewRequest:" + xViewRequest);
            }
            if (xViewRequest == null || (str = xViewRequest.requestParams) == null || (parseObject = JDJSON.parseObject(str)) == null || !"replay".equalsIgnoreCase(parseObject.optString("action")) || !JumpUtil.VALUE_DES_SH_ACTIVITY_VIDEO.equalsIgnoreCase(parseObject.optString("des"))) {
                return;
            }
            this.a = true;
            SimpleVideoActivity.this.M();
        }

        @Override // com.jingdong.common.XView.XViewCallBack
        public void onXViewVisibleChanged(boolean z) {
        }

        @Override // com.jingdong.common.XView.XViewCallBack
        public void onXVivewClosed() {
            if (this.a || this.b) {
                return;
            }
            SimpleVideoActivity.this.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G() {
        FrameLayout frameLayout = this.r;
        if (frameLayout != null) {
            frameLayout.setVisibility(8);
        }
    }

    private void H(Intent intent) {
        if (intent == null) {
            finish();
            return;
        }
        this.f10983g = intent.getStringExtra("videoUrl");
        this.f10984h = intent.getStringExtra("completeUrl");
        this.f10985i = intent.getStringExtra("completeImg");
        this.f10986j = intent.getIntExtra("isVoice", -1);
        this.f10987k = intent.getIntExtra("isShowCtrl", -1);
        if (TextUtils.isEmpty(this.f10983g)) {
            finish();
            return;
        }
        L();
        this.f10988l.B("", "30", this.f10983g, null);
        this.f10988l.D(this.f10983g);
        int i2 = this.f10986j;
        if (i2 != -1) {
            this.f10988l.o(i2 == 1);
        }
        int i3 = this.f10987k;
        if (i3 != -1) {
            this.f10988l.A(i3 != 1 ? 8 : 0);
        }
        if (OKLog.D) {
            OKLog.d(s, "videoUrl:" + this.f10983g + " completeUrl:" + this.f10984h + " completeImg:" + this.f10985i + " isVoice:" + this.f10986j + " isShowCtrl:" + this.f10987k);
            String str = s;
            StringBuilder sb = new StringBuilder();
            sb.append("Intent:");
            sb.append(intent.getExtras());
            OKLog.d(str, sb.toString());
        }
    }

    private void I() {
        this.f10988l.C(new a());
        this.f10988l.z(new b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J() {
        if (TextUtils.isEmpty(this.f10984h)) {
            return;
        }
        this.o = false;
        c cVar = new c();
        XViewEntity xViewEntity = new XViewEntity();
        xViewEntity.url = this.f10984h;
        xViewEntity.isIntercepted = true;
        xViewEntity.needAutoDisplay = false;
        xViewEntity.needCloseButton = false;
        xViewEntity.needAutoClose = false;
        XView xView = this.f10990n;
        if (xView == null) {
            this.f10990n = XViewHelper.createXView(this, this.f10989m, getClass().getSimpleName(), xViewEntity, cVar);
        } else {
            xView.configXView(this.f10989m, xViewEntity, cVar);
        }
        XView xView2 = this.f10990n;
        if (xView2 != null) {
            xView2.startXView();
            this.f10990n.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void K() {
        if (TextUtils.isEmpty(this.f10985i) || this.r != null) {
            return;
        }
        FrameLayout r = this.f10988l.r();
        this.r = r;
        r.setVisibility(8);
        if (this.r == null) {
            return;
        }
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(this);
        simpleDraweeView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        this.r.addView(simpleDraweeView, new FrameLayout.LayoutParams(-1, -1));
        JDImageUtils.displayImage(this.f10985i, (ImageView) simpleDraweeView, new JDDisplayImageOptions().resetViewBeforeLoading(false).showImageOnFail((Drawable) null).showImageOnLoading((Drawable) null).showImageForEmptyUri((Drawable) null), false);
    }

    private void L() {
        XView xView = this.f10990n;
        if (xView == null) {
            return;
        }
        xView.destroyXView();
        ViewParent parent = this.f10990n.getParent();
        if (parent != null) {
            ((ViewGroup) parent).removeView(this.f10990n);
        }
        this.f10990n = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M() {
        if (TextUtils.isEmpty(this.f10983g)) {
            return;
        }
        L();
        this.f10988l.x();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void N() {
        this.f10988l.y(true);
        if (TextUtils.isEmpty(this.f10984h) || this.f10990n == null || !this.o) {
            return;
        }
        this.f10988l.y(false);
        this.f10990n.displayXView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void O() {
        FrameLayout frameLayout = this.r;
        if (frameLayout != null) {
            frameLayout.setVisibility(0);
        }
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.statusBarTintEnable = false;
        getWindow().setFlags(1024, 1024);
        super.onCreate(bundle);
        setPageId(this.q);
        SimpleVideoView simpleVideoView = new SimpleVideoView(this);
        this.f10988l = simpleVideoView;
        setContentView(simpleVideoView);
        FrameLayout q = this.f10988l.q();
        this.f10989m = q;
        q.setVisibility(0);
        I();
        H(getIntent());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.f10988l.v();
        XView xView = this.f10990n;
        if (xView != null) {
            xView.destroyXView();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        H(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.p = true;
        this.f10988l.u();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.p = false;
        if (this.f10988l.p()) {
            return;
        }
        this.f10988l.E();
        this.f10988l.s();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
    }
}
