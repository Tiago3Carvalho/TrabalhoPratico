import java.util.Scanner;

public class TrabalhoPratico {
    static Scanner ler = new Scanner(System.in);
    public static void main (String[] args) {
        String terrenoDataHora = ler.nextLine();
        String[] infos = terrenoDataHora.split("; ");
        ler.nextLine();
        String linhasColunas = ler.nextLine();
        String[] LC = linhasColunas.split(" ");
        int L = Integer.parseInt(LC[0]);
        int C = Integer.parseInt(LC[1]);
        ler.nextLine();
        int[][] temperaturas = criarEPreencherMatriz(L, C);
    }

    private static int[][] criarEPreencherMatriz(int L, int C) {
        int[][] matriz = new int[L][C];
        for (int i = 0; i < L; i++) {
            String temperaturasL = ler.nextLine();
            String[] temperaturasC = temperaturasL.split(" ");
            for (int j = 0; j < C; j++) {
                int temperatura = Integer.parseInt(temperaturasC[j]);
                matriz[i][j] = temperatura;
                System.out.print (matriz[i][j] + " ");
            }
            System.out.println();
        }
        return matriz;
    }
}
