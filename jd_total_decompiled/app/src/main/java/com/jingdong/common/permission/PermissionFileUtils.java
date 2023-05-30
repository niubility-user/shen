package com.jingdong.common.permission;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.oklog.OKLog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class PermissionFileUtils {
    private static final String FILE_NAME_CLIPBOARD_PRIVACY = "clipBoardPermissionFile";
    private static final String FILE_NAME_PRIVACY = "permissionfilewith";
    private static final Object LOCK = new Object();
    private static final String SCENE_LOC_PATH = "locationScenePath0q4c";

    public static boolean getBooleanvalue(Context context, String str, boolean z) throws Exception {
        boolean z2;
        if (context == null) {
            return z;
        }
        JSONObject jSONObject = null;
        File file = new File(context.getFilesDir(), FILE_NAME_PRIVACY);
        if (file.exists() && file.isFile()) {
            String valueFromFile = getValueFromFile(file);
            if (!TextUtils.isEmpty(valueFromFile)) {
                try {
                    jSONObject = new JSONObject(valueFromFile);
                } catch (Exception e2) {
                    if (!OKLog.D) {
                        jSONObject = new JSONObject();
                    } else {
                        throw e2;
                    }
                }
            }
            z2 = jSONObject.optBoolean(str, z);
            if (OKLog.D) {
                OKLog.d("PermissionFileUtils", "exists - jsonObject : " + jSONObject.toString());
            }
        } else {
            z2 = context.getSharedPreferences(PermissionHelper.TAG, 0).getBoolean(str, z);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(str, z2);
            saveValueToFile(jSONObject2.toString(), file);
            if (OKLog.D) {
                OKLog.d("PermissionFileUtils", "jsonObject : " + jSONObject2.toString());
            }
        }
        if (OKLog.D) {
            OKLog.d("PermissionFileUtils", "status : " + z2);
        }
        return z2;
    }

    public static String getClipBoardValue(Context context, String str) {
        String str2 = "";
        if (context != null && !TextUtils.isEmpty(str)) {
            JSONObject jSONObject = null;
            try {
                File file = new File(context.getFilesDir(), FILE_NAME_CLIPBOARD_PRIVACY);
                if (file.exists() && file.isFile()) {
                    String valueFromFile = getValueFromFile(file);
                    if (!TextUtils.isEmpty(valueFromFile)) {
                        try {
                            jSONObject = new JSONObject(valueFromFile);
                        } catch (Exception e2) {
                            if (!OKLog.D) {
                                jSONObject = new JSONObject();
                            } else {
                                throw e2;
                            }
                        }
                    }
                    str2 = jSONObject.optString(str);
                    if (OKLog.D) {
                        OKLog.d("PermissionFileUtils", "getClipBoardValue - clipBoardValue - jsonObject : " + jSONObject.toString());
                    }
                } else {
                    str2 = context.getSharedPreferences(PermissionHelper.TAG, 0).getString(str, "");
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(str, str2);
                    saveValueToFile(jSONObject2.toString(), file);
                    if (OKLog.D) {
                        OKLog.d("PermissionFileUtils", "getClipBoardValue-jsonObject : " + jSONObject2.toString());
                    }
                }
                if (OKLog.D) {
                    OKLog.d("PermissionFileUtils", "getClipBoardValue-clipBoardValue : " + str2);
                }
            } catch (JSONException e3) {
                if (OKLog.D) {
                    e3.printStackTrace();
                }
            }
        }
        return str2;
    }

    public static int getIntValue(Context context, String str, int i2) throws Exception {
        int i3;
        if (context == null) {
            return i2;
        }
        JSONObject jSONObject = null;
        File file = new File(context.getFilesDir(), FILE_NAME_PRIVACY);
        if (file.exists() && file.isFile()) {
            String valueFromFile = getValueFromFile(file);
            if (!TextUtils.isEmpty(valueFromFile)) {
                try {
                    jSONObject = new JSONObject(valueFromFile);
                } catch (Exception e2) {
                    if (!OKLog.D) {
                        jSONObject = new JSONObject();
                    } else {
                        throw e2;
                    }
                }
            }
            i3 = jSONObject.optInt(str, i2);
            if (OKLog.D) {
                OKLog.d("PermissionFileUtils", "exists - jsonObject : " + jSONObject.toString());
            }
        } else {
            i3 = context.getSharedPreferences(PermissionHelper.TAG, 0).getInt(str, i2);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(str, i3);
            saveValueToFile(jSONObject2.toString(), file);
            if (OKLog.D) {
                OKLog.d("PermissionFileUtils", "jsonObject : " + jSONObject2.toString());
            }
        }
        if (OKLog.D) {
            OKLog.d("PermissionFileUtils", "status : " + i3);
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long getScenePreShowTime(String str) {
        synchronized (LOCK) {
            StringBuilder sb = new StringBuilder();
            sb.append(JdSdk.getInstance().getApplicationContext().getFilesDir());
            String str2 = File.separator;
            sb.append(str2);
            sb.append(SCENE_LOC_PATH);
            sb.append(str2);
            sb.append(str);
            File file = new File(sb.toString());
            if (file.exists()) {
                if (file.isDirectory()) {
                    File[] listFiles = file.listFiles();
                    if (listFiles == null) {
                        return 0L;
                    }
                    try {
                        return Long.parseLong(listFiles[0].getName());
                    } catch (Exception unused) {
                        return 0L;
                    }
                }
                return 0L;
            }
            return 0L;
        }
    }

    private static String getValueFromFile(File file) {
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
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        try {
                            th.printStackTrace();
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            return sb.toString().trim();
                        } catch (Throwable th2) {
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            throw th2;
                        }
                    }
                }
                bufferedReader2.close();
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        return sb.toString().trim();
    }

    public static synchronized void saveClipBoardValue(Context context, String str, String str2) {
        JSONObject jSONObject;
        synchronized (PermissionFileUtils.class) {
            if (context != null) {
                try {
                    context.getSharedPreferences(PermissionHelper.TAG, 0).edit().putString(str, str2).commit();
                } catch (JSONException e2) {
                    if (OKLog.D) {
                        e2.printStackTrace();
                    }
                }
            }
            File file = new File(context.getFilesDir(), FILE_NAME_CLIPBOARD_PRIVACY);
            String valueFromFile = getValueFromFile(file);
            if (!TextUtils.isEmpty(valueFromFile)) {
                try {
                    jSONObject = new JSONObject(valueFromFile);
                } catch (Exception e3) {
                    if (!OKLog.D) {
                        jSONObject = new JSONObject();
                    } else {
                        throw e3;
                    }
                }
            } else {
                jSONObject = new JSONObject();
            }
            jSONObject.put(str, str2);
            if (OKLog.D) {
                OKLog.d("PermissionFileUtils", "saveClipBoardValue key : " + str);
                OKLog.d("PermissionFileUtils", "saveClipBoardValue jsonString : " + str2);
                OKLog.d("PermissionFileUtils", "saveClipBoardValue jsonObject : " + jSONObject);
            }
            saveValueToFile(jSONObject.toString(), file);
        }
    }

    public static synchronized void savePrivacy(Context context, String str, boolean z) throws Exception {
        JSONObject jSONObject;
        synchronized (PermissionFileUtils.class) {
            if (context != null) {
                context.getSharedPreferences(PermissionHelper.TAG, 0).edit().putBoolean(str, z).commit();
            }
            File file = new File(context.getFilesDir(), FILE_NAME_PRIVACY);
            String valueFromFile = getValueFromFile(file);
            if (!TextUtils.isEmpty(valueFromFile)) {
                try {
                    jSONObject = new JSONObject(valueFromFile);
                } catch (Exception e2) {
                    if (!OKLog.D) {
                        jSONObject = new JSONObject();
                    } else {
                        throw e2;
                    }
                }
            } else {
                jSONObject = new JSONObject();
            }
            jSONObject.put(str, z);
            if (OKLog.D) {
                OKLog.d("PermissionFileUtils", "savePrivacy jsonString : " + jSONObject);
                OKLog.d("PermissionFileUtils", "savePrivacy key : " + str);
                OKLog.d("PermissionFileUtils", "savePrivacy status : " + z);
            }
            saveValueToFile(jSONObject.toString(), file);
        }
    }

    private static synchronized void saveValueToFile(String str, File file) {
        FileWriter fileWriter;
        synchronized (PermissionFileUtils.class) {
            if (file == null) {
                return;
            }
            FileWriter fileWriter2 = null;
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                fileWriter = new FileWriter(file);
            } catch (Throwable th) {
                th = th;
            }
            try {
                fileWriter.write(str);
                try {
                    fileWriter.close();
                } catch (IOException e2) {
                    e = e2;
                    e.printStackTrace();
                }
            } catch (Throwable th2) {
                th = th2;
                fileWriter2 = fileWriter;
                th.printStackTrace();
                if (fileWriter2 != null) {
                    try {
                        fileWriter2.close();
                    } catch (IOException e3) {
                        e = e3;
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void updateSceneShowTime(String str, long j2) {
        File[] listFiles;
        synchronized (LOCK) {
            File file = new File(JdSdk.getInstance().getApplicationContext().getFilesDir(), SCENE_LOC_PATH);
            if (!file.exists()) {
                file.mkdir();
            }
            File file2 = new File(file, str);
            if (!file2.exists()) {
                file2.mkdir();
            }
            if (file2.isDirectory() && (listFiles = file2.listFiles()) != null) {
                for (File file3 : listFiles) {
                    file3.delete();
                }
            }
            try {
                new File(file2, String.valueOf(j2)).createNewFile();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static synchronized void savePrivacy(Context context, String str, int i2) throws Exception {
        JSONObject jSONObject;
        synchronized (PermissionFileUtils.class) {
            if (context != null) {
                context.getSharedPreferences(PermissionHelper.TAG, 0).edit().putInt(str, i2).commit();
            }
            File file = new File(context.getFilesDir(), FILE_NAME_PRIVACY);
            String valueFromFile = getValueFromFile(file);
            if (!TextUtils.isEmpty(valueFromFile)) {
                try {
                    jSONObject = new JSONObject(valueFromFile);
                } catch (Exception e2) {
                    if (!OKLog.D) {
                        jSONObject = new JSONObject();
                    } else {
                        throw e2;
                    }
                }
            } else {
                jSONObject = new JSONObject();
            }
            jSONObject.put(str, i2);
            if (OKLog.D) {
                OKLog.d("PermissionFileUtils", "savePrivacy jsonString : " + jSONObject);
                OKLog.d("PermissionFileUtils", "savePrivacy key : " + str);
                OKLog.d("PermissionFileUtils", "savePrivacy status : " + i2);
            }
            saveValueToFile(jSONObject.toString(), file);
        }
    }
}
