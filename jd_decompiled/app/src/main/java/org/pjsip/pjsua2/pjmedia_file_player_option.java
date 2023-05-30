package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjmedia_file_player_option {
    public static final pjmedia_file_player_option PJMEDIA_FILE_NO_LOOP;
    private static int swigNext;
    private static pjmedia_file_player_option[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjmedia_file_player_option pjmedia_file_player_optionVar = new pjmedia_file_player_option("PJMEDIA_FILE_NO_LOOP", pjsua2JNI.PJMEDIA_FILE_NO_LOOP_get());
        PJMEDIA_FILE_NO_LOOP = pjmedia_file_player_optionVar;
        swigValues = new pjmedia_file_player_option[]{pjmedia_file_player_optionVar};
        swigNext = 0;
    }

    private pjmedia_file_player_option(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjmedia_file_player_option swigToEnum(int i2) {
        pjmedia_file_player_option[] pjmedia_file_player_optionVarArr = swigValues;
        if (i2 < pjmedia_file_player_optionVarArr.length && i2 >= 0 && pjmedia_file_player_optionVarArr[i2].swigValue == i2) {
            return pjmedia_file_player_optionVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjmedia_file_player_option[] pjmedia_file_player_optionVarArr2 = swigValues;
            if (i3 < pjmedia_file_player_optionVarArr2.length) {
                if (pjmedia_file_player_optionVarArr2[i3].swigValue == i2) {
                    return pjmedia_file_player_optionVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjmedia_file_player_option.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjmedia_file_player_option(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjmedia_file_player_option(String str, pjmedia_file_player_option pjmedia_file_player_optionVar) {
        this.swigName = str;
        int i2 = pjmedia_file_player_optionVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
