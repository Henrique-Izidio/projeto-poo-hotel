package src.br.edu.ufca.dados;

import java.util.ArrayList;
import java.util.List;

import src.br.edu.ufca.negocios.Quarto;

public class QuartoRepositorio implements Repositorio<Quarto> {
    private List<Quarto> quartos;

    public QuartoRepositorio() {
        this.quartos = new ArrayList<>();
    }

    @Override
    public void adicionar(Quarto quarto) {
        quartos.add(quarto);
    }

    @Override
    public void remover(Quarto quarto) {
        quartos.remove(quarto);
    }

    @Override
    public Quarto buscar(int numero) {
        for (Quarto quarto : quartos) {
            if (quarto.getNumero() == numero) {
                return quarto;
            }
        }
        return null;
    }

    @Override
    public List<Quarto> listarTodos() {
        return quartos;
    }

    @Override
    public void atualizar(Quarto quarto, int indice) {
        if (indice != -1) {
            quartos.set(indice, quarto);
        }
    }
}
