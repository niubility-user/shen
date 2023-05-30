package com.jingdong.jdreact.plugin.map;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Property;
import android.view.View;
import android.widget.LinearLayout;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.DraweeHolder;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.react.bridge.ReadableMap;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.jingdong.jdreact.plugin.map.JDReactMapMarkerManager;
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
    */
    public void setImage(String str) {
        boolean z;
        this.hasViewChanges = true;
        JDReactMapMarkerManager jDReactMapMarkerManager = this.markerManager;
        if (jDReactMapMarkerManager != null) {
            String str2 = this.imageUri;
            if (str2 != null) {
                jDReactMapMarkerManager.getSharedIcon(str2).removeMarker(this);
                this.markerManager.removeSharedIconIfEmpty(this.imageUri);
            }
            if (str != null) {
                JDReactMapMarkerManager.JDReactMapMarkerSharedIcon sharedIcon = this.markerManager.getSharedIcon(str);
                sharedIcon.addMarker(this);
                z = sharedIcon.shouldLoadImage();
                this.imageUri = str;
                if (z) {
                    return;
                }
                if (str == null) {
                    this.iconBitmapDescriptor = null;
                    update(true);
                    return;
                } else if (!str.startsWith("http://") && !str.startsWith("https://") && !str.startsWith("file://") && !str.startsWith("asset://") && !str.startsWith("data:")) {
                    BitmapDescriptor bitmapDescriptorByName = getBitmapDescriptorByName(str);
                    this.iconBitmapDescriptor = bitmapDescriptorByName;
                    if (bitmapDescriptorByName != null) {
                        int drawableResourceByName = getDrawableResourceByName(str);
                        Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), drawableResourceByName);
                        this.iconBitmap = decodeResource;
                        if (decodeResource == null) {
                            Drawable drawable = getResources().getDrawable(drawableResourceByName);
                            this.iconBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                            drawable.draw(new Canvas(this.iconBitmap));
                        }
                    }
                    JDReactMapMarkerManager jDReactMapMarkerManager2 = this.markerManager;
                    if (jDReactMapMarkerManager2 != null && str != null) {
                        jDReactMapMarkerManager2.getSharedIcon(str).updateIcon(this.iconBitmapDescriptor, this.iconBitmap);
                    }
                    update(true);
                    return;
                } else {
                    ImageRequest build = ImageRequestBuilder.newBuilderWithSource(Uri.parse(str)).build();
                    this.dataSource = Fresco.getImagePipeline().fetchDecodedImage(build, this);
                    this.logoHolder.setController(Fresco.newDraweeControllerBuilder().setImageRequest(build).setControllerListener(this.mLogoControllerListener).setOldController(this.logoHolder.getController()).build());
                    return;
                }
            }
        }
        z = true;
        this.imageUri = str;
        if (z) {
        }
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
