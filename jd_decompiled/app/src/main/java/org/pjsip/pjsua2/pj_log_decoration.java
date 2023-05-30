package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pj_log_decoration {
    public static final pj_log_decoration PJ_LOG_HAS_COLOR;
    public static final pj_log_decoration PJ_LOG_HAS_CR;
    public static final pj_log_decoration PJ_LOG_HAS_DAY_NAME;
    public static final pj_log_decoration PJ_LOG_HAS_DAY_OF_MON;
    public static final pj_log_decoration PJ_LOG_HAS_INDENT;
    public static final pj_log_decoration PJ_LOG_HAS_LEVEL_TEXT;
    public static final pj_log_decoration PJ_LOG_HAS_MICRO_SEC;
    public static final pj_log_decoration PJ_LOG_HAS_MONTH;
    public static final pj_log_decoration PJ_LOG_HAS_NEWLINE;
    public static final pj_log_decoration PJ_LOG_HAS_SENDER;
    public static final pj_log_decoration PJ_LOG_HAS_SPACE;
    public static final pj_log_decoration PJ_LOG_HAS_THREAD_ID;
    public static final pj_log_decoration PJ_LOG_HAS_THREAD_SWC;
    public static final pj_log_decoration PJ_LOG_HAS_TIME;
    public static final pj_log_decoration PJ_LOG_HAS_YEAR;
    private static int swigNext;
    private static pj_log_decoration[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pj_log_decoration pj_log_decorationVar = new pj_log_decoration("PJ_LOG_HAS_DAY_NAME", pjsua2JNI.PJ_LOG_HAS_DAY_NAME_get());
        PJ_LOG_HAS_DAY_NAME = pj_log_decorationVar;
        pj_log_decoration pj_log_decorationVar2 = new pj_log_decoration("PJ_LOG_HAS_YEAR", pjsua2JNI.PJ_LOG_HAS_YEAR_get());
        PJ_LOG_HAS_YEAR = pj_log_decorationVar2;
        pj_log_decoration pj_log_decorationVar3 = new pj_log_decoration("PJ_LOG_HAS_MONTH", pjsua2JNI.PJ_LOG_HAS_MONTH_get());
        PJ_LOG_HAS_MONTH = pj_log_decorationVar3;
        pj_log_decoration pj_log_decorationVar4 = new pj_log_decoration("PJ_LOG_HAS_DAY_OF_MON", pjsua2JNI.PJ_LOG_HAS_DAY_OF_MON_get());
        PJ_LOG_HAS_DAY_OF_MON = pj_log_decorationVar4;
        pj_log_decoration pj_log_decorationVar5 = new pj_log_decoration("PJ_LOG_HAS_TIME", pjsua2JNI.PJ_LOG_HAS_TIME_get());
        PJ_LOG_HAS_TIME = pj_log_decorationVar5;
        pj_log_decoration pj_log_decorationVar6 = new pj_log_decoration("PJ_LOG_HAS_MICRO_SEC", pjsua2JNI.PJ_LOG_HAS_MICRO_SEC_get());
        PJ_LOG_HAS_MICRO_SEC = pj_log_decorationVar6;
        pj_log_decoration pj_log_decorationVar7 = new pj_log_decoration("PJ_LOG_HAS_SENDER", pjsua2JNI.PJ_LOG_HAS_SENDER_get());
        PJ_LOG_HAS_SENDER = pj_log_decorationVar7;
        pj_log_decoration pj_log_decorationVar8 = new pj_log_decoration("PJ_LOG_HAS_NEWLINE", pjsua2JNI.PJ_LOG_HAS_NEWLINE_get());
        PJ_LOG_HAS_NEWLINE = pj_log_decorationVar8;
        pj_log_decoration pj_log_decorationVar9 = new pj_log_decoration("PJ_LOG_HAS_CR", pjsua2JNI.PJ_LOG_HAS_CR_get());
        PJ_LOG_HAS_CR = pj_log_decorationVar9;
        pj_log_decoration pj_log_decorationVar10 = new pj_log_decoration("PJ_LOG_HAS_SPACE", pjsua2JNI.PJ_LOG_HAS_SPACE_get());
        PJ_LOG_HAS_SPACE = pj_log_decorationVar10;
        pj_log_decoration pj_log_decorationVar11 = new pj_log_decoration("PJ_LOG_HAS_COLOR", pjsua2JNI.PJ_LOG_HAS_COLOR_get());
        PJ_LOG_HAS_COLOR = pj_log_decorationVar11;
        pj_log_decoration pj_log_decorationVar12 = new pj_log_decoration("PJ_LOG_HAS_LEVEL_TEXT", pjsua2JNI.PJ_LOG_HAS_LEVEL_TEXT_get());
        PJ_LOG_HAS_LEVEL_TEXT = pj_log_decorationVar12;
        pj_log_decoration pj_log_decorationVar13 = new pj_log_decoration("PJ_LOG_HAS_THREAD_ID", pjsua2JNI.PJ_LOG_HAS_THREAD_ID_get());
        PJ_LOG_HAS_THREAD_ID = pj_log_decorationVar13;
        pj_log_decoration pj_log_decorationVar14 = new pj_log_decoration("PJ_LOG_HAS_THREAD_SWC", pjsua2JNI.PJ_LOG_HAS_THREAD_SWC_get());
        PJ_LOG_HAS_THREAD_SWC = pj_log_decorationVar14;
        pj_log_decoration pj_log_decorationVar15 = new pj_log_decoration("PJ_LOG_HAS_INDENT", pjsua2JNI.PJ_LOG_HAS_INDENT_get());
        PJ_LOG_HAS_INDENT = pj_log_decorationVar15;
        swigValues = new pj_log_decoration[]{pj_log_decorationVar, pj_log_decorationVar2, pj_log_decorationVar3, pj_log_decorationVar4, pj_log_decorationVar5, pj_log_decorationVar6, pj_log_decorationVar7, pj_log_decorationVar8, pj_log_decorationVar9, pj_log_decorationVar10, pj_log_decorationVar11, pj_log_decorationVar12, pj_log_decorationVar13, pj_log_decorationVar14, pj_log_decorationVar15};
        swigNext = 0;
    }

    private pj_log_decoration(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pj_log_decoration swigToEnum(int i2) {
        pj_log_decoration[] pj_log_decorationVarArr = swigValues;
        if (i2 < pj_log_decorationVarArr.length && i2 >= 0 && pj_log_decorationVarArr[i2].swigValue == i2) {
            return pj_log_decorationVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pj_log_decoration[] pj_log_decorationVarArr2 = swigValues;
            if (i3 < pj_log_decorationVarArr2.length) {
                if (pj_log_decorationVarArr2[i3].swigValue == i2) {
                    return pj_log_decorationVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pj_log_decoration.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pj_log_decoration(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pj_log_decoration(String str, pj_log_decoration pj_log_decorationVar) {
        this.swigName = str;
        int i2 = pj_log_decorationVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
