package com.tencent.smtt.sdk;

import com.tencent.smtt.export.external.interfaces.IX5WebBackForwardList;

/* loaded from: classes9.dex */
public class WebBackForwardList {
    private IX5WebBackForwardList a = null;
    private android.webkit.WebBackForwardList b = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static WebBackForwardList a(android.webkit.WebBackForwardList webBackForwardList) {
        if (webBackForwardList == null) {
            return null;
        }
        WebBackForwardList webBackForwardList2 = new WebBackForwardList();
        webBackForwardList2.b = webBackForwardList;
        return webBackForwardList2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static WebBackForwardList a(IX5WebBackForwardList iX5WebBackForwardList) {
        if (iX5WebBackForwardList == null) {
            return null;
        }
        WebBackForwardList webBackForwardList = new WebBackForwardList();
        webBackForwardList.a = iX5WebBackForwardList;
        return webBackForwardList;
    }

    public int getCurrentIndex() {
        IX5WebBackForwardList iX5WebBackForwardList = this.a;
        return iX5WebBackForwardList != null ? iX5WebBackForwardList.getCurrentIndex() : this.b.getCurrentIndex();
    }

    public WebHistoryItem getCurrentItem() {
        IX5WebBackForwardList iX5WebBackForwardList = this.a;
        return iX5WebBackForwardList != null ? WebHistoryItem.a(iX5WebBackForwardList.getCurrentItem()) : WebHistoryItem.a(this.b.getCurrentItem());
    }

    public WebHistoryItem getItemAtIndex(int i2) {
        IX5WebBackForwardList iX5WebBackForwardList = this.a;
        return iX5WebBackForwardList != null ? WebHistoryItem.a(iX5WebBackForwardList.getItemAtIndex(i2)) : WebHistoryItem.a(this.b.getItemAtIndex(i2));
    }

    public int getSize() {
        IX5WebBackForwardList iX5WebBackForwardList = this.a;
        return iX5WebBackForwardList != null ? iX5WebBackForwardList.getSize() : this.b.getSize();
    }
}
