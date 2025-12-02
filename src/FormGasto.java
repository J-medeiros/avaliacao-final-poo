import javax.swing.*;
import java.awt.*;

public class FormGasto extends JFrame {

    private Gasto gastoOriginal; // usado quando for edição

    // construtor para novo gasto
    public FormGasto() {
        this(null);
    }

    // construtor para edição (recebe um gasto existente)
    public FormGasto(Gasto gasto) {
        this.gastoOriginal = gasto;

        setTitle(gasto == null ? "Novo Gasto" : "Editar Gasto");
        setSize(400, 300);
        setLayout(new GridLayout(6, 2));

        JTextField descricao = new JTextField();
        JTextField valor = new JTextField();
        JTextField data = new JTextField();
        JTextField categoria = new JTextField();
        JButton salvar = new JButton(gasto == null ? "Salvar" : "Atualizar");

        // Caso seja edição, preencher campos
        if (gasto != null) {
            descricao.setText(gasto.getDescricao());
            valor.setText(String.valueOf(gasto.getValor()));
            data.setText(gasto.getData());
            categoria.setText(gasto.getCategoria());
        }

        add(new JLabel("Descrição:")); add(descricao);
        add(new JLabel("Valor:")); add(valor);
        add(new JLabel("Data (dd/mm/aaaa):")); add(data);
        add(new JLabel("Categoria:")); add(categoria);
        add(new JLabel(""));
        add(salvar);

        salvar.addActionListener(e -> {
            try {
                int id = (gastoOriginal == null)
                        ? GastoDAO.listar().size() + 1
                        : gastoOriginal.getId();

                double v = Double.parseDouble(valor.getText());

                Gasto g = new Gasto(
                        id,
                        descricao.getText(),
                        v,
                        data.getText(),
                        categoria.getText()
                );

                if (gastoOriginal == null) {
                    GastoDAO.adicionar(g); // novo gasto
                } else {
                    GastoDAO.atualizar(g); // atualizar gasto existente
                }

                GastoDAO.salvar();

                JOptionPane.showMessageDialog(null,
                        gastoOriginal == null ? "Gasto salvo!" : "Gasto atualizado!");

                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar!");
            }
        });
    }
}
