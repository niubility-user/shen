package com.facebook.react.bridge;

import android.content.Context;
import com.facebook.react.common.DebugServerException;

/* loaded from: classes.dex */
public abstract class JSBundleLoader {
    public String jsBundle;
    public String jsCommonBundle;

    public JSBundleLoader() {
    }

    public static JSBundleLoader createAssetLoader(final Context context, final String str, final boolean z) {
        return new JSBundleLoader() { // from class: com.facebook.react.bridge.JSBundleLoader.1
            @Override // com.facebook.react.bridge.JSBundleLoader
            public String loadScript(JSBundleLoaderDelegate jSBundleLoaderDelegate) {
                jSBundleLoaderDelegate.loadScriptFromAssets(context.getAssets(), str, z);
                return str;
            }
        };
    }

    public static JSBundleLoader createCachedBundleFromNetworkLoader(final String str, final String str2) {
        return new JSBundleLoader() { // from class: com.facebook.react.bridge.JSBundleLoader.5
            @Override // com.facebook.react.bridge.JSBundleLoader
            public String loadScript(JSBundleLoaderDelegate jSBundleLoaderDelegate) {
                try {
                    jSBundleLoaderDelegate.loadScriptFromFile(str2, str, false);
                    return str;
                } catch (Exception e2) {
                    throw DebugServerException.makeGeneric(e2.getMessage(), e2);
                }
            }
        };
    }

    public static JSBundleLoader createDeltaFromNetworkLoader(final String str, final NativeDeltaClient nativeDeltaClient) {
        return new JSBundleLoader() { // from class: com.facebook.react.bridge.JSBundleLoader.6
            @Override // com.facebook.react.bridge.JSBundleLoader
            public String loadScript(JSBundleLoaderDelegate jSBundleLoaderDelegate) {
                try {
                    jSBundleLoaderDelegate.loadScriptFromDeltaBundle(str, nativeDeltaClient, false);
                    return str;
                } catch (Exception e2) {
                    throw DebugServerException.makeGeneric(e2.getMessage(), e2);
                }
            }
        };
    }

    public static JSBundleLoader createFileLoader(String str) {
        return createFileLoader(str, str, false);
    }

    public static JSBundleLoader createRemoteDebuggerBundleLoader(final String str, final String str2) {
        return new JSBundleLoader() { // from class: com.facebook.react.bridge.JSBundleLoader.7
            @Override // com.facebook.react.bridge.JSBundleLoader
            public String loadScript(JSBundleLoaderDelegate jSBundleLoaderDelegate) {
                jSBundleLoaderDelegate.setSourceURLs(str2, str);
                return str2;
            }
        };
    }

    public static JSBundleLoader createSepAssetLoader(final Context context, final String str, final String str2, final boolean z) {
        return new JSBundleLoader(str, str2) { // from class: com.facebook.react.bridge.JSBundleLoader.4
            @Override // com.facebook.react.bridge.JSBundleLoader
            public String loadScript(JSBundleLoaderDelegate jSBundleLoaderDelegate) {
                if (str.startsWith("assets://") && str2.startsWith("assets://")) {
                    jSBundleLoaderDelegate.loadScriptFromSepAssets(context.getAssets(), str, str2, z);
                } else if (str2.startsWith("assets://")) {
                    jSBundleLoaderDelegate.loadScriptFromSepCommonAssets(context.getAssets(), str, str2, z);
                } else {
                    String str3 = str;
                    jSBundleLoaderDelegate.loadScriptFromSepFile(str3, str3, str2, z);
                }
                return str;
            }
        };
    }

    public abstract String loadScript(JSBundleLoaderDelegate jSBundleLoaderDelegate);

    public JSBundleLoader(String str, String str2) {
        this.jsBundle = str;
        this.jsCommonBundle = str2;
    }

    public static JSBundleLoader createFileLoader(final String str, final String str2, final boolean z) {
        return new JSBundleLoader(str, null) { // from class: com.facebook.react.bridge.JSBundleLoader.2
            @Override // com.facebook.react.bridge.JSBundleLoader
            public String loadScript(JSBundleLoaderDelegate jSBundleLoaderDelegate) {
                jSBundleLoaderDelegate.loadScriptFromFile(str, str2, z);
                return str;
            }
        };
    }

    public static JSBundleLoader createFileLoader(final Context context, final String str, String str2, final String str3, final boolean z) {
        return new JSBundleLoader(str, str3) { // from class: com.facebook.react.bridge.JSBundleLoader.3
            @Override // com.facebook.react.bridge.JSBundleLoader
            public String loadScript(JSBundleLoaderDelegate jSBundleLoaderDelegate) {
                if (str.startsWith("assets://")) {
                    jSBundleLoaderDelegate.loadScriptFromSepCommonFile(context.getAssets(), str, str3, z);
                } else {
                    String str4 = str;
                    jSBundleLoaderDelegate.loadScriptFromSepFile(str4, str4, str3, z);
                }
                return str;
            }
        };
    }
}
