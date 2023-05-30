package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjmedia_orient {
    public static final pjmedia_orient PJMEDIA_ORIENT_NATURAL;
    public static final pjmedia_orient PJMEDIA_ORIENT_ROTATE_180DEG;
    public static final pjmedia_orient PJMEDIA_ORIENT_ROTATE_270DEG;
    public static final pjmedia_orient PJMEDIA_ORIENT_ROTATE_90DEG;
    public static final pjmedia_orient PJMEDIA_ORIENT_UNKNOWN;
    private static int swigNext;
    private static pjmedia_orient[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjmedia_orient pjmedia_orientVar = new pjmedia_orient("PJMEDIA_ORIENT_UNKNOWN");
        PJMEDIA_ORIENT_UNKNOWN = pjmedia_orientVar;
        pjmedia_orient pjmedia_orientVar2 = new pjmedia_orient("PJMEDIA_ORIENT_NATURAL");
        PJMEDIA_ORIENT_NATURAL = pjmedia_orientVar2;
        pjmedia_orient pjmedia_orientVar3 = new pjmedia_orient("PJMEDIA_ORIENT_ROTATE_90DEG");
        PJMEDIA_ORIENT_ROTATE_90DEG = pjmedia_orientVar3;
        pjmedia_orient pjmedia_orientVar4 = new pjmedia_orient("PJMEDIA_ORIENT_ROTATE_180DEG");
        PJMEDIA_ORIENT_ROTATE_180DEG = pjmedia_orientVar4;
        pjmedia_orient pjmedia_orientVar5 = new pjmedia_orient("PJMEDIA_ORIENT_ROTATE_270DEG");
        PJMEDIA_ORIENT_ROTATE_270DEG = pjmedia_orientVar5;
        swigValues = new pjmedia_orient[]{pjmedia_orientVar, pjmedia_orientVar2, pjmedia_orientVar3, pjmedia_orientVar4, pjmedia_orientVar5};
        swigNext = 0;
    }

    private pjmedia_orient(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjmedia_orient swigToEnum(int i2) {
        pjmedia_orient[] pjmedia_orientVarArr = swigValues;
        if (i2 < pjmedia_orientVarArr.length && i2 >= 0 && pjmedia_orientVarArr[i2].swigValue == i2) {
            return pjmedia_orientVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjmedia_orient[] pjmedia_orientVarArr2 = swigValues;
            if (i3 < pjmedia_orientVarArr2.length) {
                if (pjmedia_orientVarArr2[i3].swigValue == i2) {
                    return pjmedia_orientVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjmedia_orient.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjmedia_orient(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjmedia_orient(String str, pjmedia_orient pjmedia_orientVar) {
        this.swigName = str;
        int i2 = pjmedia_orientVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
