package tv.danmaku.ijk.media.alpha.file;

import android.media.MediaExtractor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* loaded from: classes11.dex */
public class FileContainer implements IFileContainer {
    private final File file;
    private RandomAccessFile randomAccessFile;

    public FileContainer(File file) throws FileNotFoundException {
        this.file = file;
        if (file != null) {
            if (file.exists() && file.isFile() && file.canRead()) {
                return;
            }
            throw new FileNotFoundException("Unable to read " + file);
        }
        throw new IllegalArgumentException("File can not be NULL");
    }

    @Override // tv.danmaku.ijk.media.alpha.file.IFileContainer
    public void close() {
    }

    @Override // tv.danmaku.ijk.media.alpha.file.IFileContainer
    public void closeRandomRead() throws IOException {
        RandomAccessFile randomAccessFile = this.randomAccessFile;
        if (randomAccessFile == null) {
            return;
        }
        randomAccessFile.close();
    }

    @Override // tv.danmaku.ijk.media.alpha.file.IFileContainer
    public int read(byte[] bArr, int i2, int i3) throws IOException {
        RandomAccessFile randomAccessFile = this.randomAccessFile;
        if (randomAccessFile == null) {
            return -1;
        }
        return randomAccessFile.read(bArr, i2, i3);
    }

    @Override // tv.danmaku.ijk.media.alpha.file.IFileContainer
    public void seek(int i2) throws IOException {
        RandomAccessFile randomAccessFile = this.randomAccessFile;
        if (randomAccessFile == null) {
            return;
        }
        randomAccessFile.seek(i2);
    }

    @Override // tv.danmaku.ijk.media.alpha.file.IFileContainer
    public void setDataSource(MediaExtractor mediaExtractor) throws IOException {
        mediaExtractor.setDataSource(this.file.toString());
    }

    @Override // tv.danmaku.ijk.media.alpha.file.IFileContainer
    public void skip(int i2) throws IOException {
        RandomAccessFile randomAccessFile = this.randomAccessFile;
        if (randomAccessFile == null) {
            return;
        }
        randomAccessFile.skipBytes(i2);
    }

    @Override // tv.danmaku.ijk.media.alpha.file.IFileContainer
    public void startRandomRead() throws FileNotFoundException {
        this.randomAccessFile = new RandomAccessFile(this.file, "r");
    }
}
