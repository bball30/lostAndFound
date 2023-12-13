package ru.itmo.lostandfound.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.itmo.lostandfound.messages.dtos.ItemDto;
import ru.itmo.lostandfound.messages.item.CreateItemRequest;
import ru.itmo.lostandfound.messages.item.GetBookedItemsResponse;
import ru.itmo.lostandfound.messages.item.GetItemsResponse;
import ru.itmo.lostandfound.messages.item.LossTime;
import ru.itmo.lostandfound.model.ItemStatus;
import ru.itmo.lostandfound.security.CustomUserDetails;
import ru.itmo.lostandfound.services.ItemService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
@Validated
public class ItemController {

    private final ItemService itemService;

    @PostMapping("")
    public ResponseEntity<ItemDto> createItem(
            @RequestBody @Valid CreateItemRequest createItemRequest,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.createItem(createItemRequest, userDetails));
    }

    @GetMapping("")
    public ResponseEntity<GetItemsResponse> getItems(
            @RequestParam(name = "loss_time", defaultValue = "WEEK") LossTime lossTime,
            @RequestParam(name = "page_number", defaultValue = "0") @PositiveOrZero Integer pageNumber,
            @RequestParam(name = "page_size", defaultValue = "5") @Positive Integer pageSize,
            @RequestParam(name = "campus_name", required = false) String campusName
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.getItems(lossTime, campusName, pageNumber, pageSize));
    }

    @GetMapping("/{item_id}")
    public ResponseEntity<ItemDto> getItem(
            @PathVariable(name = "item_id") String itemId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.getItem(itemId));
    }

    @PatchMapping("/{item_id}/status")
    public ResponseEntity<ItemDto> changeItemStatus(
            @PathVariable(name = "item_id") String itemId,
            @RequestParam(name = "new_status") ItemStatus newStatus,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.changeItemStatus(itemId, newStatus, userDetails));
    }

    @PostMapping("/{item_id}:bookItem")
    public ResponseEntity<ItemDto> bookItem(
            @PathVariable(name = "item_id") String itemId,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.bookItem(itemId, userDetails));
    }

    @GetMapping("/booking")
    public ResponseEntity<GetBookedItemsResponse> getBookedItems(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.getBookedItems(userDetails));
    }
}
