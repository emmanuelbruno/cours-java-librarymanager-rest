package fr.univtln.bruno.samples.jaxrs.mappers;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Provider
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class GenericExceptionMapper implements ExceptionMapper<Exception> {
    Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;

    public Response toResponse(Exception ex) {
        return Response.status(status)
                .entity(ex.getMessage())
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
