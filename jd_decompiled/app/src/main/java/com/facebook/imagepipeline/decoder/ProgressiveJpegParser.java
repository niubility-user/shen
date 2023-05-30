package com.facebook.imagepipeline.decoder;

import com.facebook.common.internal.Closeables;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Throwables;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.PooledByteArrayBufferedInputStream;
import com.facebook.common.util.StreamUtil;
import com.facebook.imagepipeline.image.EncodedImage;
import java.io.IOException;

/* loaded from: classes.dex */
public class ProgressiveJpegParser {
    private static final int BUFFER_SIZE = 16384;
    private static final int NOT_A_JPEG = 6;
    private static final int READ_FIRST_JPEG_BYTE = 0;
    private static final int READ_MARKER_FIRST_BYTE_OR_ENTROPY_DATA = 2;
    private static final int READ_MARKER_SECOND_BYTE = 3;
    private static final int READ_SECOND_JPEG_BYTE = 1;
    private static final int READ_SIZE_FIRST_BYTE = 4;
    private static final int READ_SIZE_SECOND_BYTE = 5;
    private final ByteArrayPool mByteArrayPool;
    private boolean mEndMarkerRead;
    private int mBytesParsed = 0;
    private int mLastByteRead = 0;
    private int mNextFullScanNumber = 0;
    private int mBestScanEndOffset = 0;
    private int mBestScanNumber = 0;
    private int mParserState = 0;

    public ProgressiveJpegParser(ByteArrayPool byteArrayPool) {
        this.mByteArrayPool = (ByteArrayPool) Preconditions.checkNotNull(byteArrayPool);
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x0076, code lost:
        if (r4 == 216) goto L44;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean doParseMoreData(java.io.InputStream r12) {
        /*
            r11 = this;
            int r0 = r11.mBestScanNumber
        L2:
            r1 = 0
            r2 = 6
            r3 = 1
            int r4 = r11.mParserState     // Catch: java.io.IOException -> L84
            if (r4 == r2) goto L88
            int r4 = r12.read()     // Catch: java.io.IOException -> L84
            r5 = -1
            if (r4 == r5) goto L88
            int r5 = r11.mBytesParsed     // Catch: java.io.IOException -> L84
            int r5 = r5 + r3
            r11.mBytesParsed = r5     // Catch: java.io.IOException -> L84
            boolean r6 = r11.mEndMarkerRead     // Catch: java.io.IOException -> L84
            if (r6 == 0) goto L1e
            r11.mParserState = r2     // Catch: java.io.IOException -> L84
            r11.mEndMarkerRead = r1     // Catch: java.io.IOException -> L84
            return r1
        L1e:
            int r6 = r11.mParserState     // Catch: java.io.IOException -> L84
            r7 = 255(0xff, float:3.57E-43)
            if (r6 == 0) goto L79
            r8 = 2
            if (r6 == r3) goto L74
            r9 = 3
            if (r6 == r8) goto L6f
            r10 = 4
            if (r6 == r9) goto L49
            r5 = 5
            if (r6 == r10) goto L46
            if (r6 == r5) goto L36
            com.facebook.common.internal.Preconditions.checkState(r1)     // Catch: java.io.IOException -> L84
            goto L80
        L36:
            int r5 = r11.mLastByteRead     // Catch: java.io.IOException -> L84
            int r5 = r5 << 8
            int r5 = r5 + r4
            int r5 = r5 - r8
            long r6 = (long) r5     // Catch: java.io.IOException -> L84
            com.facebook.common.util.StreamUtil.skip(r12, r6)     // Catch: java.io.IOException -> L84
            int r6 = r11.mBytesParsed     // Catch: java.io.IOException -> L84
            int r6 = r6 + r5
            r11.mBytesParsed = r6     // Catch: java.io.IOException -> L84
            goto L5a
        L46:
            r11.mParserState = r5     // Catch: java.io.IOException -> L84
            goto L80
        L49:
            if (r4 != r7) goto L4c
            goto L71
        L4c:
            if (r4 != 0) goto L4f
            goto L78
        L4f:
            r6 = 217(0xd9, float:3.04E-43)
            if (r4 != r6) goto L5d
            r11.mEndMarkerRead = r3     // Catch: java.io.IOException -> L84
            int r5 = r5 + (-2)
            r11.newScanOrImageEndFound(r5)     // Catch: java.io.IOException -> L84
        L5a:
            r11.mParserState = r8     // Catch: java.io.IOException -> L84
            goto L80
        L5d:
            r6 = 218(0xda, float:3.05E-43)
            if (r4 != r6) goto L66
            int r5 = r5 + (-2)
            r11.newScanOrImageEndFound(r5)     // Catch: java.io.IOException -> L84
        L66:
            boolean r5 = doesMarkerStartSegment(r4)     // Catch: java.io.IOException -> L84
            if (r5 == 0) goto L5a
            r11.mParserState = r10     // Catch: java.io.IOException -> L84
            goto L80
        L6f:
            if (r4 != r7) goto L80
        L71:
            r11.mParserState = r9     // Catch: java.io.IOException -> L84
            goto L80
        L74:
            r5 = 216(0xd8, float:3.03E-43)
            if (r4 != r5) goto L7e
        L78:
            goto L5a
        L79:
            if (r4 != r7) goto L7e
            r11.mParserState = r3     // Catch: java.io.IOException -> L84
            goto L80
        L7e:
            r11.mParserState = r2     // Catch: java.io.IOException -> L84
        L80:
            r11.mLastByteRead = r4     // Catch: java.io.IOException -> L84
            goto L2
        L84:
            r12 = move-exception
            com.facebook.common.internal.Throwables.propagate(r12)
        L88:
            int r12 = r11.mParserState
            if (r12 == r2) goto L91
            int r12 = r11.mBestScanNumber
            if (r12 == r0) goto L91
            r1 = 1
        L91:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.decoder.ProgressiveJpegParser.doParseMoreData(java.io.InputStream):boolean");
    }

    private static boolean doesMarkerStartSegment(int i2) {
        if (i2 == 1) {
            return false;
        }
        return ((i2 >= 208 && i2 <= 215) || i2 == 217 || i2 == 216) ? false : true;
    }

    private void newScanOrImageEndFound(int i2) {
        int i3 = this.mNextFullScanNumber;
        if (i3 > 0) {
            this.mBestScanEndOffset = i2;
        }
        this.mNextFullScanNumber = i3 + 1;
        this.mBestScanNumber = i3;
    }

    public int getBestScanEndOffset() {
        return this.mBestScanEndOffset;
    }

    public int getBestScanNumber() {
        return this.mBestScanNumber;
    }

    public boolean isEndMarkerRead() {
        return this.mEndMarkerRead;
    }

    public boolean isJpeg() {
        return this.mBytesParsed > 1 && this.mParserState != 6;
    }

    public boolean parseMoreData(EncodedImage encodedImage) {
        if (this.mParserState != 6 && encodedImage.getSize() > this.mBytesParsed) {
            PooledByteArrayBufferedInputStream pooledByteArrayBufferedInputStream = new PooledByteArrayBufferedInputStream(encodedImage.getInputStream(), this.mByteArrayPool.get(16384), this.mByteArrayPool);
            try {
                StreamUtil.skip(pooledByteArrayBufferedInputStream, this.mBytesParsed);
                return doParseMoreData(pooledByteArrayBufferedInputStream);
            } catch (IOException e2) {
                Throwables.propagate(e2);
                return false;
            } finally {
                Closeables.closeQuietly(pooledByteArrayBufferedInputStream);
            }
        }
        return false;
    }
}
