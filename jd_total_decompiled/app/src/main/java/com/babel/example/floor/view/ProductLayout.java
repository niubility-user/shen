package com.babel.example.floor.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.babel.example.floor.R;
import com.babel.example.floor.entity.ProductLayoutModel;
import com.babel.example.floor.entity.ProductModel;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.ui.IFloorView;
import com.jd.lib.babel.ifloor.utils.CommonServiceUtil;
import com.jd.lib.babel.servicekit.imagekit.ImageWraper;
import com.jd.lib.babel.servicekit.model.MtaData;
import com.jd.lib.babel.servicekit.util.FontUtil;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ProductLayout extends RelativeLayout implements IFloorView<ProductLayoutModel> {
    private TextView cart;
    private ImageWraper img;
    private BabelScope mBabelScope;
    private ProductLayoutModel mProductLayoutModel;
    private TextView name;
    private TextView pPrice;
    private TextView pcpPrice;

    public ProductLayout(Context context) {
        super(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void click(ProductModel productModel) {
        if (productModel.jump != null) {
            CommonServiceUtil.execJump(getContext(), productModel.jump);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("aid", this.mBabelScope.getPageName());
                jSONObject.put("sku", productModel.skuId);
                jSONObject.put("sgid", this.mProductLayoutModel.productGroupData.get(0).groupId);
                jSONObject.put("fno", this.mProductLayoutModel.fno);
                jSONObject.put("mid", this.mProductLayoutModel.mid);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            CommonServiceUtil.onClickMta(getContext(), MtaData.Builder.from("Babel_dev_sku_XXX", jSONObject.toString()).page(this.mBabelScope.getPageName(), this.mBabelScope.getPageParam()).build());
        }
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    public void initView(String str) {
        LayoutInflater.from(getContext()).inflate(R.layout.product_layout, this);
        this.img = (ImageWraper) findViewById(R.id.product_img);
        this.name = (TextView) findViewById(R.id.product_name);
        TextView textView = (TextView) findViewById(R.id.product_price);
        this.pPrice = textView;
        FontUtil.changeTextFont(textView);
        TextView textView2 = (TextView) findViewById(R.id.product_pcprice);
        this.pcpPrice = textView2;
        textView2.getPaint().setFlags(17);
        FontUtil.changeTextFont(this.pcpPrice);
        this.cart = (TextView) findViewById(R.id.product_cart);
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    public void update(@NonNull BabelScope babelScope, @NonNull ProductLayoutModel productLayoutModel, int i2) {
        this.mBabelScope = babelScope;
        this.mProductLayoutModel = productLayoutModel;
        final ProductModel productModel = productLayoutModel.getFirstList().get(i2);
        CommonServiceUtil.displayImage(productModel.image, this.img);
        this.name.setText(productModel.name);
        this.pPrice.setText(productModel.getPPrice(getContext()));
        this.pcpPrice.setText(productModel.getPcpPrice(getContext()));
        this.cart.setOnClickListener(new View.OnClickListener() { // from class: com.babel.example.floor.view.ProductLayout.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CommonServiceUtil.addSingleProductToCartWithToast(view.getContext(), productModel.skuId, null);
                CommonServiceUtil.onClickMta(ProductLayout.this.getContext(), MtaData.Builder.from("eventId", "eventParam").page(ProductLayout.this.mBabelScope.getPageName(), ProductLayout.this.mBabelScope.getPageParam()).build());
            }
        });
        setOnClickListener(new View.OnClickListener() { // from class: com.babel.example.floor.view.ProductLayout.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ProductLayout.this.click(productModel);
            }
        });
    }
}
