package org.crypto;

import org.crypto.api.CoinGeckoPriceProvider;
import org.crypto.monitor.CryptoPriceMonitor;


public class Main {
    public static void main(String[] args) {
        try (CryptoPriceMonitor monitor = new CryptoPriceMonitor(new CoinGeckoPriceProvider())) {
            // Iniciar o monitoramento a cada 10 segundos
            monitor.startMonitoring(10);
            // Manter o programa ativo por um tempo (exemplo: 60 segundos)
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("O programa foi interrompido.");
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        }
    }

}