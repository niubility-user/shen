package com.jingdong.app.mall.im.business;

import android.app.Activity;
import com.jingdong.common.entity.ShareImageInfo;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.utils.ShareUtil;
import com.jingdong.service.impl.IMShare;

/* loaded from: classes4.dex */
public class x extends IMShare {
    @Override // com.jingdong.service.impl.IMShare, com.jingdong.service.service.ShareService
    public void localPathShare(Activity activity, String str) {
        ShareImageInfo shareImageInfo = new ShareImageInfo();
        shareImageInfo.directPath = str;
        ShareInfo shareInfo = new ShareInfo();
        shareInfo.setChannels("QRCode");
        shareInfo.setShareImageInfo(shareImageInfo);
        ShareUtil.open(activity, shareInfo);
    }

    @Override // com.jingdong.service.impl.IMShare, com.jingdong.service.service.ShareService
    public void pictureShare(Activity activity, String str) {
        ShareImageInfo shareImageInfo = new ShareImageInfo();
        shareImageInfo.directUrl = str;
        ShareInfo shareInfo = new ShareInfo();
        shareImageInfo.imageShareType = 1;
        shareImageInfo.isDecodeDirectUrl = 1;
        shareInfo.setChannels("QRCode");
        shareInfo.setShareImageInfo(shareImageInfo);
        ShareUtil.panel(activity, shareInfo);
    }
}
