package com.tencent.mapsdk.internal;

import android.content.Context;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.interfaces.Collision;
import com.tencent.tencentmap.mapsdk.maps.model.Animation;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptor;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions;

/* loaded from: classes9.dex */
public final class w0 extends v0<o0> implements Marker {

    /* renamed from: g */
    private o0 f17417g;

    public w0(o0 o0Var) {
        this.f17417g = o0Var;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Alphable
    public float getAlpha() {
        return this.f17417g.getAlpha();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Anchorable
    public float getAnchorU() {
        return this.f17417g.getAnchorU();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Anchorable
    public float getAnchorV() {
        return this.f17417g.getAnchorV();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Accessible
    public String getContentDescription() {
        o0 o0Var = this.f17417g;
        if (o0Var != null) {
            return o0Var.getContentDescription();
        }
        return null;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public int getDisplayLevel() {
        return this.f17417g.getDisplayLevel();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Scalable
    public float getEqualScale() {
        o0 o0Var = this.f17417g;
        if (o0Var == null) {
            return 0.0f;
        }
        return o0Var.getEqualScale();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public int getHeight(Context context) {
        o0 o0Var = this.f17417g;
        if (o0Var == null) {
            return 0;
        }
        return o0Var.getHeight(context);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public TencentMap.OnMarkerDragListener getOnDragListener() {
        return this.f17417g.getOnDragListener();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public MarkerOptions getOptions() {
        return this.f17417g.getOptions();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public LatLng getPosition() {
        return this.f17417g.getPosition();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Rotatable
    public float getRotation() {
        o0 o0Var = this.f17417g;
        if (o0Var == null) {
            return 0.0f;
        }
        return o0Var.getRotation();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Scalable
    public float[] getScale() {
        o0 o0Var = this.f17417g;
        return o0Var == null ? new float[]{0.0f, 0.0f} : o0Var.getScale();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public String getSnippet() {
        return this.f17417g.getSnippet();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Tagable
    public Object getTag() {
        return this.f17417g.getTag();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public String getTitle() {
        return this.f17417g.getTitle();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public int getWidth(Context context) {
        o0 o0Var = this.f17417g;
        if (o0Var == null) {
            return 0;
        }
        return o0Var.getWidth(context);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public void hideInfoWindow() {
        o0 o0Var = this.f17417g;
        if (o0Var == null) {
            return;
        }
        o0Var.hideInfoWindow();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Clickable
    public boolean isClickable() {
        o0 o0Var = this.f17417g;
        if (o0Var == null) {
            return false;
        }
        return o0Var.isClickable();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Collisionable
    public boolean isCollisionBy(Collision collision) {
        o0 o0Var = this.f17417g;
        if (o0Var != null) {
            return o0Var.isCollisionBy(collision);
        }
        return false;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Draggable
    public boolean isDraggable() {
        return this.f17417g.isDraggable();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public boolean isFastLoad() {
        o0 o0Var = this.f17417g;
        if (o0Var == null) {
            return false;
        }
        return o0Var.isFastLoad();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public boolean isInMapCenterState() {
        return this.f17417g.isInMapCenterState();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public boolean isInfoWindowAutoOverturn() {
        return this.f17417g.isInfoWindowAutoOverturn();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public boolean isInfoWindowEnable() {
        return this.f17417g.isInfoWindowEnable();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public boolean isInfoWindowShown() {
        o0 o0Var = this.f17417g;
        if (o0Var == null) {
            return false;
        }
        return o0Var.isInfoWindowShown();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public boolean onTapMapViewBubbleHidden() {
        return this.f17417g.onTapMapViewBubbleHidden();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public void refreshInfoWindow() {
        o0 o0Var = this.f17417g;
        if (o0Var == null) {
            return;
        }
        o0Var.refreshInfoWindow();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Alphable
    public void setAlpha(float f2) {
        o0 o0Var = this.f17417g;
        if (o0Var == null) {
            return;
        }
        o0Var.setAlpha(f2);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Anchorable
    public void setAnchor(float f2, float f3) {
        o0 o0Var = this.f17417g;
        if (o0Var == null) {
            return;
        }
        o0Var.setAnchor(f2, f3);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Animationable
    public void setAnimation(Animation animation) {
        o0 o0Var = this.f17417g;
        if (o0Var == null || animation == null) {
            return;
        }
        o0Var.setAnimation(animation);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Clickable
    public void setClickable(boolean z) {
        o0 o0Var = this.f17417g;
        if (o0Var == null) {
            return;
        }
        o0Var.setClickable(z);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Collisionable
    public void setCollisions(Collision... collisionArr) {
        o0 o0Var = this.f17417g;
        if (o0Var != null) {
            o0Var.setCollisions(collisionArr);
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Accessible
    public void setContentDescription(String str) {
        o0 o0Var = this.f17417g;
        if (o0Var != null) {
            o0Var.setContentDescription(str);
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Draggable
    public void setDraggable(boolean z) {
        o0 o0Var = this.f17417g;
        if (o0Var == null) {
            return;
        }
        o0Var.setDraggable(z);
        o0 o0Var2 = this.f17417g;
        o0Var2.setFixingPointEnable(!z && o0Var2.h());
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Scalable
    public void setEqualScale(float f2) {
        o0 o0Var = this.f17417g;
        if (o0Var == null) {
            return;
        }
        o0Var.setEqualScale(f2);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public void setFastLoad(boolean z) {
        this.f17417g.setFastLoad(z);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public void setFixingPoint(int i2, int i3) {
        this.f17417g.setFixingPoint(i2, i3);
        this.f17417g.setDraggable(false);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public void setFixingPointEnable(boolean z) {
        this.f17417g.setFixingPointEnable(z);
        if (this.f17417g.isDraggable()) {
            setDraggable(!z);
        }
        if (z) {
            return;
        }
        o0 o0Var = this.f17417g;
        o0Var.setPosition(o0Var.getPosition());
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public void setIcon(BitmapDescriptor bitmapDescriptor) {
        o0 o0Var = this.f17417g;
        if (o0Var == null) {
            return;
        }
        o0Var.setIcon(bitmapDescriptor);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public void setIconLooper(BitmapDescriptor bitmapDescriptor, boolean z, int i2) {
        this.f17417g.setIconLooper(bitmapDescriptor, z, i2);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public void setInMapCenterState(boolean z) {
        o0 o0Var = this.f17417g;
        if (o0Var == null) {
            return;
        }
        o0Var.setInMapCenterState(z);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public void setInfoWindowAnchor(float f2, float f3) {
        o0 o0Var = this.f17417g;
        if (o0Var == null) {
            return;
        }
        o0Var.setInfoWindowAnchor(f2, f3);
        refreshInfoWindow();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public void setInfoWindowEnable(boolean z) {
        o0 o0Var = this.f17417g;
        if (o0Var == null) {
            return;
        }
        o0Var.setInfoWindowEnable(z);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public void setInfoWindowOffset(int i2, int i3) {
        o0 o0Var = this.f17417g;
        if (o0Var == null) {
            return;
        }
        o0Var.setInfoWindowOffset(i2, i3);
        refreshInfoWindow();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public void setMarkerOptions(MarkerOptions markerOptions) {
        if (markerOptions == null) {
            return;
        }
        this.f17417g.setMarkerOptions(markerOptions);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public void setOnTapMapViewBubbleHidden(boolean z) {
        this.f17417g.c(z);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public void setPosition(LatLng latLng) {
        o0 o0Var = this.f17417g;
        if (o0Var == null || latLng == null) {
            return;
        }
        o0Var.setPosition(latLng);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Rotatable
    public void setRotation(float f2) {
        o0 o0Var = this.f17417g;
        if (o0Var == null) {
            return;
        }
        o0Var.setRotation(f2);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Scalable
    public void setScale(float f2, float f3) {
        o0 o0Var = this.f17417g;
        if (o0Var == null) {
            return;
        }
        o0Var.setScale(f2, f3);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public void setSnippet(String str) {
        o0 o0Var = this.f17417g;
        if (o0Var == null) {
            return;
        }
        o0Var.setSnippet(str);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Tagable
    public void setTag(Object obj) {
        this.f17417g.setTag(obj);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public void setTitle(String str) {
        o0 o0Var = this.f17417g;
        if (o0Var == null) {
            return;
        }
        o0Var.setTitle(str);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public void showInfoWindow() {
        o0 o0Var = this.f17417g;
        if (o0Var == null) {
            return;
        }
        o0Var.showInfoWindow();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Animationable
    public void startAnimation(Animation animation) {
        setAnimation(animation);
        startAnimation();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Animationable
    public boolean startAnimation() {
        o0 o0Var = this.f17417g;
        if (o0Var == null) {
            return false;
        }
        return o0Var.startAnimation();
    }

    @Override // com.tencent.mapsdk.internal.v0
    /* renamed from: y */
    public o0 x() {
        return this.f17417g;
    }
}
