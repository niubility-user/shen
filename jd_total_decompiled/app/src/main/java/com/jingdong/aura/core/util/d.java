package com.jingdong.aura.core.util;

import android.text.TextUtils;
import com.jingdong.aura.a.c.l;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.zip.ZipFile;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes.dex */
public class d {
    public static String a(String str) {
        String b = b(str);
        if (b == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        byte[] bytes = b.getBytes();
        int length = bytes.length;
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 + 1;
            int i4 = i3 >= length ? i3 - length : i3;
            int i5 = i2 + 4;
            if (i5 >= length) {
                i5 -= length;
            }
            sb.append("e70d12f4985ac3b6".charAt((bytes[i2] + bytes[i4] + bytes[i5]) & 15));
            i2 = i3;
        }
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00a8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00b2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v12, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7, types: [java.io.FileInputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String b(String str) {
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream;
        Exception e2;
        ByteArrayOutputStream byteArrayOutputStream2;
        int i2;
        FileInputStream fileInputStream = null;
        try {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
                File file = new File((String) str);
                if (file.exists() && file.isFile()) {
                    str = new FileInputStream(file);
                    try {
                        byteArrayOutputStream2 = new ByteArrayOutputStream();
                    } catch (Exception e3) {
                        e2 = e3;
                        byteArrayOutputStream2 = null;
                    } catch (Throwable th2) {
                        th = th2;
                        byteArrayOutputStream = null;
                        fileInputStream = str;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Exception e4) {
                                e4.printStackTrace();
                            }
                        }
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (Exception e5) {
                                e5.printStackTrace();
                            }
                        }
                        throw th;
                    }
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = str.read(bArr, 0, 1024);
                            if (read == -1) {
                                break;
                            }
                            byteArrayOutputStream2.write(bArr, 0, read);
                        }
                        messageDigest.update(byteArrayOutputStream2.toByteArray());
                        byte[] digest = messageDigest.digest();
                        StringBuilder sb = new StringBuilder(digest.length * 2);
                        for (byte b : digest) {
                            sb.append("0123456789abcdef".charAt((b >> 4) & 15));
                            sb.append("0123456789abcdef".charAt(b & 15));
                        }
                        String sb2 = sb.toString();
                        try {
                            str.close();
                        } catch (Exception e6) {
                            e6.printStackTrace();
                        }
                        try {
                            byteArrayOutputStream2.close();
                        } catch (Exception e7) {
                            e7.printStackTrace();
                        }
                        return sb2;
                    } catch (Exception e8) {
                        e2 = e8;
                        e2.printStackTrace();
                        if (str != 0) {
                            try {
                                str.close();
                            } catch (Exception e9) {
                                e9.printStackTrace();
                            }
                        }
                        if (byteArrayOutputStream2 != null) {
                            try {
                                byteArrayOutputStream2.close();
                            } catch (Exception e10) {
                                e10.printStackTrace();
                            }
                        }
                        return null;
                    }
                }
                return null;
            } catch (Exception e11) {
                e2 = e11;
                str = 0;
                byteArrayOutputStream2 = null;
            } catch (Throwable th3) {
                th = th3;
                byteArrayOutputStream = null;
                if (fileInputStream != null) {
                }
                if (byteArrayOutputStream != null) {
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:45:0x005e -> B:70:0x0061). Please submit an issue!!! */
    public static void a(InputStream inputStream, File file) {
        FileOutputStream fileOutputStream;
        Throwable th;
        FileChannel fileChannel;
        try {
            try {
                fileOutputStream = new FileOutputStream(file);
                try {
                    fileChannel = fileOutputStream.getChannel();
                } catch (Throwable th2) {
                    th = th2;
                    fileChannel = null;
                }
            } catch (Throwable th3) {
                fileOutputStream = null;
                th = th3;
                fileChannel = null;
            }
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    fileChannel.write(ByteBuffer.wrap(bArr, 0, read));
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                if (fileChannel != null) {
                    try {
                        fileChannel.close();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
                fileOutputStream.close();
            } catch (Throwable th4) {
                th = th4;
                try {
                    th.printStackTrace();
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    }
                    if (fileChannel != null) {
                        try {
                            fileChannel.close();
                        } catch (Exception e5) {
                            e5.printStackTrace();
                        }
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (Throwable th5) {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e6) {
                            e6.printStackTrace();
                        }
                    }
                    if (fileChannel != null) {
                        try {
                            fileChannel.close();
                        } catch (Exception e7) {
                            e7.printStackTrace();
                        }
                    }
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception e8) {
                            e8.printStackTrace();
                        }
                    }
                    throw th5;
                }
            }
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    public static File a() {
        File filesDir = l.a.getFilesDir();
        if (filesDir == null) {
            return new File("/data/data/" + l.a.getPackageName() + "/files");
        }
        return filesDir;
    }

    public static void a(ZipFile zipFile) {
        if (zipFile != null) {
            try {
                zipFile.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static boolean a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && new File(str).exists() && new File(str2).exists()) {
            String b = b(str);
            String b2 = b(str2);
            if (!TextUtils.isEmpty(b) && !TextUtils.isEmpty(b2)) {
                return b.equals(b2);
            }
        }
        return false;
    }
}
