package com.jingdong.common.jdreactFramework.utils;

import androidx.collection.ArrayMap;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.download.PluginVersion;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* loaded from: classes5.dex */
public class XmlUtils {
    private static final String TAG = "ReactNativeXmlUtils";

    /* JADX WARN: Code restructure failed: missing block: B:142:0x0095, code lost:
        if (r11 == null) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x0097, code lost:
        r11.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x009c, code lost:
        if (r11 == null) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x00a0, code lost:
        if (r11 == null) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x00a4, code lost:
        if (r11 == null) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x00a7, code lost:
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static PluginVersion analysisXml(InputStream inputStream) {
        PluginVersion pluginVersion = new PluginVersion();
        try {
            XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
            newInstance.setNamespaceAware(true);
            XmlPullParser newPullParser = newInstance.newPullParser();
            newPullParser.setInput(inputStream, "UTF-8");
            for (int i2 = -1; i2 != 1; i2 = newPullParser.next()) {
                if (i2 == 2) {
                    if (newPullParser.getName().equals("module")) {
                        String attributeValue = newPullParser.getAttributeValue(null, "moduleName");
                        String attributeValue2 = newPullParser.getAttributeValue(null, JDReactConstant.ModuleCode);
                        String attributeValue3 = newPullParser.getAttributeValue(null, JDReactConstant.COMMITID);
                        if (JDReactHelper.newInstance().isDebug()) {
                            JLog.d(TAG, "Module name is " + attributeValue + " version is " + attributeValue2);
                        }
                        String attributeValue4 = newPullParser.getAttributeValue(null, "commonVersion");
                        pluginVersion.pluginName = attributeValue;
                        pluginVersion.pluginCommonVersion = attributeValue4;
                        pluginVersion.pluginName = attributeValue;
                        if (attributeValue2 != null && attributeValue2.startsWith("9999999")) {
                            pluginVersion.pluginVersion = "9999999";
                            pluginVersion.testmodeVersion = attributeValue2;
                        } else {
                            pluginVersion.pluginVersion = attributeValue2;
                            pluginVersion.commitId = attributeValue3;
                        }
                    }
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused) {
                }
            }
            return pluginVersion;
        } catch (FileNotFoundException unused2) {
        } catch (IOException unused3) {
        } catch (XmlPullParserException unused4) {
        } catch (Exception unused5) {
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused6) {
                }
            }
            throw th;
        }
    }

    public static PluginVersion getPluginVersion(String str) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            File file = new File(str);
            if (file.exists()) {
                fileInputStream = new FileInputStream(file);
                try {
                    if (JDReactConstant.USE_JSON_VERSION_FILE.booleanValue()) {
                        PluginVersion parseVersionFile = parseVersionFile(fileInputStream, str);
                        try {
                            fileInputStream.close();
                        } catch (IOException unused) {
                        }
                        return parseVersionFile;
                    }
                    PluginVersion analysisXml = analysisXml(fileInputStream);
                    try {
                        fileInputStream.close();
                    } catch (IOException unused2) {
                    }
                    return analysisXml;
                } catch (FileNotFoundException unused3) {
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException unused4) {
                        }
                    }
                    return null;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream2 = fileInputStream;
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (IOException unused5) {
                        }
                    }
                    throw th;
                }
            }
            return null;
        } catch (FileNotFoundException unused6) {
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static PluginVersion parseVersionFile(InputStream inputStream, String str) {
        PluginVersion pluginVersion = new PluginVersion();
        try {
            byte[] bArr = new byte[inputStream.available()];
            inputStream.read(bArr);
            inputStream.close();
            JSONObject jSONObject = new JSONObject(new String(bArr));
            String optString = jSONObject.optString("moduleName");
            String optString2 = jSONObject.optString(JDReactConstant.ModuleCode);
            String optString3 = jSONObject.optString(JDReactConstant.COMMITID);
            String optString4 = jSONObject.optString("commonVersion");
            pluginVersion.pluginName = optString;
            pluginVersion.pluginCommonVersion = optString4;
            if (optString2 != null && optString2.startsWith("9999999")) {
                pluginVersion.pluginVersion = "9999999";
                pluginVersion.testmodeVersion = optString2;
            } else {
                JLog.d("XmlUtils", " commitId: " + optString3);
                pluginVersion.pluginVersion = optString2;
                pluginVersion.commitId = optString3;
            }
            if (JDReactHelper.newInstance().isDebug()) {
                JLog.d(TAG, "Module directory is" + str + "Module name is " + optString + " version is " + optString2);
            }
        } catch (IOException | JSONException unused) {
        }
        return pluginVersion;
    }

    public static Map<String, String> scanDownloadPluginPaths() {
        ArrayMap arrayMap = new ArrayMap();
        File file = JDReactConstant.ReactDownloadPath;
        if (file.exists()) {
            String[] list = file.list();
            if (list == null || list.length <= 0) {
                return null;
            }
            for (String str : list) {
                File file2 = new File(file, str);
                if (file2.isDirectory()) {
                    arrayMap.put(file2.getName(), file2.getAbsolutePath());
                }
            }
            return arrayMap;
        }
        return arrayMap;
    }

    public static Map<String, String> scanPluginPaths(File file) {
        ArrayMap arrayMap = new ArrayMap();
        if (file.exists()) {
            String[] list = file.list();
            if (list == null || list.length <= 0) {
                return null;
            }
            for (String str : list) {
                File file2 = new File(file, str);
                if (file2.isDirectory()) {
                    arrayMap.put(file2.getName(), file2.getAbsolutePath());
                }
            }
        }
        return arrayMap;
    }

    public static Map<String, String> scanPreloadVersion() {
        ArrayMap arrayMap = new ArrayMap();
        try {
            if (JDReactHelper.newInstance().isDebug()) {
                JLog.d(TAG, "Begin to scan so, try to get plugin list");
            }
            String[] list = JDReactHelper.newInstance().getApplicationContext().getAssets().list(JDReactConstant.ASSERT_DIR);
            if (list != null && list.length != 0) {
                for (String str : list) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(JDReactConstant.ASSERT_DIR);
                    String str2 = File.separator;
                    sb.append(str2);
                    sb.append(str);
                    arrayMap.put(str, sb.toString() + str2 + str + ".version");
                }
            }
            return arrayMap;
        } catch (Exception unused) {
            return null;
        }
    }

    public static Map<String, String> scanPluginPaths() {
        ArrayMap arrayMap = new ArrayMap();
        File file = JDReactConstant.ReactDownloadPath;
        if (file.exists()) {
            String[] list = file.list();
            if (list == null || list.length <= 0) {
                return null;
            }
            for (String str : list) {
                File file2 = new File(file, str);
                if (file2.isDirectory()) {
                    arrayMap.put(file2.getName(), file2.getAbsolutePath());
                }
            }
            return arrayMap;
        }
        return arrayMap;
    }

    public static PluginVersion getPluginVersion(InputStream inputStream) {
        try {
            if (JDReactConstant.USE_JSON_VERSION_FILE.booleanValue()) {
                PluginVersion parseVersionFile = parseVersionFile(inputStream);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused) {
                    }
                }
                return parseVersionFile;
            }
            PluginVersion analysisXml = analysisXml(inputStream);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused2) {
                }
            }
            return analysisXml;
        } catch (Exception unused3) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                    return null;
                } catch (IOException unused4) {
                    return null;
                }
            }
            return null;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused5) {
                }
            }
            throw th;
        }
    }

    public static PluginVersion parseVersionFile(InputStream inputStream) {
        PluginVersion pluginVersion = new PluginVersion();
        try {
            byte[] bArr = new byte[inputStream.available()];
            inputStream.read(bArr);
            inputStream.close();
            JSONObject jSONObject = new JSONObject(new String(bArr));
            String optString = jSONObject.optString("moduleName");
            String optString2 = jSONObject.optString(JDReactConstant.ModuleCode);
            String optString3 = jSONObject.optString(JDReactConstant.COMMITID);
            String optString4 = jSONObject.optString("commonVersion");
            pluginVersion.pluginName = optString;
            pluginVersion.pluginCommonVersion = optString4;
            if (optString2 != null && optString2.startsWith("9999999")) {
                pluginVersion.pluginVersion = "9999999";
                pluginVersion.testmodeVersion = optString2;
            } else {
                JLog.d("XmlUtils", " commitId: " + optString3);
                pluginVersion.pluginVersion = optString2;
                pluginVersion.commitId = optString3;
            }
        } catch (IOException | JSONException unused) {
        }
        return pluginVersion;
    }
}
