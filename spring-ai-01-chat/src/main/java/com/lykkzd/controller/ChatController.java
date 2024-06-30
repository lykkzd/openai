package com.lykkzd.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 刘阳
 * @CreateTime: 2024-06-29
 * @Description: 对接ChatGPT API
 * @Version: 1.0
 */

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final OpenAiChatModel openAiChatModel;



}
