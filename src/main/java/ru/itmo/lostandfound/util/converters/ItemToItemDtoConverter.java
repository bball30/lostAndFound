package ru.itmo.lostandfound.util.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import ru.itmo.lostandfound.messages.dtos.ItemDto;
import ru.itmo.lostandfound.model.Item;

/**
 * Конвертер, ставящий в соответствие сущности Item сущность ItemDto
 */
@Component
@RequiredArgsConstructor
public class ItemToItemDtoConverter implements Converter<Item, ItemDto> {

    private final CampusToCampusDtoConverter campusToCampusDtoConverter;

    @Override
    @NonNull
    public ItemDto convert(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setItemStatus(item.getItemStatus().getTag());
        itemDto.setBarcodeId(item.getBarcodeId());
        itemDto.setCategoryName(item.getCategory().getName());
        itemDto.setCampus(campusToCampusDtoConverter.convert(item.getCampus()));
        itemDto.setLossPlaceName(item.getLossPlace().getName());
        itemDto.setPhotoId(item.getPhoto().getId());
        itemDto.setUploadDate(item.getUploadDate());

        return itemDto;
    }
}
