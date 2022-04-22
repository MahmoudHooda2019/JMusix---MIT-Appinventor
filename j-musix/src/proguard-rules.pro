# Add any ProGuard configurations specific to this
# extension here.

-keep public class com.mh.jmusix.JMusix {
    public *;
 }
-keeppackagenames gnu.kawa**, gnu.expr**

-optimizationpasses 4
-allowaccessmodification
-mergeinterfacesaggressively

-repackageclasses 'com/mh/jmusix/repack'
-flattenpackagehierarchy
-dontpreverify
