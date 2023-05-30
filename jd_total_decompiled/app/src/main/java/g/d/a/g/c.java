package g.d.a.g;

import android.content.Context;
import android.content.Intent;
import com.heytap.msp.push.mode.BaseMode;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes12.dex */
public abstract class c implements d {
    public static List<BaseMode> b(Context context, Intent intent) {
        BaseMode a;
        if (intent == null) {
            return null;
        }
        int i2 = 4096;
        try {
            i2 = Integer.parseInt(g.d.a.j.b.e(intent.getStringExtra("type")));
        } catch (Exception e2) {
            g.d.a.j.d.b("MessageParser--getMessageByIntent--Exception:" + e2.getMessage());
        }
        g.d.a.j.d.a("MessageParser--getMessageByIntent--type:" + i2);
        ArrayList arrayList = new ArrayList();
        for (d dVar : g.d.a.b.s().x()) {
            if (dVar != null && (a = dVar.a(context, i2, intent)) != null) {
                arrayList.add(a);
            }
        }
        return arrayList;
    }
}
