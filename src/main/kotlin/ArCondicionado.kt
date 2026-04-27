package Hotel

data class Orcamento(
    val empresa: String,
    val total: Double
)

fun gerenciarArCondicionado(nomeFuncionario: String) {
    val orcamentos = mutableListOf<Orcamento>()

    while (true) {
        println("\n[Ar-Condicionado - Hotel Abacaxi]")

        print("Empresa: ")
        val empresa = readln().trim()
        if (empresa.isBlank()) {
            println("Nome da empresa inválido.")
            continue
        }

        print("Valor por aparelho: ")
        val valorAparelho = readln().toDoubleOrNull() ?: 0.0

        print("Quantidade de aparelhos: ")
        val quantidade = readln().toIntOrNull() ?: 0

        print("Percentual de desconto (%): ")
        val percentualDesconto = readln().toDoubleOrNull() ?: 0.0

        print("Quantidade mínima para desconto: ")
        val minDesconto = readln().toIntOrNull() ?: 0

        print("Valor fixo de deslocamento: ")
        val deslocamento = readln().toDoubleOrNull() ?: 0.0

        val bruto = valorAparelho * quantidade

        val valorDesconto = if (quantidade >= minDesconto) {
            bruto * (percentualDesconto / 100)
        } else {
            0.0
        }

        val totalFinal = bruto - valorDesconto + deslocamento

        println("O serviço de $empresa custará R$ ${String.format("%.2f", totalFinal)}")

        // orçamento na lista
        orcamentos.add(Orcamento(empresa, totalFinal))

        print("\nDeseja informar novos dados, $nomeFuncionario? (S/N): ")
        if (readln().uppercase() != "S") break
    }

    // exibição dos resultados
    if (orcamentos.size >= 2) {
        val melhor = orcamentos.minBy { it.total }
        val pior = orcamentos.maxBy { it.total }


        val diferencaPercentual = ((pior.total - melhor.total) / melhor.total) * 100

        println("\n--- Resultado dos Orçamentos ---")
        println("Melhor orçamento: ${melhor.empresa} — R$ ${String.format("%.2f", melhor.total)}")
        println("Maior orçamento: ${pior.empresa} — R$ ${String.format("%.2f", pior.total)}")
        println("A diferença percentual entre a melhor e a pior proposta é de ${String.format("%.2f", diferencaPercentual)}%")
    } else {
        println("\nSão necessários ao menos dois orçamentos para comparação.")
    }

    println("Retornando ao menu principal...")
}