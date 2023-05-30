package com.jingdong.common.widget.custom.livewidget.bean;

import javax.annotation.Resource;

/* loaded from: classes.dex */
public class LiveVideoEntity {
    public String mIndexImage;
    public String mLiveId;
    public boolean mNeedDefaultImg;
    public String mOrigin;
    @Resource
    public int mPlaceHolderImgId;
    public int mStatus;
    public String mUrl;
    public String mVideoOrientation;

    public LiveVideoEntity(String str, String str2, String str3, String str4, String str5) {
        this(str, str2, str3, str4, str5, 1);
    }

    public LiveVideoEntity(String str, String str2, String str3, String str4, String str5, int i2) {
        this.mNeedDefaultImg = false;
        this.mOrigin = str;
        this.mUrl = str2;
        this.mLiveId = str3;
        this.mVideoOrientation = str4;
        this.mIndexImage = str5;
        this.mStatus = i2;
    }

    public LiveVideoEntity(String str, String str2, String str3, String str4) {
        this(str, str2, str3, str4, null);
    }
}
