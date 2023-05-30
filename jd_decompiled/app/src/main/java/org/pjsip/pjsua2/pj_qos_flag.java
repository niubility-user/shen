package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pj_qos_flag {
    public static final pj_qos_flag PJ_QOS_PARAM_HAS_DSCP;
    public static final pj_qos_flag PJ_QOS_PARAM_HAS_SO_PRIO;
    public static final pj_qos_flag PJ_QOS_PARAM_HAS_WMM;
    private static int swigNext;
    private static pj_qos_flag[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pj_qos_flag pj_qos_flagVar = new pj_qos_flag("PJ_QOS_PARAM_HAS_DSCP", pjsua2JNI.PJ_QOS_PARAM_HAS_DSCP_get());
        PJ_QOS_PARAM_HAS_DSCP = pj_qos_flagVar;
        pj_qos_flag pj_qos_flagVar2 = new pj_qos_flag("PJ_QOS_PARAM_HAS_SO_PRIO", pjsua2JNI.PJ_QOS_PARAM_HAS_SO_PRIO_get());
        PJ_QOS_PARAM_HAS_SO_PRIO = pj_qos_flagVar2;
        pj_qos_flag pj_qos_flagVar3 = new pj_qos_flag("PJ_QOS_PARAM_HAS_WMM", pjsua2JNI.PJ_QOS_PARAM_HAS_WMM_get());
        PJ_QOS_PARAM_HAS_WMM = pj_qos_flagVar3;
        swigValues = new pj_qos_flag[]{pj_qos_flagVar, pj_qos_flagVar2, pj_qos_flagVar3};
        swigNext = 0;
    }

    private pj_qos_flag(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pj_qos_flag swigToEnum(int i2) {
        pj_qos_flag[] pj_qos_flagVarArr = swigValues;
        if (i2 < pj_qos_flagVarArr.length && i2 >= 0 && pj_qos_flagVarArr[i2].swigValue == i2) {
            return pj_qos_flagVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pj_qos_flag[] pj_qos_flagVarArr2 = swigValues;
            if (i3 < pj_qos_flagVarArr2.length) {
                if (pj_qos_flagVarArr2[i3].swigValue == i2) {
                    return pj_qos_flagVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pj_qos_flag.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pj_qos_flag(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pj_qos_flag(String str, pj_qos_flag pj_qos_flagVar) {
        this.swigName = str;
        int i2 = pj_qos_flagVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
