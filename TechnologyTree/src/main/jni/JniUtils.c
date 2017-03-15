//// Create xxxxx

#include "com_wobiancao_ndkjnidemo_ndk_JniUtils.h"
#include <string.h>

/*
 * Class:     com_project_onepice_basicproject_ndkExample_JniUtils
 * Method:    getStringFormC
 * Signature: ()Ljava/lang/String;

 */

 JNIEXPORT jstring JNICALL Java_com_project_onepice_basicproject_ndkExample_JniUtils_getStringFormC(JNIEnv *env,jobject obj){
 return (*env)->NewStringUTF(env,"这里是来自c的string");
 }