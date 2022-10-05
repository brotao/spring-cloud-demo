package org.broto.jksj.javaadvanced.jvm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;

/**
 * @Author luotao
 * @Date 2/10/2022
 * @Description 自定义classloader
 */

public class MyClassLoader extends ClassLoader{
    public static void main(String[] args) {
        try {
            Class<?> hello = new MyClassLoader().findClass("Hello");
            Object o = hello.newInstance();
            Method helloFunc = hello.getMethod("hello");
            helloFunc.invoke(o);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String suffix = ".xlass";
        URL resource = this.getResource("jvm/" + name + suffix);
        File file = new File(resource.getPath());
        
        byte[] bytes = new byte[(int) file.length()];
        try (
                FileInputStream fileInputStream = new FileInputStream(file)
        ){
            int readBytes = fileInputStream.read(bytes);

            for (int i = 0; i < readBytes; i++) {
                bytes[i] = (byte) (255-bytes[i]);
            }
            
            return super.defineClass(name, bytes, 0, readBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
