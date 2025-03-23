package org.crypto.api;

import org.crypto.CryptoCurrencyEnum;

import java.util.Map;

public interface CryptoPriceProvider {
    double getCryptoPrice(String cryptoId) throws Exception;

    Map<CryptoCurrencyEnum, Double> getAllCryptoPrices() throws Exception;

}

