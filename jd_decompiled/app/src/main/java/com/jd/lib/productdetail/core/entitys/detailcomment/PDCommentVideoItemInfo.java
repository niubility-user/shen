package com.jd.lib.productdetail.core.entitys.detailcomment;

import android.os.Parcel;

/* loaded from: classes15.dex */
public class PDCommentVideoItemInfo extends PdCommentItemInfo {
    public String largeVideoImgUrl;
    public PdCommentItemInfo pdCommentItemInfo;
    public String sku;
    public String videoPlayAddress;
    public String videoTime;
    public String videoUrl;

    protected PDCommentVideoItemInfo(Parcel parcel) {
        super(parcel);
    }

    public PDCommentVideoItemInfo() {
    }
}
