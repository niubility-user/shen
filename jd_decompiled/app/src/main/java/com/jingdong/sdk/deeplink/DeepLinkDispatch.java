package com.jingdong.sdk.deeplink;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import com.airbnb.deeplinkdispatch.DeepLink;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: classes7.dex */
public class DeepLinkDispatch {
    @Deprecated
    public static final String JD_SCHEME = "jingdong";
    private static final String TAG = "DeepLinkDispatch";
    private static HashMap<String, a> mIntentCallbackMap = new HashMap<>();
    private static b smSwitch;

    /* loaded from: classes7.dex */
    public interface a {
        void a(String str, String str2);
    }

    /* loaded from: classes7.dex */
    public interface b {
        boolean isSwitchOpen(String str);
    }

    private static Intent generateIntentByUri(Context context, String str, Bundle bundle, int i2) {
        DeepLinkUri parse;
        Set<String> queryParameterNames;
        if (context != null && str != null) {
            com.jingdong.sdk.deeplink.a d = com.jingdong.sdk.deeplink.b.a().d(str);
            if (d == null) {
                return null;
            }
            if (smSwitch != null) {
                String b2 = d.b();
                if (TextUtils.isEmpty(b2)) {
                    return null;
                }
                if (!smSwitch.isSwitchOpen(b2)) {
                    String str2 = "bundle " + b2 + "'s switch is closed.";
                    return null;
                }
            }
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(context, d.a()));
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            intent.putExtra(DeepLink.IS_DEEP_LINK, true);
            Map<String, String> c2 = d.c(str);
            if (c2 != null && !c2.isEmpty()) {
                for (String str3 : c2.keySet()) {
                    intent.putExtra(str3, c2.get(str3));
                }
            }
            if (str.indexOf("?") != -1 && (parse = DeepLinkUri.parse(str)) != null && (queryParameterNames = parse.queryParameterNames()) != null && !queryParameterNames.isEmpty()) {
                for (String str4 : queryParameterNames) {
                    intent.putExtra(str4, parse.queryParameter(str4));
                }
            }
            intent.addFlags(i2);
            return intent;
        }
        String str5 = "context or uri is null. context = " + context + ". uri = " + str;
        return null;
    }

    private static void performNavigationCallback(Object obj, Intent intent) {
        if (obj == null || intent == null) {
            return;
        }
        try {
            if (intent.getComponent() == null) {
                return;
            }
            String className = intent.getComponent().getClassName();
            String name = obj.getClass().getName();
            a aVar = mIntentCallbackMap.get(className);
            if (aVar != null) {
                aVar.a(name, className);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void registNavigationCallback(String str, a aVar) {
        if (TextUtils.isEmpty(str) || aVar == null) {
            return;
        }
        if (mIntentCallbackMap.containsKey(str)) {
            String str2 = str + "callback will be replaced by " + aVar.getClass().getName();
        }
        mIntentCallbackMap.put(str, aVar);
    }

    public static void registSwitch(b bVar) {
        smSwitch = bVar;
    }

    public static void startActivityDirect(Context context, String str, Bundle bundle) {
        startActivityDirect(context, str, bundle, 0);
    }

    public static void startActivityForResult(Activity activity, String str, Bundle bundle, int i2) {
        startActivityForResult(activity, str, bundle, i2, 0);
    }

    public static void startActivityDirect(Context context, String str, Bundle bundle, Bundle bundle2) {
        startActivityDirect(context, str, bundle, bundle2, 0);
    }

    public static void startActivityForResult(Activity activity, String str, Bundle bundle, Bundle bundle2, int i2) {
        startActivityForResult(activity, str, bundle, i2, bundle2, 0);
    }

    public static void startActivityDirect(Context context, String str, Bundle bundle, Bundle bundle2, int i2) {
        Intent generateIntentByUri = generateIntentByUri(context, str, bundle, i2);
        if (generateIntentByUri != null) {
            if (!(context instanceof Activity)) {
                generateIntentByUri.addFlags(268435456);
            }
            if (Build.VERSION.SDK_INT < 16) {
                context.startActivity(generateIntentByUri);
            } else {
                context.startActivity(generateIntentByUri, bundle2);
            }
            performNavigationCallback(context, generateIntentByUri);
        }
    }

    public static void startActivityForResult(Activity activity, String str, Bundle bundle, int i2, Bundle bundle2, int i3) {
        Intent generateIntentByUri = generateIntentByUri(activity, str, bundle, i3);
        if (generateIntentByUri != null) {
            if (Build.VERSION.SDK_INT < 16) {
                activity.startActivityForResult(generateIntentByUri, i2);
            } else {
                activity.startActivityForResult(generateIntentByUri, i2, bundle2);
            }
            performNavigationCallback(activity, generateIntentByUri);
        }
    }

    public static void startActivityForResult(Activity activity, String str, Bundle bundle, int i2, int i3) {
        Intent generateIntentByUri = generateIntentByUri(activity, str, bundle, i3);
        if (generateIntentByUri != null) {
            activity.startActivityForResult(generateIntentByUri, i2);
            performNavigationCallback(activity, generateIntentByUri);
        }
    }

    public static void startActivityDirect(Context context, String str, Bundle bundle, int i2) {
        Intent generateIntentByUri = generateIntentByUri(context, str, bundle, i2);
        if (generateIntentByUri != null) {
            if (!(context instanceof Activity)) {
                generateIntentByUri.addFlags(268435456);
            }
            context.startActivity(generateIntentByUri);
            performNavigationCallback(context, generateIntentByUri);
        }
    }

    public static void startActivityForResult(Fragment fragment, String str, Bundle bundle, int i2) {
        startActivityForResult(fragment, str, bundle, i2, (Bundle) null, 0);
    }

    private static void startActivityForResult(Fragment fragment, String str, Bundle bundle, int i2, Bundle bundle2, int i3) {
        Intent generateIntentByUri = generateIntentByUri(fragment.getContext(), str, bundle, i3);
        if (generateIntentByUri != null) {
            if (Build.VERSION.SDK_INT < 16) {
                fragment.startActivityForResult(generateIntentByUri, i2);
            } else {
                fragment.startActivityForResult(generateIntentByUri, i2, bundle2);
            }
            performNavigationCallback(fragment, generateIntentByUri);
        }
    }
}
