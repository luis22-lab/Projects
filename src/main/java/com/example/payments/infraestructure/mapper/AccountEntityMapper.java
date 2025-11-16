package com.example.payments.infraestructure.mapper;
import com.example.payments.domain.entity.AccountBean;
import com.example.payments.infraestructure.entity.AccountEntity;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)

public interface AccountEntityMapper {

    AccountEntityMapper INSTANCE = Mappers.getMapper(AccountEntityMapper.class);

    AccountEntity toEntity(AccountBean bean);

    AccountBean toBean(AccountEntity entity);
}