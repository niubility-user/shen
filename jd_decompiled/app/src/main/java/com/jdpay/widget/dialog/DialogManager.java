package com.jdpay.widget.dialog;

import androidx.annotation.NonNull;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes18.dex */
public class DialogManager {
    private final List<WeakReference<BaseDialog>> actives = new ArrayList(3);

    /* loaded from: classes18.dex */
    public interface Manageable {
        DialogManager getDialogManager();
    }

    public void add(@NonNull BaseDialog baseDialog) {
        synchronized (this.actives) {
            int size = this.actives.size();
            int i2 = 0;
            while (i2 < size) {
                BaseDialog baseDialog2 = this.actives.get(i2).get();
                if (baseDialog2 == null) {
                    this.actives.remove(i2);
                    size--;
                } else if (baseDialog2.equals(baseDialog)) {
                    return;
                } else {
                    i2++;
                }
            }
            this.actives.add(new WeakReference<>(baseDialog));
        }
    }

    public void dismissAndRemoveAll() {
        synchronized (this.actives) {
            int size = this.actives.size();
            while (size > 0) {
                size--;
                BaseDialog baseDialog = this.actives.remove(0).get();
                if (baseDialog != null) {
                    baseDialog.dismissImmediate();
                }
            }
        }
    }

    public BaseDialog remove(@NonNull BaseDialog baseDialog) {
        synchronized (this.actives) {
            int size = this.actives.size();
            int i2 = 0;
            while (i2 < size) {
                BaseDialog baseDialog2 = this.actives.get(i2).get();
                if (baseDialog2 == null) {
                    this.actives.remove(i2);
                    size--;
                } else if (baseDialog2.equals(baseDialog)) {
                    this.actives.remove(i2);
                    return baseDialog;
                } else {
                    i2++;
                }
            }
            return null;
        }
    }

    public void removeAll() {
        synchronized (this.actives) {
            this.actives.clear();
        }
    }
}
