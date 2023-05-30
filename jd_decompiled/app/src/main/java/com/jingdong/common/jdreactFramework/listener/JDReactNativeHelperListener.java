package com.jingdong.common.jdreactFramework.listener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.text.TextUtils;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.common.jdflutter.JDFlutterCall;
import com.jingdong.common.jdflutter.JDFlutterCallResult;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.download.PluginVersion;
import com.jingdong.common.jdreactFramework.download.ReactNativeFileManager;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.common.utils.AdvertUtils;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.smtt.sdk.WebView;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.UUID;

/* loaded from: classes5.dex */
public class JDReactNativeHelperListener implements NativeHelperListener, JDFlutterCall {
    public static final int REQUEST_CODE_PICK_CONTACT = 1001;
    public static final int REQUEST_CODE_PICK_CONTACT2 = 1005;
    private static final String TAG = "JDReactNativeHelperListener";
    private static SimpleDateFormat sDateFormat;

    @SuppressLint({"SimpleDateFormat"})
    public static long dateStrToTimestamp(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        if (sDateFormat == null) {
            sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        try {
            return sDateFormat.parse(str).getTime();
        } catch (Exception e2) {
            JLog.e(TAG, e2.toString());
            return 0L;
        }
    }

    public static String getAPPVersionCode(Context context) {
        String str = "";
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            str = packageInfo.versionName;
            int i2 = packageInfo.versionCode;
            JLog.d(TAG, i2 + LangUtils.SINGLE_SPACE + str);
            return str;
        } catch (PackageManager.NameNotFoundException e2) {
            JLog.e(TAG, e2.toString());
            return str;
        }
    }

    public static String getCurrentJsbundleVersion(Context context, String str) {
        PluginVersion pluginDir = ReactNativeFileManager.getPluginDir(context, str);
        return pluginDir != null ? pluginDir.pluginVersion : "";
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeHelperListener
    public void addScheduleToCalendar(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeHelperListener
    public void addScheduleToCalendar2(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeHelperListener
    public void callPhone(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            JLog.d(TAG, "callPhone ok callback is invoked!!");
            if (hashMap != null && hashMap.containsKey(SignUpTable.TB_COLUMN_PHONE)) {
                Intent intent = new Intent("android.intent.action.DIAL");
                intent.setData(Uri.parse(WebView.SCHEME_TEL + ((String) hashMap.get(SignUpTable.TB_COLUMN_PHONE))));
                intent.addFlags(268435456);
                JDReactHelper.newInstance().getApplicationContext().startActivity(intent);
                if (jDCallback != null) {
                    jDCallback.invoke(new Object[0]);
                }
            }
        } catch (Throwable th) {
            JLog.e(TAG, th.toString());
            if (jDCallback2 != null) {
                jDCallback2.invoke(new Object[0]);
            }
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeHelperListener
    public void closePage(JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            Activity currentMyActivity = AbstractJDReactInitialHelper.getCurrentMyActivity();
            if (currentMyActivity != null) {
                currentMyActivity.finish();
                if (jDCallback != null) {
                    jDCallback.invoke(new Object[0]);
                }
            } else if (jDCallback2 != null) {
                jDCallback2.invoke(new Object[0]);
            }
        } catch (Exception e2) {
            JLog.e(TAG, e2.toString());
            if (jDCallback2 != null) {
                jDCallback2.invoke(new Object[0]);
            }
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeHelperListener
    public void getAdvertParams(JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            WritableMap createMap = Arguments.createMap();
            createMap.putString("se", AdvertUtils.getSe());
            createMap.putString("si", AdvertUtils.getSi());
            createMap.putString(JshopConst.JSHOP_M_PARAM, AdvertUtils.getMParam());
            createMap.putString("m_paramTime", AdvertUtils.getMParamTime());
            createMap.putString("jdvTime", AdvertUtils.getJdvTime());
            jDCallback.invoke(createMap);
        } catch (Exception unused) {
            jDCallback2.invoke(new Object[0]);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeHelperListener
    public void getClientVersion(JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            Activity currentMyActivity = AbstractJDReactInitialHelper.getCurrentMyActivity();
            if (currentMyActivity == null) {
                jDCallback2.invoke(new Object[0]);
            } else {
                jDCallback.invoke(getAPPVersionCode(currentMyActivity));
            }
        } catch (Exception unused) {
            jDCallback2.invoke(new Object[0]);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeHelperListener
    public void getContactByCondition(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        if (jDCallback2 != null) {
            jDCallback2.invoke(new Object[0]);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeHelperListener
    public void getContactName(String str, JDCallback jDCallback, JDCallback jDCallback2) {
        if (jDCallback2 != null) {
            jDCallback2.invoke(new Object[0]);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeHelperListener
    public void getContactsdata(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        if (jDCallback2 != null) {
            jDCallback2.invoke(new Object[0]);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeHelperListener
    public void getCurrentModuleVersion(String str, JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            Activity currentMyActivity = AbstractJDReactInitialHelper.getCurrentMyActivity();
            if (currentMyActivity == null) {
                jDCallback2.invoke(new Object[0]);
            } else {
                jDCallback.invoke(getCurrentJsbundleVersion(currentMyActivity, str));
            }
        } catch (Exception unused) {
            jDCallback2.invoke(new Object[0]);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeHelperListener
    public void getDeviceUUID(JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            String uuid = UUID.randomUUID().toString();
            JLog.d(TAG, "getDeviceUUID result =" + uuid);
            if (jDCallback != null) {
                jDCallback.invoke(uuid);
            }
        } catch (Exception e2) {
            JLog.e(TAG, e2.toString());
            if (jDCallback2 != null) {
                jDCallback2.invoke(new Object[0]);
            }
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeHelperListener
    public void getOSVersion(JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            jDCallback.invoke(Integer.valueOf(Build.VERSION.SDK_INT));
        } catch (Exception unused) {
            jDCallback2.invoke(new Object[0]);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeHelperListener
    public void gpsSettings(JDCallback jDCallback, JDCallback jDCallback2) {
        Intent intent = new Intent();
        try {
            try {
                intent.setAction("android.settings.LOCATION_SOURCE_SETTINGS");
                intent.setFlags(268435456);
                JDReactHelper.newInstance().getApplicationContext().startActivity(intent);
                jDCallback.invoke(new Object[0]);
            } catch (ActivityNotFoundException unused) {
                intent.setAction("android.settings.SETTINGS");
                intent.setFlags(268435456);
                JDReactHelper.newInstance().getApplicationContext().startActivity(intent);
                jDCallback.invoke(new Object[0]);
            } catch (Exception e2) {
                OKLog.e(TAG, e2);
                jDCallback2.invoke(new Object[0]);
            }
        } catch (Exception e3) {
            OKLog.e(TAG, e3);
            jDCallback2.invoke(new Object[0]);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeHelperListener
    public void isGpsOpen(JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            LocationManager locationManager = (LocationManager) JDReactHelper.newInstance().getApplicationContext().getSystemService(ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID);
            jDCallback.invoke(Boolean.valueOf(locationManager.getAllProviders().contains("gps") && locationManager.isProviderEnabled("gps")));
        } catch (Exception unused) {
            jDCallback2.invoke(new Object[0]);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeHelperListener
    public void md5Encode(String str, JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            String md5 = Md5Encrypt.md5(str);
            JLog.d(TAG, "md5Encode result =" + md5);
            if (jDCallback != null) {
                jDCallback.invoke(md5);
            }
        } catch (Exception e2) {
            JLog.e(TAG, e2.toString());
            if (jDCallback2 != null) {
                jDCallback2.invoke(new Object[0]);
            }
        }
    }

    @Override // com.jingdong.common.jdflutter.JDFlutterCall
    public void onMethodCall(String str, HashMap hashMap, final JDFlutterCallResult jDFlutterCallResult, Activity activity) {
        if (str.equals("callPhone")) {
            callPhone(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeHelperListener.1
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeHelperListener.2
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
            return;
        }
        if (str.equals("md5Encode")) {
            md5Encode(hashMap.containsKey("data") ? (String) hashMap.get("data") : "", new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeHelperListener.3
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeHelperListener.4
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("getDeviceUUID")) {
            getDeviceUUID(new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeHelperListener.5
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeHelperListener.6
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("closePage")) {
            closePage(new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeHelperListener.7
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeHelperListener.8
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("pickContact")) {
            pickContact(hashMap.containsKey("moduleName") ? (String) hashMap.get("moduleName") : "", new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeHelperListener.9
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeHelperListener.10
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("getOSVersion")) {
            getOSVersion(new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeHelperListener.11
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeHelperListener.12
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("getClientVersion")) {
            getClientVersion(new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeHelperListener.13
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeHelperListener.14
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("getCurrentModuleVersion")) {
            getCurrentModuleVersion(hashMap.containsKey("plug") ? (String) hashMap.get("plug") : "", new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeHelperListener.15
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeHelperListener.16
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("addScheduleToCalendar")) {
            addScheduleToCalendar(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeHelperListener.17
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeHelperListener.18
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("addScheduleToCalendar2")) {
            addScheduleToCalendar2(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeHelperListener.19
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeHelperListener.20
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("getAdvertParams")) {
            getAdvertParams(new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeHelperListener.21
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeHelperListener.22
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("isGpsOpen")) {
            isGpsOpen(new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeHelperListener.23
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeHelperListener.24
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("gpsSettings")) {
            gpsSettings(new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeHelperListener.25
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeHelperListener.26
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("getContactName")) {
            getContactName(hashMap.containsKey("number") ? (String) hashMap.get("number") : "", new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeHelperListener.27
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeHelperListener.28
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("getContactsdata")) {
            getContactsdata(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeHelperListener.29
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeHelperListener.30
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeHelperListener
    public void pickContact(String str, JDCallback jDCallback, JDCallback jDCallback2) {
        Activity currentMyActivity = AbstractJDReactInitialHelper.getCurrentMyActivity();
        Intent intent = new Intent();
        intent.setAction("android.intent.action.PICK");
        intent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        currentMyActivity.startActivityForResult(intent, 1001);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeHelperListener
    public void pickContact2(JDCallback jDCallback, JDCallback jDCallback2) {
        Activity currentMyActivity = AbstractJDReactInitialHelper.getCurrentMyActivity();
        Intent intent = new Intent();
        intent.setAction("android.intent.action.PICK");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("vnd.android.cursor.dir/phone_v2");
        currentMyActivity.startActivityForResult(intent, 1005);
    }
}
