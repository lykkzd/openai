package com.lykkzd.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @Author: 刘阳
 * @CreateTime: 2024-06-29
 * @Description: 对接ChatGPT API
 * @Version: 1.0
 */

@RestController
@RequiredArgsConstructor
public class ChatController {

    // spring-ai 自动装配的，可以直接注入使用
    private final OpenAiChatModel openAiChatModel;

    /**
     * 调用OpenAI的接口
     * @param msg 我们提的问题
     * @return
     */
    @RequestMapping(value = "/ai/chat1")
    public String chat(@RequestParam(value = "msg") String msg) {
        String called = openAiChatModel.call(msg);
        return called;
    }

    /**
     * 调用OpenAI的接口
     * @param msg 我们提的问题
     * @return
     */
    @RequestMapping(value = "/ai/chat2")
    public Object chat2(@RequestParam(value = "msg") String msg) {
        ChatResponse chatResponse = openAiChatModel.call(new Prompt(msg));
        return chatResponse.getResult().getOutput().getContent();
    }

    /**
     * 调用OpenAI的接口
     * @param msg 我们提的问题
     * @return
     */
    @RequestMapping(value = "/ai/chat3")
    public Object chat3(@RequestParam(value = "msg") String msg) {
        ChatResponse chatResponse = openAiChatModel.call(new Prompt(msg, OpenAiChatOptions.builder()
                .withModel("gpt-4-o")// gpt-4-32k：gpt的版本，32k是参数量，越高代表问题回答的越好
                .withTemperature(0.4F)// 温度越高，回答得比较有创新性，但准确率会下降；温度越低，回答的准确率会更好。
                .build()));// 这个可选参数其实可以在配置文件中配置(见配置文件)，如果这里代码和配置文件都配置了，代码中的会覆盖配置文件
        return chatResponse.getResult().getOutput().getContent();
    }

    /**
     * 调用OpenAI的流式API
     * @param msg 我们提的问题
     * @return
     */
    @RequestMapping(value = "/ai/chat4")
    public Object chat4(@RequestParam(value = "msg") String msg) {
        Flux<ChatResponse> flux = openAiChatModel.stream(new Prompt(msg, OpenAiChatOptions.builder()
                .withModel("gpt-4-o")
                .withTemperature(0.4F)
                .build()));
        flux.toStream().forEach(chatResponse -> {
            System.out.println(chatResponse.getResult().getOutput().getContent());
        });
        // 数据的序列，一序列的数据，一个一个的数据返回
        return flux.collectList();
    }
}
