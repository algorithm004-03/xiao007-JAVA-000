import java.io.*;
import java.lang.reflect.InvocationTargetException;

/**
 * 自定义类加载
 * 关键点是defineClass,读取字节码的字节流，生成class，思路就是转化class文件为字节流，传入defineClass中
 * 思路：
 * 1.继承ClassLoader，重写findClass方法
 * 2.从文件中读取转化成字节流
 * 3.传入defineClass进行加载
 * 4.生成实例，调用方法
 */
public class HelloClassLoader extends ClassLoader {
    @Override
    public Class findClass(String name) {
        byte[] b = new byte[0];
        try {
            b = loadClassFromFile(name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // name 就是加载的类名称，这里注意要填写正确
        return defineClass("Hello", b, 0, b.length);
    }

    /**
     * 读取class文件，转化内容为字节流
     * @param fileName 文件路径
     * @return
     * @throws FileNotFoundException
     */
    private byte[] loadClassFromFile(String fileName) throws FileNotFoundException {
        System.out.println(fileName);
        File file = new File(fileName);
        FileInputStream inputStream = new FileInputStream(file);
        byte[] buffer;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int nextValue = 0;
        try {
            while ( (nextValue = inputStream.read()) != -1 ) {
                // 注意字节还原
                byteStream.write(255 - nextValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer = byteStream.toByteArray();
        return buffer;
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        // 文件读取，字节流转换，加载
        String path = "C:\\Users\\12439\\Documents\\Code\\Java\\JAVA-000\\Week_01\\code\\src\\main\\resources\\Hello.xlass".replace("\\", "/");
        HelloClassLoader loader = new HelloClassLoader();
        Class hello = loader.loadClass(path);
        System.out.println(hello.getName());
        // 生成实例，调用方法
        Object instance = hello.newInstance();
        System.out.println(hello.getMethod("hello").invoke(instance));
    }
}
