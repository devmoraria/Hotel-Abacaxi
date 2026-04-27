package Hotel

fun realizarCheckout(nomeFuncionario: String) {
    println("\n===================================================")
    println("   SISTEMA DE CHECKOUT - HOTEL ABACAXI 🍍✨")
    println("===================================================")

    if (listaReservasMemoria.isEmpty()) {
        println("\n[!] Não há hóspedes no hotel no momento.")
        return
    }

    println("\nHóspedes atuais:")
    listaReservasMemoria.forEach {
        println("Quarto ${it.quarto}: ${it.nomeHospede} (${it.tipo})")
    }

    print("\nDigite o número do quarto para checkout: ")
    val numQuarto = readln().toIntOrNull() ?: 0

    val reservaParaSair = listaReservasMemoria.find { it.quarto == numQuarto }

    if (reservaParaSair != null) {
        println("\n---------------------------------------------------")
        println("Resumo da Estadia:")
        println("Hóspede: ${reservaParaSair.nomeHospede}")
        println("Tipo de Quarto: ${reservaParaSair.tipo}")
        println("Tempo: ${reservaParaSair.dias} diária(s)")
        println("Valor Total: R$ ${String.format("%.2f", reservaParaSair.total)}")
        println("---------------------------------------------------")

        print("Confirmar pagamento e liberação do quarto? (S/N): ")
        val confirma = readln().uppercase()

        if (confirma == "S") {

            // retirando o quarto no array
            quartosOcupados[numQuarto - 1] = 0

            // fora da lista de reservas ativas
            listaReservasMemoria.remove(reservaParaSair)

            println("\n[✓] Checkout realizado com sucesso!")
            println("O Quarto $numQuarto agora está LIVRE.")
            println("Obrigado por escolher o Hotel Abacaxi 🍍, ${reservaParaSair.nomeHospede}!")
        } else {
            println("\n[!] Operação cancelada. O hóspede continua no sistema.")
        }
    } else {
        println("\n[X] Erro: Quarto $numQuarto não possui reserva ativa ou não existe.")
    }

    println("\n$nomeFuncionario, pressione Enter para voltar ao menu principal.")
    readln()
}