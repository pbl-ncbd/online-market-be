package com.example.onlinemarketbe.services.impl;

import com.example.onlinemarketbe.model.*;
import com.example.onlinemarketbe.payload.request.CreateItemRequest;
import com.example.onlinemarketbe.payload.request.UpdateItemRequest;
import com.example.onlinemarketbe.payload.response.ItemResponse;
import com.example.onlinemarketbe.payload.response.UrlImgResponse;
import com.example.onlinemarketbe.repositories.*;
import com.example.onlinemarketbe.services.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ItemServiceImpl implements ItemService {

    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);
    private final ItemRepository itemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final TypeRepository typeRepository;
    private final UrlImgRepository urlImgRepository;

    ItemServiceImpl(ItemRepository itemRepository,
                    ProductRepository productRepository,
                    TypeRepository typeRepository,
                    UserRepository userRepository, UrlImgRepository urlImgRepository){
        this.itemRepository = itemRepository;
        this.productRepository = productRepository;
        this.typeRepository = typeRepository;
        this.userRepository = userRepository;
        this.urlImgRepository = urlImgRepository;
    }


    @Override
    public ItemResponse findItemById(int id, String username) {
        try {
            User user = userRepository.findUserByUsername(username);
            Item item = itemRepository.findItemByIdAndUserIdAndOrderId(id, user.getId(), null);
            if (item == null) {
                return null;
            }
            logger.info("find by user: " + username);

            return new ItemResponse(item, buildImgList(item.getProduct().getId()));
        } catch (Exception e) {
            logger.info("Find error: " + e);
            return null;
        }

    }

    @Override
    public List<ItemResponse> findAllItemByUser(String username) {

        try {
            logger.info("find by user: " + username);
            List<ItemResponse> itemResponses = new ArrayList<>();
            User user = userRepository.findUserByUsername(username);
            List<Item> items = itemRepository.findAllByUserIdAndOrderId(user.getId(), null);
            for(Item item : items) {
                itemResponses.add(new ItemResponse(item, buildImgList(item.getProduct().getId())));
            }
            return itemResponses;
        } catch (Exception e) {
            logger.info("Find error: " + e);
            return null;
        }

    }

    @Override
    public List<ItemResponse> findAllItemByOrderId(int id, String username) {
        try {
            List<ItemResponse> itemResponses = new ArrayList<>();
            User user = userRepository.findUserByUsername(username);
            List<Item> items = itemRepository.findAllByUserIdAndOrderId(user.getId(), id);
            for(Item item : items) {
                itemResponses.add(new ItemResponse(item, buildImgList(item.getProduct().getId())));
            }
            return itemResponses;
        } catch (Exception e) {
            logger.info("Find error: " + e);
            return null;
        }
    }

    @Override
    public boolean deleteItemById(int id, String username) {
        try {
            User user = userRepository.findUserByUsername(username);
            Item item = itemRepository.findItemByIdAndUserIdAndOrderId(id, user.getId(), null);
            if (item == null) {
                return false;
            }
            itemRepository.deleteById(id);
            return true;

        } catch (Exception e) {
            logger.warn("Exception :" + e);
            return false;
        }

    }

    @Override
    public ItemResponse updateItem(UpdateItemRequest updateItemRequest, String username) {
        try {
            logger.info("update item " + updateItemRequest.getItemId() + " by user: " + username);
            Item item = itemRepository.findItemByIdAndOrderId(updateItemRequest.getItemId(), null);
            if (Objects.equals(item.getUser().getUsername(), username)) {

                Type type = typeRepository.findById(updateItemRequest.getTypeId()).get();

                item.setQuantity(updateItemRequest.getQuantity());
                item.setType(type);
                item.setTotalPrice(item.getProduct().getPrice() * item.getQuantity());


                return new ItemResponse(itemRepository.save(item), buildImgList(item.getProduct().getId()));
            }
            return null;


        } catch (Exception e) {
            logger.info("update error: " + e);
            return null;
        }

    }

    @Override
    public ItemResponse addItemToCart(CreateItemRequest createItemRequest, String username) {
        try {
            Integer typeId = null;
            Type type = null;
            logger.info("add item " + createItemRequest.getProductId() + " by user: " + username);
            Product product = productRepository.findById(createItemRequest.getProductId()).get();
            User user = userRepository.findUserByUsername(username);
            List<Type> typeList = typeRepository.findAllByProductId(createItemRequest.getProductId());
            if ((user.getId() == product.getUser().getId())
                || (createItemRequest.getQuantity() > product.getQuantity())
                || (typeList.size() > 0 && createItemRequest.getTypeId() == null)
                || (typeList.size() == 0 && createItemRequest.getTypeId() != null)) {
                return null;
            }

            if (typeList.size() > 0 && createItemRequest.getTypeId() != null) {
                type = typeRepository.findById(createItemRequest.getTypeId()).get();
                if (!typeList.contains(type)) return null;
                typeId = createItemRequest.getTypeId();
            }


            Item itemExist = itemRepository
                    .findItemByProductIdAndTypeIdAndUserIdAndOrderId(product.getId(),
                            typeId,
                            user.getId(),
                            null);
            if (itemExist == null) {
                Item item = new Item();
                item.setProduct(product);
                item.setQuantity(createItemRequest.getQuantity());
                item.setType(type);
                item.setTotalPrice(createItemRequest.getQuantity() * product.getPrice());
                item.setUser(user);
                return new ItemResponse(itemRepository.save(item), buildImgList(item.getProduct().getId()));
            } else {
                itemExist.setQuantity(itemExist.getQuantity() + createItemRequest.getQuantity());
                itemExist.setTotalPrice(itemExist.getQuantity() * product.getPrice());
                return new ItemResponse(itemRepository.save(itemExist), buildImgList(itemExist.getProduct().getId()));
            }
        } catch (Exception e) {
            logger.info("add item error: " + e);
            return null;
        }
    }


    private List<UrlImgResponse> buildImgList(Integer productId){
        List<UrlImg> urlImg= urlImgRepository.findAllByProductId(productId);
        List<UrlImgResponse> listImgResponse= new ArrayList<>();

        if(urlImg != null) {
            for(UrlImg urlImg1:urlImg)
            {
                listImgResponse.add(new UrlImgResponse(urlImg1));
            }
        }
        return  listImgResponse;
    }
}
