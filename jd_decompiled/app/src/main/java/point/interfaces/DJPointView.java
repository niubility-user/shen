package point.interfaces;

import point.DJPointVisibleUtil;

/* loaded from: classes11.dex */
public interface DJPointView {
    void AgainAttach();

    void cancelTimerReport();

    boolean isVisible();

    void setDJPointVisibleUtil(DJPointVisibleUtil dJPointVisibleUtil);

    void startTimerReport();
}
