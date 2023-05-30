package com.jingdong.jdsdk.network.toolbox;

import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONArray;

/* loaded from: classes.dex */
public final class LocalIPAddressResolver {
    public static final String TAG = "LocalIPAddressResolver";

    private static ArrayList<String> getHostIPAddress() {
        return new ArrayList<>();
    }

    public static String getHostIPv6AddressJsonStr() {
        try {
            return new JSONArray((Collection) getHostIPAddress()).toString();
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }
}
