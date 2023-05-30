package com.jingdong.common.entity;

import com.jd.framework.json.JDJSON;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class MiaoShaProduct {
    private static final String TAG = "MiaoShaProduct";
    private MiaoShaBrand brand;
    public MiaoShaListCategory category;
    private MiaoShaBrand newBrand;
    public MiaoShaPlus plus;
    public Product product;
    public boolean isBrand = false;
    public boolean isCategory = false;
    public boolean isNewBrand = false;
    public boolean isHistoryHead = false;
    public boolean isCategoryHead = false;
    public boolean isPlus = false;

    public MiaoShaProduct() {
    }

    /* JADX WARN: Removed duplicated region for block: B:127:0x0044 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:131:0x006f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:133:0x00bf A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0054 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:163:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00ac  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ArrayList<MiaoShaProduct> toList(JSONObjectProxy jSONObjectProxy) {
        List parseArray;
        List parseArray2;
        MiaoShaBrand miaoShaBrand;
        List parseArray3;
        int i2;
        int i3;
        MiaoShaProduct miaoShaProduct;
        ArrayList<MiaoShaProduct> arrayList = null;
        if (jSONObjectProxy == null) {
            return null;
        }
        JSONArrayPoxy jSONArrayOrNull = jSONObjectProxy.getJSONArrayOrNull("miaoShaList");
        JSONArrayPoxy jSONArrayOrNull2 = jSONObjectProxy.getJSONArrayOrNull("brandList");
        JSONArrayPoxy jSONArrayOrNull3 = jSONObjectProxy.getJSONArrayOrNull("newBrandList");
        JSONObjectProxy jSONObjectOrNull = jSONObjectProxy.getJSONObjectOrNull("discount");
        JSONArrayPoxy jSONArrayOrNull4 = jSONObjectProxy.getJSONArrayOrNull("plusGoodsList");
        ArrayList<Product> list = Product.toList(jSONArrayOrNull, 17);
        ArrayList<Product> list2 = Product.toList(jSONArrayOrNull4, 37);
        if (jSONArrayOrNull2 != null) {
            try {
                parseArray = JDJSON.parseArray(jSONArrayOrNull2.toString(), MiaoShaBrand.class);
            } catch (Exception e2) {
                OKLog.e(TAG, e2);
            }
            if (jSONArrayOrNull3 != null) {
                try {
                    parseArray2 = JDJSON.parseArray(jSONArrayOrNull3.toString(), MiaoShaBrand.class);
                } catch (Exception e3) {
                    OKLog.e(TAG, e3);
                }
                if (jSONObjectOrNull != null) {
                    try {
                        miaoShaBrand = (MiaoShaBrand) JDJSON.parseObject(jSONObjectOrNull.toString(), MiaoShaBrand.class);
                        if (parseArray != null) {
                            try {
                                parseArray.add(miaoShaBrand);
                            } catch (Exception e4) {
                                e = e4;
                                OKLog.e(TAG, e);
                                if (jSONArrayOrNull4 != null) {
                                }
                                parseArray3 = null;
                                if (list != null) {
                                }
                                if (parseArray != null) {
                                }
                                if (parseArray2 != null) {
                                }
                                if (parseArray3 != null) {
                                }
                                i2 = r8 + r9 + 0 + r10 + r11;
                                if (i2 <= 0) {
                                }
                            }
                        }
                    } catch (Exception e5) {
                        e = e5;
                        miaoShaBrand = null;
                    }
                } else {
                    miaoShaBrand = null;
                }
                if (jSONArrayOrNull4 != null) {
                    try {
                        parseArray3 = JDJSON.parseArray(jSONArrayOrNull4.toString(), MiaoShaPlus.class);
                    } catch (Exception e6) {
                        OKLog.e(TAG, e6);
                    }
                    int size = (list != null || list.isEmpty()) ? 0 : list.size();
                    int size2 = (parseArray != null || parseArray.isEmpty()) ? 0 : parseArray.size();
                    int size3 = (parseArray2 != null || parseArray2.isEmpty()) ? 0 : parseArray2.size();
                    int size4 = (parseArray3 != null || parseArray3.isEmpty()) ? 0 : parseArray3.size();
                    i2 = size + size2 + 0 + size3 + size4;
                    if (i2 <= 0) {
                        return null;
                    }
                    try {
                        ArrayList<MiaoShaProduct> arrayList2 = new ArrayList<>();
                        if (size > 0) {
                            for (int i4 = 0; i4 < i2; i4++) {
                                try {
                                    arrayList2.add(new MiaoShaProduct());
                                } catch (Exception e7) {
                                    e = e7;
                                    arrayList = arrayList2;
                                    OKLog.e(TAG, e);
                                    return arrayList;
                                }
                            }
                            if (size2 > 0) {
                                if (miaoShaBrand != null && miaoShaBrand.position - 1 >= 0 && i3 < i2 && (miaoShaProduct = arrayList2.get(i3)) != null) {
                                    miaoShaProduct.setBrand(miaoShaBrand);
                                }
                                for (int i5 = size2 - 1; i5 >= 0; i5--) {
                                    int i6 = ((MiaoShaBrand) parseArray.get(i5)).position - 1;
                                    if (i6 >= 0 && i6 < i2) {
                                        MiaoShaProduct miaoShaProduct2 = arrayList2.get(i6);
                                        if (miaoShaProduct2 != null && parseArray.get(i5) != miaoShaBrand) {
                                            miaoShaProduct2.setBrand((MiaoShaBrand) parseArray.get(i5));
                                        }
                                    } else {
                                        i2--;
                                        arrayList2.remove(i5);
                                    }
                                }
                            }
                            if (size3 > 0) {
                                for (int i7 = 0; i7 < size3; i7++) {
                                    int i8 = ((MiaoShaBrand) parseArray2.get(i7)).position - 1;
                                    if (i8 >= 0 && i8 < i2) {
                                        MiaoShaProduct miaoShaProduct3 = arrayList2.get(i8);
                                        if (miaoShaProduct3 != null) {
                                            if (miaoShaProduct3.isBrand) {
                                                i2--;
                                            }
                                            miaoShaProduct3.setNewBrand((MiaoShaBrand) parseArray2.get(i7));
                                        }
                                    } else {
                                        i2--;
                                        arrayList2.remove(i7);
                                    }
                                }
                            }
                            if (size4 > 0) {
                                for (int i9 = 0; i9 < size4; i9++) {
                                    int i10 = ((MiaoShaPlus) parseArray3.get(i9)).position - 1;
                                    if (i10 >= 0 && i10 < i2 && arrayList2.get(i10) != null) {
                                        arrayList2.get(i10).setPlus((MiaoShaPlus) parseArray3.get(i9));
                                        arrayList2.get(i10).product = list2.get(i9);
                                    }
                                }
                            }
                            int i11 = 0;
                            for (int i12 = 0; i12 < i2; i12++) {
                                if (i11 < size) {
                                    MiaoShaProduct miaoShaProduct4 = arrayList2.get(i12);
                                    if (miaoShaProduct4 != null && !miaoShaProduct4.isBrand && !miaoShaProduct4.isNewBrand && !miaoShaProduct4.isPlus) {
                                        miaoShaProduct4.product = list.get(i11);
                                        i11++;
                                    }
                                }
                            }
                        }
                        return arrayList2;
                    } catch (Exception e8) {
                        e = e8;
                    }
                }
                parseArray3 = null;
                if (list != null) {
                }
                if (parseArray != null) {
                }
                if (parseArray2 != null) {
                }
                if (parseArray3 != null) {
                }
                i2 = size + size2 + 0 + size3 + size4;
                if (i2 <= 0) {
                }
            }
            parseArray2 = null;
            if (jSONObjectOrNull != null) {
            }
            if (jSONArrayOrNull4 != null) {
            }
            parseArray3 = null;
            if (list != null) {
            }
            if (parseArray != null) {
            }
            if (parseArray2 != null) {
            }
            if (parseArray3 != null) {
            }
            i2 = size + size2 + 0 + size3 + size4;
            if (i2 <= 0) {
            }
        }
        parseArray = null;
        if (jSONArrayOrNull3 != null) {
        }
        parseArray2 = null;
        if (jSONObjectOrNull != null) {
        }
        if (jSONArrayOrNull4 != null) {
        }
        parseArray3 = null;
        if (list != null) {
        }
        if (parseArray != null) {
        }
        if (parseArray2 != null) {
        }
        if (parseArray3 != null) {
        }
        i2 = size + size2 + 0 + size3 + size4;
        if (i2 <= 0) {
        }
    }

    public MiaoShaBrand getBrand() {
        return this.brand;
    }

    public MiaoShaPlus getPlus() {
        return this.plus;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setBrand(MiaoShaBrand miaoShaBrand) {
        this.brand = miaoShaBrand;
        this.isBrand = true;
        this.isNewBrand = false;
        this.isPlus = false;
    }

    public void setCategory(MiaoShaListCategory miaoShaListCategory) {
        this.category = miaoShaListCategory;
        this.isCategory = true;
    }

    public void setNewBrand(MiaoShaBrand miaoShaBrand) {
        this.brand = miaoShaBrand;
        this.isNewBrand = true;
        this.isBrand = false;
        this.isPlus = false;
    }

    public void setPlus(MiaoShaPlus miaoShaPlus) {
        this.plus = miaoShaPlus;
        this.isPlus = true;
        this.isBrand = false;
        this.isNewBrand = false;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public MiaoShaProduct(Product product) {
        this.product = product;
    }
}
