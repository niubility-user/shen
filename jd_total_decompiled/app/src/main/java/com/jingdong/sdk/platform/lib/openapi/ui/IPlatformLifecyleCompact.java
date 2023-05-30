package com.jingdong.sdk.platform.lib.openapi.ui;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/* loaded from: classes10.dex */
public interface IPlatformLifecyleCompact {
    AbsActivityCompact getActivityCompact(FragmentActivity fragmentActivity);

    AbsFragmentCompact getFragmentCompact(Fragment fragment);
}
