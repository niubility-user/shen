package com.jingdong.jdma.common.utils;

import android.content.Context;
import android.preference.PreferenceManager;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes12.dex */
public class f {
    public static String a = "JDMtaUtils";

    public static String a(Context context, String str) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(str, "");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8, types: [java.io.BufferedReader] */
    public static String b(Context context, String str) {
        Throwable th;
        BufferedReader bufferedReader;
        Exception e2;
        String str2 = "";
        if (context != null && j.a(context) != null) {
            File file = new File(j.a(context).getAbsolutePath() + "/" + context.getPackageName() + "/" + a + "/" + str);
            if (!file.exists()) {
                return "";
            }
            ?? r5 = 0;
            try {
                try {
                    try {
                        bufferedReader = new BufferedReader(new FileReader(file));
                        while (true) {
                            try {
                                String readLine = bufferedReader.readLine();
                                if (readLine == null) {
                                    break;
                                }
                                str2 = str2 + readLine + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
                            } catch (Exception e3) {
                                e2 = e3;
                                e2.printStackTrace();
                                if (bufferedReader != null) {
                                    bufferedReader.close();
                                }
                                return str2;
                            }
                        }
                        int lastIndexOf = str2.lastIndexOf(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                        if (str2 != null && str2.length() >= lastIndexOf) {
                            str2 = str2.substring(0, lastIndexOf);
                        }
                        bufferedReader.close();
                        bufferedReader.close();
                    } catch (Exception e4) {
                        bufferedReader = null;
                        e2 = e4;
                    } catch (Throwable th2) {
                        th = th2;
                        if (r5 != 0) {
                            try {
                                r5.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            } catch (Throwable th3) {
                th = th3;
                r5 = str;
            }
        }
        return str2;
    }

    public static Map<String, String> a(String str) {
        HashMap hashMap = new HashMap();
        for (String str2 : str.split(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE)) {
            int indexOf = str2.indexOf("\t:\t");
            if (indexOf != -1) {
                hashMap.put(str2.substring(0, indexOf).trim(), str2.substring(indexOf + 3).trim());
            }
        }
        return hashMap;
    }
}
