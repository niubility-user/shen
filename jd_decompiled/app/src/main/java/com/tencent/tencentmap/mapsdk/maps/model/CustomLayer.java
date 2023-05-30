package com.tencent.tencentmap.mapsdk.maps.model;

import com.tencent.tencentmap.mapsdk.maps.interfaces.Removable;

/* loaded from: classes9.dex */
public interface CustomLayer extends Removable, IOverlay {
    void clearDiskCache();

    void reload();
}
