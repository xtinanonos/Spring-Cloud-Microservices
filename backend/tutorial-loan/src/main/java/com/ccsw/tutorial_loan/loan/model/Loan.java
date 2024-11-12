package com.ccsw.tutorial_loan.loan.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "game_id", nullable = false)
    private Long idGame;

    @Column(name = "client_id", nullable = false)
    private Long idClient;

    @JsonFormat(pattern = "dd.MM.yyyy")
    @Column(name = "date_start", nullable = false)
    private LocalDate date_start;

    @JsonFormat(pattern = "dd.MM.yyyy")
    @Column(name = "date_end", nullable = false)
    private LocalDate date_end;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id new value of {@link #getId}
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return game
     */
    public Long getIdGame() {
        return idGame;
    }

    /**
     * @param idGame new value of {@link #getIdGame}
     */
    public void setIdGame(Long idGame) {
        this.idGame = idGame;
    }

    /**
     * @return client
     */
    public Long getIdClient() {
        return idClient;
    }

    /**
     * @param idClient new value of {@link #getIdClient}
     */
    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    /**
     * @return start date of the loan.
     */
    public LocalDate getDate_start() {
        return date_start;
    }

    /**
     * @param date_start new value of {@link #getDate_start}.
     */
    public void setDate_start(LocalDate date_start) {
        this.date_start = date_start;
    }

    /**
     * @return end date of the loan.
     */
    public LocalDate getDate_end() {
        return date_end;
    }

    /**
     * @param date_end new value of {@link #getDate_end}.
     */
    public void setDate_end(LocalDate date_end) {
        this.date_end = date_end;
    }
}
