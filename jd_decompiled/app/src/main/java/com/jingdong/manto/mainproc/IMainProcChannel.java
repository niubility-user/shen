package com.jingdong.manto.mainproc;

import android.os.Parcelable;
import com.jingdong.manto.sdk.IMantoSdkBase;
import java.util.List;

/* loaded from: classes15.dex */
public interface IMainProcChannel extends IMantoSdkBase {

    /* loaded from: classes15.dex */
    public interface MainProcListener extends IMantoSdkBase {
        void onMantoMessage(MainProcMessage mainProcMessage);

        void onMessage(Parcelable parcelable);
    }

    void registerMantoListener(MainProcListener mainProcListener);

    void sendMessageToManto(MainProcMessage mainProcMessage);

    void sendMessageToManto(String str, MainProcMessage mainProcMessage);

    void sendMessageToManto(List<String> list, MainProcMessage mainProcMessage);

    void sendMessageToNative(Parcelable parcelable);

    void sendMessageToNative(String str, Parcelable parcelable);

    void sendMessageToNative(List<String> list, Parcelable parcelable);

    void unRegisterMantoListener();
}
