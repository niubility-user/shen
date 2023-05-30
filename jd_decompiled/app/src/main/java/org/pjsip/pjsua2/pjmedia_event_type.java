package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjmedia_event_type {
    public static final pjmedia_event_type PJMEDIA_EVENT_FMT_CHANGED;
    public static final pjmedia_event_type PJMEDIA_EVENT_KEYFRAME_FOUND;
    public static final pjmedia_event_type PJMEDIA_EVENT_KEYFRAME_MISSING;
    public static final pjmedia_event_type PJMEDIA_EVENT_MOUSE_BTN_DOWN;
    public static final pjmedia_event_type PJMEDIA_EVENT_NONE;
    public static final pjmedia_event_type PJMEDIA_EVENT_ORIENT_CHANGED;
    public static final pjmedia_event_type PJMEDIA_EVENT_WND_CLOSED;
    public static final pjmedia_event_type PJMEDIA_EVENT_WND_CLOSING;
    public static final pjmedia_event_type PJMEDIA_EVENT_WND_RESIZED;
    private static int swigNext;
    private static pjmedia_event_type[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjmedia_event_type pjmedia_event_typeVar = new pjmedia_event_type("PJMEDIA_EVENT_NONE");
        PJMEDIA_EVENT_NONE = pjmedia_event_typeVar;
        pjmedia_event_type pjmedia_event_typeVar2 = new pjmedia_event_type("PJMEDIA_EVENT_FMT_CHANGED", pjsua2JNI.PJMEDIA_EVENT_FMT_CHANGED_get());
        PJMEDIA_EVENT_FMT_CHANGED = pjmedia_event_typeVar2;
        pjmedia_event_type pjmedia_event_typeVar3 = new pjmedia_event_type("PJMEDIA_EVENT_WND_CLOSING", pjsua2JNI.PJMEDIA_EVENT_WND_CLOSING_get());
        PJMEDIA_EVENT_WND_CLOSING = pjmedia_event_typeVar3;
        pjmedia_event_type pjmedia_event_typeVar4 = new pjmedia_event_type("PJMEDIA_EVENT_WND_CLOSED", pjsua2JNI.PJMEDIA_EVENT_WND_CLOSED_get());
        PJMEDIA_EVENT_WND_CLOSED = pjmedia_event_typeVar4;
        pjmedia_event_type pjmedia_event_typeVar5 = new pjmedia_event_type("PJMEDIA_EVENT_WND_RESIZED", pjsua2JNI.PJMEDIA_EVENT_WND_RESIZED_get());
        PJMEDIA_EVENT_WND_RESIZED = pjmedia_event_typeVar5;
        pjmedia_event_type pjmedia_event_typeVar6 = new pjmedia_event_type("PJMEDIA_EVENT_MOUSE_BTN_DOWN", pjsua2JNI.PJMEDIA_EVENT_MOUSE_BTN_DOWN_get());
        PJMEDIA_EVENT_MOUSE_BTN_DOWN = pjmedia_event_typeVar6;
        pjmedia_event_type pjmedia_event_typeVar7 = new pjmedia_event_type("PJMEDIA_EVENT_KEYFRAME_FOUND", pjsua2JNI.PJMEDIA_EVENT_KEYFRAME_FOUND_get());
        PJMEDIA_EVENT_KEYFRAME_FOUND = pjmedia_event_typeVar7;
        pjmedia_event_type pjmedia_event_typeVar8 = new pjmedia_event_type("PJMEDIA_EVENT_KEYFRAME_MISSING", pjsua2JNI.PJMEDIA_EVENT_KEYFRAME_MISSING_get());
        PJMEDIA_EVENT_KEYFRAME_MISSING = pjmedia_event_typeVar8;
        pjmedia_event_type pjmedia_event_typeVar9 = new pjmedia_event_type("PJMEDIA_EVENT_ORIENT_CHANGED", pjsua2JNI.PJMEDIA_EVENT_ORIENT_CHANGED_get());
        PJMEDIA_EVENT_ORIENT_CHANGED = pjmedia_event_typeVar9;
        swigValues = new pjmedia_event_type[]{pjmedia_event_typeVar, pjmedia_event_typeVar2, pjmedia_event_typeVar3, pjmedia_event_typeVar4, pjmedia_event_typeVar5, pjmedia_event_typeVar6, pjmedia_event_typeVar7, pjmedia_event_typeVar8, pjmedia_event_typeVar9};
        swigNext = 0;
    }

    private pjmedia_event_type(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjmedia_event_type swigToEnum(int i2) {
        pjmedia_event_type[] pjmedia_event_typeVarArr = swigValues;
        if (i2 < pjmedia_event_typeVarArr.length && i2 >= 0 && pjmedia_event_typeVarArr[i2].swigValue == i2) {
            return pjmedia_event_typeVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjmedia_event_type[] pjmedia_event_typeVarArr2 = swigValues;
            if (i3 < pjmedia_event_typeVarArr2.length) {
                if (pjmedia_event_typeVarArr2[i3].swigValue == i2) {
                    return pjmedia_event_typeVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjmedia_event_type.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjmedia_event_type(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjmedia_event_type(String str, pjmedia_event_type pjmedia_event_typeVar) {
        this.swigName = str;
        int i2 = pjmedia_event_typeVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
