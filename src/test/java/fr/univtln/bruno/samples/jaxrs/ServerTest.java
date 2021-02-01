package fr.univtln.bruno.samples.jaxrs;

import fr.univtln.bruno.samples.jaxrs.model.BiblioModel.Auteur;
import fr.univtln.bruno.samples.jaxrs.server.BiblioServer;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.message.internal.MediaTypes;
import org.junit.*;

import static org.junit.Assert.*;

public class ServerTest {

    private static HttpServer httpServer;

    private static WebTarget webTarget;

    @BeforeClass
    public static void setUp() throws Exception {
        //start the Grizzly2 web container
        httpServer = BiblioServer.startServer();
        // create the client
        Client client = ClientBuilder.newClient();
        webTarget = client.target(BiblioServer.BASE_URI);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        httpServer.shutdown();
    }

    @Before
    public void beforeEach() {
        webTarget.path("biblio/init").request().put(Entity.entity("", MediaType.TEXT_PLAIN));
    }

    @After
    public void afterEach() {
        webTarget.path("biblio").request().delete();
    }

    /**
     * Test to get the auteur of id 1.
     */
    @Test
    public void testGetAuteurJSON() {
        Auteur responseAuteur = webTarget.path("biblio/auteurs/1").request(MediaType.APPLICATION_JSON).get(Auteur.class);
        assertNotNull(responseAuteur);
        assertEquals("Jean", responseAuteur.getPrenom());
        assertEquals("Martin", responseAuteur.getNom());
    }

    @Test
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
    @Test
    public void testApplicationWadl() {
        String serviceWadl = webTarget.path("application.wadl")
                .request(MediaTypes.WADL_TYPE)
                .get(String.class);
        assertTrue(serviceWadl.length() > 0);
    }
}
