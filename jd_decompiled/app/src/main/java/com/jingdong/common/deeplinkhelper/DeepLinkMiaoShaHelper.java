package com.jingdong.common.deeplinkhelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.miaosha.MiaoShaPluginLoader;
import com.jingdong.app.mall.miaosha.MiaoShaPublicConstants;
import com.jingdong.jdsdk.auraSetting.AuraBundleConfig;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class DeepLinkMiaoShaHelper {
    public static final String HOST_MIAOSHA_ACTIVITY = "miaoshaactivity";
    public static final String HOST_MIAOSHA_BANNER_ACTIVITY = "miaoshabannerinner";
    public static final String HOST_MIAOSHA_BRANDCATEGORY_INNER_ACTIVITY = "miaoshabrandcategoryinner";
    public static final String HOST_MIAOSHA_BRANDINNER_ACTIVITY = "shangoubrandinner";
    public static final String HOST_MIAOSHA_CATEGORY_ACTIVITY = "miaoshacategory";
    public static final String HOST_MIAOSHA_CATEGORY_ACTIVITY_NEW = "miaoshacategoryactivity";
    public static final String HOST_MIAOSHA_GROUP_NEW_PRODUCT_ACTIVITY = "miaoshagroupnewproduct";
    public static final String HOST_MIAOSHA_JISHI_INNER_ACTIVITY = "miaoshajishiinner";
    public static final String HOST_MIAOSHA_LIANGFAN = "miaoshaliangfan";
    public static final String HOST_MIAOSHA_LIANGFAN_INNER_ACTIVITY = "liangfaninner";
    public static final String HOST_MIAOSHA_LIVE_LIST_ACTIVITY = "miaoshalivelist";
    public static final String HOST_MIAOSHA_MYCONCERN_ACTIVITY = "miaoshamyconcern";
    public static final String HOST_MIAOSHA_NEW_PRODUCT_ACTIVITY = "miaoshanewproduct";
    public static final String HOST_MIAOSHA_PARITYACTIVITY = "miaoshaparity";
    public static final String HOST_MIAOSHA_PARITY_NINENINE_ACTIVITY = "parityninenine";
    public static final String HOST_MIAOSHA_SEARCHRESULT = "seckillsearchresult";
    public static final String HOST_MIAOSHA_SELLOUT_ACTIVITY = "miaoshaselloutactivity";

    /* loaded from: classes5.dex */
    public static class BundleBuilder {
        private Bundle mBundle;

        private BundleBuilder(int i2) {
            Bundle bundle = new Bundle();
            this.mBundle = bundle;
            bundle.putInt(MiaoShaPublicConstants.KEY_GID, i2);
        }

        public static BundleBuilder from(int i2) {
            return new BundleBuilder(i2);
        }

        public Bundle build() {
            return this.mBundle;
        }

        public static BundleBuilder from(String str) {
            return new BundleBuilder(str);
        }

        private BundleBuilder(String str) {
            Bundle bundle = new Bundle();
            this.mBundle = bundle;
            bundle.putString("productId", str);
        }
    }

    public static void startMiaoShaBanner(Context context, Bundle bundle) {
        if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(35))) {
            if (OKLog.D) {
                OKLog.d("DeepLinkMiaoShaHelper", "=> MASK_AURA_SWITCH_JDMIAOSHA close");
                return;
            }
            return;
        }
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host("miaoshabannerinner").toString(), bundle);
    }

    public static void startMiaoShaBrand(Context context, Bundle bundle) {
        if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(35))) {
            if (OKLog.D) {
                OKLog.d("DeepLinkMiaoShaHelper", "=> MASK_AURA_SWITCH_JDMIAOSHA close");
                return;
            }
            return;
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString(MiaoShaPublicConstants.JUMP_TO, "1");
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host("miaoshaactivity").toString(), bundle);
    }

    public static void startMiaoShaBrandCategoryInner(Context context, Bundle bundle) {
        if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(35))) {
            if (OKLog.D) {
                OKLog.d("DeepLinkMiaoShaHelper", "=> MASK_AURA_SWITCH_JDMIAOSHA close");
                return;
            }
            return;
        }
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_MIAOSHA_BRANDCATEGORY_INNER_ACTIVITY).toString(), bundle);
    }

    public static void startMiaoShaBrandInner(Context context, Bundle bundle) {
        if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(35))) {
            if (OKLog.D) {
                OKLog.d("DeepLinkMiaoShaHelper", "=> MASK_AURA_SWITCH_JDMIAOSHA close");
                return;
            }
            return;
        }
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_MIAOSHA_BRANDINNER_ACTIVITY).toString(), bundle);
    }

    public static void startMiaoShaCategoryActivity(Context context, Bundle bundle) {
        if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(35))) {
            if (OKLog.D) {
                OKLog.d("DeepLinkMiaoShaHelper", "=> MASK_AURA_SWITCH_JDMIAOSHA close");
                return;
            }
            return;
        }
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_MIAOSHA_CATEGORY_ACTIVITY_NEW).toString(), bundle);
    }

    public static void startMiaoShaGroupNewProduct(Context context, Bundle bundle) {
        if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(35))) {
            if (OKLog.D) {
                OKLog.d("DeepLinkMiaoShaHelper", "=> MASK_AURA_SWITCH_JDMIAOSHA close");
                return;
            }
            return;
        }
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_MIAOSHA_GROUP_NEW_PRODUCT_ACTIVITY).toString(), bundle);
    }

    public static void startMiaoShaJiShiInnerActivity(Context context, Bundle bundle) {
        if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(35))) {
            if (OKLog.D) {
                OKLog.d("DeepLinkMiaoShaHelper", "=> MASK_AURA_SWITCH_JDMIAOSHA close");
                return;
            }
            return;
        }
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_MIAOSHA_JISHI_INNER_ACTIVITY).toString(), bundle);
    }

    public static void startMiaoShaLiangfan(Context context, Bundle bundle) {
        if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(35))) {
            if (OKLog.D) {
                OKLog.d("DeepLinkMiaoShaHelper", "=> MASK_AURA_SWITCH_JDMIAOSHA close");
                return;
            }
            return;
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString(MiaoShaPublicConstants.JUMP_TO, "2");
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host("miaoshaactivity").toString(), bundle);
    }

    public static void startMiaoShaLiangfanActivity(Context context, Bundle bundle) {
        if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(35))) {
            if (OKLog.D) {
                OKLog.d("DeepLinkMiaoShaHelper", "=> MASK_AURA_SWITCH_JDMIAOSHA close");
                return;
            }
            return;
        }
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_MIAOSHA_LIANGFAN).toString(), bundle);
    }

    public static void startMiaoShaLiangfanInner(Context context, Bundle bundle) {
        if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(35))) {
            if (OKLog.D) {
                OKLog.d("DeepLinkMiaoShaHelper", "=> MASK_AURA_SWITCH_JDMIAOSHA close");
                return;
            }
            return;
        }
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host("liangfaninner").toString(), bundle);
    }

    public static void startMiaoShaList(Context context) {
        if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(35))) {
            if (OKLog.D) {
                OKLog.d("DeepLinkMiaoShaHelper", "=> MASK_AURA_SWITCH_JDMIAOSHA close");
                return;
            }
            return;
        }
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host("miaoshaactivity").toString(), null);
    }

    public static void startMiaoShaListForResult(Activity activity, Bundle bundle, int i2) {
        if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(35))) {
            if (OKLog.D) {
                OKLog.d("DeepLinkMiaoShaHelper", "=> MASK_AURA_SWITCH_JDMIAOSHA close");
                return;
            }
            return;
        }
        DeepLinkDispatch.startActivityForResult(activity, new DeepLinkUri.Builder().scheme("jingdong").host("miaoshaactivity").toString(), bundle, i2);
    }

    public static void startMiaoShaLive(Context context, Bundle bundle) {
        if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(35))) {
            if (OKLog.D) {
                OKLog.d("DeepLinkMiaoShaHelper", "=> MASK_AURA_SWITCH_JDMIAOSHALIVE close");
                return;
            }
            return;
        }
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_MIAOSHA_LIVE_LIST_ACTIVITY).toString(), bundle);
    }

    public static void startMiaoShaNewProduct(Context context, Bundle bundle) {
        if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(35))) {
            if (OKLog.D) {
                OKLog.d("DeepLinkMiaoShaHelper", "=> MASK_AURA_SWITCH_JDMIAOSHA close");
                return;
            }
            return;
        }
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_MIAOSHA_NEW_PRODUCT_ACTIVITY).toString(), bundle);
    }

    public static void startMiaoShaParityActivity(Context context, Bundle bundle) {
        if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(35))) {
            if (OKLog.D) {
                OKLog.d("DeepLinkMiaoShaHelper", "=> MASK_AURA_SWITCH_JDMIAOSHA close");
                return;
            }
            return;
        }
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_MIAOSHA_PARITYACTIVITY).toString(), bundle);
    }

    public static void startMiaoShaSearchResult(Context context, Bundle bundle) {
        if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(35))) {
            if (OKLog.D) {
                OKLog.d("DeepLinkMiaoShaHelper", "=> MASK_AURA_SWITCH_JDMIAOSHA close");
                return;
            }
            return;
        }
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_MIAOSHA_SEARCHRESULT).toString(), bundle);
    }

    public static void startMiaoShaSellOutActivity(Context context, Bundle bundle) {
        if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(35))) {
            if (OKLog.D) {
                OKLog.d("DeepLinkMiaoShaHelper", "=> MASK_AURA_SWITCH_JDMIAOSHA close");
                return;
            }
            return;
        }
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_MIAOSHA_SELLOUT_ACTIVITY).toString(), bundle);
    }

    public static void startParityNineNineActivity(Context context, Bundle bundle) {
        if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(35))) {
            if (OKLog.D) {
                OKLog.d("DeepLinkMiaoShaHelper", "=> MASK_AURA_SWITCH_JDMIAOSHA close");
                return;
            }
            return;
        }
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_MIAOSHA_PARITY_NINENINE_ACTIVITY).toString(), bundle);
    }

    public static void startMiaoShaList(Context context, Bundle bundle) {
        if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(35))) {
            if (OKLog.D) {
                OKLog.d("DeepLinkMiaoShaHelper", "=> MASK_AURA_SWITCH_JDMIAOSHA close");
                return;
            }
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (!AuraBundleConfig.getInstance().isBundlePrepared(AuraBundleInfos.getBundleNameFromBundleId(35))) {
            if (OKLog.D) {
                OKLog.d("DeepLinkMiaoShaHelper", "JDMIAOSHA SO FILE NOT PREPARED");
            }
            MiaoShaPluginLoader.getInstance().setCoolStartTime(currentTimeMillis);
        } else if (!AuraBundleConfig.getInstance().isBundleLoaded(AuraBundleInfos.getBundleNameFromBundleId(35))) {
            if (OKLog.D) {
                OKLog.d("DeepLinkMiaoShaHelper", "JDMIAOSHA SO FILE NOT LOADED");
            }
            MiaoShaPluginLoader.getInstance().setWarmStartTime(currentTimeMillis);
        } else {
            if (OKLog.D) {
                OKLog.d("DeepLinkMiaoShaHelper", "JDMIAOSHA SO FILE PREPARED AND LOADED");
            }
            MiaoShaPluginLoader.getInstance().setHotStartTime(currentTimeMillis);
        }
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host("miaoshaactivity").toString(), bundle);
    }
}
