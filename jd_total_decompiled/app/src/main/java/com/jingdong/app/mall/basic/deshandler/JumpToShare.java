package com.jingdong.app.mall.basic.deshandler;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.libs.jdmbridge.base.proxy.share.IShareAdapter;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.utils.ShareUtil;
import com.jingdong.corelib.utils.Log;

@Des(des = "share")
/* loaded from: classes19.dex */
public class JumpToShare extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (context instanceof Activity) {
            String string = bundle.getString("shareUrl", "");
            if (Log.D) {
                Log.d(this.a, "JumpToShare: current activity: " + context.getClass().getName());
            }
            ShareInfo parseShareInfoFromBundle = ShareUtil.parseShareInfoFromBundle(bundle);
            if (parseShareInfoFromBundle != null) {
                parseShareInfoFromBundle.setNormalText(context.getString(R.string.wx_share_activity_content_hint1, string));
                String action = parseShareInfoFromBundle.getAction();
                if (TextUtils.isEmpty(action)) {
                    action = (TextUtils.isEmpty(parseShareInfoFromBundle.getChannels()) || parseShareInfoFromBundle.getChannels().contains(DYConstants.DY_REGEX_COMMA)) ? IShareAdapter.SHARE_ACTION_PANE : IShareAdapter.SHARE_ACTION_OPEN;
                }
                if (IShareAdapter.SHARE_ACTION_OPEN.equals(action)) {
                    ShareUtil.open((Activity) context, parseShareInfoFromBundle);
                } else {
                    ShareUtil.panel((Activity) context, parseShareInfoFromBundle);
                }
            }
            c(context);
        }
    }
}
