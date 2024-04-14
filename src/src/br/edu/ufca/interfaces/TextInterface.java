package src.br.edu.ufca.interfaces;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import src.br.edu.ufca.negocios.Hotel;
import src.br.edu.ufca.negocios.Quarto;
import src.br.edu.ufca.negocios.QuartoBasico;
import src.br.edu.ufca.negocios.QuartoDuplo;
import src.br.edu.ufca.negocios.QuartoFamilia;
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
                    Util.limparTela();
                    listarTodosQuartos();
                    break;
                case "2":
                    Util.limparTela();
                    adicionarQuarto();
                    break;
                case "3":
                    Util.limparTela();
                    removerQuarto();
                    break;
                case "4":
                    Util.limparTela();
                    criarReserva();
                    break;
                case "5":
                    Util.limparTela();
                    cancelarReserva();
                    break;
                case "6":
                    Util.limparTela();
                    prolongarReserva();
                    break;
                case "7":
                    Util.limparTela();
                    listarTodasReservas();
                    break;
                case "8":
                    System.out.println("Saindo do sistema...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            Util.pausa(scanner);
        }
    }

    private void listarTodosQuartos() {
        try {
            List<Quarto> quartos = hotel.listarTodosQuartos();

            System.out.println("Lista de todos os quartos:");

            for (Quarto quarto : quartos) {
                System.out.print("\nNúmero do quarto: " + quarto.getNumero() + " - ");

                if (quarto instanceof QuartoBasico) System.out.print("Quarto Basico");
                if (quarto instanceof QuartoDuplo) System.out.print("Quarto Duplo");
                if (quarto instanceof QuartoFamilia) System.out.print("Quarto Familiar");
                
                System.out.println("------------------------");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        
    }
    
    private void adicionarQuarto() {
        System.out.print("Digite o número do quarto: ");
        int numero = Util.scanInt(scanner);
        
        System.out.println("Qual o tipo do quarto?");
        System.out.println("1 - Basico");
        System.out.println("2 - Duplo");
        System.out.println("3 - Familia");
        System.out.print("\nDigite o tipo do quarto: ");
    
        int opcao = Util.scanInt(scanner);

        try {
            hotel.adicionarQuarto(numero, opcao);
            System.out.println("Quarto adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }    
        
    }
    
    private void removerQuarto() {

        System.out.print("Digite o número do quarto que deseja remover: ");
        
        try {
            int numero = Util.scanInt(scanner);
            
            hotel.removerQuarto(numero);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Quarto removido com sucesso!");
    }
    
    private void criarReserva() {
        System.out.print("\nDigite o ID da reserva: ");
        int id = Util.scanInt(scanner);

        System.out.print("\nDigite o número do quarto: ");
        int numeroQuarto = scanner.nextInt();

        System.out.print("\nDigite a data de início da reserva (no formato dd/MM/yyyy): ");
        String dataInicioStr = scanner.next();
        
        System.out.print("\nDigite a data de fim da reserva (no formato dd/MM/yyyy): ");
        String dataFimStr = scanner.next();
        
        Date dataInicio;
        Date dataFim;

        try {
            dataInicio = new SimpleDateFormat("dd/MM/yyyy").parse(dataInicioStr);
            dataFim = new SimpleDateFormat("dd/MM/yyyy").parse(dataFimStr);
        } catch (ParseException e) {
            System.out.println("Falaha ao criar reserva, escreva uma data no formato dd/MM/yyyy");
            e.printStackTrace();
            return;
        }

        System.out.print("\nDigite o nome do cliente: ");
        String cliente = scanner.next();

        try {
            hotel.criarReserva(id, numeroQuarto, dataInicio, dataFim, cliente);
            System.out.println("Reserva criada com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void cancelarReserva() {
        System.out.print("\nDigite o ID da reserva que deseja cancelar: ");
        int id = Util.scanInt(scanner);

        try {
            hotel.cancelarReserva(id);
            System.out.println("Reserva cancelada com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void prolongarReserva() {
        System.out.print("Digite o ID da reserva que deseja prolongar: ");
        int id = Util.scanInt(scanner);
        
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
