package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pj_file_access {
    public static final pj_file_access PJ_O_APPEND;
    public static final pj_file_access PJ_O_RDONLY;
    public static final pj_file_access PJ_O_RDWR;
    public static final pj_file_access PJ_O_WRONLY;
    private static int swigNext;
    private static pj_file_access[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pj_file_access pj_file_accessVar = new pj_file_access("PJ_O_RDONLY", pjsua2JNI.PJ_O_RDONLY_get());
        PJ_O_RDONLY = pj_file_accessVar;
        pj_file_access pj_file_accessVar2 = new pj_file_access("PJ_O_WRONLY", pjsua2JNI.PJ_O_WRONLY_get());
        PJ_O_WRONLY = pj_file_accessVar2;
        pj_file_access pj_file_accessVar3 = new pj_file_access("PJ_O_RDWR", pjsua2JNI.PJ_O_RDWR_get());
        PJ_O_RDWR = pj_file_accessVar3;
        pj_file_access pj_file_accessVar4 = new pj_file_access("PJ_O_APPEND", pjsua2JNI.PJ_O_APPEND_get());
        PJ_O_APPEND = pj_file_accessVar4;
        swigValues = new pj_file_access[]{pj_file_accessVar, pj_file_accessVar2, pj_file_accessVar3, pj_file_accessVar4};
        swigNext = 0;
    }

    private pj_file_access(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pj_file_access swigToEnum(int i2) {
        pj_file_access[] pj_file_accessVarArr = swigValues;
        if (i2 < pj_file_accessVarArr.length && i2 >= 0 && pj_file_accessVarArr[i2].swigValue == i2) {
            return pj_file_accessVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pj_file_access[] pj_file_accessVarArr2 = swigValues;
            if (i3 < pj_file_accessVarArr2.length) {
                if (pj_file_accessVarArr2[i3].swigValue == i2) {
                    return pj_file_accessVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pj_file_access.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pj_file_access(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pj_file_access(String str, pj_file_access pj_file_accessVar) {
        this.swigName = str;
        int i2 = pj_file_accessVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
