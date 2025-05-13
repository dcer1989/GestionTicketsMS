package com.hiberus.mapper;

import org.mapstruct.Mapper;

import com.hiberus.dto.ShowRequest;
import com.hiberus.dto.ShowResponse;
import com.hiberus.model.Show;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ShowMapper {

    ShowResponse toDto(Show show);

    Show toEntity(ShowRequest showRequest);
}