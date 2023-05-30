package com.jd.libs.hybrid.xbehavior.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.jd.libs.hybrid.base.HybridBase;
import com.jd.libs.hybrid.base.util.CommonUtils;
import com.jd.libs.hybrid.xbehavior.XBehaviorManager;
import com.jd.libs.hybrid.xbehavior.events.PageLifeCycleEvent;
import java.util.Iterator;
import java.util.LinkedList;

@Keep
/* loaded from: classes16.dex */
public class ActivityLifeCycle implements Application.ActivityLifecycleCallbacks {
    private static LinkedList<PageInfo> pages = new LinkedList<>();
    private FragmentLifeCycle fragmentLifeCycle;

    private void addPage(Activity activity) {
        String url = HybridBase.getInstance().getUrl(activity);
        String name = activity != null ? activity.getClass().getName() : "";
        PageInfo pageInfo = new PageInfo();
        pageInfo.pageName = name;
        pageInfo.url = CommonUtils.getCleanUrl(url);
        pageInfo.id = System.identityHashCode(activity);
        pages.add(pageInfo);
    }

    public static String getLastPageName() {
        if (pages.size() > 1) {
            PageInfo pageInfo = pages.get(r0.size() - 2);
            if (TextUtils.isEmpty(pageInfo.url)) {
                return pageInfo.pageName;
            }
            return pageInfo.pageName + " | " + pageInfo.url;
        }
        return "";
    }

    private void removePage(Activity activity) {
        if (pages.size() == 0) {
            return;
        }
        int identityHashCode = System.identityHashCode(activity);
        if (identityHashCode == pages.getLast().id) {
            pages.removeLast();
            return;
        }
        Iterator<PageInfo> it = pages.iterator();
        while (it.hasNext()) {
            if (it.next().id == identityHashCode) {
                it.remove();
                return;
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        if (activity instanceof FragmentActivity) {
            if (this.fragmentLifeCycle == null) {
                this.fragmentLifeCycle = new FragmentLifeCycle();
            }
            ((FragmentActivity) activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(this.fragmentLifeCycle, true);
        }
        XBehaviorManager.getInstance().dispatchEvent(new PageLifeCycleEvent(activity.getClass().getName(), PageLifeCycleEvent.STATE_CREATE, "activity"));
        try {
            addPage(activity);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(@NonNull Activity activity) {
        if ((activity instanceof FragmentActivity) && this.fragmentLifeCycle != null) {
            ((FragmentActivity) activity).getSupportFragmentManager().unregisterFragmentLifecycleCallbacks(this.fragmentLifeCycle);
        }
        LifeCycleDispatcher.dispatchOnDestroy(activity.getClass().getName(), "activity");
        try {
            removePage(activity);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(@NonNull Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(@NonNull Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(@NonNull Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(@NonNull Activity activity) {
    }
}
