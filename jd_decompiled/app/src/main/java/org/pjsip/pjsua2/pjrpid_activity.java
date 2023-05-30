package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjrpid_activity {
    public static final pjrpid_activity PJRPID_ACTIVITY_AWAY;
    public static final pjrpid_activity PJRPID_ACTIVITY_BUSY;
    public static final pjrpid_activity PJRPID_ACTIVITY_UNKNOWN;
    private static int swigNext;
    private static pjrpid_activity[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjrpid_activity pjrpid_activityVar = new pjrpid_activity("PJRPID_ACTIVITY_UNKNOWN");
        PJRPID_ACTIVITY_UNKNOWN = pjrpid_activityVar;
        pjrpid_activity pjrpid_activityVar2 = new pjrpid_activity("PJRPID_ACTIVITY_AWAY");
        PJRPID_ACTIVITY_AWAY = pjrpid_activityVar2;
        pjrpid_activity pjrpid_activityVar3 = new pjrpid_activity("PJRPID_ACTIVITY_BUSY");
        PJRPID_ACTIVITY_BUSY = pjrpid_activityVar3;
        swigValues = new pjrpid_activity[]{pjrpid_activityVar, pjrpid_activityVar2, pjrpid_activityVar3};
        swigNext = 0;
    }

    private pjrpid_activity(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjrpid_activity swigToEnum(int i2) {
        pjrpid_activity[] pjrpid_activityVarArr = swigValues;
        if (i2 < pjrpid_activityVarArr.length && i2 >= 0 && pjrpid_activityVarArr[i2].swigValue == i2) {
            return pjrpid_activityVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjrpid_activity[] pjrpid_activityVarArr2 = swigValues;
            if (i3 < pjrpid_activityVarArr2.length) {
                if (pjrpid_activityVarArr2[i3].swigValue == i2) {
                    return pjrpid_activityVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjrpid_activity.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjrpid_activity(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjrpid_activity(String str, pjrpid_activity pjrpid_activityVar) {
        this.swigName = str;
        int i2 = pjrpid_activityVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
