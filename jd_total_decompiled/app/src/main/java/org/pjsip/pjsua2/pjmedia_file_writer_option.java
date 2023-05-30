package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjmedia_file_writer_option {
    public static final pjmedia_file_writer_option PJMEDIA_FILE_WRITE_ALAW;
    public static final pjmedia_file_writer_option PJMEDIA_FILE_WRITE_PCM;
    public static final pjmedia_file_writer_option PJMEDIA_FILE_WRITE_ULAW;
    private static int swigNext;
    private static pjmedia_file_writer_option[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjmedia_file_writer_option pjmedia_file_writer_optionVar = new pjmedia_file_writer_option("PJMEDIA_FILE_WRITE_PCM", pjsua2JNI.PJMEDIA_FILE_WRITE_PCM_get());
        PJMEDIA_FILE_WRITE_PCM = pjmedia_file_writer_optionVar;
        pjmedia_file_writer_option pjmedia_file_writer_optionVar2 = new pjmedia_file_writer_option("PJMEDIA_FILE_WRITE_ALAW", pjsua2JNI.PJMEDIA_FILE_WRITE_ALAW_get());
        PJMEDIA_FILE_WRITE_ALAW = pjmedia_file_writer_optionVar2;
        pjmedia_file_writer_option pjmedia_file_writer_optionVar3 = new pjmedia_file_writer_option("PJMEDIA_FILE_WRITE_ULAW", pjsua2JNI.PJMEDIA_FILE_WRITE_ULAW_get());
        PJMEDIA_FILE_WRITE_ULAW = pjmedia_file_writer_optionVar3;
        swigValues = new pjmedia_file_writer_option[]{pjmedia_file_writer_optionVar, pjmedia_file_writer_optionVar2, pjmedia_file_writer_optionVar3};
        swigNext = 0;
    }

    private pjmedia_file_writer_option(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjmedia_file_writer_option swigToEnum(int i2) {
        pjmedia_file_writer_option[] pjmedia_file_writer_optionVarArr = swigValues;
        if (i2 < pjmedia_file_writer_optionVarArr.length && i2 >= 0 && pjmedia_file_writer_optionVarArr[i2].swigValue == i2) {
            return pjmedia_file_writer_optionVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjmedia_file_writer_option[] pjmedia_file_writer_optionVarArr2 = swigValues;
            if (i3 < pjmedia_file_writer_optionVarArr2.length) {
                if (pjmedia_file_writer_optionVarArr2[i3].swigValue == i2) {
                    return pjmedia_file_writer_optionVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjmedia_file_writer_option.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjmedia_file_writer_option(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjmedia_file_writer_option(String str, pjmedia_file_writer_option pjmedia_file_writer_optionVar) {
        this.swigName = str;
        int i2 = pjmedia_file_writer_optionVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
