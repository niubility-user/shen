package com.jd.libs.hybrid.xbehavior.lifecycle;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.jd.libs.hybrid.xbehavior.XBehaviorManager;
import com.jd.libs.hybrid.xbehavior.events.PageLifeCycleEvent;
import com.jingdong.common.XView2.common.XView2Constants;

/* loaded from: classes16.dex */
public class FragmentLifeCycle extends FragmentManager.FragmentLifecycleCallbacks {
    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentCreated(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @Nullable Bundle bundle) {
        XBehaviorManager.getInstance().dispatchEvent(new PageLifeCycleEvent(fragment.getClass().getName(), PageLifeCycleEvent.STATE_CREATE, XView2Constants.FRAGMENT));
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentDestroyed(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        super.onFragmentDestroyed(fragmentManager, fragment);
        LifeCycleDispatcher.dispatchOnDestroy(fragment.getClass().getName(), XView2Constants.FRAGMENT);
    }
}
