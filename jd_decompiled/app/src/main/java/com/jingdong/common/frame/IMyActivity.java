package com.jingdong.common.frame;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import com.jingdong.jdsdk.network.dependency.IModalViewController;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;

/* loaded from: classes.dex */
public interface IMyActivity extends IModalViewController {
    void addDestroyListener(IDestroyListener iDestroyListener);

    void addLogoutListener(ILogoutListener iLogoutListener);

    void addPauseListener(IPauseListener iPauseListener);

    void addResumeListener(IResumeListener iResumeListener);

    void finish();

    Handler getHandler();

    HttpGroup getHttpGroupaAsynPool();

    HttpGroup getHttpGroupaAsynPool(int i2);

    String getStringFromPreference(String str);

    Activity getThisActivity();

    void post(Runnable runnable);

    void post(Runnable runnable, int i2);

    void putBooleanToPreference(String str, Boolean bool);

    void removeDestroyListener(IDestroyListener iDestroyListener);

    void removeLogoutListener(ILogoutListener iLogoutListener);

    void removePauseListener(IPauseListener iPauseListener);

    void removeResumeListener(IResumeListener iResumeListener);

    void startActivityForResultNoException(Intent intent, int i2);

    void startActivityForResultNoExceptionStatic(Activity activity, Intent intent, int i2);

    void startActivityInFrame(Intent intent);

    void startActivityInFrameWithNoNavigation(Intent intent);

    void startActivityNoException(Intent intent);
}
