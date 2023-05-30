package com.jingdong.common.babelrn.utils;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.common.jdreactFramework.download.PluginListenerNew;
import com.jingdong.common.jdreactFramework.download.PluginUpdateInfo;
import com.jingdong.common.jdreactFramework.utils.ZipUtils;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.web.managers.PerformanceManager;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.FileGuider;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpRequest;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import jpbury.t;

/* loaded from: classes5.dex */
public class BabelRNDownloadUtil {
    public static final File ReactDownloadPath = JdSdk.getInstance().getApplication().getApplicationContext().getDir("babelrn", 0);
    private static DecimalFormat df = new DecimalFormat("#.###");

    /* loaded from: classes5.dex */
    private static class JsBundleCheckResult {
        public File bundleDir;
        public String errMsg;
        public File hDrawableFile;
        public File jsBundleFile;
        public File mDrawableFile;
        public File xhDrawableFile;
        public File xxhDrawableFile;
        public File xxxhDrawableFile;

        public JsBundleCheckResult(String str, String str2) {
            String pluginPath = BabelRNDownloadUtil.getPluginPath(str);
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(pluginPath)) {
                return;
            }
            String str3 = File.separator;
            if (!pluginPath.endsWith(str3)) {
                pluginPath = pluginPath + str3;
            }
            this.bundleDir = new File(pluginPath);
            if (!TextUtils.isEmpty(str2)) {
                this.jsBundleFile = new File(BabelRNDownloadUtil.getPluginBundlePath(str, str2));
            }
            this.hDrawableFile = new File(pluginPath + "drawable-hdpi");
            this.mDrawableFile = new File(pluginPath + "drawable-mdpi");
            this.xhDrawableFile = new File(pluginPath + "drawable-xhdpi");
            this.xxhDrawableFile = new File(pluginPath + "drawable-xxhdpi");
            this.xxxhDrawableFile = new File(pluginPath + "drawable-xxxhdpi");
        }

        public boolean filesAvailable() {
            boolean z;
            this.errMsg = "";
            File file = this.bundleDir;
            if (file != null && file.exists() && this.bundleDir.isDirectory()) {
                File file2 = this.jsBundleFile;
                if (file2 != null && file2.exists() && this.jsBundleFile.isFile()) {
                    z = true;
                } else {
                    this.errMsg += "Missing jsbundle file. ";
                    z = false;
                }
                File file3 = this.hDrawableFile;
                if (file3 == null || !file3.exists() || !this.hDrawableFile.isDirectory()) {
                    this.errMsg += "Missing hDrawable directory. ";
                    z = false;
                }
                File file4 = this.mDrawableFile;
                if (file4 == null || !file4.exists() || !this.mDrawableFile.isDirectory()) {
                    this.errMsg += "Missing mDrawable directory. ";
                    z = false;
                }
                File file5 = this.xhDrawableFile;
                if (file5 == null || !file5.exists() || !this.xhDrawableFile.isDirectory()) {
                    this.errMsg += "Missing xhDrawable directory. ";
                    z = false;
                }
                File file6 = this.xxhDrawableFile;
                if (file6 == null || !file6.exists() || !this.xxhDrawableFile.isDirectory()) {
                    this.errMsg += "Missing xxhDrawable directory. ";
                    z = false;
                }
                File file7 = this.xxxhDrawableFile;
                if (file7 != null && file7.exists() && this.xxxhDrawableFile.isDirectory()) {
                    return z;
                }
                this.errMsg += "Missing xxxhDrawable directory. ";
                return false;
            }
            this.errMsg += "Missing jsbundle directory. ";
            return false;
        }
    }

    public static void changeFiletime(File file) {
        if (file == null) {
            return;
        }
        file.setLastModified(System.currentTimeMillis());
    }

    public static boolean checkBundlePathExists(Context context, String str, String str2, String str3, String str4) {
        if (isBundlePathExists(str3, str2)) {
            return true;
        }
        downLoadBundle(context, str, str2, str3, str4, null);
        return false;
    }

    public static void clearCheck() {
        File[] listFiles = ReactDownloadPath.listFiles();
        if (listFiles == null || listFiles.length <= 20) {
            return;
        }
        Arrays.sort(listFiles, new Comparator<File>() { // from class: com.jingdong.common.babelrn.utils.BabelRNDownloadUtil.2
            @Override // java.util.Comparator
            public boolean equals(Object obj) {
                return true;
            }

            @Override // java.util.Comparator
            public int compare(File file, File file2) {
                long lastModified = file.lastModified() - file2.lastModified();
                if (lastModified > 0) {
                    return 1;
                }
                return lastModified == 0 ? 0 : -1;
            }
        });
        for (int length = listFiles.length - 20; length > 0; length--) {
            listFiles[0].delete();
        }
    }

    public static void delBundle(String str) {
        File file = new File(getPluginPath(str));
        if (file.exists()) {
            file.delete();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean deleteDir(File file) {
        String[] list;
        if (file.isDirectory() && (list = file.list()) != null) {
            int length = list.length;
            for (int i2 = 0; i2 < length && deleteDir(new File(file, list[i2])); i2++) {
            }
        }
        return file.delete();
    }

    public static HttpRequest downLoadBundle(Context context, final String str, final String str2, final String str3, final String str4, final PluginListenerNew pluginListenerNew) {
        FileGuider fileGuider = new FileGuider();
        fileGuider.setSpace(1);
        fileGuider.setImmutable(false);
        fileGuider.setFileName(str3);
        fileGuider.setMode(1);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUrl(str);
        httpSetting.setCacheMode(2);
        httpSetting.setEffect(0);
        httpSetting.setListener(new HttpGroup.OnAllAndPauseListener() { // from class: com.jingdong.common.babelrn.utils.BabelRNDownloadUtil.1
            long downloadBeginTime;

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r12v10 */
            /* JADX WARN: Type inference failed for: r12v11 */
            /* JADX WARN: Type inference failed for: r12v12 */
            /* JADX WARN: Type inference failed for: r12v2 */
            /* JADX WARN: Type inference failed for: r12v3 */
            /* JADX WARN: Type inference failed for: r12v6 */
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                String str5 = "\nDelete unzipped dir error.";
                long currentTimeMillis = System.currentTimeMillis();
                try {
                    BabelRNDownloadUtil.sendTimeData(JdSdk.getInstance().getApplicationContext(), "BabelRNFragment", "rn_ttt_load", str, this.downloadBeginTime, System.currentTimeMillis(), CartConstant.KEY_CART_TEXTINFO_FINISH);
                    File file = new File(httpResponse.getSaveFile().getAbsolutePath());
                    File file2 = new File(BabelRNDownloadUtil.getPluginPath(str3));
                    long currentTimeMillis2 = System.currentTimeMillis();
                    try {
                        ZipUtils.decompress(file, file2.getAbsolutePath());
                        file.delete();
                        JsBundleCheckResult jsBundleCheckResult = new JsBundleCheckResult(str3, str2);
                        if (!jsBundleCheckResult.filesAvailable()) {
                            ?? deleteDir = file2.exists() ? BabelRNDownloadUtil.deleteDir(file2) : -1;
                            String str6 = str;
                            StringBuilder sb = new StringBuilder();
                            sb.append(jsBundleCheckResult.errMsg);
                            if (deleteDir != 0) {
                                str5 = "";
                            }
                            sb.append(str5);
                            BabelRNDownloadUtil.reportBabelRnError("TTT_Unzip_Error", "unzip error", str6, sb.toString(), str4);
                            BabelRNDownloadUtil.sendTimeData(JdSdk.getInstance().getApplicationContext(), "BabelRNFragment", "rn_ttt_unzip", str, currentTimeMillis2, System.currentTimeMillis(), "fail");
                            PluginListenerNew pluginListenerNew2 = pluginListenerNew;
                            if (pluginListenerNew2 != null) {
                                pluginListenerNew2.onFailure(null, str2, false, "");
                                return;
                            }
                            return;
                        }
                        BabelRNDownloadUtil.sendTimeData(JdSdk.getInstance().getApplicationContext(), "BabelRNFragment", "rn_ttt_unzip", str, currentTimeMillis2, System.currentTimeMillis(), CartConstant.KEY_CART_TEXTINFO_FINISH);
                        PluginUpdateInfo pluginUpdateInfo = new PluginUpdateInfo();
                        pluginUpdateInfo.pluginUpdateName = jsBundleCheckResult.jsBundleFile.getAbsolutePath();
                        PluginListenerNew pluginListenerNew3 = pluginListenerNew;
                        if (pluginListenerNew3 != null) {
                            pluginListenerNew3.onFinish(pluginUpdateInfo);
                        }
                    } catch (Exception e2) {
                        file.delete();
                        ?? deleteDir2 = file2.exists() ? BabelRNDownloadUtil.deleteDir(file2) : -1;
                        String str7 = str;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(e2.toString());
                        if (deleteDir2 != 0) {
                            str5 = "";
                        }
                        sb2.append(str5);
                        BabelRNDownloadUtil.reportBabelRnError("TTT_Unzip_Error", "unzip error", str7, sb2.toString(), str4);
                        BabelRNDownloadUtil.sendTimeData(JdSdk.getInstance().getApplicationContext(), "BabelRNFragment", "rn_ttt_unzip", str, currentTimeMillis2, System.currentTimeMillis(), "fail");
                        PluginListenerNew pluginListenerNew4 = pluginListenerNew;
                        if (pluginListenerNew4 != null) {
                            pluginListenerNew4.onFailure(null, str2, false, "");
                        }
                    }
                } catch (Exception e3) {
                    if (Log.E) {
                        Log.e("BabelRNDownloadUtil", e3.getMessage(), e3);
                    }
                    BabelRNDownloadUtil.reportBabelRnError("TTT_Unzip_Error", "unzip error", str, e3.toString(), str4);
                    BabelRNDownloadUtil.sendTimeData(JdSdk.getInstance().getApplicationContext(), "BabelRNFragment", "rn_ttt_unzip", str, currentTimeMillis, System.currentTimeMillis(), "fail");
                    PluginListenerNew pluginListenerNew5 = pluginListenerNew;
                    if (pluginListenerNew5 != null) {
                        pluginListenerNew5.onFailure(null, str2, false, "");
                    }
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                PluginListenerNew pluginListenerNew2 = pluginListenerNew;
                if (pluginListenerNew2 != null) {
                    pluginListenerNew2.onFailure(null, str2, false, "");
                }
                BabelRNDownloadUtil.reportBabelRnError("TTT_Load_Error", httpError.getErrorCodeStr(), str, httpError.getMessage(), str4);
                BabelRNDownloadUtil.sendTimeData(JdSdk.getInstance().getApplicationContext(), "BabelRNFragment", "rn_ttt_load", str, this.downloadBeginTime, System.currentTimeMillis(), "fail");
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnPauseListener
            public void onPause() {
                PluginListenerNew pluginListenerNew2 = pluginListenerNew;
                if (pluginListenerNew2 != null) {
                    pluginListenerNew2.onFailure(null, str2, false, "");
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
                this.downloadBeginTime = System.currentTimeMillis();
            }
        });
        httpSetting.setType(500);
        httpSetting.setPriority(5000);
        httpSetting.setTopPriority(true);
        httpSetting.setSavePath(fileGuider);
        httpSetting.setBreakpointTransmission(false);
        httpSetting.setAttempts(1);
        return HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public static void getBundlePath(Context context, String str, String str2, String str3, String str4, PluginListenerNew pluginListenerNew) {
        File file = new File(getPluginBundlePath(str3, str2));
        if (file.exists()) {
            if (pluginListenerNew != null) {
                PluginUpdateInfo pluginUpdateInfo = new PluginUpdateInfo();
                pluginUpdateInfo.pluginUpdateName = file.getAbsolutePath();
                pluginListenerNew.onFinish(pluginUpdateInfo);
            }
            changeFiletime(file);
            return;
        }
        downLoadBundle(context, str, str2, str3, str4, pluginListenerNew);
    }

    private static String getFormatTime(long j2) {
        DecimalFormat decimalFormat = df;
        double d = j2;
        Double.isNaN(d);
        return decimalFormat.format(d / 1000.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getPluginBundlePath(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(ReactDownloadPath.getAbsolutePath());
        String str3 = File.separator;
        sb.append(str3);
        sb.append(str);
        sb.append(str3);
        sb.append(str2);
        sb.append(".jsbundle");
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getPluginPath(String str) {
        return ReactDownloadPath.getAbsolutePath() + File.separator + str;
    }

    public static boolean isBundlePathExists(String str, String str2) {
        return new File(getPluginBundlePath(str, str2)).exists();
    }

    public static void reportBabelRnError(String str, String str2, String str3, String str4, String str5) {
        try {
            HashMap hashMap = new HashMap(8);
            hashMap.put("function", str);
            Object[] objArr = new Object[1];
            double currentTimeMillis = System.currentTimeMillis();
            Double.isNaN(currentTimeMillis);
            objArr[0] = Double.valueOf(currentTimeMillis / 1000.0d);
            hashMap.put("occurTime", String.format("%.6f", objArr));
            hashMap.put("errCode", "1202");
            hashMap.put(PerformanceManager.ERR_TYPE, "2");
            hashMap.put(t.f20145j, str2);
            hashMap.put("url", str3);
            hashMap.put("errMsg", str4);
            hashMap.put("reserved1", str5);
            ExceptionReporter.sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            if (Log.E) {
                Log.e("BabelRNDownloadUtil", th.getMessage(), th);
            }
        }
    }

    public static void sendTimeData(Context context, String str, String str2, String str3, long j2, long j3, String str4) {
        try {
            JDMtaUtils.sendWebviewLoadData(context, str, "", str2, str3, getFormatTime(j2), getFormatTime(j3), str4);
        } catch (Throwable unused) {
        }
    }
}
