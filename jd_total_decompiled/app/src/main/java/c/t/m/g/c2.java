package c.t.m.g;

import com.jd.dynamic.DYConstants;
import java.lang.reflect.Array;
import java.util.HashMap;

/* loaded from: classes.dex */
public class c2 {
    public static double[][] a = (double[][]) Array.newInstance(double.class, 3, 3);
    public static double[][] b = (double[][]) Array.newInstance(double.class, 3, 3);

    public static final HashMap<String, String> a() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("set_is_d", DYConstants.DY_FALSE);
        hashMap.put("set_ar_detect_cycle", "5000");
        hashMap.put("set_ar_model_tran_p", "0.9,0.1,0.1;0.05,0.9,0.0;0.05,0.0,0.9");
        hashMap.put("set_ar_svm_coefs", "0.0283869,-1.8163223,0.0638987,-0.0444159,0.0060289,-0.0504448,0.0191848,-0.0393027,0.1638741,-0.3092559,0.5200077,-0.2453934,0.0074400,-0.5361405,-0.2874790,-0.2523574,1.7743204,0.4632865,-0.5654924,-0.9938366,0.4283443,-0.0267812,-0.0075819,-0.3796862,0.5648848,-1.4701368,-0.3738197,1.0125654,0.0287330,-0.4793603,-0.4990732,-0.7482630,-0.0119201,0.2415649,0.3602605,-0.1186956,-0.0065458,0.0077015,0.0112003,-0.2119662,-0.2086752,-0.5559089,0.9248951,0.0776020,-0.5748735,-0.8740246,-0.0191935,0.0197339,0.4378919,0.9551668,0.0031472,-0.0444159,-0.3499121,0.3054962,-0.0339167,-0.0579482,0.0205577,0.0458116,-1.1755013,-0.3167926,-0.3571256,-0.5214404,0.9789868,-0.0197339,1.7618797,1.7502012,0.0080006,-0.0889945,0.0060289,-0.0950234,0.0006140,-0.0600227,-0.1043452,0.8248816,0.4516337,0.8660923,0.0724954,0.4038885,-1.0389844,-0.0728029,0.0223580,-0.2480098,0.2369409,0.2986906,-0.0189037,-0.0383995,0.0194957,-0.0125456,-0.0006492,-0.0103520,0.2366384,-0.4500220,0.0751040,-0.1629494,-0.0249448,-1.0299784,-0.0052842,0.2841479,0.0005141,-0.0149822,0.0112201,-0.0262024,0.0160228,0.0153012,0.0416125,-0.3108377,1.2293581,0.0589945,0.0070212,0.9712758,0.4340083,-1.5354809,1.9832878,-0.3748374,-0.4999694,-0.8498804,0.3499110,0.0013186,0.0164225,0.2217461,-0.7702606,-0.7341467,-0.9374840,1.6125980,0.0755705,0.4475589,0.3915984,-0.9367343,-0.2411493,-0.0888510,-0.0814651,-0.0073859,0.0154422,0.0016899,0.2007890,-0.2201292,0.0100400,0.7083642,0.1848913,0.1034269,1.1637225;1.0418842,-7.3868078,0.0035373,0.4212405,0.4087681,0.0124724,0.0113050,0.1428725,0.2379786,0.7371249,-0.9905574,0.1734559,2.1556214,0.5768323,-0.5395498,12.7330721,-7.7954013,-0.9349061,0.7810094,1.5902594,-0.8092500,0.0045704,-0.0002612,0.3310318,-1.0121830,-1.1075390,-3.9021741,0.3679506,0.4636390,-0.2138417,-0.9694738,0.1747834,-0.0275008,0.0210058,0.9474795,-0.9264736,-0.0057506,-0.0012273,0.0992586,0.1435730,-1.5963312,1.0331410,-5.0559360,0.6560042,0.2829382,-0.8703365,0.4150043,0.0012120,-0.1766805,3.3060257,0.0005229,0.4212405,0.9665911,-0.5453506,-0.0252310,-0.0618809,-0.0007140,-0.0345798,-2.0606200,0.3044541,0.9506972,-0.0380453,-0.7728036,-0.0012120,5.4723194,5.6703770,0.0016881,-0.4036747,0.4087681,-0.8124428,-0.0207348,-0.0622144,0.0133250,0.2986697,-0.9675706,7.3569353,-1.9221710,-0.3974549,-1.5318498,-0.6206437,0.6331161,-3.5299407,-1.4360124,0.0832089,1.9890686,3.8647203,-1.8756518,-0.0064193,-0.0338910,0.1887917,0.0757534,-0.1678950,3.4958657,-4.5832564,-0.1175509,1.8892027,-8.3540958,0.2663805,-0.0013345,-0.2680872,-0.5851023,0.3170151,0.0035490,-0.0318145,-0.0766785,-0.5573839,-0.8530174,0.7598395,-2.2630764,0.1004934,-0.8634954,1.1891026,-1.8081157,0.5594727,0.1281623,-0.4732029,0.6013652,-0.0089514,0.0146621,-0.1304879,0.8963713,-0.4816907,0.6078821,2.1478680,-0.1874866,-2.1568543,-0.3376827,6.9502626,0.1122740,-0.1328872,-0.1546949,0.0218076,-0.0031845,0.0012185,-0.0050301,-0.5348333,0.7438813,1.0435044,2.6542413,0.4628458,1.4633816;0.2548237,0.9890333,0.1202927,0.0841747,0.0565221,0.0276526,-0.0613798,0.1483738,0.3599042,0.5305397,-0.8757212,0.8650414,-0.0800643,0.8226716,0.4611688,2.0298123,-4.1369802,-0.5624904,0.4849687,1.1327069,-0.6477382,0.0063374,0.0290977,0.1251856,-1.0821471,-0.1653598,-0.1973945,-0.7668631,0.0809552,0.0437852,-0.1000363,-1.2366286,0.0295691,0.0815955,0.0495078,0.0320877,-0.0047759,0.0000906,0.1690935,0.2474278,-0.2559574,-0.9391817,-1.0374168,0.5037218,-0.4337737,0.5601797,0.0703484,-0.0254489,0.0714858,-1.4684605,-0.0135799,0.0841747,0.1478726,-0.0636979,-0.0103589,0.0186362,-0.1188373,-0.3859411,-0.5557367,0.3716387,0.7730779,0.3204433,0.1339113,0.0254489,0.4081864,0.6695225,0.0167344,-0.0140806,0.0565221,-0.0706027,-0.0302894,0.0269102,0.3143313,-0.6372785,-0.2964311,0.6484692,-0.2981499,-0.0944981,-0.8076765,-0.1706491,0.1983016,1.0054731,-1.5813641,-0.7926331,0.2200266,0.4126966,-0.1926699,-0.0414126,-0.0430924,-0.0003168,-0.8173780,-0.2791707,-1.1007173,-0.2402280,0.9677150,2.4615157,-1.6994265,1.1129654,-0.2178536,0.0156574,0.0806253,-0.0649679,-0.0548805,-0.3670660,-0.1662315,-0.0973924,-0.8989038,0.1702653,-0.4121642,-1.0985856,-0.2396300,-1.6043173,-0.1558961,1.7102105,0.1721348,-0.3682787,0.5404134,0.0150360,0.0439431,0.1394869,0.9786450,0.0846747,1.9927191,-0.2373799,0.0923618,-1.0627939,-0.3562365,2.3588922,-0.0421611,0.2334609,0.0289008,0.2045600,-0.0576813,0.0014647,-0.0620317,-0.1242487,0.6116924,-1.9328311,2.6452816,-0.0874867,-0.8821482");
        hashMap.put("set_ar_svm_bias", "-6.3223009,19.4195462,3.3051678");
        hashMap.put("set_ar_lr_coefs", "0.87778475,-4.29081763,0.03635345,0.39206673,0.30567286,0.08639387,0.02219463,0.03325709,0.18507775,0.24029366,-0.14479202,0.92823784,1.00830753,-0.11496632,-0.78077095,6.59795528,-4.28243038,-0.94054280,0.48235688,1.04735568,-0.56499880,-0.00282712,0.00227828,-0.02854377,-0.15767259,-1.11505804,-1.81070546,0.57707194,0.43083502,-0.38845385,-0.71330083,-1.31544479,-0.03934434,0.25722147,0.66547053,-0.40824906,-0.00735026,0.00278658,0.03504350,0.04462980,-1.02673951,0.81680018,-2.69183917,0.42107058,-0.03293306,-3.08660941,0.34886979,0.12681055,0.33254005,1.66956844,0.00253043,0.39206673,0.95397854,-0.56191181,-0.03662833,-0.07686647,0.04079612,0.08694156,-2.62692038,0.77992426,0.76705717,-0.24311485,-0.21247246,0.08129119,4.18457105,4.12016294,0.00685838,-0.09567203,0.30567286,-0.40134489,-0.01158307,-0.07317127,-0.08295824,0.92871858,-0.56975433,3.98104852,-1.70679348,-0.09226725,-2.09839800,-0.48571802,0.57211189,-1.75202087,-0.52325047,0.22507118,0.90296649,1.78173639,-0.87876991,-0.00343127,-0.01568099,0.14411925,0.19460473,0.18499657,1.40323980,-2.22641087,0.00595946,0.75167643,-5.05038880,-0.04965864,0.01752355,-0.12436283,-0.24289031,0.11852747,0.01282563,0.00660185,0.01573222,-0.50981822,0.44715681,0.38578590,-1.40549115,0.94445855,0.25816393,-1.58112694,2.34675839,0.27771420,-0.48038501,-1.09404399,0.61365899,-0.01411233,0.01208518,-0.02089117,0.30854735,-0.14525940,-0.98766972,2.31674571,-0.09427345,-1.52446538,0.07364327,3.24686523,-0.13322299,-0.09683839,-0.05071558,-0.04612281,0.00062107,-0.00122816,0.06715745,-0.34635934,0.50770983,0.79200503,1.64782179,0.24008445,1.42209706;1.37917865,1.76669526,-0.05791987,0.43770399,0.45817293,-0.02046895,-0.02288683,0.13912431,0.02801129,0.85961010,-1.77028592,1.36239984,0.13750332,0.68883239,0.56597855,3.89058624,-3.39391328,0.28415984,0.73590174,1.40695690,-0.67105516,0.01516594,0.00231743,0.45307014,-0.98721156,0.15698413,-0.97703447,-0.63855210,0.02181132,0.01030451,0.18727270,1.21143361,0.03450285,-0.27160001,-0.09756483,-0.17403518,0.00455039,-0.01089697,0.11745748,0.04720286,-0.13923769,-1.15204431,-1.13232199,0.68277857,0.62169325,2.80331425,0.44793846,0.02797258,-0.66231115,-1.06276322,-0.00907545,0.43770399,0.59770909,-0.16000510,0.01404050,0.05160159,-0.05749186,-0.26547875,0.36391002,0.20490704,0.88362633,0.32109971,-1.31404267,0.11440164,-2.36022836,-2.11476479,-0.01625674,0.57617166,0.45817293,0.11799872,-0.03239907,0.02921427,0.26853926,-1.45881337,-0.80228026,0.14404900,-0.85312296,-0.31332686,1.25961235,-0.94147466,0.92100571,-1.06261479,-1.32324448,-0.41016916,0.85521373,1.69948664,-0.84427291,-0.00809875,-0.05181235,-0.03305234,-0.34150967,-1.40394893,0.91856023,-0.35835149,0.00707078,1.63764875,-5.16427668,-0.29038228,-0.02527634,-0.06835742,-0.13163637,0.06327896,-0.02235635,-0.02760041,-0.06756346,0.25872659,-2.20655833,0.04901093,-0.63157408,-1.01420523,-1.71079619,0.60616045,-2.40111035,0.65256386,0.51283495,0.54118344,-0.02834850,0.00596486,0.00216095,-0.08494664,0.98024273,-0.17253287,1.83173316,-0.80936667,-0.09063535,-1.20418391,-0.77328510,3.75712537,0.50420144,0.08308731,0.05767259,0.02541471,-0.01478087,0.00363069,-0.17673456,-0.10975169,0.11359096,-0.45705286,0.65835544,0.06891574,-0.37537447;-2.25696340,2.52412236,0.02156642,-0.82977072,-0.76384579,-0.06592492,0.00069220,-0.17238140,-0.21308904,-1.09990376,1.91507794,-2.29063768,-1.14581086,-0.57386607,0.21479240,-10.48854150,7.67634366,0.65638297,-1.21825862,-2.45431258,1.23605397,-0.01233882,-0.00459570,-0.42452637,1.14488415,0.95807392,2.78773992,0.06148016,-0.45264634,0.37814934,0.52602812,0.10401118,0.00484149,0.01437854,-0.56790570,0.58228423,0.00279987,0.00811039,-0.15250097,-0.09183267,1.16597720,0.33524413,3.82416116,-1.10384915,-0.58876018,0.28329516,-0.79680825,-0.15478314,0.32977111,-0.60680522,0.00654501,-0.82977072,-1.55168763,0.72191691,0.02258783,0.02526488,0.01669574,0.17853719,2.26301036,-0.98483130,-1.65068351,-0.07798485,1.52651514,-0.19569284,-1.82434269,-2.00539814,0.00939836,-0.48049962,-0.76384579,0.28334617,0.04398214,0.04395700,-0.18558101,0.53009479,1.37203459,-4.12509751,2.55991644,0.40559412,0.83878565,1.42719268,-1.49311760,2.81463566,1.84649495,0.18509798,-1.75818021,-3.48122303,1.72304282,0.01153002,0.06749334,-0.11106691,0.14690494,1.21895236,-2.32180003,2.58476236,-0.01303024,-2.38932518,10.21466547,0.34004092,0.00775279,0.19272025,0.37452668,-0.18180643,0.00953072,0.02099856,0.05183124,0.25109163,1.75940152,-0.43479682,2.03706523,0.06974668,1.45263226,0.97496649,0.05435196,-0.93027805,-0.03244994,0.55286055,-0.58531049,0.00814747,-0.01424613,0.10583781,-1.28879008,0.31779227,-0.84406344,-1.50737903,0.18490880,2.72864929,0.69964183,-7.00399060,-0.37097845,0.01375108,-0.00695701,0.02070809,0.01415980,-0.00240253,0.10957711,0.45611103,-0.62130079,-0.33495217,-2.30617724,-0.30900019,-1.04672259");
        hashMap.put("set_ar_lr_bias", "0.38118065,0.26245546,-0.64363609");
        hashMap.put("set_ar_speed_model", "1.0,-3.0,0.35;1.0,0.0,0.35;1.0,1.0,0.35");
        return hashMap;
    }
}