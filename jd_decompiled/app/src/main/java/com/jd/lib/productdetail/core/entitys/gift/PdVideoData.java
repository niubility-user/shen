package com.jd.lib.productdetail.core.entitys.gift;

/* loaded from: classes15.dex */
public class PdVideoData {
    public boolean del;
    public long fileSize;
    public String giftId;
    public String orderId;
    public String type = "refreshVideo";
    public String videoId;
    public String videoName;

    public PdVideoData(String str, long j2, String str2, String str3) {
        this.videoName = str;
        this.fileSize = j2;
        this.orderId = str2;
        this.giftId = str3;
    }

    public void setType(String str) {
        this.type = str;
    }
}
