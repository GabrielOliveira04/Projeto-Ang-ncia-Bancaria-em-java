# Projeto-Angencia-Bancaria-em-java
Praticando com java orientado ao objeto, fiz um projeto de agencia bancaria para melhorar mais ainda minhas praticas com java

##Descrição do Projeto


Este projeto é uma aplicação bancária simples escrita em Java que permite que o usuário crie contas bancárias e realize transações de depósito e saque. Ele foi desenvolvido usando a IDE Eclipse e utiliza conceitos básicos de orientação a objetos, como herança e encapsulamento.

A aplicação permite que o usuário crie uma nova conta bancária, informando o número da conta e o nome do titular, e faça um depósito inicial. Uma vez criada a conta, o usuário pode realizar transações de depósito e saque, que são registradas no histórico de transações da conta. O saldo atual da conta é atualizado após cada transação e pode ser verificado pelo usuário a qualquer momento.

A aplicação possui uma classe ContaBancaria que define as propriedades e comportamentos comuns a todas as contas bancárias, como o número da conta, o nome do titular, o saldo atual e o histórico de transações. Ela também possui duas subclasses, ContaCorrente e ContaPoupanca, que adicionam comportamentos específicos a cada tipo de conta.

A classe Banco é responsável por criar e gerenciar as contas bancárias. Ela possui métodos para criar uma nova conta, obter informações de uma conta existente, realizar transações de depósito e saque e imprimir o histórico de transações de uma conta.

O projeto inclui um menu simples de linha de comando que permite que o usuário interaja com a aplicação, escolhendo as opções disponíveis, como criar uma nova conta, realizar uma transação ou imprimir o histórico de transações.
