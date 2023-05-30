package g.d.a.f;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import com.heytap.mcssdk.constant.McsEventConstant$EventId;
import com.heytap.msp.push.HeytapPushManager;
import com.heytap.msp.push.constant.ConfigConstant;
import com.heytap.msp.push.mode.DataMessage;
import com.heytap.msp.push.mode.NotificationSortMessage;
import com.heytap.msp.push.notification.ISortListener;
import com.heytap.msp.push.notification.PushNotification;
import com.heytap.msp.push.statis.StatisticUtils;
import com.jd.dynamic.DYConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class b {

    /* renamed from: c  reason: collision with root package name */
    private int f19435c;
    private int d;

    /* renamed from: f  reason: collision with root package name */
    private int f19437f;

    /* renamed from: g  reason: collision with root package name */
    private int f19438g;
    private int a = 3;
    private List<NotificationSortMessage> b = new ArrayList();

    /* renamed from: e  reason: collision with root package name */
    private List<String> f19436e = new ArrayList();

    /* loaded from: classes12.dex */
    private static class a {
        private static final b a = new b();
    }

    private void a(ISortListener iSortListener, boolean z, PushNotification.Builder builder) {
        if (iSortListener != null) {
            iSortListener.buildCompleted(z, builder, this.f19436e);
        }
    }

    private DataMessage b(Context context, NotificationSortMessage notificationSortMessage) {
        DataMessage dataMessage = new DataMessage(context.getPackageName(), notificationSortMessage.getMessageId());
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("isMcs", DYConstants.DY_FALSE);
            String statisticData = notificationSortMessage.getStatisticData();
            if (!TextUtils.isEmpty(statisticData)) {
                jSONObject.put("clientStatisticData", statisticData);
            }
            dataMessage.setStatisticsExtra(jSONObject.toString());
        } catch (JSONException unused) {
        }
        return dataMessage;
    }

    private boolean c(NotificationManager notificationManager, Context context, PushNotification.Builder builder, NotificationSortMessage notificationSortMessage) {
        boolean z = true;
        if (this.f19437f + this.f19438g < this.a) {
            if (notificationSortMessage.getAutoDelete() == -1) {
                notificationSortMessage.setGroup("mcs." + context.getPackageName());
            } else {
                notificationSortMessage.setGroup("mcs.enable." + context.getPackageName());
            }
        } else if (notificationSortMessage.getAutoDelete() == -1) {
            notificationSortMessage.setGroup("mcs." + context.getPackageName());
            int i2 = this.a - this.f19438g;
            if (i2 > 0) {
                e(context, notificationManager, i2 - 1);
            } else {
                Notification a2 = g.d.a.f.a.a(context, notificationSortMessage.getGroup(), builder);
                if (a2 != null) {
                    notificationManager.notify(4096, a2);
                }
            }
        } else {
            z = o(context, notificationManager, notificationSortMessage);
        }
        if (z) {
            g(builder, notificationSortMessage);
        } else {
            g.d.a.i.a.a(context, McsEventConstant$EventId.EVENT_ID_PUSH_NO_SHOW_BY_FOLD, b(context, notificationSortMessage));
        }
        return z;
    }

    private void d(NotificationManager notificationManager, Context context) {
        m(g.d.a.f.a.b(notificationManager, context.getPackageName()));
    }

    private void e(Context context, NotificationManager notificationManager, int i2) {
        p(this.b, i2);
        r(context, notificationManager, this.b);
    }

    private void f(Context context, NotificationManager notificationManager, JSONArray jSONArray, List<NotificationSortMessage> list, List<DataMessage> list2) {
        for (NotificationSortMessage notificationSortMessage : list) {
            if (notificationSortMessage.isMcs()) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(ConfigConstant.NotificationSort.EXTRA_MESSAGE_ID, notificationSortMessage.getMessageId());
                    jSONObject.put(ConfigConstant.NotificationSort.EXTRA_NOTIFY_ID, notificationSortMessage.getNotifyId());
                    jSONArray.put(jSONObject);
                } catch (JSONException unused) {
                }
            } else {
                list2.add(b(context, notificationSortMessage));
                this.f19436e.add(notificationSortMessage.getMessageId());
            }
            notificationManager.cancel(notificationSortMessage.getNotifyId());
        }
    }

    private void g(PushNotification.Builder builder, NotificationSortMessage notificationSortMessage) {
        Bundle bundle = new Bundle();
        bundle.putInt(ConfigConstant.NotificationSort.EXTRA_AUTO_DELETE, notificationSortMessage.getAutoDelete());
        bundle.putInt(ConfigConstant.NotificationSort.EXTRA_IMPORTANT_LEVEL, notificationSortMessage.getImportantLevel());
        bundle.putString(ConfigConstant.NotificationSort.EXTRA_MESSAGE_ID, notificationSortMessage.getMessageId());
        bundle.putLong(ConfigConstant.NotificationSort.EXTRA_POST_TIME, System.currentTimeMillis());
        bundle.putBoolean(ConfigConstant.NotificationSort.EXTRA_IS_MCS, false);
        bundle.putString(ConfigConstant.NotificationSort.EXTRA_STATISTIC_DATA, notificationSortMessage.getStatisticData());
        if (Build.VERSION.SDK_INT >= 20) {
            builder.addExtras(bundle);
            builder.setGroup(notificationSortMessage.getGroup());
        }
    }

    private boolean h(PushNotification.Builder builder, int i2, int i3, String str, String str2) {
        Context q = g.d.a.b.s().q();
        if (builder == null || q == null) {
            return false;
        }
        NotificationManager c2 = g.d.a.f.a.c(q);
        NotificationSortMessage notificationSortMessage = new NotificationSortMessage(str, i3, i2, false, System.currentTimeMillis(), str2);
        if (n(q, c2, notificationSortMessage, builder)) {
            d(c2, q);
            return c(c2, q, builder, notificationSortMessage);
        }
        return true;
    }

    public static b i() {
        return a.a;
    }

    private void j(int i2) {
        if (i2 == -1) {
            this.f19438g++;
        } else if (i2 == 1) {
            this.f19437f++;
        }
    }

    private void k(int i2) {
        if (i2 == 7) {
            this.f19435c++;
        } else if (i2 == 5) {
            this.d++;
        }
    }

    private void l(NotificationSortMessage notificationSortMessage) {
        if (notificationSortMessage.getAutoDelete() != 1) {
            return;
        }
        if (this.b.size() != 0) {
            for (int size = this.b.size() - 1; size >= 0; size--) {
                NotificationSortMessage notificationSortMessage2 = this.b.get(size);
                if (notificationSortMessage.getImportantLevel() >= notificationSortMessage2.getImportantLevel() && notificationSortMessage.getPostTime() >= notificationSortMessage2.getPostTime()) {
                    this.b.add(size + 1, notificationSortMessage2);
                    return;
                }
            }
        }
        this.b.add(0, notificationSortMessage);
    }

    private void m(StatusBarNotification[] statusBarNotificationArr) {
        q();
        if (statusBarNotificationArr == null || statusBarNotificationArr.length == 0) {
            return;
        }
        for (StatusBarNotification statusBarNotification : statusBarNotificationArr) {
            Bundle bundle = statusBarNotification.getNotification().extras;
            boolean z = bundle.getBoolean(ConfigConstant.NotificationSort.EXTRA_IS_MCS, true);
            long j2 = bundle.getLong(ConfigConstant.NotificationSort.EXTRA_POST_TIME, statusBarNotification.getPostTime());
            String string = bundle.getString(ConfigConstant.NotificationSort.EXTRA_MESSAGE_ID, "");
            int i2 = bundle.getInt(ConfigConstant.NotificationSort.EXTRA_AUTO_DELETE, 1);
            int i3 = bundle.getInt(ConfigConstant.NotificationSort.EXTRA_IMPORTANT_LEVEL, 7);
            NotificationSortMessage notificationSortMessage = new NotificationSortMessage(string, i3, i2, z, j2, statusBarNotification.getId(), bundle.getString(ConfigConstant.NotificationSort.EXTRA_STATISTIC_DATA));
            j(i2);
            k(i3);
            l(notificationSortMessage);
        }
    }

    private boolean o(Context context, NotificationManager notificationManager, NotificationSortMessage notificationSortMessage) {
        int i2 = this.f19438g;
        int i3 = this.a;
        boolean z = false;
        if (i2 >= i3) {
            return false;
        }
        int i4 = i3 - i2;
        if (notificationSortMessage.getImportantLevel() == 7 || (notificationSortMessage.getImportantLevel() != 5 ? this.f19435c + this.d < i4 : this.f19435c < i4)) {
            z = true;
        }
        if (z) {
            e(context, notificationManager, i4 - 1);
        }
        return z;
    }

    private int p(List<NotificationSortMessage> list, int i2) {
        int size = list == null ? 0 : list.size();
        if (i2 <= 0 || size == 0) {
            return i2;
        }
        if (size < i2) {
            int i3 = i2 - size;
            list.clear();
            return i3;
        }
        for (int i4 = 0; i4 < i2; i4++) {
            list.remove((size - 1) - i4);
        }
        return 0;
    }

    private void q() {
        this.f19437f = 0;
        this.f19438g = 0;
        this.f19435c = 0;
        this.d = 0;
        this.b.clear();
        this.f19436e.clear();
    }

    private void r(Context context, NotificationManager notificationManager, List<NotificationSortMessage> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        ArrayList arrayList = new ArrayList();
        f(context, notificationManager, jSONArray, list, arrayList);
        if (jSONArray.length() != 0) {
            try {
                jSONObject.put("SORT_ARRAY", jSONArray);
                HeytapPushManager.cancelNotification(jSONObject);
            } catch (JSONException unused) {
            }
        }
        if (arrayList.size() != 0) {
            HashMap hashMap = new HashMap();
            hashMap.put(McsEventConstant$EventId.EVENT_ID_PUSH_DELETE_BY_FOLD, arrayList);
            StatisticUtils.statisticEvent(context, hashMap);
        }
    }

    public boolean n(Context context, NotificationManager notificationManager, NotificationSortMessage notificationSortMessage, PushNotification.Builder builder) {
        int i2;
        if (notificationSortMessage.getAutoDelete() != 0 && (i2 = Build.VERSION.SDK_INT) >= 24 && i2 < 30) {
            if (g.d.a.f.a.d(notificationManager, context.getPackageName(), 4096)) {
                notificationSortMessage.setGroup("mcs." + context.getPackageName());
                g(builder, notificationSortMessage);
                return false;
            }
            return true;
        }
        return false;
    }

    public void s(PushNotification.Builder builder, ISortListener iSortListener) {
        if (builder == null) {
            return;
        }
        a(iSortListener, h(builder, builder.getAutoDelete(), builder.getImportantLevel(), builder.getMessageId(), builder.getStatisticData()), builder);
    }
}
