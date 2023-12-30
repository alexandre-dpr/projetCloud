package com.projetcloud.util;

import com.projetcloud.dto.response.ErrorDTO;
import com.projetcloud.exceptions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@ControllerAdvice(basePackages = "com.projetcloud.controller")
public class ErrorExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(LoginAlreadyUsedException.class)
    public ResponseEntity<Object> handleLoginAlreadyUsedException(Exception ex, WebRequest request) {
        ErrorDTO errorDTO = createDTO("L'identifiant est déjà prit", HttpStatus.CONFLICT, ex, request);
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.valueOf(errorDTO.getStatus()), request);
    }

    @ExceptionHandler(BadLoginException.class)
    public ResponseEntity<Object> handleBadLoginException(Exception ex, WebRequest request) {
        ErrorDTO errorDTO = createDTO("Identifiant incorrecte", HttpStatus.UNAUTHORIZED, ex, request);
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.valueOf(errorDTO.getStatus()), request);
    }

    @ExceptionHandler(JoueurInexistantException.class)
    public ResponseEntity<Object> JoueurInexistantException(Exception ex, WebRequest request) {
        ErrorDTO errorDTO = createDTO("Le joueur n'a pas été trouvé", HttpStatus.NOT_FOUND, ex, request);
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.valueOf(errorDTO.getStatus()), request);
    }

    @ExceptionHandler(PartieInexistanceException.class)
    public ResponseEntity<Object> PartieInexistanceException(Exception ex, WebRequest request) {
        ErrorDTO errorDTO = createDTO("La partie n'a pas été trouvé", HttpStatus.NOT_FOUND, ex, request);
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.valueOf(errorDTO.getStatus()), request);
    }

    @ExceptionHandler(SalonInexistantException.class)
    public ResponseEntity<Object> SalonInexistantException(Exception ex, WebRequest request) {
        ErrorDTO errorDTO = createDTO("Le salon n'a pas été trouvé", HttpStatus.NOT_FOUND, ex, request);
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.valueOf(errorDTO.getStatus()), request);
    }

    @ExceptionHandler(DejaDansSalonException.class)
    public ResponseEntity<Object> DejaDansSalonException(Exception ex, WebRequest request) {
        ErrorDTO errorDTO = createDTO("Déjà dans le salon", HttpStatus.NOT_FOUND, ex, request);
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.valueOf(errorDTO.getStatus()), request);
    }

    @ExceptionHandler(TropDeJoueurException.class)
    public ResponseEntity<Object> TropDeJoueurException(Exception ex, WebRequest request) {
        ErrorDTO errorDTO = createDTO("Il y a trop de joueurs pour cette partie", HttpStatus.CONFLICT, ex, request);
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.valueOf(errorDTO.getStatus()), request);
    }
    @ExceptionHandler(PartieTermineException.class)
    public ResponseEntity<Object> PartieTermineException(Exception ex, WebRequest request) {
        ErrorDTO errorDTO = createDTO("La partie est fini", HttpStatus.CONFLICT, ex, request);
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.valueOf(errorDTO.getStatus()), request);
    }
    @ExceptionHandler(MauvaisesCoordonneesExcpetion.class)
    public ResponseEntity<Object> MauvaisesCoordonneesExcpetion(Exception ex, WebRequest request) {
        ErrorDTO errorDTO = createDTO("Coordonnées invalides", HttpStatus.UNAUTHORIZED, ex, request);
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.valueOf(errorDTO.getStatus()), request);
    }
    @ExceptionHandler(MauvaisTourException.class)
    public ResponseEntity<Object> MauvaisTourException(Exception ex, WebRequest request) {
        ErrorDTO errorDTO = createDTO("Pas votre tour", HttpStatus.UNAUTHORIZED, ex, request);
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.valueOf(errorDTO.getStatus()), request);
    }
    @ExceptionHandler(CoupNonAutoriseException.class)
    public ResponseEntity<Object> CoupNonAutoriseException(Exception ex, WebRequest request) {
        ErrorDTO errorDTO = createDTO("Coup non authorisé", HttpStatus.UNAUTHORIZED, ex, request);
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.valueOf(errorDTO.getStatus()), request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        System.out.println(ex.getStackTrace()[0].getFileName());
        System.out.println(ex.getStackTrace()[0].getLineNumber());
        System.out.println(ex.getClass());
        ErrorDTO errorDTO = createDTO("Une erreur est survenue.", HttpStatus.BAD_REQUEST, ex, request);
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    public ErrorDTO createDTO(String customMessage, HttpStatus status, Exception ex, WebRequest request) {
        ErrorDTO errorDTO;
        if (Objects.isNull(ex.getCause()) || Objects.isNull(ex.getCause().getMessage())) {
            errorDTO = new ErrorDTO(customMessage, status, ((ServletWebRequest) request).getRequest().getRequestURI());
        } else {
            errorDTO = new ErrorDTO(ex.getCause().getMessage(), status, ((ServletWebRequest) request).getRequest().getRequestURI());
        }
        return errorDTO;
    }
}
