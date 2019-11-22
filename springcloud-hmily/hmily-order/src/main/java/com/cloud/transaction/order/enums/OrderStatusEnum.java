
package com.cloud.transaction.order.enums;

/**
 * @author 阿甘
 * @see ://study.163.com/course/courseMain.htm?courseId=1209367806&share=2&shareId=1016671292
 * @version 1.0
 * 注：如有任何疑问欢迎加入QQ群977438372 进行讨论
 */
public enum OrderStatusEnum {

    //订单try
    CONFIRM((byte)0, "已拍下"),
    //订单cancel
    CONFIRM_FAIL((byte)1, "拍下失败"),
    //订单confirm
    NOT_PAY((byte)2, "未支付"),
    //支付confirm
    PAY_SUCCESS((byte)3, "支付成功"),
    //支付cancel
    PAY_FAIL((byte)4, "支付失败");


    private byte code;

    private String desc;

    OrderStatusEnum(byte code, String desc) {
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
