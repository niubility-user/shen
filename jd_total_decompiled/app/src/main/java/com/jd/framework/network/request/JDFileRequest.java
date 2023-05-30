package com.jd.framework.network.request;

import com.android.volley.VolleyLog;
import com.jd.framework.network.JDResponseListener;
import com.jd.framework.network.file.JDFileGuider;
import com.jd.framework.network.file.JDFileResponseListener;
import com.jd.framework.network.file.JDStopController;
import com.jd.framework.network.request.JDRequest;
import java.io.File;

/* loaded from: classes13.dex */
public class JDFileRequest extends JDRequest<File> implements JDStopController, Comparable<JDRequest<File>> {
    private int attemptsTime;
    private boolean ignoreCharset;
    private boolean ignoreRedirect;
    private boolean isBreakpointTransmission;
    private boolean isExclusiveTask;
    private boolean isTopPriority;
    private JDFileResponseListener<File> mResponseListener;
    private boolean noAttempts;
    private boolean retrieveInputStream;
    private JDFileGuider savePath;
    private int startPosBreakpointTransmission;
    private boolean stopFlag;

    public JDFileRequest(String str) {
        super(str);
        this.isTopPriority = false;
        this.isExclusiveTask = false;
    }

    public int getAttemptsTime() {
        return this.attemptsTime;
    }

    public JDFileGuider getSavePath() {
        return this.savePath;
    }

    public int getStartPosBreakpointTransmission() {
        return this.startPosBreakpointTransmission;
    }

    public boolean isBreakpointTransmission() {
        return this.isBreakpointTransmission;
    }

    public boolean isIgnoreCharset() {
        return this.ignoreCharset;
    }

    public boolean isIgnoreRedirect() {
        return this.ignoreRedirect;
    }

    public boolean isNoAttempts() {
        return this.noAttempts;
    }

    public boolean isRetrieveInputStream() {
        return this.retrieveInputStream;
    }

    @Override // com.jd.framework.network.file.JDStopController
    public boolean isStop() {
        return this.stopFlag;
    }

    public boolean isTopPriority() {
        return this.isTopPriority;
    }

    public void setAttemptsTime(int i2) {
        this.attemptsTime = i2;
    }

    public void setBreakpointTransmission(boolean z) {
        this.isBreakpointTransmission = z;
    }

    public void setIgnoreCharset(boolean z) {
        this.ignoreCharset = z;
    }

    public void setIgnoreRedirect(boolean z) {
        this.ignoreRedirect = z;
    }

    public void setNoAttempts(boolean z) {
        this.noAttempts = z;
    }

    public void setResponseListener(JDFileResponseListener<File> jDFileResponseListener) {
        this.mResponseListener = jDFileResponseListener;
    }

    public void setRetrieveInputStreamFlag(boolean z) {
        this.retrieveInputStream = z;
    }

    public void setSavePath(JDFileGuider jDFileGuider) {
        this.savePath = jDFileGuider;
    }

    public void setStartPosBreakpointTransmission(int i2) {
        this.startPosBreakpointTransmission = i2;
    }

    public void setTopPriority(boolean z) {
        this.isTopPriority = z;
    }

    @Override // com.jd.framework.network.file.JDStopController
    public void stop() {
        this.stopFlag = true;
    }

    @Override // java.lang.Comparable
    public int compareTo(JDRequest<File> jDRequest) {
        JDRequest.Priority priority = getPriority();
        JDRequest.Priority priority2 = jDRequest.getPriority();
        try {
            return priority == priority2 ? this.mSequence - jDRequest.mSequence : priority2.ordinal() - priority.ordinal();
        } catch (Exception e2) {
            if (VolleyLog.DEBUG) {
                e2.printStackTrace();
                return 0;
            }
            return 0;
        }
    }

    @Override // com.jd.framework.network.request.JDRequest
    public JDResponseListener<File> getResponseListener() {
        return this.mResponseListener;
    }

    public JDFileRequest(String str, JDFileResponseListener<File> jDFileResponseListener) {
        super(str);
        this.isTopPriority = false;
        this.isExclusiveTask = false;
        this.mResponseListener = jDFileResponseListener;
    }
}
