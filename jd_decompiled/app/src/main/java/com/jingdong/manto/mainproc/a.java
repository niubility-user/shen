package com.jingdong.manto.mainproc;

import android.os.Parcelable;
import com.jingdong.manto.mainproc.IMainProcChannel;
import com.jingdong.manto.message.MantoAcrossMessage;
import com.jingdong.manto.message.MantoAcrossMessageCenter;
import com.jingdong.manto.utils.MantoProcessUtil;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes15.dex */
public class a implements IMainProcChannel, MantoAcrossMessage.Listener {
    private IMainProcChannel.MainProcListener a;

    @Override // com.jingdong.manto.message.MantoAcrossMessage.Listener
    public void onCalled(Object obj) {
        IMainProcChannel.MainProcListener mainProcListener;
        if (obj instanceof MainProcMessage) {
            IMainProcChannel.MainProcListener mainProcListener2 = this.a;
            if (mainProcListener2 != null) {
                mainProcListener2.onMantoMessage((MainProcMessage) obj);
            }
        } else if (!(obj instanceof Parcelable) || (mainProcListener = this.a) == null) {
        } else {
            mainProcListener.onMessage((Parcelable) obj);
        }
    }

    @Override // com.jingdong.manto.mainproc.IMainProcChannel
    public void registerMantoListener(IMainProcChannel.MainProcListener mainProcListener) {
        if (MantoProcessUtil.isMainProcess()) {
            this.a = mainProcListener;
            MantoAcrossMessageCenter.registMainListener(this);
        }
    }

    @Override // com.jingdong.manto.mainproc.IMainProcChannel
    public void sendMessageToManto(MainProcMessage mainProcMessage) {
        if (MantoProcessUtil.isMainProcess()) {
            MantoAcrossMessageCenter.notifyCommonData(mainProcMessage);
        }
    }

    @Override // com.jingdong.manto.mainproc.IMainProcChannel
    public void sendMessageToManto(String str, MainProcMessage mainProcMessage) {
        if (MantoProcessUtil.isMainProcess()) {
            LinkedList linkedList = new LinkedList();
            linkedList.add(str);
            MantoAcrossMessageCenter.notifyCommonData(linkedList, mainProcMessage);
        }
    }

    @Override // com.jingdong.manto.mainproc.IMainProcChannel
    public void sendMessageToManto(List<String> list, MainProcMessage mainProcMessage) {
        if (MantoProcessUtil.isMainProcess()) {
            MantoAcrossMessageCenter.notifyCommonData(list, mainProcMessage);
        }
    }

    @Override // com.jingdong.manto.mainproc.IMainProcChannel
    public void sendMessageToNative(Parcelable parcelable) {
        if (MantoProcessUtil.isMainProcess()) {
            MantoAcrossMessageCenter.notifyCommonData(parcelable);
        }
    }

    @Override // com.jingdong.manto.mainproc.IMainProcChannel
    public void sendMessageToNative(String str, Parcelable parcelable) {
        if (MantoProcessUtil.isMainProcess()) {
            LinkedList linkedList = new LinkedList();
            linkedList.add(str);
            MantoAcrossMessageCenter.notifyCommonData(linkedList, parcelable);
        }
    }

    @Override // com.jingdong.manto.mainproc.IMainProcChannel
    public void sendMessageToNative(List<String> list, Parcelable parcelable) {
        if (MantoProcessUtil.isMainProcess()) {
            MantoAcrossMessageCenter.notifyCommonData(list, parcelable);
        }
    }

    @Override // com.jingdong.manto.mainproc.IMainProcChannel
    public void unRegisterMantoListener() {
        if (MantoProcessUtil.isMainProcess()) {
            this.a = null;
            MantoAcrossMessageCenter.unRegistMainListener(this);
        }
    }
}
