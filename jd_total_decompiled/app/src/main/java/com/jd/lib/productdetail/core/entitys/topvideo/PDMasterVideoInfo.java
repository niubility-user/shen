package com.jd.lib.productdetail.core.entitys.topvideo;

import android.text.TextUtils;
import java.util.List;

/* loaded from: classes15.dex */
public class PDMasterVideoInfo {
    public int duration;
    public PDMasterVideoExtInfo extInfo;
    public String imageUrl;
    public String playUrl;
    public String videoDuration;
    public String videoId;
    public List<PDMasterVideoMarkInfo> videoMarkList;
    public PDMasterVideoShareInfo videoShare;

    public boolean isHasExtInfo() {
        PDMasterVideoExtInfo pDMasterVideoExtInfo = this.extInfo;
        return (pDMasterVideoExtInfo == null || TextUtils.isEmpty(pDMasterVideoExtInfo.trailerPlayUrl) || TextUtils.isEmpty(this.extInfo.trailerImgUrl) || this.extInfo.trailerDuration <= 0) ? false : true;
    }

    public boolean isHasMarkInfo() {
        List<PDMasterVideoMarkInfo> list = this.videoMarkList;
        return (list == null || list.isEmpty()) ? false : true;
    }

    public boolean isHasShareInfo() {
        PDMasterVideoShareInfo pDMasterVideoShareInfo = this.videoShare;
        return (pDMasterVideoShareInfo == null || TextUtils.isEmpty(pDMasterVideoShareInfo.url) || TextUtils.isEmpty(this.videoShare.microBlog)) ? false : true;
    }
}
