package com.jd.cashier.app.jdlibcutter.impl.share;

import android.app.Activity;
import android.content.Context;
import com.jd.cashier.app.jdlibcutter.protocol.share.IShare;
import com.jd.cashier.app.jdlibcutter.protocol.share.ShareKey;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.utils.ShareUtil;
import java.util.Map;

/* loaded from: classes13.dex */
public class ShareImpl implements IShare, ShareKey {
    private static final String SHARE_CHANNEL = "Wxfriends";

    @Override // com.jd.cashier.app.jdlibcutter.protocol.share.IShare
    public void doShare(Context context, Map<String, String> map) {
        try {
            if (!(context instanceof Activity) || map == null || map.isEmpty()) {
                return;
            }
            ShareInfo shareInfo = new ShareInfo();
            shareInfo.setChannels("Wxfriends");
            shareInfo.setTitle(map.get("title"));
            shareInfo.setUrl(map.get("shareUrl"));
            shareInfo.setIconUrl(map.get("imageUrl"));
            shareInfo.setSummary(map.get("description"));
            ShareUtil.open((Activity) context, shareInfo);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.share.IShare
    public int getShareRequestCode() {
        return 1215;
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.share.IShare
    public int getShareResultSucCode() {
        return 11;
    }
}
