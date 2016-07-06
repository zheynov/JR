package com.javarush.test.level20.lesson02.task02;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* Читаем и пишем в файл: JavaRush
Реализуйте логику записи в файл и чтения из файла для класса JavaRush
В файле your_file_name.tmp может быть несколько объектов JavaRush
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution
{
    public static void main(String[] args)
    {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try
        {
            File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            User user = new User();
            user.setBirthDate(new Date(83, 10, 25));
            user.setFirstName("Ivan");
            user.setLastName("Petrov");
            user.setCountry(User.Country.UKRAINE);
            user.setMale(true);
            javaRush.users.add(user);

            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны

            outputStream.close();
            inputStream.close();

        }
        catch (IOException e)
        {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush
    {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception
        {//implement this method - реализуйте этот метод
            PrintWriter writer = new PrintWriter(outputStream);

            if (users.size() > 0)
            {
                for (User usr : users)
                {
                    if (usr != null)
                    {
                        writer.println("yes");

                        writer.println(usr.getFirstName());
                        writer.println(usr.getLastName());

                        writer.println(usr.getBirthDate().getTime());

                        writer.println(usr.isMale());
                        writer.println(usr.getCountry().getDisplayedName());
                    }
                }
            }

            writer.flush();
            writer.close();
        }

        public void load(InputStream inputStream) throws Exception
        {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String s;

            while ((s = reader.readLine()) != null)
            {
                if (s.equals("yes"))
                {
                    User user = new User();

                    String FirstName = reader.readLine();
                    user.setFirstName(FirstName);

                    String LastName = reader.readLine();
                    user.setLastName(LastName);

                    Date sss = new Date(Long.parseLong(reader.readLine()));
                    user.setBirthDate(sss);

                    String setMale = reader.readLine();
                    if (setMale.equals("true"))
                        user.setMale(true);
                    else user.setMale(false);

                    String Country = reader.readLine();
                    if (Country.equals("Ukraine"))
                        user.setCountry(User.Country.UKRAINE);
                    else if (Country.equals("Russia"))
                        user.setCountry(User.Country.RUSSIA);
                    else user.setCountry(User.Country.OTHER);

                    users.add(user);
                }
            }

            reader.close();
        }
    }
}
