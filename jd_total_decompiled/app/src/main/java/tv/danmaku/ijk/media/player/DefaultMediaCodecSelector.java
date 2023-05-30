package tv.danmaku.ijk.media.player;

import android.annotation.TargetApi;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.Build;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes11.dex */
public class DefaultMediaCodecSelector implements IMediaPlayer.OnMediaCodecSelectListener {
    @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnMediaCodecSelectListener
    @TargetApi(16)
    public String onMediaCodecSelect(IMediaPlayer iMediaPlayer, String str, int i2, int i3) {
        String[] supportedTypes;
        IjkMediaCodecInfo ijkMediaCodecInfo;
        if (Build.VERSION.SDK_INT >= 16 && !TextUtils.isEmpty(str)) {
            ArrayList arrayList = new ArrayList();
            int codecCount = MediaCodecList.getCodecCount();
            for (int i4 = 0; i4 < codecCount; i4++) {
                MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i4);
                if (!codecInfoAt.isEncoder() && (supportedTypes = codecInfoAt.getSupportedTypes()) != null) {
                    for (String str2 : supportedTypes) {
                        if (!TextUtils.isEmpty(str2) && str2.equalsIgnoreCase(str) && (ijkMediaCodecInfo = IjkMediaCodecInfo.setupCandidate(codecInfoAt, str)) != null) {
                            arrayList.add(ijkMediaCodecInfo);
                            ijkMediaCodecInfo.dumpProfileLevels(str);
                        }
                    }
                }
            }
            if (arrayList.isEmpty()) {
                return null;
            }
            IjkMediaCodecInfo ijkMediaCodecInfo2 = (IjkMediaCodecInfo) arrayList.get(0);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                IjkMediaCodecInfo ijkMediaCodecInfo3 = (IjkMediaCodecInfo) it.next();
                if (ijkMediaCodecInfo3.mRank > ijkMediaCodecInfo2.mRank) {
                    ijkMediaCodecInfo2 = ijkMediaCodecInfo3;
                }
            }
            if (ijkMediaCodecInfo2.mRank < 600) {
                return null;
            }
            return ijkMediaCodecInfo2.mCodecInfo.getName();
        }
        return null;
    }
}
