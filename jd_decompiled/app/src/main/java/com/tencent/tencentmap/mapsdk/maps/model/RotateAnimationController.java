package com.tencent.tencentmap.mapsdk.maps.model;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;
import com.tencent.tencentmap.mapsdk.maps.model.GeneralTranslateAnimator;
import com.tencent.tencentmap.mapsdk.maps.model.transform.Point;
import com.tencent.tencentmap.mapsdk.maps.model.transform.SphericalMercatorProjection;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class RotateAnimationController {
    private IAnimatorModel mAnimatorModel;
    private double[] mDistances;
    private long mDuration;
    private SphericalMercatorProjection mEarthMercatorProjection;
    private d mIValueAnimatorStrategy;
    private final float mInitRotate;
    private LatLng[] mLatLngs;
    private GeneralTranslateAnimator.ModelType mModelType;
    private AnimatorSet mRotateAnimatorSet;
    private final boolean mRotateEnabled;
    private double mSumDistance;

    /* loaded from: classes9.dex */
    public class a implements d {
        public a() {
        }

        @Override // com.tencent.tencentmap.mapsdk.maps.model.RotateAnimationController.d
        public ValueAnimator a(float f2, float f3) {
            return RotateAnimationController.this.mModelType.ordinal() != 1 ? ValueAnimator.ofFloat(f2, f3) : ValueAnimator.ofFloat(f2 * (-1.0f), f3 * (-1.0f));
        }

        @Override // com.tencent.tencentmap.mapsdk.maps.model.RotateAnimationController.d
        public double[] a() {
            double[] dArr = {0.0d, 1.0d};
            int i2 = c.a[RotateAnimationController.this.mModelType.ordinal()];
            return dArr;
        }
    }

    /* loaded from: classes9.dex */
    public class b implements ValueAnimator.AnimatorUpdateListener {
        public b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            double parseDouble = Double.parseDouble(String.valueOf(valueAnimator.getAnimatedValue()));
            if (RotateAnimationController.this.mAnimatorModel == null) {
                return;
            }
            RotateAnimationController.this.mAnimatorModel.setRotation((float) parseDouble);
        }
    }

    /* loaded from: classes9.dex */
    public static /* synthetic */ class c {
        public static final /* synthetic */ int[] a;

        static {
            GeneralTranslateAnimator.ModelType.values();
            int[] iArr = new int[2];
            a = iArr;
            try {
                GeneralTranslateAnimator.ModelType modelType = GeneralTranslateAnimator.ModelType.MODEL_OVERLAY;
                iArr[1] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* loaded from: classes9.dex */
    public interface d {
        ValueAnimator a(float f2, float f3);

        double[] a();
    }

    public RotateAnimationController(IAnimatorModel iAnimatorModel, long j2, GeneralTranslateAnimator.ModelType modelType, boolean z, float f2, LatLng[] latLngArr, double[] dArr, double d2, SphericalMercatorProjection sphericalMercatorProjection) {
        this.mRotateEnabled = z;
        this.mInitRotate = f2;
        if (z) {
            this.mAnimatorModel = iAnimatorModel;
            this.mDuration = j2;
            this.mModelType = modelType;
            this.mRotateAnimatorSet = new AnimatorSet();
            this.mLatLngs = latLngArr;
            this.mDistances = dArr;
            this.mSumDistance = d2;
            this.mEarthMercatorProjection = sphericalMercatorProjection;
            initValueAnimatorStrategy();
            initRotateAnimation();
        }
    }

    private double calculateAngle(double d2, double d3, double d4, double d5) {
        double sqrt = ((d2 * d4) + (d3 * d5)) / (Math.sqrt((d2 * d2) + (d3 * d3)) * Math.sqrt((d4 * d4) + (d5 * d5)));
        if (Double.isNaN(sqrt)) {
            return 0.0d;
        }
        if (sqrt < -1.0d) {
            sqrt = -1.0d;
        }
        if (sqrt > 1.0d) {
            sqrt = 1.0d;
        }
        double acos = (Math.acos(sqrt) * 180.0d) / 3.141592653589793d;
        if ((d2 * d5) - (d3 * d4) > 0.0d) {
            acos = -acos;
        }
        return (float) acos;
    }

    private long calculateDelay(int i2, int i3) {
        double d2 = 0.0d;
        while (i2 < i3) {
            d2 += this.mDistances[i2];
            i2++;
        }
        double d3 = this.mDuration;
        Double.isNaN(d3);
        return (long) ((d3 * d2) / this.mSumDistance);
    }

    private ValueAnimator createRotateAnimator(float f2, float f3, long j2, long j3) {
        d dVar = this.mIValueAnimatorStrategy;
        float f4 = this.mInitRotate;
        ValueAnimator a2 = dVar.a(f2 + f4, f3 + f4);
        a2.setDuration(j2);
        a2.setStartDelay(j3);
        a2.setInterpolator(new LinearInterpolator());
        a2.addUpdateListener(new b());
        return a2;
    }

    private void initRotateAnimation() {
        int i2;
        long j2;
        float f2;
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        int i4 = 1;
        int i5 = 0;
        float f3 = 0.0f;
        long j3 = 0;
        while (true) {
            LatLng[] latLngArr = this.mLatLngs;
            if (i4 >= latLngArr.length) {
                this.mRotateAnimatorSet.playSequentially(arrayList);
                return;
            }
            if (latLngArr[i5].equals(latLngArr[i4])) {
                i2 = i4;
            } else {
                Point point2 = this.mEarthMercatorProjection.toPoint(this.mLatLngs[i3]);
                Point point3 = this.mEarthMercatorProjection.toPoint(this.mLatLngs[i5]);
                Point point4 = this.mEarthMercatorProjection.toPoint(this.mLatLngs[i4]);
                double d2 = point3.x;
                double d3 = point2.y;
                int i6 = i3;
                double d4 = point3.y;
                float f4 = f3;
                i2 = i4;
                int i7 = i5;
                float calculateAngle = (float) calculateAngle(d2 - point2.x, d3 - d4, point4.x - d2, d4 - point4.y);
                if (arrayList.size() == 0) {
                    IAnimatorModel iAnimatorModel = this.mAnimatorModel;
                    if (iAnimatorModel == null) {
                        return;
                    }
                    float rotation = iAnimatorModel.getRotation();
                    double[] a2 = this.mIValueAnimatorStrategy.a();
                    calculateAngle = ((float) calculateAngle(a2[0], a2[1], point4.x - point3.x, point3.y - point4.y)) - rotation;
                    f2 = rotation;
                    j2 = 0;
                } else {
                    double abs = Math.abs(calculateAngle);
                    Double.isNaN(abs);
                    double d5 = this.mDuration;
                    Double.isNaN(d5);
                    long j4 = (long) ((d5 * (((abs * 3.141592653589793d) * 6.0d) / 180.0d)) / this.mSumDistance);
                    j3 = calculateDelay(i6, i7) - (j4 / 2);
                    j2 = j4;
                    f2 = f4;
                }
                float f5 = f2 + calculateAngle;
                arrayList.add(createRotateAnimator(f2, f5, j2, j3));
                i5 = i2;
                i3 = i7;
                f3 = f5;
            }
            i4 = i2 + 1;
        }
    }

    private void initValueAnimatorStrategy() {
        this.mIValueAnimatorStrategy = new a();
    }

    public void cancelAnimation() {
        if (this.mRotateEnabled) {
            this.mRotateAnimatorSet.cancel();
        }
    }

    public void endAnimation() {
        if (this.mRotateEnabled) {
            this.mRotateAnimatorSet.end();
        }
    }

    public void startAnimation() {
        if (this.mRotateEnabled && !this.mRotateAnimatorSet.isRunning()) {
            this.mRotateAnimatorSet.start();
        }
    }
}
