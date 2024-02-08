package com.reaulou.bvktechtest.core;

public class InternalRequest extends InternalMessage{

    private Boolean isValid;

    public Boolean getIsValid(){
        return isValid;
    }

    public void setIsValid(Boolean isValid){
        this.isValid = isValid;
    }

    public String toString() {
        return "InternalRequest [id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", extInfo=" + extInfo + "]";
    }
}
