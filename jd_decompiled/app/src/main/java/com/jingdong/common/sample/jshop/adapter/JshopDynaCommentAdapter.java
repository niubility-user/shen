package com.jingdong.common.sample.jshop.adapter;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.common.sample.jshop.Entity.JshopComment;
import com.jingdong.common.sample.jshop.utils.JshopTextViewUtils;
import com.jingdong.common.sample.jshop.utils.SpannableStringUtils;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.DPIUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class JshopDynaCommentAdapter extends BaseAdapter {
    private static final String TAG = "JshopDynaCommentAdapter";
    private MyActivity mActivity;
    private TextView mCommentNum;
    public String mNotice;
    private List<JshopComment.JshopDynamicComment> mList = new ArrayList();
    public int mTotalCount = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class ViewHolder {
        TextView commentInfo;
        TextView commentTime;
        TextView floorNumber;
        View noticeContainer;
        TextView noticeContent;
        TextView pCommentInfo;
        View pCommentLayout;
        TextView topCommentNum;
        SimpleDraweeView userIcon;
        TextView userName;

        ViewHolder() {
        }
    }

    public JshopDynaCommentAdapter(MyActivity myActivity) {
        this.mActivity = myActivity;
    }

    private View handlerConvertView(int i2, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view != null && view.getTag() != null) {
            viewHolder = (ViewHolder) view.getTag();
        } else {
            view = ImageUtil.inflate(this.mActivity, (int) R.layout.jshop_dy_comment_item, (ViewGroup) null);
            viewHolder = new ViewHolder();
            viewHolder.userIcon = (SimpleDraweeView) view.findViewById(R.id.jshop_comment_icon);
            viewHolder.commentInfo = (TextView) view.findViewById(R.id.comment_info);
            viewHolder.commentTime = (TextView) view.findViewById(R.id.comment_time);
            viewHolder.userName = (TextView) view.findViewById(R.id.user_name);
            viewHolder.floorNumber = (TextView) view.findViewById(R.id.floor_number);
            viewHolder.pCommentLayout = view.findViewById(R.id.p_comment_layout);
            viewHolder.pCommentInfo = (TextView) view.findViewById(R.id.p_comment_info);
            viewHolder.topCommentNum = (TextView) view.findViewById(R.id.top_comment_num);
            viewHolder.noticeContainer = view.findViewById(R.id.notice_container);
            viewHolder.noticeContent = (TextView) view.findViewById(R.id.notice_content);
            if (JshopTextViewUtils.IS_LARGE_MODE) {
                JshopTextViewUtils.getInstance().doTextFontScaled(viewHolder.commentInfo, viewHolder.commentTime, viewHolder.userName, viewHolder.floorNumber, viewHolder.pCommentInfo, viewHolder.topCommentNum, viewHolder.noticeContent);
            }
            view.setTag(viewHolder);
        }
        JshopComment.JshopDynamicComment item = getItem(i2);
        if (item != null && item.floorNo != -1) {
            viewHolder.floorNumber.setVisibility(0);
            viewHolder.floorNumber.setText(item.floorNo + "\u697c");
        } else {
            viewHolder.floorNumber.setVisibility(8);
        }
        viewHolder.noticeContainer.setVisibility(8);
        if (i2 == 0) {
            viewHolder.topCommentNum.setVisibility(0);
            TextView textView = viewHolder.topCommentNum;
            this.mCommentNum = textView;
            textView.setText("\u8bc4\u8bba" + this.mTotalCount);
            if (!TextUtils.isEmpty(this.mNotice)) {
                viewHolder.noticeContainer.setVisibility(0);
                viewHolder.noticeContent.setText(this.mNotice);
            }
        } else {
            viewHolder.topCommentNum.setVisibility(8);
        }
        if (item != null) {
            viewHolder.commentInfo.setText(item.comment);
            viewHolder.userName.setText(item.userName);
            viewHolder.userName.setTextColor(item.isVender == 0 ? -20430 : -8224126);
            viewHolder.commentInfo.setText(item.comment);
            viewHolder.commentTime.setText(item.cTime);
            if (TextUtils.isEmpty(item.pComment)) {
                viewHolder.pCommentLayout.setVisibility(8);
            } else {
                viewHolder.pCommentLayout.setVisibility(0);
                String str = item.pUserName + "\uff1a";
                viewHolder.pCommentInfo.setText(SpannableStringUtils.getDetailCommentSpan("\u56de\u590d\u3000" + str + item.pComment, str, item.pMySelft ? -13312 : -6579301));
            }
            if (!TextUtils.isEmpty(item.headPic)) {
                JDImageUtils.displayImage(item.headPic, viewHolder.userIcon);
            } else {
                viewHolder.userIcon.setImageResource(R.drawable.jshop_user_icon);
            }
        } else {
            Log.d(TAG, "error");
        }
        return view;
    }

    public void addCommentItem(JshopComment.JshopDynamicComment jshopDynamicComment) {
        List<JshopComment.JshopDynamicComment> list = this.mList;
        if (list != null) {
            list.add(0, jshopDynamicComment);
            notifyDataSetChanged();
        }
    }

    public View createEmptyView() {
        LinearLayout linearLayout = new LinearLayout(this.mActivity);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, DPIUtil.getHeight() - DPIUtil.dip2px(109.0f));
        View inflate = ImageUtil.inflate((int) R.layout.jshop_dynamic_comment_empty, (ViewGroup) null, false);
        TextView textView = (TextView) inflate.findViewById(R.id.top_comment_num);
        View findViewById = inflate.findViewById(R.id.notice_container);
        TextView textView2 = (TextView) inflate.findViewById(R.id.notice_content);
        TextView textView3 = (TextView) inflate.findViewById(R.id.notice_content_default);
        findViewById.setVisibility(8);
        if (!TextUtils.isEmpty(this.mNotice)) {
            findViewById.setVisibility(0);
            textView2.setText(this.mNotice);
        }
        if (JshopTextViewUtils.IS_LARGE_MODE) {
            JshopTextViewUtils.getInstance().doTextFontScaled(textView, textView2, textView3);
        }
        inflate.setLayoutParams(layoutParams);
        linearLayout.addView(inflate);
        return linearLayout;
    }

    public void delComment(JshopComment.JshopDynamicComment jshopDynamicComment) {
        List<JshopComment.JshopDynamicComment> list = this.mList;
        if (list == null || list.isEmpty()) {
            return;
        }
        this.mList.remove(jshopDynamicComment);
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<JshopComment.JshopDynamicComment> list = this.mList;
        if (list == null || list.isEmpty()) {
            return 1;
        }
        return this.mList.size();
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        List<JshopComment.JshopDynamicComment> list = this.mList;
        if (list != null && !list.isEmpty()) {
            return handlerConvertView(i2, view, viewGroup);
        }
        return createEmptyView();
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean isEmpty() {
        return isEmptyView();
    }

    public boolean isEmptyView() {
        List<JshopComment.JshopDynamicComment> list = this.mList;
        if (list == null) {
            return true;
        }
        return list.isEmpty();
    }

    public void setData(List<JshopComment.JshopDynamicComment> list) {
        List<JshopComment.JshopDynamicComment> list2 = this.mList;
        if (list2 != null) {
            list2.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void updateListSize(int i2) {
        this.mTotalCount = i2;
        TextView textView = this.mCommentNum;
        if (textView != null) {
            textView.setText("\u8bc4\u8bba" + i2);
        }
    }

    @Override // android.widget.Adapter
    public JshopComment.JshopDynamicComment getItem(int i2) {
        List<JshopComment.JshopDynamicComment> list = this.mList;
        if (list == null || list.isEmpty()) {
            return null;
        }
        return this.mList.get(i2);
    }
}
