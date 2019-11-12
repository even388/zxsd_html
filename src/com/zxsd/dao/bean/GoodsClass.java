package com.zxsd.dao.bean;

public class GoodsClass {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_goods_class.goods_class_id
     *
     * @mbggenerated Tue Jul 26 16:26:35 CST 2016
     */
    private String goodsClassId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_goods_class.goods_class_name
     *
     * @mbggenerated Tue Jul 26 16:26:35 CST 2016
     */
    private String goodsClassName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_goods_class.goods_class_type
     *
     * @mbggenerated Tue Jul 26 16:26:35 CST 2016
     */
    private Byte goodsClassType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_goods_class.p_goods_class_id
     *
     * @mbggenerated Tue Jul 26 16:26:35 CST 2016
     */
    private String pGoodsClassId="0";

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_goods_class.goods_class_level
     *
     * @mbggenerated Tue Jul 26 16:26:35 CST 2016
     */
    private Short goodsClassLevel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_goods_class.priority
     *
     * @mbggenerated Tue Jul 26 16:26:35 CST 2016
     */
    private Byte priority;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_goods_class.is_display
     *
     * @mbggenerated Tue Jul 26 16:26:35 CST 2016
     */
    private Byte isDisplay;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_goods_class.remarks
     *
     * @mbggenerated Tue Jul 26 16:26:35 CST 2016
     */
    private String remarks;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_goods_class.goods_class_id
     *
     * @return the value of tb_goods_class.goods_class_id
     *
     * @mbggenerated Tue Jul 26 16:26:35 CST 2016
     */
    public String getGoodsClassId() {
        return goodsClassId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_goods_class.goods_class_id
     *
     * @param goodsClassId the value for tb_goods_class.goods_class_id
     *
     * @mbggenerated Tue Jul 26 16:26:35 CST 2016
     */
    public void setGoodsClassId(String goodsClassId) {
        this.goodsClassId = goodsClassId == null ? null : goodsClassId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_goods_class.goods_class_name
     *
     * @return the value of tb_goods_class.goods_class_name
     *
     * @mbggenerated Tue Jul 26 16:26:35 CST 2016
     */
    public String getGoodsClassName() {
        return goodsClassName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_goods_class.goods_class_name
     *
     * @param goodsClassName the value for tb_goods_class.goods_class_name
     *
     * @mbggenerated Tue Jul 26 16:26:35 CST 2016
     */
    public void setGoodsClassName(String goodsClassName) {
        this.goodsClassName = goodsClassName == null ? null : goodsClassName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_goods_class.goods_class_type
     *
     * @return the value of tb_goods_class.goods_class_type
     *
     * @mbggenerated Tue Jul 26 16:26:35 CST 2016
     */
    public Byte getGoodsClassType() {
        return goodsClassType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_goods_class.goods_class_type
     *
     * @param goodsClassType the value for tb_goods_class.goods_class_type
     *
     * @mbggenerated Tue Jul 26 16:26:35 CST 2016
     */
    public void setGoodsClassType(Byte goodsClassType) {
        this.goodsClassType = goodsClassType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_goods_class.p_goods_class_id
     *
     * @return the value of tb_goods_class.p_goods_class_id
     *
     * @mbggenerated Tue Jul 26 16:26:35 CST 2016
     */
    public String getpGoodsClassId() {
        return pGoodsClassId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_goods_class.p_goods_class_id
     *
     * @param pGoodsClassId the value for tb_goods_class.p_goods_class_id
     *
     * @mbggenerated Tue Jul 26 16:26:35 CST 2016
     */
    public void setpGoodsClassId(String pGoodsClassId) {
        this.pGoodsClassId = pGoodsClassId == null ? null : pGoodsClassId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_goods_class.goods_class_level
     *
     * @return the value of tb_goods_class.goods_class_level
     *
     * @mbggenerated Tue Jul 26 16:26:35 CST 2016
     */
    public Short getGoodsClassLevel() {
        return goodsClassLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_goods_class.goods_class_level
     *
     * @param goodsClassLevel the value for tb_goods_class.goods_class_level
     *
     * @mbggenerated Tue Jul 26 16:26:35 CST 2016
     */
    public void setGoodsClassLevel(Short goodsClassLevel) {
        this.goodsClassLevel = goodsClassLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_goods_class.priority
     *
     * @return the value of tb_goods_class.priority
     *
     * @mbggenerated Tue Jul 26 16:26:35 CST 2016
     */
    public Byte getPriority() {
        return priority;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_goods_class.priority
     *
     * @param priority the value for tb_goods_class.priority
     *
     * @mbggenerated Tue Jul 26 16:26:35 CST 2016
     */
    public void setPriority(Byte priority) {
        this.priority = priority;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_goods_class.is_display
     *
     * @return the value of tb_goods_class.is_display
     *
     * @mbggenerated Tue Jul 26 16:26:35 CST 2016
     */
    public Byte getIsDisplay() {
        return isDisplay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_goods_class.is_display
     *
     * @param isDisplay the value for tb_goods_class.is_display
     *
     * @mbggenerated Tue Jul 26 16:26:35 CST 2016
     */
    public void setIsDisplay(Byte isDisplay) {
        this.isDisplay = isDisplay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_goods_class.remarks
     *
     * @return the value of tb_goods_class.remarks
     *
     * @mbggenerated Tue Jul 26 16:26:35 CST 2016
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_goods_class.remarks
     *
     * @param remarks the value for tb_goods_class.remarks
     *
     * @mbggenerated Tue Jul 26 16:26:35 CST 2016
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}