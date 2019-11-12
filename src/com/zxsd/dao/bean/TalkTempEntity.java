package com.zxsd.dao.bean;

/**
 * even
 * 2019/7/15
 */
public class TalkTempEntity {
    private String id;
    private String talk_id;
    private String talk_column_id;
    private String type;
    private String title;
    private String cont_intro;
    private String image_url;
    private String wap_image_url;
    private String order_num;
    private String pc_url;
    private String pc_free_url;
    private String wap_url;
    private String wap_free_url;
    private String remarks;

    public String getWap_image_url() {
        return wap_image_url;
    }

    public void setWap_image_url(String wap_image_url) {
        this.wap_image_url = wap_image_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTalk_id() {
        return talk_id;
    }

    public void setTalk_id(String talk_id) {
        this.talk_id = talk_id;
    }

    public String getTalk_column_id() {
        return talk_column_id;
    }

    public void setTalk_column_id(String talk_column_id) {
        this.talk_column_id = talk_column_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCont_intro() {
        return cont_intro;
    }

    public void setCont_intro(String cont_intro) {
        this.cont_intro = cont_intro;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getPc_url() {
        return pc_url;
    }

    public void setPc_url(String pc_url) {
        this.pc_url = pc_url;
    }

    public String getPc_free_url() {
        return pc_free_url;
    }

    public void setPc_free_url(String pc_free_url) {
        this.pc_free_url = pc_free_url;
    }

    public String getWap_url() {
        return wap_url;
    }

    public void setWap_url(String wap_url) {
        this.wap_url = wap_url;
    }

    public String getWap_free_url() {
        return wap_free_url;
    }

    public void setWap_free_url(String wap_free_url) {
        this.wap_free_url = wap_free_url;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
