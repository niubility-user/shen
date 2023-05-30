package com.jd.lib.flexcube.layout.floor.banner.vertical.view;

import android.content.Context;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.flexcube.canvas.FlexViewGroup;
import com.jd.lib.flexcube.canvas.entity.CanvasConfig;
import com.jd.lib.flexcube.help.FlexAutoPlayInterface;
import com.jd.lib.flexcube.iwidget.b.b;
import com.jd.lib.flexcube.iwidget.entity.material.MaterialModel;
import com.jd.lib.flexcube.layout.entity.FlexCubeModel;
import com.jd.lib.flexcube.layout.entity.FloorConfig;
import com.jd.lib.flexcube.layout.entity.common.FloorStyle;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class FlexVerticalBannerRow extends LinearLayout implements FlexAutoPlayInterface {
    private List<FlexViewGroup> flexViewGroupList;
    private BabelScope mBabelScope;
    public int mColumns;
    private Context mContext;
    private FlexCubeModel mFlexCubeModel;
    private float mMultiple;
    public String md5;

    public FlexVerticalBannerRow(Context context, int i2, String str, @NonNull BabelScope babelScope, @NonNull FlexCubeModel flexCubeModel) {
        super(context);
        setOrientation(0);
        this.mContext = context;
        this.mColumns = i2;
        this.md5 = str;
        this.mBabelScope = babelScope;
        this.mFlexCubeModel = flexCubeModel;
        this.flexViewGroupList = new ArrayList();
        initView();
    }

    private void clearView() {
        removeAllViews();
        this.flexViewGroupList.clear();
    }

    private void initView() {
        FlexCubeModel flexCubeModel;
        int i2;
        int i3;
        FloorStyle floorStyle;
        clearView();
        if (this.mBabelScope == null || (flexCubeModel = this.mFlexCubeModel) == null) {
            return;
        }
        CanvasConfig canvasConfig = flexCubeModel.canvasConfig;
        if (canvasConfig != null) {
            i2 = b.d(canvasConfig.w, flexCubeModel.getMultiple());
            FlexCubeModel flexCubeModel2 = this.mFlexCubeModel;
            i3 = b.d(flexCubeModel2.canvasConfig.f4223h, flexCubeModel2.getMultiple());
        } else {
            i2 = 0;
            i3 = 0;
        }
        FlexCubeModel flexCubeModel3 = this.mFlexCubeModel;
        FloorConfig floorConfig = flexCubeModel3.floorConfig;
        int d = (floorConfig == null || (floorStyle = floorConfig.floorStyle) == null) ? 0 : b.d(floorStyle.cardHPadding, flexCubeModel3.getMultiple());
        for (int i4 = 0; i4 < this.mColumns; i4++) {
            FlexViewGroup flexViewGroup = new FlexViewGroup(this.mContext);
            FlexCubeModel flexCubeModel4 = this.mFlexCubeModel;
            flexViewGroup.a(flexCubeModel4.widgetList, flexCubeModel4.getMultiple(), this.md5);
            BabelScope babelScope = this.mBabelScope;
            FlexCubeModel flexCubeModel5 = this.mFlexCubeModel;
            flexViewGroup.i(babelScope, flexCubeModel5.canvasConfig, flexCubeModel5.getMultiple());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i2, i3);
            if (i4 > 0) {
                layoutParams.leftMargin = d;
            }
            flexViewGroup.setLayoutParams(layoutParams);
            addView(flexViewGroup);
            this.flexViewGroupList.add(flexViewGroup);
        }
    }

    private void updataLayout() {
        int i2;
        int i3;
        FloorStyle floorStyle;
        try {
            FlexCubeModel flexCubeModel = this.mFlexCubeModel;
            CanvasConfig canvasConfig = flexCubeModel.canvasConfig;
            if (canvasConfig != null) {
                i2 = b.d(canvasConfig.w, flexCubeModel.getMultiple());
                FlexCubeModel flexCubeModel2 = this.mFlexCubeModel;
                i3 = b.d(flexCubeModel2.canvasConfig.f4223h, flexCubeModel2.getMultiple());
            } else {
                i2 = 0;
                i3 = 0;
            }
            FlexCubeModel flexCubeModel3 = this.mFlexCubeModel;
            FloorConfig floorConfig = flexCubeModel3.floorConfig;
            int d = (floorConfig == null || (floorStyle = floorConfig.floorStyle) == null) ? 0 : b.d(floorStyle.cardHPadding, flexCubeModel3.getMultiple());
            for (int i4 = 0; i4 < this.flexViewGroupList.size(); i4++) {
                FlexViewGroup flexViewGroup = this.flexViewGroupList.get(i4);
                FlexCubeModel flexCubeModel4 = this.mFlexCubeModel;
                flexViewGroup.a(flexCubeModel4.widgetList, flexCubeModel4.getMultiple(), this.md5);
                BabelScope babelScope = this.mBabelScope;
                FlexCubeModel flexCubeModel5 = this.mFlexCubeModel;
                flexViewGroup.i(babelScope, flexCubeModel5.canvasConfig, flexCubeModel5.getMultiple());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i2, i3);
                if (i4 > 0) {
                    layoutParams.leftMargin = d;
                }
                flexViewGroup.setLayoutParams(layoutParams);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.jd.lib.flexcube.help.FlexAutoPlayInterface
    public boolean autoPlay(boolean z, boolean z2) {
        return false;
    }

    public void updata(List<MaterialModel> list) {
        if (list != null && list.size() == this.mColumns && list.size() == this.flexViewGroupList.size()) {
            FlexCubeModel flexCubeModel = this.mFlexCubeModel;
            if (flexCubeModel != null) {
                if (flexCubeModel.getMultiple() != this.mMultiple) {
                    updataLayout();
                }
                this.mMultiple = this.mFlexCubeModel.getMultiple();
            }
            for (int i2 = 0; i2 < list.size(); i2++) {
                this.flexViewGroupList.get(i2).h(list.get(i2), this.mBabelScope);
            }
        }
    }

    public void updateView(int i2, String str, @NonNull FlexCubeModel flexCubeModel) {
        if (this.mColumns == i2 && this.md5 == str && this.mFlexCubeModel == flexCubeModel) {
            return;
        }
        this.mColumns = i2;
        this.md5 = str;
        this.mFlexCubeModel = flexCubeModel;
        initView();
    }
}
