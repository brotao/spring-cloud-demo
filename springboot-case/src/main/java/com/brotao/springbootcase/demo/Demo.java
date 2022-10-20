package com.brotao.springbootcase.demo;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.brotao.springbootcase.bean.MyJarFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.util.Set;
import java.util.concurrent.locks.LockSupport;

public class Demo {
    public static void main(String[] args) {
        String path = "C:\\Users\\luotao\\Desktop\\sonar\\service-dataservice-table-0.0.1-SNAPSHOT.jar";
        MyJarFile myJarFile = new MyJarFile(new File(path), "Sonar_DataService-table");
        Set<String> subFileNames = myJarFile.getSubFileNames();
//        System.out.println(subFileNames);
//
//        String url = "http://sonar.gildata.com:9000/api/sources/raw?key=Sonar_DataService-base:pom.xml";
        String cookie = "XSRF-TOKEN=35b7ql54p8f00ln0keb7h1rnm7; JWT-SESSION=eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJBWU56LURxN1dHUlRPSk0zQXZOayIsInN1YiI6IkFYMG1qQV9ORFJZWkFhUzZmU0YxIiwiaWF0IjoxNjY0MDk3OTkxLCJleHAiOjE2NjQ0NDYzODYsImxhc3RSZWZyZXNoVGltZSI6MTY2NDA5Nzk5MTM1NSwieHNyZlRva2VuIjoiMzViN3FsNTRwOGYwMGxuMGtlYjdoMXJubTcifQ.HariuOOX6Xggsrr5i5bJChfbLjCw8bYV4tW_o4q6SlQ";
//        HttpResponse execute = HttpRequest.get(url).header(Header.COOKIE, cookie).execute();
//        String body = execute.body();
//        System.out.println(body);

        String rootPath = "C:\\Users\\luotao\\Desktop\\sonar\\service-base\\";
        File root = new File(rootPath + myJarFile.getSonarProjectName());

        for (String subFileName : subFileNames) {
            File file = new File(root, subFileName);
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                try {
                    Files.createFile(file.toPath());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            String url2 = "http://sonar.gildata.com:9000/api/sources/raw?key=" + myJarFile.getSonarProjectName() + ":src/main/java/" + subFileName;
            LockSupport.parkNanos(200000L);
            String body1 = HttpRequest.get(url2).header(Header.COOKIE, cookie).execute().body();
            try {
                Files.write(file.toPath(), body1.getBytes(StandardCharsets.UTF_8), new StandardOpenOption[]{StandardOpenOption.WRITE});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
