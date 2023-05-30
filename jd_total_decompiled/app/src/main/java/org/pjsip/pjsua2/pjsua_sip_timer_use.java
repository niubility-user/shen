package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjsua_sip_timer_use {
    public static final pjsua_sip_timer_use PJSUA_SIP_TIMER_ALWAYS;
    public static final pjsua_sip_timer_use PJSUA_SIP_TIMER_INACTIVE;
    public static final pjsua_sip_timer_use PJSUA_SIP_TIMER_OPTIONAL;
    public static final pjsua_sip_timer_use PJSUA_SIP_TIMER_REQUIRED;
    private static int swigNext;
    private static pjsua_sip_timer_use[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjsua_sip_timer_use pjsua_sip_timer_useVar = new pjsua_sip_timer_use("PJSUA_SIP_TIMER_INACTIVE");
        PJSUA_SIP_TIMER_INACTIVE = pjsua_sip_timer_useVar;
        pjsua_sip_timer_use pjsua_sip_timer_useVar2 = new pjsua_sip_timer_use("PJSUA_SIP_TIMER_OPTIONAL");
        PJSUA_SIP_TIMER_OPTIONAL = pjsua_sip_timer_useVar2;
        pjsua_sip_timer_use pjsua_sip_timer_useVar3 = new pjsua_sip_timer_use("PJSUA_SIP_TIMER_REQUIRED");
        PJSUA_SIP_TIMER_REQUIRED = pjsua_sip_timer_useVar3;
        pjsua_sip_timer_use pjsua_sip_timer_useVar4 = new pjsua_sip_timer_use("PJSUA_SIP_TIMER_ALWAYS");
        PJSUA_SIP_TIMER_ALWAYS = pjsua_sip_timer_useVar4;
        swigValues = new pjsua_sip_timer_use[]{pjsua_sip_timer_useVar, pjsua_sip_timer_useVar2, pjsua_sip_timer_useVar3, pjsua_sip_timer_useVar4};
        swigNext = 0;
    }

    private pjsua_sip_timer_use(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjsua_sip_timer_use swigToEnum(int i2) {
        pjsua_sip_timer_use[] pjsua_sip_timer_useVarArr = swigValues;
        if (i2 < pjsua_sip_timer_useVarArr.length && i2 >= 0 && pjsua_sip_timer_useVarArr[i2].swigValue == i2) {
            return pjsua_sip_timer_useVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjsua_sip_timer_use[] pjsua_sip_timer_useVarArr2 = swigValues;
            if (i3 < pjsua_sip_timer_useVarArr2.length) {
                if (pjsua_sip_timer_useVarArr2[i3].swigValue == i2) {
                    return pjsua_sip_timer_useVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjsua_sip_timer_use.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_sip_timer_use(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjsua_sip_timer_use(String str, pjsua_sip_timer_use pjsua_sip_timer_useVar) {
        this.swigName = str;
        int i2 = pjsua_sip_timer_useVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
