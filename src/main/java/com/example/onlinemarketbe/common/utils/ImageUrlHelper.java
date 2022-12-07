package com.example.onlinemarketbe.common.utils;

public class ImageUrlHelper {
    public static String getFilenameFromUrl(String url){
        if(url == null || url.isEmpty()) throw new RuntimeException("Image url was null");
        var splits = url.split("/");
        return splits[splits.length-1];
    }
}
