package com.jd.lib.productdetail.mainimage.presenter;

import androidx.lifecycle.MutableLiveData;
import com.jingdong.common.BaseActivity;

/* loaded from: classes15.dex */
public class PdVideoContainer {
    public MutableLiveData<Boolean> isVideoInit = new MutableLiveData<>();
    public MutableLiveData<Boolean> isClickVideoPlay = new MutableLiveData<>();
    public MutableLiveData<VideoControl> videoControl = new MutableLiveData<>();
    public MutableLiveData<Integer> videoPlayTime = new MutableLiveData<>();
    public MutableLiveData<VideoStatus> videoStatus = new MutableLiveData<>(VideoStatus.OVER);

    /* loaded from: classes15.dex */
    public enum VideoControl {
        RESUME_OUT,
        PAUSE_OUT
    }

    /* loaded from: classes15.dex */
    public enum VideoStatus {
        PLAY,
        PAUSE,
        OVER,
        ERROR
    }

    public void onCleared(BaseActivity baseActivity) {
        this.isVideoInit.setValue(Boolean.FALSE);
    }
}
