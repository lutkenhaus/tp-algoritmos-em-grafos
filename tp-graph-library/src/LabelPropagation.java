import java.util.Arrays;
import java.io.*;
import java.text.DecimalFormat;

// teste

public class LabelPropagation {

    // Mude para o caminho do seu computador
    static String PathToCSVFile = "C:/Users/gomes/OneDrive/Documentos/trabalho de grafos/tp-algoritmos-em-grafos/tp-graph-library/database/two_moons.csv";
    public static void main(String[] args) {

        // Dados de exemplo
        double[][] data = {
            {1.0, 2.0, 0},
            {2.0, 3.0, 0},
            {3.0, 4.0, 1},
            {4.0, 5.0, 1},
            {5.0, 6.0, 0},
            {6.0, 7.0, 1}
        };

        try {
            data = convertDataFromCSVtoMatrix();
            System.out.println("Matriz Base:"); // terceira coluna extremos.
            printMatrix(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
        // Calcular matriz de afinidade
        double[][] affinityMatrix = computeAffinityMatrix(data);
        System.out.println("Matriz de Afinidade: ");
        printMatrix(affinityMatrix);
        System.out.println();

        // Executar propagação de rótulos
        double[][] propagatedLabels = labelPropagation(data, affinityMatrix, 0.5, 100);


        // Imprimir matriz de rótulos propagados
        System.out.println("Matriz de Rótulos Propagados:");
        printMatrix(propagatedLabels);
        System.out.println();

        // Prever rótulos
        int[] predictedLabels = predictLabels(propagatedLabels);

        // Imprimir rótulos previstos
        System.out.println("Rótulos Previstos:");
        System.out.println(Arrays.toString(predictedLabels));
        System.out.println();
    }

    public static double[][] labelPropagation(double[][] X, double[][] W, double alpha, int numIterations) {
        int numPoints = X.length;
        int numClasses = Arrays.stream(X).mapToInt(x -> (int) x[2]).max().orElse(0) + 1;

        double[][] F = new double[numPoints][numClasses];

        // Inicializar F com as classes iniciais conhecidas
        for (int i = 0; i < numPoints; i++) {
            int label = (int) X[i][2];
            F[i][label] = 1.0;
        }

        // Iteração do algoritmo de propagação de rótulos
        for (int iter = 0; iter < numIterations; iter++) {
            double[][] newF = new double[numPoints][numClasses];

            for (int i = 0; i < numPoints; i++) {
                for (int k = 0; k < numClasses; k++) {
                    double sum = 0.0;
                    for (int j = 0; j < numPoints; j++) {
                        sum += W[i][k] * F[j][k];
                    }
                    DecimalFormat df = new DecimalFormat("#0.00");

                    String formattedValue = df.format(alpha * sum).replace(",", ".");
                    newF[i][k] = Double.parseDouble(formattedValue);
                }
            }

            F = newF;
        }

        return F;
    }


    public static double[][] computeAffinityMatrix(double[][] X) {
        
        int numPoints = X.length;
        double sigma = 1.0; // Valor de sigma a ser ajustado

        double[][] W = new double[numPoints][2];
        DecimalFormat df = new DecimalFormat("#0.00");

        for (int i = 0; i < numPoints; i++) {
            for (int j = 0; j < 2; j++) {
                double[] xi = {X[i][0], X[i][1]};
                double[] xj = {X[j][0], X[j][1]};
                double distance = computeDistance(xi, xj);
                double wValue = Math.exp(-distance / (2 * sigma * sigma));
                String formattedValue = df.format(wValue).replace(",", ".");
                W[i][j] = Double.parseDouble(formattedValue);
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

    public static double[][] convertDataFromCSVtoMatrix() throws Exception {
        BufferedReader reader = null;
        String line = "";
        String csvDelimiter = ",";
        int rowCount = 0;
        int columnCount = 3;

        try {
            reader = new BufferedReader(new FileReader(PathToCSVFile));

            // Descartar a primeira linha (cabeçalho)
            reader.readLine();

            // Contar as linhas restantes e determinar o número de colunas
            while ((line = reader.readLine()) != null) {
                rowCount++;
            }

            // Criar a matriz com base no tamanho determinado
            double[][] dataMatrix = new double[rowCount][columnCount];
            reader.close();

            // Reiniciar o leitor para ler novamente o arquivo CSV
            reader = new BufferedReader(new FileReader(PathToCSVFile));

            // Descartar a primeira linha (cabeçalho)
            reader.readLine();

            int row = 0;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(csvDelimiter);
                
                DecimalFormat df = new DecimalFormat("#0.00");

                for (int col = 0; col < columnCount; col++) {
                    String formattedValue = values[col].replace(",", ".");
                    double parsedValue = Double.parseDouble(formattedValue);
                    String formattedNumber = df.format(parsedValue).replace(",", ".");
                    dataMatrix[row][col] = Double.parseDouble(formattedNumber);
                }

                row++;

            }

            return dataMatrix;
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    public static void Exemplo2() {

        // Função pra transformar CSV em forma de Matriz
        // A ser implementada
        // Erro out of bounds for lenght 0
        String line = "";  
        String splitBy = ",";  
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader(PathToCSVFile));
            while ((line = br.readLine()) != null) { // returns a Boolean value
                String[] data_properties = line.split(splitBy); // use comma as separator
                System.out.println("X1=" + data_properties[0] + ", X2=" + data_properties[1] + ", Y=" + data_properties[2]);  
            }
            br.close();
        }   
        catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}
