import java.io.*;
import java.util.*;

public class GastoDAO {

    private static final String ARQUIVO = "gastos.txt";

    public static List<Gasto> listar() {
        List<Gasto> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                lista.add(Gasto.fromString(linha));
            }
        } catch (Exception e) {}
        return lista;
    }

    public static void salvar(Gasto g) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            bw.write(g.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void atualizar(List<Gasto> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Gasto g : lista) {
                bw.write(g.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
