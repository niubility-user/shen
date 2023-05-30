package com.facebook.animated.giflite.decoder;

import androidx.core.view.InputDeviceCompat;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class GifMetadataDecoder {
    private static final int CONTROL_INDEX_DELAY = 1;
    private static final int CONTROL_INDEX_DISPOSE = 0;
    private static final int DEFAULT_FRAME_DURATION_MS = 100;
    private static final int MAX_BLOCK_SIZE = 256;
    private static final char[] NETSCAPE = {'N', 'E', 'T', 'S', 'C', 'A', 'P', 'E', '2', OrderISVUtil.MONEY_DECIMAL_CHAR, '0'};
    private final InputStream mInputStream;
    @Nullable
    private final OutputStream mOutputStream;
    private boolean shouldFixStream;
    private final byte[] block = new byte[256];
    private final List<int[]> mFrameControls = new ArrayList();
    private int mLoopCount = 1;
    private boolean mDecoded = false;
    private int mCurrentOffset = 0;

    private GifMetadataDecoder(InputStream inputStream, @Nullable OutputStream outputStream) {
        this.mInputStream = inputStream;
        this.mOutputStream = outputStream;
    }

    private void addFrame(int[] iArr) {
        this.mFrameControls.add(Arrays.copyOf(iArr, iArr.length));
    }

    private void copyFromIsToOs(InputStream inputStream, OutputStream outputStream, int i2) {
        while (i2 > 0) {
            int read = inputStream.read(this.block, 0, Math.min(256, i2));
            i2 += InputDeviceCompat.SOURCE_ANY;
            outputStream.write(this.block, 0, read);
        }
    }

    public static GifMetadataDecoder create(InputStream inputStream, @Nullable OutputStream outputStream) {
        GifMetadataDecoder gifMetadataDecoder = new GifMetadataDecoder(inputStream, outputStream);
        gifMetadataDecoder.decode();
        return gifMetadataDecoder;
    }

    private void ignoreColorTable(int i2) {
        skipAndWriteBytes(i2 * 3);
    }

    private void initFixedOutputStream() {
        if (this.shouldFixStream || this.mOutputStream == null) {
            return;
        }
        this.shouldFixStream = true;
        this.mInputStream.reset();
        copyFromIsToOs(this.mInputStream, this.mOutputStream, this.mCurrentOffset - 2);
        this.mInputStream.skip(2L);
    }

    private boolean isNetscape() {
        int length = this.block.length;
        char[] cArr = NETSCAPE;
        if (length < cArr.length) {
            return false;
        }
        int length2 = cArr.length;
        for (int i2 = 0; i2 < length2; i2++) {
            if (NETSCAPE[i2] != ((char) this.block[i2])) {
                return false;
            }
        }
        return true;
    }

    private int readAndWriteNextByte() {
        int readNextByte = readNextByte();
        writeNextByte(readNextByte);
        return readNextByte;
    }

    private int readBlock() {
        int readAndWriteNextByte = readAndWriteNextByte();
        int i2 = 0;
        if (readAndWriteNextByte > 0) {
            while (i2 < readAndWriteNextByte) {
                i2 += readIntoBlock(i2, readAndWriteNextByte - i2);
            }
        }
        return i2;
    }

    private void readGifInfo() {
        validateAndIgnoreHeader();
        int[] iArr = {0, 0};
        boolean z = false;
        while (!z) {
            int readAndWriteNextByte = readAndWriteNextByte();
            if (readAndWriteNextByte == 33) {
                int readAndWriteNextByte2 = readAndWriteNextByte();
                if (readAndWriteNextByte2 == 1) {
                    addFrame(iArr);
                } else if (readAndWriteNextByte2 == 249) {
                    readGraphicsControlExtension(iArr);
                } else if (readAndWriteNextByte2 == 255) {
                    readBlock();
                    if (isNetscape()) {
                        readNetscapeExtension();
                    }
                }
                skipExtension();
            } else if (readAndWriteNextByte == 44) {
                addFrame(iArr);
                skipImage();
            } else if (readAndWriteNextByte != 59) {
                throw new IOException("Unknown block header [" + Integer.toHexString(readAndWriteNextByte) + "]");
            } else {
                z = true;
            }
        }
    }

    private void readGraphicsControlExtension(int[] iArr) {
        skipAndWriteBytes(1);
        iArr[0] = (readAndWriteNextByte() & 28) >> 2;
        iArr[1] = readTwoByteInt() * 10;
        if (iArr[1] == 0) {
            iArr[1] = 100;
            initFixedOutputStream();
        }
        writeTwoByteInt(iArr[1] / 10);
        skipAndWriteBytes(2);
    }

    private int readIntoBlock(int i2, int i3) {
        int read = this.mInputStream.read(this.block, i2, i3);
        this.mCurrentOffset += i3;
        if (this.shouldFixStream) {
            this.mOutputStream.write(this.block, i2, i3);
        }
        if (read != -1) {
            return read;
        }
        throw new EOFException("Unexpected end of gif file");
    }

    private void readNetscapeExtension() {
        int readBlock;
        do {
            readBlock = readBlock();
            byte[] bArr = this.block;
            if (bArr[0] == 1) {
                this.mLoopCount = (bArr[1] & 255) | ((bArr[2] & 255) << 8);
            }
        } while (readBlock > 0);
    }

    private int readNextByte() {
        int read = this.mInputStream.read();
        this.mCurrentOffset++;
        if (read != -1) {
            return read;
        }
        throw new EOFException("Unexpected end of gif file");
    }

    private int readTwoByteInt() {
        return readNextByte() | (readNextByte() << 8);
    }

    private void skipAndWriteBytes(int i2) {
        if (this.shouldFixStream) {
            copyFromIsToOs(this.mInputStream, this.mOutputStream, i2);
        } else {
            this.mInputStream.skip(i2);
        }
        this.mCurrentOffset += i2;
    }

    private void skipExtension() {
        do {
        } while (readBlock() > 0);
    }

    private void skipImage() {
        skipAndWriteBytes(8);
        int readAndWriteNextByte = readAndWriteNextByte();
        if ((readAndWriteNextByte & 128) != 0) {
            ignoreColorTable(2 << (readAndWriteNextByte & 7));
        }
        skipAndWriteBytes(1);
        skipExtension();
    }

    private void validateAndIgnoreHeader() {
        readIntoBlock(0, 6);
        byte[] bArr = this.block;
        if (!('G' == ((char) bArr[0]) && 'I' == ((char) bArr[1]) && 'F' == ((char) bArr[2]) && '8' == ((char) bArr[3]) && ('7' == ((char) bArr[4]) || '9' == ((char) bArr[4])) && 'a' == ((char) bArr[5]))) {
            throw new IOException("Illegal header for gif");
        }
        skipAndWriteBytes(4);
        int readAndWriteNextByte = readAndWriteNextByte();
        boolean z = (readAndWriteNextByte & 128) != 0;
        int i2 = 2 << (readAndWriteNextByte & 7);
        skipAndWriteBytes(2);
        if (z) {
            ignoreColorTable(i2);
        }
    }

    private void writeNextByte(int i2) {
        if (this.shouldFixStream) {
            this.mOutputStream.write(i2);
        }
    }

    private void writeTwoByteInt(int i2) {
        writeNextByte(i2);
        writeNextByte(i2 >> 8);
    }

    public void decode() {
        if (this.mDecoded) {
            throw new IllegalStateException("decode called multiple times");
        }
        this.mDecoded = true;
        readGifInfo();
    }

    public int getFrameCount() {
        if (this.mDecoded) {
            return this.mFrameControls.size();
        }
        throw new IllegalStateException("getFrameCount called before decode");
    }

    public int getFrameDisposal(int i2) {
        if (this.mDecoded) {
            return this.mFrameControls.get(i2)[0];
        }
        throw new IllegalStateException("getFrameDisposal called before decode");
    }

    public int getFrameDurationMs(int i2) {
        if (this.mDecoded) {
            if (i2 >= getFrameCount()) {
                return 1;
            }
            return this.mFrameControls.get(i2)[1];
        }
        throw new IllegalStateException("getFrameDurationMs called before decode");
    }

    public int getLoopCount() {
        if (this.mDecoded) {
            return this.mLoopCount;
        }
        throw new IllegalStateException("getLoopCount called before decode");
    }
}
