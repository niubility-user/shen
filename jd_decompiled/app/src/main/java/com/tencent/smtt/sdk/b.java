package com.tencent.smtt.sdk;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class b implements com.tencent.smtt.export.external.interfaces.DownloadListener {
    private DownloadListener a;
    private WebView b;

    public b(WebView webView, DownloadListener downloadListener, boolean z) {
        this.a = downloadListener;
        this.b = webView;
    }

    @Override // com.tencent.smtt.export.external.interfaces.DownloadListener
    public void onDownloadStart(String str, String str2, String str3, String str4, long j2) {
        onDownloadStart(str, null, null, str2, str3, str4, j2, null, null);
    }

    @Override // com.tencent.smtt.export.external.interfaces.DownloadListener
    public void onDownloadStart(String str, String str2, byte[] bArr, String str3, String str4, String str5, long j2, String str6, String str7) {
        DownloadListener downloadListener = this.a;
        if (downloadListener == null) {
            return;
        }
        downloadListener.onDownloadStart(str, str3, str4, str5, j2);
    }

    @Override // com.tencent.smtt.export.external.interfaces.DownloadListener
    public void onDownloadVideo(String str, long j2, int i2) {
    }
}
