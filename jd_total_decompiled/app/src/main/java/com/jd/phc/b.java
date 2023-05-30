package com.jd.phc;

import android.content.Context;
import android.os.AsyncTask;
import com.jd.phc.e;
import com.jd.sec.LogoManager;

/* loaded from: classes.dex */
public class b extends AsyncTask<Void, Void, Void> {
    private Context a;
    private e.a b;

    public b(Context context, e.a aVar) {
        this.b = aVar;
        this.a = context;
    }

    private com.jd.phc.i.d.d b(Throwable th) {
        try {
            com.jd.phc.i.d.d dVar = com.jd.phc.i.d.d.UNKNOWN_ERROR;
            if (th instanceof com.jd.phc.i.d.b) {
                return ((com.jd.phc.i.d.b) th).getErrorCode();
            }
            dVar.setDesc(th);
            return dVar;
        } catch (Exception e2) {
            if (a.a) {
                e2.printStackTrace();
                return null;
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public Void doInBackground(Void... voidArr) {
        Context context = this.a;
        if (context != null) {
            LogoManager.getInstance(context).init();
            String logo = LogoManager.getInstance(this.a).getLogo();
            g.b(this.a, logo);
            if (a.a) {
                com.jd.phc.i.b.c("InitialTask", "eid=" + logo);
            }
            try {
                f.a(this.a).d();
                return null;
            } catch (Throwable th) {
                if (a.a) {
                    th.printStackTrace();
                }
                com.jd.phc.i.d.d b = b(th);
                if (b != null) {
                    this.b.a(b.getErrorCode(), b.getDesc());
                    return null;
                }
                return null;
            }
        }
        throw new IllegalArgumentException("context can not null");
    }
}
