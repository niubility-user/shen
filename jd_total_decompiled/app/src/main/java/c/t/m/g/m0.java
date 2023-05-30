package c.t.m.g;

import com.jd.dynamic.DYConstants;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.HashMap;

/* loaded from: classes.dex */
public class m0 {
    public static HashMap<String, String> a() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("https", DYConstants.DY_TRUE);
        hashMap.put("up_apps", DYConstants.DY_TRUE);
        hashMap.put("start_daemon", DYConstants.DY_FALSE);
        hashMap.put("up_daemon_delay", "300000");
        hashMap.put("gps_kalman", DYConstants.DY_FALSE);
        hashMap.put("callback_wifis", DYConstants.DY_FALSE);
        hashMap.put("min_wifi_scan_interval", "8000");
        hashMap.put("collect_bles", DYConstants.DY_TRUE);
        hashMap.put("start_event_track", DYConstants.DY_TRUE);
        hashMap.put("f_coll_item", "2");
        hashMap.put("f_coll_up_net", JshopConst.JSHOP_PROMOTIO_W);
        hashMap.put("enable_wifi_native_sort", DYConstants.DY_TRUE);
        hashMap.put("enable_invoke_map", DYConstants.DY_FALSE);
        hashMap.put("deny_secret_info", DYConstants.DY_FALSE);
        return hashMap;
    }
}
