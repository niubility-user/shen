package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pj_qos_wmm_prio {
    public static final pj_qos_wmm_prio PJ_QOS_WMM_PRIO_BULK;
    public static final pj_qos_wmm_prio PJ_QOS_WMM_PRIO_BULK_EFFORT;
    public static final pj_qos_wmm_prio PJ_QOS_WMM_PRIO_VIDEO;
    public static final pj_qos_wmm_prio PJ_QOS_WMM_PRIO_VOICE;
    private static int swigNext;
    private static pj_qos_wmm_prio[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pj_qos_wmm_prio pj_qos_wmm_prioVar = new pj_qos_wmm_prio("PJ_QOS_WMM_PRIO_BULK_EFFORT");
        PJ_QOS_WMM_PRIO_BULK_EFFORT = pj_qos_wmm_prioVar;
        pj_qos_wmm_prio pj_qos_wmm_prioVar2 = new pj_qos_wmm_prio("PJ_QOS_WMM_PRIO_BULK");
        PJ_QOS_WMM_PRIO_BULK = pj_qos_wmm_prioVar2;
        pj_qos_wmm_prio pj_qos_wmm_prioVar3 = new pj_qos_wmm_prio("PJ_QOS_WMM_PRIO_VIDEO");
        PJ_QOS_WMM_PRIO_VIDEO = pj_qos_wmm_prioVar3;
        pj_qos_wmm_prio pj_qos_wmm_prioVar4 = new pj_qos_wmm_prio("PJ_QOS_WMM_PRIO_VOICE");
        PJ_QOS_WMM_PRIO_VOICE = pj_qos_wmm_prioVar4;
        swigValues = new pj_qos_wmm_prio[]{pj_qos_wmm_prioVar, pj_qos_wmm_prioVar2, pj_qos_wmm_prioVar3, pj_qos_wmm_prioVar4};
        swigNext = 0;
    }

    private pj_qos_wmm_prio(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pj_qos_wmm_prio swigToEnum(int i2) {
        pj_qos_wmm_prio[] pj_qos_wmm_prioVarArr = swigValues;
        if (i2 < pj_qos_wmm_prioVarArr.length && i2 >= 0 && pj_qos_wmm_prioVarArr[i2].swigValue == i2) {
            return pj_qos_wmm_prioVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pj_qos_wmm_prio[] pj_qos_wmm_prioVarArr2 = swigValues;
            if (i3 < pj_qos_wmm_prioVarArr2.length) {
                if (pj_qos_wmm_prioVarArr2[i3].swigValue == i2) {
                    return pj_qos_wmm_prioVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pj_qos_wmm_prio.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pj_qos_wmm_prio(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pj_qos_wmm_prio(String str, pj_qos_wmm_prio pj_qos_wmm_prioVar) {
        this.swigName = str;
        int i2 = pj_qos_wmm_prioVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
