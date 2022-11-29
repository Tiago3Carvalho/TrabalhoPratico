import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TrabalhoPratico {
    static File file = new File("c:/Users/tiago/IdeaProjects/APROG_LEI_1DF_1221141_1221124/inputTrabalhoPratico.txt");
    static Scanner ler;

    static {
        try {
            ler = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found.");
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        ler.nextLine();
        String linhasColunas = ler.nextLine();
        String[] LC = linhasColunas.split(" ");
        int L = Integer.parseInt(LC[0]);
        int C = Integer.parseInt(LC[1]);
        int[][] temperaturas = criarEPreencherMatriz(L, C);
        System.out.println("b)");
        visualizarMT(temperaturas);
        System.out.println("c)");
        obterMA(temperaturas);
        int[][] temperaturasAposVariacao10GrausNeg = matrizAposVariacao10GrausNeg(temperaturas);
        System.out.println("d)");
        visualizarMT(temperaturasAposVariacao10GrausNeg);
        String[][] alertasAposVariacao10GrausNeg = obterMA(temperaturasAposVariacao10GrausNeg);
        System.out.println("e)");
        percentagemAlertasAposVariacao10GrausNeg(temperaturasAposVariacao10GrausNeg, alertasAposVariacao10GrausNeg);
        System.out.println("f)");
        tudoCatastrofico(temperaturasAposVariacao10GrausNeg);
        System.out.println("g)");
        int[][] temperaturasAposVariacao10GrausPos = matrizAposVariacao10GrausPos(temperaturasAposVariacao10GrausNeg);
        String[][] alertasAposVariacao10GrausPos = obterMA(temperaturasAposVariacao10GrausPos);
        percentagemAlertasAposVariacao10GrausPos(temperaturasAposVariacao10GrausPos, alertasAposVariacao10GrausPos, alertasAposVariacao10GrausNeg);
        System.out.println("h)");
        obterMAAposAgravamentoVento(alertasAposVariacao10GrausPos);
        System.out.println("i)");
        largarAgua(temperaturas);
        System.out.println("j)");
        colunaSegura(alertasAposVariacao10GrausPos);
        ler.close();
    }

    private static int[][] criarEPreencherMatriz(int L, int C) throws FileNotFoundException {
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

    private static int[][] matrizAposVariacao10GrausNeg(int[][] temperaturas) {
        int[][] matriz = temperaturas;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = temperaturas[i][j] - 10;
            }
        }
        return matriz;
    }

    private static int[][] matrizAposVariacao10GrausPos(int[][] temperaturas) {
        int[][] matriz = temperaturas;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = temperaturas[i][j] + 10;
            }
        }
        return matriz;
    }

    public static void visualizarMT(int[][] temperaturas) {
        for (int i = 0; i < temperaturas.length; i++) {
            for (int j = 0; j < temperaturas[i].length; j++) {
                System.out.printf("%4d", temperaturas[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static String[][] obterMA(int[][] temperaturas) {
        String[][] alerta = new String[temperaturas.length][temperaturas[0].length];
        for (int i = 0; i < temperaturas.length; i++) {
            for (int j = 0; j < temperaturas[i].length; j++) {
                if (temperaturas[i][j] < 20) {
                    alerta[i][j] = "M";
                    System.out.print(alerta[i][j]);
                } else if (temperaturas[i][j] < 30) {
                    alerta[i][j] = "H";
                    System.out.print(alerta[i][j]);
                } else if (temperaturas[i][j] < 40) {
                    alerta[i][j] = "E";
                    System.out.print(alerta[i][j]);
                } else {
                    alerta[i][j] = "C";
                    System.out.print(alerta[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println();
        return alerta;
    }

    private static void obterMAAposAgravamentoVento(String[][] alertaAposVariacao10GrausPos) {
        String[][] alertaAposAgravamentoVento = new String[alertaAposVariacao10GrausPos.length][alertaAposVariacao10GrausPos[0].length];
        for (int i = 0; i < alertaAposVariacao10GrausPos[0].length; i++) {
            System.out.print(alertaAposVariacao10GrausPos[0][i]);
        }
        System.out.println();
        for (int j = 1; j < alertaAposAgravamentoVento.length; j++) {
            for (int k = 0; k < alertaAposAgravamentoVento[j].length; k++) {
                if (alertaAposVariacao10GrausPos[j - 1][k].equals("C")) {
                    alertaAposAgravamentoVento[j][k] = "C";
                } else {
                    alertaAposAgravamentoVento[j][k] = alertaAposVariacao10GrausPos[j][k];
                }
                System.out.print(alertaAposAgravamentoVento[j][k]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void percentagemAlertasAposVariacao10GrausNeg(int[][] temperaturasAposVariacao, String[][] alertasAposVariacao10GrausNeg) {
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
        System.out.printf("MODERATE" + "%6s", ":");
        System.out.printf("%7.2f%%%n", (double) contM * 100 / somaAlertas);
        System.out.printf("HIGH" + "%10s", ":");
        System.out.printf("%7.2f%%%n", (double) contH * 100 / somaAlertas);
        System.out.printf("EXTREME" + "%7s", ":");
        System.out.printf("%7.2f%%%n", (double) contE * 100 / somaAlertas);
        System.out.printf("CATASTROPHIC" + "%2s", ":");
        System.out.printf("%7.2f%%%n", (double) contC * 100 / somaAlertas);
        System.out.println();
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
        System.out.println("To get all terrain on CATASTROPHIC alert, the temperature has to rise : " + temp + "ºC");
        System.out.println();
    }

    private static void percentagemAlertasAposVariacao10GrausPos(int[][] temperaturas, String[][] alertas, String[][] alertasAposVariacao10GrausNeg) {
        int contAlertasAlterados = 0, somaAlertas = 0;
        for (int i = 0; i < temperaturas.length; i++) {
            for (int j = 0; j < temperaturas[i].length; j++) {
                if (!(alertas[i][j].equals(alertasAposVariacao10GrausNeg[i][j]))) {
                    contAlertasAlterados++;
                }
                somaAlertas++;
            }
        }
        System.out.printf("Alert Levels changes due to temperature variations by 10ºC : " + "%.2f%%%n", (double) contAlertasAlterados * 100 / somaAlertas);
        System.out.println();
    }

    private static void largarAgua(int[][] temperaturas) {
        visualizarMT(temperaturas);
        int[] norteOesteMaior50 = new int[2];
        int somaMaior50AVolta, maiorSoma = 0;
        for (int i = 1; i < temperaturas.length - 1; i++) {
            for (int j = 1; j < temperaturas[i].length - 1; j++) {
                somaMaior50AVolta = 0;
                if (temperaturas[i - 1][j - 1] > 50) {
                    somaMaior50AVolta += 1;
                }
                if (temperaturas[i - 1][j] > 50) {
                    somaMaior50AVolta += 1;
                }
                if (temperaturas[i - 1][j + 1] > 50) {
                    somaMaior50AVolta += 1;
                }
                if (temperaturas[i][j - 1] > 50) {
                    somaMaior50AVolta += 1;
                }
                if (temperaturas[i][j + 1] > 50) {
                    somaMaior50AVolta += 1;
                }
                if (temperaturas[i + 1][j - 1] > 50) {
                    somaMaior50AVolta += 1;
                }
                if (temperaturas[i + 1][j] > 50) {
                    somaMaior50AVolta += 1;
                }
                if (temperaturas[i + 1][j + 1] > 50) {
                    somaMaior50AVolta += 1;
                }
                if (somaMaior50AVolta > maiorSoma) {
                    maiorSoma = somaMaior50AVolta;
                    norteOesteMaior50[0] = i;
                    norteOesteMaior50[1] = j;
                } else if (somaMaior50AVolta == maiorSoma) {
                    if (i + j < norteOesteMaior50[0] + norteOesteMaior50[1]) {
                        norteOesteMaior50[0] = i;
                        norteOesteMaior50[1] = j;
                    }
                }
            }
        }
        if (maiorSoma == 0) {
            System.out.println("no fire");
        } else {
            System.out.println("drop water at (" + norteOesteMaior50[0] + " , " + norteOesteMaior50[1] + ")");
        }
        System.out.println();
    }

    private static void colunaSegura(String[][] alertasAposVariacao10GrausPos) {
        int coluna = -1, contC;
        for (int i = 0; i < alertasAposVariacao10GrausPos[0].length; i++) {
            contC = 0;
            for (int j = 0; j < alertasAposVariacao10GrausPos.length; j++) {
                if (alertasAposVariacao10GrausPos[j][i].equals("C")) {
                    contC++;
                }
            }
            if (contC == 0) {
                coluna = i;
            }
        }
        if (coluna < 0) {
            System.out.println("safe column = NONE");
        } else {
            System.out.println("safe column = (" + coluna + ")");
        }
    }
}