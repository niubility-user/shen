package com.jingdong.common.XView2.utils;

import android.content.Context;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* loaded from: classes5.dex */
public class FileUtil {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0039 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String getStringFromFile(Context context, File file) {
        StringBuilder sb = null;
        r0 = null;
        BufferedReader bufferedReader = null;
        sb = null;
        if (file != null) {
            if (context != null) {
                try {
                    StringBuilder sb2 = new StringBuilder();
                    try {
                        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                        while (true) {
                            try {
                                String readLine = bufferedReader2.readLine();
                                if (readLine == null) {
                                    break;
                                }
                                sb2.append(readLine);
                            } catch (Throwable unused) {
                                bufferedReader = bufferedReader2;
                                context = sb2;
                                if (bufferedReader != null) {
                                    bufferedReader.close();
                                    context = context;
                                }
                                sb = context;
                                if (sb == null) {
                                }
                            }
                        }
                        bufferedReader2.close();
                        context = sb2;
                    } catch (Throwable unused2) {
                        context = sb2;
                    }
                } catch (Throwable unused3) {
                    context = null;
                }
                sb = context;
            }
        }
        return sb == null ? sb.toString() : "";
    }
}
