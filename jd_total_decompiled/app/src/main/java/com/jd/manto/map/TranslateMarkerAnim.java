package com.jd.manto.map;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;
import com.jd.manto.map.Beans;
import com.tencent.tencentmap.mapsdk.maps.MapView;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes17.dex */
public final class TranslateMarkerAnim {
    private MapView mapView;
    private Marker marker;
    private List<Beans.TranslateMarkerInfo> translateMarkerInfos;
    public AnimatorSet animatorSet = new AnimatorSet();
    private EarthPosHelper pointerHelper = new EarthPosHelper();

    /* loaded from: classes17.dex */
    public static class EarthPosHelper {
        public static double EARTH_RADIUS = 6378137.0d;
        final double perimeter = EARTH_RADIUS * 6.283185307179586d;

        EarthPosHelper() {
        }

        public double distance(LatLng latLng, LatLng latLng2) {
            double longitude = latLng.getLongitude();
            double d = longitude * 0.01745329251994329d;
            double latitude = latLng.getLatitude() * 0.01745329251994329d;
            double longitude2 = latLng2.getLongitude() * 0.01745329251994329d;
            double latitude2 = latLng2.getLatitude() * 0.01745329251994329d;
            double sin = Math.sin(d);
            double sin2 = Math.sin(latitude);
            double cos = Math.cos(d);
            double cos2 = Math.cos(latitude);
            double sin3 = Math.sin(longitude2);
            double sin4 = Math.sin(latitude2);
            double cos3 = Math.cos(longitude2);
            double cos4 = Math.cos(latitude2);
            double[] dArr = {cos * cos2, cos2 * sin, sin2};
            double[] dArr2 = {cos3 * cos4, cos4 * sin3, sin4};
            return Math.asin(Math.sqrt((((dArr[0] - dArr2[0]) * (dArr[0] - dArr2[0])) + ((dArr[1] - dArr2[1]) * (dArr[1] - dArr2[1]))) + ((dArr[2] - dArr2[2]) * (dArr[2] - dArr2[2]))) / 2.0d) * 1.27420015798544E7d;
        }

        public final Beans.Point getPointer(LatLng latLng) {
            double sin = Math.sin(Math.toRadians(latLng.getLatitude()));
            return new Beans.Point((((Math.log((sin + 1.0d) / (1.0d - sin)) * 0.5d) / (-6.283185307179586d)) + 0.5d) * this.perimeter, ((latLng.getLongitude() / 360.0d) + 0.5d) * this.perimeter);
        }
    }

    /* loaded from: classes17.dex */
    public interface TranslateMarkerCallback {
        void onTranslate(boolean z);
    }

    public TranslateMarkerAnim(List<Beans.TranslateMarkerInfo> list, Marker marker, MapView mapView) {
        this.translateMarkerInfos = list;
        this.marker = marker;
        this.mapView = mapView;
        ArrayList arrayList = new ArrayList();
        for (Beans.TranslateMarkerInfo translateMarkerInfo : this.translateMarkerInfos) {
            if (translateMarkerInfo.rotate != 0.0f) {
                ValueAnimator ofFloat = ValueAnimator.ofFloat(this.marker.getRotation(), this.marker.getRotation() + translateMarkerInfo.rotate);
                ofFloat.setDuration(translateMarkerInfo.duration);
                ofFloat.setInterpolator(new LinearInterpolator());
                ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jd.manto.map.TranslateMarkerAnim.1
                    {
                        TranslateMarkerAnim.this = this;
                    }

                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        TranslateMarkerAnim.this.marker.setRotation((float) Double.parseDouble(String.valueOf(valueAnimator.getAnimatedValue())));
                    }
                });
                arrayList.add(ofFloat);
            } else {
                try {
                    arrayList.add(getAnimator(translateMarkerInfo));
                } catch (Exception unused) {
                }
            }
        }
        this.animatorSet.playSequentially(arrayList);
    }

    private ValueAnimator getAnimator(Beans.TranslateMarkerInfo translateMarkerInfo) {
        LatLng[] latLngArr = {new LatLng(translateMarkerInfo.oldlatitude, translateMarkerInfo.oldlongitude), new LatLng(translateMarkerInfo.latitude, translateMarkerInfo.longitude)};
        this.pointerHelper.getPointer(latLngArr[0]);
        this.pointerHelper.getPointer(latLngArr[1]);
        this.mapView.getMap().getProjection();
        final double[] dArr = {this.pointerHelper.distance(latLngArr[0], latLngArr[1])};
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(translateMarkerInfo.duration);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setFloatValues((float) dArr[0]);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0064: INVOKE 
              (r0v3 'valueAnimator' android.animation.ValueAnimator)
              (wrap: android.animation.ValueAnimator$AnimatorUpdateListener : 0x0061: CONSTRUCTOR 
              (r8v0 'this' com.jd.manto.map.TranslateMarkerAnim A[IMMUTABLE_TYPE, THIS])
              (r1 I:com.jd.manto.map.Beans$Point A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r4 I:com.jd.manto.map.Beans$Point A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r5v2 'dArr' double[] A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[MD:(com.jd.manto.map.TranslateMarkerAnim, com.jd.manto.map.Beans$Point, com.jd.manto.map.Beans$Point, double[]):void (m), WRAPPED] (LINE:10) call: com.jd.manto.map.TranslateMarkerAnim.2.<init>(com.jd.manto.map.TranslateMarkerAnim, com.jd.manto.map.Beans$Point, com.jd.manto.map.Beans$Point, double[]):void type: CONSTRUCTOR)
             type: VIRTUAL call: android.animation.ValueAnimator.addUpdateListener(android.animation.ValueAnimator$AnimatorUpdateListener):void A[MD:(android.animation.ValueAnimator$AnimatorUpdateListener):void (c)] (LINE:10) in method: com.jd.manto.map.TranslateMarkerAnim.getAnimator(com.jd.manto.map.Beans$TranslateMarkerInfo):android.animation.ValueAnimator, file: classes17.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
            Caused by: java.lang.NullPointerException
            */
        /*
            this = this;
            r0 = 2
            com.tencent.tencentmap.mapsdk.maps.model.LatLng[] r0 = new com.tencent.tencentmap.mapsdk.maps.model.LatLng[r0]
            com.tencent.tencentmap.mapsdk.maps.model.LatLng r1 = new com.tencent.tencentmap.mapsdk.maps.model.LatLng
            double r2 = r9.oldlatitude
            double r4 = r9.oldlongitude
            r1.<init>(r2, r4)
            r2 = 0
            r0[r2] = r1
            com.tencent.tencentmap.mapsdk.maps.model.LatLng r1 = new com.tencent.tencentmap.mapsdk.maps.model.LatLng
            double r3 = r9.latitude
            double r5 = r9.longitude
            r1.<init>(r3, r5)
            r3 = 1
            r0[r3] = r1
            com.jd.manto.map.TranslateMarkerAnim$EarthPosHelper r1 = r8.pointerHelper
            r4 = r0[r2]
            com.jd.manto.map.Beans$Point r1 = r1.getPointer(r4)
            com.jd.manto.map.TranslateMarkerAnim$EarthPosHelper r4 = r8.pointerHelper
            r5 = r0[r3]
            com.jd.manto.map.Beans$Point r4 = r4.getPointer(r5)
            double[] r5 = new double[r3]
            com.tencent.tencentmap.mapsdk.maps.MapView r6 = r8.mapView
            com.tencent.tencentmap.mapsdk.maps.TencentMap r6 = r6.getMap()
            r6.getProjection()
            com.jd.manto.map.TranslateMarkerAnim$EarthPosHelper r6 = r8.pointerHelper
            r7 = r0[r2]
            r0 = r0[r3]
            double r6 = r6.distance(r7, r0)
            r5[r2] = r6
            android.animation.ValueAnimator r0 = new android.animation.ValueAnimator
            r0.<init>()
            int r9 = r9.duration
            long r6 = (long) r9
            r0.setDuration(r6)
            android.view.animation.LinearInterpolator r9 = new android.view.animation.LinearInterpolator
            r9.<init>()
            r0.setInterpolator(r9)
            float[] r9 = new float[r3]
            r6 = r5[r2]
            float r3 = (float) r6
            r9[r2] = r3
            r0.setFloatValues(r9)
            com.jd.manto.map.TranslateMarkerAnim$2 r9 = new com.jd.manto.map.TranslateMarkerAnim$2
            r9.<init>()
            r0.addUpdateListener(r9)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.manto.map.TranslateMarkerAnim.getAnimator(com.jd.manto.map.Beans$TranslateMarkerInfo):android.animation.ValueAnimator");
    }
}
