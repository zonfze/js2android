package com.viglle.carmanual.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.viglle.carmanual.R;

import java.io.File;

/**
 * Created by Administrator on 2016/4/18.
 *
 * "http://site.com/image.png" // from Web 从网络获取
 * "file:///mnt/sdcard/image.png" // from SD card 从SD卡获取
 * "file:///mnt/sdcard/video.mp4" // from SD card (video thumbnail)
 * "content://media/external/images/media/13"//from content provider 从内容提供器获取
 * "content://media/external/video/media/13" // from content provider (video thumbnail)
 * "assets://image.png" // from assets 从assets目录获取
 * "drawable://" + R.drawable.img // from drawables (non-9patch images)
 *
 */
public class ImageHelper{
    public static ImageHelper helper;
    private ImageHelper(){}
    public static ImageHelper getInstance(){
        if(helper==null){
            helper=new ImageHelper();
        }
        return helper;
    }

    protected static ImageLoader imageLoader=ImageLoader.getInstance();

    public static void initImageHelper(Context cxt){
        if(imageLoader.isInited()){
            return;
        }
        //ImageLoaderConfiguration  所有的选项都是可选的，只选择你真正想制定的去配置
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(cxt)
                // 如果图片尺寸大于了这个参数，那么就会这按照这个参数对图片大小进行限制并缓存
                .memoryCacheExtraOptions(480, 800)
                .taskExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
                .taskExecutorForCachedImages(AsyncTask.THREAD_POOL_EXECUTOR)
                .threadPoolSize(5)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .threadPriority(Thread.NORM_PRIORITY - 1)
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .diskCache(new UnlimitedDiskCache(new File(FileUtil.getChacheImageDir())))
                .diskCacheSize(100 * 1024 * 1024)
                .diskCacheFileCount(200)
                .writeDebugLogs()
                .build();
        imageLoader.init(config);
    }

    public void displayImage(Context ctx,String url,ImageView imageView){
//        DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.ic_stub) // 设置图片下载期间显示的图片
//                .showImageForEmptyUri(R.drawable.ic_empty) // 设置图片Uri为空或是错误的时候显示的图片
//                .showImageOnFail(R.drawable.ic_error) // 设置图片加载或解码过程中发生错误显示的图片
                .resetViewBeforeLoading(false)  // default 设置图片在加载前是否重置、复位
                .delayBeforeLoading(1000)  // 下载前的延迟时间
                .cacheInMemory(false) // default  设置下载的图片是否缓存在内存中
                .cacheOnDisk(true) // default  设置下载的图片是否缓存在SD卡中
                .considerExifParams(false) // default
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default 设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.ARGB_8888) // default 设置图片的解码类型
//                .decodingOptions(...)  // 图片的解码设置
                .displayer(new SimpleBitmapDisplayer()) // default  还可以设置圆角图片new RoundedBitmapDisplayer(20)
                .handler(new Handler()) // default
                .build();
//        DisplayImageOptions options = builder.build();
//        BitmapFactory.Options opt = options.getDecodingOptions();
//        opt.inSampleSize=2;
        if(!imageLoader.isInited()){
            initImageHelper(ctx.getApplicationContext());
        }
        imageLoader.displayImage(url, imageView, options);
    }

    public void loadImage(Context ctx,final String url,final ImageView imageView,final DownloadImageListener listener){
        if(!imageLoader.isInited()){
            initImageHelper(ctx.getApplicationContext());
        }
      AsyncTask<String,Integer,Bitmap> asyncTask=new AsyncTask<String, Integer, Bitmap>() {
          @Override
          protected Bitmap doInBackground(String... params) {
              return imageLoader.loadImageSync(url);
          }

          @Override
          protected void onPostExecute(Bitmap bitmap) {
              if(bitmap!=null){
                  listener.loadImage(imageView,true,100,bitmap);
              }else{
                  listener.loadImage(imageView,false,100,bitmap);
              }
          }

          @Override
          protected void onProgressUpdate(Integer... values) {
              listener.loadImage(imageView,false,values[0],null);
          }
      };
    }

    DownloadImageListener downloadImageListener;


    public interface DownloadImageListener{
        /**
         *
         * @param status 下载状态;成功或则失败
         * @param progress 下载进度
         * @param bitmap 图片;下载完成时 progress=100;bitmap不为空;否则bitmap为空
         */
        void loadImage(ImageView view,boolean status,int progress,Bitmap bitmap);
    }
}
