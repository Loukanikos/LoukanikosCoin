package org.crypto.monitor;


import org.crypto.CryptoCurrencyEnum;
import org.crypto.api.CryptoPriceProvider;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CryptoPriceMonitor implements AutoCloseable {
    private final CryptoPriceProvider priceProvider;
    private final ScheduledExecutorService executor;

    public CryptoPriceMonitor(CryptoPriceProvider priceProvider) {
        this.priceProvider = priceProvider;
        this.executor = Executors.newScheduledThreadPool(1);
    }

    public void startMonitoring(int intervalInSeconds) {
        executor.scheduleAtFixedRate(() -> {
            try {
                Map<CryptoCurrencyEnum, Double> prices = priceProvider.getAllCryptoPrices();
                prices.forEach((crypto, price) ->
                        System.out.println("Preço atual de " + crypto.name() + ": $" + price));
            } catch (Exception e) {
                System.err.println("Erro ao buscar preços: " + e.getMessage());
            }
        }, 0, intervalInSeconds, TimeUnit.SECONDS);
    }

    @Override
    public void close() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}