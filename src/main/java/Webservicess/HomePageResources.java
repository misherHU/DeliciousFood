package Webservicess;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import DomeinModellen.Recept;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import listeners.MyServletContextListener;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

@Path("/home")
public class HomePageResources {
    public static ArrayList<Recept> alleRecepten;
    private static final String ENDPOINT = "https://misherblopstorage.blob.core.windows.net/";
    private static final String SASTOKEN = "?sv=2020-02-10&ss=bfqt&srt=sco&sp=rwdlacupx&se=2021-05-11T20:16:00Z&st=2021-05-11T12:16:00Z&spr=https&sig=t8YiPgvvvT2C5H18eUCYB1hMbbtf5pNyUKUyqXW9fUM%3D";
    private static final String CONTAINER = "ipass";

    private static final BlobContainerClient blobContainer = new BlobContainerClientBuilder()
            .endpoint(ENDPOINT)
            .sasToken(SASTOKEN)
            .containerName(CONTAINER)
            .buildClient();

    @GET
    @Produces("application/json")
    public Response getGesorteerdeResultaten() throws IOException, ClassNotFoundException {
        //Mijn Azure storage wilt niet opslaan voor de een of ander reden dus voor deze sprint (sprint 2) pak ik de recepten gewoon uit een gemaakte lijk inplaats van een echte storage.

//        if (blobContainer.exists()) {
//            BlobClient blob = blobContainer.getBlobClient("recepten");
//
//            if (blob.exists()) {
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                blob.download(baos);
//
//                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
//                ObjectInputStream ois = new ObjectInputStream(bais);
//                alleRecepten=(ArrayList<Recept>)ois.readObject();
//
//
//                baos.close();
//                ois.close();
//            } else throw new IllegalStateException("container not found, loading default data");
//        }
        return Response.ok(MyServletContextListener.alles).build();
    }

}








//World loadedWorld = (World)ois.readObject();
//                //World.setWorld(loadedWorld);