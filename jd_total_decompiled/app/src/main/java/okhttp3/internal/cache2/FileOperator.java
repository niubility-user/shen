package okhttp3.internal.cache2;

import java.io.IOException;
import java.nio.channels.FileChannel;
import okio.Buffer;

/* loaded from: classes11.dex */
final class FileOperator {
    private final FileChannel fileChannel;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FileOperator(FileChannel fileChannel) {
        this.fileChannel = fileChannel;
    }

    public void read(long j2, Buffer buffer, long j3) throws IOException {
        if (j3 < 0) {
            throw new IndexOutOfBoundsException();
        }
        while (j3 > 0) {
            long transferTo = this.fileChannel.transferTo(j2, j3, buffer);
            j2 += transferTo;
            j3 -= transferTo;
        }
    }

    public void write(long j2, Buffer buffer, long j3) throws IOException {
        if (j3 < 0 || j3 > buffer.size()) {
            throw new IndexOutOfBoundsException();
        }
        long j4 = j2;
        long j5 = j3;
        while (j5 > 0) {
            long transferFrom = this.fileChannel.transferFrom(buffer, j4, j5);
            j4 += transferFrom;
            j5 -= transferFrom;
        }
    }
}
