package com.jingdong.common.permission;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.global.PasteStateRouterImpl;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.sdk.oklog.OKLog;
import de.greenrobot.event.EventBus;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class ClipBoardPermissionHelper {
    private static final String ClipBoard_PERMISSION_FIRST_KEY = "ClipBoard_PERMISSION_FIRST_";
    private static final int DEFAULT_INTERVAL = 172800000;
    private static final String KEY_CLIPBOARD_PERMISSION = "clipBoardPermissionSwitch";
    private static final String KEY_CLIPBOARD_PERMISSION_INTERVAL = "clipBoardPermissionInterval";
    private static final String KEY_INTERVAL = "interval";
    private static final String KEY_SWITCH = "switch";
    public static final String TAG = "ClipBoardPermissionHelper";
    public static final String VALUE_CLIPBOARD_DISABLE = "0";
    public static final String VALUE_CLIPBOARD_ENABLE = "1";
    private static ConcurrentHashMap<String, CopyOnWriteArrayList<ClipBoardPermissionResultCallBack>> clipBoardPermissionMap;
    private static CopyOnWriteArrayList<ClipBoardPermissionResultCallBack> clipBoardPermissionResultCallBacks;
    private static Handler handler = new Handler(Looper.getMainLooper());
    private static final ConcurrentHashMap<String, Boolean> dialogShowingStatus = new ConcurrentHashMap<>();
    private static boolean isDialogShowing = false;

    /* loaded from: classes5.dex */
    public static abstract class ClipBoardPermissionResultCallBack {
        public void onDenied() {
        }

        public void onGranted() {
        }
    }

    public static void handleClipBoardPermissionDialog(final Context context, final String str, final String str2, final String str3, final ClipBoardPermissionResultCallBack clipBoardPermissionResultCallBack) {
        if (context != null) {
            try {
                if (!TextUtils.isEmpty(str) && handler != null && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
                    handler.post(new Runnable() { // from class: com.jingdong.common.permission.ClipBoardPermissionHelper.1
                        /* JADX WARN: Removed duplicated region for block: B:241:0x0197  */
                        /* JADX WARN: Removed duplicated region for block: B:243:0x01b9  */
                        /* JADX WARN: Removed duplicated region for block: B:246:0x020c  */
                        @Override // java.lang.Runnable
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                        */
                        public void run() {
                            boolean z;
                            boolean z2;
                            boolean optBoolean;
                            long optLong;
                            String config;
                            final String str4 = ClipBoardPermissionHelper.ClipBoard_PERMISSION_FIRST_KEY + str;
                            Boolean bool = (Boolean) ClipBoardPermissionHelper.dialogShowingStatus.get(str4);
                            if (OKLog.D) {
                                OKLog.d(ClipBoardPermissionHelper.TAG, "handleClipBoardPermissionDialog->key=" + str4 + " title=" + str2 + " tipMsg=" + str3 + " dialogShowingStatus=" + ClipBoardPermissionHelper.dialogShowingStatus + " status=" + bool);
                            }
                            if (bool != null && bool.booleanValue()) {
                                if (ClipBoardPermissionHelper.clipBoardPermissionMap == null) {
                                    ConcurrentHashMap unused = ClipBoardPermissionHelper.clipBoardPermissionMap = new ConcurrentHashMap();
                                }
                                if (ClipBoardPermissionHelper.clipBoardPermissionResultCallBacks == null) {
                                    CopyOnWriteArrayList unused2 = ClipBoardPermissionHelper.clipBoardPermissionResultCallBacks = new CopyOnWriteArrayList();
                                }
                                if (clipBoardPermissionResultCallBack != null && ClipBoardPermissionHelper.clipBoardPermissionResultCallBacks != null) {
                                    ClipBoardPermissionHelper.clipBoardPermissionResultCallBacks.add(clipBoardPermissionResultCallBack);
                                }
                                if (ClipBoardPermissionHelper.clipBoardPermissionMap != null) {
                                    ClipBoardPermissionHelper.clipBoardPermissionMap.put(str4, ClipBoardPermissionHelper.clipBoardPermissionResultCallBacks);
                                }
                            }
                            boolean clipPasteStatusValue = PasteStateRouterImpl.getClipPasteStatusValue();
                            if (OKLog.D) {
                                OKLog.d(ClipBoardPermissionHelper.TAG, "handleClipBoardPermissionDialog->myJDClipBoardSetting=" + clipPasteStatusValue);
                                OKLog.d(ClipBoardPermissionHelper.TAG, "handleClipBoardPermissionDialog->clipBoardPermissionMap=" + ClipBoardPermissionHelper.clipBoardPermissionMap);
                            }
                            if (clipPasteStatusValue) {
                                String config2 = JDMobileConfig.getInstance().getConfig(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, ClipBoardPermissionHelper.KEY_CLIPBOARD_PERMISSION, ClipBoardPermissionHelper.KEY_SWITCH, "0");
                                if (OKLog.D) {
                                    OKLog.d(ClipBoardPermissionHelper.TAG, "handleClipBoardPermissionDialog->switchValue=" + config2);
                                }
                                if ("1".equals(config2)) {
                                    String clipBoardValue = PermissionFileUtils.getClipBoardValue(context, str4);
                                    if (OKLog.D) {
                                        OKLog.d(ClipBoardPermissionHelper.TAG, "handleClipBoardPermissionDialog->clipBoardValue=" + clipBoardValue);
                                    }
                                    if (!TextUtils.isEmpty(clipBoardValue)) {
                                        try {
                                            JSONObject jSONObject = new JSONObject(clipBoardValue);
                                            optBoolean = jSONObject.optBoolean("agreeOrNot");
                                            optLong = jSONObject.optLong("lastNotAgreeTime");
                                            config = JDMobileConfig.getInstance().getConfig(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, ClipBoardPermissionHelper.KEY_CLIPBOARD_PERMISSION_INTERVAL, "interval");
                                        } catch (JSONException e2) {
                                            e = e2;
                                            z = true;
                                        }
                                        if (optBoolean) {
                                            try {
                                                ClipBoardPermissionResultCallBack clipBoardPermissionResultCallBack2 = clipBoardPermissionResultCallBack;
                                                if (clipBoardPermissionResultCallBack2 != null) {
                                                    clipBoardPermissionResultCallBack2.onGranted();
                                                }
                                                ClipBoardPermissionHelper.notifySameModuleNameCallBack(str4, true);
                                                EventBus.getDefault().post(new ClipBoardDialogShow(false));
                                                return;
                                            } catch (JSONException e3) {
                                                e = e3;
                                                z = false;
                                                if (OKLog.E) {
                                                    OKLog.e(ClipBoardPermissionHelper.TAG, e);
                                                }
                                                z2 = z;
                                                if (OKLog.D) {
                                                }
                                                if (z2) {
                                                }
                                            }
                                        } else {
                                            if (System.currentTimeMillis() - optLong <= (TextUtils.isEmpty(config) ? ClipBoardPermissionHelper.DEFAULT_INTERVAL : Integer.parseInt(config))) {
                                                z2 = false;
                                                if (OKLog.D) {
                                                    OKLog.d(ClipBoardPermissionHelper.TAG, "handleClipBoardPermissionDialog->isCanShowDialog=" + z2 + " isDialogShowing=" + ClipBoardPermissionHelper.isDialogShowing);
                                                }
                                                if (z2) {
                                                    if (ClipBoardPermissionHelper.isDialogShowing) {
                                                        return;
                                                    }
                                                    final JDDialog createJdDialogWithStyle9 = JDDialogFactory.getInstance().createJdDialogWithStyle9(context, str2, str3, null, "\u4e0d\u540c\u610f", "\u540c\u610f");
                                                    createJdDialogWithStyle9.setCancelable(false);
                                                    createJdDialogWithStyle9.setCanceledOnTouchOutside(false);
                                                    createJdDialogWithStyle9.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.jingdong.common.permission.ClipBoardPermissionHelper.1.1
                                                        {
                                                            AnonymousClass1.this = this;
                                                        }

                                                        @Override // android.content.DialogInterface.OnDismissListener
                                                        public void onDismiss(DialogInterface dialogInterface) {
                                                            boolean unused3 = ClipBoardPermissionHelper.isDialogShowing = false;
                                                            ClipBoardPermissionHelper.dialogShowingStatus.remove(str4);
                                                            if (ClipBoardPermissionHelper.clipBoardPermissionResultCallBacks != null) {
                                                                ClipBoardPermissionHelper.clipBoardPermissionResultCallBacks.clear();
                                                            }
                                                            if (ClipBoardPermissionHelper.clipBoardPermissionMap != null) {
                                                                ClipBoardPermissionHelper.clipBoardPermissionMap.clear();
                                                            }
                                                            EventBus.getDefault().post(new ClipBoardDialogShow(false));
                                                            if (OKLog.D) {
                                                                OKLog.d(ClipBoardPermissionHelper.TAG, "onDismiss->clipBoardPermissionResultCallBacks=" + ClipBoardPermissionHelper.clipBoardPermissionResultCallBacks + " clipBoardPermissionMap=" + ClipBoardPermissionHelper.clipBoardPermissionMap);
                                                            }
                                                        }
                                                    });
                                                    createJdDialogWithStyle9.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.permission.ClipBoardPermissionHelper.1.2
                                                        {
                                                            AnonymousClass1.this = this;
                                                        }

                                                        @Override // android.view.View.OnClickListener
                                                        public void onClick(View view) {
                                                            ClipBoardPermissionHelper.dialogShowingStatus.remove(str4);
                                                            ClipBoardPermissionResultCallBack clipBoardPermissionResultCallBack3 = clipBoardPermissionResultCallBack;
                                                            if (clipBoardPermissionResultCallBack3 != null) {
                                                                clipBoardPermissionResultCallBack3.onDenied();
                                                            }
                                                            ClipBoardPermissionHelper.notifySameModuleNameCallBack(str4, false);
                                                            JDJSONObject jDJSONObject = new JDJSONObject();
                                                            jDJSONObject.put("agreeOrNot", (Object) Boolean.FALSE);
                                                            jDJSONObject.put("lastNotAgreeTime", (Object) Long.valueOf(System.currentTimeMillis()));
                                                            PermissionFileUtils.saveClipBoardValue(context, str4, jDJSONObject.toJSONString());
                                                            createJdDialogWithStyle9.dismiss();
                                                        }
                                                    });
                                                    createJdDialogWithStyle9.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.permission.ClipBoardPermissionHelper.1.3
                                                        {
                                                            AnonymousClass1.this = this;
                                                        }

                                                        @Override // android.view.View.OnClickListener
                                                        public void onClick(View view) {
                                                            ClipBoardPermissionHelper.dialogShowingStatus.remove(str4);
                                                            ClipBoardPermissionResultCallBack clipBoardPermissionResultCallBack3 = clipBoardPermissionResultCallBack;
                                                            if (clipBoardPermissionResultCallBack3 != null) {
                                                                clipBoardPermissionResultCallBack3.onGranted();
                                                            }
                                                            ClipBoardPermissionHelper.notifySameModuleNameCallBack(str4, true);
                                                            JDJSONObject jDJSONObject = new JDJSONObject();
                                                            jDJSONObject.put("agreeOrNot", (Object) Boolean.TRUE);
                                                            PermissionFileUtils.saveClipBoardValue(context, str4, jDJSONObject.toJSONString());
                                                            createJdDialogWithStyle9.dismiss();
                                                        }
                                                    });
                                                    ClipBoardPermissionHelper.dialogShowingStatus.put(str4, Boolean.TRUE);
                                                    boolean unused3 = ClipBoardPermissionHelper.isDialogShowing = true;
                                                    createJdDialogWithStyle9.show();
                                                    EventBus.getDefault().post(new ClipBoardDialogShow(true));
                                                    return;
                                                }
                                                ClipBoardPermissionResultCallBack clipBoardPermissionResultCallBack3 = clipBoardPermissionResultCallBack;
                                                if (clipBoardPermissionResultCallBack3 != null) {
                                                    clipBoardPermissionResultCallBack3.onDenied();
                                                }
                                                EventBus.getDefault().post(new ClipBoardDialogShow(false));
                                                return;
                                            }
                                        }
                                    }
                                    z2 = true;
                                    if (OKLog.D) {
                                    }
                                    if (z2) {
                                    }
                                } else {
                                    ClipBoardPermissionResultCallBack clipBoardPermissionResultCallBack4 = clipBoardPermissionResultCallBack;
                                    if (clipBoardPermissionResultCallBack4 != null) {
                                        clipBoardPermissionResultCallBack4.onGranted();
                                    }
                                    EventBus.getDefault().post(new ClipBoardDialogShow(false));
                                }
                            } else {
                                ClipBoardPermissionResultCallBack clipBoardPermissionResultCallBack5 = clipBoardPermissionResultCallBack;
                                if (clipBoardPermissionResultCallBack5 != null) {
                                    clipBoardPermissionResultCallBack5.onDenied();
                                }
                                EventBus.getDefault().post(new ClipBoardDialogShow(false));
                            }
                        }
                    });
                    return;
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                    return;
                }
                return;
            }
        }
        EventBus.getDefault().post(new ClipBoardDialogShow(false));
    }

    public static void notifySameModuleNameCallBack(String str, boolean z) {
        ConcurrentHashMap<String, CopyOnWriteArrayList<ClipBoardPermissionResultCallBack>> concurrentHashMap = clipBoardPermissionMap;
        if (concurrentHashMap == null || concurrentHashMap.get(str) == null) {
            return;
        }
        CopyOnWriteArrayList<ClipBoardPermissionResultCallBack> copyOnWriteArrayList = clipBoardPermissionMap.get(str);
        if (OKLog.D) {
            OKLog.d(TAG, "notifySameModuleNameCallBack->callBacks=" + copyOnWriteArrayList);
        }
        for (int size = copyOnWriteArrayList.size() - 1; size >= 0; size--) {
            ClipBoardPermissionResultCallBack clipBoardPermissionResultCallBack = copyOnWriteArrayList.get(size);
            if (clipBoardPermissionResultCallBack != null) {
                if (z) {
                    clipBoardPermissionResultCallBack.onGranted();
                } else {
                    clipBoardPermissionResultCallBack.onDenied();
                }
            }
        }
    }
}
