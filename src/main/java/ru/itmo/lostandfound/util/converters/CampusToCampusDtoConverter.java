package ru.itmo.lostandfound.util.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import ru.itmo.lostandfound.messages.dtos.CampusDto;
import ru.itmo.lostandfound.model.Campus;

/**
 * Конвертер, ставящий в соответствие сущности Campus сущность CampusDto
 */
@Component
@RequiredArgsConstructor
public class CampusToCampusDtoConverter implements Converter<Campus, CampusDto> {

    @Override
    @NonNull
    public CampusDto convert(Campus campus) {
        CampusDto campusDto = new CampusDto();
        campusDto.setName(campus.getName());
        campusDto.setAddress(campus.getAddress());
        campusDto.setWorkingHours(campus.getWorkingHours());
        campusDto.setWorkingDays(campus.getWorkingDays());

        return campusDto;
    }
}
