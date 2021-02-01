package fr.univtln.bruno.samples.jaxrs.resources;


import fr.univtln.bruno.samples.jaxrs.model.BiblioModel;
import fr.univtln.bruno.samples.jaxrs.model.BiblioModel.Auteur;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.Collection;

// The Java class will be hosted at the URI path "/biblio"
@Path("biblio")
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
public class BiblioResource {
    private static BiblioModel modeleBibliotheque = BiblioModel.of();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello() {
        return "hello";
    }

    @PUT
    @Path("init")
    public int init() {
        modeleBibliotheque.putAuteur(Auteur.builder().prenom("Jean").nom("Martin").build());
        modeleBibliotheque.putAuteur(Auteur.builder().prenom("Marie").nom("Durand").build());
        return modeleBibliotheque.getAuteurSize();
    }

    @PUT
    @Path("auteurs")
    public void ajouterAuteur(@QueryParam("prenom") String prenom, @QueryParam("nom") String nom) {
        modeleBibliotheque.putAuteur(Auteur.builder().prenom(prenom).nom(nom).build());
    }

    @DELETE
    @Path("auteurs/{id}")
    public void supprimerAuteur(@PathParam("id") final long id) {
        modeleBibliotheque.removeAuteur(id);
    }

    @DELETE
    public void supprimerAuteurs() {
        modeleBibliotheque.supprimerAuteurs();
    }

    @GET
    @Path("auteurs/{id}")
    public Auteur getAuteur(@PathParam("id") final long id) {
        return modeleBibliotheque.getAuteur(id);
    }

    @GET
    @Path("auteurs")
    public Collection<Auteur> getAuteurs() {
        return modeleBibliotheque.getAuteurs().values();
    }
}
