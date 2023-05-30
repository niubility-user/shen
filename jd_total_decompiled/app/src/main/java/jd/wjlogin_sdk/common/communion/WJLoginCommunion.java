package jd.wjlogin_sdk.common.communion;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Pair;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import jd.wjlogin_sdk.c.f;
import jd.wjlogin_sdk.common.global.WJLoginInternational;
import jd.wjlogin_sdk.common.listener.OnCommonCallback;
import jd.wjlogin_sdk.common.listener.OnDataCallback;
import jd.wjlogin_sdk.d.d;
import jd.wjlogin_sdk.model.ErrorResult;
import jd.wjlogin_sdk.model.FailResult;
import jd.wjlogin_sdk.model.ShareAppsInfo;
import jd.wjlogin_sdk.tlvtype.h0;
import jd.wjlogin_sdk.tlvtype.x;
import jd.wjlogin_sdk.util.b0;
import jd.wjlogin_sdk.util.g;
import jd.wjlogin_sdk.util.p;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes.dex */
public class WJLoginCommunion extends WJLoginInternational {
    private static final String s = "WJLogin.LoginCommunion";

    /* loaded from: classes.dex */
    class a implements f {
        final /* synthetic */ OnCommonCallback a;

        a(OnCommonCallback onCommonCallback) {
            this.a = onCommonCallback;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            WJLoginCommunion.this.i(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, this.a);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            OnCommonCallback onCommonCallback = this.a;
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(errorResult);
            }
            WJLoginCommunion.this.b((byte) -1, (short) 30, (short) 3);
        }
    }

    /* loaded from: classes.dex */
    private class b extends AsyncTask<String, Integer, List<String>> {
        private final OnCommonCallback a;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes11.dex */
        public class a implements f {
            a() {
            }

            @Override // jd.wjlogin_sdk.c.f
            public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
                WJLoginCommunion.this.h(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, b.this.a);
            }

            @Override // jd.wjlogin_sdk.c.f
            public void a(ErrorResult errorResult) {
                if (b.this.a != null) {
                    b.this.a.onErrorHandleInner(errorResult);
                }
                WJLoginCommunion.this.b((byte) -1, (short) 3, (short) 8);
            }
        }

        public b(OnCommonCallback onCommonCallback) {
            this.a = onCommonCallback;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public List<String> doInBackground(String... strArr) {
            return jd.wjlogin_sdk.common.communion.c.a();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List<String> list) {
            try {
                WJLoginCommunion.this.seq++;
                jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
                bVar.a(d.a((short) 3, (short) 8, g.d(), WJLoginCommunion.this.seq));
                d.a(bVar);
                if (list != null && !list.isEmpty()) {
                    Iterator<String> it = list.iterator();
                    while (it.hasNext()) {
                        d.w(bVar, it.next());
                    }
                }
                String a2 = WJLoginCommunion.this.getA2();
                if (a2 == null) {
                    a2 = "";
                }
                d.y(bVar, a2);
                String a3 = b0.a(jd.wjlogin_sdk.common.b.a());
                if (!TextUtils.isEmpty(a3)) {
                    d.q(bVar, a3);
                }
                ((jd.wjlogin_sdk.common.g) WJLoginCommunion.this).a = System.currentTimeMillis();
                jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new a());
                gVar.a(bVar.a()).a(WJLoginCommunion.this.x() ? 2 : 1).a(bVar.b()).a("exitShareLogin");
                gVar.b();
            } catch (Exception e2) {
                OnCommonCallback onCommonCallback = this.a;
                if (onCommonCallback != null) {
                    onCommonCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
                }
            }
        }
    }

    /* loaded from: classes.dex */
    private class c extends AsyncTask<String, Integer, List<String>> {
        private final OnDataCallback<List<ShareAppsInfo>> a;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes11.dex */
        public class a implements f {
            a() {
            }

            @Override // jd.wjlogin_sdk.c.f
            public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
                WJLoginCommunion.this.l(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second, c.this.a);
            }

            @Override // jd.wjlogin_sdk.c.f
            public void a(ErrorResult errorResult) {
                if (c.this.a != null) {
                    c.this.a.onErrorHandleInner(errorResult);
                }
                WJLoginCommunion.this.b((byte) -1, (short) 30, (short) 2);
            }
        }

        public c(OnDataCallback<List<ShareAppsInfo>> onDataCallback) {
            this.a = onDataCallback;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public List<String> doInBackground(String... strArr) {
            return jd.wjlogin_sdk.common.communion.c.b();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List<String> list) {
            try {
                WJLoginCommunion.this.seq++;
                jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
                bVar.a(d.a((short) 30, (short) 2, g.d(), WJLoginCommunion.this.seq));
                if (list != null && !list.isEmpty()) {
                    for (String str : list) {
                        d.w(bVar, str);
                        p.b(WJLoginCommunion.s, "key = " + str);
                    }
                    d.a(bVar);
                    String a2 = b0.a(jd.wjlogin_sdk.common.b.a());
                    if (!TextUtils.isEmpty(a2)) {
                        d.q(bVar, a2);
                    }
                    ((jd.wjlogin_sdk.common.g) WJLoginCommunion.this).a = System.currentTimeMillis();
                    jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new a());
                    gVar.a(bVar.a()).a(WJLoginCommunion.this.x() ? 2 : 1).a(bVar.b()).a("getShareLoginApps");
                    gVar.b();
                    return;
                }
                p.b(WJLoginCommunion.s, "keys is empty");
                if (this.a != null) {
                    this.a.onErrorHandleInner(b0.a((int) IMediaPlayer.MEDIA_ERROR_TIMED_OUT, "key\u5217\u8868\u4e3a\u7a7a\uff0c\u8bf7\u68c0\u67e5\u662f\u5426\u914d\u7f6e", (Exception) null));
                }
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            if (b2 == 0) {
                if (onCommonCallback != null) {
                    onCommonCallback.onSuccessHandleInner();
                }
                b(b2, (short) 3, (short) 8);
                return;
            }
            x p = aVar.p();
            FailResult failResult = new FailResult();
            a(failResult, b2, p);
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(failResult);
            }
            b(b2, (short) 3, (short) 8);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
            b((byte) -2, (short) 3, (short) 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0033, code lost:
        r7.onErrorHandleInner(jd.wjlogin_sdk.util.b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", new java.lang.Exception(jd.wjlogin_sdk.util.y.a)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void i(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnCommonCallback onCommonCallback) {
        try {
            FailResult failResult = new FailResult();
            if (b2 == 0) {
                a(aVar, (String) null);
                if (!TextUtils.isEmpty(getA2()) && !TextUtils.isEmpty(getPin())) {
                    if (onCommonCallback != null) {
                        onCommonCallback.onSuccessHandleInner();
                    }
                    b(b2, (short) 30, (short) 3);
                    return;
                }
                b((byte) -2, (short) 30, (short) 3);
                return;
            }
            a(failResult, b2, aVar.p());
            onCommonCallback.onFailHandleInner(failResult);
            b(b2, (short) 30, (short) 3);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (onCommonCallback != null) {
                onCommonCallback.onFailHandleInner(a());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l(byte b2, jd.wjlogin_sdk.tlvtype.a aVar, OnDataCallback<List<ShareAppsInfo>> onDataCallback) {
        try {
            FailResult failResult = new FailResult();
            if (b2 == 0) {
                List<h0> R = aVar.R();
                ArrayList arrayList = new ArrayList();
                if (R == null || R.size() <= 0) {
                    return;
                }
                for (h0 h0Var : R) {
                    if (!TextUtils.isEmpty(h0Var.b())) {
                        ShareAppsInfo shareAppsInfo = new ShareAppsInfo();
                        shareAppsInfo.setKey(h0Var.b());
                        shareAppsInfo.setIconUrl(h0Var.a());
                        shareAppsInfo.setLoginName(h0Var.c());
                        shareAppsInfo.setPhone(h0Var.e());
                        shareAppsInfo.setNickName(h0Var.d());
                        arrayList.add(shareAppsInfo);
                    }
                }
                onDataCallback.onSuccessHandleInner(arrayList);
                return;
            }
            a(failResult, b2, aVar.p());
            onDataCallback.onFailHandleInner(failResult);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (onDataCallback != null) {
                onDataCallback.onFailHandleInner(a());
            }
        }
    }

    public void exitShareLogin(OnCommonCallback onCommonCallback) {
        new b(onCommonCallback).execute(new String[0]);
    }

    public void getShareLoginApps(OnDataCallback<List<ShareAppsInfo>> onDataCallback) {
        new c(onDataCallback).execute(new String[0]);
    }

    public void shareLogin(String str, OnCommonCallback onCommonCallback) {
        try {
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(d.a((short) 30, (short) 3, g.d(), this.seq));
            d.a(bVar);
            d.w(bVar, str);
            String a2 = b0.a(jd.wjlogin_sdk.common.b.a());
            if (!TextUtils.isEmpty(a2)) {
                d.q(bVar, a2);
            }
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new a(onCommonCallback));
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("shareLogin");
            gVar.b();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
