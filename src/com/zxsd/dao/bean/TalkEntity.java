package com.zxsd.dao.bean;

import java.util.List;

/**
 * even
 * 2019/7/15
 */
public class TalkEntity {
    private String id;
    private String talk_id;
    private String title;
    private String pc_image;
    private String wap_image;
    private String status;
    private String column_title;
    private String order_num;
    private String html_name;
    private List<TalkTempEntity> list;

    public String getHtml_name() {
        return html_name;
    }

    public void setHtml_name(String html_name) {
        this.html_name = html_name;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPc_image() {
        return pc_image;
    }

    public void setPc_image(String pc_image) {
        this.pc_image = pc_image;
    }

    public String getWap_image() {
        return wap_image;
    }

    public void setWap_image(String wap_image) {
        this.wap_image = wap_image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getColumn_title() {
        return column_title;
    }

    public void setColumn_title(String column_title) {
        this.column_title = column_title;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public List<TalkTempEntity> getList() {
        return list;
    }

    public void setList(List<TalkTempEntity> list) {
        this.list = list;
    }
}
