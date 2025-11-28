package org.simplecash.projet_fall_ndeyefatou.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.simplecash.projet_fall_ndeyefatou.dto.OperationDto;
import org.simplecash.projet_fall_ndeyefatou.entity.OperationEntity;

@Mapper(componentModel = "spring")
public interface OperationMapper {

    @Mapping(target = "compteSourceId", source = "compteSource.id")
    @Mapping(target = "compteDestinationId", source = "compteDestination.id")
    @Mapping(target = "epargneSourceId", source = "epargneSource.id")
    @Mapping(target = "epargneDestinationId", source = "epargneDestination.id")
    OperationDto toDto(OperationEntity entity);
}
