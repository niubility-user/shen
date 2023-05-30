package com.jingdong.jdreact.plugin.map;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.views.view.ReactViewGroup;

/* loaded from: classes13.dex */
public class ViewAttacherGroup extends ReactViewGroup {
    public ViewAttacherGroup(Context context) {
        super(context);
        setWillNotDraw(true);
        setVisibility(0);
        setAlpha(0.0f);
        setRemoveClippedSubviews(false);
        if (Build.VERSION.SDK_INT >= 18) {
            setClipBounds(new Rect(0, 0, 0, 0));
        }
        setOverflow(ViewProps.HIDDEN);
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }
}
