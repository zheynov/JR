package com.javarush.test.level39.lesson09.big01;

import com.javarush.test.level39.lesson09.big01.query.IPQuery;

import java.io.*;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery {

    private Path logDir;

    public LogParser(Path logDir) {
        this.logDir = logDir;
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {

        return getUniqueIPs(after, before).size();
    }


    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {

        Set<String> ips = new HashSet<>();

        for (String line : getLines(logDir, after, before)) {

            ips.add(line.substring(0, line.indexOf("\t")));
        }
        return ips;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        return null;
    }


    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return null;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return null;
    }


    private List<String> getLines(Path logDir, Date after, Date before) {

        List<File> directoryLogs = new ArrayList<>();

        List<String> result = new ArrayList<>();

        for (File file : logDir.toFile().listFiles()) {

            if (file.isFile() && file.getName().endsWith(".log"))
                directoryLogs.add(file);
        }

        for (File directoryLog : directoryLogs) {

            try (BufferedReader br = new BufferedReader(new FileReader(directoryLog))) {

                String line;
                while ((line = br.readLine()) != null) {

                    String tempLine = line.substring(14, line.length()).replaceAll("[A-z]", "").trim();

                    if (tempLine.contains("\t")) {
                        tempLine = tempLine.split("\t")[0];
                    }

                    String s = tempLine;
                    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);

                    Date docDate = null;
                    try {
                        docDate = format.parse(s);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if (before == null && after == null) {
                        result.add(line);
                    } else if (before == null && (after.after(docDate) || after.compareTo(docDate) == 0)) {
                        result.add(line);
                    } else if (after == null && (before.before(docDate) || (before.compareTo(docDate) == 0))) {
                        result.add(line);
                    } else if (before != null && after != null && before.before(docDate) && after.after(docDate)) {
                        result.add(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
