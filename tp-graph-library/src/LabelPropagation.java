import java.util.Arrays;

public class LabelPropagation {

    public static void main(String[] args) {
            // Dados de exemplo
            double[][] data = {
                {1.0, 0},
                {2.0, 0},
                {3.0, 1},
                {4.0, 1},
                {5.0, 0},
                {6.0, 1}
            };

            // Calcular matriz de afinidade
            double[][] affinityMatrix = computeAffinityMatrix(data);

            // Executar propagação de rótulos
            double[][] propagatedLabels = labelPropagation(data, affinityMatrix, 0.5, 10);

            // Imprimir matriz de rótulos propagados
            System.out.println("Matriz de Rótulos Propagados:");
            printMatrix(propagatedLabels);

            // Prever rótulos
            int[] predictedLabels = predictLabels(propagatedLabels);

            // Imprimir rótulos previstos
            System.out.println("Rótulos Previstos:");
            System.out.println(Arrays.toString(predictedLabels));
        }

        // Métodos computeAffinityMatrix, labelPropagation, printMatrix, computeDistance, predictLabels
        // permanecem os mesmos do código fornecido na pergunta


    public static double[][] labelPropagation(double[][] X, double[][] W, double alpha, int numIterations) {
        int numPoints = X.length;
        int numClasses = Arrays.stream(X).mapToInt(x -> (int) x[1]).max().orElse(0) + 1;

        double[][] F = new double[numPoints][numClasses];

        // Inicializa F com as classes iniciais conhecidas
        for (int i = 0; i < numPoints; i++) {
            int label = (int) X[i][1];
            F[i][label] = 1.0;
        }

        // Iteração do algoritmo de propagação de rótulos
        for (int iter = 0; iter < numIterations; iter++) {
            double[][] newF = new double[numPoints][numClasses];

            for (int i = 0; i < numPoints; i++) {
                for (int j = 0; j < numPoints; j++) {
                    double[] row = W[j];
                    double sum = 0.0;
                    for (int k = 0; k < numClasses; k++) {
                        sum += row[k] * F[j][k];
                    }
                    for (int k = 0; k < numClasses; k++) {
                        newF[i][k] += alpha * sum * F[i][k];
                    }
                }
            }

            F = newF;
        }

        return F;
    }

    public static double[][] computeAffinityMatrix(double[][] X) {
        int numPoints = X.length;
        double sigma = 1.0; // Valor de sigma a ser ajustado

        double[][] W = new double[numPoints][numPoints];

        for (int i = 0; i < numPoints; i++) {
            for (int j = 0; j < numPoints; j++) {
                double[] xi = X[i];
                double[] xj = X[j];
                double distance = computeDistance(xi, xj);
                W[i][j] = Math.exp(-distance / (2 * sigma * sigma));
            }
        }

        return W;
    }

    public static void printMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static double computeDistance(double[] x1, double[] x2) {
        if (x1.length != x2.length) {
            throw new IllegalArgumentException("Os pontos devem ter a mesma dimensão");
        }

        double sum = 0.0;
        for (int i = 0; i < x1.length; i++) {
            double diff = x1[i] - x2[i];
            sum += diff * diff;
        }

        return Math.sqrt(sum);
    }


    public static int[] predictLabels(double[][] F) {
        int numPoints = F.length;
        int numClasses = F[0].length;

        int[] predictedLabels = new int[numPoints];

        for (int i = 0; i < numPoints; i++) {
            int maxLabel = 0;
            double maxValue = F[i][0];

            for (int j = 1; j < numClasses; j++) {
                if (F[i][j] > maxValue) {
                    maxLabel = j;
                    maxValue = F[i][j];
                }
            }

            predictedLabels[i] = maxLabel;
        }

        return predictedLabels;
    }
}
