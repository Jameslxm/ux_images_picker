package com.chavesgu.images_picker;

import com.luck.picture.lib.animators.AnimationType;
import com.luck.picture.lib.basic.PictureSelectionCameraModel;
import com.luck.picture.lib.basic.PictureSelectionModel;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.config.SelectModeConfig;
import com.luck.picture.lib.language.LanguageConfig;

public class Utils {
    public static PictureSelectionCameraModel setPhotoSelectOpt(PictureSelectionCameraModel model, int count, double quality) {
        model
//                .setCropEngine(new ImageFileCropEngine())
                .setCompressEngine(new ImageFileCompressEngine())
                .isOriginalControl(false)
                .setCameraImageFormat(PictureMimeType.JPEG)
                .setCameraVideoFormat(PictureMimeType.MP4)
        ;
        return model;
    }
    public static PictureSelectionModel setPhotoSelectOpt(PictureSelectionModel model, int count, double quality) {
        model.setImageEngine(GlideEngine.createGlideEngine())
//                .setCropEngine(new ImageFileCropEngine())
                .setCompressEngine(new ImageFileCompressEngine())
                .setMaxSelectNum(count)
                .setMinSelectNum(1)
                .setMaxVideoSelectNum(count)
                .setMinVideoSelectNum(1)
                .setSelectionMode(count > 1 ? SelectModeConfig.MULTIPLE : SelectModeConfig.SINGLE)
                .isDirectReturnSingle(true)
                .isDisplayCamera(false)
                .isSelectZoomAnim(true)
                .isEmptyResultReturn(false)
                .isOriginalControl(false)
                .isMaxSelectEnabledMask(true)
                .setCameraImageFormat(PictureMimeType.JPEG)
                .setCameraVideoFormat(PictureMimeType.MP4)
                .setRecyclerAnimationMode(AnimationType.DEFAULT_ANIMATION);
        return model;
    }

    public static PictureSelectionCameraModel setLanguage(PictureSelectionCameraModel model, String language) {
        switch (language) {
            case "Language.Chinese":
                model.setLanguage(LanguageConfig.CHINESE);
                break;
            case "Language.ChineseTraditional":
                model.setLanguage(LanguageConfig.TRADITIONAL_CHINESE);
                break;
            case "Language.English":
                model.setLanguage(LanguageConfig.ENGLISH);
                break;
            case "Language.Japanese":
                model.setLanguage(LanguageConfig.JAPAN);
                break;
            case "Language.French":
                model.setLanguage(LanguageConfig.FRANCE);
                break;
            case "Language.Korean":
                model.setLanguage(LanguageConfig.KOREA);
                break;
            case "Language.German":
                model.setLanguage(LanguageConfig.GERMANY);
                break;
            case "Language.Vietnamese":
                model.setLanguage(LanguageConfig.VIETNAM);
                break;
            default:
                model.setLanguage(-1);
        }
        return model;
    }
    public static PictureSelectionModel setLanguage(PictureSelectionModel model, String language) {
        switch (language) {
            case "Language.Chinese":
                model.setLanguage(LanguageConfig.CHINESE);
                break;
            case "Language.ChineseTraditional":
                model.setLanguage(LanguageConfig.TRADITIONAL_CHINESE);
                break;
            case "Language.English":
                model.setLanguage(LanguageConfig.ENGLISH);
                break;
            case "Language.Japanese":
                model.setLanguage(LanguageConfig.JAPAN);
                break;
            case "Language.French":
                model.setLanguage(LanguageConfig.FRANCE);
                break;
            case "Language.Korean":
                model.setLanguage(LanguageConfig.KOREA);
                break;
            case "Language.German":
                model.setLanguage(LanguageConfig.GERMANY);
                break;
            case "Language.Vietnamese":
                model.setLanguage(LanguageConfig.VIETNAM);
                break;
            default:
                model.setLanguage(-1);
        }
        return model;
    }
}
