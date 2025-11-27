import java.util.List;
import javax.swing.*;


public class ListaGasto extends JFrame {

    public ListaGasto() {
        setTitle("Lista de Gastos");
        setSize(500, 300);

        List<Gasto> lista = GastoDAO.listar();
        String[] col = {"ID", "Descrição", "Valor", "Data", "Categoria"};

        String[][] dados = new String[lista.size()][5];

        for (int i = 0; i < lista.size(); i++) {
            Gasto g = lista.get(i);
            dados[i][0] = String.valueOf(g.getId());
            dados[i][1] = g.getDescricao();
            dados[i][2] = String.valueOf(g.getValor());
            dados[i][3] = g.getData();
            dados[i][4] = g.getCategoria();
        }

        JTable tabela = new JTable(dados, col);
        add(new JScrollPane(tabela));
    }
}
