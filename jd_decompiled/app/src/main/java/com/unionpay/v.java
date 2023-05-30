package com.unionpay;

/* loaded from: classes11.dex */
final class v implements Runnable {
    final /* synthetic */ aa a;
    final /* synthetic */ String b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ ab f18235c;
    final /* synthetic */ WebViewJavascriptBridge d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public v(WebViewJavascriptBridge webViewJavascriptBridge, aa aaVar, String str, ab abVar) {
        this.d = webViewJavascriptBridge;
        this.a = aaVar;
        this.b = str;
        this.f18235c = abVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        aa aaVar = this.a;
        if (aaVar != null) {
            aaVar.a(this.b, this.f18235c);
        }
    }
}
