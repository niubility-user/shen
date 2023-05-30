package point.interfaces;

import android.view.View;
import androidx.annotation.NonNull;

/* loaded from: classes11.dex */
public interface DJPointViewImpl extends DJPointView {
    @Override // point.interfaces.DJPointView
    boolean isVisible();

    void onAttachedToWindow();

    void onDetachedFromWindow();

    void onVisibilityChanged(@NonNull View view, int i2);
}
