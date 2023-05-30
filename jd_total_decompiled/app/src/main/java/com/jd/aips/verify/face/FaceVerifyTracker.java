package com.jd.aips.verify.face;

import android.content.Context;
import android.hardware.Camera;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.aips.common.utils.FsGsonUtil;
import com.jd.aips.detect.face.bean.FaceInfo;
import com.jd.aips.detect.face.bean.FrameInfo;
import com.jd.aips.verify.tracker.BaseVerifyTracker;
import com.jd.aips.verify.tracker.TrackerCallback;
import com.jd.aips.verify.tracker.VerifyTracker;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class FaceVerifyTracker extends BaseVerifyTracker<FaceVerifySession> {
    private static final SparseArray<String> ACTION_TYPE_PREFIX_LIST;
    private static final SparseArray<String> ACTION_TYPE_SUFFIX_LIST;
    private static final int EVENT_BEGIN = 1;
    private static final int EVENT_BREAK = 4;
    public static final String EVENT_CHANGE_PREVIEW_SIZE = "change_preview_size";
    public static final String EVENT_CHANGE_PREVIEW_SIZE_TIMEOUT = "change_preview_size_timeout";
    public static final String EVENT_CLICK_FAQ = "click_faq";
    private static final int EVENT_END = 2;
    public static final String EVENT_FACE_EV_BEGIN = "face_ev_begin";
    public static final String EVENT_FACE_EV_END = "face_ev_end";
    public static final String EVENT_FACE_EV_SUCCESS = "face_ev_success";
    private static final int EVENT_FAIL = 3;
    public static final String EVENT_PERFORM_PREVIEW_SIZE_CHANGE = "perform_preview_size_change";
    public static final String EVENT_PREVIEW_SIZE_CHANGE = "preview_size_change";
    public static final String EVENT_PREVIEW_SIZE_CHANGE_EXCEPTION = "preview_size_change_exception";
    public static final String EVENT_PREVIEW_SIZE_CHANGE_FINISH = "preview_size_change_finish";
    public static final String EVENT_PREVIEW_SIZE_CHANGE_SUCCESS = "preview_size_change_success";
    public static final String EVENT_REQUEST_VERIFY = "requestVerity";
    public static final String EVENT_TEST_ACTION_CAMERA_SUPPORT_LIST = "testAction_cameraSupportList";
    public static final String KEY_CAMERA_HEIGHT = "camera_height";
    public static final String KEY_CAMERA_LIST = "cameraList";
    public static final String KEY_CAMERA_WIDTH = "camera_width";
    public static final String KEY_EXPOSURE_COMPENSATION_VALUES = "exposure_compensation_values";
    public static final String KEY_EXPOSURE_RULE = "exposure_rule";
    public static final String KEY_IMGPIX = "imgpix";
    public static final String KEY_IMG_HASHCODE = "imgHashcode";
    public static final String KEY_REASON_MSG = "reason_msg";
    public static final String KEY_SOURCE_HEIGHT = "source_height";
    public static final String KEY_SOURCE_WIDTH = "source_width";
    public static final String KEY_STOP_PREVIEW_TIME = "stopPreview_time";
    private final boolean isDialog;

    static {
        SparseArray<String> sparseArray = new SparseArray<>(4);
        ACTION_TYPE_PREFIX_LIST = sparseArray;
        sparseArray.put(1002, "face_mouth_");
        sparseArray.put(1003, "face_blink_");
        sparseArray.put(1004, "face_shake_");
        sparseArray.put(1005, "face_nod_");
        SparseArray<String> sparseArray2 = new SparseArray<>(4);
        ACTION_TYPE_SUFFIX_LIST = sparseArray2;
        sparseArray2.put(1, "begin");
        sparseArray2.put(2, "end");
        sparseArray2.put(3, "fail");
        sparseArray2.put(4, "break_fail");
    }

    public FaceVerifyTracker(@NonNull Context context, @NonNull FaceVerifySession faceVerifySession, @Nullable TrackerCallback trackerCallback) {
        super(context, FaceVerifyEngine.SDK_NAME, FaceVerifyEngine.SDK_VERSION, faceVerifySession, trackerCallback);
        if (((FaceVerifyConfig) ((FaceVerifyParams) faceVerifySession.verifyParams).verifyConfig).verificationSdk.config.authentication_mode == 3) {
            this.isDialog = true;
        } else {
            this.isDialog = false;
        }
    }

    private String getActionEvent(int i2, int i3) {
        String str = ACTION_TYPE_PREFIX_LIST.get(i2);
        if (TextUtils.isEmpty(str)) {
            str = String.valueOf(i2);
        }
        return str + ACTION_TYPE_SUFFIX_LIST.get(i3);
    }

    @Override // com.jd.aips.verify.tracker.BaseVerifyTracker
    protected String getDefaultPCode() {
        return this.isDialog ? VerifyTracker.P_CODE_NO_FEEL : VerifyTracker.P_CODE_FACE;
    }

    public void trackActionBegin(int i2) {
        try {
            track(getActionEvent(i2, 1), buildTrackData(getDefaultPCode()));
        } catch (Exception unused) {
        }
    }

    public void trackActionBreak(int i2) {
        try {
            String actionEvent = getActionEvent(i2, 4);
            JSONObject buildTrackData = buildTrackData(getDefaultPCode());
            List<FaceInfo> faceInfos = ((FaceVerifySession) this.session).getFaceInfos();
            if (faceInfos != null) {
                buildTrackData.put(VerifyTracker.KEY_MSG1, FsGsonUtil.toJson(faceInfos));
            }
            FrameInfo frameInfo = ((FaceVerifySession) this.session).getFrameInfo();
            if (frameInfo != null) {
                buildTrackData.put(VerifyTracker.KEY_MSG3, FsGsonUtil.toJson(frameInfo));
            }
            track(actionEvent, buildTrackData);
        } catch (Exception unused) {
        }
    }

    public void trackActionEnd(int i2) {
        try {
            String actionEvent = getActionEvent(i2, 2);
            JSONObject buildTrackData = buildTrackData(getDefaultPCode());
            List<FaceInfo> faceInfos = ((FaceVerifySession) this.session).getFaceInfos();
            if (faceInfos != null) {
                buildTrackData.put(VerifyTracker.KEY_MSG1, FsGsonUtil.toJson(faceInfos));
            }
            FaceInfo faceInfoOfResult = ((FaceVerifySession) this.session).getFaceInfoOfResult();
            if (faceInfoOfResult != null) {
                buildTrackData.put(VerifyTracker.KEY_MSG2, FsGsonUtil.toJson(faceInfoOfResult));
            }
            FrameInfo frameInfo = ((FaceVerifySession) this.session).getFrameInfo();
            if (frameInfo != null) {
                buildTrackData.put(VerifyTracker.KEY_MSG3, FsGsonUtil.toJson(frameInfo));
            }
            track(actionEvent, buildTrackData);
        } catch (Exception unused) {
        }
    }

    public void trackActionFail(int i2) {
        try {
            JSONObject buildTrackData = buildTrackData(getDefaultPCode());
            List<FaceInfo> faceInfos = ((FaceVerifySession) this.session).getFaceInfos();
            if (faceInfos != null) {
                buildTrackData.put(VerifyTracker.KEY_MSG1, FsGsonUtil.toJson(faceInfos));
            }
            FrameInfo frameInfo = ((FaceVerifySession) this.session).getFrameInfo();
            if (frameInfo != null) {
                buildTrackData.put(VerifyTracker.KEY_MSG3, FsGsonUtil.toJson(frameInfo));
            }
            track(getActionEvent(i2, 3), buildTrackData);
        } catch (Exception unused) {
        }
    }

    public void trackAllPass() {
        try {
            track(VerifyTracker.EVENT_ALL_PASS, buildTrackData(getDefaultPCode()));
        } catch (Exception unused) {
        }
    }

    public void trackAndroidSoInitFail(int i2, String str) {
        try {
            JSONObject buildTrackData = buildTrackData(getDefaultPCode());
            buildTrackData.put(VerifyTracker.KEY_P_CODE, i2);
            buildTrackData.put("errorMsg", str);
            track(VerifyTracker.KEY_ANDROID_SO_INIT_FAIL, buildTrackData);
        } catch (Exception unused) {
        }
    }

    public void trackChangeColorBegin() {
        try {
            track(VerifyTracker.EVENT_DAZZLE_BEGIN, buildTrackData(getDefaultPCode()));
        } catch (Exception unused) {
        }
    }

    public void trackChangeColorBreak(boolean z) {
        try {
            track(z ? VerifyTracker.EVENT_DAZZLE_END_TOOFAR : VerifyTracker.EVENT_DAZZLE_END_FRAMEOUT, buildTrackData(getDefaultPCode()));
        } catch (Exception unused) {
        }
    }

    public void trackChangeColorDelay() {
        try {
            track(VerifyTracker.EVENT_DAZZLE_CAPTURE_TIMEOUT, buildTrackData(getDefaultPCode()));
        } catch (Exception unused) {
        }
    }

    public void trackChangeExposureBegin() {
        try {
            track(EVENT_FACE_EV_BEGIN, buildTrackData(getDefaultPCode()));
        } catch (Exception unused) {
        }
    }

    public void trackChangeExposureEnd(String str, List<Float> list, List<Integer> list2) {
        try {
            JSONObject buildTrackData = buildTrackData(getDefaultPCode());
            buildTrackData.put(KEY_IMGPIX, str);
            buildTrackData.put(KEY_EXPOSURE_RULE, list);
            buildTrackData.put(KEY_EXPOSURE_COMPENSATION_VALUES, list2);
            track(EVENT_FACE_EV_END, buildTrackData);
        } catch (Exception unused) {
        }
    }

    public void trackChangeResolution(int i2, int i3) {
        try {
            JSONObject buildTrackData = buildTrackData(getDefaultPCode());
            buildTrackData.put(KEY_SOURCE_WIDTH, i2);
            buildTrackData.put(KEY_SOURCE_HEIGHT, i3);
            track(EVENT_PERFORM_PREVIEW_SIZE_CHANGE, buildTrackData);
        } catch (Exception unused) {
        }
    }

    public void trackChangeResolutionException(int i2, int i3, Exception exc) {
        try {
            JSONObject buildTrackData = buildTrackData(getDefaultPCode());
            buildTrackData.put(KEY_SOURCE_WIDTH, i2);
            buildTrackData.put(KEY_SOURCE_HEIGHT, i3);
            buildTrackData.put(VerifyTracker.KEY_EXCEPTION_MSG, exc.getMessage());
            track(EVENT_PREVIEW_SIZE_CHANGE_EXCEPTION, buildTrackData);
        } catch (Exception unused) {
        }
    }

    public void trackChangeResolutionFailed() {
        try {
            JSONObject buildTrackData = buildTrackData(getDefaultPCode());
            buildTrackData.put("rewidth", -1);
            buildTrackData.put("reheight", -1);
            track(EVENT_CHANGE_PREVIEW_SIZE, buildTrackData);
        } catch (Exception unused) {
        }
    }

    public void trackChangeResolutionFinish(int i2, int i3, int i4, int i5) {
        try {
            JSONObject buildTrackData = buildTrackData(getDefaultPCode());
            buildTrackData.put(KEY_SOURCE_WIDTH, i2);
            buildTrackData.put(KEY_SOURCE_HEIGHT, i3);
            buildTrackData.put(KEY_CAMERA_WIDTH, i4);
            buildTrackData.put(KEY_CAMERA_HEIGHT, i5);
            track(EVENT_PREVIEW_SIZE_CHANGE_FINISH, buildTrackData);
        } catch (Exception unused) {
        }
    }

    public void trackChangeResolutionSuccess(String str) {
        try {
            JSONObject buildTrackData = buildTrackData(getDefaultPCode());
            buildTrackData.put(KEY_REASON_MSG, str);
            track(EVENT_PREVIEW_SIZE_CHANGE_SUCCESS, buildTrackData);
        } catch (Exception unused) {
        }
    }

    public void trackChangeResolutionTimeout(String str) {
        try {
            JSONObject buildTrackData = buildTrackData(getDefaultPCode());
            buildTrackData.put(KEY_REASON_MSG, str);
            track(EVENT_CHANGE_PREVIEW_SIZE_TIMEOUT, buildTrackData);
        } catch (Exception unused) {
        }
    }

    public void trackClickFaq() {
        try {
            track(EVENT_CLICK_FAQ, buildTrackData(getDefaultPCode()));
        } catch (Exception unused) {
        }
    }

    public void trackClickMore() {
        try {
            track(VerifyTracker.KEY_CLICK_MORE, buildTrackData(getDefaultPCode()));
        } catch (Exception unused) {
        }
    }

    public void trackDetectSuccess() {
        try {
            JSONObject buildTrackData = buildTrackData(getDefaultPCode());
            List<FaceInfo> faceInfos = ((FaceVerifySession) this.session).getFaceInfos();
            if (faceInfos != null) {
                buildTrackData.put(VerifyTracker.KEY_MSG1, FsGsonUtil.toJson(faceInfos));
            }
            FaceInfo faceInfoOfResult = ((FaceVerifySession) this.session).getFaceInfoOfResult();
            if (faceInfoOfResult != null) {
                buildTrackData.put(VerifyTracker.KEY_MSG2, FsGsonUtil.toJson(faceInfoOfResult));
            }
            FrameInfo frameInfo = ((FaceVerifySession) this.session).getFrameInfo();
            if (frameInfo != null) {
                buildTrackData.put(VerifyTracker.KEY_MSG3, FsGsonUtil.toJson(frameInfo));
            }
            track(VerifyTracker.EVENT_PASS, buildTrackData);
        } catch (Exception unused) {
        }
    }

    public void trackDetectTimeout() {
        try {
            JSONObject buildTrackData = buildTrackData(getDefaultPCode());
            List<FaceInfo> faceInfos = ((FaceVerifySession) this.session).getFaceInfos();
            if (faceInfos != null) {
                buildTrackData.put(VerifyTracker.KEY_MSG1, FsGsonUtil.toJson(faceInfos));
            }
            FrameInfo frameInfo = ((FaceVerifySession) this.session).getFrameInfo();
            if (frameInfo != null) {
                buildTrackData.put(VerifyTracker.KEY_MSG3, FsGsonUtil.toJson(frameInfo));
            }
            track(VerifyTracker.EVENT_REJECT, buildTrackData);
        } catch (Exception unused) {
        }
    }

    public void trackRequestVerify(int i2) {
        try {
            JSONObject buildTrackData = buildTrackData(getDefaultPCode());
            buildTrackData.put(KEY_IMG_HASHCODE, i2);
            track(EVENT_REQUEST_VERIFY, buildTrackData);
        } catch (Exception unused) {
        }
    }

    public void trackSceneError() {
        try {
            track(VerifyTracker.EVENT_SCENE_ERROR, buildTrackData(getDefaultPCode()));
        } catch (Exception unused) {
        }
    }

    public void trackSilenceEnd() {
        try {
            JSONObject buildTrackData = buildTrackData(getDefaultPCode());
            List<FaceInfo> faceInfos = ((FaceVerifySession) this.session).getFaceInfos();
            if (faceInfos != null) {
                buildTrackData.put(VerifyTracker.KEY_MSG1, FsGsonUtil.toJson(faceInfos));
            }
            FrameInfo frameInfo = ((FaceVerifySession) this.session).getFrameInfo();
            if (frameInfo != null) {
                buildTrackData.put(VerifyTracker.KEY_MSG3, FsGsonUtil.toJson(frameInfo));
            }
            track("end", buildTrackData);
        } catch (Exception unused) {
        }
    }

    @Override // com.jd.aips.verify.tracker.BaseVerifyTracker
    public void trackUserCancel() {
        try {
            JSONObject buildTrackData = buildTrackData(getDefaultPCode());
            List<FaceInfo> faceInfos = ((FaceVerifySession) this.session).getFaceInfos();
            if (faceInfos != null) {
                buildTrackData.put(VerifyTracker.KEY_MSG1, FsGsonUtil.toJson(faceInfos));
            }
            FrameInfo frameInfo = ((FaceVerifySession) this.session).getFrameInfo();
            if (frameInfo != null) {
                buildTrackData.put(VerifyTracker.KEY_MSG3, FsGsonUtil.toJson(frameInfo));
            }
            track(VerifyTracker.EVENT_EXIT, buildTrackData);
        } catch (Exception unused) {
        }
    }

    public void trackVerifyFailed(int i2) {
        try {
            JSONObject buildTrackData = buildTrackData(getDefaultPCode());
            buildTrackData.put(VerifyTracker.KEY_CODE, i2);
            buildTrackData.put(VerifyTracker.KEY_SERVER_VERIFY_ID, ((FaceVerifySession) this.session).verifyId);
            track(VerifyTracker.EVENT_REJECT, buildTrackData);
        } catch (Exception unused) {
        }
    }

    public void trackVerifySuccess() {
        try {
            JSONObject buildTrackData = buildTrackData(getDefaultPCode());
            buildTrackData.put(VerifyTracker.KEY_SERVER_VERIFY_ID, ((FaceVerifySession) this.session).verifyId);
            track(VerifyTracker.EVENT_PASS, buildTrackData);
        } catch (Exception unused) {
        }
    }

    public void trackerSupportList(List<Camera.Size> list, String str) {
        try {
            JSONObject buildTrackData = buildTrackData(getDefaultPCode());
            ArrayList arrayList = new ArrayList();
            if (list != null && list.size() > 0) {
                for (Camera.Size size : list) {
                    arrayList.add("width : " + size.width + "height : " + size.height);
                }
            }
            buildTrackData.put(KEY_CAMERA_LIST, arrayList);
            buildTrackData.put(KEY_STOP_PREVIEW_TIME, str);
            track(EVENT_TEST_ACTION_CAMERA_SUPPORT_LIST, buildTrackData);
        } catch (Exception unused) {
        }
    }
}
