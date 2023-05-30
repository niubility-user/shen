package com.tencent.mm.opensdk.diffdev.a;

import android.os.AsyncTask;
import com.tencent.mm.opensdk.diffdev.OAuthErrCode;
import com.tencent.mm.opensdk.diffdev.OAuthListener;
import com.tencent.mm.opensdk.utils.Log;
import org.json.JSONObject;

/* loaded from: classes9.dex */
class c extends AsyncTask<Void, Void, a> {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private OAuthListener f17608c;
    private int d;

    /* loaded from: classes9.dex */
    static class a {
        public OAuthErrCode a;
        public String b;

        /* renamed from: c  reason: collision with root package name */
        public int f17609c;

        a() {
        }
    }

    public c(String str, OAuthListener oAuthListener) {
        this.a = str;
        this.f17608c = oAuthListener;
        this.b = String.format("https://long.open.weixin.qq.com/connect/l/qrconnect?f=json&uuid=%s", str);
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0168 A[SYNTHETIC] */
    @Override // android.os.AsyncTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected a doInBackground(Void[] voidArr) {
        a aVar;
        OAuthErrCode oAuthErrCode;
        String str;
        OAuthErrCode oAuthErrCode2;
        OAuthErrCode oAuthErrCode3;
        String format;
        JSONObject jSONObject;
        int i2;
        OAuthErrCode oAuthErrCode4;
        Thread.currentThread().setName("OpenSdkNoopingTask");
        String str2 = this.a;
        if (str2 != null && str2.length() != 0) {
            Log.i("MicroMsg.SDK.NoopingTask", "doInBackground start " + isCancelled());
            while (!isCancelled()) {
                StringBuilder sb = new StringBuilder();
                sb.append(this.b);
                if (this.d == 0) {
                    str = "";
                } else {
                    str = "&last=" + this.d;
                }
                sb.append(str);
                String sb2 = sb.toString();
                long currentTimeMillis = System.currentTimeMillis();
                byte[] a2 = com.tencent.mm.opensdk.channel.a.a.a(sb2, 60000);
                long currentTimeMillis2 = System.currentTimeMillis();
                aVar = new a();
                Log.d("MicroMsg.SDK.NoopingResult", "star parse NoopingResult");
                if (a2 != null && a2.length != 0) {
                    try {
                    } catch (Exception e2) {
                        format = String.format("parse fail, build String fail, ex = %s", e2.getMessage());
                    }
                    try {
                        jSONObject = new JSONObject(new String(a2, "utf-8"));
                        int i3 = jSONObject.getInt("wx_errcode");
                        aVar.f17609c = i3;
                        Log.d("MicroMsg.SDK.NoopingResult", String.format("nooping uuidStatusCode = %d", Integer.valueOf(i3)));
                        i2 = aVar.f17609c;
                    } catch (Exception e3) {
                        format = String.format("parse json fail, ex = %s", e3.getMessage());
                        Log.e("MicroMsg.SDK.NoopingResult", format);
                        oAuthErrCode2 = OAuthErrCode.WechatAuth_Err_NormalErr;
                        aVar.a = oAuthErrCode2;
                        Log.d("MicroMsg.SDK.NoopingTask", String.format("nooping, url = %s, errCode = %s, uuidStatusCode = %d, time consumed = %d(ms)", sb2, aVar.a.toString(), Integer.valueOf(aVar.f17609c), Long.valueOf(currentTimeMillis2 - currentTimeMillis)));
                        oAuthErrCode3 = aVar.a;
                        if (oAuthErrCode3 == OAuthErrCode.WechatAuth_Err_OK) {
                        }
                        return aVar;
                    }
                    if (i2 != 408) {
                        if (i2 != 500) {
                            switch (i2) {
                                case 402:
                                    oAuthErrCode4 = OAuthErrCode.WechatAuth_Err_Timeout;
                                    aVar.a = oAuthErrCode4;
                                    break;
                                case 403:
                                    oAuthErrCode4 = OAuthErrCode.WechatAuth_Err_Cancel;
                                    aVar.a = oAuthErrCode4;
                                    break;
                                case 405:
                                    aVar.a = OAuthErrCode.WechatAuth_Err_OK;
                                    aVar.b = jSONObject.getString("wx_code");
                                    break;
                            }
                            Log.d("MicroMsg.SDK.NoopingTask", String.format("nooping, url = %s, errCode = %s, uuidStatusCode = %d, time consumed = %d(ms)", sb2, aVar.a.toString(), Integer.valueOf(aVar.f17609c), Long.valueOf(currentTimeMillis2 - currentTimeMillis)));
                            oAuthErrCode3 = aVar.a;
                            if (oAuthErrCode3 == OAuthErrCode.WechatAuth_Err_OK) {
                                int i4 = aVar.f17609c;
                                this.d = i4;
                                if (i4 == d.UUID_SCANED.a()) {
                                    this.f17608c.onQrcodeScanned();
                                } else if (aVar.f17609c != d.UUID_KEEP_CONNECT.a() && aVar.f17609c == d.UUID_CONFIRM.a()) {
                                    String str3 = aVar.b;
                                    if (str3 == null || str3.length() == 0) {
                                        Log.e("MicroMsg.SDK.NoopingTask", "nooping fail, confirm with an empty code!!!");
                                    }
                                }
                            } else {
                                Log.e("MicroMsg.SDK.NoopingTask", String.format("nooping fail, errCode = %s, uuidStatusCode = %d", oAuthErrCode3.toString(), Integer.valueOf(aVar.f17609c)));
                            }
                            return aVar;
                        }
                        oAuthErrCode4 = OAuthErrCode.WechatAuth_Err_NormalErr;
                        aVar.a = oAuthErrCode4;
                        Log.d("MicroMsg.SDK.NoopingTask", String.format("nooping, url = %s, errCode = %s, uuidStatusCode = %d, time consumed = %d(ms)", sb2, aVar.a.toString(), Integer.valueOf(aVar.f17609c), Long.valueOf(currentTimeMillis2 - currentTimeMillis)));
                        oAuthErrCode3 = aVar.a;
                        if (oAuthErrCode3 == OAuthErrCode.WechatAuth_Err_OK) {
                        }
                        return aVar;
                    }
                    oAuthErrCode4 = OAuthErrCode.WechatAuth_Err_OK;
                    aVar.a = oAuthErrCode4;
                    Log.d("MicroMsg.SDK.NoopingTask", String.format("nooping, url = %s, errCode = %s, uuidStatusCode = %d, time consumed = %d(ms)", sb2, aVar.a.toString(), Integer.valueOf(aVar.f17609c), Long.valueOf(currentTimeMillis2 - currentTimeMillis)));
                    oAuthErrCode3 = aVar.a;
                    if (oAuthErrCode3 == OAuthErrCode.WechatAuth_Err_OK) {
                    }
                    return aVar;
                }
                Log.e("MicroMsg.SDK.NoopingResult", "parse fail, buf is null");
                oAuthErrCode2 = OAuthErrCode.WechatAuth_Err_NetworkErr;
                aVar.a = oAuthErrCode2;
                Log.d("MicroMsg.SDK.NoopingTask", String.format("nooping, url = %s, errCode = %s, uuidStatusCode = %d, time consumed = %d(ms)", sb2, aVar.a.toString(), Integer.valueOf(aVar.f17609c), Long.valueOf(currentTimeMillis2 - currentTimeMillis)));
                oAuthErrCode3 = aVar.a;
                if (oAuthErrCode3 == OAuthErrCode.WechatAuth_Err_OK) {
                }
                return aVar;
            }
            Log.i("MicroMsg.SDK.NoopingTask", "IDiffDevOAuth.stopAuth / detach invoked");
            aVar = new a();
            oAuthErrCode = OAuthErrCode.WechatAuth_Err_Auth_Stopped;
            aVar.a = oAuthErrCode;
            return aVar;
        }
        Log.e("MicroMsg.SDK.NoopingTask", "run fail, uuid is null");
        aVar = new a();
        oAuthErrCode = OAuthErrCode.WechatAuth_Err_NormalErr;
        aVar.a = oAuthErrCode;
        return aVar;
    }

    @Override // android.os.AsyncTask
    protected void onPostExecute(a aVar) {
        a aVar2 = aVar;
        this.f17608c.onAuthFinish(aVar2.a, aVar2.b);
    }
}
