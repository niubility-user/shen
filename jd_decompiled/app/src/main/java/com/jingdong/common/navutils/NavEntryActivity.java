package com.jingdong.common.navutils;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class NavEntryActivity extends Activity {
    private static final String NAV_MAIDIAN_EXTERNAL = "navMaidianExternal";
    private static String TAG = "NavEntryActivity";

    private void execEntry() {
        Uri data;
        try {
            try {
                data = getIntent().getData();
            } catch (Exception e2) {
                OKLog.e(TAG, e2);
            }
            if (data == null) {
                OKLog.e(TAG, "getData Error");
            } else if (TextUtils.isEmpty(data.toString())) {
                OKLog.e(TAG, "uri null Error");
            } else {
                Uri build = data.buildUpon().scheme("http").build();
                JDMtaUtils.sendCommonData(this, NAV_MAIDIAN_EXTERNAL, build.getHost(), "NavEntryActivity.execEntry", (Object) null, "", "", "");
                NavCenter.from(this).navTo(build);
            }
        } finally {
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        execEntry();
    }
}
