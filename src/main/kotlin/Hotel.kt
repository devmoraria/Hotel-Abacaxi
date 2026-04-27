package Hotel


// cores
private const val RESET = "\u001B[0m"
private const val VERDE = "\u001B[32m"
private const val AMARELO = "\u001B[33m"
private const val VERMELHO = "\u001B[31m"
private const val CIANO = "\u001B[36m"
private const val ROXO = "\u001B[35m"
private const val NEGRITO = "\u001B[1m"

// sgdb na ram
val listaReservasMemoria = mutableListOf<Reserva>()
val quartosOcupados = IntArray(20) { 0 }
val listaHospedesMemoria = mutableListOf<Hospede>()
var receitaEventosAcumulada = 0.0
var totalEventosConfirmados = 0


data class Reserva(
    val nomeHospede: String,
    val quarto: Int,
    val tipo: String,
    val dias: Int,
    val total: Double
)

data class Hospede(
    var nome: String,
    val dataCadastro: java.time.LocalDateTime = java.time.LocalDateTime.now()
)

fun main() {
    inicio()
}

var acesso = false

fun inicio() {
    // Identidade Visual que me deu muito trabalho
    println("""
        ${CIANO}===================================================
                                                           
           ${AMARELO}${NEGRITO}🍍 BEM-VINDO AO HOTEL ABACAXI${RESET}${CIANO}      
           ${NEGRITO}Sua estadia, é AQUI!${RESET}${CIANO}                           
                                                           
        ===================================================${RESET}
    """.trimIndent())

    print("\nOlá! Para começar, como posso te chamar? ")
    val nome = readln().ifBlank { "Colaborador" }

    // Autenticação
    for (tentativa in 1..3) {
        print("\n${NEGRITO}$nome${RESET}, por segurança, digite sua senha ($tentativa/3): ")
        val senha = readln()

        if (senha == "2678") {
            acesso = true
            println("\n${VERDE}[✓] Acesso concedido! Carregando painel de controle...${RESET}")
            Thread.sleep(800)
            break
        } else {
            println("${VERMELHO}[!] Senha incorreta. Verifique o Caps Lock.${RESET}")
        }
    }

    if (!acesso) {
        println("\n${VERMELHO}xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")
        println("X [X] SISTEMA BLOQUEADO POR SEGURANÇA           X")
        println("X Entre em contato com o suporte da TI.         X")
        println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx${RESET}")
        return
    }

    while (true) {
        println("\n${CIANO}===================================================${RESET}")
        println("   ${NEGRITO}PAINEL DE GESTÃO${RESET} - ${AMARELO}HOTEL ABACAXI 🍍${RESET}")
        println("   ${CIANO}Operador(a):${RESET} ${NEGRITO}$nome${RESET}")
        println("${CIANO}===================================================${RESET}")
        println("${VERDE} 1. 🏨 Reservas de Quartos")
        println(" 2. 👤 Cadastro de Hóspedes")
        println(" 3. 📅 Gerenciar Eventos")
        println(" 4. ❄️ Manutenção de Ar-Condicionado")
        println(" 5. ⛽ Abastecimento de Automóveis")
        println(" 6. ✅ Realizar Checkout")
        println(" 7. 📊 Relatórios Operacionais")
        println(" 8. 🤖 Assistente Virtual${RESET}")
        println("${CIANO}---------------------------------------------------${RESET}")
        println("${ROXO} 0. 🚪 Sair do Sistema${RESET}")
        println("${CIANO}===================================================${RESET}")

        print("${NEGRITO}Selecione uma opção: ${RESET}")
        val escolha = readln().toIntOrNull()

        when (escolha) {
            1 -> cadastrarQuartos(nome)
            2 -> gerenciarHospedes()
            3 -> gerenciarEventos()
            4 -> gerenciarArCondicionado(nome)
            5 -> abastecimentoDeAutomoveis(nome)
            6 -> realizarCheckout(nome)
            7 -> exibirRelatoriosOperacionais(nome)
            8 -> chatbot(nome)
            0 -> {
                sairDoHotel(nome)
                break
            }
            else -> println("\n${VERMELHO}[!] Opção '$escolha' inválida. Tente de 0 a 8.${RESET}")
        }
    }
}

fun sairDoHotel(nome: String) {
    println("\n${CIANO}---------------------------------------------------${RESET}")
    println("Encerrando sessão de ${NEGRITO}$nome${RESET}...")
    println("${AMARELO}${NEGRITO}No Hotel Abacaxi, a gestão é nota dez,")
    println("Eficiência no código e conforto aos seus pés! 🍍✨${RESET}")
    println("\nTenha uma excelente semana! Até logo. 👋")
    println("${CIANO}---------------------------------------------------${RESET}")
}