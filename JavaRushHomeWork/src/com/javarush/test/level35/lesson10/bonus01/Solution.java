package com.javarush.test.level35.lesson10.bonus01;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

/* ClassLoader - что это такое?
Реализуйте логику метода getAllAnimals.
Аргумент метода pathToAnimals - это абсолютный путь к директории, в которой хранятся скомпилированные классы.
Путь не обязательно содержит / в конце.
НЕ все классы наследуются от интерфейса Animal.
НЕ все классы имеют публичный конструктор без параметров.
Только для классов, которые наследуются от Animal и имеют публичный конструктор без параметров, - создать по одному объекту.
Добавить созданные объекты в результирующий сет и вернуть.
Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) {

//        Set<? extends Animal> allAnimals = getAllAnimals("F:/JAVA/JavaRushHomeWork/JavaRushHomeWork/out/production/JavaRushHomeWork/com/javarush/test/level35/lesson10/bonus01/data");
        Set<? extends Animal> allAnimals = getAllAnimals("C:/temp");


        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {

        Set<Animal> result = new HashSet<>();

        if (!pathToAnimals.endsWith("\\") && !pathToAnimals.endsWith("/")) pathToAnimals = pathToAnimals + "/";

        MyClassLoader loader = new MyClassLoader(pathToAnimals, ClassLoader.getSystemClassLoader());

        String[] files = new File(pathToAnimals).list();

        for (String classFile : files) {
            try {

                if (!classFile.endsWith(".class")) {
                    continue;
                }

                String className = classFile.split(".class")[0];

                Class clazz = loader.loadClass(className);

                boolean isNoParams = false, constrName = false, isPublic = false;

                for (Constructor constr : clazz.getConstructors()) {

                    if (constr.getName().substring(constr.getName().lastIndexOf(".") + 1).equals(clazz.getSimpleName())) {

                        if (Modifier.isPublic(constr.getModifiers())) {
                            isPublic = true;
                        }
                        if (constr.getGenericParameterTypes().length == 0)
                            isNoParams = true;
                    }
                }

                for (Class interf : clazz.getInterfaces()) {
                    if (interf.equals(Animal.class))
                        constrName = true;
                }

                if (isNoParams && constrName && isPublic) {
                    Object obj = clazz.newInstance();
                    result.add((Animal) obj);
                }
            } catch (Exception ignored) {

            }
        }
        return result;
    }

    private static class MyClassLoader extends java.lang.ClassLoader {

        private String pathtobin; // Путь до директории с классами.

        public MyClassLoader(String pathtobin, java.lang.ClassLoader parent) {
            super(parent);
            this.pathtobin = pathtobin;
        }

        @Override
        public Class<?> findClass(String className) throws ClassNotFoundException {
            try {
                byte b[] = fetchClassFromFS(pathtobin + className + ".class"); // Получем байт-код из файла и загружаем класс в рантайм
                return defineClass(null, b, 0, b.length);
            } catch (FileNotFoundException ex) {
                return super.findClass(className);
            } catch (IOException ex) {
                return super.findClass(className);
            }
        }

        private byte[] fetchClassFromFS(String path) throws FileNotFoundException, IOException {
            InputStream is = new FileInputStream(new File(path));

            long length = new File(path).length(); // Get the size of the file

            if (length > Integer.MAX_VALUE) {        // File is too large
            }

            byte[] bytes = new byte[(int) length];   // Create the byte array to hold the data

            // Read in the bytes
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length
                    && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }

            // Ensure all the bytes have been read in
            if (offset < bytes.length) {
                throw new IOException("Could not completely read file " + path);
            }

            is.close();  // Close the input stream and return bytes
            return bytes;
        }
    }
}