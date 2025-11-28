package org.simplecash.projet_fall_ndeyefatou.mapper;

import org.mapstruct.*;
import org.simplecash.projet_fall_ndeyefatou.dto.ConseillerCreateDto;
import org.simplecash.projet_fall_ndeyefatou.dto.ConseillerDto;
import org.simplecash.projet_fall_ndeyefatou.entity.ConseillerEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ConseillerMapper {
    ConseillerDto toDto(ConseillerEntity conseiller);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "clients", ignore = true)
    ConseillerEntity toEntity(ConseillerCreateDto dto);
}
