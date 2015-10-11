#include <jni.h>
#include <stdlib.h>
#include <stdio.h>
#include <android/log.h>

#ifdef __cplusplus
extern "C"
{
#endif

#define   LOG_TAG    "JniLib"
#define   LOG_I(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)
#define   LOG_E(...)  __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)

/*
 * Class:     com_sy_testapp_jni_JniLib
 * Method:    userLogin
 * Signature: (Ljava/lang/String;Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_com_sy_testapp_jni_JniLib_userLogin
  (JNIEnv *env, jclass cls, jstring name, jstring pwd)
{
	// 映射Java类
//	jclass JniLibCls = (*env)->FindClass(env,"com/sy/testapp/jni/JniLib");
//	LOG_I("get cls.");
	// 不传参的默认构造函数
	// jmethodID cons_id = (*env)->GetMethodID(env, JniLibCls, "<init>", "()V");
	// LOG_I("get cons_id.");
	// 通过NewObject创建对象
	// jobject mJniLib = (*env)->NewObject(env, JniLibCls, cons_id);
//	LOG_I("get obj.");
	// 映射Java类非静态方法
//	jmethodID callBack_id = (*env)->GetMethodID(env, cls, "CCallBack", "(III)V");
	// 映射Java类静态方法
	jmethodID callBack_id = (*env)->GetStaticMethodID(env, cls, "CCallBack", "(III)V");
	LOG_I("get method_id.");
	// 调用Java类方法
//	(*env)->CallStaticVoidMethod(env, JniLibCls, callBack_id, 111, 222, 333);
	// 调用Java类静态方法
	(*env)->CallStaticVoidMethod(env, cls, callBack_id, 111, 222, 333);
	LOG_I("call done.");
}

#ifdef __cplusplus
}
#endif
