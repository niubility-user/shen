package okio;

import javax.annotation.Nullable;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class SegmentPool {
    static final long MAX_SIZE = 65536;
    static long byteCount;
    @Nullable
    static Segment next;

    private SegmentPool() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void recycle(Segment segment) {
        if (segment.next == null && segment.prev == null) {
            if (segment.shared) {
                return;
            }
            synchronized (SegmentPool.class) {
                long j2 = byteCount;
                if (j2 + IjkMediaMeta.AV_CH_TOP_FRONT_CENTER > 65536) {
                    return;
                }
                byteCount = j2 + IjkMediaMeta.AV_CH_TOP_FRONT_CENTER;
                segment.next = next;
                segment.limit = 0;
                segment.pos = 0;
                next = segment;
                return;
            }
        }
        throw new IllegalArgumentException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Segment take() {
        synchronized (SegmentPool.class) {
            Segment segment = next;
            if (segment != null) {
                next = segment.next;
                segment.next = null;
                byteCount -= IjkMediaMeta.AV_CH_TOP_FRONT_CENTER;
                return segment;
            }
            return new Segment();
        }
    }
}
