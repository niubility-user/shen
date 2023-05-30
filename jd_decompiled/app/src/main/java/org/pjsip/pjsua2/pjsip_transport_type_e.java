package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjsip_transport_type_e {
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_IPV6;
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_LOOP;
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_LOOP_DGRAM;
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_SCTP;
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_START_OTHER;
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_TCP;
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_TCP6;
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_TLS;
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_TLS6;
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_UDP;
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_UDP6;
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_UNSPECIFIED;
    private static int swigNext;
    private static pjsip_transport_type_e[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjsip_transport_type_e pjsip_transport_type_eVar = new pjsip_transport_type_e("PJSIP_TRANSPORT_UNSPECIFIED");
        PJSIP_TRANSPORT_UNSPECIFIED = pjsip_transport_type_eVar;
        pjsip_transport_type_e pjsip_transport_type_eVar2 = new pjsip_transport_type_e("PJSIP_TRANSPORT_UDP");
        PJSIP_TRANSPORT_UDP = pjsip_transport_type_eVar2;
        pjsip_transport_type_e pjsip_transport_type_eVar3 = new pjsip_transport_type_e("PJSIP_TRANSPORT_TCP");
        PJSIP_TRANSPORT_TCP = pjsip_transport_type_eVar3;
        pjsip_transport_type_e pjsip_transport_type_eVar4 = new pjsip_transport_type_e("PJSIP_TRANSPORT_TLS");
        PJSIP_TRANSPORT_TLS = pjsip_transport_type_eVar4;
        pjsip_transport_type_e pjsip_transport_type_eVar5 = new pjsip_transport_type_e("PJSIP_TRANSPORT_SCTP");
        PJSIP_TRANSPORT_SCTP = pjsip_transport_type_eVar5;
        pjsip_transport_type_e pjsip_transport_type_eVar6 = new pjsip_transport_type_e("PJSIP_TRANSPORT_LOOP");
        PJSIP_TRANSPORT_LOOP = pjsip_transport_type_eVar6;
        pjsip_transport_type_e pjsip_transport_type_eVar7 = new pjsip_transport_type_e("PJSIP_TRANSPORT_LOOP_DGRAM");
        PJSIP_TRANSPORT_LOOP_DGRAM = pjsip_transport_type_eVar7;
        pjsip_transport_type_e pjsip_transport_type_eVar8 = new pjsip_transport_type_e("PJSIP_TRANSPORT_START_OTHER");
        PJSIP_TRANSPORT_START_OTHER = pjsip_transport_type_eVar8;
        pjsip_transport_type_e pjsip_transport_type_eVar9 = new pjsip_transport_type_e("PJSIP_TRANSPORT_IPV6", pjsua2JNI.PJSIP_TRANSPORT_IPV6_get());
        PJSIP_TRANSPORT_IPV6 = pjsip_transport_type_eVar9;
        pjsip_transport_type_e pjsip_transport_type_eVar10 = new pjsip_transport_type_e("PJSIP_TRANSPORT_UDP6", pjsua2JNI.PJSIP_TRANSPORT_UDP6_get());
        PJSIP_TRANSPORT_UDP6 = pjsip_transport_type_eVar10;
        pjsip_transport_type_e pjsip_transport_type_eVar11 = new pjsip_transport_type_e("PJSIP_TRANSPORT_TCP6", pjsua2JNI.PJSIP_TRANSPORT_TCP6_get());
        PJSIP_TRANSPORT_TCP6 = pjsip_transport_type_eVar11;
        pjsip_transport_type_e pjsip_transport_type_eVar12 = new pjsip_transport_type_e("PJSIP_TRANSPORT_TLS6", pjsua2JNI.PJSIP_TRANSPORT_TLS6_get());
        PJSIP_TRANSPORT_TLS6 = pjsip_transport_type_eVar12;
        swigValues = new pjsip_transport_type_e[]{pjsip_transport_type_eVar, pjsip_transport_type_eVar2, pjsip_transport_type_eVar3, pjsip_transport_type_eVar4, pjsip_transport_type_eVar5, pjsip_transport_type_eVar6, pjsip_transport_type_eVar7, pjsip_transport_type_eVar8, pjsip_transport_type_eVar9, pjsip_transport_type_eVar10, pjsip_transport_type_eVar11, pjsip_transport_type_eVar12};
        swigNext = 0;
    }

    private pjsip_transport_type_e(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjsip_transport_type_e swigToEnum(int i2) {
        pjsip_transport_type_e[] pjsip_transport_type_eVarArr = swigValues;
        if (i2 < pjsip_transport_type_eVarArr.length && i2 >= 0 && pjsip_transport_type_eVarArr[i2].swigValue == i2) {
            return pjsip_transport_type_eVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjsip_transport_type_e[] pjsip_transport_type_eVarArr2 = swigValues;
            if (i3 < pjsip_transport_type_eVarArr2.length) {
                if (pjsip_transport_type_eVarArr2[i3].swigValue == i2) {
                    return pjsip_transport_type_eVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjsip_transport_type_e.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsip_transport_type_e(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjsip_transport_type_e(String str, pjsip_transport_type_e pjsip_transport_type_eVar) {
        this.swigName = str;
        int i2 = pjsip_transport_type_eVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
