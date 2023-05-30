package butterknife;

import androidx.annotation.IdRes;
import butterknife.internal.ListenerClass;
import butterknife.internal.ListenerMethod;
import com.jd.dynamic.DYConstants;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ListenerClass(method = {@ListenerMethod(defaultReturn = DYConstants.DY_TRUE, name = DYConstants.DY_ON_TOUCH, parameters = {"android.view.View", "android.view.MotionEvent"}, returnType = "boolean")}, setter = "setOnTouchListener", targetType = "android.view.View", type = "android.view.View.OnTouchListener")
/* loaded from: classes.dex */
public @interface OnTouch {
    @IdRes
    int[] value() default {-1};
}
