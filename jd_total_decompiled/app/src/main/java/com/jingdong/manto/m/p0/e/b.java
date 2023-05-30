package com.jingdong.manto.m.p0.e;

import android.media.MediaPlayer;
import android.text.TextUtils;
import android.util.Pair;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.manto.h;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.refact.video.JsApiVideoPlayer;
import com.jingdong.manto.m.p0.e.d.e;
import com.jingdong.manto.sdk.MantoSdkManager;
import com.jingdong.manto.sdk.api.IAudioPlayer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class b {
    private static AtomicInteger a;
    private static Map<String, IAudioPlayer> b = new HashMap();

    /* renamed from: c */
    private static volatile boolean f13525c;

    /* loaded from: classes15.dex */
    public class a implements IAudioPlayer.AudioListener {
        final /* synthetic */ String a;
        final /* synthetic */ h b;

        a(String str, h hVar) {
            this.a = str;
            this.b = hVar;
        }

        @Override // com.jingdong.manto.sdk.api.IAudioPlayer.AudioListener
        public void onStateChange(String str, String str2, int i2) {
            HashMap hashMap = new HashMap();
            hashMap.put(XView2Constants.STATE, str);
            hashMap.put("audioId", this.a);
            if (i2 != 0) {
                hashMap.put("errMsg", str2);
                hashMap.put("errCode", Integer.valueOf(i2));
            }
            new e.b().a(this.b).a(hashMap).a();
        }
    }

    /* renamed from: com.jingdong.manto.m.p0.e.b$b */
    /* loaded from: classes15.dex */
    public class C0594b implements IAudioPlayer.OnPreparedListener {
        final /* synthetic */ IAudioPlayer a;

        C0594b(IAudioPlayer iAudioPlayer) {
            this.a = iAudioPlayer;
        }

        @Override // com.jingdong.manto.sdk.api.IAudioPlayer.OnPreparedListener
        public void onPrepareError(Throwable th) {
            IAudioPlayer.AudioListener audioListener = this.a.getAudioListener();
            if (audioListener != null) {
                audioListener.onStateChange(IAudioPlayer.AUDIO_STATE_ERROR, "auto play error", 10004);
            }
        }

        @Override // com.jingdong.manto.sdk.api.IAudioPlayer.OnPreparedListener
        public void onPrepared() {
            this.a.start();
            com.jingdong.manto.m.p0.b.a().a(true);
        }
    }

    /* loaded from: classes15.dex */
    public class c implements IAudioPlayer.OnSeekCompleteListener {
        final /* synthetic */ IAudioPlayer a;

        c(IAudioPlayer iAudioPlayer) {
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

    public static Pair<Boolean, String> a(String str, int i2) {
        IAudioPlayer iAudioPlayer = b.get(str);
        if (iAudioPlayer == null) {
            return new Pair<>(Boolean.FALSE, "audio player not found");
        }
        try {
            iAudioPlayer.setOnSeekCompleteListener(new c(iAudioPlayer));
            iAudioPlayer.seekTo(i2);
            return new Pair<>(Boolean.TRUE, IMantoBaseModule.SUCCESS);
        } catch (Throwable unused) {
            return new Pair<>(Boolean.FALSE, "seek audio fail");
        }
    }

    public static Pair<Boolean, String> a(String str, boolean z, boolean z2, float f2, int i2, String str2) {
        IAudioPlayer iAudioPlayer = b.get(str);
        if (iAudioPlayer == null) {
            return new Pair<>(Boolean.FALSE, "audio player not found");
        }
        try {
            String dataSource = iAudioPlayer.getDataSource();
            iAudioPlayer.setLooping(z2);
            iAudioPlayer.setVolume(f2);
            iAudioPlayer.setStartTime(i2);
            if (TextUtils.equals(str2, dataSource)) {
                iAudioPlayer.setAutoPlay(z);
            } else {
                iAudioPlayer.setDataSource(str2);
                iAudioPlayer.setPrepared(false);
                if (z) {
                    iAudioPlayer.prepare(false, new C0594b(iAudioPlayer));
                } else {
                    iAudioPlayer.prepare(false, null);
                }
            }
            return new Pair<>(Boolean.TRUE, IMantoBaseModule.SUCCESS);
        } catch (Throwable unused) {
            iAudioPlayer.setPrepared(false);
            IAudioPlayer.AudioListener audioListener = iAudioPlayer.getAudioListener();
            if (audioListener != null) {
                audioListener.onStateChange(IAudioPlayer.AUDIO_STATE_ERROR, "set audio state error", 10004);
            }
            return new Pair<>(Boolean.FALSE, "setAudioState fail");
        }
    }

    public static void a(h hVar, boolean z) {
        boolean z2 = false;
        for (IAudioPlayer iAudioPlayer : b.values()) {
            if (iAudioPlayer != null && iAudioPlayer.isPlaying()) {
                try {
                    iAudioPlayer.pause();
                } catch (Throwable unused) {
                }
                z2 = true;
            }
        }
        if (z && z2) {
            f13525c = true;
            try {
                hVar.h().f13014f.getFirstPage().i().a("onAudioInterruptionBegin", "", (int[]) null);
            } catch (Throwable unused2) {
            }
        }
    }

    public static void a(String str) {
        IAudioPlayer iAudioPlayer = b.get(str);
        if (iAudioPlayer == null) {
            return;
        }
        iAudioPlayer.stop();
        iAudioPlayer.release();
        b.remove(str);
        com.jingdong.manto.m.p0.b.a().a(false);
    }

    public static void a(String str, boolean z) {
    }

    public static boolean a(h hVar, String str) {
        if (b.containsKey(str)) {
            return true;
        }
        IAudioPlayer iAudioPlayer = (IAudioPlayer) MantoSdkManager.instanceOf(IAudioPlayer.class);
        if (iAudioPlayer == null) {
            iAudioPlayer = new com.jingdong.manto.m.p0.c();
        }
        iAudioPlayer.setAudioListener(new a(str, hVar));
        b.put(str, iAudioPlayer);
        return true;
    }

    public static String b(String str) {
        if (a == null) {
            a = new AtomicInteger();
        }
        return str + "-" + a.incrementAndGet();
    }

    public static void b(h hVar, boolean z) {
        if (z && f13525c) {
            f13525c = false;
            try {
                hVar.h().f13014f.getFirstPage().i().a("onAudioInterruptionEnd", "", (int[]) null);
            } catch (Throwable unused) {
            }
        }
    }

    public static JSONObject c(String str) {
        IAudioPlayer iAudioPlayer = b.get(str);
        if (iAudioPlayer == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("duration", iAudioPlayer.getDuration() * 1000);
            jSONObject.put("currentTime", iAudioPlayer.getCurrentPosition() * 1000);
            jSONObject.put("paused", !iAudioPlayer.isPlaying());
            jSONObject.put("buffered", iAudioPlayer.getBuffered());
            jSONObject.put("src", iAudioPlayer.getDataSource());
            jSONObject.put("startTime", iAudioPlayer.getStartTime());
            jSONObject.put(JsApiVideoPlayer.CM_PLAY_RATE, "1.0");
            return jSONObject;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Pair<Boolean, String> d(String str) {
        IAudioPlayer iAudioPlayer = b.get(str);
        if (iAudioPlayer == null) {
            return new Pair<>(Boolean.FALSE, "audio player not found");
        }
        try {
            iAudioPlayer.pause();
            com.jingdong.manto.m.p0.b.a().a(false);
            return new Pair<>(Boolean.TRUE, IMantoBaseModule.SUCCESS);
        } catch (Throwable unused) {
            return new Pair<>(Boolean.FALSE, "pause audio fail");
        }
    }

    public static void e(String str) {
        IAudioPlayer iAudioPlayer = b.get(str);
        if (iAudioPlayer == null) {
            return;
        }
        iAudioPlayer.pause();
        com.jingdong.manto.m.p0.b.a().a(false);
    }

    public static Pair<Boolean, String> f(String str) {
        IAudioPlayer iAudioPlayer = b.get(str);
        if (iAudioPlayer == null) {
            return new Pair<>(Boolean.FALSE, "audio player not found");
        }
        if (iAudioPlayer.isPlaying()) {
            return new Pair<>(Boolean.FALSE, "audio is playing, don't play again");
        }
        try {
            iAudioPlayer.start();
            com.jingdong.manto.m.p0.b.a().a(true);
            return new Pair<>(Boolean.TRUE, IMantoBaseModule.SUCCESS);
        } catch (Throwable unused) {
            return new Pair<>(Boolean.FALSE, "play audio fail");
        }
    }

    public static Pair<Boolean, String> g(String str) {
        IAudioPlayer iAudioPlayer = b.get(str);
        if (iAudioPlayer == null) {
            return new Pair<>(Boolean.FALSE, "audio player not found");
        }
        try {
            iAudioPlayer.stop();
            com.jingdong.manto.m.p0.b.a().a(false);
            return new Pair<>(Boolean.TRUE, IMantoBaseModule.SUCCESS);
        } catch (Throwable unused) {
            return new Pair<>(Boolean.FALSE, "stop audio fail");
        }
    }
}
