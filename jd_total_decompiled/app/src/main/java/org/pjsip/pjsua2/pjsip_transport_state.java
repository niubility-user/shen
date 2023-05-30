package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjsip_transport_state {
    public static final pjsip_transport_state PJSIP_TP_STATE_CONNECTED;
    public static final pjsip_transport_state PJSIP_TP_STATE_DESTROY;
    public static final pjsip_transport_state PJSIP_TP_STATE_DISCONNECTED;
    public static final pjsip_transport_state PJSIP_TP_STATE_SHUTDOWN;
    private static int swigNext;
    private static pjsip_transport_state[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjsip_transport_state pjsip_transport_stateVar = new pjsip_transport_state("PJSIP_TP_STATE_CONNECTED");
        PJSIP_TP_STATE_CONNECTED = pjsip_transport_stateVar;
        pjsip_transport_state pjsip_transport_stateVar2 = new pjsip_transport_state("PJSIP_TP_STATE_DISCONNECTED");
        PJSIP_TP_STATE_DISCONNECTED = pjsip_transport_stateVar2;
        pjsip_transport_state pjsip_transport_stateVar3 = new pjsip_transport_state("PJSIP_TP_STATE_SHUTDOWN");
        PJSIP_TP_STATE_SHUTDOWN = pjsip_transport_stateVar3;
        pjsip_transport_state pjsip_transport_stateVar4 = new pjsip_transport_state("PJSIP_TP_STATE_DESTROY");
        PJSIP_TP_STATE_DESTROY = pjsip_transport_stateVar4;
        swigValues = new pjsip_transport_state[]{pjsip_transport_stateVar, pjsip_transport_stateVar2, pjsip_transport_stateVar3, pjsip_transport_stateVar4};
        swigNext = 0;
    }

    private pjsip_transport_state(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjsip_transport_state swigToEnum(int i2) {
        pjsip_transport_state[] pjsip_transport_stateVarArr = swigValues;
        if (i2 < pjsip_transport_stateVarArr.length && i2 >= 0 && pjsip_transport_stateVarArr[i2].swigValue == i2) {
            return pjsip_transport_stateVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjsip_transport_state[] pjsip_transport_stateVarArr2 = swigValues;
            if (i3 < pjsip_transport_stateVarArr2.length) {
                if (pjsip_transport_stateVarArr2[i3].swigValue == i2) {
                    return pjsip_transport_stateVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjsip_transport_state.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsip_transport_state(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjsip_transport_state(String str, pjsip_transport_state pjsip_transport_stateVar) {
        this.swigName = str;
        int i2 = pjsip_transport_stateVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
