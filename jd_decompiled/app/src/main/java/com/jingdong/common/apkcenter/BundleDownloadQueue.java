package com.jingdong.common.apkcenter;

import android.text.TextUtils;
import java.util.Iterator;
import java.util.LinkedList;

/* loaded from: classes5.dex */
public class BundleDownloadQueue {
    public static final int STATUS_EDN = 2;
    public static final int STATUS_ERROR = 3;
    public static final int STATUS_PAUSE = 4;
    public static final int STATUS_START = 1;
    private int cursor = 0;
    private int limit;
    private LinkedList<ApkResult> queue;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BundleDownloadQueue(LinkedList<ApkResult> linkedList) {
        this.queue = linkedList;
        this.limit = linkedList.size();
    }

    ApkResult getApkresultByUpdateId(String str) {
        LinkedList<ApkResult> linkedList;
        if (TextUtils.isEmpty(str) || (linkedList = this.queue) == null) {
            return null;
        }
        Iterator<ApkResult> it = linkedList.iterator();
        while (it.hasNext()) {
            ApkResult next = it.next();
            if (str.equals(next.id)) {
                return next;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getCursor() {
        return this.cursor;
    }

    boolean hasNext() {
        return this.cursor < this.limit;
    }

    void moveToNext() {
        this.cursor++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void moveToPrev() {
        int i2 = this.cursor;
        if (i2 > 0) {
            this.cursor = i2 - 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ApkResult next() {
        int i2 = this.cursor;
        if (i2 >= this.limit) {
            return null;
        }
        this.cursor = i2 + 1;
        return this.queue.get(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void remove(ApkResult apkResult) {
        Iterator<ApkResult> it = this.queue.iterator();
        while (it.hasNext()) {
            ApkResult next = it.next();
            if (apkResult.id.equals(next.id)) {
                this.queue.remove(next);
                int size = this.queue.size();
                this.limit = size;
                int i2 = this.cursor - 1;
                this.cursor = i2;
                if (i2 >= size) {
                    this.cursor = size - 1;
                }
                if (this.cursor < 0) {
                    this.cursor = 0;
                    return;
                }
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void resetCusor() {
        this.cursor = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int size() {
        return this.queue.size();
    }
}
