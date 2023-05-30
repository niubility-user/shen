package point;

import android.os.Handler;
import android.os.Looper;

/* loaded from: classes11.dex */
public class DJPointHandler {
    private static volatile DJPointHandler instance;
    private Handler handler = new Handler(Looper.getMainLooper());

    public static DJPointHandler getInstance() {
        if (instance == null) {
            synchronized (DJPointHandler.class) {
                if (instance == null) {
                    instance = new DJPointHandler();
                }
            }
        }
        return instance;
    }

    public Handler getHandler() {
        if (this.handler == null) {
            this.handler = new Handler(Looper.getMainLooper());
        }
        return this.handler;
    }
}
