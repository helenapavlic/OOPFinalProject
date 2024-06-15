package VendingMachine.Model;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AUX_CLS {

    public <T> void printListItems(ArrayList<T> list){
        for(T listItem : list){
            System.out.println(listItem);
        }
    }

    public static boolean fileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }


    public static <T> T readObjectFromFile(String filePath) {
        T object = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(filePath)))) {
            object = (T) ois.readObject();
            System.out.println("object successfully read: " + object);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return object;
    }

    //    spremanje objekta u datoteku
    public static <T> void addObjectToFile(String filePath, T object) {
//        try with resources
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(filePath)))) {
            oos.writeObject(object);
            System.out.println("Object written to file");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




}
