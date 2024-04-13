package com.ecommerce.futrako.service;

import com.ecommerce.futrako.dto.ItemDto;
import com.ecommerce.futrako.exception.ResourceNotFoundException;
import com.ecommerce.futrako.model.Item;
import com.ecommerce.futrako.repository.IItemRepository;
import com.ecommerce.futrako.service.interfaces.IItemService;
import com.ecommerce.futrako.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService implements IItemService {

    @Autowired
    private IItemRepository iItemRepository;

    @Autowired
    private Mapper mapper;

    public ResponseEntity<?> getItem(Long id) {
        Item item = iItemRepository.findById(id).orElseThrow();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.getMapper().map(item, ItemDto.class));
    }

    @Override
    public ResponseEntity<?> getItemsByProduct(Long id) {
//        List<Item> items = iItemRepository.findByProduct_id(id);
//        List<ItemDto> itemsDto = items.stream().map(item -> mapper.getMapper().map(item, ItemDto.class)).collect(Collectors.toList());
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(itemsDto);
        return null;
    }

    @Override
    public ResponseEntity<?> getItemsByOrder(Long id) {
        List<Item> items = iItemRepository.findByOrder_id(id);
//        List<ItemDto> itemsDto = items.stream().map(item -> mapper.getMapper().map(item, ItemDto.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(items);

    }

    @Override
    public ResponseEntity<?> getAllItems() {
        List<Item> items = iItemRepository.findAll();
        List<ItemDto> itemsDto = items.stream().map(item -> mapper.getMapper().map(item, ItemDto.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(itemsDto);
    }

    @Override
    public ResponseEntity<?> postItem(Item item) {
//
//        if (item.getProduct().getStock()> item.getAmount())
//        {item.getProduct().setStock(item.getProduct().getStock() - item.getAmount());
//            return ResponseEntity.status(HttpStatus.ACCEPTED).body(iItemRepository.save(item));}
//        else
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Insufficient stock");
        return null;
    }

    @Override
    public ResponseEntity<?> patchItem(Long id, Item item) {
//        Item itemToUpdate = iItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item not found"));
//        itemToUpdate.setAmount (item.getAmount());
//        itemToUpdate.setOrder(item.getOrder());
//        itemToUpdate.setProduct(item.getProduct());
//        itemToUpdate.getProduct().setStock(itemToUpdate.getProduct().getStock() - itemToUpdate.getAmount());
//        if (itemToUpdate.getProduct().getStock()> itemToUpdate.getAmount())
//        {itemToUpdate.getProduct().setStock(itemToUpdate.getProduct().getStock() - itemToUpdate.getAmount());
//            return ResponseEntity.status(HttpStatus.ACCEPTED).body(iItemRepository.save(itemToUpdate));}
//        else
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("insufficient stock");
        return null;
    }

    @Override
    public ResponseEntity<?> deleteItem(Long id) {
        Item itemToDelete = iItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item not found"));
        iItemRepository.delete(itemToDelete);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Item deleted");
    }
}
