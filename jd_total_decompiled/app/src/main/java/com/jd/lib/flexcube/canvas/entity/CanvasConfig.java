package com.jd.lib.flexcube.canvas.entity;

import android.text.TextUtils;
import com.jd.lib.flexcube.canvas.entity.common.BgInfo;
import com.jd.lib.flexcube.widgets.entity.common.CfInfo;

/* loaded from: classes14.dex */
public class CanvasConfig {
    public BgInfo bgInfo;
    public String cardTransparent;
    public CfInfo cfInfo;

    /* renamed from: h  reason: collision with root package name */
    public int f4223h;
    public String scalingRatio;
    public String uuid;
    public int w;

    public String getUuid() {
        if (TextUtils.isEmpty(this.uuid)) {
            this.uuid = toString();
        }
        return this.uuid;
    }
}
