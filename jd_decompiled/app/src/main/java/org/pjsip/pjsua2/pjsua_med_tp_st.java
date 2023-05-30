package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjsua_med_tp_st {
    public static final pjsua_med_tp_st PJSUA_MED_TP_CREATING;
    public static final pjsua_med_tp_st PJSUA_MED_TP_DISABLED;
    public static final pjsua_med_tp_st PJSUA_MED_TP_IDLE;
    public static final pjsua_med_tp_st PJSUA_MED_TP_INIT;
    public static final pjsua_med_tp_st PJSUA_MED_TP_NULL;
    public static final pjsua_med_tp_st PJSUA_MED_TP_RUNNING;
    private static int swigNext;
    private static pjsua_med_tp_st[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjsua_med_tp_st pjsua_med_tp_stVar = new pjsua_med_tp_st("PJSUA_MED_TP_NULL");
        PJSUA_MED_TP_NULL = pjsua_med_tp_stVar;
        pjsua_med_tp_st pjsua_med_tp_stVar2 = new pjsua_med_tp_st("PJSUA_MED_TP_CREATING");
        PJSUA_MED_TP_CREATING = pjsua_med_tp_stVar2;
        pjsua_med_tp_st pjsua_med_tp_stVar3 = new pjsua_med_tp_st("PJSUA_MED_TP_IDLE");
        PJSUA_MED_TP_IDLE = pjsua_med_tp_stVar3;
        pjsua_med_tp_st pjsua_med_tp_stVar4 = new pjsua_med_tp_st("PJSUA_MED_TP_INIT");
        PJSUA_MED_TP_INIT = pjsua_med_tp_stVar4;
        pjsua_med_tp_st pjsua_med_tp_stVar5 = new pjsua_med_tp_st("PJSUA_MED_TP_RUNNING");
        PJSUA_MED_TP_RUNNING = pjsua_med_tp_stVar5;
        pjsua_med_tp_st pjsua_med_tp_stVar6 = new pjsua_med_tp_st("PJSUA_MED_TP_DISABLED");
        PJSUA_MED_TP_DISABLED = pjsua_med_tp_stVar6;
        swigValues = new pjsua_med_tp_st[]{pjsua_med_tp_stVar, pjsua_med_tp_stVar2, pjsua_med_tp_stVar3, pjsua_med_tp_stVar4, pjsua_med_tp_stVar5, pjsua_med_tp_stVar6};
        swigNext = 0;
    }

    private pjsua_med_tp_st(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjsua_med_tp_st swigToEnum(int i2) {
        pjsua_med_tp_st[] pjsua_med_tp_stVarArr = swigValues;
        if (i2 < pjsua_med_tp_stVarArr.length && i2 >= 0 && pjsua_med_tp_stVarArr[i2].swigValue == i2) {
            return pjsua_med_tp_stVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjsua_med_tp_st[] pjsua_med_tp_stVarArr2 = swigValues;
            if (i3 < pjsua_med_tp_stVarArr2.length) {
                if (pjsua_med_tp_stVarArr2[i3].swigValue == i2) {
                    return pjsua_med_tp_stVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjsua_med_tp_st.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_med_tp_st(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjsua_med_tp_st(String str, pjsua_med_tp_st pjsua_med_tp_stVar) {
        this.swigName = str;
        int i2 = pjsua_med_tp_stVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
