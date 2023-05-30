package com.jingdong.sdk.lib.puppetlayout.storage;

/* loaded from: classes8.dex */
public class DdStyles {
    public static String get(String str) {
        if (str.equals("40020101")) {
            return "<Style styleId=\"style_1161\" name=\"\u549a\u549a\u9000\u6b3e\u5f39\u7a97\u6837\u5f0f\u6d4b\u8bd5\" supportSdkVersion=\"1.1\" version=\"1.0\" preUrl=\"//img10.360buyimg.com/aplbpm/jfs/t1/52809/1/310/77467/5cd42803Ef962c4b8/c6c5afc25421a727.png\" flexibleWidth=\"0\" flexibleHeight=\"1\" ddTemplate=\"40020101\">\n    <View layout_alignItems=\"stretch\" layout_display=\"flex\" layout_flexDirection=\"col\" layout_justifyContent=\"flex-start\" layout_width=\"100%\" attribute_backgroundColor=\"#FF00FF,#FFFFFF|LEFT_RIGHT\">\n        <View layout_alignItems=\"flex-start\" layout_display=\"flex\" layout_flexDirection=\"row\" layout_justifyContent=\"flex-start\" layout_marginLeft=\"15dp\" layout_marginRight=\"15dp\" layout_marginTop=\"8dp\">\n            <Text attribute_color=\"#999999\" attribute_font=\"Semibold\" attribute_fontSize=\"13dp\" attribute_text=\"\u8ba2\u5355\u7f16\u53f7:\"></Text>\n            <Text attribute_color=\"#131313\" attribute_font=\"Semibold\" attribute_fontSize=\"13dp\" attribute_text=\"{orderId}\" layout_flexGrow=\"1\" layout_marginLeft=\"10dp\"></Text>\n        </View>\n        <View layout_alignItems=\"flex-start\" layout_display=\"flex\" layout_flexDirection=\"row\" layout_justifyContent=\"flex-start\" layout_marginLeft=\"15dp\" layout_marginRight=\"15dp\" layout_marginTop=\"4dp\">\n            <Text attribute_color=\"#999999\" attribute_font=\"Semibold\" attribute_fontSize=\"13dp\" attribute_text=\"\u9000\u6b3e\u91d1\u989d:\"></Text>\n            <Text attribute_color=\"#131313\" attribute_font=\"Semibold\" attribute_fontSize=\"13dp\" attribute_text=\"\uffe5{money}\" layout_flexGrow=\"1\" layout_marginLeft=\"10dp\"></Text>\n        </View>\n        <View layout_alignItems=\"flex-start\" layout_display=\"flex\" layout_flexDirection=\"row\" layout_justifyContent=\"flex-start\" layout_marginLeft=\"15dp\" layout_marginRight=\"15dp\" layout_marginTop=\"4dp\">\n            <Text attribute_color=\"#999999\" attribute_font=\"Semibold\" attribute_fontSize=\"13dp\" attribute_text=\"\u9000\u6b3e\u4f4d\u7f6e:\"></Text>\n            <Text attribute_color=\"#131313\" attribute_font=\"Semibold\" attribute_fontSize=\"13dp\" attribute_text=\"{site}\" layout_flexGrow=\"1\" layout_marginLeft=\"10dp\"></Text>\n        </View>\n        <Text attribute_color=\"#252525\" attribute_font=\"Semibold\" attribute_fontSize=\"13dp\" attribute_text=\"{desc}\" layout_marginLeft=\"15dp\" layout_marginRight=\"15dp\" layout_marginTop=\"9dp\"></Text>\n        <View layout_alignItems=\"flex-start\" layout_display=\"flex\" layout_flexDirection=\"col\" layout_justifyContent=\"flex-start\" layout_marginLeft=\"15dp\" layout_marginRight=\"15dp\" layout_marginTop=\"13dp\">\n            <View layout_alignItems=\"flex-start\" layout_display=\"flex\" layout_flexDirection=\"row\" layout_height=\"1dp\" layout_justifyContent=\"flex-start\" layout_marginTop=\"5\" layout_paddingLeft=\"7%\" layout_paddingRight=\"7%\" layout_position=\"absolute\" layout_width=\"100%\">\n                <Image attribute_clipsToBounds=\"1\" attribute_imageUrl=\"type:local,android:com.jd.lib.icssdk/jd_ics_sdk_dash_line,androidScaleType:1,localImageName:scs_dotline_1dp,resizable:3_0_3_0,resizingMode:0\" layout_alignItems=\"flex-start\" layout_display=\"flex\" layout_flexDirection=\"row\" layout_height=\"1dp\" layout_justifyContent=\"flex-start\" layout_width=\"100%\"></Image>\n            </View>\n            <View layout_alignItems=\"center\" layout_display=\"flex\" layout_flexDirection=\"row\" layout_justifyContent=\"space-between\" layout_width=\"100%\">\n                <Iterate eachPropName=\"item\" indexPropName=\"index\" iterateOn=\"{allFlows}\">\n                    <View layout_alignItems=\"center\" layout_display=\"flex\" layout_flexDirection=\"col\" layout_justifyContent=\"flex-start\" layout_width=\"14%\">\n                        <Image attribute_imageUrl=\"({$item.status}==0?(type:local,android:com.jd.lib.icssdk/scs_reddot_row,androidScaleType:1,localImageName:scs_reddot_row):(type:local,android:com.jd.lib.icssdk/scs_graydot_row,androidScaleType:1,localImageName:scs_graydot_row))\" layout_height=\"15dp\" layout_width=\"15dp\"></Image>\n                        <Text attribute_color=\"({$item.status}==0?#F23030:#999999)\" attribute_fontSize=\"10dp\" attribute_linesNum=\"1\" attribute_text=\"{$item.nodeName}\"></Text>\n                    </View>\n                </Iterate>\n            </View>\n        </View>\n        <View layout_alignItems=\"stretch\" layout_display=\"flex\" layout_flexDirection=\"col\" layout_justifyContent=\"flex-start\" layout_marginLeft=\"15dp\" layout_marginRight=\"15dp\">\n            <Iterate eachPropName=\"item\" indexPropName=\"index\" iterateOn=\"{records}\">\n                <View layout_alignItems=\"stretch\" layout_display=\"flex\" layout_flexDirection=\"row\" layout_justifyContent=\"flex-start\" layout_flexWrap=\"no-wrap\">\n                    <View layout_alignItems=\"center\" layout_display=\"flex\" layout_flexDirection=\"col\" layout_justifyContent=\"flex-start\" layout_width=\"18dp\">\n                        <View layout_alignItems=\"flex-start\" layout_display=\"flex\" layout_flexDirection=\"row\" layout_height=\"25dp\" layout_justifyContent=\"flex-center\" layout_width=\"1px\">\n                            <View attribute_backgroundColor=\"#FFD9D9\" layout_display=\"({$item.index_first}?none:flex)\" layout_height=\"100%\" layout_width=\"1px\"></View>\n                        </View>\n                        <View layout_alignItems=\"flex-start\" layout_display=\"flex\" layout_flexDirection=\"row\" layout_flexGrow=\"1\" layout_height=\"1dp\" layout_justifyContent=\"flex-center\" layout_width=\"1px\">\n                            <View attribute_backgroundColor=\"#FFD9D9\" layout_display=\"({$item.index_last}?none:flex)\" layout_height=\"100%\" layout_width=\"1px\"></View>\n                        </View>\n                        <Image attribute_imageUrl=\"type:local,android:com.jd.lib.icssdk/scs_reddot_col1,androidScaleType:1,localImageName:scs_reddot_col1\" displayType=\"({$item.status}==0}flex:none)\" layout_display=\"({$item.status}==0?flex:none)\" layout_height=\"17dp\" layout_position=\"absolute\" layout_top=\"18dp\" layout_width=\"17dp\"></Image>\n                        <Image attribute_imageUrl=\"type:local,android:com.jd.lib.icssdk/scs_reddot_col2,androidScaleType:1,localImageName:scs_reddot_col2\" displayType=\"({$item.status}==0}:flex:none)\" layout_display=\"({$item.status}==1?flex:none)\" layout_height=\"7.5dp\" layout_position=\"absolute\" layout_top=\"21dp\" layout_width=\"7.5dp\"></Image>\n                    </View>\n                    <View layout_alignItems=\"stretch\" layout_display=\"flex\" layout_flexDirection=\"col\" layout_justifyContent=\"flex-start\" layout_flexGrow=\"1\" layout_flexShrink=\"1\">\n                        <Text attribute_color=\"({$item.status}==1?#666666:#252525)\" attribute_font=\"Semibold\" attribute_fontSize=\"13dp\" attribute_lineBreakMode=\"truncatingTail\" attribute_linesNum=\"2\" attribute_text=\"{$item.nodeDesc}\" layout_marginLeft=\"13dp\" layout_marginTop=\"15dp\"></Text>\n                        <Text attribute_color=\"({$item.status}==1?#666666:#252525)\" attribute_fontSize=\"11dp\" attribute_linesNum=\"1\" attribute_text=\"{$item.nodeTime}\" layout_marginLeft=\"13dp\" layout_marginTop=\"5dp\" layout_marginBottom=\"15dp\"></Text>\n                        <View attribute_backgroundColor=\"#E8EBED\" layout_alignItems=\"flex-start\" layout_display=\"({$item.index_last}?none:flex)\" layout_flexDirection=\"row\" layout_height=\"1dp\" layout_justifyContent=\"flex-start\" layout_marginLeft=\"8dp\"></View>\n                    </View>\n                </View>\n            </Iterate>\n        </View>\n    </View>\n</Style>";
        }
        if (str.equals("30020101")) {
            return "<Style styleId=\"style_1149\" name=\"\u549a\u549a\u9000\u6b3e\u5361\u7247\u6837\u5f0f\u6d4b\u8bd5\" supportSdkVersion=\"1.1\" version=\"1.4\" preUrl=\"//img12.360buyimg.com/aplbpm/jfs/t29974/313/1186486892/84532/ea246f16/5cd95064N8eafdcf1.png\" flexibleWidth=\"0\" flexibleHeight=\"1\" ddTemplate=\"30020101\">\n    <View layout_alignItems=\"stretch\" layout_display=\"flex\" layout_flexDirection=\"col\" layout_justifyContent=\"flex-start\" layout_paddingBottom=\"15dp\" layout_paddingLeft=\"15dp\" layout_paddingRight=\"15dp\" layout_paddingTop=\"15dp\" layout_width=\"100%\">\n        <View attribute_backgroundColor=\"#ffffff\" attribute_borderColor=\"#E8EBED\" attribute_borderWidth=\"1dp\" attribute_roundCornerRadius=\"8\" layout_alignItems=\"stretch\" layout_display=\"flex\" layout_flexDirection=\"col\" layout_justifyContent=\"flex-start\" layout_width=\"100%\">\n            <Text attribute_color=\"#2E2D2D\" attribute_font=\"Semibold\" attribute_fontSize=\"14dp\" attribute_text=\"{tplData.title}\" layout_marginLeft=\"15dp\" layout_marginRight=\"15dp\" layout_marginTop=\"15dp\"></Text>\n            <View layout_alignItems=\"flex-start\" layout_display=\"flex\" layout_flexDirection=\"row\" layout_height=\"1px\" layout_justifyContent=\"flex-start\" layout_marginLeft=\"10dp\" layout_marginRight=\"10dp\" layout_marginTop=\"12dp\">\n                <Image attribute_imageUrl=\"type:local,android:com.jd.lib.icssdk/jd_ics_sdk_dash_line_0,androidScaleType:1,localImageName:scs_dotline_1px,resizable:3_0_3_0,resizingMode:0\" layout_alignItems=\"flex-start\" layout_display=\"flex\" layout_flexBasis=\"1dp\" layout_flexGrow=\"1\" layout_height=\"1px\" layout_justifyContent=\"flex-start\" layout_width=\"100%\"></Image>\n            </View>\n            <View layout_alignItems=\"flex-start\" layout_display=\"flex\" layout_flexDirection=\"row\" layout_justifyContent=\"flex-start\" layout_marginLeft=\"15dp\" layout_marginRight=\"15dp\" layout_marginTop=\"8dp\">\n                <Text attribute_color=\"#999999\" attribute_font=\"Semibold\" attribute_fontSize=\"13dp\" attribute_text=\"\u8ba2\u5355\u7f16\u53f7:\"></Text>\n                <Text attribute_color=\"#131313\" attribute_font=\"Semibold\" attribute_fontSize=\"13dp\" attribute_text=\"{tplData.orderId}\" layout_flexGrow=\"1\" layout_marginLeft=\"10dp\"></Text>\n            </View>\n            <View layout_alignItems=\"flex-start\" layout_display=\"flex\" layout_flexDirection=\"row\" layout_justifyContent=\"flex-start\" layout_marginLeft=\"15dp\" layout_marginRight=\"15dp\" layout_marginTop=\"4dp\">\n                <Text attribute_color=\"#999999\" attribute_font=\"Semibold\" attribute_fontSize=\"13dp\" attribute_text=\"\u9000\u6b3e\u91d1\u989d:\"></Text>\n                <Text attribute_color=\"#131313\" attribute_font=\"Semibold\" attribute_fontSize=\"13dp\" attribute_text=\"\uffe5{tplData.money}\" layout_flexGrow=\"1\" layout_marginLeft=\"10dp\"></Text>\n            </View>\n            <View layout_alignItems=\"flex-start\" layout_display=\"flex\" layout_flexDirection=\"row\" layout_justifyContent=\"flex-start\" layout_marginLeft=\"15dp\" layout_marginRight=\"15dp\" layout_marginTop=\"4dp\">\n                <Text attribute_color=\"#999999\" attribute_font=\"Semibold\" attribute_fontSize=\"13dp\" attribute_text=\"\u9000\u6b3e\u4f4d\u7f6e:\"></Text>\n                <Text attribute_color=\"#131313\" attribute_font=\"Semibold\" attribute_fontSize=\"13dp\" attribute_text=\"{tplData.site}\" layout_flexGrow=\"1\" layout_marginLeft=\"10dp\"></Text>\n            </View>\n            <Text attribute_color=\"#252525\" attribute_font=\"Semibold\" attribute_fontSize=\"13dp\" attribute_text=\"{tplData.desc}\" layout_marginLeft=\"15dp\" layout_marginRight=\"15dp\" layout_marginTop=\"9dp\"></Text>\n            <View layout_alignItems=\"flex-start\" layout_display=\"flex\" layout_flexDirection=\"col\" layout_justifyContent=\"flex-start\" layout_marginLeft=\"15dp\" layout_marginRight=\"15dp\" layout_marginTop=\"13dp\">\n                <View layout_alignItems=\"flex-start\" layout_display=\"flex\" layout_flexDirection=\"row\" layout_height=\"1dp\" layout_justifyContent=\"flex-start\" layout_marginTop=\"5\" layout_paddingLeft=\"7%\" layout_paddingRight=\"7%\" layout_position=\"absolute\" layout_width=\"100%\">\n                    <Image attribute_clipsToBounds=\"1\" attribute_imageUrl=\"type:local,android:com.jd.lib.icssdk/jd_ics_sdk_dash_line,androidScaleType:1,localImageName:scs_dotline_1dp,resizable:3_0_3_0,resizingMode:0\" layout_alignItems=\"flex-start\" layout_display=\"flex\" layout_flexDirection=\"row\" layout_height=\"1dp\" layout_justifyContent=\"flex-start\" layout_width=\"100%\"></Image>\n                </View>\n                <View layout_alignItems=\"center\" layout_display=\"flex\" layout_flexDirection=\"row\" layout_justifyContent=\"space-between\" layout_width=\"100%\">\n                    <Iterate eachPropName=\"item\" indexPropName=\"index\" iterateOn=\"{tplData.allFlows}\">\n                        <View layout_alignItems=\"center\" layout_display=\"flex\" layout_flexDirection=\"col\" layout_justifyContent=\"flex-start\" layout_width=\"14%\">\n                            <Image attribute_imageUrl=\"({$item.status}==0?(type:local,android:com.jd.lib.icssdk/scs_reddot_row,androidScaleType:1,localImageName:scs_reddot_row):(type:local,android:com.jd.lib.icssdk/scs_graydot_row,androidScaleType:1,localImageName:scs_graydot_row))\" layout_height=\"15dp\" layout_width=\"15dp\"></Image>\n                            <Text attribute_color=\"({$item.status}==0?#F23030:#999999)\" attribute_fontSize=\"10dp\" attribute_linesNum=\"1\" attribute_text=\"{$item.nodeName}\"></Text>\n                        </View>\n                    </Iterate>\n                </View>\n            </View>\n            <View layout_alignItems=\"stretch\" layout_display=\"flex\" layout_flexDirection=\"col\" layout_justifyContent=\"flex-start\" layout_marginLeft=\"15dp\" layout_marginRight=\"15dp\">\n                <Iterate eachPropName=\"item\" indexPropName=\"index\" iterateOn=\"{tplData.records}\">\n                    <View layout_alignItems=\"stretch\" layout_display=\"({$item.index}&lt;2?flex:none)\" layout_flexDirection=\"row\" layout_justifyContent=\"flex-start\" layout_flexWrap=\"no-wrap\">\n                        <View layout_alignItems=\"center\" layout_display=\"flex\" layout_flexDirection=\"col\" layout_justifyContent=\"flex-start\" layout_width=\"18dp\">\n                            <View layout_alignItems=\"flex-start\" layout_display=\"flex\" layout_flexDirection=\"row\" layout_height=\"25dp\" layout_justifyContent=\"flex-center\" layout_width=\"1px\">\n                                <View attribute_backgroundColor=\"#FFD9D9\" layout_display=\"({$item.index_first}?none:flex)\" layout_height=\"100%\" layout_width=\"1px\"></View>\n                            </View>\n                            <View layout_alignItems=\"flex-start\" layout_display=\"flex\" layout_flexDirection=\"row\" layout_flexGrow=\"1\" layout_height=\"1dp\" layout_justifyContent=\"flex-center\" layout_width=\"1px\">\n                                <View attribute_backgroundColor=\"#FFD9D9\" layout_display=\"({$item.index_last}?none:flex)\" layout_height=\"100%\" layout_width=\"1px\"></View>\n                            </View>\n                            <Image attribute_imageUrl=\"type:local,android:com.jd.lib.icssdk/scs_reddot_col1,androidScaleType:1,localImageName:scs_reddot_col1\" displayType=\"({$item.status}==0}flex:none)\" layout_display=\"({$item.status}==0?flex:none)\" layout_height=\"17dp\" layout_position=\"absolute\" layout_top=\"18dp\" layout_width=\"17dp\"></Image>\n                            <Image attribute_imageUrl=\"type:local,android:com.jd.lib.icssdk/scs_reddot_col2,androidScaleType:1,localImageName:scs_reddot_col2\" displayType=\"({$item.status}==0}:flex:none)\" layout_display=\"({$item.status}==1?flex:none)\" layout_height=\"7.5dp\" layout_position=\"absolute\" layout_top=\"21dp\" layout_width=\"7.5dp\"></Image>\n                        </View>\n                        <View layout_alignItems=\"stretch\" layout_display=\"flex\" layout_flexDirection=\"col\" layout_justifyContent=\"flex-start\" layout_flexGrow=\"1\" layout_flexShrink=\"1\">\n                            <Text attribute_color=\"({$item.status}==1?#666666:#252525)\" attribute_font=\"Semibold\" attribute_fontSize=\"13dp\" attribute_lineBreakMode=\"truncatingTail\" attribute_linesNum=\"2\" attribute_text=\"{$item.nodeDesc}\" layout_marginLeft=\"13dp\" layout_marginTop=\"15dp\"></Text>\n                            <Text attribute_color=\"({$item.status}==1?#666666:#252525)\" attribute_fontSize=\"11dp\" attribute_linesNum=\"1\" attribute_text=\"{$item.nodeTime}\" layout_marginLeft=\"13dp\" layout_marginTop=\"5dp\" layout_marginBottom=\"15dp\"></Text>\n                            <View attribute_backgroundColor=\"#E8EBED\" layout_alignItems=\"flex-start\" layout_flexDirection=\"row\" layout_height=\"1dp\" layout_justifyContent=\"flex-start\" layout_marginLeft=\"8dp\"></View>\n                        </View>\n                    </View>\n                </Iterate>\n            </View>\n            <Button attribute_backgroundColor=\"#ffffff\" attribute_borderColor=\"#E8E9ED\" attribute_borderWidth=\"1dp\" attribute_color=\"#F23030\" attribute_font=\"Semibold\" attribute_fontSize=\"13dp\" attribute_roundCornerRadius=\"15\" attribute_text=\"\u67e5\u770b\u66f4\u591a\" attribute_textAlign=\"center\" layout_alignSelf=\"center\" layout_height=\"30dp\" layout_marginBottom=\"11dp\" layout_marginTop=\"15dp\" layout_width=\"92dp\">\n                <Actions>\n                    <Action client=\"\" type=\"CallBack\" value=\"type:popup,value:{tplData}\" key=\"0\"></Action>\n                </Actions>\n            </Button>\n        </View>\n    </View>\n</Style>";
        }
        return null;
    }
}