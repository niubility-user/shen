package com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.permission;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.permission.rom.HuaweiUtils;
import com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.permission.rom.MeizuUtils;
import com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.permission.rom.MiuiUtils;
import com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.permission.rom.QikuUtils;
import com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.permission.rom.RomUtils;
import com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.permission.rom.SmartisanUtils;
import com.jingdong.common.jdreactFramework.modules.uphone_sdk.UphoneCallback;

/* loaded from: classes5.dex */
public class FloatPermissionManager {
    private static final String TAG = "FloatPermissionManager";
    private static volatile FloatPermissionManager instance;
    private Dialog dialog;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public interface OnConfirmResult {
        void confirmResult(boolean z);
    }

    private void ROM360PermissionApply(final Context context, final UphoneCallback uphoneCallback) {
        showConfirmDialog(context, new OnConfirmResult() { // from class: com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.permission.FloatPermissionManager.2
            @Override // com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.permission.FloatPermissionManager.OnConfirmResult
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

    private void SmartisanPermissionApply(final Context context, final UphoneCallback uphoneCallback) {
        showConfirmDialog(context, new OnConfirmResult() { // from class: com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.permission.FloatPermissionManager.1
            @Override // com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.permission.FloatPermissionManager.OnConfirmResult
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

    private void applyPermission(Context context, UphoneCallback uphoneCallback) {
        if (Build.VERSION.SDK_INT < 23) {
            if (RomUtils.checkIsMiuiRom()) {
                System.out.println("jdsip applyPermission checkIsMiuiRom");
                miuiROMPermissionApply(context, uphoneCallback);
            } else if (RomUtils.checkIsMeizuRom()) {
                System.out.println("jdsip applyPermission checkIsMeizuRom");
                meizuROMPermissionApply(context, uphoneCallback);
            } else if (RomUtils.checkIsHuaweiRom()) {
                System.out.println("jdsip applyPermission checkIsHuaweiRom");
                huaweiROMPermissionApply(context, uphoneCallback);
            } else if (RomUtils.checkIs360Rom()) {
                System.out.println("jdsip applyPermission checkIs360Rom");
                ROM360PermissionApply(context, uphoneCallback);
            } else if (RomUtils.checkIsSmartisanRom()) {
                SmartisanPermissionApply(context, uphoneCallback);
            }
        }
        System.out.println("jdsip applyPermission commonROMPermissionApply");
        commonROMPermissionApply(context, uphoneCallback);
    }

    private void commonROMPermissionApply(final Context context, final UphoneCallback uphoneCallback) {
        if (Build.VERSION.SDK_INT >= 23) {
            showConfirmDialog(context, RomUtils.checkIsMiuiRom() ? "\u60a8\u7684\u624b\u673a\u6ca1\u6709\u6388\u4e88\u663e\u793a\u60ac\u6d6e\u7a97\u6743\u9650\u548c\u540e\u53f0\u5f39\u51fa\u754c\u9762\u6743\u9650\uff0c\u8bf7\u5f00\u542f\u540e\u518d\u8bd5" : "\u60a8\u7684\u624b\u673a\u6ca1\u6709\u6388\u4e88\u60ac\u6d6e\u7a97\u6743\u9650\uff0c\u8bf7\u5f00\u542f\u540e\u518d\u8bd5", new OnConfirmResult() { // from class: com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.permission.FloatPermissionManager.6
                @Override // com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.permission.FloatPermissionManager.OnConfirmResult
                public void confirmResult(boolean z) {
                    if (z) {
                        try {
                            Intent intent = new Intent(Settings.class.getDeclaredField(com.jingdong.common.permission.FloatPermissionManager.PERSSION_NAME).get(null).toString());
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

    private void huaweiROMPermissionApply(final Context context, final UphoneCallback uphoneCallback) {
        showConfirmDialog(context, new OnConfirmResult() { // from class: com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.permission.FloatPermissionManager.3
            @Override // com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.permission.FloatPermissionManager.OnConfirmResult
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

    private void meizuROMPermissionApply(final Context context, final UphoneCallback uphoneCallback) {
        showConfirmDialog(context, new OnConfirmResult() { // from class: com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.permission.FloatPermissionManager.4
            @Override // com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.permission.FloatPermissionManager.OnConfirmResult
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

    private void miuiROMPermissionApply(final Context context, final UphoneCallback uphoneCallback) {
        showConfirmDialog(context, "\u60a8\u7684\u624b\u673a\u6ca1\u6709\u6388\u4e88\u663e\u793a\u60ac\u6d6e\u7a97\u6743\u9650\u548c\u540e\u53f0\u5f39\u51fa\u754c\u9762\u6743\u9650\uff0c\u8bf7\u5f00\u542f\u540e\u518d\u8bd5", new OnConfirmResult() { // from class: com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.permission.FloatPermissionManager.5
            @Override // com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.permission.FloatPermissionManager.OnConfirmResult
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

    private void showConfirmDialog(Context context, OnConfirmResult onConfirmResult) {
        showConfirmDialog(context, "\u60a8\u7684\u624b\u673a\u6ca1\u6709\u6388\u4e88\u60ac\u6d6e\u7a97\u6743\u9650\uff0c\u8bf7\u5f00\u542f\u540e\u518d\u8bd5", onConfirmResult);
    }

    public boolean applyFloatWindow(Context context, UphoneCallback uphoneCallback) {
        if (checkPermission(context)) {
            System.out.println("jdsip checkPermission");
            return true;
        }
        System.out.println("jdsip applyPermission");
        applyPermission(context, uphoneCallback);
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

    private void showConfirmDialog(Context context, String str, final OnConfirmResult onConfirmResult) {
        Dialog dialog = this.dialog;
        if (dialog != null && dialog.isShowing()) {
            this.dialog.dismiss();
        }
        AlertDialog create = new AlertDialog.Builder(context).setCancelable(true).setTitle("").setMessage(str).setPositiveButton("\u73b0\u5728\u53bb\u5f00\u542f", new DialogInterface.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.permission.FloatPermissionManager.8
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                onConfirmResult.confirmResult(true);
                dialogInterface.dismiss();
            }
        }).setNegativeButton("\u6682\u4e0d\u5f00\u542f", new DialogInterface.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.permission.FloatPermissionManager.7
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                onConfirmResult.confirmResult(false);
                dialogInterface.dismiss();
            }
        }).create();
        this.dialog = create;
        create.show();
    }
}
