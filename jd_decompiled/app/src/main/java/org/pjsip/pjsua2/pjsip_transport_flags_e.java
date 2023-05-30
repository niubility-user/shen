package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjsip_transport_flags_e {
    public static final pjsip_transport_flags_e PJSIP_TRANSPORT_DATAGRAM;
    public static final pjsip_transport_flags_e PJSIP_TRANSPORT_RELIABLE;
    public static final pjsip_transport_flags_e PJSIP_TRANSPORT_SECURE;
    private static int swigNext;
    private static pjsip_transport_flags_e[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjsip_transport_flags_e pjsip_transport_flags_eVar = new pjsip_transport_flags_e("PJSIP_TRANSPORT_RELIABLE", pjsua2JNI.PJSIP_TRANSPORT_RELIABLE_get());
        PJSIP_TRANSPORT_RELIABLE = pjsip_transport_flags_eVar;
        pjsip_transport_flags_e pjsip_transport_flags_eVar2 = new pjsip_transport_flags_e("PJSIP_TRANSPORT_SECURE", pjsua2JNI.PJSIP_TRANSPORT_SECURE_get());
        PJSIP_TRANSPORT_SECURE = pjsip_transport_flags_eVar2;
        pjsip_transport_flags_e pjsip_transport_flags_eVar3 = new pjsip_transport_flags_e("PJSIP_TRANSPORT_DATAGRAM", pjsua2JNI.PJSIP_TRANSPORT_DATAGRAM_get());
        PJSIP_TRANSPORT_DATAGRAM = pjsip_transport_flags_eVar3;
        swigValues = new pjsip_transport_flags_e[]{pjsip_transport_flags_eVar, pjsip_transport_flags_eVar2, pjsip_transport_flags_eVar3};
        swigNext = 0;
    }

    private pjsip_transport_flags_e(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjsip_transport_flags_e swigToEnum(int i2) {
        pjsip_transport_flags_e[] pjsip_transport_flags_eVarArr = swigValues;
        if (i2 < pjsip_transport_flags_eVarArr.length && i2 >= 0 && pjsip_transport_flags_eVarArr[i2].swigValue == i2) {
            return pjsip_transport_flags_eVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjsip_transport_flags_e[] pjsip_transport_flags_eVarArr2 = swigValues;
            if (i3 < pjsip_transport_flags_eVarArr2.length) {
                if (pjsip_transport_flags_eVarArr2[i3].swigValue == i2) {
                    return pjsip_transport_flags_eVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjsip_transport_flags_e.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsip_transport_flags_e(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjsip_transport_flags_e(String str, pjsip_transport_flags_e pjsip_transport_flags_eVar) {
        this.swigName = str;
        int i2 = pjsip_transport_flags_eVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
