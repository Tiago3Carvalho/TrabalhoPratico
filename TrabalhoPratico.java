import java.util.Arrays;
import java.util.Scanner;

public class TrabalhoPratico {
    static Scanner ler = new Scanner(System.in);
    public static void main (String[] args) {
        String terrenoDataHora = ler.nextLine();
        String[] infos = terrenoDataHora.split("; ");
        String linhasColunas = ler.nextLine();
        String[] LC = linhasColunas.split(" ");
        int L = Integer.parseInt (LC[0]);
        int C = Integer.parseInt (LC[1]);
        int[][] temperaturas = criarEPreencherMatriz(L, C);
        System.out.println("b)");
        visualizarMT (temperaturas);
        System.out.println();
        System.out.println("c)");
        obterMA (temperaturas, L, C);
        int[][] temperaturasAposVariacao10GrausNeg = matrizAposVariação10GrausNeg(temperaturas);
        System.out.println();
        System.out.println("d)");
        visualizarMT (temperaturasAposVariacao10GrausNeg);
        System.out.println();
        String[][] alertasAposVariacao10GrausNeg = obterMA (temperaturasAposVariacao10GrausNeg, L, C);
        System.out.println();
        System.out.println("e)");
        percentagemAlertasAposVariacao10GrausNeg (temperaturasAposVariacao10GrausNeg, alertasAposVariacao10GrausNeg);
        System.out.println();
        System.out.println("f)");
        tudoCatastrofico (temperaturasAposVariacao10GrausNeg);
        System.out.println();
        System.out.println("g)");
        int[][] temperaturasAposVariacao10GrausPos = matrizAposVariação10GrausPos(temperaturasAposVariacao10GrausNeg);
        String[][] alertas = obterMA (temperaturasAposVariacao10GrausPos, L, C);
        System.out.println();
        percentagemAlertasAposVariacao10GrausPos (temperaturasAposVariacao10GrausPos, alertas, alertasAposVariacao10GrausNeg);
    }

    private static int[][] criarEPreencherMatriz(int L, int C) {
        int[][] matriz = new int[L][C];
        for (int i = 0; i < matriz.length; i++) {
            String temperaturasL = ler.nextLine();
            String[] temperaturasC = temperaturasL.split(" ");
            for (int j = 0; j < matriz[i].length; j++) {
                int temperatura = Integer.parseInt(temperaturasC[j]);
                matriz[i][j] = temperatura;
            }
        }
        return matriz;
    }

    private static int[][] matrizAposVariação10GrausNeg (int[][] temperaturas) {
        int[][] matriz = temperaturas;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = temperaturas[i][j] - 10;
            }
        }
        return matriz;
    }

    private static int[][] matrizAposVariação10GrausPos (int[][] temperaturas) {
        int[][] matriz = temperaturas;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = temperaturas[i][j] + 10;
            }
        }
        return matriz;
    }

    public static void visualizarMT (int[][] temperaturas){
        for(int i = 0; i < temperaturas.length; i++) {
            for(int j = 0; j < temperaturas[i].length ; j++) {
                System.out.printf ("%4d", temperaturas[i][j]);
            }
            System.out.println();
        }
    }

    private static String[][] obterMA (int[][] temperaturas, int L, int C) {
        String[][] alerta = new String[L][C];
        for (int i = 0; i < temperaturas.length; i++) {
            for (int j = 0; j < temperaturas[i].length; j++) {
                if (temperaturas[i][j] < 20) {
                    alerta[i][j] = "M";
                    System.out.print (alerta[i][j]);
                } else if (temperaturas[i][j] < 30) {
                    alerta[i][j] = "H";
                    System.out.print (alerta[i][j]);
                } else if (temperaturas[i][j] < 40) {
                    alerta[i][j] = "E";
                    System.out.print (alerta[i][j]);
                } else {
                    alerta[i][j] = "C";
                    System.out.print (alerta[i][j]);
                }
            }
            System.out.println();
        }
        return alerta;
    }

    private static void percentagemAlertasAposVariacao10GrausNeg (int[][] temperaturasAposVariacao, String[][] alertasAposVariacao10GrausNeg) {
        int somaAlertas = 0, contM = 0, contH = 0, contE = 0, contC = 0;
        for (int i = 0; i < temperaturasAposVariacao.length; i++) {
            for (int j = 0; j < temperaturasAposVariacao[i].length; j++) {
                switch (alertasAposVariacao10GrausNeg[i][j]) {
                    case "M":
                        contM++;
                        break;
                    case "H":
                        contH++;
                        break;
                    case "E":
                        contE++;
                        break;
                    case "C":
                        contC++;
                        break;
                }
                somaAlertas++;
            }
        }
        System.out.printf ("MODERATE" + "%6s", ":");
        System.out.printf ("%7.2f%%%n", (double) contM * 100 / somaAlertas);
        System.out.printf ("HIGH" + "%10s", ":");
        System.out.printf ("%7.2f%%%n", (double) contH * 100 / somaAlertas);
        System.out.printf ("EXTREME" + "%7s", ":");
        System.out.printf ("%7.2f%%%n", (double) contE * 100 / somaAlertas);
        System.out.printf ("CATASTROPHIC" + "%2s", ":");
        System.out.printf ("%7.2f%%%n", (double) contC * 100 / somaAlertas);
    }

    private static void tudoCatastrofico(int[][] temperaturasAposVariacao) {
        int menorValor = temperaturasAposVariacao[0][0];
        for (int i = 0; i < temperaturasAposVariacao.length; i++) {
            for (int j = 0; j < temperaturasAposVariacao[i].length; j++) {
                if (temperaturasAposVariacao[i][j] < menorValor) {
                    menorValor = temperaturasAposVariacao[i][j];
                }
            }
        }
        int temp = 40 - menorValor;
        System.out.println ("To get all terrain on CATASTROPHIC alert, the temperature has to rise : " + temp + "ºC");
    }

    private static void percentagemAlertasAposVariacao10GrausPos(int[][] temperaturas, String[][] alertas, String[][] alertasAposVariacao10GrausNeg) {
        int contAlertasAlterados = 0, somaAlertas = 0;
        for (int i = 0; i < temperaturas.length; i++) {
            for (int j = 0; j < temperaturas[i].length; j++) {
                if (alertas[i][j] != alertasAposVariacao10GrausNeg[i][j]) {
                    contAlertasAlterados++;
                }
                somaAlertas ++;
            }
        }
        System.out.printf ("Alert Levels changes due to temperature variations by 10ºC : " + "%.2f%%", (double) contAlertasAlterados * 100 / somaAlertas);
    }
}