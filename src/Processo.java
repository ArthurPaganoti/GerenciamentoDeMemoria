public class Processo {
    private int id;
    private int tamanho;

    public Processo(int id, int tamanho) {
        this.id = id;
        this.tamanho = tamanho;
    }

    public int getId() {
        return id;
    }

    public int getTamanho() {
        return tamanho;
    }

    @Override
    public String toString() {
        return "Processo{id=" + id + ", tamanho=" + tamanho + "}";
    }
}
