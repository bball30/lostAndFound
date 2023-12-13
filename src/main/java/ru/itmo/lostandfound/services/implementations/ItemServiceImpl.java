package ru.itmo.lostandfound.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.lostandfound.exceptions.*;
import ru.itmo.lostandfound.messages.dtos.ItemDto;
import ru.itmo.lostandfound.messages.dtos.ItemStatusChangeDto;
import ru.itmo.lostandfound.messages.item.CreateItemRequest;
import ru.itmo.lostandfound.messages.item.GetBookedItemsResponse;
import ru.itmo.lostandfound.messages.item.GetItemsResponse;
import ru.itmo.lostandfound.messages.item.LossTime;
import ru.itmo.lostandfound.model.*;
import ru.itmo.lostandfound.repositories.ItemRepository;
import ru.itmo.lostandfound.repositories.ItemStatusChangeRepository;
import ru.itmo.lostandfound.security.CustomUserDetails;
import ru.itmo.lostandfound.util.converters.CreateItemRequestToItemConverter;
import ru.itmo.lostandfound.util.converters.ItemStatusChangeToItemStatusChangeDtoConverter;
import ru.itmo.lostandfound.util.converters.ItemToItemDtoConverter;
import ru.itmo.lostandfound.util.resolvers.CampusResolveService;
import ru.itmo.lostandfound.util.resolvers.PhotoResolveService;
import ru.itmo.lostandfound.util.resolvers.UserResolveService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ru.itmo.lostandfound.services.ItemService {

    private final ItemRepository itemRepository;
    private final PhotoResolveService photoResolveService;
    private final UserResolveService userResolveService;
    private final CampusResolveService campusResolveService;
    private final CreateItemRequestToItemConverter createItemRequestToItemConverter;
    private final ItemToItemDtoConverter itemToItemDtoConverter;
    private final ItemStatusChangeToItemStatusChangeDtoConverter itemStatusChangeToItemStatusChangeDtoConverter;
    private final ItemStatusChangeRepository itemStatusChangeRepository;

    @Override
    @Transactional
    public ItemDto createItem(CreateItemRequest createItemRequest, CustomUserDetails userDetails) {
        String photoId = createItemRequest.getPhotoId();
        Photo photo = photoResolveService.resolve(photoId);
        if (!userDetails.getId().equals(photo.getUser().getId())) {
            throw new PhotoBelongsAnotherUserException(photoId + " belongs " + photo.getUser().getLogin());
        }
        if (photo.getItem() != null) {
            throw new PhotoAlreadyUsedException(photoId);
        }
        Item item = itemRepository.save(createItemRequestToItemConverter.convert(createItemRequest));
        photo.setItem(item);
        return itemToItemDtoConverter.convert(item);
    }

    @Override
    public GetItemsResponse getItems(LossTime lossTime, String campusName, Integer pageNumber, Integer pageSize) {
        Page<Item> items;
        if (campusName == null) {
            items = itemRepository.findAllByItemStatusAndUploadDateBetween(ItemStatus.ACTIVE, lossTime.getStart().get(),
                    lossTime.getEnd().get(), PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "uploadDate"));
        } else {
            Campus campus = campusResolveService.resolve(campusName);
            items = itemRepository.findAllByCampusAndItemStatusAndUploadDateBetween(campus, ItemStatus.ACTIVE, lossTime.getStart().get(),
                    lossTime.getEnd().get(), PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "uploadDate"));
        }
        GetItemsResponse getItemsResponse = new GetItemsResponse();
        getItemsResponse.setItems(items.map(itemToItemDtoConverter::convert).toList());
        return getItemsResponse;
    }

    @Override
    public ItemDto getItem(String itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new NoSuchItemException(itemId));
        return itemToItemDtoConverter.convert(item);
    }

    @Override
    @Transactional
    public ItemDto changeItemStatus(String itemId, ItemStatus newStatus, CustomUserDetails userDetails) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new NoSuchItemException(itemId));

        if (newStatus.equals(item.getItemStatus())) {
            throw new ItemStatusConflictException(newStatus.getTag());
        }
        User user = userResolveService.resolve(userDetails.getUsername());
        ItemStatusChange itemStatusChange = new ItemStatusChange(
                null,
                item,
                item.getItemStatus(),
                newStatus,
                Timestamp.valueOf(LocalDateTime.now()),
                user
        );
        itemStatusChangeRepository.save(itemStatusChange);

        item.setItemStatus(newStatus);
        return itemToItemDtoConverter.convert(itemRepository.save(item));
    }

    @Override
    @Transactional
    public ItemDto bookItem(String itemId, CustomUserDetails userDetails) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new NoSuchItemException(itemId));

        if (!item.getItemStatus().equals(ItemStatus.ACTIVE)) {
            throw new ItemStatusConflictException(item.getItemStatus().toString());
        }
        User user = userResolveService.resolve(userDetails.getUsername());
        ItemStatusChange itemStatusChange = new ItemStatusChange(
                null,
                item,
                item.getItemStatus(),
                ItemStatus.LOCKED,
                Timestamp.valueOf(LocalDateTime.now()),
                user
        );
        itemStatusChangeRepository.save(itemStatusChange);

        item.setItemStatus(ItemStatus.LOCKED);

        return itemToItemDtoConverter.convert(itemRepository.save(item));
    }

    @Override
    public GetBookedItemsResponse getBookedItems(CustomUserDetails customUserDetails) {
        User user = userResolveService.resolve(customUserDetails.getUsername());
        List<ItemStatusChange> itemStatusChanges = itemStatusChangeRepository.findAllByItemCampusOrderByChangeDateDesc(user.getCampus());
        List<ItemStatusChangeDto> itemStatusChangeDtos = itemStatusChanges.stream().map(itemStatusChangeToItemStatusChangeDtoConverter::convert).toList();
        Set<String> itemIds = new HashSet<>();
        List<ItemStatusChangeDto> resultList = new ArrayList<>();

        for (ItemStatusChangeDto itemStatusChangeDto : itemStatusChangeDtos) {
            if (itemIds.add(itemStatusChangeDto.getItem().getId()) && itemStatusChangeDto.getNewStatus().equals(ItemStatus.LOCKED.getTag())) {
                resultList.add(itemStatusChangeDto);
            }
        }
        GetBookedItemsResponse getBookedItemsResponse = new GetBookedItemsResponse();
        getBookedItemsResponse.setItemStatusChanges(resultList);
        return getBookedItemsResponse;
    }
}
