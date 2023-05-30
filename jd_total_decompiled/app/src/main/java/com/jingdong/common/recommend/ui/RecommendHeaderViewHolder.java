package com.jingdong.common.recommend.ui;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendConfig;
import com.jingdong.common.utils.DeepDarkUtils;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes6.dex */
public class RecommendHeaderViewHolder extends RecyclerView.ViewHolder {
    private int from;
    private SimpleDraweeView onlineImg;
    private TextView testin;

    public RecommendHeaderViewHolder(View view, int i2) {
        super(view);
        this.from = i2;
        this.onlineImg = (SimpleDraweeView) view.findViewById(R.id.online_layout_img);
        this.testin = (TextView) view.findViewById(R.id.recommend_testin_btn);
    }

    public void onBindRecommendHeaderViewHolder(String str, String str2, String str3, RecommendConfig recommendConfig) {
        try {
            if (this.from == 6) {
                this.itemView.setBackgroundResource(R.color.transparent);
            } else if (recommendConfig != null && recommendConfig.isDarkEnable()) {
                this.itemView.setBackgroundColor(DeepDarkUtils.getDarkColor_F2F2F2_bg1());
            } else {
                this.itemView.setBackgroundColor(Color.parseColor(JDDarkUtil.COLOR_F6F6F6));
            }
            if (recommendConfig != null && recommendConfig.isDarkEnable()) {
                if (TextUtils.isEmpty(str2)) {
                    JDImageUtils.displayImage("res:///" + R.drawable.recommend_head_image_dark, this.onlineImg);
                } else {
                    JDImageUtils.displayImage(str2, this.onlineImg, (JDDisplayImageOptions) null, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.ui.RecommendHeaderViewHolder.1
                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingCancelled(String str4, View view) {
                            if (Log.D) {
                                Log.e("onlineImg", "onLoadingCancelled");
                            }
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingComplete(String str4, View view, Bitmap bitmap) {
                            if (Log.D) {
                                Log.e("onlineImg", "onLoadingComplete");
                            }
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingFailed(String str4, View view, JDFailReason jDFailReason) {
                            RecommendHeaderViewHolder.this.onlineImg.setImageResource(R.drawable.recommend_head_image_dark);
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingStarted(String str4, View view) {
                            if (Log.D) {
                                Log.e("onlineImg", "onLoadingStarted");
                            }
                        }
                    });
                }
            } else if (TextUtils.isEmpty(str)) {
                JDImageUtils.displayImage("res:///" + R.drawable.recommend_headimg, this.onlineImg);
            } else {
                JDImageUtils.displayImage(str, this.onlineImg, (JDDisplayImageOptions) null, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.ui.RecommendHeaderViewHolder.2
                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingCancelled(String str4, View view) {
                        if (Log.D) {
                            Log.e("onlineImg", "onLoadingCancelled");
                        }
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingComplete(String str4, View view, Bitmap bitmap) {
                        if (Log.D) {
                            Log.e("onlineImg", "onLoadingComplete");
                        }
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingFailed(String str4, View view, JDFailReason jDFailReason) {
                        RecommendHeaderViewHolder.this.onlineImg.setImageResource(R.drawable.recommend_headimg);
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingStarted(String str4, View view) {
                        if (Log.D) {
                            Log.e("onlineImg", "onLoadingStarted");
                        }
                    }
                });
            }
            if (TextUtils.equals("1", str3)) {
                this.testin.setVisibility(0);
            } else {
                this.testin.setVisibility(8);
            }
            this.onlineImg.requestLayout();
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
    }

    public void setOnTestClickListener(View.OnClickListener onClickListener) {
        TextView textView = this.testin;
        if (textView != null) {
            textView.setOnClickListener(onClickListener);
        }
    }
}
