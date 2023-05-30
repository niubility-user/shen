package com.jingdong.common.entity;

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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<com.jingdong.common.entity.MiaoShaProduct> toList(com.jingdong.jdsdk.utils.JSONObjectProxy r15) {
        /*
            Method dump skipped, instructions count: 444
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.entity.MiaoShaProduct.toList(com.jingdong.jdsdk.utils.JSONObjectProxy):java.util.ArrayList");
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
