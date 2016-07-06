package com.javarush.test.level36.lesson10.bonus01;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/* Осваиваем ClassLoader и Reflection
Аргументом для класса Solution является абсолютный путь к пакету,
например, "C:\JavaRushHomeWork\src\com\javarush\test\level36\lesson10\bonus01\data\second".
Имя пакета может содержать File.separator.
В этом пакете находятся только скомпилированные классы.
Известно, что каждый класс имеет конструктор без параметров и реализует интерфейс HiddenClass.
Считайте все классы с файловой системы, создайте фабрику - реализуйте метод getHiddenClassObjectByKey.
Известно, что есть только один класс, простое имя которого начинается с String key без учета регистра.
*/
public class Solution {
    private static List<Class> hiddenClasses = new ArrayList<>();

    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {

        Solution solution = new Solution("D:\\JAVA\\JavaRushHomeWork\\out\\production\\JavaRushHomeWork\\com\\javarush\\test\\level36\\lesson10\\bonus01\\data\\second");

        solution.scanFileSystem();

        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {

        if (!packageName.endsWith(File.separator)) packageName = packageName + File.separator;

        MyClassLoader loader = new MyClassLoader(packageName, ClassLoader.getSystemClassLoader());

        String[] files = new File(packageName).list();

        for (String classFile : files) {
            try {

                if (!classFile.endsWith(".class")) {
                    continue;
                }

                String className = classFile.split(".class")[0];

                Class clazz = loader.loadClass(className);

                hiddenClasses.add(clazz);

            } catch (Exception ignored) {

            }
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {

        for (Class hiddenClass : hiddenClasses) {

            if (hiddenClass.getSimpleName().toLowerCase().startsWith(key.toLowerCase())) {
                try {
                    if ((HiddenClass.class.isAssignableFrom(hiddenClass))) {

                        for (Constructor constr : hiddenClass.getDeclaredConstructors()) {

                            if (constr.getGenericParameterTypes().length == 0) {

                                constr.setAccessible(true);
                                Object obj = constr.newInstance();
                                return (HiddenClass) obj;
                            }
                        }
                    }

                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
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
