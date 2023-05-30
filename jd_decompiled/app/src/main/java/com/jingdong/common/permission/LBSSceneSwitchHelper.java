package com.jingdong.common.permission;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.threadpool.ThreadManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class LBSSceneSwitchHelper {
    private static final String KEY_ALL_SCENE_SWITCH = "allLbsSceneSwitch";
    private static final String TAG = "LBSSceneSwitchHelper";
    private static JSONObject sCurrentStatus;
    private static final String FILE_NAME_LBS_SCENE_SWITCH_DIR = "lbsSceneSwitch";
    private static final String FILE_NAME_LBS_SCENE_SWITCH_PRIVACY = "lbsSceneSwitchFile";
    private static File sFile = new File(JdSdk.getInstance().getApplicationContext().getFilesDir() + File.separator + FILE_NAME_LBS_SCENE_SWITCH_DIR, FILE_NAME_LBS_SCENE_SWITCH_PRIVACY);

    public static synchronized void appUpgradeInit(boolean z) {
        synchronized (LBSSceneSwitchHelper.class) {
            if (z) {
                if (!sFile.exists() && PermissionHelper.hasGrantedLocation(new Bundle())) {
                    JSONObject jSONObj = getJSONObj();
                    jSONObj.put("basicShoppingProcess", true);
                    jSONObj.put("locService", true);
                    jSONObj.put("marketingActivities", true);
                    jSONObj.put("receiveAddress", true);
                    saveLbsSceneSwitchAsync();
                }
            }
        }
    }

    private static synchronized JSONObject getJSONObj() {
        JSONObject jSONObject;
        synchronized (LBSSceneSwitchHelper.class) {
            if (sCurrentStatus == null) {
                try {
                    sCurrentStatus = new JSONObject(getValueFromFile());
                } catch (Exception e2) {
                    e2.printStackTrace();
                    sCurrentStatus = new JSONObject();
                }
            }
            jSONObject = sCurrentStatus;
        }
        return jSONObject;
    }

    public static synchronized boolean getLbsSceneSwitch(String str) {
        boolean z;
        synchronized (LBSSceneSwitchHelper.class) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            JSONObject jSONObj = getJSONObj();
            if (jSONObj.has(str)) {
                z = jSONObj.optBoolean(str);
            } else {
                boolean optBoolean = jSONObj.has(KEY_ALL_SCENE_SWITCH) ? jSONObj.optBoolean(KEY_ALL_SCENE_SWITCH) : false;
                try {
                    jSONObj.put(str, optBoolean);
                    saveLbsSceneSwitchAsync();
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                z = optBoolean;
            }
            return z;
        }
    }

    private static synchronized String getValueFromFile() {
        BufferedReader bufferedReader;
        Throwable th;
        synchronized (LBSSceneSwitchHelper.class) {
            File file = sFile;
            if (file != null && file.exists()) {
                StringBuilder sb = new StringBuilder();
                try {
                    bufferedReader = new BufferedReader(new FileReader(sFile));
                    while (true) {
                        try {
                            String readLine = bufferedReader.readLine();
                            if (readLine != null) {
                                sb.append(readLine);
                            } else {
                                try {
                                    break;
                                } catch (IOException e2) {
                                    e = e2;
                                    e.printStackTrace();
                                    return sb.toString().trim();
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            th.printStackTrace();
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e3) {
                                    e = e3;
                                    e.printStackTrace();
                                    return sb.toString().trim();
                                }
                            }
                            return sb.toString().trim();
                        }
                    }
                    bufferedReader.close();
                } catch (Throwable th3) {
                    bufferedReader = null;
                    th = th3;
                }
                return sb.toString().trim();
            }
            return "";
        }
    }

    public static synchronized void saveAllLbsSceneSwtich(boolean z) {
        synchronized (LBSSceneSwitchHelper.class) {
            JSONObject jSONObj = getJSONObj();
            if (jSONObj.has(KEY_ALL_SCENE_SWITCH) && jSONObj.optBoolean(KEY_ALL_SCENE_SWITCH) == z && !z) {
                return;
            }
            jSONObj.put(KEY_ALL_SCENE_SWITCH, z);
            if (z) {
                Iterator<String> keys = jSONObj.keys();
                while (keys.hasNext()) {
                    jSONObj.put(keys.next(), true);
                }
            }
            saveLbsSceneSwitchAsync();
        }
    }

    public static synchronized void saveLbsSceneSwitch(String str, boolean z) {
        synchronized (LBSSceneSwitchHelper.class) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            JSONObject jSONObj = getJSONObj();
            if (jSONObj.has(str) && jSONObj.optBoolean(str) == z) {
                return;
            }
            jSONObj.put(str, z);
            saveLbsSceneSwitchAsync();
        }
    }

    private static void saveLbsSceneSwitchAsync() {
        ThreadManager.light().post(new Runnable() { // from class: com.jingdong.common.permission.LBSSceneSwitchHelper.1
            @Override // java.lang.Runnable
            public void run() {
                LBSSceneSwitchHelper.saveValueToFile();
            }
        });
    }

    public static synchronized void saveValueToFile() {
        FileWriter fileWriter;
        synchronized (LBSSceneSwitchHelper.class) {
            File file = sFile;
            if (file == null) {
                return;
            }
            FileWriter fileWriter2 = null;
            try {
                if (!file.exists()) {
                    File parentFile = sFile.getParentFile();
                    if (parentFile != null) {
                        parentFile.mkdirs();
                    }
                    sFile.createNewFile();
                }
                fileWriter = new FileWriter(sFile);
            } catch (Throwable th) {
                th = th;
            }
            try {
                fileWriter.write(getJSONObj().toString());
            } catch (Throwable th2) {
                fileWriter2 = fileWriter;
                th = th2;
                th.printStackTrace();
                if (fileWriter2 != null) {
                    try {
                        fileWriter2.close();
                    } catch (IOException e2) {
                        e = e2;
                        e.printStackTrace();
                    }
                }
            }
            try {
                fileWriter.close();
            } catch (IOException e3) {
                e = e3;
                e.printStackTrace();
            }
        }
    }

    public static void startSceneActivity(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(context, LbsScenePermissionActivity.class);
        context.startActivity(intent);
    }
}
