package com.facebook.react.bridge;

import android.content.Context;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.download.PluginVersion;
import com.jingdong.common.jdreactFramework.utils.FileUtil;
import com.jingdong.common.jdreactFramework.utils.JDReactCustomException;
import com.jingdong.common.jdreactFramework.utils.ReactVersionUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;

/* loaded from: classes.dex */
public class JDReactJSLoadingModule {
    public static String files;
    public static PluginVersion mPluginVersion;

    public static String getFromAssets(Context context, String str) {
        if (context == null) {
            return "";
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getResources().getAssets().open(str)));
            String str2 = "";
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return str2;
                }
                if (!TextUtils.isEmpty(readLine)) {
                    str2 = str2 + readLine;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static boolean loadJS(Context context, CatalystInstanceImpl catalystInstanceImpl, String str, String str2, boolean z) {
        boolean z2;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (!str.startsWith("/data")) {
            String str3 = "assets://" + str + File.separator + str2;
            try {
                String[] list = context.getAssets().list(str);
                if (list != null && list.length > 0) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= list.length) {
                            z2 = false;
                            break;
                        } else if (str2.equals(list[i2])) {
                            z2 = true;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (z2) {
                        catalystInstanceImpl.loadJSFromAssets(context.getAssets(), str3, z);
                        return true;
                    }
                    return false;
                }
            } catch (IOException unused) {
            }
            return false;
        }
        String str4 = str + File.separator + str2;
        if (new File(str4).exists()) {
            catalystInstanceImpl.loadJSFromFile(str4, str4, z);
            return true;
        }
        return false;
    }

    public static boolean loadJSFromAssetsSynchronously(Context context, CatalystInstanceImpl catalystInstanceImpl, String str, String str2) {
        boolean z;
        if (!TextUtils.isEmpty(str) && !str.startsWith("/data")) {
            String str3 = "assets://" + str + File.separator + str2;
            try {
                String[] list = context.getAssets().list(str);
                if (list != null && list.length > 0) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= list.length) {
                            z = false;
                            break;
                        } else if (str2.equals(list[i2])) {
                            z = true;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (!z) {
                        return false;
                    }
                    try {
                        catalystInstanceImpl.loadJSFromAssetsSynchronously(context.getAssets(), str3);
                        return true;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (IOException unused) {
            }
        }
        return false;
    }

    public static boolean loadJSFromFileSynchronously(CatalystInstanceImpl catalystInstanceImpl, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        if (file.exists() && !file.isDirectory()) {
            try {
                catalystInstanceImpl.loadJSFromFileSynchronously(str, str, true);
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    public static boolean loadJSModule(Context context, CatalystInstanceImpl catalystInstanceImpl, String str, String str2, boolean z) {
        return loadJSModule(context, catalystInstanceImpl, str, str2, z, null);
    }

    public static String loadJSModuleAsString(Context context, CatalystInstanceImpl catalystInstanceImpl, String str, String str2) {
        int loadingType = catalystInstanceImpl.getLoadingType();
        if (loadingType == 0) {
            PluginVersion pluginPreloadDataPath = ReactVersionUtils.getPluginPreloadDataPath(context, str);
            if (pluginPreloadDataPath == null || TextUtils.isEmpty(pluginPreloadDataPath.pluginDir)) {
                return "";
            }
            String str3 = pluginPreloadDataPath.pluginDir + File.separator + str2;
            files = str3;
            return getFromAssets(context, str3);
        }
        if (loadingType == 1) {
            PluginVersion pluginVersionPath = ReactVersionUtils.getPluginVersionPath(true, str);
            PluginVersion pluginVersionPath2 = ReactVersionUtils.getPluginVersionPath(false, str);
            if (pluginVersionPath2.splitEnable) {
                pluginVersionPath = ReactVersionUtils.getNewPluginVersion(pluginVersionPath2, pluginVersionPath);
            }
            mPluginVersion = pluginVersionPath;
            if (pluginVersionPath != null && !TextUtils.isEmpty(pluginVersionPath.pluginDir)) {
                File file = new File(pluginVersionPath.pluginDir + File.separator + str2);
                return !file.exists() ? "" : readFileData(file);
            }
        }
        return "";
    }

    private static String readFileData(File file) {
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        StringBuffer stringBuffer = new StringBuffer();
        if (file == null || !file.exists()) {
            return "";
        }
        FileInputStream fileInputStream = null;
        try {
            try {
                FileInputStream fileInputStream2 = new FileInputStream(file);
                try {
                    inputStreamReader = new InputStreamReader(fileInputStream2);
                    try {
                        bufferedReader = new BufferedReader(inputStreamReader);
                        while (true) {
                            try {
                                String readLine = bufferedReader.readLine();
                                if (readLine != null) {
                                    if (!TextUtils.isEmpty(readLine)) {
                                        stringBuffer.append(readLine);
                                    }
                                } else {
                                    try {
                                        break;
                                    } catch (IOException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            } catch (FileNotFoundException unused) {
                                fileInputStream = fileInputStream2;
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e3) {
                                        e3.printStackTrace();
                                    }
                                }
                                if (inputStreamReader != null) {
                                    try {
                                        inputStreamReader.close();
                                    } catch (IOException e4) {
                                        e4.printStackTrace();
                                    }
                                }
                                if (bufferedReader != null) {
                                    bufferedReader.close();
                                }
                                return stringBuffer.toString();
                            } catch (IOException unused2) {
                                fileInputStream = fileInputStream2;
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e5) {
                                        e5.printStackTrace();
                                    }
                                }
                                if (inputStreamReader != null) {
                                    try {
                                        inputStreamReader.close();
                                    } catch (IOException e6) {
                                        e6.printStackTrace();
                                    }
                                }
                                if (bufferedReader != null) {
                                    bufferedReader.close();
                                }
                                return stringBuffer.toString();
                            } catch (Throwable th) {
                                th = th;
                                fileInputStream = fileInputStream2;
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e7) {
                                        e7.printStackTrace();
                                    }
                                }
                                if (inputStreamReader != null) {
                                    try {
                                        inputStreamReader.close();
                                    } catch (IOException e8) {
                                        e8.printStackTrace();
                                    }
                                }
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (IOException e9) {
                                        e9.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                        }
                        fileInputStream2.close();
                        if (inputStreamReader != null) {
                            try {
                                inputStreamReader.close();
                            } catch (IOException e10) {
                                e10.printStackTrace();
                            }
                        }
                    } catch (FileNotFoundException unused3) {
                        bufferedReader = null;
                    } catch (IOException unused4) {
                        bufferedReader = null;
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedReader = null;
                    }
                } catch (FileNotFoundException unused5) {
                    inputStreamReader = null;
                    bufferedReader = null;
                } catch (IOException unused6) {
                    inputStreamReader = null;
                    bufferedReader = null;
                } catch (Throwable th3) {
                    th = th3;
                    inputStreamReader = null;
                    bufferedReader = null;
                }
            } catch (FileNotFoundException unused7) {
                inputStreamReader = null;
                bufferedReader = null;
            } catch (IOException unused8) {
                inputStreamReader = null;
                bufferedReader = null;
            } catch (Throwable th4) {
                th = th4;
                inputStreamReader = null;
                bufferedReader = null;
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        } catch (IOException e11) {
            e11.printStackTrace();
        }
        return stringBuffer.toString();
    }

    private static boolean validateFileMd5(String str, String str2, Map<String, String> map, WritableMap writableMap) {
        if (TextUtils.isEmpty(str) || map == null) {
            return true;
        }
        String str3 = map.get("md5");
        if (TextUtils.isEmpty(str3)) {
            return true;
        }
        try {
            String fileMD5ForThrow = FileUtil.getFileMD5ForThrow(new File(str));
            if (TextUtils.equals(str3, fileMD5ForThrow)) {
                return true;
            }
            ArrayMap arrayMap = new ArrayMap();
            arrayMap.put("moduleName", str2);
            arrayMap.put("appName", str2);
            arrayMap.put("version", map.get("version"));
            String str4 = "validateFileMd5 failed file path is " + str + ", md5 is " + str3 + ", targetMd5 is " + fileMD5ForThrow + ", version is " + map.get("version");
            JDReactHelper.newInstance().postException(new JDReactCustomException(str4), arrayMap);
            writeError(writableMap, str4);
            return false;
        } catch (Exception e2) {
            ArrayMap arrayMap2 = new ArrayMap();
            arrayMap2.put("moduleName", str2);
            arrayMap2.put("appName", str2);
            arrayMap2.put("version", map.get("version"));
            JDReactHelper.newInstance().postException(new JDReactCustomException(e2), arrayMap2);
            writeError(writableMap, "validateFileMd5 exception is " + e2.getMessage());
            return false;
        }
    }

    private static void writeError(WritableMap writableMap, String str) {
        if (writableMap != null) {
            writableMap.putString("errorMessage", str);
        }
    }

    public static boolean loadJSModule(Context context, CatalystInstanceImpl catalystInstanceImpl, String str, String str2, boolean z, WritableMap writableMap) {
        return loadJSModule(context, catalystInstanceImpl, str, str2, z, writableMap, null);
    }

    public static boolean loadJSModule(Context context, CatalystInstanceImpl catalystInstanceImpl, String str, String str2, boolean z, WritableMap writableMap, Map<String, String> map) {
        int loadingType = catalystInstanceImpl.getLoadingType();
        String sourceURL = catalystInstanceImpl.getSourceURL();
        if (!TextUtils.isEmpty(sourceURL)) {
            int lastIndexOf = sourceURL.lastIndexOf(47);
            if (lastIndexOf > 0) {
                String substring = sourceURL.substring(0, lastIndexOf);
                File file = new File(substring);
                String str3 = substring + File.separator + str2;
                if (loadingType == 0) {
                    catalystInstanceImpl.loadJSFromAssets(context.getAssets(), str3, z);
                    return true;
                } else if (loadingType == 1) {
                    if (!new File(str3).exists()) {
                        writeError(writableMap, "type is outer SourceURL is " + sourceURL + " and file is empty patch is " + str3 + " and dir list is " + Arrays.toString(file.list()));
                    } else if (validateFileMd5(str3, str, map, writableMap)) {
                        catalystInstanceImpl.loadJSFromFile(str3, str3, z);
                        return true;
                    }
                } else {
                    writeError(writableMap, "SourceURL is " + sourceURL + " load type error is " + loadingType);
                }
            } else {
                writeError(writableMap, "SourceURL is " + sourceURL);
            }
        } else if (loadingType == 0) {
            PluginVersion pluginPreloadDataPath = ReactVersionUtils.getPluginPreloadDataPath(context, str);
            if (pluginPreloadDataPath != null && !TextUtils.isEmpty(pluginPreloadDataPath.pluginDir)) {
                String str4 = "assets://" + pluginPreloadDataPath.pluginDir + File.separator + str2;
                files = str4;
                catalystInstanceImpl.loadJSFromAssets(context.getAssets(), str4, z);
                return true;
            }
            writeError(writableMap, "type is inner plugin dir is null");
        } else if (loadingType == 1) {
            PluginVersion pluginVersionPath = ReactVersionUtils.getPluginVersionPath(true, str);
            PluginVersion pluginVersionPath2 = ReactVersionUtils.getPluginVersionPath(false, str);
            if (pluginVersionPath2.splitEnable) {
                pluginVersionPath = ReactVersionUtils.getNewPluginVersion(pluginVersionPath2, pluginVersionPath);
            }
            mPluginVersion = pluginVersionPath;
            if (pluginVersionPath != null && !TextUtils.isEmpty(pluginVersionPath.pluginDir)) {
                String str5 = pluginVersionPath.pluginDir + File.separator + str2;
                if (!new File(str5).exists()) {
                    writeError(writableMap, "type is outer file not exists path is" + str5);
                } else if (validateFileMd5(str5, str, map, writableMap)) {
                    catalystInstanceImpl.loadJSFromFile(str5, str5, z);
                    return true;
                }
            } else {
                writeError(writableMap, "type is outer plugin dir is null");
            }
        } else {
            writeError(writableMap, "load type error is " + loadingType);
        }
        return false;
    }
}
