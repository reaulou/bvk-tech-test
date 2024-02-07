package com.reaulou.bvktechtest.core;

public class InternalResponse extends InternalMessage{

    private String returnCode;
    private String returnDesc;

    public String getReturnCode() {
        return returnCode;
    }
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
    public String getReturnDesc() {
        return returnDesc;
    }
    public void setReturnDesc(String returnDesc) {
        this.returnDesc = returnDesc;
    }

    public String toString() {
        return "InternalResponse [id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", returnCode=" + returnCode + ", returnDesc=" + returnDesc + ", extInfo=" + extInfo + "]";
    }
}
