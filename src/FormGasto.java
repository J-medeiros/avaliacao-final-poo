import javax.swing.*;
import java.awt.*;

public class FormGasto extends JFrame {

    public FormGasto() {
        setTitle("Novo Gasto");
        setSize(300, 300);
        setLayout(new GridLayout(6, 2));

        JTextField descricao = new JTextField();
        JTextField valor = new JTextField();
        JTextField data = new JTextField();
        JTextField categoria = new JTextField();
        JButton salvar = new JButton("Salvar");

        add(new JLabel("Descrição:")); add(descricao);
        add(new JLabel("Valor:")); add(valor);
        add(new JLabel("Data (dd/mm/aaaa):")); add(data);
        add(new JLabel("Categoria:")); add(categoria);
        add(salvar);

        salvar.addActionListener(e -> {
            try {
                int id = GastoDAO.listar().size() + 1;
                double v = Double.parseDouble(valor.getText());

                Gasto g = new Gasto(id, descricao.getText(), v, data.getText(), categoria.getText());
                GastoDAO.salvar(g);

                JOptionPane.showMessageDialog(null, "Gasto salvo!");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar!");
            }
        });
    }
}
