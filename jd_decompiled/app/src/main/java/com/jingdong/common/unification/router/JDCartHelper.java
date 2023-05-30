package com.jingdong.common.unification.router;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;

/* loaded from: classes6.dex */
public class JDCartHelper {
    public static final String CART_LOCATION_SKUID = "locationSkuId";
    public static final String CART_LOCATION_SKUPRICE = "locationSkuPrice";
    public static final String CART_MODULE_URL = "router://JDCartModule/show?";
    public static final String CART_PACK_ID = "packsId";
    public static final String CART_PACK_NUM = "packsNum";
    public static final String CART_SKULIST = "skuList";
    public static final String CART_SKU_ID = "skuId";
    public static final String CART_SKU_NUM = "skuNum";

    /* loaded from: classes6.dex */
    public static final class UrlBuilder {
        String routerUrl = JDCartHelper.CART_MODULE_URL;

        private UrlBuilder() {
        }

        public static UrlBuilder from() {
            return new UrlBuilder();
        }

        public String build() {
            return this.routerUrl;
        }

        public UrlBuilder putLocateId(String str) {
            if (!TextUtils.isEmpty(str)) {
                if (!this.routerUrl.endsWith(ContainerUtils.FIELD_DELIMITER) && !this.routerUrl.endsWith("?")) {
                    this.routerUrl += "&locationSkuId=" + str;
                } else {
                    this.routerUrl += "locationSkuId=" + str;
                }
            }
            return this;
        }

        public UrlBuilder putLocatePrice(String str) {
            if (!TextUtils.isEmpty(str)) {
                if (!this.routerUrl.endsWith(ContainerUtils.FIELD_DELIMITER) && !this.routerUrl.endsWith("?")) {
                    this.routerUrl += "&locationSkuPrice=" + str;
                } else {
                    this.routerUrl += "locationSkuPrice=" + str;
                }
            }
            return this;
        }

        public UrlBuilder putPackId(String str) {
            if (!TextUtils.isEmpty(str)) {
                if (!this.routerUrl.endsWith(ContainerUtils.FIELD_DELIMITER) && !this.routerUrl.endsWith("?")) {
                    this.routerUrl += "&packsId=" + str;
                } else {
                    this.routerUrl += "packsId=" + str;
                }
            }
            return this;
        }

        public UrlBuilder putPackNum(int i2) {
            if (i2 > 0) {
                if (!this.routerUrl.endsWith(ContainerUtils.FIELD_DELIMITER) && !this.routerUrl.endsWith("?")) {
                    this.routerUrl += "&packsNum=" + i2;
                } else {
                    this.routerUrl += "packsNum=" + i2;
                }
            }
            return this;
        }

        public UrlBuilder putSkuId(String str) {
            if (!TextUtils.isEmpty(str)) {
                if (!this.routerUrl.endsWith(ContainerUtils.FIELD_DELIMITER) && !this.routerUrl.endsWith("?")) {
                    this.routerUrl += "&skuId=" + str;
                } else {
                    this.routerUrl += "skuId=" + str;
                }
            }
            return this;
        }

        public UrlBuilder putSkuList(String str) {
            if (!TextUtils.isEmpty(str)) {
                if (!this.routerUrl.endsWith(ContainerUtils.FIELD_DELIMITER) && !this.routerUrl.endsWith("?")) {
                    this.routerUrl += "&skuList=" + str;
                } else {
                    this.routerUrl += "skuList=" + str;
                }
            }
            return this;
        }

        public UrlBuilder putSkuNum(int i2) {
            if (i2 > 0) {
                if (!this.routerUrl.endsWith(ContainerUtils.FIELD_DELIMITER) && !this.routerUrl.endsWith("?")) {
                    this.routerUrl += "&skuNum=" + i2;
                } else {
                    this.routerUrl += "skuNum=" + i2;
                }
            }
            return this;
        }
    }
}
