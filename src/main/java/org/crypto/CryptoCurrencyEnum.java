package org.crypto;

public enum CryptoCurrencyEnum {
    BITCOIN("bitcoin"),
    ETHEREUM("ethereum"),
    DOGECOIN("dogecoin");

    private final String id;

    CryptoCurrencyEnum(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
