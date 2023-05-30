package com.tencent.mapsdk.internal;

import android.graphics.Rect;
import com.tencent.mapsdk.internal.p0;
import com.tencent.tencentmap.mapsdk.maps.interfaces.Boundable;
import com.tencent.tencentmap.mapsdk.maps.interfaces.Selectable;
import java.util.List;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes9.dex */
public abstract class v0<D extends p0> implements t4 {
    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Boundable
    /* renamed from: a */
    public Rect getBound(s4 s4Var) {
        return x().getBound(s4Var);
    }

    @Override // com.tencent.mapsdk.internal.n4
    public void a(GL10 gl10) {
        x().a(gl10);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Boundable
    /* renamed from: b */
    public Rect getScreenBound(s4 s4Var) {
        return x().getScreenBound(s4Var);
    }

    public List<Boundable<s4>> getGroupBounds() {
        return x().getGroupBounds();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.IOverlay
    public String getId() {
        return x().getId();
    }

    public int getLevel() {
        return x().getLevel();
    }

    public int getZIndex() {
        return x().getZIndex();
    }

    public boolean handleOnTap() {
        return x().handleOnTap();
    }

    public boolean isRemoved() {
        return x().isRemoved();
    }

    public boolean isSelected() {
        return x().isSelected();
    }

    public boolean isVisible() {
        return x().isVisible();
    }

    public boolean onTap(float f2, float f3) {
        return x().onTap(f2, f3);
    }

    public void releaseData() {
        x().releaseData();
    }

    public void remove() {
        x().remove();
    }

    public void setLevel(int i2) {
        x().setLevel(i2);
    }

    public void setSelected(boolean z) {
        x().setSelected(z);
    }

    public <T> void setSelectedListener(Selectable.OnSelectedListener<T> onSelectedListener) {
        x().setSelectedListener(onSelectedListener);
    }

    public void setVisible(boolean z) {
        x().setVisible(z);
    }

    public void setZIndex(float f2) {
        setZIndex((int) f2);
    }

    public void setZIndex(int i2) {
        x().setZIndex(i2);
    }

    public abstract D x();
}
