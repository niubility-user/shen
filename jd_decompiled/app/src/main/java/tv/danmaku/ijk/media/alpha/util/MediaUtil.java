package tv.danmaku.ijk.media.alpha.util;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;
import tv.danmaku.ijk.media.alpha.file.IFileContainer;
import tv.danmaku.ijk.media.player.misc.IMediaFormat;
import tv.danmaku.ijk.media.utils.MediaInfoUtil;

/* loaded from: classes11.dex */
public class MediaUtil {
    public static final MediaUtil INSTANCE = new MediaUtil();
    private static final String TAG = "MediaUtil";
    public static final String TRACK_AUDIO = "audio/";
    public static final String TRACK_VIDEO = "video/";
    private boolean isTypeMapInit = false;
    private Map<String, Boolean> supportTypeMap = new HashMap();

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface TrackType {
    }

    private MediaUtil() {
    }

    private void getSupportType() {
        int codecCount = MediaCodecList.getCodecCount();
        for (int i2 = 0; i2 < codecCount; i2++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i2);
            if (codecInfoAt.isEncoder()) {
                for (String str : codecInfoAt.getSupportedTypes()) {
                    this.supportTypeMap.put(str.toLowerCase(), Boolean.TRUE);
                }
            }
        }
    }

    public boolean checkIsHevc(MediaFormat mediaFormat) {
        return mediaFormat.getString(IMediaFormat.KEY_MIME).contains("hevc");
    }

    public boolean checkSupportCodec(String str) {
        if (!this.isTypeMapInit) {
            this.isTypeMapInit = true;
            getSupportType();
        }
        return this.supportTypeMap.containsKey(str.toLowerCase());
    }

    public MediaExtractor getExtractor(IFileContainer iFileContainer) throws IOException {
        MediaExtractor mediaExtractor = new MediaExtractor();
        iFileContainer.setDataSource(mediaExtractor);
        return mediaExtractor;
    }

    public boolean isDeviceSupportHevc() {
        return checkSupportCodec(MediaInfoUtil.AMIME_VIDEO_HEVC);
    }

    public int selectTrack(MediaExtractor mediaExtractor, String str) {
        int trackCount = mediaExtractor.getTrackCount();
        for (int i2 = 0; i2 < trackCount; i2++) {
            String string = mediaExtractor.getTrackFormat(i2).getString(IMediaFormat.KEY_MIME);
            if (string != null && string.startsWith(str)) {
                return i2;
            }
        }
        return -1;
    }
}
