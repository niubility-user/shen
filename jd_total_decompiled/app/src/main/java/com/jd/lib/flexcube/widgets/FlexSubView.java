package com.jd.lib.flexcube.widgets;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.ui.IFloorView;
import com.jd.lib.flexcube.FlexCube;
import com.jd.lib.flexcube.help.FlexAutoPlayInterface;
import com.jd.lib.flexcube.help.MsgActionInterface;
import com.jd.lib.flexcube.help.MsgInterface;
import com.jd.lib.flexcube.iwidget.entity.material.MaterialModel;
import com.jd.lib.flexcube.iwidget.ui.IKnowWidget;
import com.jd.lib.flexcube.iwidget.ui.IWidget;
import com.jd.lib.flexcube.iwidget.ui.IWidgetCommunication;
import com.jd.lib.flexcube.layout.entity.FloorConfig;
import com.jd.lib.flexcube.layout.entity.MaterialGroup;
import com.jd.lib.flexcube.widgets.b.b;
import com.jd.lib.flexcube.widgets.b.c;
import com.jd.lib.flexcube.widgets.entity.SubViewEntity;
import com.jd.lib.flexcube.widgets.entity.subview.SubViewDataPath;
import com.jd.lib.flexcube.widgets.entity.subview.SubViewFlexCubeModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes15.dex */
public class FlexSubView extends ViewGroup implements IWidget<SubViewEntity>, FlexAutoPlayInterface, MsgInterface, IKnowWidget<SubViewEntity> {

    /* renamed from: g  reason: collision with root package name */
    private Context f4479g;

    /* renamed from: h  reason: collision with root package name */
    private float f4480h;

    /* renamed from: i  reason: collision with root package name */
    private SubViewEntity f4481i;

    /* renamed from: j  reason: collision with root package name */
    private BabelScope f4482j;

    /* renamed from: k  reason: collision with root package name */
    private List<IFloorView> f4483k;

    public FlexSubView(Context context) {
        super(context);
        this.f4483k = new ArrayList();
        this.f4479g = context;
    }

    private void b() {
        SubViewFlexCubeModel subViewFlexCubeModel;
        int totalCount;
        SubViewEntity subViewEntity = this.f4481i;
        if (subViewEntity == null || (subViewFlexCubeModel = subViewEntity.flexCubeModel) == null || (totalCount = subViewFlexCubeModel.getTotalCount()) == this.f4483k.size()) {
            return;
        }
        removeAllViews();
        this.f4483k.clear();
        if (totalCount == 0) {
            return;
        }
        for (int i2 = 0; i2 < totalCount; i2++) {
            View view = FlexCube.getView(this.f4479g, subViewFlexCubeModel.getId());
            if ((view instanceof IFloorView) && c.a(this.f4481i, this.f4480h) != null) {
                IFloorView iFloorView = (IFloorView) view;
                iFloorView.initView(subViewFlexCubeModel.getId());
                addView(view);
                this.f4483k.add(iFloorView);
            }
        }
    }

    private int[] c() {
        int i2;
        int i3;
        int childCount = getChildCount();
        if (childCount > 0) {
            i2 = 0;
            i3 = 0;
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = getChildAt(i4);
                i2 += childAt.getMeasuredHeight();
                i3 = Math.max(i3, childAt.getMeasuredWidth());
            }
        } else {
            i2 = 0;
            i3 = 0;
        }
        return new int[]{i3, i2};
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IKnowWidget
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public SubViewEntity getWidgetEntity() {
        return this.f4481i;
    }

    @Override // com.jd.lib.flexcube.help.FlexAutoPlayInterface
    public boolean autoPlay(boolean z, boolean z2) {
        boolean z3 = false;
        if (this.f4483k.size() > 0) {
            for (IFloorView iFloorView : this.f4483k) {
                if ((iFloorView instanceof FlexAutoPlayInterface) && (z3 = ((FlexAutoPlayInterface) iFloorView).autoPlay(z, z2))) {
                    break;
                }
            }
        }
        return z3;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void clear() {
        removeAllViews();
        this.f4483k.clear();
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    /* renamed from: d  reason: merged with bridge method [inline-methods] */
    public void updateStyle(@NonNull SubViewEntity subViewEntity, float f2) {
        this.f4480h = f2;
        this.f4481i = subViewEntity;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsHeight() {
        return -2;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsWidth() {
        return -2;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int childCount = getChildCount();
        if (childCount > 0) {
            int i6 = 0;
            int i7 = 0;
            while (i6 < childCount) {
                View childAt = getChildAt(i6);
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight() + i7;
                childAt.layout(0, i7, measuredWidth, measuredHeight);
                i6++;
                i7 = measuredHeight;
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        measureChildren(i2, i3);
        int[] c2 = c();
        setMeasuredDimension(c2[0], c2[1]);
    }

    @Override // com.jd.lib.flexcube.help.MsgInterface
    public void pushAction(Class<? extends MsgActionInterface> cls, Object obj) {
        List<IFloorView> list = this.f4483k;
        if (list == null) {
            return;
        }
        for (IFloorView iFloorView : list) {
            if (iFloorView instanceof MsgInterface) {
                ((MsgInterface) iFloorView).pushAction(cls, obj);
            }
        }
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void updateContent(@NonNull Map<String, String> map, IWidgetCommunication iWidgetCommunication) {
        SubViewEntity subViewEntity;
        if (iWidgetCommunication != null && iWidgetCommunication.getBabelScope() != null && (subViewEntity = this.f4481i) != null && subViewEntity.flexCubeModel != null) {
            this.f4482j = iWidgetCommunication.getBabelScope();
            SubViewFlexCubeModel subViewFlexCubeModel = this.f4481i.flexCubeModel;
            SubViewDataPath subViewDataPath = subViewFlexCubeModel.dataPath;
            if (subViewDataPath != null) {
                List<MaterialModel> e2 = b.e(map, subViewDataPath.dataList);
                if (e2 != null) {
                    subViewFlexCubeModel.handleData();
                    if (subViewFlexCubeModel.floorConfig != null) {
                        subViewFlexCubeModel.setFlexCubeWidth((int) (this.f4480h * r0.w));
                        if (FlexCube.FLATVIEW.equals(subViewFlexCubeModel.floorConfig.style)) {
                            FloorConfig floorConfig = subViewFlexCubeModel.floorConfig;
                            int i2 = floorConfig.rows;
                            int i3 = floorConfig.columns;
                            if (i2 != 0 && i3 != 0) {
                                int i4 = i2 * i3;
                                if (e2.size() > i4) {
                                    e2 = e2.subList(0, i4);
                                }
                            } else {
                                clear();
                                return;
                            }
                        }
                    }
                    subViewFlexCubeModel.materialGroupList = new ArrayList(1);
                    MaterialGroup materialGroup = new MaterialGroup();
                    materialGroup.groupInfoList = e2;
                    subViewFlexCubeModel.materialGroupList.add(materialGroup);
                    b();
                } else {
                    clear();
                }
            } else {
                clear();
            }
            if (this.f4483k.size() > 0) {
                for (int i5 = 0; i5 < this.f4483k.size(); i5++) {
                    this.f4483k.get(i5).update(this.f4482j, (BabelScope) subViewFlexCubeModel, i5);
                }
                return;
            }
            return;
        }
        clear();
    }
}
