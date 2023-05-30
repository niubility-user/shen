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
@ListenerClass(method = {@ListenerMethod(defaultReturn = DYConstants.DY_TRUE, name = "onEditorAction", parameters = {"android.widget.TextView", "int", "android.view.KeyEvent"}, returnType = "boolean")}, setter = "setOnEditorActionListener", targetType = "android.widget.TextView", type = "android.widget.TextView.OnEditorActionListener")
/* loaded from: classes.dex */
public @interface OnEditorAction {
    @IdRes
    int[] value() default {-1};
}
