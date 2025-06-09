import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import User.Asnaf.*;
import User.ZakatPayer;

public class FileManager {

    private final String[] asnafFileNames = {
        "AlFuqara.txt", "AlMasakin.txt", "AlAmilunaAlaiha.txt", "AlGharimoon.txt", 
        "AlMualafaQulubuhum.txt", "AlRiqab.txt", "FiSabiLillah.txt", "IbnAlSabil.txt"
    };

    public List<String[]> allFileReader() {
        List<String[]> allData = new ArrayList<>();
        for (String fileName : asnafFileNames) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    allData.add(line.split(";"));
                }
            } catch (IOException e) {
            }
        }
        try (BufferedReader reader = new BufferedReader(new FileReader("ZakatPayer.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                allData.add(line.split(";"));
            }
        } catch (IOException e) {
        }
        return allData;
    }

    public void writeToFile(Optional<Asnaf> optionalAsnaf, Optional<ZakatPayer> optionalZakatPayer) {
        if (optionalAsnaf.isPresent()) {
            Asnaf asnaf = optionalAsnaf.get();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(asnaf.getTypeOfAsnaf() + ".txt", true))) {
                writer.write(asnafToCsvString(asnaf));
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (optionalZakatPayer.isPresent()) {
            ZakatPayer payer = optionalZakatPayer.get();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("ZakatPayer.txt", true))) {
                writer.write(payerToCsvString(payer));
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void rewriteAllAsnafFiles(List<Asnaf> asnafList) {
        Map<String, List<Asnaf>> groupedAsnaf = asnafList.stream()
            .collect(Collectors.groupingBy(Asnaf::getTypeOfAsnaf));

        for (String asnafType : asnafFileNames) {
            String cleanAsnafType = asnafType.replace(".txt", "");
            List<Asnaf> listForType = groupedAsnaf.getOrDefault(cleanAsnafType, new ArrayList<>());
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(asnafType, false))) {
                for (Asnaf asnaf : listForType) {
                    writer.write(asnafToCsvString(asnaf));
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String asnafToCsvString(Asnaf asnaf) {
        String base = asnaf.getUserID() + ";" + asnaf.getName() + ";" + asnaf.getPhoneNumber() + ";" + asnaf.getEmail() + ";" + 
                      asnaf.getAddress() + ";" + asnaf.getAge() + ";" + asnaf.getMonthlyIncome() + ";" + 
                      asnaf.getMonthlyExpenses() + ";" + asnaf.getFamilyInformation();
        
        String specificPart = "";
        switch(asnaf.getTypeOfAsnaf()) {
            case "AlAmilunaAlaiha": specificPart = ";" + ((AlAmilunaAlaiha) asnaf).getZakatAgency(); break;
            case "AlGharimoon": specificPart = ";" + ((AlGharimoon) asnaf).getDebtAmount(); break;
            case "AlMualafaQulubuhum": specificPart = ";" + ((AlMualafaQulubuhum) asnaf).getDateOfConversion(); break;
            case "AlRiqab": specificPart = ";" + ((AlRiqab) asnaf).getTypeOfCaptivity(); break;
            case "FiSabiLillah": specificPart = ";" + ((FiSabiLillah) asnaf).getAcitivityInTheCauseOfAllah(); break;
            case "IbnAlSabil": specificPart = ";" + ((IbnAlSabil) asnaf).getReasonForBeingStranded(); break;
            default: specificPart = "";
        }

        if (asnaf instanceof AlFuqara || asnaf instanceof AlMasakin) {
            return base + ";" + asnaf.getDateOfApplication() + ";" + asnaf.getTypeOfAsnaf();
        } else {
             return base + ";" + asnaf.getDateOfApplication() + specificPart + ";" + asnaf.getTypeOfAsnaf();
        }
    }

    private String payerToCsvString(ZakatPayer payer) {
        return payer.getUserID() + ";" + payer.getName() + ";" + payer.getPhoneNumber() + ";" + payer.getEmail() + ";" + 
               payer.getAddress() + ";" + payer.getAge() + ";" + payer.getZakatAmount();
    }
}