package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pj_qos_type {
    public static final pj_qos_type PJ_QOS_TYPE_BACKGROUND;
    public static final pj_qos_type PJ_QOS_TYPE_BEST_EFFORT;
    public static final pj_qos_type PJ_QOS_TYPE_CONTROL;
    public static final pj_qos_type PJ_QOS_TYPE_SIGNALLING;
    public static final pj_qos_type PJ_QOS_TYPE_VIDEO;
    public static final pj_qos_type PJ_QOS_TYPE_VOICE;
    private static int swigNext;
    private static pj_qos_type[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pj_qos_type pj_qos_typeVar = new pj_qos_type("PJ_QOS_TYPE_BEST_EFFORT");
        PJ_QOS_TYPE_BEST_EFFORT = pj_qos_typeVar;
        pj_qos_type pj_qos_typeVar2 = new pj_qos_type("PJ_QOS_TYPE_BACKGROUND");
        PJ_QOS_TYPE_BACKGROUND = pj_qos_typeVar2;
        pj_qos_type pj_qos_typeVar3 = new pj_qos_type("PJ_QOS_TYPE_VIDEO");
        PJ_QOS_TYPE_VIDEO = pj_qos_typeVar3;
        pj_qos_type pj_qos_typeVar4 = new pj_qos_type("PJ_QOS_TYPE_VOICE");
        PJ_QOS_TYPE_VOICE = pj_qos_typeVar4;
        pj_qos_type pj_qos_typeVar5 = new pj_qos_type("PJ_QOS_TYPE_CONTROL");
        PJ_QOS_TYPE_CONTROL = pj_qos_typeVar5;
        pj_qos_type pj_qos_typeVar6 = new pj_qos_type("PJ_QOS_TYPE_SIGNALLING");
        PJ_QOS_TYPE_SIGNALLING = pj_qos_typeVar6;
        swigValues = new pj_qos_type[]{pj_qos_typeVar, pj_qos_typeVar2, pj_qos_typeVar3, pj_qos_typeVar4, pj_qos_typeVar5, pj_qos_typeVar6};
        swigNext = 0;
    }

    private pj_qos_type(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pj_qos_type swigToEnum(int i2) {
        pj_qos_type[] pj_qos_typeVarArr = swigValues;
        if (i2 < pj_qos_typeVarArr.length && i2 >= 0 && pj_qos_typeVarArr[i2].swigValue == i2) {
            return pj_qos_typeVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pj_qos_type[] pj_qos_typeVarArr2 = swigValues;
            if (i3 < pj_qos_typeVarArr2.length) {
                if (pj_qos_typeVarArr2[i3].swigValue == i2) {
                    return pj_qos_typeVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pj_qos_type.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pj_qos_type(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pj_qos_type(String str, pj_qos_type pj_qos_typeVar) {
        this.swigName = str;
        int i2 = pj_qos_typeVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
