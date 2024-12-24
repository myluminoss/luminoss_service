package com.ruoyi.system.service;


import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.stereotype.Service;

@Service
public class BaseScanService {

    private String baseUrl = "https://api-sepolia.basescan.org/api";

    private String apiKey = "ZVJQAJG27TMZ3AS623BE4XXGUWPX5SSP7D";

    public boolean queryTransactionStatusByHash(String hash) {
        String url = String.format("%s?module=transaction&action=getstatus&txhash=%s&apikey=%s", baseUrl, hash, apiKey);
        String resStr = HttpUtil.get(url);
        JSONObject json = JSONUtil.parseObj(resStr);
        return json.containsKey("result") && "0".equals(json.getJSONObject("result").getStr("isError"));
    }



}
