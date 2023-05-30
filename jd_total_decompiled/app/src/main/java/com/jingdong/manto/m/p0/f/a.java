package com.jingdong.manto.m.p0.f;

import android.media.MediaMetadataRetriever;
import android.net.Uri;
import com.jingdong.manto.utils.MantoLog;

/* loaded from: classes15.dex */
public class a {
    public static int a(String str) {
        try {
            Uri parse = Uri.parse("file:///" + str);
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(com.jingdong.manto.c.a(), parse);
            return Integer.parseInt(mediaMetadataRetriever.extractMetadata(9));
        } catch (Throwable th) {
            MantoLog.e("Audio.Util", "err:", th);
            return 0;
        }
    }
}
