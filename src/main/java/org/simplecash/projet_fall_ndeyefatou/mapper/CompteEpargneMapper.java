package org.simplecash.projet_fall_ndeyefatou.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.simplecash.projet_fall_ndeyefatou.dto.CompteEpargneDto;
import org.simplecash.projet_fall_ndeyefatou.entity.CompteEpargneEntity;

@Mapper(componentModel = "spring")
public interface CompteEpargneMapper {

    @Mapping(target = "clientId", source = "client.id")
    CompteEpargneDto toDto(CompteEpargneEntity entity);
}
