package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjmedia_aud_dev_route {
    public static final pjmedia_aud_dev_route PJMEDIA_AUD_DEV_ROUTE_BLUETOOTH;
    public static final pjmedia_aud_dev_route PJMEDIA_AUD_DEV_ROUTE_CUSTOM;
    public static final pjmedia_aud_dev_route PJMEDIA_AUD_DEV_ROUTE_DEFAULT;
    public static final pjmedia_aud_dev_route PJMEDIA_AUD_DEV_ROUTE_EARPIECE;
    public static final pjmedia_aud_dev_route PJMEDIA_AUD_DEV_ROUTE_LOUDSPEAKER;
    private static int swigNext;
    private static pjmedia_aud_dev_route[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjmedia_aud_dev_route pjmedia_aud_dev_routeVar = new pjmedia_aud_dev_route("PJMEDIA_AUD_DEV_ROUTE_DEFAULT", pjsua2JNI.PJMEDIA_AUD_DEV_ROUTE_DEFAULT_get());
        PJMEDIA_AUD_DEV_ROUTE_DEFAULT = pjmedia_aud_dev_routeVar;
        pjmedia_aud_dev_route pjmedia_aud_dev_routeVar2 = new pjmedia_aud_dev_route("PJMEDIA_AUD_DEV_ROUTE_LOUDSPEAKER", pjsua2JNI.PJMEDIA_AUD_DEV_ROUTE_LOUDSPEAKER_get());
        PJMEDIA_AUD_DEV_ROUTE_LOUDSPEAKER = pjmedia_aud_dev_routeVar2;
        pjmedia_aud_dev_route pjmedia_aud_dev_routeVar3 = new pjmedia_aud_dev_route("PJMEDIA_AUD_DEV_ROUTE_EARPIECE", pjsua2JNI.PJMEDIA_AUD_DEV_ROUTE_EARPIECE_get());
        PJMEDIA_AUD_DEV_ROUTE_EARPIECE = pjmedia_aud_dev_routeVar3;
        pjmedia_aud_dev_route pjmedia_aud_dev_routeVar4 = new pjmedia_aud_dev_route("PJMEDIA_AUD_DEV_ROUTE_BLUETOOTH", pjsua2JNI.PJMEDIA_AUD_DEV_ROUTE_BLUETOOTH_get());
        PJMEDIA_AUD_DEV_ROUTE_BLUETOOTH = pjmedia_aud_dev_routeVar4;
        pjmedia_aud_dev_route pjmedia_aud_dev_routeVar5 = new pjmedia_aud_dev_route("PJMEDIA_AUD_DEV_ROUTE_CUSTOM", pjsua2JNI.PJMEDIA_AUD_DEV_ROUTE_CUSTOM_get());
        PJMEDIA_AUD_DEV_ROUTE_CUSTOM = pjmedia_aud_dev_routeVar5;
        swigValues = new pjmedia_aud_dev_route[]{pjmedia_aud_dev_routeVar, pjmedia_aud_dev_routeVar2, pjmedia_aud_dev_routeVar3, pjmedia_aud_dev_routeVar4, pjmedia_aud_dev_routeVar5};
        swigNext = 0;
    }

    private pjmedia_aud_dev_route(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjmedia_aud_dev_route swigToEnum(int i2) {
        pjmedia_aud_dev_route[] pjmedia_aud_dev_routeVarArr = swigValues;
        if (i2 < pjmedia_aud_dev_routeVarArr.length && i2 >= 0 && pjmedia_aud_dev_routeVarArr[i2].swigValue == i2) {
            return pjmedia_aud_dev_routeVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjmedia_aud_dev_route[] pjmedia_aud_dev_routeVarArr2 = swigValues;
            if (i3 < pjmedia_aud_dev_routeVarArr2.length) {
                if (pjmedia_aud_dev_routeVarArr2[i3].swigValue == i2) {
                    return pjmedia_aud_dev_routeVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjmedia_aud_dev_route.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjmedia_aud_dev_route(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjmedia_aud_dev_route(String str, pjmedia_aud_dev_route pjmedia_aud_dev_routeVar) {
        this.swigName = str;
        int i2 = pjmedia_aud_dev_routeVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
