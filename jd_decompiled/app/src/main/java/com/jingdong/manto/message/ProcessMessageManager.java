package com.jingdong.manto.message;

import android.os.Parcelable;
import android.os.Process;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.message.MantoAcrossMessage;

/* loaded from: classes15.dex */
public class ProcessMessageManager {
    private static volatile ProcessMessageManager instance;
    private MantoAcrossMessage mantoAcrossMessage = new MantoAcrossMessage();
    private String code = Process.myPid() + CartConstant.KEY_YB_INFO_LINK + hashCode();

    private ProcessMessageManager() {
    }

    public static ProcessMessageManager getInstance() {
        if (instance == null) {
            synchronized (ProcessMessageManager.class) {
                if (instance == null) {
                    instance = new ProcessMessageManager();
                }
            }
        }
        return instance;
    }

    public String getCode() {
        return this.code;
    }

    public void init() {
        this.mantoAcrossMessage.a(this.code);
    }

    public void registListener(MantoAcrossMessage.Listener listener) {
        this.mantoAcrossMessage.a(listener);
    }

    public void sendMessageToMain(Parcelable parcelable) {
        this.mantoAcrossMessage.a(parcelable);
    }

    public void unInit() {
        this.mantoAcrossMessage.b(this.code);
    }

    public void unRegistListener(MantoAcrossMessage.Listener listener) {
        this.mantoAcrossMessage.b(listener);
    }
}
