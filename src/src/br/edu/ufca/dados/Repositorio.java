package src.br.edu.ufca.dados;

import java.util.List;

public interface Repositorio<T> {
    void adicionar(T item);
    void remover(T item);
    T buscar(int id);
    List<T> listarTodos();
    void atualizar(T item, int indice);
}
