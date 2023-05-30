package com.jingdong.common.widget.shadow.engine;

import android.graphics.Canvas;
import android.view.View;

/* loaded from: classes12.dex */
public class PathShadowEngine extends BaseShadowEngine {
    @Override // com.jingdong.common.widget.shadow.engine.BaseShadowEngine
    public void initConfig() {
    }

    @Override // com.jingdong.common.widget.shadow.engine.ShadowEngine
    public boolean onClipChildCanvas(Canvas canvas, View view) {
        return false;
    }

    @Override // com.jingdong.common.widget.shadow.engine.ShadowEngine
    public void onDraw(Canvas canvas) {
    }

    @Override // com.jingdong.common.widget.shadow.engine.ShadowEngine
    public void onDrawOver(Canvas canvas) {
    }

    @Override // com.jingdong.common.widget.shadow.engine.ShadowEngine
    public void onLayout(View view, int i2, int i3, int i4, int i5) {
    }
}
