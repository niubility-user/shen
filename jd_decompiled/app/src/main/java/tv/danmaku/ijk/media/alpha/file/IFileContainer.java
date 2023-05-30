package tv.danmaku.ijk.media.alpha.file;

import android.media.MediaExtractor;
import java.io.FileNotFoundException;
import java.io.IOException;

/* loaded from: classes11.dex */
public interface IFileContainer {
    void close() throws IOException;

    void closeRandomRead() throws IOException;

    int read(byte[] bArr, int i2, int i3) throws IOException;

    void seek(int i2) throws IOException;

    void setDataSource(MediaExtractor mediaExtractor) throws IOException;

    void skip(int i2) throws IOException;

    void startRandomRead() throws FileNotFoundException;
}
