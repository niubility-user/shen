package com.jingdong.common.utils;

import android.content.Context;
import com.jingdong.common.web.IRouterParams;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.oklog.OKLog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/* loaded from: classes.dex */
public class JDPrivacyHelper {
    private static final String FILE_NAME_PRIVACY = "jdPrivacyState";
    private static final String KEY_PRIVACY_STATUS = "privacy_has_show";
    private static final String PRIVACY_POLICY_OFFICIAL_NAME_1 = "\u300a\u4eac\u4e1c\u9690\u79c1\u653f\u7b56\u300b";
    private static final String PRIVACY_POLICY_OFFICIAL_NAME_2 = "\u300a\u4eac\u4e1c\u57fa\u672c\u529f\u80fd\u9690\u79c1\u653f\u7b56\u300b";
    private static final String PRIVACY_URL_DEBUG = "https://pro.m.jd.com/mall/active/4M9iBnt9U3KxUacwiRhHgP3BnRp1/index.html";
    private static final String PRIVACY_URL_RELEASE = "https://in.m.jd.com/help/app/private_policy.html";
    private static final String SP_NAME_PRIVACY = "privacy";
    private static volatile boolean sIsAcceptPrivacy;

    public static String getPrivacyOfficialName() {
        return PRIVACY_POLICY_OFFICIAL_NAME_1;
    }

    public static String getPrivacyUri() {
        return JdSdk.getInstance().getPrivacyOffLineStatus() ? PRIVACY_URL_DEBUG : "https://in.m.jd.com/help/app/private_policy.html";
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

    public static synchronized boolean isAcceptPrivacy(Context context) {
        synchronized (JDPrivacyHelper.class) {
            if (context == null) {
                return false;
            }
            if (!sIsAcceptPrivacy) {
                File file = new File(context.getFilesDir(), FILE_NAME_PRIVACY);
                if (file.exists() && file.isFile()) {
                    sIsAcceptPrivacy = Boolean.parseBoolean(getValueFromFile(file));
                } else {
                    sIsAcceptPrivacy = context.getSharedPreferences(SP_NAME_PRIVACY, 0).getBoolean(KEY_PRIVACY_STATUS, false);
                    saveValueToFile(String.valueOf(sIsAcceptPrivacy), file);
                }
            }
            if (OKLog.D) {
                OKLog.d("JDPrivacyHelper", "user privacy result : " + sIsAcceptPrivacy);
            }
            return sIsAcceptPrivacy;
        }
    }

    public static String privacyUri(IRouterParams iRouterParams) {
        return getPrivacyUri();
    }

    public static void savePrivacy(Context context, boolean z) {
        if (sIsAcceptPrivacy != z) {
            sIsAcceptPrivacy = z;
            if (context != null) {
                context.getSharedPreferences(SP_NAME_PRIVACY, 0).edit().putBoolean(KEY_PRIVACY_STATUS, z).commit();
            }
            if (OKLog.D) {
                OKLog.d("JDPrivacyHelper", "savePrivacy result : " + z);
            }
            saveValueToFile(String.valueOf(z), new File(context.getFilesDir(), FILE_NAME_PRIVACY));
        }
    }

    private static void saveValueToFile(String str, File file) {
        FileWriter fileWriter;
        if (file == null) {
            return;
        }
        FileWriter fileWriter2 = null;
        try {
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
                fileWriter.close();
            } catch (Throwable th2) {
                th = th2;
                fileWriter2 = fileWriter;
                try {
                    th.printStackTrace();
                    if (fileWriter2 != null) {
                        fileWriter2.close();
                    }
                } catch (Throwable th3) {
                    if (fileWriter2 != null) {
                        try {
                            fileWriter2.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    throw th3;
                }
            }
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }
}
