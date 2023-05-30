package c.t.m.g;

import android.net.wifi.ScanResult;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class z5 {
    public static boolean a(List<ScanResult> list, List<ScanResult> list2) {
        if (list != null && list2 != null) {
            int size = list.size();
            int size2 = list2.size();
            if (size == 0 && size2 == 0) {
                return true;
            }
            if (size != 0 && size2 != 0) {
                if (list.size() > list2.size()) {
                    list2 = list;
                    list = list2;
                }
                int i2 = 0;
                for (ScanResult scanResult : list) {
                    Iterator<ScanResult> it = list2.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        } else if (it.next().BSSID.equals(scanResult.BSSID)) {
                            i2++;
                            break;
                        }
                    }
                }
                int i3 = size2 + size;
                double d = i3;
                Double.isNaN(d);
                r0 = ((double) (i2 * 2)) < d * 0.85d || i2 < 13;
                StringBuilder sb = new StringBuilder("isDiffrent:c=");
                sb.append(size);
                sb.append(",k=");
                sb.append(i2);
                sb.append(",f=");
                sb.append(i3);
                sb.append(",fun_r=0.85,s=");
                sb.append(r0);
            }
        }
        return r0;
    }
}
