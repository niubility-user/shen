package com.jingdong.common.messagepop.template;

/* loaded from: classes5.dex */
public class PopViewTemplateFactory {
    public IPopView getPopView(int i2) {
        switch (i2) {
            case 3:
            case 4:
            case 5:
                return new ChannelPopView();
            case 6:
                return new PureTextPopView();
            case 7:
                return new ImageTextPopView();
            case 8:
                return new ItemLivePopView();
            case 9:
                return new ItemVideoPopView();
            case 10:
                return new ActivityPopView();
            case 11:
                return new BenefitPopView();
            case 12:
            case 13:
            default:
                return null;
            case 14:
                return new BenefitV2PopView();
            case 15:
                return new TemplateFifteenPopView();
            case 16:
                return new TemplateSixTeenPopView();
            case 17:
                return new TemplateSevenTeenPopView();
            case 18:
                return new TranslatePopView();
            case 19:
            case 20:
            case 21:
                return new TemplateImgTextPopView(i2);
        }
    }
}
