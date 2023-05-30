package com.jd.manto.d;

import android.content.Context;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.jump.OpenAppConstant;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.messagecenter.view.MessageRedObserver;
import com.jingdong.common.messagecenter.view.NewMessagRedManager;
import com.jingdong.common.messagecenter.view.NewMessageRedInfo;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.JumpMessageActivityUtil;
import com.jingdong.manto.sdk.api.IHostMenuInterface;
import java.util.Map;

/* loaded from: classes17.dex */
public class n implements IHostMenuInterface {

    /* renamed from: g  reason: collision with root package name */
    private IHostMenuInterface.RedMsgCallBack f6661g;

    /* renamed from: h  reason: collision with root package name */
    MessageRedObserver f6662h = new a();

    /* loaded from: classes17.dex */
    class a implements MessageRedObserver {
        a() {
        }

        @Override // com.jingdong.common.messagecenter.view.MessageRedObserver
        public void onMessageRedReceived(Map<String, NewMessageRedInfo> map) {
            NewMessageRedInfo newMessageRedInfo;
            if (map == null || !map.containsKey("messageRedInfo") || (newMessageRedInfo = map.get("messageRedInfo")) == null || newMessageRedInfo.num < 0 || n.this.f6661g == null) {
                return;
            }
            n.this.f6661g.onMsgRead(newMessageRedInfo.redPoint ? 1 : 0, newMessageRedInfo.num);
            NewMessagRedManager.getInstance(null);
            NewMessagRedManager.deregisterPersonalMessageObserver(n.this.f6662h);
            n.this.f6661g = null;
        }
    }

    @Override // com.jingdong.manto.sdk.api.IHostMenuInterface
    public void getRedMsg(Context context, IHostMenuInterface.RedMsgCallBack redMsgCallBack) {
        NewMessagRedManager.getInstance(null);
        NewMessagRedManager.deregisterPersonalMessageObserver(this.f6662h);
        this.f6661g = redMsgCallBack;
        NewMessagRedManager.getInstance(LoginUserBase.getUserPin());
        NewMessagRedManager.registPersonalMessageObserver(this.f6662h);
        NewMessagRedManager.getInstance(LoginUserBase.getUserPin());
        NewMessagRedManager.requestMessage(HttpGroupUtils.getHttpGroupaAsynPool());
    }

    @Override // com.jingdong.manto.sdk.api.IHostMenuInterface
    public void jumpToHome(Context context) {
        DeepLinkCommonHelper.startActivity(context, DeepLinkCommonHelper.HOST_JD_TASK_CLEAR_ACTIVITY, null, true, 67108864, false, "");
    }

    @Override // com.jingdong.manto.sdk.api.IHostMenuInterface
    public void jumpToMsgCenter(Context context) {
        JumpMessageActivityUtil.jumpToMessageCenter(context);
    }

    @Override // com.jingdong.manto.sdk.api.IHostMenuInterface
    public void jumpToShop(Context context, String str, String str2) {
        OpenAppJumpBuilder.Builder builder = new OpenAppJumpBuilder.Builder();
        builder.host(OpenAppConstant.HOST_1);
        builder.scheme(OpenAppConstant.SCHEME_OPENAPP_1);
        builder.des(JumpUtil.VALUE_DES_JSHOP_DETAIL);
        builder.category("jump");
        builder.map("fromManto", Boolean.TRUE);
        builder.map("shopId", str2);
        builder.map("venderId", str);
        builder.build().jump(context);
    }
}
