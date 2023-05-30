package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjsip_event_id_e {
    public static final pjsip_event_id_e PJSIP_EVENT_RX_MSG;
    public static final pjsip_event_id_e PJSIP_EVENT_TIMER;
    public static final pjsip_event_id_e PJSIP_EVENT_TRANSPORT_ERROR;
    public static final pjsip_event_id_e PJSIP_EVENT_TSX_STATE;
    public static final pjsip_event_id_e PJSIP_EVENT_TX_MSG;
    public static final pjsip_event_id_e PJSIP_EVENT_UNKNOWN;
    public static final pjsip_event_id_e PJSIP_EVENT_USER;
    private static int swigNext;
    private static pjsip_event_id_e[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjsip_event_id_e pjsip_event_id_eVar = new pjsip_event_id_e("PJSIP_EVENT_UNKNOWN");
        PJSIP_EVENT_UNKNOWN = pjsip_event_id_eVar;
        pjsip_event_id_e pjsip_event_id_eVar2 = new pjsip_event_id_e("PJSIP_EVENT_TIMER");
        PJSIP_EVENT_TIMER = pjsip_event_id_eVar2;
        pjsip_event_id_e pjsip_event_id_eVar3 = new pjsip_event_id_e("PJSIP_EVENT_TX_MSG");
        PJSIP_EVENT_TX_MSG = pjsip_event_id_eVar3;
        pjsip_event_id_e pjsip_event_id_eVar4 = new pjsip_event_id_e("PJSIP_EVENT_RX_MSG");
        PJSIP_EVENT_RX_MSG = pjsip_event_id_eVar4;
        pjsip_event_id_e pjsip_event_id_eVar5 = new pjsip_event_id_e("PJSIP_EVENT_TRANSPORT_ERROR");
        PJSIP_EVENT_TRANSPORT_ERROR = pjsip_event_id_eVar5;
        pjsip_event_id_e pjsip_event_id_eVar6 = new pjsip_event_id_e("PJSIP_EVENT_TSX_STATE");
        PJSIP_EVENT_TSX_STATE = pjsip_event_id_eVar6;
        pjsip_event_id_e pjsip_event_id_eVar7 = new pjsip_event_id_e("PJSIP_EVENT_USER");
        PJSIP_EVENT_USER = pjsip_event_id_eVar7;
        swigValues = new pjsip_event_id_e[]{pjsip_event_id_eVar, pjsip_event_id_eVar2, pjsip_event_id_eVar3, pjsip_event_id_eVar4, pjsip_event_id_eVar5, pjsip_event_id_eVar6, pjsip_event_id_eVar7};
        swigNext = 0;
    }

    private pjsip_event_id_e(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjsip_event_id_e swigToEnum(int i2) {
        pjsip_event_id_e[] pjsip_event_id_eVarArr = swigValues;
        if (i2 < pjsip_event_id_eVarArr.length && i2 >= 0 && pjsip_event_id_eVarArr[i2].swigValue == i2) {
            return pjsip_event_id_eVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjsip_event_id_e[] pjsip_event_id_eVarArr2 = swigValues;
            if (i3 < pjsip_event_id_eVarArr2.length) {
                if (pjsip_event_id_eVarArr2[i3].swigValue == i2) {
                    return pjsip_event_id_eVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjsip_event_id_e.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsip_event_id_e(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjsip_event_id_e(String str, pjsip_event_id_e pjsip_event_id_eVar) {
        this.swigName = str;
        int i2 = pjsip_event_id_eVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
