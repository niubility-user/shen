package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkLiveHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.runTimeConfig.ConfigUtil;
import com.jingdong.jdsdk.widget.ToastUtils;

@Des(des = JumpUtil.VALUE_DES_LIVE_ROOM)
/* loaded from: classes19.dex */
public class JumpToLive_room extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if ("1".equals(ConfigUtil.getStringFromPreference("livePlaySwitch", "1"))) {
            DeepLinkLiveHelper.startVideoLiveRoomActivity(context, bundle);
        } else {
            ToastUtils.shortToast((int) R.string.tip_live_player_room_closed);
        }
        c(context);
    }
}
