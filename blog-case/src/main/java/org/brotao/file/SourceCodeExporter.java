package org.brotao.file;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.regex.Pattern;

/**
 * @author luotao
 * @date 2022-03-29 15:06
 */
public class SourceCodeExporter {

    private static Pattern pattern = Pattern.compile("/\\*.+?\\*/", Pattern.DOTALL);

    public static void main(String[] args) throws IOException {
        main2(args);
    }

    public static void main1(String[] args) throws IOException {
        String projectSrc = "e:\\SVN\\IDG\\ift-parent\\";
        Collection<File> files = FileUtils.listFiles(new File(projectSrc), new String[]{"java"}, true);

        File destFile = new File(projectSrc + "backendSrc.txt");
        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(destFile.getAbsoluteFile(), true);

        for (File file : files) {
            String filePath = file.getAbsolutePath();
            if ((!filePath.contains("org\\apache")) && (!filePath.contains("src\\test\\"))) {
                String str = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
                str.replaceAll("//.+\\r\\n", "");
                str.replaceAll("^\\s*\\n", "");
                str = pattern.matcher(str).replaceAll("");
                fileWriter.write(str);
                fileWriter.flush();
            }
            fileWriter.write("\n");
            fileWriter.flush();
        }
        fileWriter.close();

    }

    public static void main2(String[] args) throws IOException {
        String projectSrc = "e:\\SVN\\IDG\\ift_web\\src\\";
        Collection<File> files = FileUtils.listFiles(new File(projectSrc), new String[]{"vue", "js"}, true);

        File destFile = new File(projectSrc + "frontendSrc.txt");
        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(destFile.getAbsoluteFile(), true);

        for (File file : files) {
            String filePath = file.getAbsolutePath();
            if ((!filePath.contains("org\\apache")) && (!filePath.contains("src\\test\\"))) {
                String str = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
                str.replaceAll("//.+\\r\\n", "");
                str.replaceAll("^\\s*\\n", "");
                str = pattern.matcher(str).replaceAll("");
                fileWriter.write(str);
                fileWriter.flush();
            }
            fileWriter.write("\n");
            fileWriter.flush();
        }
        fileWriter.close();

    }
}
