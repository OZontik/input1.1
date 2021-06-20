package zozo.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String path = "Q:\\Games";
        StringBuilder log = new StringBuilder();

        if (prepareInstallDir(path)) {
            addToLog(log, "Создан корневой каталог");
            cDir(path + "/", new String[]{"src", "res", "savegames", "temp"}, log);
            cDir(path + "/"+"src/", new String[]{"main", "test"}, log);
            cFile(path + "/"+"src/main/", "Main.java", log);
            cFile(path + "/"+"src/main/", "Utils.java", log);
            cDir(path + "/"+"res/", new String[]{"drawables", "vectors", "icons"}, log);
            cFile(path + "/"+"temp/", "temp.txt", log);
            saveLogs(path + "/"+"temp/temp.txt", log);
        } else {
            addToLog(log, "Не создан корневой каталог");
        }
    }

    private static void saveLogs(String path, StringBuilder log) {
        File file = new File(path);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(log.toString());
            System.out.println("Done");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static boolean prepareInstallDir(String path) {
        File file = new File(path);
        return file.mkdirs();
    }

    public static void addToLog(StringBuilder log, String text) {
        log.append(text).append("\n ");
        System.out.println(text);
    }

    public static void cDir(String path, String[] directories, StringBuilder log) {
        for (String dir : directories) {
            File file = new File(path + dir);
            if (!file.exists()) {
                if (file.mkdir()) {
                    addToLog(log, "Создана папка " + file.getAbsolutePath());
                } else {
                    addToLog(log, "Папка " + file.getAbsolutePath() + " не была создана!");
                }
            }
        }
    }

    public static void cFile(String path, String fileName, StringBuilder log) {
        File newFile = new File(path, fileName);
        try {
            boolean created = newFile.createNewFile();
            if (created)
                addToLog(log, "Создан файл " + newFile.getAbsolutePath());
        } catch (IOException e) {
            addToLog(log, "Ошибка создания файла " + fileName + " в дирекории " + path + ": " + e.getMessage());
        }
    }


}
