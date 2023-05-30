package g.g.a;

import com.tencent.smtt.sdk.TbsCommonCode;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes18.dex */
public enum l {
    OK_NO(0, "Success"),
    OK_NULL(1, "Success, but data null"),
    ERR_RESOURCE_NO(-100, "Loading model and resource files error"),
    ERR_PLAYER_NO(-101, "Initialize audio player error"),
    ERR_TEXTEMPTY_NO(-102, "Text is empty"),
    ERR_TEXTTOOLONG_NO(jd.wjweblogin.d.c.f20052g, "Text is too long"),
    ERR_SYNTHESIZE_NO(-104, "Synthesize error"),
    ERR_INITTTSENGINE_NO(jd.wjweblogin.d.c.f20056k, "Init TTS engine error"),
    ERR_BATCHEMPTY_NO(jd.wjweblogin.d.c.f20058m, "Batch speak empty"),
    ERR_BATCHTOOLONG_NO(jd.wjweblogin.d.c.o, "Batch speak too long"),
    ERR_PLAYERNULL_NO(jd.wjweblogin.d.c.q, "Audio player is null"),
    ERR_PLAYERCLOSE_NO(jd.wjweblogin.d.c.s, "Audio player release failed"),
    ERR_TTSENGINECLOSE_NO(IMediaPlayer.MEDIA_ERROR_TIMED_OUT, "TTS engine release failed"),
    ERR_NOT_SUPPORT(-111, "TTS engine not support"),
    ERR_SRV_Error(-112, "TTS Srv status"),
    ERR_NOT_AUTH(-113, "TTS Not Auth"),
    ERR_NO_Data_Recv(-114, "Base64 Decoe Data = 0"),
    ERR_PARAM_NO_SUPPORT(-115, "Param Not Support"),
    ERR_SRV_Exception(-116, "Http Srv Exception"),
    ERR_AUTH_OK(-117, "TTS Auth OK"),
    ERR_AUTH_Err(-118, "TTS Auth Err"),
    ERR_HTTP(-119, "Http Code Not 200"),
    ERR_HTTP_RECV(-119, "Http Recv Len 0"),
    ERR_Base64_Decoe(-120, "Audio Decoe Data = 0"),
    ERR_Audo_Decoe(-120, "Audio Decoe Data = 0"),
    ERR_SRV_TIMEOUT(-121, "Http Srv TimeOut"),
    ERR_Base64_Len(TbsCommonCode.DOWNLOAD_NO_NEED_REQUEST, "Base64 Len 0"),
    ERR_SRV_JSON(-123, "Json Str err"),
    ERR_GateWay_Status(-124, "gateway \uff01= 10000"),
    ERR_Engine_Status(-125, "engine status \uff01= 0"),
    ERR_UNKNOWN_NO(-999, "Unknown error");
    
    private String desc;
    private int errno;

    l(int i2, String str) {
        this.desc = str;
        this.errno = i2;
    }

    public String getDesc() {
        return this.desc;
    }

    public int getErrno() {
        return this.errno;
    }

    @Override // java.lang.Enum
    public String toString() {
        return "errno=" + this.errno + ", desc=" + this.desc;
    }
}
