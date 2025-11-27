import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class Login extends JFrame {

    public Login() {
        setTitle("Login");
        setSize(300, 200);
        setLayout(new GridLayout(3, 2));

        JTextField txtUser = new JTextField();
        JPasswordField txtSenha = new JPasswordField();
        JButton btnLogin = new JButton("Entrar");
        JLabel lblErro = new JLabel("");

        add(new JLabel("Usuário:")); add(txtUser);
        add(new JLabel("Senha:")); add(txtSenha);
        add(btnLogin); add(lblErro);

        btnLogin.addActionListener(e -> {
            if (autenticar(txtUser.getText(), new String(txtSenha.getPassword())))
                new MenuPrincipal().setVisible(true);
            else
                lblErro.setText("Credenciais inválidas!");
        });
    }

    private boolean autenticar(String u, String s) {
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] p = linha.split(";");
                if (p[0].equals(u) && p[1].equals(s))
                    return true;
            }
        } catch (Exception e) {}
        return false;
    }
}
