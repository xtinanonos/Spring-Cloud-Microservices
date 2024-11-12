package com.ccsw.tutorial_loan.loan.model;

import com.ccsw.tutorial_loan.common.pagination.PageableRequest;

import java.time.LocalDate;

public class LoanSearchDto {

    private String gameName; // Nombre del juego para filtrar
    private Long idClient; // ID del cliente para filtrar
    private LocalDate date; // Fecha para filtrar
    private PageableRequest pageable; // Objeto de paginación

    // Getters y Setters
    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public PageableRequest getPageable() {
        return pageable;
    }

    public void setPageable(PageableRequest pageable) {
        this.pageable = pageable;
    }
}
