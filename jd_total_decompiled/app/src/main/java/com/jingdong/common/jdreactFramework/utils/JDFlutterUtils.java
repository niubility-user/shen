package com.jingdong.common.jdreactFramework.utils;

import android.os.AsyncTask;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.download.PluginVersion;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/* loaded from: classes5.dex */
public class JDFlutterUtils {
    private static boolean EXCHANGE_FINISHED = true;
    private static MyAsyncTask mMyAsyncTask;
    private static ArrayList<WeakReference<ExchangeLisener>> pluginListeners = new ArrayList<>();

    /* loaded from: classes5.dex */
    public interface ExchangeLisener {
        void onLoadSuccess(boolean z);
    }

    /* loaded from: classes5.dex */
    private static class MyAsyncTask extends AsyncTask<Void, Void, Boolean> {
        private int mStatus;

        MyAsyncTask(int i2) {
            this.mStatus = 0;
            this.mStatus = i2;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(Void... voidArr) {
            MyAsyncTask myAsyncTask;
            String str;
            PluginVersion pluginVersion;
            String absolutePath = JDReactConstant.FLUTTERDownloadPath.getAbsolutePath();
            StringBuilder sb = new StringBuilder();
            sb.append(absolutePath);
            String str2 = File.separator;
            sb.append(str2);
            sb.append("flutter_assets");
            File file = new File(sb.toString());
            PluginVersion pluginVersionPath = ReactVersionUtils.getPluginVersionPath(true, JDReactConstant.JDFLUTTER_PACKAGE_NAME);
            String str3 = str2 + "flutter_assets";
            if (new File(absolutePath + str2 + "flutter_assets" + str2 + "isolate_snapshot_data").exists()) {
                myAsyncTask = this;
            } else {
                myAsyncTask = this;
                str3 = "";
            }
            if (myAsyncTask.mStatus == 2) {
                if (!"".equals(str3)) {
                    ZipUtils.copyJDFlutterFileDirect(new File(pluginVersionPath.pluginDir), file);
                } else {
                    ZipUtils.copyJDFlutterFileDirect(new File(pluginVersionPath.pluginDir), new File(absolutePath));
                }
            }
            if (!new File(absolutePath + str3 + str2 + "isolate_snapshot_data_new").exists()) {
                boolean unused = JDFlutterUtils.EXCHANGE_FINISHED = true;
                return Boolean.FALSE;
            }
            if ("".equals(str3)) {
                File file2 = new File(absolutePath + str3 + str2 + "isolate_snapshot_data");
                File file3 = new File(absolutePath + str3 + str2 + "isolate_snapshot_instr");
                File file4 = new File(absolutePath + str3 + str2 + JDReactConstant.JDFLUTTER_PACKAGE_NAME + ".version");
                StringBuilder sb2 = new StringBuilder();
                sb2.append(absolutePath);
                sb2.append(str3);
                sb2.append(str2);
                sb2.append("isolate_snapshot_data_backup");
                File file5 = new File(sb2.toString());
                File file6 = new File(absolutePath + str3 + str2 + "isolate_snapshot_instr_backup");
                File file7 = new File(absolutePath + str3 + str2 + JDReactConstant.JDFLUTTER_PACKAGE_NAME + ".version_backup");
                if (file5.exists()) {
                    file5.delete();
                }
                if (file6.exists()) {
                    file6.delete();
                }
                if (file7.exists()) {
                    file7.delete();
                }
                ZipUtils.renameFile(file2.getAbsolutePath(), file2.getAbsolutePath() + "_backup");
                ZipUtils.renameFile(file3.getAbsolutePath(), file3.getAbsolutePath() + "_backup");
                ZipUtils.renameFile(file4.getAbsolutePath(), file4.getAbsolutePath() + "_backup");
                ZipUtils.renameFile(file2.getAbsolutePath() + "_new", file2.getAbsolutePath());
                ZipUtils.renameFile(file3.getAbsolutePath() + "_new", file3.getAbsolutePath());
                ZipUtils.renameFile(file4.getAbsolutePath() + "_new", file4.getAbsolutePath());
                str = "flutter_assets";
                pluginVersion = pluginVersionPath;
            } else {
                File file8 = new File(absolutePath + str3 + str2 + "isolate_snapshot_data");
                File file9 = new File(absolutePath + str3 + str2 + "kernel_blob.bin");
                File file10 = new File(absolutePath + str3 + str2 + "vm_snapshot_data");
                File file11 = new File(absolutePath + str3 + str2 + JDReactConstant.JDFLUTTER_PACKAGE_NAME + ".version");
                StringBuilder sb3 = new StringBuilder();
                sb3.append(absolutePath);
                sb3.append(str3);
                sb3.append(str2);
                sb3.append("isolate_snapshot_data_backup");
                File file12 = new File(sb3.toString());
                StringBuilder sb4 = new StringBuilder();
                sb4.append(absolutePath);
                sb4.append(str3);
                sb4.append(str2);
                str = "flutter_assets";
                sb4.append("kernel_blob.bin_backup");
                File file13 = new File(sb4.toString());
                StringBuilder sb5 = new StringBuilder();
                sb5.append(absolutePath);
                sb5.append(str3);
                sb5.append(str2);
                pluginVersion = pluginVersionPath;
                sb5.append("vm_snapshot_data_backup");
                File file14 = new File(sb5.toString());
                File file15 = new File(absolutePath + str3 + str2 + JDReactConstant.JDFLUTTER_PACKAGE_NAME + ".version_backup");
                if (file12.exists()) {
                    file12.delete();
                }
                if (file13.exists()) {
                    file13.delete();
                }
                if (file14.exists()) {
                    file14.delete();
                }
                if (file15.exists()) {
                    file15.delete();
                }
                ZipUtils.renameFile(file8.getAbsolutePath(), file8.getAbsolutePath() + "_backup");
                ZipUtils.renameFile(file9.getAbsolutePath(), file9.getAbsolutePath() + "_backup");
                ZipUtils.renameFile(file10.getAbsolutePath(), file10.getAbsolutePath() + "_backup");
                ZipUtils.renameFile(file11.getAbsolutePath(), file11.getAbsolutePath() + "_backup");
                ZipUtils.renameFile(file8.getAbsolutePath() + "_new", file8.getAbsolutePath());
                ZipUtils.renameFile(file9.getAbsolutePath() + "_new", file9.getAbsolutePath());
                ZipUtils.renameFile(file10.getAbsolutePath() + "_new", file10.getAbsolutePath());
                ZipUtils.renameFile(file11.getAbsolutePath() + "_new", file11.getAbsolutePath());
            }
            StringBuilder sb6 = new StringBuilder();
            sb6.append(pluginVersion.pluginDir);
            sb6.append(str3);
            String str4 = str;
            sb6.append(str4);
            File file16 = new File(sb6.toString());
            if (file16.exists()) {
                ZipUtils.copyFileDirect(file16, new File(absolutePath + str2 + str4));
            }
            ReactSharedPreferenceUtils.putJDFlutterStatus(JDReactConstant.JDFLUTTER_PACKAGE_NAME, "finished");
            boolean unused2 = JDFlutterUtils.EXCHANGE_FINISHED = true;
            JDFlutterUtils.onFinish(true);
            return Boolean.TRUE;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean bool) {
            super.onPostExecute((MyAsyncTask) bool);
        }
    }

    public static void exchangeFiles(ExchangeLisener exchangeLisener, String str) {
        if (!JDReactConstant.JDFLUTTER_PACKAGE_NAME.equals(str)) {
            exchangeLisener.onLoadSuccess(true);
            return;
        }
        int shouldExchange = shouldExchange();
        if (shouldExchange == 0) {
            exchangeLisener.onLoadSuccess(true);
        } else if (!EXCHANGE_FINISHED) {
            pluginListeners.add(new WeakReference<>(exchangeLisener));
        } else {
            EXCHANGE_FINISHED = false;
            mMyAsyncTask = new MyAsyncTask(shouldExchange);
            pluginListeners.add(new WeakReference<>(exchangeLisener));
            mMyAsyncTask.execute(new Void[0]);
        }
    }

    private static boolean isFlutterExist() {
        String absolutePath = JDReactConstant.FLUTTERDownloadPath.getAbsolutePath();
        StringBuilder sb = new StringBuilder();
        sb.append(absolutePath);
        String str = File.separator;
        sb.append(str);
        sb.append("flutter_assets");
        sb.append(str);
        sb.append("isolate_snapshot_data");
        File file = new File(sb.toString());
        String str2 = str + "flutter_assets";
        if (new File(absolutePath).exists()) {
            File file2 = new File(absolutePath + str2);
            if (file2.exists()) {
                if (!file.exists()) {
                    str2 = "";
                }
                File file3 = new File(absolutePath + str2 + str + "isolate_snapshot_data");
                File file4 = new File(absolutePath + str2 + str + "vm_snapshot_data");
                if (file2.exists()) {
                    if (file3.exists() && file4.exists()) {
                        return true;
                    }
                    try {
                        new File(absolutePath).delete();
                    } catch (Exception unused) {
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void onFinish(boolean z) {
        ExchangeLisener exchangeLisener;
        ArrayList arrayList = new ArrayList(Arrays.asList(new Integer[pluginListeners.size()]));
        Collections.copy(arrayList, pluginListeners);
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            WeakReference weakReference = (WeakReference) arrayList.get(i2);
            if (weakReference != null && (exchangeLisener = (ExchangeLisener) weakReference.get()) != null) {
                exchangeLisener.onLoadSuccess(z);
            }
        }
        arrayList.clear();
    }

    public static int shouldExchange() {
        String str;
        String jDFlutterStatus = ReactSharedPreferenceUtils.getJDFlutterStatus(JDReactConstant.JDFLUTTER_PACKAGE_NAME);
        String absolutePath = JDReactConstant.FLUTTERDownloadPath.getAbsolutePath();
        StringBuilder sb = new StringBuilder();
        String str2 = File.separator;
        sb.append(str2);
        sb.append("flutter_assets");
        String sb2 = sb.toString();
        File file = new File(absolutePath);
        if ("none".equals(jDFlutterStatus) || "finished".equals(jDFlutterStatus) || !file.exists() || !isFlutterExist()) {
            return 0;
        }
        if (!new File(absolutePath + sb2 + str2 + "isolate_snapshot_data").exists()) {
            sb2 = "";
        }
        PluginVersion pluginVersion = XmlUtils.getPluginVersion(absolutePath + sb2 + str2 + JDReactConstant.JDFLUTTER_PACKAGE_NAME + ".version_new");
        PluginVersion pluginVersion2 = XmlUtils.getPluginVersion(absolutePath + sb2 + str2 + JDReactConstant.JDFLUTTER_PACKAGE_NAME + ".version");
        PluginVersion pluginVersionPath = ReactVersionUtils.getPluginVersionPath(true, JDReactConstant.JDFLUTTER_PACKAGE_NAME);
        if (pluginVersion == null && pluginVersionPath != null && (str = pluginVersionPath.pluginVersion) != null) {
            return ((pluginVersion2 == null || pluginVersion2.pluginVersion != str) && VersionUtils.compareVersion(pluginVersion2.pluginVersion, str) < 0) ? 2 : 0;
        } else if (pluginVersionPath != null && pluginVersion != null) {
            return VersionUtils.compareVersion(pluginVersion.pluginVersion, pluginVersionPath.pluginVersion) == 0 ? 1 : 2;
        }
        return 0;
    }
}
