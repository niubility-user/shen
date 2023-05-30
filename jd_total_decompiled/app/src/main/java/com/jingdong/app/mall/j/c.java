package com.jingdong.app.mall.j;

import java.io.Serializable;

/* loaded from: classes4.dex */
public class c implements Serializable {
    private static final long serialVersionUID = 1;
    public String content;
    public String customerHeadIconUrl;
    public String customerName;
    public String customertype;
    public String date;

    public String toString() {
        return "IpcTransferMessageEntity [content=" + this.content + ", date=" + this.date + ", customertype=" + this.customertype + ", customerName=" + this.customerName + ", customerHeadIconUrl=" + this.customerHeadIconUrl + "]";
    }
}
