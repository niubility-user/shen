package com.jd.framework.network.dialing;

import java.util.HashMap;

/* loaded from: classes13.dex */
public class IPConfiguration {
    static HashMap<String, String[]> sBuildInIPMap;

    static {
        HashMap<String, String[]> hashMap = new HashMap<>();
        sBuildInIPMap = hashMap;
        hashMap.put("api.m.jd.com", new String[]{"106.39.169.231", "36.110.181.150", "120.52.148.120", "211.144.24.231", "111.13.24.98", "39.156.62.242"});
    }

    public static HashMap<String, String[]> buildInIPMap() {
        return sBuildInIPMap;
    }
}
