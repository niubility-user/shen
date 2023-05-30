package com.jingdong.app.mall.bundle.dolphinlib.common.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.entity.PageInfo;
import com.jd.lib.babel.ifloor.utils.ColorUtil;
import com.jd.lib.babel.ifloor.utils.CommonServiceUtil;
import com.jd.lib.babel.servicekit.imagekit.ImageWraper;
import com.jd.lib.babel.servicekit.model.MtaData;
import com.jd.lib.babel.servicekit.util.DPIUtil;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.mall.bundle.dolphinlib.R;
import com.jingdong.app.mall.bundle.dolphinlib.common.util.EtModelMaker;
import com.jingdong.app.mall.bundle.dolphinlib.common.util.SortUtil;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.AdvertEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.AdvertGroupData;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinAdvMtaEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinTopicBillboardFloorConfig;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinTopicBillboardFloorModel;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes19.dex */
public class DolphinTopicBillboardStaggeredAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private static final int DEFAULT_TEXT_SIZE_DP = 12;
    private static final String ITEM_TEXT_APPEND_CHARACTER = "...";
    private static final int ITEM_TEXT_CUT_LENGTH = 10;
    private DolphinTopicBillboardFloorConfig configLayout;
    private final List<AdvertEntity> dataTopics = new ArrayList();
    private DolphinAdvMtaEntity mAdvMtaEntity = new DolphinAdvMtaEntity();
    private BabelScope scopeHolder;
    private static final int DEFAULT_TEXT_COLOR = ColorUtil.parseColor(JDDarkUtil.COLOR_1A1A1A, -16777216);
    private static final int DEFAULT_BG_COLOR = ColorUtil.parseColor("#F9F7FE", -1);

    /* loaded from: classes19.dex */
    public final class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageWraper vImage;
        private TextView vText;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ItemViewHolder(View view) {
            super(view);
            DolphinTopicBillboardStaggeredAdapter.this = r4;
            this.vImage = (ImageWraper) view.findViewById(R.id.image);
            this.vText = (TextView) view.findViewById(R.id.text);
            if (r4.configLayout == null) {
                view.setBackgroundDrawable(createItemBgDrawable(view, DolphinTopicBillboardStaggeredAdapter.DEFAULT_BG_COLOR));
                this.vText.setTextSize(1, 12.0f);
                this.vText.setTextColor(DolphinTopicBillboardStaggeredAdapter.DEFAULT_TEXT_COLOR);
                return;
            }
            view.setBackgroundDrawable(createItemBgDrawable(view, ColorUtil.parseColor(r4.configLayout.itemBgColor, DolphinTopicBillboardStaggeredAdapter.DEFAULT_BG_COLOR)));
            this.vText.setTextSize(1, r4.configLayout.textSize);
            this.vText.setTextColor(ColorUtil.parseColor(r4.configLayout.titleColor, DolphinTopicBillboardStaggeredAdapter.DEFAULT_TEXT_COLOR));
        }

        private GradientDrawable createItemBgDrawable(View view, int i2) {
            GradientDrawable gradientDrawable = (GradientDrawable) view.getResources().getDrawable(R.drawable.dolphin_topic_billboard_item_bg);
            gradientDrawable.setColor(i2);
            return gradientDrawable;
        }

        void onBind(final AdvertEntity advertEntity, final int i2) {
            String str = advertEntity.imageUrl;
            if (TextUtils.isEmpty(str)) {
                str = advertEntity.pictureUrl;
            }
            CommonServiceUtil.displayImage(str, this.vImage);
            this.vText.setText(advertEntity.name);
            this.vText.setVisibility(TextUtils.isEmpty(advertEntity.name) ? 8 : 0);
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.dolphinlib.common.adapter.DolphinTopicBillboardStaggeredAdapter.ItemViewHolder.1
                {
                    ItemViewHolder.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    CommonServiceUtil.execJump(ItemViewHolder.this.itemView.getContext(), advertEntity.jump);
                    DolphinTopicBillboardStaggeredAdapter.this.mAdvMtaEntity.agid = advertEntity.groupId;
                    CommonServiceUtil.onClickMta(ItemViewHolder.this.itemView.getContext(), MtaData.Builder.from(" Babel_dev_adv_mzhtlc", DolphinTopicBillboardStaggeredAdapter.this.mAdvMtaEntity.makeAvdJson()).page(DolphinTopicBillboardStaggeredAdapter.this.scopeHolder.getPageName(), DolphinTopicBillboardStaggeredAdapter.this.scopeHolder.getPageParam()).build());
                    try {
                        PageInfo pageInfo = DolphinTopicBillboardStaggeredAdapter.this.scopeHolder.pageInfo;
                        HashMap hashMap = new HashMap();
                        EtModelMaker.makeSingleLSTM(hashMap, pageInfo.pageId, "-100", String.valueOf(i2), pageInfo.activityId, pageInfo.titleName, "-100", "3_1", advertEntity.advertId);
                        JDJSONObject jDJSONObject = new JDJSONObject();
                        jDJSONObject.put("floorId", (Object) pageInfo.activityId);
                        jDJSONObject.put("rankid", (Object) advertEntity.advertId);
                        JDMtaUtils.sendClickDataWithExt(ItemViewHolder.this.itemView.getContext(), pageInfo.pageId + "_trend_list", "", "", pageInfo.pageId, "", "", "", jDJSONObject.toJSONString(), hashMap);
                    } catch (Throwable unused) {
                    }
                }
            });
        }
    }

    private void onSendAdvExpo(AdvertGroupData advertGroupData) {
        if (advertGroupData == null || TextUtils.isEmpty(advertGroupData.groupId)) {
            return;
        }
        DolphinAdvMtaEntity dolphinAdvMtaEntity = this.mAdvMtaEntity;
        dolphinAdvMtaEntity.agid = advertGroupData.groupId;
        CommonServiceUtil.sendExposureData(this.scopeHolder, "Babel_dev_expo_adv_mzhtlc", dolphinAdvMtaEntity.makeAdvExpoJson());
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [T, com.jingdong.app.mall.bundle.dolphinlib.floor.entity.AdvertEntity] */
    private List<AdvertEntity> sortByTextSize(List<AdvertEntity> list) {
        if (list != null && !list.isEmpty()) {
            Paint paint = new Paint();
            if (this.configLayout == null) {
                paint.setTextSize(DPIUtil.dip2px(12.0f));
            } else {
                paint.setTextSize(DPIUtil.dip2px(r1.textSize));
            }
            ArrayList arrayList = new ArrayList();
            for (AdvertEntity advertEntity : list) {
                SortUtil.SortByInt sortByInt = new SortUtil.SortByInt();
                sortByInt.sortKey = (int) paint.measureText(advertEntity.name);
                sortByInt.data = advertEntity;
                arrayList.add(sortByInt);
            }
            return SortUtil.sort(arrayList);
        }
        return new ArrayList();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.dataTopics.size();
    }

    public final void onUpdateMtaData(BabelScope babelScope, DolphinTopicBillboardFloorModel dolphinTopicBillboardFloorModel) {
        this.scopeHolder = babelScope;
        ArrayList<AdvertGroupData> arrayList = new ArrayList();
        List<AdvertGroupData> list = dolphinTopicBillboardFloorModel.advertGroupData;
        if (list != null) {
            arrayList.addAll(list);
        }
        if (arrayList.isEmpty()) {
            return;
        }
        DolphinAdvMtaEntity dolphinAdvMtaEntity = this.mAdvMtaEntity;
        dolphinAdvMtaEntity.eventIdSuffix = DolphinAdvMtaEntity.EVENT_ID_MZHTLC;
        dolphinAdvMtaEntity.aid = babelScope.getPageName();
        DolphinAdvMtaEntity dolphinAdvMtaEntity2 = this.mAdvMtaEntity;
        dolphinAdvMtaEntity2.fno = dolphinTopicBillboardFloorModel.fno;
        dolphinAdvMtaEntity2.mid = dolphinTopicBillboardFloorModel.mid;
        for (AdvertGroupData advertGroupData : arrayList) {
            if (advertGroupData != null && advertGroupData.advertList != null) {
                onSendAdvExpo(advertGroupData);
            }
        }
    }

    public final void onUpdateTopicData(List<AdvertEntity> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        this.dataTopics.clear();
        this.dataTopics.addAll(list);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i2) {
        itemViewHolder.onBind(this.dataTopics.get(i2), i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new ItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_topic_billboard_item, viewGroup, false));
    }

    public final void onUpdateTopicData(DolphinTopicBillboardFloorConfig dolphinTopicBillboardFloorConfig, List<AdvertGroupData> list) {
        List<AdvertEntity> list2;
        this.configLayout = dolphinTopicBillboardFloorConfig;
        if (list == null || list.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (AdvertGroupData advertGroupData : list) {
            if (advertGroupData != null && (list2 = advertGroupData.advertList) != null) {
                for (AdvertEntity advertEntity : list2) {
                    advertEntity.groupId = advertGroupData.groupId;
                    if (!TextUtils.isEmpty(advertEntity.name) && advertEntity.name.length() > 10) {
                        advertEntity.name = advertEntity.name.substring(0, 10) + ITEM_TEXT_APPEND_CHARACTER;
                    }
                    arrayList.add(advertEntity);
                }
            }
        }
        onUpdateTopicData(arrayList);
    }

    public void onSendAdvExpo(Context context, List<Integer> list) {
        AdvertEntity advertEntity;
        if (list == null || list.isEmpty() || this.dataTopics.isEmpty()) {
            return;
        }
        PageInfo pageInfo = this.scopeHolder.pageInfo;
        JDJSONArray jDJSONArray = new JDJSONArray();
        JDJSONArray jDJSONArray2 = new JDJSONArray();
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if (intValue >= 0 && intValue < this.dataTopics.size() && (advertEntity = this.dataTopics.get(intValue)) != null) {
                jDJSONArray2.add(EtModelMaker.makeJsonLSTM(pageInfo.pageId, "-100", String.valueOf(intValue), pageInfo.activityId, pageInfo.titleName, "-100", "3_1", advertEntity.advertId));
                JDJSONObject jDJSONObject = new JDJSONObject();
                jDJSONObject.put("floorId", (Object) pageInfo.activityId);
                jDJSONObject.put("rankid", (Object) advertEntity.advertId);
                jDJSONArray.add(jDJSONObject);
            }
        }
        HashMap hashMap = new HashMap();
        EtModelMaker.makeLSTMArray(hashMap, jDJSONArray2);
        JDMtaUtils.sendExposureDataWithExt(context, pageInfo.pageId + "_trend_list_expo", "", pageInfo.pageId, "", "", jDJSONArray.toJSONString(), hashMap);
    }
}
