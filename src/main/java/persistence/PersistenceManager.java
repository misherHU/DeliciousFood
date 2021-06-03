package persistence;

import DomeinModellen.Recept;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;


import java.io.*;
import java.util.ArrayList;

public class PersistenceManager {
    private static final String ENDPOINT = "https://misherblopstorage.blob.core.windows.net/";
    private static final String SASTOKEN = "?sv=2020-02-10&ss=bfqt&srt=sco&sp=rwdlacuptfx&se=2021-06-14T23:00:15Z&st=2021-06-03T15:00:15Z&spr=https&sig=BNDTemU1EloAQ9nmeKaW95zgHVI6AXwxgBYk%2Bw0x%2FV8%3D";
    private static final String CONTAINER = "ipass";

    private static BlobContainerClient blobContainer = new BlobContainerClientBuilder()
                                                            .endpoint(ENDPOINT)
                                                            .sasToken(SASTOKEN)
                                                            .containerName(CONTAINER)
                                                            .buildClient();

    private PersistenceManager() {
    }

    public static void loadReceptenFromAzure(String blobNaam) throws IOException, ClassNotFoundException {
        if (blobContainer.exists()) {
            BlobClient blob = blobContainer.getBlobClient(blobNaam);

            if (blob.exists()) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                blob.download(baos);

                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bais);

                //World loadedWorld = (World)ois.readObject();
                //World.setWorld(loadedWorld);

                baos.close();
                ois.close();
            } else throw new IllegalStateException("container not found, loading default data");
        }
    }

    public static void saveDataToAzure() throws IOException {
        System.out.println("te ahinda");

        if (!blobContainer.exists()) {
            blobContainer.create();
        }
        ArrayList<Recept> alles=new ArrayList<>();
//        alles.add(new Recept("Korean Fried Chicken","15 minuten","Chicken"));
//        alles.add(new Recept("One Pan Chicken and Broccoli Stir Fry","30 minuten","Chicken"));
//        alles.add(new Recept("Roasted Chicken & Potatoes","1 uur","Chicken"));
//        alles.add(new Recept("Triple Smash w/Bacon & Cheese","15 minuten","Burger"));
//        alles.add(new Recept("Mexi-Mac Burger","30 minuten","Burger"));
//        alles.add(new Recept("Shrim Scampi Pasta","15 minuten","Pasta"));
//        alles.add(new Recept("One-Pan Spanish Garlic Pasta","30 minuten","Pasta"));

        BlobClient blob = blobContainer.getBlobClient("recepten");


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(alles);

        byte[] bytez = baos.toByteArray();

        ByteArrayInputStream bais = new ByteArrayInputStream(bytez);
        blob.upload(bais, bytez.length, true);

        oos.close();
        bais.close();
    }
}