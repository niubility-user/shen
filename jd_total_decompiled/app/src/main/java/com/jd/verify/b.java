package com.jd.verify;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;
import com.jd.verify.model.IninVerifyInfo;
import verify.jd.com.myverify.R;

/* loaded from: classes18.dex */
public class b {
    private com.jd.verify.common.a a = new a(this);

    /* loaded from: classes18.dex */
    class a implements com.jd.verify.common.a {
        a(b bVar) {
        }

        @Override // com.jd.verify.common.a
        public void a() {
        }

        @Override // com.jd.verify.common.a
        public void a(int i2) {
        }

        @Override // com.jd.verify.common.a
        public void a(IninVerifyInfo ininVerifyInfo) {
        }

        @Override // com.jd.verify.common.a
        public void b() {
        }

        @Override // com.jd.verify.common.a
        public void b(int i2) {
        }

        @Override // com.jd.verify.common.a
        public void c() {
        }

        @Override // com.jd.verify.common.a
        public void d() {
        }
    }

    private void a(Context context, EmbedVerifyView embedVerifyView, CallBack callBack, String str, String str2, String str3, String str4) {
        if (embedVerifyView != null) {
            embedVerifyView.stopLoad();
            com.jd.verify.model.a aVar = new com.jd.verify.model.a();
            aVar.a("0");
            embedVerifyView.setAdditionParam(aVar);
            embedVerifyView.setUdid(str2);
            embedVerifyView.setSession_id(str);
            embedVerifyView.setAccount(str3);
            embedVerifyView.setLanguage(str4);
            embedVerifyView.setNotifyListener(this.a);
            embedVerifyView.startLoad();
        }
    }

    public void b(Context context, EmbedVerifyView embedVerifyView, CallBack callBack, String str, String str2, String str3, String str4) {
        if (context == null) {
            com.jd.verify.j.d.b("context is null");
        } else if (!com.jd.verify.j.b.l(context)) {
            Toast.makeText(context, context.getResources().getString(R.string.verify_fail), 0).show();
            com.jd.verify.j.d.b("bad network");
            if (callBack == null || !(callBack instanceof ShowCapCallback)) {
                return;
            }
            ((ShowCapCallback) callBack).loadFail();
        } else if (TextUtils.isEmpty(str)) {
            Toast.makeText(context, context.getResources().getString(R.string.verify_fail), 0).show();
            if (callBack != null && (callBack instanceof ShowCapCallback)) {
                ((ShowCapCallback) callBack).loadFail();
            }
            com.jd.verify.j.d.b("sid is null");
        } else {
            if (TextUtils.isEmpty(str2)) {
                str2 = com.jd.verify.j.b.b();
            }
            a(context, embedVerifyView, callBack, str, str2, str3, str4);
        }
    }
}
