package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjsua_buddy_status {
    public static final pjsua_buddy_status PJSUA_BUDDY_STATUS_OFFLINE;
    public static final pjsua_buddy_status PJSUA_BUDDY_STATUS_ONLINE;
    public static final pjsua_buddy_status PJSUA_BUDDY_STATUS_UNKNOWN;
    private static int swigNext;
    private static pjsua_buddy_status[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjsua_buddy_status pjsua_buddy_statusVar = new pjsua_buddy_status("PJSUA_BUDDY_STATUS_UNKNOWN");
        PJSUA_BUDDY_STATUS_UNKNOWN = pjsua_buddy_statusVar;
        pjsua_buddy_status pjsua_buddy_statusVar2 = new pjsua_buddy_status("PJSUA_BUDDY_STATUS_ONLINE");
        PJSUA_BUDDY_STATUS_ONLINE = pjsua_buddy_statusVar2;
        pjsua_buddy_status pjsua_buddy_statusVar3 = new pjsua_buddy_status("PJSUA_BUDDY_STATUS_OFFLINE");
        PJSUA_BUDDY_STATUS_OFFLINE = pjsua_buddy_statusVar3;
        swigValues = new pjsua_buddy_status[]{pjsua_buddy_statusVar, pjsua_buddy_statusVar2, pjsua_buddy_statusVar3};
        swigNext = 0;
    }

    private pjsua_buddy_status(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjsua_buddy_status swigToEnum(int i2) {
        pjsua_buddy_status[] pjsua_buddy_statusVarArr = swigValues;
        if (i2 < pjsua_buddy_statusVarArr.length && i2 >= 0 && pjsua_buddy_statusVarArr[i2].swigValue == i2) {
            return pjsua_buddy_statusVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjsua_buddy_status[] pjsua_buddy_statusVarArr2 = swigValues;
            if (i3 < pjsua_buddy_statusVarArr2.length) {
                if (pjsua_buddy_statusVarArr2[i3].swigValue == i2) {
                    return pjsua_buddy_statusVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjsua_buddy_status.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_buddy_status(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjsua_buddy_status(String str, pjsua_buddy_status pjsua_buddy_statusVar) {
        this.swigName = str;
        int i2 = pjsua_buddy_statusVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
