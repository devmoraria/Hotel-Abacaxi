package Hotel

private const val RESET   = "\u001B[0m"
private const val VERDE   = "\u001B[32m"
private const val AMARELO = "\u001B[33m"
private const val VERMELHO = "\u001B[31m"
private const val CIANO   = "\u001B[36m"
private const val NEGRITO = "\u001B[1m"

fun chatbot(nomeFuncionario: String) {
    println("\n${NEGRITO}${CIANO}===================================================")
    println("║     ASSISTENTE VIRTUAL - HOTEL ABACAXI 🤖       ║")
    println("===================================================${RESET}")
    println("Digite o que deseja fazer. Ex: ${AMARELO}'quero fazer uma reserva'${RESET}")
    println("Digite ${VERMELHO}sair${RESET} para voltar ao menu.\n")

    while (true) {
        print("${NEGRITO}$nomeFuncionario${RESET} > ")
        val entrada = readln().trim().lowercase()

        if (entrada == "sair" || entrada == "voltar") {
            println("${AMARELO}Voltando ao menu principal...${RESET}")
            break
        }

        interpretarComando(entrada, nomeFuncionario)
    }
}

fun interpretarComando(entrada: String, nomeFuncionario: String) {
    val reservaKeys     = listOf("reserva", "quarto", "hospedagem", "check-in", "checkin")
    val hospedeKeys     = listOf("hospede", "hóspede", "cadastro", "cadastrar", "cliente")
    val eventoKeys      = listOf("evento", "auditorio", "auditório", "festa", "empresa")
    val arKeys          = listOf("ar-condicionado", "condicionado", "climatizador", "orcamento", "orçamento")
    val combustivelKeys = listOf("combustivel", "combustível", "abastecimento", "posto", "gasolina", "alcool", "álcool")
    val checkoutKeys    = listOf("checkout", "check-out", "saida", "saída", "liberar quarto")
    val relatorioKeys   = listOf("relatorio", "relatório", "dashboard", "receita", "ocupacao", "ocupação")

    when {
        reservaKeys.any     { entrada.contains(it) } -> {
            println("${VERDE}[✓] Abrindo ${NEGRITO}Reservas de Quartos${RESET}${VERDE}...${RESET}\n")
            cadastrarQuartos(nomeFuncionario)
        }
        hospedeKeys.any     { entrada.contains(it) } -> {
            println("${VERDE}[✓] Abrindo ${NEGRITO}Cadastro de Hóspedes${RESET}${VERDE}...${RESET}\n")
            gerenciarHospedes()
        }
        eventoKeys.any      { entrada.contains(it) } -> {
            println("${VERDE}[✓] Abrindo ${NEGRITO}Gerenciamento de Eventos${RESET}${VERDE}...${RESET}\n")
            gerenciarEventos()
        }
        combustivelKeys.any { entrada.contains(it) } -> {
            println("${VERDE}[✓] Abrindo ${NEGRITO}Abastecimento de Automóveis${RESET}${VERDE}...${RESET}\n")
            abastecimentoDeAutomoveis(nomeFuncionario)
        }
        checkoutKeys.any    { entrada.contains(it) } -> {
            println("${VERDE}[✓] Abrindo ${NEGRITO}Checkout${RESET}${VERDE}...${RESET}\n")
            realizarCheckout(nomeFuncionario)
        }
        relatorioKeys.any   { entrada.contains(it) } -> {
            println("${VERDE}[✓] Abrindo ${NEGRITO}Dashboard${RESET}${VERDE}...${RESET}\n")
            exibirRelatoriosOperacionais(nomeFuncionario)
        }
        arKeys.any          { entrada.contains(it) } -> {
            println("${VERDE}[✓] Abrindo ${NEGRITO}Manutenção de Ar-Condicionado${RESET}${VERDE}...${RESET}\n")
            gerenciarArCondicionado(nomeFuncionario)
        }
        else -> {
            println("${VERMELHO}[?] Não entendi '${NEGRITO}$entrada${RESET}${VERMELHO}'.${RESET}")
            println("${AMARELO}    Tente mencionar: reserva, hóspede, evento, ar-condicionado,")
            println("    combustível, checkout ou relatório.${RESET}\n")
        }
    }
}