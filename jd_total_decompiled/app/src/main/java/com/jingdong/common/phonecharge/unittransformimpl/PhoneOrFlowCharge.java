package com.jingdong.common.phonecharge.unittransformimpl;

import android.content.Context;
import com.jingdong.common.deeplinkhelper.unittransform.IPhoneOrFlowCharge;
import com.jingdong.common.phonecharge.game.UnitTransformUtil;

/* loaded from: classes5.dex */
public class PhoneOrFlowCharge implements IPhoneOrFlowCharge {
    @Override // com.jingdong.common.deeplinkhelper.unittransform.IPhoneOrFlowCharge
    public void start(Context context, String str, int i2) {
        UnitTransformUtil.startPhoneOrFlowCharge(context, str, i2);
    }

    @Override // com.jingdong.common.deeplinkhelper.unittransform.IPhoneOrFlowCharge
    public void start(Context context, String str, String str2, int i2) {
        UnitTransformUtil.startPhoneOrFlowCharge(context, str, str2, i2);
    }
}
