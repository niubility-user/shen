package com.jingdong.jdsdk.config;

import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.alibaba.fastjson.parser.Feature;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.TypeToken;
import com.jingdong.common.unification.i18n.UnI18NUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class HostConfig {
    public static final int BETA_HOST = 0;
    public static final int HTTPS_BETA_HOST = 2;
    public static final int INDEX_2 = 2;
    public static final int INDEX_3 = 3;
    public static final int RELEASE_HOST = 1;
    private static HostConfig hostConfig;
    private static int jdPayDevelopType;
    private HostModel serviceTestModel;
    private int developType = 0;
    private int rvcDevelopType = 0;
    private ArrayMap<String, HostModel> hostModelArrayMap = new ArrayMap<>();

    private HostConfig() {
    }

    public static synchronized HostConfig getInstance() {
        HostConfig hostConfig2;
        synchronized (HostConfig.class) {
            if (hostConfig == null) {
                hostConfig = new HostConfig();
            }
            hostConfig2 = hostConfig;
        }
        return hostConfig2;
    }

    public static int getJDPayDevelopType() {
        return jdPayDevelopType;
    }

    public static void setJDPayDevelopType(int i2) {
        jdPayDevelopType = i2;
    }

    public int getDevelopType() {
        return this.developType;
    }

    public String getHost(String str) {
        HostModel hostModel;
        if (!this.hostModelArrayMap.keySet().contains(str) || (hostModel = this.hostModelArrayMap.get(str)) == null) {
            return null;
        }
        if (Configuration.isBeta()) {
            if (Log.D) {
                setServiceTestModel(hostModel);
            }
            if (Configuration.getBooleanProperty(Configuration.TEST_RELEASE_HOST_MODE, Boolean.FALSE).booleanValue()) {
                return hostModel.getReleaseHost();
            }
            List<String> list = hostModel.list;
            if (list == null) {
                return null;
            }
            return list.get(hostModel.selectIndex);
        }
        return hostModel.getReleaseHost();
    }

    public ArrayMap<String, HostModel> getHostModelArrayMap() {
        return this.hostModelArrayMap;
    }

    public int getRvcDevelopType() {
        return this.rvcDevelopType;
    }

    public HostModel getServiceTestModel() {
        return this.serviceTestModel;
    }

    public boolean isUseBeta(String str) {
        HostModel hostModel;
        if (this.hostModelArrayMap.keySet().contains(str) && (hostModel = this.hostModelArrayMap.get(str)) != null && Configuration.isBeta()) {
            return hostModel.isUseBeta();
        }
        return false;
    }

    public void putHost(String str, HostModel hostModel) {
        if (!UnI18NUtils.isMainApp()) {
            hostModel.list.add(2, Configuration.UNIFORM_GLOBAL_HOST_INTRA_BETA);
            hostModel.list.add(3, Configuration.UNIFORM_GLOBAL_HOST_BETA);
        }
        this.hostModelArrayMap.put(str, hostModel);
        hostModel.name = str;
    }

    public void restoreSvaedConfigFromSp() {
        HashMap hashMap;
        String string = SharedPreferencesUtil.getSharedPreferences().getString("host_config_saved", null);
        if (TextUtils.isEmpty(string) || (hashMap = (HashMap) JDJSON.parseObject(string, new TypeToken<HashMap<String, HostModel>>() { // from class: com.jingdong.jdsdk.config.HostConfig.1
            {
                HostConfig.this = this;
            }
        }.getType(), new Feature[0])) == null) {
            return;
        }
        this.hostModelArrayMap.clear();
        this.hostModelArrayMap.putAll(hashMap);
    }

    public void saveHostConfigToSP() {
        String jSONString = JDJSON.toJSONString(this.hostModelArrayMap);
        if (jSONString != null) {
            SharedPreferencesUtil.getSharedPreferences().edit().putString("host_config_saved", jSONString).apply();
        }
    }

    public void setDevelopType(int i2) {
        this.developType = i2;
    }

    public void setRvcDevelopType(int i2) {
        this.rvcDevelopType = i2;
    }

    public void setServiceTestModel(HostModel hostModel) {
        this.serviceTestModel = hostModel;
    }

    /* loaded from: classes.dex */
    public static class HostModel {
        public String betaHost;
        public boolean isUseBeta;
        public List<String> list;
        public String name;
        public String releaseHost;
        public int selectIndex;
        public int srcollX;

        public HostModel() {
            this.selectIndex = 0;
            this.isUseBeta = false;
        }

        public String getBetaHost() {
            return this.betaHost;
        }

        public String getReleaseHost() {
            return this.releaseHost;
        }

        public boolean isUseBeta() {
            return this.isUseBeta;
        }

        public void setUseBetaHost(boolean z) {
            this.isUseBeta = z;
        }

        public HostModel(String str) {
            this.selectIndex = 0;
            this.isUseBeta = false;
            this.name = str;
            this.list = new ArrayList();
        }

        public HostModel(String str, String str2) {
            this.selectIndex = 0;
            this.isUseBeta = false;
            this.betaHost = str;
            this.releaseHost = str2;
            if (Configuration.isBeta() && !Configuration.getBooleanProperty(Configuration.TEST_RELEASE_HOST_MODE, Boolean.FALSE).booleanValue()) {
                this.isUseBeta = true;
            }
            ArrayList arrayList = new ArrayList();
            this.list = arrayList;
            arrayList.add(str);
            this.list.add(str2);
        }

        public HostModel(String str, String str2, String... strArr) {
            this(str, str2);
            this.list.addAll(Arrays.asList(strArr));
        }
    }
}
