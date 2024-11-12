package com.ccsw.tutorial_loan.loan;

import com.ccsw.tutorial_loan.loan.model.Loan;
import com.ccsw.tutorial_loan.loan.model.LoanDto;
import com.ccsw.tutorial_loan.loan.model.LoanSearchDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ValidationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Tag(name = "Loan", description = "API of Loan")
@RequestMapping(value = "/loan")
@RestController
@CrossOrigin(origins = "*")
public class LoanController {

    @Autowired
    LoanService loanService;

    @Autowired
    ModelMapper mapper;

    /**
     * Recupera un listado de prestamos paginados y filtrados {@link Loan}
     * @return {@link Page} de {@link LoanDto}
     */
    @Operation(summary = "Find Loans", description = "Method that returns a filtered and/or paginated list of Loans")
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Page<LoanDto> findLoans(@RequestBody LoanSearchDto loanSearchDto) {

        Page<Loan> page = loanService.find(loanSearchDto);       // Llama al servicio pasandole los parametros de busqueda

        // Transformar a LoanDto y devolver paginacion de elementos
        return new PageImpl<>(page.getContent().stream().map(e -> mapper.map(e, LoanDto.class)).collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
    }

    /**
     * Método para crear un {@link Loan}
     * @param dto datos de la entidad
     */
    @Operation(summary = "Save", description = "Method that saves a Loan")
    @RequestMapping(path = "", method = RequestMethod.PUT)
    public void save(@RequestBody LoanDto dto) throws ValidationException {
        this.loanService.save(dto);
    }

    /**
     * Método para eliminar un {@link Loan}
     * @param id PK de la entidad
     */
    @Operation(summary = "Delete", description = "Method that deletes a Loan")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws Exception {

        this.loanService.delete(id);
    }

}
