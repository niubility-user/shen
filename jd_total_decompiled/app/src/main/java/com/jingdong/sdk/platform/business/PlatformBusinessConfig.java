package com.jingdong.sdk.platform.business;

import android.content.Context;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.config.PlatformConfig;
import com.jingdong.sdk.platform.config.PlatformLog;
import com.jingdong.sdk.platform.config.PlatformPlugin;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes9.dex */
public class PlatformBusinessConfig {
    public static PlatformConfig.PlatformConfigBuilder getPlatformBuilder(boolean z, final Context context) {
        return PlatformConfig.PlatformConfigBuilder.create(z).platformLog(new PlatformLog() { // from class: com.jingdong.sdk.platform.business.PlatformBusinessConfig.2
            @Override // com.jingdong.sdk.platform.config.PlatformLog
            public void d(String str, String str2) {
                OKLog.d(str, str2);
            }

            @Override // com.jingdong.sdk.platform.config.PlatformLog
            public void e(String str, String str2) {
                OKLog.e(str, str2);
            }

            @Override // com.jingdong.sdk.platform.config.PlatformLog
            public void i(String str, String str2) {
                OKLog.i(str, str2);
            }

            @Override // com.jingdong.sdk.platform.config.PlatformLog
            public void reportException(Throwable th) {
                ExceptionReporter.reportExceptionToBugly(th);
            }
        }).platformList(new PlatformPlugin() { // from class: com.jingdong.sdk.platform.business.PlatformBusinessConfig.1
            @Override // com.jingdong.sdk.platform.config.PlatformPlugin
            public List<String> getInitList() {
                return PlatformBusinessConfig.getPluginList(context);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<String> getPluginList(Context context) {
        ArrayList arrayList = new ArrayList(3);
        arrayList.add("com.jingdong.sdk.platform.business.Init");
        arrayList.add("com.jingdong.sdk.platform.business.personal.Init");
        arrayList.add("com.jingdong.sdk.platform.business.puppet.Init");
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0077 A[Catch: IOException -> 0x0073, TRY_LEAVE, TryCatch #4 {IOException -> 0x0073, blocks: (B:48:0x006f, B:52:0x0077), top: B:64:0x006f }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x006f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static JSONArray readInitJson(Context context) {
        InputStream inputStream;
        BufferedReader bufferedReader;
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader2 = null;
        try {
            try {
                inputStream = context.getAssets().open("platform_inits.json");
            } catch (IOException e2) {
                e = e2;
                inputStream = null;
                bufferedReader = null;
            } catch (Throwable th) {
                th = th;
                inputStream = null;
            }
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        if (inputStream == null) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            return null;
        }
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                try {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        sb.append(readLine);
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedReader2 = bufferedReader;
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                                throw th;
                            }
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        throw th;
                    }
                } catch (IOException e6) {
                    e = e6;
                    e.printStackTrace();
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    return new JSONArray(sb.toString());
                }
            }
            bufferedReader.close();
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException e7) {
            e = e7;
            bufferedReader = null;
        } catch (Throwable th3) {
            th = th3;
            if (bufferedReader2 != null) {
            }
            if (inputStream != null) {
            }
            throw th;
        }
        try {
            return new JSONArray(sb.toString());
        } catch (JSONException e8) {
            e8.printStackTrace();
            return null;
        }
    }
}
