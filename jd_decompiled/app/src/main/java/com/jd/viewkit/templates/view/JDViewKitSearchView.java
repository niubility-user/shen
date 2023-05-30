package com.jd.viewkit.templates.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.global.GlobalManage;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.jdviewkit.R;
import com.jd.viewkit.templates.JDViewKitBaseLayout;
import com.jd.viewkit.templates.model.event.JDViewKitVirtualEvent;
import com.jd.viewkit.templates.model.jdviewkitvirtualsearchview.JDViewKitVirtualSearchView;
import com.jd.viewkit.thirdinterface.model.JDViewKitEventModel;
import com.jd.viewkit.thirdinterface.model.JDViewKitMtaModel;
import com.jd.viewkit.tool.ExpressionParserTool;
import com.jd.viewkit.tool.JSONTool;
import java.util.Map;

/* loaded from: classes18.dex */
public class JDViewKitSearchView extends JDViewKitBaseLayout<JDViewKitVirtualSearchView> implements View.OnClickListener {
    private ImageView iconImageView;
    private JDViewKitDataSourceModel mDataSourceModel;
    private JDViewKitTextView textView;

    public JDViewKitSearchView(@NonNull Context context) {
        super(context);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        JDViewKitDataSourceModel jDViewKitDataSourceModel;
        JDViewKitVirtualEvent virtualEventByType = ((JDViewKitVirtualSearchView) this.virtualView).getVirtualEventByType(JDViewKitVirtualEvent.typeClick);
        if (virtualEventByType != null && (jDViewKitDataSourceModel = this.mDataSourceModel) != null) {
            try {
                Map<String, Object> jumpParams = jDViewKitDataSourceModel.getJumpParams(virtualEventByType.getEventKey());
                if (jumpParams == null) {
                    return;
                }
                String jsonString = JSONTool.getJsonString(jumpParams);
                ((JDViewKitVirtualSearchView) this.virtualView).getServiceModel().getEventService().clickEvent(new JDViewKitEventModel(jsonString), view.getContext());
                ((JDViewKitVirtualSearchView) this.virtualView).getServiceModel().getMtaService().click(new JDViewKitMtaModel(jsonString, this.mDataSourceModel.getParamsModel()), view.getContext());
            } catch (Throwable unused) {
            }
        }
    }

    public void setDataSourceModel(JDViewKitDataSourceModel jDViewKitDataSourceModel, boolean z) {
        this.mDataSourceModel = jDViewKitDataSourceModel;
        if (this.virtualView != 0 && jDViewKitDataSourceModel != null && jDViewKitDataSourceModel.getDataMap() != null) {
            boolean filterValue = JDViewKitBaseLayout.getFilterValue(((JDViewKitVirtualSearchView) this.virtualView).isFilter(), jDViewKitDataSourceModel.getDataMap());
            this.isFilter = filterValue;
            if (filterValue) {
                return;
            }
        }
        requestLayout();
        this.textView.setText(ExpressionParserTool.getStringValueRef(((JDViewKitVirtualSearchView) this.virtualView).getValueRefer(), jDViewKitDataSourceModel.getDataMap()));
    }

    public JDViewKitSearchView(@NonNull Context context, @NonNull JDViewKitChannelModel jDViewKitChannelModel) {
        super(context, jDViewKitChannelModel);
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setVirtualView(JDViewKitVirtualSearchView jDViewKitVirtualSearchView) {
        super.setVirtualView((JDViewKitSearchView) jDViewKitVirtualSearchView);
        if (this.iconImageView == null) {
            ImageView imageView = new ImageView(this.mContext);
            this.iconImageView = imageView;
            imageView.setImageResource(R.drawable.jdvk_search_icon);
            addView(this.iconImageView);
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(GlobalManage.getInstance().getRealPx(jDViewKitVirtualSearchView.getSearchIconStyle().getWidth(), getChannelModel()), GlobalManage.getInstance().getRealPx(jDViewKitVirtualSearchView.getSearchIconStyle().getHeigh(), getChannelModel()));
        layoutParams.topMargin = GlobalManage.getInstance().getRealPx((jDViewKitVirtualSearchView.getHeigh() - jDViewKitVirtualSearchView.getSearchIconStyle().getHeigh()) / 2, getChannelModel());
        layoutParams.leftMargin = GlobalManage.getInstance().getRealPx(jDViewKitVirtualSearchView.getSearchIconStyle().getMarginLeft(), getChannelModel());
        this.iconImageView.setLayoutParams(layoutParams);
        if (this.textView == null) {
            JDViewKitTextView jDViewKitTextView = new JDViewKitTextView(this.mContext, getChannelModel());
            this.textView = jDViewKitTextView;
            jDViewKitTextView.setVirtualTextView(jDViewKitVirtualSearchView.getSearchShowWordStyle());
            addView(this.textView);
        }
        this.textView.setText((CharSequence) null);
        if (jDViewKitVirtualSearchView.getVirtualEventByType(JDViewKitVirtualEvent.typeClick) != null) {
            setOnClickListener(this);
        }
    }

    public JDViewKitSearchView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
