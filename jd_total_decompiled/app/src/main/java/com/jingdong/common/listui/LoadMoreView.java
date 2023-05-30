package com.jingdong.common.listui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.listui.R;

/* loaded from: classes5.dex */
public class LoadMoreView extends RelativeLayout {
    private LinearLayout jdDogLayout;
    private TextView jdDogText;
    private LinearLayout loadingLayout;
    private TextView loadingText;
    private ReqStatus mStatus;
    private String noMoreText;
    private JDProgressBar progressBar;

    /* renamed from: com.jingdong.common.listui.LoadMoreView$1  reason: invalid class name */
    /* loaded from: classes5.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$jingdong$common$listui$ReqStatus;

        static {
            int[] iArr = new int[ReqStatus.values().length];
            $SwitchMap$com$jingdong$common$listui$ReqStatus = iArr;
            try {
                iArr[ReqStatus.INIT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jingdong$common$listui$ReqStatus[ReqStatus.LOADING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$jingdong$common$listui$ReqStatus[ReqStatus.NET_ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$jingdong$common$listui$ReqStatus[ReqStatus.DATA_ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$jingdong$common$listui$ReqStatus[ReqStatus.DATA_EMPTY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$jingdong$common$listui$ReqStatus[ReqStatus.NOMORE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public LoadMoreView(Context context) {
        this(context, null);
    }

    private void init(Context context, AttributeSet attributeSet, int i2) {
        LayoutInflater.from(context).inflate(R.layout.liui_load_more_view, (ViewGroup) this, true);
        this.loadingLayout = (LinearLayout) findViewById(R.id.loading_layout);
        this.progressBar = (JDProgressBar) findViewById(R.id.loading_pb);
        this.loadingText = (TextView) findViewById(R.id.loading_tv);
        this.jdDogLayout = (LinearLayout) findViewById(R.id.jddog_layout);
        this.jdDogText = (TextView) findViewById(R.id.jddog_text);
    }

    public ReqStatus getStatus() {
        return this.mStatus;
    }

    public void setNoMoreText(String str) {
        this.noMoreText = str;
    }

    public void setRetry(View.OnClickListener onClickListener) {
        this.loadingLayout.setOnClickListener(onClickListener);
        this.jdDogLayout.setOnClickListener(onClickListener);
    }

    public void setStatus(ReqStatus reqStatus) {
        this.mStatus = reqStatus;
        switch (AnonymousClass1.$SwitchMap$com$jingdong$common$listui$ReqStatus[reqStatus.ordinal()]) {
            case 1:
                this.loadingLayout.setVisibility(4);
                this.loadingLayout.setClickable(false);
                this.jdDogLayout.setVisibility(4);
                this.jdDogLayout.setClickable(false);
                return;
            case 2:
                this.loadingLayout.setVisibility(0);
                this.jdDogLayout.setVisibility(8);
                this.progressBar.setVisibility(0);
                this.loadingLayout.setClickable(false);
                this.loadingText.setText(R.string.listui_loading);
                return;
            case 3:
                this.loadingLayout.setVisibility(0);
                this.jdDogLayout.setVisibility(8);
                this.progressBar.setVisibility(8);
                this.loadingLayout.setClickable(true);
                this.loadingText.setText(R.string.listui_net_error);
                return;
            case 4:
                this.loadingLayout.setVisibility(8);
                this.jdDogLayout.setVisibility(0);
                this.jdDogLayout.setClickable(true);
                this.jdDogText.setText(R.string.listui_data_error);
                return;
            case 5:
                this.loadingLayout.setVisibility(8);
                this.jdDogLayout.setVisibility(0);
                this.progressBar.setVisibility(8);
                this.loadingLayout.setClickable(false);
                this.jdDogLayout.setClickable(false);
                this.jdDogText.setText(R.string.listui_data_empty);
                return;
            case 6:
                this.loadingLayout.setVisibility(0);
                this.jdDogLayout.setVisibility(8);
                this.progressBar.setVisibility(8);
                this.loadingLayout.setClickable(false);
                this.jdDogLayout.setClickable(false);
                this.loadingText.setText(TextUtils.isEmpty(this.noMoreText) ? getContext().getString(R.string.listui_nomore) : this.noMoreText);
                return;
            default:
                return;
        }
    }

    public LoadMoreView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LoadMoreView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        init(context, attributeSet, i2);
    }
}
