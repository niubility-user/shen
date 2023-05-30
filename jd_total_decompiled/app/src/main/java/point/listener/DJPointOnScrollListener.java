package point.listener;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.pdj.libdjbasecore.utils.DJCastUtil;
import java.lang.ref.WeakReference;
import java.util.List;
import point.DJPointVisibleUtil;
import point.interfaces.DJPointListener;

/* loaded from: classes11.dex */
public class DJPointOnScrollListener extends RecyclerView.OnScrollListener implements DJPointListener {
    private WeakReference calculateViewWeakReference;
    private DJPointVisibleUtil djPointVisibleUtil;
    private List<String> filterPointDataList;
    private int orientation;

    public DJPointOnScrollListener(DJPointVisibleUtil dJPointVisibleUtil, int i2) {
        this.orientation = 1;
        this.djPointVisibleUtil = dJPointVisibleUtil;
        this.orientation = i2;
    }

    private ViewGroup getCalculateView() {
        WeakReference weakReference = this.calculateViewWeakReference;
        if (weakReference == null || weakReference.get() == null) {
            return null;
        }
        return (ViewGroup) DJCastUtil.convert(this.calculateViewWeakReference.get());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrolled(@NonNull RecyclerView recyclerView, int i2, int i3) {
        super.onScrolled(recyclerView, i2, i3);
        if (this.orientation == 1) {
            if (Math.abs(i3) <= 0 || this.djPointVisibleUtil == null) {
                return;
            }
            ViewGroup calculateView = getCalculateView();
            DJPointVisibleUtil dJPointVisibleUtil = this.djPointVisibleUtil;
            RecyclerView recyclerView2 = recyclerView;
            if (calculateView != null) {
                recyclerView2 = calculateView;
            }
            dJPointVisibleUtil.calculateRectVisible(recyclerView2, this.filterPointDataList, true);
            this.filterPointDataList = null;
        } else if (Math.abs(i2) <= 0 || this.djPointVisibleUtil == null) {
        } else {
            ViewGroup calculateView2 = getCalculateView();
            DJPointVisibleUtil dJPointVisibleUtil2 = this.djPointVisibleUtil;
            RecyclerView recyclerView3 = recyclerView;
            if (calculateView2 != null) {
                recyclerView3 = calculateView2;
            }
            dJPointVisibleUtil2.calculateRectVisible(recyclerView3, this.filterPointDataList, true);
            this.filterPointDataList = null;
        }
    }

    @Override // point.interfaces.DJPointListener
    public void setFilterPointDataList(List<String> list) {
        this.filterPointDataList = list;
    }

    public DJPointOnScrollListener(DJPointVisibleUtil dJPointVisibleUtil, int i2, ViewGroup viewGroup) {
        this.orientation = 1;
        this.djPointVisibleUtil = dJPointVisibleUtil;
        this.orientation = i2;
        this.calculateViewWeakReference = new WeakReference(viewGroup);
    }
}
