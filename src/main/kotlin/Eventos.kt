package Hotel
import kotlin.math.ceil

private const val RESET    = "\u001B[0m"
private const val VERDE    = "\u001B[32m"
private const val AMARELO  = "\u001B[33m"
private const val VERMELHO = "\u001B[31m"
private const val CIANO    = "\u001B[36m"
private const val NEGRITO  = "\u001B[1m"

fun gerenciarEventos() {
    println("\n${NEGRITO}${CIANO}===================================================")
    println("║           GERENCIAR EVENTOS  📅              ║")
    println("===================================================${RESET}")

    println("Quantos convidados são esperados?")
    val convidados = extrairNumero(readln())?.toInt() ?: -1

    if (convidados <= 0) {
        println("${VERMELHO}[✗] Número de convidados inválido.${RESET}")
        return
    }

    if (convidados > 350) {
        println("${VERMELHO}[✗] Quantidade acima da capacidade máxima (350 convidados).${RESET}")
        return
    }

    var auditorio: String
    if (convidados <= 220) {
        auditorio = "Laranja"
        if (convidados > 150) {
            val cadeirasExtras = convidados - 150
            println("${VERDE}[✓] Auditório ${NEGRITO}Laranja${RESET}${VERDE} selecionado ($cadeirasExtras cadeiras adicionais).${RESET}")
        } else {
            println("${VERDE}[✓] Auditório ${NEGRITO}Laranja${RESET}${VERDE} selecionado.${RESET}")
        }
    } else {
        auditorio = "Colorado"
        println("${VERDE}[✓] Auditório ${NEGRITO}Colorado${RESET}${VERDE} selecionado.${RESET}")
    }

    val diasValidos = listOf("segunda", "terca", "quarta", "quinta", "sexta", "sabado", "domingo")
    println("Qual dia da semana será o evento?")
    val dia = readln().lowercase().trim()

    if (dia !in diasValidos) {
        println("${VERMELHO}[✗] Dia inválido. Informe um dia da semana (ex: segunda, sabado).${RESET}")
        return
    }

    println("Qual o horário de início? (entre 7h e 23h)")
    val horaInicial = extrairNumero(readln())?.toInt() ?: -1

    println("Qual a duração do evento em horas? (1 a 12)")
    val duracao = extrairNumero(readln())?.toInt() ?: -1

    if (duracao < 1 || duracao > 12) {
        println("${VERMELHO}[✗] Duração inválida. O evento deve ter entre 1 e 12 horas.${RESET}")
        return
    }

    val limiteFechamento = if (dia == "sabado" || dia == "domingo") 15 else 23
    val horaTermino = horaInicial + duracao

    if (!(horaInicial >= 7 && horaTermino <= limiteFechamento)) {
        println("${VERMELHO}[✗] Horário indisponível. O auditório fecha às ${limiteFechamento}h.${RESET}")
        return
    }

    println("Qual o nome da empresa contratante?")
    val empresa = readln().trim()

    if (empresa.isBlank()) {
        println("${VERMELHO}[✗] O nome da empresa é obrigatório.${RESET}")
        return
    }

    println("${VERDE}[✓] Auditório reservado para ${NEGRITO}$empresa${RESET}${VERDE} — $dia às ${horaInicial}h.${RESET}")

    // Cálculos
    val garconsBase    = ceil(convidados.toDouble() / 12).toInt()
    val garconsReforco = duracao / 2
    val totalGarcons   = garconsBase + garconsReforco
    val custoGarcons   = totalGarcons * duracao * 10.50

    val cafeL       = convidados * 0.2
    val aguaL       = convidados * 0.5
    val salgadosUn  = convidados * 7
    val custoCafe   = cafeL * 0.80
    val custoAgua   = aguaL * 0.40
    val custoSalgados = (salgadosUn.toDouble() / 100) * 34.00
    val custoBuffet = custoCafe + custoAgua + custoSalgados
    val totalEvento = custoGarcons + custoBuffet

    println("\n${NEGRITO}${CIANO}--- Relatório do Evento ---${RESET}")
    println("  Auditório  : ${NEGRITO}$auditorio${RESET}")
    println("  Empresa    : ${NEGRITO}$empresa${RESET}")
    println("  Data/Hora  : $dia das ${horaInicial}h às ${horaTermino}h")
    println("  Convidados : $convidados pessoas")
    println("  Garçons    : $totalGarcons necessários")
    println("  Custo garçons : R$ ${String.format("%.2f", custoGarcons)}")
    println("\n${NEGRITO}  Buffet:${RESET}")
    println("  ☕ Café    : ${String.format("%.1f", cafeL)} L")
    println("  💧 Água   : ${String.format("%.1f", aguaL)} L")
    println("  🥪 Salgados: $salgadosUn un")
    println("  Custo buffet : R$ ${String.format("%.2f", custoBuffet)}")
    println("\n  ${NEGRITO}Total Geral : ${VERDE}R$ ${String.format("%.2f", totalEvento)}${RESET}")

    println("\nPosso confirmar a reserva do evento?")
    if (confirma(readln())) {
        receitaEventosAcumulada += totalEvento
        totalEventosConfirmados++
        println("${VERDE}[✓] Evento confirmado com sucesso! Boa festa! 🎉${RESET}")
    } else {
        println("${AMARELO}Reserva não efetuada. Retornando ao menu.${RESET}")
    }
}