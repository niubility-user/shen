package point.delegate;

import android.view.View;
import androidx.annotation.NonNull;
import com.jingdong.pdj.libdjbasecore.R;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import point.DJPointHandler;
import point.DJPointVisibleUtil;
import point.interfaces.DJPointData;
import point.interfaces.DJPointViewImpl;

/* loaded from: classes11.dex */
public class DJPointViewDelegate implements DJPointViewImpl {
    private DJPointVisibleUtil djPointVisibleUtil;
    private View pointView;
    private AtomicBoolean isReporting = new AtomicBoolean();
    private AtomicBoolean reportAgain = new AtomicBoolean();
    private List<Integer> visibilityFlags = new ArrayList();
    private Runnable runnable = new Runnable() { // from class: point.delegate.DJPointViewDelegate.1
        @Override // java.lang.Runnable
        public void run() {
            DJPointViewDelegate.this.isReporting.set(false);
            try {
                if (DJPointViewDelegate.this.djPointVisibleUtil != null && DJPointViewDelegate.this.djPointVisibleUtil.isPointView(DJPointViewDelegate.this.pointView) && DJPointViewDelegate.this.isVisible()) {
                    DJPointViewDelegate.this.djPointVisibleUtil.report(DJPointViewDelegate.this.pointView.getContext(), DJPointViewDelegate.this.djPointVisibleUtil.getEpMTADelayDuration() + "\u6beb\u79d2\u540e\u66dd\u5149", (DJPointData) DJPointViewDelegate.this.pointView.getTag(R.id.pointDataKey));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    };

    public DJPointViewDelegate(View view) {
        this.pointView = view;
    }

    @Override // point.interfaces.DJPointView
    public void AgainAttach() {
    }

    @Override // point.interfaces.DJPointView
    public void cancelTimerReport() {
        try {
            DJPointHandler.getInstance().getHandler().removeCallbacks(this.runnable);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // point.interfaces.DJPointViewImpl, point.interfaces.DJPointView
    public boolean isVisible() {
        try {
            View view = this.pointView;
            if (view != null) {
                return view.getVisibility() == 0;
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @Override // point.interfaces.DJPointViewImpl
    public void onAttachedToWindow() {
        try {
            DJPointVisibleUtil dJPointVisibleUtil = this.djPointVisibleUtil;
            if (dJPointVisibleUtil != null) {
                dJPointVisibleUtil.registerView(this.pointView);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // point.interfaces.DJPointViewImpl
    public void onDetachedFromWindow() {
        try {
            cancelTimerReport();
            DJPointVisibleUtil dJPointVisibleUtil = this.djPointVisibleUtil;
            if (dJPointVisibleUtil != null) {
                dJPointVisibleUtil.unregisterView(this.pointView);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0040, code lost:
        if (r1.get(r1.size() - 1).intValue() == 8) goto L15;
     */
    @Override // point.interfaces.DJPointViewImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onVisibilityChanged(@NonNull View view, int i2) {
        List<Integer> list;
        try {
            if (i2 != 0) {
                cancelTimerReport();
                if (this.isReporting.compareAndSet(true, false)) {
                    this.visibilityFlags.clear();
                    this.reportAgain.set(true);
                }
            } else if (this.reportAgain.get() && (list = this.visibilityFlags) != null) {
                if (list.size() >= 2) {
                    List<Integer> list2 = this.visibilityFlags;
                }
                if (this.reportAgain.compareAndSet(true, false)) {
                    startTimerReport();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (this.reportAgain.get()) {
            this.visibilityFlags.add(Integer.valueOf(i2));
        }
    }

    @Override // point.interfaces.DJPointView
    public void setDJPointVisibleUtil(DJPointVisibleUtil dJPointVisibleUtil) {
        this.djPointVisibleUtil = dJPointVisibleUtil;
    }

    @Override // point.interfaces.DJPointView
    public void startTimerReport() {
        try {
            if (this.djPointVisibleUtil != null) {
                this.isReporting.set(DJPointHandler.getInstance().getHandler().postDelayed(this.runnable, this.djPointVisibleUtil.getEpMTADelayDuration()));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
