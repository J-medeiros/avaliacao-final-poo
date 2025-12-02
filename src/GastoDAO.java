import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GastoDAO {

    private static final String FILE = "gastos.txt";

    // Lista carregada do arquivo
    private static List<Gasto> lista = new ArrayList<>();

    // Carrega ao iniciar (se existir)
    static {
        carregar();
    }

    public static void adicionar(Gasto g) {
        lista.add(g);
    }

    public static List<Gasto> listar() {
        return lista;
    }

    public static void remover(int id) {
        lista.removeIf(g -> g.getId() == id);
    }

    public static Gasto buscar(int id) {
        for (Gasto g : lista)
            if (g.getId() == id) return g;
        return null;
    }

    public static void salvar() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            for (Gasto g : lista) {
                pw.println(g.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void carregar() {
        File f = new File(FILE);
        if (!f.exists()) return; // Não faz nada

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                lista.add(Gasto.fromString(linha));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void atualizar(Gasto g) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == g.getId()) {
                lista.set(i, g);
                break;
            }
        }
    }

    public static void excluir(int id) {
        lista.removeIf(g -> g.getId() == id);
    }
    public static Gasto buscarPorId(int id) {
        for (Gasto g : lista) {
            if (g.getId() == id)
                return g;
        }
        return null;
    }
}
