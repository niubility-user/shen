package com.jingdong.app.mall.bundle.styleinfoview.entitys;

/* loaded from: classes3.dex */
public class CommonDLayerItemEntity {
    private final String btn;
    private final int btnBgc;
    private final int btnC;
    private final String ppTitle;
    private final int ppTitleC;

    /* loaded from: classes3.dex */
    public static class LayerBuilder {
        private String btn;
        private int btnBgc;
        private int btnC;
        private String ppTitle;
        private int ppTitleC;

        public CommonDLayerItemEntity build() {
            return new CommonDLayerItemEntity(this);
        }

        public LayerBuilder setBtn(String str) {
            this.btn = str;
            return this;
        }

        public LayerBuilder setBtnBgc(int i2) {
            this.btnBgc = i2;
            return this;
        }

        public LayerBuilder setBtnC(int i2) {
            this.btnC = i2;
            return this;
        }

        public LayerBuilder setPpTitle(String str) {
            this.ppTitle = str;
            return this;
        }

        public LayerBuilder setPpTitleC(int i2) {
            this.ppTitleC = i2;
            return this;
        }
    }

    public String getBtn() {
        return this.btn;
    }

    public int getBtnBgc() {
        return this.btnBgc;
    }

    public int getBtnC() {
        return this.btnC;
    }

    public String getPpTitle() {
        return this.ppTitle;
    }

    public int getPpTitleC() {
        return this.ppTitleC;
    }

    private CommonDLayerItemEntity(LayerBuilder layerBuilder) {
        this.ppTitle = layerBuilder.ppTitle;
        this.ppTitleC = layerBuilder.ppTitleC;
        this.btn = layerBuilder.btn;
        this.btnC = layerBuilder.btnC;
        this.btnBgc = layerBuilder.btnBgc;
    }
}
