package fr.univtln.bruno.samples.jaxrs.client;

import fr.univtln.bruno.samples.jaxrs.model.BiblioModel.Auteur;
import fr.univtln.bruno.samples.jaxrs.server.BiblioServer;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

/**
 * Created by bruno on 04/11/14.
 */

public class BiblioClient {
    public static void main(String[] args) {
        // create the client
        Client client = ClientBuilder.newClient();
        WebTarget webResource = client.target(BiblioServer.BASE_URI);

        //Send a get with a String as response
        String responseAuteursAsJson = webResource.path("biblio/auteurs").request().get(String.class);
        System.out.println(responseAuteursAsJson);

        //Idem but the result is deserialised to an instance of Auteur
        Auteur auteur = webResource.path("biblio/auteur/1").request().get(Auteur.class);
        System.out.println(auteur);

        webResource.path("biblio/auteur")
                .queryParam("nom", "John").queryParam("prenom", "Smith")
                .request().put(Entity.entity(Auteur.builder().prenom("John").nom("Smith").build(),
                MediaType.APPLICATION_JSON));
        System.out.println(webResource.path("biblio/auteurs").request().get(String.class));
    }
}
