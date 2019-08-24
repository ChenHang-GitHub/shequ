package com.csh.community.provider;


import com.alibaba.fastjson.JSON;
import com.csh.community.dto.AccessTokenDTO;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class GitHubPro {

    Logger logger =  LoggerFactory.getLogger(GitHubPro.class);
    public String getAccessToken(AccessTokenDTO accessTokenDTO) throws  Exception{
        final MediaType mediaType
                = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {

            String string = response.body().string();
            String[] split= string.split("&");
            String  tokenString  = split[0];
            String token = tokenString.split("=")[1];
            logger.info(token+"...");
            return  token;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return  null;
    }



    public GitHubUser getGitHubUser (String accessToken) throws  Exception
    {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken).build();

        try (Response response = client.newCall(request).execute()) {

            String string = response.body().string();
            GitHubUser gitHubUser = JSON.parseObject(string, GitHubUser.class);
            if(gitHubUser.getName()==null || gitHubUser.getName().equals(""))
            {
                gitHubUser.setName("You need GitHub Name");
            }
            return gitHubUser;
        }

    }
}


