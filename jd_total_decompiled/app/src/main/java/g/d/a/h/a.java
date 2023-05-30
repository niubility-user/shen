package g.d.a.h;

import android.content.Context;
import com.heytap.msp.push.callback.ICallBackResultService;
import com.heytap.msp.push.callback.IDataMessageCallBackService;
import com.heytap.msp.push.callback.IGetAppNotificationCallBackService;
import com.heytap.msp.push.callback.ISetAppNotificationCallBackService;
import com.heytap.msp.push.mode.BaseMode;
import g.d.a.j.d;
import g.d.a.j.g;
import g.d.a.j.h;

/* loaded from: classes12.dex */
public class a implements c {

    /* renamed from: g.d.a.h.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    class RunnableC0831a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ g.d.a.e.b f19439g;

        RunnableC0831a(g.d.a.e.b bVar) {
            this.f19439g = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.c(this.f19439g, g.d.a.b.s());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(g.d.a.e.b bVar, g.d.a.b bVar2) {
        if (bVar == null) {
            d.b("message is null , please check param of parseCommandMessage(2)");
        } else if (bVar2 == null) {
            d.b("pushService is null , please check param of parseCommandMessage(2)");
        } else if (bVar2.z() == null) {
            d.b("pushService.getPushCallback() is null , please check param of parseCommandMessage(2)");
        } else {
            int a = bVar.a();
            if (a == 12287) {
                ICallBackResultService z = bVar2.z();
                if (z != null) {
                    z.onError(bVar.b(), bVar.getContent());
                }
            } else if (a == 12298) {
                bVar2.z().onSetPushTime(bVar.b(), bVar.getContent());
            } else if (a == 12306) {
                bVar2.z().onGetPushStatus(bVar.b(), h.i(bVar.getContent()));
            } else if (a == 12309) {
                bVar2.z().onGetNotificationStatus(bVar.b(), h.i(bVar.getContent()));
            } else if (a == 12289) {
                if (bVar.b() == 0) {
                    bVar2.X(bVar.getContent());
                }
                bVar2.z().onRegister(bVar.b(), bVar.getContent());
            } else if (a != 12290) {
                switch (a) {
                    case 12316:
                    case 12317:
                        ISetAppNotificationCallBackService B = bVar2.B();
                        if (B != null) {
                            B.onSetAppNotificationSwitch(bVar.b());
                            return;
                        }
                        return;
                    case 12318:
                        int i2 = 0;
                        try {
                            i2 = Integer.parseInt(bVar.getContent());
                        } catch (Exception unused) {
                        }
                        IGetAppNotificationCallBackService A = bVar2.A();
                        if (A != null) {
                            A.onGetAppNotificationSwitch(bVar.b(), i2);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            } else {
                bVar2.z().onUnRegister(bVar.b());
            }
        }
    }

    @Override // g.d.a.h.c
    public void a(Context context, BaseMode baseMode, IDataMessageCallBackService iDataMessageCallBackService) {
        if (baseMode != null && baseMode.getType() == 4105) {
            g.d.a.e.b bVar = (g.d.a.e.b) baseMode;
            d.a("mcssdk-CallBackResultProcessor:" + bVar.toString());
            g.b(new RunnableC0831a(bVar));
        }
    }
}
