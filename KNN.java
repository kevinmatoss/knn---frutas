import java.util.Scanner;

public class KNN {

    public static void main(String[] args) {
        // Matriz de Treinamento
        double[][] dadosFrutas = { //
                { 100, 5, 1 }, // cítrica
                { 150, 6, 1 }, // cítrica
                { 200, 8, 0 }, // não cítrica
                { 120, 4, 0 }, // não cítrica
                { 180, 7, 1 }, // cítrica
                { 170, 6, 1 }, // cítrica
                { 210, 9, 0 }, // não cítrica
                { 130, 5, 0 }, // não cítrica
                { 140, 6, 1 }, // cítrica
                { 160, 7, 0 }, // não cítrica
                { 190, 8, 1 } // cítrica
        };

        // Número de vizinhos a considerar
        int k = 5;

        try (Scanner scanner = new Scanner(System.in)) {
            double peso = obterValor(scanner, "Informe o peso da fruta (g): ");
            double tamanho = obterValor(scanner, "Informe o tamanho da fruta (cm): ");

            double[] novaFruta = { peso, tamanho };

            String resultado = classificarFruta(novaFruta, dadosFrutas, k);
            System.out.println("A nova fruta é: " + resultado);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Ocorreu uma falha inesperada");
        }
    }

    private static double obterValor(Scanner scanner, String label) {
        double valor = 0;

        do {
            System.out.print(label);
            while (!scanner.hasNextDouble()) {
                System.out.print(label);
                scanner.next();
            }
            valor = scanner.nextDouble();
        } while (valor <= 0);

        return valor;
    }

    private static double calcularDistanciaEuclidiana(double[] novaFruta, double[] treino) {
        double somaQuadrados = 0;
        for (int i = 0; i < novaFruta.length; i++) {
            somaQuadrados += Math.pow(Math.abs(novaFruta[i] - treino[i]), 2);
        }
        return Math.sqrt(somaQuadrados);
    }

    private static String classificarFruta(double[] novaFruta, double[][] dadosFrutas, int k) {
        int countCitricas = 0;
        int countNaoCitricas = 0;

        // Calcula as distâncias e verifica se cada fruta é cítrica ou não
        for (double[] element : dadosFrutas) {

            double distancia = calcularDistanciaEuclidiana(novaFruta, element);

            if (distancia <= k) {
                if (element[2] == 1) { // Verifica se a fruta é cítrica
                    countCitricas++;
                } else {
                    countNaoCitricas++;
                }
            }
        }

        // Classificação da nova fruta
        return countCitricas > countNaoCitricas ? "Cítrica" : "Não cítrica";
    }
}
