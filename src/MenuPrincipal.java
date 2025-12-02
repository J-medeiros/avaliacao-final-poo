import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {


    public MenuPrincipal() {
        setTitle("Controle de Gastos");
        setSize(400, 300);

        JMenuBar menu = new JMenuBar();
        JMenu cad = new JMenu("Gastos");
        JMenuItem add = new JMenuItem("Adicionar Gasto");
        JMenuItem list = new JMenuItem("Listar Gastos");
        JButton btnSalvar = new JButton("Salvar");

        cad.add(add);
        cad.add(list);
        menu.add(cad);
        setJMenuBar(menu);

        add.addActionListener(e -> new FormGasto().setVisible(true));
        list.addActionListener(e -> new ListaGasto().setVisible(true));

        btnSalvar.addActionListener(e -> {
            GastoDAO.salvar();
            JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");
        });
    }
}
