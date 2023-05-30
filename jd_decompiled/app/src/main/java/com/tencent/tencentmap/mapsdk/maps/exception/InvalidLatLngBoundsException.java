package com.tencent.tencentmap.mapsdk.maps.exception;

/* loaded from: classes9.dex */
public class InvalidLatLngBoundsException extends RuntimeException {
    public InvalidLatLngBoundsException(int i2) {
        super("Cannot create a LatLngBounds from " + i2 + " items");
    }
}
