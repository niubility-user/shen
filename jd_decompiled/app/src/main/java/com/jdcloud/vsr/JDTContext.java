package com.jdcloud.vsr;

import com.jdcloud.vsr.exceptions.CoreException;
import com.jdcloud.vsr.imaging.PixelFormat;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes18.dex */
public class JDTContext extends JDTObject {
    private final HashMap<Long, JDTBitmap> bitmaps;
    private long eventListenerHandle;

    public JDTContext(long j2) {
        super(j2);
        this.eventListenerHandle = attachEventListener(j2);
        this.bitmaps = new HashMap<>();
    }

    private native boolean abortJob(long j2, int i2, int i3);

    private static native long attachEventListener(long j2);

    private native boolean busy(long j2, int i2);

    private native void check(long j2, int i2) throws CoreException;

    private native long copyBitmap(JDTBitmap jDTBitmap, int i2);

    private static native void detachEventListener(long j2, long j3);

    public static long getTotalRAMBytes() {
        return getTotalRam();
    }

    private static native long getTotalRam();

    private native boolean isGPUQueried(long j2);

    private native boolean isGPUReady(long j2);

    private native void limitWorkerCount(long j2, int i2, int i3);

    private native int maxAllowedWorkerCount(long j2, int i2);

    private native float performTask(long j2, int i2, Task task) throws CoreException;

    private native void recycleGPUGarbage(long j2);

    private native void repeatTask(long j2, int i2, Task task, boolean z);

    private native int submitPersistentTask(long j2, int i2, Task task);

    private native int submitTask(long j2, int i2, Task task);

    private native void waitForAllJobs(long j2, int i2);

    private native void waitForJob(long j2, int i2, int i3);

    public boolean abortJob(int i2) {
        return abortJob(this.handle, 0, i2);
    }

    public void check() throws CoreException {
        check(this.handle, 0);
    }

    public JDTBitmap copyBitmap(JDTBitmap jDTBitmap, PixelFormat pixelFormat) {
        return new JDTBitmap(this, copyBitmap(jDTBitmap, pixelFormat.ordinal()));
    }

    @Override // com.jdcloud.vsr.JDTObject
    public synchronized void dispose() {
        synchronized (this.bitmaps) {
            Iterator<JDTBitmap> it = this.bitmaps.values().iterator();
            while (it.hasNext()) {
                it.next().dispose();
            }
        }
        detachEventListener(this.handle, this.eventListenerHandle);
        super.dispose();
    }

    public boolean isGPUQueried() {
        return isGPUQueried(this.handle);
    }

    public boolean isGPUReady() {
        return isGPUReady(this.handle);
    }

    public void limitWorkerCount(int i2) {
        limitWorkerCount(this.handle, 0, i2);
    }

    public float performTask(Task task) throws CoreException {
        return performTask(this.handle, 0, task);
    }

    public void recycleGPUGarbage() {
        recycleGPUGarbage(this.handle);
    }

    public void repeatTask(Task task, boolean z) {
        repeatTask(this.handle, 0, task, z);
    }

    public int submitPersistentTask(Task task) {
        return submitPersistentTask(this.handle, 0, task);
    }

    protected synchronized void unwatchBitmap(JDTBitmap jDTBitmap) {
        synchronized (this.bitmaps) {
            this.bitmaps.remove(Long.valueOf(jDTBitmap.handle));
        }
    }

    public void waitForJob(int i2) {
        waitForJob(this.handle, 0, i2);
    }

    public void watchBitmap(JDTBitmap jDTBitmap) {
        synchronized (this.bitmaps) {
            this.bitmaps.put(Long.valueOf(jDTBitmap.handle), jDTBitmap);
        }
    }

    public boolean abortJob(int i2, int i3) {
        return abortJob(this.handle, i3, i2);
    }

    public void check(int i2) throws CoreException {
        check(this.handle, i2);
    }

    public void limitWorkerCount(int i2, int i3) {
        limitWorkerCount(this.handle, i3, i2);
    }

    public float performTask(Task task, int i2) throws CoreException {
        return performTask(this.handle, i2, task);
    }

    public void repeatTask(Task task, boolean z, int i2) {
        repeatTask(this.handle, i2, task, z);
    }

    public int submitPersistentTask(Task task, int i2) {
        return submitPersistentTask(this.handle, i2, task);
    }

    public void waitForJob(int i2, int i3) {
        waitForJob(this.handle, i3, i2);
    }
}
