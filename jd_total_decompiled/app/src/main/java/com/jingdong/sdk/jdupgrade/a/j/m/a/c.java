package com.jingdong.sdk.jdupgrade.a.j.m.a;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public final class c {
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0043, code lost:
        if (r1 == null) goto L31;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Map<Integer, ByteBuffer> a(File file) {
        Throwable th;
        FileChannel fileChannel;
        RandomAccessFile randomAccessFile;
        Map<Integer, ByteBuffer> map = null;
        try {
            try {
                randomAccessFile = new RandomAccessFile(file, "r");
                try {
                    fileChannel = randomAccessFile.getChannel();
                } catch (IOException unused) {
                    fileChannel = null;
                } catch (Throwable th2) {
                    th = th2;
                    fileChannel = null;
                }
            } catch (IOException unused2) {
                fileChannel = null;
                randomAccessFile = null;
            } catch (Throwable th3) {
                th = th3;
                fileChannel = null;
                randomAccessFile = null;
            }
            try {
                map = a.b(a.a(fileChannel).a());
                if (fileChannel != null) {
                    try {
                        fileChannel.close();
                    } catch (IOException unused3) {
                    }
                }
            } catch (IOException unused4) {
                if (fileChannel != null) {
                    try {
                        fileChannel.close();
                    } catch (IOException unused5) {
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                if (fileChannel != null) {
                    try {
                        fileChannel.close();
                    } catch (IOException unused6) {
                    }
                }
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException unused7) {
                    }
                }
                throw th;
            }
            randomAccessFile.close();
        } catch (d | IOException unused8) {
        }
        return map;
    }

    public static byte[] a(ByteBuffer byteBuffer) {
        byte[] array = byteBuffer.array();
        int arrayOffset = byteBuffer.arrayOffset();
        return Arrays.copyOfRange(array, byteBuffer.position() + arrayOffset, arrayOffset + byteBuffer.limit());
    }

    public static Map<Integer, String> b(File file) {
        Map<Integer, ByteBuffer> a = a(file);
        if (a == null) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<Integer, ByteBuffer> entry : a.entrySet()) {
            try {
                linkedHashMap.put(entry.getKey(), new String(a(entry.getValue()), "UTF-8"));
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        }
        return linkedHashMap;
    }
}
