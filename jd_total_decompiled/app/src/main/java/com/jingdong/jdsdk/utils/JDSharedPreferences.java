package com.jingdong.jdsdk.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.jd.jdsdk.security.AesCbcCrypto;
import com.jingdong.common.utils.security.JDAesCrypto;
import com.jingdong.sdk.oklog.OKLog;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public final class JDSharedPreferences implements SharedPreferences {
    private static final String AES_KEY = "!@#$";
    private static final String TAG = JDSharedPreferences.class.getSimpleName();
    private SharedPreferences mSharedPreferences;

    /* loaded from: classes.dex */
    public class a implements SharedPreferences.Editor {
        private SharedPreferences.Editor a;

        public a(JDSharedPreferences jDSharedPreferences, SharedPreferences.Editor editor) {
            this.a = editor;
        }

        @Override // android.content.SharedPreferences.Editor
        public void apply() {
            this.a.apply();
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor clear() {
            return this.a.clear();
        }

        @Override // android.content.SharedPreferences.Editor
        public boolean commit() {
            return this.a.commit();
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putBoolean(String str, boolean z) {
            return this.a.putBoolean(str, z);
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putFloat(String str, float f2) {
            return this.a.putFloat(str, f2);
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putInt(String str, int i2) {
            return this.a.putInt(str, i2);
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putLong(String str, long j2) {
            return this.a.putLong(str, j2);
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putString(String str, @Nullable String str2) {
            if (!TextUtils.isEmpty(str)) {
                String encrypt = JDSharedPreferences.encrypt(str2);
                if (!TextUtils.isEmpty(encrypt)) {
                    String format = String.format("%s_encryptV2", str);
                    this.a.putString(format, encrypt);
                    if (OKLog.D) {
                        OKLog.d(JDSharedPreferences.TAG, "Put String with key : " + format + ", value : " + encrypt);
                    }
                    this.a.remove(str);
                    this.a.remove(String.format("%s_encrypt", str));
                }
            }
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putStringSet(String str, @Nullable Set<String> set) {
            return this.a.putStringSet(str, set);
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor remove(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.a.remove(str);
                this.a.remove(String.format("%s_encrypt", str));
                this.a.remove(String.format("%s_encryptV2", str));
            }
            return this.a;
        }
    }

    public JDSharedPreferences(Context context, String str, int i2) {
        this.mSharedPreferences = context.getSharedPreferences(str, i2);
    }

    private static String decrypt(String str) {
        try {
            return new String(AesCbcCrypto.decrypt(com.jd.jdsdk.security.a.d(str.getBytes()), JDAesCrypto.getRawKey(AES_KEY), (byte[]) null));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String encrypt(String str) {
        try {
            return com.jd.jdsdk.security.a.i(AesCbcCrypto.encrypt(str.getBytes(), JDAesCrypto.getRawKey(AES_KEY), (byte[]) null));
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
            return null;
        }
    }

    @Override // android.content.SharedPreferences
    public boolean contains(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return this.mSharedPreferences.contains(str) || this.mSharedPreferences.contains(String.format("%s_encrypt", str)) || this.mSharedPreferences.contains(String.format("%s_encryptV2", str));
    }

    @Override // android.content.SharedPreferences
    public SharedPreferences.Editor edit() {
        return new a(this, this.mSharedPreferences.edit());
    }

    @Override // android.content.SharedPreferences
    public Map<String, ?> getAll() {
        return this.mSharedPreferences.getAll();
    }

    @Override // android.content.SharedPreferences
    public boolean getBoolean(String str, boolean z) {
        return this.mSharedPreferences.getBoolean(str, z);
    }

    @Override // android.content.SharedPreferences
    public float getFloat(String str, float f2) {
        return this.mSharedPreferences.getFloat(str, f2);
    }

    @Override // android.content.SharedPreferences
    public int getInt(String str, int i2) {
        return this.mSharedPreferences.getInt(str, i2);
    }

    @Override // android.content.SharedPreferences
    public long getLong(String str, long j2) {
        return this.mSharedPreferences.getLong(str, j2);
    }

    @Override // android.content.SharedPreferences
    @Nullable
    public String getString(String str, @Nullable String str2) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        long currentTimeMillis = System.currentTimeMillis();
        String str3 = null;
        String format = String.format("%s_encrypt", str);
        String format2 = String.format("%s_encryptV2", str);
        if (this.mSharedPreferences.contains(format2)) {
            String string = this.mSharedPreferences.getString(format2, "");
            if (!TextUtils.isEmpty(string)) {
                str3 = decrypt(string);
                if (OKLog.D) {
                    OKLog.d(TAG, "get encrypt string with key : " + format2 + ", value : " + string + ", clearText : " + str3);
                }
            }
        } else if (this.mSharedPreferences.contains(format)) {
            String string2 = this.mSharedPreferences.getString(format, "");
            if (!TextUtils.isEmpty(string2)) {
                str3 = JDAesCrypto.decrypt(AES_KEY, string2);
                if (OKLog.D) {
                    OKLog.d(TAG, "get encrypt string with key : " + format + ", value : " + string2 + ", clearText : " + str3);
                }
                restore(str, str3);
            }
        } else if (this.mSharedPreferences.contains(str)) {
            str3 = this.mSharedPreferences.getString(str, "");
            if (OKLog.D) {
                OKLog.d(TAG, "get origin string with key : " + str + ", value : " + str3);
            }
            restore(str, str3);
        }
        if (OKLog.D) {
            OKLog.d(TAG, "[JDSharedPreferences] getString cost : " + (System.currentTimeMillis() - currentTimeMillis));
        }
        return !TextUtils.isEmpty(str3) ? str3 : str2;
    }

    @Override // android.content.SharedPreferences
    @Nullable
    public Set<String> getStringSet(String str, @Nullable Set<String> set) {
        return this.mSharedPreferences.getStringSet(str, set);
    }

    @Override // android.content.SharedPreferences
    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.mSharedPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    void restore(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        a aVar = (a) edit();
        aVar.putString(str, str2);
        aVar.apply();
    }

    @Override // android.content.SharedPreferences
    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.mSharedPreferences.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }
}
