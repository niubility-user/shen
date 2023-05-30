package com.jingdong.common.utils;

import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class MyHandlerList {
    private int currentTaskIndex;
    private boolean multiThread;
    private List<MyHandler> taskList = new ArrayList();

    /* loaded from: classes6.dex */
    public interface MyHandler {
        void run();
    }

    public MyHandlerList(boolean z) {
        this.multiThread = z;
    }

    public void add(MyHandler myHandler) {
        this.taskList.add(myHandler);
    }

    public void doLast() {
        int size = this.taskList.size() - 1;
        if (OKLog.D) {
            OKLog.d("TaskList", "doLast() i -->> " + this.currentTaskIndex);
        }
        this.currentTaskIndex = size + 1;
        this.taskList.get(size).run();
        if (this.multiThread) {
            return;
        }
        this.currentTaskIndex = size;
    }

    public void doNext() {
        int i2 = this.currentTaskIndex;
        if (OKLog.D) {
            OKLog.d("TaskList", "doNext() i -->> " + this.currentTaskIndex);
        }
        this.currentTaskIndex++;
        if (i2 < this.taskList.size()) {
            this.taskList.get(i2).run();
            if (this.multiThread) {
                return;
            }
            this.currentTaskIndex = i2;
        }
    }

    public void start() {
        if (this.currentTaskIndex == 0) {
            doNext();
        }
    }
}
