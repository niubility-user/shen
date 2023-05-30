package com.jingdong.common.jdreactFramework.modules.community.upload;

import android.media.MediaMetadataRetriever;
import android.text.TextUtils;
import cn.com.union.fido.common.MIMEType;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.XView2.strategy.preDownLoadManager.PreDownLoadManager;
import com.jingdong.common.utils.StringUtil;
import java.io.File;
import tv.danmaku.ijk.media.JDPlayerConstant;
import tv.danmaku.ijk.media.alpha.util.MediaUtil;
import tv.danmaku.ijk.media.utils.MediaInfoUtil;

/* loaded from: classes5.dex */
public final class PictureMimeType {
    public static String createImageType(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return MIMEType.MIME_TYPE_JPEG;
            }
            String name = new File(str).getName();
            return "image/" + name.substring(name.lastIndexOf(OrderISVUtil.MONEY_DECIMAL) + 1, name.length());
        } catch (Exception e2) {
            e2.printStackTrace();
            return MIMEType.MIME_TYPE_JPEG;
        }
    }

    public static String createVideoType(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return JDPlayerConstant.IJK_CACHE_SUPPORT_TYPE;
            }
            String name = new File(str).getName();
            return MediaUtil.TRACK_VIDEO + name.substring(name.lastIndexOf(OrderISVUtil.MONEY_DECIMAL) + 1, name.length());
        } catch (Exception e2) {
            e2.printStackTrace();
            return JDPlayerConstant.IJK_CACHE_SUPPORT_TYPE;
        }
    }

    public static boolean filterVideo(String str) {
        return !StringUtil.isEmpty(str) && str.endsWith(".avi");
    }

    public static int getLocalVideoDuration(String str) {
        try {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(str);
            return Integer.parseInt(mediaMetadataRetriever.extractMetadata(9));
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static boolean isVideo(String str) {
        if (StringUtil.isEmpty(str)) {
            return false;
        }
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1664118616:
                if (str.equals(MediaInfoUtil.AMIME_VIDEO_H264)) {
                    c2 = 0;
                    break;
                }
                break;
            case -1662382439:
                if (str.equals(MIMEType.MIME_TYPE_MPEG)) {
                    c2 = 1;
                    break;
                }
                break;
            case -1662235845:
                if (str.equals("video/rmvb")) {
                    c2 = 2;
                    break;
                }
                break;
            case -1662095187:
                if (str.equals("video/webm")) {
                    c2 = 3;
                    break;
                }
                break;
            case 1331792072:
                if (str.equals("video/3gp")) {
                    c2 = 4;
                    break;
                }
                break;
            case 1331841244:
                if (str.equals("video/flv")) {
                    c2 = 5;
                    break;
                }
                break;
            case 1331846235:
                if (str.equals("video/m4v")) {
                    c2 = 6;
                    break;
                }
                break;
            case 1331847940:
                if (str.equals("video/mkv")) {
                    c2 = 7;
                    break;
                }
                break;
            case 1331848029:
                if (str.equals(JDPlayerConstant.IJK_CACHE_SUPPORT_TYPE)) {
                    c2 = '\b';
                    break;
                }
                break;
            case 1331848064:
                if (str.equals("video/mov")) {
                    c2 = '\t';
                    break;
                }
                break;
            case 1331848080:
                if (str.equals("video/mpg")) {
                    c2 = '\n';
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case '\b':
            case '\t':
            case '\n':
                return true;
            default:
                return false;
        }
    }

    public static boolean isVideo(File file) {
        if (file == null) {
            return false;
        }
        String name = file.getName();
        if (StringUtil.isEmpty(name)) {
            return false;
        }
        return name.endsWith(PreDownLoadManager.VIDEO_SKU_SUFFIX) || name.endsWith(".3gp") || name.endsWith(".3gpp") || name.endsWith(".mov") || name.endsWith(".mkv") || name.endsWith(".flv") || name.endsWith(".m4v") || name.endsWith(".webm") || name.endsWith(".mpg") || ".mpeg".equals(name) || ".rmvb".equals(name);
    }
}
