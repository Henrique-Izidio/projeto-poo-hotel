package src.br.edu.ufca.negocios;

import java.util.Date;
import java.util.List;

import src.br.edu.ufca.dados.QuartoRepositorio;
import src.br.edu.ufca.dados.ReservaRepositorio;

public class Hotel {
    private QuartoRepositorio quartoRepositorio;
    private ReservaRepositorio reservaRepositorio;

    public Hotel() {
        this.quartoRepositorio = new QuartoRepositorio();
        this.reservaRepositorio = new ReservaRepositorio();
    }

    public void adicionarQuarto(int numero, int tipo) {

        for (Quarto q : listarTodosQuartos()) {
            if (numero == q.getNumero()) throw new RuntimeException("O quarto já existe");
        }

        Quarto nQuarto;

        switch (tipo) {
            case 1:
                nQuarto = new QuartoBasico(numero);
                break;
            case 2:
                nQuarto = new QuartoDuplo(numero);
                break;
            case 3:
                nQuarto = new QuartoFamilia(numero);
                break;  
            default:
                throw new RuntimeException("O tipo de quarto não existe");
        }

        quartoRepositorio.adicionar(nQuarto);
    }

    public List<Quarto> listarTodosQuartos() {
        
        if (quartoRepositorio.listarTodos().isEmpty()) {
            throw new RuntimeException("Não há quartos disponíveis no momento");
        }

        return quartoRepositorio.listarTodos();
    }

    public Quarto buscarQuarto(int numero) {
        Quarto quarto = quartoRepositorio.buscar(numero);

        if (quarto == null) throw new RuntimeException("O quarto não existe");

        return quarto;
    }    

    public void removerQuarto(int numero) {
        Quarto quarto = buscarQuarto(numero);

        if (quarto == null) throw new RuntimeException("O quarto não existe");

        quartoRepositorio.remover(quarto);
    }

    public void criarReserva(int id, int numeroQuarto, Date dataInicio, Date dataFim, String cliente) {

        buscarQuarto(numeroQuarto);

        for (Reserva reserva : listarTodasReservas()) {
            if (numeroQuarto == reserva.getNumQuarto()) {
                if (dataInicio.before(reserva.getDataFim())) {
                    throw new RuntimeException("O quarto não está disponivel no periodo da reserva");
                } else if (dataFim.after(reserva.getDataInicio())) {
                    throw new RuntimeException("O quarto não está disponivel no periodo da reserva");
                }
            }
        }
        
        Reserva reserva = new Reserva(id, numeroQuarto, dataInicio, dataFim, cliente);

        reservaRepositorio.adicionar(reserva);
        
    }

    public void cancelarReserva(int id) {
        Reserva reserva = buscarReserva(id);

        reservaRepositorio.remover(reserva);
    }

    public List<Reserva> listarTodasReservas() {
        return reservaRepositorio.listarTodos();
    }

    public Reserva buscarReserva(int id) {

        Reserva reserva = reservaRepositorio.buscar(id);

        if (reserva == null) throw new RuntimeException("A reserva não existe");

        return reserva;
    }

    public void prolongarReserva(int id, Date novaDataFim) {
        Reserva reserva = buscarReserva(id);

        int indice = reservaRepositorio.listarTodos().indexOf(reserva);

        for (Reserva r : listarTodasReservas()) {
            if (r.getNumQuarto() == r.getNumQuarto() && id != r.getId()) {
                if (novaDataFim.after(r.getDataInicio())) {
                    throw new RuntimeException("O quarto não está disponivel neste periodo");
                }
            }
        }

        reserva.setDataFim(novaDataFim);
        reservaRepositorio.atualizar(reserva, indice);
    }
}
