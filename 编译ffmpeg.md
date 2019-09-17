# 编译ffmpeg
---

## 下载
1. ffmpeg
https://ffmpeg.org/releases/ffmpeg-3.4.6.tar.gz
2. lame
https://jaist.dl.sourceforge.net/project/lame/lame/3.100/lame-3.100.tar.gz
3. fdk-aac
https://jaist.dl.sourceforge.net/project/opencore-amr/fdk-aac/fdk-aac-0.1.6.tar.gz
---

## 解压缩
```shell
tar xzvf ffmpeg-3.4.6.tar.gz
tar xzvf lame-3.100.tar.gz
tar xzvf fdk-aac-0.1.6.tar.gz
```
* 确保解压缩后得到的"ffmpeg-3.4.6",“lame-3.100”和"fdk-aac-0.1.6"，位于同一路径目录下。
---

## 编译
#### 1.编译fdk-aac
```shell
cd fdk-aac-0.1.6
./configure --prefix=$(pwd)/../output --disable-shared CFLAGS="-fPIC" CXXFLAGS="-fPIC"
make
make install
```
编译后的库和头文件在与"fdk-aac-0.1.6"同级目录中的output目录中
#### 2.编译lame-3.100
```shell
cd lame-3.100
./configure --prefix=$(pwd)/../output --disable-shared CFLAGS="-fPIC"
make
make install
```
编译后的库和头文件在与"lame-3.100"同级目录中的output目录中
#### 3.编译ffmpeg
```sh
cd ffmpeg-3.4.6
./configure --prefix=$(pwd)/../output --disable-static --enable-shared \
    --disable-gpl --disable-nonfree \
    --enable-runtime-cpudetect \
    --disable-autodetect --disable-iconv \
    --disable-doc --disable-programs \
    --disable-avdevice --disable-postproc --disable-hwaccels \
    --disable-swscale --disable-avfilter \
    --enable-network \
    --enable-libfdk-aac --enable-libmp3lame --disable-encoder=aac \
    --extra-cflags="-I$(pwd)/../output/include -fPIC" \
    --extra-ldflags="-lm -L$(pwd)/../output/lib"
make
make install
```
编译后的库和头文件在与"ffmpeg-3.4.6"同级目录中的output目录中。
* disable-encoder=aac禁用的是ffmpeg自带的aac编码器，由于我们使用fdk-aac进行编码，禁用内置aac编码器后，将会默认使用fdk-aac
* ffmpeg没有内置mp3编码器，编译带lame版本后，编码会默认使用lame

---
## 编译后的使用说明
1. 此方法中fdk-aac和lame采用的是静态编译，编译结果为静态库。
2. ffmpeg编译采用动态编译，编译结果为动态库。
3. 由于编译ffmpeg动态库会link其他2个工程的静态库，所以在进行二次开发时，无需引用另外2个库。
4. 如果修改编译配置，ffmpeg也采用静态编译的话，则进行二次开发时，需要引用另外2个库。
5. 如果fdk-aac和lame采用动态编译的方式，则3个工程在二次开发时，需要全部引用。
* 推荐ffmpeg采用动态编译的方法，而ffmpeg调用的其他工程使用静态编译；这样ffmpeg动态库生成时会link所有的静态库，进行二次开发时，只需要引用ffmpeg即可。