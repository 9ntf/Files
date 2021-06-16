import java.io.*;

public class Main {
    public static void main(String[] args) {
        StringBuilder log = new StringBuilder();

        File srcDir = new File("D:\\Netology\\Games\\src");
        File resDir = new File("D:\\Netology\\Games\\res");
        File saveGamesDir = new File("D:\\Netology\\Games\\savegames");
        File tempDir = new File("D:\\Netology\\Games\\temp");
        File mainDir = new File("D:\\Netology\\Games\\src\\main");
        File testDir = new File("D:\\Netology\\Games\\src\\test");
        File mainFileJava = new File("D:\\Netology\\Games\\src\\main\\Main.java");
        File utilsFileJava = new File("D:\\Netology\\Games\\src\\main\\Utils.java");
        File drawablesDir = new File("D:\\Netology\\Games\\res\\drawables");
        File vectorsDir = new File("D:\\Netology\\Games\\res\\vectors");
        File iconsDir = new File("D:\\Netology\\Games\\res\\icons");
        File tempFile = new File("D:\\Netology\\Games\\temp\\temp.txt");

        //Этап 1
        if (srcDir.mkdir()) {
            log.append("Создана дирректория: " + srcDir.getName() + ", Путь: " + srcDir.getParent() + "\n");
        }
        if (resDir.mkdir()) {
            log.append("Создана дирректория: " + resDir.getName() + ", Путь: " + resDir.getParent() + "\n");
        }
        if (saveGamesDir.mkdir()) {
            log.append("Создана дирректория: " + saveGamesDir.getName() + ", Путь: " + saveGamesDir.getParent() + "\n");
        }
        if (tempDir.mkdir()) {
            log.append("Создана дирректория: " + tempDir.getName() + ", Путь: " + tempDir.getParent() + "\n");
        }

        //Этап 2
        if (mainDir.mkdir()) {
            log.append("Создана дирректория: " + mainDir.getName() + ", Путь: " + mainDir.getParent() + "\n");
        }
        if (testDir.mkdir()) {
            log.append("Создана дирректория: " + testDir.getName() + ", Путь: " + testDir.getParent() + "\n");
        }

        //Этап 3
        try {
            if (mainFileJava.createNewFile()) {
                log.append("Создан Файл: " + mainFileJava.getName() + ", Путь: " + mainFileJava.getParent() + "\n");
            }
            if (utilsFileJava.createNewFile()) {
                log.append("Создан Файл: " + utilsFileJava.getName() + ", Путь: " + utilsFileJava.getParent() + "\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        //Этап 4
        if (drawablesDir.mkdir()) {
            log.append("Создана дирректория: " + drawablesDir.getName() + ", Путь: " + drawablesDir.getParent() + "\n");
        }
        if (vectorsDir.mkdir()) {
            log.append("Создана дирректория: " + vectorsDir.getName() + ", Путь: " + vectorsDir.getParent() + "\n");
        }
        if (iconsDir.mkdir()) {
            log.append("Создана дирректория: " + iconsDir.getName() + ", Путь: " + iconsDir.getParent() + "\n");
        }

        //Этап 5
        try {
            if (tempFile.createNewFile()) {
                log.append("Создан Файл: " + tempFile.getName() + ", Путь: " + tempFile.getParent() + "\n");
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));
            bufferedWriter.write(log.toString());
            bufferedWriter.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}