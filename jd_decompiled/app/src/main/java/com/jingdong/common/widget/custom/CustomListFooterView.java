package com.jingdong.common.widget.custom;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.common.R;
import com.jingdong.common.ui.JDProgressBar;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.sdk.log.Log;

/* loaded from: classes12.dex */
public class CustomListFooterView extends RelativeLayout {
    public static final short FOOTER_ALL_COMMENT_ENTRANCE = 7;
    public static final short FOOTER_ALL_FILTER = 6;
    public static final short FOOTER_END = 3;
    public static final short FOOTER_ERROR = 2;
    public static final short FOOTER_NODATA = 4;
    public static final short FOOTER_NORMAL = 1;
    public static final short FOOTER_NOTHING = 5;
    private static final String TAG = "CustomListFooterView";
    private AllCommentEntranceListener allCommentEntranceListener;
    private String endText;
    private FrameLayout end_content;
    private TextView end_tv;
    private String externNoDataStr;
    private LinearLayout loadingLayout;
    private boolean mAttached;
    private LinearLayout nodata_content;
    private TextView nodata_txt;
    private String objectText;
    private JDProgressBar progressBar;
    private RetryListener retryListener;
    private RelativeLayout rl_all_comment_entrance;
    private short state;
    private TextView text;

    /* loaded from: classes12.dex */
    public interface AllCommentEntranceListener {
        void jumpToAllComment();
    }

    /* loaded from: classes12.dex */
    public interface RetryListener {
        void emptyRetry();

        void retry();
    }

    public CustomListFooterView(Context context) {
        this(context, null);
    }

    private void footerStateChange(short s) {
        this.loadingLayout.setVisibility(8);
        this.nodata_content.setVisibility(8);
        this.progressBar.setVisibility(8);
        this.end_content.setVisibility(8);
        switch (s) {
            case 1:
                this.progressBar.setVisibility(this.nodata_content.getVisibility() == 0 ? 8 : 0);
                this.loadingLayout.setClickable(false);
                this.loadingLayout.setVisibility(this.nodata_content.getVisibility() == 0 ? 8 : 0);
                this.nodata_content.setVisibility(8);
                this.end_content.setVisibility(8);
                this.rl_all_comment_entrance.setVisibility(8);
                this.text.setText(getResources().getString(R.string.recommend_loading));
                Log.d(TAG, "FOOTER_NORMAL");
                break;
            case 2:
                this.loadingLayout.setClickable(true);
                this.loadingLayout.setVisibility(0);
                this.rl_all_comment_entrance.setVisibility(8);
                this.text.setText(getResources().getString(R.string.click_reload));
                Log.d(TAG, "FOOTER_ERROR");
                break;
            case 3:
                this.end_content.setVisibility(0);
                this.rl_all_comment_entrance.setVisibility(8);
                if (TextUtils.isEmpty(this.endText)) {
                    this.end_tv.setText(getResources().getString(R.string.no_more_data));
                } else {
                    this.end_tv.setText(this.endText);
                }
                Log.d(TAG, "FOOTER_END");
                break;
            case 4:
                this.nodata_content.setVisibility(0);
                this.rl_all_comment_entrance.setVisibility(8);
                if (TextUtils.isEmpty(this.objectText) && TextUtils.isEmpty(this.externNoDataStr)) {
                    this.nodata_txt.setText(getResources().getString(R.string.zan_wu_data));
                } else if (!TextUtils.isEmpty(this.externNoDataStr)) {
                    this.nodata_txt.setText(this.externNoDataStr);
                } else {
                    this.nodata_txt.setText(getResources().getString(R.string.fenlei_zan_wu_data, this.objectText));
                }
                Log.d(TAG, "FOOTER_NODATA");
                break;
            case 5:
                this.loadingLayout.setClickable(false);
                Log.d(TAG, "FOOTER_NOTHING");
                this.loadingLayout.setVisibility(8);
                this.rl_all_comment_entrance.setVisibility(8);
                break;
            case 6:
                this.loadingLayout.setClickable(true);
                this.loadingLayout.setVisibility(0);
                this.text.setText(getResources().getString(R.string.all_filter));
                Log.d(TAG, "FOOTER_ALL_FILTER");
                break;
            case 7:
                this.rl_all_comment_entrance.setVisibility(0);
                break;
        }
        postInvalidate();
    }

    private void initFooter() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.channel_loading_layout);
        this.loadingLayout = linearLayout;
        this.progressBar = (JDProgressBar) linearLayout.findViewById(R.id.channel_loading_pb);
        this.text = (TextView) this.loadingLayout.findViewById(R.id.channel_loading_tv);
        this.loadingLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.CustomListFooterView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CustomListFooterView.this.retryListener != null) {
                    CustomListFooterView.this.retryListener.retry();
                }
            }
        });
        this.end_content = (FrameLayout) findViewById(R.id.end_content);
        this.end_tv = (TextView) findViewById(R.id.end_tv);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.nodata_content);
        this.nodata_content = linearLayout2;
        linearLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.CustomListFooterView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CustomListFooterView.this.retryListener != null) {
                    CustomListFooterView.this.retryListener.emptyRetry();
                }
            }
        });
        this.nodata_txt = (TextView) findViewById(R.id.nodata_txt);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rl_all_comment_entrance);
        this.rl_all_comment_entrance = relativeLayout;
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.CustomListFooterView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CustomListFooterView.this.allCommentEntranceListener != null) {
                    CustomListFooterView.this.allCommentEntranceListener.jumpToAllComment();
                }
            }
        });
    }

    public short getState() {
        return this.state;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mAttached = true;
        footerStateChange(this.state);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        this.mAttached = false;
        super.onDetachedFromWindow();
    }

    public void setAllCommentEntranceListener(AllCommentEntranceListener allCommentEntranceListener) {
        this.allCommentEntranceListener = allCommentEntranceListener;
    }

    public void setEndText(String str) {
        this.endText = str;
    }

    public void setEndView(View view) {
        if (view != null) {
            this.end_content.removeAllViews();
            this.end_content.addView(view);
        }
    }

    public void setExternNoDataStr(String str) {
        this.externNoDataStr = str;
    }

    public void setFooterState(short s) {
        this.state = s;
        if (this.mAttached) {
            footerStateChange(s);
        }
    }

    public void setNoDataView(View view) {
        if (view != null) {
            this.nodata_content.removeAllViews();
            this.nodata_content.addView(view);
        }
    }

    public void setObjectText(String str) {
        this.objectText = str;
    }

    public void setRetryListener(RetryListener retryListener) {
        this.retryListener = retryListener;
    }

    public CustomListFooterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        ImageUtil.inflate(R.layout.layout_list_footer, this);
        initFooter();
    }

    /* loaded from: classes12.dex */
    public static class Builder {
        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        public CustomListFooterView create(String str, String str2) {
            CustomListFooterView customListFooterView = new CustomListFooterView(this.context);
            customListFooterView.setExternNoDataStr(str);
            customListFooterView.setEndText(str2);
            return customListFooterView;
        }

        public CustomListFooterView create(String str, View view) {
            CustomListFooterView customListFooterView = new CustomListFooterView(this.context);
            customListFooterView.setExternNoDataStr(str);
            customListFooterView.setEndView(view);
            return customListFooterView;
        }

        public CustomListFooterView create(View view, View view2) {
            CustomListFooterView customListFooterView = new CustomListFooterView(this.context);
            customListFooterView.setNoDataView(view);
            customListFooterView.setEndView(view2);
            return customListFooterView;
        }

        @Deprecated
        public CustomListFooterView create(String str, String str2, String str3) {
            CustomListFooterView create = create(str, str2);
            create.setObjectText(str3);
            return create;
        }
    }
}
