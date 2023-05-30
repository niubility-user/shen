package tv.danmaku.ijk.media.ext.auth.bean;

import android.text.TextUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class AuthInfoBean implements Serializable {
    private List<PermissionData> bizList = new ArrayList();
    private int globalSwitch;
    private long timestamp;

    /* loaded from: classes11.dex */
    public static class PermissionData implements Serializable {
        private String palytypeid;
        private String palytypename;

        public PermissionData() {
        }

        public boolean equals(Object obj) {
            return (obj instanceof PermissionData) && this.palytypeid.equals(((PermissionData) obj).palytypeid) && !TextUtils.isEmpty(this.palytypeid);
        }

        public String getPalytypeid() {
            return this.palytypeid;
        }

        public String getPalytypename() {
            return this.palytypename;
        }

        public void setPalytypeid(String str) {
            this.palytypeid = str;
        }

        public void setPalytypename(String str) {
            this.palytypename = str;
        }

        public PermissionData(String str) {
            this.palytypeid = str;
        }
    }

    public boolean enableAuth() {
        return this.globalSwitch == 1;
    }

    public List<PermissionData> getBizList() {
        return this.bizList;
    }

    public int getGlobalSwitch() {
        return this.globalSwitch;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setBizList(List<PermissionData> list) {
        this.bizList = list;
    }

    public void setGlobalSwitch(int i2) {
        this.globalSwitch = i2;
    }

    public void setTimestamp(long j2) {
        this.timestamp = j2;
    }
}
