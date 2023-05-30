package com.jingdong.app.mall.nfc;

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class a {

    /* renamed from: com.jingdong.app.mall.nfc.a$a */
    /* loaded from: classes4.dex */
    public class C0364a implements com.jingdong.app.mall.nfc.f.a {
        final /* synthetic */ NdefRecord a;

        C0364a(NdefRecord ndefRecord) {
            this.a = ndefRecord;
        }

        @Override // com.jingdong.app.mall.nfc.f.a
        public String a() {
            return new String(this.a.getPayload()) + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
        }
    }

    public static List<com.jingdong.app.mall.nfc.f.a> a(NdefRecord[] ndefRecordArr) {
        ArrayList arrayList = new ArrayList();
        for (NdefRecord ndefRecord : ndefRecordArr) {
            if (com.jingdong.app.mall.nfc.f.d.b(ndefRecord)) {
                arrayList.add(com.jingdong.app.mall.nfc.f.d.c(ndefRecord));
            } else if (com.jingdong.app.mall.nfc.f.c.c(ndefRecord)) {
                arrayList.add(com.jingdong.app.mall.nfc.f.c.d(ndefRecord));
            } else if (com.jingdong.app.mall.nfc.f.b.d(ndefRecord)) {
                arrayList.add(com.jingdong.app.mall.nfc.f.b.e(ndefRecord));
            } else {
                arrayList.add(new C0364a(ndefRecord));
            }
        }
        return arrayList;
    }

    public static List<com.jingdong.app.mall.nfc.f.a> b(NdefMessage ndefMessage) {
        return a(ndefMessage.getRecords());
    }
}
