package com.example.smartcity1_1.bean;

import java.io.Serializable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/1 at 20:55
 */
public class NewsList implements Serializable {

    /**
     * id : 1
     * type : 时政
     * img : http://localhost:8080/mobileA/images/1.jpg
     * title : 天安门广场举行国庆升旗仪式
     * content : 10月1日，中华人民共和国成立71周年，天安门广场将举行2020年国庆升旗仪式。今年国庆的升旗仪式，有什么特殊的意义？又会有哪些新亮点？戳直播，看五星红旗在天安门广场冉冉升起，一起祝福祖国！
     * publicTime : 2020-10-01 06:00:00
     * audienceCount : 600
     * praiseCount : 500
     * subject : 国庆专题
     * flag : 1
     */

    private int id;
    private String type;
    private String img;
    private String title;
    private String content;
    private String publicTime;
    private int audienceCount;
    private int praiseCount;
    private String subject;
    private int flag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(String publicTime) {
        this.publicTime = publicTime;
    }

    public int getAudienceCount() {
        return audienceCount;
    }

    public void setAudienceCount(int audienceCount) {
        this.audienceCount = audienceCount;
    }

    public int getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(int praiseCount) {
        this.praiseCount = praiseCount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
