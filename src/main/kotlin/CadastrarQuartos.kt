package Hotel

fun confirma(entrada: String): Boolean {
    val sim = listOf("sim", "s", "pode", "confirma", "ok", "isso", "quero", "claro", "com certeza")
    val nao = listOf("nao", "não", "n", "cancela", "desisto", "negativo")
    val lower = entrada.lowercase().trim()
    if (nao.any { lower.contains(it) }) return false
    return sim.any { lower.contains(it) }
}

fun tipo(entrada: String): Pair<Double, String> {
    val lower = entrada.lowercase()
    return when {
        lower.contains("luxo")                                  -> 1.65 to "Luxo"
        lower.contains("executivo") || lower.contains("exec")   -> 1.35 to "Executivo"
        lower.contains("standard") || lower.contains("simples") || lower.contains("normal") -> 1.00 to "Standard"
        lower.trim() == "l"                                     -> 1.65 to "Luxo"
        lower.trim() == "e"                                     -> 1.35 to "Executivo"
        else                                                    -> 1.00 to "Standard"
    }
}

fun extrairNumero(entrada: String): Double? {
    val regex = Regex("""\d+([.,]\d+)?""")
    val match = regex.find(entrada) ?: return null
    return match.value.replace(",", ".").toDoubleOrNull()
}

fun cadastrarQuartos(nomeFuncionario: String) {
    println("\n[Reservas - Hotel Abacaxi]")

    println("Qual será o valor da diária?")
    val valorDiaria = extrairNumero(readln()) ?: -1.0

    println("Por quantas diárias?")
    val qtdDiarias = extrairNumero(readln())?.toInt() ?: -1

    if (valorDiaria < 50.0 || qtdDiarias <= 0 || qtdDiarias > 30) {
        println("Hmm, algo não está certo com esses valores. Retornando ao menu.")
        return
    }

    val nomesHospedesQuarto = mutableListOf<String>()
    while (true) {
        println("Qual o nome do hóspede?")
        val nomeHospedeInput = readln()
        if (nomeHospedeInput.isNotBlank()) nomesHospedesQuarto.add(nomeHospedeInput)

        println("Tem mais algum hóspede neste quarto?")
        if (!confirma(readln())) break
    }

    println("Que tipo de quarto prefere? (Standard, Executivo ou Luxo)")
    val (fator, nomeTipo) = tipo(readln())
    println("[✓] Quarto $nomeTipo selecionado.")

    exibirMapaQuartos()

    var quartoEscolhido: Int
    while (true) {
        println("Qual quarto deseja reservar?")
        quartoEscolhido = extrairNumero(readln())?.toInt() ?: 0

        if (quartoEscolhido !in 1..20) {
            println("Esse quarto não existe. Escolha entre 1 e 20.")
            continue
        }

        if (quartosOcupados[quartoEscolhido - 1] == 1) {
            println("Esse quarto já está ocupado. Veja o mapa e escolha outro.")
            exibirMapaQuartos()
        } else {
            break
        }
    }

    val subtotal = valorDiaria * qtdDiarias * fator
    val taxaServico = subtotal * 0.10
    val totalFinal = subtotal + taxaServico

    println("\nResumo da Reserva:")
    println("Hóspedes: ${nomesHospedesQuarto.joinToString(", ")}")
    println("Quarto: $quartoEscolhido ($nomeTipo)")
    println("Subtotal: R$ ${String.format("%.2f", subtotal)}")
    println("Taxa de Serviço (10%): R$ ${String.format("%.2f", taxaServico)}")
    println("Total a pagar: R$ ${String.format("%.2f", totalFinal)}")

    println("\nPosso confirmar a reserva, $nomeFuncionario?")
    if (confirma(readln())) {
        quartosOcupados[quartoEscolhido - 1] = 1
        listaReservasMemoria.add(
            Reserva(nomesHospedesQuarto.joinToString(", "), quartoEscolhido, nomeTipo, qtdDiarias, totalFinal)
        )
        for (nomeHospede in nomesHospedesQuarto) {
            val jatem = listaHospedesMemoria.any { it.nome.equals(nomeHospede, ignoreCase = true) }
            if (!jatem) listaHospedesMemoria.add(Hospede(nomeHospede))
        }
        println("Reserva efetuada com sucesso. Boa estadia! 🍍")
    } else {
        println("Tudo bem, reserva cancelada.")
    }
}

fun exibirMapaQuartos() {
    println("\nMapa de Quartos (L=Livre, O=Ocupado):")
    for (i in 0 until 20) {
        val status = if (quartosOcupados[i] == 1) "O" else "L"
        print("[${i + 1}:$status] ")
        if ((i + 1) % 5 == 0) println()
    }
    println()
}