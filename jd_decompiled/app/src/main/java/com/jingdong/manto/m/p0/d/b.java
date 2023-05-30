package com.jingdong.manto.m.p0.d;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import android.widget.RemoteViews;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.R;
import com.jingdong.manto.h;
import com.jingdong.manto.jsapi.refact.video.JsApiVideoPlayer;
import com.jingdong.manto.m.p0.d.d;
import com.jingdong.manto.sdk.MantoSdkManager;
import com.jingdong.manto.sdk.api.IAudioPlayer;
import com.jingdong.manto.sdk.api.IImageLoader;
import com.jingdong.manto.service.BackgroundAudioService;
import com.jingdong.manto.service.BackgroundAudioService1;
import com.jingdong.manto.service.BackgroundAudioService2;
import com.jingdong.manto.service.BackgroundAudioService3;
import com.jingdong.manto.service.BackgroundAudioService4;
import com.jingdong.manto.service.BackgroundAudioServiceDefaultProcess;
import com.jingdong.manto.service.BackgroundAudioServiceSingleProcess;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoProcessUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes15.dex */
public class b {
    private IAudioPlayer a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f13510c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private String f13511e;

    /* renamed from: f  reason: collision with root package name */
    private String f13512f;

    /* renamed from: g  reason: collision with root package name */
    private String f13513g;

    /* renamed from: h  reason: collision with root package name */
    private String f13514h;

    /* renamed from: i  reason: collision with root package name */
    private int f13515i;

    /* renamed from: j  reason: collision with root package name */
    private NotificationManagerCompat f13516j;

    /* renamed from: k  reason: collision with root package name */
    private NotificationCompat.Builder f13517k;

    /* renamed from: l  reason: collision with root package name */
    private int f13518l;

    /* renamed from: m  reason: collision with root package name */
    private RemoteViews f13519m;

    /* renamed from: n  reason: collision with root package name */
    private h f13520n;
    private Bitmap o = null;
    private IAudioPlayer.AudioListener p;
    private boolean q;
    private boolean r;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class a implements IAudioPlayer.AudioListener {
        a() {
        }

        @Override // com.jingdong.manto.sdk.api.IAudioPlayer.AudioListener
        public void onStateChange(String str, String str2, int i2) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            if (TextUtils.equals(str, IAudioPlayer.AUDIO_STATE_ENDED)) {
                b bVar = b.this;
                bVar.a(bVar.f13510c, b.this.d, false);
            }
            String lowerCase = str.toLowerCase();
            HashMap hashMap = new HashMap();
            hashMap.put("src", b.this.b);
            hashMap.put(XView2Constants.STATE, lowerCase);
            if (i2 != 0) {
                hashMap.put("errMsg", str2);
                hashMap.put("errCode", Integer.valueOf(i2));
            }
            new d.C0593d().a(b.this.f13520n).a(hashMap).a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.manto.m.p0.d.b$b  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    public class C0592b implements IAudioPlayer.OnPreparedListener {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ IAudioPlayer f13521c;

        C0592b(String str, String str2, IAudioPlayer iAudioPlayer) {
            this.a = str;
            this.b = str2;
            this.f13521c = iAudioPlayer;
        }

        @Override // com.jingdong.manto.sdk.api.IAudioPlayer.OnPreparedListener
        public void onPrepareError(Throwable th) {
            if (b.this.p != null) {
                if (th != null) {
                    b.this.p.onStateChange(IAudioPlayer.AUDIO_STATE_ERROR, th.getMessage(), -1);
                } else {
                    b.this.p.onStateChange(IAudioPlayer.AUDIO_STATE_ERROR, "prepare error with src:" + b.this.b, -1);
                }
            }
            com.jingdong.manto.m.p0.b.a().a(false);
        }

        @Override // com.jingdong.manto.sdk.api.IAudioPlayer.OnPreparedListener
        public void onPrepared() {
            b.this.a(this.a, this.b, true);
            this.f13521c.start();
            com.jingdong.manto.m.p0.b.a().a(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class c implements IImageLoader.ImageLoaderCallback {
        final /* synthetic */ boolean a;
        final /* synthetic */ CountDownLatch b;

        c(boolean z, CountDownLatch countDownLatch) {
            this.a = z;
            this.b = countDownLatch;
        }

        @Override // com.jingdong.manto.sdk.api.IImageLoader.ImageLoaderCallback
        public void onFail() {
            if (this.a) {
                this.b.countDown();
            }
        }

        @Override // com.jingdong.manto.sdk.api.IImageLoader.ImageLoaderCallback
        public void onSuccess(Bitmap bitmap) {
            b.this.o = bitmap;
            if (this.a) {
                this.b.countDown();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class d implements IAudioPlayer.OnSeekCompleteListener {
        final /* synthetic */ IAudioPlayer a;

        d(b bVar, IAudioPlayer iAudioPlayer) {
            this.a = iAudioPlayer;
        }

        @Override // com.jingdong.manto.sdk.api.IAudioPlayer.OnSeekCompleteListener
        public void onSeekComplete(MediaPlayer mediaPlayer) {
            IAudioPlayer.AudioListener audioListener = this.a.getAudioListener();
            if (audioListener != null) {
                audioListener.onStateChange(IAudioPlayer.AUDIO_STATE_SEEKED, null, 0);
                if (this.a.isPlaying()) {
                    audioListener.onStateChange(IAudioPlayer.AUDIO_STATE_PLAY, null, 0);
                }
            }
        }
    }

    public b(int i2) {
        this.f13518l = i2;
    }

    private PendingIntent a(Context context, String str) {
        String processName = MantoProcessUtil.getProcessName();
        Intent intent = new Intent(context, processName.contains(":manto0") ? BackgroundAudioService.class : processName.contains(":manto1") ? BackgroundAudioService1.class : processName.contains(":manto2") ? BackgroundAudioService2.class : processName.contains(":manto3") ? BackgroundAudioService3.class : processName.contains(":manto4") ? BackgroundAudioService4.class : processName.contains(":mantoProcess") ? BackgroundAudioServiceSingleProcess.class : BackgroundAudioServiceDefaultProcess.class);
        intent.setAction(str + CartConstant.KEY_YB_INFO_LINK + this.f13520n.a());
        return PendingIntent.getService(context, 1, intent, Build.VERSION.SDK_INT >= 31 ? 201326592 : 134217728);
    }

    private void a(Context context, String str, String str2) {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(str, str2, 3);
            notificationChannel.enableLights(false);
            notificationChannel.setShowBadge(false);
            notificationChannel.enableVibration(false);
            notificationChannel.setSound(null, null);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(RemoteMessageConst.NOTIFICATION);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, boolean z) {
        Context g2 = com.jingdong.a.g();
        if (this.f13516j == null) {
            this.f13516j = NotificationManagerCompat.from(g2);
        }
        if (this.f13517k == null) {
            String b = com.jingdong.manto.c.b();
            try {
                a(g2, b, "BackgroundAudio");
                this.f13517k = new NotificationCompat.Builder(g2, b);
            } catch (Throwable unused) {
                this.f13517k = new NotificationCompat.Builder(g2);
            }
            this.f13517k.setChannelId(b);
        }
        if (this.f13519m == null) {
            RemoteViews remoteViews = new RemoteViews(com.jingdong.manto.c.b(), R.layout.manto_backgroundaudio_notification);
            this.f13519m = remoteViews;
            remoteViews.setOnClickPendingIntent(R.id.manto_backgroundaudio_notification_play, a(g2, "PlayOrPause"));
            this.f13519m.setOnClickPendingIntent(R.id.manto_backgroundaudio_notification_pre, a(g2, "Previous"));
            this.f13519m.setOnClickPendingIntent(R.id.manto_backgroundaudio_notification_next, a(g2, "Next"));
            this.f13519m.setOnClickPendingIntent(R.id.manto_backgroundaudio_notification_close, a(g2, "Close"));
        }
        try {
            this.f13519m.setImageViewResource(R.id.manto_backgroundaudio_notification_play, z ? R.drawable.manto_bg_audio_pause : R.drawable.manto_bg_audio_play);
            this.f13519m.setTextViewText(R.id.manto_backgroundaudio_notification_title, str);
            this.f13519m.setTextViewText(R.id.manto_backgroundaudio_notification_content, str2);
            if (this.o == null) {
                a(true);
            }
            Bitmap bitmap = this.o;
            if (bitmap != null) {
                this.f13519m.setImageViewBitmap(R.id.manto_backgroundaudio_notification_cover, bitmap);
            } else {
                this.f13519m.setImageViewResource(R.id.manto_backgroundaudio_notification_cover, R.drawable.manto_icon_default);
            }
            this.f13516j.notify(this.f13518l, this.f13517k.setContent(this.f13519m).setSmallIcon(R.drawable.manto_icon_default).setAutoCancel(true).setOngoing(true).setPriority(1).build());
        } catch (Throwable th) {
            MantoLog.e("BackgroundAudioWorker", "showNotification error", th);
        }
    }

    private void a(boolean z) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        IImageLoader iImageLoader = (IImageLoader) MantoSdkManager.instanceOf(IImageLoader.class);
        if (iImageLoader != null) {
            iImageLoader.loadImage(com.jingdong.manto.c.a(), this.f13513g, new c(z, countDownLatch));
        }
        if (z) {
            try {
                countDownLatch.await(2L, TimeUnit.SECONDS);
            } catch (Throwable unused) {
            }
        }
    }

    private void k() {
        try {
            NotificationManagerCompat notificationManagerCompat = this.f13516j;
            if (notificationManagerCompat != null) {
                notificationManagerCompat.cancel(this.f13518l);
            }
            IAudioPlayer a2 = a();
            if (a2 == null || !a2.isPlaying()) {
                return;
            }
            a2.stop();
            com.jingdong.manto.m.p0.b.a().a(false);
        } catch (Throwable unused) {
        }
    }

    public Pair<Boolean, String> a(h hVar, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i2) {
        a(hVar);
        IAudioPlayer a2 = a();
        if (i2 >= 0) {
            this.f13515i = i2;
            a2.setStartTime(i2);
        }
        if (!TextUtils.isEmpty(str2)) {
            this.f13510c = str2;
        }
        if (!TextUtils.isEmpty(str5)) {
            this.f13513g = str5;
            a(false);
        }
        if (!TextUtils.isEmpty(str3)) {
            this.d = str3;
        }
        if (!TextUtils.isEmpty(str7)) {
            this.f13514h = str7;
        }
        if (!TextUtils.isEmpty(str4)) {
            this.f13511e = str4;
        }
        if (!TextUtils.isEmpty(str6)) {
            this.f13512f = str6;
        }
        if (!TextUtils.isEmpty(str)) {
            if (TextUtils.equals(str, this.b)) {
                if (a2.isPrepared() && !a2.isPlaying()) {
                    a2.start();
                    com.jingdong.manto.m.p0.b.a().a(true);
                }
                return new Pair<>(Boolean.TRUE, "src is same with current music src");
            }
            this.b = str;
            k();
            a2.setPrepared(false);
            a2.setDataSource(str);
            a2.prepare(true, new C0592b(str2, str3, a2));
        }
        return new Pair<>(Boolean.TRUE, "");
    }

    public IAudioPlayer a() {
        if (this.a == null) {
            IAudioPlayer iAudioPlayer = (IAudioPlayer) MantoSdkManager.instanceOf(IAudioPlayer.class);
            this.a = iAudioPlayer;
            if (iAudioPlayer == null) {
                this.a = new com.jingdong.manto.m.p0.c();
            }
            a aVar = new a();
            this.p = aVar;
            this.a.setAudioListener(aVar);
        }
        return this.a;
    }

    public void a(double d2) {
        try {
            IAudioPlayer a2 = a();
            a2.setOnSeekCompleteListener(new d(this, a2));
            a2.seekTo((int) (d2 * 1000.0d));
        } catch (Throwable unused) {
        }
    }

    public void a(h hVar) {
        this.f13520n = hVar;
    }

    public Map b() {
        HashMap hashMap = new HashMap();
        IAudioPlayer a2 = a();
        if (a2 != null) {
            hashMap.put("duration", Integer.valueOf(a2.getDuration()));
            hashMap.put("currentTime", Integer.valueOf(a2.getCurrentPosition()));
            hashMap.put("paused", Boolean.valueOf(!a2.isPlaying()));
            hashMap.put("buffered", Integer.valueOf(a2.getBuffered()));
            hashMap.put("src", this.b);
            hashMap.put("title", this.f13510c);
            hashMap.put("epname", this.d);
            hashMap.put("singer", this.f13511e);
            hashMap.put("coverImgUrl", this.f13513g);
            hashMap.put("webUrl", this.f13512f);
            hashMap.put("protocol", this.f13514h);
            hashMap.put("startTime", Integer.valueOf(this.f13515i));
            hashMap.put(JsApiVideoPlayer.CM_PLAY_RATE, "1.0");
        }
        return hashMap;
    }

    public void c() {
        if (this.f13520n != null) {
            try {
                IAudioPlayer a2 = a();
                if (a2 != null && a2.isPlaying()) {
                    this.r = true;
                    i();
                }
                this.q = true;
            } catch (Throwable unused) {
            }
        }
    }

    public void d() {
        if (this.f13520n != null) {
            try {
                if (this.q) {
                    this.q = false;
                    if (this.r) {
                        this.r = false;
                        j();
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    public void e() {
        k();
    }

    public void f() {
        d.b.a(this.f13520n);
    }

    public void g() {
        IAudioPlayer a2 = a();
        boolean z = false;
        if (a2 != null) {
            boolean isPlaying = a2.isPlaying();
            MantoLog.d("BackgroundAudioWorker", "isPlaying:" + isPlaying);
            if (isPlaying) {
                a2.pause();
                com.jingdong.manto.m.p0.b.a().a(false);
            } else {
                a2.start();
                com.jingdong.manto.m.p0.b.a().a(true);
            }
            z = isPlaying;
        }
        RemoteViews remoteViews = this.f13519m;
        if (remoteViews != null) {
            remoteViews.setImageViewResource(R.id.manto_backgroundaudio_notification_play, z ? R.drawable.manto_bg_audio_play : R.drawable.manto_bg_audio_pause);
            this.f13516j.notify(this.f13518l, this.f13517k.setContent(this.f13519m).setSmallIcon(R.drawable.manto_icon_default).setAutoCancel(true).setOngoing(true).setPriority(1).build());
        }
    }

    public void h() {
        d.c.a(this.f13520n);
    }

    public void i() {
        IAudioPlayer a2 = a();
        if (a2 != null) {
            boolean isPlaying = a2.isPlaying();
            MantoLog.d("BackgroundAudioWorker", "isPlaying:" + isPlaying);
            if (isPlaying) {
                a2.pause();
                com.jingdong.manto.m.p0.b.a().a(false);
            }
        }
        a(this.f13510c, this.d, false);
    }

    public void j() {
        IAudioPlayer a2 = a();
        if (a2 != null) {
            boolean isPlaying = a2.isPlaying();
            MantoLog.d("BackgroundAudioWorker", "isPlaying:" + isPlaying);
            if (!isPlaying) {
                a2.start();
                com.jingdong.manto.m.p0.b.a().a(true);
            }
        }
        a(this.f13510c, this.d, true);
    }

    public void l() {
        k();
    }
}
