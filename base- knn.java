public class KNN {

    private static double distanciaEuclidiana(double[] teste, double[] treino) {
        double somaQuadrados = 0;
        for (int i = 0; i < teste.length; i++) {
            double diferenca = teste[i] - treino[i];
            double quadradoDaDiferenca = diferenca * diferenca;
            somaQuadrados += quadradoDaDiferenca;
        }
        return Math.sqrt(somaQuadrados);
    }

    public static void main(String[] args) {
        double[][] dadosFrutas = {
            {100, 5, 1},  // cítrica
            {150, 6, 1},  // cítrica
            {200, 8, 0},  // não cítrica
            {120, 4, 0},  // não cítrica
            {180, 7, 1},  // cítrica
            {170, 6, 1},  // cítrica
            {210, 9, 0},  // não cítrica
            {130, 5, 0},  // não cítrica
            {140, 6, 1},  // cítrica
            {160, 7, 0},  // não cítrica
            {190, 8, 1}   // cítrica
        };

        double[] novoPonto = {2.0, 2.0};
        int k = 5;  // Número de vizinhos mais próximos

        // Arrays para armazenar as distâncias e os rótulos
        double[] distancias = new double[dadosFrutas.length];
        int[] rotulos = new int[dadosFrutas.length];

        // Calcular a distância Euclidiana entre o novo ponto e cada ponto da matriz de treinamento
        for (int i = 0; i < dadosFrutas.length; i++) {
            double[] treino = {dadosFrutas[i][0], dadosFrutas[i][1]};
            distancias[i] = distanciaEuclidiana(novoPonto, treino);
            rotulos[i] = (int) dadosFrutas[i][2];
        }

        // Ordenar as distâncias e os rótulos correspondentes
        for (int i = 0; i < distancias.length - 1; i++) {
            for (int j = 0; j < distancias.length - 1 - i; j++) {
                if (distancias[j] > distancias[j + 1]) {
                    // Troca as distâncias
                    double tempDist = distancias[j];
                    distancias[j] = distancias[j + 1];
                    distancias[j + 1] = tempDist;

                    // Troca os rótulos
                    int tempRot = rotulos[j];
                    rotulos[j] = rotulos[j + 1];
                    rotulos[j + 1] = tempRot;
                }
            }
        }

        // Contar as ocorrências das classes dos k vizinhos mais próximos
        int countCitricas = 0;
        int countNaoCitricas = 0;

        for (int i = 0; i < k; i++) {
            if (rotulos[i] == 1) {
                countCitricas++;
            } else {
                countNaoCitricas++;
            }
        }

        // Determinar a classe mais frequente
        String resultado = (countCitricas > countNaoCitricas) ? "Cítrica" : "Não Cítrica";

        // Imprimir o resultado
        System.out.println("A nova fruta é: " + resultado);
    }
}
