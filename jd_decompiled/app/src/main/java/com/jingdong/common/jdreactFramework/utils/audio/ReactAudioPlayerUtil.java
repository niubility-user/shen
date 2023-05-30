package com.jingdong.common.jdreactFramework.utils.audio;

import android.content.Context;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.jdsdk.widget.ToastUtils;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.example.widget.media.MediaPlayerHelper;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes5.dex */
public class ReactAudioPlayerUtil {
    private static ReactAudioPlayerUtil instance;
    private final String TAG = ReactAudioPlayerUtil.class.getSimpleName();
    private boolean isPaused = false;
    private AudioPlayerListener mListener;
    private IMediaPlayer mPlayer;
    private String playPath;

    /* loaded from: classes5.dex */
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
        MediaPlayerHelper.loadLibrariesOnceSafe((Context) BaseFrameUtil.getInstance().getCurrentMyActivity());
    }

    private void start(final String str, AudioPlayerListener audioPlayerListener) {
        IMediaPlayer iMediaPlayer;
        if (!TextUtils.isEmpty(this.playPath) && this.playPath.equals(str) && (iMediaPlayer = this.mPlayer) != null) {
            try {
                iMediaPlayer.start();
            } catch (Exception e2) {
                JLog.e(this.TAG, e2.getMessage());
            }
        } else {
            IMediaPlayer iMediaPlayer2 = this.mPlayer;
            if (iMediaPlayer2 != null) {
                iMediaPlayer2.reset();
                this.mPlayer.release();
            }
            initIjkPlayer();
            IPlayerControl.PlayerOptions playerOptions = new IPlayerControl.PlayerOptions(false);
            playerOptions.setVolume(1.0f);
            this.mPlayer = MediaPlayerHelper.createPlayer(playerOptions);
            Context context = (Context) BaseFrameUtil.getInstance().getCurrentMyActivity();
            if (context != null) {
                ((AudioManager) context.getSystemService("audio")).requestAudioFocus(null, 3, 1);
            }
            Uri parse = Uri.parse(str);
            try {
                if (Build.VERSION.SDK_INT >= 14) {
                    this.mPlayer.setDataSource(context, parse, null);
                } else {
                    this.mPlayer.setDataSource(parse.toString());
                }
                this.mPlayer.setOnCompletionListener(new IMediaPlayer.OnCompletionListener() { // from class: com.jingdong.common.jdreactFramework.utils.audio.ReactAudioPlayerUtil.1
                    @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnCompletionListener
                    public void onCompletion(IMediaPlayer iMediaPlayer3) {
                        if (ReactAudioPlayerUtil.this.mListener != null) {
                            ReactAudioPlayerUtil.this.mListener.onComplete();
                        }
                    }
                });
                this.mPlayer.prepareAsync();
                this.mPlayer.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() { // from class: com.jingdong.common.jdreactFramework.utils.audio.ReactAudioPlayerUtil.2
                    @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener
                    public void onPrepared(IMediaPlayer iMediaPlayer3) {
                        if (ReactAudioPlayerUtil.this.mPlayer != null) {
                            ReactAudioPlayerUtil.this.mPlayer.start();
                            ReactAudioPlayerUtil.this.playPath = str;
                        }
                    }
                });
            } catch (Exception e3) {
                JLog.e(this.TAG, e3.getMessage());
                ToastUtils.shortToast("play fail");
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
            Context context = (Context) BaseFrameUtil.getInstance().getCurrentMyActivity();
            if (context != null) {
                ((AudioManager) context.getSystemService("audio")).abandonAudioFocus(null);
            }
        } catch (Exception e2) {
            JLog.e(this.TAG, e2.getMessage());
        }
    }

    public void pause() {
        try {
            if (this.mPlayer.isPlaying()) {
                this.mPlayer.pause();
                this.isPaused = true;
            }
        } catch (Exception e2) {
            JLog.e(this.TAG, e2.getMessage());
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
            Context context = (Context) BaseFrameUtil.getInstance().getCurrentMyActivity();
            if (context != null) {
                ((AudioManager) context.getSystemService("audio")).abandonAudioFocus(null);
            }
        } catch (Exception e2) {
            JLog.e(this.TAG, e2.getMessage());
        }
    }

    public void play(String str, AudioPlayerListener audioPlayerListener) {
        start(str, audioPlayerListener);
    }
}
