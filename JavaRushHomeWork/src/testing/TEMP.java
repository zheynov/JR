package testing;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TEMP {


    public static void main(String[] args) throws ParseException {

        Path logDir = Paths.get("C:\\Users\\Redlaw\\IdeaProjects\\JR\\JavaRushHomeWork\\src\\com\\javarush\\test\\level39\\lesson09\\big01\\logs");

        List<String> lines = getLines(logDir);

        List<String> uniqueIPs = new ArrayList<>();


        Date after = new Date(), before = new Date();




        for (String line : lines) {

            String ipAddress = line.substring(0, line.indexOf("\t"));

            String tempLine = line.substring(14, line.length()).replaceAll("[A-z]", "").trim();

            if (tempLine.length() > 19) {
                tempLine = tempLine.substring(0, 19);
            }

            String s = tempLine;
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
            Date docDate = format.parse(s);


            if (before.before(docDate) && after.after(docDate)) {
                uniqueIPs.add(ipAddress);

            } else if (before == null && (after.after(docDate) || after.compareTo(docDate) == 0)) {
                uniqueIPs.add(ipAddress);

            } else if (after == null && (before.before(docDate) || (before.compareTo(docDate) == 0))) {
                uniqueIPs.add(ipAddress);
            } else uniqueIPs.add(ipAddress);
        }
    }


    private static List<String> getLines(Path logDir) {

        List<File> directoryLogs = new ArrayList<>();

        logDir = Paths.get("C:\\Users\\Redlaw\\IdeaProjects\\JR\\JavaRushHomeWork\\src\\com\\javarush\\test\\level39\\lesson09\\big01\\logs");

        List<String> result = new ArrayList<>();

        for (File file : logDir.toFile().listFiles()) {

            if (file.isFile() && file.getName().endsWith(".log"))
                directoryLogs.add(file);
        }

        for (File directoryLog : directoryLogs) {

            try (BufferedReader br = new BufferedReader(new FileReader(directoryLog))) {

                String line;

                while ((line = br.readLine()) != null) {

                    String tempLine[] = line.split("\t");
                 //   String tempLine = line.substring(14, line.length()).replaceAll("[A-z]", "").trim();


                    for (String s : tempLine) {
                        System.out.print(s + " ");

                    }
                    System.out.println();
                    result.add(line);


                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}


