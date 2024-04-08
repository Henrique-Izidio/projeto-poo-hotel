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

    public void adicionarQuarto(int numero, String tipo) {
        Quarto quarto = new Quarto(numero, tipo);
        quartoRepositorio.adicionar(quarto);
    }

    public void removerQuarto(int numero) {
        Quarto quarto = buscarQuarto(numero);
        if (quarto != null) {
            quartoRepositorio.remover(quarto);
        }
    }

    public Quarto buscarQuarto(int numero) {
        return quartoRepositorio.buscar(numero);
    }

    public List<Quarto> listarTodosQuartos() {
        return quartoRepositorio.listarTodos();
    }

    public List<Reserva> listarTodasReservas() {
        return reservaRepositorio.listarTodos();
    }

    public void criarReserva(int id, int numeroQuarto, Date dataInicio, Date dataFim, String cliente) {
        Quarto quarto = buscarQuarto(numeroQuarto);
        quarto.setDisponivel(false);
        if (quarto != null) {
            Reserva reserva = new Reserva(id, numeroQuarto, dataInicio, dataFim, cliente);
            reservaRepositorio.adicionar(reserva);
        }
    }

    public void cancelarReserva(int id) {
        Reserva reserva = buscarReserva(id);
        Quarto quarto = buscarQuarto(reserva.getNumQuarto());
        quarto.setDisponivel(true);
        if (reserva != null) {
            reservaRepositorio.remover(reserva);
        }
    }

    public Reserva buscarReserva(int id) {
        return reservaRepositorio.buscar(id);
    }

    public void prolongarReserva(int id, Date novaDataFim) {
        Reserva reserva = buscarReserva(id);
        int indice = reservaRepositorio.listarTodos().indexOf(reserva);
        if (reserva != null) {
            reserva.setDataFim(novaDataFim);
            reservaRepositorio.atualizar(reserva, indice);
        }
    }
}
