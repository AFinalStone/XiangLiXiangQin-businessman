package com.wei.xianglixiangqin.util;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/7/13 15:57
 */
public class ImagerLoaderUtil {

    private static ImagerLoaderUtil imagerLoaderUtil;
    private Context context;

    private ImagerLoaderUtil(Context context) {
        super();
        this.context = context;
    }

    public synchronized static ImagerLoaderUtil getInstance(Context context){
        if(imagerLoaderUtil == null){
            imagerLoaderUtil = new ImagerLoaderUtil(context);
        }
        return imagerLoaderUtil;
    }

    public void displayImage(String imageUrl, ImageView imageView){
    }
}
