package com.meituan.android.walle;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Map;

/* loaded from: classes.dex */
public final class PayloadReader {
    private PayloadReader() {
    }

    public static byte[] get(File file, int i2) {
        ByteBuffer byteBuffer;
        Map<Integer, ByteBuffer> all = getAll(file);
        if (all == null || (byteBuffer = all.get(Integer.valueOf(i2))) == null) {
            return null;
        }
        return getBytes(byteBuffer);
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0044, code lost:
        if (r1 == null) goto L31;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static Map<Integer, ByteBuffer> getAll(File file) {
        FileChannel fileChannel;
        RandomAccessFile randomAccessFile;
        Map<Integer, ByteBuffer> map = null;
        try {
            try {
                randomAccessFile = new RandomAccessFile(file, "r");
                try {
                    fileChannel = randomAccessFile.getChannel();
                    try {
                        map = ApkUtil.findIdValues(ApkUtil.findApkSigningBlock(fileChannel).getFirst());
                        if (fileChannel != null) {
                            try {
                                fileChannel.close();
                            } catch (IOException unused) {
                            }
                        }
                    } catch (IOException unused2) {
                        if (fileChannel != null) {
                            try {
                                fileChannel.close();
                            } catch (IOException unused3) {
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (fileChannel != null) {
                            try {
                                fileChannel.close();
                            } catch (IOException unused4) {
                            }
                        }
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (IOException unused5) {
                            }
                        }
                        throw th;
                    }
                } catch (IOException unused6) {
                    fileChannel = null;
                } catch (Throwable th2) {
                    th = th2;
                    fileChannel = null;
                }
            } catch (IOException unused7) {
                fileChannel = null;
                randomAccessFile = null;
            } catch (Throwable th3) {
                th = th3;
                fileChannel = null;
                randomAccessFile = null;
            }
            randomAccessFile.close();
        } catch (SignatureNotFoundException | IOException unused8) {
        }
        return map;
    }

    private static byte[] getBytes(ByteBuffer byteBuffer) {
        byte[] array = byteBuffer.array();
        int arrayOffset = byteBuffer.arrayOffset();
        return Arrays.copyOfRange(array, byteBuffer.position() + arrayOffset, arrayOffset + byteBuffer.limit());
    }

    public static String getString(File file, int i2) {
        byte[] bArr = get(file, i2);
        if (bArr == null) {
            return null;
        }
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
