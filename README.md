# 🍍 Hotel Abacaxi - Sistema de Gestão em Kotlin com IA

Sistema de gestão hoteleira desenvolvido em Kotlin para terminal (TUI). O projeto simula a operação completa de um hotel, focando em lógica de programação robusta e experiência do utilizador via linha de comandos.

## 🚀 Funcionalidades


- **🏨 Reservas:** Sistema de quartos (Standard, Executivo, Luxo) com mapa de ocupação visual.
- **👤 Cadastro de Hóspedes:** CRUD completo com busca por prefixo e lista organizada de A-Z.
- **📅 Eventos:** Calculadora de custos para auditórios, incluindo buffet e staff.
- **📊 Dashboard:** Relatórios com barras de progresso ANSI para ocupação e metas de receita.
- **🤖 Assistente Virtual:** Chatbot que interpreta comandos em linguagem natural.
- **❄️/⛽ Módulos Extra:** Gestão de ar-condicionado e comparador de combustíveis.
## 🧠 Destaque: Assistente Virtual com Processamento de Intenções

O diferencial deste projeto é o seu **Chatbot Integrado**, que permite a navegação por linguagem natural. Em vez de decorar comandos ou navegar apenas por números, o operador pode simplesmente "conversar" com o sistema.

### Como a "IA" funciona:
O sistema utiliza uma lógica de **Tokens e Chaves Semânticas**. Ele processa a entrada do usuário e busca correspondências em listas de palavras-chave pré-definidas (intents):

- **Intent Reservas:** identifica termos como `quarto`, `hospedagem`, `check-in`.
- **Intent Financeiro:** identifica termos como `receita`, `lucro`, `dashboard`.
- **Intent Logística:** identifica termos como `combustível`, `carro`, `posto`.

### Exemplo de Fluxo:
> **Entrada do Usuário:** "Quero cadastrar um novo cliente agora."  
> **Processamento:** O motor identifica o token `cliente` -> mapeia para a função `gerenciarHospedes()`.  
> **Resultado:** O sistema redireciona o usuário instantaneamente para o módulo correto.
## 🛠️ Tecnologias e Conceitos

- **Linguagem:** Kotlin
- **Modularização:** Organização do código em múltiplos ficheiros por responsabilidade.
- **Regex:** Extração inteligente de dados numéricos em entradas de texto.
- **UI de Terminal:** Uso de cores ANSI e formatação tabular para melhor legibilidade.

## ⚙️ Como executar

1. Clone o repositório.
2. Importe o projeto no IntelliJ IDEA.
3. Execute o ficheiro `Hotel.kt`.
4. **Senha de acesso:** `2678`

---
*Desenvolvido como projeto prático de lógica de programação.*