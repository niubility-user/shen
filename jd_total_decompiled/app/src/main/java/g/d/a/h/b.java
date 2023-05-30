package g.d.a.h;

import android.app.NotificationManager;
import android.content.Context;
import com.heytap.msp.push.callback.IDataMessageCallBackService;
import com.heytap.msp.push.mode.BaseMode;
import com.heytap.msp.push.mode.DataMessage;
import com.heytap.msp.push.statis.StatisticUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import g.d.a.j.d;
import g.d.a.j.g;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes12.dex */
public class b implements c {

    /* loaded from: classes12.dex */
    class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ DataMessage f19441g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Context f19442h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ IDataMessageCallBackService f19443i;

        a(DataMessage dataMessage, Context context, IDataMessageCallBackService iDataMessageCallBackService) {
            this.f19441g = dataMessage;
            this.f19442h = context;
            this.f19443i = iDataMessageCallBackService;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f19441g.getMsgCommand() == 1) {
                b.this.c(this.f19442h, this.f19441g);
            } else {
                this.f19443i.processMessage(this.f19442h, this.f19441g);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Context context, DataMessage dataMessage) {
        if (context == null) {
            d.a("context is null");
            return;
        }
        d.a("Receive revokeMessage  extra : " + dataMessage.getStatisticsExtra() + "notifyId :" + dataMessage.getNotifyID() + "messageId : " + dataMessage.getTaskID());
        ((NotificationManager) context.getSystemService(RemoteMessageConst.NOTIFICATION)).cancel(dataMessage.getNotifyID());
        d(context, dataMessage);
    }

    private void d(Context context, DataMessage dataMessage) {
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        arrayList.add(dataMessage);
        hashMap.put(dataMessage.getEventId(), arrayList);
        StatisticUtils.statisticEvent(context, hashMap);
    }

    @Override // g.d.a.h.c
    public void a(Context context, BaseMode baseMode, IDataMessageCallBackService iDataMessageCallBackService) {
        if (baseMode != null && baseMode.getType() == 4103) {
            DataMessage dataMessage = (DataMessage) baseMode;
            if (iDataMessageCallBackService != null) {
                g.b(new a(dataMessage, context, iDataMessageCallBackService));
            }
        }
    }
}
