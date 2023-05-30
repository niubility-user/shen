package point.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import java.util.ArrayList;
import java.util.List;
import point.interfaces.DJOnScrollViewChangeListener;

/* loaded from: classes11.dex */
public class DJPointNestedScrollView extends NestedScrollView {
    private List<DJOnScrollViewChangeListener> mScrollListeners;

    public DJPointNestedScrollView(Context context) {
        super(context);
    }

    public void addOnScrollListener(@NonNull DJOnScrollViewChangeListener dJOnScrollViewChangeListener) {
        if (this.mScrollListeners == null) {
            this.mScrollListeners = new ArrayList();
        }
        this.mScrollListeners.add(dJOnScrollViewChangeListener);
    }

    public void clearOnScrollListeners() {
        List<DJOnScrollViewChangeListener> list = this.mScrollListeners;
        if (list != null) {
            list.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.core.widget.NestedScrollView, android.view.View
    public void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
        List<DJOnScrollViewChangeListener> list = this.mScrollListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mScrollListeners.get(size).onScrollChange(this, i2, i3, i4, i5);
            }
        }
    }

    public void removeOnScrollListener(@NonNull DJOnScrollViewChangeListener dJOnScrollViewChangeListener) {
        List<DJOnScrollViewChangeListener> list = this.mScrollListeners;
        if (list != null) {
            list.remove(dJOnScrollViewChangeListener);
        }
    }

    public DJPointNestedScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DJPointNestedScrollView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
