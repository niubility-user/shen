package com.jingdong.manto.m.g1;

import android.os.Bundle;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.MantoUtils;
import com.jingdong.manto.widget.k.a;
import com.jingdong.manto.widget.k.c;
import com.jingdong.union.common.config.UnionConstants;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class a extends f {

    /* renamed from: f  reason: collision with root package name */
    private b f13346f;

    /* renamed from: g  reason: collision with root package name */
    private Date f13347g;

    /* renamed from: h  reason: collision with root package name */
    private Date f13348h;

    /* renamed from: i  reason: collision with root package name */
    private Date f13349i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.manto.m.g1.a$a  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    public class RunnableC0560a implements Runnable {
        final /* synthetic */ MantoCore a;
        final /* synthetic */ JSONObject b;

        /* renamed from: com.jingdong.manto.m.g1.a$a$a  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class C0561a implements a.InterfaceC0701a<String> {
            C0561a() {
            }

            @Override // com.jingdong.manto.widget.k.a.InterfaceC0701a
            public void a(String str) {
                Bundle bundle;
                a aVar;
                String str2;
                if (MantoStringUtils.isEmpty(str)) {
                    aVar = a.this;
                    bundle = null;
                    str2 = "fail";
                } else {
                    bundle = new Bundle();
                    bundle.putString("value", str);
                    aVar = a.this;
                    str2 = IMantoBaseModule.SUCCESS;
                }
                aVar.a(str2, bundle);
            }

            @Override // com.jingdong.manto.widget.k.a.InterfaceC0701a
            public void onCancel() {
                a.this.a("cancel", (Bundle) null);
            }
        }

        RunnableC0560a(MantoCore mantoCore, JSONObject jSONObject) {
            this.a = mantoCore;
            this.b = jSONObject;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.manto.widget.k.f a = a.this.a(this.a);
            if (a == null) {
                return;
            }
            a.setHeaderText(this.b.optString("headerText"));
            c.EnumC0703c enumC0703c = a.this.f13346f == b.b ? c.EnumC0703c.YEAR : a.this.f13346f == b.f13351c ? c.EnumC0703c.MONTH : c.EnumC0703c.DAY;
            com.jingdong.manto.widget.k.c cVar = (com.jingdong.manto.widget.k.c) a.this.a(com.jingdong.manto.widget.k.c.class);
            if (cVar == null) {
                cVar = new com.jingdong.manto.widget.k.c(a.getContext());
            }
            cVar.a(enumC0703c);
            cVar.a(a.this.f13347g, a.this.f13348h);
            cVar.a(a.this.f13349i);
            cVar.a(new C0561a());
            a.a(cVar);
        }
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum b uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:444)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:391)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:320)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:258)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* loaded from: classes15.dex */
    public static final class b {
        public static final b b;

        /* renamed from: c  reason: collision with root package name */
        public static final b f13351c;
        public static final b d;

        /* renamed from: e  reason: collision with root package name */
        private static final /* synthetic */ b[] f13352e;
        final DateFormat a;

        static {
            Locale locale = Locale.US;
            b bVar = new b("YEAR", 0, new SimpleDateFormat("yyyy", locale));
            b = bVar;
            b bVar2 = new b("MONTH", 1, new SimpleDateFormat("yyyy-MM", locale));
            f13351c = bVar2;
            b bVar3 = new b("DAY", 2, new SimpleDateFormat("yyyy-MM-dd", locale));
            d = bVar3;
            f13352e = new b[]{bVar, bVar2, bVar3};
        }

        private b(String str, int i2, DateFormat dateFormat) {
            this.a = dateFormat;
        }

        static b b(String str) {
            if (MantoStringUtils.isEmpty(str)) {
                return f13351c;
            }
            String lowerCase = str.toLowerCase();
            lowerCase.hashCode();
            lowerCase.hashCode();
            char c2 = '\uffff';
            switch (lowerCase.hashCode()) {
                case 99228:
                    if (lowerCase.equals("day")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case 3704893:
                    if (lowerCase.equals(CartConstant.KEY_CART_YEAR)) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 104080000:
                    if (lowerCase.equals("month")) {
                        c2 = 2;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    return d;
                case 1:
                    return b;
                case 2:
                    return f13351c;
                default:
                    return f13351c;
            }
        }

        public static b valueOf(String str) {
            return (b) Enum.valueOf(b.class, str);
        }

        public static b[] values() {
            return (b[]) f13352e.clone();
        }

        public Date a(String str) {
            try {
                return this.a.parse(str);
            } catch (ParseException e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }

    @Override // com.jingdong.manto.m.g1.f
    void a(Bundle bundle, MantoCore mantoCore) {
        try {
            JSONObject jSONObject = new JSONObject(bundle.getString("params"));
            this.f13346f = b.b(jSONObject.optString("fields"));
            JSONObject optJSONObject = jSONObject.optJSONObject("range");
            if (optJSONObject != null) {
                this.f13347g = this.f13346f.a(optJSONObject.optString("start", ""));
                this.f13348h = this.f13346f.a(optJSONObject.optString("end", ""));
            }
            if (this.f13347g == null) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(1900, 0, 1);
                this.f13347g = calendar.getTime();
            }
            if (this.f13348h == null) {
                Calendar calendar2 = Calendar.getInstance();
                calendar2.set(2100, 11, 31);
                this.f13348h = calendar2.getTime();
            }
            Date a = this.f13346f.a(jSONObject.optString(UnionConstants.BUNDLE_CURRENT, ""));
            this.f13349i = a;
            if (a == null) {
                this.f13349i = new Date(System.currentTimeMillis());
            }
            MantoUtils.runOnUiThread(new RunnableC0560a(mantoCore, jSONObject));
        } catch (Exception unused) {
            a("fail", (Bundle) null);
        }
    }
}
