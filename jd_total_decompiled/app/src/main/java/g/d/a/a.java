package g.d.a;

import android.content.Context;
import android.content.Intent;
import com.heytap.msp.push.callback.IDataMessageCallBackService;
import com.heytap.msp.push.mode.BaseMode;
import g.d.a.g.c;
import g.d.a.j.d;
import g.d.a.j.g;
import g.d.a.j.h;
import java.util.List;

/* loaded from: classes12.dex */
public class a {

    /* renamed from: g.d.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    class RunnableC0828a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f19403g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Intent f19404h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ IDataMessageCallBackService f19405i;

        RunnableC0828a(Context context, Intent intent, IDataMessageCallBackService iDataMessageCallBackService) {
            this.f19403g = context;
            this.f19404h = intent;
            this.f19405i = iDataMessageCallBackService;
        }

        @Override // java.lang.Runnable
        public void run() {
            List<BaseMode> b = c.b(this.f19403g, this.f19404h);
            if (b == null) {
                return;
            }
            for (BaseMode baseMode : b) {
                if (baseMode != null) {
                    for (g.d.a.h.c cVar : b.s().y()) {
                        if (cVar != null) {
                            cVar.a(this.f19403g, baseMode, this.f19405i);
                        }
                    }
                }
            }
        }
    }

    public static void a(Context context, Intent intent, IDataMessageCallBackService iDataMessageCallBackService) {
        if (context == null) {
            d.b("context is null , please check param of parseIntent()");
        } else if (intent == null) {
            d.b("intent is null , please check param of parseIntent()");
        } else if (iDataMessageCallBackService == null) {
            d.b("callback is null , please check param of parseIntent()");
        } else if (!h.h(context)) {
            d.b("push is null ,please check system has push");
        } else {
            g.a(new RunnableC0828a(context, intent, iDataMessageCallBackService));
        }
    }
}
