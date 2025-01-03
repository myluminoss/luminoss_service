package com.ruoyi.system.service;


import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.system.domain.dto.TwitterTweetsRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;


@Service
public class TwitterService {

    @Value("${twitter.consumerKey}")
    private String consumerKey;

    @Value("${twitter.consumerSecret}")
    private String consumerSecret;

    @Value("${twitter.accessToken}")
    private String accessToken;

    @Value("${twitter.accessTokenSecret}")
    private String accessTokenSecret;

    @Value("${twitter.bearerToken}")
    private String bearerToken;

    @Value("${twitter.baseUrl}")
    private String baseUrl;

    @Value("${twitter.useProxy}")
    private Boolean useProxy;

    public String createTweet(TwitterTweetsRequest request) {
        String url = baseUrl + "/2/tweets";
        String dataStr = JSONUtil.toJsonStr(request);
        String dst = genOAuth1aAuthorization("POST", url);

        HttpRequest httpRequest = HttpRequest.post(url)
            .header("Authorization", dst)
            .body(dataStr);
        if (useProxy) {
            Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("127.0.0.1", 7890));
            httpRequest.setProxy(proxy);
        }
        String res = httpRequest.execute().body();

        /* String res2 = HttpRequest.get("https://api.x.com/2/tweets?ids=1874709933301874829")
            .header("Authorization", "Bearer " + bearerToken)
            .body(dataStr)
            .setProxy(proxy)
            .execute()
            .body(); */
        JSONObject jsonObject = JSONUtil.parseObj(res);
        if (jsonObject.containsKey("data") && !jsonObject.getJSONObject("data").containsKey("id")) {
            return jsonObject.getJSONObject("data").getStr("id");
        } else {
            throw new ServiceException(jsonObject.getStr("title"));
        }
    }

    public String genOAuth1aAuthorization(String method, String url) {
        Map<String, Object> signMap = new TreeMap<>(String::compareTo);
        signMap.put("oauth_consumer_key", consumerKey);
        signMap.put("oauth_nonce", IdUtil.fastSimpleUUID());
        signMap.put("oauth_signature_method", "HMAC-SHA1");
        signMap.put("oauth_timestamp", System.currentTimeMillis() / 1000);
        signMap.put("oauth_token", accessToken);
        signMap.put("oauth_version", "1.0");

        List<String> yyList = new ArrayList<>();
        for (String key : signMap.keySet()) {
            yyList.add(String.format("%s=%s", URLEncoder.encode(key, StandardCharsets.UTF_8), URLEncoder.encode(signMap.get(key).toString(), StandardCharsets.UTF_8)));
        }

        String yyStr = String.join("&", yyList);
        yyStr = String.format("%s&%s&%s", method, URLEncoder.encode(url, StandardCharsets.UTF_8), URLEncoder.encode(yyStr, StandardCharsets.UTF_8));
        String signingKey = String.format("%s&%s", URLEncoder.encode(consumerSecret, StandardCharsets.UTF_8), URLEncoder.encode(accessTokenSecret, StandardCharsets.UTF_8));

        HMac hMac = SecureUtil.hmacSha1(signingKey);
        String OAuthSignature = hMac.digestBase64(yyStr, false);

        return String.format("OAuth oauth_consumer_key=\"%s\", oauth_nonce=\"%s\", oauth_signature=\"%s\", oauth_signature_method=\"HMAC-SHA1\", oauth_timestamp=\"%s\", oauth_token=\"%s\", oauth_version=\"%s\"",
            consumerKey, signMap.get("oauth_nonce"), URLEncoder.encode(OAuthSignature, StandardCharsets.UTF_8), signMap.get("oauth_timestamp"), accessToken, signMap.get("oauth_version"));
    }


}
