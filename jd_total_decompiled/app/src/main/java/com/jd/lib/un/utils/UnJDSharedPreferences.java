package com.jd.lib.un.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import java.util.Map;
import java.util.Set;

/* loaded from: classes16.dex */
public final class UnJDSharedPreferences implements SharedPreferences {
    private static final String TAG = UnJDSharedPreferences.class.getSimpleName();
    private SharedPreferences mSharedPreferences;

    /* loaded from: classes16.dex */
    public class JDEditor implements SharedPreferences.Editor {
        private SharedPreferences.Editor mEditor;

        public JDEditor(SharedPreferences.Editor editor) {
            this.mEditor = editor;
        }

        @Override // android.content.SharedPreferences.Editor
        public void apply() {
            this.mEditor.apply();
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor clear() {
            return this.mEditor.clear();
        }

        @Override // android.content.SharedPreferences.Editor
        public boolean commit() {
            return this.mEditor.commit();
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putBoolean(String str, boolean z) {
            return this.mEditor.putBoolean(str, z);
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putFloat(String str, float f2) {
            return this.mEditor.putFloat(str, f2);
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putInt(String str, int i2) {
            return this.mEditor.putInt(str, i2);
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putLong(String str, long j2) {
            return this.mEditor.putLong(str, j2);
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putString(String str, @Nullable String str2) {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                this.mEditor.putString(str, str2);
            }
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putStringSet(String str, @Nullable Set<String> set) {
            return this.mEditor.putStringSet(str, set);
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor remove(String str) {
            if (!TextUtils.isEmpty(str)) {
                removeOri(str);
                removeOri(String.format("%s_encrypt", str));
                return this.mEditor.remove(String.format("%s_encrypt_new", str));
            }
            return this.mEditor;
        }

        public SharedPreferences.Editor removeOri(String str) {
            return this.mEditor.remove(str);
        }
    }

    public UnJDSharedPreferences(Context context, String str, int i2) {
        this.mSharedPreferences = context.getApplicationContext().getSharedPreferences(str, i2);
    }

    @Override // android.content.SharedPreferences
    public boolean contains(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return this.mSharedPreferences.contains(str);
    }

    @Override // android.content.SharedPreferences
    public SharedPreferences.Editor edit() {
        return new JDEditor(this.mSharedPreferences.edit());
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
        return TextUtils.isEmpty(str) ? "" : this.mSharedPreferences.getString(str, "");
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

    @Override // android.content.SharedPreferences
    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.mSharedPreferences.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }
}
