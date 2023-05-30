package com.xiaomi.push.service;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.utils.LangUtils;
import com.xiaomi.push.a8;
import com.xiaomi.push.p7;
import com.xiaomi.push.y4;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes11.dex */
public class u {
    private static final boolean a = Log.isLoggable("NCHelper", 3);

    private static int a(NotificationChannel notificationChannel) {
        int i2 = 0;
        try {
            i2 = ((Integer) com.xiaomi.push.k0.n(notificationChannel, "getUserLockedFields", new Object[0])).intValue();
            if (a) {
                j("isUserLockedChannel:" + i2 + LangUtils.SINGLE_SPACE + notificationChannel);
            }
        } catch (Exception e2) {
            g.j.a.a.a.c.p("NCHelper", "is user locked error" + e2);
        }
        return i2;
    }

    @TargetApi(26)
    private static NotificationChannel b(String str, NotificationChannel notificationChannel) {
        NotificationChannel notificationChannel2 = new NotificationChannel(str, notificationChannel.getName(), notificationChannel.getImportance());
        notificationChannel2.setDescription(notificationChannel.getDescription());
        notificationChannel2.enableVibration(notificationChannel.shouldVibrate());
        notificationChannel2.enableLights(notificationChannel.shouldShowLights());
        notificationChannel2.setSound(notificationChannel.getSound(), notificationChannel.getAudioAttributes());
        notificationChannel2.setLockscreenVisibility(notificationChannel.getLockscreenVisibility());
        return notificationChannel2;
    }

    private static SharedPreferences c(Context context) {
        return context.getSharedPreferences("mipush_channel_copy_sp", 0);
    }

    @TargetApi(26)
    public static String d(y yVar, String str, CharSequence charSequence, String str2, int i2, int i3, String str3, String str4) {
        String i4 = yVar.i(str);
        boolean z = a;
        if (z) {
            j("createChannel: appChannelId:" + i4 + " serverChannelId:" + str + " serverChannelName:" + ((Object) charSequence) + " serverChannelDesc:" + str2 + " serverChannelNotifyType:" + i2 + " serverChannelName:" + ((Object) charSequence) + " serverChannelImportance:" + i3 + " channelSoundStr:" + str3 + " channelPermissions:" + str4);
        }
        NotificationChannel notificationChannel = new NotificationChannel(i4, charSequence, i3);
        notificationChannel.setDescription(str2);
        notificationChannel.enableVibration((i2 & 2) != 0);
        notificationChannel.enableLights((i2 & 4) != 0);
        if ((i2 & 1) == 0) {
            notificationChannel.setSound(null, null);
        } else if (!TextUtils.isEmpty(str3)) {
            if (str3.startsWith("android.resource://" + yVar.h())) {
                notificationChannel.setSound(Uri.parse(str3), Notification.AUDIO_ATTRIBUTES_DEFAULT);
            }
        }
        if (z) {
            j("create channel:" + notificationChannel);
        }
        i(yVar, notificationChannel, str4);
        return i4;
    }

    @SuppressLint({"WrongConstant"})
    @TargetApi(26)
    static void e(Context context, y yVar, NotificationChannel notificationChannel, int i2, String str) {
        if (i2 <= 0) {
            yVar.o(notificationChannel);
            return;
        }
        int a2 = y4.a(context) >= 2 ? c2.a(context.getPackageName(), str) : 0;
        NotificationChannel b = b(notificationChannel.getId(), notificationChannel);
        if ((i2 & 32) != 0) {
            if (notificationChannel.getSound() != null) {
                b.setSound(null, null);
            } else {
                b.setSound(Settings.System.DEFAULT_NOTIFICATION_URI, Notification.AUDIO_ATTRIBUTES_DEFAULT);
            }
        }
        if ((i2 & 16) != 0) {
            if (notificationChannel.shouldVibrate()) {
                b.enableVibration(false);
            } else {
                b.enableVibration(true);
            }
        }
        if ((i2 & 8) != 0) {
            if (notificationChannel.shouldShowLights()) {
                b.enableLights(false);
            } else {
                b.enableLights(true);
            }
        }
        if ((i2 & 4) != 0) {
            int importance = notificationChannel.getImportance() - 1;
            if (importance <= 0) {
                importance = 2;
            }
            b.setImportance(importance);
        }
        if ((i2 & 2) != 0) {
            b.setLockscreenVisibility(notificationChannel.getLockscreenVisibility() - 1);
        }
        yVar.o(b);
        yVar.p(notificationChannel, true);
        c2.j(yVar.h(), notificationChannel.getId(), a2, 0);
    }

    public static void f(Context context, String str) {
        if (!a8.j(context) || TextUtils.isEmpty(str)) {
            return;
        }
        n(context, str);
        c2.d(context, str);
    }

    private static void g(Context context, List<String> list) {
        if (a) {
            j("deleteCopiedChannelRecord:" + list);
        }
        if (list.isEmpty()) {
            return;
        }
        SharedPreferences.Editor edit = c(context).edit();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            edit.remove(it.next());
        }
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void h(p7 p7Var) {
        Map<String, String> map;
        if (p7Var == null || (map = p7Var.f195a) == null || !map.containsKey("REMOVE_CHANNEL_MARK")) {
            return;
        }
        p7Var.a = 0;
        p7Var.f195a.remove("channel_id");
        p7Var.f195a.remove("channel_importance");
        p7Var.f195a.remove("channel_name");
        p7Var.f195a.remove("channel_description");
        p7Var.f195a.remove("channel_perm");
        g.j.a.a.a.c.o("delete channel info by:" + p7Var.f195a.get("REMOVE_CHANNEL_MARK"));
        p7Var.f195a.remove("REMOVE_CHANNEL_MARK");
    }

    @TargetApi(26)
    private static void i(y yVar, NotificationChannel notificationChannel, String str) {
        int i2;
        char c2;
        int i3;
        Context d = yVar.d();
        String id = notificationChannel.getId();
        String j2 = y.j(id, yVar.h());
        boolean z = a;
        if (z) {
            j("appChannelId:" + id + " oldChannelId:" + j2);
        }
        if (!a8.j(d) || TextUtils.equals(id, j2)) {
            NotificationChannel b = yVar.b(id);
            if (z) {
                j("elseLogic getNotificationChannel:" + b);
            }
            if (b == null) {
                yVar.o(notificationChannel);
            }
            i2 = 0;
            c2 = 0;
        } else {
            NotificationManager notificationManager = (NotificationManager) d.getSystemService(RemoteMessageConst.NOTIFICATION);
            NotificationChannel notificationChannel2 = notificationManager.getNotificationChannel(j2);
            NotificationChannel b2 = yVar.b(id);
            if (z) {
                j("xmsfChannel:" + notificationChannel2);
                j("appChannel:" + b2);
            }
            if (notificationChannel2 != null) {
                NotificationChannel b3 = b(id, notificationChannel2);
                if (z) {
                    j("copyXmsf copyXmsfChannel:" + b3);
                }
                if (b2 != null) {
                    i3 = a(b2);
                    yVar.p(b3, i3 == 0);
                    c2 = 3;
                } else {
                    i3 = a(notificationChannel2);
                    e(d, yVar, b3, i3, notificationChannel2.getId());
                    c2 = 4;
                }
                m(d, id);
                notificationManager.deleteNotificationChannel(j2);
            } else if (b2 == null) {
                if (z) {
                    j("appHack createNotificationChannel:" + notificationChannel);
                }
                yVar.o(notificationChannel);
                i3 = 0;
                c2 = 1;
            } else if (l(d, id) || !k(notificationChannel, b2)) {
                i3 = 0;
                c2 = 0;
            } else {
                if (z) {
                    j("appHack updateNotificationChannel:" + notificationChannel);
                }
                i3 = a(b2);
                yVar.p(notificationChannel, i3 == 0);
                c2 = 2;
            }
            i2 = i3;
        }
        c2.e(yVar.d(), yVar.h(), id, notificationChannel.getImportance(), str, c2 == 1 || c2 == 4 || c2 == 3, i2);
    }

    private static void j(String str) {
        g.j.a.a.a.c.p("NCHelper", str);
    }

    @TargetApi(26)
    private static boolean k(NotificationChannel notificationChannel, NotificationChannel notificationChannel2) {
        boolean z;
        if (notificationChannel == null || notificationChannel2 == null) {
            return false;
        }
        boolean z2 = true;
        if (TextUtils.equals(notificationChannel.getName(), notificationChannel2.getName())) {
            z = false;
        } else {
            if (a) {
                j("appHack channelConfigLowerCompare:getName");
            }
            z = true;
        }
        if (!TextUtils.equals(notificationChannel.getDescription(), notificationChannel2.getDescription())) {
            if (a) {
                j("appHack channelConfigLowerCompare:getDescription");
            }
            z = true;
        }
        if (notificationChannel.getImportance() != notificationChannel2.getImportance()) {
            notificationChannel.setImportance(Math.min(notificationChannel.getImportance(), notificationChannel2.getImportance()));
            if (a) {
                j("appHack channelConfigLowerCompare:getImportance  " + notificationChannel.getImportance() + LangUtils.SINGLE_SPACE + notificationChannel2.getImportance());
            }
            z = true;
        }
        if (notificationChannel.shouldVibrate() != notificationChannel2.shouldVibrate()) {
            notificationChannel.enableVibration(false);
            if (a) {
                j("appHack channelConfigLowerCompare:enableVibration");
            }
            z = true;
        }
        if (notificationChannel.shouldShowLights() != notificationChannel2.shouldShowLights()) {
            notificationChannel.enableLights(false);
            if (a) {
                j("appHack channelConfigLowerCompare:enableLights");
            }
            z = true;
        }
        if ((notificationChannel.getSound() != null) != (notificationChannel2.getSound() != null)) {
            notificationChannel.setSound(null, null);
            if (a) {
                j("appHack channelConfigLowerCompare:setSound");
            }
        } else {
            z2 = z;
        }
        if (a) {
            j("appHack channelConfigLowerCompare:isDifferent:" + z2);
        }
        return z2;
    }

    private static boolean l(Context context, String str) {
        if (a) {
            j("checkCopeidChannel:newFullChannelId:" + str + "  " + c(context).getBoolean(str, false));
        }
        return c(context).getBoolean(str, false);
    }

    private static void m(Context context, String str) {
        if (a) {
            j("recordCopiedChannel:" + str);
        }
        c(context).edit().putBoolean(str, true).apply();
    }

    private static void n(Context context, String str) {
        try {
            y e2 = y.e(context, str);
            Set<String> keySet = c(context).getAll().keySet();
            ArrayList arrayList = new ArrayList();
            for (String str2 : keySet) {
                if (e2.u(str2)) {
                    arrayList.add(str2);
                    if (a) {
                        j("delete channel copy record:" + str2);
                    }
                }
            }
            g(context, arrayList);
        } catch (Exception unused) {
        }
    }
}
