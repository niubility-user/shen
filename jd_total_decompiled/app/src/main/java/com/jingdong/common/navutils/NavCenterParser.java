package com.jingdong.common.navutils;

import android.net.Uri;
import android.text.TextUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.Constants;
import com.jingdong.sdk.oklog.OKLog;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class NavCenterParser {
    private static final String TAG = "NavCenterParser";
    private static NavCenterParser mInstance;
    private List<NavActivityInfo> mActivityInfoList;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class DataInfo {
        String host;
        String path;
        String pathPattern;
        String scheme;

        private DataInfo() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class IntentFilterInfo {
        List<DataInfo> dataInfoList;

        private IntentFilterInfo() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public class NavActivityInfo {
        String activityName;
        String bundleName;
        List<IntentFilterInfo> intentFilterList;

        NavActivityInfo() {
        }
    }

    private NavCenterParser() {
    }

    private List<DataInfo> getDataInfoList(NavActivityInfo navActivityInfo) {
        ArrayList arrayList = null;
        if (navActivityInfo == null) {
            return null;
        }
        List<IntentFilterInfo> list = navActivityInfo.intentFilterList;
        if (list != null && list.size() > 0) {
            arrayList = new ArrayList();
            for (IntentFilterInfo intentFilterInfo : navActivityInfo.intentFilterList) {
                List<DataInfo> list2 = intentFilterInfo.dataInfoList;
                if (list2 != null && list2.size() > 0) {
                    arrayList.addAll(intentFilterInfo.dataInfoList);
                }
            }
        }
        return arrayList;
    }

    public static NavCenterParser getInstance() {
        if (mInstance == null) {
            mInstance = new NavCenterParser();
        }
        return mInstance;
    }

    private boolean isUriMatchData(Uri uri, DataInfo dataInfo) {
        if (uri != null && dataInfo != null && dataInfo.scheme.equals(uri.getScheme()) && dataInfo.host.equals(uri.getHost())) {
            if (TextUtils.isEmpty(dataInfo.path) && TextUtils.isEmpty(dataInfo.pathPattern)) {
                return TextUtils.isEmpty(uri.getPath());
            }
            if (TextUtils.isEmpty(uri.getPath())) {
                return false;
            }
            if (dataInfo.path.equals(uri.getPath()) || uri.getPath().matches(dataInfo.pathPattern)) {
                return true;
            }
        }
        return false;
    }

    private List<DataInfo> parseDataInfoList(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        try {
            int length = jSONArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i2);
                DataInfo dataInfo = new DataInfo();
                dataInfo.host = jSONObject.optString("host");
                dataInfo.scheme = jSONObject.optString("scheme");
                dataInfo.path = jSONObject.optString("path");
                dataInfo.pathPattern = jSONObject.optString("pathPattern");
                arrayList.add(dataInfo);
            }
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
        return arrayList;
    }

    private List<IntentFilterInfo> parseIntentFilterList(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        try {
            int length = jSONArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                IntentFilterInfo intentFilterInfo = new IntentFilterInfo();
                intentFilterInfo.dataInfoList = parseDataInfoList(((JSONObject) jSONArray.get(i2)).getJSONArray("dataInfoList"));
                arrayList.add(intentFilterInfo);
            }
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00a8 A[Catch: IOException -> 0x00ac, TRY_ENTER, TRY_LEAVE, TryCatch #10 {IOException -> 0x00ac, blocks: (B:21:0x0084, B:42:0x00a8), top: B:73:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00b4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00be A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x009e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:79:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:45:0x00ad -> B:72:0x00b0). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void parseJson() {
        BufferedOutputStream bufferedOutputStream;
        BufferedInputStream bufferedInputStream;
        BufferedInputStream bufferedInputStream2;
        Exception e2;
        InputStream open;
        int i2;
        try {
        } catch (IOException e3) {
            OKLog.e(TAG, e3);
        }
        try {
            try {
                open = JdSdk.getInstance().getApplicationContext().getAssets().open("navcenter.json");
            } catch (Exception e4) {
                e = e4;
                bufferedOutputStream = null;
                bufferedInputStream2 = null;
            } catch (Throwable th) {
                th = th;
                bufferedOutputStream = null;
                bufferedInputStream = null;
            }
            if (open == null) {
                return;
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
            try {
                bufferedInputStream2 = new BufferedInputStream(open);
            } catch (Exception e5) {
                e = e5;
                bufferedInputStream2 = null;
                e2 = e;
                OKLog.e(TAG, e2);
                if (bufferedInputStream2 != null) {
                }
                if (bufferedOutputStream == null) {
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream = null;
                th = th;
                if (bufferedInputStream != null) {
                }
                if (bufferedOutputStream != null) {
                }
                throw th;
            }
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = bufferedInputStream2.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    bufferedOutputStream.write(bArr, 0, read);
                }
                JSONArray jSONArray = new JSONArray(byteArrayOutputStream.toString());
                this.mActivityInfoList = new ArrayList();
                int length = jSONArray.length();
                for (i2 = 0; i2 < length; i2++) {
                    NavActivityInfo navActivityInfo = new NavActivityInfo();
                    JSONObject jSONObject = (JSONObject) jSONArray.get(i2);
                    navActivityInfo.activityName = jSONObject.optString(Constants.JLOG_ACTIVITYNAME_PARAM_KEY);
                    navActivityInfo.bundleName = jSONObject.optString("bundleName");
                    navActivityInfo.intentFilterList = parseIntentFilterList(jSONObject.getJSONArray("intentFilterList"));
                    this.mActivityInfoList.add(navActivityInfo);
                }
                try {
                    bufferedInputStream2.close();
                } catch (IOException e6) {
                    OKLog.e(TAG, e6);
                }
                bufferedOutputStream.flush();
            } catch (Exception e7) {
                e2 = e7;
                OKLog.e(TAG, e2);
                if (bufferedInputStream2 != null) {
                    try {
                        bufferedInputStream2.close();
                    } catch (IOException e8) {
                        OKLog.e(TAG, e8);
                    }
                }
                if (bufferedOutputStream == null) {
                    bufferedOutputStream.flush();
                }
            }
        } catch (Throwable th3) {
            th = th3;
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e9) {
                    OKLog.e(TAG, e9);
                }
            }
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.flush();
                } catch (IOException e10) {
                    OKLog.e(TAG, e10);
                }
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NavActivityInfo parseUri(Uri uri) {
        if (DegradeConfig.getInstance().shouldUseNavCenterParser()) {
            if (this.mActivityInfoList == null) {
                parseJson();
            }
            List<NavActivityInfo> list = this.mActivityInfoList;
            if (list == null) {
                return null;
            }
            for (NavActivityInfo navActivityInfo : list) {
                List<DataInfo> dataInfoList = getDataInfoList(navActivityInfo);
                if (dataInfoList != null && dataInfoList.size() > 0) {
                    Iterator<DataInfo> it = dataInfoList.iterator();
                    while (it.hasNext()) {
                        if (isUriMatchData(uri, it.next())) {
                            return navActivityInfo;
                        }
                    }
                }
            }
            return null;
        }
        return null;
    }
}
