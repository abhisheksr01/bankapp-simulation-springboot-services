package com.service.balance.service;

import com.service.balance.model.BalanceUpdateDAO;
import com.service.balance.model.BalanceUpdateVO;
import com.service.balance.repository.BalanceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

import static com.service.balance.mapper.BalanceVOToBalanceDAOMapper.MAPPER;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service
public class BalanceService {

    private BalanceRepository balanceRepository;

    public BalanceService(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    public BalanceUpdateDAO updateBalance(BalanceUpdateVO balanceUpdateVO) {
        log.info("BalanceService : updateBalance : End..");
        log.debug("BalanceService : updateBalance : Balance Updating to : {}", balanceUpdateVO);
        BalanceUpdateDAO balanceUpdateDAO = MAPPER.mapToBalanceUpdateDAO(balanceUpdateVO);
        BalanceUpdateDAO updateDAO = balanceRepository.save(balanceUpdateDAO);
        log.debug("BalanceService : updateBalance : Balance Updated to : {}", updateDAO);
        log.info("BalanceService : updateBalance : End..");
        return updateDAO;
    }

    public BalanceUpdateVO getCurrentBalance(int accountNumber) {
        log.info("BalanceService : getCurrentBalance : Init..");

        log.debug("BalanceService : getCurrentBalance : Get balance for account number : {}", accountNumber);
        Optional<BalanceUpdateDAO> balanceUpdateDAOOptional = balanceRepository.findById(accountNumber);
        balanceUpdateDAOOptional.orElseThrow(() -> new HttpClientErrorException(NOT_FOUND, "Account Number not found"));
        BalanceUpdateDAO balanceUpdateDAO = balanceUpdateDAOOptional.get();

        log.debug("BalanceService : getCurrentBalance : For accountNumber : {} Details are : {}"
                , accountNumber, balanceUpdateDAO);
        log.info("BalanceService : getCurrentBalance : End..");
        return MAPPER.mapToBalanceUpdateVO(balanceUpdateDAO);
    }
}
