package com.jingdong.common.widget.custom;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.frame.IResumeListener;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

@Deprecated
/* loaded from: classes12.dex */
public class ZanUtil {
    public static final int MSG_LIKE_CANCEL = 3;
    public static final int MSG_LIKE_CANCEL_FAIL = 4;
    public static final int MSG_LIKE_FAIL = 2;
    public static final int MSG_LIKE_SUCCESS = 0;
    public static final int MSG_LIKE_SUCCESS_REPEAT = 1;
    public static boolean isLikeInProgress;

    /* loaded from: classes12.dex */
    public interface OnHandleLikeStatusListener {
        void getLikeStatus(int i2, long j2);
    }

    public static void clickZan(final IMyActivity iMyActivity, final int i2, final String str, final int i3, final OnHandleLikeStatusListener onHandleLikeStatusListener) {
        if (isLikeInProgress) {
            return;
        }
        isLikeInProgress = true;
        LoginUserHelper.getInstance().executeLoginRunnable(iMyActivity, new Runnable() { // from class: com.jingdong.common.widget.custom.ZanUtil.1
            @Override // java.lang.Runnable
            public void run() {
                HttpSetting httpSetting = new HttpSetting();
                httpSetting.setHost(Configuration.getNgwHost());
                httpSetting.setFunctionId("jdDiscoveryZan");
                httpSetting.putJsonParam("type", Integer.valueOf(i2));
                httpSetting.putJsonParam("cancel", Integer.valueOf(i3));
                httpSetting.putJsonParam("id", str);
                httpSetting.setEffect(0);
                httpSetting.setNotifyUser(false);
                httpSetting.setUseFastJsonParser(true);
                httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.widget.custom.ZanUtil.1.1
                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                    public void onEnd(HttpResponse httpResponse) {
                        JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                        if (fastJsonObject != null && fastJsonObject.containsKey("data")) {
                            JDJSONObject optJSONObject = fastJsonObject.optJSONObject("data");
                            if (optJSONObject != null) {
                                int optInt = optJSONObject.containsKey("status") ? optJSONObject.optInt("status", -1) : -1;
                                long optLong = optJSONObject.containsKey("cnt") ? optJSONObject.optLong("cnt", -1L) : -1L;
                                if (optInt == 0) {
                                    AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                                    int i4 = i3;
                                    if (i4 == 0) {
                                        ZanUtil.sendResult(iMyActivity, onHandleLikeStatusListener, 0, optLong);
                                    } else if (1 == i4) {
                                        ZanUtil.sendResult(iMyActivity, onHandleLikeStatusListener, 3, optLong);
                                    }
                                } else if (1 == optInt) {
                                    AnonymousClass1 anonymousClass12 = AnonymousClass1.this;
                                    if (i3 == 0) {
                                        ZanUtil.sendResult(iMyActivity, onHandleLikeStatusListener, 1, optLong);
                                    }
                                } else {
                                    AnonymousClass1 anonymousClass13 = AnonymousClass1.this;
                                    int i5 = i3;
                                    if (i5 == 0) {
                                        ZanUtil.sendResult(iMyActivity, onHandleLikeStatusListener, 2, -1L);
                                    } else if (i5 == 1) {
                                        ZanUtil.sendResult(iMyActivity, onHandleLikeStatusListener, 4, -1L);
                                    }
                                }
                            } else {
                                AnonymousClass1 anonymousClass14 = AnonymousClass1.this;
                                ZanUtil.sendResult(iMyActivity, onHandleLikeStatusListener, 2, -1L);
                            }
                        } else {
                            AnonymousClass1 anonymousClass15 = AnonymousClass1.this;
                            ZanUtil.sendResult(iMyActivity, onHandleLikeStatusListener, 2, -1L);
                        }
                        ZanUtil.isLikeInProgress = false;
                    }

                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                    public void onError(HttpError httpError) {
                        AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                        int i4 = i3;
                        if (i4 == 0) {
                            ZanUtil.sendResult(iMyActivity, onHandleLikeStatusListener, 2, -1L);
                        } else if (i4 == 1) {
                            ZanUtil.sendResult(iMyActivity, onHandleLikeStatusListener, 4, -1L);
                        }
                        ZanUtil.isLikeInProgress = false;
                    }

                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
                    public void onProgress(int i4, int i5) {
                    }

                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
                    public void onStart() {
                    }
                });
                iMyActivity.getHttpGroupaAsynPool().add(httpSetting);
            }
        }, 273);
        iMyActivity.addResumeListener(new IResumeListener() { // from class: com.jingdong.common.widget.custom.ZanUtil.2
            @Override // com.jingdong.common.frame.IResumeListener
            public void onResume() {
                IMyActivity.this.removeResumeListener(this);
                ZanUtil.isLikeInProgress = false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void sendResult(IMyActivity iMyActivity, final OnHandleLikeStatusListener onHandleLikeStatusListener, final int i2, final long j2) {
        if (onHandleLikeStatusListener != null) {
            iMyActivity.post(new Runnable() { // from class: com.jingdong.common.widget.custom.ZanUtil.3
                @Override // java.lang.Runnable
                public void run() {
                    OnHandleLikeStatusListener.this.getLikeStatus(i2, j2);
                }
            });
        }
    }
}
