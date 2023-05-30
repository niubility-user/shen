package com.jd.lib.flexcube.layout.floor.banner.swipe;

import android.content.Context;
import android.graphics.Rect;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.flexcube.help.FlexAutoPlayInterface;
import com.jd.lib.flexcube.iwidget.entity.material.MaterialModel;
import com.jd.lib.flexcube.layout.entity.FlexCubeModel;
import com.jd.lib.flexcube.layout.entity.common.ViewStyle;
import com.jd.lib.flexcube.layout.floor.banner.common.FlexParentFloor;
import com.jd.lib.flexcube.widgets.entity.common.CfInfo;
import java.util.List;

/* loaded from: classes14.dex */
public class FlexSwipeCardFloor extends FlexParentFloor implements FlexAutoPlayInterface {

    /* renamed from: m  reason: collision with root package name */
    private FlexSwipeCardLayout f4327m;

    public FlexSwipeCardFloor(@NonNull Context context) {
        super(context);
        FlexSwipeCardLayout flexSwipeCardLayout = new FlexSwipeCardLayout(context);
        this.f4327m = flexSwipeCardLayout;
        addView(flexSwipeCardLayout);
    }

    private void g(ViewStyle viewStyle, float f2, int i2) {
        if (viewStyle == null) {
            this.f4327m.l(new CfInfo(0.0f, 0.0f, 0.0f, 0.0f), f2);
        } else {
            this.f4327m.l(viewStyle, f2);
        }
    }

    @Override // com.jd.lib.flexcube.layout.floor.banner.common.FlexParentFloor
    public void a() {
        this.f4327m.setLayoutParams(new FrameLayout.LayoutParams(-1, 1));
    }

    @Override // com.jd.lib.flexcube.help.FlexAutoPlayInterface
    public boolean autoPlay(boolean z, boolean z2) {
        FlexCubeModel flexCubeModel;
        if (z && (flexCubeModel = this.f4272i) != null && flexCubeModel.hasVideo) {
            return this.f4327m.autoPlay(z, z2);
        }
        return false;
    }

    @Override // com.jd.lib.flexcube.layout.floor.banner.common.FlexParentFloor
    public void c(@NonNull BabelScope babelScope, @NonNull FlexCubeModel flexCubeModel, List<MaterialModel> list, int i2) {
        g(flexCubeModel.floorConfig.viewStyle, this.f4273j, i2);
        this.f4327m.m(babelScope, flexCubeModel, list, 0);
    }

    @Override // com.jd.lib.flexcube.layout.floor.banner.common.FlexParentFloor
    public void e(int i2, int i3, Rect rect, Rect rect2) {
        int i4 = rect2.top + rect2.bottom + i3;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams((this.f4274k - rect.left) - rect.right, i4);
        layoutParams.setMargins(rect.left, rect.top, rect.right, rect.bottom);
        this.f4327m.setLayoutParams(layoutParams);
        this.f4275l = rect.top + i4 + rect.bottom;
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    public void initView(String str) {
        this.f4327m.h(str);
    }
}
