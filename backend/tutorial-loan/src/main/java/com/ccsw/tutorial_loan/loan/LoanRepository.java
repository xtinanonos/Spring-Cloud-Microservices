package com.ccsw.tutorial_loan.loan;

import com.ccsw.tutorial_loan.loan.model.Loan;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface LoanRepository extends CrudRepository<Loan, Long>, JpaSpecificationExecutor<Loan> {

    /**
     * Método para recuperar un listado de {@link Loan}
     * @param gameId id
     * @param startDate fecha de inicio del {@link Loan}
     * @param endDate fecha final del {@link Loan}
     * @return {@link List} de {@link Loan}
     */
    @Query("SELECT l FROM Loan l WHERE l.idGame = :gameId AND (" + "l.date_start BETWEEN :startDate AND :endDate OR " + "l.date_end BETWEEN :startDate AND :endDate OR " + "(l.date_start <= :startDate AND l.date_end >= :endDate))")
    List<Loan> findByGameIdAndDateRange(Long gameId, LocalDate startDate, LocalDate endDate);

    /**
     * Método para recuperar un listado de {@link Loan}
     * @param clientId id
     * @param startDate fecha de inicio del {@link Loan}
     * @param endDate fecha final del {@link Loan}
     * @return {@link List} de {@link Loan}
     */
    @Query("SELECT l FROM Loan l WHERE l.idClient = :clientId AND (" + "l.date_start BETWEEN :startDate AND :endDate OR " + "l.date_end BETWEEN :startDate AND :endDate OR " + "(l.date_start <= :startDate AND l.date_end >= :endDate))")
    List<Loan> findByClientIdAndDateRange(Long clientId, LocalDate startDate, LocalDate endDate);
}
