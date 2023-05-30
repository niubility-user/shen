package com.jingdong.jdsdk.d.c.a;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.jingdong.sdk.platform.lib.openapi.ui.AbsActivityCompact;
import com.jingdong.sdk.platform.lib.openapi.ui.AbsFragmentCompact;
import com.jingdong.sdk.platform.lib.openapi.ui.IPlatformLifecyleCompact;

/* loaded from: classes14.dex */
public class p implements IPlatformLifecyleCompact {
    @Override // com.jingdong.sdk.platform.lib.openapi.ui.IPlatformLifecyleCompact
    public AbsActivityCompact getActivityCompact(FragmentActivity fragmentActivity) {
        return new b(fragmentActivity);
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.ui.IPlatformLifecyleCompact
    public AbsFragmentCompact getFragmentCompact(Fragment fragment) {
        return new c(fragment);
    }
}
