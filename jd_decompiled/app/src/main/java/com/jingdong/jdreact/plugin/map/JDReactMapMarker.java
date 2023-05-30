package com.jingdong.jdreact.plugin.map;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.util.Property;
import android.view.View;
import android.widget.LinearLayout;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.DraweeHolder;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.react.bridge.ReadableMap;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptor;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptorFactory;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions;
import javax.annotation.Nullable;

/* loaded from: classes13.dex */
public class JDReactMapMarker extends JDReactMapFeature {
    private boolean anchorIsSet;
    private float anchorX;
    private float anchorY;
    private boolean calloutAnchorIsSet;
    private float calloutAnchorX;
    private float calloutAnchorY;
    private JDReactMapCallout calloutView;
    private final Context context;
    private DataSource<CloseableReference<CloseableImage>> dataSource;
    private boolean draggable;
    private boolean flat;
    private boolean hasCustomMarkerView;
    private boolean hasViewChanges;
    private int height;
    private Bitmap iconBitmap;
    private BitmapDescriptor iconBitmapDescriptor;
    private String identifier;
    private String imageUri;
    private final DraweeHolder<?> logoHolder;
    private Bitmap mLastBitmapCreated;
    private final ControllerListener<ImageInfo> mLogoControllerListener;
    private Marker marker;
    private float markerHue;
    private final JDReactMapMarkerManager markerManager;
    private MarkerOptions markerOptions;
    private float opacity;
    private LatLng position;
    private float rotation;
    private String snippet;
    private String title;
    private boolean tracksViewChanges;
    private boolean tracksViewChangesActive;
    private int width;
    private View wrappedCalloutView;
    private int zIndex;

    public JDReactMapMarker(Context context, JDReactMapMarkerManager jDReactMapMarkerManager) {
        super(context);
        this.markerHue = 0.0f;
        this.rotation = 0.0f;
        this.flat = false;
        this.draggable = false;
        this.zIndex = 0;
        this.opacity = 1.0f;
        this.tracksViewChanges = true;
        this.tracksViewChangesActive = false;
        this.hasViewChanges = true;
        this.hasCustomMarkerView = false;
        this.mLogoControllerListener = new BaseControllerListener<ImageInfo>() { // from class: com.jingdong.jdreact.plugin.map.JDReactMapMarker.1
            @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
            public void onFinalImageSet(String str, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable, Drawable drawable) {
                CloseableReference closeableReference;
                Throwable th;
                Bitmap underlyingBitmap;
                super.onFinalImageSet(str, (String) imageInfo, animatable, drawable);
                try {
                    closeableReference = (CloseableReference) JDReactMapMarker.this.dataSource.getResult();
                    if (closeableReference != null) {
                        try {
                            CloseableImage closeableImage = (CloseableImage) closeableReference.get();
                            if (closeableImage != null && (closeableImage instanceof CloseableStaticBitmap) && (underlyingBitmap = ((CloseableStaticBitmap) closeableImage).getUnderlyingBitmap()) != null) {
                                Bitmap copy = underlyingBitmap.copy(Bitmap.Config.ARGB_8888, true);
                                JDReactMapMarker.this.iconBitmap = copy;
                                JDReactMapMarker.this.iconBitmapDescriptor = BitmapDescriptorFactory.fromBitmap(copy);
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            JDReactMapMarker.this.dataSource.close();
                            if (closeableReference != null) {
                                CloseableReference.closeSafely(closeableReference);
                            }
                            throw th;
                        }
                    }
                    JDReactMapMarker.this.dataSource.close();
                    if (closeableReference != null) {
                        CloseableReference.closeSafely(closeableReference);
                    }
                    if (JDReactMapMarker.this.markerManager != null && JDReactMapMarker.this.imageUri != null) {
                        JDReactMapMarker.this.markerManager.getSharedIcon(JDReactMapMarker.this.imageUri).updateIcon(JDReactMapMarker.this.iconBitmapDescriptor, JDReactMapMarker.this.iconBitmap);
                    }
                    JDReactMapMarker.this.update(true);
                } catch (Throwable th3) {
                    closeableReference = null;
                    th = th3;
                }
            }
        };
        this.mLastBitmapCreated = null;
        this.context = context;
        this.markerManager = jDReactMapMarkerManager;
        DraweeHolder<?> create = DraweeHolder.create(createDraweeHierarchy(), context);
        this.logoHolder = create;
        create.onAttach();
    }

    private void clearDrawableCache() {
        this.mLastBitmapCreated = null;
    }

    private Bitmap createDrawable() {
        int i2 = this.width;
        if (i2 <= 0) {
            i2 = 100;
        }
        int i3 = this.height;
        int i4 = i3 > 0 ? i3 : 100;
        buildDrawingCache();
        Bitmap bitmap = this.mLastBitmapCreated;
        if (bitmap != null && !bitmap.isRecycled() && bitmap.getWidth() == i2 && bitmap.getHeight() == i4) {
            bitmap.eraseColor(0);
        } else {
            bitmap = Bitmap.createBitmap(i2, i4, Bitmap.Config.ARGB_8888);
            this.mLastBitmapCreated = bitmap;
        }
        draw(new Canvas(bitmap));
        return bitmap;
    }

    private GenericDraweeHierarchy createDraweeHierarchy() {
        return new GenericDraweeHierarchyBuilder(getResources()).setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER).setFadeDuration(0).build();
    }

    private MarkerOptions fillMarkerOptions(MarkerOptions markerOptions) {
        markerOptions.position(this.position);
        if (this.anchorIsSet) {
            markerOptions.anchor(this.anchorX, this.anchorY);
        }
        if (this.calloutAnchorIsSet) {
            markerOptions.anchor(this.calloutAnchorX, this.calloutAnchorY);
        }
        markerOptions.title(this.title);
        markerOptions.snippet(this.snippet);
        markerOptions.rotation(this.rotation);
        markerOptions.draggable(this.draggable);
        markerOptions.alpha(this.opacity);
        markerOptions.icon(getIcon());
        return markerOptions;
    }

    private BitmapDescriptor getBitmapDescriptorByName(String str) {
        return BitmapDescriptorFactory.fromResource(getDrawableResourceByName(str));
    }

    private int getDrawableResourceByName(String str) {
        return getResources().getIdentifier(str, "drawable", getContext().getPackageName());
    }

    private BitmapDescriptor getIcon() {
        if (this.hasCustomMarkerView) {
            if (this.iconBitmapDescriptor != null) {
                Bitmap createDrawable = createDrawable();
                Bitmap createBitmap = Bitmap.createBitmap(Math.max(this.iconBitmap.getWidth(), createDrawable.getWidth()), Math.max(this.iconBitmap.getHeight(), createDrawable.getHeight()), this.iconBitmap.getConfig());
                Canvas canvas = new Canvas(createBitmap);
                canvas.drawBitmap(this.iconBitmap, 0.0f, 0.0f, (Paint) null);
                canvas.drawBitmap(createDrawable, 0.0f, 0.0f, (Paint) null);
                return BitmapDescriptorFactory.fromBitmap(createBitmap);
            }
            return BitmapDescriptorFactory.fromBitmap(createDrawable());
        }
        BitmapDescriptor bitmapDescriptor = this.iconBitmapDescriptor;
        return bitmapDescriptor != null ? bitmapDescriptor : BitmapDescriptorFactory.fromResource(R.drawable.jdreact_map_marker);
    }

    private void updateTracksViewChanges() {
        boolean z = this.tracksViewChanges && this.hasCustomMarkerView && this.marker != null;
        if (z == this.tracksViewChangesActive) {
            return;
        }
        this.tracksViewChangesActive = z;
        if (z) {
            ViewChangesTracker.getInstance().addMarker(this);
            return;
        }
        ViewChangesTracker.getInstance().removeMarker(this);
        updateMarkerIcon();
    }

    private void wrapCalloutView() {
        JDReactMapCallout jDReactMapCallout = this.calloutView;
        if (jDReactMapCallout == null || jDReactMapCallout.getChildCount() == 0) {
            return;
        }
        LinearLayout linearLayout = new LinearLayout(this.context);
        linearLayout.setOrientation(1);
        JDReactMapCallout jDReactMapCallout2 = this.calloutView;
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(jDReactMapCallout2.width, jDReactMapCallout2.height, 0.0f));
        LinearLayout linearLayout2 = new LinearLayout(this.context);
        linearLayout2.setOrientation(0);
        JDReactMapCallout jDReactMapCallout3 = this.calloutView;
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(jDReactMapCallout3.width, jDReactMapCallout3.height, 0.0f));
        linearLayout.addView(linearLayout2);
        linearLayout2.addView(this.calloutView);
        this.wrappedCalloutView = linearLayout;
    }

    @Override // com.jingdong.jdreact.plugin.map.JDReactMapFeature
    public void addToMap(TencentMap tencentMap) {
        try {
            this.marker = tencentMap.addMarker(getMarkerOptions());
            updateTracksViewChanges();
        } catch (Exception unused) {
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2) {
        super.addView(view, i2);
        if (!(view instanceof JDReactMapCallout)) {
            this.hasCustomMarkerView = true;
            updateTracksViewChanges();
        }
        update(true);
    }

    public void animateToCoodinate(LatLng latLng, Integer num) {
        ObjectAnimator ofObject = ObjectAnimator.ofObject(this.marker, Property.of(Marker.class, LatLng.class, "position"), new TypeEvaluator<LatLng>() { // from class: com.jingdong.jdreact.plugin.map.JDReactMapMarker.2
            @Override // android.animation.TypeEvaluator
            public LatLng evaluate(float f2, LatLng latLng2, LatLng latLng3) {
                return JDReactMapMarker.this.interpolate(f2, latLng2, latLng3);
            }
        }, latLng);
        ofObject.setDuration(num.intValue());
        ofObject.start();
    }

    public View getCallout() {
        if (this.calloutView == null) {
            return null;
        }
        if (this.wrappedCalloutView == null) {
            wrapCalloutView();
        }
        if (this.calloutView.getTooltip()) {
            return this.wrappedCalloutView;
        }
        return null;
    }

    public JDReactMapCallout getCalloutView() {
        return this.calloutView;
    }

    @Override // com.jingdong.jdreact.plugin.map.JDReactMapFeature
    public Object getFeature() {
        return this.marker;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public View getInfoContents() {
        if (this.calloutView == null) {
            return null;
        }
        if (this.wrappedCalloutView == null) {
            wrapCalloutView();
        }
        if (this.calloutView.getTooltip()) {
            return null;
        }
        return this.wrappedCalloutView;
    }

    public MarkerOptions getMarkerOptions() {
        if (this.markerOptions == null) {
            this.markerOptions = new MarkerOptions();
        }
        fillMarkerOptions(this.markerOptions);
        return this.markerOptions;
    }

    public LatLng interpolate(float f2, LatLng latLng, LatLng latLng2) {
        double latitude = latLng2.getLatitude() - latLng.getLatitude();
        double d = f2;
        Double.isNaN(d);
        Double.isNaN(d);
        return new LatLng((latitude * d) + latLng.getLatitude(), ((latLng2.getLongitude() - latLng.getLongitude()) * d) + latLng.getLongitude());
    }

    @Override // com.jingdong.jdreact.plugin.map.JDReactMapFeature
    public void removeFromMap(TencentMap tencentMap) {
        Marker marker = this.marker;
        if (marker == null) {
            return;
        }
        marker.remove();
        this.marker = null;
        updateTracksViewChanges();
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.View, android.view.ViewParent
    public void requestLayout() {
        super.requestLayout();
        if (getChildCount() == 0 && this.hasCustomMarkerView) {
            this.hasCustomMarkerView = false;
            clearDrawableCache();
            updateTracksViewChanges();
            update(true);
        }
    }

    public void setAnchor(double d, double d2) {
        this.anchorIsSet = true;
        float f2 = (float) d;
        this.anchorX = f2;
        float f3 = (float) d2;
        this.anchorY = f3;
        Marker marker = this.marker;
        if (marker != null) {
            marker.setAnchor(f2, f3);
        }
        update(false);
    }

    public void setCalloutView(JDReactMapCallout jDReactMapCallout) {
        this.calloutView = jDReactMapCallout;
    }

    public void setCoordinate(ReadableMap readableMap) {
        LatLng latLng = new LatLng(readableMap.getDouble(PdLVBody.LATITUDE), readableMap.getDouble(PdLVBody.LONGITUDE));
        this.position = latLng;
        Marker marker = this.marker;
        if (marker != null) {
            marker.setPosition(latLng);
        }
        update(false);
    }

    public void setDraggable(boolean z) {
        this.draggable = z;
        Marker marker = this.marker;
        if (marker != null) {
            marker.setDraggable(z);
        }
        update(false);
    }

    public void setIconBitmap(Bitmap bitmap) {
        this.iconBitmap = bitmap;
    }

    public void setIconBitmapDescriptor(BitmapDescriptor bitmapDescriptor, Bitmap bitmap) {
        this.iconBitmapDescriptor = bitmapDescriptor;
        this.iconBitmap = bitmap;
        this.hasViewChanges = true;
        update(true);
    }

    public void setIdentifier(String str) {
        this.identifier = str;
        update(false);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setImage(java.lang.String r6) {
        /*
            r5 = this;
            r0 = 1
            r5.hasViewChanges = r0
            com.jingdong.jdreact.plugin.map.JDReactMapMarkerManager r1 = r5.markerManager
            if (r1 == 0) goto L29
            java.lang.String r2 = r5.imageUri
            if (r2 == 0) goto L19
            com.jingdong.jdreact.plugin.map.JDReactMapMarkerManager$JDReactMapMarkerSharedIcon r1 = r1.getSharedIcon(r2)
            r1.removeMarker(r5)
            com.jingdong.jdreact.plugin.map.JDReactMapMarkerManager r1 = r5.markerManager
            java.lang.String r2 = r5.imageUri
            r1.removeSharedIconIfEmpty(r2)
        L19:
            if (r6 == 0) goto L29
            com.jingdong.jdreact.plugin.map.JDReactMapMarkerManager r1 = r5.markerManager
            com.jingdong.jdreact.plugin.map.JDReactMapMarkerManager$JDReactMapMarkerSharedIcon r1 = r1.getSharedIcon(r6)
            r1.addMarker(r5)
            boolean r1 = r1.shouldLoadImage()
            goto L2a
        L29:
            r1 = 1
        L2a:
            r5.imageUri = r6
            if (r1 != 0) goto L2f
            return
        L2f:
            if (r6 != 0) goto L39
            r6 = 0
            r5.iconBitmapDescriptor = r6
            r5.update(r0)
            goto Lfa
        L39:
            java.lang.String r1 = "http://"
            boolean r1 = r6.startsWith(r1)
            if (r1 != 0) goto Lbd
            java.lang.String r1 = "https://"
            boolean r1 = r6.startsWith(r1)
            if (r1 != 0) goto Lbd
            java.lang.String r1 = "file://"
            boolean r1 = r6.startsWith(r1)
            if (r1 != 0) goto Lbd
            java.lang.String r1 = "asset://"
            boolean r1 = r6.startsWith(r1)
            if (r1 != 0) goto Lbd
            java.lang.String r1 = "data:"
            boolean r1 = r6.startsWith(r1)
            if (r1 == 0) goto L62
            goto Lbd
        L62:
            com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptor r1 = r5.getBitmapDescriptorByName(r6)
            r5.iconBitmapDescriptor = r1
            if (r1 == 0) goto La8
            int r1 = r5.getDrawableResourceByName(r6)
            android.content.res.Resources r2 = r5.getResources()
            android.graphics.Bitmap r2 = android.graphics.BitmapFactory.decodeResource(r2, r1)
            r5.iconBitmap = r2
            if (r2 != 0) goto La8
            android.content.res.Resources r2 = r5.getResources()
            android.graphics.drawable.Drawable r1 = r2.getDrawable(r1)
            int r2 = r1.getIntrinsicWidth()
            int r3 = r1.getIntrinsicHeight()
            android.graphics.Bitmap$Config r4 = android.graphics.Bitmap.Config.ARGB_8888
            android.graphics.Bitmap r2 = android.graphics.Bitmap.createBitmap(r2, r3, r4)
            r5.iconBitmap = r2
            int r2 = r1.getIntrinsicWidth()
            int r3 = r1.getIntrinsicHeight()
            r4 = 0
            r1.setBounds(r4, r4, r2, r3)
            android.graphics.Canvas r2 = new android.graphics.Canvas
            android.graphics.Bitmap r3 = r5.iconBitmap
            r2.<init>(r3)
            r1.draw(r2)
        La8:
            com.jingdong.jdreact.plugin.map.JDReactMapMarkerManager r1 = r5.markerManager
            if (r1 == 0) goto Lb9
            if (r6 == 0) goto Lb9
            com.jingdong.jdreact.plugin.map.JDReactMapMarkerManager$JDReactMapMarkerSharedIcon r6 = r1.getSharedIcon(r6)
            com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptor r1 = r5.iconBitmapDescriptor
            android.graphics.Bitmap r2 = r5.iconBitmap
            r6.updateIcon(r1, r2)
        Lb9:
            r5.update(r0)
            goto Lfa
        Lbd:
            android.net.Uri r6 = android.net.Uri.parse(r6)
            com.facebook.imagepipeline.request.ImageRequestBuilder r6 = com.facebook.imagepipeline.request.ImageRequestBuilder.newBuilderWithSource(r6)
            com.facebook.imagepipeline.request.ImageRequest r6 = r6.build()
            com.facebook.imagepipeline.core.ImagePipeline r0 = com.facebook.drawee.backends.pipeline.Fresco.getImagePipeline()
            com.facebook.datasource.DataSource r0 = r0.fetchDecodedImage(r6, r5)
            r5.dataSource = r0
            com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder r0 = com.facebook.drawee.backends.pipeline.Fresco.newDraweeControllerBuilder()
            com.facebook.drawee.controller.AbstractDraweeControllerBuilder r6 = r0.setImageRequest(r6)
            com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder r6 = (com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder) r6
            com.facebook.drawee.controller.ControllerListener<com.facebook.imagepipeline.image.ImageInfo> r0 = r5.mLogoControllerListener
            com.facebook.drawee.controller.AbstractDraweeControllerBuilder r6 = r6.setControllerListener(r0)
            com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder r6 = (com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder) r6
            com.facebook.drawee.view.DraweeHolder<?> r0 = r5.logoHolder
            com.facebook.drawee.interfaces.DraweeController r0 = r0.getController()
            com.facebook.drawee.controller.AbstractDraweeControllerBuilder r6 = r6.setOldController(r0)
            com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder r6 = (com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder) r6
            com.facebook.drawee.controller.AbstractDraweeController r6 = r6.build()
            com.facebook.drawee.view.DraweeHolder<?> r0 = r5.logoHolder
            r0.setController(r6)
        Lfa:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdreact.plugin.map.JDReactMapMarker.setImage(java.lang.String):void");
    }

    public void setMarkerHue(float f2) {
        this.markerHue = f2;
        update(false);
    }

    public void setOpacity(float f2) {
        this.opacity = f2;
        Marker marker = this.marker;
        if (marker != null) {
            marker.setAlpha(f2);
        }
        update(false);
    }

    @Override // android.view.View
    public void setRotation(float f2) {
        this.rotation = f2;
        Marker marker = this.marker;
        if (marker != null) {
            marker.setRotation(f2);
        }
        update(false);
    }

    public void setSnippet(String str) {
        this.snippet = str;
        Marker marker = this.marker;
        if (marker != null) {
            marker.setSnippet(str);
        }
        update(false);
    }

    public void setTitle(String str) {
        this.title = str;
        Marker marker = this.marker;
        if (marker != null) {
            marker.setTitle(str);
        }
        update(false);
    }

    public void setTracksViewChanges(boolean z) {
        this.tracksViewChanges = z;
        updateTracksViewChanges();
    }

    public void update(boolean z) {
        if (this.marker == null) {
            return;
        }
        if (z) {
            updateMarkerIcon();
        }
        if (this.anchorIsSet) {
            this.marker.setAnchor(this.anchorX, this.anchorY);
        } else {
            this.marker.setAnchor(0.5f, 1.0f);
        }
    }

    public boolean updateCustomForTracking() {
        if (this.tracksViewChangesActive) {
            updateMarkerIcon();
            return true;
        }
        return false;
    }

    public void updateMarkerIcon() {
        Marker marker = this.marker;
        if (marker == null) {
            return;
        }
        if (!this.hasCustomMarkerView) {
            this.hasViewChanges = false;
        }
        if (marker != null) {
            marker.setIcon(getIcon());
        }
    }

    public void update(int i2, int i3) {
        this.width = i2;
        this.height = i3;
        update(true);
    }

    public JDReactMapMarker(Context context, MarkerOptions markerOptions, JDReactMapMarkerManager jDReactMapMarkerManager) {
        super(context);
        this.markerHue = 0.0f;
        this.rotation = 0.0f;
        this.flat = false;
        this.draggable = false;
        this.zIndex = 0;
        this.opacity = 1.0f;
        this.tracksViewChanges = true;
        this.tracksViewChangesActive = false;
        this.hasViewChanges = true;
        this.hasCustomMarkerView = false;
        this.mLogoControllerListener = new BaseControllerListener<ImageInfo>() { // from class: com.jingdong.jdreact.plugin.map.JDReactMapMarker.1
            @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
            public void onFinalImageSet(String str, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable, Drawable drawable) {
                CloseableReference closeableReference;
                Throwable th;
                Bitmap underlyingBitmap;
                super.onFinalImageSet(str, (String) imageInfo, animatable, drawable);
                try {
                    closeableReference = (CloseableReference) JDReactMapMarker.this.dataSource.getResult();
                    if (closeableReference != null) {
                        try {
                            CloseableImage closeableImage = (CloseableImage) closeableReference.get();
                            if (closeableImage != null && (closeableImage instanceof CloseableStaticBitmap) && (underlyingBitmap = ((CloseableStaticBitmap) closeableImage).getUnderlyingBitmap()) != null) {
                                Bitmap copy = underlyingBitmap.copy(Bitmap.Config.ARGB_8888, true);
                                JDReactMapMarker.this.iconBitmap = copy;
                                JDReactMapMarker.this.iconBitmapDescriptor = BitmapDescriptorFactory.fromBitmap(copy);
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            JDReactMapMarker.this.dataSource.close();
                            if (closeableReference != null) {
                                CloseableReference.closeSafely(closeableReference);
                            }
                            throw th;
                        }
                    }
                    JDReactMapMarker.this.dataSource.close();
                    if (closeableReference != null) {
                        CloseableReference.closeSafely(closeableReference);
                    }
                    if (JDReactMapMarker.this.markerManager != null && JDReactMapMarker.this.imageUri != null) {
                        JDReactMapMarker.this.markerManager.getSharedIcon(JDReactMapMarker.this.imageUri).updateIcon(JDReactMapMarker.this.iconBitmapDescriptor, JDReactMapMarker.this.iconBitmap);
                    }
                    JDReactMapMarker.this.update(true);
                } catch (Throwable th3) {
                    closeableReference = null;
                    th = th3;
                }
            }
        };
        this.mLastBitmapCreated = null;
        this.context = context;
        this.markerManager = jDReactMapMarkerManager;
        DraweeHolder<?> create = DraweeHolder.create(createDraweeHierarchy(), context);
        this.logoHolder = create;
        create.onAttach();
        this.position = markerOptions.getPosition();
        setAnchor(markerOptions.getAnchorU(), markerOptions.getAnchorV());
        setTitle(markerOptions.getTitle());
        setSnippet(markerOptions.getSnippet());
        setRotation(markerOptions.getRotation());
        setDraggable(markerOptions.isDraggable());
        setAlpha(markerOptions.getAlpha());
        this.iconBitmapDescriptor = markerOptions.getIcon();
    }
}
