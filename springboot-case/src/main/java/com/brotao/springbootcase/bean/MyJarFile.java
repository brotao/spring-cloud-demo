package com.brotao.springbootcase.bean;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


@Slf4j
@Getter
public class MyJarFile {
    private JarFile jarFile;

    private String sonarProjectName;

    private String fileName;
    private Set<String> subFileNames;

    public MyJarFile(File jarFile) {
        try {
            this.jarFile = new JarFile(jarFile);
        } catch (IOException e) {
            log.error("读取文件失败", e);
        }

        this.sonarProjectName = jarFile.getName();
        this.fileName = jarFile.getName();

        Enumeration<JarEntry> entries = this.jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            if (!jarEntry.isDirectory()) {
                if (subFileNames == null) {
                    subFileNames = new HashSet<>();
                }
                String name = jarEntry.getName();
                if (!name.contains("$") && (name.endsWith(".class") || name.endsWith(".xml")) ) {
                    subFileNames.add(name.replace(".class", ".java"));
                }
            }
        }


    }

    public MyJarFile(File jarFile, String sonarProjectName) {
        try {
            this.jarFile = new JarFile(jarFile);
        } catch (IOException e) {
            log.error("读取文件失败", e);
        }

        this.sonarProjectName = sonarProjectName;

        this.fileName = jarFile.getName();

        Enumeration<JarEntry> entries = this.jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            if (!jarEntry.isDirectory()) {
                if (subFileNames == null) {
                    subFileNames = new HashSet<>();
                }
                String name = jarEntry.getName();
                if (!name.contains("$") && (name.endsWith(".class") || name.endsWith(".xml")) ) {
                    subFileNames.add(name.replace(".class", ".java"));
                }
            }
        }

    }



}
