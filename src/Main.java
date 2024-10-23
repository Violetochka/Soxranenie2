import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress game1 = new GameProgress(6, 50, 3, 1011);
        GameProgress game2 = new GameProgress(9,200,7,2000);
        GameProgress game3 = new GameProgress(10,250,8,8000);
        saveGame("C://Games//savegames//game1.dat", game1);
        saveGame("C://Games//savegames//game2.dat", game2);
        saveGame("C://Games//savegames//game3.dat", game3);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("C://Games//savegames//game1.dat");
        arrayList.add("C://Games//savegames//game2.dat");
        arrayList.add("C://Games//savegames//game3.dat");
        zipFiles("C://Games//savegames//zip.zip", arrayList);
        File game1Dat = new File("C://Games//savegames//game1.dat");
        File game2Dat = new File("C://Games//savegames//game2.dat");
        File game3Dat = new File("C://Games//savegames//game3.dat");
        if (game1Dat.delete()) System.out.println("Файл \"game1.dat\" удален");
        if (game2Dat.delete()) System.out.println("Файл \"game2.dat\" удален");
        if (game3Dat.delete()) System.out.println("Файл \"game3.dat\" удален");
    }

    private static void saveGame(String path, GameProgress game) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    private static void zipFiles(String path, List<String> arrayList) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path))) {
            for (String arr : arrayList) {
                try (FileInputStream fis = new FileInputStream(arr)) {
                    ZipEntry ze = new ZipEntry(arr);
                    zout.putNextEntry(ze);
                    while (fis.available() > 0) {
                        zout.write(fis.read());
                    }
                    zout.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
