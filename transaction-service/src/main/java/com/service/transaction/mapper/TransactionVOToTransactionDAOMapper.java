package com.service.transaction.mapper;

import com.service.transaction.model.TransactionDAO;
import com.service.transaction.model.TransactionVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionVOToTransactionDAOMapper {
    TransactionVOToTransactionDAOMapper MAPPER = Mappers.getMapper(TransactionVOToTransactionDAOMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    TransactionDAO map(TransactionVO creditTransaction);
}
