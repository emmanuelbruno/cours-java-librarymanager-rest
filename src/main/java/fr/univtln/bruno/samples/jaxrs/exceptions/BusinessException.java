package fr.univtln.bruno.samples.jaxrs.exceptions;

import jakarta.ws.rs.core.Response;
import lombok.Getter;

@Getter
public class BusinessException extends Exception {
    Response.Status status;

    public BusinessException(Response.Status status) {
        super(status.getReasonPhrase());
        this.status = status;
    }
}
