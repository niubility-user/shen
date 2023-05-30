package com.facebook.react.devsupport;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.modules.debug.interfaces.DeveloperSettings;
import com.facebook.react.packagerconnection.PackagerConnectionSettings;

@VisibleForTesting
/* loaded from: classes12.dex */
public class DevInternalSettings implements DeveloperSettings, SharedPreferences.OnSharedPreferenceChangeListener {
    private static final String PREFS_ANIMATIONS_DEBUG_KEY = "animations_debug";
    private static final String PREFS_FPS_DEBUG_KEY = "fps_debug";
    private static final String PREFS_HOT_MODULE_REPLACEMENT_KEY = "hot_module_replacement";
    private static final String PREFS_INSPECTOR_DEBUG_KEY = "inspector_debug";
    private static final String PREFS_JS_BUNDLE_DELTAS_CPP_KEY = "js_bundle_deltas_cpp";
    private static final String PREFS_JS_BUNDLE_DELTAS_KEY = "js_bundle_deltas";
    private static final String PREFS_JS_DEV_MODE_DEBUG_KEY = "js_dev_mode_debug";
    private static final String PREFS_JS_MINIFY_DEBUG_KEY = "js_minify_debug";
    private static final String PREFS_RELOAD_ON_JS_CHANGE_KEY = "reload_on_js_change";
    private static final String PREFS_REMOTE_JS_DEBUG_KEY = "remote_js_debug";
    private final Listener mListener;
    private final PackagerConnectionSettings mPackagerConnectionSettings;
    private final SharedPreferences mPreferences;
    private final boolean mSupportsNativeDeltaClients;

    /* loaded from: classes12.dex */
    public interface Listener {
        void onInternalSettingsChanged();
    }

    public DevInternalSettings(Context context, Listener listener) {
        this(context, listener, true);
    }

    public static DevInternalSettings withoutNativeDeltaClient(Context context, Listener listener) {
        return new DevInternalSettings(context, listener, false);
    }

    public PackagerConnectionSettings getPackagerConnectionSettings() {
        return this.mPackagerConnectionSettings;
    }

    @Override // com.facebook.react.modules.debug.interfaces.DeveloperSettings
    public boolean isAnimationFpsDebugEnabled() {
        return this.mPreferences.getBoolean(PREFS_ANIMATIONS_DEBUG_KEY, false);
    }

    @SuppressLint({"SharedPreferencesUse"})
    public boolean isBundleDeltasCppEnabled() {
        return this.mSupportsNativeDeltaClients && this.mPreferences.getBoolean(PREFS_JS_BUNDLE_DELTAS_CPP_KEY, false);
    }

    @SuppressLint({"SharedPreferencesUse"})
    public boolean isBundleDeltasEnabled() {
        return this.mPreferences.getBoolean(PREFS_JS_BUNDLE_DELTAS_KEY, true);
    }

    @Override // com.facebook.react.modules.debug.interfaces.DeveloperSettings
    public boolean isElementInspectorEnabled() {
        return this.mPreferences.getBoolean(PREFS_INSPECTOR_DEBUG_KEY, false);
    }

    @Override // com.facebook.react.modules.debug.interfaces.DeveloperSettings
    public boolean isFpsDebugEnabled() {
        return this.mPreferences.getBoolean(PREFS_FPS_DEBUG_KEY, false);
    }

    public boolean isHotModuleReplacementEnabled() {
        return this.mPreferences.getBoolean(PREFS_HOT_MODULE_REPLACEMENT_KEY, false);
    }

    @Override // com.facebook.react.modules.debug.interfaces.DeveloperSettings
    public boolean isJSDevModeEnabled() {
        return this.mPreferences.getBoolean(PREFS_JS_DEV_MODE_DEBUG_KEY, true);
    }

    @Override // com.facebook.react.modules.debug.interfaces.DeveloperSettings
    public boolean isJSMinifyEnabled() {
        return this.mPreferences.getBoolean(PREFS_JS_MINIFY_DEBUG_KEY, false);
    }

    @Override // com.facebook.react.modules.debug.interfaces.DeveloperSettings
    public boolean isNuclideJSDebugEnabled() {
        return false;
    }

    public boolean isReloadOnJSChangeEnabled() {
        return this.mPreferences.getBoolean(PREFS_RELOAD_ON_JS_CHANGE_KEY, false);
    }

    @Override // com.facebook.react.modules.debug.interfaces.DeveloperSettings
    public boolean isRemoteJSDebugEnabled() {
        return this.mPreferences.getBoolean(PREFS_REMOTE_JS_DEBUG_KEY, false);
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (this.mListener != null) {
            if (PREFS_FPS_DEBUG_KEY.equals(str) || PREFS_RELOAD_ON_JS_CHANGE_KEY.equals(str) || PREFS_JS_DEV_MODE_DEBUG_KEY.equals(str) || PREFS_JS_BUNDLE_DELTAS_KEY.equals(str) || PREFS_JS_BUNDLE_DELTAS_CPP_KEY.equals(str) || PREFS_JS_MINIFY_DEBUG_KEY.equals(str)) {
                this.mListener.onInternalSettingsChanged();
            }
        }
    }

    @SuppressLint({"SharedPreferencesUse"})
    public void setBundleDeltasCppEnabled(boolean z) {
        this.mPreferences.edit().putBoolean(PREFS_JS_BUNDLE_DELTAS_CPP_KEY, z).apply();
    }

    @SuppressLint({"SharedPreferencesUse"})
    public void setBundleDeltasEnabled(boolean z) {
        this.mPreferences.edit().putBoolean(PREFS_JS_BUNDLE_DELTAS_KEY, z).apply();
    }

    public void setElementInspectorEnabled(boolean z) {
        this.mPreferences.edit().putBoolean(PREFS_INSPECTOR_DEBUG_KEY, z).apply();
    }

    public void setFpsDebugEnabled(boolean z) {
        this.mPreferences.edit().putBoolean(PREFS_FPS_DEBUG_KEY, z).apply();
    }

    public void setHotModuleReplacementEnabled(boolean z) {
        this.mPreferences.edit().putBoolean(PREFS_HOT_MODULE_REPLACEMENT_KEY, z).apply();
    }

    public void setReloadOnJSChangeEnabled(boolean z) {
        this.mPreferences.edit().putBoolean(PREFS_RELOAD_ON_JS_CHANGE_KEY, z).apply();
    }

    @Override // com.facebook.react.modules.debug.interfaces.DeveloperSettings
    public void setRemoteJSDebugEnabled(boolean z) {
        this.mPreferences.edit().putBoolean(PREFS_REMOTE_JS_DEBUG_KEY, z).apply();
    }

    private DevInternalSettings(Context context, Listener listener, boolean z) {
        this.mListener = listener;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.mPreferences = defaultSharedPreferences;
        defaultSharedPreferences.registerOnSharedPreferenceChangeListener(this);
        this.mPackagerConnectionSettings = new PackagerConnectionSettings(context);
        this.mSupportsNativeDeltaClients = z;
    }
}
