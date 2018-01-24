package com.buaa.CliDB.utils;

import net.coobird.thumbnailator.Thumbnails;

public class ImageCompressUtils {

    static public void compress(String input_images, String output_images, int width, int height, float quality) {

        try {
            Thumbnails.of(input_images)
                    .height(height).width(width)
                    .outputQuality(quality)
                    .determineOutputFormat()
                    .toFile(output_images);
        } catch (Exception e) {
            return;
        }

    }

}
