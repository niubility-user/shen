package com.paipai.library.inspect.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.jd.framework.json.JDJSON;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.widget.PDFlowLayout;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.paipai.library.inspect.R;
import com.paipai.library.inspect.cb.PpInspectViewActionCb;
import com.paipai.library.inspect.dataclass.PpAttrValues;
import com.paipai.library.inspect.dataclass.PpInspectInfo;
import com.paipai.library.inspect.dataclass.PpInspectSalesAttrInfo;
import com.paipai.library.inspect.dataclass.PpInspectSalesAttrs;
import com.paipai.library.inspect.dataclass.PpInspectSkuList;
import com.paipai.library.inspect.dataclass.PpInspectSkuProfileInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class PpInspectView extends FrameLayout {
    private LinearLayout attrContainer;
    private LinearLayout filterAttrContainer;
    private boolean isAttached;
    private String lastDisplayInspectSkus;
    private Map<String, String> lastSelectedInspectSalesAttrs;
    private PpInspectViewActionCb mActionCb;
    private PpInspectInfo mAllInfo;
    private LayoutInflater mInflater;
    private String mSkuTags;
    private LinearLayout reportContainer;
    private LinearLayout reportTipsContainer;
    private TextView tvChooseNum;
    private TextView tvChooseTips;
    private TextView tvReportEmptyTips;

    public PpInspectView(@NonNull Context context) {
        super(context);
        this.isAttached = false;
        setup(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b(PpInspectSalesAttrs ppInspectSalesAttrs, View view) {
        handleAttrProfileTitleClicked(ppInspectSalesAttrs);
    }

    private void changeAttrChain(List<PpInspectSalesAttrs> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            ArrayList arrayList = new ArrayList();
            PpInspectSalesAttrs ppInspectSalesAttrs = list.get(i2);
            List<PpAttrValues> attrValues = ppInspectSalesAttrs.getAttrValues();
            int i3 = 0;
            for (int i4 = 0; i4 < size; i4++) {
                if (i2 != i4) {
                    for (PpAttrValues ppAttrValues : list.get(i4).getAttrValues()) {
                        if (ppAttrValues.isSelected()) {
                            i3++;
                            if (arrayList.isEmpty()) {
                                arrayList.addAll(ppAttrValues.getInspectSkuGroupIds());
                            } else {
                                arrayList.retainAll(ppAttrValues.getInspectSkuGroupIds());
                            }
                        }
                    }
                }
            }
            for (PpAttrValues ppAttrValues2 : attrValues) {
                ArrayList arrayList2 = new ArrayList(arrayList);
                if (arrayList2.isEmpty() && i3 == 0) {
                    arrayList2.addAll(ppAttrValues2.getInspectSkuGroupIds());
                } else {
                    arrayList2.retainAll(ppAttrValues2.getInspectSkuGroupIds());
                }
                ppAttrValues2.setEnabled(!(arrayList2.isEmpty() && ppInspectSalesAttrs.isLinkage()) && ppAttrValues2.getSkuIds().contains(this.mAllInfo.getYoupinSkuId()));
            }
        }
        Iterator<PpInspectSalesAttrs> it = list.iterator();
        while (it.hasNext()) {
            List<PpAttrValues> attrValues2 = it.next().getAttrValues();
            Iterator<PpAttrValues> it2 = attrValues2.iterator();
            int i5 = 0;
            while (it2.hasNext()) {
                if (!it2.next().isEnabled()) {
                    i5++;
                }
            }
            if (i5 == attrValues2.size()) {
                it.remove();
            }
        }
    }

    private void changeTagsStatusByChain() {
        List<PpInspectSalesAttrs> inspectSaleAttrs = this.mAllInfo.getSelectableSalesAttrInfo().getInspectSaleAttrs();
        int size = inspectSaleAttrs.size();
        LinearLayout linearLayout = this.attrContainer;
        for (int i2 = 0; i2 < size; i2++) {
            View findViewById = linearLayout.getChildAt(i2).findViewById(R.id.attr_tag_container);
            if (findViewById instanceof PDFlowLayout) {
                PDFlowLayout pDFlowLayout = (PDFlowLayout) findViewById;
                List<PpAttrValues> attrValues = inspectSaleAttrs.get(i2).getAttrValues();
                int size2 = attrValues.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    PpAttrValues ppAttrValues = attrValues.get(i3);
                    TextView textView = (TextView) pDFlowLayout.getChildAt(i3);
                    textView.setEnabled(ppAttrValues.isEnabled());
                    textView.setSelected(ppAttrValues.isSelected() && ppAttrValues.isEnabled());
                    textView.setText(ppAttrValues.getAttrValueName());
                }
            }
        }
        for (int i4 = 0; i4 < size; i4++) {
            PpInspectSalesAttrs ppInspectSalesAttrs = inspectSaleAttrs.get(i4);
            List<PpAttrValues> attrValues2 = ppInspectSalesAttrs.getAttrValues();
            int i5 = 0;
            for (int i6 = 0; i6 < attrValues2.size(); i6++) {
                PpAttrValues ppAttrValues2 = attrValues2.get(i6);
                if (ppAttrValues2.isSelected() && ppAttrValues2.isEnabled()) {
                    i5++;
                }
            }
            if (i4 < linearLayout.getChildCount()) {
                TextView textView2 = (TextView) linearLayout.getChildAt(i4).findViewById(R.id.tv_attr_tag_status_tips);
                if (i5 == 0) {
                    textView2.setText(String.format("\u8bf7\u9009\u62e9%s", ppInspectSalesAttrs.getAttrName()));
                } else {
                    textView2.setText((CharSequence) null);
                }
            }
        }
    }

    private boolean checkNull(Object... objArr) {
        for (Object obj : objArr) {
            if (obj == null) {
                return true;
            }
        }
        return false;
    }

    private String currentDisplayInspectSkus() {
        List<PpInspectSkuProfileInfo> inspectSkuList;
        PpInspectSkuList queryInspectSkuList = this.mAllInfo.getQueryInspectSkuList();
        if (queryInspectSkuList == null || (inspectSkuList = queryInspectSkuList.getInspectSkuList()) == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Iterator<PpInspectSkuProfileInfo> it = inspectSkuList.iterator();
        while (it.hasNext()) {
            sb.append(it.next().getInspectSkuId());
            sb.append("-");
        }
        return sb.toString();
    }

    private boolean dataVerified(PpInspectInfo ppInspectInfo) {
        List<PpInspectSalesAttrs> inspectSaleAttrs;
        if (checkNull(ppInspectInfo, ppInspectInfo.getYoupinSkuId(), ppInspectInfo.getInspectSkuId())) {
            return false;
        }
        PpInspectSalesAttrInfo selectableSalesAttrInfo = ppInspectInfo.getSelectableSalesAttrInfo();
        return (checkNull(selectableSalesAttrInfo) || (inspectSaleAttrs = selectableSalesAttrInfo.getInspectSaleAttrs()) == null || inspectSaleAttrs.isEmpty()) ? false : true;
    }

    private void exposure(String str, String str2) {
        Context context = getContext();
        if (checkNull(context)) {
            return;
        }
        JDMtaUtils.sendExposureDataWithExt(context, str, "", RecommendMtaUtils.Productdetail_MainPage, "\u5546\u54c1\u8be6\u60c5\u9875", this.mAllInfo.getYoupinSkuId(), str2, "", "", this.mSkuTags, null);
    }

    private Pair<Boolean, PpInspectInfo> formatPpInspectInfo(String str, String str2) {
        List<PpInspectSkuProfileInfo> inspectSkuList;
        try {
            if (checkNull(str)) {
                return null;
            }
            Gson create = new GsonBuilder().create();
            PpInspectInfo ppInspectInfo = (PpInspectInfo) create.fromJson(str, PpInspectInfo.class);
            if (checkNull(ppInspectInfo)) {
                return null;
            }
            if (TextUtils.isEmpty(str2)) {
                return new Pair<>(Boolean.FALSE, ppInspectInfo);
            }
            PpInspectInfo ppInspectInfo2 = (PpInspectInfo) create.fromJson(str2, PpInspectInfo.class);
            if (checkNull(ppInspectInfo2)) {
                return new Pair<>(Boolean.FALSE, ppInspectInfo);
            }
            String ppSaleAttrId = ppInspectInfo.getPpSaleAttrId();
            Object ppSaleAttrId2 = ppInspectInfo2.getPpSaleAttrId();
            if (checkNull(ppSaleAttrId, ppSaleAttrId2)) {
                return null;
            }
            if (ppSaleAttrId.equals(ppSaleAttrId2)) {
                String inspectSkuId = ppInspectInfo.getInspectSkuId();
                if (checkNull(inspectSkuId)) {
                    return null;
                }
                int status = ppInspectInfo.getStatus();
                String price = ppInspectInfo.getPrice();
                PpInspectSkuList queryInspectSkuList = ppInspectInfo2.getQueryInspectSkuList();
                if (queryInspectSkuList != null && (inspectSkuList = queryInspectSkuList.getInspectSkuList()) != null) {
                    Iterator<PpInspectSkuProfileInfo> it = inspectSkuList.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        PpInspectSkuProfileInfo next = it.next();
                        if (inspectSkuId.equals(next.getInspectSkuId())) {
                            next.setStatus(status);
                            next.setPrice(price);
                            break;
                        }
                    }
                }
                ppInspectInfo.setQueryInspectSkuList(queryInspectSkuList);
                return new Pair<>(Boolean.TRUE, ppInspectInfo);
            }
            return new Pair<>(Boolean.FALSE, ppInspectInfo);
        } catch (JsonSyntaxException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void h(PpInspectSkuProfileInfo ppInspectSkuProfileInfo, List list, boolean z, View view) {
        handleOnReportClicked(ppInspectSkuProfileInfo, list, z);
    }

    private String getExposureData() {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            JSONArray jSONArray2 = new JSONArray();
            PpInspectSalesAttrInfo selectableSalesAttrInfo = this.mAllInfo.getSelectableSalesAttrInfo();
            List<PpInspectSalesAttrs> inspectSaleAttrs = selectableSalesAttrInfo.getInspectSaleAttrs();
            if (inspectSaleAttrs == null) {
                inspectSaleAttrs = Collections.emptyList();
            }
            for (PpInspectSalesAttrs ppInspectSalesAttrs : inspectSaleAttrs) {
                Iterator<PpAttrValues> it = ppInspectSalesAttrs.getAttrValues().iterator();
                while (true) {
                    if (it.hasNext()) {
                        PpAttrValues next = it.next();
                        if (next.isSelected()) {
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("attrId", ppInspectSalesAttrs.getAttrId());
                            jSONObject2.put("attrName", ppInspectSalesAttrs.getAttrName());
                            jSONObject2.put("attrValueId", next.getAttrValueId());
                            jSONObject2.put("attrValueName", next.getAttrValueName());
                            jSONArray.put(jSONObject2);
                            break;
                        }
                    }
                }
            }
            List<PpInspectSalesAttrs> filterAttrs = selectableSalesAttrInfo.getFilterAttrs();
            if (filterAttrs == null) {
                filterAttrs = Collections.emptyList();
            }
            for (PpInspectSalesAttrs ppInspectSalesAttrs2 : filterAttrs) {
                Iterator<PpAttrValues> it2 = ppInspectSalesAttrs2.getAttrValues().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        PpAttrValues next2 = it2.next();
                        if (next2.isSelected()) {
                            JSONObject jSONObject3 = new JSONObject();
                            jSONObject3.put("attrId", ppInspectSalesAttrs2.getAttrId());
                            jSONObject3.put("attrName", ppInspectSalesAttrs2.getAttrName());
                            jSONObject3.put("attrValueId", next2.getAttrValueId());
                            jSONObject3.put("attrValueName", next2.getAttrValueName());
                            jSONArray2.put(jSONObject3);
                            break;
                        }
                    }
                }
            }
            List<PpInspectSkuProfileInfo> inspectSkuList = this.mAllInfo.getQueryInspectSkuList().getInspectSkuList();
            if (inspectSkuList == null) {
                inspectSkuList = Collections.emptyList();
            }
            JSONArray jSONArray3 = new JSONArray();
            for (PpInspectSkuProfileInfo ppInspectSkuProfileInfo : inspectSkuList) {
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put("youpinSkuId", ppInspectSkuProfileInfo.getYoupinSkuId());
                jSONObject4.put("inspectSkuId", ppInspectSkuProfileInfo.getInspectSkuId());
                jSONArray3.put(jSONObject4);
            }
            JSONObject jSONObject5 = new JSONObject();
            jSONObject5.put("inspectSaleAttrs", jSONArray);
            jSONObject5.put("filterAttrs", jSONArray2);
            jSONObject.put("selectableSalesAttrInfo", jSONObject5);
            jSONObject.put("inspectSkuList", jSONArray3);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return JSONObject.NULL.toString();
        }
    }

    private int getTvColor(int i2) {
        return ContextCompat.getColor(getContext(), i2);
    }

    private ColorStateList getTvColorStateList(int i2) {
        return ContextCompat.getColorStateList(getContext(), i2);
    }

    private void handleAttrProfileTitleClicked(PpInspectSalesAttrs ppInspectSalesAttrs) {
        PpInspectViewActionCb ppInspectViewActionCb;
        String attrProfileUrl = ppInspectSalesAttrs.getAttrProfileUrl();
        if (TextUtils.isEmpty(attrProfileUrl) || (ppInspectViewActionCb = this.mActionCb) == null) {
            return;
        }
        ppInspectViewActionCb.onSalesAttrTipsClicked(attrProfileUrl);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: handleOnAttrTagClicked  reason: merged with bridge method [inline-methods] */
    public void d(View view, PDFlowLayout pDFlowLayout, View view2, PpInspectSalesAttrs ppInspectSalesAttrs, PpAttrValues ppAttrValues, List<PpAttrValues> list, List<PpInspectSalesAttrs> list2) {
        TextView textView = (TextView) view2.findViewById(R.id.tv_attr_tag_status_tips);
        if (ppAttrValues.isSelected()) {
            ppAttrValues.setSelected(false);
            view.setSelected(false);
            textView.setText(String.format("\u8bf7\u9009\u62e9%s", ppInspectSalesAttrs.getAttrName()));
            changeAttrChain(list2);
            changeTagsStatusByChain();
            return;
        }
        textView.setText((CharSequence) null);
        for (int i2 = 0; i2 < list.size(); i2++) {
            PpAttrValues ppAttrValues2 = list.get(i2);
            if (ppAttrValues2 == ppAttrValues) {
                ppAttrValues2.setSelected(true);
            } else {
                ppAttrValues2.setSelected(false);
            }
            pDFlowLayout.getChildAt(i2).setSelected(ppAttrValues2.isSelected());
        }
        changeAttrChain(list2);
        changeTagsStatusByChain();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        for (PpInspectSalesAttrs ppInspectSalesAttrs2 : list2) {
            String attrId = ppInspectSalesAttrs2.getAttrId();
            for (PpAttrValues ppAttrValues3 : ppInspectSalesAttrs2.getAttrValues()) {
                if (ppAttrValues3.isSelected() && ppAttrValues3.isEnabled()) {
                    hashMap2.put(attrId, ppAttrValues3.getAttrValueId());
                }
            }
        }
        if (hashMap2.size() != list2.size()) {
            return;
        }
        this.lastSelectedInspectSalesAttrs = hashMap2;
        hashMap.put("selectedInspectSalesAttrs", hashMap2);
        List<PpInspectSkuProfileInfo> inspectSkuList = this.mAllInfo.getQueryInspectSkuList().getInspectSkuList();
        if (!checkNull(inspectSkuList)) {
            Iterator<PpInspectSkuProfileInfo> it = inspectSkuList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                PpInspectSkuProfileInfo next = it.next();
                if (next.isSelected()) {
                    hashMap.put("lastInspectSkuId", next.getInspectSkuId());
                    break;
                }
            }
        }
        PpInspectViewActionCb ppInspectViewActionCb = this.mActionCb;
        if (ppInspectViewActionCb == null) {
            return;
        }
        String object2JsonString = object2JsonString(hashMap);
        if (TextUtils.isEmpty(object2JsonString)) {
            return;
        }
        ppInspectViewActionCb.onAttrTagSelected(object2JsonString);
        log(String.format("\u8d28\u68c0\u5c5e\u6027\u6807\u7b7e\u70b9\u51fb%s", object2JsonString));
        stat("Productdetail_SpecSituation", object2JsonString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: handleOnFilterAttrTagClicked  reason: merged with bridge method [inline-methods] */
    public void f(View view, PDFlowLayout pDFlowLayout, View view2, PpInspectSalesAttrs ppInspectSalesAttrs, PpAttrValues ppAttrValues, List<PpAttrValues> list, List<PpInspectSalesAttrs> list2) {
        TextView textView = (TextView) view2.findViewById(R.id.tv_attr_tag_status_tips);
        if (ppAttrValues.isSelected()) {
            return;
        }
        textView.setText((CharSequence) null);
        for (int i2 = 0; i2 < list.size(); i2++) {
            PpAttrValues ppAttrValues2 = list.get(i2);
            if (ppAttrValues2 == ppAttrValues) {
                ppAttrValues2.setSelected(true);
            } else {
                ppAttrValues2.setSelected(false);
            }
            pDFlowLayout.getChildAt(i2).setSelected(ppAttrValues2.isSelected());
        }
        HashMap hashMap = new HashMap();
        hashMap.put("selectedInspectSalesAttrs", this.lastSelectedInspectSalesAttrs);
        HashMap hashMap2 = new HashMap();
        for (PpInspectSalesAttrs ppInspectSalesAttrs2 : list2) {
            String attrId = ppInspectSalesAttrs2.getAttrId();
            for (PpAttrValues ppAttrValues3 : ppInspectSalesAttrs2.getAttrValues()) {
                if (ppAttrValues3.isSelected()) {
                    hashMap2.put(attrId, ppAttrValues3.getAttrValueId());
                }
            }
        }
        hashMap.put("selectedFilterAttrs", hashMap2);
        List<PpInspectSkuProfileInfo> inspectSkuList = this.mAllInfo.getQueryInspectSkuList().getInspectSkuList();
        if (!checkNull(inspectSkuList)) {
            Iterator<PpInspectSkuProfileInfo> it = inspectSkuList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                PpInspectSkuProfileInfo next = it.next();
                if (next.isSelected()) {
                    hashMap.put("lastInspectSkuId", next.getInspectSkuId());
                    break;
                }
            }
        }
        PpInspectViewActionCb ppInspectViewActionCb = this.mActionCb;
        if (ppInspectViewActionCb == null) {
            return;
        }
        String object2JsonString = object2JsonString(hashMap);
        if (TextUtils.isEmpty(object2JsonString)) {
            return;
        }
        ppInspectViewActionCb.onAttrTagSelected(object2JsonString);
        log(String.format("\u68c0\u6d4b\u5c5e\u6027\u6807\u7b7e\u70b9\u51fb%s", object2JsonString));
        stat("Productdetail_SpecQualityInspection", object2JsonString);
    }

    private void handleOnReportActionClicked(PpInspectSkuProfileInfo ppInspectSkuProfileInfo) {
        PpInspectViewActionCb ppInspectViewActionCb = this.mActionCb;
        if (ppInspectViewActionCb == null) {
            return;
        }
        String reportLink = ppInspectSkuProfileInfo.getReportLink();
        if (TextUtils.isEmpty(reportLink)) {
            return;
        }
        ppInspectViewActionCb.onInspectSkuReportActionClicked(reportLink);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("inspectSkuId", ppInspectSkuProfileInfo.getInspectSkuId());
            stat("Productdetail_SpecReport", jSONObject.toString());
        } catch (JSONException unused) {
        }
    }

    private void handleOnReportClicked(PpInspectSkuProfileInfo ppInspectSkuProfileInfo, List<PpInspectSkuProfileInfo> list, boolean z) {
        if (ppInspectSkuProfileInfo.isSelected()) {
            return;
        }
        int i2 = 0;
        while (true) {
            boolean z2 = true;
            if (i2 >= list.size()) {
                break;
            }
            PpInspectSkuProfileInfo ppInspectSkuProfileInfo2 = list.get(i2);
            if (ppInspectSkuProfileInfo2 != ppInspectSkuProfileInfo) {
                z2 = false;
            }
            ppInspectSkuProfileInfo2.setSelected(z2);
            setupLayoutReportItemStyle(this.reportContainer.getChildAt(i2), ppInspectSkuProfileInfo2, z);
            i2++;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("inspectSkuId", ppInspectSkuProfileInfo.getInspectSkuId());
        PpInspectSalesAttrInfo selectableSalesAttrInfo = this.mAllInfo.getSelectableSalesAttrInfo();
        if (selectableSalesAttrInfo != null && selectableSalesAttrInfo.getFilterAttrs() != null) {
            List<PpInspectSalesAttrs> filterAttrs = selectableSalesAttrInfo.getFilterAttrs();
            HashMap hashMap2 = new HashMap();
            for (PpInspectSalesAttrs ppInspectSalesAttrs : filterAttrs) {
                String attrId = ppInspectSalesAttrs.getAttrId();
                for (PpAttrValues ppAttrValues : ppInspectSalesAttrs.getAttrValues()) {
                    if (ppAttrValues.isSelected()) {
                        hashMap2.put(attrId, ppAttrValues.getAttrValueId());
                    }
                }
            }
            hashMap.put("selectedFilterAttrs", hashMap2);
        }
        PpInspectViewActionCb ppInspectViewActionCb = this.mActionCb;
        if (ppInspectViewActionCb == null) {
            return;
        }
        String object2JsonString = object2JsonString(hashMap);
        if (TextUtils.isEmpty(object2JsonString)) {
            return;
        }
        ppInspectViewActionCb.onInspectSkuSelected(object2JsonString);
        log(String.format("\u8d28\u68c0\u62a5\u544a\u70b9\u51fb%s", object2JsonString));
        stat("Productdetail_SpecZJProduct", object2JsonString);
    }

    private void hideReportEmptyTipsView() {
        this.tvReportEmptyTips.setVisibility(8);
    }

    private void hideReppreView() {
        this.reportTipsContainer.setVisibility(8);
        this.reportContainer.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void j(PpInspectSkuProfileInfo ppInspectSkuProfileInfo, View view) {
        handleOnReportActionClicked(ppInspectSkuProfileInfo);
    }

    private void log(String str) {
    }

    private String object2JsonString(Object obj) {
        try {
            Object json = JDJSON.toJSON(obj);
            if (json == null) {
                return null;
            }
            return json.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    private int parseColor(String str) {
        return Color.parseColor(str);
    }

    private void resetViews() {
        this.attrContainer.removeAllViews();
        this.filterAttrContainer.removeAllViews();
        this.reportContainer.removeAllViews();
        this.tvChooseNum.setText((CharSequence) null);
        this.tvChooseTips.setText((CharSequence) null);
        this.tvReportEmptyTips.setText((CharSequence) null);
    }

    private void setup(Context context) {
        LayoutInflater from = LayoutInflater.from(context);
        this.mInflater = from;
        from.inflate(R.layout.layout_inspect_view_pp, (ViewGroup) this, true);
    }

    private void setupAttrViews(boolean z) {
        boolean z2 = z;
        final List<PpInspectSalesAttrs> inspectSaleAttrs = this.mAllInfo.getSelectableSalesAttrInfo().getInspectSaleAttrs();
        LayoutInflater layoutInflater = this.mInflater;
        Iterator<PpInspectSalesAttrs> it = inspectSaleAttrs.iterator();
        while (it.hasNext()) {
            final PpInspectSalesAttrs next = it.next();
            boolean z3 = false;
            final View inflate = layoutInflater.inflate(R.layout.layout_attr_item_pp, (ViewGroup) this.attrContainer, false);
            setupLayoutAttrItemStyle(inflate, z2);
            TextView textView = (TextView) inflate.findViewById(R.id.tv_attrProfileTitle);
            if (!TextUtils.isEmpty(next.getAttrProfileTitle()) && !TextUtils.isEmpty(next.getAttrProfileUrl())) {
                textView.setText(next.getAttrProfileTitle());
                textView.setVisibility(0);
                textView.setOnClickListener(new View.OnClickListener() { // from class: com.paipai.library.inspect.view.c
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        PpInspectView.this.b(next, view);
                    }
                });
            } else {
                textView.setText((CharSequence) null);
                textView.setVisibility(8);
                textView.setOnClickListener(null);
            }
            ((TextView) inflate.findViewById(R.id.tv_attr_title)).setText(next.getAttrName());
            PDFlowLayout pDFlowLayout = (PDFlowLayout) inflate.findViewById(R.id.attr_tag_container);
            final List<PpAttrValues> attrValues = next.getAttrValues();
            if (attrValues == null || attrValues.isEmpty()) {
                return;
            }
            for (final PpAttrValues ppAttrValues : attrValues) {
                View inflate2 = layoutInflater.inflate(R.layout.layout_attr_tag_pp, pDFlowLayout, z3);
                setupLayoutAttrTagStyle(inflate2, z2);
                final PDFlowLayout pDFlowLayout2 = pDFlowLayout;
                Iterator<PpInspectSalesAttrs> it2 = it;
                PDFlowLayout pDFlowLayout3 = pDFlowLayout;
                ((TextView) inflate2.findViewById(R.id.tv_attr_tag)).setOnClickListener(new View.OnClickListener() { // from class: com.paipai.library.inspect.view.b
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        PpInspectView.this.d(pDFlowLayout2, inflate, next, ppAttrValues, attrValues, inspectSaleAttrs, view);
                    }
                });
                pDFlowLayout3.addView(inflate2);
                z2 = z;
                pDFlowLayout = pDFlowLayout3;
                layoutInflater = layoutInflater;
                it = it2;
                z3 = false;
            }
            this.attrContainer.addView(inflate);
            z2 = z;
        }
        changeTagsStatusByChain();
    }

    private void setupFilterAttrViews(boolean z) {
        final List<PpInspectSalesAttrs> filterAttrs;
        boolean z2 = z;
        PpInspectSalesAttrInfo selectableSalesAttrInfo = this.mAllInfo.getSelectableSalesAttrInfo();
        if (selectableSalesAttrInfo == null || (filterAttrs = selectableSalesAttrInfo.getFilterAttrs()) == null || filterAttrs.size() == 0) {
            return;
        }
        LayoutInflater layoutInflater = this.mInflater;
        Iterator<PpInspectSalesAttrs> it = filterAttrs.iterator();
        while (it.hasNext()) {
            final PpInspectSalesAttrs next = it.next();
            boolean z3 = false;
            final View inflate = layoutInflater.inflate(R.layout.layout_attr_item_pp, (ViewGroup) this.filterAttrContainer, false);
            setupLayoutAttrItemStyle(inflate, z2);
            TextView textView = (TextView) inflate.findViewById(R.id.tv_attrProfileTitle);
            if (!TextUtils.isEmpty(next.getAttrProfileTitle()) && !TextUtils.isEmpty(next.getAttrProfileUrl())) {
                textView.setText(next.getAttrProfileTitle());
                textView.setVisibility(0);
            } else {
                textView.setText((CharSequence) null);
                textView.setVisibility(8);
            }
            textView.setOnClickListener(null);
            ((TextView) inflate.findViewById(R.id.tv_attr_title)).setText(next.getAttrName());
            PDFlowLayout pDFlowLayout = (PDFlowLayout) inflate.findViewById(R.id.attr_tag_container);
            final List<PpAttrValues> attrValues = next.getAttrValues();
            if (attrValues == null || attrValues.isEmpty()) {
                return;
            }
            for (final PpAttrValues ppAttrValues : attrValues) {
                View inflate2 = layoutInflater.inflate(R.layout.layout_attr_tag_pp, pDFlowLayout, z3);
                setupLayoutAttrTagStyle(inflate2, z2);
                TextView textView2 = (TextView) inflate2.findViewById(R.id.tv_attr_tag);
                textView2.setText(ppAttrValues.getAttrValueName());
                textView2.setSelected(true);
                textView2.setSelected(ppAttrValues.isSelected());
                final PDFlowLayout pDFlowLayout2 = pDFlowLayout;
                Iterator<PpInspectSalesAttrs> it2 = it;
                PDFlowLayout pDFlowLayout3 = pDFlowLayout;
                textView2.setOnClickListener(new View.OnClickListener() { // from class: com.paipai.library.inspect.view.a
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        PpInspectView.this.f(pDFlowLayout2, inflate, next, ppAttrValues, attrValues, filterAttrs, view);
                    }
                });
                pDFlowLayout3.addView(inflate2);
                z2 = z;
                pDFlowLayout = pDFlowLayout3;
                layoutInflater = layoutInflater;
                it = it2;
                z3 = false;
            }
            this.filterAttrContainer.addView(inflate);
            z2 = z;
        }
    }

    private void setupLastSalesAttrs() {
        List<PpInspectSalesAttrs> inspectSaleAttrs = this.mAllInfo.getSelectableSalesAttrInfo().getInspectSaleAttrs();
        HashMap hashMap = new HashMap();
        for (PpInspectSalesAttrs ppInspectSalesAttrs : inspectSaleAttrs) {
            String attrId = ppInspectSalesAttrs.getAttrId();
            for (PpAttrValues ppAttrValues : ppInspectSalesAttrs.getAttrValues()) {
                if (ppAttrValues.isSelected() && ppAttrValues.isEnabled()) {
                    hashMap.put(attrId, ppAttrValues.getAttrValueId());
                }
            }
        }
        if (hashMap.size() != inspectSaleAttrs.size()) {
            return;
        }
        this.lastSelectedInspectSalesAttrs = hashMap;
    }

    private void setupLayoutAttrItemStyle(View view, boolean z) {
        TextView textView = (TextView) view.findViewById(R.id.tv_attr_title);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_attrProfileTitle);
        if (z) {
            textView.setTextColor(getTvColor(R.color.pp_inspect_ececec));
            textView2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.pp_inspect_explain_dark, 0);
            return;
        }
        textView.setTextColor(getTvColor(R.color.pp_inspect_262626));
        textView2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.pp_inspect_explain, 0);
    }

    private void setupLayoutAttrTagStyle(View view, boolean z) {
        TextView textView = (TextView) view.findViewById(R.id.tv_attr_tag);
        if (z) {
            textView.setBackgroundResource(R.drawable.bg_attr_tag_selector_dark_pp);
            textView.setTextColor(getTvColorStateList(R.color.text_color_attr_tag_selector_dark_pp));
            return;
        }
        textView.setBackgroundResource(R.drawable.bg_attr_tag_selector_pp);
        textView.setTextColor(getTvColorStateList(R.color.text_color_attr_tag_selector_pp));
    }

    private void setupLayoutReportItemStyle(View view, PpInspectSkuProfileInfo ppInspectSkuProfileInfo, boolean z) {
        boolean isSelected = ppInspectSkuProfileInfo.isSelected();
        int status = ppInspectSkuProfileInfo.getStatus();
        View findViewById = view.findViewById(R.id.report_item_root);
        TextView textView = (TextView) view.findViewById(R.id.tv_report_action);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_tested_tips);
        TextView textView3 = (TextView) view.findViewById(R.id.tv_price);
        View findViewById2 = view.findViewById(R.id.v_photo_cover);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll_report_tags);
        if (isSelected) {
            textView.setTypeface(Typeface.DEFAULT_BOLD);
        } else {
            textView.setTypeface(Typeface.DEFAULT);
        }
        int i2 = 0;
        if (z) {
            findViewById2.setVisibility(0);
            if (isSelected) {
                if (status == 1) {
                    findViewById.setBackgroundResource(R.drawable.bg_report_selected_dark_pp);
                    textView.setBackgroundResource(R.drawable.bg_report_action_selected_dark_pp);
                    textView.setTextColor(Color.parseColor("#FFFFFF"));
                    textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_report_arrow_red_dark_pp, 0);
                    textView2.setTextColor(parseColor(JDDarkUtil.COLOR_ECECEC));
                    textView3.setTextColor(parseColor("#DE3E26"));
                } else {
                    findViewById.setBackgroundResource(R.drawable.bg_report_selected_dark_pp);
                    textView.setBackgroundResource(R.drawable.bg_report_action_selected_dark_pp);
                    textView.setTextColor(Color.parseColor("#FFFFFF"));
                    textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_report_arrow_red_dark_pp, 0);
                    textView2.setTextColor(parseColor(JDDarkUtil.COLOR_ECECEC));
                    textView3.setTextColor(parseColor("#DE3E26"));
                }
                while (i2 < linearLayout.getChildCount()) {
                    TextView textView4 = (TextView) linearLayout.getChildAt(i2);
                    textView4.setBackgroundResource(R.drawable.bg_report_tags_dark_enable_pp);
                    textView4.setTextColor(parseColor("#DE3E26"));
                    i2++;
                }
                return;
            } else if (status == 1) {
                findViewById.setBackgroundResource(R.drawable.bg_report_normal_dark_pp);
                textView.setBackgroundResource(R.drawable.bg_report_action_selected_dark_pp);
                textView.setTextColor(Color.parseColor("#FFFFFF"));
                textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_report_arrow_red_dark_pp, 0);
                textView2.setTextColor(getTvColor(R.color.pp_inspect_ececec));
                textView3.setTextColor(parseColor("#DE3E26"));
                while (i2 < linearLayout.getChildCount()) {
                    TextView textView5 = (TextView) linearLayout.getChildAt(i2);
                    textView5.setBackgroundResource(R.drawable.bg_report_tags_dark_enable_pp);
                    textView5.setTextColor(parseColor("#DE3E26"));
                    i2++;
                }
                return;
            } else {
                findViewById.setBackgroundResource(R.drawable.bg_report_disabled_dark_pp);
                textView.setBackgroundResource(R.drawable.bg_report_action_normal_dark_pp);
                textView.setTextColor(Color.parseColor("#8E8D8D"));
                textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_report_arrow_grey_dark_pp, 0);
                textView2.setTextColor(Color.parseColor("#80ececec"));
                textView3.setTextColor(Color.parseColor("#80ececec"));
                while (i2 < linearLayout.getChildCount()) {
                    TextView textView6 = (TextView) linearLayout.getChildAt(i2);
                    textView6.setBackgroundResource(R.drawable.bg_report_tags_dark_disable_pp);
                    textView6.setTextColor(parseColor("#86362A"));
                    i2++;
                }
                return;
            }
        }
        findViewById2.setVisibility(8);
        if (isSelected) {
            if (status == 1) {
                findViewById.setBackgroundResource(R.drawable.bg_report_selected_pp);
                textView.setBackgroundResource(R.drawable.bg_report_action_selected_pp);
                textView.setTextColor(Color.parseColor("#FFFFFF"));
                textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_report_arrow_red_pp, 0);
                textView2.setTextColor(Color.parseColor(JDDarkUtil.COLOR_1A1A1A));
                textView3.setTextColor(parseColor("#FFFA2C19"));
            } else {
                findViewById.setBackgroundResource(R.drawable.bg_report_selected_pp);
                textView.setBackgroundResource(R.drawable.bg_report_action_selected_pp);
                textView.setTextColor(Color.parseColor("#FFFFFF"));
                textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_report_arrow_red_pp, 0);
                textView2.setTextColor(Color.parseColor(JDDarkUtil.COLOR_1A1A1A));
                textView3.setTextColor(parseColor("#FFFA2C19"));
            }
            while (i2 < linearLayout.getChildCount()) {
                TextView textView7 = (TextView) linearLayout.getChildAt(i2);
                textView7.setBackgroundResource(R.drawable.bg_report_tags_normal_enable_pp);
                textView7.setTextColor(parseColor("#F2270C"));
                i2++;
            }
        } else if (status == 1) {
            findViewById.setBackgroundResource(R.drawable.bg_report_normal_pp);
            textView.setBackgroundResource(R.drawable.bg_report_action_selected_pp);
            textView.setTextColor(Color.parseColor("#FFFFFF"));
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_report_arrow_red_pp, 0);
            textView2.setTextColor(Color.parseColor(JDDarkUtil.COLOR_1A1A1A));
            textView3.setTextColor(parseColor("#FFFA2C19"));
            while (i2 < linearLayout.getChildCount()) {
                TextView textView8 = (TextView) linearLayout.getChildAt(i2);
                textView8.setBackgroundResource(R.drawable.bg_report_tags_normal_enable_pp);
                textView8.setTextColor(parseColor(JDDarkUtil.COLOR_FF3826));
                i2++;
            }
        } else {
            findViewById.setBackgroundResource(R.drawable.bg_report_disabled_pp);
            textView.setBackgroundResource(R.drawable.bg_report_action_normal_pp);
            textView.setTextColor(Color.parseColor("#FFFFFF"));
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_report_arrow_grey_pp, 0);
            textView2.setTextColor(Color.parseColor("#FFCCCCCC"));
            textView3.setTextColor(parseColor("#FFCCCCCC"));
            while (i2 < linearLayout.getChildCount()) {
                TextView textView9 = (TextView) linearLayout.getChildAt(i2);
                textView9.setBackgroundResource(R.drawable.bg_report_tags_normal_disable_pp);
                textView9.setTextColor(parseColor("#F38E80"));
                i2++;
            }
        }
    }

    private void setupLayoutReportTipsStyle(boolean z) {
        int tvColor = getTvColor(R.color.pp_inspect_262626);
        int tvColor2 = getTvColor(R.color.pp_inspect_ececec);
        if (z) {
            this.tvChooseNum.setTextColor(tvColor2);
            this.tvChooseTips.setTextColor(tvColor2);
            return;
        }
        this.tvChooseNum.setTextColor(tvColor);
        this.tvChooseTips.setTextColor(Color.parseColor(JDDarkUtil.COLOR_808080));
    }

    private void setupOtherLayoutStyle(boolean z) {
        if (z) {
            this.tvReportEmptyTips.setTextColor(getTvColor(R.color.pp_inspect_ececec));
        } else {
            this.tvReportEmptyTips.setTextColor(getTvColor(R.color.pp_inspect_262626));
        }
    }

    private void setupReportViews(final boolean z) {
        int i2;
        PpInspectSkuList queryInspectSkuList = this.mAllInfo.getQueryInspectSkuList();
        final List<PpInspectSkuProfileInfo> inspectSkuList = queryInspectSkuList.getInspectSkuList();
        boolean z2 = false;
        int i3 = 1;
        if (!checkNull(queryInspectSkuList, inspectSkuList) && !inspectSkuList.isEmpty()) {
            hideReportEmptyTipsView();
            showReportView();
            setupLayoutReportTipsStyle(z);
            this.tvChooseNum.setText(queryInspectSkuList.getTitle());
            this.tvChooseTips.setText(queryInspectSkuList.getSummaryTips());
            LayoutInflater layoutInflater = this.mInflater;
            int size = inspectSkuList.size();
            int dip2px = DpiUtil.dip2px(getContext(), 3.0f);
            int dip2px2 = DpiUtil.dip2px(getContext(), 1.0f);
            int i4 = 0;
            while (i4 < size) {
                final PpInspectSkuProfileInfo ppInspectSkuProfileInfo = inspectSkuList.get(i4);
                View inflate = layoutInflater.inflate(R.layout.layout_report_item_pp, this.reportContainer, z2);
                List<String> tags = ppInspectSkuProfileInfo.getTags();
                if (tags != null && tags.size() > 0) {
                    int size2 = tags.size();
                    LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.ll_report_tags);
                    int i5 = 0;
                    while (i5 < size2 && i5 <= i3) {
                        TextView textView = new TextView(getContext());
                        textView.setGravity(16);
                        textView.setPadding(dip2px, 0, dip2px, dip2px2);
                        LayoutInflater layoutInflater2 = layoutInflater;
                        textView.setTextSize(2, 9.0f);
                        textView.setText(tags.get(i5));
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                        if (i5 > 0) {
                            i2 = size;
                            layoutParams.leftMargin = DpiUtil.dip2px(getContext(), 4.0f);
                        } else {
                            i2 = size;
                        }
                        linearLayout.addView(textView, layoutParams);
                        i5++;
                        layoutInflater = layoutInflater2;
                        size = i2;
                        i3 = 1;
                    }
                }
                LayoutInflater layoutInflater3 = layoutInflater;
                int i6 = size;
                this.reportContainer.addView(inflate);
                inflate.setOnClickListener(new View.OnClickListener() { // from class: com.paipai.library.inspect.view.d
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        PpInspectView.this.h(ppInspectSkuProfileInfo, inspectSkuList, z, view);
                    }
                });
                View findViewById = inflate.findViewById(R.id.iv_sell_out);
                TextView textView2 = (TextView) inflate.findViewById(R.id.tv_price);
                TextView textView3 = (TextView) inflate.findViewById(R.id.tv_tested_tips);
                TextView textView4 = (TextView) inflate.findViewById(R.id.tv_report_action);
                if (ppInspectSkuProfileInfo.getStatus() != 1) {
                    findViewById.setVisibility(0);
                }
                ppInspectSkuProfileInfo.setSelected(this.mAllInfo.getInspectSkuId().equals(ppInspectSkuProfileInfo.getInspectSkuId()));
                List<String> imageList = ppInspectSkuProfileInfo.getImageList();
                if (imageList != null && !imageList.isEmpty()) {
                    String a = com.paipai.library.inspect.utility.b.a(imageList.get(0));
                    JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
                    createSimple.setPlaceholder(19);
                    JDImageUtils.displayImage(a, (SimpleDraweeView) inflate.findViewById(R.id.iv_photo), createSimple);
                }
                String format = String.format("\u00a5%s", ppInspectSkuProfileInfo.getPrice());
                SpannableString spannableString = new SpannableString(format);
                List<PpInspectSkuProfileInfo> list = inspectSkuList;
                spannableString.setSpan(new AbsoluteSizeSpan(10, true), 0, 1, 17);
                int indexOf = format.indexOf(OrderISVUtil.MONEY_DECIMAL);
                if (indexOf > 0) {
                    spannableString.setSpan(new AbsoluteSizeSpan(12, true), indexOf, format.length(), 17);
                }
                FontsUtil.changeTextFont(textView2, 4099);
                textView2.setText(spannableString);
                textView3.setText(ppInspectSkuProfileInfo.getInspectResult());
                textView4.setOnClickListener(new View.OnClickListener() { // from class: com.paipai.library.inspect.view.e
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        PpInspectView.this.j(ppInspectSkuProfileInfo, view);
                    }
                });
                setupLayoutReportItemStyle(inflate, ppInspectSkuProfileInfo, z);
                i4++;
                inspectSkuList = list;
                layoutInflater = layoutInflater3;
                size = i6;
                z2 = false;
                i3 = 1;
            }
            return;
        }
        hideReppreView();
        showReportEmptyTipsView();
    }

    private void setupViews(boolean z) {
        setupAttrViews(z);
        setupFilterAttrViews(z);
        setupReportViews(z);
        setupOtherLayoutStyle(z);
    }

    private void showReportEmptyTipsView() {
        this.tvReportEmptyTips.setVisibility(0);
    }

    private void showReportView() {
        this.reportTipsContainer.setVisibility(0);
        this.reportContainer.setVisibility(0);
    }

    private void stat(String str, String str2) {
        Context context = getContext();
        if (checkNull(context)) {
            return;
        }
        JDMtaUtils.sendClickDataWithExt(context, str, "", "onClick", RecommendMtaUtils.Productdetail_MainPage, "\u5546\u54c1\u8be6\u60c5\u9875", this.mAllInfo.getYoupinSkuId(), "", str2, "", "", "", this.mSkuTags, null);
    }

    private void viewEnable(View... viewArr) {
        for (View view : viewArr) {
            view.setEnabled(true);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.isAttached = true;
        if (this.mAllInfo == null) {
            return;
        }
        exposure("Productdetail_SpecReportExpo", getExposureData());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.isAttached = false;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.tvChooseNum = (TextView) findViewById(R.id.tv_choose_num);
        this.tvChooseTips = (TextView) findViewById(R.id.tv_choose_tips);
        this.attrContainer = (LinearLayout) findViewById(R.id.attr_container);
        this.filterAttrContainer = (LinearLayout) findViewById(R.id.filter_attr_container);
        this.reportContainer = (LinearLayout) findViewById(R.id.report_container);
        this.reportTipsContainer = (LinearLayout) findViewById(R.id.report_tips_container);
        this.tvReportEmptyTips = (TextView) findViewById(R.id.tv_report_empty_tips);
    }

    public void reload(String str) {
    }

    public void reload(String str, String str2, boolean z) {
        PpInspectViewActionCb ppInspectViewActionCb;
        resetViews();
        Pair<Boolean, PpInspectInfo> formatPpInspectInfo = formatPpInspectInfo(str, str2);
        if (checkNull(formatPpInspectInfo)) {
            return;
        }
        PpInspectInfo ppInspectInfo = (PpInspectInfo) formatPpInspectInfo.second;
        if (dataVerified(ppInspectInfo)) {
            this.mAllInfo = ppInspectInfo;
            changeAttrChain(ppInspectInfo.getSelectableSalesAttrInfo().getInspectSaleAttrs());
            setupViews(z);
            setupLastSalesAttrs();
            if (((Boolean) formatPpInspectInfo.first).booleanValue() && (ppInspectViewActionCb = this.mActionCb) != null) {
                ppInspectViewActionCb.onPpInfoProcessed(ppInspectInfo.getPpSaleAttrId(), object2JsonString(ppInspectInfo));
                String currentDisplayInspectSkus = currentDisplayInspectSkus();
                if (this.isAttached && !TextUtils.equals(this.lastDisplayInspectSkus, currentDisplayInspectSkus)) {
                    exposure("Productdetail_SpecReportExpo", getExposureData());
                }
                this.lastDisplayInspectSkus = currentDisplayInspectSkus;
            }
        }
    }

    public void setActionCb(PpInspectViewActionCb ppInspectViewActionCb) {
        this.mActionCb = ppInspectViewActionCb;
    }

    public void setSkuTags(String str) {
        this.mSkuTags = str;
    }

    public PpInspectView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isAttached = false;
        setup(context);
    }
}
