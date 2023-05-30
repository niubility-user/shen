package g.g.a.e;

import android.content.Context;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jingdong.sdk.platform.business.personal.R2;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import g.g.a.f;
import g.g.a.n;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;

/* loaded from: classes18.dex */
public class a {
    private static String o = "https://aiapi.jd.com/jdai/tts";
    private static String p;
    private static String q;
    private static String u;
    private static String v;
    private String a;

    /* renamed from: h  reason: collision with root package name */
    private Context f19595h;
    private static Integer r = 1;
    private static Integer s = 2;
    private static Integer t = 0;
    private static String w = "1";
    private static int x = 5000;
    private static int y = 10000;
    private static int z = 10;
    private static int A = 1;
    private static int B = 5;
    private boolean b = true;

    /* renamed from: c  reason: collision with root package name */
    private g.g.a.d.a f19591c = null;
    private g.g.a.c d = g.g.a.c.AUDIO_ENCODE_PCM;

    /* renamed from: e  reason: collision with root package name */
    private boolean f19592e = true;

    /* renamed from: f  reason: collision with root package name */
    private boolean f19593f = true;

    /* renamed from: g  reason: collision with root package name */
    private OkHttpClient f19594g = null;

    /* renamed from: i  reason: collision with root package name */
    Object f19596i = new Object();

    /* renamed from: j  reason: collision with root package name */
    private BlockingQueue<g.g.a.b> f19597j = new LinkedBlockingQueue();

    /* renamed from: k  reason: collision with root package name */
    private byte[] f19598k = {0};

    /* renamed from: l  reason: collision with root package name */
    private int f19599l = R2.id.rn_redbox_report_label;

    /* renamed from: m  reason: collision with root package name */
    private boolean f19600m = false;

    /* renamed from: n  reason: collision with root package name */
    private Long f19601n = 0L;

    public a(Context context, String str) {
        this.a = "";
        this.f19595h = context;
        this.a = str;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:(3:741|742|(15:746|747|748|749|750|751|752|753|754|755|756|757|758|759|(6:761|(3:763|764|765)(1:781)|766|779|604|208)(9:782|783|784|785|786|787|103|104|105))(2:744|745))(3:45|46|(20:48|49|50|51|(13:666|667|668|669|670|671|672|673|674|675|676|677|(5:682|683|684|685|686)(3:679|680|681))(1:(18:568|569|570|571|572|573|574|575|576|577|578|579|580|581|583|584|585|(6:587|(3:589|590|591)(1:605)|592|603|604|208)(9:606|607|608|609|610|611|103|104|105))(1:60))|61|62|63|64|(3:67|68|(14:70|71|72|73|74|75|76|77|78|79|(3:81|82|83)(1:92)|84|86|87))|150|151|152|(2:154|(4:485|486|487|a08)(1:156))(1:550)|157|158|(4:240|241|242|(15:427|428|429|430|431|432|433|434|435|(3:437|438|439)(1:446)|440|442|443|444|445)(8:244|245|246|(1:248)(1:415)|249|(1:251)(1:414)|252|(7:254|255|256|258|259|260|261)(3:274|275|(15:365|366|367|368|369|370|371|372|373|(3:375|376|377)(1:384)|378|380|381|382|383)(9:277|278|279|(3:329|330|e5a)(2:281|(2:(2:283|(1:286)(1:285))|287))|288|289|(6:291|292|293|294|295|296)(1:328)|297|(6:299|301|302|304|305|306)(6:313|314|102|103|104|105)))))(5:161|162|164|165|(13:192|193|194|195|196|197|198|199|(3:201|202|203)(1:210)|204|206|207|208)(7:167|168|169|170|172|173|174))|175|176|105))|151|152|(0)(0)|157|158|(0)|240|241|242|(0)(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(23:38|(3:39|40|41)|(2:42|43)|(16:(3:741|742|(15:746|747|748|749|750|751|752|753|754|755|756|757|758|759|(6:761|(3:763|764|765)(1:781)|766|779|604|208)(9:782|783|784|785|786|787|103|104|105))(2:744|745))(3:45|46|(20:48|49|50|51|(13:666|667|668|669|670|671|672|673|674|675|676|677|(5:682|683|684|685|686)(3:679|680|681))(1:(18:568|569|570|571|572|573|574|575|576|577|578|579|580|581|583|584|585|(6:587|(3:589|590|591)(1:605)|592|603|604|208)(9:606|607|608|609|610|611|103|104|105))(1:60))|61|62|63|64|(3:67|68|(14:70|71|72|73|74|75|76|77|78|79|(3:81|82|83)(1:92)|84|86|87))|150|151|152|(2:154|(4:485|486|487|a08)(1:156))(1:550)|157|158|(4:240|241|242|(15:427|428|429|430|431|432|433|434|435|(3:437|438|439)(1:446)|440|442|443|444|445)(8:244|245|246|(1:248)(1:415)|249|(1:251)(1:414)|252|(7:254|255|256|258|259|260|261)(3:274|275|(15:365|366|367|368|369|370|371|372|373|(3:375|376|377)(1:384)|378|380|381|382|383)(9:277|278|279|(3:329|330|e5a)(2:281|(2:(2:283|(1:286)(1:285))|287))|288|289|(6:291|292|293|294|295|296)(1:328)|297|(6:299|301|302|304|305|306)(6:313|314|102|103|104|105)))))(5:161|162|164|165|(13:192|193|194|195|196|197|198|199|(3:201|202|203)(1:210)|204|206|207|208)(7:167|168|169|170|172|173|174))|175|176|105))|62|63|64|(3:67|68|(0))|150|151|152|(0)(0)|157|158|(0)|240|241|242|(0)(0))|740|49|50|51|(1:53)|666|667|668|669|670|671|672|673|674|675|676|677|(0)(0)|61) */
    /* JADX WARN: Can't wrap try/catch for region: R(41:38|39|40|41|42|43|(3:741|742|(15:746|747|748|749|750|751|752|753|754|755|756|757|758|759|(6:761|(3:763|764|765)(1:781)|766|779|604|208)(9:782|783|784|785|786|787|103|104|105))(2:744|745))(3:45|46|(20:48|49|50|51|(13:666|667|668|669|670|671|672|673|674|675|676|677|(5:682|683|684|685|686)(3:679|680|681))(1:(18:568|569|570|571|572|573|574|575|576|577|578|579|580|581|583|584|585|(6:587|(3:589|590|591)(1:605)|592|603|604|208)(9:606|607|608|609|610|611|103|104|105))(1:60))|61|62|63|64|(3:67|68|(14:70|71|72|73|74|75|76|77|78|79|(3:81|82|83)(1:92)|84|86|87))|150|151|152|(2:154|(4:485|486|487|a08)(1:156))(1:550)|157|158|(4:240|241|242|(15:427|428|429|430|431|432|433|434|435|(3:437|438|439)(1:446)|440|442|443|444|445)(8:244|245|246|(1:248)(1:415)|249|(1:251)(1:414)|252|(7:254|255|256|258|259|260|261)(3:274|275|(15:365|366|367|368|369|370|371|372|373|(3:375|376|377)(1:384)|378|380|381|382|383)(9:277|278|279|(3:329|330|e5a)(2:281|(2:(2:283|(1:286)(1:285))|287))|288|289|(6:291|292|293|294|295|296)(1:328)|297|(6:299|301|302|304|305|306)(6:313|314|102|103|104|105)))))(5:161|162|164|165|(13:192|193|194|195|196|197|198|199|(3:201|202|203)(1:210)|204|206|207|208)(7:167|168|169|170|172|173|174))|175|176|105))|740|49|50|51|(1:53)|666|667|668|669|670|671|672|673|674|675|676|677|(0)(0)|61|62|63|64|(3:67|68|(0))|150|151|152|(0)(0)|157|158|(0)|240|241|242|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:677:0x0fb4, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:678:0x0fb5, code lost:
        r24 = r12;
        r21 = r33;
        r37 = r35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:679:0x0fbd, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:680:0x0fbe, code lost:
        r24 = r12;
        r21 = r33;
        r17 = r2;
        r10 = r20;
        r4 = r21;
        r7 = r24;
        r6 = r35;
        r21 = r21;
        r24 = r24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:681:0x0fcf, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:682:0x0fd0, code lost:
        r2 = r0;
        r15 = r18;
        r4 = r33;
        r7 = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:684:0x0fdf, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:685:0x0fe0, code lost:
        r24 = r41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:688:0x0fee, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:689:0x0fef, code lost:
        r24 = r41;
        r2 = r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:690:0x0ff3, code lost:
        r20 = r25;
        r21 = r33;
        r24 = r24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:694:0x100a, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:695:0x100b, code lost:
        r24 = r41;
        r21 = r33;
        r17 = r12;
        r10 = r25;
        r4 = r21;
        r7 = r24;
        r6 = r34;
        r21 = r21;
        r24 = r24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:703:0x104d, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:704:0x104e, code lost:
        r24 = r41;
        r21 = r31;
        r37 = r32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:705:0x1056, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:706:0x1057, code lost:
        r24 = r41;
        r21 = r31;
        r20 = r25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:707:0x105e, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:708:0x105f, code lost:
        r24 = r41;
        r21 = r31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:709:0x1064, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:710:0x1065, code lost:
        r24 = r41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:711:0x1068, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:712:0x1069, code lost:
        r24 = r41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:713:0x106c, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:714:0x106d, code lost:
        r24 = r41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:715:0x1070, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:716:0x1071, code lost:
        r24 = r41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:717:0x1073, code lost:
        r37 = r32;
        r20 = r25;
        r21 = r31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:718:0x107b, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:719:0x107c, code lost:
        r24 = r41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:720:0x107e, code lost:
        r20 = r25;
        r21 = r31;
        r24 = r24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:721:0x1082, code lost:
        r2 = r0;
        r6 = r32;
        r21 = r21;
        r24 = r24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:722:0x1087, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:723:0x1088, code lost:
        r24 = r41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:724:0x108a, code lost:
        r21 = r31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:725:0x108c, code lost:
        r2 = r0;
        r6 = r32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:726:0x1090, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:727:0x1091, code lost:
        r24 = r41;
        r20 = r25;
        r21 = r31;
        r37 = r32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:728:0x109b, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:729:0x109c, code lost:
        r24 = r41;
        r21 = r31;
        r2 = r0;
        r10 = r25;
        r4 = r21;
        r7 = r24;
        r6 = r32;
        r21 = r21;
        r24 = r24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:730:0x10ae, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:731:0x10af, code lost:
        r2 = r0;
        r15 = r18;
        r4 = r31;
        r7 = r41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:733:0x10be, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:734:0x10bf, code lost:
        r24 = r41;
        r20 = r25;
        r37 = r29;
        r21 = r30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:735:0x10c9, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:736:0x10ca, code lost:
        r24 = r41;
        r21 = r30;
        r2 = r0;
        r10 = r25;
        r4 = r21;
        r7 = r24;
        r6 = r29;
        r21 = r21;
        r24 = r24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:737:0x10dc, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:738:0x10dd, code lost:
        r2 = r0;
        r15 = r18;
        r4 = r30;
        r7 = r41;
        r6 = r29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:757:0x1136, code lost:
        r20 = r25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:761:0x114b, code lost:
        r10 = r20;
        r4 = r21;
        r21 = r21;
        r24 = r24;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:1067:0x131f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1073:0x0927 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:317:0x08df  */
    /* JADX WARN: Removed duplicated region for block: B:361:0x09e5  */
    /* JADX WARN: Removed duplicated region for block: B:423:0x0b1f  */
    /* JADX WARN: Removed duplicated region for block: B:529:0x0d30  */
    /* JADX WARN: Removed duplicated region for block: B:832:0x1292 A[Catch: all -> 0x1240, TryCatch #212 {, blocks: (B:5:0x000a, B:7:0x00cc, B:837:0x12cb, B:338:0x0984, B:853:0x1396, B:41:0x02b1, B:46:0x02cf, B:101:0x0473, B:160:0x05e5, B:246:0x0796, B:399:0x0ae2, B:456:0x0bda, B:496:0x0cbe, B:543:0x0d58, B:567:0x0df7, B:648:0x0f3e, B:655:0x0f62, B:9:0x00cf, B:10:0x00d1, B:19:0x00f3, B:20:0x0127, B:23:0x01f4, B:24:0x0211, B:830:0x124c, B:832:0x1292, B:834:0x1296, B:836:0x12c8, B:835:0x12ae, B:840:0x12d0, B:845:0x12db, B:847:0x131f, B:849:0x1345, B:851:0x1385, B:850:0x1364, B:852:0x138a, B:860:0x13a7, B:862:0x13dd, B:864:0x140f, B:863:0x13f5, B:32:0x0226, B:34:0x022e, B:36:0x0262, B:38:0x0288, B:40:0x02af, B:39:0x028c, B:44:0x02bf, B:45:0x02cd, B:71:0x0344, B:73:0x035c, B:75:0x0366, B:76:0x0382, B:78:0x03ba, B:80:0x03e2, B:81:0x03ec, B:90:0x0405, B:92:0x040d, B:94:0x0411, B:96:0x0433, B:98:0x0459, B:97:0x0437, B:99:0x045d, B:100:0x046e, B:128:0x0500, B:130:0x0504, B:132:0x0511, B:134:0x051f, B:136:0x0537, B:138:0x0541, B:140:0x054b, B:141:0x055d, B:149:0x0577, B:151:0x057f, B:153:0x0583, B:155:0x05a5, B:157:0x05cb, B:156:0x05a9, B:158:0x05cf, B:159:0x05e0, B:183:0x064f, B:207:0x06b1, B:218:0x06cb, B:220:0x06e3, B:222:0x06ed, B:224:0x06f7, B:226:0x0701, B:227:0x070b, B:235:0x0728, B:237:0x0730, B:239:0x0734, B:241:0x0756, B:243:0x077c, B:242:0x075a, B:244:0x0780, B:245:0x0791, B:319:0x0900, B:320:0x0906, B:324:0x0921, B:326:0x0927, B:328:0x0935, B:330:0x094a, B:331:0x094c, B:333:0x0950, B:335:0x0969, B:336:0x096d, B:359:0x09df, B:363:0x09e9, B:364:0x0a08, B:398:0x0ae0, B:405:0x0aed, B:424:0x0b21, B:427:0x0b29, B:431:0x0b2f, B:433:0x0b37, B:435:0x0b57, B:437:0x0b61, B:439:0x0b74, B:441:0x0b90, B:443:0x0bad, B:442:0x0b94, B:454:0x0bc5, B:455:0x0bd5, B:483:0x0c43, B:485:0x0c4a, B:487:0x0c62, B:489:0x0c74, B:491:0x0c82, B:493:0x0c9e, B:495:0x0cbb, B:494:0x0ca2, B:530:0x0d36, B:534:0x0d42, B:538:0x0d4b, B:541:0x0d53, B:554:0x0d80, B:556:0x0d83, B:558:0x0da3, B:560:0x0dad, B:562:0x0dbb, B:564:0x0dd7, B:566:0x0df4, B:565:0x0ddb, B:602:0x0e54, B:604:0x0e58, B:605:0x0e5a, B:634:0x0f05, B:637:0x0f0c, B:639:0x0f0f, B:643:0x0f16, B:645:0x0f1a, B:646:0x0f20, B:620:0x0e9d, B:626:0x0ead, B:628:0x0ed1, B:631:0x0ede, B:296:0x0854, B:298:0x086c, B:300:0x0876, B:302:0x0880, B:304:0x088e, B:306:0x08af, B:318:0x08e6, B:203:0x06a4, B:810:0x1217), top: B:952:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:839:0x12cf  */
    /* JADX WARN: Removed duplicated region for block: B:852:0x138a A[Catch: all -> 0x1240, TRY_LEAVE, TryCatch #212 {, blocks: (B:5:0x000a, B:7:0x00cc, B:837:0x12cb, B:338:0x0984, B:853:0x1396, B:41:0x02b1, B:46:0x02cf, B:101:0x0473, B:160:0x05e5, B:246:0x0796, B:399:0x0ae2, B:456:0x0bda, B:496:0x0cbe, B:543:0x0d58, B:567:0x0df7, B:648:0x0f3e, B:655:0x0f62, B:9:0x00cf, B:10:0x00d1, B:19:0x00f3, B:20:0x0127, B:23:0x01f4, B:24:0x0211, B:830:0x124c, B:832:0x1292, B:834:0x1296, B:836:0x12c8, B:835:0x12ae, B:840:0x12d0, B:845:0x12db, B:847:0x131f, B:849:0x1345, B:851:0x1385, B:850:0x1364, B:852:0x138a, B:860:0x13a7, B:862:0x13dd, B:864:0x140f, B:863:0x13f5, B:32:0x0226, B:34:0x022e, B:36:0x0262, B:38:0x0288, B:40:0x02af, B:39:0x028c, B:44:0x02bf, B:45:0x02cd, B:71:0x0344, B:73:0x035c, B:75:0x0366, B:76:0x0382, B:78:0x03ba, B:80:0x03e2, B:81:0x03ec, B:90:0x0405, B:92:0x040d, B:94:0x0411, B:96:0x0433, B:98:0x0459, B:97:0x0437, B:99:0x045d, B:100:0x046e, B:128:0x0500, B:130:0x0504, B:132:0x0511, B:134:0x051f, B:136:0x0537, B:138:0x0541, B:140:0x054b, B:141:0x055d, B:149:0x0577, B:151:0x057f, B:153:0x0583, B:155:0x05a5, B:157:0x05cb, B:156:0x05a9, B:158:0x05cf, B:159:0x05e0, B:183:0x064f, B:207:0x06b1, B:218:0x06cb, B:220:0x06e3, B:222:0x06ed, B:224:0x06f7, B:226:0x0701, B:227:0x070b, B:235:0x0728, B:237:0x0730, B:239:0x0734, B:241:0x0756, B:243:0x077c, B:242:0x075a, B:244:0x0780, B:245:0x0791, B:319:0x0900, B:320:0x0906, B:324:0x0921, B:326:0x0927, B:328:0x0935, B:330:0x094a, B:331:0x094c, B:333:0x0950, B:335:0x0969, B:336:0x096d, B:359:0x09df, B:363:0x09e9, B:364:0x0a08, B:398:0x0ae0, B:405:0x0aed, B:424:0x0b21, B:427:0x0b29, B:431:0x0b2f, B:433:0x0b37, B:435:0x0b57, B:437:0x0b61, B:439:0x0b74, B:441:0x0b90, B:443:0x0bad, B:442:0x0b94, B:454:0x0bc5, B:455:0x0bd5, B:483:0x0c43, B:485:0x0c4a, B:487:0x0c62, B:489:0x0c74, B:491:0x0c82, B:493:0x0c9e, B:495:0x0cbb, B:494:0x0ca2, B:530:0x0d36, B:534:0x0d42, B:538:0x0d4b, B:541:0x0d53, B:554:0x0d80, B:556:0x0d83, B:558:0x0da3, B:560:0x0dad, B:562:0x0dbb, B:564:0x0dd7, B:566:0x0df4, B:565:0x0ddb, B:602:0x0e54, B:604:0x0e58, B:605:0x0e5a, B:634:0x0f05, B:637:0x0f0c, B:639:0x0f0f, B:643:0x0f16, B:645:0x0f1a, B:646:0x0f20, B:620:0x0e9d, B:626:0x0ead, B:628:0x0ed1, B:631:0x0ede, B:296:0x0854, B:298:0x086c, B:300:0x0876, B:302:0x0880, B:304:0x088e, B:306:0x08af, B:318:0x08e6, B:203:0x06a4, B:810:0x1217), top: B:952:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:862:0x13dd A[Catch: all -> 0x1240, TryCatch #212 {, blocks: (B:5:0x000a, B:7:0x00cc, B:837:0x12cb, B:338:0x0984, B:853:0x1396, B:41:0x02b1, B:46:0x02cf, B:101:0x0473, B:160:0x05e5, B:246:0x0796, B:399:0x0ae2, B:456:0x0bda, B:496:0x0cbe, B:543:0x0d58, B:567:0x0df7, B:648:0x0f3e, B:655:0x0f62, B:9:0x00cf, B:10:0x00d1, B:19:0x00f3, B:20:0x0127, B:23:0x01f4, B:24:0x0211, B:830:0x124c, B:832:0x1292, B:834:0x1296, B:836:0x12c8, B:835:0x12ae, B:840:0x12d0, B:845:0x12db, B:847:0x131f, B:849:0x1345, B:851:0x1385, B:850:0x1364, B:852:0x138a, B:860:0x13a7, B:862:0x13dd, B:864:0x140f, B:863:0x13f5, B:32:0x0226, B:34:0x022e, B:36:0x0262, B:38:0x0288, B:40:0x02af, B:39:0x028c, B:44:0x02bf, B:45:0x02cd, B:71:0x0344, B:73:0x035c, B:75:0x0366, B:76:0x0382, B:78:0x03ba, B:80:0x03e2, B:81:0x03ec, B:90:0x0405, B:92:0x040d, B:94:0x0411, B:96:0x0433, B:98:0x0459, B:97:0x0437, B:99:0x045d, B:100:0x046e, B:128:0x0500, B:130:0x0504, B:132:0x0511, B:134:0x051f, B:136:0x0537, B:138:0x0541, B:140:0x054b, B:141:0x055d, B:149:0x0577, B:151:0x057f, B:153:0x0583, B:155:0x05a5, B:157:0x05cb, B:156:0x05a9, B:158:0x05cf, B:159:0x05e0, B:183:0x064f, B:207:0x06b1, B:218:0x06cb, B:220:0x06e3, B:222:0x06ed, B:224:0x06f7, B:226:0x0701, B:227:0x070b, B:235:0x0728, B:237:0x0730, B:239:0x0734, B:241:0x0756, B:243:0x077c, B:242:0x075a, B:244:0x0780, B:245:0x0791, B:319:0x0900, B:320:0x0906, B:324:0x0921, B:326:0x0927, B:328:0x0935, B:330:0x094a, B:331:0x094c, B:333:0x0950, B:335:0x0969, B:336:0x096d, B:359:0x09df, B:363:0x09e9, B:364:0x0a08, B:398:0x0ae0, B:405:0x0aed, B:424:0x0b21, B:427:0x0b29, B:431:0x0b2f, B:433:0x0b37, B:435:0x0b57, B:437:0x0b61, B:439:0x0b74, B:441:0x0b90, B:443:0x0bad, B:442:0x0b94, B:454:0x0bc5, B:455:0x0bd5, B:483:0x0c43, B:485:0x0c4a, B:487:0x0c62, B:489:0x0c74, B:491:0x0c82, B:493:0x0c9e, B:495:0x0cbb, B:494:0x0ca2, B:530:0x0d36, B:534:0x0d42, B:538:0x0d4b, B:541:0x0d53, B:554:0x0d80, B:556:0x0d83, B:558:0x0da3, B:560:0x0dad, B:562:0x0dbb, B:564:0x0dd7, B:566:0x0df4, B:565:0x0ddb, B:602:0x0e54, B:604:0x0e58, B:605:0x0e5a, B:634:0x0f05, B:637:0x0f0c, B:639:0x0f0f, B:643:0x0f16, B:645:0x0f1a, B:646:0x0f20, B:620:0x0e9d, B:626:0x0ead, B:628:0x0ed1, B:631:0x0ede, B:296:0x0854, B:298:0x086c, B:300:0x0876, B:302:0x0880, B:304:0x088e, B:306:0x08af, B:318:0x08e6, B:203:0x06a4, B:810:0x1217), top: B:952:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:863:0x13f5 A[Catch: all -> 0x1240, TryCatch #212 {, blocks: (B:5:0x000a, B:7:0x00cc, B:837:0x12cb, B:338:0x0984, B:853:0x1396, B:41:0x02b1, B:46:0x02cf, B:101:0x0473, B:160:0x05e5, B:246:0x0796, B:399:0x0ae2, B:456:0x0bda, B:496:0x0cbe, B:543:0x0d58, B:567:0x0df7, B:648:0x0f3e, B:655:0x0f62, B:9:0x00cf, B:10:0x00d1, B:19:0x00f3, B:20:0x0127, B:23:0x01f4, B:24:0x0211, B:830:0x124c, B:832:0x1292, B:834:0x1296, B:836:0x12c8, B:835:0x12ae, B:840:0x12d0, B:845:0x12db, B:847:0x131f, B:849:0x1345, B:851:0x1385, B:850:0x1364, B:852:0x138a, B:860:0x13a7, B:862:0x13dd, B:864:0x140f, B:863:0x13f5, B:32:0x0226, B:34:0x022e, B:36:0x0262, B:38:0x0288, B:40:0x02af, B:39:0x028c, B:44:0x02bf, B:45:0x02cd, B:71:0x0344, B:73:0x035c, B:75:0x0366, B:76:0x0382, B:78:0x03ba, B:80:0x03e2, B:81:0x03ec, B:90:0x0405, B:92:0x040d, B:94:0x0411, B:96:0x0433, B:98:0x0459, B:97:0x0437, B:99:0x045d, B:100:0x046e, B:128:0x0500, B:130:0x0504, B:132:0x0511, B:134:0x051f, B:136:0x0537, B:138:0x0541, B:140:0x054b, B:141:0x055d, B:149:0x0577, B:151:0x057f, B:153:0x0583, B:155:0x05a5, B:157:0x05cb, B:156:0x05a9, B:158:0x05cf, B:159:0x05e0, B:183:0x064f, B:207:0x06b1, B:218:0x06cb, B:220:0x06e3, B:222:0x06ed, B:224:0x06f7, B:226:0x0701, B:227:0x070b, B:235:0x0728, B:237:0x0730, B:239:0x0734, B:241:0x0756, B:243:0x077c, B:242:0x075a, B:244:0x0780, B:245:0x0791, B:319:0x0900, B:320:0x0906, B:324:0x0921, B:326:0x0927, B:328:0x0935, B:330:0x094a, B:331:0x094c, B:333:0x0950, B:335:0x0969, B:336:0x096d, B:359:0x09df, B:363:0x09e9, B:364:0x0a08, B:398:0x0ae0, B:405:0x0aed, B:424:0x0b21, B:427:0x0b29, B:431:0x0b2f, B:433:0x0b37, B:435:0x0b57, B:437:0x0b61, B:439:0x0b74, B:441:0x0b90, B:443:0x0bad, B:442:0x0b94, B:454:0x0bc5, B:455:0x0bd5, B:483:0x0c43, B:485:0x0c4a, B:487:0x0c62, B:489:0x0c74, B:491:0x0c82, B:493:0x0c9e, B:495:0x0cbb, B:494:0x0ca2, B:530:0x0d36, B:534:0x0d42, B:538:0x0d4b, B:541:0x0d53, B:554:0x0d80, B:556:0x0d83, B:558:0x0da3, B:560:0x0dad, B:562:0x0dbb, B:564:0x0dd7, B:566:0x0df4, B:565:0x0ddb, B:602:0x0e54, B:604:0x0e58, B:605:0x0e5a, B:634:0x0f05, B:637:0x0f0c, B:639:0x0f0f, B:643:0x0f16, B:645:0x0f1a, B:646:0x0f20, B:620:0x0e9d, B:626:0x0ead, B:628:0x0ed1, B:631:0x0ede, B:296:0x0854, B:298:0x086c, B:300:0x0876, B:302:0x0880, B:304:0x088e, B:306:0x08af, B:318:0x08e6, B:203:0x06a4, B:810:0x1217), top: B:952:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:978:0x0c4a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:988:0x088e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r15v107, types: [java.lang.Object, java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r15v108 */
    /* JADX WARN: Type inference failed for: r15v110 */
    /* JADX WARN: Type inference failed for: r15v111 */
    /* JADX WARN: Type inference failed for: r15v137 */
    /* JADX WARN: Type inference failed for: r15v67, types: [org.json.JSONObject] */
    /* JADX WARN: Type inference failed for: r19v10 */
    /* JADX WARN: Type inference failed for: r19v11 */
    /* JADX WARN: Type inference failed for: r19v14 */
    /* JADX WARN: Type inference failed for: r19v17 */
    /* JADX WARN: Type inference failed for: r19v18 */
    /* JADX WARN: Type inference failed for: r19v19 */
    /* JADX WARN: Type inference failed for: r19v24 */
    /* JADX WARN: Type inference failed for: r19v26 */
    /* JADX WARN: Type inference failed for: r19v4 */
    /* JADX WARN: Type inference failed for: r19v5, types: [int] */
    /* JADX WARN: Type inference failed for: r20v12 */
    /* JADX WARN: Type inference failed for: r20v51 */
    /* JADX WARN: Type inference failed for: r20v52 */
    /* JADX WARN: Type inference failed for: r20v53 */
    /* JADX WARN: Type inference failed for: r20v54 */
    /* JADX WARN: Type inference failed for: r20v55 */
    /* JADX WARN: Type inference failed for: r21v10 */
    /* JADX WARN: Type inference failed for: r21v11 */
    /* JADX WARN: Type inference failed for: r21v160 */
    /* JADX WARN: Type inference failed for: r24v23, types: [g.g.a.l] */
    /* JADX WARN: Type inference failed for: r24v95, types: [int] */
    /* JADX WARN: Type inference failed for: r42v0, types: [g.g.a.e.b] */
    /* JADX WARN: Type inference failed for: r4v165, types: [java.lang.StringBuilder] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:808:0x1215 -> B:887:0x1216). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:906:? -> B:621:0x0e9e). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized int a(java.lang.String r39, java.lang.String r40, java.lang.Integer r41, g.g.a.e.b r42) {
        /*
            Method dump skipped, instructions count: 5154
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: g.g.a.e.a.a(java.lang.String, java.lang.String, java.lang.Integer, g.g.a.e.b):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:174:0x0578 A[Catch: all -> 0x0638, TryCatch #18 {, blocks: (B:5:0x0008, B:8:0x0086, B:10:0x008a, B:12:0x0097, B:14:0x015b, B:15:0x016e, B:18:0x0178, B:19:0x01a2, B:20:0x01a5, B:30:0x01c2, B:31:0x01ca, B:33:0x01e6, B:53:0x027c, B:55:0x028b, B:57:0x0299, B:59:0x02a4, B:60:0x02bd, B:61:0x02d9, B:68:0x02f4, B:70:0x02fc, B:72:0x0316, B:73:0x032f, B:75:0x034b, B:78:0x036b, B:79:0x036f, B:94:0x03b8, B:98:0x03bc, B:100:0x03c3, B:102:0x03dd, B:104:0x03f5, B:178:0x05b6, B:180:0x05f9, B:182:0x05fd, B:183:0x0615, B:105:0x03fa, B:106:0x0418, B:110:0x0425, B:114:0x042e, B:116:0x0434, B:117:0x044b, B:119:0x044e, B:121:0x0468, B:122:0x0480, B:124:0x04a1, B:125:0x04a3, B:136:0x04e5, B:137:0x04e9, B:139:0x04ed, B:172:0x0542, B:174:0x0578, B:175:0x0591, B:150:0x0508, B:65:0x02e9, B:39:0x01f3, B:41:0x01fb, B:43:0x0217, B:45:0x0237, B:46:0x023c), top: B:217:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0591 A[Catch: all -> 0x0638, TryCatch #18 {, blocks: (B:5:0x0008, B:8:0x0086, B:10:0x008a, B:12:0x0097, B:14:0x015b, B:15:0x016e, B:18:0x0178, B:19:0x01a2, B:20:0x01a5, B:30:0x01c2, B:31:0x01ca, B:33:0x01e6, B:53:0x027c, B:55:0x028b, B:57:0x0299, B:59:0x02a4, B:60:0x02bd, B:61:0x02d9, B:68:0x02f4, B:70:0x02fc, B:72:0x0316, B:73:0x032f, B:75:0x034b, B:78:0x036b, B:79:0x036f, B:94:0x03b8, B:98:0x03bc, B:100:0x03c3, B:102:0x03dd, B:104:0x03f5, B:178:0x05b6, B:180:0x05f9, B:182:0x05fd, B:183:0x0615, B:105:0x03fa, B:106:0x0418, B:110:0x0425, B:114:0x042e, B:116:0x0434, B:117:0x044b, B:119:0x044e, B:121:0x0468, B:122:0x0480, B:124:0x04a1, B:125:0x04a3, B:136:0x04e5, B:137:0x04e9, B:139:0x04ed, B:172:0x0542, B:174:0x0578, B:175:0x0591, B:150:0x0508, B:65:0x02e9, B:39:0x01f3, B:41:0x01fb, B:43:0x0217, B:45:0x0237, B:46:0x023c), top: B:217:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0630  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x05f9 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:206:? -> B:151:0x0509). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized int b(java.lang.String r25, java.lang.String r26, java.lang.Integer r27, g.g.a.e.b r28) {
        /*
            Method dump skipped, instructions count: 1598
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: g.g.a.e.a.b(java.lang.String, java.lang.String, java.lang.Integer, g.g.a.e.b):int");
    }

    public void c(Long l2) {
        this.f19601n = l2;
    }

    public int d(n nVar) {
        u = "{  \"platform\": \"Linux\", \"version\": \"0.0.0.1\",  \"timestamp\": \"1\", \"parameters\": {\"tte\":\"" + nVar.a("tte") + "\", \"tt\":\"" + nVar.a(PushConstants.PUSH_NOTIFICATION_CREATE_TIMES_TAMP) + "\", \"aue\": \"" + nVar.a("aue") + "\", \"tim\": \"" + nVar.a("tim") + "\",  \"vol\": \"" + nVar.a("vol") + "\",     \"sp\": \"" + nVar.a("sp") + "\",  \"sr\": \"" + nVar.a("sr") + "\"   }}";
        o = nVar.a("serverURL");
        p = nVar.a(PairKey.APP_KEY);
        q = nVar.a("appSecret");
        v = nVar.a("appID");
        w = nVar.a("CustomerType");
        x = Integer.valueOf(nVar.a("connectTimeout")).intValue();
        y = Integer.valueOf(nVar.a("readTimeout")).intValue();
        B = Integer.valueOf(nVar.a("httpTryCount")).intValue();
        if (Integer.valueOf(nVar.a("streamMode")).intValue() == 1) {
            this.b = true;
        } else {
            this.b = false;
        }
        int i2 = B;
        int i3 = z;
        if (i2 > i3) {
            B = i3;
        }
        int i4 = B;
        int i5 = A;
        if (i4 < i5) {
            B = i5;
        }
        f.c("HttpClient", "readTimeout=" + y);
        if (!this.f19600m) {
            String a = nVar.a("aue");
            if (a.equals("0")) {
                this.d = g.g.a.c.AUDIO_ENCODE_WAV;
            } else if (a.equals("1")) {
                this.d = g.g.a.c.AUDIO_ENCODE_PCM;
            } else if (a.equals("2")) {
                this.d = g.g.a.c.AUDIO_ENCODE_OPUS;
            } else if (a.equals("3")) {
                this.d = g.g.a.c.AUDIO_ENCODE_MP3;
            } else {
                this.d = g.g.a.c.AUDIO_ENCODE_PCM;
            }
            f.c("HttpClient", "audioEncodeType=" + this.d);
            if (this.f19591c != null) {
                this.f19591c = null;
            }
            this.f19591c = new g.g.a.d.a(this.d, Integer.parseInt(nVar.a("sr")));
            this.f19600m = true;
            this.f19599l = Integer.parseInt(nVar.a("sr"));
        } else if (this.f19599l != Integer.parseInt(nVar.a("sr"))) {
            this.f19591c = null;
            this.f19591c = new g.g.a.d.a(this.d, Integer.parseInt(nVar.a("sr")));
        }
        this.f19594g = null;
        TimeUnit timeUnit = TimeUnit.SECONDS;
        this.f19594g = new OkHttpClient().newBuilder().connectTimeout(x, timeUnit).readTimeout(y, timeUnit).writeTimeout(15L, timeUnit).sslSocketFactory(d.a(this.f19595h).getSocketFactory()).protocols(Collections.unmodifiableList(Arrays.asList(Protocol.HTTP_2, Protocol.HTTP_1_1))).build();
        return 0;
    }

    public int e() {
        f.c("HttpClient", "http name=" + this.a + ", enter setStop");
        synchronized (this.f19596i) {
            if (this.f19593f) {
                f.c("HttpClient", "http name=" + this.a + ", setStop, isStop=" + this.f19593f + ", do nothing and return");
                return 0;
            }
            f.c("HttpClient", "http name=" + this.a + ", setStop isStop=" + this.f19593f);
            this.f19592e = true;
            while (true) {
                synchronized (this.f19596i) {
                    if (this.f19593f) {
                        f.c("HttpClient", "setStop, now Stop, exit");
                        return 0;
                    }
                }
                try {
                    Thread.sleep(200L);
                } catch (InterruptedException unused) {
                }
                f.c("HttpClient", "http name=" + this.a + ", setStop, wait stop, isStop=" + this.f19593f);
            }
        }
    }
}
