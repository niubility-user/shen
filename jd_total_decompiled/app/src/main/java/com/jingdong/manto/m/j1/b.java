package com.jingdong.manto.m.j1;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.jdsdk.res.StringUtil;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.R;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.l.k;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoUtils;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class b extends AbstractMantoModule {
    static int b;
    private d a;

    /* loaded from: classes15.dex */
    class a extends BaseAdapter {
        final /* synthetic */ Activity a;

        /* renamed from: com.jingdong.manto.m.j1.b$a$a  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        final class C0573a {
            TextView a;

            C0573a(a aVar) {
            }
        }

        a(b bVar, Activity activity) {
            this.a = activity;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return 2;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            Activity activity;
            int i3;
            if (i2 == 0) {
                activity = this.a;
                i3 = R.string.add_contact;
            } else {
                activity = this.a;
                i3 = R.string.edit_contact;
            }
            return activity.getString(i3);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            C0573a c0573a;
            if (view == null) {
                view = View.inflate(viewGroup.getContext(), R.layout.manto_actionsheet_item_layout, null);
                c0573a = new C0573a(this);
                c0573a.a = (TextView) view.findViewById(R.id.title);
                view.setTag(c0573a);
            } else {
                c0573a = (C0573a) view.getTag();
            }
            c0573a.a.setText((CharSequence) getItem(i2));
            return view;
        }
    }

    /* renamed from: com.jingdong.manto.m.j1.b$b  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    class C0574b implements AdapterView.OnItemClickListener {
        final /* synthetic */ com.jingdong.manto.widget.h.a a;
        final /* synthetic */ Activity b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f13389c;

        C0574b(com.jingdong.manto.widget.h.a aVar, Activity activity, String str) {
            this.a = aVar;
            this.b = activity;
            this.f13389c = str;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
            Intent intent;
            this.a.dismiss();
            if (i2 == 0) {
                intent = new Intent("android.intent.action.INSERT", ContactsContract.Contacts.CONTENT_URI);
            } else if (i2 != 1) {
                return;
            } else {
                intent = new Intent("android.intent.action.INSERT_OR_EDIT", ContactsContract.Contacts.CONTENT_URI);
                intent.setType("vnd.android.cursor.item/person");
            }
            b.this.a(intent, this.b, this.f13389c);
            this.b.startActivityForResult(intent, b.b);
        }
    }

    /* loaded from: classes15.dex */
    class c implements DialogInterface.OnCancelListener {
        final /* synthetic */ MantoResultCallBack a;

        c(b bVar, MantoResultCallBack mantoResultCallBack) {
            this.a = mantoResultCallBack;
        }

        @Override // android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialogInterface) {
            Bundle bundle = new Bundle();
            bundle.putString("message", "cancel");
            this.a.onFailed(bundle);
        }
    }

    /* loaded from: classes15.dex */
    public static class d {
        public String a;
        public String b;

        /* renamed from: c  reason: collision with root package name */
        public String f13390c;
        public String d;

        /* renamed from: e  reason: collision with root package name */
        public String f13391e;

        /* renamed from: f  reason: collision with root package name */
        public C0575b f13392f;

        /* renamed from: g  reason: collision with root package name */
        public String f13393g;

        /* renamed from: h  reason: collision with root package name */
        public a f13394h;

        /* renamed from: i  reason: collision with root package name */
        public a f13395i;

        /* renamed from: j  reason: collision with root package name */
        public a f13396j;

        /* renamed from: k  reason: collision with root package name */
        public String f13397k;

        /* renamed from: l  reason: collision with root package name */
        public String f13398l;

        /* renamed from: m  reason: collision with root package name */
        public String f13399m;

        /* renamed from: n  reason: collision with root package name */
        public String f13400n;
        public String o;
        public String p;
        public String q;
        public String r;

        /* loaded from: classes15.dex */
        public static class a {
            private String a;
            private String b;

            /* renamed from: c  reason: collision with root package name */
            private String f13401c;
            public String d;

            /* renamed from: e  reason: collision with root package name */
            private String f13402e;

            public a(String str, String str2, String str3, String str4, String str5) {
                this.a = MantoUtils.getNonNull(str);
                this.f13402e = MantoUtils.getNonNull(str2);
                this.b = MantoUtils.getNonNull(str3);
                this.f13401c = MantoUtils.getNonNull(str4);
                this.d = MantoUtils.getNonNull(str5);
            }

            public final String a() {
                if (MantoUtils.containsChineseCharacter(this.a) || MantoUtils.containsChineseCharacter(this.f13402e) || MantoUtils.containsChineseCharacter(this.b) || MantoUtils.containsChineseCharacter(this.f13401c) || MantoUtils.containsChineseCharacter(this.d)) {
                    StringBuilder sb = new StringBuilder();
                    if (this.a.length() > 0) {
                        sb.append(this.a);
                    }
                    if (this.f13402e.length() > 0) {
                        sb.append(this.f13402e);
                    }
                    if (this.b.length() > 0) {
                        sb.append(this.b);
                    }
                    if (this.f13401c.length() > 0) {
                        sb.append(this.f13401c);
                    }
                    if (this.d.length() > 0) {
                        sb.append(LangUtils.SINGLE_SPACE);
                        sb.append(this.d);
                    }
                    return sb.toString();
                }
                StringBuilder sb2 = new StringBuilder();
                if (this.f13401c.length() > 0) {
                    sb2.append(this.f13401c);
                    sb2.append(LangUtils.SINGLE_SPACE);
                }
                if (this.b.length() > 0) {
                    sb2.append(this.b + LangUtils.SINGLE_SPACE);
                }
                if (this.f13402e.length() > 0) {
                    sb2.append(this.f13402e + LangUtils.SINGLE_SPACE);
                }
                if (this.a.length() > 0) {
                    sb2.append(this.a);
                }
                if (this.d.length() > 0) {
                    sb2.append(LangUtils.SINGLE_SPACE);
                    sb2.append(this.d);
                }
                return sb2.toString();
            }
        }

        /* renamed from: com.jingdong.manto.m.j1.b$d$b  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        public static class C0575b {
            public String a;
            public String b;

            /* renamed from: c  reason: collision with root package name */
            public String f13403c;

            public C0575b(String str, String str2, String str3) {
                this.a = MantoUtils.getNonNull(str);
                this.b = MantoUtils.getNonNull(str2);
                this.f13403c = MantoUtils.getNonNull(str3);
            }
        }
    }

    private void a(ArrayList<ContentValues> arrayList, d.a aVar, int i2) {
        if (aVar == null || aVar.a().length() <= 0) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("mimetype", "vnd.android.cursor.item/postal-address_v2");
        contentValues.put("data1", aVar.a());
        contentValues.put("data9", aVar.d);
        contentValues.put("data2", Integer.valueOf(i2));
        arrayList.add(contentValues);
    }

    private void a(ArrayList<ContentValues> arrayList, String str, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("mimetype", "vnd.android.cursor.item/phone_v2");
        contentValues.put("data1", str);
        contentValues.put("data2", Integer.valueOf(i2));
        arrayList.add(contentValues);
    }

    void a(Intent intent, Activity activity, String str) {
        String str2;
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        d.C0575b c0575b = this.a.f13392f;
        StringBuilder sb = new StringBuilder();
        if (MantoUtils.containsChineseCharacter(c0575b.a) || MantoUtils.containsChineseCharacter(c0575b.b) || MantoUtils.containsChineseCharacter(c0575b.f13403c)) {
            if (c0575b.f13403c.trim().length() > 0) {
                sb.append(c0575b.f13403c);
            }
            if (c0575b.b.trim().length() > 0) {
                sb.append(c0575b.b);
            }
            if (c0575b.a.trim().length() > 0) {
                str2 = c0575b.a;
                sb.append(str2);
            }
        } else {
            if (c0575b.a.trim().length() > 0) {
                sb.append(c0575b.a);
            }
            if (c0575b.b.trim().length() > 0) {
                sb.append(LangUtils.SINGLE_SPACE);
                sb.append(c0575b.b);
            }
            if (c0575b.f13403c.trim().length() > 0) {
                sb.append(LangUtils.SINGLE_SPACE);
                str2 = c0575b.f13403c;
                sb.append(str2);
            }
        }
        String sb2 = sb.toString();
        if (TextUtils.isEmpty(sb2)) {
            MantoLog.e("JsApiAddPhoneContactNew", "addOrEditPhoneContact: no contact user name");
        } else {
            intent.putExtra("name", sb2);
        }
        if (!TextUtils.isEmpty(this.a.a)) {
            String str3 = this.a.a;
            ContentValues contentValues = new ContentValues();
            contentValues.put("mimetype", "vnd.android.cursor.item/nickname");
            contentValues.put("data1", str3);
            contentValues.put("data2", (Integer) 1);
            arrayList.add(contentValues);
        }
        if (!TextUtils.isEmpty(this.a.o)) {
            String str4 = this.a.o;
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put("mimetype", "vnd.android.cursor.item/note");
            contentValues2.put("data1", str4);
            arrayList.add(contentValues2);
        }
        if (!TextUtils.isEmpty(this.a.p) || !TextUtils.isEmpty(this.a.q)) {
            ContentValues contentValues3 = new ContentValues();
            contentValues3.put("mimetype", "vnd.android.cursor.item/organization");
            if (!TextUtils.isEmpty(this.a.p)) {
                contentValues3.put("data1", this.a.p);
            }
            if (!TextUtils.isEmpty(this.a.q)) {
                contentValues3.put("data4", this.a.q);
            }
            contentValues3.put("data2", (Integer) 1);
            arrayList.add(contentValues3);
        }
        if (!TextUtils.isEmpty(this.a.r)) {
            String str5 = this.a.r;
            ContentValues contentValues4 = new ContentValues();
            contentValues4.put("mimetype", "vnd.android.cursor.item/website");
            contentValues4.put("data1", str5);
            contentValues4.put("data2", (Integer) 1);
            arrayList.add(contentValues4);
        }
        if (!TextUtils.isEmpty(this.a.b)) {
            intent.putExtra("email", this.a.b);
        }
        if (!TextUtils.isEmpty(this.a.f13397k)) {
            a((ArrayList<ContentValues>) arrayList, this.a.f13397k, 2);
        }
        if (!TextUtils.isEmpty(this.a.f13398l)) {
            a((ArrayList<ContentValues>) arrayList, this.a.f13398l, 1);
        }
        if (!TextUtils.isEmpty(this.a.f13400n)) {
            a((ArrayList<ContentValues>) arrayList, this.a.f13400n, 3);
        }
        if (!TextUtils.isEmpty(this.a.f13399m)) {
            a((ArrayList<ContentValues>) arrayList, this.a.f13399m, 10);
        }
        if (!TextUtils.isEmpty(this.a.d)) {
            a((ArrayList<ContentValues>) arrayList, this.a.d, 5);
        }
        if (!TextUtils.isEmpty(this.a.f13390c)) {
            a((ArrayList<ContentValues>) arrayList, this.a.f13390c, 4);
        }
        a((ArrayList<ContentValues>) arrayList, this.a.f13396j, 3);
        a((ArrayList<ContentValues>) arrayList, this.a.f13395i, 2);
        a((ArrayList<ContentValues>) arrayList, this.a.f13394h, 1);
        if (!TextUtils.isEmpty(this.a.f13391e)) {
            String str6 = this.a.f13391e;
            ContentValues contentValues5 = new ContentValues();
            contentValues5.put("mimetype", "vnd.android.cursor.item/im");
            contentValues5.put("data1", str6);
            contentValues5.put("data5", (Integer) (-1));
            contentValues5.put("data6", StringUtil.app_name);
            arrayList.add(contentValues5);
        }
        com.jingdong.manto.t.d g2 = com.jingdong.manto.t.c.g(str, this.a.f13393g);
        if (g2 != null && !TextUtils.isEmpty(g2.b)) {
            String str7 = g2.b;
            if (!str7.startsWith("file://")) {
                str7 = "file://" + str7;
            }
            Bitmap a2 = k.d().a(str7, null);
            if (a2 != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                a2.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                ContentValues contentValues6 = new ContentValues();
                contentValues6.put("mimetype", "vnd.android.cursor.item/photo");
                contentValues6.put("data15", byteArray);
                arrayList.add(contentValues6);
                a2.recycle();
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
        intent.putParcelableArrayListExtra("data", arrayList);
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "addPhoneContact";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(bundle.getString("json"));
        } catch (JSONException e2) {
            e2.printStackTrace();
            jSONObject = null;
        }
        Bundle bundle2 = new Bundle();
        if (jSONObject == null) {
            bundle2.putString("message", "data is null");
            mantoResultCallBack.onFailed(bundle2);
            MantoLog.e("JsApiAddPhoneContactNew", "data is null");
        } else if (TextUtils.isEmpty(jSONObject.optString("firstName"))) {
            bundle2.putString("message", "firstName is null");
            mantoResultCallBack.onFailed(bundle2);
            MantoLog.e("JsApiAddPhoneContactNew", "firstName is null");
        } else {
            d dVar = new d();
            this.a = dVar;
            dVar.f13393g = jSONObject.optString("photoFilePath");
            this.a.a = jSONObject.optString("nickName");
            this.a.f13392f = new d.C0575b(jSONObject.optString("firstName"), jSONObject.optString("middleName"), jSONObject.optString("lastName"));
            this.a.o = jSONObject.optString(SignUpTable.TB_COLUMN_REMARK);
            this.a.f13397k = jSONObject.optString("mobilePhoneNumber");
            this.a.f13391e = jSONObject.optString("jdNumber");
            this.a.f13396j = new d.a(jSONObject.optString("addressCountry"), jSONObject.optString("addressState"), jSONObject.optString("addressCity"), jSONObject.optString("addressStreet"), jSONObject.optString("addressPostalCode"));
            this.a.p = jSONObject.optString("organization");
            this.a.q = jSONObject.optString("title");
            this.a.f13390c = jSONObject.optString("workFaxNumber");
            this.a.f13400n = jSONObject.optString("workPhoneNumber");
            this.a.f13399m = jSONObject.optString("hostNumber");
            this.a.b = jSONObject.optString("email");
            this.a.r = jSONObject.optString("url");
            this.a.f13395i = new d.a(jSONObject.optString("workAddressCountry"), jSONObject.optString("workAddressState"), jSONObject.optString("workAddressCity"), jSONObject.optString("workAddressStreet"), jSONObject.optString("workAddressPostalCode"));
            this.a.d = jSONObject.optString("homeFaxNumber");
            this.a.f13398l = jSONObject.optString("homePhoneNumber");
            this.a.f13394h = new d.a(jSONObject.optString("homeAddressCountry"), jSONObject.optString("homeAddressState"), jSONObject.optString("homeAddressCity"), jSONObject.optString("homeAddressStreet"), jSONObject.optString("homeAddressPostalCode"));
            String string = bundle.getString(IMantoBaseModule.APP_UNIQUEID_ID_KEY);
            Activity activity = mantoCore.getActivity();
            com.jingdong.manto.widget.h.a aVar = new com.jingdong.manto.widget.h.a(activity);
            ViewGroup viewGroup = (ViewGroup) View.inflate(activity, R.layout.manto_actionsheet_layout, null);
            aVar.setContentView(viewGroup);
            ListView listView = (ListView) viewGroup.findViewById(R.id.list);
            listView.setAdapter((ListAdapter) new a(this, activity));
            listView.setOnItemClickListener(new C0574b(aVar, activity, string));
            aVar.setCanceledOnTouchOutside(true);
            aVar.setOnCancelListener(new c(this, mantoResultCallBack));
            aVar.show();
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle handleResult(String str, MantoCore mantoCore, Intent intent, int i2, int i3) {
        Bundle bundle = new Bundle();
        bundle.putString(IMantoBaseModule.ERROR_CODE, "1");
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        if ("addPhoneContact".equals(str)) {
            Bundle bundle = new Bundle();
            int hashCode = hashCode() & 65535;
            b = hashCode;
            bundle.putInt("requestCode", hashCode);
            bundle.putString("json", jSONObject.toString());
            return bundle;
        }
        return null;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("addPhoneContact", 2));
    }
}
