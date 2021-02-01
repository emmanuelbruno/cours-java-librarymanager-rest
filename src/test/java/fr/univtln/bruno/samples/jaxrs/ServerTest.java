package fr.univtln.bruno.samples.jaxrs;

import fr.univtln.bruno.samples.jaxrs.model.BiblioModel.Auteur;
import fr.univtln.bruno.samples.jaxrs.server.BiblioServer;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import junit.framework.TestCase;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.message.internal.MediaTypes;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;


public class ServerTest extends TestCase {

    private HttpServer httpServer;

    private WebTarget webTarget;

    public ServerTest(String testName) {
        super(testName);
    }

    @BeforeClass
    protected void setUp() throws Exception {
        //start the Grizzly2 web container
        httpServer = BiblioServer.startServer();
        // create the client
        Client client = ClientBuilder.newClient();
        webTarget = client.target(BiblioServer.BASE_URI);
    }

    @AfterClass
    protected void tearDown() throws Exception {
        httpServer.shutdown();
    }

    @Before
    protected void beforeEach() {
        webTarget.path("biblio/init").request(MediaType.TEXT_PLAIN).put(Entity.entity("", MediaType.TEXT_PLAIN));
    }

    @After
    protected void afterEach() {
        webTarget.path("biblio").request(MediaType.TEXT_PLAIN).delete();
    }

    /**
     * Test to get the auteur of id 1.
     */
    public void testGetAuteurJSON() {
        Auteur responseAuteur = webTarget.path("biblio/auteurs/1").request(MediaType.APPLICATION_JSON).get(Auteur.class);
        assertNotNull(responseAuteur);
        assertEquals("Jean", responseAuteur.getPrenom());
        assertEquals("Martin", responseAuteur.getNom());
    }
    public void testGetAuteurXML() {
        Auteur responseAuteur = webTarget.path("biblio/auteurs/1").request(MediaType.TEXT_XML).get(Auteur.class);
        assertNotNull(responseAuteur);
        assertEquals("Jean", responseAuteur.getPrenom());
        assertEquals("Martin", responseAuteur.getNom());
    }

    /**
     * Test if a WADL document is available at the relative path
     * "application.wadl".
     */
    public void testApplicationWadl() {
        String serviceWadl = webTarget.path("application.wadl")
                .request(MediaTypes.WADL_TYPE)
                .get(String.class);
        assertTrue(serviceWadl.length() > 0);
    }
}
