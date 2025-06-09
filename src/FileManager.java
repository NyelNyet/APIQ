import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import User.Asnaf.*;
import User.ZakatPayer;

public class FileManager {

    public List<String[]> allFileReader() {
        List<String[]> allData = new ArrayList<>();
        String[] asnafTypeFiles = {"AlFuqara.txt", "AlMasakin.txt", "AlAmilunaAlaiha.txt", "AlGharimoon.txt", "AlMualafaQulubuhum.txt", "AlRiqab.txt", "FiSabiLillah.txt", "IbnAlSabil.txt"};

        for (String fileName : asnafTypeFiles) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.trim().isEmpty()) {
                        allData.add(line.split(";"));
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Info: File not found: " + fileName + ". It will be created if needed.");
            } catch (IOException e) {
                System.err.println("Error reading file " + fileName + ": " + e.getMessage());
            }
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader("ZakatPayer.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                 if (!line.trim().isEmpty()) {
                    allData.add(line.split(";"));
                 }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Info: File not found: ZakatPayer.txt. It will be created if needed.");
        } catch (IOException e) {
             System.err.println("Error reading file ZakatPayer.txt: " + e.getMessage());
        }
        return allData;
    }

    public void writeToFile(Optional<Asnaf> optionalAsnaf, Optional<ZakatPayer> optionalZakatPayer) {
        if (optionalAsnaf == null || optionalZakatPayer == null) {
            throw new IllegalArgumentException("Optional parameters cannot be null.");
        }
        
        optionalAsnaf.ifPresent(this::writeAsnafToFile);
        optionalZakatPayer.ifPresent(this::writePayerToFile);
    }
    
    private void writeAsnafToFile(Asnaf asnaf) {
        if (asnaf == null) return;
        String fileName = asnaf.getClass().getSimpleName() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(asnaf.toString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file " + fileName + ": " + e.getMessage());
        }
    }
    
    private void writePayerToFile(ZakatPayer payer) {
        if (payer == null) return;
        String fileName = "ZakatPayer.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(payer.toString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file " + fileName + ": " + e.getMessage());
        }
    }
}