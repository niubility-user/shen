package com.jd.lib.productdetail.core.entitys;

import android.net.Uri;
import android.text.TextUtils;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import java.util.ArrayList;
import java.util.Map;
import jpbury.t;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class PDHostLinksEntity {
    private static final String HOST_LINK_CONFIG = "{\"links\":[\"http://m.jd.com/html/quanqiugou/ebayIntro.html\"],\"hosts\":[\"sale.jd.com\",\"mall.jd.com\",\"m.huishou.jd.com\"]}";
    private static final String ISV_HOST = "-isv.isvjcloud.com";
    public static PDHostLinksEntity hostLinksEntity;
    public ArrayList<String> hosts;
    public ArrayList<String> links;

    public PDHostLinksEntity(String str) {
        int length;
        int length2;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObjectProxy jSONObjectProxy = new JSONObjectProxy(new JSONObject(str));
            JSONArrayPoxy jSONArray = jSONObjectProxy.getJSONArray("links");
            if (jSONArray != null && (length2 = jSONArray.length()) > 0) {
                this.links = new ArrayList<>(length2);
                for (int i2 = 0; i2 < length2; i2++) {
                    this.links.add(jSONArray.getString(i2));
                }
            }
            JSONArrayPoxy jSONArray2 = jSONObjectProxy.getJSONArray("hosts");
            if (jSONArray2 == null || (length = jSONArray2.length()) <= 0) {
                return;
            }
            this.hosts = new ArrayList<>(length);
            for (int i3 = 0; i3 < length; i3++) {
                this.hosts.add(jSONArray2.getString(i3));
            }
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(t.f20145j, e2.getMessage());
            }
        }
    }

    public static PDHostLinksEntity getHostLinksEntity() {
        if (hostLinksEntity == null) {
            readHostLinksEntity();
        }
        return hostLinksEntity;
    }

    public static boolean isTrueURL(String str) {
        PDHostLinksEntity hostLinksEntity2 = getHostLinksEntity();
        if (hostLinksEntity2 != null) {
            ArrayList<String> arrayList = hostLinksEntity2.hosts;
            if (arrayList != null && !arrayList.isEmpty()) {
                String host = Uri.parse(str).getHost();
                if (hostLinksEntity2.hosts.contains(host)) {
                    return true;
                }
                if (hostLinksEntity2.hosts.contains(ISV_HOST) && host != null && host.endsWith(ISV_HOST)) {
                    return true;
                }
            }
            ArrayList<String> arrayList2 = hostLinksEntity2.links;
            return (arrayList2 == null || arrayList2.isEmpty() || !hostLinksEntity2.links.contains(str)) ? false : true;
        }
        return false;
    }

    public static void readHostLinksEntity() {
        Map<String, String> configs = JDMobileConfig.getInstance().getConfigs("JDProductdetail", "productDetailLinkHost");
        String obj = configs != null ? configs.toString() : "";
        if (TextUtils.isEmpty(obj)) {
            obj = HOST_LINK_CONFIG;
        }
        try {
            hostLinksEntity = new PDHostLinksEntity(obj);
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(t.f20145j, e2.getMessage());
            }
            hostLinksEntity = null;
        }
    }
}
