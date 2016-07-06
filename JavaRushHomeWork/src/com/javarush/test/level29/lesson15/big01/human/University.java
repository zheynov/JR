package com.javarush.test.level29.lesson15.big01.human;

import java.util.ArrayList;
import java.util.List;

public class University
{

    private List<Student> students = new ArrayList<>();
    private String name;
    private int age;

    public University(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double averageGrade)
    {
        Student result = null;

        for (Student student : students)
        {
            if (student.getAverageGrade() == averageGrade)
                result = student;
        }

        return result;
    }

    public Student getStudentWithMaxAverageGrade()
    {
        Student result = null;

        if (students.size() > 0 && students != null)
        {
            result = students.get(0);

            for (Student student : students)
            {
                if (student.getAverageGrade() > result.getAverageGrade())
                    result = student;
            }
        }

        return result;
    }

    public Student getStudentWithMinAverageGrade()
    {
        Student result = null;

        if (students.size() > 0 && students != null)
        {
            result = students.get(0);

            for (Student student : students)
            {
                if (student.getAverageGrade() < result.getAverageGrade())
                    result = student;
            }
        }

        return result;
    }

    public void expel(Student student)
    {
        students.remove(student);
    }


    public List<Student> getStudents()
    {
        return students;
    }


    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public void setStudents(List<Student> students)
    {
        this.students = students;
    }
}
