package com.tencent.tencentmap.mapsdk.maps;

import android.graphics.SurfaceTexture;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import com.tencent.map.tools.Callback;
import com.tencent.map.tools.net.AdapterType;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.model.MapViewType;
import com.tencent.tencentmap.mapsdk.maps.model.OverSeaSource;
import com.tencent.tencentmap.mapsdk.maps.model.TrafficStyle;
import java.lang.reflect.Field;
import java.util.Arrays;

/* loaded from: classes9.dex */
public final class TencentMapOptions {
    public static final int LOGO_POSITION_BOTTOM_CENTER = 4;
    public static final int LOGO_POSITION_BOTTOM_LEFT = 0;
    public static final int LOGO_POSITION_BOTTOM_RIGHT = 1;
    public static final int LOGO_POSITION_TOP_CENTER = 5;
    public static final int LOGO_POSITION_TOP_LEFT = 3;
    public static final int LOGO_POSITION_TOP_RIGHT = 2;
    public static final int SCALEVIEW_POSITION_BOTTOM_CENTER = 4;
    public static final int SCALEVIEW_POSITION_BOTTOM_LEFT = 0;
    public static final int SCALEVIEW_POSITION_BOTTOM_RIGHT = 1;
    public static final int SCALEVIEW_POSITION_TOP_CENTER = 5;
    public static final int SCALEVIEW_POSITION_TOP_LEFT = 3;
    public static final int SCALEVIEW_POSITION_TOP_RIGHT = 2;
    public static final int ZOOM_POSITION_BOTTOM_LEFT = 0;
    public static final int ZOOM_POSITION_BOTTOM_RIGHT = 1;
    public static final int ZOOM_POSITION_TOP_LEFT = 3;
    public static final int ZOOM_POSITION_TOP_RIGHT = 2;
    private String customAssetsPath;
    private String customLocalPath;
    private String mCustomCacheRootPath;
    private String mCustomUserId;
    private String[] mDebugTags;
    private boolean mDisallowIntercept;
    private Object mExtSurface;
    private int mExtSurfaceHeight;
    private int mExtSurfaceWidth;
    private Callback<TencentMap> mMapCallback;
    private IMapKernel mMapKernel;
    private String mMapKey;
    private MapViewType mMapViewType;
    private Bundle mNetParams;
    private boolean mOfflineMapEnable;
    private TencentMap.OnMapLoadedCallback mOnMapLoadedCallback;
    private Object mProtocolDataDesc;
    private int mProtocolFrom;
    private String mSubId;
    private String mSubKey;
    private TrafficStyle mTrafficStyle;
    private Typeface mUserTypeface;
    private boolean isHandDrawMapEnable = false;
    private boolean isEnableMultipleInfoWindow = false;
    private String mSatelliteVersion = "0";
    private AdapterType mNetAdapterType = AdapterType.DEFAULT;
    private OverSeaSource mOverSeaSource = OverSeaSource.DEFAULT;
    private float mMapFrameRate = 60.0f;
    private boolean mOpaque = true;
    private boolean mForceHttps = true;
    private int mMaxIconMemoryCacheSize = 30;

    /* loaded from: classes9.dex */
    public interface IMapKernel {
        String name();
    }

    private void setTypeface(Typeface typeface, boolean z) {
        if (typeface == null) {
            return;
        }
        if (!z) {
            this.mUserTypeface = typeface;
            return;
        }
        try {
            Field declaredField = Typeface.class.getDeclaredField("DEFAULT");
            declaredField.setAccessible(true);
            declaredField.set(Typeface.DEFAULT, typeface);
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (NoSuchFieldException e3) {
            e3.printStackTrace();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || TencentMapOptions.class != obj.getClass()) {
            return false;
        }
        TencentMapOptions tencentMapOptions = (TencentMapOptions) obj;
        if (this.isHandDrawMapEnable == tencentMapOptions.isHandDrawMapEnable && this.mExtSurfaceWidth == tencentMapOptions.mExtSurfaceWidth && this.mExtSurfaceHeight == tencentMapOptions.mExtSurfaceHeight && this.isEnableMultipleInfoWindow == tencentMapOptions.isEnableMultipleInfoWindow && this.mProtocolFrom == tencentMapOptions.mProtocolFrom && this.mOfflineMapEnable == tencentMapOptions.mOfflineMapEnable && Float.compare(tencentMapOptions.mMapFrameRate, this.mMapFrameRate) == 0 && this.mForceHttps == tencentMapOptions.mForceHttps) {
            String str = this.customAssetsPath;
            if (str == null ? tencentMapOptions.customAssetsPath == null : str.equals(tencentMapOptions.customAssetsPath)) {
                String str2 = this.customLocalPath;
                if (str2 == null ? tencentMapOptions.customLocalPath == null : str2.equals(tencentMapOptions.customLocalPath)) {
                    String str3 = this.mCustomCacheRootPath;
                    if (str3 == null ? tencentMapOptions.mCustomCacheRootPath == null : str3.equals(tencentMapOptions.mCustomCacheRootPath)) {
                        Object obj2 = this.mExtSurface;
                        if (obj2 == null ? tencentMapOptions.mExtSurface == null : obj2.equals(tencentMapOptions.mExtSurface)) {
                            String str4 = this.mSubId;
                            if (str4 == null ? tencentMapOptions.mSubId == null : str4.equals(tencentMapOptions.mSubId)) {
                                String str5 = this.mSubKey;
                                if (str5 == null ? tencentMapOptions.mSubKey == null : str5.equals(tencentMapOptions.mSubKey)) {
                                    String str6 = this.mMapKey;
                                    if (str6 == null ? tencentMapOptions.mMapKey == null : str6.equals(tencentMapOptions.mMapKey)) {
                                        if (Arrays.equals(this.mDebugTags, tencentMapOptions.mDebugTags)) {
                                            String str7 = this.mSatelliteVersion;
                                            if (str7 == null ? tencentMapOptions.mSatelliteVersion == null : str7.equals(tencentMapOptions.mSatelliteVersion)) {
                                                if (this.mMapViewType != tencentMapOptions.mMapViewType) {
                                                    return false;
                                                }
                                                IMapKernel iMapKernel = this.mMapKernel;
                                                if (iMapKernel == null ? tencentMapOptions.mMapKernel == null : iMapKernel.equals(tencentMapOptions.mMapKernel)) {
                                                    Typeface typeface = this.mUserTypeface;
                                                    if (typeface == null ? tencentMapOptions.mUserTypeface == null : typeface.equals(tencentMapOptions.mUserTypeface)) {
                                                        Object obj3 = this.mProtocolDataDesc;
                                                        if (obj3 == null ? tencentMapOptions.mProtocolDataDesc == null : obj3.equals(tencentMapOptions.mProtocolDataDesc)) {
                                                            if (this.mNetAdapterType != tencentMapOptions.mNetAdapterType) {
                                                                return false;
                                                            }
                                                            Bundle bundle = this.mNetParams;
                                                            if (bundle == null ? tencentMapOptions.mNetParams == null : bundle.equals(tencentMapOptions.mNetParams)) {
                                                                String str8 = this.mCustomUserId;
                                                                if (str8 == null ? tencentMapOptions.mCustomUserId == null : str8.equals(tencentMapOptions.mCustomUserId)) {
                                                                    Callback<TencentMap> callback = this.mMapCallback;
                                                                    if (callback == null ? tencentMapOptions.mMapCallback == null : callback.equals(tencentMapOptions.mMapCallback)) {
                                                                        if (this.mOverSeaSource != tencentMapOptions.mOverSeaSource) {
                                                                            return false;
                                                                        }
                                                                        TrafficStyle trafficStyle = this.mTrafficStyle;
                                                                        TrafficStyle trafficStyle2 = tencentMapOptions.mTrafficStyle;
                                                                        return trafficStyle != null ? trafficStyle.equals(trafficStyle2) : trafficStyle2 == null;
                                                                    }
                                                                    return false;
                                                                }
                                                                return false;
                                                            }
                                                            return false;
                                                        }
                                                        return false;
                                                    }
                                                    return false;
                                                }
                                                return false;
                                            }
                                            return false;
                                        }
                                        return false;
                                    }
                                    return false;
                                }
                                return false;
                            }
                            return false;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public final String getCustomAssetsPath() {
        return this.customAssetsPath;
    }

    public final String getCustomCacheRootPath() {
        return this.mCustomCacheRootPath;
    }

    public final String getCustomLocalPath() {
        return this.customLocalPath;
    }

    public String getCustomUserId() {
        return this.mCustomUserId;
    }

    public String[] getDebugTags() {
        return this.mDebugTags;
    }

    public final Object getExtSurface() {
        return this.mExtSurface;
    }

    public final int getExtSurfaceHeight() {
        return this.mExtSurfaceHeight;
    }

    public final int getExtSurfaceWidth() {
        return this.mExtSurfaceWidth;
    }

    public Callback<TencentMap> getMapAsyncCallback() {
        return this.mMapCallback;
    }

    public float getMapFrameRate() {
        return this.mMapFrameRate;
    }

    public IMapKernel getMapKernel() {
        return this.mMapKernel;
    }

    public final String getMapKey() {
        return this.mMapKey;
    }

    public MapViewType getMapViewType() {
        return this.mMapViewType;
    }

    public int getMaxIconMemoryCacheSize() {
        return this.mMaxIconMemoryCacheSize;
    }

    public AdapterType getNetAdapterType() {
        return this.mNetAdapterType;
    }

    public Bundle getNetParams() {
        return this.mNetParams;
    }

    public TencentMap.OnMapLoadedCallback getOnMapLoadedCallback() {
        return this.mOnMapLoadedCallback;
    }

    public OverSeaSource getOverSeaSource() {
        return this.mOverSeaSource;
    }

    public Object getProtocolDataDesc() {
        return this.mProtocolDataDesc;
    }

    public int getProtocolFrom() {
        return this.mProtocolFrom;
    }

    public String getSatelliteVersion() {
        return this.mSatelliteVersion;
    }

    public final String getSubId() {
        return this.mSubId;
    }

    public final String getSubKey() {
        return this.mSubKey;
    }

    public TrafficStyle getTrafficStyle() {
        return this.mTrafficStyle;
    }

    public Typeface getTypeface() {
        Typeface typeface = this.mUserTypeface;
        return typeface == null ? Typeface.DEFAULT : typeface;
    }

    public int hashCode() {
        String str = this.customAssetsPath;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.customLocalPath;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.mCustomCacheRootPath;
        int hashCode3 = (((hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31) + (this.isHandDrawMapEnable ? 1 : 0)) * 31;
        Object obj = this.mExtSurface;
        int hashCode4 = (((((hashCode3 + (obj != null ? obj.hashCode() : 0)) * 31) + this.mExtSurfaceWidth) * 31) + this.mExtSurfaceHeight) * 31;
        String str4 = this.mSubId;
        int hashCode5 = (hashCode4 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.mSubKey;
        int hashCode6 = (hashCode5 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.mMapKey;
        int hashCode7 = (((((hashCode6 + (str6 != null ? str6.hashCode() : 0)) * 31) + (this.isEnableMultipleInfoWindow ? 1 : 0)) * 31) + Arrays.hashCode(this.mDebugTags)) * 31;
        String str7 = this.mSatelliteVersion;
        int hashCode8 = (hashCode7 + (str7 != null ? str7.hashCode() : 0)) * 31;
        MapViewType mapViewType = this.mMapViewType;
        int hashCode9 = (hashCode8 + (mapViewType != null ? mapViewType.hashCode() : 0)) * 31;
        IMapKernel iMapKernel = this.mMapKernel;
        int hashCode10 = (hashCode9 + (iMapKernel != null ? iMapKernel.hashCode() : 0)) * 31;
        Typeface typeface = this.mUserTypeface;
        int hashCode11 = (((hashCode10 + (typeface != null ? typeface.hashCode() : 0)) * 31) + this.mProtocolFrom) * 31;
        Object obj2 = this.mProtocolDataDesc;
        int hashCode12 = (hashCode11 + (obj2 != null ? obj2.hashCode() : 0)) * 31;
        AdapterType adapterType = this.mNetAdapterType;
        int hashCode13 = (hashCode12 + (adapterType != null ? adapterType.hashCode() : 0)) * 31;
        Bundle bundle = this.mNetParams;
        int hashCode14 = (hashCode13 + (bundle != null ? bundle.hashCode() : 0)) * 31;
        String str8 = this.mCustomUserId;
        int hashCode15 = (hashCode14 + (str8 != null ? str8.hashCode() : 0)) * 31;
        Callback<TencentMap> callback = this.mMapCallback;
        int hashCode16 = (((hashCode15 + (callback != null ? callback.hashCode() : 0)) * 31) + (this.mOfflineMapEnable ? 1 : 0)) * 31;
        OverSeaSource overSeaSource = this.mOverSeaSource;
        int hashCode17 = (hashCode16 + (overSeaSource != null ? overSeaSource.hashCode() : 0)) * 31;
        TrafficStyle trafficStyle = this.mTrafficStyle;
        int hashCode18 = (hashCode17 + (trafficStyle != null ? trafficStyle.hashCode() : 0)) * 31;
        float f2 = this.mMapFrameRate;
        return ((hashCode18 + (f2 != 0.0f ? Float.floatToIntBits(f2) : 0)) * 31) + (this.mForceHttps ? 1 : 0);
    }

    public boolean isDisallowInterceptTouchEvent() {
        return this.mDisallowIntercept;
    }

    public boolean isForceHttps() {
        return this.mForceHttps;
    }

    public final boolean isHandDrawMapEnable() {
        return this.isHandDrawMapEnable;
    }

    public final boolean isMultipleInfoWindowEnable() {
        return this.isEnableMultipleInfoWindow;
    }

    public boolean isOfflineMapEnable() {
        return this.mOfflineMapEnable;
    }

    public boolean isOpaque() {
        return this.mOpaque;
    }

    public final TencentMapOptions openDebugLogByTags(String... strArr) {
        this.mDebugTags = strArr;
        return this;
    }

    public final TencentMapOptions setCustomAssetsPath(String str) {
        this.customAssetsPath = str;
        return this;
    }

    public final TencentMapOptions setCustomCacheRootPath(String str) {
        this.mCustomCacheRootPath = str;
        return this;
    }

    public final TencentMapOptions setCustomLocalPath(String str) {
        this.customLocalPath = str;
        return this;
    }

    public TencentMapOptions setCustomUserID(String str) {
        this.mCustomUserId = str;
        return this;
    }

    public TencentMapOptions setDisallowInterceptTouchEvent(boolean z) {
        this.mDisallowIntercept = z;
        return this;
    }

    public final TencentMapOptions setExtSurface(Object obj) {
        if ((obj instanceof Surface) || (obj instanceof SurfaceTexture) || (obj instanceof SurfaceHolder)) {
            this.mExtSurface = obj;
            return this;
        }
        throw new IllegalArgumentException("Parameter Surface should be Surface,SurfaceTexture or SurfaceHolder");
    }

    public final TencentMapOptions setExtSurfaceDimension(int i2, int i3) {
        this.mExtSurfaceWidth = i2;
        this.mExtSurfaceHeight = i3;
        return this;
    }

    public TencentMapOptions setForceHttps(boolean z) {
        this.mForceHttps = z;
        return this;
    }

    public TencentMapOptions setGetMapAsync(Callback<TencentMap> callback) {
        this.mMapCallback = callback;
        return this;
    }

    public final TencentMapOptions setHandDrawMapEnable(boolean z) {
        this.isHandDrawMapEnable = z;
        return this;
    }

    public TencentMapOptions setMapFrameRate(float f2) {
        this.mMapFrameRate = f2;
        return this;
    }

    public final TencentMapOptions setMapKernel(IMapKernel iMapKernel) {
        this.mMapKernel = iMapKernel;
        return this;
    }

    public final TencentMapOptions setMapKey(String str) {
        this.mMapKey = str;
        return this;
    }

    public final TencentMapOptions setMapViewType(MapViewType mapViewType) {
        this.mMapViewType = mapViewType;
        return this;
    }

    public TencentMapOptions setMaxIconMemoryCacheSize(int i2) {
        this.mMaxIconMemoryCacheSize = i2;
        return this;
    }

    public final TencentMapOptions setMultipleInfoWindowEnable(boolean z) {
        this.isEnableMultipleInfoWindow = z;
        return this;
    }

    public TencentMapOptions setNetAdapter(AdapterType adapterType, Bundle bundle) {
        this.mNetAdapterType = adapterType;
        this.mNetParams = bundle;
        return this;
    }

    public TencentMapOptions setOfflineMapEnable(boolean z) {
        this.mOfflineMapEnable = z;
        return this;
    }

    public TencentMapOptions setOnMapLoadedCallback(TencentMap.OnMapLoadedCallback onMapLoadedCallback) {
        this.mOnMapLoadedCallback = onMapLoadedCallback;
        return this;
    }

    public TencentMapOptions setOpaque(boolean z) {
        this.mOpaque = z;
        return this;
    }

    public TencentMapOptions setOverSeaSource(OverSeaSource overSeaSource) {
        this.mOverSeaSource = overSeaSource;
        return this;
    }

    public TencentMapOptions setSatelliteVersion(String str) {
        this.mSatelliteVersion = str;
        return this;
    }

    public TencentMapOptions setServiceProtocol(int i2, Object obj) {
        this.mProtocolFrom = i2;
        this.mProtocolDataDesc = obj;
        return this;
    }

    public final TencentMapOptions setSubInfo(String str, String str2) {
        if (str != null) {
            this.mSubKey = str.trim();
        } else {
            this.mSubKey = "";
        }
        if (str2 != null) {
            this.mSubId = str2.trim();
        } else {
            this.mSubId = "";
        }
        return this;
    }

    public TencentMapOptions setTrafficStyle(TrafficStyle trafficStyle) {
        this.mTrafficStyle = trafficStyle;
        return this;
    }

    public final TencentMapOptions setTypeface(Typeface typeface) {
        setTypeface(typeface, false);
        return this;
    }

    public String toString() {
        return "TencentMapOptions{customAssetsPath='" + this.customAssetsPath + "', customLocalPath='" + this.customLocalPath + "', mCustomCacheRootPath='" + this.mCustomCacheRootPath + "', isHandDrawMapEnable=" + this.isHandDrawMapEnable + ", mExtSurface=" + this.mExtSurface + ", mExtSurfaceWidth=" + this.mExtSurfaceWidth + ", mExtSurfaceHeight=" + this.mExtSurfaceHeight + ", mSubId='" + this.mSubId + "', mSubKey='" + this.mSubKey + "', mMapKey='" + this.mMapKey + "', isEnableMultipleInfoWindow=" + this.isEnableMultipleInfoWindow + ", mDebugTags=" + Arrays.toString(this.mDebugTags) + ", mSatelliteVersion='" + this.mSatelliteVersion + "', mMapViewType=" + this.mMapViewType + ", mMapKernel=" + this.mMapKernel + ", mUserTypeface=" + this.mUserTypeface + ", mProtocolFrom=" + this.mProtocolFrom + ", mProtocolDataDesc=" + this.mProtocolDataDesc + ", mNetAdapterType=" + this.mNetAdapterType + ", mNetParams=" + this.mNetParams + ", mCustomUserId='" + this.mCustomUserId + "', mMapCallback=" + this.mMapCallback + ", mOfflineMapEnable=" + this.mOfflineMapEnable + ", mOverSeaSource=" + this.mOverSeaSource + ", mTrafficStyle=" + this.mTrafficStyle + ", mMapFrameRate=" + this.mMapFrameRate + ", mForceHttps=" + this.mForceHttps + ", mDisallowIntercept=" + this.mDisallowIntercept + '}';
    }
}
