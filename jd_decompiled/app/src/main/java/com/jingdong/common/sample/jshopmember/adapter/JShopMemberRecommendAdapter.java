package com.jingdong.common.sample.jshopmember.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.s;
import com.jingdong.common.bing.utils.PreferenceUtil;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.entity.Product;
import com.jingdong.common.entity.TwoProduct;
import com.jingdong.common.sample.jshop.ui.JShopProductImageView;
import com.jingdong.common.sample.jshop.utils.JShopUtil;
import com.jingdong.common.sample.jshop.utils.SpannableStringUtils;
import com.jingdong.common.sample.jshopmember.JshopMemberActivity;
import com.jingdong.common.sample.jshopmember.entity.JshopProduct;
import com.jingdong.common.sample.jshopmember.utils.JshopMemberUtils;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.DPIUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class JShopMemberRecommendAdapter extends BaseAdapter {
    private static final String TAG = "JShopMemberRecommendAdapter";
    private Context mContext;
    public View tipGuildView;
    private int imageWidth = (DPIUtil.getWidth() - DPIUtil.dip2px(5.0f)) >> 1;
    private ArrayList<TwoProduct> mProducts = new ArrayList<>();
    public int guildIconId = 0;
    public int fansIconId = 0;
    public int pIconId = 0;
    public boolean isMember = false;
    public int saleType = 0;
    public Handler mHandler = new Handler() { // from class: com.jingdong.common.sample.jshopmember.adapter.JShopMemberRecommendAdapter.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            View view = JShopMemberRecommendAdapter.this.tipGuildView;
            if (view != null) {
                view.setVisibility(8);
                PreferenceUtil.put(JShopMemberRecommendAdapter.this.mContext, "member_guild_hidden", "1");
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class ViewHolder {
        SimpleDraweeView addCart1;
        SimpleDraweeView addCart2;
        SimpleDraweeView fansPriceIcon;
        View leftItem;
        TextView martPriceView;
        TextView memberJdPrice;
        TextView pIcon;
        JShopProductImageView pItemImage;
        TextView pname;
        View rigthItme;
        TextView tMartPriceView;
        TextView tMemberJdPriceView;
        JShopProductImageView tPItemImage;
        TextView tPname;
        SimpleDraweeView tipsView;
        TextView tpIcon;

        public ViewHolder(View view) {
            this.fansPriceIcon = (SimpleDraweeView) view.findViewById(R.id.fans_price_icon);
            this.pItemImage = (JShopProductImageView) view.findViewById(R.id.p_item_image);
            this.pname = (TextView) view.findViewById(R.id.p_item_name);
            this.memberJdPrice = (TextView) view.findViewById(R.id.p_item_jdPrice);
            this.martPriceView = (TextView) view.findViewById(R.id.product_item_martPrice);
            this.addCart1 = (SimpleDraweeView) view.findViewById(R.id.add_shopcart1);
            this.tipsView = (SimpleDraweeView) view.findViewById(R.id.jshop_tips_view);
            this.pIcon = (TextView) view.findViewById(R.id.p_icon);
            this.leftItem = view.findViewById(R.id.left_item);
            this.tPItemImage = (JShopProductImageView) view.findViewById(R.id.two_p_item_image);
            this.tPname = (TextView) view.findViewById(R.id.two_p_item_name);
            this.tpIcon = (TextView) view.findViewById(R.id.tp_icon);
            this.tMemberJdPriceView = (TextView) view.findViewById(R.id.two_p_item_jdPrice);
            this.tMartPriceView = (TextView) view.findViewById(R.id.two_product_item_martPrice);
            this.addCart2 = (SimpleDraweeView) view.findViewById(R.id.add_shopcart2);
            this.rigthItme = view.findViewById(R.id.right_item);
        }
    }

    public JShopMemberRecommendAdapter(Context context) {
        this.mContext = context;
    }

    public void bindData1(ViewHolder viewHolder, final JshopProduct jshopProduct) {
        if (jshopProduct == null) {
            viewHolder.leftItem.setVisibility(4);
            return;
        }
        viewHolder.leftItem.setVisibility(0);
        viewHolder.pItemImage.getLayoutParams().height = this.imageWidth;
        viewHolder.pItemImage.getLayoutParams().width = this.imageWidth;
        JDImageUtils.displayImage(jshopProduct.getImageUrl(), viewHolder.pItemImage);
        if (this.saleType == 1) {
            viewHolder.pIcon.setText(this.mContext.getText(R.string.jshop_member_only_sale_price_hint));
        } else {
            viewHolder.pIcon.setText(this.mContext.getText(R.string.jshop_member_first_sale_price_hint));
        }
        viewHolder.pname.setText(jshopProduct.getName());
        viewHolder.memberJdPrice.setText(SpannableStringUtils.getFormatSpanPrice(jshopProduct.getCustomerPrice()));
        if (!JShopUtil.isPrice(jshopProduct.getJdPrice()) && !JShopUtil.isToPublishPrice(jshopProduct.getJdPrice())) {
            viewHolder.martPriceView.setText(jshopProduct.getJdPrice());
        } else {
            viewHolder.martPriceView.setText(this.mContext.getString(R.string.pg_home_jdpirce, jshopProduct.getJdPrice()));
        }
        viewHolder.addCart1.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshopmember.adapter.JShopMemberRecommendAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (JShopMemberRecommendAdapter.this.mContext instanceof JshopMemberActivity) {
                    JShopMemberRecommendAdapter jShopMemberRecommendAdapter = JShopMemberRecommendAdapter.this;
                    jShopMemberRecommendAdapter.sendVipProduct("ShopVIP_AddCart", (JshopMemberActivity) jShopMemberRecommendAdapter.mContext, jshopProduct);
                }
                JshopMemberUtils.gotoShoppingCart((JshopMemberActivity) JShopMemberRecommendAdapter.this.mContext, String.valueOf(jshopProduct.getId()));
            }
        });
        viewHolder.leftItem.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshopmember.adapter.JShopMemberRecommendAdapter.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (JShopMemberRecommendAdapter.this.mContext instanceof JshopMemberActivity) {
                    JShopMemberRecommendAdapter jShopMemberRecommendAdapter = JShopMemberRecommendAdapter.this;
                    jShopMemberRecommendAdapter.sendVipProduct("ShopVIP_VIPProduct", (JshopMemberActivity) jShopMemberRecommendAdapter.mContext, jshopProduct);
                }
                s.g(JShopMemberRecommendAdapter.this.mContext, DeeplinkProductDetailHelper.BundleBuilder.from(jshopProduct.getId().longValue()).imageTitlePrice(jshopProduct.getImageUrl(), jshopProduct.getName(), jshopProduct.getJdPrice()).build());
                JShopMemberRecommendAdapter.this.mHandler.sendEmptyMessageDelayed(0, 1000L);
            }
        });
        if (this.saleType == 1) {
            viewHolder.addCart1.setVisibility(0);
        } else {
            viewHolder.addCart1.setVisibility(8);
        }
    }

    public void bindData2(ViewHolder viewHolder, final JshopProduct jshopProduct) {
        if (jshopProduct == null) {
            viewHolder.rigthItme.setVisibility(4);
            return;
        }
        if (this.saleType == 1) {
            viewHolder.tpIcon.setText(this.mContext.getText(R.string.jshop_member_only_sale_price_hint));
        } else {
            viewHolder.tpIcon.setText(this.mContext.getText(R.string.jshop_member_first_sale_price_hint));
        }
        viewHolder.rigthItme.setVisibility(0);
        viewHolder.tPItemImage.getLayoutParams().height = this.imageWidth;
        viewHolder.tPItemImage.getLayoutParams().width = this.imageWidth;
        JDImageUtils.displayImage(jshopProduct.getImageUrl(), viewHolder.tPItemImage);
        viewHolder.tPname.setText(jshopProduct.getName());
        viewHolder.tMemberJdPriceView.setText(SpannableStringUtils.getFormatSpanPrice(jshopProduct.getCustomerPrice()));
        if (!JShopUtil.isPrice(jshopProduct.getJdPrice()) && !JShopUtil.isToPublishPrice(jshopProduct.getJdPrice())) {
            viewHolder.tMartPriceView.setText(jshopProduct.getJdPrice());
        } else {
            viewHolder.tMartPriceView.setText(this.mContext.getString(R.string.pg_home_jdpirce, jshopProduct.getJdPrice()));
        }
        viewHolder.addCart2.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshopmember.adapter.JShopMemberRecommendAdapter.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (JShopMemberRecommendAdapter.this.mContext instanceof JshopMemberActivity) {
                    JShopMemberRecommendAdapter jShopMemberRecommendAdapter = JShopMemberRecommendAdapter.this;
                    jShopMemberRecommendAdapter.sendVipProduct("ShopVIP_AddCart", (JshopMemberActivity) jShopMemberRecommendAdapter.mContext, jshopProduct);
                }
                JshopMemberUtils.gotoShoppingCart((JshopMemberActivity) JShopMemberRecommendAdapter.this.mContext, String.valueOf(jshopProduct.getId()));
            }
        });
        viewHolder.rigthItme.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshopmember.adapter.JShopMemberRecommendAdapter.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (JShopMemberRecommendAdapter.this.mContext instanceof JshopMemberActivity) {
                    JShopMemberRecommendAdapter jShopMemberRecommendAdapter = JShopMemberRecommendAdapter.this;
                    jShopMemberRecommendAdapter.sendVipProduct("ShopVIP_VIPProduct", (JshopMemberActivity) jShopMemberRecommendAdapter.mContext, jshopProduct);
                }
                s.g(JShopMemberRecommendAdapter.this.mContext, DeeplinkProductDetailHelper.BundleBuilder.from(jshopProduct.getId().longValue()).imageTitlePrice(jshopProduct.getImageUrl(), jshopProduct.getName(), jshopProduct.getJdPrice()).build());
                JShopMemberRecommendAdapter.this.mHandler.sendEmptyMessageDelayed(0, 1000L);
            }
        });
        if (this.saleType == 1) {
            viewHolder.addCart2.setVisibility(0);
        } else {
            viewHolder.addCart2.setVisibility(8);
        }
    }

    public void clear() {
        ArrayList<TwoProduct> arrayList = this.mProducts;
        if (arrayList != null) {
            arrayList.clear();
            notifyDataSetChanged();
        }
    }

    @Override // android.widget.Adapter
    public int getCount() {
        ArrayList<TwoProduct> arrayList = this.mProducts;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        ArrayList<TwoProduct> arrayList = this.mProducts;
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        return this.mProducts.get(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LinearLayout.inflate(this.mContext, R.layout.jshop_member_recommend_item, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        TwoProduct twoProduct = (TwoProduct) getItem(i2);
        if (twoProduct == null) {
            return view;
        }
        if (i2 == 0) {
            viewHolder.fansPriceIcon.setVisibility(0);
            int i3 = this.fansIconId;
            if (i3 != 0) {
                viewHolder.fansPriceIcon.setBackgroundResource(i3);
            } else {
                viewHolder.fansPriceIcon.setBackgroundResource(R.drawable.jshop_text_fans_price);
            }
            if (twoProduct.getProductTwo() != null) {
                Log.d(TAG, "set guild view position = " + i2);
                if (!"1".equals(PreferenceUtil.get(this.mContext, "member_guild_hidden"))) {
                    setGuildView(viewHolder);
                } else {
                    viewHolder.tipsView.setVisibility(8);
                }
            } else {
                Log.d(TAG, "set guild hidden!");
                viewHolder.tipsView.setVisibility(8);
            }
        } else {
            viewHolder.tipsView.setVisibility(8);
            viewHolder.fansPriceIcon.setVisibility(8);
        }
        bindData1(viewHolder, (JshopProduct) twoProduct.getProductOne());
        bindData2(viewHolder, (JshopProduct) twoProduct.getProductTwo());
        return view;
    }

    public void hiddenGuildTip() {
        Handler handler = this.mHandler;
        if (handler != null && handler.hasMessages(0)) {
            this.mHandler.removeMessages(0);
        }
        View view = this.tipGuildView;
        if (view == null || view.getVisibility() != 0) {
            return;
        }
        this.tipGuildView.setVisibility(8);
        PreferenceUtil.put(this.mContext, "member_guild_hidden", "1");
    }

    public void sendVipProduct(String str, JshopMemberActivity jshopMemberActivity, Product product) {
        if (jshopMemberActivity == null || product == null) {
            return;
        }
        JDMtaUtils.sendCommonData(jshopMemberActivity, str, product.getId() + CartConstant.KEY_YB_INFO_LINK + product.getPsPrice() + CartConstant.KEY_YB_INFO_LINK + product.getJdPrice(), "", jshopMemberActivity, jshopMemberActivity.getMemberLevel(), "", "", "Shop_VIP", jshopMemberActivity.mShopId);
    }

    public void setData(List<TwoProduct> list) {
        if (list != null) {
            this.mProducts.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void setGuildView(ViewHolder viewHolder) {
        viewHolder.tipsView.setVisibility(0);
        viewHolder.tipsView.setBackgroundResource(this.guildIconId);
        viewHolder.tipsView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshopmember.adapter.JShopMemberRecommendAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                view.setVisibility(8);
                PreferenceUtil.put(JShopMemberRecommendAdapter.this.mContext, "member_guild_hidden", "1");
            }
        });
        this.tipGuildView = viewHolder.tipsView;
        this.mHandler.sendEmptyMessageDelayed(0, 20000L);
    }
}
