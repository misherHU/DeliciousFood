package IPASS.persistence;

import IPASS.DomeinModellen.Recept;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;


import java.io.*;
import java.util.ArrayList;

public class PersistenceManager {
    private static final String ENDPOINT = "https://misherblopstorage.blob.core.windows.net/";
    private static final String SASTOKEN = "?sv=2020-02-10&ss=bfqt&srt=sco&sp=rwdlacuptfx&se=2021-07-08T23:54:44Z&st=2021-06-08T15:54:44Z&spr=https&sig=bVE1%2FGZldyLb1oFfVlmn5TH3ewQKMDGbMvXUDbSx%2FKs%3D";
    private static final String CONTAINER = "ipass";

    private static BlobContainerClient blobContainer = new BlobContainerClientBuilder()
                                                            .endpoint(ENDPOINT)
                                                            .sasToken(SASTOKEN)
                                                            .containerName(CONTAINER)
                                                            .buildClient();



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

    public static void saveDataToAzure() throws IOException, CloneNotSupportedException {
        System.out.println("begin van saveData to azure");

        if (!blobContainer.exists()) {
            blobContainer.create();
        }
        System.out.println("na het createn");
//        ArrayList<Recept> alless=new ArrayList<>();
//        alless.add(new Recept("Korean Fried Chicken","15 minuten","Chicken"));
//        alless.add(new Recept("One Pan Chicken and Broccoli Stir Fry","30 minuten","Chicken"));
//        alless.add(new Recept("Roasted Chicken & Potatoes","1 uur","Chicken"));
//        alless.add(new Recept("Triple Smash w/Bacon & Cheese","15 minuten","Burger"));
//        alless.add(new Recept("Mexi-Mac Burger","30 minuten","Burger"));
//        alless.add(new Recept("Shrim Scampi Pasta","15 minuten","Pasta"));
//        alless.add(new Recept("One-Pan Spanish Garlic Pasta","30 minuten","Pasta"));



        BlobClient blob = blobContainer.getBlobClient("recepten");
        System.out.println("na get blobclient");


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.out.println("maak boas");
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        System.out.println("maak oos");
        oos.writeObject(Recept.alleRecepten);
        System.out.println("na writen");

        byte[] bytez = baos.toByteArray();

        ByteArrayInputStream bais = new ByteArrayInputStream(bytez);
        blob.upload(bais, bytez.length, true);
        System.out.println("klaar");

        oos.close();
        bais.close();
    }
}