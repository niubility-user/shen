package com.jd.lib.flexcube.layout.floor.scroll;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.ui.IFloorView;
import com.jd.lib.flexcube.iwidget.b.a;
import com.jd.lib.flexcube.iwidget.b.b;
import com.jd.lib.flexcube.layout.entity.FlexCubeModel;
import com.jd.lib.flexcube.layout.entity.FloorConfig;
import com.jd.lib.flexcube.layout.entity.common.ScrollConfig;

/* loaded from: classes14.dex */
public class FlexScrollBar extends FrameLayout implements IFloorView<FlexCubeModel> {

    /* renamed from: g  reason: collision with root package name */
    private Context f4392g;

    /* renamed from: h  reason: collision with root package name */
    private float f4393h;

    /* renamed from: i  reason: collision with root package name */
    private View f4394i;

    public FlexScrollBar(Context context, @NonNull FlexCubeModel flexCubeModel) {
        super(context);
        FloorConfig floorConfig;
        ScrollConfig scrollConfig;
        this.f4392g = context;
        if (flexCubeModel == null || (floorConfig = flexCubeModel.floorConfig) == null || (scrollConfig = floorConfig.scrollConfig) == null || !"1".equals(scrollConfig.showBar)) {
            setLayoutParams(new FrameLayout.LayoutParams(-1, 0));
        }
    }

    private GradientDrawable a(String str) {
        int a = a.a(str, a.b("#FF445E", 0));
        float d = b.d(6, this.f4393h);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadii(new float[]{d, d, d, d, d, d, d, d});
        gradientDrawable.setColor(a);
        return gradientDrawable;
    }

    public View b() {
        return this.f4394i;
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    /* renamed from: c  reason: merged with bridge method [inline-methods] */
    public void update(@NonNull BabelScope babelScope, @NonNull FlexCubeModel flexCubeModel, int i2) {
        FloorConfig floorConfig = flexCubeModel.floorConfig;
        if (floorConfig == null) {
            return;
        }
        ScrollConfig scrollConfig = floorConfig.scrollConfig;
        this.f4393h = flexCubeModel.getMultiple();
        if (scrollConfig == null) {
            return;
        }
        removeAllViews();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, b.d(54, this.f4393h));
        layoutParams.gravity = 80;
        setLayoutParams(layoutParams);
        setPadding(b.d(18, this.f4393h), 0, b.d(18, this.f4393h), 0);
        setBackgroundColor(a.a(scrollConfig.barBgColor, 0));
        View view = new View(this.f4392g);
        view.setBackgroundColor(a.a(scrollConfig.barColor, Color.argb(76, 255, 68, 94)));
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, b.d(3, this.f4393h));
        layoutParams2.topMargin = b.d(21, this.f4393h);
        addView(view, layoutParams2);
        View view2 = new View(this.f4392g);
        this.f4394i = view2;
        view2.setBackground(a(scrollConfig.barBlockColor));
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(b.d(105, this.f4393h), b.d(9, this.f4393h));
        layoutParams3.topMargin = b.d(18, this.f4393h);
        addView(this.f4394i, layoutParams3);
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    public void initView(String str) {
    }
}
