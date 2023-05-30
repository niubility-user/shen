package g.g.a.e;

import android.content.Context;
import android.util.Base64;
import androidx.core.app.NotificationCompat;
import com.google.common.net.HttpHeaders;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jdpay.net.http.HTTP;
import com.jingdong.sdk.platform.business.personal.R2;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import g.g.a.f;
import g.g.a.l;
import g.g.a.n;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

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
    */
    public synchronized int a(String str, String str2, Integer num, b bVar) {
        int i2;
        String str3;
        JSONException jSONException;
        String str4;
        String str5;
        String str6;
        MalformedURLException malformedURLException;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        String str12;
        String str13;
        String str14;
        Integer num2;
        String str15;
        IOException iOException;
        Integer num3;
        String str16;
        boolean z2;
        Integer num4;
        String str17;
        Integer num5;
        String str18;
        Integer num6;
        String str19;
        boolean z3;
        Integer num7;
        Throwable th;
        Integer num8;
        Integer num9;
        Integer num10;
        Integer num11;
        String str20;
        Response execute;
        Integer num12;
        Integer num13;
        Integer num14;
        String str21;
        Integer num15;
        String str22;
        boolean z4;
        Integer num16;
        String str23;
        Integer num17;
        Integer num18;
        Integer num19;
        String string;
        String str24;
        String str25;
        Integer num20;
        String str26;
        StringBuilder sb;
        String str27;
        Integer num21;
        Integer num22;
        String str28;
        Integer num23;
        String str29;
        JSONObject jSONObject;
        String str30;
        String str31;
        JSONObject jSONObject2;
        int i3;
        String str32;
        String str33;
        JSONObject jSONObject3;
        int i4;
        String string2;
        int i5;
        boolean z5;
        byte[] decode;
        String str34;
        byte[] a;
        Integer num24;
        String str35;
        String str36;
        StringBuilder sb2;
        Integer num25;
        String str37;
        String str38;
        String str39;
        Object obj;
        Integer num26;
        Integer num27;
        Integer num28;
        Integer num29;
        Integer num30;
        Integer num31;
        Integer num32;
        Integer num33;
        Object obj2;
        Throwable th2;
        Object obj3;
        byte[] bArr;
        g.g.a.b bVar2;
        int i6;
        byte[] bArr2;
        String str40;
        String str41;
        StringBuilder sb3;
        String str42;
        String str43;
        String str44 = str;
        synchronized (this) {
            boolean z6 = false;
            String str45 = "";
            this.f19592e = false;
            this.f19593f = false;
            String uuid = UUID.randomUUID().toString();
            bVar.onStart(uuid);
            Long valueOf = Long.valueOf(System.currentTimeMillis() + this.f19601n.longValue());
            new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String str46 = o + "?appkey=" + p + "&timestamp=" + valueOf.toString() + "&sign=" + c.b(q + valueOf.toString(), "UTF-8");
            StringBuilder sb4 = new StringBuilder();
            sb4.append("http name=");
            sb4.append(this.a);
            sb4.append(", isStop=");
            sb4.append(this.f19593f);
            sb4.append(", Post1 ReqID=");
            sb4.append(uuid);
            sb4.append(", index=");
            Integer num34 = num;
            sb4.append(num34);
            sb4.append(", txt=");
            sb4.append(str44);
            f.c("HttpClient", sb4.toString());
            this.f19597j.clear();
            this.f19591c.stop();
            Integer num35 = 0;
            int i7 = 1;
            int i8 = -1;
            while (i7 > 0) {
                this.f19593f = z6;
                JSONObject jSONObject4 = null;
                try {
                    try {
                        try {
                        } catch (MalformedURLException e2) {
                            malformedURLException = e2;
                        } catch (IOException e3) {
                            str4 = str46;
                            iOException = e3;
                            str5 = uuid;
                            str19 = str17;
                            num6 = num4;
                        }
                    } catch (JSONException e4) {
                        str3 = uuid;
                        jSONException = e4;
                    }
                } catch (MalformedURLException e5) {
                    str6 = uuid;
                    malformedURLException = e5;
                } catch (IOException e6) {
                    e = e6;
                    str4 = str46;
                    str5 = uuid;
                    str16 = str17;
                    num3 = num4;
                }
                synchronized (this.f19596i) {
                    try {
                        if (this.f19592e) {
                            try {
                                num34 = Integer.valueOf(num34.intValue() * (-1));
                                this.f19591c.stop();
                            } catch (Throwable th3) {
                                th = th3;
                                num7 = num35;
                                str4 = str46;
                                str3 = uuid;
                                num4 = num4;
                                try {
                                    try {
                                    } catch (Throwable th4) {
                                        th = th4;
                                        th = th;
                                        num4 = num4;
                                        throw th;
                                        break;
                                    }
                                    throw th;
                                    break;
                                    break;
                                } catch (MalformedURLException e7) {
                                    malformedURLException = e7;
                                    i2 = i8;
                                    str6 = str3;
                                    f.c("HttpClient", "URL E\uff1areq=" + str6 + ", seq=" + num34 + ", e1=" + malformedURLException.toString() + ", recv=" + str45);
                                    if (this.b) {
                                    }
                                    z2 = true;
                                    this.f19593f = true;
                                    this.f19593f = z2;
                                    return i2;
                                } catch (IOException e8) {
                                    iOException = e8;
                                    num35 = num7;
                                    str5 = str3;
                                    str19 = str17;
                                    num6 = num4;
                                    f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                    if (num35.intValue() <= B) {
                                    }
                                } catch (JSONException e9) {
                                    jSONException = e9;
                                    i2 = i8;
                                    num35 = num7;
                                    f.c("HttpClient", "JSON E:req=" + str3 + ", seq=" + num34 + ", e3=" + jSONException.toString() + ", recv=" + str45);
                                    if (Integer.valueOf(num35.intValue() + 1).intValue() <= B) {
                                    }
                                    this.f19593f = z2;
                                    return i2;
                                }
                            }
                        }
                        num8 = num34;
                    } catch (Throwable th5) {
                        th = th5;
                        num7 = num35;
                        str4 = str46;
                        str3 = uuid;
                        th = th;
                        num4 = num4;
                        throw th;
                        break;
                        break;
                    }
                    try {
                        try {
                            f.c("HttpClient", "http name=" + this.a + ", post ReqID=" + uuid + ", index[1]=" + num8 + ", isStop=" + this.f19593f);
                            try {
                                Request build = new Request.Builder().url(str46).addHeader("Charset", "UTF-8").addHeader("host", "aiapi.jd.com").addHeader("accept", HTTP.CONTENT_TYPE_JSON).addHeader(HttpHeaders.CONTENT_TYPE, "text/plain").addHeader("Service-Type", "synthesis").addHeader("Application-Id", v).addHeader("Request-Id", uuid).addHeader("Protocol", r.toString()).addHeader("Net-State", s.toString()).addHeader("Applicator", t.toString()).addHeader("Property", u).addHeader("Sequence-Id", num8.toString()).post(RequestBody.create(MediaType.parse("text/plain"), str44)).build();
                                f.c("HttpClient", "http name=" + this.a + ", post ReqID=" + uuid + ", index[2]=" + num8 + ", isStop=" + this.f19593f);
                                execute = this.f19594g.newCall(build).execute();
                                num12 = 1000;
                                num12 = 1000;
                            } catch (MalformedURLException e10) {
                                e = e10;
                                num11 = num8;
                            } catch (IOException e11) {
                                num10 = num8;
                                str4 = str46;
                                iOException = e11;
                                str5 = uuid;
                                str45 = "";
                                str20 = str17;
                            } catch (JSONException e12) {
                                num9 = num8;
                                str3 = uuid;
                                jSONException = e12;
                                i2 = i8;
                                str45 = "";
                            }
                        } catch (MalformedURLException e13) {
                            num11 = num8;
                            malformedURLException = e13;
                            str6 = uuid;
                            i2 = i8;
                        } catch (IOException e14) {
                            num10 = num8;
                            str4 = str46;
                            iOException = e14;
                            str5 = uuid;
                            str20 = str17;
                        } catch (JSONException e15) {
                            num9 = num8;
                            str3 = uuid;
                            jSONException = e15;
                            i2 = i8;
                        }
                        if (execute.code() != 200) {
                            try {
                                f.c("HttpClient", "http name=" + this.a + ", http code!=200");
                                try {
                                    Thread.sleep(1000L);
                                } catch (IOException e16) {
                                    iOException = e16;
                                    num34 = num8;
                                    str4 = str46;
                                    str5 = uuid;
                                    str45 = "";
                                    str23 = str17;
                                    num16 = num4;
                                    str19 = str23;
                                    num6 = num16;
                                    f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                    if (num35.intValue() <= B) {
                                    }
                                } catch (InterruptedException unused) {
                                } catch (MalformedURLException e17) {
                                    malformedURLException = e17;
                                    num34 = num8;
                                    str6 = uuid;
                                    i2 = i8;
                                    str45 = "";
                                    f.c("HttpClient", "URL E\uff1areq=" + str6 + ", seq=" + num34 + ", e1=" + malformedURLException.toString() + ", recv=" + str45);
                                    if (this.b) {
                                    }
                                    z2 = true;
                                    this.f19593f = true;
                                    this.f19593f = z2;
                                    return i2;
                                } catch (JSONException e18) {
                                    jSONException = e18;
                                    num34 = num8;
                                    str3 = uuid;
                                    i2 = i8;
                                    str45 = "";
                                    f.c("HttpClient", "JSON E:req=" + str3 + ", seq=" + num34 + ", e3=" + jSONException.toString() + ", recv=" + str45);
                                    if (Integer.valueOf(num35.intValue() + 1).intValue() <= B) {
                                    }
                                    this.f19593f = z2;
                                    return i2;
                                }
                            } catch (MalformedURLException e19) {
                                num34 = num8;
                                malformedURLException = e19;
                            } catch (IOException e20) {
                                str4 = str46;
                                num34 = num8;
                                iOException = e20;
                            } catch (JSONException e21) {
                                num34 = num8;
                                jSONException = e21;
                            }
                            if (num35.intValue() > B) {
                                f.b("HttpClient", "http name=" + this.a + ", req=" + uuid + "seq=" + num8 + ", tryCount=" + num35);
                                if (!this.b) {
                                    this.f19597j.clear();
                                    byte[] bArr3 = this.f19598k;
                                    str17 = 0;
                                    ?? r24 = l.ERR_HTTP_RECV;
                                    num13 = num8;
                                    num14 = num35;
                                    z4 = true;
                                    str4 = str46;
                                    str21 = uuid;
                                    bVar.a(str2, bArr3, 0, 1, -1, 0.0d, "", r24);
                                    num4 = r24;
                                } else {
                                    num13 = num8;
                                    num14 = num35;
                                    str4 = str46;
                                    str21 = uuid;
                                    z4 = true;
                                    bVar.a(str2, this.f19598k, 0, num13.intValue(), num13.intValue() * (-1), 0.0d, "", l.ERR_HTTP_RECV);
                                    str17 = str17;
                                    num4 = num4;
                                }
                                this.f19593f = z4;
                                this.f19593f = z4;
                                i2 = -1;
                                break;
                            }
                            num13 = num8;
                            num14 = num35;
                            str4 = str46;
                            str21 = uuid;
                            try {
                                try {
                                    bVar.onTry(str2, l.ERR_HTTP);
                                    num35 = Integer.valueOf(num14.intValue() + 1);
                                } catch (IOException e22) {
                                    num34 = num13;
                                    iOException = e22;
                                    str45 = "";
                                    num35 = num14;
                                    str22 = str17;
                                    num15 = num4;
                                } catch (JSONException e23) {
                                    num34 = num13;
                                    jSONException = e23;
                                    i2 = i8;
                                    str45 = "";
                                    num35 = num14;
                                }
                                try {
                                    this.f19593f = true;
                                    this.f19593f = true;
                                    num34 = num13;
                                    str45 = "";
                                    str46 = str4;
                                    uuid = str21;
                                    str17 = str17;
                                    num4 = num4;
                                } catch (IOException e24) {
                                    num34 = num13;
                                    iOException = e24;
                                    str45 = "";
                                    str22 = str17;
                                    num15 = num4;
                                    str5 = str21;
                                    str23 = str22;
                                    num16 = num15;
                                    str19 = str23;
                                    num6 = num16;
                                    f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                    if (num35.intValue() <= B) {
                                    }
                                } catch (JSONException e25) {
                                    num34 = num13;
                                    jSONException = e25;
                                    i2 = i8;
                                    str45 = "";
                                    str3 = str21;
                                    f.c("HttpClient", "JSON E:req=" + str3 + ", seq=" + num34 + ", e3=" + jSONException.toString() + ", recv=" + str45);
                                    if (Integer.valueOf(num35.intValue() + 1).intValue() <= B) {
                                    }
                                    this.f19593f = z2;
                                    return i2;
                                }
                                z6 = false;
                            } catch (MalformedURLException e26) {
                                num34 = num13;
                                malformedURLException = e26;
                                i2 = i8;
                                str45 = "";
                                str6 = str21;
                                f.c("HttpClient", "URL E\uff1areq=" + str6 + ", seq=" + num34 + ", e1=" + malformedURLException.toString() + ", recv=" + str45);
                                if (this.b) {
                                    this.f19597j.clear();
                                    bVar.a(str2, this.f19598k, 0, 1, -1, 0.0d, "", l.ERR_SRV_Exception);
                                } else {
                                    bVar.a(str2, this.f19598k, 0, num34.intValue(), num34.intValue() * (-1), 0.0d, "", l.ERR_SRV_Exception);
                                }
                                z2 = true;
                                this.f19593f = true;
                                this.f19593f = z2;
                                return i2;
                            }
                        } else {
                            Integer num36 = num8;
                            Integer num37 = num35;
                            str4 = str46;
                            str21 = uuid;
                            z4 = true;
                            try {
                                StringBuilder sb5 = new StringBuilder();
                                sb5.append("http name=");
                                sb5.append(this.a);
                                sb5.append(", post ReqID=");
                                uuid = str21;
                                try {
                                    sb5.append(uuid);
                                    sb5.append(", index[3]=");
                                    num19 = num36;
                                    try {
                                        sb5.append(num19);
                                        sb5.append(", isStop=");
                                        sb5.append(this.f19593f);
                                        f.c("HttpClient", sb5.toString());
                                        string = execute.body().string();
                                        try {
                                            f.c("HttpClient", "http name=" + this.a + ", post ReqID=" + uuid + ", index[4]=" + num19 + ", isStop=" + this.f19593f);
                                        } catch (MalformedURLException e27) {
                                            e = e27;
                                            str25 = string;
                                            num11 = num19;
                                        } catch (IOException e28) {
                                            String str47 = string;
                                            Integer num38 = num19;
                                            Integer num39 = num37;
                                            iOException = e28;
                                            str5 = uuid;
                                        } catch (JSONException e29) {
                                            e = e29;
                                            str24 = string;
                                            num9 = num19;
                                            str3 = uuid;
                                        }
                                    } catch (MalformedURLException e30) {
                                        e = e30;
                                        num11 = num19;
                                        malformedURLException = e;
                                        str6 = uuid;
                                        i2 = i8;
                                        str45 = "";
                                        num34 = num11;
                                        f.c("HttpClient", "URL E\uff1areq=" + str6 + ", seq=" + num34 + ", e1=" + malformedURLException.toString() + ", recv=" + str45);
                                        if (this.b) {
                                        }
                                        z2 = true;
                                        this.f19593f = true;
                                        this.f19593f = z2;
                                        return i2;
                                    } catch (IOException e31) {
                                        e = e31;
                                        num18 = num19;
                                        iOException = e;
                                        str5 = uuid;
                                        str45 = "";
                                        num35 = num37;
                                        str20 = str17;
                                        num10 = num18;
                                        num34 = num10;
                                        str19 = str20;
                                        num6 = num10;
                                        f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                        if (num35.intValue() <= B) {
                                        }
                                    } catch (JSONException e32) {
                                        e = e32;
                                        num9 = num19;
                                        str3 = uuid;
                                        num17 = num37;
                                        jSONException = e;
                                        i2 = i8;
                                        str45 = "";
                                        num35 = num17;
                                        num34 = num9;
                                        f.c("HttpClient", "JSON E:req=" + str3 + ", seq=" + num34 + ", e3=" + jSONException.toString() + ", recv=" + str45);
                                        if (Integer.valueOf(num35.intValue() + 1).intValue() <= B) {
                                        }
                                        this.f19593f = z2;
                                        return i2;
                                    }
                                } catch (MalformedURLException e33) {
                                    e = e33;
                                    num11 = num36;
                                } catch (IOException e34) {
                                    e = e34;
                                    num18 = num36;
                                } catch (JSONException e35) {
                                    e = e35;
                                    num9 = num36;
                                }
                            } catch (MalformedURLException e36) {
                                malformedURLException = e36;
                                i2 = i8;
                                str45 = "";
                                num34 = num36;
                                str6 = str21;
                                f.c("HttpClient", "URL E\uff1areq=" + str6 + ", seq=" + num34 + ", e1=" + malformedURLException.toString() + ", recv=" + str45);
                                if (this.b) {
                                }
                                z2 = true;
                                this.f19593f = true;
                                this.f19593f = z2;
                                return i2;
                            } catch (IOException e37) {
                                Integer num40 = num36;
                                iOException = e37;
                                str45 = "";
                                num35 = num37;
                                num34 = num40;
                                str5 = str21;
                                str19 = str17;
                                num6 = num40;
                            } catch (JSONException e38) {
                                e = e38;
                                num9 = num36;
                                num17 = num37;
                                str3 = str21;
                            }
                            if (string.length() <= 0) {
                                try {
                                    try {
                                        sb = new StringBuilder();
                                        sb.append("http name=");
                                        sb.append(this.a);
                                        sb.append(", req=");
                                        sb.append(uuid);
                                        sb.append("\uff0cseq=");
                                        sb.append(num19);
                                        sb.append(", recv null, tryCount=");
                                        num35 = num37;
                                    } catch (IOException e39) {
                                        str14 = string;
                                        num34 = num19;
                                        iOException = e39;
                                        str5 = uuid;
                                        num35 = num37;
                                    } catch (JSONException e40) {
                                        str26 = string;
                                        num34 = num19;
                                        jSONException = e40;
                                        str3 = uuid;
                                        i2 = i8;
                                        num35 = num37;
                                    }
                                    try {
                                        sb.append(num35);
                                        f.b("HttpClient", sb.toString());
                                        try {
                                            Thread.sleep(1000L);
                                        } catch (IOException e41) {
                                            iOException = e41;
                                            str45 = string;
                                            num34 = num19;
                                            str5 = uuid;
                                            str23 = str17;
                                            num16 = num4;
                                            str19 = str23;
                                            num6 = num16;
                                            f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                            if (num35.intValue() <= B) {
                                            }
                                        } catch (InterruptedException unused2) {
                                        } catch (MalformedURLException e42) {
                                            malformedURLException = e42;
                                            str45 = string;
                                            num34 = num19;
                                            str6 = uuid;
                                            i2 = i8;
                                            f.c("HttpClient", "URL E\uff1areq=" + str6 + ", seq=" + num34 + ", e1=" + malformedURLException.toString() + ", recv=" + str45);
                                            if (this.b) {
                                            }
                                            z2 = true;
                                            this.f19593f = true;
                                            this.f19593f = z2;
                                            return i2;
                                        } catch (JSONException e43) {
                                            jSONException = e43;
                                            str45 = string;
                                            num34 = num19;
                                            str3 = uuid;
                                            i2 = i8;
                                            f.c("HttpClient", "JSON E:req=" + str3 + ", seq=" + num34 + ", e3=" + jSONException.toString() + ", recv=" + str45);
                                            if (Integer.valueOf(num35.intValue() + 1).intValue() <= B) {
                                            }
                                            this.f19593f = z2;
                                            return i2;
                                        }
                                    } catch (IOException e44) {
                                        str14 = string;
                                        num34 = num19;
                                        iOException = e44;
                                        str5 = uuid;
                                        str45 = str14;
                                        str23 = str17;
                                        num16 = num4;
                                        str19 = str23;
                                        num6 = num16;
                                        f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                        if (num35.intValue() <= B) {
                                        }
                                    } catch (JSONException e45) {
                                        str26 = string;
                                        num34 = num19;
                                        jSONException = e45;
                                        str3 = uuid;
                                        i2 = i8;
                                        str45 = str26;
                                        f.c("HttpClient", "JSON E:req=" + str3 + ", seq=" + num34 + ", e3=" + jSONException.toString() + ", recv=" + str45);
                                        if (Integer.valueOf(num35.intValue() + 1).intValue() <= B) {
                                        }
                                        this.f19593f = z2;
                                        return i2;
                                    }
                                    if (num35.intValue() > B) {
                                        if (!this.b) {
                                            this.f19597j.clear();
                                            str14 = string;
                                            num37 = num35;
                                            num36 = num19;
                                            str13 = uuid;
                                            bVar.a(str2, this.f19598k, 0, 1, -1, 0.0d, "", l.ERR_HTTP_RECV);
                                            str17 = "";
                                        } else {
                                            str14 = string;
                                            num37 = num35;
                                            num36 = num19;
                                            str13 = uuid;
                                            bVar.a(str2, this.f19598k, 0, num36.intValue(), num36.intValue() * (-1), 0.0d, "", l.ERR_HTTP_RECV);
                                            str17 = str17;
                                        }
                                        this.f19593f = true;
                                        this.f19593f = z4;
                                        i2 = -1;
                                        break;
                                    }
                                    try {
                                        try {
                                            str14 = string;
                                            str13 = uuid;
                                            num35 = Integer.valueOf(num35.intValue() + 1);
                                        } catch (IOException e46) {
                                            num34 = num36;
                                            iOException = e46;
                                            num35 = num37;
                                            str27 = str17;
                                        } catch (JSONException e47) {
                                            num34 = num36;
                                            jSONException = e47;
                                            i2 = i8;
                                            num35 = num37;
                                        }
                                        try {
                                            bVar.onTry(str2, l.ERR_HTTP_RECV);
                                            this.f19593f = true;
                                            num34 = num19;
                                            str46 = str4;
                                            str45 = str14;
                                            uuid = str13;
                                            str17 = str17;
                                            num4 = num4;
                                        } catch (IOException e48) {
                                            num34 = num19;
                                            iOException = e48;
                                            str27 = str17;
                                            str45 = str14;
                                            str5 = str13;
                                            str23 = str27;
                                            num16 = num4;
                                            str19 = str23;
                                            num6 = num16;
                                            f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                            if (num35.intValue() <= B) {
                                            }
                                        } catch (JSONException e49) {
                                            num34 = num19;
                                            jSONException = e49;
                                            i2 = i8;
                                            str45 = str14;
                                            str3 = str13;
                                            f.c("HttpClient", "JSON E:req=" + str3 + ", seq=" + num34 + ", e3=" + jSONException.toString() + ", recv=" + str45);
                                            if (Integer.valueOf(num35.intValue() + 1).intValue() <= B) {
                                            }
                                            this.f19593f = z2;
                                            return i2;
                                        }
                                        z6 = false;
                                    } catch (MalformedURLException e50) {
                                        num34 = num36;
                                        malformedURLException = e50;
                                        i2 = i8;
                                        str45 = str14;
                                        str6 = str13;
                                        f.c("HttpClient", "URL E\uff1areq=" + str6 + ", seq=" + num34 + ", e1=" + malformedURLException.toString() + ", recv=" + str45);
                                        if (this.b) {
                                        }
                                        z2 = true;
                                        this.f19593f = true;
                                        this.f19593f = z2;
                                        return i2;
                                    }
                                } catch (MalformedURLException e51) {
                                    num34 = num19;
                                    malformedURLException = e51;
                                    str6 = uuid;
                                    i2 = i8;
                                    str45 = string;
                                    f.c("HttpClient", "URL E\uff1areq=" + str6 + ", seq=" + num34 + ", e1=" + malformedURLException.toString() + ", recv=" + str45);
                                    if (this.b) {
                                    }
                                    z2 = true;
                                    this.f19593f = true;
                                    this.f19593f = z2;
                                    return i2;
                                }
                            } else {
                                str14 = string;
                                Integer num41 = num19;
                                str13 = uuid;
                                try {
                                } catch (MalformedURLException e52) {
                                    e = e52;
                                    num23 = num41;
                                    str29 = str14;
                                } catch (IOException e53) {
                                    e = e53;
                                    num21 = num41;
                                    num22 = num37;
                                    str28 = str14;
                                } catch (JSONException e54) {
                                    e = e54;
                                    num9 = num41;
                                    num20 = num37;
                                    str24 = str14;
                                }
                                try {
                                    jSONObject = new JSONObject(str14);
                                } catch (MalformedURLException e55) {
                                    e = e55;
                                    num23 = num41;
                                    str29 = str14;
                                    malformedURLException = e;
                                    i2 = i8;
                                    str45 = str29;
                                    num34 = num23;
                                    str6 = str13;
                                    f.c("HttpClient", "URL E\uff1areq=" + str6 + ", seq=" + num34 + ", e1=" + malformedURLException.toString() + ", recv=" + str45);
                                    if (this.b) {
                                    }
                                    z2 = true;
                                    this.f19593f = true;
                                    this.f19593f = z2;
                                    return i2;
                                } catch (IOException e56) {
                                    e = e56;
                                    num21 = num41;
                                    str28 = str14;
                                    num22 = num37;
                                    iOException = e;
                                    num35 = num22;
                                    str45 = str28;
                                    num34 = num21;
                                    str5 = str13;
                                    str19 = str28;
                                    num6 = num21;
                                    f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                    if (num35.intValue() <= B) {
                                    }
                                } catch (JSONException e57) {
                                    e = e57;
                                    num9 = num41;
                                    str24 = str14;
                                    num20 = num37;
                                    str3 = str13;
                                    jSONException = e;
                                    i2 = i8;
                                    num35 = num20;
                                    str45 = str24;
                                    num34 = num9;
                                    f.c("HttpClient", "JSON E:req=" + str3 + ", seq=" + num34 + ", e3=" + jSONException.toString() + ", recv=" + str45);
                                    if (Integer.valueOf(num35.intValue() + 1).intValue() <= B) {
                                    }
                                    this.f19593f = z2;
                                    return i2;
                                }
                                try {
                                    try {
                                        if (w.equals("0")) {
                                            try {
                                            } catch (MalformedURLException e58) {
                                                str11 = str14;
                                                num34 = num41;
                                                malformedURLException = e58;
                                                i2 = i8;
                                                str6 = str13;
                                            } catch (IOException e59) {
                                                str11 = str14;
                                                num34 = num41;
                                                iOException = e59;
                                                num35 = num37;
                                                str5 = str13;
                                                str30 = str17;
                                            } catch (JSONException e60) {
                                                str11 = str14;
                                                num34 = num41;
                                                jSONException = e60;
                                                i2 = i8;
                                                num35 = num37;
                                                str3 = str13;
                                            }
                                            if (!jSONObject.getString("code").equals("10000")) {
                                                StringBuilder sb6 = new StringBuilder();
                                                sb6.append("http name=");
                                                sb6.append(this.a);
                                                sb6.append(", !10000:req=");
                                                try {
                                                    sb6.append(str13);
                                                    sb6.append("seq=");
                                                } catch (MalformedURLException e61) {
                                                    e = e61;
                                                } catch (IOException e62) {
                                                    e = e62;
                                                } catch (JSONException e63) {
                                                    e = e63;
                                                }
                                                try {
                                                    try {
                                                        sb6.append(num41);
                                                        sb6.append(", tryCount=");
                                                        num35 = num37;
                                                        try {
                                                            sb6.append(num35);
                                                            sb6.append(", recv=");
                                                            sb6.append(str14);
                                                            f.c("HttpClient", sb6.toString());
                                                            try {
                                                                Thread.sleep(1000L);
                                                            } catch (IOException e64) {
                                                                iOException = e64;
                                                                num34 = num41;
                                                                str5 = str13;
                                                                str45 = str14;
                                                                str23 = str17;
                                                                num16 = num4;
                                                                str19 = str23;
                                                                num6 = num16;
                                                                f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                                                if (num35.intValue() <= B) {
                                                                }
                                                            } catch (InterruptedException unused3) {
                                                            } catch (MalformedURLException e65) {
                                                                malformedURLException = e65;
                                                                num34 = num41;
                                                                str6 = str13;
                                                                str45 = str14;
                                                                i2 = i8;
                                                                f.c("HttpClient", "URL E\uff1areq=" + str6 + ", seq=" + num34 + ", e1=" + malformedURLException.toString() + ", recv=" + str45);
                                                                if (this.b) {
                                                                }
                                                                z2 = true;
                                                                this.f19593f = true;
                                                                this.f19593f = z2;
                                                                return i2;
                                                            } catch (JSONException e66) {
                                                                jSONException = e66;
                                                                num34 = num41;
                                                                str3 = str13;
                                                                str45 = str14;
                                                                i2 = i8;
                                                                f.c("HttpClient", "JSON E:req=" + str3 + ", seq=" + num34 + ", e3=" + jSONException.toString() + ", recv=" + str45);
                                                                if (Integer.valueOf(num35.intValue() + 1).intValue() <= B) {
                                                                }
                                                                this.f19593f = z2;
                                                                return i2;
                                                            }
                                                        } catch (IOException e67) {
                                                            str11 = str14;
                                                            num34 = num41;
                                                            iOException = e67;
                                                            str5 = str13;
                                                            str30 = str17;
                                                        } catch (JSONException e68) {
                                                            str11 = str14;
                                                            num34 = num41;
                                                            jSONException = e68;
                                                            str3 = str13;
                                                            i2 = i8;
                                                        }
                                                    } catch (IOException e69) {
                                                        e = e69;
                                                        num41 = num41;
                                                        str11 = str14;
                                                        num34 = num41;
                                                        iOException = e;
                                                        str5 = str13;
                                                        num35 = num37;
                                                        str30 = str17;
                                                        str45 = str11;
                                                        str23 = str30;
                                                        num16 = num4;
                                                        str19 = str23;
                                                        num6 = num16;
                                                        f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                                        if (num35.intValue() <= B) {
                                                        }
                                                    } catch (JSONException e70) {
                                                        e = e70;
                                                        num41 = num41;
                                                        str11 = str14;
                                                        num34 = num41;
                                                        jSONException = e;
                                                        str3 = str13;
                                                        i2 = i8;
                                                        num35 = num37;
                                                        str45 = str11;
                                                        f.c("HttpClient", "JSON E:req=" + str3 + ", seq=" + num34 + ", e3=" + jSONException.toString() + ", recv=" + str45);
                                                        if (Integer.valueOf(num35.intValue() + 1).intValue() <= B) {
                                                        }
                                                        this.f19593f = z2;
                                                        return i2;
                                                    }
                                                    if (num35.intValue() > B) {
                                                        if (!this.b) {
                                                            this.f19597j.clear();
                                                            str17 = "";
                                                            num41 = num41;
                                                            num37 = num35;
                                                            str12 = str13;
                                                            str11 = str14;
                                                            bVar.a(str2, this.f19598k, 0, 1, -1, 0.0d, "", l.ERR_GateWay_Status);
                                                            str17 = "";
                                                        } else {
                                                            num41 = num41;
                                                            num37 = num35;
                                                            str12 = str13;
                                                            str11 = str14;
                                                            bVar.a(str2, this.f19598k, 0, num41.intValue(), num41.intValue() * (-1), 0.0d, "", l.ERR_GateWay_Status);
                                                            str17 = str17;
                                                        }
                                                        this.f19593f = true;
                                                        this.f19593f = z4;
                                                        i2 = -1;
                                                        break;
                                                    }
                                                    try {
                                                        try {
                                                            str12 = str13;
                                                            str11 = str14;
                                                            num35 = Integer.valueOf(num35.intValue() + 1);
                                                        } catch (IOException e71) {
                                                            num34 = num41;
                                                            iOException = e71;
                                                            num35 = num37;
                                                            str31 = str17;
                                                        } catch (JSONException e72) {
                                                            num34 = num41;
                                                            jSONException = e72;
                                                            i2 = i8;
                                                            num35 = num37;
                                                        }
                                                        try {
                                                            bVar.onTry(str2, l.ERR_GateWay_Status);
                                                            this.f19593f = true;
                                                            num34 = num41;
                                                            str46 = str4;
                                                            uuid = str12;
                                                            str45 = str11;
                                                            str17 = str17;
                                                            num4 = num4;
                                                        } catch (IOException e73) {
                                                            num34 = num41;
                                                            iOException = e73;
                                                            str31 = str17;
                                                            str5 = str12;
                                                            str30 = str31;
                                                            str45 = str11;
                                                            str23 = str30;
                                                            num16 = num4;
                                                            str19 = str23;
                                                            num6 = num16;
                                                            f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                                            if (num35.intValue() <= B) {
                                                            }
                                                        } catch (JSONException e74) {
                                                            num34 = num41;
                                                            jSONException = e74;
                                                            i2 = i8;
                                                            str3 = str12;
                                                            str45 = str11;
                                                            f.c("HttpClient", "JSON E:req=" + str3 + ", seq=" + num34 + ", e3=" + jSONException.toString() + ", recv=" + str45);
                                                            if (Integer.valueOf(num35.intValue() + 1).intValue() <= B) {
                                                                if (!this.b) {
                                                                    this.f19597j.clear();
                                                                    bVar.a(str2, this.f19598k, 0, 1, -1, 0.0d, "", l.ERR_SRV_JSON);
                                                                } else {
                                                                    bVar.a(str2, this.f19598k, 0, num34.intValue(), num34.intValue() * (-1), 0.0d, "", l.ERR_SRV_JSON);
                                                                }
                                                                z2 = true;
                                                                this.f19593f = true;
                                                            } else {
                                                                z2 = true;
                                                                this.f19593f = true;
                                                            }
                                                            this.f19593f = z2;
                                                            return i2;
                                                        }
                                                        z6 = false;
                                                    } catch (MalformedURLException e75) {
                                                        num34 = num41;
                                                        malformedURLException = e75;
                                                        i2 = i8;
                                                        str6 = str12;
                                                        str45 = str11;
                                                        f.c("HttpClient", "URL E\uff1areq=" + str6 + ", seq=" + num34 + ", e1=" + malformedURLException.toString() + ", recv=" + str45);
                                                        if (this.b) {
                                                        }
                                                        z2 = true;
                                                        this.f19593f = true;
                                                        this.f19593f = z2;
                                                        return i2;
                                                    }
                                                } catch (MalformedURLException e76) {
                                                    e = e76;
                                                    num41 = num41;
                                                    str11 = str14;
                                                    num34 = num41;
                                                    malformedURLException = e;
                                                    str6 = str13;
                                                    i2 = i8;
                                                    str45 = str11;
                                                    f.c("HttpClient", "URL E\uff1areq=" + str6 + ", seq=" + num34 + ", e1=" + malformedURLException.toString() + ", recv=" + str45);
                                                    if (this.b) {
                                                    }
                                                    z2 = true;
                                                    this.f19593f = true;
                                                    this.f19593f = z2;
                                                    return i2;
                                                }
                                            } else {
                                                str11 = str14;
                                                str12 = str13;
                                                jSONObject4 = jSONObject.getJSONObject("result");
                                            }
                                        } else {
                                            str11 = str14;
                                            str12 = str13;
                                            if (w.equals("1")) {
                                                jSONObject2 = jSONObject;
                                                i3 = jSONObject2.getInt("status");
                                                if (i3 != 30201 || i3 == 30202 || i3 == 30252 || i3 == 30253) {
                                                    str9 = str12;
                                                    str10 = str11;
                                                    StringBuilder sb7 = new StringBuilder();
                                                    sb7.append("http name=");
                                                    sb7.append(this.a);
                                                    sb7.append(", status !0:req0=");
                                                    sb7.append(str9);
                                                    sb7.append("seq=");
                                                    sb7.append(num41);
                                                    sb7.append(", recv=");
                                                    sb7.append(str10);
                                                    f.c("HttpClient", sb7.toString());
                                                    if (this.b) {
                                                        try {
                                                            this.f19597j.clear();
                                                            num41 = num41;
                                                            str32 = str10;
                                                            str33 = str9;
                                                            jSONObject3 = jSONObject2;
                                                        } catch (MalformedURLException e77) {
                                                            str32 = str10;
                                                            num34 = num41;
                                                            malformedURLException = e77;
                                                            str6 = str9;
                                                            i2 = i8;
                                                            str45 = str32;
                                                            f.c("HttpClient", "URL E\uff1areq=" + str6 + ", seq=" + num34 + ", e1=" + malformedURLException.toString() + ", recv=" + str45);
                                                            if (this.b) {
                                                            }
                                                            z2 = true;
                                                            this.f19593f = true;
                                                            this.f19593f = z2;
                                                            return i2;
                                                        } catch (IOException e78) {
                                                            str32 = str10;
                                                            num34 = num41;
                                                            iOException = e78;
                                                            str5 = str9;
                                                            num35 = num37;
                                                            str45 = str32;
                                                            str23 = str17;
                                                            num16 = num4;
                                                            str19 = str23;
                                                            num6 = num16;
                                                            f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                                            if (num35.intValue() <= B) {
                                                            }
                                                        } catch (JSONException e79) {
                                                            str32 = str10;
                                                            num34 = num41;
                                                            jSONException = e79;
                                                            str3 = str9;
                                                            i2 = i8;
                                                            num35 = num37;
                                                            str45 = str32;
                                                            f.c("HttpClient", "JSON E:req=" + str3 + ", seq=" + num34 + ", e3=" + jSONException.toString() + ", recv=" + str45);
                                                            if (Integer.valueOf(num35.intValue() + 1).intValue() <= B) {
                                                            }
                                                            this.f19593f = z2;
                                                            return i2;
                                                        }
                                                        try {
                                                            bVar.a(str2, this.f19598k, 0, 1, -1, 0.0d, "", l.ERR_Engine_Status);
                                                            i4 = "";
                                                        } catch (IOException e80) {
                                                            num34 = num41;
                                                            iOException = e80;
                                                            num35 = num37;
                                                            str45 = str32;
                                                            str5 = str33;
                                                            str23 = str17;
                                                            num16 = num4;
                                                            str19 = str23;
                                                            num6 = num16;
                                                            f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                                            if (num35.intValue() <= B) {
                                                            }
                                                        }
                                                    } else {
                                                        num41 = num41;
                                                        str32 = str10;
                                                        str33 = str9;
                                                        jSONObject3 = jSONObject2;
                                                        try {
                                                            bVar.a(str2, this.f19598k, 0, num41.intValue(), num41.intValue() * (-1), 0.0d, "", l.ERR_Engine_Status);
                                                            i4 = "";
                                                        } catch (IOException e81) {
                                                            Integer num42 = num41;
                                                            String str48 = str32;
                                                            iOException = e81;
                                                            num35 = num37;
                                                            str45 = str48;
                                                            num34 = num42;
                                                            str5 = str33;
                                                            str19 = str48;
                                                            num6 = num42;
                                                            f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                                            if (num35.intValue() <= B) {
                                                                f.c("HttpClient", "tryCount > " + B + ", isStream=" + this.b);
                                                                if (!this.b) {
                                                                    this.f19597j.clear();
                                                                    f.c("HttpClient", "Timeout:! isStream");
                                                                    bVar.a(str2, this.f19598k, 0, 1, -1, 0.0d, "", l.ERR_SRV_TIMEOUT);
                                                                } else {
                                                                    f.c("HttpClient", "Timeout: isStream");
                                                                    bVar.a(str2, this.f19598k, 0, num34.intValue(), num34.intValue() * (-1), 0.0d, "", l.ERR_SRV_TIMEOUT);
                                                                }
                                                                z3 = true;
                                                                this.f19593f = true;
                                                                this.f19593f = z3;
                                                                i2 = i8;
                                                                return i2;
                                                            }
                                                            this.f19593f = true;
                                                            Integer valueOf2 = Integer.valueOf(num35.intValue() + 1);
                                                            this.f19593f = true;
                                                            num35 = valueOf2;
                                                            str18 = str19;
                                                            num5 = num6;
                                                            str44 = str;
                                                            uuid = str5;
                                                            str46 = str4;
                                                            str17 = str18;
                                                            num4 = num5;
                                                            z6 = false;
                                                        }
                                                    }
                                                } else if (i3 != 0) {
                                                    try {
                                                        sb3 = new StringBuilder();
                                                        sb3.append("http name=");
                                                        sb3.append(this.a);
                                                        sb3.append(", status !0:req1=");
                                                        uuid = str12;
                                                        try {
                                                            sb3.append(uuid);
                                                            sb3.append("seq=");
                                                            num19 = num41;
                                                        } catch (MalformedURLException e82) {
                                                            e = e82;
                                                        } catch (IOException e83) {
                                                            e = e83;
                                                        } catch (JSONException e84) {
                                                            e = e84;
                                                        }
                                                    } catch (MalformedURLException e85) {
                                                        str41 = str11;
                                                        num34 = num41;
                                                        malformedURLException = e85;
                                                        i2 = i8;
                                                        str6 = str12;
                                                    } catch (IOException e86) {
                                                        str10 = str11;
                                                        num34 = num41;
                                                        iOException = e86;
                                                        num35 = num37;
                                                        str5 = str12;
                                                    } catch (JSONException e87) {
                                                        str40 = str11;
                                                        num34 = num41;
                                                        jSONException = e87;
                                                        i2 = i8;
                                                        num35 = num37;
                                                        str3 = str12;
                                                    }
                                                    try {
                                                        try {
                                                            sb3.append(num19);
                                                            sb3.append(", tryCount=");
                                                            try {
                                                                sb3.append(num37);
                                                                sb3.append(", recv=");
                                                                str42 = str11;
                                                                try {
                                                                    sb3.append(str42);
                                                                    f.c("HttpClient", sb3.toString());
                                                                    try {
                                                                        Thread.sleep(1000L);
                                                                    } catch (MalformedURLException e88) {
                                                                        malformedURLException = e88;
                                                                        str45 = str42;
                                                                        num34 = num19;
                                                                        str6 = uuid;
                                                                        i2 = i8;
                                                                        f.c("HttpClient", "URL E\uff1areq=" + str6 + ", seq=" + num34 + ", e1=" + malformedURLException.toString() + ", recv=" + str45);
                                                                        if (this.b) {
                                                                        }
                                                                        z2 = true;
                                                                        this.f19593f = true;
                                                                        this.f19593f = z2;
                                                                        return i2;
                                                                    } catch (IOException e89) {
                                                                        iOException = e89;
                                                                        str45 = str42;
                                                                        num34 = num19;
                                                                        str5 = uuid;
                                                                        num35 = num37;
                                                                        str19 = str17;
                                                                        num6 = num4;
                                                                        f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                                                        if (num35.intValue() <= B) {
                                                                        }
                                                                    } catch (InterruptedException unused4) {
                                                                    } catch (JSONException e90) {
                                                                        jSONException = e90;
                                                                        str45 = str42;
                                                                        num34 = num19;
                                                                        str3 = uuid;
                                                                        i2 = i8;
                                                                        num35 = num37;
                                                                        f.c("HttpClient", "JSON E:req=" + str3 + ", seq=" + num34 + ", e3=" + jSONException.toString() + ", recv=" + str45);
                                                                        if (Integer.valueOf(num35.intValue() + 1).intValue() <= B) {
                                                                        }
                                                                        this.f19593f = z2;
                                                                        return i2;
                                                                    }
                                                                } catch (MalformedURLException e91) {
                                                                    e = e91;
                                                                    str41 = str42;
                                                                    num41 = num19;
                                                                    num34 = num41;
                                                                    malformedURLException = e;
                                                                    str6 = uuid;
                                                                    i2 = i8;
                                                                    str45 = str41;
                                                                    f.c("HttpClient", "URL E\uff1areq=" + str6 + ", seq=" + num34 + ", e1=" + malformedURLException.toString() + ", recv=" + str45);
                                                                    if (this.b) {
                                                                    }
                                                                    z2 = true;
                                                                    this.f19593f = true;
                                                                    this.f19593f = z2;
                                                                    return i2;
                                                                } catch (IOException e92) {
                                                                    e = e92;
                                                                    num37 = num37;
                                                                    str10 = str42;
                                                                    num41 = num19;
                                                                    num34 = num41;
                                                                    iOException = e;
                                                                    str5 = uuid;
                                                                    num35 = num37;
                                                                    str45 = str10;
                                                                    str23 = str17;
                                                                    num16 = num4;
                                                                    str19 = str23;
                                                                    num6 = num16;
                                                                    f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                                                    if (num35.intValue() <= B) {
                                                                    }
                                                                } catch (JSONException e93) {
                                                                    e = e93;
                                                                    num37 = num37;
                                                                    str40 = str42;
                                                                    num41 = num19;
                                                                    num34 = num41;
                                                                    jSONException = e;
                                                                    str3 = uuid;
                                                                    i2 = i8;
                                                                    num35 = num37;
                                                                    str45 = str40;
                                                                    f.c("HttpClient", "JSON E:req=" + str3 + ", seq=" + num34 + ", e3=" + jSONException.toString() + ", recv=" + str45);
                                                                    if (Integer.valueOf(num35.intValue() + 1).intValue() <= B) {
                                                                    }
                                                                    this.f19593f = z2;
                                                                    return i2;
                                                                }
                                                            } catch (IOException e94) {
                                                                e = e94;
                                                                num37 = num37;
                                                                num41 = num19;
                                                                str10 = str11;
                                                                num34 = num41;
                                                                iOException = e;
                                                                str5 = uuid;
                                                                num35 = num37;
                                                                str45 = str10;
                                                                str23 = str17;
                                                                num16 = num4;
                                                                str19 = str23;
                                                                num6 = num16;
                                                                f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                                                if (num35.intValue() <= B) {
                                                                }
                                                            } catch (JSONException e95) {
                                                                e = e95;
                                                                num37 = num37;
                                                                num41 = num19;
                                                                str40 = str11;
                                                                num34 = num41;
                                                                jSONException = e;
                                                                str3 = uuid;
                                                                i2 = i8;
                                                                num35 = num37;
                                                                str45 = str40;
                                                                f.c("HttpClient", "JSON E:req=" + str3 + ", seq=" + num34 + ", e3=" + jSONException.toString() + ", recv=" + str45);
                                                                if (Integer.valueOf(num35.intValue() + 1).intValue() <= B) {
                                                                }
                                                                this.f19593f = z2;
                                                                return i2;
                                                            }
                                                        } catch (IOException e96) {
                                                            e = e96;
                                                        } catch (JSONException e97) {
                                                            e = e97;
                                                        }
                                                        if (num37.intValue() > B) {
                                                            if (!this.b) {
                                                                this.f19597j.clear();
                                                                num37 = num37;
                                                                str10 = str42;
                                                                num41 = num19;
                                                                str9 = uuid;
                                                                bVar.a(str2, this.f19598k, 0, 1, -1, 0.0d, "", l.ERR_Engine_Status);
                                                                str17 = "";
                                                            } else {
                                                                num37 = num37;
                                                                str10 = str42;
                                                                num41 = num19;
                                                                str9 = uuid;
                                                                bVar.a(str2, this.f19598k, 0, num41.intValue(), num41.intValue() * (-1), 0.0d, "", l.ERR_Engine_Status);
                                                                str17 = str17;
                                                            }
                                                            this.f19593f = true;
                                                            this.f19593f = z4;
                                                            i2 = -1;
                                                            break;
                                                        }
                                                        try {
                                                            try {
                                                                str10 = str42;
                                                                str9 = uuid;
                                                                num35 = Integer.valueOf(num37.intValue() + 1);
                                                                try {
                                                                    bVar.onTry(str2, l.ERR_Engine_Status);
                                                                    this.f19593f = true;
                                                                    num34 = num19;
                                                                    str46 = str4;
                                                                    str45 = str10;
                                                                    uuid = str9;
                                                                    str17 = str17;
                                                                    num4 = num4;
                                                                } catch (IOException e98) {
                                                                    num34 = num19;
                                                                    iOException = e98;
                                                                    str43 = str17;
                                                                    str45 = str10;
                                                                    str5 = str9;
                                                                    str23 = str43;
                                                                    num16 = num4;
                                                                    str19 = str23;
                                                                    num6 = num16;
                                                                    f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                                                    if (num35.intValue() <= B) {
                                                                    }
                                                                } catch (JSONException e99) {
                                                                    num34 = num19;
                                                                    jSONException = e99;
                                                                    i2 = i8;
                                                                    str45 = str10;
                                                                    str3 = str9;
                                                                    f.c("HttpClient", "JSON E:req=" + str3 + ", seq=" + num34 + ", e3=" + jSONException.toString() + ", recv=" + str45);
                                                                    if (Integer.valueOf(num35.intValue() + 1).intValue() <= B) {
                                                                    }
                                                                    this.f19593f = z2;
                                                                    return i2;
                                                                }
                                                            } catch (IOException e100) {
                                                                num34 = num41;
                                                                iOException = e100;
                                                                num35 = num37;
                                                                str43 = str17;
                                                            } catch (JSONException e101) {
                                                                num34 = num41;
                                                                jSONException = e101;
                                                                i2 = i8;
                                                                num35 = num37;
                                                            }
                                                            z6 = false;
                                                        } catch (MalformedURLException e102) {
                                                            num34 = num41;
                                                            malformedURLException = e102;
                                                            i2 = i8;
                                                            str45 = str10;
                                                            str6 = str9;
                                                            f.c("HttpClient", "URL E\uff1areq=" + str6 + ", seq=" + num34 + ", e1=" + malformedURLException.toString() + ", recv=" + str45);
                                                            if (this.b) {
                                                            }
                                                            z2 = true;
                                                            this.f19593f = true;
                                                            this.f19593f = z2;
                                                            return i2;
                                                        }
                                                    } catch (MalformedURLException e103) {
                                                        e = e103;
                                                        num41 = num19;
                                                        str41 = str11;
                                                        num34 = num41;
                                                        malformedURLException = e;
                                                        str6 = uuid;
                                                        i2 = i8;
                                                        str45 = str41;
                                                        f.c("HttpClient", "URL E\uff1areq=" + str6 + ", seq=" + num34 + ", e1=" + malformedURLException.toString() + ", recv=" + str45);
                                                        if (this.b) {
                                                        }
                                                        z2 = true;
                                                        this.f19593f = true;
                                                        this.f19593f = z2;
                                                        return i2;
                                                    }
                                                } else {
                                                    jSONObject3 = jSONObject2;
                                                    str33 = str12;
                                                    str32 = str11;
                                                    i4 = "";
                                                }
                                                int i9 = jSONObject3.getInt("index");
                                                double d = jSONObject3.getDouble(NotificationCompat.CATEGORY_PROGRESS);
                                                string2 = jSONObject3.getString("audio");
                                                boolean has = jSONObject3.has(VerifyTracker.KEY_TIMESTAMP);
                                                if (num41.intValue() == 1 && i9 < 0) {
                                                    try {
                                                        try {
                                                        } catch (MalformedURLException e104) {
                                                            num34 = num41;
                                                            malformedURLException = e104;
                                                            i2 = i8;
                                                            str45 = str32;
                                                            str6 = str33;
                                                            f.c("HttpClient", "URL E\uff1areq=" + str6 + ", seq=" + num34 + ", e1=" + malformedURLException.toString() + ", recv=" + str45);
                                                            if (this.b) {
                                                            }
                                                            z2 = true;
                                                            this.f19593f = true;
                                                            this.f19593f = z2;
                                                            return i2;
                                                        } catch (JSONException e105) {
                                                            num34 = num41;
                                                            jSONException = e105;
                                                            i2 = i8;
                                                            num35 = num37;
                                                            str45 = str32;
                                                            str3 = str33;
                                                            f.c("HttpClient", "JSON E:req=" + str3 + ", seq=" + num34 + ", e3=" + jSONException.toString() + ", recv=" + str45);
                                                            if (Integer.valueOf(num35.intValue() + 1).intValue() <= B) {
                                                            }
                                                            this.f19593f = z2;
                                                            return i2;
                                                        }
                                                    } catch (IOException e106) {
                                                        num34 = num41;
                                                        iOException = e106;
                                                        i7 = i9;
                                                        num35 = num37;
                                                        str45 = str32;
                                                        str5 = str33;
                                                        str23 = str17;
                                                        num16 = num4;
                                                        str19 = str23;
                                                        num6 = num16;
                                                        f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                                        if (num35.intValue() <= B) {
                                                        }
                                                    }
                                                    if (string2.length() == 0) {
                                                        StringBuilder sb8 = new StringBuilder();
                                                        sb8.append("reqID=");
                                                        String str49 = str33;
                                                        try {
                                                            try {
                                                                sb8.append(str49);
                                                                sb8.append(", return null");
                                                                f.c("HttpClient", sb8.toString());
                                                                this.f19593f = true;
                                                                bArr2 = new byte[1];
                                                            } catch (MalformedURLException e107) {
                                                                num34 = num41;
                                                                malformedURLException = e107;
                                                                str6 = str49;
                                                                i2 = i8;
                                                                str45 = str32;
                                                                f.c("HttpClient", "URL E\uff1areq=" + str6 + ", seq=" + num34 + ", e1=" + malformedURLException.toString() + ", recv=" + str45);
                                                                if (this.b) {
                                                                }
                                                                z2 = true;
                                                                this.f19593f = true;
                                                                this.f19593f = z2;
                                                                return i2;
                                                            } catch (JSONException e108) {
                                                                num34 = num41;
                                                                jSONException = e108;
                                                                str3 = str49;
                                                                i2 = i8;
                                                                num35 = num37;
                                                                str45 = str32;
                                                                f.c("HttpClient", "JSON E:req=" + str3 + ", seq=" + num34 + ", e3=" + jSONException.toString() + ", recv=" + str45);
                                                                if (Integer.valueOf(num35.intValue() + 1).intValue() <= B) {
                                                                }
                                                                this.f19593f = z2;
                                                                return i2;
                                                            }
                                                        } catch (IOException e109) {
                                                            e = e109;
                                                            i4 = i9;
                                                        }
                                                        try {
                                                            bArr2[0] = 0;
                                                            try {
                                                                if (!this.b) {
                                                                    this.f19597j.clear();
                                                                    i4 = i9;
                                                                    bVar.a(str2, bArr2, 0, num41.intValue(), i9, 0.0d, "", l.OK_NULL);
                                                                } else {
                                                                    i4 = i9;
                                                                    bVar.a(str2, bArr2, 0, num41.intValue(), i4, 0.0d, "", l.OK_NULL);
                                                                }
                                                                z3 = true;
                                                            } catch (IOException e110) {
                                                                e = e110;
                                                                num34 = num41;
                                                                iOException = e;
                                                                str5 = str49;
                                                                i7 = i4;
                                                                num35 = num37;
                                                                str45 = str32;
                                                                str23 = str17;
                                                                num16 = num4;
                                                                str19 = str23;
                                                                num6 = num16;
                                                                f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                                                if (num35.intValue() <= B) {
                                                                }
                                                            }
                                                        } catch (IOException e111) {
                                                            num34 = num41;
                                                            iOException = e111;
                                                            str5 = str49;
                                                            i7 = i9;
                                                            num35 = num37;
                                                            str45 = str32;
                                                            str19 = str17;
                                                            num6 = num4;
                                                            f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                                            if (num35.intValue() <= B) {
                                                            }
                                                        }
                                                        this.f19593f = z3;
                                                        break;
                                                    }
                                                }
                                                Object obj4 = i9;
                                                uuid = str33;
                                                if (string2.length() == 0) {
                                                    i5 = obj4;
                                                } else if (obj4 < 0) {
                                                    try {
                                                        try {
                                                            f.c("HttpClient", "reqID=" + uuid + ", pack null");
                                                            Object obj5 = this.f19596i;
                                                            try {
                                                                try {
                                                                    synchronized (obj5) {
                                                                        try {
                                                                            if (!this.f19592e) {
                                                                                if (this.b) {
                                                                                    try {
                                                                                        if (has) {
                                                                                            try {
                                                                                                obj4 = obj5;
                                                                                                num4 = obj4;
                                                                                                bVar.a(str2, "0".getBytes(), 0, num41.intValue(), obj4, d, jSONObject3.getString(VerifyTracker.KEY_TIMESTAMP), l.OK_NO);
                                                                                            } catch (Throwable th6) {
                                                                                                obj2 = obj5;
                                                                                                th2 = th6;
                                                                                                throw th2;
                                                                                            }
                                                                                        } else {
                                                                                            obj4 = obj5;
                                                                                            ?? r242 = obj4;
                                                                                            bVar.a(str2, "0".getBytes(), 0, num41.intValue(), r242, d, "", l.OK_NO);
                                                                                            num4 = r242;
                                                                                        }
                                                                                        obj3 = obj4;
                                                                                    } catch (Throwable th7) {
                                                                                        th2 = th7;
                                                                                        obj2 = obj4;
                                                                                    }
                                                                                } else {
                                                                                    obj4 = obj5;
                                                                                    try {
                                                                                        bArr = new byte[]{0};
                                                                                        bVar2 = new g.g.a.b();
                                                                                        bVar2.l(str2);
                                                                                        bVar2.k(num41.intValue());
                                                                                        i6 = obj4;
                                                                                    } catch (Throwable th8) {
                                                                                        th = th8;
                                                                                    }
                                                                                    try {
                                                                                        bVar2.h(i6);
                                                                                        bVar2.i(bArr);
                                                                                        bVar2.j(d);
                                                                                        bVar2.g(l.OK_NULL);
                                                                                        this.f19597j.add(bVar2);
                                                                                        if (i6 < 0) {
                                                                                            while (true) {
                                                                                                g.g.a.b poll = this.f19597j.poll();
                                                                                                if (poll == null) {
                                                                                                    break;
                                                                                                }
                                                                                                int i10 = i6;
                                                                                                bVar.a(poll.f(), poll.c(), poll.c().length, poll.e(), poll.b(), poll.d(), "", poll.a());
                                                                                                i6 = i10;
                                                                                            }
                                                                                            obj3 = obj4;
                                                                                        } else {
                                                                                            obj3 = obj4;
                                                                                        }
                                                                                    } catch (Throwable th9) {
                                                                                        th = th9;
                                                                                        th2 = th;
                                                                                        obj2 = obj4;
                                                                                        throw th2;
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                obj3 = obj5;
                                                                                this.f19591c.stop();
                                                                            }
                                                                            z5 = true;
                                                                            this.f19593f = true;
                                                                            this.f19593f = z5;
                                                                            i2 = -1;
                                                                            break;
                                                                        } catch (Throwable th10) {
                                                                            th = th10;
                                                                            obj4 = obj5;
                                                                        }
                                                                    }
                                                                } catch (IOException e112) {
                                                                    e = e112;
                                                                    num34 = num41;
                                                                    i7 = str44;
                                                                    str5 = jSONObject3;
                                                                    num35 = num37;
                                                                    str45 = str32;
                                                                    str16 = str17;
                                                                    num3 = num4;
                                                                    str15 = str16;
                                                                    num2 = num3;
                                                                    iOException = e;
                                                                    str19 = str15;
                                                                    num6 = num2;
                                                                    f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                                                    if (num35.intValue() <= B) {
                                                                    }
                                                                }
                                                            } catch (Throwable th11) {
                                                                th = th11;
                                                            }
                                                        } catch (MalformedURLException e113) {
                                                            num34 = num41;
                                                            malformedURLException = e113;
                                                            str6 = uuid;
                                                            i2 = i8;
                                                            str45 = str32;
                                                            f.c("HttpClient", "URL E\uff1areq=" + str6 + ", seq=" + num34 + ", e1=" + malformedURLException.toString() + ", recv=" + str45);
                                                            if (this.b) {
                                                            }
                                                            z2 = true;
                                                            this.f19593f = true;
                                                            this.f19593f = z2;
                                                            return i2;
                                                        } catch (JSONException e114) {
                                                            num34 = num41;
                                                            jSONException = e114;
                                                            str3 = uuid;
                                                            i2 = i8;
                                                            num35 = num37;
                                                            str45 = str32;
                                                            f.c("HttpClient", "JSON E:req=" + str3 + ", seq=" + num34 + ", e3=" + jSONException.toString() + ", recv=" + str45);
                                                            if (Integer.valueOf(num35.intValue() + 1).intValue() <= B) {
                                                            }
                                                            this.f19593f = z2;
                                                            return i2;
                                                        }
                                                    } catch (IOException e115) {
                                                        e = e115;
                                                        i5 = obj4;
                                                        num34 = num41;
                                                        i7 = i5;
                                                        str5 = uuid;
                                                        num35 = num37;
                                                        str45 = str32;
                                                        str16 = str17;
                                                        num3 = num4;
                                                        str15 = str16;
                                                        num2 = num3;
                                                        iOException = e;
                                                        str19 = str15;
                                                        num6 = num2;
                                                        f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                                        if (num35.intValue() <= B) {
                                                        }
                                                    }
                                                } else {
                                                    i5 = obj4;
                                                }
                                                if (string2.length() != 0 && i5 > 0) {
                                                    try {
                                                        Thread.sleep(1000L);
                                                    } catch (IOException e116) {
                                                        e = e116;
                                                        num34 = num41;
                                                        i7 = i5;
                                                        str5 = uuid;
                                                        num35 = num37;
                                                        str45 = str32;
                                                        str16 = str17;
                                                        num3 = num4;
                                                        str15 = str16;
                                                        num2 = num3;
                                                        iOException = e;
                                                        str19 = str15;
                                                        num6 = num2;
                                                        f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                                        if (num35.intValue() <= B) {
                                                        }
                                                    } catch (InterruptedException unused5) {
                                                    }
                                                    try {
                                                        if (num37.intValue() > B) {
                                                            ?? sb9 = new StringBuilder();
                                                            sb9.append("http name=");
                                                            sb9.append(this.a);
                                                            sb9.append(", status !0:req1=");
                                                            sb9.append(uuid);
                                                            sb9.append("seq=");
                                                            jSONObject3 = num41;
                                                            try {
                                                                try {
                                                                    sb9.append(jSONObject3);
                                                                    sb9.append(", tryCount=");
                                                                } catch (IOException e117) {
                                                                    e = e117;
                                                                    num27 = jSONObject3;
                                                                    num30 = num37;
                                                                    num32 = num27;
                                                                    i7 = i5;
                                                                    str5 = uuid;
                                                                    num34 = num32;
                                                                    num35 = num30;
                                                                    str45 = str32;
                                                                    str16 = str17;
                                                                    num3 = num4;
                                                                    str15 = str16;
                                                                    num2 = num3;
                                                                    iOException = e;
                                                                    str19 = str15;
                                                                    num6 = num2;
                                                                    f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                                                    if (num35.intValue() <= B) {
                                                                    }
                                                                } catch (JSONException e118) {
                                                                    e = e118;
                                                                    num26 = jSONObject3;
                                                                    num29 = num37;
                                                                    num31 = num26;
                                                                    jSONException = e;
                                                                    str3 = uuid;
                                                                    num34 = num31;
                                                                    i2 = i8;
                                                                    num35 = num29;
                                                                    str45 = str32;
                                                                    f.c("HttpClient", "JSON E:req=" + str3 + ", seq=" + num34 + ", e3=" + jSONException.toString() + ", recv=" + str45);
                                                                    if (Integer.valueOf(num35.intValue() + 1).intValue() <= B) {
                                                                    }
                                                                    this.f19593f = z2;
                                                                    return i2;
                                                                }
                                                                try {
                                                                    sb9.append(num37);
                                                                    sb9.append(",base64 null");
                                                                    f.c("HttpClient", sb9.toString());
                                                                    if (!this.b) {
                                                                        this.f19597j.clear();
                                                                        num12 = num37;
                                                                        str8 = uuid;
                                                                        bVar.a(str2, this.f19598k, 0, 1, -1, 0.0d, "", l.ERR_Base64_Len);
                                                                    } else {
                                                                        num12 = num37;
                                                                        str8 = uuid;
                                                                        bVar.a(str2, this.f19598k, 0, jSONObject3.intValue(), i5, 0.0d, "", l.ERR_Base64_Len);
                                                                    }
                                                                    z5 = true;
                                                                    this.f19593f = true;
                                                                    this.f19593f = z5;
                                                                    i2 = -1;
                                                                    break;
                                                                } catch (IOException e119) {
                                                                    e = e119;
                                                                    num30 = num37;
                                                                    num32 = jSONObject3;
                                                                    i7 = i5;
                                                                    str5 = uuid;
                                                                    num34 = num32;
                                                                    num35 = num30;
                                                                    str45 = str32;
                                                                    str16 = str17;
                                                                    num3 = num4;
                                                                    str15 = str16;
                                                                    num2 = num3;
                                                                    iOException = e;
                                                                    str19 = str15;
                                                                    num6 = num2;
                                                                    f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                                                    if (num35.intValue() <= B) {
                                                                    }
                                                                } catch (JSONException e120) {
                                                                    e = e120;
                                                                    num29 = num37;
                                                                    num31 = jSONObject3;
                                                                    jSONException = e;
                                                                    str3 = uuid;
                                                                    num34 = num31;
                                                                    i2 = i8;
                                                                    num35 = num29;
                                                                    str45 = str32;
                                                                    f.c("HttpClient", "JSON E:req=" + str3 + ", seq=" + num34 + ", e3=" + jSONException.toString() + ", recv=" + str45);
                                                                    if (Integer.valueOf(num35.intValue() + 1).intValue() <= B) {
                                                                    }
                                                                    this.f19593f = z2;
                                                                    return i2;
                                                                }
                                                            } catch (MalformedURLException e121) {
                                                                e = e121;
                                                                num28 = jSONObject3;
                                                                malformedURLException = e;
                                                                str6 = uuid;
                                                                num34 = num28;
                                                                i2 = i8;
                                                                str45 = str32;
                                                                f.c("HttpClient", "URL E\uff1areq=" + str6 + ", seq=" + num34 + ", e1=" + malformedURLException.toString() + ", recv=" + str45);
                                                                if (this.b) {
                                                                }
                                                                z2 = true;
                                                                this.f19593f = true;
                                                                this.f19593f = z2;
                                                                return i2;
                                                            }
                                                        } else {
                                                            try {
                                                                try {
                                                                    num33 = num41;
                                                                    str8 = uuid;
                                                                    num35 = Integer.valueOf(num37.intValue() + 1);
                                                                } catch (IOException e122) {
                                                                    e = e122;
                                                                    i7 = i5;
                                                                    num34 = jSONObject3;
                                                                    num35 = num12;
                                                                } catch (JSONException e123) {
                                                                    jSONException = e123;
                                                                    num34 = jSONObject3;
                                                                    i2 = i8;
                                                                    num35 = num12;
                                                                }
                                                                try {
                                                                    bVar.onTry(str2, l.ERR_Base64_Len);
                                                                    this.f19593f = true;
                                                                    i7 = i5;
                                                                    num34 = num33;
                                                                    str46 = str4;
                                                                    str45 = str32;
                                                                    uuid = str8;
                                                                } catch (IOException e124) {
                                                                    e = e124;
                                                                    i7 = i5;
                                                                    num34 = num33;
                                                                    str45 = str32;
                                                                    str5 = str8;
                                                                    str16 = str17;
                                                                    num3 = num4;
                                                                    str15 = str16;
                                                                    num2 = num3;
                                                                    iOException = e;
                                                                    str19 = str15;
                                                                    num6 = num2;
                                                                    f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                                                    if (num35.intValue() <= B) {
                                                                    }
                                                                } catch (JSONException e125) {
                                                                    jSONException = e125;
                                                                    num34 = num33;
                                                                    i2 = i8;
                                                                    str45 = str32;
                                                                    str3 = str8;
                                                                    f.c("HttpClient", "JSON E:req=" + str3 + ", seq=" + num34 + ", e3=" + jSONException.toString() + ", recv=" + str45);
                                                                    if (Integer.valueOf(num35.intValue() + 1).intValue() <= B) {
                                                                    }
                                                                    this.f19593f = z2;
                                                                    return i2;
                                                                }
                                                            } catch (MalformedURLException e126) {
                                                                malformedURLException = e126;
                                                                num34 = jSONObject3;
                                                                i2 = i8;
                                                                str45 = str32;
                                                                str6 = str8;
                                                                f.c("HttpClient", "URL E\uff1areq=" + str6 + ", seq=" + num34 + ", e1=" + malformedURLException.toString() + ", recv=" + str45);
                                                                if (this.b) {
                                                                }
                                                                z2 = true;
                                                                this.f19593f = true;
                                                                this.f19593f = z2;
                                                                return i2;
                                                            }
                                                        }
                                                    } catch (MalformedURLException e127) {
                                                        e = e127;
                                                        num28 = num41;
                                                    } catch (IOException e128) {
                                                        e = e128;
                                                        num27 = num41;
                                                    } catch (JSONException e129) {
                                                        e = e129;
                                                        num26 = num41;
                                                    }
                                                } else {
                                                    str8 = uuid;
                                                    num20 = num37;
                                                    Integer num43 = num41;
                                                    decode = Base64.decode(string2, 0);
                                                    if (decode.length > 0) {
                                                        try {
                                                            StringBuilder sb10 = new StringBuilder();
                                                            sb10.append("http name=");
                                                            sb10.append(this.a);
                                                            sb10.append(", Base64\uff1areq=");
                                                            try {
                                                                sb10.append(str8);
                                                                sb10.append("seq=");
                                                                sb10.append(num43);
                                                                sb10.append(", recv=");
                                                                str34 = str32;
                                                                try {
                                                                    sb10.append(str34);
                                                                    f.c("HttpClient", sb10.toString());
                                                                } catch (MalformedURLException e130) {
                                                                    e = e130;
                                                                    str7 = str34;
                                                                    num41 = num43;
                                                                } catch (IOException e131) {
                                                                    e = e131;
                                                                    str7 = str34;
                                                                    num41 = num43;
                                                                } catch (JSONException e132) {
                                                                    e = e132;
                                                                    str7 = str34;
                                                                    num41 = num43;
                                                                }
                                                            } catch (MalformedURLException e133) {
                                                                e = e133;
                                                                num41 = num43;
                                                                str7 = str32;
                                                            } catch (IOException e134) {
                                                                e = e134;
                                                                num41 = num43;
                                                                str7 = str32;
                                                            } catch (JSONException e135) {
                                                                e = e135;
                                                                num41 = num43;
                                                                str7 = str32;
                                                            }
                                                            try {
                                                                if (!this.b) {
                                                                    this.f19597j.clear();
                                                                    str7 = str34;
                                                                    num41 = num43;
                                                                    bVar.a(str2, this.f19598k, 0, 1, -1, 0.0d, "", l.ERR_Base64_Decoe);
                                                                } else {
                                                                    str7 = str34;
                                                                    num41 = num43;
                                                                    bVar.a(str2, this.f19598k, 0, num41.intValue(), i5, 0.0d, "", l.ERR_Base64_Decoe);
                                                                }
                                                                this.f19593f = true;
                                                                this.f19593f = true;
                                                                return -1;
                                                            } catch (MalformedURLException e136) {
                                                                e = e136;
                                                                num34 = num41;
                                                                malformedURLException = e;
                                                                str6 = str8;
                                                                i2 = i8;
                                                                str45 = str7;
                                                                f.c("HttpClient", "URL E\uff1areq=" + str6 + ", seq=" + num34 + ", e1=" + malformedURLException.toString() + ", recv=" + str45);
                                                                if (this.b) {
                                                                }
                                                                z2 = true;
                                                                this.f19593f = true;
                                                                this.f19593f = z2;
                                                                return i2;
                                                            } catch (IOException e137) {
                                                                e = e137;
                                                                num34 = num41;
                                                                i7 = i5;
                                                                str5 = str8;
                                                                num35 = num20;
                                                                str45 = str7;
                                                                str16 = str17;
                                                                num3 = num4;
                                                                str15 = str16;
                                                                num2 = num3;
                                                                iOException = e;
                                                                str19 = str15;
                                                                num6 = num2;
                                                                f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                                                if (num35.intValue() <= B) {
                                                                }
                                                            } catch (JSONException e138) {
                                                                e = e138;
                                                                num34 = num41;
                                                                jSONException = e;
                                                                str3 = str8;
                                                                i2 = i8;
                                                                num35 = num20;
                                                                str45 = str7;
                                                                f.c("HttpClient", "JSON E:req=" + str3 + ", seq=" + num34 + ", e3=" + jSONException.toString() + ", recv=" + str45);
                                                                if (Integer.valueOf(num35.intValue() + 1).intValue() <= B) {
                                                                }
                                                                this.f19593f = z2;
                                                                return i2;
                                                            }
                                                        } catch (MalformedURLException e139) {
                                                            str7 = str32;
                                                            num34 = num43;
                                                            malformedURLException = e139;
                                                            i2 = i8;
                                                            str6 = str8;
                                                            str45 = str7;
                                                            f.c("HttpClient", "URL E\uff1areq=" + str6 + ", seq=" + num34 + ", e1=" + malformedURLException.toString() + ", recv=" + str45);
                                                            if (this.b) {
                                                            }
                                                            z2 = true;
                                                            this.f19593f = true;
                                                            this.f19593f = z2;
                                                            return i2;
                                                        } catch (IOException e140) {
                                                            e = e140;
                                                            str7 = str32;
                                                            num34 = num43;
                                                            i7 = i5;
                                                            num35 = num20;
                                                            str5 = str8;
                                                            str45 = str7;
                                                            str16 = str17;
                                                            num3 = num4;
                                                            str15 = str16;
                                                            num2 = num3;
                                                            iOException = e;
                                                            str19 = str15;
                                                            num6 = num2;
                                                            f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                                            if (num35.intValue() <= B) {
                                                            }
                                                        } catch (JSONException e141) {
                                                            str7 = str32;
                                                            num34 = num43;
                                                            jSONException = e141;
                                                            i2 = i8;
                                                            num35 = num20;
                                                            str3 = str8;
                                                            str45 = str7;
                                                            f.c("HttpClient", "JSON E:req=" + str3 + ", seq=" + num34 + ", e3=" + jSONException.toString() + ", recv=" + str45);
                                                            if (Integer.valueOf(num35.intValue() + 1).intValue() <= B) {
                                                            }
                                                            this.f19593f = z2;
                                                            return i2;
                                                        }
                                                    } else {
                                                        str7 = str32;
                                                        uuid = str8;
                                                        try {
                                                            a = this.f19591c.a(decode, num43.intValue() == 1, num43.intValue() < 0);
                                                        } catch (MalformedURLException e142) {
                                                            e = e142;
                                                            num11 = num43;
                                                            str25 = str7;
                                                            malformedURLException = e;
                                                            str6 = uuid;
                                                            i2 = i8;
                                                            str45 = str25;
                                                            num34 = num11;
                                                            f.c("HttpClient", "URL E\uff1areq=" + str6 + ", seq=" + num34 + ", e1=" + malformedURLException.toString() + ", recv=" + str45);
                                                            if (this.b) {
                                                            }
                                                            z2 = true;
                                                            this.f19593f = true;
                                                            this.f19593f = z2;
                                                            return i2;
                                                        } catch (IOException e143) {
                                                            e = e143;
                                                            Integer num44 = num43;
                                                            String str50 = str7;
                                                            i7 = i5;
                                                            str5 = uuid;
                                                            num35 = num20;
                                                            str45 = str50;
                                                            num34 = num44;
                                                            str15 = str50;
                                                            num2 = num44;
                                                            iOException = e;
                                                            str19 = str15;
                                                            num6 = num2;
                                                            f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                                            if (num35.intValue() <= B) {
                                                            }
                                                        } catch (JSONException e144) {
                                                            e = e144;
                                                            num9 = num43;
                                                            str3 = uuid;
                                                            str24 = str7;
                                                        }
                                                        if (a == null) {
                                                            try {
                                                                num34 = Integer.valueOf(i5 + 1);
                                                                this.f19593f = true;
                                                                i7 = i5;
                                                                num35 = num20;
                                                                str46 = str4;
                                                                str45 = str7;
                                                            } catch (MalformedURLException e145) {
                                                                num34 = num43;
                                                                malformedURLException = e145;
                                                                str6 = uuid;
                                                                i2 = i8;
                                                                str45 = str7;
                                                                f.c("HttpClient", "URL E\uff1areq=" + str6 + ", seq=" + num34 + ", e1=" + malformedURLException.toString() + ", recv=" + str45);
                                                                if (this.b) {
                                                                }
                                                                z2 = true;
                                                                this.f19593f = true;
                                                                this.f19593f = z2;
                                                                return i2;
                                                            } catch (IOException e146) {
                                                                e = e146;
                                                                num34 = num43;
                                                                i7 = i5;
                                                                str5 = uuid;
                                                                num35 = num20;
                                                                str45 = str7;
                                                                str16 = str17;
                                                                num3 = num4;
                                                                str15 = str16;
                                                                num2 = num3;
                                                                iOException = e;
                                                                str19 = str15;
                                                                num6 = num2;
                                                                f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                                                if (num35.intValue() <= B) {
                                                                }
                                                            } catch (JSONException e147) {
                                                                num34 = num43;
                                                                jSONException = e147;
                                                                str3 = uuid;
                                                                i2 = i8;
                                                                num35 = num20;
                                                                str45 = str7;
                                                                f.c("HttpClient", "JSON E:req=" + str3 + ", seq=" + num34 + ", e3=" + jSONException.toString() + ", recv=" + str45);
                                                                if (Integer.valueOf(num35.intValue() + 1).intValue() <= B) {
                                                                }
                                                                this.f19593f = z2;
                                                                return i2;
                                                            }
                                                        } else if (a.length <= 0) {
                                                            try {
                                                                sb2 = new StringBuilder();
                                                                sb2.append("http name=");
                                                                sb2.append(this.a);
                                                                sb2.append(", audioDecoder err\uff1areq=");
                                                                sb2.append(uuid);
                                                                sb2.append("seq=");
                                                                num24 = num43;
                                                            } catch (MalformedURLException e148) {
                                                                e = e148;
                                                                num24 = num43;
                                                            } catch (IOException e149) {
                                                                e = e149;
                                                                num24 = num43;
                                                            } catch (JSONException e150) {
                                                                e = e150;
                                                                num24 = num43;
                                                            }
                                                            try {
                                                                sb2.append(num24);
                                                                sb2.append(", recv=");
                                                                try {
                                                                    sb2.append(str7);
                                                                    f.c("HttpClient", sb2.toString());
                                                                } catch (MalformedURLException e151) {
                                                                    e = e151;
                                                                    str36 = str7;
                                                                    malformedURLException = e;
                                                                    str6 = uuid;
                                                                    num34 = num24;
                                                                    i2 = i8;
                                                                    str45 = str36;
                                                                    f.c("HttpClient", "URL E\uff1areq=" + str6 + ", seq=" + num34 + ", e1=" + malformedURLException.toString() + ", recv=" + str45);
                                                                    if (this.b) {
                                                                    }
                                                                    z2 = true;
                                                                    this.f19593f = true;
                                                                    this.f19593f = z2;
                                                                    return i2;
                                                                } catch (IOException e152) {
                                                                    e = e152;
                                                                    str35 = str7;
                                                                    i7 = i5;
                                                                    str5 = uuid;
                                                                    num34 = num24;
                                                                    num35 = num20;
                                                                    str45 = str35;
                                                                    str16 = str35;
                                                                    num3 = num4;
                                                                    str15 = str16;
                                                                    num2 = num3;
                                                                    iOException = e;
                                                                    str19 = str15;
                                                                    num6 = num2;
                                                                    f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                                                    if (num35.intValue() <= B) {
                                                                    }
                                                                } catch (JSONException e153) {
                                                                    e = e153;
                                                                    str17 = str7;
                                                                    str3 = uuid;
                                                                }
                                                            } catch (MalformedURLException e154) {
                                                                e = e154;
                                                                str36 = str7;
                                                                malformedURLException = e;
                                                                str6 = uuid;
                                                                num34 = num24;
                                                                i2 = i8;
                                                                str45 = str36;
                                                                f.c("HttpClient", "URL E\uff1areq=" + str6 + ", seq=" + num34 + ", e1=" + malformedURLException.toString() + ", recv=" + str45);
                                                                if (this.b) {
                                                                }
                                                                z2 = true;
                                                                this.f19593f = true;
                                                                this.f19593f = z2;
                                                                return i2;
                                                            } catch (IOException e155) {
                                                                e = e155;
                                                                str35 = str7;
                                                                i7 = i5;
                                                                str5 = uuid;
                                                                num34 = num24;
                                                                num35 = num20;
                                                                str45 = str35;
                                                                str16 = str35;
                                                                num3 = num4;
                                                                str15 = str16;
                                                                num2 = num3;
                                                                iOException = e;
                                                                str19 = str15;
                                                                num6 = num2;
                                                                f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                                                if (num35.intValue() <= B) {
                                                                }
                                                            } catch (JSONException e156) {
                                                                e = e156;
                                                                str3 = uuid;
                                                                str17 = str7;
                                                                jSONException = e;
                                                                num34 = num24;
                                                                str38 = str17;
                                                                i2 = i8;
                                                                num35 = num20;
                                                                str45 = str38;
                                                                f.c("HttpClient", "JSON E:req=" + str3 + ", seq=" + num34 + ", e3=" + jSONException.toString() + ", recv=" + str45);
                                                                if (Integer.valueOf(num35.intValue() + 1).intValue() <= B) {
                                                                }
                                                                this.f19593f = z2;
                                                                return i2;
                                                            }
                                                            try {
                                                                if (!this.b) {
                                                                    this.f19597j.clear();
                                                                    str17 = str7;
                                                                    str3 = uuid;
                                                                    bVar.a(str2, this.f19598k, 0, 1, -1, 0.0d, "", l.ERR_Audo_Decoe);
                                                                } else {
                                                                    str17 = str7;
                                                                    str3 = uuid;
                                                                    bVar.a(str2, this.f19598k, 0, num24.intValue(), i5, 0.0d, "", l.ERR_Audo_Decoe);
                                                                }
                                                                this.f19593f = true;
                                                                this.f19593f = true;
                                                                return -1;
                                                            } catch (MalformedURLException e157) {
                                                                malformedURLException = e157;
                                                                num34 = num24;
                                                                str39 = str17;
                                                                i2 = i8;
                                                                str45 = str39;
                                                                str6 = str3;
                                                                f.c("HttpClient", "URL E\uff1areq=" + str6 + ", seq=" + num34 + ", e1=" + malformedURLException.toString() + ", recv=" + str45);
                                                                if (this.b) {
                                                                }
                                                                z2 = true;
                                                                this.f19593f = true;
                                                                this.f19593f = z2;
                                                                return i2;
                                                            } catch (IOException e158) {
                                                                e = e158;
                                                                i7 = i5;
                                                                num34 = num24;
                                                                num35 = num20;
                                                                str45 = str17;
                                                                str37 = str17;
                                                                num25 = num4;
                                                                str5 = str3;
                                                                str16 = str37;
                                                                num3 = num25;
                                                                str15 = str16;
                                                                num2 = num3;
                                                                iOException = e;
                                                                str19 = str15;
                                                                num6 = num2;
                                                                f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                                                if (num35.intValue() <= B) {
                                                                }
                                                            } catch (JSONException e159) {
                                                                e = e159;
                                                                jSONException = e;
                                                                num34 = num24;
                                                                str38 = str17;
                                                                i2 = i8;
                                                                num35 = num20;
                                                                str45 = str38;
                                                                f.c("HttpClient", "JSON E:req=" + str3 + ", seq=" + num34 + ", e3=" + jSONException.toString() + ", recv=" + str45);
                                                                if (Integer.valueOf(num35.intValue() + 1).intValue() <= B) {
                                                                }
                                                                this.f19593f = z2;
                                                                return i2;
                                                            }
                                                        } else {
                                                            num9 = num43;
                                                            str3 = uuid;
                                                            str24 = str7;
                                                            try {
                                                                try {
                                                                    if (this.b) {
                                                                        try {
                                                                            Object obj6 = this.f19596i;
                                                                            synchronized (obj6) {
                                                                                try {
                                                                                    if (this.f19592e) {
                                                                                        obj = obj6;
                                                                                        this.f19591c.stop();
                                                                                    } else if (has) {
                                                                                        obj = obj6;
                                                                                        try {
                                                                                            bVar.a(str2, a, decode.length, num9.intValue(), i5, d, jSONObject3.getString(VerifyTracker.KEY_TIMESTAMP), l.OK_NO);
                                                                                        } catch (Throwable th12) {
                                                                                            th = th12;
                                                                                            Throwable th13 = th;
                                                                                            throw th13;
                                                                                            break;
                                                                                        }
                                                                                    } else {
                                                                                        obj = obj6;
                                                                                        bVar.a(str2, a, decode.length, num9.intValue(), i5, d, "", l.OK_NO);
                                                                                    }
                                                                                } catch (Throwable th14) {
                                                                                    th = th14;
                                                                                    obj = obj6;
                                                                                    Throwable th132 = th;
                                                                                    throw th132;
                                                                                    break;
                                                                                    break;
                                                                                }
                                                                            }
                                                                        } catch (IOException e160) {
                                                                            e = e160;
                                                                            i7 = i5;
                                                                            num35 = num20;
                                                                            str45 = str24;
                                                                            num34 = num9;
                                                                            str37 = str24;
                                                                            num25 = num9;
                                                                            str5 = str3;
                                                                            str16 = str37;
                                                                            num3 = num25;
                                                                            str15 = str16;
                                                                            num2 = num3;
                                                                            iOException = e;
                                                                            str19 = str15;
                                                                            num6 = num2;
                                                                            f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                                                            if (num35.intValue() <= B) {
                                                                            }
                                                                        }
                                                                    } else {
                                                                        g.g.a.b bVar3 = new g.g.a.b();
                                                                        bVar3.l(str2);
                                                                        bVar3.k(num9.intValue());
                                                                        bVar3.h(i5);
                                                                        bVar3.i(a);
                                                                        bVar3.j(d);
                                                                        bVar3.g(l.OK_NO);
                                                                        this.f19597j.add(bVar3);
                                                                        if (i5 < 0) {
                                                                            while (true) {
                                                                                g.g.a.b poll2 = this.f19597j.poll();
                                                                                if (poll2 == null) {
                                                                                    break;
                                                                                }
                                                                                bVar.a(poll2.f(), poll2.c(), poll2.c().length, poll2.e(), poll2.b(), poll2.d(), "", poll2.a());
                                                                            }
                                                                            i8 = -1;
                                                                        }
                                                                    }
                                                                    num34 = Integer.valueOf(i5 + 1);
                                                                    if (i5 < 0) {
                                                                        try {
                                                                            try {
                                                                                this.f19593f = true;
                                                                            } catch (MalformedURLException e161) {
                                                                                malformedURLException = e161;
                                                                                str39 = str24;
                                                                                i2 = i8;
                                                                                str45 = str39;
                                                                                str6 = str3;
                                                                                f.c("HttpClient", "URL E\uff1areq=" + str6 + ", seq=" + num34 + ", e1=" + malformedURLException.toString() + ", recv=" + str45);
                                                                                if (this.b) {
                                                                                }
                                                                                z2 = true;
                                                                                this.f19593f = true;
                                                                                this.f19593f = z2;
                                                                                return i2;
                                                                            } catch (JSONException e162) {
                                                                                jSONException = e162;
                                                                                str38 = str24;
                                                                                i2 = i8;
                                                                                num35 = num20;
                                                                                str45 = str38;
                                                                                f.c("HttpClient", "JSON E:req=" + str3 + ", seq=" + num34 + ", e3=" + jSONException.toString() + ", recv=" + str45);
                                                                                if (Integer.valueOf(num35.intValue() + 1).intValue() <= B) {
                                                                                }
                                                                                this.f19593f = z2;
                                                                                return i2;
                                                                            }
                                                                        } catch (IOException e163) {
                                                                            e = e163;
                                                                            i7 = i5;
                                                                            num35 = num20;
                                                                            str45 = str24;
                                                                            str5 = str3;
                                                                            str15 = str24;
                                                                            num2 = num9;
                                                                            iOException = e;
                                                                            str19 = str15;
                                                                            num6 = num2;
                                                                            f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                                                            if (num35.intValue() <= B) {
                                                                            }
                                                                        }
                                                                        try {
                                                                            this.f19592e = false;
                                                                        } catch (IOException e164) {
                                                                            e = e164;
                                                                            i7 = i5;
                                                                            num35 = num20;
                                                                            str45 = str24;
                                                                            str5 = str3;
                                                                            str15 = str24;
                                                                            num2 = num9;
                                                                            iOException = e;
                                                                            str19 = str15;
                                                                            num6 = num2;
                                                                            f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                                                            if (num35.intValue() <= B) {
                                                                            }
                                                                        }
                                                                    }
                                                                } catch (MalformedURLException e165) {
                                                                    malformedURLException = e165;
                                                                    i2 = i8;
                                                                    str45 = str24;
                                                                    num34 = num9;
                                                                    str6 = str3;
                                                                    f.c("HttpClient", "URL E\uff1areq=" + str6 + ", seq=" + num34 + ", e1=" + malformedURLException.toString() + ", recv=" + str45);
                                                                    if (this.b) {
                                                                    }
                                                                    z2 = true;
                                                                    this.f19593f = true;
                                                                    this.f19593f = z2;
                                                                    return i2;
                                                                } catch (JSONException e166) {
                                                                    e = e166;
                                                                    jSONException = e;
                                                                    i2 = i8;
                                                                    num35 = num20;
                                                                    str45 = str24;
                                                                    num34 = num9;
                                                                    f.c("HttpClient", "JSON E:req=" + str3 + ", seq=" + num34 + ", e3=" + jSONException.toString() + ", recv=" + str45);
                                                                    if (Integer.valueOf(num35.intValue() + 1).intValue() <= B) {
                                                                    }
                                                                    this.f19593f = z2;
                                                                    return i2;
                                                                }
                                                            } catch (IOException e167) {
                                                                e = e167;
                                                                i7 = i5;
                                                                num35 = num20;
                                                                str45 = str24;
                                                                num34 = num9;
                                                            }
                                                            if (this.f19592e) {
                                                                bVar.onEnd(str2);
                                                                this.f19593f = true;
                                                                try {
                                                                    this.f19591c.stop();
                                                                    f.f("HttpClient", "Http1 to Stop, status" + this.f19593f);
                                                                    this.f19593f = true;
                                                                    i2 = 0;
                                                                    break;
                                                                } catch (MalformedURLException e168) {
                                                                    malformedURLException = e168;
                                                                    str45 = str24;
                                                                    str6 = str3;
                                                                    i2 = 0;
                                                                    f.c("HttpClient", "URL E\uff1areq=" + str6 + ", seq=" + num34 + ", e1=" + malformedURLException.toString() + ", recv=" + str45);
                                                                    if (this.b) {
                                                                    }
                                                                    z2 = true;
                                                                    this.f19593f = true;
                                                                    this.f19593f = z2;
                                                                    return i2;
                                                                } catch (IOException e169) {
                                                                    e = e169;
                                                                    i7 = i5;
                                                                    num35 = num20;
                                                                    str45 = str24;
                                                                    str5 = str3;
                                                                    i8 = 0;
                                                                    str15 = str24;
                                                                    num2 = num9;
                                                                    iOException = e;
                                                                    str19 = str15;
                                                                    num6 = num2;
                                                                    f.c("HttpClient", "Timeout:req=" + str5 + ", seq=" + num34 + ", e2=" + iOException.toString() + ", tryCount=" + num35 + ", tryCountConf=" + B);
                                                                    if (num35.intValue() <= B) {
                                                                    }
                                                                } catch (JSONException e170) {
                                                                    jSONException = e170;
                                                                    num35 = num20;
                                                                    str45 = str24;
                                                                    i2 = 0;
                                                                    f.c("HttpClient", "JSON E:req=" + str3 + ", seq=" + num34 + ", e3=" + jSONException.toString() + ", recv=" + str45);
                                                                    if (Integer.valueOf(num35.intValue() + 1).intValue() <= B) {
                                                                    }
                                                                    this.f19593f = z2;
                                                                    return i2;
                                                                }
                                                            } else {
                                                                this.f19593f = true;
                                                                i7 = i5;
                                                                num35 = num20;
                                                                str45 = str24;
                                                                str5 = str3;
                                                                str18 = str24;
                                                                num5 = num9;
                                                                str44 = str;
                                                                uuid = str5;
                                                                str46 = str4;
                                                                str17 = str18;
                                                                num4 = num5;
                                                                z6 = false;
                                                            }
                                                        }
                                                    }
                                                }
                                                z6 = false;
                                                str44 = str;
                                            }
                                        }
                                        if (string2.length() == 0) {
                                        }
                                        if (string2.length() != 0) {
                                        }
                                        str8 = uuid;
                                        num20 = num37;
                                        Integer num432 = num41;
                                        decode = Base64.decode(string2, 0);
                                        if (decode.length > 0) {
                                        }
                                    } catch (MalformedURLException e171) {
                                        e = e171;
                                        num11 = num41;
                                        str25 = str32;
                                    } catch (JSONException e172) {
                                        e = e172;
                                        num9 = num41;
                                        str3 = uuid;
                                        num20 = num37;
                                        str24 = str32;
                                    }
                                    double d2 = jSONObject3.getDouble(NotificationCompat.CATEGORY_PROGRESS);
                                    string2 = jSONObject3.getString("audio");
                                    boolean has2 = jSONObject3.has(VerifyTracker.KEY_TIMESTAMP);
                                    if (num41.intValue() == 1) {
                                        if (string2.length() == 0) {
                                        }
                                    }
                                    Object obj42 = i9;
                                    uuid = str33;
                                } catch (MalformedURLException e173) {
                                    malformedURLException = e173;
                                    i2 = i8;
                                    str45 = str32;
                                    num34 = num41;
                                } catch (JSONException e174) {
                                    e = e174;
                                    num9 = num41;
                                    num20 = num37;
                                    str24 = str32;
                                    str3 = str33;
                                }
                                jSONObject2 = jSONObject4;
                                i3 = jSONObject2.getInt("status");
                                if (i3 != 30201) {
                                }
                                str9 = str12;
                                str10 = str11;
                                StringBuilder sb72 = new StringBuilder();
                                sb72.append("http name=");
                                sb72.append(this.a);
                                sb72.append(", status !0:req0=");
                                sb72.append(str9);
                                sb72.append("seq=");
                                sb72.append(num41);
                                sb72.append(", recv=");
                                sb72.append(str10);
                                f.c("HttpClient", sb72.toString());
                                if (this.b) {
                                }
                                int i92 = jSONObject3.getInt("index");
                            }
                        }
                    } catch (Throwable th15) {
                        Integer num45 = num8;
                        num7 = num35;
                        str4 = str46;
                        str3 = uuid;
                        th = th15;
                        num34 = num45;
                        num4 = num45;
                        throw th;
                        break;
                        break;
                    }
                }
            }
            i2 = i8;
            return i2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:174:0x0578 A[Catch: all -> 0x0638, TryCatch #18 {, blocks: (B:5:0x0008, B:8:0x0086, B:10:0x008a, B:12:0x0097, B:14:0x015b, B:15:0x016e, B:18:0x0178, B:19:0x01a2, B:20:0x01a5, B:30:0x01c2, B:31:0x01ca, B:33:0x01e6, B:53:0x027c, B:55:0x028b, B:57:0x0299, B:59:0x02a4, B:60:0x02bd, B:61:0x02d9, B:68:0x02f4, B:70:0x02fc, B:72:0x0316, B:73:0x032f, B:75:0x034b, B:78:0x036b, B:79:0x036f, B:94:0x03b8, B:98:0x03bc, B:100:0x03c3, B:102:0x03dd, B:104:0x03f5, B:178:0x05b6, B:180:0x05f9, B:182:0x05fd, B:183:0x0615, B:105:0x03fa, B:106:0x0418, B:110:0x0425, B:114:0x042e, B:116:0x0434, B:117:0x044b, B:119:0x044e, B:121:0x0468, B:122:0x0480, B:124:0x04a1, B:125:0x04a3, B:136:0x04e5, B:137:0x04e9, B:139:0x04ed, B:172:0x0542, B:174:0x0578, B:175:0x0591, B:150:0x0508, B:65:0x02e9, B:39:0x01f3, B:41:0x01fb, B:43:0x0217, B:45:0x0237, B:46:0x023c), top: B:217:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0591 A[Catch: all -> 0x0638, TryCatch #18 {, blocks: (B:5:0x0008, B:8:0x0086, B:10:0x008a, B:12:0x0097, B:14:0x015b, B:15:0x016e, B:18:0x0178, B:19:0x01a2, B:20:0x01a5, B:30:0x01c2, B:31:0x01ca, B:33:0x01e6, B:53:0x027c, B:55:0x028b, B:57:0x0299, B:59:0x02a4, B:60:0x02bd, B:61:0x02d9, B:68:0x02f4, B:70:0x02fc, B:72:0x0316, B:73:0x032f, B:75:0x034b, B:78:0x036b, B:79:0x036f, B:94:0x03b8, B:98:0x03bc, B:100:0x03c3, B:102:0x03dd, B:104:0x03f5, B:178:0x05b6, B:180:0x05f9, B:182:0x05fd, B:183:0x0615, B:105:0x03fa, B:106:0x0418, B:110:0x0425, B:114:0x042e, B:116:0x0434, B:117:0x044b, B:119:0x044e, B:121:0x0468, B:122:0x0480, B:124:0x04a1, B:125:0x04a3, B:136:0x04e5, B:137:0x04e9, B:139:0x04ed, B:172:0x0542, B:174:0x0578, B:175:0x0591, B:150:0x0508, B:65:0x02e9, B:39:0x01f3, B:41:0x01fb, B:43:0x0217, B:45:0x0237, B:46:0x023c), top: B:217:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0630  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x05f9 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:206:? -> B:151:0x0509). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized int b(String str, String str2, Integer num, b bVar) {
        String str3;
        int i2;
        Object obj;
        IOException iOException;
        JSONException jSONException;
        Response execute;
        Integer valueOf;
        String str4;
        Object obj2;
        Object obj3;
        Integer num2 = 0;
        String str5 = "";
        this.f19592e = false;
        this.f19593f = false;
        bVar.onStart(str2);
        Long valueOf2 = Long.valueOf(System.currentTimeMillis());
        String str6 = "https://116.196.91.238/jdai/tts?appkey=" + p + "&timestamp=" + valueOf2.toString() + "&sign=" + c.b(q + valueOf2.toString(), "UTF-8");
        StringBuilder sb = new StringBuilder();
        sb.append("post2 reqID=");
        sb.append(str2);
        sb.append(", SN=");
        Integer num3 = num;
        sb.append(num3);
        f.c("HttpClient", sb.toString());
        int i3 = 1;
        int i4 = 1;
        while (true) {
            if (i4 <= 0) {
                break;
            }
            Integer valueOf3 = this.f19592e ? Integer.valueOf(num3.intValue() * (-1)) : num3;
            valueOf3.intValue();
            RequestBody create = RequestBody.create(MediaType.parse("text/plain"), str);
            Request build = new Request.Builder().url(str6).addHeader("Charset", "UTF-8").addHeader("host", "aiapi.jd.com").addHeader("accept", HTTP.CONTENT_TYPE_JSON).addHeader(HttpHeaders.CONTENT_TYPE, "text/plain").addHeader("Service-Type", "synthesis").addHeader("Application-Id", v).addHeader("Request-Id", str2).addHeader("Protocol", r.toString()).addHeader("Net-State", s.toString()).addHeader("Applicator", t.toString()).addHeader("Property", u).addHeader("Sequence-Id", valueOf3.toString()).post(create).build();
            f.c("HttpClient", "post2 reqID=" + str2 + ", SN=" + valueOf3 + ", requestBody=" + create.contentType() + ", body=" + create.toString());
            JSONObject jSONObject = null;
            try {
                try {
                    execute = this.f19594g.newCall(build).execute();
                    valueOf = Integer.valueOf(num2.intValue() + i3);
                    try {
                    } catch (IOException e2) {
                        str3 = str6;
                        i2 = 1;
                        obj = null;
                        iOException = e2;
                    }
                } catch (JSONException e3) {
                    e = e3;
                }
            } catch (IOException e4) {
                str3 = str6;
                i2 = 1;
                obj = null;
                iOException = e4;
            }
            if (execute.code() != 200) {
                try {
                    f.c("HttpClient", "code=" + execute.code() + ", headers=" + execute.headers().toString());
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException unused) {
                    }
                } catch (IOException e5) {
                    iOException = e5;
                    str3 = str6;
                    num2 = valueOf;
                    i2 = 1;
                    obj = null;
                    f.c("HttpClient", "req=" + str2 + ", seq=" + valueOf3 + ", e2=" + iOException.toString() + ", recv=" + str5);
                    num2 = Integer.valueOf(num2.intValue() + i2);
                    if (num2.intValue() <= B) {
                    }
                } catch (JSONException e6) {
                    e = e6;
                    jSONException = e;
                    f.c("HttpClient", "req=" + str2 + "seq=" + valueOf3 + "e2=" + jSONException.toString() + ", recv=" + str5);
                    if (this.b) {
                    }
                    return -1;
                }
                if (valueOf.intValue() > B) {
                    break;
                }
                num3 = valueOf3;
                num2 = valueOf;
            } else {
                String string = execute.body().string();
                try {
                    try {
                        f.c("HttpClient", "recv=" + string);
                    } catch (IOException e7) {
                        e = e7;
                        str4 = string;
                        str3 = str6;
                        i2 = 1;
                        obj = null;
                    }
                    if (string.length() <= 0) {
                        try {
                            Thread.sleep(1000L);
                        } catch (IOException e8) {
                            iOException = e8;
                            str5 = string;
                            str3 = str6;
                            num2 = valueOf;
                            i2 = 1;
                            obj = null;
                            f.c("HttpClient", "req=" + str2 + ", seq=" + valueOf3 + ", e2=" + iOException.toString() + ", recv=" + str5);
                            num2 = Integer.valueOf(num2.intValue() + i2);
                            if (num2.intValue() <= B) {
                            }
                        } catch (InterruptedException unused2) {
                        } catch (JSONException e9) {
                            jSONException = e9;
                            str5 = string;
                            f.c("HttpClient", "req=" + str2 + "seq=" + valueOf3 + "e2=" + jSONException.toString() + ", recv=" + str5);
                            if (this.b) {
                            }
                            return -1;
                        }
                        try {
                        } catch (IOException e10) {
                            str3 = str6;
                            i2 = 1;
                            iOException = e10;
                            str5 = string;
                            num2 = valueOf;
                            obj = null;
                            f.c("HttpClient", "req=" + str2 + ", seq=" + valueOf3 + ", e2=" + iOException.toString() + ", recv=" + str5);
                            num2 = Integer.valueOf(num2.intValue() + i2);
                            if (num2.intValue() <= B) {
                            }
                        }
                        if (valueOf.intValue() > B) {
                            f.b("HttpClient", "tryCount > " + B);
                            if (!this.b) {
                                this.f19597j.clear();
                                bVar.a(str2, this.f19598k, 0, 1, -1, 0.0d, "", l.ERR_SRV_Error);
                                break;
                            }
                            Integer num4 = valueOf3;
                            bVar.a(str2, this.f19598k, 0, num4.intValue(), num4.intValue() * (-1), 0.0d, "", l.ERR_SRV_Error);
                            break;
                        }
                        str5 = string;
                        num2 = valueOf;
                        num3 = valueOf3;
                    } else {
                        str4 = string;
                        Integer num5 = valueOf3;
                        str3 = str6;
                        i2 = 1;
                        try {
                            try {
                                JSONObject jSONObject2 = new JSONObject(str4);
                                if (w.equals("0")) {
                                    try {
                                        if (!jSONObject2.getString("code").equals("10000")) {
                                            f.b("HttpClient", "code != 10000");
                                            if (!this.b) {
                                                this.f19597j.clear();
                                                bVar.a(str2, this.f19598k, 0, 1, -1, 0.0d, "", l.ERR_SRV_Error);
                                            } else {
                                                bVar.a(str2, this.f19598k, 0, num5.intValue(), num5.intValue() * (-1), 0.0d, "", l.ERR_SRV_Error);
                                            }
                                        } else {
                                            jSONObject = jSONObject2.getJSONObject("result");
                                        }
                                    } catch (IOException e11) {
                                        iOException = e11;
                                        str5 = str4;
                                        num2 = valueOf;
                                        valueOf3 = num5;
                                        obj = null;
                                        f.c("HttpClient", "req=" + str2 + ", seq=" + valueOf3 + ", e2=" + iOException.toString() + ", recv=" + str5);
                                        num2 = Integer.valueOf(num2.intValue() + i2);
                                        if (num2.intValue() <= B) {
                                        }
                                    }
                                } else if (w.equals("1")) {
                                    jSONObject = jSONObject2;
                                }
                            } catch (JSONException e12) {
                                jSONException = e12;
                                str5 = str4;
                                valueOf3 = num5;
                                f.c("HttpClient", "req=" + str2 + "seq=" + valueOf3 + "e2=" + jSONException.toString() + ", recv=" + str5);
                                if (this.b) {
                                    this.f19597j.clear();
                                    bVar.a(str2, this.f19598k, 0, 1, -1, 0.0d, "", l.ERR_SRV_JSON);
                                } else {
                                    bVar.a(str2, this.f19598k, 0, valueOf3.intValue(), valueOf3.intValue() * (-1), 0.0d, "", l.ERR_SRV_JSON);
                                }
                                return -1;
                            }
                        } catch (IOException e13) {
                            e = e13;
                            obj = null;
                        }
                        if (jSONObject.getInt("status") != 0) {
                            f.b("HttpClient", "status != 0, recv=" + str4);
                            if (!this.b) {
                                this.f19597j.clear();
                                bVar.a(str2, this.f19598k, 0, 1, -1, 0.0d, "", l.ERR_SRV_Error);
                            } else {
                                bVar.a(str2, this.f19598k, 0, num5.intValue(), num5.intValue() * (-1), 0.0d, "", l.ERR_SRV_Error);
                            }
                        } else {
                            i4 = jSONObject.getInt("index");
                            double d = jSONObject.getDouble(NotificationCompat.CATEGORY_PROGRESS);
                            String string2 = jSONObject.getString("audio");
                            boolean has = jSONObject.has(VerifyTracker.KEY_TIMESTAMP);
                            if (string2.length() == 0 && i4 < 0) {
                                Object obj4 = this.f19596i;
                                try {
                                    synchronized (obj4) {
                                        try {
                                            if (this.f19592e) {
                                                obj3 = obj4;
                                            } else if (has) {
                                                obj3 = obj4;
                                                bVar.a(str2, "0".getBytes(), 0, num5.intValue(), i4, d, jSONObject.getString(VerifyTracker.KEY_TIMESTAMP), l.OK_NO);
                                            } else {
                                                obj3 = obj4;
                                                bVar.a(str2, "0".getBytes(), 0, num5.intValue(), i4, d, "", l.OK_NO);
                                            }
                                        } catch (Throwable th) {
                                            th = th;
                                            obj = obj4;
                                            Throwable th2 = th;
                                            throw th2;
                                        }
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                }
                            } else {
                                byte[] decode = Base64.decode(string2, 0);
                                if (decode.length <= 0) {
                                    f.b("HttpClient", "http recv null=" + str4);
                                    if (!this.b) {
                                        this.f19597j.clear();
                                        obj = null;
                                        try {
                                            bVar.a(str2, this.f19598k, 0, 1, -1, 0.0d, "", l.ERR_NO_Data_Recv);
                                        } catch (IOException e14) {
                                            e = e14;
                                            iOException = e;
                                            str5 = str4;
                                            num2 = valueOf;
                                            valueOf3 = num5;
                                            f.c("HttpClient", "req=" + str2 + ", seq=" + valueOf3 + ", e2=" + iOException.toString() + ", recv=" + str5);
                                            num2 = Integer.valueOf(num2.intValue() + i2);
                                            if (num2.intValue() <= B) {
                                                if (!this.b) {
                                                    this.f19597j.clear();
                                                    bVar.a(str2, this.f19598k, 0, 1, -1, 0.0d, "", l.ERR_SRV_Exception);
                                                } else {
                                                    bVar.a(str2, this.f19598k, 0, valueOf3.intValue(), valueOf3.intValue() * (-1), 0.0d, "", l.ERR_SRV_Exception);
                                                }
                                                return -1;
                                            }
                                            num3 = valueOf3;
                                            str6 = str3;
                                            i3 = 1;
                                        }
                                    } else {
                                        obj = null;
                                        bVar.a(str2, this.f19598k, 0, num5.intValue(), num5.intValue() * (-1), 0.0d, "", l.ERR_NO_Data_Recv);
                                    }
                                    str5 = str4;
                                    num2 = valueOf;
                                    num3 = num5;
                                } else {
                                    obj = null;
                                    byte[] a = this.f19591c.a(decode, num5.intValue() == 1, num5.intValue() < 0);
                                    if (a == null) {
                                        f.b("HttpClient", "audioDecoder getPCMData null=" + str4);
                                    } else if (a.length <= 0) {
                                        f.b("HttpClient", "audioDecoder recv null=" + str4);
                                        if (!this.b) {
                                            this.f19597j.clear();
                                            bVar.a(str2, this.f19598k, 0, 1, -1, 0.0d, "", l.ERR_SRV_Exception);
                                        } else {
                                            bVar.a(str2, this.f19598k, 0, num5.intValue(), num5.intValue() * (-1), 0.0d, "", l.ERR_SRV_Exception);
                                        }
                                    } else {
                                        Object obj5 = this.f19596i;
                                        synchronized (obj5) {
                                            try {
                                                if (this.f19592e) {
                                                    obj2 = obj5;
                                                } else if (has) {
                                                    obj2 = obj5;
                                                    try {
                                                        bVar.a(str2, a, a.length, num5.intValue(), i4, d, jSONObject.getString(VerifyTracker.KEY_TIMESTAMP), l.OK_NO);
                                                    } catch (Throwable th4) {
                                                        th = th4;
                                                        Throwable th5 = th;
                                                        throw th5;
                                                        break;
                                                    }
                                                } else {
                                                    obj2 = obj5;
                                                    bVar.a(str2, a, a.length, num5.intValue(), i4, d, "", l.OK_NO);
                                                }
                                                valueOf3 = Integer.valueOf(i4 + 1);
                                                try {
                                                    if (this.f19592e) {
                                                        bVar.onEnd(str2);
                                                        f.c("HttpClient", "post stop");
                                                        break;
                                                    }
                                                    num3 = valueOf3;
                                                    str5 = str4;
                                                    num2 = valueOf;
                                                } catch (IOException e15) {
                                                    e = e15;
                                                    iOException = e;
                                                    str5 = str4;
                                                    num2 = valueOf;
                                                    f.c("HttpClient", "req=" + str2 + ", seq=" + valueOf3 + ", e2=" + iOException.toString() + ", recv=" + str5);
                                                    num2 = Integer.valueOf(num2.intValue() + i2);
                                                    if (num2.intValue() <= B) {
                                                    }
                                                } catch (JSONException e16) {
                                                    e = e16;
                                                    jSONException = e;
                                                    str5 = str4;
                                                    f.c("HttpClient", "req=" + str2 + "seq=" + valueOf3 + "e2=" + jSONException.toString() + ", recv=" + str5);
                                                    if (this.b) {
                                                    }
                                                    return -1;
                                                }
                                            } catch (Throwable th6) {
                                                th = th6;
                                                obj2 = obj5;
                                                Throwable th52 = th;
                                                throw th52;
                                                break;
                                                break;
                                            }
                                        }
                                    }
                                    str5 = str4;
                                    num2 = valueOf;
                                    num3 = num5;
                                }
                                str6 = str3;
                                i3 = 1;
                            }
                        }
                    }
                } catch (JSONException e17) {
                    e = e17;
                    str4 = string;
                }
            }
        }
        return -1;
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
