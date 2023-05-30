package tv.danmaku.ijk.media.example.widget.media;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import tv.danmaku.ijk.media.JDPlayerConstant;
import tv.danmaku.ijk.media.utils.DebugLog;

@Deprecated
/* loaded from: classes11.dex */
public class LiveDnsManager {
    private final Map<String, String> hostIpMap = new ConcurrentHashMap();
    private boolean isAdvanceDns = true;
    private DnsCache mDnsCache;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class DnsCache {
        private static final String HOST_KEY = "host_name_";
        private static int MAX_CACHE_LEN = 2;
        private static final String PREFERENCES_NAME = "live_host_cache_list";
        private volatile LinkedList<String> hostList;
        private Context mContext;

        /* JADX INFO: Access modifiers changed from: private */
        public void addHostCache(String str) {
            if (TextUtils.isEmpty(str) || this.hostList.contains(str)) {
                return;
            }
            this.hostList.add(str);
            if (this.hostList.size() > MAX_CACHE_LEN) {
                this.hostList.removeFirst();
            }
            if (this.mContext == null) {
                return;
            }
            DebugLog.d(IjkVideoView.TAG, "add cache:" + str);
            SharedPreferences.Editor edit = this.mContext.getSharedPreferences(PREFERENCES_NAME, 0).edit();
            for (int i2 = 0; i2 < this.hostList.size(); i2++) {
                edit.putString(HOST_KEY + i2, this.hostList.get(i2));
            }
            edit.apply();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public List<String> getCacheHostList() {
            Context context = this.mContext;
            if (context == null) {
                return null;
            }
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, 0);
            for (int i2 = 0; i2 < MAX_CACHE_LEN; i2++) {
                String string = sharedPreferences.getString(HOST_KEY + i2, null);
                if (!TextUtils.isEmpty(string)) {
                    this.hostList.add(string);
                }
            }
            return this.hostList;
        }

        private DnsCache(Context context) {
            this.hostList = new LinkedList<>();
            this.mContext = context.getApplicationContext();
        }
    }

    /* loaded from: classes11.dex */
    protected static class HostIpEntity {
        protected String host;
        protected String ip;

        private HostIpEntity(String str, String str2) {
            this.host = str;
            this.ip = str2;
        }
    }

    /* loaded from: classes11.dex */
    private static class InstanceHolder {
        private static LiveDnsManager INSTANCE = new LiveDnsManager();

        private InstanceHolder() {
        }
    }

    private String getHostFromUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return Uri.parse(str).getHost();
    }

    public static LiveDnsManager getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private void loadHostIp(String str) {
        DebugLog.d(IjkVideoView.TAG, "addLiveDomain " + str);
    }

    public void addLiveHost(String str) {
        if (this.isAdvanceDns && !TextUtils.isEmpty(str)) {
            loadHostIp(str);
            DnsCache dnsCache = this.mDnsCache;
            if (dnsCache != null) {
                dnsCache.addHostCache(str);
            }
        }
    }

    public void addLiveUrl(String str) {
        if (this.isAdvanceDns && !TextUtils.isEmpty(str)) {
            DebugLog.d(IjkVideoView.TAG, "addLiveUrl " + str);
            addLiveHost(getHostFromUrl(str));
        }
    }

    public void clearDnsCache() {
        Map<String, String> map = this.hostIpMap;
        if (map == null || map.isEmpty()) {
            return;
        }
        this.hostIpMap.clear();
    }

    public HostIpEntity getHostIpFromUrl(String str) {
        if (this.isAdvanceDns && !TextUtils.isEmpty(str)) {
            if (str.startsWith(JDPlayerConstant.LIVE_RTMP_HEAD) || str.startsWith("http://") || str.startsWith("https://")) {
                if ((!str.startsWith("http://") && !str.startsWith("https://")) || str.endsWith(".flv") || str.endsWith(JDPlayerConstant.LIVE_REPLAY_SUFFIX)) {
                    String hostFromUrl = getHostFromUrl(str);
                    if (TextUtils.isEmpty(hostFromUrl)) {
                        return null;
                    }
                    String str2 = this.hostIpMap.get(hostFromUrl);
                    if (TextUtils.isEmpty(str2)) {
                        addLiveHost(hostFromUrl);
                        return null;
                    }
                    refreshHostIp(hostFromUrl);
                    return new HostIpEntity(hostFromUrl, str2);
                }
                return null;
            }
            return null;
        }
        return null;
    }

    public void refreshHostIp(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        loadHostIp(str);
    }

    public void setIsAdvanceDns(boolean z) {
        this.isAdvanceDns = z;
    }

    public void startDnsAnalysis(Context context) {
        if (this.isAdvanceDns && this.mDnsCache == null && context != null) {
            DnsCache dnsCache = new DnsCache(context);
            this.mDnsCache = dnsCache;
            List cacheHostList = dnsCache.getCacheHostList();
            if (cacheHostList == null || cacheHostList.size() == 0) {
                return;
            }
            for (int i2 = 0; i2 < cacheHostList.size(); i2++) {
                String str = (String) cacheHostList.get(i2);
                DebugLog.d(IjkVideoView.TAG, "has cache:" + str);
                loadHostIp(str);
            }
        }
    }
}
