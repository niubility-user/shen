package com.jd.lib.productdetail.core.entitys.warebusiness;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes15.dex */
public class WareBusinessExpression {
    public List<JDJSONObject> bestPreferences;
    public String computedResult;
    public String des;
    public Minuend minuend;
    public int num;
    public String priceType;
    public boolean selected;
    public ArrayList<Subtrahends> subtrahends;
    public String topDes;
    public String type;

    /* loaded from: classes15.dex */
    public static class Minuend {
        public String amount;
        public String name;
    }

    /* loaded from: classes15.dex */
    public static class Subtrahends {
        public String amount;
        public ArrayList<String> details;
        public String name;
    }

    public boolean isValid() {
        boolean z;
        boolean z2 = (TextUtils.isEmpty(this.computedResult) || TextUtils.isEmpty(this.des)) ? false : true;
        Minuend minuend = this.minuend;
        boolean z3 = (minuend == null || TextUtils.isEmpty(minuend.amount) || TextUtils.isEmpty(this.minuend.name)) ? false : true;
        ArrayList<Subtrahends> arrayList = this.subtrahends;
        if (arrayList == null || arrayList.size() <= 0) {
            z = false;
        } else {
            Iterator<Subtrahends> it = this.subtrahends.iterator();
            loop0: while (true) {
                while (it.hasNext()) {
                    Subtrahends next = it.next();
                    z = (!z || TextUtils.isEmpty(next.name) || TextUtils.isEmpty(next.amount)) ? false : true;
                }
            }
        }
        return z2 && z3 && z;
    }
}
