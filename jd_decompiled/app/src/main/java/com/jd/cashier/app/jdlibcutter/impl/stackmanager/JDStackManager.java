package com.jd.cashier.app.jdlibcutter.impl.stackmanager;

import android.app.Activity;
import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.stackmanager.IStackManager;
import com.jingdong.common.ActivityNumController;
import com.jingdong.common.BaseFrameUtil;

/* loaded from: classes13.dex */
public class JDStackManager implements IStackManager {
    private static final String MINI_ORDER_DETAIL = "com.jd.lib.ordercenter.orderinfocard.OrderInfoCardActivity";

    @Override // com.jd.cashier.app.jdlibcutter.protocol.stackmanager.IStackManager
    public void removeActivity(int i2, int i3) {
        try {
            ActivityNumController.removeActivity(i2, i3);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.stackmanager.IStackOrderManager
    public void removeMiniOrderDetail() {
        try {
            Activity thisActivity = BaseFrameUtil.getInstance().getCurrentMyActivity().getThisActivity();
            if (thisActivity != null && TextUtils.equals(thisActivity.getComponentName().getClassName(), MINI_ORDER_DETAIL)) {
                thisActivity.finish();
            }
            ActivityNumController.removeActivity(MINI_ORDER_DETAIL);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.stackmanager.IStackManager
    public void removeActivity(String str) {
        try {
            ActivityNumController.removeActivity(str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
