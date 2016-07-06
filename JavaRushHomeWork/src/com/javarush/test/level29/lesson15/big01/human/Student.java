package com.javarush.test.level29.lesson15.big01.human;

import java.util.Date;

public class Student extends UniversityPerson
{
    private double averageGrade;
    private Date beginningOfSession;
    private Date endOfSession;
    private int course;

    public Student(String name, int age, double averageGrade)
    {
        super(name, age);
        this.averageGrade = averageGrade;
    }

    public void live()
    {
        learn();
    }

    public void learn()
    {
    }

    public String getPosition()
    {
        return "Студент";
    }

    public void incAverageGrade(double delta)
    {
        double average = getAverageGrade() + delta;
        setAverageGrade(average);
    }


    public void setAverageGrade(double averageGrade)
    {
        this.averageGrade = averageGrade;
    }

    public void setCourse(int course)
    {
        this.course = course;
    }


    public void setBeginningOfSession(Date date)
    {
        beginningOfSession = new Date(date.getYear(), date.getMonth(), date.getDay());
    }

    public void setEndOfSession(Date date)
    {
        endOfSession = new Date(date.getYear(), date.getMonth(), date.getDay());
    }

    public double getAverageGrade()
    {
        return averageGrade;
    }

    public int getCourse()
    {
        return course;
    }

}
