package com.test;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Langs> list = new ArrayList<>();
        list.add(new Langs("Chinese Simplified", "zh_cn","简体中文"));//简体中文
        list.add(new Langs("Chinese Traditional", "zh_tw","繁体中文"));//繁体中文
        list.add(new Langs("English", "en","英语"));//英语
        list.add(new Langs("French", "fr","法语"));//法语
        list.add(new Langs("Russian", "ru","俄语"));//俄语
        list.add(new Langs("Spanish", "es","西班牙语"));//西班牙语
        list.add(new Langs("Japanese", "ja","日本语"));//日本语
        for (Langs lang : list) {
            Translator instance = Translator.getInstance();
            String text = instance.translateText("你好", "auto", lang.getValue());
            System.out.println(lang.getName() + ": " + text);
        }
    }
}
