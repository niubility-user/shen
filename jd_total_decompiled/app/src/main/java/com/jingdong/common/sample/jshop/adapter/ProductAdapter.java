package com.jingdong.common.sample.jshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.common.sample.jshop.Entity.JShopStock;
import com.jingdong.common.sample.jshop.Entity.ProductEntity;
import com.jingdong.common.sample.jshop.ui.JShopProductImageView;
import com.jingdong.common.sample.jshop.utils.JShopStockUtils;
import com.jingdong.common.sample.jshop.utils.JShopUtil;
import com.jingdong.common.utils.JDImageUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class ProductAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mJshopProductItemInflater;
    private ArrayList<ProductEntity> products = new ArrayList<>();

    /* loaded from: classes6.dex */
    static class ViewHolder {
        public JShopProductImageView mSignItemImage;
        public TextView mSignItemPrice;
        public TextView mSignItemTitle;

        ViewHolder() {
        }
    }

    public ProductAdapter(Context context) {
        this.mContext = context;
        this.mJshopProductItemInflater = LayoutInflater.from(context);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.products.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        return this.products.get(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        String str;
        if (view == null) {
            view = this.mJshopProductItemInflater.inflate(R.layout.jshop_sign_up_item, (ViewGroup) null);
            viewHolder = new ViewHolder();
            viewHolder.mSignItemImage = (JShopProductImageView) view.findViewById(R.id.jshop_sign_item_image);
            viewHolder.mSignItemTitle = (TextView) view.findViewById(R.id.jshop_sign_item_title);
            viewHolder.mSignItemPrice = (TextView) view.findViewById(R.id.jshop_sign_item_price);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        ProductEntity productEntity = this.products.get(i2);
        JShopStock parseFromInt = JShopStock.parseFromInt(productEntity.isUnderCarriage, productEntity.stock);
        JDImageUtils.displayImage(productEntity.imgPath, viewHolder.mSignItemImage);
        viewHolder.mSignItemImage.setStock(parseFromInt);
        viewHolder.mSignItemTitle.setText(productEntity.wareName);
        JShopStockUtils.setTextColorWithStockState(viewHolder.mSignItemTitle, parseFromInt, this.mContext.getResources().getColor(R.color.cj));
        TextView textView = viewHolder.mSignItemPrice;
        if (JShopUtil.isPrice(productEntity.jdPrice) || JShopUtil.isToPublishPrice(productEntity.jdPrice)) {
            str = "\u00a5" + productEntity.jdPrice;
        } else {
            str = productEntity.jdPrice;
        }
        textView.setText(str);
        JShopStockUtils.setTextColorWithStockState(viewHolder.mSignItemPrice, parseFromInt, this.mContext.getResources().getColor(R.color.h7));
        return view;
    }

    public void setProductAdapterData(List<ProductEntity> list) {
        ArrayList<ProductEntity> arrayList = this.products;
        if (arrayList != null) {
            arrayList.clear();
        } else {
            this.products = new ArrayList<>();
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.products.add(list.get(i2).m22clone());
        }
    }

    public ProductAdapter(List<ProductEntity> list, Context context) {
        this.mContext = context;
        this.mJshopProductItemInflater = LayoutInflater.from(context);
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.products.add(list.get(i2).m22clone());
        }
    }
}
