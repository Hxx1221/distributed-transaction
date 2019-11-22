
package com.cloud.transaction.commons.enums;

/**
 * @author 阿甘
 * @see ://study.163.com/course/courseMain.htm?courseId=1209367806&share=2&shareId=1016671292
 * @version 1.0
 * 注：如有任何疑问欢迎加入QQ群977438372 进行讨论
 */
public enum TccEnum {


    /**
     * Not pay order status enum.
     */
    TRY((byte)1, "try"),

    CONFIRM((byte)2, "confirm"),

    CANCEL((byte)3, "cancel");


    private byte code;

    private String desc;

    TccEnum(byte code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public byte getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(byte code) {
        this.code = code;
    }

    /**
     * Gets desc.
     *
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets desc.
     *
     * @param desc the desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
}
