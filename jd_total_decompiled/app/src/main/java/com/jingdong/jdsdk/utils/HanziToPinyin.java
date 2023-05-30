package com.jingdong.jdsdk.utils;

import android.text.TextUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Locale;
import jd.wjlogin_sdk.util.ReplyCode;

/* loaded from: classes14.dex */
public class HanziToPinyin {
    private static final boolean DEBUG = false;
    private static final String FIRST_PINYIN_UNIHAN = "\u963f";
    private static final String LAST_PINYIN_UNIHAN = "\u9fff";
    private static final String TAG = "HanziToPinyin";
    private static HanziToPinyin sInstance;
    private final boolean mHasChinaCollator;
    public static final char[] UNIHANS = {'\u963f', '\u54ce', '\u5b89', '\u80ae', '\u51f9', '\u516b', '\u6300', '\u6273', '\u90a6', '\u52f9', '\u9642', '\u5954', '\u4f3b', '\u5c44', '\u8fb9', '\u706c', '\u618b', '\u6c43', '\u51ab', '\u7676', '\u5cec', '\u5693', '\u5072', '\u53c2', '\u4ed3', '\u64a1', '\u518a', '\u5d7e', '\u66fd', '\u66fe', '\u5c64', '\u53c9', '\u8286', '\u8fbf', '\u4f25', '\u6284', '\u8f66', '\u62bb', '\u6c88', '\u6c89', '\u9637', '\u5403', '\u5145', '\u62bd', '\u51fa', '\u6b3b', '\u63e3', '\u5ddb', '\u5205', '\u5439', '\u65fe', '\u9034', '\u5472', '\u5306', '\u51d1', '\u7c97', '\u6c46', '\u5d14', '\u90a8', '\u6413', '\u5491', '\u5446', '\u4e39', '\u5f53', '\u5200', '\u561a', '\u6265', '\u706f', '\u6c10', '\u55f2', '\u7538', '\u5201', '\u7239', '\u4e01', '\u4e1f', '\u4e1c', '\u543a', '\u53be', '\u8011', '\u8968', '\u5428', '\u591a', '\u59b8', '\u8bf6', '\u5940', '\u97a5', '\u513f', '\u53d1', '\u5e06', '\u531a', '\u98de', '\u5206', '\u4e30', '\u8985', '\u4ecf', '\u7d11', '\u4f15', '\u65ee', '\u4f85', '\u7518', '\u5188', '\u768b', '\u6208', '\u7ed9', '\u6839', '\u522f', '\u5de5', '\u52fe', '\u4f30', '\u74dc', '\u4e56', '\u5173', '\u5149', '\u5f52', '\u4e28', '\u5459', '\u54c8', '\u548d', '\u4f44', '\u592f', '\u8320', '\u8bc3', '\u9ed2', '\u62eb', '\u4ea8', '\u5677', '\u53ff', '\u9f41', '\u4e6f', '\u82b1', '\u6000', '\u72bf', '\u5ddf', '\u7070', '\u660f', '\u5419', '\u4e0c', '\u52a0', '\u620b', '\u6c5f', '\u827d', '\u9636', '\u5dfe', '\u5755', '\u5182', '\u4e29', '\u51e5', '\u59e2', '\u5658', '\u519b', '\u5494', '\u5f00', '\u520a', '\u5ffc', '\u5c3b', '\u533c', '\u808e', '\u52a5', '\u7a7a', '\u62a0', '\u625d', '\u5938', '\u84af', '\u5bbd', '\u5321', '\u4e8f', '\u5764', '\u6269', '\u5783', '\u6765', '\u5170', '\u5577', '\u635e', '\u808b', '\u52d2', '\u5d1a', '\u5215', '\u4fe9', '\u5941', '\u826f', '\u64a9', '\u5217', '\u62ce', '\u5222', '\u6e9c', '\u56d6', '\u9f99', '\u779c', '\u565c', '\u5a08', '\u7567', '\u62a1', '\u7f57', '\u5463', '\u5988', '\u57cb', '\u5ada', '\u7264', '\u732b', '\u4e48', '\u5445', '\u95e8', '\u753f', '\u54aa', '\u5b80', '\u55b5', '\u4e5c', '\u6c11', '\u540d', '\u8c2c', '\u6478', '\u54de', '\u6bea', '\u55ef', '\u62cf', '\u8149', '\u56e1', '\u56d4', '\u5b6c', '\u7592', '\u5a1e', '\u6041', '\u80fd', '\u59ae', '\u62c8', '\u5b22', '\u9e1f', '\u634f', '\u56dc', '\u5b81', '\u599e', '\u519c', '\u7fba', '\u5974', '\u597b', '\u759f', '\u9ec1', '\u90cd', '\u5594', '\u8bb4', '\u5991', '\u62cd', '\u7705', '\u4e53', '\u629b', '\u5478', '\u55b7', '\u5309', '\u4e15', '\u56e8', '\u527d', '\u6c15', '\u59d8', '\u4e52', '\u948b', '\u5256', '\u4ec6', '\u4e03', '\u6390', '\u5343', '\u545b', '\u6084', '\u767f', '\u4eb2', '\u72c5', '\u828e', '\u4e18', '\u533a', '\u5cd1', '\u7f3a', '\u590b', '\u5465', '\u7a63', '\u5a06', '\u60f9', '\u4eba', '\u6254', '\u65e5', '\u8338', '\u53b9', '\u909a', '\u633c', '\u5827', '\u5a51', '\u77a4', '\u637c', '\u4ee8', '\u6be2', '\u4e09', '\u6852', '\u63bb', '\u95aa', '\u68ee', '\u50e7', '\u6740', '\u7b5b', '\u5c71', '\u4f24', '\u5f30', '\u5962', '\u7533', '\u8398', '\u6552', '\u5347', '\u5c38', '\u53ce', '\u4e66', '\u5237', '\u8870', '\u95e9', '\u53cc', '\u8c01', '\u542e', '\u8bf4', '\u53b6', '\u5fea', '\u635c', '\u82cf', '\u72fb', '\u590a', '\u5b59', '\u5506', '\u4ed6', '\u56fc', '\u574d', '\u6c64', '\u5932', '\u5fd1', '\u71a5', '\u5254', '\u5929', '\u65eb', '\u5e16', '\u5385', '\u56f2', '\u5077', '\u51f8', '\u6e4d', '\u63a8', '\u541e', '\u4e47', '\u7a75', '\u6b6a', '\u5f2f', '\u5c23', '\u5371', '\u6637', '\u7fc1', '\u631d', '\u4e4c', '\u5915', '\u8672', '\u4eda', '\u4e61', '\u7071', '\u4e9b', '\u5fc3', '\u661f', '\u51f6', '\u4f11', '\u5401', '\u5405', '\u524a', '\u5743', '\u4e2b', '\u6079', '\u592e', '\u5e7a', '\u503b', '\u4e00', '\u56d9', '\u5e94', '\u54df', '\u4f63', '\u4f18', '\u625c', '\u56e6', '\u66f0', '\u6655', '\u7b60', '\u7b7c', '\u5e00', '\u707d', '\u5142', '\u5328', '\u50ae', '\u5219', '\u8d3c', '\u600e', '\u5897', '\u624e', '\u635a', '\u6cbe', '\u5f20', '\u957f', '\u9577', '\u4f4b', '\u8707', '\u8d1e', '\u4e89', '\u4e4b', '\u5cd9', '\u5ea2', '\u4e2d', '\u5dde', '\u6731', '\u6293', '\u62fd', '\u4e13', '\u5986', '\u96b9', '\u5b92', '\u5353', '\u4e72', '\u5b97', '\u90b9', '\u79df', '\u94bb', '\u539c', '\u5c0a', '\u6628', '\u5159', '\u9fc3', '\u9fc4'};
    public static final byte[][] PINYINS = {new byte[]{65, 0, 0, 0, 0, 0}, new byte[]{65, 73, 0, 0, 0, 0}, new byte[]{65, 78, 0, 0, 0, 0}, new byte[]{65, 78, 71, 0, 0, 0}, new byte[]{65, ReplyCode.reply0x4f, 0, 0, 0, 0}, new byte[]{66, 65, 0, 0, 0, 0}, new byte[]{66, 65, 73, 0, 0, 0}, new byte[]{66, 65, 78, 0, 0, 0}, new byte[]{66, 65, 78, 71, 0, 0}, new byte[]{66, 65, ReplyCode.reply0x4f, 0, 0, 0}, new byte[]{66, 69, 73, 0, 0, 0}, new byte[]{66, 69, 78, 0, 0, 0}, new byte[]{66, 69, 78, 71, 0, 0}, new byte[]{66, 73, 0, 0, 0, 0}, new byte[]{66, 73, 65, 78, 0, 0}, new byte[]{66, 73, 65, ReplyCode.reply0x4f, 0, 0}, new byte[]{66, 73, 69, 0, 0, 0}, new byte[]{66, 73, 78, 0, 0, 0}, new byte[]{66, 73, 78, 71, 0, 0}, new byte[]{66, ReplyCode.reply0x4f, 0, 0, 0, 0}, new byte[]{66, 85, 0, 0, 0, 0}, new byte[]{67, 65, 0, 0, 0, 0}, new byte[]{67, 65, 73, 0, 0, 0}, new byte[]{67, 65, 78, 0, 0, 0}, new byte[]{67, 65, 78, 71, 0, 0}, new byte[]{67, 65, ReplyCode.reply0x4f, 0, 0, 0}, new byte[]{67, 69, 0, 0, 0, 0}, new byte[]{67, 69, 78, 0, 0, 0}, new byte[]{67, 69, 78, 71, 0, 0}, new byte[]{90, 69, 78, 71, 0, 0}, new byte[]{67, 69, 78, 71, 0, 0}, new byte[]{67, 72, 65, 0, 0, 0}, new byte[]{67, 72, 65, 73, 0, 0}, new byte[]{67, 72, 65, 78, 0, 0}, new byte[]{67, 72, 65, 78, 71, 0}, new byte[]{67, 72, 65, ReplyCode.reply0x4f, 0, 0}, new byte[]{67, 72, 69, 0, 0, 0}, new byte[]{67, 72, 69, 78, 0, 0}, new byte[]{83, 72, 69, 78, 0, 0}, new byte[]{67, 72, 69, 78, 0, 0}, new byte[]{67, 72, 69, 78, 71, 0}, new byte[]{67, 72, 73, 0, 0, 0}, new byte[]{67, 72, ReplyCode.reply0x4f, 78, 71, 0}, new byte[]{67, 72, ReplyCode.reply0x4f, 85, 0, 0}, new byte[]{67, 72, 85, 0, 0, 0}, new byte[]{67, 72, 85, 65, 0, 0}, new byte[]{67, 72, 85, 65, 73, 0}, new byte[]{67, 72, 85, 65, 78, 0}, new byte[]{67, 72, 85, 65, 78, 71}, new byte[]{67, 72, 85, 73, 0, 0}, new byte[]{67, 72, 85, 78, 0, 0}, new byte[]{67, 72, 85, ReplyCode.reply0x4f, 0, 0}, new byte[]{67, 73, 0, 0, 0, 0}, new byte[]{67, ReplyCode.reply0x4f, 78, 71, 0, 0}, new byte[]{67, ReplyCode.reply0x4f, 85, 0, 0, 0}, new byte[]{67, 85, 0, 0, 0, 0}, new byte[]{67, 85, 65, 78, 0, 0}, new byte[]{67, 85, 73, 0, 0, 0}, new byte[]{67, 85, 78, 0, 0, 0}, new byte[]{67, 85, ReplyCode.reply0x4f, 0, 0, 0}, new byte[]{68, 65, 0, 0, 0, 0}, new byte[]{68, 65, 73, 0, 0, 0}, new byte[]{68, 65, 78, 0, 0, 0}, new byte[]{68, 65, 78, 71, 0, 0}, new byte[]{68, 65, ReplyCode.reply0x4f, 0, 0, 0}, new byte[]{68, 69, 0, 0, 0, 0}, new byte[]{68, 69, 78, 0, 0, 0}, new byte[]{68, 69, 78, 71, 0, 0}, new byte[]{68, 73, 0, 0, 0, 0}, new byte[]{68, 73, 65, 0, 0, 0}, new byte[]{68, 73, 65, 78, 0, 0}, new byte[]{68, 73, 65, ReplyCode.reply0x4f, 0, 0}, new byte[]{68, 73, 69, 0, 0, 0}, new byte[]{68, 73, 78, 71, 0, 0}, new byte[]{68, 73, 85, 0, 0, 0}, new byte[]{68, ReplyCode.reply0x4f, 78, 71, 0, 0}, new byte[]{68, ReplyCode.reply0x4f, 85, 0, 0, 0}, new byte[]{68, 85, 0, 0, 0, 0}, new byte[]{68, 85, 65, 78, 0, 0}, new byte[]{68, 85, 73, 0, 0, 0}, new byte[]{68, 85, 78, 0, 0, 0}, new byte[]{68, 85, ReplyCode.reply0x4f, 0, 0, 0}, new byte[]{69, 0, 0, 0, 0, 0}, new byte[]{69, 73, 0, 0, 0, 0}, new byte[]{69, 78, 0, 0, 0, 0}, new byte[]{69, 78, 71, 0, 0, 0}, new byte[]{69, 82, 0, 0, 0, 0}, new byte[]{70, 65, 0, 0, 0, 0}, new byte[]{70, 65, 78, 0, 0, 0}, new byte[]{70, 65, 78, 71, 0, 0}, new byte[]{70, 69, 73, 0, 0, 0}, new byte[]{70, 69, 78, 0, 0, 0}, new byte[]{70, 69, 78, 71, 0, 0}, new byte[]{70, 73, 65, ReplyCode.reply0x4f, 0, 0}, new byte[]{70, ReplyCode.reply0x4f, 0, 0, 0, 0}, new byte[]{70, ReplyCode.reply0x4f, 85, 0, 0, 0}, new byte[]{70, 85, 0, 0, 0, 0}, new byte[]{71, 65, 0, 0, 0, 0}, new byte[]{71, 65, 73, 0, 0, 0}, new byte[]{71, 65, 78, 0, 0, 0}, new byte[]{71, 65, 78, 71, 0, 0}, new byte[]{71, 65, ReplyCode.reply0x4f, 0, 0, 0}, new byte[]{71, 69, 0, 0, 0, 0}, new byte[]{71, 69, 73, 0, 0, 0}, new byte[]{71, 69, 78, 0, 0, 0}, new byte[]{71, 69, 78, 71, 0, 0}, new byte[]{71, ReplyCode.reply0x4f, 78, 71, 0, 0}, new byte[]{71, ReplyCode.reply0x4f, 85, 0, 0, 0}, new byte[]{71, 85, 0, 0, 0, 0}, new byte[]{71, 85, 65, 0, 0, 0}, new byte[]{71, 85, 65, 73, 0, 0}, new byte[]{71, 85, 65, 78, 0, 0}, new byte[]{71, 85, 65, 78, 71, 0}, new byte[]{71, 85, 73, 0, 0, 0}, new byte[]{71, 85, 78, 0, 0, 0}, new byte[]{71, 85, ReplyCode.reply0x4f, 0, 0, 0}, new byte[]{72, 65, 0, 0, 0, 0}, new byte[]{72, 65, 73, 0, 0, 0}, new byte[]{72, 65, 78, 0, 0, 0}, new byte[]{72, 65, 78, 71, 0, 0}, new byte[]{72, 65, ReplyCode.reply0x4f, 0, 0, 0}, new byte[]{72, 69, 0, 0, 0, 0}, new byte[]{72, 69, 73, 0, 0, 0}, new byte[]{72, 69, 78, 0, 0, 0}, new byte[]{72, 69, 78, 71, 0, 0}, new byte[]{72, 77, 0, 0, 0, 0}, new byte[]{72, ReplyCode.reply0x4f, 78, 71, 0, 0}, new byte[]{72, ReplyCode.reply0x4f, 85, 0, 0, 0}, new byte[]{72, 85, 0, 0, 0, 0}, new byte[]{72, 85, 65, 0, 0, 0}, new byte[]{72, 85, 65, 73, 0, 0}, new byte[]{72, 85, 65, 78, 0, 0}, new byte[]{72, 85, 65, 78, 71, 0}, new byte[]{72, 85, 73, 0, 0, 0}, new byte[]{72, 85, 78, 0, 0, 0}, new byte[]{72, 85, ReplyCode.reply0x4f, 0, 0, 0}, new byte[]{74, 73, 0, 0, 0, 0}, new byte[]{74, 73, 65, 0, 0, 0}, new byte[]{74, 73, 65, 78, 0, 0}, new byte[]{74, 73, 65, 78, 71, 0}, new byte[]{74, 73, 65, ReplyCode.reply0x4f, 0, 0}, new byte[]{74, 73, 69, 0, 0, 0}, new byte[]{74, 73, 78, 0, 0, 0}, new byte[]{74, 73, 78, 71, 0, 0}, new byte[]{74, 73, ReplyCode.reply0x4f, 78, 71, 0}, new byte[]{74, 73, 85, 0, 0, 0}, new byte[]{74, 85, 0, 0, 0, 0}, new byte[]{74, 85, 65, 78, 0, 0}, new byte[]{74, 85, 69, 0, 0, 0}, new byte[]{74, 85, 78, 0, 0, 0}, new byte[]{75, 65, 0, 0, 0, 0}, new byte[]{75, 65, 73, 0, 0, 0}, new byte[]{75, 65, 78, 0, 0, 0}, new byte[]{75, 65, 78, 71, 0, 0}, new byte[]{75, 65, ReplyCode.reply0x4f, 0, 0, 0}, new byte[]{75, 69, 0, 0, 0, 0}, new byte[]{75, 69, 78, 0, 0, 0}, new byte[]{75, 69, 78, 71, 0, 0}, new byte[]{75, ReplyCode.reply0x4f, 78, 71, 0, 0}, new byte[]{75, ReplyCode.reply0x4f, 85, 0, 0, 0}, new byte[]{75, 85, 0, 0, 0, 0}, new byte[]{75, 85, 65, 0, 0, 0}, new byte[]{75, 85, 65, 73, 0, 0}, new byte[]{75, 85, 65, 78, 0, 0}, new byte[]{75, 85, 65, 78, 71, 0}, new byte[]{75, 85, 73, 0, 0, 0}, new byte[]{75, 85, 78, 0, 0, 0}, new byte[]{75, 85, ReplyCode.reply0x4f, 0, 0, 0}, new byte[]{76, 65, 0, 0, 0, 0}, new byte[]{76, 65, 73, 0, 0, 0}, new byte[]{76, 65, 78, 0, 0, 0}, new byte[]{76, 65, 78, 71, 0, 0}, new byte[]{76, 65, ReplyCode.reply0x4f, 0, 0, 0}, new byte[]{76, 69, 0, 0, 0, 0}, new byte[]{76, 69, 73, 0, 0, 0}, new byte[]{76, 69, 78, 71, 0, 0}, new byte[]{76, 73, 0, 0, 0, 0}, new byte[]{76, 73, 65, 0, 0, 0}, new byte[]{76, 73, 65, 78, 0, 0}, new byte[]{76, 73, 65, 78, 71, 0}, new byte[]{76, 73, 65, ReplyCode.reply0x4f, 0, 0}, new byte[]{76, 73, 69, 0, 0, 0}, new byte[]{76, 73, 78, 0, 0, 0}, new byte[]{76, 73, 78, 71, 0, 0}, new byte[]{76, 73, 85, 0, 0, 0}, new byte[]{76, ReplyCode.reply0x4f, 0, 0, 0, 0}, new byte[]{76, ReplyCode.reply0x4f, 78, 71, 0, 0}, new byte[]{76, ReplyCode.reply0x4f, 85, 0, 0, 0}, new byte[]{76, 85, 0, 0, 0, 0}, new byte[]{76, 85, 65, 78, 0, 0}, new byte[]{76, 85, 69, 0, 0, 0}, new byte[]{76, 85, 78, 0, 0, 0}, new byte[]{76, 85, ReplyCode.reply0x4f, 0, 0, 0}, new byte[]{77, 0, 0, 0, 0, 0}, new byte[]{77, 65, 0, 0, 0, 0}, new byte[]{77, 65, 73, 0, 0, 0}, new byte[]{77, 65, 78, 0, 0, 0}, new byte[]{77, 65, 78, 71, 0, 0}, new byte[]{77, 65, ReplyCode.reply0x4f, 0, 0, 0}, new byte[]{77, 69, 0, 0, 0, 0}, new byte[]{77, 69, 73, 0, 0, 0}, new byte[]{77, 69, 78, 0, 0, 0}, new byte[]{77, 69, 78, 71, 0, 0}, new byte[]{77, 73, 0, 0, 0, 0}, new byte[]{77, 73, 65, 78, 0, 0}, new byte[]{77, 73, 65, ReplyCode.reply0x4f, 0, 0}, new byte[]{77, 73, 69, 0, 0, 0}, new byte[]{77, 73, 78, 0, 0, 0}, new byte[]{77, 73, 78, 71, 0, 0}, new byte[]{77, 73, 85, 0, 0, 0}, new byte[]{77, ReplyCode.reply0x4f, 0, 0, 0, 0}, new byte[]{77, ReplyCode.reply0x4f, 85, 0, 0, 0}, new byte[]{77, 85, 0, 0, 0, 0}, new byte[]{78, 0, 0, 0, 0, 0}, new byte[]{78, 65, 0, 0, 0, 0}, new byte[]{78, 65, 73, 0, 0, 0}, new byte[]{78, 65, 78, 0, 0, 0}, new byte[]{78, 65, 78, 71, 0, 0}, new byte[]{78, 65, ReplyCode.reply0x4f, 0, 0, 0}, new byte[]{78, 69, 0, 0, 0, 0}, new byte[]{78, 69, 73, 0, 0, 0}, new byte[]{78, 69, 78, 0, 0, 0}, new byte[]{78, 69, 78, 71, 0, 0}, new byte[]{78, 73, 0, 0, 0, 0}, new byte[]{78, 73, 65, 78, 0, 0}, new byte[]{78, 73, 65, 78, 71, 0}, new byte[]{78, 73, 65, ReplyCode.reply0x4f, 0, 0}, new byte[]{78, 73, 69, 0, 0, 0}, new byte[]{78, 73, 78, 0, 0, 0}, new byte[]{78, 73, 78, 71, 0, 0}, new byte[]{78, 73, 85, 0, 0, 0}, new byte[]{78, ReplyCode.reply0x4f, 78, 71, 0, 0}, new byte[]{78, ReplyCode.reply0x4f, 85, 0, 0, 0}, new byte[]{78, 85, 0, 0, 0, 0}, new byte[]{78, 85, 65, 78, 0, 0}, new byte[]{78, 85, 69, 0, 0, 0}, new byte[]{78, 85, 78, 0, 0, 0}, new byte[]{78, 85, ReplyCode.reply0x4f, 0, 0, 0}, new byte[]{ReplyCode.reply0x4f, 0, 0, 0, 0, 0}, new byte[]{ReplyCode.reply0x4f, 85, 0, 0, 0, 0}, new byte[]{80, 65, 0, 0, 0, 0}, new byte[]{80, 65, 73, 0, 0, 0}, new byte[]{80, 65, 78, 0, 0, 0}, new byte[]{80, 65, 78, 71, 0, 0}, new byte[]{80, 65, ReplyCode.reply0x4f, 0, 0, 0}, new byte[]{80, 69, 73, 0, 0, 0}, new byte[]{80, 69, 78, 0, 0, 0}, new byte[]{80, 69, 78, 71, 0, 0}, new byte[]{80, 73, 0, 0, 0, 0}, new byte[]{80, 73, 65, 78, 0, 0}, new byte[]{80, 73, 65, ReplyCode.reply0x4f, 0, 0}, new byte[]{80, 73, 69, 0, 0, 0}, new byte[]{80, 73, 78, 0, 0, 0}, new byte[]{80, 73, 78, 71, 0, 0}, new byte[]{80, ReplyCode.reply0x4f, 0, 0, 0, 0}, new byte[]{80, ReplyCode.reply0x4f, 85, 0, 0, 0}, new byte[]{80, 85, 0, 0, 0, 0}, new byte[]{81, 73, 0, 0, 0, 0}, new byte[]{81, 73, 65, 0, 0, 0}, new byte[]{81, 73, 65, 78, 0, 0}, new byte[]{81, 73, 65, 78, 71, 0}, new byte[]{81, 73, 65, ReplyCode.reply0x4f, 0, 0}, new byte[]{81, 73, 69, 0, 0, 0}, new byte[]{81, 73, 78, 0, 0, 0}, new byte[]{81, 73, 78, 71, 0, 0}, new byte[]{81, 73, ReplyCode.reply0x4f, 78, 71, 0}, new byte[]{81, 73, 85, 0, 0, 0}, new byte[]{81, 85, 0, 0, 0, 0}, new byte[]{81, 85, 65, 78, 0, 0}, new byte[]{81, 85, 69, 0, 0, 0}, new byte[]{81, 85, 78, 0, 0, 0}, new byte[]{82, 65, 78, 0, 0, 0}, new byte[]{82, 65, 78, 71, 0, 0}, new byte[]{82, 65, ReplyCode.reply0x4f, 0, 0, 0}, new byte[]{82, 69, 0, 0, 0, 0}, new byte[]{82, 69, 78, 0, 0, 0}, new byte[]{82, 69, 78, 71, 0, 0}, new byte[]{82, 73, 0, 0, 0, 0}, new byte[]{82, ReplyCode.reply0x4f, 78, 71, 0, 0}, new byte[]{82, ReplyCode.reply0x4f, 85, 0, 0, 0}, new byte[]{82, 85, 0, 0, 0, 0}, new byte[]{82, 85, 65, 0, 0, 0}, new byte[]{82, 85, 65, 78, 0, 0}, new byte[]{82, 85, 73, 0, 0, 0}, new byte[]{82, 85, 78, 0, 0, 0}, new byte[]{82, 85, ReplyCode.reply0x4f, 0, 0, 0}, new byte[]{83, 65, 0, 0, 0, 0}, new byte[]{83, 65, 73, 0, 0, 0}, new byte[]{83, 65, 78, 0, 0, 0}, new byte[]{83, 65, 78, 71, 0, 0}, new byte[]{83, 65, ReplyCode.reply0x4f, 0, 0, 0}, new byte[]{83, 69, 0, 0, 0, 0}, new byte[]{83, 69, 78, 0, 0, 0}, new byte[]{83, 69, 78, 71, 0, 0}, new byte[]{83, 72, 65, 0, 0, 0}, new byte[]{83, 72, 65, 73, 0, 0}, new byte[]{83, 72, 65, 78, 0, 0}, new byte[]{83, 72, 65, 78, 71, 0}, new byte[]{83, 72, 65, ReplyCode.reply0x4f, 0, 0}, new byte[]{83, 72, 69, 0, 0, 0}, new byte[]{83, 72, 69, 78, 0, 0}, new byte[]{88, 73, 78, 0, 0, 0}, new byte[]{83, 72, 69, 78, 0, 0}, new byte[]{83, 72, 69, 78, 71, 0}, new byte[]{83, 72, 73, 0, 0, 0}, new byte[]{83, 72, ReplyCode.reply0x4f, 85, 0, 0}, new byte[]{83, 72, 85, 0, 0, 0}, new byte[]{83, 72, 85, 65, 0, 0}, new byte[]{83, 72, 85, 65, 73, 0}, new byte[]{83, 72, 85, 65, 78, 0}, new byte[]{83, 72, 85, 65, 78, 71}, new byte[]{83, 72, 85, 73, 0, 0}, new byte[]{83, 72, 85, 78, 0, 0}, new byte[]{83, 72, 85, ReplyCode.reply0x4f, 0, 0}, new byte[]{83, 73, 0, 0, 0, 0}, new byte[]{83, ReplyCode.reply0x4f, 78, 71, 0, 0}, new byte[]{83, ReplyCode.reply0x4f, 85, 0, 0, 0}, new byte[]{83, 85, 0, 0, 0, 0}, new byte[]{83, 85, 65, 78, 0, 0}, new byte[]{83, 85, 73, 0, 0, 0}, new byte[]{83, 85, 78, 0, 0, 0}, new byte[]{83, 85, ReplyCode.reply0x4f, 0, 0, 0}, new byte[]{84, 65, 0, 0, 0, 0}, new byte[]{84, 65, 73, 0, 0, 0}, new byte[]{84, 65, 78, 0, 0, 0}, new byte[]{84, 65, 78, 71, 0, 0}, new byte[]{84, 65, ReplyCode.reply0x4f, 0, 0, 0}, new byte[]{84, 69, 0, 0, 0, 0}, new byte[]{84, 69, 78, 71, 0, 0}, new byte[]{84, 73, 0, 0, 0, 0}, new byte[]{84, 73, 65, 78, 0, 0}, new byte[]{84, 73, 65, ReplyCode.reply0x4f, 0, 0}, new byte[]{84, 73, 69, 0, 0, 0}, new byte[]{84, 73, 78, 71, 0, 0}, new byte[]{84, ReplyCode.reply0x4f, 78, 71, 0, 0}, new byte[]{84, ReplyCode.reply0x4f, 85, 0, 0, 0}, new byte[]{84, 85, 0, 0, 0, 0}, new byte[]{84, 85, 65, 78, 0, 0}, new byte[]{84, 85, 73, 0, 0, 0}, new byte[]{84, 85, 78, 0, 0, 0}, new byte[]{84, 85, ReplyCode.reply0x4f, 0, 0, 0}, new byte[]{87, 65, 0, 0, 0, 0}, new byte[]{87, 65, 73, 0, 0, 0}, new byte[]{87, 65, 78, 0, 0, 0}, new byte[]{87, 65, 78, 71, 0, 0}, new byte[]{87, 69, 73, 0, 0, 0}, new byte[]{87, 69, 78, 0, 0, 0}, new byte[]{87, 69, 78, 71, 0, 0}, new byte[]{87, ReplyCode.reply0x4f, 0, 0, 0, 0}, new byte[]{87, 85, 0, 0, 0, 0}, new byte[]{88, 73, 0, 0, 0, 0}, new byte[]{88, 73, 65, 0, 0, 0}, new byte[]{88, 73, 65, 78, 0, 0}, new byte[]{88, 73, 65, 78, 71, 0}, new byte[]{88, 73, 65, ReplyCode.reply0x4f, 0, 0}, new byte[]{88, 73, 69, 0, 0, 0}, new byte[]{88, 73, 78, 0, 0, 0}, new byte[]{88, 73, 78, 71, 0, 0}, new byte[]{88, 73, ReplyCode.reply0x4f, 78, 71, 0}, new byte[]{88, 73, 85, 0, 0, 0}, new byte[]{88, 85, 0, 0, 0, 0}, new byte[]{88, 85, 65, 78, 0, 0}, new byte[]{88, 85, 69, 0, 0, 0}, new byte[]{88, 85, 78, 0, 0, 0}, new byte[]{89, 65, 0, 0, 0, 0}, new byte[]{89, 65, 78, 0, 0, 0}, new byte[]{89, 65, 78, 71, 0, 0}, new byte[]{89, 65, ReplyCode.reply0x4f, 0, 0, 0}, new byte[]{89, 69, 0, 0, 0, 0}, new byte[]{89, 73, 0, 0, 0, 0}, new byte[]{89, 73, 78, 0, 0, 0}, new byte[]{89, 73, 78, 71, 0, 0}, new byte[]{89, ReplyCode.reply0x4f, 0, 0, 0, 0}, new byte[]{89, ReplyCode.reply0x4f, 78, 71, 0, 0}, new byte[]{89, ReplyCode.reply0x4f, 85, 0, 0, 0}, new byte[]{89, 85, 0, 0, 0, 0}, new byte[]{89, 85, 65, 78, 0, 0}, new byte[]{89, 85, 69, 0, 0, 0}, new byte[]{89, 85, 78, 0, 0, 0}, new byte[]{74, 85, 78, 0, 0, 0}, new byte[]{89, 85, 78, 0, 0, 0}, new byte[]{90, 65, 0, 0, 0, 0}, new byte[]{90, 65, 73, 0, 0, 0}, new byte[]{90, 65, 78, 0, 0, 0}, new byte[]{90, 65, 78, 71, 0, 0}, new byte[]{90, 65, ReplyCode.reply0x4f, 0, 0, 0}, new byte[]{90, 69, 0, 0, 0, 0}, new byte[]{90, 69, 73, 0, 0, 0}, new byte[]{90, 69, 78, 0, 0, 0}, new byte[]{90, 69, 78, 71, 0, 0}, new byte[]{90, 72, 65, 0, 0, 0}, new byte[]{90, 72, 65, 73, 0, 0}, new byte[]{90, 72, 65, 78, 0, 0}, new byte[]{90, 72, 65, 78, 71, 0}, new byte[]{67, 72, 65, 78, 71, 0}, new byte[]{90, 72, 65, 78, 71, 0}, new byte[]{90, 72, 65, ReplyCode.reply0x4f, 0, 0}, new byte[]{90, 72, 69, 0, 0, 0}, new byte[]{90, 72, 69, 78, 0, 0}, new byte[]{90, 72, 69, 78, 71, 0}, new byte[]{90, 72, 73, 0, 0, 0}, new byte[]{83, 72, 73, 0, 0, 0}, new byte[]{90, 72, 73, 0, 0, 0}, new byte[]{90, 72, ReplyCode.reply0x4f, 78, 71, 0}, new byte[]{90, 72, ReplyCode.reply0x4f, 85, 0, 0}, new byte[]{90, 72, 85, 0, 0, 0}, new byte[]{90, 72, 85, 65, 0, 0}, new byte[]{90, 72, 85, 65, 73, 0}, new byte[]{90, 72, 85, 65, 78, 0}, new byte[]{90, 72, 85, 65, 78, 71}, new byte[]{90, 72, 85, 73, 0, 0}, new byte[]{90, 72, 85, 78, 0, 0}, new byte[]{90, 72, 85, ReplyCode.reply0x4f, 0, 0}, new byte[]{90, 73, 0, 0, 0, 0}, new byte[]{90, ReplyCode.reply0x4f, 78, 71, 0, 0}, new byte[]{90, ReplyCode.reply0x4f, 85, 0, 0, 0}, new byte[]{90, 85, 0, 0, 0, 0}, new byte[]{90, 85, 65, 78, 0, 0}, new byte[]{90, 85, 73, 0, 0, 0}, new byte[]{90, 85, 78, 0, 0, 0}, new byte[]{90, 85, ReplyCode.reply0x4f, 0, 0, 0}, new byte[]{0, 0, 0, 0, 0, 0}, new byte[]{83, 72, 65, 78, 0, 0}, new byte[]{0, 0, 0, 0, 0, 0}};
    private static final Collator COLLATOR = Collator.getInstance(Locale.CHINA);

    /* loaded from: classes14.dex */
    public static class a {
        public int a;
        public String b;

        /* renamed from: c  reason: collision with root package name */
        public String f12930c;

        public a() {
        }

        public a(int i2, String str, String str2) {
            this.a = i2;
            this.b = str;
            this.f12930c = str2;
        }
    }

    protected HanziToPinyin(boolean z) {
        this.mHasChinaCollator = z;
    }

    private void addToken(StringBuilder sb, ArrayList<a> arrayList, int i2) {
        String sb2 = sb.toString();
        arrayList.add(new a(i2, sb2, sb2));
        sb.setLength(0);
    }

    private static boolean doSelfValidation() {
        char[] cArr = UNIHANS;
        char c2 = cArr[0];
        String ch = Character.toString(c2);
        for (char c3 : cArr) {
            if (c2 != c3) {
                String ch2 = Character.toString(c3);
                if (COLLATOR.compare(ch, ch2) >= 0) {
                    OKLog.e(TAG, "Internal error in Unihan table. The last string \"" + ch + "\" is greater than current string \"" + ch2 + "\".");
                    return false;
                }
                ch = ch2;
            }
        }
        return true;
    }

    public static HanziToPinyin getInstance() {
        int i2;
        synchronized (HanziToPinyin.class) {
            HanziToPinyin hanziToPinyin = sInstance;
            if (hanziToPinyin != null) {
                return hanziToPinyin;
            }
            Locale[] availableLocales = Collator.getAvailableLocales();
            Locale locale = new Locale("zh");
            while (i2 < availableLocales.length) {
                i2 = (availableLocales[i2].equals(Locale.CHINA) || availableLocales[i2].equals(locale)) ? 0 : i2 + 1;
                HanziToPinyin hanziToPinyin2 = new HanziToPinyin(true);
                sInstance = hanziToPinyin2;
                return hanziToPinyin2;
            }
            OKLog.w(TAG, "There is no Chinese collator, HanziToPinyin is disabled");
            HanziToPinyin hanziToPinyin3 = new HanziToPinyin(false);
            sInstance = hanziToPinyin3;
            return hanziToPinyin3;
        }
    }

    private a getToken(char c2) {
        int i2;
        a aVar = new a();
        String ch = Character.toString(c2);
        aVar.b = ch;
        if (c2 < '\u0100') {
            aVar.a = 1;
            aVar.f12930c = ch;
            return aVar;
        }
        Collator collator = COLLATOR;
        int compare = collator.compare(ch, FIRST_PINYIN_UNIHAN);
        if (compare < 0) {
            aVar.a = 3;
            aVar.f12930c = ch;
            return aVar;
        }
        int i3 = 0;
        if (compare == 0) {
            aVar.a = 2;
            i2 = 0;
        } else {
            compare = collator.compare(ch, LAST_PINYIN_UNIHAN);
            if (compare > 0) {
                aVar.a = 3;
                aVar.f12930c = ch;
                return aVar;
            } else if (compare == 0) {
                aVar.a = 2;
                i2 = UNIHANS.length - 1;
            } else {
                i2 = -1;
            }
        }
        aVar.a = 2;
        if (i2 < 0) {
            int length = UNIHANS.length - 1;
            int i4 = 0;
            while (i4 <= length) {
                i2 = (i4 + length) / 2;
                compare = COLLATOR.compare(ch, Character.toString(UNIHANS[i2]));
                if (compare == 0) {
                    break;
                } else if (compare > 0) {
                    i4 = i2 + 1;
                } else {
                    length = i2 - 1;
                }
            }
        }
        if (compare < 0) {
            i2--;
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            byte[][] bArr = PINYINS;
            if (i3 >= bArr[i2].length || bArr[i2][i3] == 0) {
                break;
            }
            sb.append((char) bArr[i2][i3]);
            i3++;
        }
        String sb2 = sb.toString();
        aVar.f12930c = sb2;
        if (TextUtils.isEmpty(sb2)) {
            aVar.a = 3;
            aVar.f12930c = aVar.b;
        }
        return aVar;
    }

    public ArrayList<a> get(String str) {
        ArrayList<a> arrayList = new ArrayList<>();
        if (this.mHasChinaCollator && !TextUtils.isEmpty(str)) {
            int length = str.length();
            StringBuilder sb = new StringBuilder();
            int i2 = 1;
            for (int i3 = 0; i3 < length; i3++) {
                char charAt = str.charAt(i3);
                if (charAt == ' ') {
                    if (sb.length() > 0) {
                        addToken(sb, arrayList, i2);
                    }
                } else if (charAt < '\u0100') {
                    if (i2 != 1 && sb.length() > 0) {
                        addToken(sb, arrayList, i2);
                    }
                    sb.append(charAt);
                    i2 = 1;
                } else {
                    a token = getToken(charAt);
                    int i4 = token.a;
                    if (i4 == 2) {
                        if (sb.length() > 0) {
                            addToken(sb, arrayList, i2);
                        }
                        arrayList.add(token);
                        i2 = 2;
                    } else {
                        if (i2 != i4 && sb.length() > 0) {
                            addToken(sb, arrayList, i2);
                        }
                        i2 = token.a;
                        sb.append(charAt);
                    }
                }
            }
            if (sb.length() > 0) {
                addToken(sb, arrayList, i2);
            }
        }
        return arrayList;
    }
}
