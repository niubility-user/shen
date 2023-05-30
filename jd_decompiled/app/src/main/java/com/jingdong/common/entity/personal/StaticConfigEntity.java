package com.jingdong.common.entity.personal;

import java.util.List;

/* loaded from: classes5.dex */
public class StaticConfigEntity {
    public int code;
    public List<StaticConfigFloor> floors;

    /* loaded from: classes5.dex */
    public static class RedDotInfoData {
        public String functionId;
        public long redDotVersion;
    }

    /* loaded from: classes5.dex */
    public static class StaticConfigData {
        public List<RedDotInfoData> tabRedDotInfoList;
        public int tabRedDotType;
        public long tabRedDotVersion;
    }

    /* loaded from: classes5.dex */
    public static class StaticConfigFloor {
        public String bId;
        public StaticConfigData data;
        public String mId;
        public int sortId;
    }
}
