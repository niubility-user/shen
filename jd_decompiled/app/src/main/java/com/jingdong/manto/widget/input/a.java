package com.jingdong.manto.widget.input;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes16.dex */
public abstract class a extends y {
    public a(Context context) {
        super(context);
        super.setHorizontallyScrolling(true);
    }

    @Override // com.jingdong.manto.widget.input.z.d
    public final boolean a() {
        return false;
    }

    @Override // android.view.View
    public boolean canScrollVertically(int i2) {
        return false;
    }

    @Override // com.jingdong.manto.widget.input.y, com.jingdong.manto.widget.input.z.d
    public final boolean e() {
        return false;
    }

    @Override // android.widget.TextView
    public void setGravity(int i2) {
        super.setGravity((i2 & (-81) & (-49)) | 16);
    }

    @Override // com.jingdong.manto.widget.input.y, android.widget.TextView
    public final void setInputType(int i2) {
        super.setInputType(i2 & (-131073));
    }

    @Override // com.jingdong.manto.widget.input.y, android.widget.TextView
    public final void setSingleLine(boolean z) {
    }
}
