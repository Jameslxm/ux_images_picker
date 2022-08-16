package com.chavesgu.images_picker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.engine.CropFileEngine;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropImageEngine;

import java.io.File;
import java.util.ArrayList;

/**
 * Desc
 */
public class ImageFileCropEngine implements CropFileEngine {

    @Override
    public void onStartCrop(Fragment fragment, Uri srcUri, Uri destinationUri, ArrayList<String> dataSource, int requestCode) {
        FragmentActivity activity = fragment.getActivity();
        if(activity == null) return;
        UCrop.Options options = buildOptions(activity);
        UCrop uCrop = UCrop.of(srcUri, destinationUri, dataSource);
        uCrop.withOptions(options);
        uCrop.setImageEngine(new UCropImageEngine() {
            @Override
            public void loadImage(Context context, String url, ImageView imageView) {
                if (!ImageLoaderUtils.assertValidRequest(context)) {
                    return;
                }
                Glide.with(context).load(url).override(180, 180).into(imageView);
            }

            @Override
            public void loadImage(Context context, Uri url, int maxWidth, int maxHeight, OnCallbackListener<Bitmap> call) {
                if (!ImageLoaderUtils.assertValidRequest(context)) {
                    return;
                }
                Glide.with(context).asBitmap().override(maxWidth, maxHeight).load(url).into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        if (call != null) {
                            call.onCall(resource);
                        }
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        if (call != null) {
                            call.onCall(null);
                        }
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });
            }
        });
        uCrop.start(activity, fragment, requestCode);
    }

    /**
     * 配制UCrop，可根据需求自我扩展
     * @param activity
     */
    private UCrop.Options buildOptions(FragmentActivity activity) {
        UCrop.Options options = new UCrop.Options();
        options.setHideBottomControls(true);
        options.setFreeStyleCropEnabled(false);
        options.setShowCropFrame(true);
        options.setShowCropGrid(true);
        options.setCircleDimmedLayer(false);
        options.setCropOutputPathDir(getSandboxPath(activity));
        options.isCropDragSmoothToCenter(false);
        options.isUseCustomLoaderBitmap(false);
        options.setSkipCropMimeType(PictureMimeType.ofGIF(), PictureMimeType.ofWEBP());
        options.isForbidCropGifWebp(true);
        options.isForbidSkipMultipleCrop(false);
        options.setMaxScaleMultiplier(100);
        options.setStatusBarColor(ContextCompat.getColor(activity, R.color.ps_color_grey));
        options.setToolbarColor(ContextCompat.getColor(activity, R.color.ps_color_grey));
        options.setToolbarWidgetColor(ContextCompat.getColor(activity, R.color.ps_color_white));
        options.withAspectRatio(1, 1);
        return options;
    }
    /**
     * 创建自定义输出目录
     * @param activity
     */
    private String getSandboxPath(FragmentActivity activity) {
        File externalFilesDir = activity.getExternalFilesDir("");
        File customFile = new File(externalFilesDir.getAbsolutePath(), "Sandbox");
        if (!customFile.exists()) {
            customFile.mkdirs();
        }
        return customFile.getAbsolutePath() + File.separator;
    }
}