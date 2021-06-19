import java.io.*;
import java.util.zip.*;

public class Main {
    static StringBuilder log = new StringBuilder();

    public static void main(String[] args) {

        File srcDir = new File("D:" + File.separator + "Netology" + File.separator + "Games" + File.separator + "src");
        File resDir = new File("D:" + File.separator + "Netology" + File.separator + "Games" + File.separator + "res");
        File saveGamesDir = new File("D:" + File.separator + "Netology" + File.separator + "Games" + File.separator + "savegames");
        File tempDir = new File("D:" + File.separator + "Netology" + File.separator + "Games" + File.separator + "temp");
        File mainDir = new File("D:" + File.separator + "Netology" + File.separator + "Games" + File.separator + "src" + File.separator + "main");
        File testDir = new File("D:" + File.separator + "Netology" + File.separator + "Games" + File.separator + "src" + File.separator + "test");
        File mainFileJava = new File("D:" + File.separator + "Netology" + File.separator + "Games" + File.separator + "src" + File.separator + "main" + File.separator + "Main.java");
        File utilsFileJava = new File("D:" + File.separator + "Netology" + File.separator + "Games" + File.separator + "src" + File.separator + "main" + File.separator + "Utils.java");
        File drawablesDir = new File("D:" + File.separator + "Netology" + File.separator + "Games" + File.separator + "res" + File.separator + "drawables");
        File vectorsDir = new File("D:" + File.separator + "Netology" + File.separator + "Games" + File.separator + "res" + File.separator + "vectors");
        File iconsDir = new File("D:" + File.separator + "Netology" + File.separator + "Games" + File.separator + "res" + File.separator + "icons");
        File tempFile = new File("D:" + File.separator + "Netology" + File.separator + "Games" + File.separator + "temp" + File.separator + "temp.txt");
        String saveFile1 = "D:" + File.separator + "Netology" + File.separator + "Games" + File.separator + "savegames" + File.separator + "save1.dat";
        String saveFile2 = "D:" + File.separator + "Netology" + File.separator + "Games" + File.separator + "savegames" + File.separator + "save2.dat";
        String saveFile3 = "D:" + File.separator + "Netology" + File.separator + "Games" + File.separator + "savegames" + File.separator + "save3.dat";
        String zipFile = "D:" + File.separator + "Netology" + File.separator + "Games" + File.separator + "savegames" + File.separator + "saves.zip";

        //Этап 1
        createDir(srcDir);
        createDir(resDir);
        createDir(saveGamesDir);
        createDir(tempDir);

        //Этап 2
        createDir(mainDir);
        createDir(testDir);

        //Этап 3
        createFile(mainFileJava);
        createFile(utilsFileJava);

        //Этап 4
        createDir(drawablesDir);
        createDir(vectorsDir);
        createDir(iconsDir);

        //Этап 5
        createFile(tempFile);
        createLog(tempFile);

        //Задача 2.
        //Создаем экземпляры класса
        GameProgress gameProgress1 = new GameProgress(180, 80, 3, 345.23);
        GameProgress gameProgress2 = new GameProgress(230, 110, 7, 524.65);
        GameProgress gameProgress3 = new GameProgress(65, 5, 1, 1400.99);

        //Делаем 3 сохранения
        saveGame(saveFile1, gameProgress1);
        saveGame(saveFile2, gameProgress2);
        saveGame(saveFile3, gameProgress3);

        //Архивируем сохранения
        zipFiles(zipFile, saveGamesDir.listFiles());

        //Задача 3.
        //Разархивируем сохранения
        openZip(zipFile, saveGamesDir.getPath());

        //Выведем в консоль десериализацию
        System.out.println(openProgress(saveFile1));
        System.out.println(openProgress(saveFile2));
        System.out.println(openProgress(saveFile3));

    }

    //Метод создания директорий
    private static void createDir(File file) {
        if (file.mkdir()) {
            log.append("Создана дирректория: " + file.getName() + ", Путь: " + file.getParent() + "\n");
        } else if (file.exists()) {
            log.append("Дирректория уже создана " + file.getName() + ", Путь: " + file.getParent() + "\n");
        } else {
            log.append("Не удалось создать дирректорию: " + file.getName() + ", Путь: " + file.getParent() + "\n");
        }
    }

    //Метод создания файлов
    private static void createFile(File file) {
        try {
            if (file.createNewFile()) {
                log.append("Создан Файл: " + file.getName() + ", Путь: " + file.getParent() + "\n");
            } else if (file.exists()) {
                log.append("Файл уже создан " + file.getName() + ", Путь: " + file.getParent() + "\n");
            }
        } catch (IOException ex) {
            log.append("Не удалось создать файл: " + file.getName() + ", Путь: " + file.getParent() + "\n");
            System.out.println(ex.getMessage());
        }
    }

    //Метод записи логов в файл
    private static void createLog(File file) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            bufferedWriter.write(log.toString());
            bufferedWriter.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //Метод сохранения игрового процесса(Сериализация)
    public static void saveGame(String filePath, GameProgress gameProgress) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println("Не удалось сохранить файл");
        }
    }

    //Метод архивирующий файлы
    public static void zipFiles(String zipPath, File[] filePath) {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipPath))) {
            for (File file : filePath) {
                ZipEntry entry = new ZipEntry(file.getName());
                zipOutputStream.putNextEntry(entry);
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] buffer = new byte[fileInputStream.available()];
                fileInputStream.read(buffer);
                zipOutputStream.write(buffer);
                zipOutputStream.closeEntry();
                fileInputStream.close();
                file.delete();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //Метод разархивирующий файлы
    public static void openZip(String zipPath, String savesPath) {
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipPath))) {
            ZipEntry entry;
            String name;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                name = entry.getName();
                File file = new File(savesPath, name);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                for (int c = zipInputStream.read(); c != -1; c = zipInputStream.read()) {
                    fileOutputStream.write(c);
                }
                fileOutputStream.flush();
                zipInputStream.closeEntry();
                fileOutputStream.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //Метод загрузки игрового процесса(Десериализация)
    public static GameProgress openProgress(String filePath) {
        GameProgress gameProgress = null;
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            gameProgress = (GameProgress) objectInputStream.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return gameProgress;
    }
}