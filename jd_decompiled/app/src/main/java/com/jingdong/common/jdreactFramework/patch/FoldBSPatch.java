package com.jingdong.common.jdreactFramework.patch;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.xweb.util.BSpatch;
import java.io.File;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class FoldBSPatch {
    public static final String FOLD_BS_PATCH = "FoldBSPatch";

    public static boolean foldPatch(String str, String str2, String str3) {
        try {
            return foldPatchForThrow(str, str2, str3);
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean foldPatchAndUnZipPatch(Context context, String str, String str2, String str3) {
        try {
            return foldPatchAndUnZipPatchForThrow(context, str, str2, str3);
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean foldPatchAndUnZipPatchForThrow(Context context, String str, String str2, String str3) throws Exception {
        File file;
        if (TextUtils.isEmpty(str3)) {
            return false;
        }
        File file2 = null;
        try {
            file = new File(context.getDir("foldBSPatch", 0), System.currentTimeMillis() + "");
        } catch (Throwable th) {
            th = th;
        }
        try {
            file.mkdirs();
            FileUtil.unzip(str3, file.getAbsolutePath());
            String str4 = "foldPatchWhitUnZip end" + System.currentTimeMillis() + "";
            boolean foldPatchForThrow = foldPatchForThrow(str, str2, file.getAbsolutePath());
            FileUtil.deleteDir(file);
            return foldPatchForThrow;
        } catch (Throwable th2) {
            th = th2;
            file2 = file;
            if (file2 != null) {
                FileUtil.deleteDir(file2);
            }
            throw th;
        }
    }

    public static boolean foldPatchCopy2Dest(String str, String str2, String str3) {
        try {
            return foldPatchCopy2DestForThrow(str, str2, str3);
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static synchronized boolean foldPatchCopy2DestForThrow(String str, String str2, String str3) throws Exception {
        synchronized (FoldBSPatch.class) {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                FileUtil.emptyDir(new File(str2));
                FileUtil.copyFolder(str, str2);
                String str4 = "foldPatchCopy2Dest end" + System.currentTimeMillis() + "";
                return foldPatchForThrow(str, str2, str3);
            }
            return false;
        }
    }

    public static boolean foldPatchForThrow(String str, String str2, String str3) throws Exception {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        JSONObject fileToJsonObject = FileUtil.fileToJsonObject(new File(str3, "change.json").getAbsolutePath());
        JSONArray optJSONArray = fileToJsonObject.optJSONArray("delete");
        if (optJSONArray != null) {
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                FileUtil.deleteDir(new File(str2 + optJSONArray.getString(i2)));
            }
            String str4 = "deleteDir end" + System.currentTimeMillis() + "";
        }
        JSONArray optJSONArray2 = fileToJsonObject.optJSONArray("update");
        if (optJSONArray2 != null) {
            for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                String string = optJSONArray2.getString(i3);
                FileUtil.copyFile(str3 + string, str2 + string);
            }
        }
        String str5 = "copyFile end" + System.currentTimeMillis() + "";
        JSONArray optJSONArray3 = fileToJsonObject.optJSONArray("patch");
        if (optJSONArray3 != null) {
            for (int i4 = 0; i4 < optJSONArray3.length(); i4++) {
                JSONObject jSONObject = optJSONArray3.getJSONObject(i4);
                String string2 = jSONObject.getString("src");
                String string3 = jSONObject.getString("patch");
                String string4 = jSONObject.getString("destMd5");
                if (BSpatch.h(str + string2, str3 + string3, str2 + string2) == 0) {
                    if (!TextUtils.isEmpty(string4)) {
                        if (!TextUtils.equals(FileUtil.getFileMD5ForThrow(new File(str2 + string2)), string4)) {
                            throw new Exception("patch success and md5 validate fail file is: " + string2);
                        }
                    }
                } else {
                    throw new Exception("patch error file is: " + string2 + "patch is: " + string3 + "destMd5 is: " + string4);
                }
            }
        }
        String str6 = "patch end" + System.currentTimeMillis() + "";
        return true;
    }

    public static boolean foldPatchUnZipPatchAndCopy2Dest(Context context, String str, String str2, String str3) {
        try {
            return foldPatchUnZipPatchAndCopy2DestForThrow(context, str, str2, str3);
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static synchronized boolean foldPatchUnZipPatchAndCopy2DestForThrow(Context context, String str, String str2, String str3) throws Exception {
        File file;
        synchronized (FoldBSPatch.class) {
            if (TextUtils.isEmpty(str3)) {
                return false;
            }
            File file2 = null;
            try {
                file = new File(context.getDir("foldBSPatch", 0), System.currentTimeMillis() + "");
            } catch (Throwable th) {
                th = th;
            }
            try {
                String absolutePath = file.getAbsolutePath();
                FileUtil.unzip(str3, absolutePath);
                String str4 = "foldPatchWhitUnZip end" + System.currentTimeMillis() + "";
                boolean foldPatchCopy2DestForThrow = foldPatchCopy2DestForThrow(str, str2, absolutePath);
                FileUtil.deleteDir(file);
                return foldPatchCopy2DestForThrow;
            } catch (Throwable th2) {
                th = th2;
                file2 = file;
                if (file2 != null) {
                    FileUtil.deleteDir(file2);
                }
                throw th;
            }
        }
    }
}
