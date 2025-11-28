package org.simplecash.projet_fall_ndeyefatou.mapper;

import org.mapstruct.*;
import org.simplecash.projet_fall_ndeyefatou.dto.ClientCreateDto;
import org.simplecash.projet_fall_ndeyefatou.dto.ClientDto;
import org.simplecash.projet_fall_ndeyefatou.dto.ClientUpdateDto;
import org.simplecash.projet_fall_ndeyefatou.entity.ClientEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.ERROR)
public interface ClientMapper {

    ClientDto toClientDto(ClientEntity clientEntity);

    @Mapping(target ="id", ignore = true)
    ClientEntity toClientEntity(ClientCreateDto clientDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget ClientEntity entity, ClientUpdateDto dto);
}
