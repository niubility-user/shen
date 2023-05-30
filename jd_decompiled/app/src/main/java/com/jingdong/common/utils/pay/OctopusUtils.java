package com.jingdong.common.utils.pay;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import com.jingdong.common.R;
import com.jingdong.common.ui.JDCheckDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes6.dex */
public class OctopusUtils {
    public static final String COM_OCTOPUSCARDS_NFC_READER = "com.octopuscards.nfc_reader";
    public static final int OCTOPUS_APP_REQUEST_CODE = 10000;
    private static final String TAG = "OctopusUtils";

    private static String getOctopusURI(String str) {
        return "octopus://payment?token=" + str;
    }

    public static boolean isAppExist(Activity activity, String str) {
        if (activity != null) {
            try {
                if (TextUtils.isEmpty(str)) {
                    return false;
                }
                return BaseInfo.isPkgInstalled(activity, str);
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static void showOpenOctopusFailDialog(final Activity activity, final String str) {
        String string = JdSdk.getInstance().getApplication().getString(R.string.open_octopus_fail_title);
        String string2 = JdSdk.getInstance().getApplication().getString(R.string.open_octopus_fail_cancel);
        String string3 = JdSdk.getInstance().getApplication().getString(R.string.open_octopus_fail_retry);
        final JDCheckDialog createJdDialogWithStyle6 = JDDialogFactory.getInstance().createJdDialogWithStyle6(activity, string, JdSdk.getInstance().getApplication().getString(R.string.retry), string2, string3);
        createJdDialogWithStyle6.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.utils.pay.OctopusUtils.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JDCheckDialog.this.dismiss();
            }
        });
        createJdDialogWithStyle6.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.utils.pay.OctopusUtils.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JDCheckDialog.this.dismiss();
                OctopusUtils.startpay(activity, str);
            }
        });
        createJdDialogWithStyle6.show();
    }

    public static void startpay(Activity activity, String str) {
        if (activity != null) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    if (BaseInfo.isPkgInstalled(activity, COM_OCTOPUSCARDS_NFC_READER)) {
                        activity.startActivityForResult(new Intent("android.intent.action.VIEW", Uri.parse(getOctopusURI(str))), 10000);
                    } else {
                        showOpenOctopusFailDialog(activity, str);
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private static String getOctopusURI(String str, String str2) {
        return str + "://payment?token=" + str2;
    }
}
