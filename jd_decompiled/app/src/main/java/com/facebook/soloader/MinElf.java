package com.facebook.soloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;

/* loaded from: classes12.dex */
public final class MinElf {
    public static final int DT_NEEDED = 1;
    public static final int DT_NULL = 0;
    public static final int DT_STRTAB = 5;
    public static final int ELF_MAGIC = 1179403647;
    public static final int PN_XNUM = 65535;
    public static final int PT_DYNAMIC = 2;
    public static final int PT_LOAD = 1;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class ElfError extends RuntimeException {
        ElfError(String str) {
            super(str);
        }
    }

    /* loaded from: classes12.dex */
    public enum ISA {
        NOT_SO("not_so"),
        X86("x86"),
        ARM("armeabi-v7a"),
        X86_64("x86_64"),
        AARCH64("arm64-v8a"),
        OTHERS("others");
        
        private final String value;

        ISA(String str) {
            this.value = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.value;
        }
    }

    public static String[] extract_DT_NEEDED(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            return extract_DT_NEEDED(fileInputStream.getChannel());
        } finally {
            fileInputStream.close();
        }
    }

    private static long get64(FileChannel fileChannel, ByteBuffer byteBuffer, long j2) throws IOException {
        read(fileChannel, byteBuffer, 8, j2);
        return byteBuffer.getLong();
    }

    private static String getSz(FileChannel fileChannel, ByteBuffer byteBuffer, long j2) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            long j3 = 1 + j2;
            short u8Var = getu8(fileChannel, byteBuffer, j2);
            if (u8Var != 0) {
                sb.append((char) u8Var);
                j2 = j3;
            } else {
                return sb.toString();
            }
        }
    }

    private static int getu16(FileChannel fileChannel, ByteBuffer byteBuffer, long j2) throws IOException {
        read(fileChannel, byteBuffer, 2, j2);
        return byteBuffer.getShort() & 65535;
    }

    private static long getu32(FileChannel fileChannel, ByteBuffer byteBuffer, long j2) throws IOException {
        read(fileChannel, byteBuffer, 4, j2);
        return byteBuffer.getInt() & 4294967295L;
    }

    private static short getu8(FileChannel fileChannel, ByteBuffer byteBuffer, long j2) throws IOException {
        read(fileChannel, byteBuffer, 1, j2);
        return (short) (byteBuffer.get() & 255);
    }

    private static void read(FileChannel fileChannel, ByteBuffer byteBuffer, int i2, long j2) throws IOException {
        int read;
        byteBuffer.position(0);
        byteBuffer.limit(i2);
        while (byteBuffer.remaining() > 0 && (read = fileChannel.read(byteBuffer, j2)) != -1) {
            j2 += read;
        }
        if (byteBuffer.remaining() <= 0) {
            byteBuffer.position(0);
            return;
        }
        throw new ElfError("ELF file truncated");
    }

    public static String[] extract_DT_NEEDED(FileChannel fileChannel) throws IOException {
        long j2;
        long j3;
        long j4;
        long j5;
        long j6;
        long j7;
        long j8;
        long j9;
        long j10;
        long j11;
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        if (getu32(fileChannel, allocate, 0L) == 1179403647) {
            boolean z = getu8(fileChannel, allocate, 4L) == 1;
            if (getu8(fileChannel, allocate, 5L) == 2) {
                allocate.order(ByteOrder.BIG_ENDIAN);
            }
            long j12 = z ? getu32(fileChannel, allocate, 28L) : get64(fileChannel, allocate, 32L);
            long j13 = z ? getu16(fileChannel, allocate, 44L) : getu16(fileChannel, allocate, 56L);
            int i2 = getu16(fileChannel, allocate, z ? 42L : 54L);
            if (j13 == 65535) {
                long j14 = z ? getu32(fileChannel, allocate, 32L) : get64(fileChannel, allocate, 40L);
                if (z) {
                    j11 = getu32(fileChannel, allocate, j14 + 28);
                } else {
                    j11 = getu32(fileChannel, allocate, j14 + 44);
                }
                j13 = j11;
            }
            long j15 = j12;
            long j16 = 0;
            while (true) {
                if (j16 >= j13) {
                    j2 = 0;
                    break;
                }
                if (z) {
                    j10 = getu32(fileChannel, allocate, j15 + 0);
                } else {
                    j10 = getu32(fileChannel, allocate, j15 + 0);
                }
                if (j10 != 2) {
                    j15 += i2;
                    j16++;
                } else if (z) {
                    j2 = getu32(fileChannel, allocate, j15 + 4);
                } else {
                    j2 = get64(fileChannel, allocate, j15 + 8);
                }
            }
            long j17 = 0;
            if (j2 == 0) {
                throw new ElfError("ELF file does not contain dynamic linking information");
            }
            long j18 = j2;
            long j19 = 0;
            int i3 = 0;
            while (true) {
                boolean z2 = z;
                long j20 = z ? getu32(fileChannel, allocate, j18 + j17) : get64(fileChannel, allocate, j18 + j17);
                if (j20 == 1) {
                    j3 = j2;
                    if (i3 == Integer.MAX_VALUE) {
                        throw new ElfError("malformed DT_NEEDED section");
                    }
                    i3++;
                } else {
                    j3 = j2;
                    if (j20 == 5) {
                        j19 = z2 ? getu32(fileChannel, allocate, j18 + 4) : get64(fileChannel, allocate, j18 + 8);
                    }
                }
                long j21 = 16;
                j18 += z2 ? 8L : 16L;
                j17 = 0;
                if (j20 != 0) {
                    z = z2;
                    j2 = j3;
                } else if (j19 == 0) {
                    throw new ElfError("Dynamic section string-table not found");
                } else {
                    int i4 = 0;
                    while (true) {
                        if (i4 >= j13) {
                            j4 = 0;
                            break;
                        }
                        if (z2) {
                            j5 = getu32(fileChannel, allocate, j12 + j17);
                        } else {
                            j5 = getu32(fileChannel, allocate, j12 + j17);
                        }
                        if (j5 == 1) {
                            if (z2) {
                                j7 = getu32(fileChannel, allocate, j12 + 8);
                            } else {
                                j7 = get64(fileChannel, allocate, j12 + j21);
                            }
                            if (z2) {
                                j6 = j13;
                                j8 = getu32(fileChannel, allocate, j12 + 20);
                            } else {
                                j6 = j13;
                                j8 = get64(fileChannel, allocate, j12 + 40);
                            }
                            if (j7 <= j19 && j19 < j8 + j7) {
                                if (z2) {
                                    j9 = getu32(fileChannel, allocate, j12 + 4);
                                } else {
                                    j9 = get64(fileChannel, allocate, j12 + 8);
                                }
                                j4 = j9 + (j19 - j7);
                            }
                        } else {
                            j6 = j13;
                        }
                        j12 += i2;
                        i4++;
                        j13 = j6;
                        j21 = 16;
                        j17 = 0;
                    }
                    long j22 = 0;
                    if (j4 != 0) {
                        String[] strArr = new String[i3];
                        int i5 = 0;
                        while (true) {
                            long j23 = j3 + j22;
                            long j24 = z2 ? getu32(fileChannel, allocate, j23) : get64(fileChannel, allocate, j23);
                            if (j24 == 1) {
                                strArr[i5] = getSz(fileChannel, allocate, (z2 ? getu32(fileChannel, allocate, j3 + 4) : get64(fileChannel, allocate, j3 + 8)) + j4);
                                if (i5 == Integer.MAX_VALUE) {
                                    throw new ElfError("malformed DT_NEEDED section");
                                }
                                i5++;
                            }
                            j3 += z2 ? 8L : 16L;
                            if (j24 == 0) {
                                if (i5 == i3) {
                                    return strArr;
                                }
                                throw new ElfError("malformed DT_NEEDED section");
                            }
                            j22 = 0;
                        }
                    } else {
                        throw new ElfError("did not find file offset of DT_STRTAB table");
                    }
                }
            }
        } else {
            throw new ElfError("file is not ELF");
        }
    }
}
