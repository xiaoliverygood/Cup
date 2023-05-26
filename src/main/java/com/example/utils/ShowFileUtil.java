package com.example.utils;
import org.springframework.stereotype.Component;

@Component
public class ShowFileUtil {
    public String getPhotoByName(String name) {
      return QiniuUtil.getImageUrl(name);
    }

}
