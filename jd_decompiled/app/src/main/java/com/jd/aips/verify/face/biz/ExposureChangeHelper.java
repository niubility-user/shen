package com.jd.aips.verify.face.biz;

import android.content.Context;
import android.hardware.Camera;
import android.os.CountDownTimer;
import androidx.annotation.NonNull;
import com.jd.aips.detect.face.camera.FsCameraProxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes12.dex */
public class ExposureChangeHelper {
    private CountDownTimer countDownTimer;
    private List<Integer> exposureCompensationValues;
    private final int exposureTime;
    private final int exposureTimeInterval;
    private volatile boolean cancelled = false;
    private final List<Float> exposureRule = new ArrayList();

    /* loaded from: classes12.dex */
    public interface ExposureChangeCallback {
        void onCompleted();
    }

    public ExposureChangeHelper(Context context, int i2, int i3, List<String> list) {
        this.exposureTime = i2;
        this.exposureTimeInterval = i3;
        try {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                this.exposureRule.add(Float.valueOf(Float.parseFloat(it.next())));
            }
        } catch (Exception unused) {
        }
        this.exposureCompensationValues = new ArrayList(list.size());
    }

    private CountDownTimer createCountDownTimer(@NonNull final FsCameraProxy fsCameraProxy, @NonNull final ExposureChangeCallback exposureChangeCallback) {
        try {
            Camera.Parameters parameters = fsCameraProxy.getCamera().getParameters();
            final int maxExposureCompensation = parameters.getMaxExposureCompensation();
            final int minExposureCompensation = parameters.getMinExposureCompensation();
            String.format("camera exposure compensation min: %d, max: %d, current: %d", Integer.valueOf(minExposureCompensation), Integer.valueOf(maxExposureCompensation), Integer.valueOf(parameters.getExposureCompensation()));
            return new CountDownTimer(this.exposureTime, this.exposureTimeInterval) { // from class: com.jd.aips.verify.face.biz.ExposureChangeHelper.1
                private int index = 0;

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    ExposureChangeHelper.this.resumeExposure(fsCameraProxy);
                    exposureChangeCallback.onCompleted();
                }

                @Override // android.os.CountDownTimer
                public void onTick(long j2) {
                    if (ExposureChangeHelper.this.cancelled) {
                        return;
                    }
                    Camera camera = fsCameraProxy.getCamera();
                    if (camera != null) {
                        if (this.index < ExposureChangeHelper.this.exposureRule.size()) {
                            float floatValue = ((Float) ExposureChangeHelper.this.exposureRule.get(this.index)).floatValue();
                            String.format("change exposure to level: %f", Float.valueOf(floatValue));
                            int round = Math.round(floatValue * maxExposureCompensation);
                            int i2 = minExposureCompensation;
                            if (round < i2 || round > (i2 = maxExposureCompensation)) {
                                round = i2;
                            }
                            String str = "set exposure compensation: " + round;
                            try {
                                Camera.Parameters parameters2 = camera.getParameters();
                                parameters2.setAutoExposureLock(true);
                                parameters2.setAutoWhiteBalanceLock(true);
                                parameters2.setExposureCompensation(round);
                                camera.setParameters(parameters2);
                                ExposureChangeHelper.this.exposureCompensationValues.add(Integer.valueOf(round));
                            } catch (Exception unused) {
                            }
                            this.index++;
                            return;
                        }
                        return;
                    }
                    cancel();
                }
            };
        } catch (Exception unused) {
            return null;
        }
    }

    private void reset() {
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.countDownTimer = null;
        }
        this.exposureCompensationValues.clear();
    }

    public void cancel() {
        this.cancelled = true;
        reset();
    }

    public void changeExposure(@NonNull FsCameraProxy fsCameraProxy, @NonNull ExposureChangeCallback exposureChangeCallback) {
        this.cancelled = false;
        reset();
        if (fsCameraProxy.getCamera() != null) {
            CountDownTimer createCountDownTimer = createCountDownTimer(fsCameraProxy, exposureChangeCallback);
            this.countDownTimer = createCountDownTimer;
            if (createCountDownTimer != null) {
                createCountDownTimer.start();
                return;
            } else {
                exposureChangeCallback.onCompleted();
                return;
            }
        }
        exposureChangeCallback.onCompleted();
    }

    public List<Integer> getExposureCompensationValues() {
        return this.exposureCompensationValues;
    }

    public List<Float> getExposureRule() {
        return this.exposureRule;
    }

    public void resumeExposure(FsCameraProxy fsCameraProxy) {
        Camera camera = fsCameraProxy.getCamera();
        if (camera != null) {
            try {
                Camera.Parameters parameters = camera.getParameters();
                parameters.setExposureCompensation(0);
                parameters.setAutoExposureLock(false);
                parameters.setAutoWhiteBalanceLock(false);
                camera.setParameters(parameters);
            } catch (Exception unused) {
            }
        }
    }
}
