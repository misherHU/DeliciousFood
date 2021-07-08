package IPASS.persistence;

import IPASS.DomeinModellen.Account;
import IPASS.DomeinModellen.Recept;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;


import java.io.*;
import java.util.ArrayList;

public class PersistenceManager {
    private static final String ENDPOINT = "https://misherblopstorage.blob.core.windows.net/";
    private static final String SASTOKEN = "?sv=2020-08-04&ss=bfqt&srt=sco&sp=rwdlacuptfx&se=2021-11-08T11:04:18Z&st=2021-07-08T02:04:18Z&spr=https&sig=QThkqfNu61rjjUFWB%2F7dQOmbaKMzbek%2FoAY5ZJYSb9I%3D";
    private static final String CONTAINER = "ipass";

    private static BlobContainerClient blobContainer = new BlobContainerClientBuilder()
                                                            .endpoint(ENDPOINT)
                                                            .sasToken(SASTOKEN)
                                                            .containerName(CONTAINER)
                                                            .buildClient();



    public static void loadReceptenFromAzure() throws IOException, ClassNotFoundException {
        if (blobContainer.exists()) {
            BlobClient blob = blobContainer.getBlobClient("recepten");

            if (blob.exists()) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                blob.download(baos);

                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bais);
                Recept.alleRecepten=(ArrayList<Recept>) ois.readObject();
                //World loadedWorld =
                //World.setWorld(loadedWorld);
                //System.out.println();

                baos.close();
                ois.close();
            } else throw new IllegalStateException("container not found, loading default data");
        }
    }


    public static void loadAccountenFromAzure() throws IOException, ClassNotFoundException {
        if (blobContainer.exists()) {
            BlobClient blob = blobContainer.getBlobClient("accounts");

            if (blob.exists()) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                blob.download(baos);

                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bais);

                ArrayList<Account>loadedAccounten=(ArrayList<Account>) ois.readObject();
                Account.setAlleAccounten(loadedAccounten);
                System.out.println(Account.getAlleAccounten().get(1).getNaam());
                //World loadedWorld =
                //World.setWorld(loadedWorld);
                //System.out.println();

                baos.close();
                ois.close();
            } else throw new IllegalStateException("container not found, loading default data");
        }
    }




    public static void saveReceptenToAzure() throws IOException, CloneNotSupportedException {
        System.out.println("begin van saveData to azure");

        if (!blobContainer.exists()) {
            blobContainer.create();
        }
        System.out.println("na het createn");




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

    public static void saveAccountsToAzure() throws IOException, CloneNotSupportedException {
        System.out.println("begin van saveData to azure");

        if (!blobContainer.exists()) {
            blobContainer.create();
        }
        System.out.println("na het createn");




        BlobClient blob = blobContainer.getBlobClient("accounts");
        System.out.println("na get blobclient");


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.out.println("maak boas");
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        System.out.println("maak oos");
        oos.writeObject(Account.getAlleAccounten());
        System.out.println("na writen");

        byte[] bytez = baos.toByteArray();

        ByteArrayInputStream bais = new ByteArrayInputStream(bytez);
        blob.upload(bais, bytez.length, true);
        System.out.println("klaar");

        oos.close();
        bais.close();
    }
}