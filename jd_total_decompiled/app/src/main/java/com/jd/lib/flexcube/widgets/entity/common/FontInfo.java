package com.jd.lib.flexcube.widgets.entity.common;

/* loaded from: classes15.dex */
public class FontInfo {
    public String align;
    public String bold;
    public String color;
    public String italic;
    public String jdFont;
    public float scale;
    public float size;
    public String strickout;
    public String underscore;
    public String zoom;

    public float getScale() {
        float f2 = this.scale;
        if (f2 <= 0.0f || f2 > 1.0f) {
            this.scale = 0.7f;
        }
        return this.scale;
    }
}
