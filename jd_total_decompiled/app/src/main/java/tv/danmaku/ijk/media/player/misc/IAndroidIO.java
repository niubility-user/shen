package tv.danmaku.ijk.media.player.misc;

import java.io.IOException;

/* loaded from: classes11.dex */
public interface IAndroidIO {
    int close() throws IOException;

    int open(String str) throws IOException;

    int read(byte[] bArr, int i2) throws IOException;

    long seek(long j2, int i2) throws IOException;
}
