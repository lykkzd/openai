package com.lykkzd.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

/**
 * @Author: 刘阳
 * @CreateTime: 2024-07-12
 * @Description: 文本转语音控制层
 * @Version: 1.0
 */
@RestController
@RequiredArgsConstructor
public class TTSController {

    private final OpenAiAudioSpeechModel openAiAudioSpeechModel;

    @RequestMapping("/ai/tts")
    public Boolean tts() {
        String text = "需要转成语音的话";
        byte[] bytes = openAiAudioSpeechModel.call(text);
        // fname:将文件下载到哪里 
        return save2File("/Users/lykkzd/Downloads", bytes);
    }

    /**
     * 方法功能：将字节数组写入到新建文件中
     * @param fname
     * @param msg
     * @return
     */
    public static boolean save2File(String fname, byte[] msg){
        OutputStream fos = null;
        try{
            File file = new File(fname);
            File parent = file.getParentFile();
            boolean bool;
            if ((!parent.exists()) &&
                    (!parent.mkdirs())) {
                return false;
            }
            fos = new FileOutputStream(file);
            fos.write(msg);
            fos.flush();
            return true;
        }catch (FileNotFoundException e){
            return false;
        }catch (IOException e){
            File parent;
            return false;
        }
        finally{
            if (fos != null) {
                try{
                    fos.close();
                }catch (IOException e) {}
            }
        }
    }
}
