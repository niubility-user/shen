package com.jingdong.jdsdk.network.toolbox;

import android.app.Activity;
import android.view.ViewGroup;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.utils.HttpUiBaseHelper;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class HttpGroupSetting {
    public static final int PRIORITY_FILE = 500;
    public static final int PRIORITY_IMAGE = 5000;
    public static final int PRIORITY_JSON = 1000;
    public static final int TOP_PRIORITY = 10000;
    public static final int TYPE_ADVERTISE = 6000;
    public static final int TYPE_FILE = 500;
    public static final int TYPE_IMAGE = 5000;
    public static final int TYPE_JSON = 1000;
    public static final int TYPE_JSON_ARRAY = 1001;
    private HttpGroup.HttpErrorAlertControllerFactory httpErrorAlertControllerFactory;
    private HttpUiBaseHelper httpUiHelper;
    private WeakReference<Activity> myActivity;
    private int priority;
    private WeakReference<ViewGroup> progressBarRootLayout;
    private int type;

    public HttpGroupSetting(HttpUiBaseHelper httpUiBaseHelper) {
        this.httpUiHelper = httpUiBaseHelper;
    }

    public HttpGroup.HttpErrorAlertControllerFactory getHttpErrorAlertControllerFactory() {
        return this.httpErrorAlertControllerFactory;
    }

    public HttpUiBaseHelper getHttpUiHelper() {
        return this.httpUiHelper;
    }

    public Activity getMyActivity() {
        WeakReference<Activity> weakReference = this.myActivity;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public String getMyActivityTag() {
        Activity myActivity = getMyActivity();
        if (myActivity != null) {
            return myActivity.toString();
        }
        return null;
    }

    public int getPriority() {
        return this.priority;
    }

    public ViewGroup getProgressBarRootLayout() {
        WeakReference<ViewGroup> weakReference = this.progressBarRootLayout;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public int getType() {
        return this.type;
    }

    public void setHttpErrorAlertControllerFactory(HttpGroup.HttpErrorAlertControllerFactory httpErrorAlertControllerFactory) {
        this.httpErrorAlertControllerFactory = httpErrorAlertControllerFactory;
    }

    public void setMyActivity(Activity activity) {
        if (activity != null) {
            this.myActivity = new WeakReference<>(activity);
        }
    }

    public void setPriority(int i2) {
        this.priority = i2;
    }

    public void setProgressBarRootLayout(ViewGroup viewGroup) {
        this.progressBarRootLayout = new WeakReference<>(viewGroup);
    }

    public void setType(int i2) {
        this.type = i2;
        if (this.priority == 0) {
            if (i2 == 1000) {
                setPriority(1000);
            } else if (i2 != 5000) {
            } else {
                setPriority(5000);
            }
        }
    }
}
