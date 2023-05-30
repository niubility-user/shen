package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjsua_ip_change_op {
    public static final pjsua_ip_change_op PJSUA_IP_CHANGE_OP_ACC_HANGUP_CALLS;
    public static final pjsua_ip_change_op PJSUA_IP_CHANGE_OP_ACC_REINVITE_CALLS;
    public static final pjsua_ip_change_op PJSUA_IP_CHANGE_OP_ACC_SHUTDOWN_TP;
    public static final pjsua_ip_change_op PJSUA_IP_CHANGE_OP_ACC_UPDATE_CONTACT;
    public static final pjsua_ip_change_op PJSUA_IP_CHANGE_OP_NULL;
    public static final pjsua_ip_change_op PJSUA_IP_CHANGE_OP_RESTART_LIS;
    private static int swigNext;
    private static pjsua_ip_change_op[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjsua_ip_change_op pjsua_ip_change_opVar = new pjsua_ip_change_op("PJSUA_IP_CHANGE_OP_NULL");
        PJSUA_IP_CHANGE_OP_NULL = pjsua_ip_change_opVar;
        pjsua_ip_change_op pjsua_ip_change_opVar2 = new pjsua_ip_change_op("PJSUA_IP_CHANGE_OP_RESTART_LIS");
        PJSUA_IP_CHANGE_OP_RESTART_LIS = pjsua_ip_change_opVar2;
        pjsua_ip_change_op pjsua_ip_change_opVar3 = new pjsua_ip_change_op("PJSUA_IP_CHANGE_OP_ACC_SHUTDOWN_TP");
        PJSUA_IP_CHANGE_OP_ACC_SHUTDOWN_TP = pjsua_ip_change_opVar3;
        pjsua_ip_change_op pjsua_ip_change_opVar4 = new pjsua_ip_change_op("PJSUA_IP_CHANGE_OP_ACC_UPDATE_CONTACT");
        PJSUA_IP_CHANGE_OP_ACC_UPDATE_CONTACT = pjsua_ip_change_opVar4;
        pjsua_ip_change_op pjsua_ip_change_opVar5 = new pjsua_ip_change_op("PJSUA_IP_CHANGE_OP_ACC_HANGUP_CALLS");
        PJSUA_IP_CHANGE_OP_ACC_HANGUP_CALLS = pjsua_ip_change_opVar5;
        pjsua_ip_change_op pjsua_ip_change_opVar6 = new pjsua_ip_change_op("PJSUA_IP_CHANGE_OP_ACC_REINVITE_CALLS");
        PJSUA_IP_CHANGE_OP_ACC_REINVITE_CALLS = pjsua_ip_change_opVar6;
        swigValues = new pjsua_ip_change_op[]{pjsua_ip_change_opVar, pjsua_ip_change_opVar2, pjsua_ip_change_opVar3, pjsua_ip_change_opVar4, pjsua_ip_change_opVar5, pjsua_ip_change_opVar6};
        swigNext = 0;
    }

    private pjsua_ip_change_op(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjsua_ip_change_op swigToEnum(int i2) {
        pjsua_ip_change_op[] pjsua_ip_change_opVarArr = swigValues;
        if (i2 < pjsua_ip_change_opVarArr.length && i2 >= 0 && pjsua_ip_change_opVarArr[i2].swigValue == i2) {
            return pjsua_ip_change_opVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjsua_ip_change_op[] pjsua_ip_change_opVarArr2 = swigValues;
            if (i3 < pjsua_ip_change_opVarArr2.length) {
                if (pjsua_ip_change_opVarArr2[i3].swigValue == i2) {
                    return pjsua_ip_change_opVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjsua_ip_change_op.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_ip_change_op(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjsua_ip_change_op(String str, pjsua_ip_change_op pjsua_ip_change_opVar) {
        this.swigName = str;
        int i2 = pjsua_ip_change_opVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
