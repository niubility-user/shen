package com.jingdong.app.mall.home.floor.view.view.module;

import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.i.p;
import com.jingdong.app.mall.home.floor.common.i.r;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class ModuleCommonFunc {
    public static int getSeparationItemImgsGap(int i2, int i3) {
        return i2 == d.d(30) ? d.d((int) (30.0f - (((374.0f - ((i3 * R2.attr.decimalNumber) / d.f9279g)) * 10.0f) / 22.0f))) : i2;
    }

    protected static MallFloorModuleCommon getSeparationItemLayout(BaseMallFloor<?> baseMallFloor, r rVar, p.a aVar, int i2, int i3, int i4, int i5, f fVar) {
        int saveKey = aVar.X.getSaveKey(rVar.getSaveKey(baseMallFloor.getRealItemIndex(i2)));
        MallFloorModuleCommon mallFloorModuleCommon = baseMallFloor.mModelViewCache.get(saveKey);
        if (mallFloorModuleCommon == null) {
            mallFloorModuleCommon = rVar.getModelView(baseMallFloor);
            baseMallFloor.mModelViewCache.append(saveKey, mallFloorModuleCommon);
        }
        ViewGroup.LayoutParams layoutParams = mallFloorModuleCommon.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.width = i3;
            layoutParams2.height = i4;
        } else {
            mallFloorModuleCommon.setLayoutParams(new RelativeLayout.LayoutParams(i3, i4));
        }
        mallFloorModuleCommon.setBackgroundColor(i5);
        mallFloorModuleCommon.setContentDescription(fVar.O());
        return mallFloorModuleCommon;
    }

    public static Object initSeparationFloorViewItemOnMainThread(BaseMallFloor<?> baseMallFloor, com.jingdong.app.mall.home.r.e.d dVar, r rVar, f fVar, p.a aVar, int i2, int i3, int i4, Object obj) {
        p.a.C0288a c0288a;
        if (baseMallFloor == null || (c0288a = aVar.Y) == null || c0288a.f9349i == 0) {
            return null;
        }
        MallFloorModuleCommon separationItemLayout = getSeparationItemLayout(baseMallFloor, rVar, aVar, i4, i2, i3, dVar.useRoundBg ? 0 : c0288a.f9350j, fVar);
        separationItemLayout.setRejectDowngrading(aVar.x);
        baseMallFloor.doOtherWithSeparationItemLayout(aVar, c0288a, separationItemLayout, i4);
        aVar.X.drawModuleItem(dVar, separationItemLayout, fVar, aVar, baseMallFloor, rVar, R.id.mallfloor_item1 - 1, i2, i4);
        return baseMallFloor.setItemPosAndAddItem(separationItemLayout, fVar, i4, i2, obj);
    }
}
