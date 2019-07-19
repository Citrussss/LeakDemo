//
// Created by 29283 on 2019/7/10.
//

#include "native.h"
#include <jni.h>
#include <string>

extern "C"
JNIEXPORT j string

JNICALL
Java_gebilaolitou_ndkdemo_NDKTools_getStringFromNDK(
        JNIEnv *env, jobject /* this */) {
    std::string hello = "(*^__^*) 嘻嘻……~Hello from C++ 隔壁老李头";
    return env->NewStringUTF(hello.c_str());
}