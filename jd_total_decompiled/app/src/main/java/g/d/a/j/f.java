package g.d.a.j;

import android.content.Context;
import android.content.Intent;
import com.heytap.msp.push.mode.MessageStat;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes12.dex */
public class f {
    private static boolean a(Context context) {
        String u = g.d.a.b.s().u(context);
        return h.f(context, u) && h.c(context, u) >= 1017;
    }

    public static void b(Context context, MessageStat messageStat) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(messageStat);
        c(context, linkedList);
    }

    public static boolean c(Context context, List<MessageStat> list) {
        LinkedList linkedList = new LinkedList();
        linkedList.addAll(list);
        d.a("isSupportStatisticByMcs:" + a(context) + ",list size:" + linkedList.size());
        if (linkedList.size() <= 0 || !a(context)) {
            return false;
        }
        return d(context, linkedList);
    }

    private static boolean d(Context context, List<MessageStat> list) {
        try {
            Intent intent = new Intent();
            intent.setAction(g.d.a.b.s().F(context));
            intent.setPackage(g.d.a.b.s().u(context));
            intent.putExtra("appPackage", context.getPackageName());
            intent.putExtra("type", 12291);
            intent.putExtra("count", list.size());
            ArrayList<String> arrayList = new ArrayList<>();
            Iterator<MessageStat> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().toJsonObject());
            }
            intent.putStringArrayListExtra(ThemeTitleConstant.TITLE_LIST_DRAWABLE_ID, arrayList);
            context.startService(intent);
            return true;
        } catch (Exception e2) {
            d.b("statisticMessage--Exception" + e2.getMessage());
            return false;
        }
    }
}
