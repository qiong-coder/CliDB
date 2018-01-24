package com.buaa.CliDB.utils;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;

public class ImageCompressUtils {

    static public boolean compress(String input, File output, int width, int height, float quality) {

        try {
            Thumbnails.of(input)
                    .height(height).width(width)
                    .outputQuality(quality)
                    .outputFormat("jpg")
                    .toFile(output);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
