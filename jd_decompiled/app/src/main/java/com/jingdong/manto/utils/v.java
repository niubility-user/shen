package com.jingdong.manto.utils;

import android.webkit.ValueCallback;
import com.jingdong.manto.jsengine.IMantoWebViewJS;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes16.dex */
public class v {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements ValueCallback<String> {
        final /* synthetic */ b a;

        a(b bVar) {
            this.a = bVar;
        }

        @Override // android.webkit.ValueCallback
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onReceiveValue(String str) {
            if (str == null || !str.contains("11111")) {
                b bVar = this.a;
                if (bVar != null) {
                    bVar.a(str);
                    return;
                }
                return;
            }
            b bVar2 = this.a;
            if (bVar2 != null) {
                bVar2.onSuccess(str);
            }
        }
    }

    /* loaded from: classes16.dex */
    public interface b {
        void a(String str);

        void onSuccess(String str);
    }

    public static void a(IMantoWebViewJS iMantoWebViewJS, String str, b bVar) {
        if (MantoStringUtils.isEmpty(str) && bVar != null) {
            bVar.a("empty script");
        }
        iMantoWebViewJS.evaluateJavascript(str + String.format(";var ___result_return = function(){return %d;};___result_return();", Integer.valueOf((int) R2.drawable.security_total_key_capslock_selector_dark)), new a(bVar));
    }
}
