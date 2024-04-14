package src.br.edu.ufca.negocios;

public abstract class Quarto {

    protected int numero;

    protected double tamanhho;
    protected double preco;
    protected int camas;
    

    public Quarto(int numero) {
        this.numero = numero;
    }


    public int getNumero() {
        return numero;
    }


    public void setNumero(int numero) {
        this.numero = numero;
    }


    public double getTamanhho() {
        return tamanhho;
    }


    public void setTamanhho(double tamanhho) {
        this.tamanhho = tamanhho;
    }


    public double getPreco() {
        return preco;
    }


    public void setPreco(double preco) {
        this.preco = preco;
    }


    public int getCamas() {
        return camas;
    }


    public void setCamas(int camas) {
        this.camas = camas;
    }

}
