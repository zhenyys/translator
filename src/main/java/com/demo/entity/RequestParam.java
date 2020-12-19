package com.demo.entity;

public class RequestParam {
    /**
     * 源语言
     */
    private String fromLang;
    /**
     * 目标语言
      */
    private String toLang;

    public String getFromLang() {
        return fromLang;
    }

    @Override
    public String toString() {
        return "RequestParam{" +
                "fromLang='" + fromLang + '\'' +
                ", toLang='" + toLang + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public void setFromLang(String fromLang) {
        this.fromLang = fromLang;
    }

    public String getToLang() {
        return toLang;
    }

    public void setToLang(String toLang) {
        this.toLang = toLang;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 要翻译的内容
     */
    private String content;
}
