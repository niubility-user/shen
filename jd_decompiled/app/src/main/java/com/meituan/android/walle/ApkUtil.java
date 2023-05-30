package com.meituan.android.walle;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes.dex */
final class ApkUtil {
    public static final int APK_CHANNEL_BLOCK_ID = 1903654775;
    public static final int APK_SIGNATURE_SCHEME_V2_BLOCK_ID = 1896449818;
    public static final long APK_SIG_BLOCK_MAGIC_HI = 3617552046287187010L;
    public static final long APK_SIG_BLOCK_MAGIC_LO = 2334950737559900225L;
    private static final int APK_SIG_BLOCK_MIN_SIZE = 32;
    public static final String DEFAULT_CHARSET = "UTF-8";
    private static final int UINT16_MAX_VALUE = 65535;
    private static final int ZIP_EOCD_COMMENT_LENGTH_FIELD_OFFSET = 20;
    private static final int ZIP_EOCD_REC_MIN_SIZE = 22;
    private static final int ZIP_EOCD_REC_SIG = 101010256;

    private ApkUtil() {
    }

    private static void checkByteOrderLittleEndian(ByteBuffer byteBuffer) {
        if (byteBuffer.order() != ByteOrder.LITTLE_ENDIAN) {
            throw new IllegalArgumentException("ByteBuffer byte order must be little endian");
        }
    }

    public static Pair<ByteBuffer, Long> findApkSigningBlock(FileChannel fileChannel) throws IOException, SignatureNotFoundException {
        return findApkSigningBlock(fileChannel, findCentralDirStartOffset(fileChannel));
    }

    public static long findCentralDirStartOffset(FileChannel fileChannel) throws IOException {
        return findCentralDirStartOffset(fileChannel, getCommentLength(fileChannel));
    }

    public static Map<Integer, ByteBuffer> findIdValues(ByteBuffer byteBuffer) throws SignatureNotFoundException {
        checkByteOrderLittleEndian(byteBuffer);
        ByteBuffer sliceFromTo = sliceFromTo(byteBuffer, 8, byteBuffer.capacity() - 24);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i2 = 0;
        while (sliceFromTo.hasRemaining()) {
            i2++;
            if (sliceFromTo.remaining() >= 8) {
                long j2 = sliceFromTo.getLong();
                if (j2 >= 4 && j2 <= 2147483647L) {
                    int i3 = (int) j2;
                    int position = sliceFromTo.position() + i3;
                    if (i3 <= sliceFromTo.remaining()) {
                        linkedHashMap.put(Integer.valueOf(sliceFromTo.getInt()), getByteBuffer(sliceFromTo, i3 - 4));
                        sliceFromTo.position(position);
                    } else {
                        throw new SignatureNotFoundException("APK Signing Block entry #" + i2 + " size out of range: " + i3 + ", available: " + sliceFromTo.remaining());
                    }
                } else {
                    throw new SignatureNotFoundException("APK Signing Block entry #" + i2 + " size out of range: " + j2);
                }
            } else {
                throw new SignatureNotFoundException("Insufficient data to read size of APK Signing Block entry #" + i2);
            }
        }
        return linkedHashMap;
    }

    private static ByteBuffer getByteBuffer(ByteBuffer byteBuffer, int i2) throws BufferUnderflowException {
        if (i2 >= 0) {
            int limit = byteBuffer.limit();
            int position = byteBuffer.position();
            int i3 = i2 + position;
            if (i3 >= position && i3 <= limit) {
                byteBuffer.limit(i3);
                try {
                    ByteBuffer slice = byteBuffer.slice();
                    slice.order(byteBuffer.order());
                    byteBuffer.position(i3);
                    return slice;
                } finally {
                    byteBuffer.limit(limit);
                }
            }
            throw new BufferUnderflowException();
        }
        throw new IllegalArgumentException("size: " + i2);
    }

    public static long getCommentLength(FileChannel fileChannel) throws IOException {
        long size = fileChannel.size();
        if (size >= 22) {
            long j2 = size - 22;
            long min = Math.min(j2, 65535L);
            int i2 = 0;
            while (true) {
                long j3 = i2;
                if (j3 <= min) {
                    long j4 = j2 - j3;
                    ByteBuffer allocate = ByteBuffer.allocate(4);
                    fileChannel.position(j4);
                    fileChannel.read(allocate);
                    ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
                    allocate.order(byteOrder);
                    if (allocate.getInt(0) == ZIP_EOCD_REC_SIG) {
                        ByteBuffer allocate2 = ByteBuffer.allocate(2);
                        fileChannel.position(j4 + 20);
                        fileChannel.read(allocate2);
                        allocate2.order(byteOrder);
                        short s = allocate2.getShort(0);
                        if (s == i2) {
                            return s;
                        }
                    }
                    i2++;
                } else {
                    throw new IOException("ZIP End of Central Directory (EOCD) record not found");
                }
            }
        } else {
            throw new IOException("APK too small for ZIP End of Central Directory (EOCD) record");
        }
    }

    private static ByteBuffer sliceFromTo(ByteBuffer byteBuffer, int i2, int i3) {
        if (i2 < 0) {
            throw new IllegalArgumentException("start: " + i2);
        } else if (i3 >= i2) {
            int capacity = byteBuffer.capacity();
            if (i3 <= byteBuffer.capacity()) {
                int limit = byteBuffer.limit();
                int position = byteBuffer.position();
                try {
                    byteBuffer.position(0);
                    byteBuffer.limit(i3);
                    byteBuffer.position(i2);
                    ByteBuffer slice = byteBuffer.slice();
                    slice.order(byteBuffer.order());
                    return slice;
                } finally {
                    byteBuffer.position(0);
                    byteBuffer.limit(limit);
                    byteBuffer.position(position);
                }
            }
            throw new IllegalArgumentException("end > capacity: " + i3 + " > " + capacity);
        } else {
            throw new IllegalArgumentException("end < start: " + i3 + " < " + i2);
        }
    }

    public static long findCentralDirStartOffset(FileChannel fileChannel, long j2) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        fileChannel.position((fileChannel.size() - j2) - 6);
        fileChannel.read(allocate);
        return allocate.getInt(0);
    }

    public static Pair<ByteBuffer, Long> findApkSigningBlock(FileChannel fileChannel, long j2) throws IOException, SignatureNotFoundException {
        if (j2 >= 32) {
            fileChannel.position(j2 - 24);
            ByteBuffer allocate = ByteBuffer.allocate(24);
            fileChannel.read(allocate);
            ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
            allocate.order(byteOrder);
            if (allocate.getLong(8) == APK_SIG_BLOCK_MAGIC_LO && allocate.getLong(16) == APK_SIG_BLOCK_MAGIC_HI) {
                long j3 = allocate.getLong(0);
                if (j3 < allocate.capacity() || j3 > 2147483639) {
                    throw new SignatureNotFoundException("APK Signing Block size out of range: " + j3);
                }
                int i2 = (int) (8 + j3);
                long j4 = j2 - i2;
                if (j4 >= 0) {
                    fileChannel.position(j4);
                    ByteBuffer allocate2 = ByteBuffer.allocate(i2);
                    fileChannel.read(allocate2);
                    allocate2.order(byteOrder);
                    long j5 = allocate2.getLong(0);
                    if (j5 == j3) {
                        return Pair.of(allocate2, Long.valueOf(j4));
                    }
                    throw new SignatureNotFoundException("APK Signing Block sizes in header and footer do not match: " + j5 + " vs " + j3);
                }
                throw new SignatureNotFoundException("APK Signing Block offset out of range: " + j4);
            }
            throw new SignatureNotFoundException("No APK Signing Block before ZIP Central Directory");
        }
        throw new SignatureNotFoundException("APK too small for APK Signing Block. ZIP Central Directory offset: " + j2);
    }
}
