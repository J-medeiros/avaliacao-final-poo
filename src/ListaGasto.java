import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ListaGasto extends JFrame {

    public ListaGasto() {
        setTitle("Lista de Gastos");
        setSize(600, 400);
        setLayout(new BorderLayout());

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
        JScrollPane scroll = new JScrollPane(tabela);

        JButton editar = new JButton("Editar");
        JButton excluir = new JButton("Excluir");

        JPanel botoes = new JPanel();
        botoes.add(editar);
        botoes.add(excluir);

        add(scroll, BorderLayout.CENTER);
        add(botoes, BorderLayout.SOUTH);

        // === AÇÃO EDITAR ===
        editar.addActionListener(e -> {
            int linha = tabela.getSelectedRow();
            if (linha == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um gasto!");
                return;
            }

            int id = Integer.parseInt((String) tabela.getValueAt(linha, 0));

            Gasto g = GastoDAO.buscarPorId(id);

            new FormGasto(g).setVisible(true);
            dispose();
        });

        // === AÇÃO EXCLUIR ===
        excluir.addActionListener(e -> {
            int linha = tabela.getSelectedRow();
            if (linha == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um gasto!");
                return;
            }

            int id = Integer.parseInt((String) tabela.getValueAt(linha, 0));

            int opc = JOptionPane.showConfirmDialog(this,
                    "Deseja realmente excluir?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION
            );

            if (opc == JOptionPane.YES_OPTION) {
                GastoDAO.excluir(id);
                GastoDAO.salvar();
                JOptionPane.showMessageDialog(this, "Excluído!");
                dispose();
                new ListaGasto().setVisible(true); // recarregar lista
            }
        });
    }
}
