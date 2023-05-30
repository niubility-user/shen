package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Matrix;
import android.os.Build;
import android.widget.ImageView;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
class ImageViewUtils {
    private static final String TAG = "ImageViewUtils";
    private static Method sAnimateTransformMethod;
    private static boolean sAnimateTransformMethodFetched;

    private ImageViewUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void animateTransform(ImageView imageView, Matrix matrix) {
        if (Build.VERSION.SDK_INT < 21) {
            imageView.setImageMatrix(matrix);
            return;
        }
        fetchAnimateTransformMethod();
        Method method = sAnimateTransformMethod;
        if (method != null) {
            try {
                method.invoke(imageView, matrix);
            } catch (IllegalAccessException unused) {
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2.getCause());
            }
        }
    }

    private static void fetchAnimateTransformMethod() {
        if (sAnimateTransformMethodFetched) {
            return;
        }
        try {
            Method declaredMethod = ImageView.class.getDeclaredMethod("animateTransform", Matrix.class);
            sAnimateTransformMethod = declaredMethod;
            declaredMethod.setAccessible(true);
        } catch (NoSuchMethodException unused) {
        }
        sAnimateTransformMethodFetched = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void reserveEndAnimateTransform(final ImageView imageView, Animator animator) {
        if (Build.VERSION.SDK_INT < 21) {
            animator.addListener(new AnimatorListenerAdapter() { // from class: androidx.transition.ImageViewUtils.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator2) {
                    ImageView imageView2 = imageView;
                    int i2 = R.id.save_scale_type;
                    ImageView.ScaleType scaleType = (ImageView.ScaleType) imageView2.getTag(i2);
                    imageView.setScaleType(scaleType);
                    imageView.setTag(i2, null);
                    if (scaleType == ImageView.ScaleType.MATRIX) {
                        ImageView imageView3 = imageView;
                        int i3 = R.id.save_image_matrix;
                        imageView3.setImageMatrix((Matrix) imageView3.getTag(i3));
                        imageView.setTag(i3, null);
                    }
                    animator2.removeListener(this);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void startAnimateTransform(ImageView imageView) {
        if (Build.VERSION.SDK_INT < 21) {
            ImageView.ScaleType scaleType = imageView.getScaleType();
            imageView.setTag(R.id.save_scale_type, scaleType);
            ImageView.ScaleType scaleType2 = ImageView.ScaleType.MATRIX;
            if (scaleType == scaleType2) {
                imageView.setTag(R.id.save_image_matrix, imageView.getImageMatrix());
            } else {
                imageView.setScaleType(scaleType2);
            }
            imageView.setImageMatrix(MatrixUtils.IDENTITY_MATRIX);
        }
    }
}
