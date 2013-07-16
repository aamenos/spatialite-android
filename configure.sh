#!/bin/sh
git clone https://code.google.com/p/spatialite-android/ 
#In the JNI directory download, unpack, and configure GEOS and PROJ.4

cd spatialite-android/spatialite-android-library/jni/

wget http://download.osgeo.org/proj/proj-4.8.0.tar.gz 
wget http://download.osgeo.org/geos/geos-3.3.6.tar.bz2 
wget http://www.sqlite.org/2013/sqlite-amalgamation-3071602.zip
wget http://www.gaia-gis.it/gaia-sins/libspatialite-sources/libspatialite-4.0.0.tar.gz 


tar -xvzf proj-4.8.0.tar.gz 
tar -xvjf geos-3.3.6.tar.bz2 
unzip sqlite-amalgamation-3071602.zip
tar -xvzf libspatialite-4.0.0.tar.gz

cd proj-4.8.0/ 
./configure --build=x86_64-pc-linux-gnu --host=arm-linux-eabi 
cd .. 
cd geos-3.3.6 
./configure --build=x86_64-pc-linux-gnu --host=arm-linux-eabi 
#http://www.gaia-gis.it/gaia-sins/spatialite-android/spatialite-android-build-steps.txt
# STEP 11.
#    Add this new line anywhere:
#    #define HAVE_ISNAN 1
vi include/geos/platform.h
cd ..



cd libspatialite-4.0.0/ 
#http://www.linuxquestions.org/questions/slackware-14/problems-building-spatialite-943115/
#spatialite-android/spatialite-android-library/jni/libspatialite-4.0.0/README
./configure --build=x86_64-pc-linux-gnu --host=arm-linux-eabi --enable-freexl=no CFLAGS=-ldl
cd .. 

android_sdk_path=~/Downloads/adt-bundle-linux-x86-20130522/sdk
android_ndk_path=~/Downloads/android-ndk-r8e

export PATH=$PATH:$android_sdk_path/platform-tools:$PATH:$android_sdk_path/tools:$android_ndk_path


ndk-build -j10
