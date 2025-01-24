package com.jacquinc.admin.application.bo;

import java.io.Serializable;

/**
 *
 * Created by JiangSF on 18/1/29.
 */
public class RsaBO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6476250066268531327L;

    private String rsaModule;

    private String rsaExponent;

    public String getRsaModule() {
        return rsaModule;
    }

    public void setRsaModule(String rsaModule) {
        this.rsaModule = rsaModule;
    }

    public String getRsaExponent() {
        return rsaExponent;
    }

    public void setRsaExponent(String rsaExponent) {
        this.rsaExponent = rsaExponent;
    }
}