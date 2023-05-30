package com.jd.lib.cashier.sdk.core.ui;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.d.b.a;

/* loaded from: classes14.dex */
public class CashierRegulatorItemView extends CashierItemView {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Rect rect;
            TextView textView = CashierRegulatorItemView.this.p;
            if (textView != null && textView.getVisibility() == 0) {
                rect = new Rect();
                CashierRegulatorItemView.this.p.getDrawingRect(rect);
                CashierRegulatorItemView cashierRegulatorItemView = CashierRegulatorItemView.this;
                cashierRegulatorItemView.offsetDescendantRectToMyCoords(cashierRegulatorItemView.p, rect);
            } else {
                TextView textView2 = CashierRegulatorItemView.this.q;
                if (textView2 == null || textView2.getVisibility() != 0) {
                    rect = null;
                } else {
                    rect = new Rect();
                    CashierRegulatorItemView.this.q.getDrawingRect(rect);
                    CashierRegulatorItemView cashierRegulatorItemView2 = CashierRegulatorItemView.this;
                    cashierRegulatorItemView2.offsetDescendantRectToMyCoords(cashierRegulatorItemView2.q, rect);
                }
            }
            if (rect == null) {
                return;
            }
            ImageView imageView = CashierRegulatorItemView.this.o;
            if (imageView != null && imageView.getVisibility() == 0) {
                Rect rect2 = new Rect();
                CashierRegulatorItemView.this.o.getDrawingRect(rect2);
                CashierRegulatorItemView cashierRegulatorItemView3 = CashierRegulatorItemView.this;
                cashierRegulatorItemView3.offsetDescendantRectToMyCoords(cashierRegulatorItemView3.o, rect2);
                if (rect2.intersect(rect)) {
                    CashierRegulatorItemView.this.o.setVisibility(8);
                    CashierRegulatorItemView.this.S();
                    return;
                }
                return;
            }
            ImageView imageView2 = CashierRegulatorItemView.this.f2991n;
            if (imageView2 == null || imageView2.getVisibility() != 0) {
                return;
            }
            Rect rect3 = new Rect();
            CashierRegulatorItemView.this.f2991n.getDrawingRect(rect3);
            CashierRegulatorItemView cashierRegulatorItemView4 = CashierRegulatorItemView.this;
            cashierRegulatorItemView4.offsetDescendantRectToMyCoords(cashierRegulatorItemView4.f2991n, rect3);
            if (rect3.intersect(rect)) {
                CashierRegulatorItemView.this.f2991n.setVisibility(8);
            }
        }
    }

    public CashierRegulatorItemView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void S() {
        post(new a());
    }

    public void T(a.b bVar) {
        RelativeLayout relativeLayout = this.f2984g;
        if (relativeLayout != null) {
            if (a.b.NORMAL == bVar) {
                relativeLayout.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
            } else if (a.b.FLOOR_BOTTOM == bVar) {
                if (JDDarkUtil.isDarkMode()) {
                    this.f2984g.setBackgroundResource(R.drawable.lib_cashier_sdk_regulator_bottom_corner_dark_bg);
                } else {
                    this.f2984g.setBackgroundResource(R.drawable.lib_cashier_sdk_regulator_bottom_corner_bg);
                }
            } else if (a.b.FLOOR_TOP_AND_NORMAL == bVar) {
                if (JDDarkUtil.isDarkMode()) {
                    this.f2984g.setBackgroundResource(R.drawable.lib_cashier_sdk_regulator_top_corner_dark_bg);
                } else {
                    this.f2984g.setBackgroundResource(R.drawable.lib_cashier_sdk_regulator_top_corner_bg);
                }
            } else if (a.b.FLOOR_TOP_AND_BOTTOM == bVar) {
                if (JDDarkUtil.isDarkMode()) {
                    this.f2984g.setBackgroundResource(R.drawable.lib_cashier_sdk_regulator_top_bottom_corner_dark_bg);
                } else {
                    this.f2984g.setBackgroundResource(R.drawable.lib_cashier_sdk_regulator_top_bottom_corner_bg);
                }
            }
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.CashierItemView
    public int b() {
        return R.layout.lib_cashier_sdk_regulator_item_pay_view;
    }

    public CashierRegulatorItemView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
