package com.lykkzd.controller;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.webresources.FileResource;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 刘阳
 * @CreateTime: 2024-07-01
 * @Description: 语音转文本
 * @Version: 1.0
 */
@RestController
@RequiredArgsConstructor
public class TranscriptionsController {

    private final OpenAiAudioTranscriptionModel openAiAudioTranscriptionModel;

    /**
     * 根据音频文件转成对应的文本
     * @return
     */
    @RequestMapping("/ai/transcriptions")
    public Object transcriptions() {
        // 传入音频文件路径
        Resource audioFile = new FileSystemResource("classpath:jfk.flac");
        // 返回音频转好的文本信息
        String text = openAiAudioTranscriptionModel.call(audioFile);
        return text;
    }
}
