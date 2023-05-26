package com.example.utils;

import com.google.gson.Gson;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

public class QiniuUtil {
    private static final String ACCESS_KEY = "Y29YcvXWUe4k3Su0A63ziWxe86XTHLbcgx0-51Ke";
    private static final String SECRET_KEY = "qjQcw6AbtcsX1LqcAu1E1bUBUMT3L9Yt1S2Ysxo6";
    private static final String BUCKET_NAME = "xiaoli2023";
    private static final String DOMAIN = "ru83u0iyp.hn-bkt.clouddn.com";
    // 上传图片并返回图片名称
    public static String uploadImage( byte[] data,String imageName) throws Exception {
        Configuration cfg = new Configuration(Region.autoRegion());
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        StringMap putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"width\":\"$(imageInfo.width)\",\"height\":\"$(imageInfo.height)\"}");
        String upToken = auth.uploadToken(BUCKET_NAME, null, 3600, putPolicy);

        Response response = uploadManager.put(data, imageName, upToken);
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        return putRet.key;
    }

    // 根据图片名称获取图片URL
    public static String getImageUrl(String imageName) {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth, new Configuration());
        try {
            FileInfo fileInfo = bucketManager.stat(BUCKET_NAME, imageName);
            return "http://" + DOMAIN + "/" + imageName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
