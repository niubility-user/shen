package com.jingdong.common.deeplinkhelper.unittransform;

/* loaded from: classes5.dex */
public class UnitTransformHelper {
    private static UnitTransformHelper unitTransformHelper;
    private IPhoneOrFlowCharge phoneOrFlowCharge;

    private UnitTransformHelper() {
    }

    public static synchronized UnitTransformHelper getInstance() {
        UnitTransformHelper unitTransformHelper2;
        synchronized (UnitTransformHelper.class) {
            if (unitTransformHelper == null) {
                unitTransformHelper = new UnitTransformHelper();
            }
            unitTransformHelper2 = unitTransformHelper;
        }
        return unitTransformHelper2;
    }

    public IPhoneOrFlowCharge getPhoneOrFlowCharge() {
        return this.phoneOrFlowCharge;
    }

    public void setPhoneOrFlowCharge(IPhoneOrFlowCharge iPhoneOrFlowCharge) {
        this.phoneOrFlowCharge = iPhoneOrFlowCharge;
    }
}
