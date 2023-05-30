package com.facebook.imagepipeline.bitmaps;

import cn.com.union.fido.bean.uafclient.ErrorCode;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteBufferOutputStream;
import com.facebook.common.references.CloseableReference;
import java.io.IOException;
import jd.wjlogin_sdk.util.ReplyCode;

/* loaded from: classes.dex */
public class EmptyJpegGenerator {
    private static final byte[] EMPTY_JPEG_PREFIX = {-1, -40, -1, -37, 0, 67, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -64, 0, 17, 8};
    private static final byte[] EMPTY_JPEG_SUFFIX = {3, 1, ReplyCode.reply0x22, 0, 2, 17, 0, 3, 17, 0, -1, -60, 0, 31, 0, 0, 1, 5, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, -1, -60, 0, ReplyCode.reply0xb5, 16, 0, 2, 1, 3, 3, 2, 4, 3, 5, 5, 4, 4, 0, 0, 1, 125, 1, 2, 3, 0, 4, 17, 5, 18, ReplyCode.reply0x21, ReplyCode.reply0x31, 65, 6, 19, 81, 97, 7, ReplyCode.reply0x22, 113, 20, ReplyCode.reply0x32, -127, -111, -95, 8, ReplyCode.reply0x23, 66, ReplyCode.reply0xb1, -63, 21, 82, -47, -16, ReplyCode.reply0x24, 51, 98, 114, ReplyCode.reply0x82, 9, 10, 22, 23, 24, 25, 26, ReplyCode.reply0x25, ReplyCode.reply0x26, ReplyCode.reply0x27, ReplyCode.reply0x28, ReplyCode.reply0x29, 42, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 58, 67, 68, 69, 70, 71, 72, 73, 74, 83, 84, 85, 86, 87, 88, 89, 90, 99, ReplyCode.reply0x64, 101, 102, ReplyCode.reply0x67, ReplyCode.reply0x68, 105, ReplyCode.reply0x6a, ReplyCode.reply0x73, 116, 117, 118, ReplyCode.reply0x77, ReplyCode.reply0x78, 121, ReplyCode.reply0x7a, ReplyCode.reply0x83, ReplyCode.reply0x84, ReplyCode.reply0x85, ReplyCode.reply0x86, ReplyCode.reply0x87, ReplyCode.reply0x88, ReplyCode.reply0x89, ReplyCode.reply0x8a, -110, -109, -108, -107, -106, -105, -104, -103, -102, -94, -93, -92, ReplyCode.reply0xa5, ReplyCode.reply0xa6, ReplyCode.reply0xa7, ReplyCode.reply0xa8, ReplyCode.reply0xa9, ReplyCode.reply0xaa, ReplyCode.reply0xb2, ReplyCode.reply0xb3, ReplyCode.reply0xb4, ReplyCode.reply0xb5, -74, -73, -72, -71, -70, ReplyCode.reply0xc2, -61, -60, ReplyCode.reply0xc5, -58, ReplyCode.reply0xc7, ReplyCode.reply0xc8, ReplyCode.reply0xc9, ReplyCode.reply0xca, ReplyCode.reply0xd2, -45, -44, -43, -42, -41, -40, -39, -38, -31, -30, -29, -28, -27, -26, -25, -24, -23, -22, -15, -14, -13, -12, -11, -10, -9, -8, -7, -6, -1, -60, 0, 31, 1, 0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, -1, -60, 0, ReplyCode.reply0xb5, 17, 0, 2, 1, 2, 4, 4, 3, 4, 7, 5, 4, 4, 0, 1, 2, ReplyCode.reply0x77, 0, 1, 2, 3, 17, 4, 5, ReplyCode.reply0x21, ReplyCode.reply0x31, 6, 18, 65, 81, 7, 97, 113, 19, ReplyCode.reply0x22, ReplyCode.reply0x32, -127, 8, 20, 66, -111, -95, ReplyCode.reply0xb1, -63, 9, ReplyCode.reply0x23, 51, 82, -16, 21, 98, 114, -47, 10, 22, ReplyCode.reply0x24, ReplyCode.reply0x34, -31, ReplyCode.reply0x25, -15, 23, 24, 25, 26, ReplyCode.reply0x26, ReplyCode.reply0x27, ReplyCode.reply0x28, ReplyCode.reply0x29, 42, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 58, 67, 68, 69, 70, 71, 72, 73, 74, 83, 84, 85, 86, 87, 88, 89, 90, 99, ReplyCode.reply0x64, 101, 102, ReplyCode.reply0x67, ReplyCode.reply0x68, 105, ReplyCode.reply0x6a, ReplyCode.reply0x73, 116, 117, 118, ReplyCode.reply0x77, ReplyCode.reply0x78, 121, ReplyCode.reply0x7a, ReplyCode.reply0x82, ReplyCode.reply0x83, ReplyCode.reply0x84, ReplyCode.reply0x85, ReplyCode.reply0x86, ReplyCode.reply0x87, ReplyCode.reply0x88, ReplyCode.reply0x89, ReplyCode.reply0x8a, -110, -109, -108, -107, -106, -105, -104, -103, -102, -94, -93, -92, ReplyCode.reply0xa5, ReplyCode.reply0xa6, ReplyCode.reply0xa7, ReplyCode.reply0xa8, ReplyCode.reply0xa9, ReplyCode.reply0xaa, ReplyCode.reply0xb2, ReplyCode.reply0xb3, ReplyCode.reply0xb4, ReplyCode.reply0xb5, -74, -73, -72, -71, -70, ReplyCode.reply0xc2, -61, -60, ReplyCode.reply0xc5, -58, ReplyCode.reply0xc7, ReplyCode.reply0xc8, ReplyCode.reply0xc9, ReplyCode.reply0xca, ReplyCode.reply0xd2, -45, -44, -43, -42, -41, -40, -39, -38, -30, -29, -28, -27, -26, -25, -24, -23, -22, -14, -13, -12, -11, -10, -9, -8, -7, -6, -1, -38, 0, 12, 3, 1, 0, 2, 17, 3, 17, 0, 63, 0, ReplyCode.reply0x8e, ReplyCode.reply0x8a, ReplyCode.reply0x28, ReplyCode.reply0xa0, 15, -1, -39};
    private final PooledByteBufferFactory mPooledByteBufferFactory;

    public EmptyJpegGenerator(PooledByteBufferFactory pooledByteBufferFactory) {
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
    }

    public CloseableReference<PooledByteBuffer> generate(short s, short s2) {
        PooledByteBufferOutputStream pooledByteBufferOutputStream = null;
        try {
            try {
                PooledByteBufferFactory pooledByteBufferFactory = this.mPooledByteBufferFactory;
                byte[] bArr = EMPTY_JPEG_PREFIX;
                int length = bArr.length;
                byte[] bArr2 = EMPTY_JPEG_SUFFIX;
                pooledByteBufferOutputStream = pooledByteBufferFactory.newOutputStream(length + bArr2.length + 4);
                pooledByteBufferOutputStream.write(bArr);
                pooledByteBufferOutputStream.write((byte) (s2 >> 8));
                pooledByteBufferOutputStream.write((byte) (s2 & ErrorCode.UNKNOWN));
                pooledByteBufferOutputStream.write((byte) (s >> 8));
                pooledByteBufferOutputStream.write((byte) (s & ErrorCode.UNKNOWN));
                pooledByteBufferOutputStream.write(bArr2);
                return CloseableReference.of(pooledByteBufferOutputStream.toByteBuffer());
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        } finally {
            if (pooledByteBufferOutputStream != null) {
                pooledByteBufferOutputStream.close();
            }
        }
    }
}
