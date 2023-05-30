package point.listener;

import android.view.ViewGroup;
import com.jingdong.pdj.libdjbasecore.utils.DJCastUtil;
import java.lang.ref.WeakReference;
import java.util.List;
import point.DJPointVisibleUtil;
import point.interfaces.DJOnScrollViewChangeListener;
import point.interfaces.DJPointListener;

/* loaded from: classes11.dex */
public class DJPointOnScrollViewListener implements DJOnScrollViewChangeListener, DJPointListener {
    private WeakReference calculateViewWeakReference;
    private DJPointVisibleUtil djPointVisibleUtil;
    private List<String> filterPointDataList;
    private int orientation;

    public DJPointOnScrollViewListener(DJPointVisibleUtil dJPointVisibleUtil, int i2) {
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

    @Override // point.interfaces.DJOnScrollViewChangeListener
    public void onScrollChange(ViewGroup viewGroup, int i2, int i3, int i4, int i5) {
        if (this.orientation == 1) {
            if (Math.abs(i3) <= 0 || this.djPointVisibleUtil == null) {
                return;
            }
            ViewGroup calculateView = getCalculateView();
            DJPointVisibleUtil dJPointVisibleUtil = this.djPointVisibleUtil;
            if (calculateView != null) {
                viewGroup = calculateView;
            }
            dJPointVisibleUtil.calculateRectVisible(viewGroup, this.filterPointDataList, true);
            this.filterPointDataList = null;
        } else if (Math.abs(i2) <= 0 || this.djPointVisibleUtil == null) {
        } else {
            ViewGroup calculateView2 = getCalculateView();
            DJPointVisibleUtil dJPointVisibleUtil2 = this.djPointVisibleUtil;
            if (calculateView2 != null) {
                viewGroup = calculateView2;
            }
            dJPointVisibleUtil2.calculateRectVisible(viewGroup, this.filterPointDataList, true);
            this.filterPointDataList = null;
        }
    }

    @Override // point.interfaces.DJPointListener
    public void setFilterPointDataList(List<String> list) {
        this.filterPointDataList = list;
    }

    public DJPointOnScrollViewListener(DJPointVisibleUtil dJPointVisibleUtil, int i2, ViewGroup viewGroup) {
        this.orientation = 1;
        this.djPointVisibleUtil = dJPointVisibleUtil;
        this.orientation = i2;
        this.calculateViewWeakReference = new WeakReference(viewGroup);
    }
}
