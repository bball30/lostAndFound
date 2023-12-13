package ru.itmo.lostandfound.util.converters;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itmo.lostandfound.messages.dtos.ItemStatusChangeDto;
import ru.itmo.lostandfound.model.ItemStatusChange;

/**
 * Конвертер, ставящий в соответствие сущности ItemStatusChange сущность ItemStatusChangeDto
 */
@Component
@RequiredArgsConstructor
public class ItemStatusChangeToItemStatusChangeDtoConverter implements Converter<ItemStatusChange, ItemStatusChangeDto> {

    private final ItemToItemDtoConverter itemToItemDtoConverter;
    private final UserToUserDtoConverter userToUserDtoConverter;

    @Override
    @NonNull
    public ItemStatusChangeDto convert(ItemStatusChange itemStatusChange) {
        ItemStatusChangeDto itemStatusChangeDto = new ItemStatusChangeDto();
        itemStatusChangeDto.setId(itemStatusChange.getId());
        itemStatusChangeDto.setItem(itemToItemDtoConverter.convert(itemStatusChange.getItem()));
        itemStatusChangeDto.setOldStatus(itemStatusChange.getOldStatus().getTag());
        itemStatusChangeDto.setNewStatus(itemStatusChange.getNewStatus().getTag());
        itemStatusChangeDto.setChangeDate(itemStatusChange.getChangeDate());
        itemStatusChangeDto.setUser(userToUserDtoConverter.convert(itemStatusChange.getUser()));

        return itemStatusChangeDto;
    }
}
