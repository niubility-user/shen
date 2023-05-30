package com.jd.stat.common;

import android.text.TextUtils;
import android.util.Pair;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.mall.miaosha.MiaoShaPublicConstants;
import com.jingdong.common.utils.LangUtils;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* loaded from: classes18.dex */
public class i {
    /* JADX WARN: Code restructure failed: missing block: B:20:0x007f, code lost:
        if (r6 != null) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00b7, code lost:
        if (r6 == null) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00b9, code lost:
        r6.destroy();
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00bc, code lost:
        return r1;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00cc  */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.io.Closeable[]] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.io.Closeable[]] */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v12, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* JADX WARN: Type inference failed for: r9v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String[] a() {
        Process process;
        InputStreamReader inputStreamReader;
        InputStream inputStream;
        ?? r9;
        IOException e2;
        InputStreamReader inputStreamReader2;
        String[] strArr = {"", "", ""};
        InputStream inputStream2 = null;
        try {
            process = Runtime.getRuntime().exec("id -n");
        } catch (IOException e3) {
            inputStream = null;
            inputStreamReader = null;
            r9 = 0;
            e2 = e3;
            process = null;
        } catch (Throwable th) {
            th = th;
            process = null;
            inputStreamReader = null;
        }
        try {
            inputStream = process.getInputStream();
            try {
                inputStreamReader = new InputStreamReader(inputStream);
            } catch (IOException e4) {
                r9 = 0;
                e2 = e4;
                inputStreamReader = null;
            } catch (Throwable th2) {
                th = th2;
                inputStreamReader = null;
                r9 = 0;
            }
        } catch (IOException e5) {
            inputStreamReader = null;
            r9 = 0;
            e2 = e5;
            inputStream = null;
        } catch (Throwable th3) {
            th = th3;
            inputStreamReader = null;
            inputStreamReader2 = inputStreamReader;
            com.jd.stat.common.b.g.a(inputStream2, inputStreamReader, inputStreamReader2);
            if (process != null) {
            }
            throw th;
        }
        try {
            r9 = new BufferedReader(inputStreamReader);
        } catch (IOException e6) {
            r9 = 0;
            e2 = e6;
        } catch (Throwable th4) {
            th = th4;
            r9 = 0;
            inputStream2 = inputStream;
            inputStreamReader2 = r9;
            com.jd.stat.common.b.g.a(inputStream2, inputStreamReader, inputStreamReader2);
            if (process != null) {
            }
            throw th;
        }
        try {
            try {
                for (String str : r9.readLine().split(LangUtils.SINGLE_SPACE)) {
                    Pair<String, String> a = a(str);
                    if (TextUtils.equals("uid", (CharSequence) a.first)) {
                        strArr[0] = (String) a.second;
                    }
                    if (TextUtils.equals(MiaoShaPublicConstants.KEY_GID, (CharSequence) a.first)) {
                        strArr[1] = (String) a.second;
                    }
                    if (TextUtils.equals(AnnoConst.Constructor_Context, (CharSequence) a.first)) {
                        strArr[2] = (String) a.second;
                    }
                }
                com.jd.stat.common.b.g.a((Closeable[]) new Closeable[]{inputStream, inputStreamReader, r9});
            } catch (Throwable th5) {
                th = th5;
                inputStream2 = inputStream;
                inputStreamReader2 = r9;
                com.jd.stat.common.b.g.a(inputStream2, inputStreamReader, inputStreamReader2);
                if (process != null) {
                    process.destroy();
                }
                throw th;
            }
        } catch (IOException e7) {
            e2 = e7;
            e2.printStackTrace();
            com.jd.stat.common.b.g.a((Closeable[]) new Closeable[]{inputStream, inputStreamReader, r9});
        }
    }

    private static Pair<String, String> a(String str) {
        if (TextUtils.isEmpty(str)) {
            return new Pair<>("", "");
        }
        String[] split = str.split(ContainerUtils.KEY_VALUE_DELIMITER);
        if (split.length != 2) {
            return new Pair<>("", "");
        }
        return new Pair<>(split[0], split[1]);
    }
}
