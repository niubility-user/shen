package com.jd.viewkit.helper;

/* loaded from: classes18.dex */
public interface JDViewKitCountdownInterface {
    public static final int CountType_Accumulate = 1;
    public static final int CountType_Append = 2;
    public static final int CountType_Error = -1;
    public static final int HandleType_Begin = 1;
    public static final int HandleType_Pause = 2;
    public static final int LifeCycle_Appear = 1;
    public static final int LifeCycle_Disappear = 2;
    public static final int TriggerType_BeginByScroll = 2;
    public static final int TriggerType_Delay = 1;
    public static final int TriggerType_Error = -1;
    public static final int TriggerType_Now = 0;

    int getCountType();

    int getTriggerType();

    void handleCountdown(int i2);

    void initCountdownParamsByPlay();

    void setCountdownLifeCycle(int i2);
}
