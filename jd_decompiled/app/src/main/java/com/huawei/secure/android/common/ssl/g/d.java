package com.huawei.secure.android.common.ssl.g;

import android.content.Context;
import android.os.AsyncTask;
import java.io.InputStream;

/* loaded from: classes12.dex */
public class d extends AsyncTask<Context, Integer, Boolean> {
    private static final String a = d.class.getSimpleName();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public Boolean doInBackground(Context... contextArr) {
        InputStream inputStream;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            inputStream = a.m(contextArr[0]);
        } catch (Exception e2) {
            f.d(a, "doInBackground: exception : " + e2.getMessage());
            inputStream = null;
        }
        f.b(a, "doInBackground: get bks from hms tss cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
        if (inputStream != null) {
            e.b(inputStream);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: b  reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Boolean bool) {
        if (bool.booleanValue()) {
            f.e(a, "onPostExecute: upate done");
        } else {
            f.d(a, "onPostExecute: upate failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: c  reason: merged with bridge method [inline-methods] */
    public void onProgressUpdate(Integer... numArr) {
        f.e(a, "onProgressUpdate");
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        f.b(a, "onPreExecute");
    }
}
