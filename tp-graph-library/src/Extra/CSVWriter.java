package Extra;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVWriter {

    public static void main(String[] args) {
        String csvFilePath = "dados.csv";

        List<String> data = new ArrayList<>();
        data.add("Nome,Idade,Cidade");
        data.add("João,25,São Paulo");
        data.add("Maria,30,Rio de Janeiro");
        data.add("Pedro,20,Belo Horizonte");

        writeDataToCSV(csvFilePath, data);
    }

    private static void writeDataToCSV(String filePath, List<String> data) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (String row : data) {
                writer.write(row + "\n");
            }
            System.out.println("Arquivo CSV criado com sucesso!");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao escrever o arquivo CSV: " + e.getMessage());
        }
    }
}
