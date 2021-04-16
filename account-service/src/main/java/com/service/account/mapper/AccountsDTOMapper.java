package com.service.account.mapper;

import com.service.account.model.AccountCustomerVO;
import com.service.account.model.AccountDAO;
import com.service.account.model.AccountVO;
import com.service.account.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountsDTOMapper {
    AccountsDTOMapper MAPPER = Mappers.getMapper(AccountsDTOMapper.class);

    AccountVO mapToAccountVO(AccountDAO accountDAO, Customer customerData);

    AccountCustomerVO mapToAccountCustomerVO(AccountDAO storedAccountDAO);
}
