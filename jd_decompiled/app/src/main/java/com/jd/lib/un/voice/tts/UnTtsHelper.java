package com.jd.lib.un.voice.tts;

import android.content.Context;
import com.jd.lib.un.voice.VoiceConfig;
import g.g.a.f;
import g.g.a.j;
import g.g.a.k;
import g.g.a.l;
import g.g.a.m;

/* loaded from: classes16.dex */
public class UnTtsHelper implements k {
    private UnTtsConfig config;
    private Context context;
    private OnSpeechSynthesizeListener listener;
    private j ttsEngine;

    public UnTtsHelper(Context context) {
        this.context = context;
        this.ttsEngine = new j(this.context, m.ONLINE);
        f.d(true, 2);
        this.ttsEngine.i(this);
    }

    public void cancle() {
        j jVar = this.ttsEngine;
        if (jVar != null) {
            try {
                jVar.e();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public UnTtsConfig getConfig() {
        return this.config;
    }

    public int onBufValid() {
        return 0;
    }

    @Override // g.g.a.k
    public void onError(String str, l lVar) {
        OnSpeechSynthesizeListener onSpeechSynthesizeListener = this.listener;
        if (onSpeechSynthesizeListener != null) {
            onSpeechSynthesizeListener.onError(str, lVar.getDesc());
        }
    }

    @Override // g.g.a.k
    public void onPlayFinish(String str) {
        OnSpeechSynthesizeListener onSpeechSynthesizeListener = this.listener;
        if (onSpeechSynthesizeListener != null) {
            onSpeechSynthesizeListener.onPlayFinish(str);
        }
    }

    @Override // g.g.a.k
    public void onPlayPause(String str) {
        OnSpeechSynthesizeListener onSpeechSynthesizeListener = this.listener;
        if (onSpeechSynthesizeListener != null) {
            onSpeechSynthesizeListener.onPlayPause(str);
        }
    }

    @Override // g.g.a.k
    public void onPlayProgressChanged(String str, double d) {
    }

    @Override // g.g.a.k
    public void onPlayResume(String str) {
        OnSpeechSynthesizeListener onSpeechSynthesizeListener = this.listener;
        if (onSpeechSynthesizeListener != null) {
            onSpeechSynthesizeListener.onPlayResume(str);
        }
    }

    @Override // g.g.a.k
    public void onPlayStart(String str) {
        OnSpeechSynthesizeListener onSpeechSynthesizeListener = this.listener;
        if (onSpeechSynthesizeListener != null) {
            onSpeechSynthesizeListener.onPlayStart(str);
        }
    }

    @Override // g.g.a.k
    public void onSynthesizeDataArrived(String str, byte[] bArr, int i2, double d, String str2) {
    }

    @Override // g.g.a.k
    public void onSynthesizeFinish(String str) {
        OnSpeechSynthesizeListener onSpeechSynthesizeListener = this.listener;
        if (onSpeechSynthesizeListener != null) {
            onSpeechSynthesizeListener.onSynthesizeFinish(str);
        }
    }

    @Override // g.g.a.k
    public void onSynthesizeFirstPackage(String str) {
    }

    @Override // g.g.a.k
    public void onSynthesizeStart(String str) {
        OnSpeechSynthesizeListener onSpeechSynthesizeListener = this.listener;
        if (onSpeechSynthesizeListener != null) {
            onSpeechSynthesizeListener.onSynthesizeStart(str);
        }
    }

    @Override // g.g.a.k
    public void onTry(String str, l lVar) {
    }

    public void pause() {
        j jVar = this.ttsEngine;
        if (jVar != null) {
            try {
                jVar.f();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void resume() {
        j jVar = this.ttsEngine;
        if (jVar != null) {
            jVar.g();
        }
    }

    public UnTtsHelper setConfig(UnTtsConfig unTtsConfig) {
        if (unTtsConfig == null) {
            return this;
        }
        this.config = unTtsConfig;
        try {
            this.ttsEngine.h(unTtsConfig.getTtsParam());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return this;
    }

    public UnTtsHelper setOnSpeechSynthesizeListener(OnSpeechSynthesizeListener onSpeechSynthesizeListener) {
        this.listener = onSpeechSynthesizeListener;
        return this;
    }

    public void start(String str) {
        if (this.config == null) {
            this.config = new UnTtsConfig(VoiceConfig.getInstance().getTtsKey(), VoiceConfig.getInstance().getTtsS());
        }
        try {
            this.ttsEngine.h(this.config.getTtsParam());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            this.ttsEngine.j(str);
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public void stop() {
        j jVar = this.ttsEngine;
        if (jVar != null) {
            try {
                jVar.k();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
