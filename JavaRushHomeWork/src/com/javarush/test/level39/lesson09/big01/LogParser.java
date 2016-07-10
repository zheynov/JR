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
    public int getNumberOfUniqueIPs(Date after, Date before) {  // возвращет количество уникальных IP адресов за выбранный перио
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {  // множество, содержащее все неповторяющиеся IP
        Set<String> ips = new HashSet<>();
        for (String line : getLines(logDir, after, before)) {
            ips.add(line.substring(0, line.indexOf("\t")));
        }
        return ips;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) { // IP addressess, с которых работал переданный пользователь.
        Set<String> ipsForUser = new HashSet<>();
        for (String line : getLines(logDir, after, before)) {
            String userIP = line.substring(0, line.indexOf("\t"));
            String tempUser = line.split("\t")[1];
            if (user != null && user.equalsIgnoreCase(tempUser))
                ipsForUser.add(userIP);
        }
        return ipsForUser;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) { // возвращает IP, с которых было произведено переданное событие
        Set<String> ipsForEvent = new HashSet<>();
        for (String line : getLines(logDir, after, before)) {
            String userIP = line.substring(0, line.indexOf("\t"));
            String eventTemp = line.split("\t")[3].trim().split(" ")[0];
            if (event != null && eventTemp.equals(event.name()))
                ipsForEvent.add(userIP);
        }
        return ipsForEvent;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> ipsForStatus = new HashSet<>();
        for (String line : getLines(logDir, after, before)) {
            String userIP = line.substring(0, line.indexOf("\t"));
            String statusTemp = line.split("\t")[4].trim();
            if (status != null && statusTemp.equals(status.name()))
                ipsForStatus.add(userIP);
        }
        return ipsForStatus;
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
                    String[] tempLine = line.split("\t");
                    String dateFormatted = tempLine[2];
                    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
                    Date docDate = null;
                    try {
                        docDate = format.parse(dateFormatted);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if (before == null && after == null) {
                        result.add(line);
                    } else if (before == null && after != null && docDate.getTime() >= after.getTime()) {
                        result.add(line);
                    } else if (after == null && before != null && docDate.getTime() <= before.getTime()) {
                        result.add(line);
                    } else if (before != null && after != null && docDate.getTime() >= after.getTime() && docDate.getTime() <= before.getTime()) {
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
