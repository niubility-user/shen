package com.tencent.smtt.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import com.tencent.smtt.export.external.DexLoader;

/* loaded from: classes9.dex */
public class r extends FrameLayout implements MediaPlayer.OnErrorListener {
    private Object a;
    private t b;

    /* renamed from: c */
    private VideoView f17859c;
    private Context d;

    /* renamed from: e */
    private String f17860e;

    public r(Context context) {
        super(context.getApplicationContext());
        this.d = null;
        this.d = context;
    }

    private void b(Bundle bundle, Object obj) {
        boolean z;
        a();
        if (b()) {
            bundle.putInt("callMode", bundle.getInt("callMode"));
            z = this.b.a(this.a, bundle, this, obj);
        } else {
            z = false;
        }
        if (z) {
            return;
        }
        VideoView videoView = this.f17859c;
        if (videoView != null) {
            videoView.stopPlayback();
        }
        if (this.f17859c == null) {
            this.f17859c = new VideoView(getContext());
        }
        String string = bundle.getString("videoUrl");
        this.f17860e = string;
        this.f17859c.setVideoURI(Uri.parse(string));
        this.f17859c.setOnErrorListener(this);
        Intent intent = new Intent("com.tencent.smtt.tbs.video.PLAY");
        intent.addFlags(268435456);
        Context applicationContext = getContext().getApplicationContext();
        intent.setPackage(applicationContext.getPackageName());
        applicationContext.startActivity(intent);
    }

    public void a() {
        setBackgroundColor(-16777216);
        if (this.b == null) {
            f.a(true).a(getContext().getApplicationContext(), false, false);
            s a = f.a(true).a();
            DexLoader c2 = a != null ? a.c() : null;
            if (c2 != null && QbSdk.canLoadVideo(getContext())) {
                this.b = new t(c2);
            }
        }
        t tVar = this.b;
        if (tVar == null || this.a != null) {
            return;
        }
        this.a = tVar.a(getContext().getApplicationContext());
    }

    public void a(Activity activity) {
        VideoView videoView;
        if (b() || (videoView = this.f17859c) == null) {
            return;
        }
        if (videoView.getParent() == null) {
            Window window = activity.getWindow();
            FrameLayout frameLayout = (FrameLayout) window.getDecorView();
            window.addFlags(1024);
            window.addFlags(128);
            frameLayout.setBackgroundColor(-16777216);
            MediaController mediaController = new MediaController(activity);
            mediaController.setMediaPlayer(this.f17859c);
            this.f17859c.setMediaController(mediaController);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            layoutParams.gravity = 17;
            frameLayout.addView(this.f17859c, layoutParams);
        }
        if (Build.VERSION.SDK_INT >= 8) {
            this.f17859c.start();
        }
    }

    public void a(Activity activity, int i2) {
        VideoView videoView;
        VideoView videoView2;
        if (i2 == 3 && !b() && (videoView2 = this.f17859c) != null) {
            videoView2.pause();
        }
        if (i2 == 4) {
            this.d = null;
            if (!b() && (videoView = this.f17859c) != null) {
                videoView.stopPlayback();
                this.f17859c = null;
            }
        }
        if (i2 == 2 && !b()) {
            this.d = activity;
            a(activity);
        }
        if (b()) {
            this.b.a(this.a, activity, i2);
        }
    }

    public void a(Bundle bundle, Object obj) {
        b(bundle, obj);
    }

    public boolean b() {
        return (this.b == null || this.a == null) ? false : true;
    }

    public void c() {
        if (b()) {
            this.b.a(this.a);
        }
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
        try {
            Context context = this.d;
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                if (!activity.isFinishing()) {
                    activity.finish();
                }
            }
            Context context2 = getContext();
            if (context2 != null) {
                Toast.makeText(context2, "\u64ad\u653e\u5931\u8d25\uff0c\u8bf7\u9009\u62e9\u5176\u5b83\u64ad\u653e\u5668\u64ad\u653e", 1).show();
                Context applicationContext = context2.getApplicationContext();
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.addFlags(268435456);
                intent.setDataAndType(Uri.parse(this.f17860e), "video/*");
                applicationContext.startActivity(intent);
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }
}
