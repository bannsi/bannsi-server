package com.bannsi.accountservice.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class KakaoService {
    private static final Logger logger = LoggerFactory.getLogger(KakaoService.class);
    private static final String APIKEY = "057a07ccce2a54f042f9b62e61d4457f";
    public String getAccessToken(String authCode) throws IOException {
        String accessToken = new String();
        String refreshToken = new String();
        String tokenURL = "https://kauth.kakao.com/oauth/token";
        logger.info("get access token");
        try{
            URL url = new URL(tokenURL);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            // conn.setRequestProperty("Accept", "application/json");
            // conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
            conn.setDoOutput(true);

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("grant_type=authorization_code");
            stringBuilder.append("&client_id="+APIKEY);
            stringBuilder.append("&redirect_uri=http://localhost:5555/accounts/v1/kakao/login/");
            stringBuilder.append("&code=" + authCode);
            bufferedWriter.write(stringBuilder.toString());           
            bufferedWriter.flush();

            if (conn.getResponseCode() != 200) {
                logger.info("get kakao access token failed: " + String.valueOf(conn.getResponseCode()));
                logger.info(conn.getResponseMessage());
                throw new IOException();
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = new String();
            String result = new String();

            while((line = bufferedReader.readLine()) != null){
                result += line;
            }
            logger.info(result);
            JsonObject jsonObject = (JsonObject)JsonParser.parseString(result);
            // JsonElement jsonElement = JsonParser.parseString(result);

            // accessToken = jsonElement.getAsJsonObject().get("access_token").getAsString();
            // refreshToken = jsonElement.getAsJsonObject().get("refresh_token").getAsString();
            accessToken = jsonObject.get("access_token").getAsString();
            bufferedReader.close();
            bufferedWriter.close();
        } catch(IOException e) {
            logger.info(e.getMessage());
            e.printStackTrace();
        }
        return accessToken;
    }

    public HashMap<String, Object> getUserInfo(String accessToken) throws IOException {
        HashMap<String, Object> userInfo = new HashMap<String, Object>();
        String userInfoURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(userInfoURL);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);
            int responseStatus = conn.getResponseCode();
            if(responseStatus != 200){
                throw new IOException();
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = new String();
            String result = new String();
            
            while((line = bufferedReader.readLine())!= null) {
                result += line;
            }
            logger.info("result: " + result);
            // JsonElement jsonElement = JsonParser.parseString(result);
            JsonObject jsonObject = (JsonObject)JsonParser.parseString(result);

            String kakaoUserId = jsonObject.get("id").getAsString();
            String nickname = jsonObject.get("kakao_account").getAsJsonObject().get("profile").getAsJsonObject().get("nickname").getAsString();

            logger.info("id: " + kakaoUserId + ", nickname: " + nickname);
            userInfo.put("accessToken", accessToken);
            userInfo.put("userId", kakaoUserId);
            userInfo.put("nickname", nickname);
        } catch(IOException e){
            e.printStackTrace();;
        }
        return userInfo;
    }

    public String getAuthCode(){
        String authCodeURL = "https://kauth.kaako.com/oauth/authorize";
        String authCode = new String();
        try{
            URL url = new URL(authCodeURL);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("client_id="+APIKEY);
            stringBuilder.append("&redirect-uri=http://localhost:8080/account/kakaoLogin");
            stringBuilder.append("&response_type=code");

            if(conn.getResponseCode() != 200){
                logger.debug("get kakao authorization code failed: " + String.valueOf(conn.getResponseCode()));
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = new String();
            String result = new String();

            while((line = bufferedReader.readLine()) != null){
                result += line;
            }
            JsonElement jsonElement = JsonParser.parseString(result);

            authCode = jsonElement.getAsJsonObject().get("code").getAsString();

            bufferedReader.close();
            bufferedWriter.close();
        } catch(IOException e){
            e.printStackTrace();
        }
        return authCode;
    }
}
