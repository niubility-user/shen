package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjmedia_type {
    public static final pjmedia_type PJMEDIA_TYPE_APPLICATION;
    public static final pjmedia_type PJMEDIA_TYPE_AUDIO;
    public static final pjmedia_type PJMEDIA_TYPE_NONE;
    public static final pjmedia_type PJMEDIA_TYPE_UNKNOWN;
    public static final pjmedia_type PJMEDIA_TYPE_VIDEO;
    private static int swigNext;
    private static pjmedia_type[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjmedia_type pjmedia_typeVar = new pjmedia_type("PJMEDIA_TYPE_NONE");
        PJMEDIA_TYPE_NONE = pjmedia_typeVar;
        pjmedia_type pjmedia_typeVar2 = new pjmedia_type("PJMEDIA_TYPE_AUDIO");
        PJMEDIA_TYPE_AUDIO = pjmedia_typeVar2;
        pjmedia_type pjmedia_typeVar3 = new pjmedia_type("PJMEDIA_TYPE_VIDEO");
        PJMEDIA_TYPE_VIDEO = pjmedia_typeVar3;
        pjmedia_type pjmedia_typeVar4 = new pjmedia_type("PJMEDIA_TYPE_APPLICATION");
        PJMEDIA_TYPE_APPLICATION = pjmedia_typeVar4;
        pjmedia_type pjmedia_typeVar5 = new pjmedia_type("PJMEDIA_TYPE_UNKNOWN");
        PJMEDIA_TYPE_UNKNOWN = pjmedia_typeVar5;
        swigValues = new pjmedia_type[]{pjmedia_typeVar, pjmedia_typeVar2, pjmedia_typeVar3, pjmedia_typeVar4, pjmedia_typeVar5};
        swigNext = 0;
    }

    private pjmedia_type(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjmedia_type swigToEnum(int i2) {
        pjmedia_type[] pjmedia_typeVarArr = swigValues;
        if (i2 < pjmedia_typeVarArr.length && i2 >= 0 && pjmedia_typeVarArr[i2].swigValue == i2) {
            return pjmedia_typeVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjmedia_type[] pjmedia_typeVarArr2 = swigValues;
            if (i3 < pjmedia_typeVarArr2.length) {
                if (pjmedia_typeVarArr2[i3].swigValue == i2) {
                    return pjmedia_typeVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjmedia_type.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjmedia_type(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjmedia_type(String str, pjmedia_type pjmedia_typeVar) {
        this.swigName = str;
        int i2 = pjmedia_typeVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
