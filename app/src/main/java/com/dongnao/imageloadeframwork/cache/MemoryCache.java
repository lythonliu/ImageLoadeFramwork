package com.dongnao.imageloadeframwork.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.dongnao.imageloadeframwork.request.BitmapRequest;

/**
 * Created by Administrator on 2017/2/6 0006.
 */

public class MemoryCache implements  BitmapCache {
    private LruCache<String,Bitmap> mLruCache;
    public MemoryCache()
    {
        int maxSize= (int) (Runtime.getRuntime().freeMemory()/1024/8);
        mLruCache=new LruCache<String,Bitmap>(maxSize)
        {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight();
            }
        };
    }
    @Override
    public void put(BitmapRequest request, Bitmap bitmap) {
        mLruCache.put(request.getImageUriMD5(),bitmap);
    }

    @Override
    public Bitmap get(BitmapRequest request) {
        return mLruCache.get(request.getImageUriMD5());
    }

    @Override
    public void remove(BitmapRequest request) {
         mLruCache.remove(request.getImageUriMD5());
    }
}
