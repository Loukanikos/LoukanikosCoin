Programa simples para verificação de cotação de criptomoeda usando os seguintes paradigmas:
1) Ser aderente ao SOLID
    .CryptoPriceMonitor cuida exclusivamente do monitoramento e gerenciamento de recursos, enquanto a lógica de
     inicialização permanece na classe Main. (Responsabilidade unica)

    .CryptoPriceMonitor depende de uma abstração da CrypoPriceProvider em vez de uma implementação concreta.
      (inversão de dependencia)

    .O monitor aceita diferentes implementações de provedores de preços, permitindo extensibilidade sem modificar o código existente.
      (Aberto/Fechado)

    .CryptoPriceProvider permite adicionar novas APIs sem alterar a lógica principal.

2) Polimorfismo
    .Permite facilmente adicionar ou alterar criptomoedas suportadas, mantendo o código modular.


