package com.meizu.cloud.pushsdk.handler;

import android.content.Context;
import com.meizu.cloud.pushsdk.notification.PushNotificationBuilder;
import com.meizu.cloud.pushsdk.platform.message.PushSwitchStatus;
import com.meizu.cloud.pushsdk.platform.message.RegisterStatus;
import com.meizu.cloud.pushsdk.platform.message.SubAliasStatus;
import com.meizu.cloud.pushsdk.platform.message.SubTagsStatus;
import com.meizu.cloud.pushsdk.platform.message.UnRegisterStatus;

/* loaded from: classes14.dex */
public interface b {
    void a(Context context, String str);

    void a(Context context, String str, String str2);

    void b(Context context, RegisterStatus registerStatus);

    void b(Context context, String str);

    void c(Context context, String str);

    void c(PushNotificationBuilder pushNotificationBuilder);

    void d(Context context, SubAliasStatus subAliasStatus);

    void e(Context context, MzPushMessage mzPushMessage);

    void f(Context context, MzPushMessage mzPushMessage);

    void g(Context context, MzPushMessage mzPushMessage);

    void h(Context context, PushSwitchStatus pushSwitchStatus);

    void i(Context context, boolean z);

    void j(Context context, SubTagsStatus subTagsStatus);

    void k(Context context, UnRegisterStatus unRegisterStatus);
}
