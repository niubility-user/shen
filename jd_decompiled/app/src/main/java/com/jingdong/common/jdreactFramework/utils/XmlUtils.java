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

/* loaded from: classes5.dex */
public class XmlUtils {
    private static final String TAG = "ReactNativeXmlUtils";

    /* JADX WARN: Code restructure failed: missing block: B:85:0x0095, code lost:
        if (r11 == null) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0097, code lost:
        r11.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x009c, code lost:
        if (r11 == null) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x00a0, code lost:
        if (r11 == null) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x00a4, code lost:
        if (r11 == null) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x00a7, code lost:
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.jingdong.common.jdreactFramework.download.PluginVersion analysisXml(java.io.InputStream r11) {
        /*
            java.lang.String r0 = "9999999"
            com.jingdong.common.jdreactFramework.download.PluginVersion r1 = new com.jingdong.common.jdreactFramework.download.PluginVersion
            r1.<init>()
            org.xmlpull.v1.XmlPullParserFactory r2 = org.xmlpull.v1.XmlPullParserFactory.newInstance()     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L94 java.io.IOException -> L9b org.xmlpull.v1.XmlPullParserException -> L9f java.io.FileNotFoundException -> La3
            r3 = 1
            r2.setNamespaceAware(r3)     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L94 java.io.IOException -> L9b org.xmlpull.v1.XmlPullParserException -> L9f java.io.FileNotFoundException -> La3
            org.xmlpull.v1.XmlPullParser r2 = r2.newPullParser()     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L94 java.io.IOException -> L9b org.xmlpull.v1.XmlPullParserException -> L9f java.io.FileNotFoundException -> La3
            java.lang.String r4 = "UTF-8"
            r2.setInput(r11, r4)     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L94 java.io.IOException -> L9b org.xmlpull.v1.XmlPullParserException -> L9f java.io.FileNotFoundException -> La3
            r4 = -1
        L19:
            if (r4 == r3) goto L87
            r5 = 2
            if (r4 != r5) goto L82
            java.lang.String r4 = r2.getName()     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L94 java.io.IOException -> L9b org.xmlpull.v1.XmlPullParserException -> L9f java.io.FileNotFoundException -> La3
            java.lang.String r5 = "module"
            boolean r4 = r4.equals(r5)     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L94 java.io.IOException -> L9b org.xmlpull.v1.XmlPullParserException -> L9f java.io.FileNotFoundException -> La3
            if (r4 == 0) goto L82
            java.lang.String r4 = "moduleName"
            r5 = 0
            java.lang.String r4 = r2.getAttributeValue(r5, r4)     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L94 java.io.IOException -> L9b org.xmlpull.v1.XmlPullParserException -> L9f java.io.FileNotFoundException -> La3
            java.lang.String r6 = "moduleCode"
            java.lang.String r6 = r2.getAttributeValue(r5, r6)     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L94 java.io.IOException -> L9b org.xmlpull.v1.XmlPullParserException -> L9f java.io.FileNotFoundException -> La3
            java.lang.String r7 = "commitId"
            java.lang.String r7 = r2.getAttributeValue(r5, r7)     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L94 java.io.IOException -> L9b org.xmlpull.v1.XmlPullParserException -> L9f java.io.FileNotFoundException -> La3
            com.jingdong.common.jdreactFramework.JDReactHelper r8 = com.jingdong.common.jdreactFramework.JDReactHelper.newInstance()     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L94 java.io.IOException -> L9b org.xmlpull.v1.XmlPullParserException -> L9f java.io.FileNotFoundException -> La3
            boolean r8 = r8.isDebug()     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L94 java.io.IOException -> L9b org.xmlpull.v1.XmlPullParserException -> L9f java.io.FileNotFoundException -> La3
            if (r8 == 0) goto L65
            java.lang.String r8 = "ReactNativeXmlUtils"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L94 java.io.IOException -> L9b org.xmlpull.v1.XmlPullParserException -> L9f java.io.FileNotFoundException -> La3
            r9.<init>()     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L94 java.io.IOException -> L9b org.xmlpull.v1.XmlPullParserException -> L9f java.io.FileNotFoundException -> La3
            java.lang.String r10 = "Module name is "
            r9.append(r10)     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L94 java.io.IOException -> L9b org.xmlpull.v1.XmlPullParserException -> L9f java.io.FileNotFoundException -> La3
            r9.append(r4)     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L94 java.io.IOException -> L9b org.xmlpull.v1.XmlPullParserException -> L9f java.io.FileNotFoundException -> La3
            java.lang.String r10 = " version is "
            r9.append(r10)     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L94 java.io.IOException -> L9b org.xmlpull.v1.XmlPullParserException -> L9f java.io.FileNotFoundException -> La3
            r9.append(r6)     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L94 java.io.IOException -> L9b org.xmlpull.v1.XmlPullParserException -> L9f java.io.FileNotFoundException -> La3
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L94 java.io.IOException -> L9b org.xmlpull.v1.XmlPullParserException -> L9f java.io.FileNotFoundException -> La3
            com.jingdong.common.jdreactFramework.utils.JLog.d(r8, r9)     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L94 java.io.IOException -> L9b org.xmlpull.v1.XmlPullParserException -> L9f java.io.FileNotFoundException -> La3
        L65:
            java.lang.String r8 = "commonVersion"
            java.lang.String r5 = r2.getAttributeValue(r5, r8)     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L94 java.io.IOException -> L9b org.xmlpull.v1.XmlPullParserException -> L9f java.io.FileNotFoundException -> La3
            r1.pluginName = r4     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L94 java.io.IOException -> L9b org.xmlpull.v1.XmlPullParserException -> L9f java.io.FileNotFoundException -> La3
            r1.pluginCommonVersion = r5     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L94 java.io.IOException -> L9b org.xmlpull.v1.XmlPullParserException -> L9f java.io.FileNotFoundException -> La3
            r1.pluginName = r4     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L94 java.io.IOException -> L9b org.xmlpull.v1.XmlPullParserException -> L9f java.io.FileNotFoundException -> La3
            if (r6 == 0) goto L7e
            boolean r4 = r6.startsWith(r0)     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L94 java.io.IOException -> L9b org.xmlpull.v1.XmlPullParserException -> L9f java.io.FileNotFoundException -> La3
            if (r4 == 0) goto L7e
            r1.pluginVersion = r0     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L94 java.io.IOException -> L9b org.xmlpull.v1.XmlPullParserException -> L9f java.io.FileNotFoundException -> La3
            r1.testmodeVersion = r6     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L94 java.io.IOException -> L9b org.xmlpull.v1.XmlPullParserException -> L9f java.io.FileNotFoundException -> La3
            goto L82
        L7e:
            r1.pluginVersion = r6     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L94 java.io.IOException -> L9b org.xmlpull.v1.XmlPullParserException -> L9f java.io.FileNotFoundException -> La3
            r1.commitId = r7     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L94 java.io.IOException -> L9b org.xmlpull.v1.XmlPullParserException -> L9f java.io.FileNotFoundException -> La3
        L82:
            int r4 = r2.next()     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L94 java.io.IOException -> L9b org.xmlpull.v1.XmlPullParserException -> L9f java.io.FileNotFoundException -> La3
            goto L19
        L87:
            if (r11 == 0) goto L8c
            r11.close()     // Catch: java.io.IOException -> L8c
        L8c:
            return r1
        L8d:
            r0 = move-exception
            if (r11 == 0) goto L93
            r11.close()     // Catch: java.io.IOException -> L93
        L93:
            throw r0
        L94:
            if (r11 == 0) goto La7
        L97:
            r11.close()     // Catch: java.io.IOException -> La7
            goto La7
        L9b:
            if (r11 == 0) goto La7
            goto L97
        L9f:
            if (r11 == 0) goto La7
            goto L97
        La3:
            if (r11 == 0) goto La7
            goto L97
        La7:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.jdreactFramework.utils.XmlUtils.analysisXml(java.io.InputStream):com.jingdong.common.jdreactFramework.download.PluginVersion");
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
