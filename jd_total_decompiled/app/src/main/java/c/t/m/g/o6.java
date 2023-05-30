package c.t.m.g;

import android.net.wifi.ScanResult;
import com.unionpay.tsmservice.data.Constant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class o6 {
    public static final List<String> a;

    static {
        ArrayList arrayList = new ArrayList();
        a = arrayList;
        arrayList.add("mobile");
        arrayList.add("16wifi");
        arrayList.add("cmcc");
        arrayList.add("360wifi");
        arrayList.add("androidap");
        arrayList.add("htcphone");
        arrayList.add("xiaomi");
        arrayList.add("lenovo");
        arrayList.add("macbook");
    }

    public static void a(List<ScanResult> list) {
        HashSet hashSet = new HashSet();
        Iterator<ScanResult> it = list.iterator();
        while (it.hasNext()) {
            String str = it.next().BSSID;
            if (str == null || str.equals(Constant.DEFAULT_BALANCE) || str.equals("00-00-00-00-00-00") || str.equals("00:00:00:00:00:00") || str.equals("02:00:00:00:00:00") || hashSet.contains(str)) {
                it.remove();
            } else {
                hashSet.add(str);
            }
        }
    }
}
