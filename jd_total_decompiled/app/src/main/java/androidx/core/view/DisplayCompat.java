package androidx.core.view;

import android.annotation.SuppressLint;
import android.app.UiModeManager;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.text.TextUtils;
import android.view.Display;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.util.Preconditions;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class DisplayCompat {
    private static final int DISPLAY_SIZE_4K_HEIGHT = 2160;
    private static final int DISPLAY_SIZE_4K_WIDTH = 3840;

    private DisplayCompat() {
    }

    private static Point getPhysicalDisplaySize(@NonNull Context context, @NonNull Display display) {
        Point parsePhysicalDisplaySizeFromSystemProperties;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 28) {
            parsePhysicalDisplaySizeFromSystemProperties = parsePhysicalDisplaySizeFromSystemProperties("sys.display-size", display);
        } else {
            parsePhysicalDisplaySizeFromSystemProperties = parsePhysicalDisplaySizeFromSystemProperties("vendor.display-size", display);
        }
        if (parsePhysicalDisplaySizeFromSystemProperties != null) {
            return parsePhysicalDisplaySizeFromSystemProperties;
        }
        if (isSonyBravia4kTv(context)) {
            return new Point(3840, 2160);
        }
        Point point2 = new Point();
        if (i2 >= 23) {
            Display.Mode mode = display.getMode();
            point2.x = mode.getPhysicalWidth();
            point2.y = mode.getPhysicalHeight();
        } else if (i2 >= 17) {
            display.getRealSize(point2);
        } else {
            display.getSize(point2);
        }
        return point2;
    }

    @NonNull
    @SuppressLint({"ArrayReturn"})
    public static ModeCompat[] getSupportedModes(@NonNull Context context, @NonNull Display display) {
        Point physicalDisplaySize = getPhysicalDisplaySize(context, display);
        if (Build.VERSION.SDK_INT >= 23) {
            Display.Mode[] supportedModes = display.getSupportedModes();
            ArrayList arrayList = new ArrayList(supportedModes.length);
            boolean z = false;
            for (int i2 = 0; i2 < supportedModes.length; i2++) {
                if (physicalSizeEquals(supportedModes[i2], physicalDisplaySize)) {
                    arrayList.add(i2, new ModeCompat(supportedModes[i2], true));
                    z = true;
                } else {
                    arrayList.add(i2, new ModeCompat(supportedModes[i2], false));
                }
            }
            if (!z) {
                arrayList.add(new ModeCompat(physicalDisplaySize));
            }
            return (ModeCompat[]) arrayList.toArray(new ModeCompat[0]);
        }
        return new ModeCompat[]{new ModeCompat(physicalDisplaySize)};
    }

    @Nullable
    private static String getSystemProperty(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod(IMantoServerRequester.GET, String.class).invoke(cls, str);
        } catch (Exception unused) {
            return null;
        }
    }

    private static boolean isSonyBravia4kTv(@NonNull Context context) {
        return isTv(context) && "Sony".equals(Build.MANUFACTURER) && Build.MODEL.startsWith("BRAVIA") && context.getPackageManager().hasSystemFeature("com.sony.dtv.hardware.panel.qfhd");
    }

    private static boolean isTv(@NonNull Context context) {
        UiModeManager uiModeManager = (UiModeManager) context.getSystemService("uimode");
        return uiModeManager != null && uiModeManager.getCurrentModeType() == 4;
    }

    private static Point parseDisplaySize(@NonNull String str) throws NumberFormatException {
        String[] split = str.trim().split(JshopConst.JSHOP_PROMOTIO_X, -1);
        if (split.length == 2) {
            int parseInt = Integer.parseInt(split[0]);
            int parseInt2 = Integer.parseInt(split[1]);
            if (parseInt > 0 && parseInt2 > 0) {
                return new Point(parseInt, parseInt2);
            }
        }
        throw new NumberFormatException();
    }

    @Nullable
    private static Point parsePhysicalDisplaySizeFromSystemProperties(@NonNull String str, @NonNull Display display) {
        if (display.getDisplayId() == 0) {
            String systemProperty = getSystemProperty(str);
            if (TextUtils.isEmpty(systemProperty)) {
                return null;
            }
            try {
                return parseDisplaySize(systemProperty);
            } catch (NumberFormatException unused) {
                return null;
            }
        }
        return null;
    }

    @RequiresApi(23)
    private static boolean physicalSizeEquals(Display.Mode mode, Point point2) {
        return (mode.getPhysicalWidth() == point2.x && mode.getPhysicalHeight() == point2.y) || (mode.getPhysicalWidth() == point2.y && mode.getPhysicalHeight() == point2.x);
    }

    /* loaded from: classes.dex */
    public static final class ModeCompat {
        private final boolean mIsNative;
        private final Display.Mode mMode;
        private final Point mPhysicalDisplaySize;

        ModeCompat(@NonNull Point point2) {
            Preconditions.checkNotNull(point2, "physicalDisplaySize == null");
            this.mIsNative = true;
            this.mPhysicalDisplaySize = point2;
            this.mMode = null;
        }

        public int getPhysicalHeight() {
            return this.mPhysicalDisplaySize.y;
        }

        public int getPhysicalWidth() {
            return this.mPhysicalDisplaySize.x;
        }

        public boolean isNative() {
            return this.mIsNative;
        }

        @Nullable
        @RequiresApi(23)
        public Display.Mode toMode() {
            return this.mMode;
        }

        @RequiresApi(23)
        ModeCompat(@NonNull Display.Mode mode, boolean z) {
            Preconditions.checkNotNull(mode, "Display.Mode == null, can't wrap a null reference");
            this.mIsNative = z;
            this.mPhysicalDisplaySize = new Point(mode.getPhysicalWidth(), mode.getPhysicalHeight());
            this.mMode = mode;
        }
    }
}
