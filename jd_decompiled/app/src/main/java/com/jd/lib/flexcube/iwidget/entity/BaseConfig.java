package com.jd.lib.flexcube.iwidget.entity;

import com.jd.lib.flexcube.iwidget.entity.material.PaddingInfo;

/* loaded from: classes14.dex */
public class BaseConfig {

    /* renamed from: h */
    public float f4225h;
    public PaddingInfo marginInfo;
    public float w;
    public float x;
    public float y;

    public int getH(float f2) {
        return (int) (this.f4225h * f2);
    }

    public int getW(float f2) {
        return (int) (this.w * f2);
    }

    public int getX(float f2) {
        return (int) (this.x * f2);
    }

    public int getY(float f2) {
        return (int) (this.y * f2);
    }
}
