package com.jingdong.jdreact.plugin.map;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import androidx.core.internal.view.SupportMenu;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.jd.dynamic.DYConstants;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.jingdong.jdsdk.constant.JshopConst;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptor;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nullable;

/* loaded from: classes13.dex */
public class JDReactMapMarkerManager extends ViewGroupManager<JDReactMapMarker> {
    private static final int ANIMATE_MARKER_TO_COORDINATE = 3;
    private static final int HIDE_INFO_WINDOW = 2;
    private static final int REDRAW = 4;
    private static final int SHOW_INFO_WINDOW = 1;
    private Map<String, JDReactMapMarkerSharedIcon> sharedIcons = new ConcurrentHashMap();

    /* loaded from: classes13.dex */
    public static class JDReactMapMarkerSharedIcon {
        private Bitmap bitmap;
        private BitmapDescriptor iconBitmapDescriptor;
        private Map<JDReactMapMarker, Boolean> markers = new WeakHashMap();
        private boolean loadImageStarted = false;

        public synchronized void addMarker(JDReactMapMarker jDReactMapMarker) {
            this.markers.put(jDReactMapMarker, Boolean.TRUE);
            BitmapDescriptor bitmapDescriptor = this.iconBitmapDescriptor;
            if (bitmapDescriptor != null) {
                jDReactMapMarker.setIconBitmapDescriptor(bitmapDescriptor, this.bitmap);
            }
        }

        public synchronized boolean hasMarker() {
            return this.markers.isEmpty();
        }

        public synchronized void removeMarker(JDReactMapMarker jDReactMapMarker) {
            this.markers.remove(jDReactMapMarker);
        }

        public synchronized boolean shouldLoadImage() {
            if (this.loadImageStarted) {
                return false;
            }
            this.loadImageStarted = true;
            return true;
        }

        public synchronized void updateIcon(BitmapDescriptor bitmapDescriptor, Bitmap bitmap) {
            this.iconBitmapDescriptor = bitmapDescriptor;
            this.bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
            if (this.markers.isEmpty()) {
                return;
            }
            for (Map.Entry<JDReactMapMarker, Boolean> entry : this.markers.entrySet()) {
                if (entry.getKey() != null) {
                    entry.getKey().setIconBitmapDescriptor(bitmapDescriptor, bitmap);
                }
            }
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("showCallout", 1, "hideCallout", 2, "animateMarkerToCoordinate", 3, "redraw", 4);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map getExportedCustomDirectEventTypeConstants() {
        Map of = MapBuilder.of("onPress", MapBuilder.of("registrationName", "onPress"), "onCalloutPress", MapBuilder.of("registrationName", "onCalloutPress"), "onDragStart", MapBuilder.of("registrationName", "onDragStart"), "onDrag", MapBuilder.of("registrationName", "onDrag"), "onDragEnd", MapBuilder.of("registrationName", "onDragEnd"));
        of.putAll(MapBuilder.of("onDragStart", MapBuilder.of("registrationName", "onDragStart"), "onDrag", MapBuilder.of("registrationName", "onDrag"), "onDragEnd", MapBuilder.of("registrationName", "onDragEnd")));
        return of;
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactMapMarker";
    }

    public JDReactMapMarkerSharedIcon getSharedIcon(String str) {
        JDReactMapMarkerSharedIcon jDReactMapMarkerSharedIcon = this.sharedIcons.get(str);
        if (jDReactMapMarkerSharedIcon == null) {
            synchronized (this) {
                jDReactMapMarkerSharedIcon = this.sharedIcons.get(str);
                if (jDReactMapMarkerSharedIcon == null) {
                    jDReactMapMarkerSharedIcon = new JDReactMapMarkerSharedIcon();
                    this.sharedIcons.put(str, jDReactMapMarkerSharedIcon);
                }
            }
        }
        return jDReactMapMarkerSharedIcon;
    }

    public void removeSharedIconIfEmpty(String str) {
        JDReactMapMarkerSharedIcon jDReactMapMarkerSharedIcon = this.sharedIcons.get(str);
        if (jDReactMapMarkerSharedIcon == null || jDReactMapMarkerSharedIcon.hasMarker()) {
            return;
        }
        synchronized (this) {
            JDReactMapMarkerSharedIcon jDReactMapMarkerSharedIcon2 = this.sharedIcons.get(str);
            if (jDReactMapMarkerSharedIcon2 != null && !jDReactMapMarkerSharedIcon2.hasMarker()) {
                this.sharedIcons.remove(str);
            }
        }
    }

    @ReactProp(name = "anchor")
    public void setAnchor(JDReactMapMarker jDReactMapMarker, ReadableMap readableMap) {
        jDReactMapMarker.setAnchor((readableMap == null || !readableMap.hasKey(JshopConst.JSHOP_PROMOTIO_X)) ? 0.5d : readableMap.getDouble(JshopConst.JSHOP_PROMOTIO_X), (readableMap == null || !readableMap.hasKey(JshopConst.JSHOP_PROMOTIO_Y)) ? 1.0d : readableMap.getDouble(JshopConst.JSHOP_PROMOTIO_Y));
    }

    @ReactProp(name = "coordinate")
    public void setCoordinate(JDReactMapMarker jDReactMapMarker, ReadableMap readableMap) {
        jDReactMapMarker.setCoordinate(readableMap);
    }

    @ReactProp(name = "description")
    public void setDescription(JDReactMapMarker jDReactMapMarker, String str) {
        jDReactMapMarker.setSnippet(str);
    }

    @ReactProp(defaultBoolean = false, name = "draggable")
    public void setDraggable(JDReactMapMarker jDReactMapMarker, boolean z) {
        jDReactMapMarker.setDraggable(z);
    }

    @ReactProp(name = "icon")
    public void setIcon(JDReactMapMarker jDReactMapMarker, @Nullable String str) {
        jDReactMapMarker.setImage(str);
    }

    @ReactProp(name = DYConstants.DY_IDENTIFIER)
    public void setIdentifier(JDReactMapMarker jDReactMapMarker, String str) {
        jDReactMapMarker.setIdentifier(str);
    }

    @ReactProp(name = "image")
    public void setImage(JDReactMapMarker jDReactMapMarker, @Nullable String str) {
        jDReactMapMarker.setImage(str);
    }

    @ReactProp(defaultFloat = 0.0f, name = "rotation")
    public void setMarkerRotation(JDReactMapMarker jDReactMapMarker, float f2) {
        jDReactMapMarker.setRotation(f2);
    }

    @ReactProp(customType = "Color", defaultInt = SupportMenu.CATEGORY_MASK, name = "pinColor")
    public void setPinColor(JDReactMapMarker jDReactMapMarker, int i2) {
        float[] fArr = new float[3];
        Color.colorToHSV(i2, fArr);
        jDReactMapMarker.setMarkerHue(fArr[0]);
    }

    @ReactProp(name = "title")
    public void setTitle(JDReactMapMarker jDReactMapMarker, String str) {
        jDReactMapMarker.setTitle(str);
    }

    @ReactProp(defaultBoolean = true, name = "tracksViewChanges")
    public void setTracksViewChanges(JDReactMapMarker jDReactMapMarker, boolean z) {
        jDReactMapMarker.setTracksViewChanges(z);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void addView(JDReactMapMarker jDReactMapMarker, View view, int i2) {
        if (view instanceof JDReactMapCallout) {
            jDReactMapMarker.setCalloutView((JDReactMapCallout) view);
            return;
        }
        super.addView((JDReactMapMarkerManager) jDReactMapMarker, view, i2);
        jDReactMapMarker.update(true);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
    public LayoutShadowNode createShadowNodeInstance() {
        return new SizeReportingShadowNode();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public JDReactMapMarker createViewInstance(ThemedReactContext themedReactContext) {
        return new JDReactMapMarker(themedReactContext, this);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(JDReactMapMarker jDReactMapMarker, int i2, @Nullable ReadableArray readableArray) {
        if (i2 == 1) {
            ((Marker) jDReactMapMarker.getFeature()).showInfoWindow();
        } else if (i2 == 2) {
            ((Marker) jDReactMapMarker.getFeature()).hideInfoWindow();
        } else if (i2 != 3) {
            if (i2 != 4) {
                return;
            }
            jDReactMapMarker.updateMarkerIcon();
        } else {
            ReadableMap map = readableArray.getMap(0);
            jDReactMapMarker.animateToCoodinate(new LatLng(Double.valueOf(map.getDouble(PdLVBody.LATITUDE)).doubleValue(), Double.valueOf(map.getDouble(PdLVBody.LONGITUDE)).doubleValue()), Integer.valueOf(readableArray.getInt(1)));
        }
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void removeViewAt(JDReactMapMarker jDReactMapMarker, int i2) {
        super.removeViewAt((JDReactMapMarkerManager) jDReactMapMarker, i2);
        jDReactMapMarker.update(true);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager
    @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
    public void setOpacity(JDReactMapMarker jDReactMapMarker, float f2) {
        super.setOpacity((JDReactMapMarkerManager) jDReactMapMarker, f2);
        jDReactMapMarker.setOpacity(f2);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void updateExtraData(JDReactMapMarker jDReactMapMarker, Object obj) {
        HashMap hashMap = (HashMap) obj;
        jDReactMapMarker.update((int) ((Float) hashMap.get("width")).floatValue(), (int) ((Float) hashMap.get("height")).floatValue());
    }
}
