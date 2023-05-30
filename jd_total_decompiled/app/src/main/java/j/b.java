package j;

import android.content.Context;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;

/* loaded from: classes11.dex */
public class b {
    public static boolean a(Context context) {
        return (context == null || ((NfcManager) context.getSystemService("nfc")).getDefaultAdapter() == null) ? false : true;
    }

    public static boolean b(Context context) {
        NfcAdapter defaultAdapter;
        return (context == null || (defaultAdapter = ((NfcManager) context.getSystemService("nfc")).getDefaultAdapter()) == null || !defaultAdapter.isEnabled()) ? false : true;
    }
}
