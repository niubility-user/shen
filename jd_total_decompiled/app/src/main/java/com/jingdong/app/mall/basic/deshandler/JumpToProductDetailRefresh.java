package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.jd.lib.productdetail.core.utils.PDLocalReceiver;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_PRODUCTDETAIL_REFRESH)
/* loaded from: classes19.dex */
public class JumpToProductDetailRefresh extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
        String string = bundle.getString("param");
        Intent intent = new Intent(PDLocalReceiver.INTENT_ACTION_REFRESH_PRODUCTDETAIL);
        intent.putExtra("key", string);
        localBroadcastManager.sendBroadcast(intent);
        c(context);
    }
}
