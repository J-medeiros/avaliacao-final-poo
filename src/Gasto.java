public class Gasto {
    private int id;
    private String descricao;
    private double valor;
    private String data;
    private String categoria;

    public Gasto(int id, String descricao, double valor, String data, String categoria) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.categoria = categoria;
    }

    // ========= GETTERS =========

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public String getData() {
        return data;
    }

    public String getCategoria() {
        return categoria;
    }

    // Converter para texto ao salvar
    @Override
    public String toString() {
        return id + ";" + descricao + ";" + valor + ";" + data + ";" + categoria;
    }

    // Converter do texto ao ler
    public static Gasto fromString(String linha) {
        String[] p = linha.split(";");
        return new Gasto(
                Integer.parseInt(p[0]),
                p[1],
                Double.parseDouble(p[2]),
                p[3],
                p[4]
        );
    }
}
