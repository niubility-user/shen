package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pj_stun_nat_type {
    public static final pj_stun_nat_type PJ_STUN_NAT_TYPE_BLOCKED;
    public static final pj_stun_nat_type PJ_STUN_NAT_TYPE_ERR_UNKNOWN;
    public static final pj_stun_nat_type PJ_STUN_NAT_TYPE_FULL_CONE;
    public static final pj_stun_nat_type PJ_STUN_NAT_TYPE_OPEN;
    public static final pj_stun_nat_type PJ_STUN_NAT_TYPE_PORT_RESTRICTED;
    public static final pj_stun_nat_type PJ_STUN_NAT_TYPE_RESTRICTED;
    public static final pj_stun_nat_type PJ_STUN_NAT_TYPE_SYMMETRIC;
    public static final pj_stun_nat_type PJ_STUN_NAT_TYPE_SYMMETRIC_UDP;
    public static final pj_stun_nat_type PJ_STUN_NAT_TYPE_UNKNOWN;
    private static int swigNext;
    private static pj_stun_nat_type[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pj_stun_nat_type pj_stun_nat_typeVar = new pj_stun_nat_type("PJ_STUN_NAT_TYPE_UNKNOWN");
        PJ_STUN_NAT_TYPE_UNKNOWN = pj_stun_nat_typeVar;
        pj_stun_nat_type pj_stun_nat_typeVar2 = new pj_stun_nat_type("PJ_STUN_NAT_TYPE_ERR_UNKNOWN");
        PJ_STUN_NAT_TYPE_ERR_UNKNOWN = pj_stun_nat_typeVar2;
        pj_stun_nat_type pj_stun_nat_typeVar3 = new pj_stun_nat_type("PJ_STUN_NAT_TYPE_OPEN");
        PJ_STUN_NAT_TYPE_OPEN = pj_stun_nat_typeVar3;
        pj_stun_nat_type pj_stun_nat_typeVar4 = new pj_stun_nat_type("PJ_STUN_NAT_TYPE_BLOCKED");
        PJ_STUN_NAT_TYPE_BLOCKED = pj_stun_nat_typeVar4;
        pj_stun_nat_type pj_stun_nat_typeVar5 = new pj_stun_nat_type("PJ_STUN_NAT_TYPE_SYMMETRIC_UDP");
        PJ_STUN_NAT_TYPE_SYMMETRIC_UDP = pj_stun_nat_typeVar5;
        pj_stun_nat_type pj_stun_nat_typeVar6 = new pj_stun_nat_type("PJ_STUN_NAT_TYPE_FULL_CONE");
        PJ_STUN_NAT_TYPE_FULL_CONE = pj_stun_nat_typeVar6;
        pj_stun_nat_type pj_stun_nat_typeVar7 = new pj_stun_nat_type("PJ_STUN_NAT_TYPE_SYMMETRIC");
        PJ_STUN_NAT_TYPE_SYMMETRIC = pj_stun_nat_typeVar7;
        pj_stun_nat_type pj_stun_nat_typeVar8 = new pj_stun_nat_type("PJ_STUN_NAT_TYPE_RESTRICTED");
        PJ_STUN_NAT_TYPE_RESTRICTED = pj_stun_nat_typeVar8;
        pj_stun_nat_type pj_stun_nat_typeVar9 = new pj_stun_nat_type("PJ_STUN_NAT_TYPE_PORT_RESTRICTED");
        PJ_STUN_NAT_TYPE_PORT_RESTRICTED = pj_stun_nat_typeVar9;
        swigValues = new pj_stun_nat_type[]{pj_stun_nat_typeVar, pj_stun_nat_typeVar2, pj_stun_nat_typeVar3, pj_stun_nat_typeVar4, pj_stun_nat_typeVar5, pj_stun_nat_typeVar6, pj_stun_nat_typeVar7, pj_stun_nat_typeVar8, pj_stun_nat_typeVar9};
        swigNext = 0;
    }

    private pj_stun_nat_type(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pj_stun_nat_type swigToEnum(int i2) {
        pj_stun_nat_type[] pj_stun_nat_typeVarArr = swigValues;
        if (i2 < pj_stun_nat_typeVarArr.length && i2 >= 0 && pj_stun_nat_typeVarArr[i2].swigValue == i2) {
            return pj_stun_nat_typeVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pj_stun_nat_type[] pj_stun_nat_typeVarArr2 = swigValues;
            if (i3 < pj_stun_nat_typeVarArr2.length) {
                if (pj_stun_nat_typeVarArr2[i3].swigValue == i2) {
                    return pj_stun_nat_typeVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pj_stun_nat_type.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pj_stun_nat_type(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pj_stun_nat_type(String str, pj_stun_nat_type pj_stun_nat_typeVar) {
        this.swigName = str;
        int i2 = pj_stun_nat_typeVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
