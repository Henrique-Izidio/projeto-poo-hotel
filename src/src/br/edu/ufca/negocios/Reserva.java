package src.br.edu.ufca.negocios;

import java.util.Date;

public class Reserva {
    private int id;
    private int numQuarto;
    private Date dataInicio;
    private Date dataFim;
    private String cliente;

    public Reserva(int id, int numQuarto, Date dataInicio, Date dataFim, String cliente) {
        this.id = id;
        this.numQuarto = numQuarto;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumQuarto() {
        return numQuarto;
    }

    public void setQuarto(int quarto) {
        this.numQuarto = quarto;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
}
