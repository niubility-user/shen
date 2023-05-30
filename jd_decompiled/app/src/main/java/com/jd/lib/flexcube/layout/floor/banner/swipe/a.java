package com.jd.lib.flexcube.layout.floor.banner.swipe;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.flexcube.canvas.FlexViewGroup;
import com.jd.lib.flexcube.iwidget.entity.material.MaterialModel;
import com.jd.lib.flexcube.layout.entity.FlexCubeModel;
import com.jd.lib.flexcube.layout.floor.banner.common.FlexViewGroupHolder;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class a extends BaseAdapter {

    /* renamed from: g */
    private List<MaterialModel> f4336g;

    /* renamed from: h */
    private BabelScope f4337h;

    /* renamed from: i */
    private FlexCubeModel f4338i;

    /* renamed from: j */
    private FrameLayout.LayoutParams f4339j;

    public MaterialModel a() {
        if (getCount() > 0) {
            return this.f4336g.get(0);
        }
        return null;
    }

    public void b(FlexViewGroupHolder flexViewGroupHolder, int i2) {
        if (i2 >= this.f4336g.size()) {
            return;
        }
        FlexCubeModel flexCubeModel = this.f4338i;
        if (flexCubeModel != null) {
            float multiple = flexCubeModel.getMultiple();
            FlexViewGroup flexViewGroup = flexViewGroupHolder.a;
            if (multiple != flexViewGroup.f4221m) {
                FlexCubeModel flexCubeModel2 = this.f4338i;
                flexViewGroup.a(flexCubeModel2.widgetList, flexCubeModel2.getMultiple(), this.f4338i.canvasConfig.getUuid());
                flexViewGroupHolder.a.setLayoutParams(this.f4339j);
            }
        }
        FlexViewGroup flexViewGroup2 = flexViewGroupHolder.a;
        BabelScope babelScope = this.f4337h;
        FlexCubeModel flexCubeModel3 = this.f4338i;
        flexViewGroup2.i(babelScope, flexCubeModel3 == null ? null : flexCubeModel3.canvasConfig, flexCubeModel3 == null ? 1.0f : flexCubeModel3.getMultiple());
        flexViewGroupHolder.a.h(this.f4336g.get(i2), this.f4337h);
    }

    public FlexViewGroupHolder c(ViewGroup viewGroup, int i2, FlexCubeModel flexCubeModel) {
        FlexViewGroup flexViewGroup = new FlexViewGroup(viewGroup.getContext());
        flexViewGroup.a(flexCubeModel.widgetList, flexCubeModel.getMultiple(), flexCubeModel.canvasConfig.getUuid());
        flexViewGroup.f(false);
        flexViewGroup.setLayoutParams(this.f4339j);
        return new FlexViewGroupHolder(flexViewGroup);
    }

    public void d() {
        if (getCount() <= 1) {
            return;
        }
        this.f4336g.add(0, this.f4336g.remove(getCount() - 1));
        notifyDataSetChanged();
    }

    public void e() {
        if (getCount() <= 1) {
            return;
        }
        this.f4336g.add(this.f4336g.remove(0));
        notifyDataSetChanged();
    }

    public void f(List<MaterialModel> list) {
        if (this.f4336g == null) {
            this.f4336g = new ArrayList();
        }
        this.f4336g.clear();
        this.f4336g.addAll(list);
        int size = this.f4336g.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.f4336g.get(i2).p_position = i2;
        }
    }

    public void g(BabelScope babelScope, FlexCubeModel flexCubeModel) {
        this.f4337h = babelScope;
        this.f4338i = flexCubeModel;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<MaterialModel> list = this.f4336g;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        List<MaterialModel> list = this.f4336g;
        if (list != null) {
            return list.get(i2);
        }
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return 0L;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i2) {
        if (i2 < 0 || i2 >= getCount()) {
            return -1;
        }
        return this.f4336g.get(i2).p_position;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        View view2;
        FlexViewGroupHolder flexViewGroupHolder;
        int itemViewType = getItemViewType(i2);
        if (view == null) {
            flexViewGroupHolder = c(viewGroup, itemViewType, this.f4338i);
            view2 = flexViewGroupHolder.itemView;
            view2.setTag(flexViewGroupHolder);
        } else {
            view2 = view;
            flexViewGroupHolder = (FlexViewGroupHolder) view.getTag();
        }
        b(flexViewGroupHolder, i2);
        return view2;
    }

    public void h(FrameLayout.LayoutParams layoutParams) {
        this.f4339j = layoutParams;
    }
}
