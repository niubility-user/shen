package com.jd.lib.un.basewidget.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import com.jd.lib.un.global.GlobalThemeController;
import com.jd.lib.un.global.IThemeChange;

/* loaded from: classes16.dex */
public class SampleButton extends Button implements IThemeChange {

    /* renamed from: g  reason: collision with root package name */
    private GlobalThemeController f5684g;

    public SampleButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        GlobalThemeController newInstance = GlobalThemeController.newInstance();
        this.f5684g = newInstance;
        if (newInstance.isCustomTheme()) {
            customTheme();
        }
    }

    @Override // com.jd.lib.un.global.IThemeChange
    public void customTheme() {
        if (this.f5684g.getThemeConfig().b() != null) {
            setBackgroundDrawable(this.f5684g.getThemeConfig().b());
        }
        setTextColor(this.f5684g.getThemeConfig().d());
    }

    public SampleButton(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a();
    }
}
