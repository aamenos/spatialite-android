LOCAL_PATH := $(call my-dir)

JSQLITE_PATH := javasqlite-20120209
SPATIALITE_PATH := libspatialite-4.0.0
GEOS_PATH := geos-3.3.6
PROJ4_PATH := proj-4.8.0
SQLITE_PATH := sqlite-amalgamation-3071602

include $(LOCAL_PATH)/sqlite.mk
include $(LOCAL_PATH)/proj4.mk
include $(LOCAL_PATH)/geos-3.3.6.mk
include $(LOCAL_PATH)/spatialite.mk
include $(LOCAL_PATH)/jsqlite.mk
