package org.crypto.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.crypto.CryptoCurrencyEnum;

import java.util.HashMap;
import java.util.Map;

public class CoinGeckoPriceProvider implements CryptoPriceProvider {
    private final OkHttpClient client = new OkHttpClient();

    @Override
    public double getCryptoPrice(String cryptoId) throws Exception {
        String url = String.format("https://api.coingecko.com/api/v3/simple/price?ids=%s&vs_currencies=usd", cryptoId);
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Erro na requisição: " + response);
            }

            String jsonData = response.body().string();
            JsonObject jsonObject = JsonParser.parseString(jsonData).getAsJsonObject();
            return jsonObject.getAsJsonObject(cryptoId).get("usd").getAsDouble();
        }
    }

    @Override
    public Map<CryptoCurrencyEnum, Double> getAllCryptoPrices() throws Exception {
        StringBuilder cryptoIds = new StringBuilder();
        for (CryptoCurrencyEnum crypto : CryptoCurrencyEnum.values()) {
            cryptoIds.append(crypto.getId()).append(",");
        }

        String url = String.format("https://api.coingecko.com/api/v3/simple/price?ids=%s&vs_currencies=usd",
                cryptoIds.toString().replaceAll(",$", ""));

        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Erro na requisição: " + response);
            }

            String jsonData = response.body().string();
            JsonObject jsonObject = JsonParser.parseString(jsonData).getAsJsonObject();

            Map<CryptoCurrencyEnum, Double> cryptoPrices = new HashMap<>();
            for (CryptoCurrencyEnum crypto : CryptoCurrencyEnum.values()) {
                double price = jsonObject.getAsJsonObject(crypto.getId()).get("usd").getAsDouble();
                cryptoPrices.put(crypto, price);
            }
            return cryptoPrices;
        }
    }
}