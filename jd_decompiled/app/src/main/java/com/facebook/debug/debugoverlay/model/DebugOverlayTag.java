package com.facebook.debug.debugoverlay.model;

import javax.annotation.concurrent.Immutable;

@Immutable
/* loaded from: classes.dex */
public class DebugOverlayTag {
    public final int color;
    public final String description;
    public final String name;

    public DebugOverlayTag(String str, String str2, int i2) {
        this.name = str;
        this.description = str2;
        this.color = i2;
    }
}
