package com.jingdong.common.unification.navigationbar;

/* loaded from: classes6.dex */
public class NavigationFrequencyRuleEntity {
    public String dataVersion;
    public long endTime;
    public String id;
    public String position;
    public long startTime;
    public int state;
    public int statisticsCycle = -1;
    public int sumNum = -1;
    public int bubbleNum = -1;
    public int angleNum = -1;
    public int iconAbnormityNum = -1;
    public int bubbleAbnormityNum = -1;
    public int angleAbnormityNum = -1;

    public String toString() {
        return "NavigationFrequencyRuleEntity{id='" + this.id + "', startTime=" + this.startTime + ", endTime=" + this.endTime + ", dataVersion='" + this.dataVersion + "', position='" + this.position + "', statisticsCycle=" + this.statisticsCycle + ", sumNum=" + this.sumNum + ", bubbleNum=" + this.bubbleNum + ", angleNum=" + this.angleNum + ", iconAbnormityNum=" + this.iconAbnormityNum + ", bubbleAbnormityNum=" + this.bubbleAbnormityNum + ", angleAbnormityNum=" + this.angleAbnormityNum + ", state=" + this.state + '}';
    }
}
