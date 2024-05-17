# Sistema Bancário em Java

## Descrição do Projeto
Este projeto é um sistema bancário simples desenvolvido em Java, projetado para reforçar o conhecimento em Programação Orientada a Objetos (POO). Ele aborda os pilares fundamentais da POO, como abstração, encapsulamento, herança e polimorfismo, dentro de um contexto bancário.

## Objetivos
- Demonstrar a aplicação prática dos princípios de POO em Java.
- Fornecer um exemplo de referência para um sistema bancário simples.
- Incentivar melhorias e evoluções no código base.

## Funcionalidades Implementadas
- Criação e gerenciamento de contas bancárias (Conta Corrente e Conta Poupança).
- Depósito, saque e transferência de valores entre contas.
- Exibição de extratos de conta.

## Pilares da POO Aplicados

### Abstração
As contas bancárias são representadas por uma classe abstrata `Conta`, que define os métodos e propriedades comuns a todas as contas.

### Encapsulamento
Os atributos das classes são privados e acessados através de métodos públicos (getters e setters), garantindo o controle sobre os dados.

### Herança
As classes `ContaCorrente` e `ContaPoupanca` herdam da classe `Conta`, reutilizando e especializando comportamentos comuns.

### Polimorfismo
Os métodos de `Conta` podem ser sobrescritos (overridden) nas classes filhas (`ContaCorrente` e `ContaPoupanca`), permitindo comportamentos específicos para cada tipo de conta.

### Diagrama de classes básico
![Diagrama de classes](/assets/SistemaBancario1.drawio.png)

## Autor
Desenvolvido por [Thiago José Lopes](https://github.com/Thiago-JLopes).