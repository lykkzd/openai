package com.lykkzd.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 刘阳
 * @CreateTime: 2024-07-01
 * @Description: 根据图片询问OpenAI
 * @Version: 1.0
 */

@RestController
@RequiredArgsConstructor
public class ImageController {

    private final OpenAiImageModel openAiImageModel;

    /**
     * 根据文字生成图片
     * @param msg
     * @return
     */
    @RequestMapping("/ai/image")
    public Object image(@RequestParam("msg") String msg) {
        ImageResponse imageResponse = openAiImageModel.call(new ImagePrompt(msg)) ;
        String imageUrl = imageResponse.getResult().getOutput().getUrl();
        // 后续业务就是拿着这张图片进行处理。。。
        return imageResponse.getResult().getOutput();
    }

    /**
     * 配置图片的一些基础配置
     * @param msg
     * @return
     */
    @RequestMapping("/ai/image2")
    public Object image2(@RequestParam("msg") String msg) {
        ImageResponse imageResponse = openAiImageModel.call(new ImagePrompt(msg, OpenAiImageOptions.builder()
                .withQuality("hd")// 高清图像
                .withN(4)// 生成4张图片
                .withHeight(1024)// 生成的图片高度
                .withWidth(1024)// 生成的图片宽度
                .build()));// 也可以在配置文件中配置，都配置了代码中的会覆盖配置文件
        String imageUrl = imageResponse.getResult().getOutput().getUrl();
        // 后续业务就是拿着这张图片进行处理。。。
        return imageResponse.getResult().getOutput();
    }
}
