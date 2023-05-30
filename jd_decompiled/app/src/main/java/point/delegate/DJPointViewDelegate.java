package point.delegate;

import android.view.View;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onVisibilityChanged(@androidx.annotation.NonNull android.view.View r4, int r5) {
        /*
            r3 = this;
            r4 = 0
            r0 = 1
            if (r5 == 0) goto L1a
            r3.cancelTimerReport()     // Catch: java.lang.Exception -> L4e
            java.util.concurrent.atomic.AtomicBoolean r1 = r3.isReporting     // Catch: java.lang.Exception -> L4e
            boolean r4 = r1.compareAndSet(r0, r4)     // Catch: java.lang.Exception -> L4e
            if (r4 == 0) goto L52
            java.util.List<java.lang.Integer> r4 = r3.visibilityFlags     // Catch: java.lang.Exception -> L4e
            r4.clear()     // Catch: java.lang.Exception -> L4e
            java.util.concurrent.atomic.AtomicBoolean r4 = r3.reportAgain     // Catch: java.lang.Exception -> L4e
            r4.set(r0)     // Catch: java.lang.Exception -> L4e
            goto L52
        L1a:
            java.util.concurrent.atomic.AtomicBoolean r1 = r3.reportAgain     // Catch: java.lang.Exception -> L4e
            boolean r1 = r1.get()     // Catch: java.lang.Exception -> L4e
            if (r1 == 0) goto L52
            java.util.List<java.lang.Integer> r1 = r3.visibilityFlags     // Catch: java.lang.Exception -> L4e
            if (r1 == 0) goto L52
            int r1 = r1.size()     // Catch: java.lang.Exception -> L4e
            r2 = 2
            if (r1 < r2) goto L42
            java.util.List<java.lang.Integer> r1 = r3.visibilityFlags     // Catch: java.lang.Exception -> L4e
            int r2 = r1.size()     // Catch: java.lang.Exception -> L4e
            int r2 = r2 - r0
            java.lang.Object r1 = r1.get(r2)     // Catch: java.lang.Exception -> L4e
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch: java.lang.Exception -> L4e
            int r1 = r1.intValue()     // Catch: java.lang.Exception -> L4e
            r2 = 8
            if (r1 != r2) goto L52
        L42:
            java.util.concurrent.atomic.AtomicBoolean r1 = r3.reportAgain     // Catch: java.lang.Exception -> L4e
            boolean r4 = r1.compareAndSet(r0, r4)     // Catch: java.lang.Exception -> L4e
            if (r4 == 0) goto L52
            r3.startTimerReport()     // Catch: java.lang.Exception -> L4e
            goto L52
        L4e:
            r4 = move-exception
            r4.printStackTrace()
        L52:
            java.util.concurrent.atomic.AtomicBoolean r4 = r3.reportAgain
            boolean r4 = r4.get()
            if (r4 == 0) goto L63
            java.util.List<java.lang.Integer> r4 = r3.visibilityFlags
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r4.add(r5)
        L63:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: point.delegate.DJPointViewDelegate.onVisibilityChanged(android.view.View, int):void");
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
