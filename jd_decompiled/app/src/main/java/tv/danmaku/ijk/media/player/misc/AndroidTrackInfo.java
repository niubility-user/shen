package tv.danmaku.ijk.media.player.misc;

import android.annotation.TargetApi;
import android.media.MediaFormat;
import android.media.MediaPlayer;
import android.os.Build;
import com.jd.dynamic.DYConstants;

/* loaded from: classes11.dex */
public class AndroidTrackInfo implements ITrackInfo {
    private final MediaPlayer.TrackInfo mTrackInfo;

    private AndroidTrackInfo(MediaPlayer.TrackInfo trackInfo) {
        this.mTrackInfo = trackInfo;
    }

    public static AndroidTrackInfo[] fromMediaPlayer(MediaPlayer mediaPlayer) {
        if (Build.VERSION.SDK_INT >= 16) {
            return fromTrackInfo(mediaPlayer.getTrackInfo());
        }
        return null;
    }

    private static AndroidTrackInfo[] fromTrackInfo(MediaPlayer.TrackInfo[] trackInfoArr) {
        if (trackInfoArr == null) {
            return null;
        }
        AndroidTrackInfo[] androidTrackInfoArr = new AndroidTrackInfo[trackInfoArr.length];
        for (int i2 = 0; i2 < trackInfoArr.length; i2++) {
            androidTrackInfoArr[i2] = new AndroidTrackInfo(trackInfoArr[i2]);
        }
        return androidTrackInfoArr;
    }

    @Override // tv.danmaku.ijk.media.player.misc.ITrackInfo
    @TargetApi(19)
    public IMediaFormat getFormat() {
        MediaFormat format;
        MediaPlayer.TrackInfo trackInfo = this.mTrackInfo;
        if (trackInfo == null || Build.VERSION.SDK_INT < 19 || (format = trackInfo.getFormat()) == null) {
            return null;
        }
        return new AndroidMediaFormat(format);
    }

    @Override // tv.danmaku.ijk.media.player.misc.ITrackInfo
    @TargetApi(16)
    public String getInfoInline() {
        MediaPlayer.TrackInfo trackInfo = this.mTrackInfo;
        return trackInfo != null ? trackInfo.toString() : DYConstants.DY_NULL_STR;
    }

    @Override // tv.danmaku.ijk.media.player.misc.ITrackInfo
    @TargetApi(16)
    public String getLanguage() {
        MediaPlayer.TrackInfo trackInfo = this.mTrackInfo;
        return trackInfo == null ? "und" : trackInfo.getLanguage();
    }

    @Override // tv.danmaku.ijk.media.player.misc.ITrackInfo
    @TargetApi(16)
    public int getTrackType() {
        MediaPlayer.TrackInfo trackInfo = this.mTrackInfo;
        if (trackInfo == null) {
            return 0;
        }
        return trackInfo.getTrackType();
    }

    @TargetApi(16)
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(getClass().getSimpleName());
        sb.append('{');
        MediaPlayer.TrackInfo trackInfo = this.mTrackInfo;
        if (trackInfo != null) {
            sb.append(trackInfo.toString());
        } else {
            sb.append(DYConstants.DY_NULL_STR);
        }
        sb.append('}');
        return sb.toString();
    }
}
