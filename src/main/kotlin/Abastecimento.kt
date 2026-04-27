package Hotel

fun abastecimentoDeAutomoveis(nomeFuncionario: String) {
    println("\n[Abastecimento - Hotel Abacaxi]")

    // Posto Wayne Oil
    println("Informe o preço do álcool no posto Wayne Oil:")
    val alcoolWayne = readln().toDoubleOrNull() ?: 0.0
    println("Informe o preço da gasolina no posto Wayne Oil:")
    val gasolinaWayne = readln().toDoubleOrNull() ?: 0.0

    // Posto Stark Petrol
    println("Informe o preço do álcool no posto Stark Petrol:")
    val alcoolStark = readln().toDoubleOrNull() ?: 0.0
    println("Informe o preço da gasolina no posto Stark Petrol:")
    val gasolinaStark = readln().toDoubleOrNull() ?: 0.0

    val tanqueLitros = 42

    // Wayne Oil
    val melhorCombustivelWayne = if (alcoolWayne <= gasolinaWayne * 0.70) "Álcool" else "Gasolina"
    val precoWayne = if (melhorCombustivelWayne == "Álcool") alcoolWayne else gasolinaWayne
    val totalWayne = precoWayne * tanqueLitros

    // Stark Petrol
    val melhorCombustivelStark = if (alcoolStark <= gasolinaStark * 0.70) "Álcool" else "Gasolina"
    val precoStark = if (melhorCombustivelStark == "Álcool") alcoolStark else gasolinaStark
    val totalStark = precoStark * tanqueLitros

    // Exibição dos resultados
    println("\nWayne Oil: melhor opção = $melhorCombustivelWayne | Total (42L) = R$ ${String.format("%.2f", totalWayne)}")
    println("Stark Petrol: melhor opção = $melhorCombustivelStark | Total (42L) = R$ ${String.format("%.2f", totalStark)}")

    // Ranking
    if (totalWayne < totalStark) {
        println("\n$nomeFuncionario, é mais barato abastecer com ${melhorCombustivelWayne.lowercase()} no posto Wayne Oil.")
    } else if (totalStark < totalWayne) {
        println("\n$nomeFuncionario, é mais barato abastecer com ${melhorCombustivelStark.lowercase()} no posto Stark Petrol.")
    } else {
        println("\n$nomeFuncionario, o custo é igual em ambos os postos (R$ ${String.format("%.2f", totalWayne)}).")
    }
}