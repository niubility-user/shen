package g.f.c.b;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import com.tencent.wcdb.database.SQLiteDatabase;

/* loaded from: classes13.dex */
public class a {
    private NfcAdapter a;
    private PendingIntent b;

    /* renamed from: c  reason: collision with root package name */
    private IntentFilter f19549c;
    private Context d;

    /* renamed from: e  reason: collision with root package name */
    private String f19550e;

    /* renamed from: f  reason: collision with root package name */
    private String f19551f;

    /* renamed from: g  reason: collision with root package name */
    String[][] f19552g = {new String[]{"android.nfc.tech.MifareClassic"}, new String[]{"android.nfc.tech.MifareUltralight"}, new String[]{"android.nfc.tech.NfcA"}, new String[]{"android.nfc.tech.NfcF"}, new String[]{"android.nfc.tech.Ndef"}, new String[]{"android.nfc.tech.NfcV"}, new String[]{"android.nfc.tech.NfcB"}};

    public a(Context context, String str, String str2) {
        this.d = context;
        this.f19550e = str;
        this.f19551f = str2;
    }

    public void a() {
        NfcAdapter nfcAdapter = this.a;
        if (nfcAdapter != null) {
            Context context = this.d;
            if (context instanceof Activity) {
                nfcAdapter.disableForegroundDispatch((Activity) context);
            }
        }
    }

    public void b() {
        IntentFilter intentFilter;
        String[][] strArr;
        NfcAdapter nfcAdapter = this.a;
        if (nfcAdapter != null) {
            Context context = this.d;
            if (!(context instanceof Activity) || (intentFilter = this.f19549c) == null || (strArr = this.f19552g) == null) {
                return;
            }
            nfcAdapter.enableForegroundDispatch((Activity) context, this.b, new IntentFilter[]{intentFilter}, strArr);
        }
    }

    public void c() {
        this.a = NfcAdapter.getDefaultAdapter(this.d);
        Context context = this.d;
        Context context2 = this.d;
        this.b = PendingIntent.getActivity(context, 0, new Intent(context2, context2.getClass()).addFlags(SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING), 0);
        IntentFilter intentFilter = new IntentFilter("android.nfc.action.TECH_DISCOVERED");
        this.f19549c = intentFilter;
        intentFilter.addCategory("android.intent.category.DEFAULT");
    }

    public void d(Intent intent) {
        g.f.c.a.f(this.d, intent, this.f19550e, this.f19551f);
    }
}
