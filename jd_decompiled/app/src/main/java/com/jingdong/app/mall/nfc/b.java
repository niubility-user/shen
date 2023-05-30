package com.jingdong.app.mall.nfc;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Build;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.BaseActivity;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.platform.business.personal.R2;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.nio.charset.Charset;
import org.apache.commons.codec.CharEncoding;

/* loaded from: classes4.dex */
public class b {

    /* renamed from: f */
    private static final String f11414f = "b";
    private NfcAdapter a;
    private PendingIntent b;

    /* renamed from: c */
    private IntentFilter f11415c;
    private BaseActivity d;

    /* renamed from: e */
    private String[][] f11416e = {new String[]{"android.nfc.tech.MifareClassic"}, new String[]{"android.nfc.tech.MifareUltralight"}, new String[]{"android.nfc.tech.NfcA"}, new String[]{"android.nfc.tech.NfcF"}, new String[]{"android.nfc.tech.Ndef"}, new String[]{"android.nfc.tech.NfcV"}, new String[]{"android.nfc.tech.NfcB"}};

    public b(BaseActivity baseActivity) {
        this.d = baseActivity;
    }

    public static boolean e() {
        return TextUtils.equals("1", JDMobileConfig.getInstance().getConfig("JDScan", "scanNfcSwitcher", "scanNfcSwitcher", "0"));
    }

    private boolean f(Intent intent) {
        String str;
        Tag tag = (Tag) intent.getParcelableExtra("android.nfc.extra.TAG");
        String a = g.f.b.b.a.a(tag.getId());
        if (TextUtils.isEmpty(a)) {
            return false;
        }
        String str2 = "";
        if (Ndef.get(tag) != null) {
            try {
                Parcelable[] parcelableArrayExtra = intent.getParcelableArrayExtra("android.nfc.extra.NDEF_MESSAGES");
                if (parcelableArrayExtra != null) {
                    if (Log.D) {
                        Log.i(f11414f, "getNdefMsg: ndef\u683c\u5f0f ");
                    }
                    NdefMessage[] ndefMessageArr = new NdefMessage[parcelableArrayExtra.length];
                    for (int i2 = 0; i2 < parcelableArrayExtra.length; i2++) {
                        ndefMessageArr[i2] = (NdefMessage) parcelableArrayExtra[i2];
                    }
                    com.jingdong.app.mall.nfc.f.a aVar = a.b(ndefMessageArr[0]).get(0);
                    if (Log.D) {
                        Log.i(f11414f, "setNFCMsgView: " + aVar.a());
                    }
                    String str3 = new String(ndefMessageArr[0].getRecords()[0].getType(), Charset.forName(CharEncoding.US_ASCII));
                    if (Log.D) {
                        Log.i(f11414f, "type = " + str3);
                    }
                    str2 = aVar.a();
                    str = str3;
                    d.f(this.d, str2, str, a);
                    return true;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (Log.D) {
                    Log.i(f11414f, "exception = " + e2.toString());
                }
                return false;
            }
        }
        str = "";
        d.f(this.d, str2, str, a);
        return true;
    }

    public void a() {
        NfcAdapter nfcAdapter = this.a;
        if (nfcAdapter != null) {
            BaseActivity baseActivity = this.d;
            if (baseActivity instanceof Activity) {
                nfcAdapter.disableForegroundDispatch(baseActivity);
            }
        }
    }

    public void b() {
        IntentFilter intentFilter;
        String[][] strArr;
        NfcAdapter nfcAdapter = this.a;
        if (nfcAdapter != null) {
            BaseActivity baseActivity = this.d;
            if (!(baseActivity instanceof Activity) || (intentFilter = this.f11415c) == null || (strArr = this.f11416e) == null) {
                return;
            }
            nfcAdapter.enableForegroundDispatch(baseActivity, this.b, new IntentFilter[]{intentFilter}, strArr);
        }
    }

    public int c(Intent intent) {
        if (intent == null) {
            return R2.anim.slide_out_to_right;
        }
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            return R2.anim.slide_out_to_right;
        }
        action.hashCode();
        char c2 = '\uffff';
        switch (action.hashCode()) {
            case -1634370981:
                if (action.equals("android.nfc.action.TECH_DISCOVERED")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1468892125:
                if (action.equals("android.nfc.action.TAG_DISCOVERED")) {
                    c2 = 1;
                    break;
                }
                break;
            case 1865807226:
                if (action.equals("android.nfc.action.NDEF_DISCOVERED")) {
                    c2 = 2;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
            case 1:
            case 2:
                if (f(intent)) {
                    return R2.attr.flow_lastVerticalBias;
                }
                break;
        }
        return R2.anim.slide_out_to_right;
    }

    public void d() {
        this.a = NfcAdapter.getDefaultAdapter(this.d);
        this.b = PendingIntent.getActivity(this.d, 0, new Intent(this.d, NfcIntentHandleActivity.class).addFlags(SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING), Build.VERSION.SDK_INT >= 31 ? 67108864 : 0);
        IntentFilter intentFilter = new IntentFilter("android.nfc.action.TECH_DISCOVERED");
        this.f11415c = intentFilter;
        intentFilter.addCategory("android.intent.category.DEFAULT");
    }
}
