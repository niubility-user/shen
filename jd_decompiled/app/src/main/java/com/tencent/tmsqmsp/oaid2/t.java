package com.tencent.tmsqmsp.oaid2;

import android.os.AsyncTask;

/* loaded from: classes9.dex */
public class t extends AsyncTask<Void, Void, Boolean> {
    public q a;
    public s b;

    public t(q qVar, s sVar) {
        this.a = qVar;
        this.b = sVar;
    }

    @Override // android.os.AsyncTask
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public Boolean doInBackground(Void... voidArr) {
        boolean c2;
        s sVar;
        if (this.a == null) {
            return Boolean.FALSE;
        }
        int i2 = 0;
        while (true) {
            try {
                c2 = this.a.c();
            } catch (InterruptedException unused) {
            }
            if (c2) {
                break;
            }
            Thread.sleep(10L);
            i2++;
            if (i2 >= 30) {
                break;
            }
        }
        if (c2 && (sVar = this.b) != null) {
            sVar.a(true);
        }
        return Boolean.valueOf(c2);
    }

    @Override // android.os.AsyncTask
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Boolean bool) {
        super.onPostExecute(bool);
    }
}
