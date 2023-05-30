package com.jd.lib.babel.task.view;

/* loaded from: classes14.dex */
public class TaskConfig {
    public boolean isFloat;
    public String mBusinessId;
    public String mComponentId;
    public String mTaskParms;
    public String mcpUid;

    /* loaded from: classes14.dex */
    public static class Builder {
        private String businessId;
        private String componentId;
        private String cpUid;
        private boolean isFloat = true;
        private String taskParms;

        public Builder(String str, String str2) {
            this.businessId = str;
            this.componentId = str2;
        }

        public TaskConfig build() {
            return new TaskConfig(this);
        }

        public Builder isFloat(boolean z) {
            this.isFloat = z;
            return this;
        }

        public Builder setCpUid(String str) {
            this.cpUid = str;
            return this;
        }

        public Builder setTaskParm(String str) {
            this.taskParms = str;
            return this;
        }

        public Builder setTaskParms(String str) {
            this.taskParms = str;
            return this;
        }
    }

    private TaskConfig(Builder builder) {
        this.mComponentId = builder.componentId;
        this.mBusinessId = builder.businessId;
        this.mTaskParms = builder.taskParms;
        this.mcpUid = builder.cpUid;
        this.isFloat = builder.isFloat;
    }
}
