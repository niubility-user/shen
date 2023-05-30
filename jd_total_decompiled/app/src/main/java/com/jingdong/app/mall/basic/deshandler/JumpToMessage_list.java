package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.app.mall.utils.n;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.corelib.utils.Log;

@Des(des = JumpUtil.VALUE_DES_MESSAGE_BOX)
/* loaded from: classes19.dex */
public class JumpToMessage_list extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (Log.D) {
            Log.d(this.a, "MainFrameActivity toTargetActivity() -->> MODULE_ID_MESSAGE_LIST");
        }
        n.a(context);
        c(context);
    }
}
