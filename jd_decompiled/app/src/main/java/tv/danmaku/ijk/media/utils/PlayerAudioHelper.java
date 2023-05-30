package tv.danmaku.ijk.media.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.audiofx.Equalizer;
import android.os.Build;
import java.lang.ref.WeakReference;
import tv.danmaku.ijk.media.ext.config.AudioConfigBean;
import tv.danmaku.ijk.media.ext.config.PlayerConfigLoader;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes11.dex */
public class PlayerAudioHelper {
    private static final String EXTRA_VOLUME_STREAM_TYPE = "android.media.EXTRA_VOLUME_STREAM_TYPE";
    private static final String VOLUME_CHANGE_ACTION = "android.media.VOLUME_CHANGED_ACTION";
    private AudioConfigBean audioConfigBean;
    private Equalizer equalizer;
    private AudioAttributes mAttribute;
    private AudioManager mAudioManager;
    private Context mContext;
    private AudioFocusRequest mFocusRequest;
    private AudioManager.OnAudioFocusChangeListener mListener;
    private VolumeChangeReceiver volumeReceiver;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class VolumeChangeReceiver extends BroadcastReceiver {
        private final WeakReference<PlayerAudioHelper> playerAudioHelperWeakRef;

        public VolumeChangeReceiver(PlayerAudioHelper playerAudioHelper) {
            this.playerAudioHelperWeakRef = new WeakReference<>(playerAudioHelper);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (b.a(intent.getAction(), PlayerAudioHelper.VOLUME_CHANGE_ACTION) && intent.getIntExtra(PlayerAudioHelper.EXTRA_VOLUME_STREAM_TYPE, -1) == 3 && this.playerAudioHelperWeakRef.get() != null) {
                this.playerAudioHelperWeakRef.get().setEq();
            }
        }
    }

    public PlayerAudioHelper(Context context, final AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        if (context == null) {
            return;
        }
        this.mContext = context;
        this.audioConfigBean = PlayerConfigLoader.audioConfigBean;
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
        this.mListener = new AudioManager.OnAudioFocusChangeListener() { // from class: tv.danmaku.ijk.media.utils.PlayerAudioHelper.1
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public void onAudioFocusChange(int i2) {
                AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener2 = onAudioFocusChangeListener;
                if (onAudioFocusChangeListener2 != null) {
                    onAudioFocusChangeListener2.onAudioFocusChange(i2);
                }
            }
        };
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 21) {
            this.mAttribute = new AudioAttributes.Builder().setUsage(1).setContentType(2).build();
        }
        if (i2 >= 26) {
            this.mFocusRequest = new AudioFocusRequest.Builder(2).setWillPauseWhenDucked(true).setAcceptsDelayedFocusGain(true).setOnAudioFocusChangeListener(this.mListener).setAudioAttributes(this.mAttribute).build();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEq() {
        AudioConfigBean audioConfigBean;
        if (this.equalizer == null || (audioConfigBean = this.audioConfigBean) == null || !audioConfigBean.isEnableEq()) {
            return;
        }
        short numberOfBands = this.equalizer.getNumberOfBands();
        short s = 0;
        short s2 = this.equalizer.getBandLevelRange()[0];
        String str = "EQLevel max: " + ((int) this.equalizer.getBandLevelRange()[1]) + ", min: " + ((int) s2);
        short s3 = -1;
        while (true) {
            if (s < numberOfBands) {
                int centerFreq = this.equalizer.getCenterFreq(s);
                if (this.audioConfigBean.getMinFreq() < centerFreq && centerFreq < this.audioConfigBean.getMaxFreq()) {
                    s3 = s;
                    break;
                }
                s = (short) (s + 1);
            } else {
                break;
            }
        }
        this.equalizer.setBandLevel(s3, (short) (r3 * this.audioConfigBean.getEnhanceRatio()));
        String str2 = "current Freq is: " + this.equalizer.getCenterFreq(s3) + ", band level is: " + ((int) this.equalizer.getBandLevel(s3)) + ", band: " + ((int) s3);
    }

    public void abandonAudioFocus() {
        AudioFocusRequest audioFocusRequest;
        AudioManager audioManager = this.mAudioManager;
        if (audioManager == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 26 && (audioFocusRequest = this.mFocusRequest) != null) {
            audioManager.abandonAudioFocusRequest(audioFocusRequest);
            return;
        }
        AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = this.mListener;
        if (onAudioFocusChangeListener != null) {
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }

    public void enhanceAudio(IMediaPlayer iMediaPlayer) {
        AudioConfigBean audioConfigBean;
        if (iMediaPlayer == null || this.mContext == null || (audioConfigBean = this.audioConfigBean) == null || !audioConfigBean.isEnableEq()) {
            return;
        }
        try {
            if (this.volumeReceiver == null) {
                this.volumeReceiver = new VolumeChangeReceiver(this);
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(VOLUME_CHANGE_ACTION);
            this.mContext.registerReceiver(this.volumeReceiver, intentFilter);
            if (this.equalizer == null) {
                this.equalizer = new Equalizer(0, iMediaPlayer.getAudioSessionId());
            }
            this.equalizer.setEnabled(true);
            setEq();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void release() {
        Context context;
        abandonAudioFocus();
        Equalizer equalizer = this.equalizer;
        if (equalizer != null) {
            equalizer.setEnabled(false);
            this.equalizer.release();
            this.equalizer = null;
        }
        VolumeChangeReceiver volumeChangeReceiver = this.volumeReceiver;
        if (volumeChangeReceiver == null || (context = this.mContext) == null) {
            return;
        }
        context.unregisterReceiver(volumeChangeReceiver);
    }

    public void requestAudioFocus() {
        AudioFocusRequest audioFocusRequest;
        AudioManager audioManager = this.mAudioManager;
        if (audioManager == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 26 && (audioFocusRequest = this.mFocusRequest) != null) {
            audioManager.requestAudioFocus(audioFocusRequest);
            return;
        }
        AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = this.mListener;
        if (onAudioFocusChangeListener != null) {
            audioManager.requestAudioFocus(onAudioFocusChangeListener, 3, 2);
        }
    }
}
