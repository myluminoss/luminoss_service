package com.ruoyi.system.controller;


import cn.dev33.satoken.annotation.SaIgnore;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.annotation.OutApi;
import com.ruoyi.system.domain.dto.TwitterTweetsRequest;
import com.ruoyi.system.service.TwitterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RequiredArgsConstructor
@RestController
public class TwitterApiController {

    private final TwitterService twitterService;

    @SaIgnore
    @OutApi
    @PostMapping("/twitterApi/createTweet")
    public R<?> createTweet(@RequestBody TwitterTweetsRequest request) {
        HashMap<String, Object> res = new HashMap<>();
        res.put("id", twitterService.createTweet(request));
        return R.ok(res);
    }

}
