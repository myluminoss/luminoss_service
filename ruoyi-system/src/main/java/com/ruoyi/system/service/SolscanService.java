package com.ruoyi.system.service;


import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.stereotype.Service;

@Service
public class SolscanService {

    private String baseUrl = "https://few-palpable-glitter.solana-devnet.quiknode.pro/6a74e8fe6bcb69d3cf31e8b8f1b6a4ba6b9879c9/";

    public JSONObject queryTransactionStatusByHash(String hash) {
        String data = "{\n" +
            "    \"jsonrpc\": \"2.0\",\n" +
            "    \"id\": 1,\n" +
            "    \"method\": \"getTransaction\",\n" +
            "    \"params\": [\n" +
            "        \"%s\",\n" +
            "        {\n" +
            "            \"encoding\": \"jsonParsed\",\n" +
            "            \"maxSupportedTransactionVersion\": 0\n" +
            "        }\n" +
            "    ]\n" +
            "}";
        data = String.format(data, hash);

        String res = HttpRequest.post(baseUrl).body(data).execute().body();
        return JSONUtil.parseObj(res);
    }


}
