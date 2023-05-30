package com.jingdong.discovertask.model;

import com.jingdong.discovertask.model.entity.TaskClickParams;

/* loaded from: classes12.dex */
public class SingletonWholeParams {
    public TaskClickParams mTaskClickParams;

    /* loaded from: classes12.dex */
    private static class SingletonInstance {
        public static SingletonWholeParams singletonState = new SingletonWholeParams();

        private SingletonInstance() {
        }
    }

    public static SingletonWholeParams getNewInstance() {
        return SingletonInstance.singletonState;
    }

    private SingletonWholeParams() {
    }
}
