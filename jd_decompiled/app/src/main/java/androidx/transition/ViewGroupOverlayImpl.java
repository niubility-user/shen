package androidx.transition;

import android.view.View;
import androidx.annotation.NonNull;

/* loaded from: classes.dex */
interface ViewGroupOverlayImpl extends ViewOverlayImpl {
    void add(@NonNull View view);

    void remove(@NonNull View view);
}
