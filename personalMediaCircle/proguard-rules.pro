-optimizationpasses 5 #指定代码的压缩级别
-dontusemixedcaseclassnames #是否使用大小写混合
-dontskipnonpubliclibraryclasses #是否混淆第三方jar
-dontskipnonpubliclibraryclassmembers  #混淆时是否做预校验
-dontoptimize
-dontobfuscate
-dontpreverify
-verbose #混淆时是否记录日志
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/* #混淆时所采用的算法

#腾讯互动直播
-keep class com.tencent.**{*;}
-dontwarn com.tencent.**
-keep class tencent.**{*;}
-dontwarn tencent.**
-keep class qalsdk.**{*;}
-dontwarn qalsdk.**
#小米推送
-dontwarn com.xiaomi.push.**
-keep class com.hudong.wemedia.ui.activity.im.MiPushMessageReceiver {*;}
#华为推送
-keep class com.huawei.android.pushagent.**{*;}
-keep class com.huawei.android.pushselfshow.**{*;}
-keep class com.huawei.android.microkernel.**{*;}
-keep class com.baidu.mapapi.**{*;}
-keep class com.huawei.** { *; }
-dontwarn com.huawei.**
#极光推送
-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }
#蓝牙
-dontwarn com.minew.device.**
-keep class com.minew.device.** { *; }
#async-http
#-keep class com.loopj.android.http.** { *; }
#-keepclassmembers class * {
#   public <methods>;
#}
#微信相关
-keep class com.tencent.mm.sdk.openapi.WXMediaMessage {*;}
-keep class com.tencent.mm.sdk.openapi.** implements com.tencent.mm.sdk.openapi.WXMediaMessage$IMediaObject {*;}
#fastjson
-keep public class * implements java.io.Serializable {
        public *;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.** { *; }
-keepclassmembers class * {
public <methods>;
}
#gson
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }
-keep class com.google.gson.** { *;}
#Android-support-v4
-dontwarn android.support.v4.**
-dontwarn android.support.**
-keep class android.support.v4.** { *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment
#Android-support-v7
-dontwarn android.support.v7.**
-keep class android.support.v7.** { *; }
-keep interface android.support.v7.** { *; }
#Android基本类
-keep public class * extends android.view.View
#eventbus
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(Java.lang.Throwable);
}
#zhixin
-keep class net.sourceforge.zbar.{*;}
-dontwarn cn.bingoogolapple.**
-keep class cn.bingoogolapple.*{*;}
#ButterKnife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-dontwarn butterknife.compiler.**
-dontwarn com.tencent.callsdk.**
-keep class com.tencent.callsdk.** { *; }
-dontwarn com.bigkoo.convenientbanner.**
-keep class com.bigkoo.convenientbanner.** { *; }
-keepclasseswithmembernames class * {
   @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
 @butterknife.* <methods>;
}
#google
-keep class com.google.auto.common.** { *; }
-keep class com.google.auto.service.** { *; }
-dontwarn auto-common-0.6.jar
-dontwarn com.google.auto.value.**
-dontnote com.google.common.util.concurrent.**
-dontwarn com.google.common.**
-dontnote com.google.vending.licensing.ILicensingService
-dontnote com.android.vending.licensing.ILicensingService
-dontwarn com.google.auto.**
#javapoet
-keep class com.squareup.javapoet.**{*;}
-dontwarn com.squareup.javapoet.**
#javax
-dontwarn javax.lang.**
-dontwarn javax.tools.**
-dontwarn autovalue.shaded.com.**
-dontwarn javax.annotation.**
# autovalue gson extension
-keep class **.AutoParcelGson_*
-keepnames @auto.parcelgson.AutoParcelGson class *

-dontwarn autovalue.shaded.**

-keepclassmembers class **.R$* {
    public static <fields>;
}
-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keep class * implements android.os.Parcelable {
    *;
}
-keepclassmembers class * implements android.os.Parcelable {
    static ** CREATOR;
}
-keep,includedescriptorclasses class retrofit.** { *; }
-keep,includedescriptorclasses class android.support.** { *; }
-keep,includedescriptorclasses class com.felipecsl.**
-keep,includedescriptorclasses class com.squareup.moshi.** { *; }

-keepclassmembers class ** { ** base; }
-keepclassmembers class ** { ** value; }
-keepclassmembers class ** { ** icon; }
-keepclassmembers class ** { ** title; }
-keepclassmembers class ** { ** theUnsafe; }
-keepclassmembers class ** { ** busy; }
-keepclassmembers class ** { ** actionIntent; }
-keepclassmembers class ** { ** SDK_INT; }

#微信
-keep class com.tencent.mm.opensdk.** {*;}
-keep class com.tencent.wxop.** { *;}
-keep class com.tencent.mm.sdk.** {*;}

#rxjava
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
 long producerIndex;
 long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode consumerNode;
}



