package com.jingdong.common.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.text.TextUtils;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.ui.DialogController;
import com.jingdong.jdsdk.widget.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.smtt.sdk.WebView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes6.dex */
public class ReadContactsUtil {
    public static final String IS_FIRST_READ_CANTACTS = "isFirstReadContacts";
    public static final int REQUEST_CODE_READ_CONTACTS = 1100;
    private static final String TAG = "ReadContactsUtil";

    private static boolean checkSDKForReadContacts() {
        int i2;
        try {
            i2 = Integer.parseInt(Build.VERSION.SDK);
        } catch (Throwable th) {
            OKLog.e(TAG, th);
            i2 = 5;
        }
        if (OKLog.D) {
            OKLog.d(TAG, " checkSDKForReadContacts ---> sdkNum : " + i2);
        }
        return i2 >= 5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void forwardContacts(IMyActivity iMyActivity) {
        if (OKLog.D) {
            OKLog.d(TAG, " forwardContacts -->> ");
        }
        if (checkSDKForReadContacts()) {
            iMyActivity.startActivityForResultNoException(new Intent("android.intent.action.PICK", ContactsContract.Contacts.CONTENT_URI), 1100);
        } else {
            iMyActivity.startActivityForResultNoException(new Intent("android.intent.action.PICK", Contacts.People.CONTENT_URI), 1100);
        }
    }

    public static String getContactMobile(Activity activity, String str) {
        String str2;
        Cursor cursor;
        Cursor query;
        int i2;
        int i3;
        str2 = "";
        Cursor cursor2 = null;
        try {
            query = activity.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, new String[]{"_id", "display_name"}, null, null, null);
            try {
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
        }
        if (query == null) {
            ToastUtils.shortToast(com.jingdong.jdsdk.res.StringUtil.errorGetContactData);
            if (query != null && !query.isClosed()) {
                query.close();
            }
            return "";
        }
        if (query.getCount() > 0) {
            i3 = query.getColumnIndex("_id");
            i2 = query.getColumnIndex("display_name");
        } else {
            i2 = 0;
            i3 = 0;
        }
        while (query.moveToNext()) {
            String string = query.getString(i3);
            String string2 = query.getString(i2);
            if (OKLog.D) {
                if (string == null) {
                    string = "";
                }
                if (string2 == null) {
                    string2 = "";
                }
                OKLog.i(TAG, string);
                OKLog.i(TAG, string2);
            }
            if (TextUtils.equals(string2, str)) {
                cursor2 = activity.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, "contact_id=" + string, null, null);
                if (cursor2 == null) {
                    if (query != null && !query.isClosed()) {
                        query.close();
                    }
                    if (cursor2 != null && !cursor2.isClosed()) {
                        cursor2.close();
                    }
                    return "";
                }
                int columnIndex = cursor2.getCount() > 0 ? cursor2.getColumnIndex("data1") : 0;
                if (cursor2.moveToNext()) {
                    String string3 = cursor2.getString(columnIndex);
                    try {
                        if (OKLog.D) {
                            str2 = string3 != null ? string3 : "";
                            OKLog.i(TAG, str2);
                            string3 = str2;
                        }
                        if (query != null && !query.isClosed()) {
                            query.close();
                        }
                        if (cursor2 != null && !cursor2.isClosed()) {
                            cursor2.close();
                        }
                        return string3;
                    } catch (Throwable th3) {
                        th = th3;
                        str2 = string3;
                        cursor = cursor2;
                        cursor2 = query;
                        try {
                            if (OKLog.E) {
                                OKLog.e(TAG, th);
                            }
                            ToastUtils.shortToast(com.jingdong.jdsdk.res.StringUtil.errorGetContactData);
                            return str2;
                        } finally {
                            if (cursor2 != null && !cursor2.isClosed()) {
                                cursor2.close();
                            }
                            if (cursor != null && !cursor.isClosed()) {
                                cursor.close();
                            }
                        }
                    }
                }
            }
        }
        if (query != null && !query.isClosed()) {
            query.close();
        }
        if (cursor2 != null && !cursor2.isClosed()) {
            cursor2.close();
        }
        return str2;
    }

    public static final Cursor getContactsCursor(Context context) {
        if (context == null) {
            return null;
        }
        return context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
    }

    public static final String getContactsName(Cursor cursor) {
        return cursor == null ? "" : cursor.getString(cursor.getColumnIndex("display_name"));
    }

    public static final Cursor getContactsNumberCursor(Context context, Cursor cursor) {
        if (cursor == null || context == null) {
            return null;
        }
        String string = cursor.getString(cursor.getColumnIndex("_id"));
        return context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, "contact_id=" + string, null, null);
    }

    public static void handleContacts(Context context, Intent intent, WebView webView) {
        Uri data;
        try {
            if (OKLog.D) {
                OKLog.d(TAG, " handleContacts -->> ");
            }
            if (intent == null || (data = intent.getData()) == null) {
                return;
            }
            Cursor query = context.getContentResolver().query(data, null, null, null, null);
            if (query != null) {
                if (query.moveToFirst()) {
                    if (OKLog.D) {
                        OKLog.d(TAG, " handleContacts -->> moveToFirst ");
                    }
                    if (checkSDKForReadContacts()) {
                        readContactsForNewSDK(context, query, webView);
                    } else {
                        readContactsForOldSDK(context, query, webView);
                    }
                }
                query.close();
                return;
            }
            ToastUtils.shortToast(com.jingdong.jdsdk.res.StringUtil.errorGetContactData);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
            ToastUtils.shortToast(com.jingdong.jdsdk.res.StringUtil.errorGetContactData);
        }
    }

    private static String judgeNumber(String str) {
        if (OKLog.D) {
            OKLog.d(TAG, " judgeNumber -->>1 number " + str);
            OKLog.d(TAG, " judgeNumber -->>1 number  length : " + str.length());
        }
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (str.length() > 11) {
            str = str.substring(str.length() - 11);
        }
        if (OKLog.D) {
            OKLog.d(TAG, " judgeNumber -->>1 number " + str);
        }
        return (!TextUtils.isEmpty(str) && str.length() == 11 && str.startsWith("1")) ? str : "";
    }

    public static void readContacts(IMyActivity iMyActivity) {
        if (OKLog.D) {
            OKLog.d(TAG, " -->> webActivity : " + iMyActivity);
        }
        try {
            if (CommonBase.getBooleanFromPreference(IS_FIRST_READ_CANTACTS, Boolean.TRUE).booleanValue()) {
                showPermissonDialog(iMyActivity);
            } else {
                forwardContacts(iMyActivity);
            }
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
            ToastUtils.shortToast(com.jingdong.jdsdk.res.StringUtil.errorGetContactData);
        }
    }

    @SuppressLint({"NewApi"})
    private static void readContactsForNewSDK(Context context, Cursor cursor, final WebView webView) {
        int i2;
        String string = cursor.getString(cursor.getColumnIndex("has_phone_number"));
        String string2 = cursor.getString(cursor.getColumnIndex("_id"));
        if ("1".equals(string)) {
            Cursor query = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, "contact_id=" + string2, null, null);
            if (query == null) {
                webView.loadUrl("javascript:contactsCallBack('')");
                return;
            }
            HashSet hashSet = new HashSet();
            if (OKLog.D) {
                OKLog.d(TAG, " ---> phoneCursor.getCount() : " + query.getCount());
            }
            while (query.moveToNext()) {
                String string3 = query.getString(query.getColumnIndex("data1"));
                if (!TextUtils.isEmpty(string3)) {
                    if (OKLog.D) {
                        OKLog.d(TAG, " ---> phoneNumber : ||" + string3 + "||");
                    }
                    String replaceAll = string3.trim().replaceAll(LangUtils.SINGLE_SPACE, "");
                    if (OKLog.D) {
                        OKLog.d(TAG, " ---> REPALCE phoneNumber : ||" + replaceAll + "||");
                    }
                    String judgeNumber = judgeNumber(replaceAll);
                    if (!TextUtils.isEmpty(judgeNumber)) {
                        hashSet.add(judgeNumber);
                    }
                }
            }
            int size = hashSet.size();
            final ArrayList arrayList = new ArrayList();
            if (size > 0) {
                Iterator it = hashSet.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next());
                }
                i2 = arrayList.size();
            } else {
                i2 = 0;
            }
            if (i2 > 1) {
                CharSequence[] charSequenceArr = new CharSequence[i2];
                arrayList.toArray(charSequenceArr);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(com.jingdong.jdsdk.res.StringUtil.recharge_get_contacts);
                builder.setSingleChoiceItems(charSequenceArr, 0, new DialogInterface.OnClickListener() { // from class: com.jingdong.common.utils.ReadContactsUtil.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i3) {
                        webView.loadUrl("javascript:contactsCallBack('" + ((String) arrayList.get(i3)) + "')");
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            } else if (i2 > 0) {
                webView.loadUrl("javascript:contactsCallBack('" + ((String) arrayList.get(0)) + "')");
            } else {
                webView.loadUrl("javascript:contactsCallBack('')");
            }
            query.close();
            return;
        }
        if (OKLog.D) {
            OKLog.d("Main", " ---> phone number : no");
        }
        webView.loadUrl("javascript:contactsCallBack('')");
    }

    private static void readContactsForOldSDK(Context context, Cursor cursor, WebView webView) {
        if (OKLog.D) {
            OKLog.d(TAG, " readContactsForOldSDK --->  ");
        }
        String string = cursor.getString(cursor.getColumnIndex("number"));
        if (OKLog.D) {
            OKLog.d(TAG, " readContactsForOldSDK ---> " + string);
        }
        if (TextUtils.isEmpty(string)) {
            webView.loadUrl("javascript:contactsCallBack('')");
            return;
        }
        String judgeNumber = judgeNumber(string.trim().replaceAll(LangUtils.SINGLE_SPACE, ""));
        if (!TextUtils.isEmpty(judgeNumber)) {
            webView.loadUrl("javascript:contactsCallBack('" + judgeNumber + "')");
            return;
        }
        webView.loadUrl("javascript:contactsCallBack('')");
    }

    private static void showPermissonDialog(final IMyActivity iMyActivity) {
        DialogController dialogController = new DialogController() { // from class: com.jingdong.common.utils.ReadContactsUtil.2
            @Override // com.jingdong.common.ui.DialogController, android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                if (i2 == -2) {
                    CommonBase.putBooleanToPreference(ReadContactsUtil.IS_FIRST_READ_CANTACTS, Boolean.TRUE);
                    dialogInterface.dismiss();
                } else if (i2 != -1) {
                } else {
                    CommonBase.putBooleanToPreference(ReadContactsUtil.IS_FIRST_READ_CANTACTS, Boolean.FALSE);
                    ReadContactsUtil.forwardContacts(IMyActivity.this);
                }
            }
        };
        dialogController.setTitle("\u63d0\u793a");
        dialogController.setMessage(com.jingdong.jdsdk.res.StringUtil.recharge_get_contacts_permission);
        dialogController.setPositiveButton("\u786e\u5b9a");
        dialogController.setNegativeButton("\u53d6\u6d88");
        dialogController.init(iMyActivity.getThisActivity());
        dialogController.show();
    }
}
