package com.xiaomi.push.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import com.xiaomi.push.t9;
import com.xiaomi.push.u9;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

/* loaded from: classes11.dex */
public class x {
    private static long a;

    /* loaded from: classes11.dex */
    public static class a {
        byte[] a;
        int b;

        public a(byte[] bArr, int i2) {
            this.a = bArr;
            this.b = i2;
        }
    }

    /* loaded from: classes11.dex */
    public static class b {
        public Bitmap a;
        public long b;

        public b(Bitmap bitmap, long j2) {
            this.a = bitmap;
            this.b = j2;
        }
    }

    private static int a(Context context, InputStream inputStream) {
        int i2;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, options);
        if (options.outWidth == -1 || options.outHeight == -1) {
            g.j.a.a.a.c.o("decode dimension failed for bitmap.");
            return 1;
        }
        int round = Math.round((context.getResources().getDisplayMetrics().densityDpi / 160.0f) * 48.0f);
        int i3 = options.outWidth;
        if (i3 <= round || (i2 = options.outHeight) <= round) {
            return 1;
        }
        return Math.min(i3 / round, i2 / round);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r0v3 */
    public static Bitmap b(Context context, String str) {
        InputStream inputStream;
        InputStream inputStream2;
        int a2;
        Uri parse = Uri.parse(str);
        ?? r0 = 0;
        r0 = 0;
        try {
            try {
                inputStream = context.getContentResolver().openInputStream(parse);
                try {
                    a2 = a(context, inputStream);
                    inputStream2 = context.getContentResolver().openInputStream(parse);
                } catch (IOException e2) {
                    e = e2;
                    inputStream2 = null;
                } catch (Throwable th) {
                    th = th;
                    u9.b(r0);
                    u9.b(inputStream);
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
                inputStream2 = null;
                inputStream = null;
            } catch (Throwable th2) {
                th = th2;
                inputStream = null;
            }
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = a2;
                Bitmap decodeStream = BitmapFactory.decodeStream(inputStream2, null, options);
                u9.b(inputStream2);
                u9.b(inputStream);
                return decodeStream;
            } catch (IOException e4) {
                e = e4;
                g.j.a.a.a.c.s(e);
                u9.b(inputStream2);
                u9.b(inputStream);
                return null;
            }
        } catch (Throwable th3) {
            th = th3;
            r0 = context;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x00dd, code lost:
        if (r1 == null) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00df, code lost:
        r1.disconnect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00fc, code lost:
        if (r1 == null) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00ff, code lost:
        return null;
     */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0101: MOVE (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:64:0x0101 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static a c(String str, boolean z) {
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        Closeable closeable;
        Closeable closeable2 = null;
        try {
            try {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                try {
                    httpURLConnection.setConnectTimeout(8000);
                    httpURLConnection.setReadTimeout(20000);
                    httpURLConnection.setRequestProperty("User-agent", "Mozilla/5.0 (Linux; U;) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/71.0.3578.141 Mobile Safari/537.36 XiaoMi/MiuiBrowser");
                    httpURLConnection.connect();
                    int contentLength = httpURLConnection.getContentLength();
                    if (z && contentLength > 102400) {
                        g.j.a.a.a.c.o("Bitmap size is too big, max size is 102400  contentLen size is " + contentLength + " from url " + str);
                        u9.b(null);
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        return null;
                    }
                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode != 200) {
                        g.j.a.a.a.c.o("Invalid Http Response Code " + responseCode + " received");
                        u9.b(null);
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        return null;
                    }
                    inputStream = httpURLConnection.getInputStream();
                    try {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        int i2 = z ? 102400 : 2048000;
                        byte[] bArr = new byte[1024];
                        while (i2 > 0) {
                            int read = inputStream.read(bArr, 0, 1024);
                            if (read == -1) {
                                break;
                            }
                            i2 -= read;
                            byteArrayOutputStream.write(bArr, 0, read);
                        }
                        if (i2 <= 0) {
                            g.j.a.a.a.c.o("length 102400 exhausted.");
                            a aVar = new a(null, 102400);
                            u9.b(inputStream);
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            return aVar;
                        }
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        a aVar2 = new a(byteArray, byteArray.length);
                        u9.b(inputStream);
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        return aVar2;
                    } catch (SocketTimeoutException unused) {
                        g.j.a.a.a.c.D("Connect timeout to " + str);
                        u9.b(inputStream);
                    } catch (IOException e2) {
                        e = e2;
                        g.j.a.a.a.c.s(e);
                        u9.b(inputStream);
                    }
                } catch (SocketTimeoutException unused2) {
                    inputStream = null;
                } catch (IOException e3) {
                    e = e3;
                    inputStream = null;
                } catch (Throwable th) {
                    th = th;
                    u9.b(closeable2);
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (SocketTimeoutException unused3) {
                httpURLConnection = null;
                inputStream = null;
            } catch (IOException e4) {
                e = e4;
                httpURLConnection = null;
                inputStream = null;
            } catch (Throwable th2) {
                th = th2;
                httpURLConnection = null;
            }
        } catch (Throwable th3) {
            th = th3;
            closeable2 = closeable;
        }
    }

    public static b d(Context context, String str, boolean z) {
        a c2;
        ByteArrayInputStream byteArrayInputStream = null;
        b bVar = new b(null, 0L);
        Bitmap g2 = g(context, str);
        try {
            if (g2 != null) {
                bVar.a = g2;
                return bVar;
            }
            try {
                c2 = c(str, z);
            } catch (Exception e2) {
                e = e2;
            }
            if (c2 == null) {
                u9.b(null);
                return bVar;
            }
            bVar.b = c2.b;
            byte[] bArr = c2.a;
            if (bArr != null) {
                if (z) {
                    ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(bArr);
                    try {
                        int a2 = a(context, byteArrayInputStream2);
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = a2;
                        bVar.a = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
                        byteArrayInputStream = byteArrayInputStream2;
                    } catch (Exception e3) {
                        e = e3;
                        byteArrayInputStream = byteArrayInputStream2;
                        g.j.a.a.a.c.s(e);
                        u9.b(byteArrayInputStream);
                        return bVar;
                    } catch (Throwable th) {
                        th = th;
                        byteArrayInputStream = byteArrayInputStream2;
                        u9.b(byteArrayInputStream);
                        throw th;
                    }
                } else {
                    bVar.a = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
                }
            }
            f(context, c2.a, str);
            u9.b(byteArrayInputStream);
            return bVar;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static void e(Context context) {
        File file = new File(context.getCacheDir().getPath() + File.separator + "mipush_icon");
        if (file.exists()) {
            if (a == 0) {
                a = t9.a(file);
            }
            if (a > 15728640) {
                try {
                    File[] listFiles = file.listFiles();
                    for (int i2 = 0; i2 < listFiles.length; i2++) {
                        if (!listFiles[i2].isDirectory() && Math.abs(System.currentTimeMillis() - listFiles[i2].lastModified()) > 1209600) {
                            listFiles[i2].delete();
                        }
                    }
                } catch (Exception e2) {
                    g.j.a.a.a.c.s(e2);
                }
                a = 0L;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:30:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v10, types: [java.io.OutputStream, java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v7, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void f(Context context, byte[] bArr, String str) {
        Closeable closeable;
        if (bArr == null) {
            g.j.a.a.a.c.o("cannot save small icon cause bitmap is null");
            return;
        }
        e(context);
        ?? file = new File(context.getCacheDir().getPath() + File.separator + "mipush_icon");
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File((File) file, com.xiaomi.push.p0.b(str));
        BufferedOutputStream bufferedOutputStream = null;
        try {
            try {
                if (!file2.exists()) {
                    file2.createNewFile();
                }
                file = new FileOutputStream(file2);
                try {
                    BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(file);
                    try {
                        bufferedOutputStream2.write(bArr);
                        bufferedOutputStream2.flush();
                        u9.b(bufferedOutputStream2);
                        closeable = file;
                    } catch (Exception e2) {
                        e = e2;
                        bufferedOutputStream = bufferedOutputStream2;
                        g.j.a.a.a.c.s(e);
                        u9.b(bufferedOutputStream);
                        closeable = file;
                        u9.b(closeable);
                        file = (a > 0L ? 1 : (a == 0L ? 0 : -1));
                        if (file != 0) {
                        }
                    } catch (Throwable th) {
                        th = th;
                        bufferedOutputStream = bufferedOutputStream2;
                        u9.b(bufferedOutputStream);
                        u9.b(file);
                        throw th;
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            } catch (Exception e4) {
                e = e4;
                file = 0;
            } catch (Throwable th2) {
                th = th2;
                file = 0;
            }
            u9.b(closeable);
            file = (a > 0L ? 1 : (a == 0L ? 0 : -1));
            if (file != 0) {
                a = t9.a(new File(context.getCacheDir().getPath() + File.separator + "mipush_icon")) + file2.length();
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    private static Bitmap g(Context context, String str) {
        Throwable th;
        FileInputStream fileInputStream;
        Bitmap bitmap;
        File file = new File(context.getCacheDir().getPath() + File.separator + "mipush_icon", com.xiaomi.push.p0.b(str));
        FileInputStream fileInputStream2 = null;
        Bitmap bitmap2 = null;
        fileInputStream2 = null;
        if (!file.exists()) {
            return null;
        }
        try {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    bitmap2 = BitmapFactory.decodeStream(fileInputStream);
                    file.setLastModified(System.currentTimeMillis());
                    u9.b(fileInputStream);
                    return bitmap2;
                } catch (Exception e2) {
                    e = e2;
                    Bitmap bitmap3 = bitmap2;
                    fileInputStream2 = fileInputStream;
                    bitmap = bitmap3;
                    g.j.a.a.a.c.s(e);
                    u9.b(fileInputStream2);
                    return bitmap;
                } catch (Throwable th2) {
                    th = th2;
                    u9.b(fileInputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                FileInputStream fileInputStream3 = fileInputStream2;
                th = th3;
                fileInputStream = fileInputStream3;
            }
        } catch (Exception e3) {
            e = e3;
            bitmap = null;
        }
    }
}
