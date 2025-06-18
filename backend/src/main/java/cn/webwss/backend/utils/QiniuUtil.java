package cn.webwss.backend.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class QiniuUtil {

    //访问授权码
    @Value("${qn.accessKey}")
    private String accessKey;
    //秘密钥匙
    @Value("${qn.secretKey}")
    private String secretKey;
    //空间名称
    @Value("${qn.bucket}")
    private String bucket;
    //外链域名
    @Value("${qn.domain}")
    private String domain;
    //路径
    @Value("${qn.path}")
    private String P;


    //文件上传 通过上传文件的方式上传到存储空间
    public  String uploadFile(byte[] bytes, String fileName, String path){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);

        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = P + path + fileName;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(bytes, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            //返回文件完整路径
            return domain+"/"+putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
                return "";
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return "";
    }


    //删除文件 参数：存储的图片文件名
    public  void deleteFile(String fileName,String path){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        String key = P +  path + fileName;
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }


}
