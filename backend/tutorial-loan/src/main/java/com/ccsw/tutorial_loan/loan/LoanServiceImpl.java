package com.ccsw.tutorial_loan.loan;

import com.ccsw.tutorial_loan.client.ClientsClient;
import com.ccsw.tutorial_loan.game.GameClient;
import com.ccsw.tutorial_loan.game.model.GameDto;
import com.ccsw.tutorial_loan.loan.model.Loan;
import com.ccsw.tutorial_loan.loan.model.LoanDto;
import com.ccsw.tutorial_loan.loan.model.LoanSearchDto;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {
    @Autowired
    LoanRepository loanRepository;

    @Autowired
    GameClient gameClient;

    @Autowired
    ClientsClient clientClient;

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Loan> find(LoanSearchDto dto) {
        Pageable pageable = PageRequest.of(dto.getPageable().getPageNumber(), dto.getPageable().getPageSize());
        String gameName = dto.getGameName();
        Long idClient = dto.getIdClient();
        LocalDate date = dto.getDate();

        // Obtemos todos los prestamos
        List<Loan> loans = (List<Loan>) loanRepository.findAll();    // .findAll() devuelve un iterable, lo casteamos

        // Filtra por el nombre del juego
        if (gameName != null && !gameName.isEmpty()) {

            // recuperamos todos los juegos del microservicio Game
            List<GameDto> games = gameClient.findAll();
      
            // obtenemos una lista de gameIds filtrando la lista de games por su titulo
            List<Long> gameIds = games.stream().filter(game -> game.getTitle().equalsIgnoreCase(gameName)).map(GameDto::getId).collect(Collectors.toList());

            // filtramos los loans por aquellos en los que coincida el gameId con la lista de gameIds
            loans = loans.stream().filter(loan -> gameIds.contains(loan.getIdGame())).collect(Collectors.toList());
            System.out.println("loans ya filtrados" + loans);
        }

        // Filtra por el id del cliente
        if (idClient != null) {
            loans = loans.stream().filter(loan -> loan.getIdClient().equals(idClient)).collect(Collectors.toList());
        }

        // Filtra por la fecha comprobando que la fecha de inicio sea anterior a la fecha, y tambien que la final sea posterior a la fecha (dentro del rango)
        if (date != null) {
            loans = loans.stream().filter(loan -> loan.getDate_start().isBefore(date) && loan.getDate_end().isAfter(date)).collect(Collectors.toList());
        }

        // Convierte la lista filtrada a una página
        int start = (int) pageable.getOffset(); // indice del primer elemento de la pag actual
        int end = Math.min((start + pageable.getPageSize()), loans.size());     // indice final: o el maximo posible o, si es menor, el total de elementos
        List<Loan> subList = loans.subList(start, end);   // elementos del indice inicial al maximo
        Page<Loan> page = new PageImpl<>(subList, pageable, loans.size());

        return page;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Loan get(Long id) {
        return this.loanRepository.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(LoanDto dto) throws ValidationException {
        Loan loan = new Loan();

        //copia la informacion del dto (controller) al loan (entidad bbdd), excepto el id, el game y el client
        BeanUtils.copyProperties(dto, loan, "id", "game", "client");

        // en su lugar, se llama al id de game y al id de client
        loan.setIdGame(dto.getGame().getId());
        loan.setIdClient(dto.getClient().getId());

        List<String> errorMessages = new ArrayList<>();

        // Validaciones --> esta forma de ir añadiendo los mensajes en un array de strings sirve
        // para que se puedan validar todos los campos, y envie todos los mensajes de error.

        if (!validateLoanPeriod(loan)) {
            errorMessages.add("El periodo del préstamo no es válido.");
        }
        if (!validateGameIsAvailable(loan)) {
            errorMessages.add("El juego no está disponible en el período seleccionado.");
        }
        if (!validateClientLoans(loan)) {
            errorMessages.add("El cliente ya tiene préstamos en el mismo período.");
        }

        if (!errorMessages.isEmpty()) {
            throw new ValidationException(String.join(", ", errorMessages));  // Lanza todos los errores en un solo mensaje
        }

        // Guardar el préstamo
        this.loanRepository.save(loan);
    }

    // comprueba que el cliente no tenga préstamos en el período seleccionado
    private boolean validateClientLoans(Loan loan) {
        List<Loan> loansForClient = loanRepository.findByClientIdAndDateRange(loan.getIdClient(), loan.getDate_start(), loan.getDate_end());

        return loansForClient.isEmpty();
    }

    // comprueba que el juego esté disponible en el período seleccionado
    private boolean validateGameIsAvailable(Loan loan) {
        List<Loan> loansForGame = loanRepository.findByGameIdAndDateRange(loan.getIdGame(), loan.getDate_start(), loan.getDate_end());

        return loansForGame.isEmpty();
    }

    // comprueba que el período sea válido
    private boolean validateLoanPeriod(Loan loan) {
        // Verifica que la fecha de inicio no sea posterior a la fecha de fin
        if (loan.getDate_start().isAfter(loan.getDate_end())) {
            return false;
        }
        // metodo ChronoUnit para calcular diferencia entre dos fechas
        long diasPrestamo = ChronoUnit.DAYS.between(loan.getDate_start(), loan.getDate_end());
        return diasPrestamo <= 14;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {
        if (this.get(id) == null) {
            throw new Exception("Not exists");
        }

        this.loanRepository.deleteById(id);
    }
}
