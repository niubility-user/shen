package com.tencent.tencentmap.mapsdk.maps.model;

import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;
import com.tencent.tencentmap.mapsdk.maps.model.IAnimatorModel;
import com.tencent.tencentmap.mapsdk.maps.model.transform.OverlayAnimator;
import com.tencent.tencentmap.mapsdk.maps.model.transform.Point;
import com.tencent.tencentmap.mapsdk.maps.model.transform.SphericalMercatorProjection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class GeneralTranslateAnimator extends OverlayAnimator {
    private List<IAnimatorModel.IAnimatorEndListener> mAnimatorEndListeners;
    private final IAnimatorModel mAnimatorModel;
    private double[] mDistances;
    private SphericalMercatorProjection mEarthMercatorProjection;
    private LatLng[] mLatLngs;
    private RotateAnimationController mRotateAnimationController;
    private double mSumDistance;

    /* loaded from: classes9.dex */
    public static class Builder {
        private final IAnimatorModel animatorModel;
        private final long duration;
        private final LatLng[] latLngs;
        private boolean rotateEnabled = false;
        private float initRotate = 0.0f;
        private ModelType modelType = ModelType.MARKER_OVERLAY;

        public Builder(IAnimatorModel iAnimatorModel, long j2, LatLng[] latLngArr) {
            this.animatorModel = iAnimatorModel;
            this.duration = j2;
            this.latLngs = latLngArr;
        }

        public GeneralTranslateAnimator build() {
            return new GeneralTranslateAnimator(this);
        }

        public Builder initRotate(float f2) {
            this.initRotate = f2;
            return this;
        }

        public Builder modelType(ModelType modelType) {
            this.modelType = modelType;
            return this;
        }

        public Builder rotateEnabled(boolean z) {
            this.rotateEnabled = z;
            return this;
        }
    }

    /* loaded from: classes9.dex */
    public enum ModelType {
        MARKER_OVERLAY,
        MODEL_OVERLAY
    }

    /* loaded from: classes9.dex */
    public class a implements ValueAnimator.AnimatorUpdateListener {
        public final /* synthetic */ Point a;
        public final /* synthetic */ Point b;

        /* renamed from: c */
        public final /* synthetic */ int f17999c;

        public a(Point point2, Point point3, int i2) {
            GeneralTranslateAnimator.this = r1;
            this.a = point2;
            this.b = point3;
            this.f17999c = i2;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (this.a.equals(this.b)) {
                return;
            }
            double parseDouble = Double.parseDouble(String.valueOf(valueAnimator.getAnimatedValue()));
            double d = this.a.x;
            double d2 = d + (((this.b.x - d) * parseDouble) / GeneralTranslateAnimator.this.mDistances[this.f17999c]);
            double d3 = this.a.y;
            double d4 = d3 + (((this.b.y - d3) * parseDouble) / GeneralTranslateAnimator.this.mDistances[this.f17999c]);
            if (GeneralTranslateAnimator.this.mAnimatorModel == null) {
                return;
            }
            GeneralTranslateAnimator.this.mAnimatorModel.setPosition(GeneralTranslateAnimator.this.mEarthMercatorProjection.toLatLng(new Point(d2, d4)));
        }
    }

    public GeneralTranslateAnimator(Builder builder) {
        super(builder.animatorModel, builder.duration);
        this.mAnimatorModel = builder.animatorModel;
        if (builder.latLngs == null || builder.latLngs.length <= 0 || builder.duration < 0) {
            return;
        }
        this.mLatLngs = builder.latLngs;
        this.mAnimatorEndListeners = new ArrayList();
        this.mEarthMercatorProjection = new SphericalMercatorProjection();
        ArrayList arrayList = new ArrayList();
        this.mDistances = new double[this.mLatLngs.length - 1];
        int i2 = 0;
        while (true) {
            LatLng[] latLngArr = this.mLatLngs;
            if (i2 >= latLngArr.length - 1) {
                break;
            }
            int i3 = i2 + 1;
            this.mDistances[i2] = this.mEarthMercatorProjection.distanceBetween(latLngArr[i2], latLngArr[i3]);
            this.mSumDistance += this.mDistances[i2];
            i2 = i3;
        }
        for (int i4 = 0; i4 < this.mLatLngs.length - 1; i4++) {
            arrayList.add(createSegmentAnimator(i4));
        }
        getAnimatorSet().playSequentially(arrayList);
        this.mRotateAnimationController = new RotateAnimationController(builder.animatorModel, builder.duration, builder.modelType, builder.rotateEnabled, builder.initRotate, this.mLatLngs, this.mDistances, this.mSumDistance, this.mEarthMercatorProjection);
    }

    public void addAnimatorEndListener(IAnimatorModel.IAnimatorEndListener iAnimatorEndListener) {
        List<IAnimatorModel.IAnimatorEndListener> list = this.mAnimatorEndListeners;
        if (list == null || list.contains(iAnimatorEndListener) || iAnimatorEndListener == null) {
            return;
        }
        this.mAnimatorEndListeners.add(iAnimatorEndListener);
        addAnimationListener();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.transform.OverlayAnimator
    public void cancelAnimation() {
        super.cancelAnimation();
        if (this.mRotateAnimationController == null) {
            return;
        }
        synchronized (this) {
            this.mRotateAnimationController.cancelAnimation();
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.transform.OverlayAnimator
    public ValueAnimator createSegmentAnimator(int i2) {
        Point point2 = this.mEarthMercatorProjection.toPoint(this.mLatLngs[i2]);
        Point point3 = this.mEarthMercatorProjection.toPoint(this.mLatLngs[i2 + 1]);
        ValueAnimator valueAnimator = new ValueAnimator();
        double duration = getDuration();
        double d = this.mDistances[i2];
        Double.isNaN(duration);
        valueAnimator.setDuration((long) ((duration * d) / this.mSumDistance));
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setFloatValues((float) this.mDistances[i2]);
        valueAnimator.addUpdateListener(new a(point2, point3, i2));
        return valueAnimator;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.transform.OverlayAnimator
    public void endAnimation() {
        super.endAnimation();
        if (this.mRotateAnimationController == null) {
            return;
        }
        synchronized (this) {
            this.mRotateAnimationController.endAnimation();
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.transform.OverlayAnimator
    public void innerAnimationEnd() {
        List<IAnimatorModel.IAnimatorEndListener> list = this.mAnimatorEndListeners;
        if (list == null) {
            return;
        }
        Iterator<IAnimatorModel.IAnimatorEndListener> it = list.iterator();
        while (it.hasNext()) {
            it.next().onAnimatorEnd();
        }
    }

    public void removeAnimatorEndListener(IAnimatorModel.IAnimatorEndListener iAnimatorEndListener) {
        List<IAnimatorModel.IAnimatorEndListener> list = this.mAnimatorEndListeners;
        if (list == null) {
            return;
        }
        list.remove(iAnimatorEndListener);
        removeAnimationListener();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.transform.OverlayAnimator
    public void startAnimation() {
        super.startAnimation();
        if (this.mRotateAnimationController == null) {
            return;
        }
        synchronized (this) {
            this.mRotateAnimationController.startAnimation();
        }
    }
}
