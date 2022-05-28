# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class com.play.together.library.model.** { *; }

#################### 3rd sdk ####################
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule {
 <init>(...);
}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
  *** rewind();
}

-keepnames class com.google.android.gms.** { *; }
-keepnames class com.google.firebase.** { *; }

-keep class * extends com.san.ads.base.SANBaseAd { *; }

-keep class com.google.android.gms.ads.** { public *; }

-keep public class * implements java.io.Serializable { *; }

#################################### WMRouter Begin ########################################
-keep class com.sankuai.waimai.router.generated.ServiceLoaderInit { *; }
-keep @interface com.sankuai.waimai.router.annotation.RouterService
-keepclassmembers @com.sankuai.waimai.router.annotation.RouterService class * { *; }
#################################### WMRouter End ########################################