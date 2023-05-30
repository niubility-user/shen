package g.d.a.i;

import android.content.Context;
import com.heytap.msp.push.mode.DataMessage;
import com.heytap.msp.push.mode.MessageStat;
import g.d.a.j.f;
import java.util.ArrayList;

/* loaded from: classes12.dex */
public class a {
    public static boolean a(Context context, String str, DataMessage dataMessage) {
        MessageStat messageStat;
        ArrayList arrayList = new ArrayList();
        String packageName = context.getPackageName();
        if (dataMessage == null) {
            messageStat = new MessageStat(packageName, str);
        } else {
            messageStat = new MessageStat(dataMessage.getMessageType(), packageName, dataMessage.getGlobalId(), dataMessage.getTaskID(), str, null, dataMessage.getStatisticsExtra(), dataMessage.getDataExtra());
        }
        arrayList.add(messageStat);
        return f.c(context, arrayList);
    }
}
