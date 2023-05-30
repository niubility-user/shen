package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjmedia_vid_packing {
    public static final pjmedia_vid_packing PJMEDIA_VID_PACKING_PACKETS;
    public static final pjmedia_vid_packing PJMEDIA_VID_PACKING_UNKNOWN;
    public static final pjmedia_vid_packing PJMEDIA_VID_PACKING_WHOLE;
    private static int swigNext;
    private static pjmedia_vid_packing[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjmedia_vid_packing pjmedia_vid_packingVar = new pjmedia_vid_packing("PJMEDIA_VID_PACKING_UNKNOWN");
        PJMEDIA_VID_PACKING_UNKNOWN = pjmedia_vid_packingVar;
        pjmedia_vid_packing pjmedia_vid_packingVar2 = new pjmedia_vid_packing("PJMEDIA_VID_PACKING_PACKETS", pjsua2JNI.PJMEDIA_VID_PACKING_PACKETS_get());
        PJMEDIA_VID_PACKING_PACKETS = pjmedia_vid_packingVar2;
        pjmedia_vid_packing pjmedia_vid_packingVar3 = new pjmedia_vid_packing("PJMEDIA_VID_PACKING_WHOLE", pjsua2JNI.PJMEDIA_VID_PACKING_WHOLE_get());
        PJMEDIA_VID_PACKING_WHOLE = pjmedia_vid_packingVar3;
        swigValues = new pjmedia_vid_packing[]{pjmedia_vid_packingVar, pjmedia_vid_packingVar2, pjmedia_vid_packingVar3};
        swigNext = 0;
    }

    private pjmedia_vid_packing(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjmedia_vid_packing swigToEnum(int i2) {
        pjmedia_vid_packing[] pjmedia_vid_packingVarArr = swigValues;
        if (i2 < pjmedia_vid_packingVarArr.length && i2 >= 0 && pjmedia_vid_packingVarArr[i2].swigValue == i2) {
            return pjmedia_vid_packingVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjmedia_vid_packing[] pjmedia_vid_packingVarArr2 = swigValues;
            if (i3 < pjmedia_vid_packingVarArr2.length) {
                if (pjmedia_vid_packingVarArr2[i3].swigValue == i2) {
                    return pjmedia_vid_packingVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjmedia_vid_packing.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjmedia_vid_packing(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjmedia_vid_packing(String str, pjmedia_vid_packing pjmedia_vid_packingVar) {
        this.swigName = str;
        int i2 = pjmedia_vid_packingVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
