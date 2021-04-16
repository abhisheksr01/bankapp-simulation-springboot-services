package com.service.balance.mapper;

import com.service.balance.model.BalanceUpdateDAO;
import com.service.balance.model.BalanceUpdateVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BalanceVOToBalanceDAOMapper {
    BalanceVOToBalanceDAOMapper MAPPER = Mappers.getMapper(BalanceVOToBalanceDAOMapper.class);

    @Mapping(target = "createdAt", ignore = true)
    BalanceUpdateDAO mapToBalanceUpdateDAO(BalanceUpdateVO balanceUpdateVO);

    BalanceUpdateVO mapToBalanceUpdateVO(BalanceUpdateDAO balanceUpdateDAO);
}
