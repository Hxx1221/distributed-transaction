
package com.cloud.transaction.commons.enums;


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
