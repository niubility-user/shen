package kotlin.ranges;

import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.util.NoSuchElementException;
import kotlin.Deprecated;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.random.Random;
import kotlin.random.RandomKt;
import kotlin.ranges.CharProgression;
import kotlin.ranges.IntProgression;
import kotlin.ranges.LongProgression;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0002\b\u0004\n\u0002\u0010\n\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b9\n\u0002\u0010\u000f\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0014\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\u0002\u0010\u0003\u001a\u0014\u0010\u0002\u001a\u00020\u0005*\u00020\u0004H\u0087\b\u00a2\u0006\u0004\b\u0002\u0010\u0006\u001a\u0014\u0010\u0002\u001a\u00020\b*\u00020\u0007H\u0087\b\u00a2\u0006\u0004\b\u0002\u0010\t\u001a\u001b\u0010\u0002\u001a\u00020\u0001*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\nH\u0007\u00a2\u0006\u0004\b\u0002\u0010\u000b\u001a\u001b\u0010\u0002\u001a\u00020\u0005*\u00020\u00042\u0006\u0010\u0002\u001a\u00020\nH\u0007\u00a2\u0006\u0004\b\u0002\u0010\f\u001a\u001b\u0010\u0002\u001a\u00020\b*\u00020\u00072\u0006\u0010\u0002\u001a\u00020\nH\u0007\u00a2\u0006\u0004\b\u0002\u0010\r\u001a\u0016\u0010\u000e\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\u000e\u0010\u000f\u001a\u0016\u0010\u000e\u001a\u0004\u0018\u00010\u0005*\u00020\u0004H\u0087\b\u00a2\u0006\u0004\b\u000e\u0010\u0010\u001a\u0016\u0010\u000e\u001a\u0004\u0018\u00010\b*\u00020\u0007H\u0087\b\u00a2\u0006\u0004\b\u000e\u0010\u0011\u001a\u001d\u0010\u000e\u001a\u0004\u0018\u00010\u0001*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\nH\u0007\u00a2\u0006\u0004\b\u000e\u0010\u0012\u001a\u001d\u0010\u000e\u001a\u0004\u0018\u00010\u0005*\u00020\u00042\u0006\u0010\u0002\u001a\u00020\nH\u0007\u00a2\u0006\u0004\b\u000e\u0010\u0013\u001a\u001d\u0010\u000e\u001a\u0004\u0018\u00010\b*\u00020\u00072\u0006\u0010\u0002\u001a\u00020\nH\u0007\u00a2\u0006\u0004\b\u000e\u0010\u0014\u001a\u001e\u0010\u0017\u001a\u00020\u0016*\u00020\u00002\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u0087\n\u00a2\u0006\u0004\b\u0017\u0010\u0018\u001a\u001e\u0010\u0017\u001a\u00020\u0016*\u00020\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u0005H\u0087\n\u00a2\u0006\u0004\b\u0017\u0010\u0019\u001a\u001e\u0010\u0017\u001a\u00020\u0016*\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\bH\u0087\n\u00a2\u0006\u0004\b\u0017\u0010\u001a\u001a\"\u0010\u0017\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020\u00010\u001b2\u0006\u0010\u001d\u001a\u00020\u001cH\u0087\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001f\u001a\"\u0010\u0017\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020\u00050\u001b2\u0006\u0010\u001d\u001a\u00020\u001cH\u0087\u0002\u00a2\u0006\u0004\b \u0010\u001f\u001a\"\u0010\u0017\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020!0\u001b2\u0006\u0010\u001d\u001a\u00020\u001cH\u0087\u0002\u00a2\u0006\u0004\b\"\u0010\u001f\u001a\"\u0010\u0017\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020#0\u001b2\u0006\u0010\u001d\u001a\u00020\u001cH\u0087\u0002\u00a2\u0006\u0004\b$\u0010\u001f\u001a\"\u0010\u0017\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020%0\u001b2\u0006\u0010\u001d\u001a\u00020\u001cH\u0087\u0002\u00a2\u0006\u0004\b&\u0010\u001f\u001a\"\u0010\u0017\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020\u00010\u001b2\u0006\u0010\u001d\u001a\u00020#H\u0087\u0002\u00a2\u0006\u0004\b\u001e\u0010'\u001a\"\u0010\u0017\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020\u00050\u001b2\u0006\u0010\u001d\u001a\u00020#H\u0087\u0002\u00a2\u0006\u0004\b \u0010'\u001a\"\u0010\u0017\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u001d\u001a\u00020#H\u0087\u0002\u00a2\u0006\u0004\b(\u0010'\u001a\"\u0010\u0017\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020!0\u001b2\u0006\u0010\u001d\u001a\u00020#H\u0087\u0002\u00a2\u0006\u0004\b\"\u0010'\u001a\"\u0010\u0017\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020%0\u001b2\u0006\u0010\u001d\u001a\u00020#H\u0087\u0002\u00a2\u0006\u0004\b&\u0010'\u001a\"\u0010\u0017\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020\u00010\u001b2\u0006\u0010\u001d\u001a\u00020%H\u0087\u0002\u00a2\u0006\u0004\b\u001e\u0010)\u001a\"\u0010\u0017\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020\u00050\u001b2\u0006\u0010\u001d\u001a\u00020%H\u0087\u0002\u00a2\u0006\u0004\b \u0010)\u001a\"\u0010\u0017\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u001d\u001a\u00020%H\u0087\u0002\u00a2\u0006\u0004\b(\u0010)\u001a\"\u0010\u0017\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020!0\u001b2\u0006\u0010\u001d\u001a\u00020%H\u0087\u0002\u00a2\u0006\u0004\b\"\u0010)\u001a\"\u0010\u0017\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020#0\u001b2\u0006\u0010\u001d\u001a\u00020%H\u0087\u0002\u00a2\u0006\u0004\b$\u0010)\u001a\"\u0010\u0017\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020\u00050\u001b2\u0006\u0010\u001d\u001a\u00020\u0001H\u0087\u0002\u00a2\u0006\u0004\b \u0010*\u001a\"\u0010\u0017\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u001d\u001a\u00020\u0001H\u0087\u0002\u00a2\u0006\u0004\b(\u0010*\u001a\"\u0010\u0017\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020!0\u001b2\u0006\u0010\u001d\u001a\u00020\u0001H\u0087\u0002\u00a2\u0006\u0004\b\"\u0010*\u001a\"\u0010\u0017\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020#0\u001b2\u0006\u0010\u001d\u001a\u00020\u0001H\u0087\u0002\u00a2\u0006\u0004\b$\u0010*\u001a\"\u0010\u0017\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020%0\u001b2\u0006\u0010\u001d\u001a\u00020\u0001H\u0087\u0002\u00a2\u0006\u0004\b&\u0010*\u001a\"\u0010\u0017\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020\u00010\u001b2\u0006\u0010\u001d\u001a\u00020\u0005H\u0087\u0002\u00a2\u0006\u0004\b\u001e\u0010+\u001a\"\u0010\u0017\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u001d\u001a\u00020\u0005H\u0087\u0002\u00a2\u0006\u0004\b(\u0010+\u001a\"\u0010\u0017\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020!0\u001b2\u0006\u0010\u001d\u001a\u00020\u0005H\u0087\u0002\u00a2\u0006\u0004\b\"\u0010+\u001a\"\u0010\u0017\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020#0\u001b2\u0006\u0010\u001d\u001a\u00020\u0005H\u0087\u0002\u00a2\u0006\u0004\b$\u0010+\u001a\"\u0010\u0017\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020%0\u001b2\u0006\u0010\u001d\u001a\u00020\u0005H\u0087\u0002\u00a2\u0006\u0004\b&\u0010+\u001a\"\u0010\u0017\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020\u00010\u001b2\u0006\u0010\u001d\u001a\u00020!H\u0087\u0002\u00a2\u0006\u0004\b\u001e\u0010,\u001a\"\u0010\u0017\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020\u00050\u001b2\u0006\u0010\u001d\u001a\u00020!H\u0087\u0002\u00a2\u0006\u0004\b \u0010,\u001a\"\u0010\u0017\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u001d\u001a\u00020!H\u0087\u0002\u00a2\u0006\u0004\b(\u0010,\u001a\"\u0010\u0017\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020#0\u001b2\u0006\u0010\u001d\u001a\u00020!H\u0087\u0002\u00a2\u0006\u0004\b$\u0010,\u001a\"\u0010\u0017\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020%0\u001b2\u0006\u0010\u001d\u001a\u00020!H\u0087\u0002\u00a2\u0006\u0004\b&\u0010,\u001a\u001c\u0010/\u001a\u00020.*\u00020\u00012\u0006\u0010-\u001a\u00020\u001cH\u0086\u0004\u00a2\u0006\u0004\b/\u00100\u001a\u001c\u0010/\u001a\u000201*\u00020\u00052\u0006\u0010-\u001a\u00020\u001cH\u0086\u0004\u00a2\u0006\u0004\b/\u00102\u001a\u001c\u0010/\u001a\u00020.*\u00020\u001c2\u0006\u0010-\u001a\u00020\u001cH\u0086\u0004\u00a2\u0006\u0004\b/\u00103\u001a\u001c\u0010/\u001a\u00020.*\u00020!2\u0006\u0010-\u001a\u00020\u001cH\u0086\u0004\u00a2\u0006\u0004\b/\u00104\u001a\u001c\u0010/\u001a\u000205*\u00020\b2\u0006\u0010-\u001a\u00020\bH\u0086\u0004\u00a2\u0006\u0004\b/\u00106\u001a\u001c\u0010/\u001a\u00020.*\u00020\u00012\u0006\u0010-\u001a\u00020\u0001H\u0086\u0004\u00a2\u0006\u0004\b/\u00107\u001a\u001c\u0010/\u001a\u000201*\u00020\u00052\u0006\u0010-\u001a\u00020\u0001H\u0086\u0004\u00a2\u0006\u0004\b/\u00108\u001a\u001c\u0010/\u001a\u00020.*\u00020\u001c2\u0006\u0010-\u001a\u00020\u0001H\u0086\u0004\u00a2\u0006\u0004\b/\u00109\u001a\u001c\u0010/\u001a\u00020.*\u00020!2\u0006\u0010-\u001a\u00020\u0001H\u0086\u0004\u00a2\u0006\u0004\b/\u0010:\u001a\u001c\u0010/\u001a\u000201*\u00020\u00012\u0006\u0010-\u001a\u00020\u0005H\u0086\u0004\u00a2\u0006\u0004\b/\u0010;\u001a\u001c\u0010/\u001a\u000201*\u00020\u00052\u0006\u0010-\u001a\u00020\u0005H\u0086\u0004\u00a2\u0006\u0004\b/\u0010<\u001a\u001c\u0010/\u001a\u000201*\u00020\u001c2\u0006\u0010-\u001a\u00020\u0005H\u0086\u0004\u00a2\u0006\u0004\b/\u0010=\u001a\u001c\u0010/\u001a\u000201*\u00020!2\u0006\u0010-\u001a\u00020\u0005H\u0086\u0004\u00a2\u0006\u0004\b/\u0010>\u001a\u001c\u0010/\u001a\u00020.*\u00020\u00012\u0006\u0010-\u001a\u00020!H\u0086\u0004\u00a2\u0006\u0004\b/\u0010?\u001a\u001c\u0010/\u001a\u000201*\u00020\u00052\u0006\u0010-\u001a\u00020!H\u0086\u0004\u00a2\u0006\u0004\b/\u0010@\u001a\u001c\u0010/\u001a\u00020.*\u00020\u001c2\u0006\u0010-\u001a\u00020!H\u0086\u0004\u00a2\u0006\u0004\b/\u0010A\u001a\u001c\u0010/\u001a\u00020.*\u00020!2\u0006\u0010-\u001a\u00020!H\u0086\u0004\u00a2\u0006\u0004\b/\u0010B\u001a\u0011\u0010C\u001a\u00020.*\u00020.\u00a2\u0006\u0004\bC\u0010D\u001a\u0011\u0010C\u001a\u000201*\u000201\u00a2\u0006\u0004\bC\u0010E\u001a\u0011\u0010C\u001a\u000205*\u000205\u00a2\u0006\u0004\bC\u0010F\u001a\u001c\u0010G\u001a\u00020.*\u00020.2\u0006\u0010G\u001a\u00020\u0001H\u0086\u0004\u00a2\u0006\u0004\bG\u0010H\u001a\u001c\u0010G\u001a\u000201*\u0002012\u0006\u0010G\u001a\u00020\u0005H\u0086\u0004\u00a2\u0006\u0004\bG\u0010I\u001a\u001c\u0010G\u001a\u000205*\u0002052\u0006\u0010G\u001a\u00020\u0001H\u0086\u0004\u00a2\u0006\u0004\bG\u0010J\u001a\u0015\u0010K\u001a\u0004\u0018\u00010\u001c*\u00020\u0001H\u0000\u00a2\u0006\u0004\bK\u0010L\u001a\u0015\u0010K\u001a\u0004\u0018\u00010\u001c*\u00020\u0005H\u0000\u00a2\u0006\u0004\bK\u0010M\u001a\u0015\u0010K\u001a\u0004\u0018\u00010\u001c*\u00020!H\u0000\u00a2\u0006\u0004\bK\u0010N\u001a\u0015\u0010K\u001a\u0004\u0018\u00010\u001c*\u00020#H\u0000\u00a2\u0006\u0004\bK\u0010O\u001a\u0015\u0010K\u001a\u0004\u0018\u00010\u001c*\u00020%H\u0000\u00a2\u0006\u0004\bK\u0010P\u001a\u0015\u0010Q\u001a\u0004\u0018\u00010\u0001*\u00020\u0005H\u0000\u00a2\u0006\u0004\bQ\u0010R\u001a\u0015\u0010Q\u001a\u0004\u0018\u00010\u0001*\u00020#H\u0000\u00a2\u0006\u0004\bQ\u0010S\u001a\u0015\u0010Q\u001a\u0004\u0018\u00010\u0001*\u00020%H\u0000\u00a2\u0006\u0004\bQ\u0010T\u001a\u0015\u0010U\u001a\u0004\u0018\u00010\u0005*\u00020#H\u0000\u00a2\u0006\u0004\bU\u0010V\u001a\u0015\u0010U\u001a\u0004\u0018\u00010\u0005*\u00020%H\u0000\u00a2\u0006\u0004\bU\u0010W\u001a\u0015\u0010X\u001a\u0004\u0018\u00010!*\u00020\u0001H\u0000\u00a2\u0006\u0004\bX\u0010Y\u001a\u0015\u0010X\u001a\u0004\u0018\u00010!*\u00020\u0005H\u0000\u00a2\u0006\u0004\bX\u0010Z\u001a\u0015\u0010X\u001a\u0004\u0018\u00010!*\u00020#H\u0000\u00a2\u0006\u0004\bX\u0010[\u001a\u0015\u0010X\u001a\u0004\u0018\u00010!*\u00020%H\u0000\u00a2\u0006\u0004\bX\u0010\\\u001a\u001c\u0010]\u001a\u00020\u0000*\u00020\u00012\u0006\u0010-\u001a\u00020\u001cH\u0086\u0004\u00a2\u0006\u0004\b]\u0010^\u001a\u001c\u0010]\u001a\u00020\u0004*\u00020\u00052\u0006\u0010-\u001a\u00020\u001cH\u0086\u0004\u00a2\u0006\u0004\b]\u0010_\u001a\u001c\u0010]\u001a\u00020\u0000*\u00020\u001c2\u0006\u0010-\u001a\u00020\u001cH\u0086\u0004\u00a2\u0006\u0004\b]\u0010`\u001a\u001c\u0010]\u001a\u00020\u0000*\u00020!2\u0006\u0010-\u001a\u00020\u001cH\u0086\u0004\u00a2\u0006\u0004\b]\u0010a\u001a\u001c\u0010]\u001a\u00020\u0007*\u00020\b2\u0006\u0010-\u001a\u00020\bH\u0086\u0004\u00a2\u0006\u0004\b]\u0010b\u001a\u001c\u0010]\u001a\u00020\u0000*\u00020\u00012\u0006\u0010-\u001a\u00020\u0001H\u0086\u0004\u00a2\u0006\u0004\b]\u0010c\u001a\u001c\u0010]\u001a\u00020\u0004*\u00020\u00052\u0006\u0010-\u001a\u00020\u0001H\u0086\u0004\u00a2\u0006\u0004\b]\u0010d\u001a\u001c\u0010]\u001a\u00020\u0000*\u00020\u001c2\u0006\u0010-\u001a\u00020\u0001H\u0086\u0004\u00a2\u0006\u0004\b]\u0010e\u001a\u001c\u0010]\u001a\u00020\u0000*\u00020!2\u0006\u0010-\u001a\u00020\u0001H\u0086\u0004\u00a2\u0006\u0004\b]\u0010f\u001a\u001c\u0010]\u001a\u00020\u0004*\u00020\u00012\u0006\u0010-\u001a\u00020\u0005H\u0086\u0004\u00a2\u0006\u0004\b]\u0010g\u001a\u001c\u0010]\u001a\u00020\u0004*\u00020\u00052\u0006\u0010-\u001a\u00020\u0005H\u0086\u0004\u00a2\u0006\u0004\b]\u0010h\u001a\u001c\u0010]\u001a\u00020\u0004*\u00020\u001c2\u0006\u0010-\u001a\u00020\u0005H\u0086\u0004\u00a2\u0006\u0004\b]\u0010i\u001a\u001c\u0010]\u001a\u00020\u0004*\u00020!2\u0006\u0010-\u001a\u00020\u0005H\u0086\u0004\u00a2\u0006\u0004\b]\u0010j\u001a\u001c\u0010]\u001a\u00020\u0000*\u00020\u00012\u0006\u0010-\u001a\u00020!H\u0086\u0004\u00a2\u0006\u0004\b]\u0010k\u001a\u001c\u0010]\u001a\u00020\u0004*\u00020\u00052\u0006\u0010-\u001a\u00020!H\u0086\u0004\u00a2\u0006\u0004\b]\u0010l\u001a\u001c\u0010]\u001a\u00020\u0000*\u00020\u001c2\u0006\u0010-\u001a\u00020!H\u0086\u0004\u00a2\u0006\u0004\b]\u0010m\u001a\u001c\u0010]\u001a\u00020\u0000*\u00020!2\u0006\u0010-\u001a\u00020!H\u0086\u0004\u00a2\u0006\u0004\b]\u0010n\u001a)\u0010r\u001a\u00028\u0000\"\u000e\b\u0000\u0010p*\b\u0012\u0004\u0012\u00028\u00000o*\u00028\u00002\u0006\u0010q\u001a\u00028\u0000\u00a2\u0006\u0004\br\u0010s\u001a\u0019\u0010r\u001a\u00020\u001c*\u00020\u001c2\u0006\u0010q\u001a\u00020\u001c\u00a2\u0006\u0004\br\u0010t\u001a\u0019\u0010r\u001a\u00020!*\u00020!2\u0006\u0010q\u001a\u00020!\u00a2\u0006\u0004\br\u0010u\u001a\u0019\u0010r\u001a\u00020\u0001*\u00020\u00012\u0006\u0010q\u001a\u00020\u0001\u00a2\u0006\u0004\br\u0010v\u001a\u0019\u0010r\u001a\u00020\u0005*\u00020\u00052\u0006\u0010q\u001a\u00020\u0005\u00a2\u0006\u0004\br\u0010w\u001a\u0019\u0010r\u001a\u00020%*\u00020%2\u0006\u0010q\u001a\u00020%\u00a2\u0006\u0004\br\u0010x\u001a\u0019\u0010r\u001a\u00020#*\u00020#2\u0006\u0010q\u001a\u00020#\u00a2\u0006\u0004\br\u0010y\u001a)\u0010{\u001a\u00028\u0000\"\u000e\b\u0000\u0010p*\b\u0012\u0004\u0012\u00028\u00000o*\u00028\u00002\u0006\u0010z\u001a\u00028\u0000\u00a2\u0006\u0004\b{\u0010s\u001a\u0019\u0010{\u001a\u00020\u001c*\u00020\u001c2\u0006\u0010z\u001a\u00020\u001c\u00a2\u0006\u0004\b{\u0010t\u001a\u0019\u0010{\u001a\u00020!*\u00020!2\u0006\u0010z\u001a\u00020!\u00a2\u0006\u0004\b{\u0010u\u001a\u0019\u0010{\u001a\u00020\u0001*\u00020\u00012\u0006\u0010z\u001a\u00020\u0001\u00a2\u0006\u0004\b{\u0010v\u001a\u0019\u0010{\u001a\u00020\u0005*\u00020\u00052\u0006\u0010z\u001a\u00020\u0005\u00a2\u0006\u0004\b{\u0010w\u001a\u0019\u0010{\u001a\u00020%*\u00020%2\u0006\u0010z\u001a\u00020%\u00a2\u0006\u0004\b{\u0010x\u001a\u0019\u0010{\u001a\u00020#*\u00020#2\u0006\u0010z\u001a\u00020#\u00a2\u0006\u0004\b{\u0010y\u001a5\u0010|\u001a\u00028\u0000\"\u000e\b\u0000\u0010p*\b\u0012\u0004\u0012\u00028\u00000o*\u00028\u00002\b\u0010q\u001a\u0004\u0018\u00018\u00002\b\u0010z\u001a\u0004\u0018\u00018\u0000\u00a2\u0006\u0004\b|\u0010}\u001a!\u0010|\u001a\u00020\u001c*\u00020\u001c2\u0006\u0010q\u001a\u00020\u001c2\u0006\u0010z\u001a\u00020\u001c\u00a2\u0006\u0004\b|\u0010~\u001a!\u0010|\u001a\u00020!*\u00020!2\u0006\u0010q\u001a\u00020!2\u0006\u0010z\u001a\u00020!\u00a2\u0006\u0004\b|\u0010\u007f\u001a\"\u0010|\u001a\u00020\u0001*\u00020\u00012\u0006\u0010q\u001a\u00020\u00012\u0006\u0010z\u001a\u00020\u0001\u00a2\u0006\u0005\b|\u0010\u0080\u0001\u001a\"\u0010|\u001a\u00020\u0005*\u00020\u00052\u0006\u0010q\u001a\u00020\u00052\u0006\u0010z\u001a\u00020\u0005\u00a2\u0006\u0005\b|\u0010\u0081\u0001\u001a\"\u0010|\u001a\u00020%*\u00020%2\u0006\u0010q\u001a\u00020%2\u0006\u0010z\u001a\u00020%\u00a2\u0006\u0005\b|\u0010\u0082\u0001\u001a\"\u0010|\u001a\u00020#*\u00020#2\u0006\u0010q\u001a\u00020#2\u0006\u0010z\u001a\u00020#\u00a2\u0006\u0005\b|\u0010\u0083\u0001\u001a4\u0010|\u001a\u00028\u0000\"\u000e\b\u0000\u0010p*\b\u0012\u0004\u0012\u00028\u00000o*\u00028\u00002\u000e\u0010\u0085\u0001\u001a\t\u0012\u0004\u0012\u00028\u00000\u0084\u0001H\u0007\u00a2\u0006\u0005\b|\u0010\u0086\u0001\u001a1\u0010|\u001a\u00028\u0000\"\u000e\b\u0000\u0010p*\b\u0012\u0004\u0012\u00028\u00000o*\u00028\u00002\r\u0010\u0085\u0001\u001a\b\u0012\u0004\u0012\u00028\u00000\u001b\u00a2\u0006\u0005\b|\u0010\u0087\u0001\u001a!\u0010|\u001a\u00020\u0001*\u00020\u00012\r\u0010\u0085\u0001\u001a\b\u0012\u0004\u0012\u00020\u00010\u001b\u00a2\u0006\u0005\b|\u0010\u0088\u0001\u001a!\u0010|\u001a\u00020\u0005*\u00020\u00052\r\u0010\u0085\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u001b\u00a2\u0006\u0005\b|\u0010\u0089\u0001\u00a8\u0006\u008a\u0001"}, d2 = {"Lkotlin/ranges/IntRange;", "", "random", "(Lkotlin/ranges/IntRange;)I", "Lkotlin/ranges/LongRange;", "", "(Lkotlin/ranges/LongRange;)J", "Lkotlin/ranges/CharRange;", "", "(Lkotlin/ranges/CharRange;)C", "Lkotlin/random/Random;", "(Lkotlin/ranges/IntRange;Lkotlin/random/Random;)I", "(Lkotlin/ranges/LongRange;Lkotlin/random/Random;)J", "(Lkotlin/ranges/CharRange;Lkotlin/random/Random;)C", "randomOrNull", "(Lkotlin/ranges/IntRange;)Ljava/lang/Integer;", "(Lkotlin/ranges/LongRange;)Ljava/lang/Long;", "(Lkotlin/ranges/CharRange;)Ljava/lang/Character;", "(Lkotlin/ranges/IntRange;Lkotlin/random/Random;)Ljava/lang/Integer;", "(Lkotlin/ranges/LongRange;Lkotlin/random/Random;)Ljava/lang/Long;", "(Lkotlin/ranges/CharRange;Lkotlin/random/Random;)Ljava/lang/Character;", "element", "", "contains", "(Lkotlin/ranges/IntRange;Ljava/lang/Integer;)Z", "(Lkotlin/ranges/LongRange;Ljava/lang/Long;)Z", "(Lkotlin/ranges/CharRange;Ljava/lang/Character;)Z", "Lkotlin/ranges/ClosedRange;", "", "value", "intRangeContains", "(Lkotlin/ranges/ClosedRange;B)Z", "longRangeContains", "", "shortRangeContains", "", "doubleRangeContains", "", "floatRangeContains", "(Lkotlin/ranges/ClosedRange;D)Z", "byteRangeContains", "(Lkotlin/ranges/ClosedRange;F)Z", "(Lkotlin/ranges/ClosedRange;I)Z", "(Lkotlin/ranges/ClosedRange;J)Z", "(Lkotlin/ranges/ClosedRange;S)Z", RemoteMessageConst.TO, "Lkotlin/ranges/IntProgression;", "downTo", "(IB)Lkotlin/ranges/IntProgression;", "Lkotlin/ranges/LongProgression;", "(JB)Lkotlin/ranges/LongProgression;", "(BB)Lkotlin/ranges/IntProgression;", "(SB)Lkotlin/ranges/IntProgression;", "Lkotlin/ranges/CharProgression;", "(CC)Lkotlin/ranges/CharProgression;", "(II)Lkotlin/ranges/IntProgression;", "(JI)Lkotlin/ranges/LongProgression;", "(BI)Lkotlin/ranges/IntProgression;", "(SI)Lkotlin/ranges/IntProgression;", "(IJ)Lkotlin/ranges/LongProgression;", "(JJ)Lkotlin/ranges/LongProgression;", "(BJ)Lkotlin/ranges/LongProgression;", "(SJ)Lkotlin/ranges/LongProgression;", "(IS)Lkotlin/ranges/IntProgression;", "(JS)Lkotlin/ranges/LongProgression;", "(BS)Lkotlin/ranges/IntProgression;", "(SS)Lkotlin/ranges/IntProgression;", "reversed", "(Lkotlin/ranges/IntProgression;)Lkotlin/ranges/IntProgression;", "(Lkotlin/ranges/LongProgression;)Lkotlin/ranges/LongProgression;", "(Lkotlin/ranges/CharProgression;)Lkotlin/ranges/CharProgression;", "step", "(Lkotlin/ranges/IntProgression;I)Lkotlin/ranges/IntProgression;", "(Lkotlin/ranges/LongProgression;J)Lkotlin/ranges/LongProgression;", "(Lkotlin/ranges/CharProgression;I)Lkotlin/ranges/CharProgression;", "toByteExactOrNull", "(I)Ljava/lang/Byte;", "(J)Ljava/lang/Byte;", "(S)Ljava/lang/Byte;", "(D)Ljava/lang/Byte;", "(F)Ljava/lang/Byte;", "toIntExactOrNull", "(J)Ljava/lang/Integer;", "(D)Ljava/lang/Integer;", "(F)Ljava/lang/Integer;", "toLongExactOrNull", "(D)Ljava/lang/Long;", "(F)Ljava/lang/Long;", "toShortExactOrNull", "(I)Ljava/lang/Short;", "(J)Ljava/lang/Short;", "(D)Ljava/lang/Short;", "(F)Ljava/lang/Short;", "until", "(IB)Lkotlin/ranges/IntRange;", "(JB)Lkotlin/ranges/LongRange;", "(BB)Lkotlin/ranges/IntRange;", "(SB)Lkotlin/ranges/IntRange;", "(CC)Lkotlin/ranges/CharRange;", "(II)Lkotlin/ranges/IntRange;", "(JI)Lkotlin/ranges/LongRange;", "(BI)Lkotlin/ranges/IntRange;", "(SI)Lkotlin/ranges/IntRange;", "(IJ)Lkotlin/ranges/LongRange;", "(JJ)Lkotlin/ranges/LongRange;", "(BJ)Lkotlin/ranges/LongRange;", "(SJ)Lkotlin/ranges/LongRange;", "(IS)Lkotlin/ranges/IntRange;", "(JS)Lkotlin/ranges/LongRange;", "(BS)Lkotlin/ranges/IntRange;", "(SS)Lkotlin/ranges/IntRange;", "", "T", "minimumValue", "coerceAtLeast", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", "(BB)B", "(SS)S", "(II)I", "(JJ)J", "(FF)F", "(DD)D", "maximumValue", "coerceAtMost", "coerceIn", "(Ljava/lang/Comparable;Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", "(BBB)B", "(SSS)S", "(III)I", "(JJJ)J", "(FFF)F", "(DDD)D", "Lkotlin/ranges/ClosedFloatingPointRange;", "range", "(Ljava/lang/Comparable;Lkotlin/ranges/ClosedFloatingPointRange;)Ljava/lang/Comparable;", "(Ljava/lang/Comparable;Lkotlin/ranges/ClosedRange;)Ljava/lang/Comparable;", "(ILkotlin/ranges/ClosedRange;)I", "(JLkotlin/ranges/ClosedRange;)J", "kotlin-stdlib"}, k = 5, mv = {1, 4, 0}, xs = "kotlin/ranges/RangesKt")
/* loaded from: classes.dex */
public class RangesKt___RangesKt extends RangesKt__RangesKt {
    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @JvmName(name = "byteRangeContains")
    public static final boolean byteRangeContains(@NotNull ClosedRange<Byte> closedRange, double d) {
        Byte byteExactOrNull = toByteExactOrNull(d);
        if (byteExactOrNull != null) {
            return closedRange.contains(byteExactOrNull);
        }
        return false;
    }

    public static final byte coerceAtLeast(byte b, byte b2) {
        return b < b2 ? b2 : b;
    }

    public static final double coerceAtLeast(double d, double d2) {
        return d < d2 ? d2 : d;
    }

    public static float coerceAtLeast(float f2, float f3) {
        return f2 < f3 ? f3 : f2;
    }

    public static int coerceAtLeast(int i2, int i3) {
        return i2 < i3 ? i3 : i2;
    }

    public static long coerceAtLeast(long j2, long j3) {
        return j2 < j3 ? j3 : j2;
    }

    @NotNull
    public static final <T extends Comparable<? super T>> T coerceAtLeast(@NotNull T t, @NotNull T t2) {
        return t.compareTo(t2) < 0 ? t2 : t;
    }

    public static final short coerceAtLeast(short s, short s2) {
        return s < s2 ? s2 : s;
    }

    public static final byte coerceAtMost(byte b, byte b2) {
        return b > b2 ? b2 : b;
    }

    public static final double coerceAtMost(double d, double d2) {
        return d > d2 ? d2 : d;
    }

    public static float coerceAtMost(float f2, float f3) {
        return f2 > f3 ? f3 : f2;
    }

    public static int coerceAtMost(int i2, int i3) {
        return i2 > i3 ? i3 : i2;
    }

    public static long coerceAtMost(long j2, long j3) {
        return j2 > j3 ? j3 : j2;
    }

    @NotNull
    public static final <T extends Comparable<? super T>> T coerceAtMost(@NotNull T t, @NotNull T t2) {
        return t.compareTo(t2) > 0 ? t2 : t;
    }

    public static final short coerceAtMost(short s, short s2) {
        return s > s2 ? s2 : s;
    }

    @NotNull
    public static final <T extends Comparable<? super T>> T coerceIn(@NotNull T t, @Nullable T t2, @Nullable T t3) {
        if (t2 != null && t3 != null) {
            if (t2.compareTo(t3) <= 0) {
                if (t.compareTo(t2) < 0) {
                    return t2;
                }
                if (t.compareTo(t3) > 0) {
                    return t3;
                }
            } else {
                throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + t3 + " is less than minimum " + t2 + OrderISVUtil.MONEY_DECIMAL_CHAR);
            }
        } else if (t2 != null && t.compareTo(t2) < 0) {
            return t2;
        } else {
            if (t3 != null && t.compareTo(t3) > 0) {
                return t3;
            }
        }
        return t;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final boolean contains(@NotNull  Var, Integer num) {
        return num != null && Var.contains(num.intValue());
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @JvmName(name = "doubleRangeContains")
    public static final boolean doubleRangeContains(@NotNull ClosedRange<Double> closedRange, byte b) {
        return closedRange.contains(Double.valueOf(b));
    }

    @NotNull
    public static final IntProgression downTo(int i2, byte b) {
        return IntProgression.INSTANCE.fromClosedRange(i2, b, -1);
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @JvmName(name = "floatRangeContains")
    public static final boolean floatRangeContains(@NotNull ClosedRange<Float> closedRange, byte b) {
        return closedRange.contains(Float.valueOf(b));
    }

    @JvmName(name = "intRangeContains")
    public static final boolean intRangeContains(@NotNull ClosedRange<Integer> closedRange, byte b) {
        return closedRange.contains(Integer.valueOf(b));
    }

    @JvmName(name = "longRangeContains")
    public static final boolean longRangeContains(@NotNull ClosedRange<Long> closedRange, byte b) {
        return closedRange.contains(Long.valueOf(b));
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final int random(@NotNull  Var) {
        return random(Var, Random.INSTANCE);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final Integer randomOrNull(@NotNull  Var) {
        return randomOrNull(Var, Random.INSTANCE);
    }

    @NotNull
    public static final IntProgression reversed(@NotNull IntProgression intProgression) {
        return IntProgression.INSTANCE.fromClosedRange(intProgression.getLast(), intProgression.getFirst(), -intProgression.getStep());
    }

    @JvmName(name = "shortRangeContains")
    public static final boolean shortRangeContains(@NotNull ClosedRange<Short> closedRange, byte b) {
        return closedRange.contains(Short.valueOf(b));
    }

    @NotNull
    public static IntProgression step(@NotNull IntProgression intProgression, int i2) {
        RangesKt__RangesKt.checkStepIsPositive(i2 > 0, Integer.valueOf(i2));
        IntProgression.Companion companion = IntProgression.INSTANCE;
        int first = intProgression.getFirst();
        int last = intProgression.getLast();
        if (intProgression.getStep() <= 0) {
            i2 = -i2;
        }
        return companion.fromClosedRange(first, last, i2);
    }

    @Nullable
    public static final Byte toByteExactOrNull(int i2) {
        if (-128 <= i2 && 127 >= i2) {
            return Byte.valueOf((byte) i2);
        }
        return null;
    }

    @Nullable
    public static final Integer toIntExactOrNull(long j2) {
        long j3 = Integer.MAX_VALUE;
        if (Integer.MIN_VALUE <= j2 && j3 >= j2) {
            return Integer.valueOf((int) j2);
        }
        return null;
    }

    @Nullable
    public static final Long toLongExactOrNull(double d) {
        double d2 = Long.MAX_VALUE;
        if (d < Long.MIN_VALUE || d > d2) {
            return null;
        }
        return Long.valueOf((long) d);
    }

    @Nullable
    public static final Short toShortExactOrNull(int i2) {
        if (-32768 <= i2 && 32767 >= i2) {
            return Short.valueOf((short) i2);
        }
        return null;
    }

    @NotNull
    public static final  until(int i2, byte b) {
        return new (i2, b - 1);
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @JvmName(name = "byteRangeContains")
    public static final boolean byteRangeContains(@NotNull ClosedRange<Byte> closedRange, float f2) {
        Byte byteExactOrNull = toByteExactOrNull(f2);
        if (byteExactOrNull != null) {
            return closedRange.contains(byteExactOrNull);
        }
        return false;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final boolean contains(@NotNull  Var, Long l2) {
        return l2 != null && Var.contains(l2.longValue());
    }

    @JvmName(name = "doubleRangeContains")
    public static final boolean doubleRangeContains(@NotNull ClosedRange<Double> closedRange, float f2) {
        return closedRange.contains(Double.valueOf(f2));
    }

    @NotNull
    public static final LongProgression downTo(long j2, byte b) {
        return LongProgression.INSTANCE.fromClosedRange(j2, b, -1L);
    }

    @JvmName(name = "floatRangeContains")
    public static final boolean floatRangeContains(@NotNull ClosedRange<Float> closedRange, double d) {
        return closedRange.contains(Float.valueOf((float) d));
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @JvmName(name = "intRangeContains")
    public static final boolean intRangeContains(@NotNull ClosedRange<Integer> closedRange, double d) {
        Integer intExactOrNull = toIntExactOrNull(d);
        if (intExactOrNull != null) {
            return closedRange.contains(intExactOrNull);
        }
        return false;
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @JvmName(name = "longRangeContains")
    public static final boolean longRangeContains(@NotNull ClosedRange<Long> closedRange, double d) {
        Long longExactOrNull = toLongExactOrNull(d);
        if (longExactOrNull != null) {
            return closedRange.contains(longExactOrNull);
        }
        return false;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final long random(@NotNull  Var) {
        return random(Var, Random.INSTANCE);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final Long randomOrNull(@NotNull  Var) {
        return randomOrNull(Var, Random.INSTANCE);
    }

    @NotNull
    public static final LongProgression reversed(@NotNull LongProgression longProgression) {
        return LongProgression.INSTANCE.fromClosedRange(longProgression.getLast(), longProgression.getFirst(), -longProgression.getStep());
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @JvmName(name = "shortRangeContains")
    public static final boolean shortRangeContains(@NotNull ClosedRange<Short> closedRange, double d) {
        Short shortExactOrNull = toShortExactOrNull(d);
        if (shortExactOrNull != null) {
            return closedRange.contains(shortExactOrNull);
        }
        return false;
    }

    @Nullable
    public static final Byte toByteExactOrNull(long j2) {
        long j3 = 127;
        if (-128 <= j2 && j3 >= j2) {
            return Byte.valueOf((byte) j2);
        }
        return null;
    }

    @Nullable
    public static final Integer toIntExactOrNull(double d) {
        double d2 = Integer.MAX_VALUE;
        if (d < Integer.MIN_VALUE || d > d2) {
            return null;
        }
        return Integer.valueOf((int) d);
    }

    @Nullable
    public static final Long toLongExactOrNull(float f2) {
        float f3 = (float) Long.MAX_VALUE;
        if (f2 < ((float) Long.MIN_VALUE) || f2 > f3) {
            return null;
        }
        return Long.valueOf(f2);
    }

    @Nullable
    public static final Short toShortExactOrNull(long j2) {
        long j3 = 32767;
        if (-32768 <= j2 && j3 >= j2) {
            return Short.valueOf((short) j2);
        }
        return null;
    }

    @NotNull
    public static final  until(long j2, byte b) {
        return new (j2, b - 1);
    }

    @JvmName(name = "byteRangeContains")
    public static final boolean byteRangeContains(@NotNull ClosedRange<Byte> closedRange, int i2) {
        Byte byteExactOrNull = toByteExactOrNull(i2);
        if (byteExactOrNull != null) {
            return closedRange.contains(byteExactOrNull);
        }
        return false;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final boolean contains(@NotNull  Var, Character ch) {
        return ch != null && Var.contains(ch.charValue());
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @JvmName(name = "doubleRangeContains")
    public static final boolean doubleRangeContains(@NotNull ClosedRange<Double> closedRange, int i2) {
        return closedRange.contains(Double.valueOf(i2));
    }

    @NotNull
    public static final IntProgression downTo(byte b, byte b2) {
        return IntProgression.INSTANCE.fromClosedRange(b, b2, -1);
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @JvmName(name = "floatRangeContains")
    public static final boolean floatRangeContains(@NotNull ClosedRange<Float> closedRange, int i2) {
        return closedRange.contains(Float.valueOf(i2));
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @JvmName(name = "intRangeContains")
    public static final boolean intRangeContains(@NotNull ClosedRange<Integer> closedRange, float f2) {
        Integer intExactOrNull = toIntExactOrNull(f2);
        if (intExactOrNull != null) {
            return closedRange.contains(intExactOrNull);
        }
        return false;
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @JvmName(name = "longRangeContains")
    public static final boolean longRangeContains(@NotNull ClosedRange<Long> closedRange, float f2) {
        Long longExactOrNull = toLongExactOrNull(f2);
        if (longExactOrNull != null) {
            return closedRange.contains(longExactOrNull);
        }
        return false;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final char random(@NotNull  Var) {
        return random(Var, Random.INSTANCE);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final Character randomOrNull(@NotNull  Var) {
        return randomOrNull(Var, Random.INSTANCE);
    }

    @NotNull
    public static final CharProgression reversed(@NotNull CharProgression charProgression) {
        return CharProgression.INSTANCE.fromClosedRange(charProgression.getLast(), charProgression.getFirst(), -charProgression.getStep());
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @JvmName(name = "shortRangeContains")
    public static final boolean shortRangeContains(@NotNull ClosedRange<Short> closedRange, float f2) {
        Short shortExactOrNull = toShortExactOrNull(f2);
        if (shortExactOrNull != null) {
            return closedRange.contains(shortExactOrNull);
        }
        return false;
    }

    @NotNull
    public static final LongProgression step(@NotNull LongProgression longProgression, long j2) {
        RangesKt__RangesKt.checkStepIsPositive(j2 > 0, Long.valueOf(j2));
        LongProgression.Companion companion = LongProgression.INSTANCE;
        long first = longProgression.getFirst();
        long last = longProgression.getLast();
        if (longProgression.getStep() <= 0) {
            j2 = -j2;
        }
        return companion.fromClosedRange(first, last, j2);
    }

    @Nullable
    public static final Byte toByteExactOrNull(short s) {
        short s2 = (short) 127;
        if (((short) (-128)) <= s && s2 >= s) {
            return Byte.valueOf((byte) s);
        }
        return null;
    }

    @Nullable
    public static final Integer toIntExactOrNull(float f2) {
        float f3 = Integer.MAX_VALUE;
        if (f2 < Integer.MIN_VALUE || f2 > f3) {
            return null;
        }
        return Integer.valueOf((int) f2);
    }

    @Nullable
    public static final Short toShortExactOrNull(double d) {
        double d2 = 32767;
        if (d < -32768 || d > d2) {
            return null;
        }
        return Short.valueOf((short) d);
    }

    @NotNull
    public static final  until(byte b, byte b2) {
        return new (b, b2 - 1);
    }

    @JvmName(name = "byteRangeContains")
    public static final boolean byteRangeContains(@NotNull ClosedRange<Byte> closedRange, long j2) {
        Byte byteExactOrNull = toByteExactOrNull(j2);
        if (byteExactOrNull != null) {
            return closedRange.contains(byteExactOrNull);
        }
        return false;
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @JvmName(name = "doubleRangeContains")
    public static final boolean doubleRangeContains(@NotNull ClosedRange<Double> closedRange, long j2) {
        return closedRange.contains(Double.valueOf(j2));
    }

    @NotNull
    public static final IntProgression downTo(short s, byte b) {
        return IntProgression.INSTANCE.fromClosedRange(s, b, -1);
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @JvmName(name = "floatRangeContains")
    public static final boolean floatRangeContains(@NotNull ClosedRange<Float> closedRange, long j2) {
        return closedRange.contains(Float.valueOf((float) j2));
    }

    @JvmName(name = "intRangeContains")
    public static final boolean intRangeContains(@NotNull ClosedRange<Integer> closedRange, long j2) {
        Integer intExactOrNull = toIntExactOrNull(j2);
        if (intExactOrNull != null) {
            return closedRange.contains(intExactOrNull);
        }
        return false;
    }

    @JvmName(name = "longRangeContains")
    public static final boolean longRangeContains(@NotNull ClosedRange<Long> closedRange, int i2) {
        return closedRange.contains(Long.valueOf(i2));
    }

    @SinceKotlin(version = "1.3")
    public static final int random(@NotNull  Var, @NotNull Random random) {
        try {
            return RandomKt.nextInt(random, Var);
        } catch (IllegalArgumentException e2) {
            throw new NoSuchElementException(e2.getMessage());
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Nullable
    public static final Integer randomOrNull(@NotNull  Var, @NotNull Random random) {
        if (Var.isEmpty()) {
            return null;
        }
        return Integer.valueOf(RandomKt.nextInt(random, Var));
    }

    @JvmName(name = "shortRangeContains")
    public static final boolean shortRangeContains(@NotNull ClosedRange<Short> closedRange, int i2) {
        Short shortExactOrNull = toShortExactOrNull(i2);
        if (shortExactOrNull != null) {
            return closedRange.contains(shortExactOrNull);
        }
        return false;
    }

    @Nullable
    public static final Byte toByteExactOrNull(double d) {
        double d2 = 127;
        if (d < -128 || d > d2) {
            return null;
        }
        return Byte.valueOf((byte) d);
    }

    @Nullable
    public static final Short toShortExactOrNull(float f2) {
        float f3 = 32767;
        if (f2 < -32768 || f2 > f3) {
            return null;
        }
        return Short.valueOf((short) f2);
    }

    @NotNull
    public static final  until(short s, byte b) {
        return new (s, b - 1);
    }

    @JvmName(name = "byteRangeContains")
    public static final boolean byteRangeContains(@NotNull ClosedRange<Byte> closedRange, short s) {
        Byte byteExactOrNull = toByteExactOrNull(s);
        if (byteExactOrNull != null) {
            return closedRange.contains(byteExactOrNull);
        }
        return false;
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @JvmName(name = "doubleRangeContains")
    public static final boolean doubleRangeContains(@NotNull ClosedRange<Double> closedRange, short s) {
        return closedRange.contains(Double.valueOf(s));
    }

    @NotNull
    public static final CharProgression downTo(char c2, char c3) {
        return CharProgression.INSTANCE.fromClosedRange(c2, c3, -1);
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @JvmName(name = "floatRangeContains")
    public static final boolean floatRangeContains(@NotNull ClosedRange<Float> closedRange, short s) {
        return closedRange.contains(Float.valueOf(s));
    }

    @JvmName(name = "intRangeContains")
    public static final boolean intRangeContains(@NotNull ClosedRange<Integer> closedRange, short s) {
        return closedRange.contains(Integer.valueOf(s));
    }

    @JvmName(name = "longRangeContains")
    public static final boolean longRangeContains(@NotNull ClosedRange<Long> closedRange, short s) {
        return closedRange.contains(Long.valueOf(s));
    }

    @JvmName(name = "shortRangeContains")
    public static final boolean shortRangeContains(@NotNull ClosedRange<Short> closedRange, long j2) {
        Short shortExactOrNull = toShortExactOrNull(j2);
        if (shortExactOrNull != null) {
            return closedRange.contains(shortExactOrNull);
        }
        return false;
    }

    @NotNull
    public static final CharProgression step(@NotNull CharProgression charProgression, int i2) {
        RangesKt__RangesKt.checkStepIsPositive(i2 > 0, Integer.valueOf(i2));
        CharProgression.Companion companion = CharProgression.INSTANCE;
        char first = charProgression.getFirst();
        char last = charProgression.getLast();
        if (charProgression.getStep() <= 0) {
            i2 = -i2;
        }
        return companion.fromClosedRange(first, last, i2);
    }

    @Nullable
    public static final Byte toByteExactOrNull(float f2) {
        float f3 = 127;
        if (f2 < -128 || f2 > f3) {
            return null;
        }
        return Byte.valueOf((byte) f2);
    }

    @NotNull
    public static final  until(char c2, char c3) {
        if (c3 <= 0) {
            return .INSTANCE.getEMPTY();
        }
        return new (c2, (char) (c3 - 1));
    }

    @NotNull
    public static IntProgression downTo(int i2, int i3) {
        return IntProgression.INSTANCE.fromClosedRange(i2, i3, -1);
    }

    @SinceKotlin(version = "1.3")
    public static final long random(@NotNull  Var, @NotNull Random random) {
        try {
            return RandomKt.nextLong(random, Var);
        } catch (IllegalArgumentException e2) {
            throw new NoSuchElementException(e2.getMessage());
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Nullable
    public static final Long randomOrNull(@NotNull  Var, @NotNull Random random) {
        if (Var.isEmpty()) {
            return null;
        }
        return Long.valueOf(RandomKt.nextLong(random, Var));
    }

    public static final byte coerceIn(byte b, byte b2, byte b3) {
        if (b2 <= b3) {
            return b < b2 ? b2 : b > b3 ? b3 : b;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + ((int) b3) + " is less than minimum " + ((int) b2) + OrderISVUtil.MONEY_DECIMAL_CHAR);
    }

    @NotNull
    public static final LongProgression downTo(long j2, int i2) {
        return LongProgression.INSTANCE.fromClosedRange(j2, i2, -1L);
    }

    @NotNull
    public static  until(int i2, int i3) {
        if (i3 <= Integer.MIN_VALUE) {
            return .INSTANCE.getEMPTY();
        }
        return new (i2, i3 - 1);
    }

    public static final short coerceIn(short s, short s2, short s3) {
        if (s2 <= s3) {
            return s < s2 ? s2 : s > s3 ? s3 : s;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + ((int) s3) + " is less than minimum " + ((int) s2) + OrderISVUtil.MONEY_DECIMAL_CHAR);
    }

    @NotNull
    public static final IntProgression downTo(byte b, int i2) {
        return IntProgression.INSTANCE.fromClosedRange(b, i2, -1);
    }

    @SinceKotlin(version = "1.3")
    public static final char random(@NotNull  Var, @NotNull Random random) {
        try {
            return (char) random.nextInt(Var.getFirst(), Var.getLast() + 1);
        } catch (IllegalArgumentException e2) {
            throw new NoSuchElementException(e2.getMessage());
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Nullable
    public static final Character randomOrNull(@NotNull  Var, @NotNull Random random) {
        if (Var.isEmpty()) {
            return null;
        }
        return Character.valueOf((char) random.nextInt(Var.getFirst(), Var.getLast() + 1));
    }

    public static int coerceIn(int i2, int i3, int i4) {
        if (i3 <= i4) {
            return i2 < i3 ? i3 : i2 > i4 ? i4 : i2;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + i4 + " is less than minimum " + i3 + OrderISVUtil.MONEY_DECIMAL_CHAR);
    }

    @NotNull
    public static final IntProgression downTo(short s, int i2) {
        return IntProgression.INSTANCE.fromClosedRange(s, i2, -1);
    }

    @NotNull
    public static final  until(long j2, int i2) {
        return new (j2, i2 - 1);
    }

    public static final long coerceIn(long j2, long j3, long j4) {
        if (j3 <= j4) {
            return j2 < j3 ? j3 : j2 > j4 ? j4 : j2;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + j4 + " is less than minimum " + j3 + OrderISVUtil.MONEY_DECIMAL_CHAR);
    }

    @NotNull
    public static final LongProgression downTo(int i2, long j2) {
        return LongProgression.INSTANCE.fromClosedRange(i2, j2, -1L);
    }

    @NotNull
    public static final  until(byte b, int i2) {
        if (i2 <= Integer.MIN_VALUE) {
            return .INSTANCE.getEMPTY();
        }
        return new (b, i2 - 1);
    }

    public static final float coerceIn(float f2, float f3, float f4) {
        if (f3 <= f4) {
            return f2 < f3 ? f3 : f2 > f4 ? f4 : f2;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + f4 + " is less than minimum " + f3 + OrderISVUtil.MONEY_DECIMAL_CHAR);
    }

    @NotNull
    public static final LongProgression downTo(long j2, long j3) {
        return LongProgression.INSTANCE.fromClosedRange(j2, j3, -1L);
    }

    public static final double coerceIn(double d, double d2, double d3) {
        if (d2 <= d3) {
            return d < d2 ? d2 : d > d3 ? d3 : d;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + d3 + " is less than minimum " + d2 + OrderISVUtil.MONEY_DECIMAL_CHAR);
    }

    @NotNull
    public static final LongProgression downTo(byte b, long j2) {
        return LongProgression.INSTANCE.fromClosedRange(b, j2, -1L);
    }

    @NotNull
    public static final  until(short s, int i2) {
        if (i2 <= Integer.MIN_VALUE) {
            return .INSTANCE.getEMPTY();
        }
        return new (s, i2 - 1);
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T extends Comparable<? super T>> T coerceIn(@NotNull T t, @NotNull ClosedFloatingPointRange<T> closedFloatingPointRange) {
        if (!closedFloatingPointRange.isEmpty()) {
            return (!closedFloatingPointRange.lessThanOrEquals(t, closedFloatingPointRange.getStart()) || closedFloatingPointRange.lessThanOrEquals(closedFloatingPointRange.getStart(), t)) ? (!closedFloatingPointRange.lessThanOrEquals(closedFloatingPointRange.getEndInclusive(), t) || closedFloatingPointRange.lessThanOrEquals(t, closedFloatingPointRange.getEndInclusive())) ? t : closedFloatingPointRange.getEndInclusive() : closedFloatingPointRange.getStart();
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: " + closedFloatingPointRange + OrderISVUtil.MONEY_DECIMAL_CHAR);
    }

    @NotNull
    public static final LongProgression downTo(short s, long j2) {
        return LongProgression.INSTANCE.fromClosedRange(s, j2, -1L);
    }

    @NotNull
    public static final IntProgression downTo(int i2, short s) {
        return IntProgression.INSTANCE.fromClosedRange(i2, s, -1);
    }

    @NotNull
    public static final  until(int i2, long j2) {
        if (j2 <= Long.MIN_VALUE) {
            return .INSTANCE.getEMPTY();
        }
        return new (i2, j2 - 1);
    }

    @NotNull
    public static final LongProgression downTo(long j2, short s) {
        return LongProgression.INSTANCE.fromClosedRange(j2, s, -1L);
    }

    @NotNull
    public static final IntProgression downTo(byte b, short s) {
        return IntProgression.INSTANCE.fromClosedRange(b, s, -1);
    }

    @NotNull
    public static final  until(long j2, long j3) {
        if (j3 <= Long.MIN_VALUE) {
            return .INSTANCE.getEMPTY();
        }
        return new (j2, j3 - 1);
    }

    @NotNull
    public static final <T extends Comparable<? super T>> T coerceIn(@NotNull T t, @NotNull ClosedRange<T> closedRange) {
        if (closedRange instanceof ClosedFloatingPointRange) {
            return (T) coerceIn((Comparable) t, (ClosedFloatingPointRange) closedRange);
        }
        if (!closedRange.isEmpty()) {
            return t.compareTo(closedRange.getStart()) < 0 ? closedRange.getStart() : t.compareTo(closedRange.getEndInclusive()) > 0 ? closedRange.getEndInclusive() : t;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: " + closedRange + OrderISVUtil.MONEY_DECIMAL_CHAR);
    }

    @NotNull
    public static final IntProgression downTo(short s, short s2) {
        return IntProgression.INSTANCE.fromClosedRange(s, s2, -1);
    }

    @NotNull
    public static final  until(byte b, long j2) {
        if (j2 <= Long.MIN_VALUE) {
            return .INSTANCE.getEMPTY();
        }
        return new (b, j2 - 1);
    }

    @NotNull
    public static final  until(short s, long j2) {
        if (j2 <= Long.MIN_VALUE) {
            return .INSTANCE.getEMPTY();
        }
        return new (s, j2 - 1);
    }

    @NotNull
    public static final  until(int i2, short s) {
        return new (i2, s - 1);
    }

    public static final int coerceIn(int i2, @NotNull ClosedRange<Integer> closedRange) {
        if (closedRange instanceof ClosedFloatingPointRange) {
            return ((Number) coerceIn(Integer.valueOf(i2), (ClosedFloatingPointRange<Integer>) closedRange)).intValue();
        }
        if (!closedRange.isEmpty()) {
            return i2 < closedRange.getStart().intValue() ? closedRange.getStart().intValue() : i2 > closedRange.getEndInclusive().intValue() ? closedRange.getEndInclusive().intValue() : i2;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: " + closedRange + OrderISVUtil.MONEY_DECIMAL_CHAR);
    }

    @NotNull
    public static final  until(long j2, short s) {
        return new (j2, s - 1);
    }

    @NotNull
    public static final  until(byte b, short s) {
        return new (b, s - 1);
    }

    @NotNull
    public static final  until(short s, short s2) {
        return new (s, s2 - 1);
    }

    public static final long coerceIn(long j2, @NotNull ClosedRange<Long> closedRange) {
        if (closedRange instanceof ClosedFloatingPointRange) {
            return ((Number) coerceIn(Long.valueOf(j2), (ClosedFloatingPointRange<Long>) closedRange)).longValue();
        }
        if (!closedRange.isEmpty()) {
            return j2 < closedRange.getStart().longValue() ? closedRange.getStart().longValue() : j2 > closedRange.getEndInclusive().longValue() ? closedRange.getEndInclusive().longValue() : j2;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: " + closedRange + OrderISVUtil.MONEY_DECIMAL_CHAR);
    }
}
