package com.jingdong.common.personal;

import android.text.TextUtils;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.web.IWebBusinessParams;
import java.util.regex.Pattern;

/* loaded from: classes5.dex */
public class UploadContactListImpl {
    public static final int CONTACTS_DATA_EMPTY_STRING_LENGHT = 2;
    private static final String KEY = "@G$(^b*~";
    public static final String TAG = "UploadContactListImpl";
    IWebBusinessParams businessParams;
    public final Pattern pattern = Pattern.compile("1\\d{10}$");

    /* loaded from: classes5.dex */
    private interface CallBackH5Status {
        public static final int COMPLETE = 0;
        public static final int CONTACT_DATA_EMPTY = 1;
        public static final int JD_DIALOG_CANCEL = 2;
        public static final int SYSTEM_DIALOG_DENIED = 3;
        public static final int UPLOADING_NETWORK_ERROR = 4;
    }

    public UploadContactListImpl(IWebBusinessParams iWebBusinessParams) {
        this.businessParams = iWebBusinessParams;
    }

    private void callBackH5(final String str, final int i2) {
        BaseActivity baseActivity;
        IWebBusinessParams iWebBusinessParams = this.businessParams;
        if (iWebBusinessParams == null || (baseActivity = iWebBusinessParams.getBaseActivity()) == null || TextUtils.isEmpty(str)) {
            return;
        }
        baseActivity.runOnUiThread(new Runnable() { // from class: com.jingdong.common.personal.UploadContactListImpl.1
            @Override // java.lang.Runnable
            public void run() {
                IWebBusinessParams iWebBusinessParams2 = UploadContactListImpl.this.businessParams;
                if (iWebBusinessParams2 == null) {
                    return;
                }
                iWebBusinessParams2.injectJs("javascript:" + str + "(" + i2 + ",-1)");
            }
        });
    }

    public void uploadContactList(String str, String str2) {
        IWebBusinessParams iWebBusinessParams = this.businessParams;
        if (iWebBusinessParams == null || iWebBusinessParams.getBaseActivity() == null) {
            return;
        }
        callBackH5(str, 3);
    }
}
