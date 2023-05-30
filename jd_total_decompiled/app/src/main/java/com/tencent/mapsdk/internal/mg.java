package com.tencent.mapsdk.internal;

import android.text.TextUtils;
import android.util.Log;
import androidx.core.view.MotionEventCompat;
import com.jingdong.jdsdk.constant.CartConstant;
import com.tencent.mapsdk.core.utils.cache.DiskCache;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.Arrays;

/* loaded from: classes9.dex */
public class mg implements DiskCache.b {
    private final int a = 128;
    private String b;

    public mg(String str) {
        this.b = str;
    }

    private int a(byte[] bArr) {
        return ((bArr[3] << 24) & (-16777216)) | (bArr[0] & 255) | ((bArr[1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | ((bArr[2] << 16) & 16711680);
    }

    private long a(int i2, int i3) {
        return ((i2 % 128) * 128) + (i3 % 128);
    }

    private String a(int i2, int i3, int i4) {
        StringBuilder sb = new StringBuilder();
        int i5 = i2 / 128;
        int i6 = i3 / 128;
        sb.append(File.separatorChar);
        sb.append("glGrid");
        sb.append(File.separatorChar);
        sb.append(i4);
        sb.append(File.separatorChar);
        sb.append(i5 / 10);
        sb.append(File.separatorChar);
        sb.append(i6 / 10);
        sb.append(File.separatorChar);
        sb.append(i4);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(i5);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(i6);
        return sb.toString();
    }

    private boolean a(String str, int i2, int i3, byte[] bArr) {
        int length;
        RandomAccessFile randomAccessFile;
        long j2;
        long j3;
        if (bArr != null && (length = bArr.length) > 0) {
            String[] strArr = {str + ".idx", str + ".dat"};
            File file = new File(strArr[1]);
            if (file.exists() || fa.b(file)) {
                RandomAccessFile randomAccessFile2 = null;
                try {
                    randomAccessFile = new RandomAccessFile(file, "rws");
                } catch (Exception unused) {
                    randomAccessFile = null;
                }
                if (randomAccessFile == null) {
                    return false;
                }
                byte[] a = a(length);
                b(a);
                try {
                    try {
                        j2 = randomAccessFile.length();
                        try {
                            randomAccessFile.seek(j2);
                            randomAccessFile.write(a);
                            randomAccessFile.write(bArr);
                        } catch (Exception unused2) {
                        }
                    } finally {
                        ga.a(randomAccessFile);
                    }
                } catch (Exception unused3) {
                    j2 = 0;
                }
                ga.a(randomAccessFile);
                File file2 = new File(strArr[0]);
                if (file2.exists() || fa.b(file2)) {
                    try {
                        randomAccessFile2 = new RandomAccessFile(file2, "rws");
                    } catch (Exception unused4) {
                    }
                    if (randomAccessFile == null) {
                        return false;
                    }
                    try {
                        j3 = randomAccessFile.length();
                    } catch (Exception unused5) {
                        j3 = 0;
                    }
                    if (j3 == 0) {
                        byte[] bArr2 = new byte[65536];
                        Arrays.fill(bArr2, (byte) -1);
                        try {
                            randomAccessFile.write(bArr2);
                        } catch (Exception unused6) {
                        }
                    }
                    long a2 = a(i2, i3);
                    if (a2 < 0) {
                        return false;
                    }
                    try {
                        randomAccessFile.seek(a2 * 4);
                    } catch (Exception unused7) {
                    }
                    byte[] a3 = a((int) j2);
                    b(a3);
                    try {
                        randomAccessFile.write(a3);
                    } catch (Exception unused8) {
                    } catch (Throwable th) {
                        throw th;
                    }
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    private byte[] a(int i2) {
        return new byte[]{(byte) (i2 & 255), (byte) ((65280 & i2) >> 8), (byte) ((16711680 & i2) >> 16), (byte) ((i2 & (-16777216)) >> 24)};
    }

    private int[] a(String str) {
        String[] split = str.split("-");
        if (split.length < 3) {
            return null;
        }
        int[] iArr = new int[3];
        for (int i2 = 0; i2 < 3; i2++) {
            try {
                iArr[i2] = Integer.parseInt(split[i2]);
            } catch (NumberFormatException e2) {
                ma.c(Log.getStackTraceString(e2));
                return null;
            }
        }
        return iArr;
    }

    private void b(byte[] bArr) {
        if (bArr == null || bArr.length != 4) {
            return;
        }
        byte b = bArr[0];
        bArr[0] = bArr[3];
        bArr[3] = b;
        byte b2 = bArr[1];
        bArr[1] = bArr[2];
        bArr[2] = b2;
    }

    @Override // com.tencent.mapsdk.core.utils.cache.DiskCache.b
    public File a(String str, String str2, byte[] bArr) {
        if (TextUtils.isEmpty(this.b)) {
            return null;
        }
        int lastIndexOf = str.lastIndexOf(File.separatorChar);
        if (lastIndexOf > -1) {
            String substring = str.substring(0, lastIndexOf);
            if (!TextUtils.isEmpty(substring)) {
                str2 = str2 + File.separator + substring;
            }
            str = str.substring(lastIndexOf + 1);
        }
        int[] a = a(str);
        if (a == null) {
            return new File(str2, str);
        }
        String str3 = str2 + a(a[0], a[1], a[2]);
        if (a(str3, a[0], a[1], bArr)) {
            return new File(str3 + ".dat");
        }
        return new File(str2, str);
    }

    public byte[] a(String str, int i2, int i3) {
        RandomAccessFile randomAccessFile;
        RandomAccessFile randomAccessFile2;
        String[] strArr = {str + ".idx", str + ".dat"};
        File file = new File(strArr[0]);
        RandomAccessFile randomAccessFile3 = null;
        if (file.exists() && file.length() != 0) {
            long a = a(i2, i3);
            if (a < 0) {
                return null;
            }
            byte[] bArr = new byte[4];
            try {
                randomAccessFile = new RandomAccessFile(file, "r");
            } catch (Exception unused) {
                randomAccessFile = null;
            } catch (Throwable th) {
                th = th;
            }
            try {
                randomAccessFile.seek(a * 4);
                randomAccessFile.read(bArr, 0, 4);
                ga.a(randomAccessFile);
                b(bArr);
                int a2 = a(bArr);
                if (a2 < 0) {
                    return null;
                }
                File file2 = new File(strArr[1]);
                if (file2.exists()) {
                    try {
                        randomAccessFile2 = new RandomAccessFile(file2, "r");
                    } catch (Exception unused2) {
                        randomAccessFile2 = null;
                    }
                    try {
                        randomAccessFile2.seek(a2);
                        randomAccessFile2.read(bArr, 0, 4);
                        b(bArr);
                        int a3 = a(bArr);
                        if (a3 <= 0) {
                            return null;
                        }
                        try {
                            byte[] bArr2 = new byte[a3];
                            randomAccessFile2.read(bArr2, 0, a3);
                            return bArr2;
                        } catch (Throwable th2) {
                            try {
                                ma.b("\u8bfb\u53d6\u74e6\u7247\u7f13\u5b58\u7684\u5927\u5c0f\u5f02\u5e38", th2);
                                return null;
                            } finally {
                                ga.a(randomAccessFile2);
                            }
                        }
                    } catch (Exception unused3) {
                        return null;
                    }
                }
                return null;
            } catch (Exception unused4) {
                ga.a(randomAccessFile);
                return null;
            } catch (Throwable th3) {
                th = th3;
                randomAccessFile3 = randomAccessFile;
                ga.a(randomAccessFile3);
                throw th;
            }
        }
        return null;
    }

    @Override // com.tencent.mapsdk.core.utils.cache.DiskCache.b
    public byte[] a(String str, File file) {
        String str2 = this.b;
        int lastIndexOf = str.lastIndexOf(File.separatorChar);
        if (lastIndexOf > -1) {
            String substring = str.substring(0, lastIndexOf);
            if (!TextUtils.isEmpty(substring)) {
                str2 = str2 + File.separator + substring;
            }
            str = str.substring(lastIndexOf + 1);
        }
        int[] a = a(str);
        if (a == null) {
            return null;
        }
        return a(str2 + a(a[0], a[1], a[2]), a[0], a[1]);
    }

    @Override // com.tencent.mapsdk.core.utils.cache.DiskCache.b
    public boolean b(String str, File file) {
        return fa.d(new File(this.b));
    }
}
