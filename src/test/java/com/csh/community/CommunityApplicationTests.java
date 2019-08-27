package com.csh.community;

import com.aliyun.oss.OSSClient;
//import com.csh.community.provider.AliCloudProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommunityApplicationTests {
    private String bucketName = "pic-chenhang1";
    @Test
    public void contextLoads() {
//        // Endpoint以杭州为例，其它Region请按实际情况填写。
//        String endpoint = "oss-cn-beijing.aliyuncs.com";
//        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
//        String accessKeyId = "LTAIXiUwCnhQimw4";
//        String accessKeySecret = "vQ2MuWFGEBNrNeD57mgiluxbSEMOkW";
//
//        // 创建OSSClient实例。
//        OSSClient ossClient = new OSSClient(endpoint,accessKeyId,accessKeySecret);
//        try {
//            InputStream inputStream = new FileInputStream(new File("C:\\Users\\chenshihang\\Desktop\\touxiang.jpg"));
//            AliCloudProvider.uploadByInputStream(ossClient, inputStream, bucketName, "test/b.jpg");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


 }

}
