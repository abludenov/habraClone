package com.javamentor.backend.mappers;

import com.javamentor.backend.model.Hub;
import com.javamentor.backend.model.dto.HubDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DtoToHubMapper {

    Hub toHub(HubDto hubDto);
}
