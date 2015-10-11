LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := CLibModule
LOCAL_SRC_FILES := CLib.c
LOCAL_LDLIBS 	+= -L$(SYSROOT)/usr/lib -llog	# 打印log需要

include $(BUILD_SHARED_LIBRARY)
