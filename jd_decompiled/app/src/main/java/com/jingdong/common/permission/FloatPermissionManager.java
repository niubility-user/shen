package com.jingdong.common.permission;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.permission.rom.HuaweiUtils;
import com.jingdong.common.permission.rom.MeizuUtils;
import com.jingdong.common.permission.rom.MiuiUtils;
import com.jingdong.common.permission.rom.QikuUtils;
import com.jingdong.common.permission.rom.RomUtils;
import com.jingdong.common.permission.rom.SmartisanUtils;
import com.jingdong.common.ui.JDCheckDialog;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.jdsdk.JdSdk;

/* loaded from: classes5.dex */
public class FloatPermissionManager {
    public static final String PERSSION_NAME = "ACTION_MANAGE_OVERLAY_PERMISSION";
    private static final String TAG = "FloatPermissionManager";
    private static volatile FloatPermissionManager instance;
    private JDDialog dialog;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public interface OnConfirmResult {
        void confirmResult(boolean z);
    }

    private void ROM360PermissionApply(final Context context, String str, String str2, SharedPreferences sharedPreferences, final UphoneCallback uphoneCallback) {
        showConfirmDialog(context, str, str2, sharedPreferences, new OnConfirmResult() { // from class: com.jingdong.common.permission.FloatPermissionManager.2
            @Override // com.jingdong.common.permission.FloatPermissionManager.OnConfirmResult
            public void confirmResult(boolean z) {
                if (z) {
                    QikuUtils.applyPermission(context);
                    uphoneCallback.invoke(true, "successed");
                    return;
                }
                uphoneCallback.invoke(false, JDReactConstant.FAILED);
            }
        });
    }

    private void SmartisanPermissionApply(final Context context, String str, String str2, SharedPreferences sharedPreferences, final UphoneCallback uphoneCallback) {
        showConfirmDialog(context, str, str2, sharedPreferences, new OnConfirmResult() { // from class: com.jingdong.common.permission.FloatPermissionManager.1
            @Override // com.jingdong.common.permission.FloatPermissionManager.OnConfirmResult
            public void confirmResult(boolean z) {
                if (z) {
                    SmartisanUtils.applyPermission(context);
                    uphoneCallback.invoke(true, "successed");
                    return;
                }
                uphoneCallback.invoke(false, JDReactConstant.FAILED);
            }
        });
    }

    private boolean SmartisanPermissionCheck(Context context) {
        return SmartisanUtils.checkFloatWindowPermission(context);
    }

    private void applyPermission(Context context, String str, Bundle bundle, UphoneCallback uphoneCallback) {
        String str2;
        String str3;
        boolean z = true;
        String str4 = "";
        if (bundle != null) {
            String string = bundle.getString("moduleName", "");
            str3 = bundle.getString("className", "");
            String string2 = bundle.getString("methodName", "");
            z = bundle.getBoolean(PermissionHelper.PARAM_USER_INITIATIVE, true);
            str2 = string2;
            str4 = string;
        } else {
            str2 = "";
            str3 = str2;
        }
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str4);
        sb2.append(str3);
        sb2.append(str2);
        sb.append((Object) sb2);
        sb.append(PERSSION_NAME);
        String sb3 = sb.toString();
        SharedPreferences sharedPreferences = JdSdk.getInstance().getApplication().getSharedPreferences(PermissionHelper.TAG, 0);
        boolean z2 = sharedPreferences.getBoolean(sb3, false);
        if (z) {
            showPermissionDialog(context, str, sb3, sharedPreferences, uphoneCallback);
        } else if (!z2) {
            showPermissionDialog(context, str, sb3, sharedPreferences, uphoneCallback);
        } else {
            uphoneCallback.onIgnored();
        }
    }

    private void commonROMPermissionApply(final Context context, String str, String str2, SharedPreferences sharedPreferences, final UphoneCallback uphoneCallback) {
        if (Build.VERSION.SDK_INT >= 23) {
            showConfirmDialog(context, str, str2, sharedPreferences, new OnConfirmResult() { // from class: com.jingdong.common.permission.FloatPermissionManager.6
                @Override // com.jingdong.common.permission.FloatPermissionManager.OnConfirmResult
                public void confirmResult(boolean z) {
                    if (z) {
                        try {
                            Intent intent = new Intent(Settings.class.getDeclaredField(FloatPermissionManager.PERSSION_NAME).get(null).toString());
                            intent.setFlags(268435456);
                            intent.setData(Uri.parse("package:" + context.getPackageName()));
                            context.startActivity(intent);
                            uphoneCallback.invoke(true, "successed");
                            return;
                        } catch (Exception e2) {
                            Log.getStackTraceString(e2);
                            return;
                        }
                    }
                    uphoneCallback.invoke(false, JDReactConstant.FAILED);
                }
            });
        }
    }

    private boolean commonROMPermissionCheck(Context context) {
        Boolean bool = Boolean.TRUE;
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                bool = (Boolean) Settings.class.getDeclaredMethod("canDrawOverlays", Context.class).invoke(null, context);
            } catch (Exception e2) {
                Log.getStackTraceString(e2);
            }
        }
        return bool.booleanValue();
    }

    public static FloatPermissionManager getInstance() {
        if (instance == null) {
            synchronized (FloatPermissionManager.class) {
                if (instance == null) {
                    instance = new FloatPermissionManager();
                }
            }
        }
        return instance;
    }

    private boolean huaweiPermissionCheck(Context context) {
        return HuaweiUtils.checkFloatWindowPermission(context);
    }

    private void huaweiROMPermissionApply(final Context context, String str, String str2, SharedPreferences sharedPreferences, final UphoneCallback uphoneCallback) {
        showConfirmDialog(context, str, str2, sharedPreferences, new OnConfirmResult() { // from class: com.jingdong.common.permission.FloatPermissionManager.3
            @Override // com.jingdong.common.permission.FloatPermissionManager.OnConfirmResult
            public void confirmResult(boolean z) {
                if (z) {
                    HuaweiUtils.applyPermission(context);
                    uphoneCallback.invoke(true, "successed");
                    return;
                }
                uphoneCallback.invoke(false, JDReactConstant.FAILED);
            }
        });
    }

    private boolean meizuPermissionCheck(Context context) {
        return MeizuUtils.checkFloatWindowPermission(context);
    }

    private void meizuROMPermissionApply(final Context context, String str, String str2, SharedPreferences sharedPreferences, final UphoneCallback uphoneCallback) {
        showConfirmDialog(context, str, str2, sharedPreferences, new OnConfirmResult() { // from class: com.jingdong.common.permission.FloatPermissionManager.4
            @Override // com.jingdong.common.permission.FloatPermissionManager.OnConfirmResult
            public void confirmResult(boolean z) {
                if (z) {
                    MeizuUtils.applyPermission(context);
                    uphoneCallback.invoke(true, "successed");
                    return;
                }
                uphoneCallback.invoke(false, JDReactConstant.FAILED);
            }
        });
    }

    private boolean miuiPermissionCheck(Context context) {
        return MiuiUtils.checkFloatWindowPermission(context);
    }

    private void miuiROMPermissionApply(final Context context, String str, String str2, SharedPreferences sharedPreferences, final UphoneCallback uphoneCallback) {
        showConfirmDialog(context, str, str2, sharedPreferences, new OnConfirmResult() { // from class: com.jingdong.common.permission.FloatPermissionManager.5
            @Override // com.jingdong.common.permission.FloatPermissionManager.OnConfirmResult
            public void confirmResult(boolean z) {
                if (z) {
                    System.out.println("jdsip applyPermission miuiROMPermissionApply confirmResult true");
                    MiuiUtils.applyMiuiPermission(context);
                    uphoneCallback.invoke(true, "successed");
                    return;
                }
                System.out.println("jdsip applyPermission miuiROMPermissionApply confirmResult false");
                uphoneCallback.invoke(false, JDReactConstant.FAILED);
            }
        });
    }

    private boolean qikuPermissionCheck(Context context) {
        return QikuUtils.checkFloatWindowPermission(context);
    }

    private void showConfirmDialog(Context context, String str, final String str2, final SharedPreferences sharedPreferences, final OnConfirmResult onConfirmResult) {
        JDDialog jDDialog = this.dialog;
        if (jDDialog != null && jDDialog.isShowing()) {
            this.dialog.dismiss();
        }
        if (TextUtils.isEmpty(str)) {
            str = "\u4eac\u4e1c\u9700\u8981\u7533\u8bf7\u60ac\u6d6e\u7a97\u6743\u9650\uff0c\u4ee5\u4fbf\u60a8\u89c2\u770b\u76f4\u64ad\u3001\u77ed\u89c6\u9891\u65f6\u5b9e\u73b0\u60ac\u6d6e\u7a97\u64ad\u653e";
        }
        JDCheckDialog createJdDialogWithStyle6 = JDDialogFactory.getInstance().createJdDialogWithStyle6(context, "\u8bf7\u5f00\u542f\u91cd\u8981\u6743\u9650", str, "\u4ee5\u540e\u518d\u8bf4", "\u53bb\u5f00\u542f");
        this.dialog = createJdDialogWithStyle6;
        createJdDialogWithStyle6.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.permission.FloatPermissionManager.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                onConfirmResult.confirmResult(false);
                sharedPreferences.edit().putBoolean(str2, true).apply();
                FloatPermissionManager.this.dialog.dismiss();
            }
        });
        this.dialog.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.permission.FloatPermissionManager.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                onConfirmResult.confirmResult(true);
                sharedPreferences.edit().putBoolean(str2, true).apply();
                FloatPermissionManager.this.dialog.dismiss();
            }
        });
        this.dialog.show();
    }

    private void showPermissionDialog(Context context, String str, String str2, SharedPreferences sharedPreferences, UphoneCallback uphoneCallback) {
        if (Build.VERSION.SDK_INT < 23) {
            if (RomUtils.checkIsMiuiRom()) {
                System.out.println("jdsip applyPermission checkIsMiuiRom");
                miuiROMPermissionApply(context, str, str2, sharedPreferences, uphoneCallback);
            } else if (RomUtils.checkIsMeizuRom()) {
                System.out.println("jdsip applyPermission checkIsMeizuRom");
                meizuROMPermissionApply(context, str, str2, sharedPreferences, uphoneCallback);
            } else if (RomUtils.checkIsHuaweiRom()) {
                System.out.println("jdsip applyPermission checkIsHuaweiRom");
                huaweiROMPermissionApply(context, str, str2, sharedPreferences, uphoneCallback);
            } else if (RomUtils.checkIs360Rom()) {
                System.out.println("jdsip applyPermission checkIs360Rom");
                ROM360PermissionApply(context, str, str2, sharedPreferences, uphoneCallback);
            } else if (RomUtils.checkIsSmartisanRom()) {
                SmartisanPermissionApply(context, str, str2, sharedPreferences, uphoneCallback);
            }
        }
        System.out.println("jdsip applyPermission commonROMPermissionApply");
        commonROMPermissionApply(context, str, str2, sharedPreferences, uphoneCallback);
    }

    public boolean applyFloatWindow(Context context, String str, Bundle bundle, UphoneCallback uphoneCallback) {
        if (checkPermission(context)) {
            System.out.println("jdsip checkPermission");
            return true;
        }
        System.out.println("jdsip applyPermission");
        applyPermission(context, str, bundle, uphoneCallback);
        return false;
    }

    public boolean checkPermission(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            if (RomUtils.checkIsMiuiRom()) {
                return miuiPermissionCheck(context);
            }
            if (RomUtils.checkIsMeizuRom()) {
                return meizuPermissionCheck(context);
            }
            if (RomUtils.checkIsHuaweiRom()) {
                return huaweiPermissionCheck(context);
            }
            if (RomUtils.checkIs360Rom()) {
                return qikuPermissionCheck(context);
            }
            if (RomUtils.checkIsSmartisanRom()) {
                return SmartisanPermissionCheck(context);
            }
        }
        return commonROMPermissionCheck(context);
    }
}
