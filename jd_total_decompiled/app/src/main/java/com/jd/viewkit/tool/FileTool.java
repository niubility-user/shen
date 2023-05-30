package com.jd.viewkit.tool;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* loaded from: classes18.dex */
public class FileTool {
    /* JADX WARN: Multi-variable type inference failed */
    public static String openJson(Context context, String str) {
        InputStream inputStream;
        InputStreamReader inputStreamReader;
        String str2;
        BufferedReader bufferedReader = null;
        String str3 = null;
        bufferedReader = null;
        try {
            inputStream = context.getAssets().open(str);
        } catch (Exception e2) {
            e = e2;
            inputStream = null;
            inputStreamReader = null;
        }
        try {
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            try {
                BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader);
                try {
                    StringBuffer stringBuffer = new StringBuffer("");
                    while (true) {
                        String readLine = bufferedReader2.readLine();
                        if (readLine != null) {
                            stringBuffer.append(readLine);
                        } else {
                            str3 = stringBuffer.toString().trim();
                            bufferedReader2.close();
                            inputStreamReader.close();
                            inputStream.close();
                            return str3;
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    str2 = str3;
                    bufferedReader = bufferedReader2;
                    e.printStackTrace();
                    try {
                        bufferedReader.close();
                    } catch (IOException unused) {
                    }
                    try {
                        inputStreamReader.close();
                    } catch (IOException unused2) {
                    }
                    try {
                        inputStream.close();
                    } catch (IOException unused3) {
                    }
                    return str2;
                }
            } catch (Exception e4) {
                e = e4;
                str2 = null;
            }
        } catch (Exception e5) {
            e = e5;
            inputStreamReader = null;
            str2 = inputStreamReader;
            e.printStackTrace();
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            return str2;
        }
    }
}
