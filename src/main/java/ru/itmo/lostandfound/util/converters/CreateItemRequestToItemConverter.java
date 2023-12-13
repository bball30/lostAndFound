package ru.itmo.lostandfound.util.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import ru.itmo.lostandfound.messages.item.CreateItemRequest;
import ru.itmo.lostandfound.model.Item;
import ru.itmo.lostandfound.model.ItemStatus;
import ru.itmo.lostandfound.model.Campus;
import ru.itmo.lostandfound.util.BarcodeIdGenerator;
import ru.itmo.lostandfound.util.resolvers.CategoryResolveService;
import ru.itmo.lostandfound.util.resolvers.LossPlaceResolveService;
import ru.itmo.lostandfound.util.resolvers.CampusResolveService;
import ru.itmo.lostandfound.util.resolvers.PhotoResolveService;

/**
 * Конвертер, ставящий в соответствие сущности CreateItemRequest сущность Item
 */
@Component
@RequiredArgsConstructor
public class CreateItemRequestToItemConverter implements Converter<CreateItemRequest, Item> {

    private final CategoryResolveService categoryResolveService;
    private final CampusResolveService campusResolveService;
    private final LossPlaceResolveService lossPlaceResolveService;
    private final BarcodeIdGenerator barcodeIdGenerator;
    private final PhotoResolveService photoResolveService;

    @Override
    @NonNull
    public Item convert(CreateItemRequest request) {
        Item item = new Item();
        item.setItemStatus(ItemStatus.ACTIVE);
        item.setCategory(categoryResolveService.resolve(request.getCategoryName()));
        item.setLossPlace(lossPlaceResolveService.resolve(request.getLossPlaceName()));
        item.setPhoto(photoResolveService.resolve(request.getPhotoId()));
        item.setUploadDate(request.getUploadDate());
        Campus campus = campusResolveService.resolve(request.getCampusName());
        item.setCampus(campus);
        item.setBarcodeId(barcodeIdGenerator.generate(campus));

        return item;
    }
}
