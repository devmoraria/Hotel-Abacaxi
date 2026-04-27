package Hotel

private const val RESET  = "\u001B[0m"
private const val VERDE  = "\u001B[32m"
private const val AMARELO = "\u001B[33m"
private const val VERMELHO = "\u001B[31m"
private const val CIANO  = "\u001B[36m"
private const val NEGRITO = "\u001B[1m"

fun barra(percentual: Double, cor: String): String {
    val total = 20
    val cheios = ((percentual / 100) * total).toInt().coerceIn(0, total)
    val vazios = total - cheios
    return "$cor${"█".repeat(cheios)}$RESET${"░".repeat(vazios)}"
}

fun exibirRelatoriosOperacionais(nomeFuncionario: String) {

    val totalReservas     = listaReservasMemoria.size
    val quartosOcupCount  = quartosOcupados.count { it == 1 }
    val taxaOcupacao      = (quartosOcupCount.toDouble() / 20) * 100
    val receitaHospedagem = listaReservasMemoria.sumOf { it.total }
    val totalGeral        = receitaHospedagem + receitaEventosAcumulada
    val metaReceita       = 10000.0 // meta do hotel — ajuste como quiser
    val percentualMeta    = ((totalGeral / metaReceita) * 100).coerceAtMost(100.0)

    println("\n${NEGRITO}${VERDE}===================================================")
    println("║       DASHBOARD - HOTEL ABACAXI  🍍          ║")
    println("===================================================${RESET}")

    println("\n${NEGRITO}[ OCUPAÇÃO ]${RESET}")
    println("Quartos ocupados  |${barra(taxaOcupacao, VERDE)}| ${String.format("%.1f", taxaOcupacao)}%  ($quartosOcupCount/20)")

    println("\n${NEGRITO}[ HÓSPEDES & EVENTOS ]${RESET}")
    val pctHospedes = (listaHospedesMemoria.size.toDouble() / 15) * 100
    println("Hóspedes cadastr. |${barra(pctHospedes, AMARELO)}| ${listaHospedesMemoria.size}/15")
    println("Eventos confirm.  |${barra(totalEventosConfirmados.toDouble() * 10, CIANO)}| $totalEventosConfirmados confirmados")

    println("\n${NEGRITO}[ RECEITA ]${RESET}")
    println("Hospedagem        |${barra((receitaHospedagem/metaReceita)*100, VERDE)}| R$ ${String.format("%.2f", receitaHospedagem)}")
    println("Eventos           |${barra((receitaEventosAcumulada/metaReceita)*100, AMARELO)}| R$ ${String.format("%.2f", receitaEventosAcumulada)}")
    println("Meta total        |${barra(percentualMeta, if (percentualMeta >= 100) VERDE else VERMELHO)}| R$ ${String.format("%.2f", totalGeral)} / R$ ${String.format("%.2f", metaReceita)}")

    println("\n${NEGRITO}[ RESUMO RÁPIDO ]${RESET}")
    println("  Reservas ativas : $totalReservas")
    println("  Receita total   : ${VERDE}R$ ${String.format("%.2f", totalGeral)}${RESET}")
    val status = when {
        percentualMeta >= 100 -> "${VERDE}✓ META ATINGIDA!${RESET}"
        percentualMeta >= 50  -> "${AMARELO}⚠ Metade do caminho${RESET}"
        else                  -> "${VERMELHO}✗ Abaixo da meta${RESET}"
    }
    println("  Status da meta  : $status")

    println("\n$nomeFuncionario, pressione Enter para voltar...")
    readln()
}
