package com.tencent.tencentmap.mapsdk.maps.interfaces;

/* loaded from: classes9.dex */
public interface Collisionable {
    boolean isCollisionBy(Collision collision);

    void setCollisions(Collision... collisionArr);
}
