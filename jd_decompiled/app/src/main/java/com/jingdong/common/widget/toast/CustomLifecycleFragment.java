package com.jingdong.common.widget.toast;

import android.app.Fragment;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes12.dex */
public class CustomLifecycleFragment extends Fragment {
    private List<ICustomLifecycleListener> lifecycleListeners = null;

    private void notifyOnStart() {
        List<ICustomLifecycleListener> list = this.lifecycleListeners;
        if (list == null || list.isEmpty()) {
            return;
        }
        Iterator<ICustomLifecycleListener> it = this.lifecycleListeners.iterator();
        while (it.hasNext()) {
            it.next().onStart();
        }
    }

    private void notifyOnStop() {
        List<ICustomLifecycleListener> list = this.lifecycleListeners;
        if (list == null || list.isEmpty()) {
            return;
        }
        Iterator<ICustomLifecycleListener> it = this.lifecycleListeners.iterator();
        while (it.hasNext()) {
            it.next().onStop();
        }
    }

    private void removeLifecycleListener() {
        List<ICustomLifecycleListener> list = this.lifecycleListeners;
        if (list != null) {
            list.clear();
            this.lifecycleListeners = null;
        }
    }

    public void addLifecycleListener(ICustomLifecycleListener iCustomLifecycleListener) {
        if (iCustomLifecycleListener != null) {
            if (this.lifecycleListeners == null) {
                this.lifecycleListeners = new ArrayList();
            }
            this.lifecycleListeners.add(iCustomLifecycleListener);
        }
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        removeLifecycleListener();
    }

    @Override // android.app.Fragment
    public void onStart() {
        super.onStart();
        notifyOnStart();
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
        notifyOnStop();
    }
}
