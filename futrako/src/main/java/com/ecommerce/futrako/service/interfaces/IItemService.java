package com.ecommerce.futrako.service.interfaces;

import com.ecommerce.futrako.model.Item;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface IItemService {
    ResponseEntity<?> getAllItems();

    ResponseEntity<?> getItem(Long id);

    ResponseEntity<?> getItemsByOrder(Long id);

    ResponseEntity<?> getItemsByProduct(Long id);

    ResponseEntity<?> postItem(Item item);

    ResponseEntity<?> patchItem(Long id, Item item);

    ResponseEntity<?> deleteItem(Long id);

}