package com.jingdong.common.web.util;

import com.jingdong.common.jdreactFramework.download.PluginDownloadInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class AlbumSaveHelper {
    private static Map<String, String> typeMap;

    static {
        HashMap hashMap = new HashMap();
        typeMap = hashMap;
        hashMap.put("2e524d46000000120001", "rmvb");
        typeMap.put("464c5601050000000900", "flv");
        typeMap.put("00000020667479706973", "mp4");
        typeMap.put("000001ba210001000180", "mpg");
        typeMap.put("3026b2758e66cf11a6d9", "wmv");
        typeMap.put("52494646e27807005741", "wav");
        typeMap.put("52494646d07d60074156", "avi");
        typeMap.put("1a45dfa3010000000000", "mkv");
        typeMap.put("0000001c667479703367", "3gp");
    }

    public static String getFileHead(File file) {
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                byte[] bArr = new byte[10];
                fileInputStream2.read(bArr, 0, 10);
                String bytesToHexString = PluginDownloadInfo.bytesToHexString(bArr);
                try {
                    fileInputStream2.close();
                } catch (IOException unused) {
                }
                return bytesToHexString;
            } catch (Exception unused2) {
                fileInputStream = fileInputStream2;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException unused3) {
                    }
                }
                return "";
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException unused4) {
                    }
                }
                throw th;
            }
        } catch (Exception unused5) {
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String getFileType(File file) {
        return typeMap.get(getFileHead(file).toLowerCase()) == null ? "mp4" : typeMap.get(getFileHead(file).toLowerCase());
    }

    public static String stringfyJSonData(String str, String str2, String str3, String str4) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", str);
            jSONObject.put("data", str2);
            jSONObject.put("msg", str3);
            jSONObject.put("callBackId", str4);
        } catch (Exception unused) {
        }
        return jSONObject.toString();
    }
}
