package androidx.transition;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.util.Property;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import java.lang.reflect.Field;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ViewUtils {
    static final Property<View, Rect> CLIP_BOUNDS;
    private static final ViewUtilsBase IMPL;
    private static final String TAG = "ViewUtils";
    static final Property<View, Float> TRANSITION_ALPHA;
    private static final int VISIBILITY_MASK = 12;
    private static Field sViewFlagsField;
    private static boolean sViewFlagsFieldFetched;

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 22) {
            IMPL = new ViewUtilsApi22();
        } else if (i2 >= 21) {
            IMPL = new ViewUtilsApi21();
        } else if (i2 >= 19) {
            IMPL = new ViewUtilsApi19();
        } else {
            IMPL = new ViewUtilsBase();
        }
        TRANSITION_ALPHA = new Property<View, Float>(Float.class, "translationAlpha") { // from class: androidx.transition.ViewUtils.1
            @Override // android.util.Property
            public Float get(View view) {
                return Float.valueOf(ViewUtils.getTransitionAlpha(view));
            }

            @Override // android.util.Property
            public void set(View view, Float f2) {
                ViewUtils.setTransitionAlpha(view, f2.floatValue());
            }
        };
        CLIP_BOUNDS = new Property<View, Rect>(Rect.class, "clipBounds") { // from class: androidx.transition.ViewUtils.2
            @Override // android.util.Property
            public Rect get(View view) {
                return ViewCompat.getClipBounds(view);
            }

            @Override // android.util.Property
            public void set(View view, Rect rect) {
                ViewCompat.setClipBounds(view, rect);
            }
        };
    }

    private ViewUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void clearNonTransitionAlpha(@NonNull View view) {
        IMPL.clearNonTransitionAlpha(view);
    }

    private static void fetchViewFlagsField() {
        if (sViewFlagsFieldFetched) {
            return;
        }
        try {
            Field declaredField = View.class.getDeclaredField("mViewFlags");
            sViewFlagsField = declaredField;
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException unused) {
        }
        sViewFlagsFieldFetched = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ViewOverlayImpl getOverlay(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= 18) {
            return new ViewOverlayApi18(view);
        }
        return ViewOverlayApi14.createFrom(view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float getTransitionAlpha(@NonNull View view) {
        return IMPL.getTransitionAlpha(view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static WindowIdImpl getWindowId(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= 18) {
            return new WindowIdApi18(view);
        }
        return new WindowIdApi14(view.getWindowToken());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void saveNonTransitionAlpha(@NonNull View view) {
        IMPL.saveNonTransitionAlpha(view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setAnimationMatrix(@NonNull View view, @Nullable Matrix matrix) {
        IMPL.setAnimationMatrix(view, matrix);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setLeftTopRightBottom(@NonNull View view, int i2, int i3, int i4, int i5) {
        IMPL.setLeftTopRightBottom(view, i2, i3, i4, i5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setTransitionAlpha(@NonNull View view, float f2) {
        IMPL.setTransitionAlpha(view, f2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setTransitionVisibility(@NonNull View view, int i2) {
        fetchViewFlagsField();
        Field field = sViewFlagsField;
        if (field != null) {
            try {
                sViewFlagsField.setInt(view, i2 | (field.getInt(view) & (-13)));
            } catch (IllegalAccessException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void transformMatrixToGlobal(@NonNull View view, @NonNull Matrix matrix) {
        IMPL.transformMatrixToGlobal(view, matrix);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void transformMatrixToLocal(@NonNull View view, @NonNull Matrix matrix) {
        IMPL.transformMatrixToLocal(view, matrix);
    }
}
