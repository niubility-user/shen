package com.jingdong.common.navutils;

import android.net.Uri;
import android.text.TextUtils;
import com.jingdong.sdk.oklog.OKLog;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void parseJson() {
        /*
            r9 = this;
            java.lang.String r0 = "NavCenterParser"
            r1 = 0
            com.jingdong.jdsdk.JdSdk r2 = com.jingdong.jdsdk.JdSdk.getInstance()     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L95
            android.content.Context r2 = r2.getApplicationContext()     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L95
            android.content.res.AssetManager r2 = r2.getAssets()     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L95
            java.lang.String r3 = "navcenter.json"
            java.io.InputStream r2 = r2.open(r3)     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L95
            if (r2 != 0) goto L18
            return
        L18:
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L95
            r3.<init>()     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L95
            java.io.BufferedOutputStream r4 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L95
            r4.<init>(r3)     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L95
            java.io.BufferedInputStream r5 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8d
            r5.<init>(r2)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8d
            r1 = 4096(0x1000, float:5.74E-42)
            byte[] r1 = new byte[r1]     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> Lb1
        L2b:
            int r2 = r5.read(r1)     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> Lb1
            r6 = -1
            r7 = 0
            if (r2 == r6) goto L37
            r4.write(r1, r7, r2)     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> Lb1
            goto L2b
        L37:
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> Lb1
            java.lang.String r2 = r3.toString()     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> Lb1
            r1.<init>(r2)     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> Lb1
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> Lb1
            r2.<init>()     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> Lb1
            r9.mActivityInfoList = r2     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> Lb1
            int r2 = r1.length()     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> Lb1
        L4b:
            if (r7 >= r2) goto L7c
            com.jingdong.common.navutils.NavCenterParser$NavActivityInfo r3 = new com.jingdong.common.navutils.NavCenterParser$NavActivityInfo     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> Lb1
            r3.<init>()     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> Lb1
            java.lang.Object r6 = r1.get(r7)     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> Lb1
            org.json.JSONObject r6 = (org.json.JSONObject) r6     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> Lb1
            java.lang.String r8 = "activityName"
            java.lang.String r8 = r6.optString(r8)     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> Lb1
            r3.activityName = r8     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> Lb1
            java.lang.String r8 = "bundleName"
            java.lang.String r8 = r6.optString(r8)     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> Lb1
            r3.bundleName = r8     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> Lb1
            java.lang.String r8 = "intentFilterList"
            org.json.JSONArray r6 = r6.getJSONArray(r8)     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> Lb1
            java.util.List r6 = r9.parseIntentFilterList(r6)     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> Lb1
            r3.intentFilterList = r6     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> Lb1
            java.util.List<com.jingdong.common.navutils.NavCenterParser$NavActivityInfo> r6 = r9.mActivityInfoList     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> Lb1
            r6.add(r3)     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> Lb1
            int r7 = r7 + 1
            goto L4b
        L7c:
            r5.close()     // Catch: java.io.IOException -> L80
            goto L84
        L80:
            r1 = move-exception
            com.jingdong.sdk.oklog.OKLog.e(r0, r1)
        L84:
            r4.flush()     // Catch: java.io.IOException -> Lac
            goto Lb0
        L88:
            r1 = move-exception
            goto L99
        L8a:
            r2 = move-exception
            r5 = r1
            goto L93
        L8d:
            r2 = move-exception
            r5 = r1
            goto L98
        L90:
            r2 = move-exception
            r4 = r1
            r5 = r4
        L93:
            r1 = r2
            goto Lb2
        L95:
            r2 = move-exception
            r4 = r1
            r5 = r4
        L98:
            r1 = r2
        L99:
            com.jingdong.sdk.oklog.OKLog.e(r0, r1)     // Catch: java.lang.Throwable -> Lb1
            if (r5 == 0) goto La6
            r5.close()     // Catch: java.io.IOException -> La2
            goto La6
        La2:
            r1 = move-exception
            com.jingdong.sdk.oklog.OKLog.e(r0, r1)
        La6:
            if (r4 == 0) goto Lb0
            r4.flush()     // Catch: java.io.IOException -> Lac
            goto Lb0
        Lac:
            r1 = move-exception
            com.jingdong.sdk.oklog.OKLog.e(r0, r1)
        Lb0:
            return
        Lb1:
            r1 = move-exception
        Lb2:
            if (r5 == 0) goto Lbc
            r5.close()     // Catch: java.io.IOException -> Lb8
            goto Lbc
        Lb8:
            r2 = move-exception
            com.jingdong.sdk.oklog.OKLog.e(r0, r2)
        Lbc:
            if (r4 == 0) goto Lc6
            r4.flush()     // Catch: java.io.IOException -> Lc2
            goto Lc6
        Lc2:
            r2 = move-exception
            com.jingdong.sdk.oklog.OKLog.e(r0, r2)
        Lc6:
            goto Lc8
        Lc7:
            throw r1
        Lc8:
            goto Lc7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.navutils.NavCenterParser.parseJson():void");
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
