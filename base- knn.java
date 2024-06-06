import java.util.Scanner;


public class KNN{
    // calcula a distancia dos pontos
    private static double distanciaEuclidiana(double[] novaFruta, double[] treino) {
        double somaQuadrados = 0;
        for (int i = 0; i < novaFruta.length; i++) {
            somaQuadrados += Math.pow(novaFruta[i] - treino[i], 2);
        }
        return Math.sqrt(somaQuadrados);
    }

    private static String classificarFruta(double[] novaFruta, double[][] dadosFrutas, int k) {
        int countCitricas = 0;
        int countNaoCitricas = 0;

        // calcula as distâncias e verifica se cada fruta é cítrica ou não
        for (int i = 0; i < dadosFrutas.length; i++) {
            double[] distancias = new double[dadosFrutas.length];
            double[] treino = {dadosFrutas[i][0], dadosFrutas[i][1]};
            distancias[i] = distanciaEuclidiana(novaFruta, treino);
    
            if (distancias[i] <= k) {
                if (dadosFrutas[i][2] == 1) { // Verifica se a fruta é cítrica
                    countCitricas++;
                } else {
                    countNaoCitricas++;
                }
            }
        }
    
        // classifica a nova fruta
        String resposta;
        if (countCitricas > countNaoCitricas) {
            resposta = "Citrica";
        } else {
            resposta = "Nao citrica";
        }
        return resposta;
    }
    
    
    

    public static void main(String[] args) {
        // Matriz de treino
        double[][] dadosFrutas = {
            {100, 5, 1},
            {150, 6, 1},
            {200, 8, 0},
            {120, 4, 0},
            {180, 7, 1},
            {170, 6, 1},
            {210, 9, 0},
            {130, 5, 0},
            {140, 6, 1},
            {160, 7, 0},
            {190, 8, 1}
        };

        int k = 5;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o peso da fruta: ");
        double peso = scanner.nextDouble();

        System.out.print("Digite o tamanho da fruta: ");
        double tamanho = scanner.nextDouble();

        double[] novaFruta = {peso, tamanho};

        String resultado = classificarFruta(novaFruta, dadosFrutas, k);
        System.out.println("A nova fruta é: " + resultado);
    }
}
