package ru.itmo.lostandfound.services;

import ru.itmo.lostandfound.messages.dtos.ItemDto;
import ru.itmo.lostandfound.messages.item.CreateItemRequest;
import ru.itmo.lostandfound.messages.item.GetBookedItemsResponse;
import ru.itmo.lostandfound.messages.item.GetItemsResponse;
import ru.itmo.lostandfound.messages.item.LossTime;
import ru.itmo.lostandfound.model.ItemStatus;
import ru.itmo.lostandfound.security.CustomUserDetails;

public interface ItemService {
    ItemDto createItem(CreateItemRequest createItemRequest, CustomUserDetails userDetails);

    GetItemsResponse getItems(LossTime lossTime, String officeName, Integer pageNumber, Integer pageSize);

    ItemDto changeItemStatus(String itemId, ItemStatus newStatus, CustomUserDetails userDetails);

    ItemDto getItem(String itemId);

    ItemDto bookItem(String itemId, CustomUserDetails userDetails);

    GetBookedItemsResponse getBookedItems(CustomUserDetails customUserDetails);
}
