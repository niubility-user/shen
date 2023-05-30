package tv.danmaku.ijk.media.ext.config;

import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.framework.json.JDJSON;
import com.jdcloud.media.common.JDTAuthUtil;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.bundle.mobileConfig.JDMoblieConfigListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.JDPlayerConstant;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.ext.config.AvOptionsConfigBean;
import tv.danmaku.ijk.media.ext.config.PlayerConfigKey;
import tv.danmaku.ijk.media.ext.identify.PlayerNetHeaderUtil;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.utils.PlayerStringUtils;

/* loaded from: classes11.dex */
public class PlayerConfigLoader {
    public static AudioConfigBean audioConfigBean = null;
    public static CacheConfigBean cacheConfigBean = null;
    private static AvOptionsConfigBean dynamicConfig = null;
    private static boolean enableAlphaDl = false;
    public static boolean enableAuth = true;
    private static boolean enableQuic;
    private static boolean enableVsr;
    public static boolean loadLibOnce;
    private static volatile PlayerConfigLoader mInstance;
    private static Map<String, Map<String, String>> mPlayerDynamicConfig;
    private static DynamicLibInfoBean mcdnLibInfo;
    public static NetConfigBean netConfigBean;
    public boolean enableShare = true;
    private ConfigLoaderCallback mCallback;
    private final JDMoblieConfigListener mConfigListener;

    /* loaded from: classes11.dex */
    public interface ConfigLoaderCallback {
        void onConfigLoad(boolean z, boolean z2, boolean z3, boolean z4, DynamicLibInfoBean dynamicLibInfoBean);
    }

    private PlayerConfigLoader() {
        JDMoblieConfigListener jDMoblieConfigListener = new JDMoblieConfigListener() { // from class: tv.danmaku.ijk.media.ext.config.PlayerConfigLoader.1
            {
                PlayerConfigLoader.this = this;
            }

            @Override // com.jingdong.app.mall.bundle.mobileConfig.JDMoblieConfigListener
            public void onConfigUpdate() {
                PlayerConfigLoader.this.loadConfig();
                if (PlayerConfigLoader.this.mCallback != null) {
                    PlayerConfigLoader.this.mCallback.onConfigLoad(PlayerConfigLoader.enableAuth, PlayerConfigLoader.enableVsr, PlayerConfigLoader.enableQuic, PlayerConfigLoader.enableAlphaDl, PlayerConfigLoader.mcdnLibInfo);
                }
                JDMobileConfig.getInstance().unregisterListener(PlayerConfigLoader.this.mConfigListener);
            }
        };
        this.mConfigListener = jDMoblieConfigListener;
        loadConfig();
        if (mPlayerDynamicConfig == null) {
            JDMobileConfig.getInstance().registerListener(jDMoblieConfigListener);
        }
    }

    public static PlayerConfigLoader getInstance() {
        if (mInstance == null) {
            synchronized (PlayerConfigLoader.class) {
                if (mInstance == null) {
                    mInstance = new PlayerConfigLoader();
                }
            }
        }
        return mInstance;
    }

    private void loadAlphaConfig() {
        Map<String, String> map;
        Map<String, Map<String, String>> map2 = mPlayerDynamicConfig;
        if (map2 == null || (map = map2.get(PlayerConfigKey.AlphaDlConfigKey.KEY_ALPHA_CONFIG)) == null || map.isEmpty()) {
            return;
        }
        enableAlphaDl = Boolean.parseBoolean(map.get(PlayerConfigKey.AlphaDlConfigKey.KEY_ENABLE_DL));
    }

    private void loadAudioConfig() {
        Map<String, String> map;
        Map<String, Map<String, String>> map2 = mPlayerDynamicConfig;
        if (map2 == null || (map = map2.get(PlayerConfigKey.AudioConfigKey.KEY_AUDIO_CONFIG)) == null || map.isEmpty()) {
            return;
        }
        AudioConfigBean audioConfigBean2 = new AudioConfigBean();
        audioConfigBean = audioConfigBean2;
        audioConfigBean2.setEnableEq(Boolean.parseBoolean(map.get(PlayerConfigKey.AudioConfigKey.KEY_ENABLE_EQ)));
        audioConfigBean.setMinFreq(Integer.parseInt(map.get(PlayerConfigKey.AudioConfigKey.KEY_MIN_FREQ)));
        audioConfigBean.setMaxFreq(Integer.parseInt(map.get(PlayerConfigKey.AudioConfigKey.KEY_MAX_FREQ)));
        audioConfigBean.setEnhanceRatio(Float.parseFloat(map.get(PlayerConfigKey.AudioConfigKey.KEY_ENHANCE_RATIO)));
    }

    private void loadAuthConfig() {
        Map<String, String> map;
        Map<String, Map<String, String>> map2 = mPlayerDynamicConfig;
        if (map2 == null || (map = map2.get(PlayerConfigKey.AuthConfigKey.KEY_AUTH_CONFIG)) == null || map.isEmpty()) {
            return;
        }
        enableAuth = Boolean.parseBoolean(map.get(PlayerConfigKey.AuthConfigKey.KEY_ENABLE));
    }

    private void loadAvOptionConfig() throws JSONException {
        Map<String, String> map;
        Map<String, Map<String, String>> map2 = mPlayerDynamicConfig;
        if (map2 == null || (map = map2.get(PlayerConfigKey.OptionsConfigKey.KEY_OPTIONS_CONFIG)) == null || map.isEmpty()) {
            return;
        }
        String str = map.get(PlayerConfigKey.OptionsConfigKey.KEY_OPTIONS_COMMON_CONFIG);
        String str2 = map.get(PlayerConfigKey.OptionsConfigKey.KEY_OPTIONS_LIVE_CONFIG);
        String str3 = map.get(PlayerConfigKey.OptionsConfigKey.KEY_OPTIONS_VOD_CONFIG);
        String str4 = map.get(PlayerConfigKey.OptionsConfigKey.KEY_OPTIONS_LAS_CONFIG);
        String str5 = map.get(PlayerConfigKey.OptionsConfigKey.KEY_OPTIONS_QUIC_CONFIG);
        if (str == null && str2 == null && str3 == null && str4 == null && str5 == null) {
            return;
        }
        AvOptionsConfigBean avOptionsConfigBean = new AvOptionsConfigBean();
        dynamicConfig = avOptionsConfigBean;
        avOptionsConfigBean.setCommonConfig(parseConfigItems(str));
        dynamicConfig.setLiveConfig(parseConfigItems(str2));
        dynamicConfig.setVodConfig(parseConfigItems(str3));
        dynamicConfig.setLasConfig(parseConfigItems(str4));
        dynamicConfig.setQuicConfig(parseConfigItems(str5));
    }

    private void loadBusinessConfig() {
        Map<String, String> map;
        Map<String, Map<String, String>> map2 = mPlayerDynamicConfig;
        if (map2 == null || (map = map2.get(PlayerConfigKey.BusinessConfigKey.KEY_BUSINESS_CONFIG)) == null || map.isEmpty()) {
            return;
        }
        this.enableShare = Boolean.parseBoolean(map.get(PlayerConfigKey.BusinessConfigKey.KEY_ENABLE_SHARE));
    }

    private void loadCacheConfig() {
        Map<String, String> map;
        Map<String, Map<String, String>> map2 = mPlayerDynamicConfig;
        if (map2 == null || (map = map2.get(PlayerConfigKey.CacheConfigKey.KEY_CACHE_CONFIG)) == null || map.isEmpty()) {
            return;
        }
        cacheConfigBean = new CacheConfigBean();
        if (map.get(PlayerConfigKey.CacheConfigKey.KEY_FORCE_CLOSE) != null) {
            cacheConfigBean.setForeClose(Boolean.parseBoolean(map.get(PlayerConfigKey.CacheConfigKey.KEY_FORCE_CLOSE)));
        }
        String str = map.get(PlayerConfigKey.CacheConfigKey.KEY_IO_THREAD_COUNT);
        if (str != null) {
            JDPlayerConstant.CACHE_IO_THREAD_COUNT = Integer.parseInt(str);
        }
        if (map.get(PlayerConfigKey.CacheConfigKey.KEY_FORCE_CLOSE_PRELOAD) != null) {
            cacheConfigBean.setForceClosePreload(Boolean.parseBoolean(map.get(PlayerConfigKey.CacheConfigKey.KEY_FORCE_CLOSE_PRELOAD)));
        }
        if (map.get(PlayerConfigKey.CacheConfigKey.KEY_MAX_CACHE_SIZE) != null) {
            cacheConfigBean.setMaxCacheSize(Integer.parseInt(r1) * 1024 * 1024);
        }
        if (map.get(PlayerConfigKey.CacheConfigKey.KEY_PRELOAD_SIZE) != null) {
            cacheConfigBean.setPreloadSize(Integer.parseInt(r1));
        }
        if (map.get(PlayerConfigKey.CacheConfigKey.KEY_MAX_CACHE_TIME) != null) {
            cacheConfigBean.setMaxCacheTime(Integer.parseInt(r0) * 24 * 60 * 60 * 1000);
        }
    }

    public void loadConfig() {
        if (JDMobileConfig.getInstance().getAllConfig() == null) {
            return;
        }
        Map<String, Map<String, String>> map = JDMobileConfig.getInstance().getAllConfig().get(PlayerConfigKey.KEY_GLOBAL_CONFIG);
        mPlayerDynamicConfig = map;
        if (map == null) {
            return;
        }
        try {
            loadGlobalConfig();
            loadAvOptionConfig();
            loadCacheConfig();
            loadAuthConfig();
            loadAudioConfig();
            loadVsrConfig();
            loadNetConfig();
            loadUAConfig();
            loadAlphaConfig();
            loadBusinessConfig();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void loadGlobalConfig() {
        Map<String, String> map;
        Map<String, Map<String, String>> map2 = mPlayerDynamicConfig;
        if (map2 == null || (map = map2.get(PlayerConfigKey.PlayerGlobalKey.KEY_GLOBAL_CONFIG)) == null || map.isEmpty()) {
            return;
        }
        loadLibOnce = Boolean.parseBoolean(map.get(PlayerConfigKey.PlayerGlobalKey.KEY_LOAD_LIB_ONCE));
    }

    private void loadNetConfig() throws JSONException {
        Map<String, String> map = mPlayerDynamicConfig.get(PlayerConfigKey.NetConfigKey.KEY_NET_CONFIG);
        if (map == null || map.isEmpty()) {
            return;
        }
        netConfigBean = new NetConfigBean();
        if (map.get(PlayerConfigKey.NetConfigKey.KEY_QUIC_ENABLE) != null) {
            boolean parseBoolean = Boolean.parseBoolean(map.get(PlayerConfigKey.NetConfigKey.KEY_QUIC_ENABLE));
            enableQuic = parseBoolean;
            netConfigBean.setQuicEnable(parseBoolean);
        }
        if (map.get(PlayerConfigKey.NetConfigKey.KEY_HTTPDNS_ENABLE) != null) {
            netConfigBean.setHttpDnsEnable(Boolean.parseBoolean(map.get(PlayerConfigKey.NetConfigKey.KEY_HTTPDNS_ENABLE)));
        }
        if (map.get(PlayerConfigKey.NetConfigKey.KEY_MCDN_INFO) != null) {
            DynamicLibInfoBean dynamicLibInfoBean = (DynamicLibInfoBean) JDJSON.optParseObject(map.get(PlayerConfigKey.NetConfigKey.KEY_MCDN_INFO), DynamicLibInfoBean.class);
            mcdnLibInfo = dynamicLibInfoBean;
            netConfigBean.setMcdnLibInfo(dynamicLibInfoBean);
        }
        if (map.get(PlayerConfigKey.NetConfigKey.KEY_QUIC_BLACKLIST) != null) {
            JSONArray jSONArray = new JSONArray(map.get(PlayerConfigKey.NetConfigKey.KEY_QUIC_BLACKLIST));
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                arrayList.add((String) jSONArray.get(i2));
            }
            netConfigBean.setQuicBlacklist(arrayList);
        }
    }

    private void loadUAConfig() {
        Map<String, String> map = mPlayerDynamicConfig.get(PlayerConfigKey.UAConfigKey.KEY_UA_CONFIG);
        if (map == null || map.isEmpty() || map.get(PlayerConfigKey.UAConfigKey.KEY_UA_SWITCH) == null) {
            return;
        }
        PlayerNetHeaderUtil.uaEnable = Integer.parseInt(map.get(PlayerConfigKey.UAConfigKey.KEY_UA_SWITCH));
    }

    private void loadVsrConfig() {
        Map<String, String> map;
        Map<String, Map<String, String>> map2 = mPlayerDynamicConfig;
        if (map2 == null || (map = map2.get(PlayerConfigKey.VsrConfigKey.KEY_VSR_CONFIG)) == null || map.isEmpty()) {
            return;
        }
        enableVsr = Boolean.parseBoolean(map.get("enable"));
    }

    private List<AvOptionsConfigBean.AvConfigItem> parseConfigItems(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return new ArrayList();
        }
        JSONArray jSONArray = new JSONArray(str);
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            AvOptionsConfigBean.AvConfigItem avConfigItem = new AvOptionsConfigBean.AvConfigItem();
            JSONObject jSONObject = jSONArray.getJSONObject(i2);
            if (jSONObject != null) {
                avConfigItem.setOptCategory(jSONObject.optInt(PlayerConfigKey.OptionsConfigKey.KEY_OPT_CATEGORY));
                JSONArray optJSONArray = jSONObject.optJSONArray("configs");
                if (optJSONArray != null) {
                    ArrayList arrayList2 = new ArrayList();
                    for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                        arrayList2.add(optJSONArray.optString(i3));
                    }
                    avConfigItem.setConfigs(arrayList2);
                }
                arrayList.add(avConfigItem);
            }
        }
        return arrayList;
    }

    private void setOpts(IjkMediaPlayer ijkMediaPlayer, List<AvOptionsConfigBean.AvConfigItem> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            AvOptionsConfigBean.AvConfigItem avConfigItem = list.get(i2);
            if (!avConfigItem.getConfigs().isEmpty()) {
                for (int i3 = 0; i3 < avConfigItem.getConfigs().size(); i3++) {
                    String str = avConfigItem.getConfigs().get(i3);
                    if (str.contains(ContainerUtils.KEY_VALUE_DELIMITER)) {
                        String[] split = str.split(ContainerUtils.KEY_VALUE_DELIMITER);
                        if (split.length > 1) {
                            ijkMediaPlayer.setOption(avConfigItem.getOptCategory(), split[0], split[1]);
                        }
                    }
                }
            }
        }
    }

    public void applyConfig(IjkMediaPlayer ijkMediaPlayer, IPlayerControl.PlayerOptions playerOptions, Uri uri) {
        if (ijkMediaPlayer == null) {
            return;
        }
        if (dynamicConfig == null || cacheConfigBean == null) {
            loadConfig();
        }
        CacheConfigBean cacheConfigBean2 = cacheConfigBean;
        if (cacheConfigBean2 != null && cacheConfigBean2.isForeClose()) {
            playerOptions.setUseCache(false);
        }
        AvOptionsConfigBean avOptionsConfigBean = dynamicConfig;
        if (avOptionsConfigBean != null && avOptionsConfigBean.hasCommonConfig()) {
            setOpts(ijkMediaPlayer, dynamicConfig.getCommonConfig());
        }
        AvOptionsConfigBean avOptionsConfigBean2 = dynamicConfig;
        if (avOptionsConfigBean2 != null && avOptionsConfigBean2.hasLiveConfig() && playerOptions != null && playerOptions.getIsLive()) {
            setOpts(ijkMediaPlayer, dynamicConfig.getLiveConfig());
        }
        AvOptionsConfigBean avOptionsConfigBean3 = dynamicConfig;
        if (avOptionsConfigBean3 != null && avOptionsConfigBean3.hasLasConfig() && playerOptions != null && playerOptions.getIsLive() && !TextUtils.isEmpty(playerOptions.getLasMPD())) {
            setOpts(ijkMediaPlayer, dynamicConfig.getLasConfig());
        }
        AvOptionsConfigBean avOptionsConfigBean4 = dynamicConfig;
        if (avOptionsConfigBean4 != null && avOptionsConfigBean4.hasQuicConfig() && playerOptions != null && PlayerStringUtils.isQuicProtocol(uri)) {
            setOpts(ijkMediaPlayer, dynamicConfig.getQuicConfig());
        }
        AvOptionsConfigBean avOptionsConfigBean5 = dynamicConfig;
        if (avOptionsConfigBean5 == null || !avOptionsConfigBean5.hasVodConfig() || playerOptions == null || playerOptions.getIsLive()) {
            return;
        }
        setOpts(ijkMediaPlayer, dynamicConfig.getVodConfig());
    }

    public boolean enableVrs(IPlayerControl.PlayerOptions playerOptions) {
        if (enableVsr) {
            return (JDTAuthUtil.getInstance() == null || JDTAuthUtil.getInstance().getInitResult()) && playerOptions != null && playerOptions.isCouldMediaCodec() && playerOptions.getIsLive() && Build.VERSION.SDK_INT >= 21;
        }
        return false;
    }

    public void registerListener(ConfigLoaderCallback configLoaderCallback) {
        if (configLoaderCallback == null) {
            return;
        }
        this.mCallback = configLoaderCallback;
        if (mPlayerDynamicConfig != null) {
            configLoaderCallback.onConfigLoad(enableAuth, enableVsr, enableQuic, enableAlphaDl, mcdnLibInfo);
        }
    }

    public void updateOptShareState(IPlayerControl.PlayerOptions playerOptions) {
        if (this.enableShare || playerOptions == null) {
            return;
        }
        playerOptions.setSharePlayer(false);
    }
}
