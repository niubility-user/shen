package com.jingdong.app.mall.home.floor.presenter.engine;

import android.graphics.Point;
import android.graphics.Rect;
import android.text.TextUtils;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.model.entity.FloorEntity;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.app.mall.home.state.dark.a;
import com.jingdong.common.utils.StringUtil;

/* loaded from: classes4.dex */
public class FloorEngine<E extends FloorEntity> {
    private volatile String a = "";

    private Point a(String str, int i2) {
        int i3;
        Point r = m.r(str);
        int i4 = r.x;
        if (i4 <= 0 || (i3 = r.y) <= 0) {
            return null;
        }
        float f2 = (i2 / i3) * i4;
        float f3 = d.f9279g;
        if (Math.abs(f2 - f3) <= 10.0f) {
            f2 = f3;
        }
        return new Point((int) f2, i2);
    }

    private void g(h hVar, com.jingdong.app.mall.home.r.e.d dVar, E e2) {
        int i2 = hVar.b;
        if (i2 < 0) {
            i2 = 0;
        }
        int d = d.d(i2);
        if (hVar.f10694f > 0 && d <= 0) {
            d = 2;
        }
        e2.setBottomDividerHeight(d);
        e2.setDividerColor(hVar.m() ? 0 : hVar.w[0]);
        f(hVar, dVar, e2, hVar.e());
    }

    public String b() {
        return this.a;
    }

    public void c(com.jingdong.app.mall.home.r.e.d dVar, E e2) {
        if (dVar == null || e2 == null) {
            return;
        }
        e2.mLayoutHeight = dVar.mFloorHeight;
    }

    public void d(h hVar, E e2) {
        if (hVar == null || e2 == null) {
            return;
        }
        e2.setShapedFloorRadii(m.h(hVar.u));
    }

    public void e(h hVar, com.jingdong.app.mall.home.r.e.d dVar, E e2) {
        if (e2 == null || hVar == null) {
            return;
        }
        c(dVar, e2);
        e2.setDataFromCache(hVar.Z);
        e2.setFloorId(hVar.A);
        e2.setFloorPos(hVar.f10696h);
        e2.setIsShowTitle("1".equals(hVar.f10700l));
        e2.setIsSeparationTitle("1".equals(hVar.f10701m));
        String str = hVar.f10702n;
        if (e2.isShowTitle() && e2.isSeparationTitle() && !StringUtil.isEmpty(str)) {
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < str.length(); i2++) {
                if (i2 != 0) {
                    sb.append(' ');
                }
                sb.append(str.charAt(i2));
            }
            str = sb.toString();
        }
        e2.setTitleText(str);
        int i3 = hVar.H;
        String[] strArr = hVar.K;
        boolean z = strArr != null && strArr.length > 0;
        if (i3 == 1 && z) {
            e2.setHasCloseButton(true);
            e2.setCloseButtonImg(hVar.I);
            e2.setCloseReason(hVar.K);
            e2.setCloseTips(hVar.L);
            e2.setCloseLog(hVar.J);
            e2.setHasRightCorner(false);
        } else {
            e2.setHasCloseButton(false);
            String str2 = hVar.r;
            e2.setHasRightCorner(!TextUtils.isEmpty(str2));
            e2.setRightCornerText(str2);
        }
        String str3 = hVar.p;
        e2.setTitleImgUrl(a.h() ? hVar.q : str3);
        e2.setTitleImgSize(a(str3, e2.getTitleImgDefaultHeight()));
        e2.setRightCornerArrowColor(m.t(hVar.o));
        e2.setRightCornerTextColor(m.j(hVar.s, -16777216));
        e2.setRightCornerArrowImgUrl(hVar.t);
        int[] p = m.p(a.h() ? JDDarkUtil.COLOR_ECECEC : hVar.o, -16777216, false);
        if (e2.isSeparationTitle() && p == null) {
            p = FloorEntity.SEPARATION_TITLE_TEXT_COLOR_DEFAULT;
        }
        e2.setTitleTextColor(p);
        if (dVar.useRoundBg) {
            d(hVar, e2);
        }
        g(hVar, dVar, e2);
    }

    protected void f(h hVar, com.jingdong.app.mall.home.r.e.d dVar, E e2, int i2) {
        int i3;
        Rect rect;
        if (hVar.f10695g > 0) {
            i3 = d.d(hVar.f10693e);
            if (i3 == 0) {
                i3 = 2;
            }
        } else {
            i3 = 0;
        }
        e2.setItemDividerWidth(i3);
        e2.setItemDividerColor(m.j(hVar.v, i2));
        if (dVar != null && (rect = dVar.mPaddingRect) != null) {
            e2.setLayoutLeftRightMargin(rect.left);
        } else {
            e2.setLayoutLeftRightMarginBy750Design(hVar.d);
        }
    }

    public void h(String str) {
        this.a = str;
    }
}
