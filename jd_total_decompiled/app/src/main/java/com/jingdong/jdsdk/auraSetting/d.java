package com.jingdong.jdsdk.auraSetting;

import java.util.List;
import java.util.Set;

/* loaded from: classes14.dex */
public interface d {
    String[] a();

    long b();

    long c(long j2);

    long d(int i2);

    long e(String str);

    long f();

    String g(long j2);

    List<String> getBundleDownloadOrder();

    String getBundleNameFromBundleId(int i2);

    String getBundleNameFromUpdateID(String str);

    String getUpdateIdFromBundleName(String str);

    long h(long j2);

    Set<String> i();
}
