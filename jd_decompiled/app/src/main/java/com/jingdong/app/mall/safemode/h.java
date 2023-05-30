package com.jingdong.app.mall.safemode;

import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class h {

    /* loaded from: classes4.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Thread.sleep(500L);
            } catch (Throwable unused) {
            }
            Process.killProcess(Process.myPid());
        }
    }

    public static void a() {
        new Thread(new a()).start();
    }

    public static JSONObject b(File file) {
        String c2 = c(file);
        if (TextUtils.isEmpty(c2)) {
            return new JSONObject();
        }
        try {
            return new JSONObject(c2);
        } catch (Throwable unused) {
            return new JSONObject();
        }
    }

    public static String c(File file) {
        BufferedReader bufferedReader = null;
        if (file == null || !file.exists()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file));
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine == null) {
                            break;
                        }
                        sb.append(readLine);
                    } catch (Throwable unused) {
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        return sb.toString();
                    }
                }
                bufferedReader2.close();
            } catch (Throwable unused2) {
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return sb.toString();
    }

    public static JSONArray d(JSONArray jSONArray, int i2) {
        if (jSONArray != null && i2 >= 0 && i2 < jSONArray.length()) {
            if (Build.VERSION.SDK_INT >= 19) {
                jSONArray.remove(i2);
                return jSONArray;
            }
            JSONArray jSONArray2 = new JSONArray();
            for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                try {
                    if (i3 != i2) {
                        jSONArray2.put(jSONArray.get(i3));
                    }
                } catch (Throwable unused) {
                }
            }
            return jSONArray2;
        }
        return jSONArray;
    }

    public static void e(String str, File file) {
        if (file == null) {
            return;
        }
        FileWriter fileWriter = null;
        try {
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fileWriter2 = new FileWriter(file);
                try {
                    fileWriter2.write(str);
                    fileWriter2.close();
                } catch (Throwable th) {
                    th = th;
                    fileWriter = fileWriter2;
                    try {
                        g.b("safemodefile", th.toString());
                        if (fileWriter != null) {
                            fileWriter.close();
                        }
                    } catch (Throwable th2) {
                        if (fileWriter != null) {
                            try {
                                fileWriter.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }
}
