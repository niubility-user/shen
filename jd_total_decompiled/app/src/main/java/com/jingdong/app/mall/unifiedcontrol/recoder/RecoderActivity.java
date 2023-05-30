package com.jingdong.app.mall.unifiedcontrol.recoder;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.unifiedcontrol.recoder.a;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.MyCountdownTimer;
import com.jingdong.jdsdk.utils.NetUtils;
import java.io.IOException;

/* loaded from: classes4.dex */
public class RecoderActivity extends BaseActivity implements a.b {

    /* renamed from: g  reason: collision with root package name */
    private ImageView f11639g;

    /* renamed from: h  reason: collision with root package name */
    private ImageView f11640h;

    /* renamed from: i  reason: collision with root package name */
    private ImageView f11641i;

    /* renamed from: j  reason: collision with root package name */
    private ImageView f11642j;

    /* renamed from: k  reason: collision with root package name */
    private LinearLayout f11643k;

    /* renamed from: l  reason: collision with root package name */
    private RecoderTimeView f11644l;

    /* renamed from: m  reason: collision with root package name */
    private com.jingdong.app.mall.unifiedcontrol.recoder.a f11645m;
    private AnimationDrawable p;
    private TextView q;
    private TextView r;
    private TextView s;
    private TextView t;
    private MediaPlayer u;
    private long w;
    private String x;
    private MyCountdownTimer y;

    /* renamed from: n  reason: collision with root package name */
    boolean f11646n = false;
    boolean o = false;
    private String v = "RecoderActivity";
    private boolean z = false;
    private int A = 0;
    private long B = 0;

    /* loaded from: classes4.dex */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RecoderActivity recoderActivity = RecoderActivity.this;
            if (recoderActivity.f11646n) {
                recoderActivity.N();
            } else {
                recoderActivity.P();
            }
        }
    }

    /* loaded from: classes4.dex */
    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RecoderActivity.this.L();
            RecoderActivity.this.finish();
        }
    }

    /* loaded from: classes4.dex */
    class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RecoderActivity.this.L();
            RecoderActivity.this.initView();
        }
    }

    /* loaded from: classes4.dex */
    class d implements View.OnClickListener {
        d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RecoderActivity.this.K(true);
        }
    }

    /* loaded from: classes4.dex */
    class e implements View.OnClickListener {
        e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            try {
                if (NetUtils.isNetworkAvailable()) {
                    RecoderActivity.this.J();
                    Intent intent = new Intent();
                    intent.putExtra("recoderResult", RecoderActivity.this.x);
                    RecoderActivity.this.setResult(-1, intent);
                    RecoderActivity.this.finish();
                    return;
                }
                Toast.makeText(RecoderActivity.this, "\u65e0\u7f51\u7edc\uff0c\u8bf7\u68c0\u67e5\u7f51\u7edc\u72b6\u6001", 1).show();
            } catch (Exception e2) {
                e2.printStackTrace();
                Toast.makeText(RecoderActivity.this, "\u5f55\u97f3\u6587\u4ef6\u9519\u8bef", 1).show();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class f extends MyCountdownTimer {
        f(long j2, long j3, int i2) {
            super(j2, j3, i2);
        }

        @Override // com.jingdong.jdsdk.utils.MyCountdownTimer
        public void onFinish(int i2) {
            RecoderActivity recoderActivity = RecoderActivity.this;
            RecoderActivity.this.O(recoderActivity.R(recoderActivity.w));
            RecoderActivity.this.f11640h.setImageResource(R.drawable.record_play);
            RecoderActivity.this.f11640h.setClickable(true);
            RecoderActivity.this.z = false;
        }

        @Override // com.jingdong.jdsdk.utils.MyCountdownTimer
        public void onTick(long j2, int i2) {
            long j3 = RecoderActivity.this.w - j2;
            if (j3 > RecoderActivity.this.w) {
                j3 = RecoderActivity.this.w;
            }
            RecoderActivity.this.O(RecoderActivity.this.R(j3));
            RecoderActivity.this.B = j3;
        }
    }

    /* loaded from: classes4.dex */
    class g implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ long f11652g;

        g(long j2) {
            this.f11652g = j2;
        }

        @Override // java.lang.Runnable
        public void run() {
            RecoderActivity.this.O(RecoderActivity.this.R(this.f11652g));
            RecoderActivity.this.w = this.f11652g;
            RecoderActivity recoderActivity = RecoderActivity.this;
            if (!recoderActivity.o && this.f11652g > 10000) {
                recoderActivity.t.setVisibility(4);
                RecoderActivity.this.f11639g.setImageResource(R.drawable.record_finish_usable);
                RecoderActivity.this.f11639g.setClickable(true);
                RecoderActivity recoderActivity2 = RecoderActivity.this;
                recoderActivity2.f11646n = true;
                recoderActivity2.o = true;
            }
            if (this.f11652g > 180000) {
                RecoderActivity.this.N();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class h extends PermissionHelper.PermissionResultCallBack {
        h() {
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onGranted() {
            RecoderActivity.this.M();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J() {
        MediaPlayer mediaPlayer = this.u;
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                this.u.stop();
                Q();
            }
            this.u.release();
            this.u = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void K(boolean z) {
        MediaPlayer mediaPlayer = this.u;
        if (mediaPlayer == null) {
            this.u = new MediaPlayer();
        } else {
            mediaPlayer.reset();
        }
        try {
            this.u.setDataSource(this.x);
            this.u.prepare();
            if (z) {
                this.A = 0;
                this.B = 0L;
            }
            int i2 = this.A;
            if (i2 < this.w && i2 > 0) {
                this.u.seekTo(i2);
            }
            this.u.start();
            this.f11640h.setImageResource(R.drawable.record_pause);
            this.f11640h.setClickable(false);
            Q();
            f fVar = new f(this.w - this.B, 500L, 123);
            this.y = fVar;
            fVar.start();
        } catch (IOException unused) {
            if (Log.D) {
                Log.e(this.v, "\u64ad\u653e\u5931\u8d25");
                this.f11640h.setImageResource(R.drawable.record_play);
                this.f11640h.setClickable(true);
                this.z = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void L() {
        this.f11645m.e();
        MediaPlayer mediaPlayer = this.u;
        if (mediaPlayer == null || !mediaPlayer.isPlaying()) {
            return;
        }
        Q();
        this.u.pause();
        this.A = this.u.getCurrentPosition();
        this.z = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M() {
        if (this.f11645m.b(this)) {
            this.f11639g.setClickable(false);
            this.f11642j.setVisibility(0);
            this.p.start();
            this.r.setText("\u8bed\u97f3\u5f55\u5165\u4e2d~");
            this.t.setVisibility(0);
            this.f11645m.d(this);
            this.f11639g.setImageResource(R.drawable.record_finish_unusable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void N() {
        this.f11646n = false;
        this.f11645m.e();
        this.r.setText("\u5f55\u97f3\u5b8c\u6210~");
        this.f11644l.setFinishTime();
        this.p.stop();
        this.t.setVisibility(4);
        this.f11639g.setVisibility(4);
        this.f11643k.setVisibility(0);
        this.s.setTextColor(-905168);
        this.s.setClickable(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void P() {
        if (PermissionHelper.hasGrantedPermissions(this, PermissionHelper.generateBundle("recoder", getClass().getSimpleName(), "onClick"), new String[]{"android.permission.RECORD_AUDIO"}, true, new h())) {
            M();
        }
    }

    private void Q() {
        MyCountdownTimer myCountdownTimer = this.y;
        if (myCountdownTimer != null) {
            myCountdownTimer.cancel(123);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initView() {
        this.r.setText("\u5f00\u59cb\u5f55\u97f3\u5427~");
        this.f11639g.setImageResource(R.drawable.record_start);
        this.f11644l.setTextEnd("/03:00");
        this.f11644l.setTextTime("00", "00");
        this.f11639g.setVisibility(0);
        this.f11643k.setVisibility(8);
        this.t.setVisibility(4);
        this.f11642j.setVisibility(4);
        this.f11640h.setVisibility(0);
        this.f11640h.setImageResource(R.drawable.record_play);
        this.f11640h.setClickable(true);
        this.s.setTextColor(-3421237);
        this.f11639g.setClickable(true);
        this.s.setClickable(false);
        this.o = false;
        this.z = false;
        this.A = 0;
    }

    public void O(long[] jArr) {
        try {
            String str = jArr[1] + "";
            String str2 = jArr[2] + "";
            if (str.length() <= 1) {
                str = "0" + str;
            }
            if (str2.length() <= 1) {
                str2 = "0" + str2;
            }
            this.f11644l.setTextTime(str, str2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public long[] R(long j2) {
        long j3 = j2 / 1000;
        long j4 = (j3 / 60) / 60;
        long j5 = j4 * 60 * 60;
        long j6 = ((j2 - (j5 * 1000)) / 1000) / 60;
        long j7 = (j3 - j5) - (60 * j6);
        if (j4 < 0) {
            j4 = 0;
        }
        if (j6 < 0) {
            j6 = 0;
        }
        if (j7 < 0) {
            j7 = 0;
        }
        return new long[]{j4, j6, j7};
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(128, 128);
        setContentView(R.layout.activity_recoder_dialog);
        this.f11642j = (ImageView) findViewById(R.id.record_audio);
        this.f11639g = (ImageView) findViewById(R.id.record_start);
        this.f11640h = (ImageView) findViewById(R.id.record_play);
        this.f11641i = (ImageView) findViewById(R.id.record_reset);
        this.f11643k = (LinearLayout) findViewById(R.id.record_finish);
        this.f11644l = (RecoderTimeView) findViewById(R.id.record_time);
        this.q = (TextView) findViewById(R.id.record_close);
        this.r = (TextView) findViewById(R.id.record_message);
        this.s = (TextView) findViewById(R.id.record_publish);
        this.t = (TextView) findViewById(R.id.record_hint);
        this.f11642j.setImageResource(R.drawable.recoder_animlist);
        initView();
        this.p = (AnimationDrawable) this.f11642j.getDrawable();
        com.jingdong.app.mall.unifiedcontrol.recoder.a aVar = new com.jingdong.app.mall.unifiedcontrol.recoder.a();
        this.f11645m = aVar;
        aVar.c(this);
        this.f11639g.setOnClickListener(new a());
        this.q.setOnClickListener(new b());
        this.f11641i.setOnClickListener(new c());
        this.f11640h.setOnClickListener(new d());
        this.s.setOnClickListener(new e());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        J();
        Q();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        L();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.f11643k.getVisibility() == 8) {
            if (this.f11646n) {
                N();
            } else {
                initView();
            }
        } else if (this.z) {
            K(false);
        }
    }

    @Override // com.jingdong.app.mall.unifiedcontrol.recoder.a.b
    public void onStop(String str) {
        this.x = str;
    }

    @Override // com.jingdong.app.mall.unifiedcontrol.recoder.a.b
    public void onUpdate(double d2, long j2) {
        post(new g(j2));
    }
}
