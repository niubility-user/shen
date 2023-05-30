package com.jingdong.common.unification.video;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.text.TextUtils;
import com.jingdong.common.UnLog;
import java.io.File;

/* loaded from: classes6.dex */
public class VideoInfoUtil {
    private static final String TAG = "VideoInfoUtil";
    private long fileLength;
    private boolean isSetDataSourceSuccess;
    private MediaMetadataRetriever mMetadataRetriever;

    public VideoInfoUtil(String str) {
        this.fileLength = 0L;
        this.isSetDataSourceSuccess = true;
        File file = new File(str);
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        this.mMetadataRetriever = mediaMetadataRetriever;
        try {
            mediaMetadataRetriever.setDataSource(file.getAbsolutePath());
        } catch (Exception e2) {
            if (UnLog.E) {
                UnLog.e(TAG, e2.toString());
            }
            this.isSetDataSourceSuccess = false;
        }
        if (this.isSetDataSourceSuccess) {
            String videoLength = getVideoLength();
            this.fileLength = TextUtils.isEmpty(videoLength) ? 0L : Long.valueOf(videoLength).longValue();
        }
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, int i2, int i3) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float max = Math.max(i2 / width, i3 / height);
        Matrix matrix = new Matrix();
        matrix.postScale(max, max);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
    }

    public Bitmap extractFrame() {
        return this.mMetadataRetriever.getFrameAtTime();
    }

    public int getVideoDegree() {
        if (Build.VERSION.SDK_INT >= 17) {
            String extractMetadata = this.mMetadataRetriever.extractMetadata(24);
            if (!TextUtils.isEmpty(extractMetadata)) {
                return Integer.valueOf(extractMetadata).intValue();
            }
        }
        return 0;
    }

    public int getVideoHeight() {
        String extractMetadata = this.mMetadataRetriever.extractMetadata(19);
        if (TextUtils.isEmpty(extractMetadata)) {
            return -1;
        }
        return Integer.valueOf(extractMetadata).intValue();
    }

    public String getVideoLength() {
        return this.mMetadataRetriever.extractMetadata(9);
    }

    public int getVideoWidth() {
        String extractMetadata = this.mMetadataRetriever.extractMetadata(18);
        if (TextUtils.isEmpty(extractMetadata)) {
            return -1;
        }
        return Integer.valueOf(extractMetadata).intValue();
    }

    public boolean isSetDataSourceSuccess() {
        return this.isSetDataSourceSuccess;
    }

    public void release() {
        MediaMetadataRetriever mediaMetadataRetriever = this.mMetadataRetriever;
        if (mediaMetadataRetriever != null) {
            mediaMetadataRetriever.release();
        }
    }

    public Bitmap extractFrame(long j2) {
        Bitmap bitmap = null;
        while (j2 < this.fileLength && (bitmap = this.mMetadataRetriever.getFrameAtTime(j2 * 1000, 2)) == null) {
            j2 += 1000;
        }
        return bitmap;
    }
}
