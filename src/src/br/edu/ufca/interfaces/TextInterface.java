package src.br.edu.ufca.interfaces;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import src.br.edu.ufca.negocios.Hotel;
import src.br.edu.ufca.negocios.Quarto;
import src.br.edu.ufca.negocios.Reserva;

public class TextInterface {
    private Hotel hotel;
    private Scanner scanner;

    public TextInterface() {
        this.hotel = new Hotel();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        while (true) {
            Util.limparTela();
            System.out.println("Bem-vindo ao sistema de gerenciamento de reservas do hotel!");
            System.out.println("1. Listar todos os quartos");
            System.out.println("2. Adicionar quarto");
            System.out.println("3. Remover quarto");
            System.out.println("4. Criar reserva");
            System.out.println("5. Cancelar reserva");
            System.out.println("6. Prolongar reserva");
            System.out.println("7. Listar todos as reservas");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.next();
            scanner.nextLine();  // Consume newline left-over
            switch (opcao) {
                case "1":
                    listarTodosQuartos();
                    break;
                case "2":
                    adicionarQuarto();
                    break;
                case "3":
                    removerQuarto();
                    break;
                case "4":
                    criarReserva();
                    break;
                case "5":
                    cancelarReserva();
                    break;
                case "6":
                    prolongarReserva();
                    break;
                case "7":
                    listarTodasReservas();
                    break;
                case "8":
                    System.out.println("Saindo do sistema...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            // Util.aguardar(2);
            Util.pausa(scanner);;
        }
    }

    private void listarTodosQuartos() {
        List<Quarto> quartos = hotel.listarTodosQuartos();
        if (quartos.isEmpty()) {
            System.out.println("Não há quartos disponíveis no momento.");
        } else {
            System.out.println("Lista de todos os quartos:");
            for (Quarto quarto : quartos) {
                System.out.println("Número do quarto: " + quarto.getNumero());
                System.out.println("Tipo do quarto: " + quarto.getTipo());
                System.out.println("Disponível: " + (quarto.isDisponivel() ? "Sim" : "Não"));
                System.out.println("------------------------");
            }
        }
    }
    
    private void adicionarQuarto() {
        System.out.print("Digite o número do quarto: ");
        int numero = scanner.nextInt();
        scanner.nextLine();  // Consume newline left-over
        System.out.print("Digite o tipo do quarto: ");
        String tipo = scanner.nextLine();
        hotel.adicionarQuarto(numero, tipo);
        System.out.println("Quarto adicionado com sucesso!");
    }
    
    private void removerQuarto() {
        System.out.print("Digite o número do quarto que deseja remover: ");
        int numero = scanner.nextInt();
        scanner.nextLine();  // Consume newline left-over
        Quarto quarto = hotel.buscarQuarto(numero);
        if (quarto != null) {
            hotel.removerQuarto(numero);
            System.out.println("Quarto removido com sucesso!");
        } else {
            System.out.println("Quarto não encontrado.");
        }
    }
    
    private void criarReserva() {
        System.out.print("Digite o ID da reserva: ");
        int id = scanner.nextInt();
        System.out.print("Digite o número do quarto: ");
        int numeroQuarto = scanner.nextInt();
        System.out.print("Digite a data de início da reserva (no formato dd/MM/yyyy): ");
        String dataInicioStr = scanner.next();
        Date dataInicio;
        try {
            dataInicio = new SimpleDateFormat("dd/MM/yyyy").parse(dataInicioStr);
        } catch (ParseException e) {
            System.out.println("Erro: Falaha ao criar reserva, escreva uma data no formato dd/MM/yyyy");
            e.printStackTrace();
            return;
        }
        System.out.print("Digite a data de fim da reserva (no formato dd/MM/yyyy): ");
        String dataFimStr = scanner.next();
        Date dataFim;
        try {
            dataFim = new SimpleDateFormat("dd/MM/yyyy").parse(dataFimStr);
        } catch (ParseException e) {
            System.out.println("Erro: Falaha ao criar reserva, escreva uma data no formato dd/MM/yyyy");
            e.printStackTrace();
            return;
        }
        System.out.print("Digite o nome do cliente: ");
        String cliente = scanner.next();
        if(dataFim.before(dataInicio)){
            System.out.println("Não é possivel reservar um quarto nesse periodo");
            return;
        }
        hotel.criarReserva(id, numeroQuarto, dataInicio, dataFim, cliente);
        System.out.println("Reserva criada com sucesso!");
    }
    
    private void cancelarReserva() {
        System.out.print("Digite o ID da reserva que deseja cancelar: ");
        int id = scanner.nextInt();
        Reserva reserva = hotel.buscarReserva(id);
        if (reserva != null) {
            hotel.cancelarReserva(id);
            System.out.println("Reserva cancelada com sucesso!");
        } else {
            System.out.println("Reserva não encontrada.");
        }
    }
    
    private void prolongarReserva() {
        System.out.print("Digite o ID da reserva que deseja prolongar: ");
        int id = scanner.nextInt();
        System.out.print("Digite a nova data de fim da reserva (no formato dd/MM/yyyy): ");
        String novaDataFimStr = scanner.next();
        Date novaDataFim;
        try {
            novaDataFim = new SimpleDateFormat("dd/MM/yyyy").parse(novaDataFimStr);
        } catch (ParseException e) {
            System.out.println("Erro: Falaha ao prolongar reserva, escreva uma data no formato dd/MM/yyyy");
            e.printStackTrace();
            return;
        }
        Reserva reserva = hotel.buscarReserva(id);
        if (reserva != null) {
            if(novaDataFim.before(reserva.getDataInicio())){
                System.out.println("Não foi possivel alterar o periodo de reserva");
                return;
            }
            hotel.prolongarReserva(id, novaDataFim);
            System.out.println("Periodo de reserva alterado com sucesso!");
        } else {
            System.out.println("Reserva não encontrada.");
        }
    }

    private void listarTodasReservas() {
        List<Reserva> reservas = hotel.listarTodasReservas();
        if (reservas.isEmpty()) {
            System.out.println("Não há reservas feitas no momento.");
        } else {
            System.out.println("Lista de todos os quartos:");
            for (Reserva reserva : reservas) {
                System.out.println("Nome do Cliente: " + reserva.getCliente());
                System.out.println("Número do quarto: " + reserva.getNumQuarto());
                System.out.println("De: " + (reserva.getDataInicio()));
                System.out.println("Ate: " + (reserva.getDataFim()));
                System.out.println("------------------------");
            }
        }
    }
    
}
