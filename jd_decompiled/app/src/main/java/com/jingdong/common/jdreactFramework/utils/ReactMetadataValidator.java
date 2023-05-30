package com.jingdong.common.jdreactFramework.utils;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.download.JDReactHttpSetting;
import com.tencent.open.SocialConstants;
import java.io.File;

/* loaded from: classes5.dex */
public class ReactMetadataValidator {
    private static final String TAG = "ReactMetadataValidator";

    /* loaded from: classes5.dex */
    public interface ReactMetaDataCallback {
        void onFail();

        void onSuccess(JDJSONObject jDJSONObject);
    }

    public static boolean checkRules(JDJSONArray jDJSONArray) {
        boolean isBeta = JDReactHelper.newInstance().isBeta();
        try {
            String str = JDReactHelper.newInstance().getApplication().getPackageManager().getPackageInfo(JDReactHelper.newInstance().getApplication().getPackageName(), 16384).versionName;
            if (jDJSONArray != null && jDJSONArray.size() != 0) {
                int size = jDJSONArray.size();
                for (int i2 = 0; i2 < size; i2++) {
                    if (getValueOfRule(jDJSONArray.optJSONObject(i2), isBeta, "android", str)) {
                        return true;
                    }
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    private static boolean compareNodeAndValue(Object obj, Object obj2) {
        if (obj == null || obj2 == null) {
            return true;
        }
        if (obj instanceof JDJSONArray) {
            return isValueInJSONArray((JDJSONArray) obj, obj2);
        }
        return obj.equals(obj2);
    }

    public static void getReactMetaData(final ReactMetaDataCallback reactMetaDataCallback) {
        JDReactHelper newInstance = JDReactHelper.newInstance();
        JDReactHttpSetting httpSetting = newInstance.getHttpSetting();
        httpSetting.setHost(newInstance.getVirtualHost(JDReactHelper.newInstance().getNativeMetaData()));
        httpSetting.putJsonParam("businessType", (Object) 99);
        if (newInstance.isBeta()) {
            httpSetting.putJsonParam("dataId", "818");
        } else {
            httpSetting.putJsonParam("dataId", "800");
        }
        httpSetting.setFunctionId(JDReactHelper.newInstance().getNativeMetaData());
        httpSetting.setNotifyUser(false);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setEffect(newInstance.getEffect(JDReactHelper.newInstance().getNativeMetaData()));
        httpSetting.setListener(new JDReactHttpSetting.HttpCallback() { // from class: com.jingdong.common.jdreactFramework.utils.ReactMetadataValidator.1
            @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.HttpCallback
            public void onEnd(JDJSONObject jDJSONObject) {
                if (jDJSONObject != null) {
                    try {
                        ReactMetaDataCallback.this.onSuccess(jDJSONObject);
                    } catch (Exception unused) {
                    }
                }
            }

            @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.HttpCallback
            public void onEnd(File file) {
            }

            @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.HttpCallback
            public void onError() {
                ReactMetaDataCallback.this.onFail();
            }

            @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.HttpCallback
            public void onPause() {
            }

            @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.HttpCallback
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.HttpCallback
            public void onStart() {
            }
        });
        httpSetting.startToload();
    }

    private static boolean getValueOfRule(JDJSONObject jDJSONObject, boolean z, String str, String str2) {
        boolean z2;
        boolean compareNodeAndValue = jDJSONObject.containsKey("isBeta") ? compareNodeAndValue(Boolean.valueOf(jDJSONObject.optBoolean("isBeta")), new Boolean(z)) : true;
        boolean compareNodeAndValue2 = compareNodeAndValue(jDJSONObject.optJSONArray("client"), str);
        JDJSONArray optJSONArray = jDJSONObject.optJSONArray("appv");
        if (optJSONArray != null && optJSONArray.size() != 0) {
            int size = optJSONArray.size();
            for (int i2 = 0; i2 < size; i2++) {
                try {
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                if (!isVersionInRange(optJSONArray.optJSONObject(i2), str2)) {
                }
            }
            z2 = false;
            return !compareNodeAndValue && compareNodeAndValue2 && z2;
        }
        z2 = true;
        if (compareNodeAndValue) {
        }
    }

    private static boolean isValueInJSONArray(JDJSONArray jDJSONArray, Object obj) {
        int size = jDJSONArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (jDJSONArray.get(i2).equals(obj)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isVersionInRange(JDJSONObject jDJSONObject, String str) {
        if (jDJSONObject == null || str == null) {
            return true;
        }
        String optString = jDJSONObject.optString("min");
        String optString2 = jDJSONObject.optString("max");
        JDJSONArray optJSONArray = jDJSONObject.optJSONArray(SocialConstants.PARAM_EXCLUDE);
        return (TextUtils.isEmpty(optString) || verCompare(str, optString) >= 0) && (TextUtils.isEmpty(optString2) || verCompare(str, optString2) <= 0) && !(optJSONArray != null ? isValueInJSONArray(optJSONArray, str) : false);
    }

    private static int[] parseVersion(String str) {
        String[] split = str.split("\\.");
        if (split == null || split.length == 0) {
            return null;
        }
        int length = split.length;
        int[] iArr = new int[length];
        if (length == 0) {
            return iArr;
        }
        int length2 = split.length;
        for (int i2 = 0; i2 < length2; i2++) {
            try {
                iArr[i2] = Integer.parseInt(split[i2]);
            } catch (Exception unused) {
                iArr[i2] = 0;
            }
        }
        return iArr;
    }

    private static int verCompare(String str, String str2) {
        int[] parseVersion = parseVersion(str);
        int[] parseVersion2 = parseVersion(str2);
        int i2 = 0;
        if (parseVersion == null && parseVersion2 == null) {
            return 0;
        }
        if (parseVersion != null || parseVersion2 == null) {
            if (parseVersion == null || parseVersion2 != null) {
                int length = parseVersion.length;
                int length2 = parseVersion2.length;
                int i3 = 0;
                while (true) {
                    if (i2 >= length && i2 >= length2) {
                        break;
                    }
                    if (i2 < length || i2 >= length2) {
                        i3 = (i2 >= length || i2 < length2) ? parseVersion[i2] - parseVersion2[i2] : 1;
                    } else {
                        i3 = -1;
                    }
                    if (i3 != 0) {
                        break;
                    }
                    i2++;
                }
                return i3;
            }
            return 1;
        }
        return -1;
    }
}
