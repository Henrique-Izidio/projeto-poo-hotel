package src.br.edu.ufca.dados;

import java.util.ArrayList;
import java.util.List;

import src.br.edu.ufca.negocios.Reserva;

public class ReservaRepositorio implements Repositorio<Reserva> {
    private List<Reserva> reservas;

    public ReservaRepositorio() {
        this.reservas = new ArrayList<>();
    }

    @Override
    public void adicionar(Reserva reserva) {
        reservas.add(reserva);
    }

    @Override
    public void remover(Reserva reserva) {
        reservas.remove(reserva);
    }

    @Override
    public Reserva buscar(int id) {
        for (Reserva reserva : reservas) {
            if (reserva.getId() == id) {
                return reserva;
            }
        }
        return null;
    }

    @Override
    public List<Reserva> listarTodos() {
        return reservas;
    }

    @Override
    public void atualizar(Reserva reserva, int indice) {
        reservas.set(indice, reserva);
    }
}
