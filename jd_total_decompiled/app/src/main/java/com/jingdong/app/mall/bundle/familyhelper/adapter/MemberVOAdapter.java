package com.jingdong.app.mall.bundle.familyhelper.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.jd.framework.json.JDJSONObject;
import com.jd.mobile.image.ImageRequestListener;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.app.mall.bundle.familyhelper.R;
import com.jingdong.app.mall.bundle.familyhelper.entity.FamilyRelationDTO;
import com.jingdong.app.mall.bundle.familyhelper.util.FamilyHelperConstant;
import com.jingdong.app.mall.bundle.familyhelper.util.FamilyHelperNetUtil;
import com.jingdong.app.mall.bundle.familyhelper.util.JumpUtil;
import com.jingdong.app.mall.bundle.familyhelper.util.StringUtil;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.utils.friend.JDFriendConstants;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.utils.FontsUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes18.dex */
public class MemberVOAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Activity activity;
    private String channelId;
    private JDJSONObject jsonParam;
    private List<FamilyRelationDTO> memberVOList;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes18.dex */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView label;
        SimpleDraweeView memberIcon;
        TextView messageNumber;
        TextView nickName;

        public ViewHolder(View view) {
            super(view);
            this.memberIcon = (SimpleDraweeView) view.findViewById(R.id.memberIcon);
            this.messageNumber = (TextView) view.findViewById(R.id.messageNumber);
            this.label = (TextView) view.findViewById(R.id.label);
            this.nickName = (TextView) view.findViewById(R.id.nickName);
        }
    }

    public MemberVOAdapter() {
        this.memberVOList = new ArrayList();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.memberVOList.size();
    }

    public void setMemberVOList(List<FamilyRelationDTO> list) {
        this.memberVOList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i2) {
        final FamilyRelationDTO familyRelationDTO = this.memberVOList.get(i2);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) viewHolder.itemView.getLayoutParams();
        if (i2 == 0) {
            ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = DpiUtil.dip2px(this.activity, 32.0f);
        } else {
            ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = 0;
        }
        if (familyRelationDTO.isInvite()) {
            viewHolder.itemView.setLayoutParams(layoutParams);
            viewHolder.messageNumber.setVisibility(4);
            viewHolder.label.setVisibility(4);
            viewHolder.nickName.setText("\u9080\u8bf7\u5bb6\u4eba");
            viewHolder.memberIcon.setImageResource(R.mipmap.icon_add_family);
        } else {
            viewHolder.itemView.setLayoutParams(layoutParams);
            JDImageLoader.display(StringUtil.verifyUri(familyRelationDTO.imgUrl, ""), viewHolder.memberIcon, new JDDisplayImageOptions(), (ImageRequestListener<ImageInfo>) null);
            viewHolder.nickName.setText(StringUtil.verifyString(familyRelationDTO.nickname, "\u5bb6\u4eba"));
            viewHolder.label.setText(StringUtil.verifyString(familyRelationDTO.labelName, "\u5bb6\u4eba"));
            viewHolder.label.setVisibility(0);
            String str = "memberVO.unReadNum\uff1a" + familyRelationDTO.unReadNum;
            int i3 = familyRelationDTO.unReadNum;
            if (i3 > 0) {
                String valueOf = String.valueOf(i3);
                if (familyRelationDTO.unReadNum > 99) {
                    valueOf = "99+";
                }
                viewHolder.messageNumber.setTypeface(FontsUtil.getTypeFace(this.activity, 4099));
                viewHolder.messageNumber.setText(valueOf);
                viewHolder.messageNumber.setVisibility(0);
            }
        }
        viewHolder.memberIcon.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.familyhelper.adapter.MemberVOAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (familyRelationDTO.isInvite()) {
                    JDMtaUtils.sendClickDataWithExt(MemberVOAdapter.this.activity, "family_elder_popupInviteFamily", "", "", "family_elder", "", "", "", "{\"source\":\"" + MemberVOAdapter.this.channelId + "\"}", null);
                    JumpUtil.jumpToJDReactMyFamily(MemberVOAdapter.this.activity, MemberVOAdapter.this.channelId, MemberVOAdapter.this.jsonParam);
                    return;
                }
                JDMtaUtils.sendClickDataWithExt(MemberVOAdapter.this.activity, "family_elder_popupHead", "", "", "family_elder", "", "", "", "{\"source\":\"" + MemberVOAdapter.this.channelId + "\"}", null);
                StringBuilder sb = new StringBuilder();
                sb.append("jsonParam:");
                sb.append(MemberVOAdapter.this.jsonParam);
                sb.toString();
                if (MemberVOAdapter.this.jsonParam == null || !StringUtil.isEmpty(MemberVOAdapter.this.jsonParam.optString("pid")) || StringUtil.isEmpty(MemberVOAdapter.this.jsonParam.optString("url"))) {
                    JumpUtil.jumpToSingleChat(MemberVOAdapter.this.activity, familyRelationDTO.pin, MemberVOAdapter.this.jsonParam);
                    return;
                }
                MemberVOAdapter.this.jsonParam.put("channelId", (Object) MemberVOAdapter.this.channelId);
                MemberVOAdapter.this.jsonParam.put(JDFriendConstants.REQUEST_PARAM_TARGET_PIN, (Object) familyRelationDTO.pin);
                MemberVOAdapter.this.jsonParam.put("client", (Object) "android");
                FamilyHelperNetUtil.sendHttpRequest(null, FamilyHelperConstant.FAMILY_IM_SHARE, MemberVOAdapter.this.jsonParam, true, new HttpGroup.OnCommonListener() { // from class: com.jingdong.app.mall.bundle.familyhelper.adapter.MemberVOAdapter.1.1
                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                    public void onEnd(HttpResponse httpResponse) {
                        JumpUtil.jumpToSingleChat(MemberVOAdapter.this.activity, familyRelationDTO.pin, null);
                    }

                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                    public void onError(HttpError httpError) {
                        JumpUtil.jumpToSingleChat(MemberVOAdapter.this.activity, familyRelationDTO.pin, null);
                    }

                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
                    public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                    }
                });
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_family_member, viewGroup, false);
        ViewGroup.LayoutParams layoutParams = inflate.getLayoutParams();
        layoutParams.width = DpiUtil.getAppWidth(this.activity) / 5;
        inflate.setLayoutParams(layoutParams);
        return new ViewHolder(inflate);
    }

    public MemberVOAdapter(List<FamilyRelationDTO> list, Activity activity, JDJSONObject jDJSONObject, String str) {
        this.memberVOList = list;
        this.activity = activity;
        this.jsonParam = jDJSONObject;
        this.channelId = str;
    }
}
