package com.jingdong.app.mall.privacy;

import android.text.TextUtils;
import com.jingdong.jdsdk.JdSdk;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class JDPrivacyFile {
    private static final String PRIVACY_DIR = "jdPrivacyFile";
    private static final String PRIVACY_JSON = "jdPrivacyJson";
    private static final File sPrivacyFile = new File(JdSdk.getInstance().getApplication().getFilesDir() + File.separator + PRIVACY_DIR, PRIVACY_JSON);

    private static void close(Reader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private static String getFileString(File file) {
        BufferedReader bufferedReader = null;
        if (file == null || !file.exists()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file));
            while (true) {
                try {
                    String readLine = bufferedReader2.readLine();
                    if (readLine == null) {
                        break;
                    }
                    sb.append(readLine);
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    try {
                        th.printStackTrace();
                        return sb.toString().trim();
                    } finally {
                        close(bufferedReader);
                    }
                }
            }
            close(bufferedReader2);
        } catch (Throwable th2) {
            th = th2;
        }
        return sb.toString().trim();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized int getIntValue(String str) {
        synchronized (JDPrivacyFile.class) {
            String fileString = getFileString(sPrivacyFile);
            try {
                if (TextUtils.isEmpty(fileString)) {
                    return 0;
                }
                return new JSONObject(fileString).optInt(str, 0);
            } catch (Exception e2) {
                e2.printStackTrace();
                return 0;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void saveIntValue(String str, int i2) {
        synchronized (JDPrivacyFile.class) {
            File file = sPrivacyFile;
            String fileString = getFileString(file);
            try {
                JSONObject jSONObject = TextUtils.isEmpty(fileString) ? null : new JSONObject(fileString);
                if (jSONObject == null) {
                    jSONObject = new JSONObject();
                }
                jSONObject.put(str, i2);
                saveValueToFile(file, jSONObject.toString());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private static synchronized void saveValueToFile(File file, String str) {
        FileWriter fileWriter;
        synchronized (JDPrivacyFile.class) {
            if (file == null) {
                return;
            }
            FileWriter fileWriter2 = null;
            try {
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
                fileWriter = new FileWriter(file);
            } catch (Throwable th) {
                th = th;
            }
            try {
                fileWriter.write(str);
                close(fileWriter);
            } catch (Throwable th2) {
                th = th2;
                fileWriter2 = fileWriter;
                th.printStackTrace();
                close(fileWriter2);
            }
        }
    }

    private static void close(Writer writer) {
        if (writer != null) {
            try {
                writer.close();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
