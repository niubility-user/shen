package com.bumptech.glide.signature;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.load.Key;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes.dex */
public final class ApplicationVersionSignature {
    private static final ConcurrentMap<String, Key> PACKAGE_NAME_TO_KEY = new ConcurrentHashMap();
    private static final String TAG = "AppVersionSignature";

    private ApplicationVersionSignature() {
    }

    @Nullable
    private static PackageInfo getPackageInfo(@NonNull Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException unused) {
            String str = "Cannot resolve info for" + context.getPackageName();
            return null;
        }
    }

    @NonNull
    private static String getVersionCode(@Nullable PackageInfo packageInfo) {
        if (packageInfo != null) {
            return String.valueOf(packageInfo.versionCode);
        }
        return UUID.randomUUID().toString();
    }

    @NonNull
    public static Key obtain(@NonNull Context context) {
        String packageName = context.getPackageName();
        ConcurrentMap<String, Key> concurrentMap = PACKAGE_NAME_TO_KEY;
        Key key = concurrentMap.get(packageName);
        if (key == null) {
            Key obtainVersionSignature = obtainVersionSignature(context);
            Key putIfAbsent = concurrentMap.putIfAbsent(packageName, obtainVersionSignature);
            return putIfAbsent == null ? obtainVersionSignature : putIfAbsent;
        }
        return key;
    }

    @NonNull
    private static Key obtainVersionSignature(@NonNull Context context) {
        return new ObjectKey(getVersionCode(getPackageInfo(context)));
    }

    @VisibleForTesting
    static void reset() {
        PACKAGE_NAME_TO_KEY.clear();
    }
}
