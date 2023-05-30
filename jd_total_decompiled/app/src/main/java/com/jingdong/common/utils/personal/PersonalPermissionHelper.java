package com.jingdong.common.utils.personal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.huawei.hms.push.AttributionReporter;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.lib.personal.R;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\b\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b4\u00105J\u001b\u0010\u0004\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0007\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\u0006\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0007\u00a2\u0006\u0004\b\u0006\u0010\u0005J'\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\t2\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0007H\u0007\u00a2\u0006\u0004\b\n\u0010\u000bJ'\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\t2\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0007H\u0007\u00a2\u0006\u0004\b\f\u0010\u000bJ#\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0007\u00a2\u0006\u0004\b\u0010\u0010\u0011J)\u0010\u0012\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\tH\u0007\u00a2\u0006\u0004\b\u0012\u0010\u0013JK\u0010\u001b\u001a\u00020\u001a2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\b\u0010\u0018\u001a\u0004\u0018\u00010\u00022\b\u0010\u0019\u001a\u0004\u0018\u00010\u0002H\u0007\u00a2\u0006\u0004\b\u001b\u0010\u001cJ]\u0010\u001d\u001a\u00020\u001a2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\t2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u000e\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\t2\u000e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\tH\u0007\u00a2\u0006\u0004\b\u001d\u0010\u001eJ/\u0010\"\u001a\u0004\u0018\u00010\u00142\b\u0010\u001f\u001a\u0004\u0018\u00010\u00022\b\u0010 \u001a\u0004\u0018\u00010\u00022\b\u0010!\u001a\u0004\u0018\u00010\u0002H\u0007\u00a2\u0006\u0004\b\"\u0010#J7\u0010\"\u001a\u0004\u0018\u00010\u00142\b\u0010\u001f\u001a\u0004\u0018\u00010\u00022\b\u0010 \u001a\u0004\u0018\u00010\u00022\b\u0010!\u001a\u0004\u0018\u00010\u00022\u0006\u0010$\u001a\u00020\u000fH\u0007\u00a2\u0006\u0004\b\"\u0010%J-\u0010&\u001a\u00020\u001a2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0007\u00a2\u0006\u0004\b&\u0010'J7\u0010(\u001a\u00020\u001a2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0007\u00a2\u0006\u0004\b(\u0010)J=\u0010*\u001a\u00020\u001a2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\t2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0007\u00a2\u0006\u0004\b*\u0010+R\u0016\u0010,\u001a\u00020\u00028\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b,\u0010-R\u001d\u00103\u001a\u00020.8B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b/\u00100\u001a\u0004\b1\u00102\u00a8\u00066"}, d2 = {"Lcom/jingdong/common/utils/personal/PersonalPermissionHelper;", "", "", AttributionReporter.SYSTEM_PERMISSION, "getTitleMsg", "(Ljava/lang/String;)Ljava/lang/String;", "getTipMsg", "", "permissions", "", "getTitleMsgList", "([Ljava/lang/String;)Ljava/util/List;", "getTipMsgList", "Landroid/app/Activity;", "activity", "", "hasPermission", "(Landroid/app/Activity;Ljava/lang/String;)Z", "hasPermissions", "(Landroid/app/Activity;Ljava/util/List;)Z", "Landroid/os/Bundle;", "bundle", "Lcom/jingdong/common/permission/PermissionHelper$PermissionResultCallBack;", "callBack", "titleMsg", "tipMsg", "", "requestPermission", "(Landroid/app/Activity;Landroid/os/Bundle;Ljava/lang/String;Lcom/jingdong/common/permission/PermissionHelper$PermissionResultCallBack;Ljava/lang/String;Ljava/lang/String;)V", "requestPermissions", "(Landroid/app/Activity;Landroid/os/Bundle;Ljava/util/List;Lcom/jingdong/common/permission/PermissionHelper$PermissionResultCallBack;Ljava/util/List;Ljava/util/List;)V", "moduleName", "className", "methodName", "generateBundle", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/os/Bundle;", PermissionHelper.PARAM_USER_INITIATIVE, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)Landroid/os/Bundle;", "requestRecordAudio", "(Landroid/app/Activity;Landroid/os/Bundle;Lcom/jingdong/common/permission/PermissionHelper$PermissionResultCallBack;)V", "requestCommonPermission", "(Landroid/app/Activity;Ljava/lang/String;Landroid/os/Bundle;Lcom/jingdong/common/permission/PermissionHelper$PermissionResultCallBack;)V", "requestCommonPermissions", "(Landroid/app/Activity;Ljava/util/List;Landroid/os/Bundle;Lcom/jingdong/common/permission/PermissionHelper$PermissionResultCallBack;)V", "TAG", "Ljava/lang/String;", "Landroid/content/Context;", "context$delegate", "Lkotlin/Lazy;", "getContext", "()Landroid/content/Context;", AnnoConst.Constructor_Context, "<init>", "()V", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class PersonalPermissionHelper {
    public static final PersonalPermissionHelper INSTANCE = new PersonalPermissionHelper();
    private static final String TAG = "PersonalPermissionHelper";

    /* renamed from: context$delegate  reason: from kotlin metadata */
    private static final Lazy context;

    static {
        Lazy lazy;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<Context>() { // from class: com.jingdong.common.utils.personal.PersonalPermissionHelper$context$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Context invoke() {
                JdSdk jdSdk = JdSdk.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(jdSdk, "JdSdk.getInstance()");
                return jdSdk.getApplicationContext();
            }
        });
        context = lazy;
    }

    private PersonalPermissionHelper() {
    }

    @JvmStatic
    @Nullable
    public static final Bundle generateBundle(@Nullable String moduleName, @Nullable String className, @Nullable String methodName) {
        return PermissionHelper.generateBundle(moduleName, className, methodName, true);
    }

    private final Context getContext() {
        return (Context) context.getValue();
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0032, code lost:
        if (r3.equals("android.settings.action.MANAGE_OVERLAY_PERMISSION") != false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x003b, code lost:
        if (r3.equals("android.permission.SYSTEM_ALERT_WINDOW") != false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x003d, code lost:
        r3 = com.jingdong.common.utils.personal.PersonalPermissionHelper.INSTANCE.getContext().getString(com.jingdong.sdk.lib.personal.R.string.permission_MANAGE_OVERLAY_PERMISSION_tipMsg);
     */
    @JvmStatic
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final String getTipMsg(@Nullable String permission) {
        String string;
        if (permission != null) {
            int hashCode = permission.hashCode();
            if (hashCode != -1561629405) {
                if (hashCode != 604372044) {
                    if (hashCode == 1831139720 && permission.equals("android.permission.RECORD_AUDIO")) {
                        string = INSTANCE.getContext().getString(R.string.permission_RECORD_AUDIO_tipMsg);
                    }
                }
                string = null;
            }
            if (string != null) {
                return string;
            }
            return null;
        }
        return null;
    }

    @JvmStatic
    @Nullable
    public static final List<String> getTipMsgList(@Nullable String[] permissions) {
        ArrayList arrayList = null;
        if (permissions != null) {
            if ((!(permissions.length == 0)) == false) {
                permissions = null;
            }
            if (permissions != null) {
                arrayList = new ArrayList();
                for (String str : permissions) {
                    String tipMsg = getTipMsg(str);
                    if (tipMsg == null) {
                        tipMsg = "";
                    }
                    arrayList.add(tipMsg);
                }
            }
        }
        return arrayList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0032, code lost:
        if (r3.equals("android.settings.action.MANAGE_OVERLAY_PERMISSION") != false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x003b, code lost:
        if (r3.equals("android.permission.SYSTEM_ALERT_WINDOW") != false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x003d, code lost:
        r3 = com.jingdong.common.utils.personal.PersonalPermissionHelper.INSTANCE.getContext().getString(com.jingdong.sdk.lib.personal.R.string.permission_MANAGE_OVERLAY_PERMISSION_titleMsg);
     */
    @JvmStatic
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final String getTitleMsg(@Nullable String permission) {
        String string;
        if (permission != null) {
            int hashCode = permission.hashCode();
            if (hashCode != -1561629405) {
                if (hashCode != 604372044) {
                    if (hashCode == 1831139720 && permission.equals("android.permission.RECORD_AUDIO")) {
                        string = INSTANCE.getContext().getString(R.string.permission_RECORD_AUDIO_titleMsg);
                    }
                }
                string = null;
            }
            if (string != null) {
                return string;
            }
            return null;
        }
        return null;
    }

    @JvmStatic
    @Nullable
    public static final List<String> getTitleMsgList(@Nullable String[] permissions) {
        ArrayList arrayList = null;
        if (permissions != null) {
            if ((!(permissions.length == 0)) == false) {
                permissions = null;
            }
            if (permissions != null) {
                arrayList = new ArrayList();
                for (String str : permissions) {
                    String titleMsg = getTitleMsg(str);
                    if (titleMsg == null) {
                        titleMsg = "";
                    }
                    arrayList.add(titleMsg);
                }
            }
        }
        return arrayList;
    }

    @JvmStatic
    public static final boolean hasPermission(@Nullable Activity activity, @Nullable String permission) {
        return PermissionHelper.hasPermission(activity, permission);
    }

    @JvmStatic
    public static final boolean hasPermissions(@Nullable Activity activity, @Nullable List<String> permissions) {
        return PermissionHelper.hasPermission(activity, permissions);
    }

    @JvmStatic
    public static final void requestCommonPermission(@Nullable Activity activity, @Nullable String permission, @Nullable Bundle bundle, @Nullable PermissionHelper.PermissionResultCallBack callBack) {
        requestPermission(activity, bundle, permission, callBack, getTitleMsg(permission), getTipMsg(permission));
    }

    @JvmStatic
    public static final void requestCommonPermissions(@Nullable Activity activity, @Nullable List<String> permissions, @Nullable Bundle bundle, @Nullable PermissionHelper.PermissionResultCallBack callBack) {
        String[] strArr;
        String[] strArr2 = null;
        if (permissions != null) {
            Object[] array = permissions.toArray(new String[0]);
            if (array == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            strArr = (String[]) array;
        } else {
            strArr = null;
        }
        List<String> titleMsgList = getTitleMsgList(strArr);
        if (permissions != null) {
            Object[] array2 = permissions.toArray(new String[0]);
            if (array2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            strArr2 = (String[]) array2;
        }
        requestPermissions(activity, bundle, permissions, callBack, titleMsgList, getTipMsgList(strArr2));
    }

    @JvmStatic
    public static final void requestPermission(@Nullable Activity activity, @Nullable Bundle bundle, @Nullable String permission, @Nullable PermissionHelper.PermissionResultCallBack callBack, @Nullable String titleMsg, @Nullable String tipMsg) {
        try {
            if (!hasPermission(activity, permission)) {
                PermissionHelper.requestPermission(activity, bundle, permission, callBack, titleMsg, tipMsg);
            } else if (callBack != null) {
                callBack.onGranted();
            }
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.d(TAG, "PersonalPermissionHelper requestPermission error" + e2);
            }
        }
    }

    @JvmStatic
    public static final void requestPermissions(@Nullable Activity activity, @Nullable Bundle bundle, @Nullable List<String> permissions, @Nullable PermissionHelper.PermissionResultCallBack callBack, @Nullable List<String> titleMsg, @Nullable List<String> tipMsg) {
        try {
            if (!hasPermissions(activity, permissions)) {
                PermissionHelper.requestPermission(activity, bundle, permissions, callBack, titleMsg, tipMsg);
            } else if (callBack != null) {
                callBack.onGranted();
            }
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.d(TAG, "PersonalPermissionHelper requestPermissions error" + e2);
            }
        }
    }

    @JvmStatic
    public static final void requestRecordAudio(@Nullable Activity activity, @Nullable Bundle bundle, @Nullable PermissionHelper.PermissionResultCallBack callBack) {
        requestPermission(activity, bundle, "android.permission.RECORD_AUDIO", callBack, getTitleMsg("android.permission.RECORD_AUDIO"), getTipMsg("android.permission.RECORD_AUDIO"));
    }

    @JvmStatic
    @Nullable
    public static final Bundle generateBundle(@Nullable String moduleName, @Nullable String className, @Nullable String methodName, boolean isInitiative) {
        return PermissionHelper.generateBundle(moduleName, className, methodName, isInitiative);
    }
}
