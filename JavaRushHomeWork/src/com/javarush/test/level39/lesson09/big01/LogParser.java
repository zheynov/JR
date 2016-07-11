
package com.javarush.test.level39.lesson09.big01;

import com.javarush.test.level39.lesson09.big01.query.DateQuery;
import com.javarush.test.level39.lesson09.big01.query.IPQuery;
import com.javarush.test.level39.lesson09.big01.query.UserQuery;

import java.io.*;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery, UserQuery, DateQuery {
    private Path logDir;
    private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);

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

    @Override
    public Set<String> getAllUsers() {
        Set<String> allUsers = new HashSet<>();
        for (String line : getLines(logDir, null, null)) {
            allUsers.add(line.split("\t")[1]);
        }
        return allUsers;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        Set<String> allUsers = new HashSet<>();
        for (String line : getLines(logDir, after, before)) {
            allUsers.add(line.split("\t")[1]);
        }
        return allUsers.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        Set<String> allEvents = new HashSet<>();
        for (String line : getLines(logDir, after, before)) {
            if (user.equalsIgnoreCase(line.split("\t")[1].trim()))
                allEvents.add(line.split("\t")[3].replaceAll("[^A-z]", "").trim());
        }
        return allEvents.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        Set<String> allUsersForIP = new HashSet<>();
        for (String line : getLines(logDir, after, before)) {
            if (ip.equals(line.split("\t")[0].trim()))
                allUsersForIP.add(line.split("\t")[1].trim());
        }
        return allUsersForIP;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) // должен возвращать пользователей, которые были залогинены
    {
        Set<String> allLoggedUsers = new HashSet<>();
        for (String line : getLines(logDir, after, before)) {
            if (line.split("\t")[3].trim().replaceAll("[^A-z]", "").trim().equals("LOGIN") && line.split("\t")[4].trim().equals("OK"))
                allLoggedUsers.add(line.split("\t")[1].trim());
        }
        return allLoggedUsers;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) // должен возвращать пользователей, которые скачали плагин
    {
        Set<String> allDwnldUsers = new HashSet<>();
        for (String line : getLines(logDir, after, before)) {
            if (line.split("\t")[3].trim().replaceAll("[^A-z]", "").trim().equals("DOWNLOAD_PLUGIN") && line.split("\t")[4].trim().equals("OK"))
                allDwnldUsers.add(line.split("\t")[1].trim());
        }
        return allDwnldUsers;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) // должен возвращать пользователей, которые отправили сообщение
    {
        Set<String> allWrtUsers = new HashSet<>();
        for (String line : getLines(logDir, after, before)) {
            if (line.split("\t")[3].trim().replaceAll("[^A-z]", "").trim().equals("WRITE_MESSAGE") && line.split("\t")[4].trim().equals("OK"))
                allWrtUsers.add(line.split("\t")[1].trim());
        }
        return allWrtUsers;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) // должен возвращать пользователей, которые решали любую задачу.
    {
        Set<String> allAnytaskUsers = new HashSet<>();
        for (String line : getLines(logDir, after, before)) {
            if (line.split("\t")[3].trim().replaceAll("[^A-z]", "").trim().equals("SOLVE_TASK"))
                allAnytaskUsers.add(line.split("\t")[1].trim());
        }
        return allAnytaskUsers;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) // должен возвращать пользователей, которые решали задачу с номером task
    {
        Set<String> allTaskNumberUsers = new HashSet<>();
        for (String line : getLines(logDir, after, before)) {
            String taskNumber = line.split("\t")[3].trim().replaceAll("[A-z]", "").trim();
            if (taskNumber.isEmpty()) continue;
            if (Integer.parseInt(taskNumber) == task && line.split("\t")[3].trim().replaceAll("[^A-z]", "").trim().equals("SOLVE_TASK"))
                allTaskNumberUsers.add(line.split("\t")[1].trim());
        }
        return allTaskNumberUsers;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before)  // должен возвращать пользователей, которые решили любую задачу.
    {
        Set<String> allTaskNumberUsers = new HashSet<>();
        for (String line : getLines(logDir, after, before)) {
            if (line.split("\t")[3].trim().replaceAll("[^A-z]", "").trim().equals("DONE_TASK"))
                allTaskNumberUsers.add(line.split("\t")[1].trim());
        }
        return allTaskNumberUsers;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        Set<String> allTaskNumberUsers = new HashSet<>();
        for (String line : getLines(logDir, after, before)) {
            String taskNumber = line.split("\t")[3].trim().replaceAll("[A-z]", "").trim();
            if (taskNumber.isEmpty()) continue;
            if (Integer.parseInt(taskNumber) == task && line.split("\t")[3].trim().replaceAll("[^A-z]", "").trim().equals("DONE_TASK"))
                allTaskNumberUsers.add(line.split("\t")[1].trim());
        }
        return allTaskNumberUsers;
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) throws ParseException { // должен возвращать даты, когда определенный пользователь произвел определенное событие
        Set<Date> userEventsDates = new HashSet<>();
        for (String line : getLines(logDir, after, before)) {
            if (event.name().equals(line.split("\t")[3].replaceAll("[^A-z]", "")) && line.split("\t")[1].equals(user))
                userEventsDates.add(format.parse(line.split("\t")[2].trim()));
        }
        return userEventsDates;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) throws ParseException { // должен возвращать даты, когда определенное событие не выполнилось (статус FAILED)
        Set<Date> userFailedEventsDates = new HashSet<>();
        for (String line : getLines(logDir, after, before)) {
            if (line.split("\t")[4].equals("FAILED"))
                userFailedEventsDates.add(format.parse(line.split("\t")[2].trim()));
        }
        return userFailedEventsDates;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) throws ParseException { // должен возвращать даты, когда определенное событие закончилось ошибкой (статус ERROR).
        Set<Date> userErrorEventsDates = new HashSet<>();
        for (String line : getLines(logDir, after, before)) {
            if (line.split("\t")[4].equals("ERROR")) userErrorEventsDates.add(format.parse(line.split("\t")[2].trim()));
        }
        return userErrorEventsDates;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) throws ParseException { // должен возвращать дату, когда пользователь залогинился впервые за указанный период
        Date tempdate = null;
        for (String line : getLines(logDir, after, before)) {
            if (tempdate != null && line.split("\t")[1].equals(user) && line.split("\t")[3].trim().replaceAll("[^A-z]", "").trim().equals("LOGIN")) {
                if (tempdate.getTime() > format.parse(line.split("\t")[2].trim()).getTime())
                    tempdate = format.parse(line.split("\t")[2].trim());
            } else if (tempdate == null && line.split("\t")[1].equals(user) && line.split("\t")[3].trim().replaceAll("[^A-z]", "").trim().equals("LOGIN"))
                tempdate = format.parse(line.split("\t")[2].trim());
        }
        return tempdate;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) throws ParseException { // должен возвращать дату, когда пользователь впервые попытался решить определенную задачу.
        Date tempdate = null;
        for (String line : getLines(logDir, after, before)) {
            String taskNumber = line.split("\t")[3].trim().replaceAll("[A-z]", "").trim();

            if (!taskNumber.isEmpty() && tempdate != null && task == Integer.parseInt(taskNumber) &&
                    line.split("\t")[1].equals(user) && line.split("\t")[3].trim().replaceAll("[^A-z]", "").trim().equals("SOLVE_TASK")) {
                if (tempdate.getTime() > format.parse(line.split("\t")[2].trim()).getTime())
                    tempdate = format.parse(line.split("\t")[2].trim());
            } else if (!taskNumber.isEmpty() && line.split("\t")[1].equals(user) &&
                    task == Integer.parseInt(taskNumber) && line.split("\t")[3].trim().replaceAll("[^A-z]", "").trim().equals("SOLVE_TASK"))
                tempdate = format.parse(line.split("\t")[2].trim());
        }
        return tempdate;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) throws ParseException { // должен возвращать дату, когда пользователь решил определенную задачу.
        Date tempdate = null;
        for (String line : getLines(logDir, after, before)) {
            String taskNumber = line.split("\t")[3].trim().replaceAll("[A-z]", "").trim();
            if (!taskNumber.isEmpty() && task == Integer.parseInt(taskNumber) && line.split("\t")[1].equals(user) && line.split("\t")[3].trim().replaceAll("[^A-z]", "").trim().equals("DONE_TASK")) {
                tempdate = format.parse(line.split("\t")[2].trim());
            }
        }
        return tempdate;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) throws ParseException { // должен возвращать даты, когда пользователь написал сообщение
        Set<Date> userWrMesDates = new HashSet<>();
        for (String line : getLines(logDir, after, before)) {
            if (line.split("\t")[1].equals(user) && line.split("\t")[3].trim().replaceAll("[^A-z]", "").trim().equals("WRITE_MESSAGE"))
                userWrMesDates.add(format.parse(line.split("\t")[2].trim()));
        }
        return userWrMesDates;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) throws ParseException { // должен возвращать даты, когда пользователь скачал плагин
        Set<Date> userDwnldDates = new HashSet<>();
        for (String line : getLines(logDir, after, before)) {
            if (line.split("\t")[1].equals(user) && line.split("\t")[3].trim().replaceAll("[^A-z]", "").trim().equals("DOWNLOAD_PLUGIN"))
                userDwnldDates.add(format.parse(line.split("\t")[2].trim()));
        }
        return userDwnldDates;
    }
}