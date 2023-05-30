package com.jingdong.jdreact.plugin.audio;

import android.app.Activity;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.example.widget.media.MediaPlayerHelper;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes13.dex */
public class ReactAudioPlayerUtil {
    private static ReactAudioPlayerUtil instance;
    private final String TAG = ReactAudioPlayerUtil.class.getSimpleName();
    private boolean isPaused = false;
    private AudioPlayerListener mListener;
    private IMediaPlayer mPlayer;
    private String playPath;

    /* loaded from: classes13.dex */
    public interface AudioPlayerListener {
        void onComplete();
    }

    private ReactAudioPlayerUtil() {
    }

    public static ReactAudioPlayerUtil getInstance() {
        if (instance == null) {
            instance = new ReactAudioPlayerUtil();
        }
        return instance;
    }

    private void initIjkPlayer() {
        MediaPlayerHelper.loadLibrariesOnceSafe(JDReactHelper.newInstance().getCurrentMyActivity());
    }

    private void start(String str, AudioPlayerListener audioPlayerListener) {
        IMediaPlayer iMediaPlayer;
        if (!TextUtils.isEmpty(this.playPath) && this.playPath.equals(str) && (iMediaPlayer = this.mPlayer) != null) {
            try {
                iMediaPlayer.start();
            } catch (Exception e2) {
                OKLog.e(this.TAG, e2);
            }
        } else {
            IMediaPlayer iMediaPlayer2 = this.mPlayer;
            if (iMediaPlayer2 != null) {
                iMediaPlayer2.reset();
                this.mPlayer.release();
            }
            initIjkPlayer();
            this.mPlayer = MediaPlayerHelper.createPlayer(new IPlayerControl.PlayerOptions(false));
            Activity currentMyActivity = JDReactHelper.newInstance().getCurrentMyActivity();
            if (currentMyActivity != null) {
                ((AudioManager) currentMyActivity.getSystemService("audio")).requestAudioFocus(null, 3, 1);
            }
            Uri parse = Uri.parse(str);
            try {
                if (Build.VERSION.SDK_INT >= 14) {
                    this.mPlayer.setDataSource(currentMyActivity, parse, null);
                } else {
                    this.mPlayer.setDataSource(parse.toString());
                }
                this.mPlayer.setOnCompletionListener(new IMediaPlayer.OnCompletionListener() { // from class: com.jingdong.jdreact.plugin.audio.ReactAudioPlayerUtil.1
                    @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnCompletionListener
                    public void onCompletion(IMediaPlayer iMediaPlayer3) {
                        if (ReactAudioPlayerUtil.this.mListener != null) {
                            ReactAudioPlayerUtil.this.mListener.onComplete();
                        }
                    }
                });
                this.mPlayer.prepareAsync();
                this.mPlayer.start();
                this.playPath = str;
            } catch (Exception e3) {
                OKLog.e(this.TAG, e3);
                ToastUtils.shortToast(JDReactHelper.newInstance().getCurrentMyActivity(), "play fail");
            }
        }
        this.isPaused = false;
    }

    public void free() {
        try {
            this.isPaused = false;
            this.mListener = null;
            IMediaPlayer iMediaPlayer = this.mPlayer;
            if (iMediaPlayer != null) {
                iMediaPlayer.release();
            }
            this.playPath = null;
            Activity currentMyActivity = JDReactHelper.newInstance().getCurrentMyActivity();
            if (currentMyActivity != null) {
                ((AudioManager) currentMyActivity.getSystemService("audio")).abandonAudioFocus(null);
            }
        } catch (Exception e2) {
            OKLog.e(this.TAG, e2);
        }
    }

    public void pause() {
        try {
            if (this.mPlayer.isPlaying()) {
                this.mPlayer.pause();
                this.isPaused = true;
            }
        } catch (Exception e2) {
            OKLog.e(this.TAG, e2);
        }
    }

    public void play(String str) {
        play(str, null);
    }

    public void stop() {
        try {
            if (this.mPlayer.isPlaying()) {
                this.mPlayer.stop();
            }
            this.isPaused = false;
            this.mListener = null;
            this.mPlayer.release();
            this.mPlayer = null;
            Activity currentMyActivity = JDReactHelper.newInstance().getCurrentMyActivity();
            if (currentMyActivity != null) {
                ((AudioManager) currentMyActivity.getSystemService("audio")).abandonAudioFocus(null);
            }
        } catch (Exception e2) {
            OKLog.e(this.TAG, e2);
        }
    }

    public void play(String str, AudioPlayerListener audioPlayerListener) {
        start(str, audioPlayerListener);
    }
}
