package g.j.b.c;

import android.text.TextUtils;
import com.xiaomi.push.u9;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.HashMap;

/* loaded from: classes11.dex */
public class g {
    private static g.j.b.a.c a(g.j.b.a.c cVar, String str) {
        long[] i2;
        if (cVar == null || (i2 = i(str)) == null) {
            return null;
        }
        cVar.f19675i = i2[0];
        cVar.f19676j = i2[1];
        return cVar;
    }

    private static g.j.b.a.c b(String str) {
        g.j.b.a.c cVar = null;
        try {
            String[] j2 = j(str);
            if (j2 == null || j2.length < 4 || TextUtils.isEmpty(j2[0]) || TextUtils.isEmpty(j2[1]) || TextUtils.isEmpty(j2[2]) || TextUtils.isEmpty(j2[3])) {
                return null;
            }
            cVar = g.j.b.a.c.e();
            cVar.a = Integer.parseInt(j2[0]);
            cVar.b = j2[1];
            cVar.f19677c = Integer.parseInt(j2[2]);
            cVar.f19674h = Integer.parseInt(j2[3]);
            return cVar;
        } catch (Exception unused) {
            g.j.a.a.a.c.B("parse per key error");
            return cVar;
        }
    }

    public static String c(g.j.b.a.c cVar) {
        return cVar.a + "#" + cVar.b + "#" + cVar.f19677c + "#" + cVar.f19674h;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v12, types: [int] */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r5v9, types: [java.lang.Object] */
    private static HashMap<String, String> d(String str) {
        HashMap hashMap = new HashMap();
        if (TextUtils.isEmpty(str) || !new File(str).exists()) {
            return hashMap;
        }
        BufferedReader bufferedReader = 0;
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new FileReader(str));
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine == null) {
                            break;
                        }
                        String[] split = readLine.split("%%%");
                        bufferedReader = split.length;
                        if (bufferedReader >= 2) {
                            bufferedReader = 0;
                            bufferedReader = 0;
                            if (!TextUtils.isEmpty(split[0]) && !TextUtils.isEmpty(split[1])) {
                                bufferedReader = split[0];
                                hashMap.put(bufferedReader, split[1]);
                            }
                        }
                    } catch (Exception e2) {
                        e = e2;
                        bufferedReader = bufferedReader2;
                        g.j.a.a.a.c.s(e);
                        u9.b(bufferedReader);
                        return hashMap;
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        u9.b(bufferedReader);
                        throw th;
                    }
                }
                u9.b(bufferedReader2);
            } catch (Exception e3) {
                e = e3;
            }
            return hashMap;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x00d5, code lost:
        if (r1 != null) goto L67;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00f4  */
    /* JADX WARN: Type inference failed for: r4v10, types: [java.io.Closeable, java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<java.lang.String> e(android.content.Context r7, java.lang.String r8) {
        /*
            Method dump skipped, instructions count: 249
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: g.j.b.c.g.e(android.content.Context, java.lang.String):java.util.List");
    }

    private static void f(String str, HashMap<String, String> hashMap) {
        BufferedWriter bufferedWriter;
        Throwable th;
        Exception e2;
        if (TextUtils.isEmpty(str) || hashMap == null || hashMap.size() == 0) {
            return;
        }
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
        } catch (Exception e3) {
            bufferedWriter = null;
            e2 = e3;
        } catch (Throwable th2) {
            bufferedWriter = null;
            th = th2;
            u9.b(bufferedWriter);
            throw th;
        }
        try {
            try {
                for (String str2 : hashMap.keySet()) {
                    bufferedWriter.write(str2 + "%%%" + hashMap.get(str2));
                    bufferedWriter.newLine();
                }
            } catch (Throwable th3) {
                th = th3;
                u9.b(bufferedWriter);
                throw th;
            }
        } catch (Exception e4) {
            e2 = e4;
            g.j.a.a.a.c.s(e2);
            u9.b(bufferedWriter);
        }
        u9.b(bufferedWriter);
    }

    public static void g(String str, g.j.b.a.d[] dVarArr) {
        RandomAccessFile randomAccessFile;
        if (dVarArr == null || dVarArr.length <= 0 || TextUtils.isEmpty(str)) {
            return;
        }
        FileLock fileLock = null;
        try {
            File file = new File(str + ".lock");
            u9.f(file);
            randomAccessFile = new RandomAccessFile(file, "rw");
            try {
                fileLock = randomAccessFile.getChannel().lock();
                HashMap<String, String> d = d(str);
                for (g.j.b.a.d dVar : dVarArr) {
                    if (dVar != null) {
                        String c2 = c((g.j.b.a.c) dVar);
                        long j2 = ((g.j.b.a.c) dVar).f19675i;
                        long j3 = ((g.j.b.a.c) dVar).f19676j;
                        if (!TextUtils.isEmpty(c2) && j2 > 0 && j3 >= 0) {
                            h(d, c2, j2, j3);
                        }
                    }
                }
                f(str, d);
                if (fileLock != null && fileLock.isValid()) {
                    try {
                        fileLock.release();
                    } catch (IOException e2) {
                        e = e2;
                        g.j.a.a.a.c.s(e);
                        u9.b(randomAccessFile);
                    }
                }
            } catch (Throwable unused) {
                try {
                    g.j.a.a.a.c.B("failed to write perf to file ");
                    if (fileLock != null && fileLock.isValid()) {
                        try {
                            fileLock.release();
                        } catch (IOException e3) {
                            e = e3;
                            g.j.a.a.a.c.s(e);
                            u9.b(randomAccessFile);
                        }
                    }
                    u9.b(randomAccessFile);
                } catch (Throwable th) {
                    if (fileLock != null && fileLock.isValid()) {
                        try {
                            fileLock.release();
                        } catch (IOException e4) {
                            g.j.a.a.a.c.s(e4);
                        }
                    }
                    u9.b(randomAccessFile);
                    throw th;
                }
            }
        } catch (Throwable unused2) {
            randomAccessFile = null;
        }
        u9.b(randomAccessFile);
    }

    private static void h(HashMap<String, String> hashMap, String str, long j2, long j3) {
        StringBuilder sb;
        String str2 = hashMap.get(str);
        if (TextUtils.isEmpty(str2)) {
            sb = new StringBuilder();
        } else {
            long[] i2 = i(str2);
            if (i2 == null || i2[0] <= 0 || i2[1] < 0) {
                sb = new StringBuilder();
            } else {
                j2 += i2[0];
                j3 += i2[1];
                sb = new StringBuilder();
            }
        }
        sb.append(j2);
        sb.append("#");
        sb.append(j3);
        hashMap.put(str, sb.toString());
    }

    protected static long[] i(String str) {
        long[] jArr = new long[2];
        try {
            String[] split = str.split("#");
            if (split.length >= 2) {
                jArr[0] = Long.parseLong(split[0].trim());
                jArr[1] = Long.parseLong(split[1].trim());
            }
            return jArr;
        } catch (Exception e2) {
            g.j.a.a.a.c.s(e2);
            return null;
        }
    }

    private static String[] j(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str.split("#");
    }
}
