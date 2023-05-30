package tv.danmaku.ijk.media.ext.config;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes11.dex */
public class AvOptionsConfigBean implements Serializable {
    private List<AvConfigItem> commonConfig;
    private List<AvConfigItem> lasConfig;
    private List<AvConfigItem> liveConfig;
    private List<AvConfigItem> quicConfig;
    private List<AvConfigItem> vodConfig;

    /* loaded from: classes11.dex */
    public static class AvConfigItem implements Serializable {
        private List<String> configs;
        private int optCategory;

        public List<String> getConfigs() {
            return this.configs;
        }

        public int getOptCategory() {
            return this.optCategory;
        }

        public void setConfigs(List<String> list) {
            this.configs = list;
        }

        public void setOptCategory(int i2) {
            this.optCategory = i2;
        }
    }

    public List<AvConfigItem> getCommonConfig() {
        return this.commonConfig;
    }

    public List<AvConfigItem> getLasConfig() {
        return this.lasConfig;
    }

    public List<AvConfigItem> getLiveConfig() {
        return this.liveConfig;
    }

    public List<AvConfigItem> getQuicConfig() {
        return this.quicConfig;
    }

    public List<AvConfigItem> getVodConfig() {
        return this.vodConfig;
    }

    public boolean hasCommonConfig() {
        List<AvConfigItem> list = this.commonConfig;
        return list != null && list.size() > 0;
    }

    public boolean hasLasConfig() {
        List<AvConfigItem> list = this.lasConfig;
        return list != null && list.size() > 0;
    }

    public boolean hasLiveConfig() {
        List<AvConfigItem> list = this.liveConfig;
        return list != null && list.size() > 0;
    }

    public boolean hasQuicConfig() {
        List<AvConfigItem> list = this.quicConfig;
        return list != null && list.size() > 0;
    }

    public boolean hasVodConfig() {
        List<AvConfigItem> list = this.vodConfig;
        return list != null && list.size() > 0;
    }

    public void setCommonConfig(List<AvConfigItem> list) {
        this.commonConfig = list;
    }

    public void setLasConfig(List<AvConfigItem> list) {
        this.lasConfig = list;
    }

    public void setLiveConfig(List<AvConfigItem> list) {
        this.liveConfig = list;
    }

    public void setQuicConfig(List<AvConfigItem> list) {
        this.quicConfig = list;
    }

    public void setVodConfig(List<AvConfigItem> list) {
        this.vodConfig = list;
    }
}
