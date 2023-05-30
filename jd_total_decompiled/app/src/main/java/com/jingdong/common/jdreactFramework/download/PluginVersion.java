package com.jingdong.common.jdreactFramework.download;

/* loaded from: classes5.dex */
public class PluginVersion {
    public String commitId;
    public boolean full;
    public boolean isPreset;
    public String pluginCommonDir;
    public String pluginCommonVersion;
    public String pluginDir;
    public String pluginName;
    public String pluginVersion;
    public boolean splitEnable;
    public String testmodeVersion;

    public String toString() {
        return "PluginVersion{pluginName='" + this.pluginName + "', pluginVersion='" + this.pluginVersion + "', pluginDir='" + this.pluginDir + "', pluginCommonDir='" + this.pluginCommonDir + "', pluginCommonVersion='" + this.pluginCommonVersion + "', full=" + this.full + ", splitEnable=" + this.splitEnable + '}';
    }
}
