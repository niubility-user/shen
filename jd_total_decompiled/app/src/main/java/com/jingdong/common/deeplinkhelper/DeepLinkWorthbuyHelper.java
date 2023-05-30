package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;

/* loaded from: classes5.dex */
public class DeepLinkWorthbuyHelper {
    public static final String HOST_WORTHBUY_2G_ALBUM_DETAIL = "worthbuyalbum2Gdetail";
    public static final String HOST_WORTHBUY_2G_INVENTORY_DETAIL = "worthbuy2Ginventorydetail";
    public static final String HOST_WORTHBUY_ALBUM_DETAIL = "worthbuyalbumdetail";
    public static final String HOST_WORTHBUY_ARTICLE_DETAIL = "worthbuyarticledetail";
    public static final String HOST_WORTHBUY_AUTHOR = "worthbuyauthor";
    public static final String HOST_WORTHBUY_GOODS_DETAIL = "worthbuygoodsdetail";
    public static final String HOST_WORTHBUY_INVENTORY_DETAIL = "worthbuyinventorydetail";
    public static final String HOST_WORTHBUY_LIKE = "worthbuy_like";
    public static final String HOST_WORTHBUY_LIST = "worthbuy";
    public static final String HOST_WORTHBUY_NEW_PRODUCT_DETAIL = "worthbuyNewProductDetail";
    public static final String HOST_WORTHBUY_RELATED_PRODUCTS = "worthbuyrelatedproducts";
    public static final String HOST_WORTHBUY_TAG = "worthbuytag";
    public static final String HOST_WORTHBUY_TICKET_LIST = "worthbuyTicketList";
    public static final String HOST_WORTHBUY_TOGETHER = "worthbuyTogether";
    public static final String HOST_WORTHBUY_WISH_LIST = "worthbuyWishList";

    public static void startAlbumSelectedList(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(19))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(JumpUtil.VALUE_DES_ALBUM_SELECTED_LIST).toString(), bundle);
        }
    }

    public static void startSubjectDetailListActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(19))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(JumpUtil.VALUE_DES_SUBJECT_LIST).toString(), bundle);
        }
    }

    public static void startWorthbuy2GAlbumDetailActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(19))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_WORTHBUY_2G_ALBUM_DETAIL).toString(), bundle);
        }
    }

    public static void startWorthbuy2GInventoryDetailActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(19))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_WORTHBUY_2G_INVENTORY_DETAIL).toString(), bundle);
        }
    }

    public static void startWorthbuyActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(19))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_WORTHBUY_LIST).toString(), bundle);
        }
    }

    public static void startWorthbuyAlbumDetailActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(19))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_WORTHBUY_ALBUM_DETAIL).toString(), bundle);
        }
    }

    public static void startWorthbuyArticleDetailActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(19))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_WORTHBUY_ARTICLE_DETAIL).toString(), bundle);
        }
    }

    public static void startWorthbuyAuthorActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(19))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host("worthbuyauthor").toString(), bundle);
        }
    }

    public static void startWorthbuyGoodsDetailActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(19))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_WORTHBUY_GOODS_DETAIL).toString(), bundle);
        }
    }

    public static void startWorthbuyInventoryDetailActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(19))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_WORTHBUY_INVENTORY_DETAIL).toString(), bundle);
        }
    }

    public static void startWorthbuyLikeActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(19))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host("worthbuy_like").toString(), bundle);
        }
    }

    public static void startWorthbuyNewProductDetailActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(19))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host("worthbuyNewProductDetail").toString(), bundle);
        }
    }

    public static void startWorthbuyRelatedActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(19))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_WORTHBUY_RELATED_PRODUCTS).toString(), bundle);
        }
    }

    public static void startWorthbuyTagActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(19))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_WORTHBUY_TAG).toString(), bundle);
        }
    }

    public static void startWorthbuyTicketListActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(19))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host("worthbuyTicketList").toString(), bundle);
        }
    }

    public static void startWorthbuyTogetherActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(19))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_WORTHBUY_TOGETHER).toString(), bundle);
        }
    }

    public static void startWorthbuyWishListActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(19))) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host("worthbuyWishList").toString(), bundle);
        }
    }
}
