package Hotel

import java.time.format.DateTimeFormatter

private const val RESET    = "\u001B[0m"
private const val VERDE    = "\u001B[32m"
private const val AMARELO  = "\u001B[33m"
private const val VERMELHO = "\u001B[31m"
private const val CIANO    = "\u001B[36m"
private const val NEGRITO  = "\u001B[1m"

fun gerenciarHospedes() {
    println("\n${NEGRITO}${CIANO}===================================================")
    println("║         CADASTRO DE HÓSPEDES  👤            ║")
    println("===================================================${RESET}")
    println("O que deseja fazer? Ex: ${AMARELO}'cadastrar', 'buscar', 'listar', 'atualizar', 'remover'${RESET}")
    println("Digite ${VERMELHO}voltar${RESET} para retornar ao menu.\n")

    while (true) {
        print("${NEGRITO}Hóspedes${RESET} > ")
        val entrada = readln().trim().lowercase()

        when {
            entrada == "voltar" || entrada == "sair" -> {
                println("${AMARELO}Voltando ao menu principal...${RESET}")
                return
            }
            listOf("cadastrar", "adicionar", "novo", "registrar").any { entrada.contains(it) } -> cadastrarHospede()
            listOf("buscar", "pesquisar", "procurar", "encontrar", "exato").any { entrada.contains(it) } -> pesquisarExato()
            listOf("prefixo", "começa", "comeca", "inicial").any { entrada.contains(it) } -> pesquisarPrefixo()
            listOf("listar", "lista", "todos", "ver todos", "mostrar").any { entrada.contains(it) } -> listarHospedes()
            listOf("atualizar", "editar", "alterar", "mudar", "corrigir").any { entrada.contains(it) } -> atualizarHospede()
            listOf("remover", "deletar", "excluir", "apagar").any { entrada.contains(it) } -> removerHospede()
            else -> {
                println("${VERMELHO}[?] Não entendi '${NEGRITO}$entrada${RESET}${VERMELHO}'.${RESET}")
                println("${AMARELO}    Tente: cadastrar, buscar, prefixo, listar, atualizar ou remover.${RESET}\n")
            }
        }
    }
}

fun cadastrarHospede() {
    if (listaHospedesMemoria.size >= 15) {
        println("${VERMELHO}Nossa lista de hóspedes já está cheia (máximo 15).${RESET}")
        return
    }

    println("Qual o nome do hóspede?")
    val nome = readln().trim()

    if (listaHospedesMemoria.any { it.nome.equals(nome, ignoreCase = true) }) {
        println("${AMARELO}Esse hóspede já está cadastrado no sistema.${RESET}")
        return
    }

    listaHospedesMemoria.add(Hospede(nome))
    println("${VERDE}[✓] ${NEGRITO}$nome${RESET}${VERDE} cadastrado com sucesso!${RESET}")
}

fun pesquisarExato() {
    println("Qual o nome exato do hóspede que está procurando?")
    val busca = readln().trim()
    val encontrado = listaHospedesMemoria.find { it.nome.equals(busca, ignoreCase = true) }

    if (encontrado != null) {
        println("${VERDE}[✓] Hóspede ${NEGRITO}${encontrado.nome}${RESET}${VERDE} encontrado no sistema.${RESET}")
    } else {
        println("${VERMELHO}[✗] Nenhum hóspede com esse nome foi encontrado.${RESET}")
    }
}

fun pesquisarPrefixo() {
    println("Por quais letras começa o nome do hóspede?")
    val prefixo = readln().trim()
    val resultados = listaHospedesMemoria.filter { it.nome.startsWith(prefixo, ignoreCase = true) }

    if (resultados.isEmpty()) {
        println("${VERMELHO}[✗] Nenhum hóspede encontrado com esse prefixo.${RESET}")
    } else {
        println("\n${NEGRITO}Resultados para '$prefixo':${RESET}")
        resultados.forEachIndexed { index, h ->
            println("  ${CIANO}[${index + 1}]${RESET} ${h.nome}")
        }
    }
}

fun listarHospedes() {
    if (listaHospedesMemoria.isEmpty()) {
        println("${AMARELO}Ainda não há hóspedes cadastrados no sistema.${RESET}")
        return
    }

    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
    val listaOrdenada = listaHospedesMemoria.sortedBy { it.nome }

    println("\n${NEGRITO}${CIANO}--- Lista de Hóspedes (A-Z) ---${RESET}")
    listaOrdenada.forEachIndexed { index, h ->
        println("  ${CIANO}[${index + 1}]${RESET} ${NEGRITO}${h.nome}${RESET} | cadastrado em ${h.dataCadastro.format(formatter)}")
    }
}

fun atualizarHospede() {
    if (listaHospedesMemoria.isEmpty()) {
        println("${AMARELO}Não há hóspedes cadastrados para atualizar.${RESET}")
        return
    }

    listarHospedes()
    println("\nQual o número do hóspede que deseja atualizar?")
    val index = (readln().toIntOrNull() ?: 0) - 1

    val listaOrdenada = listaHospedesMemoria.sortedBy { it.nome }
    if (index !in listaOrdenada.indices) {
        println("${VERMELHO}[✗] Número inválido.${RESET}")
        return
    }

    val hospedeReal = listaHospedesMemoria.find { it.nome == listaOrdenada[index].nome }
    if (hospedeReal != null) {
        println("Qual o novo nome para ${NEGRITO}${hospedeReal.nome}${RESET}?")
        hospedeReal.nome = readln().trim()
        println("${VERDE}[✓] Nome atualizado com sucesso!${RESET}")
    }
}

fun removerHospede() {
    if (listaHospedesMemoria.isEmpty()) {
        println("${AMARELO}Não há hóspedes cadastrados para remover.${RESET}")
        return
    }

    listarHospedes()
    println("\nQual o número do hóspede que deseja remover?")
    val index = (readln().toIntOrNull() ?: 0) - 1

    val listaOrdenada = listaHospedesMemoria.sortedBy { it.nome }
    if (index !in listaOrdenada.indices) {
        println("${VERMELHO}[✗] Número inválido.${RESET}")
        return
    }

    val nome = listaOrdenada[index].nome
    listaHospedesMemoria.removeIf { it.nome == nome }
    println("${VERDE}[✓] ${NEGRITO}$nome${RESET}${VERDE} removido do sistema.${RESET}")
}