package com.meizu.cloud.pushsdk.c.c;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import com.jdpay.lib.crypto.AES;
import com.jingdong.common.jdreactFramework.utils.RSAUtils;
import com.jingdong.common.utils.security.JDKeyStore;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class a {

    /* renamed from: i  reason: collision with root package name */
    private static a f15689i;

    /* renamed from: j  reason: collision with root package name */
    private static final Object f15690j = new Object();
    private byte[] a;
    private byte[] b;

    /* renamed from: c  reason: collision with root package name */
    private byte[] f15691c;
    private byte[] d;

    /* renamed from: e  reason: collision with root package name */
    private byte[] f15692e;

    /* renamed from: f  reason: collision with root package name */
    private PublicKey f15693f;

    /* renamed from: g  reason: collision with root package name */
    private final SharedPreferences f15694g;

    /* renamed from: h  reason: collision with root package name */
    private final SharedPreferences f15695h;

    private a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.x.y.1", 0);
        this.f15694g = sharedPreferences;
        this.f15695h = context.getSharedPreferences("com.x.y.2", 0);
        Integer.parseInt(sharedPreferences.getString("keyTimeout", "0"));
        sharedPreferences.getLong("createDate", 0L);
        o();
        byte[] bArr = this.a;
        if (bArr != null && bArr.length != 0) {
            byte[] bArr2 = this.b;
            if (bArr2 == null || bArr2.length == 0) {
                PublicKey f2 = f(context);
                this.f15693f = f2;
                if (f2 != null) {
                    g();
                    return;
                }
                return;
            }
            return;
        }
        PublicKey f3 = f(context);
        this.f15693f = f3;
        if (f3 != null) {
            i();
            return;
        }
        sharedPreferences.edit().clear().apply();
        try {
            b();
            PublicKey f4 = f(context);
            this.f15693f = f4;
            if (f4 != null) {
                i();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private String a(InputStream inputStream) {
        String str;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            str = null;
            try {
                int read = inputStream.read();
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(read);
            } catch (IOException unused) {
            } catch (Throwable th) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException unused2) {
                }
                throw th;
            }
        }
        str = byteArrayOutputStream.toString();
        try {
            byteArrayOutputStream.close();
        } catch (IOException unused3) {
            return str;
        }
    }

    private void b() throws IOException {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(PushConstants.URL_DOWNLOAD_PUBLIC_KEY).openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            try {
                httpURLConnection.setRequestMethod("GET");
            } catch (ProtocolException e2) {
                e2.printStackTrace();
            }
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            InputStream inputStream = null;
            try {
                d("code = " + httpURLConnection.getResponseCode());
                inputStream = httpURLConnection.getInputStream();
                if (inputStream != null) {
                    String a = a(inputStream);
                    d("body = " + a);
                    if (!TextUtils.isEmpty(a)) {
                        try {
                            JSONObject jSONObject = new JSONObject(a);
                            if (jSONObject.getInt("code") == 200) {
                                String string = jSONObject.getString("value");
                                SharedPreferences.Editor edit = this.f15695h.edit();
                                edit.putString("publicKey", string);
                                edit.apply();
                            }
                        } catch (Exception e3) {
                            h("downloadPublicKey message error " + e3.getMessage());
                        }
                    }
                }
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused) {
                    }
                }
                httpURLConnection.disconnect();
            }
        } catch (MalformedURLException unused2) {
        }
    }

    public static void c(Context context) {
        if (f15689i == null) {
            synchronized (f15690j) {
                if (f15689i == null) {
                    f15689i = new a(context);
                }
            }
        }
    }

    private void d(String str) {
        DebugLogger.d("HttpKeyMgr", str);
    }

    private PublicKey f(Context context) {
        d("load publicKey from preference");
        String string = this.f15695h.getString("publicKey", "");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        try {
            return KeyFactory.getInstance(RSAUtils.KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(Base64.decode(string, 2)));
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        } catch (InvalidKeySpecException e3) {
            e3.printStackTrace();
            return null;
        } catch (Exception e4) {
            e4.printStackTrace();
            return null;
        }
    }

    private void g() {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(1, this.f15693f);
            byte[] doFinal = cipher.doFinal(this.a);
            this.b = doFinal;
            this.d = Base64.encode(doFinal, 2);
            d("***** aKey64: " + new String(this.d));
            SharedPreferences.Editor edit = this.f15694g.edit();
            edit.putString("aKey64", new String(this.d));
            edit.apply();
        } catch (InvalidKeyException e2) {
            e2.printStackTrace();
        } catch (NoSuchAlgorithmException e3) {
            e3.printStackTrace();
        } catch (BadPaddingException e4) {
            e4.printStackTrace();
        } catch (IllegalBlockSizeException e5) {
            e5.printStackTrace();
        } catch (NoSuchPaddingException e6) {
            e6.printStackTrace();
        } catch (Exception e7) {
            e7.printStackTrace();
        }
    }

    private void h(String str) {
        DebugLogger.e("HttpKeyMgr", str);
    }

    private void i() {
        k();
        g();
    }

    private void k() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(JDKeyStore.KEY_TYPE_AES);
            keyGenerator.init(128);
            byte[] encoded = keyGenerator.generateKey().getEncoded();
            this.a = encoded;
            this.f15691c = Base64.encode(encoded, 2);
            d("***** rKey64: " + new String(this.f15691c));
            SharedPreferences.Editor edit = this.f15694g.edit();
            edit.putString("rKey64", new String(this.f15691c));
            edit.apply();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static a l() {
        a aVar = f15689i;
        if (aVar != null) {
            return aVar;
        }
        throw new IllegalStateException("KeyMgr is not initialised - invoke at least once with parameterised init/get");
    }

    private void o() {
        d("loadKeys");
        String string = this.f15694g.getString("sKey64", "");
        d("saved sKey64: " + string);
        if (!TextUtils.isEmpty(string)) {
            this.f15692e = string.getBytes();
        }
        String string2 = this.f15694g.getString("aKey64", "");
        d("saved aKey64: " + string2);
        if (!TextUtils.isEmpty(string2)) {
            byte[] bytes = string2.getBytes();
            this.d = bytes;
            this.b = Base64.decode(bytes, 2);
        }
        String string3 = this.f15694g.getString("rKey64", "");
        d("saved rKey64: " + string3);
        if (TextUtils.isEmpty(string3)) {
            return;
        }
        byte[] bytes2 = string3.getBytes();
        this.f15691c = bytes2;
        this.a = Base64.decode(bytes2, 2);
        d("saved rKey: " + new String(this.a));
    }

    public byte[] e(byte[] bArr) {
        String str;
        byte[] bArr2 = this.a;
        if (bArr2 == null || bArr2.length == 0) {
            str = "rKey null!";
        } else if (bArr != null && bArr.length != 0) {
            d(">>>>>>>>>> encrypt input >>>>>>>>>>\n" + new String(Base64.encode(bArr, 2)));
            d("<<<<<<<<<< encrypt input <<<<<<<<<<");
            try {
                Cipher cipher = Cipher.getInstance(AES.ALGORITHM);
                cipher.init(1, new SecretKeySpec(this.a, JDKeyStore.KEY_TYPE_AES), new IvParameterSpec(this.a));
                byte[] doFinal = cipher.doFinal(bArr);
                d(">>>>>>>>>> encrypt output >>>>>>>>>>\n" + new String(Base64.encode(doFinal, 2)));
                d("<<<<<<<<<< encrypt output <<<<<<<<<<");
                return doFinal;
            } catch (InvalidAlgorithmParameterException e2) {
                e2.printStackTrace();
                return null;
            } catch (InvalidKeyException e3) {
                e3.printStackTrace();
                return null;
            } catch (NoSuchAlgorithmException e4) {
                e4.printStackTrace();
                return null;
            } catch (BadPaddingException e5) {
                e5.printStackTrace();
                return null;
            } catch (IllegalBlockSizeException e6) {
                e6.printStackTrace();
                return null;
            } catch (NoSuchPaddingException e7) {
                e7.printStackTrace();
                return null;
            } catch (Exception e8) {
                e8.printStackTrace();
                return null;
            }
        } else {
            str = "input null!";
        }
        h(str);
        return null;
    }

    public void j(String str) {
        this.f15692e = str.getBytes();
        SharedPreferences.Editor edit = this.f15694g.edit();
        edit.putString("sKey64", new String(this.f15692e));
        edit.apply();
    }

    public byte[] m() {
        return this.d;
    }

    public byte[] n() {
        return this.f15692e;
    }
}
