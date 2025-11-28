package org.simplecash.projet_fall_ndeyefatou.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.simplecash.projet_fall_ndeyefatou.dto.CompteCourantDto;
import org.simplecash.projet_fall_ndeyefatou.entity.CompteCourantEntity;

@Mapper(componentModel = "spring")
public interface CompteCourantMapper {

    @Mapping(target = "clientId", source = "client.id")
    CompteCourantDto toDto(CompteCourantEntity entity);
}
