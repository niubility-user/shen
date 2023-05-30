package com.jd.android.sdk.coreinfo.util;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/* loaded from: classes.dex */
public class FileReaderUtil {
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0040, code lost:
        if (r1 == null) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String read(String str, boolean z) {
        BufferedReader bufferedReader;
        StringBuffer stringBuffer = new StringBuffer();
        File file = new File(str);
        if (file.exists()) {
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
            } catch (Throwable unused) {
                bufferedReader = null;
            }
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        if (z) {
                            readLine = readLine + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
                        }
                        stringBuffer.append(readLine);
                    }
                } catch (Throwable unused2) {
                }
                try {
                    break;
                } catch (IOException unused3) {
                }
            }
            bufferedReader.close();
        }
        String trim = stringBuffer.toString().trim();
        return (z && trim.endsWith(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE)) ? trim.substring(0, trim.length() - 2) : trim;
    }
}
