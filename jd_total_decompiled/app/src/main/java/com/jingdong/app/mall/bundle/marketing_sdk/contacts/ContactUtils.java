package com.jingdong.app.mall.bundle.marketing_sdk.contacts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import com.jingdong.app.mall.bundle.marketing_sdk.contacts.listener.IContactAuthListener;
import com.jingdong.app.mall.bundle.marketing_sdk.contacts.listener.IContactAuthStatusListener;
import com.jingdong.app.mall.bundle.marketing_sdk.contacts.listener.IContactListener;
import com.jingdong.app.mall.bundle.marketing_sdk.contacts.listener.IContactUploadListener;
import com.jingdong.sdk.jdtoast.ToastUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ContactUtils {

    /* loaded from: classes.dex */
    static class a implements IContactListener {
        final /* synthetic */ IContactUploadListener a;
        final /* synthetic */ String b;

        /* renamed from: com.jingdong.app.mall.bundle.marketing_sdk.contacts.ContactUtils$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        class C0255a implements IContactAuthListener {
            final /* synthetic */ JSONArray a;

            C0255a(JSONArray jSONArray) {
                this.a = jSONArray;
            }

            @Override // com.jingdong.app.mall.bundle.marketing_sdk.contacts.listener.IContactAuthListener
            public void updateAuthResult(int i2, String str) {
                if (i2 != 0 && i2 != 8102) {
                    IContactUploadListener iContactUploadListener = a.this.a;
                    if (iContactUploadListener != null) {
                        iContactUploadListener.updateUploadResult(null);
                        return;
                    }
                    return;
                }
                a aVar = a.this;
                ContactUtils.upload(aVar.b, this.a, aVar.a);
            }
        }

        a(IContactUploadListener iContactUploadListener, String str) {
            this.a = iContactUploadListener;
            this.b = str;
        }

        @Override // com.jingdong.app.mall.bundle.marketing_sdk.contacts.listener.IContactListener
        public void updateResult(JSONObject jSONObject) {
            if (jSONObject == null) {
                IContactUploadListener iContactUploadListener = this.a;
                if (iContactUploadListener != null) {
                    iContactUploadListener.updateUploadResult(null);
                    return;
                }
                return;
            }
            try {
                JSONArray optJSONArray = jSONObject.optJSONArray("contacts");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    ContactUtils.phoneBookAuth(this.b, new C0255a(optJSONArray));
                }
                if (this.a != null) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("code", 1004);
                    this.a.updateUploadResult(jSONObject2);
                }
            } catch (Exception unused) {
            }
        }
    }

    public static void checkAuth(String str, IContactAuthStatusListener iContactAuthStatusListener) {
        b.b(str, iContactAuthStatusListener);
    }

    public static void gotoContact(Activity activity) {
        if (activity == null) {
            return;
        }
        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.PICK");
            intent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
            activity.startActivityForResult(intent, 1110);
        } catch (Exception unused) {
            ToastUtils.shortToast(activity, "\u5e94\u7528\u5f00\u5c0f\u5dee\u4e86\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5");
        }
    }

    public static void gotoSetting(Activity activity) {
        com.jingdong.app.mall.bundle.marketing_sdk.b.c(activity);
    }

    public static void gotoSmsPage(Activity activity, String str, String str2) {
        try {
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + str));
            intent.setFlags(268435456);
            if (TextUtils.isEmpty(str2)) {
                str2 = "";
            }
            intent.putExtra("sms_body", str2);
            activity.startActivity(intent);
        } catch (Exception unused) {
        }
    }

    public static void handlePickData(Activity activity, String str, Intent intent, IContactUploadListener iContactUploadListener) {
        c.a(activity, intent, new a(iContactUploadListener, str));
    }

    public static boolean isFirst(Context context) {
        return false;
    }

    public static void phoneBookAuth(String str, IContactAuthListener iContactAuthListener) {
        b.a(str, iContactAuthListener);
    }

    public static void phoneBookAuthCancel(String str, IContactAuthListener iContactAuthListener) {
        b.d(str, iContactAuthListener);
    }

    public static void requestContactPermission(Activity activity, IContactListener iContactListener, boolean z, int i2) {
        if (iContactListener == null) {
            return;
        }
        try {
            iContactListener.updateResult(new JSONObject());
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void upload(String str, JSONArray jSONArray, IContactUploadListener iContactUploadListener) {
        if (jSONArray != null && jSONArray.length() > 0) {
            try {
                JSONArray jSONArray2 = new JSONArray();
                String a2 = com.jingdong.app.mall.bundle.marketing_sdk.b.a(jSONArray.toString());
                if (!TextUtils.isEmpty(a2)) {
                    jSONArray2.put(a2);
                }
                b.c(str, jSONArray2, iContactUploadListener);
            } catch (Exception unused) {
            }
        }
    }
}
