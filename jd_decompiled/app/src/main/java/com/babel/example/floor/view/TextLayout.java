package com.babel.example.floor.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import com.babel.example.floor.R;
import com.babel.example.floor.entity.PicModel;
import com.babel.example.floor.entity.TextLayoutConfig;
import com.babel.example.floor.entity.TextLayoutModel;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.ui.IFloorView;
import com.jd.lib.babel.ifloor.utils.ColorUtil;
import com.jd.lib.babel.ifloor.utils.CommonServiceUtil;
import com.jd.lib.babel.servicekit.imagekit.ImageWraper;
import com.jd.lib.babel.servicekit.model.MtaData;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class TextLayout extends FrameLayout implements IFloorView<TextLayoutModel> {
    private ImageView bgView;
    private ImageWraper img;
    private BabelScope mBabelScope;
    private TextLayoutModel mTextLayoutModel;
    private TextView mTextView;

    public TextLayout(Context context) {
        super(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void click(PicModel picModel) {
        if (picModel.jump != null) {
            CommonServiceUtil.execJump(getContext(), picModel.jump);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("aid", this.mBabelScope.getPageName());
                jSONObject.put("adid", "adid");
                jSONObject.put("agid", this.mTextLayoutModel.advertGroupData.get(0).groupId);
                jSONObject.put("fno", this.mTextLayoutModel.fno);
                jSONObject.put("mid", this.mTextLayoutModel.mid);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            CommonServiceUtil.onClickMta(getContext(), MtaData.Builder.from("Babel_dev_adv_XXX", jSONObject.toString()).page(this.mBabelScope.getPageName(), this.mBabelScope.getPageParam()).build());
        }
    }

    private void updateFloorBg(@NonNull TextLayoutModel textLayoutModel) {
        TextLayoutConfig textLayoutConfig = textLayoutModel.boardParams;
        if (textLayoutConfig != null && textLayoutConfig.bgStyle == 1 && !TextUtils.isEmpty(textLayoutConfig.bgPic)) {
            if (this.bgView == null) {
                ImageView imageView = CommonServiceUtil.getImageView(getContext());
                this.bgView = imageView;
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                addView(this.bgView, 0, new FrameLayout.LayoutParams(-1, -1));
            }
            CommonServiceUtil.displayImage(textLayoutModel.boardParams.bgPic, this.bgView);
            setBackgroundColor(0);
            return;
        }
        ImageView imageView2 = this.bgView;
        if (imageView2 != null) {
            imageView2.setImageDrawable(null);
        }
        TextLayoutConfig textLayoutConfig2 = textLayoutModel.boardParams;
        setBackgroundColor(ColorUtil.parseColor(textLayoutConfig2 != null ? textLayoutConfig2.backgroundColor : "", ViewCompat.MEASURED_SIZE_MASK));
    }

    private void updateTextView(TextView textView, final PicModel picModel) {
        if (picModel == null) {
            return;
        }
        CommonServiceUtil.sendExposureData(this.mBabelScope, "ExpoEventId", "expoSrv");
        textView.setText(picModel.name);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.babel.example.floor.view.TextLayout.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TextLayout.this.click(picModel);
            }
        });
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    public void initView(String str) {
        LayoutInflater.from(getContext()).inflate(R.layout.text_layout, this);
        this.img = (ImageWraper) findViewById(R.id.text_img);
        this.mTextView = (TextView) findViewById(R.id.text_0);
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    public void update(@NonNull BabelScope babelScope, @NonNull TextLayoutModel textLayoutModel, int i2) {
        this.mBabelScope = babelScope;
        this.mTextLayoutModel = textLayoutModel;
        updateFloorBg(textLayoutModel);
        List<PicModel> list = textLayoutModel.advertGroupData.get(0).advertList;
        if (list == null || list.size() <= 0) {
            return;
        }
        final PicModel picModel = list.get(0);
        updateTextView(this.mTextView, picModel);
        CommonServiceUtil.displayImage(picModel.pictureUrl, this.img);
        this.img.setOnClickListener(new View.OnClickListener() { // from class: com.babel.example.floor.view.TextLayout.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TextLayout.this.click(picModel);
            }
        });
    }
}
