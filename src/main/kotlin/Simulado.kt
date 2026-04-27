package Hotel

import kotlin.random.Random
private const val RESET  = "\u001B[0m"
private const val VERDE  = "\u001B[32m"
private const val AMARELO = "\u001B[33m"
private const val VERMELHO = "\u001B[31m"
private const val CIANO  = "\u001B[36m"
private const val NEGRITO = "\u001B[1m"

// nomes fictícios para com reais
private val NOMES_FICTICIOS = listOf(
    "Carlos Lima", "Ana Souza", "Pedro Alves", "Julia Mendes",
    "Rafael Costa", "Beatriz Nunes", "Lucas Ferreira", "Mariana Rocha"
)

private val LOG = mutableListOf<String>() // histórico de eventos
private var rodando = true               // controla o loop

fun modoSimulacao() {
    LOG.clear()
    rodando = true

    // Thread que escuta o teclado — quando Enter for pressionado, para
    val threadTeclado = Thread {
        readln()
        rodando = false
    }
    threadTeclado.isDaemon = true // morre junto com o programa
    threadTeclado.start()

    LOG.add("Sistema iniciado.")

    while (rodando) {
        simularEvento()
        desenharPainel()
        Thread.sleep(2500)
    }

    print("\u001B[2J\u001B[H")
    println("Saindo do modo simulação...")
}

private fun simularEvento() {
    val chance = Random.nextInt(100)

    when {
        // Check-in — 35% de chance
        chance < 35 -> {
            val quartosLivres = (0 until 20).filter { quartosOcupados[it] == 0 }
            if (quartosLivres.isNotEmpty()) {
                val quarto = quartosLivres.random()

                // Mistura: 50% nome fictício, 50% hóspede real cadastrado
                val nome = if (listaHospedesMemoria.isNotEmpty() && Random.nextBoolean()) {
                    listaHospedesMemoria.random().nome
                } else {
                    NOMES_FICTICIOS.random()
                }

                val tipos = listOf("Standard" to 1.00, "Executivo" to 1.35, "Luxo" to 1.65)
                val (tipo, fator) = tipos.random()
                val dias = Random.nextInt(1, 6)
                val total = 150.0 * dias * fator * 1.10

                quartosOcupados[quarto] = 1
                listaReservasMemoria.add(Reserva(nome, quarto + 1, tipo, dias, total))
                LOG.add("✅ Check-in: $nome → Quarto ${quarto + 1} ($tipo)")
            }
        }

        // Checkout — 25% de chance
        chance < 60 -> {
            val reservasAtivas = listaReservasMemoria.toList()
            if (reservasAtivas.isNotEmpty()) {
                val reserva = reservasAtivas.random()
                quartosOcupados[reserva.quarto - 1] = 0
                listaReservasMemoria.remove(reserva)
                LOG.add("🚪 Checkout: ${reserva.nomeHospede} saiu do Quarto ${reserva.quarto}")
            }
        }

        // Evento — 15% de chance
        chance < 75 -> {
            val receita = Random.nextDouble(500.0, 3000.0)
            receitaEventosAcumulada += receita
            totalEventosConfirmados++
            LOG.add("📅 Evento confirmado! +R$ ${String.format("%.2f", receita)}")
        }

        // Nada acontece — 25% de chance
        else -> LOG.add("⏳ Sem novidades no momento...")
    }

    // Mantém o log com no máximo 5 linhas
    if (LOG.size > 5) LOG.removeAt(0)
}

private fun desenharPainel() {
    val ocupados = quartosOcupados.count { it == 1 }
    val taxaOcup = (ocupados.toDouble() / 20) * 100
    val receita  = listaReservasMemoria.sumOf { it.total } + receitaEventosAcumulada

    print("\u001B[2J\u001B[H") // limpa a tela

    println("${NEGRITO}${CIANO}╔══════════════════════════════════════════════╗")
    println("║       HOTEL ABACAXI — AO VIVO 🍍             ║")
    println("║       Pressione Enter para sair               ║")
    println("╚══════════════════════════════════════════════╝${RESET}")

    // Mapa de quartos
    println("\n${NEGRITO}[ QUARTOS ]${RESET}")
    for (i in 0 until 20) {
        val cor = if (quartosOcupados[i] == 1) VERMELHO else VERDE
        print("$cor[${i + 1}]${RESET} ")
        if ((i + 1) % 5 == 0) println()
    }
    println("  ${VERDE}[N]${RESET} Livre   ${VERMELHO}[N]${RESET} Ocupado")

    // Indicadores
    println("\n${NEGRITO}[ INDICADORES ]${RESET}")
    println("Ocupação  : ${String.format("%.1f", taxaOcup)}%  ($ocupados/20 quartos)")
    println("Reservas  : ${listaReservasMemoria.size} ativas")
    println("Eventos   : $totalEventosConfirmados confirmados")
    println("Receita   : ${VERDE}R$ ${String.format("%.2f", receita)}${RESET}")

    // Log de eventos
    println("\n${NEGRITO}[ ÚLTIMOS EVENTOS ]${RESET}")
    LOG.forEach { println("  $it") }
}