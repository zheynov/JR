package com.javarush.test.level39.lesson09.big01.query;

        import com.javarush.test.level39.lesson09.big01.Event;

        import java.text.ParseException;
        import java.util.Date;
        import java.util.Set;

public interface DateQuery {
    Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) throws ParseException;
    Set<Date> getDatesWhenSomethingFailed(Date after, Date before) throws ParseException;
    Set<Date> getDatesWhenErrorHappened(Date after, Date before) throws ParseException;
    Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) throws ParseException;
    Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) throws ParseException;
    Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) throws ParseException;
    Set<Date>  getDatesWhenUserWroteMessage(String user, Date after, Date before) throws ParseException;
    Set<Date>  getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) throws ParseException;
}