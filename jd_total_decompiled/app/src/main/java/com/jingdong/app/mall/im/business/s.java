package com.jingdong.app.mall.im.business;

import android.app.Activity;
import android.content.Context;
import com.jingdong.app.mall.R;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.messagepop.JDMessagePopManager;
import com.jingdong.common.utils.BackForegroundWatcher;
import com.jingdong.common.utils.MSGWithDDUtil;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.service.impl.IMNotify;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class s extends IMNotify {
    private static final String a = "s";

    @Override // com.jingdong.service.impl.IMNotify, com.jingdong.service.service.NotifyService
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public ArrayList<String> getPopWindowList() {
        OKLog.d("bundleicssdkservice", a + "---getPopWindowList");
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("com.jd.lib.search.view.Activity.ConvergeListActivity");
        arrayList.add("com.jd.lib.cashier.sdk.pay.view.CashierPayActivity");
        arrayList.add("com.jd.lib.cashier.sdk.freindpay.view.FriendPayActivity");
        arrayList.add("com.jd.lib.cashier.sdk.freindpaydialog.view.FriendPayDialogActivity");
        arrayList.add("com.jd.lib.cashier.sdk.quickpay.view.CashierQuickPayActivity");
        arrayList.add("com.jd.lib.cashier.sdk.creditpay.view.CashierCreditPayActivity");
        arrayList.add("com.jd.lib.cashier.sdk.complete.view.CashierCompleteActivity");
        arrayList.add("com.jd.lib.cashier.complete.view.CashierCompleteActivity");
        arrayList.add("com.jd.lib.search.view.Activity.SearchActivity");
        arrayList.add("com.jd.lib.settlement.fillorder.activity.NewFillOrderActivity");
        arrayList.add("com.jd.lib.search.view.Activity.ConvergeListActivity");
        arrayList.add("com.jd.lib.meme.search.SearchMainActivity");
        arrayList.add("com.jd.lib.ordercenter.myGoodsOrderList.view.activity.SearchOrderListActivity");
        arrayList.add("com.jd.lib.jshop.jshop.JshopGuessWordActivity");
        arrayList.add("com.jingdong.app.mall.messagecenter.view.activity.MessageCenterMainActivity");
        return arrayList;
    }

    @Override // com.jingdong.service.impl.IMNotify, com.jingdong.service.service.NotifyService
    public boolean checkMessagePopShow() {
        boolean checkMessagePopShow = JDMessagePopManager.getInstance().checkMessagePopShow();
        OKLog.d("bundleicssdkservice", a + "---checkMessagePopShow :" + checkMessagePopShow);
        return checkMessagePopShow;
    }

    @Override // com.jingdong.service.impl.IMNotify, com.jingdong.service.service.NotifyService
    public void dismissNotifyActivity(Activity activity) {
        OKLog.d("bundleicssdkservice", a + "---dismissNotifyActivity");
    }

    @Override // com.jingdong.service.impl.IMNotify, com.jingdong.service.service.NotifyService
    public int getAnimationStyle() {
        OKLog.d("bundleicssdkservice", a + "---getAnimationStyle");
        return R.style.mj;
    }

    @Override // com.jingdong.service.impl.IMNotify, com.jingdong.service.service.NotifyService
    public String getAppPackageName() {
        OKLog.d("bundleicssdkservice", a + "---getAppPackageName");
        return jd.wjlogin_sdk.util.f.f19954c;
    }

    @Override // com.jingdong.service.impl.IMNotify, com.jingdong.service.service.NotifyService
    public Activity getCurrentActivity() {
        OKLog.d("bundleicssdkservice", a + "---getCurrentActivity");
        if (BaseFrameUtil.getInstance().getCurrentMyActivity() != null) {
            return BaseFrameUtil.getInstance().getCurrentMyActivity().getThisActivity();
        }
        return null;
    }

    @Override // com.jingdong.service.impl.IMNotify, com.jingdong.service.service.NotifyService
    public boolean getDDStationWindowKey() {
        boolean dDStationWindowKey = MSGWithDDUtil.getDDStationWindowKey();
        OKLog.d("bundleicssdkservice", a + "---getDDStationWindowKey :" + dDStationWindowKey);
        return dDStationWindowKey;
    }

    @Override // com.jingdong.service.impl.IMNotify, com.jingdong.service.service.NotifyService
    public boolean getMSGSOUND() {
        boolean msgsound = MSGWithDDUtil.getMSGSOUND();
        OKLog.d("bundleicssdkservice", a + "---getMSGSOUND:" + msgsound);
        return msgsound;
    }

    @Override // com.jingdong.service.impl.IMNotify, com.jingdong.service.service.NotifyService
    public boolean getMsgShakeSwitch() {
        boolean msgShakeSwitch = MSGWithDDUtil.getMsgShakeSwitch();
        OKLog.d("bundleicssdkservice", a + "---getMsgShakeSwitch:" + msgShakeSwitch);
        return msgShakeSwitch;
    }

    @Override // com.jingdong.service.impl.IMNotify, com.jingdong.service.service.NotifyService
    public String getNotifyClassName() {
        OKLog.d("bundleicssdkservice", a + "---getNotifyClassName");
        return "mix.ActivityShadow";
    }

    @Override // com.jingdong.service.impl.IMNotify, com.jingdong.service.service.NotifyService
    public boolean isAppForeground(Context context) {
        boolean isAppForeground = BackForegroundWatcher.getInstance().isAppForeground();
        OKLog.d("bundleicssdkservice", a + "---isAppForeground : " + isAppForeground);
        return isAppForeground;
    }

    @Override // com.jingdong.service.impl.IMNotify, com.jingdong.service.service.NotifyService
    public int notifyLargeIcon() {
        OKLog.d("bundleicssdkservice", a + "---notifyLargeIcon");
        return R.drawable.xf;
    }

    @Override // com.jingdong.service.impl.IMNotify, com.jingdong.service.service.NotifyService
    public int notifySmallIcon() {
        OKLog.d("bundleicssdkservice", a + "---notifySmallIcon");
        return R.drawable.xh;
    }

    @Override // com.jingdong.service.impl.IMNotify, com.jingdong.service.service.NotifyService
    public int soundResId() {
        OKLog.d("bundleicssdkservice", a + "---soundResId");
        return R.raw.f7803f;
    }
}
