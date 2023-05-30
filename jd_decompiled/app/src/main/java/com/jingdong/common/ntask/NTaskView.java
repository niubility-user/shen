package com.jingdong.common.ntask;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.mylib.R;
import com.jingdong.common.BaseActivity;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.JDSharedPreferences;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.TimeZone;

/* loaded from: classes.dex */
public final class NTaskView extends RelativeLayout implements LifecycleObserver {
    private ImageView closeView;
    private NTaskPopView hintPopView;
    private BaseActivity mActivity;
    private Lifecycle mLifecycle;
    private NTaskStateListener mListener;
    private NTaskButton mTaskButton;
    private Observer<NTaskEvent> modelObserver;
    private NTaskVM nTaskVM;
    private NTaskXView nTaskXView;

    public NTaskView(@NonNull Context context) {
        super(context);
        this.modelObserver = new Observer<NTaskEvent>() { // from class: com.jingdong.common.ntask.NTaskView.1
            @Override // androidx.lifecycle.Observer
            public void onChanged(NTaskEvent nTaskEvent) {
                NTaskView.this.handlerData(nTaskEvent);
            }
        };
        init();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlerData(NTaskEvent nTaskEvent) {
        if (getLayoutParams() == null) {
            return;
        }
        getLayoutParams().width = -2;
        getLayoutParams().height = DPIUtil.getWidthByDesignValue750(getContext(), 100);
        final NTaskModel nTaskModel = nTaskEvent.nTaskModel;
        String str = "NTaskView this:" + hashCode() + " update";
        if (nTaskModel == null) {
            NTaskStateListener nTaskStateListener = this.mListener;
            if (nTaskStateListener != null) {
                nTaskStateListener.update(false);
                return;
            }
            return;
        }
        NTaskStateListener nTaskStateListener2 = this.mListener;
        if (nTaskStateListener2 != null) {
            nTaskStateListener2.update(true);
        }
        this.mTaskButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ntask.NTaskView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (NTaskView.this.nTaskXView == null) {
                    NTaskView nTaskView = NTaskView.this;
                    nTaskView.nTaskXView = new NTaskXView(nTaskView.mActivity, NTaskView.this.mListener);
                }
                NTaskView.this.nTaskXView.openXViewH5(nTaskModel.url);
                NTaskView.this.mtaClick(nTaskModel);
            }
        });
        this.closeView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ntask.NTaskView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NTaskView.this.removeAllViews();
                if (NTaskView.this.mListener != null) {
                    NTaskView.this.mListener.close();
                }
            }
        });
        this.mTaskButton.update(nTaskModel);
        updateHintPowView(nTaskModel);
        mtaExposure(nTaskModel);
    }

    private void init() {
        if (getContext() instanceof BaseActivity) {
            BaseActivity baseActivity = (BaseActivity) getContext();
            this.mActivity = baseActivity;
            Lifecycle lifecycle = baseActivity.getLifecycle();
            this.mLifecycle = lifecycle;
            lifecycle.addObserver(this);
            NTaskVM nTaskVM = new NTaskVM(this.mActivity);
            this.nTaskVM = nTaskVM;
            nTaskVM.taskLiveData.observe(this.mActivity, this.modelObserver);
            NTaskButton nTaskButton = new NTaskButton(getContext());
            this.mTaskButton = nTaskButton;
            nTaskButton.setId(R.id.ntask_btn);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(DPIUtil.getWidthByDesignValue750(getContext(), 100), DPIUtil.getWidthByDesignValue750(getContext(), 100));
            layoutParams.addRule(11);
            addView(this.mTaskButton, layoutParams);
            ImageView imageView = new ImageView(getContext());
            this.closeView = imageView;
            imageView.setId(R.id.ntask_close);
            this.closeView.setImageResource(R.drawable.ntask_icon_close);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(DPIUtil.dip2px(15.0f), DPIUtil.dip2px(10.0f));
            layoutParams2.addRule(0, this.mTaskButton.getId());
            this.closeView.setPadding(DPIUtil.dip2px(5.0f), 0, 0, 0);
            addView(this.closeView, layoutParams2);
            this.hintPopView = new NTaskPopView(getContext());
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(DPIUtil.getWidthByDesignValue750(getContext(), 140), DPIUtil.getWidthByDesignValue750(getContext(), 100));
            layoutParams3.addRule(0, this.closeView.getId());
            addView(this.hintPopView, layoutParams3);
        }
    }

    public static boolean isSameDay(long j2, long j3, TimeZone timeZone) {
        long j4 = j2 - j3;
        return j4 < 86400000 && j4 > -86400000 && millis2Days(j2, timeZone) == millis2Days(j3, timeZone);
    }

    private static long millis2Days(long j2, TimeZone timeZone) {
        return (timeZone.getOffset(j2) + j2) / 86400000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mtaClick(NTaskModel nTaskModel) {
        try {
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("contentid", (Object) nTaskModel.contentId);
            jDJSONObject.put("contentType", (Object) Integer.valueOf(nTaskModel.contentType));
            jDJSONObject.put("activityid", (Object) nTaskModel.activityId);
            JDMtaUtils.sendClickDataWithExt(this.mActivity, "video_content_fission_icon", "", "", "", "", "", "", jDJSONObject.toJSONString(), null);
        } catch (Exception unused) {
        }
    }

    private void mtaExposure(NTaskModel nTaskModel) {
        try {
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("contentid", (Object) nTaskModel.contentId);
            jDJSONObject.put("contentType", (Object) Integer.valueOf(nTaskModel.contentType));
            jDJSONObject.put("activityid", (Object) nTaskModel.activityId);
            JDMtaUtils.sendExposureDataWithExt(this.mActivity, "video_content_fisstion_icon_expo", "", "", "", "", jDJSONObject.toJSONString(), null);
        } catch (Exception unused) {
        }
    }

    private void updateHintPowView(NTaskModel nTaskModel) {
        try {
            JDSharedPreferences jDSharedPreferences = new JDSharedPreferences(JdSdk.getInstance().getApplicationContext(), "jdNTask", 0);
            long j2 = jDSharedPreferences.getLong("popTime" + nTaskModel.contentType, 0L);
            long currentTimeMillis = System.currentTimeMillis();
            if (isSameDay(j2, currentTimeMillis, TimeZone.getDefault())) {
                return;
            }
            this.hintPopView.update(nTaskModel, this.mListener);
            jDSharedPreferences.edit().putLong("popTime" + nTaskModel.contentType, currentTimeMillis).commit();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        NTaskXView nTaskXView = this.nTaskXView;
        if (nTaskXView != null) {
            nTaskXView.onDestroy();
        }
        this.nTaskVM.taskLiveData.removeObserver(this.modelObserver);
    }

    public void show(NTaskModel nTaskModel, NTaskStateListener nTaskStateListener) {
        if (nTaskModel == null) {
            return;
        }
        if (nTaskStateListener != null) {
            this.mListener = nTaskStateListener;
        }
        String str = "NTaskView this:" + hashCode() + " show";
        handlerData(new NTaskEvent(true, nTaskModel));
    }

    public void update(JDJSONObject jDJSONObject) {
        this.nTaskVM.taskReq(jDJSONObject);
    }
}
