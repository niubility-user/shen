package tv.danmaku.ijk.media.player;

import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.jdcloud.mcdnsdk.McdnManager;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.sdk.jdhttpdns.JDHttpDnsToolkit;
import com.jingdong.sdk.jdhttpdns.pojo.IpModel;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import tv.danmaku.ijk.media.JDPlayerConstant;
import tv.danmaku.ijk.media.JDPlayerSdk;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.example.widget.media.IjkUtils;
import tv.danmaku.ijk.media.example.widget.media.MediaPlayerHelper;
import tv.danmaku.ijk.media.ext.cache.JDPlayerVideoCache;
import tv.danmaku.ijk.media.ext.config.NetConfigBean;
import tv.danmaku.ijk.media.ext.config.PlayerConfigLoader;
import tv.danmaku.ijk.media.ext.identify.PlayerNetHeaderUtil;
import tv.danmaku.ijk.media.player.datasource.RawDataSourceProvider;
import tv.danmaku.ijk.media.utils.PlayerStringUtils;

/* loaded from: classes11.dex */
public class JDPlayerHelper {
    private static final JDPlayerHelper single = new JDPlayerHelper();

    private JDPlayerHelper() {
    }

    private Uri generateRealUrl(Uri uri, IPlayerControl.PlayerOptions playerOptions) {
        if (uri != null) {
            if (playerOptions == null || !playerOptions.isForceAndroidPlayer()) {
                String uri2 = uri.toString();
                if (playerOptions != null && playerOptions.getReconnectCount() > 0 && PlayerStringUtils.isValidUrl(uri2)) {
                    if (!playerOptions.isLive()) {
                        uri2 = JDPlayerConstant.HTTP_HOOK_HEAD + uri2;
                    } else if (playerOptions.isLive() && !PlayerStringUtils.isRtmpStream(uri2)) {
                        uri2 = JDPlayerConstant.LIVE_HOOK_HEAD + uri2;
                    }
                }
                return Uri.parse(uri2);
            }
            return uri;
        }
        return uri;
    }

    public static IjkMediaPlayer getIjkMediaPlayer(IMediaPlayer iMediaPlayer) {
        IMediaPlayer internalMediaPlayer;
        if (iMediaPlayer == null) {
            return null;
        }
        if (iMediaPlayer instanceof IjkMediaPlayer) {
            return (IjkMediaPlayer) iMediaPlayer;
        }
        if ((iMediaPlayer instanceof MediaPlayerProxy) && (internalMediaPlayer = ((MediaPlayerProxy) iMediaPlayer).getInternalMediaPlayer()) != null && (internalMediaPlayer instanceof IjkMediaPlayer)) {
            return (IjkMediaPlayer) internalMediaPlayer;
        }
        return null;
    }

    public static JDPlayerHelper getInstance() {
        return single;
    }

    private String insertIp(String str, Map<String, String> map, IPlayerControl.PlayerOptions playerOptions) {
        if (playerOptions == null || TextUtils.isEmpty(playerOptions.getHostIp().first) || TextUtils.isEmpty(playerOptions.getHostIp().second)) {
            return str;
        }
        if (str.startsWith(JDPlayerConstant.LIVE_RTMP_HEAD + playerOptions.getHostIp().first)) {
            playerOptions.addCustomOption(1, "rtmp_tcurl", str);
            return IjkUtils.replaceOnce(str, playerOptions.getHostIp().first, playerOptions.getHostIp().second);
        }
        if (!str.startsWith("http://" + playerOptions.getHostIp().first)) {
            if (!str.startsWith("https://" + playerOptions.getHostIp().first)) {
                return str;
            }
        }
        String replaceOnce = IjkUtils.replaceOnce(str, playerOptions.getHostIp().first, playerOptions.getHostIp().second);
        map.put(HttpHeaders.HOST, LangUtils.SINGLE_SPACE + playerOptions.getHostIp().first);
        return replaceOnce;
    }

    public void addStreamHostIp(String str, IPlayerControl.PlayerOptions playerOptions, boolean z) {
        IpModel ipFromMemoryCache;
        String format;
        NetConfigBean netConfigBean = PlayerConfigLoader.netConfigBean;
        if (netConfigBean == null || !netConfigBean.isHttpDnsEnable()) {
            return;
        }
        String host = Uri.parse(str).getHost();
        if (JDHttpDnsToolkit.getInstance() == null || (ipFromMemoryCache = JDHttpDnsToolkit.getInstance().getIpFromMemoryCache(host)) == null) {
            return;
        }
        if (z && !TextUtils.isEmpty(ipFromMemoryCache.master)) {
            String str2 = ipFromMemoryCache.master;
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            playerOptions.setHostIp(ipFromMemoryCache.host, str2);
        } else if (z) {
        } else {
            String[] strArr = ipFromMemoryCache.v4;
            if (strArr != null && strArr.length > 0) {
                format = strArr[0];
            } else {
                String[] strArr2 = ipFromMemoryCache.v6;
                format = (strArr2 == null || strArr2.length <= 0) ? "" : String.format("[%s]", strArr2[0]);
            }
            if (TextUtils.isEmpty(format)) {
                return;
            }
            playerOptions.setHostIp(ipFromMemoryCache.host, format);
        }
    }

    public void addUAHeader(IPlayerControl.PlayerOptions playerOptions, HashMap<String, String> hashMap) {
        if (!PlayerNetHeaderUtil.canUse() || hashMap == null) {
            return;
        }
        hashMap.put("User-Agent", PlayerNetHeaderUtil.generateUA());
        PlayerNetHeaderUtil.PlayerType playerType = PlayerNetHeaderUtil.PlayerType.IJK_SH;
        if (playerOptions != null) {
            if (playerOptions.isUnVideo()) {
                playerType = PlayerNetHeaderUtil.PlayerType.IJK_BJ;
            } else if (playerOptions.isForceAndroidPlayer()) {
                playerType = PlayerNetHeaderUtil.PlayerType.MEDIA_SH;
            }
        }
        hashMap.put("Referer", PlayerNetHeaderUtil.generateReferer(playerType));
    }

    public IMediaPlayer createIdlePlayer(IPlayerControl.PlayerOptions playerOptions, Uri uri) {
        IMediaPlayer createPlayer = MediaPlayerHelper.createPlayer(playerOptions);
        PlayerConfigLoader.getInstance().applyConfig(getIjkMediaPlayer(createPlayer), playerOptions, uri);
        createPlayer.setAudioStreamType(3);
        createPlayer.setScreenOnWhilePlaying(true);
        if (PlayerStringUtils.canPreSetLoop(uri)) {
            createPlayer.setLooping(playerOptions.isLoop());
            createPlayer.setLoopCount(playerOptions.getLoopCount());
        }
        return createPlayer;
    }

    public String generateMcdnUrl(String str) {
        NetConfigBean netConfigBean;
        return (PlayerStringUtils.isRtmpStream(str) || !PlayerStringUtils.isMp4Url(str) || (netConfigBean = PlayerConfigLoader.netConfigBean) == null || !netConfigBean.isMcdnEnable() || Build.VERSION.SDK_INT < 23 || !PlayerStringUtils.isSupportMcdn(str)) ? str : McdnManager.g().c(str);
    }

    public Uri generateUrl(String str, IPlayerControl.PlayerOptions playerOptions, HashMap<String, String> hashMap) {
        if (str == null) {
            str = "";
        }
        String trim = str.trim();
        if (PlayerStringUtils.isLocalFile(trim)) {
            return Uri.parse(trim);
        }
        String updateProtocol = getInstance().updateProtocol(trim, playerOptions);
        addUAHeader(playerOptions, hashMap);
        if (updateProtocol.contains("adaptationSet") && playerOptions != null && playerOptions.isLive()) {
            playerOptions.setLasMPD(updateProtocol);
            return Uri.parse("ijklas:");
        }
        addStreamHostIp(updateProtocol, playerOptions, true);
        Uri parseVideoPath = IjkUtils.parseVideoPath(insertIp(updateProtocol, hashMap, playerOptions));
        if (parseVideoPath != null) {
            updateProtocol = parseVideoPath.toString();
        }
        if (playerOptions != null && !playerOptions.isLive()) {
            updateProtocol = generateMcdnUrl(updateProtocol);
        }
        return generateRealUrl(Uri.parse(updateProtocol), playerOptions);
    }

    public Uri setDataSource(IMediaPlayer iMediaPlayer, RawDataSourceProvider rawDataSourceProvider, IPlayerControl.PlayerOptions playerOptions, Map<String, String> map, Uri uri, String str) throws IOException {
        if (rawDataSourceProvider != null) {
            iMediaPlayer.setDataSource(rawDataSourceProvider);
        } else if (playerOptions.isUseCache() && !playerOptions.isLive() && !playerOptions.isForceAndroidPlayer() && !PlayerStringUtils.isLocalFile(uri) && getIjkMediaPlayer(iMediaPlayer) != null) {
            return JDPlayerVideoCache.getInstance().setDataSource(getIjkMediaPlayer(iMediaPlayer), JDPlayerSdk.getInstance().getApplicationContext(), uri, str, map);
        } else {
            iMediaPlayer.setDataSource(JDPlayerSdk.getInstance().getApplicationContext(), uri, map);
        }
        return uri;
    }

    public String updateProtocol(String str, IPlayerControl.PlayerOptions playerOptions) {
        if (playerOptions != null && playerOptions.isLive() && PlayerStringUtils.isLiveDomain(str) && !PlayerStringUtils.isRtmpStream(str) && IjkMediaPlayer.hasLoadCronet) {
            if (str.startsWith("https")) {
                return str.replaceFirst("https", "quics");
            }
            return str.startsWith("http") ? str.replaceFirst("http", "quic") : str;
        }
        return str;
    }
}
