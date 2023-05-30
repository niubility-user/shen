package g.j.b.c;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Base64;
import com.jd.dynamic.DYConstants;
import com.xiaomi.push.p0;
import com.xiaomi.push.t0;
import com.xiaomi.push.u9;
import com.xiaomi.push.y5;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* loaded from: classes11.dex */
public class c implements a {
    protected Context a;
    private HashMap<String, ArrayList<g.j.b.a.d>> b;

    public c(Context context) {
        f(context);
    }

    public static String d(g.j.b.a.d dVar) {
        return String.valueOf(dVar.a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x003a, code lost:
        g.j.a.a.a.c.D(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0063, code lost:
        r9 = "eventData read from cache file failed cause lengthBuffer < 1 || lengthBuffer > 4K";
        r4 = r4;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v18 */
    /* JADX WARN: Type inference failed for: r4v20 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.List<java.lang.String> e(java.lang.String r9) {
        /*
            r8 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 4
            byte[] r2 = new byte[r1]
            byte[] r3 = new byte[r1]
            r4 = 0
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            java.io.File r6 = new java.io.File     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            r6.<init>(r9)     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
        L15:
            int r9 = r5.read(r2)     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6d
            r4 = -1
            if (r9 != r4) goto L1d
            goto L66
        L1d:
            java.lang.String r6 = "eventData read from cache file failed because magicNumber error"
            if (r9 == r1) goto L25
        L21:
            g.j.a.a.a.c.D(r6)     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6d
            goto L66
        L25:
            int r9 = com.xiaomi.push.c.a(r2)     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6d
            r7 = -573785174(0xffffffffddccbbaa, float:-1.84407149E18)
            if (r9 == r7) goto L2f
            goto L21
        L2f:
            int r9 = r5.read(r3)     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6d
            if (r9 != r4) goto L36
            goto L66
        L36:
            if (r9 == r1) goto L3e
            java.lang.String r9 = "eventData read from cache file failed cause lengthBuffer error"
        L3a:
            g.j.a.a.a.c.D(r9)     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6d
            goto L66
        L3e:
            int r9 = com.xiaomi.push.c.a(r3)     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6d
            r4 = 1
            if (r9 < r4) goto L63
            r4 = 4096(0x1000, float:5.74E-42)
            if (r9 <= r4) goto L4a
            goto L63
        L4a:
            byte[] r4 = new byte[r9]     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6d
            int r6 = r5.read(r4)     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6d
            if (r6 == r9) goto L55
            java.lang.String r9 = "eventData read from cache file failed cause buffer size not equal length"
            goto L3a
        L55:
            java.lang.String r9 = r8.m(r4)     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6d
            boolean r4 = android.text.TextUtils.isEmpty(r9)     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6d
            if (r4 != 0) goto L15
            r0.add(r9)     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6d
            goto L15
        L63:
            java.lang.String r9 = "eventData read from cache file failed cause lengthBuffer < 1 || lengthBuffer > 4K"
            goto L3a
        L66:
            com.xiaomi.push.u9.b(r5)
            goto L79
        L6a:
            r9 = move-exception
            r4 = r5
            goto L7a
        L6d:
            r9 = move-exception
            r4 = r5
            goto L73
        L70:
            r9 = move-exception
            goto L7a
        L72:
            r9 = move-exception
        L73:
            g.j.a.a.a.c.s(r9)     // Catch: java.lang.Throwable -> L70
            com.xiaomi.push.u9.b(r4)
        L79:
            return r0
        L7a:
            com.xiaomi.push.u9.b(r4)
            goto L7f
        L7e:
            throw r9
        L7f:
            goto L7e
        */
        throw new UnsupportedOperationException("Method not decompiled: g.j.b.c.c.e(java.lang.String):java.util.List");
    }

    private void g(RandomAccessFile randomAccessFile, FileLock fileLock) {
        if (fileLock != null && fileLock.isValid()) {
            try {
                fileLock.release();
            } catch (IOException e2) {
                g.j.a.a.a.c.s(e2);
            }
        }
        u9.b(randomAccessFile);
    }

    private void h(String str, String str2) {
        g.j.b.a.b d = g.j.b.b.b.e(this.a).d(5001, "24:" + str + DYConstants.DY_REGEX_COMMA + str2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(d.d());
        i(arrayList);
    }

    private g.j.b.a.d[] k(g.j.b.a.d[] dVarArr) {
        FileLock fileLock;
        RandomAccessFile randomAccessFile;
        BufferedOutputStream bufferedOutputStream;
        String l2 = l(dVarArr[0]);
        BufferedOutputStream bufferedOutputStream2 = null;
        if (TextUtils.isEmpty(l2)) {
            return null;
        }
        try {
            File file = new File(l2 + ".lock");
            u9.f(file);
            randomAccessFile = new RandomAccessFile(file, "rw");
            try {
                fileLock = randomAccessFile.getChannel().lock();
                try {
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(l2), true));
                } catch (Exception e2) {
                    e = e2;
                    bufferedOutputStream = null;
                } catch (Throwable th) {
                    th = th;
                    u9.b(bufferedOutputStream2);
                    g(randomAccessFile, fileLock);
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                fileLock = null;
                bufferedOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                fileLock = null;
            }
        } catch (Exception e4) {
            e = e4;
            fileLock = null;
            randomAccessFile = null;
            bufferedOutputStream = null;
        } catch (Throwable th3) {
            th = th3;
            fileLock = null;
            randomAccessFile = null;
        }
        try {
            try {
                int i2 = 0;
                for (g.j.b.a.d dVar : dVarArr) {
                    if (dVar != null) {
                        byte[] n2 = n(dVar.d());
                        if (n2 != null && n2.length >= 1 && n2.length <= 4096) {
                            if (!t0.d(this.a, l2)) {
                                int length = dVarArr.length - i2;
                                g.j.b.a.d[] dVarArr2 = new g.j.b.a.d[length];
                                System.arraycopy(dVarArr, i2, dVarArr2, 0, length);
                                u9.b(bufferedOutputStream);
                                g(randomAccessFile, fileLock);
                                return dVarArr2;
                            }
                            bufferedOutputStream.write(com.xiaomi.push.c.b(-573785174));
                            bufferedOutputStream.write(com.xiaomi.push.c.b(n2.length));
                            bufferedOutputStream.write(n2);
                            bufferedOutputStream.flush();
                            i2++;
                        }
                        g.j.a.a.a.c.D("event data throw a invalid item ");
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                bufferedOutputStream2 = bufferedOutputStream;
                u9.b(bufferedOutputStream2);
                g(randomAccessFile, fileLock);
                throw th;
            }
        } catch (Exception e5) {
            e = e5;
            g.j.a.a.a.c.q("event data write to cache file failed cause exception", e);
            u9.b(bufferedOutputStream);
            g(randomAccessFile, fileLock);
            return null;
        }
        u9.b(bufferedOutputStream);
        g(randomAccessFile, fileLock);
        return null;
    }

    private String l(g.j.b.a.d dVar) {
        File externalFilesDir = this.a.getExternalFilesDir("event");
        String d = d(dVar);
        if (externalFilesDir == null) {
            return null;
        }
        String str = externalFilesDir.getAbsolutePath() + File.separator + d;
        for (int i2 = 0; i2 < 100; i2++) {
            String str2 = str + i2;
            if (t0.d(this.a, str2)) {
                return str2;
            }
        }
        return null;
    }

    @Override // g.j.b.c.e
    public void a() {
        int i2;
        RandomAccessFile randomAccessFile;
        t0.c(this.a, "event", "eventUploading");
        File[] f2 = t0.f(this.a, "eventUploading");
        if (f2 == null || f2.length <= 0) {
            return;
        }
        int length = f2.length;
        FileLock fileLock = null;
        RandomAccessFile randomAccessFile2 = null;
        File file = null;
        while (i2 < length) {
            File file2 = f2[i2];
            if (file2 == null) {
                if (fileLock != null && fileLock.isValid()) {
                    try {
                        fileLock.release();
                    } catch (IOException e2) {
                        g.j.a.a.a.c.s(e2);
                    }
                }
                u9.b(randomAccessFile2);
                i2 = file == null ? i2 + 1 : 0;
                file.delete();
            } else {
                try {
                    try {
                    } catch (Throwable th) {
                        th = th;
                    }
                } catch (Exception e3) {
                    e = e3;
                }
                if (file2.length() > 5242880) {
                    g.j.a.a.a.c.D("eventData read from cache file failed because " + file2.getName() + " is too big, length " + file2.length());
                    h(file2.getName(), Formatter.formatFileSize(this.a, file2.length()));
                    file2.delete();
                    if (fileLock != null && fileLock.isValid()) {
                        try {
                            fileLock.release();
                        } catch (IOException e4) {
                            g.j.a.a.a.c.s(e4);
                        }
                    }
                    u9.b(randomAccessFile2);
                    if (file == null) {
                    }
                    file.delete();
                } else {
                    String absolutePath = file2.getAbsolutePath();
                    File file3 = new File(absolutePath + ".lock");
                    try {
                        u9.f(file3);
                        randomAccessFile = new RandomAccessFile(file3, "rw");
                    } catch (Exception e5) {
                        e = e5;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                    try {
                        fileLock = randomAccessFile.getChannel().lock();
                        i(e(absolutePath));
                        file2.delete();
                        if (fileLock != null && fileLock.isValid()) {
                            try {
                                fileLock.release();
                            } catch (IOException e6) {
                                g.j.a.a.a.c.s(e6);
                            }
                        }
                        u9.b(randomAccessFile);
                        file3.delete();
                        randomAccessFile2 = randomAccessFile;
                        file = file3;
                    } catch (Exception e7) {
                        e = e7;
                        randomAccessFile2 = randomAccessFile;
                        file = file3;
                        g.j.a.a.a.c.s(e);
                        if (fileLock != null && fileLock.isValid()) {
                            try {
                                fileLock.release();
                            } catch (IOException e8) {
                                g.j.a.a.a.c.s(e8);
                            }
                        }
                        u9.b(randomAccessFile2);
                        if (file == null) {
                        }
                        file.delete();
                    } catch (Throwable th3) {
                        th = th3;
                        randomAccessFile2 = randomAccessFile;
                        file = file3;
                        if (fileLock != null && fileLock.isValid()) {
                            try {
                                fileLock.release();
                            } catch (IOException e9) {
                                g.j.a.a.a.c.s(e9);
                            }
                        }
                        u9.b(randomAccessFile2);
                        if (file != null) {
                            file.delete();
                        }
                        throw th;
                    }
                }
            }
        }
    }

    @Override // g.j.b.c.a
    public void a(HashMap<String, ArrayList<g.j.b.a.d>> hashMap) {
        this.b = hashMap;
    }

    @Override // g.j.b.c.f
    public void b() {
        HashMap<String, ArrayList<g.j.b.a.d>> hashMap = this.b;
        if (hashMap == null) {
            return;
        }
        if (hashMap.size() > 0) {
            Iterator<String> it = this.b.keySet().iterator();
            while (it.hasNext()) {
                ArrayList<g.j.b.a.d> arrayList = this.b.get(it.next());
                if (arrayList != null && arrayList.size() > 0) {
                    g.j.b.a.d[] dVarArr = new g.j.b.a.d[arrayList.size()];
                    arrayList.toArray(dVarArr);
                    j(dVarArr);
                }
            }
        }
        this.b.clear();
    }

    @Override // g.j.b.c.f
    public void c(g.j.b.a.d dVar) {
        if ((dVar instanceof g.j.b.a.b) && this.b != null) {
            g.j.b.a.b bVar = (g.j.b.a.b) dVar;
            String d = d(bVar);
            ArrayList<g.j.b.a.d> arrayList = this.b.get(d);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
            }
            arrayList.add(bVar);
            this.b.put(d, arrayList);
        }
    }

    public void f(Context context) {
        this.a = context;
    }

    public void i(List<String> list) {
        throw null;
    }

    public void j(g.j.b.a.d[] dVarArr) {
        if (dVarArr == null || dVarArr.length == 0 || dVarArr[0] == null) {
            g.j.a.a.a.c.o("event data write to cache file failed because data null");
            return;
        }
        do {
            dVarArr = k(dVarArr);
            if (dVarArr == null || dVarArr.length <= 0) {
                return;
            }
        } while (dVarArr[0] != null);
    }

    public String m(byte[] bArr) {
        byte[] e2;
        if (bArr != null && bArr.length >= 1) {
            if (!g.j.b.b.b.e(this.a).c().f()) {
                return p0.l(bArr);
            }
            String b = t0.b(this.a);
            if (!TextUtils.isEmpty(b) && (e2 = t0.e(b)) != null && e2.length > 0) {
                try {
                    return p0.l(Base64.decode(y5.b(e2, bArr), 2));
                } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e3) {
                    g.j.a.a.a.c.s(e3);
                }
            }
        }
        return null;
    }

    public byte[] n(String str) {
        byte[] e2;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (g.j.b.b.b.e(this.a).c().f()) {
            String b = t0.b(this.a);
            byte[] j2 = p0.j(str);
            if (!TextUtils.isEmpty(b) && j2 != null && j2.length > 1 && (e2 = t0.e(b)) != null) {
                try {
                    if (e2.length > 1) {
                        return y5.c(e2, Base64.encode(j2, 2));
                    }
                } catch (Exception e3) {
                    g.j.a.a.a.c.s(e3);
                }
            }
            return null;
        }
        return p0.j(str);
    }
}
