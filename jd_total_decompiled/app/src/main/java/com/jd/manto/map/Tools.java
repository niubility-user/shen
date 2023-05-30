package com.jd.manto.map;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.sdk.api.IPermission;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoPermission;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes17.dex */
public final class Tools {
    public static final String JD_LOCATION_ID = "e62e6209b70a03f9ba03f225d76e8f78";
    public static final String JD_SCENE_ID = "locService";
    public static String[] PERMISSIONS = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};

    static Bitmap getBitmap(InputStream inputStream) {
        if (inputStream != null) {
            try {
                Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused) {
                    }
                }
                return decodeStream;
            } catch (Exception unused2) {
                if (inputStream == null) {
                    return null;
                }
            } catch (Throwable th) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused3) {
                    }
                }
                throw th;
            }
        } else if (inputStream == null) {
            return null;
        }
        try {
            inputStream.close();
        } catch (IOException unused4) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getSizeByPx(Context context, Number number) {
        if (context == null) {
            return number.intValue();
        }
        return Math.round(number.intValue() * MantoDensityUtils.getDensity(context));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final boolean hasLocationPermission() {
        return MantoPermission.hasLocationPermissionWithScene("locService", JD_LOCATION_ID);
    }

    public static boolean isLatLngValid(double d, double d2) {
        return (!Double.isNaN(d) && (Math.abs(d) > 90.0d ? 1 : (Math.abs(d) == 90.0d ? 0 : -1)) <= 0) && (!Double.isNaN(d2) && !Double.isInfinite(d2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final int parseColor(String str, int i2) {
        return (int) parseLongColor(str, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float parseFloat(String str, float f2) {
        if (str != null) {
            try {
                return Float.parseFloat(str);
            } catch (Throwable unused) {
                return f2;
            }
        }
        return f2;
    }

    private static long parseLongColor(String str, long j2) {
        if (TextUtils.isEmpty(str)) {
            return j2;
        }
        if (str.startsWith("#") && str.length() == 4) {
            StringBuilder sb = new StringBuilder(str);
            sb.insert(2, str.charAt(1));
            sb.insert(4, str.charAt(2));
            sb.insert(6, str.charAt(3));
            str = sb.toString();
        }
        try {
            return Color.parseColor(str);
        } catch (Exception unused) {
            MantoLog.e("", String.format("Failed to parse color: %s", str));
            return j2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final void requireLocationPermission(Activity activity, final MantoResultCallBack mantoResultCallBack) {
        MantoPermission.requestPermissions(activity, PERMISSIONS, new IPermission.PermissionCallBack() { // from class: com.jd.manto.map.Tools.1
            @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
            public void onDenied() {
                MantoResultCallBack.this.onFailed(null);
            }

            @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
            public void onGranted() {
                MantoResultCallBack.this.onSuccess(null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final void unbindParent(View view) {
        if (view == null || !ViewGroup.class.isInstance(view.getParent())) {
            return;
        }
        ((ViewGroup) view.getParent()).removeView(view);
    }
}
