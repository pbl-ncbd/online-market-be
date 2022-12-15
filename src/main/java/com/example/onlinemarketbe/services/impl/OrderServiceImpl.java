package com.example.onlinemarketbe.services.impl;

import com.example.onlinemarketbe.model.*;
import com.example.onlinemarketbe.payload.request.CreateOrderRequest;
import com.example.onlinemarketbe.payload.request.UpdateStatusOrder;
import com.example.onlinemarketbe.payload.response.InfoProduct;
import com.example.onlinemarketbe.payload.response.ViewOrder;
import com.example.onlinemarketbe.payload.response.ViewOrder1;
import com.example.onlinemarketbe.repositories.*;
import com.example.onlinemarketbe.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    AddressOrderRepository addressOrderRepository;
    @Autowired
    SalesRepository salesRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UrlImgRepository urlImgRepository;

    @Override
    public ResponseEntity<?> createOrder(String username, CreateOrderRequest createOrderRequest)
    {
        User user = userRepository.findUserByUsername(username);
        if(user== null)
        {
            return ResponseEntity.ok("not logged in");
        }
        else
        {
            List<Integer> list= createOrderRequest.getListIdItem();
            Set<Integer> listIdSales= new HashSet<>();
            for(int id: list)
            {
                Item item= itemRepository.findItemById(id);
                listIdSales.add(item.getProduct().getUser().getId());
            }

            if(list!=null)
            {
                for(int idSales: listIdSales) {
                Order order = new Order();
                Date date = new Date();
                order.setDateOrder(date);
                order.setPayment(paymentRepository.findPaymentById(createOrderRequest.getIdPayment()));
                order.setBuyer(user);
                List<Double> price = new ArrayList<>();
                //Sum Price
                for(int idItem : list)
                {
                    Item item= itemRepository.findItemBySale(idSales,idItem);
                    if(item!=null) price.add(item.getTotalPrice());


                }

                double sum=0;
                for (double element : price) {
                    sum += element;
                }
                order.setTotalPrice(sum);
                order.setStatus(statusRepository.findStatusByName(EStatus.ordered));
                Order order1 = orderRepository.save(order);
                AddressOrder addressOrder = new AddressOrder();
                addressOrder.setAddress(createOrderRequest.getAddress());
                addressOrder.setOrder(order1);
                addressOrder.setProvince_city(createOrderRequest.getProvince());
                addressOrder.setUser(user);
                addressOrderRepository.save(addressOrder);


                for (int idItem : list) {
                    Item item = itemRepository.findItemBySale(idSales, idItem);
                    if (item != null) {
                        item.setOrder(order1);
                        itemRepository.save(item);
                        Product product = item.getProduct();
                                product.setQuantity(product.getQuantity()-item.getQuantity());
                                productRepository.save(product);

                    }

                }
                    Sales sales = new Sales();
                    sales.setOrder(order1);
                    sales.setUser(userRepository.findUserById(idSales));
                    salesRepository.save(sales);
            }
                return  ResponseEntity.ok("create success");
            }

         return ResponseEntity.ok("List Item empty!");


        }


    }

    @Override
    public ResponseEntity<?> getInfoOrder(String username, CreateOrderRequest createOrderRequest) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            return ResponseEntity.ok("not logged in");
        } else {
            List<Integer> list= createOrderRequest.getListIdItem();
            List<ViewOrder> list1 = new ArrayList<>();
            Set<Integer> listIdSales= new HashSet<>();
            for(int id: list)
            {
                Item item= itemRepository.findItemById(id);
                listIdSales.add(item.getProduct().getUser().getId());
            }
            if (list != null) {
                for (int idSales : listIdSales) {
                    ViewOrder viewOrder = new ViewOrder();
                    Date date = new Date();
                    viewOrder.setDateOrder(date);
                    viewOrder.setNamePersonOrder(username);
                    viewOrder.setPayment(paymentRepository.findPaymentById(createOrderRequest.getIdPayment()).getName());
                    List<Double> price = new ArrayList<>();
                    //Sum Price
                    for(int idItem : list)
                    {
                        Item item= itemRepository.findItemBySale(idSales,idItem);
                        if(item!=null) price.add(item.getTotalPrice());

                    }

                    double sum=0;
                    for (double element : price) {
                        sum += element;
                    }
                    viewOrder.setTotalPrice(sum);
                    viewOrder.setAddressOrder(createOrderRequest.getAddress() + "," + createOrderRequest.getProvince());
                    List<InfoProduct> infoProducts = new ArrayList<>();
                    for (int idItem : list) {
                        Item item= itemRepository.findItemBySale(idSales,idItem);

                        if (item != null) {
                            InfoProduct infoProduct = new InfoProduct();
                            if(item.getType()!=null) {
                                infoProduct.setTypeOrder("Color: " + item.getType().getColor() + "," + "Size: " + item.getType().getSize());
                            }
                            infoProduct.setNameShop(item.getProduct().getUser().getUsername());
                            infoProduct.setNumberProduct(item.getQuantity());
                            infoProduct.setNameProduct(item.getProduct().getName());
                            UrlImg urlImg= urlImgRepository.findUrlImgByProductId(item.getProduct().getId());
                            if(urlImg!=null) {
                                infoProduct.setUrl(urlImg.getUrl());
                            }
                            infoProduct.setPrice(item.getProduct().getPrice());
                            infoProducts.add(infoProduct);


                        }
                    }
                    viewOrder.setProducts(infoProducts);
                    list1.add(viewOrder);

                }
                return ResponseEntity.ok(list1);
            }
            return ResponseEntity.ok("List Item empty!");


        }
    }
         @Override
          public ResponseEntity<?> getInfoOrderByOrdered(String username)
        {
            User user = userRepository.findUserByUsername(username);
            if(user== null)
            {
                return ResponseEntity.ok("not logged in");
            }
            else
            {  List<ViewOrder> listView= new ArrayList<>();
                List<Order> list= orderRepository.findAllByBuyerIdAndStatus(user.getUsername(),EStatus.ordered);
                if(list!=null)
                {  for(Order i: list) {
                    ViewOrder viewOrder = new ViewOrder();
                    viewOrder.setDateOrder(i.getDateOrder());
                    viewOrder.setNamePersonOrder(username);
                    viewOrder.setPayment(i.getPayment().getName());
                    viewOrder.setTotalPrice(i.getTotalPrice());
                    AddressOrder addressOrder= addressOrderRepository.findAddressOrderByOrderIdAndUserId(i.getId(),user.getId());
                    if(addressOrder!=null) {
                        viewOrder.setAddressOrder(addressOrder.getAddress() + "," + addressOrder.getProvince_city());
                    }
                    List<InfoProduct> infoProducts= new ArrayList<>();
                    List<Item> list1= itemRepository.findAllByUserIdAndOrderId(user.getId(),i.getId());
                    for (Item item : list1) {
                        if (item != null) {
                            InfoProduct infoProduct= new InfoProduct();
                            if(item.getType()!=null) {
                                infoProduct.setTypeOrder("Color: " + item.getType().getColor() + "," + "Size: " + item.getType().getSize());
                            } infoProduct.setNameShop(item.getProduct().getUser().getUsername());
                            infoProduct.setNumberProduct(item.getQuantity());
                            infoProduct.setNameProduct(item.getProduct().getName());
                            UrlImg urlImg= urlImgRepository.findUrlImgByProductId(item.getProduct().getId());
                            if(urlImg!=null) {
                                infoProduct.setUrl(urlImg.getUrl());
                            }
                            infoProduct.setPrice(item.getProduct().getPrice());
                            infoProducts.add(infoProduct);

                        }
                    }
                    viewOrder.setProducts(infoProducts);
                    listView.add(viewOrder);
                }
                    return  ResponseEntity.ok(listView);
                }

                return ResponseEntity.ok("List Order empty!");


            }



        }
    @Override
    public ResponseEntity<?> getInfoOrderByTransport(String username)
    {
        User user = userRepository.findUserByUsername(username);
        if(user== null)
        {
            return ResponseEntity.ok("not logged in");
        }
        else
        {  List<ViewOrder> listView= new ArrayList<>();
            List<Order> list= orderRepository.findAllByBuyerIdAndStatus(user.getUsername(),EStatus.transport);
            if(list!=null)
            {  for(Order i: list) {
                ViewOrder viewOrder = new ViewOrder();
                viewOrder.setDateOrder(i.getDateOrder());
                viewOrder.setNamePersonOrder(username);
                viewOrder.setPayment(i.getPayment().getName());
                viewOrder.setTotalPrice(i.getTotalPrice());
                AddressOrder addressOrder= addressOrderRepository.findAddressOrderByOrderIdAndUserId(i.getId(),user.getId());
                if(addressOrder!=null) {
                    viewOrder.setAddressOrder(addressOrder.getAddress() + "," + addressOrder.getProvince_city());
                }
                List<InfoProduct> infoProducts= new ArrayList<>();
                List<Item> list1= itemRepository.findAllByUserIdAndOrderId(user.getId(),i.getId());
                for (Item item : list1) {
                    if (item != null) {

                        InfoProduct infoProduct= new InfoProduct();
                        if(item.getType()!=null) {
                            infoProduct.setTypeOrder("Color: " + item.getType().getColor() + "," + "Size: " + item.getType().getSize());
                        }
                        infoProduct.setNameShop(item.getProduct().getUser().getUsername());
                        infoProduct.setNumberProduct(item.getQuantity());
                        infoProduct.setNameProduct(item.getProduct().getName());
                        UrlImg urlImg= urlImgRepository.findUrlImgByProductId(item.getProduct().getId());
                        if(urlImg!=null) {
                            infoProduct.setUrl(urlImg.getUrl());
                        }
                        infoProduct.setPrice(item.getProduct().getPrice());
                        infoProducts.add(infoProduct);

                    }
                }
                viewOrder.setProducts(infoProducts);
                listView.add(viewOrder);
            }
                return  ResponseEntity.ok(listView);
            }

            return ResponseEntity.ok("List Item empty!");


        }



    }
    @Override
    public ResponseEntity<?> getInfoOrderByReceived(String username)
    {
        User user = userRepository.findUserByUsername(username);
        if(user== null)
        {
            return ResponseEntity.ok("not logged in");
        }
        else
        {  List<ViewOrder> listView= new ArrayList<>();
            List<Order> list= orderRepository.findAllByBuyerIdAndStatus(user.getUsername(),EStatus.received);
            if(list!=null)
            {  for(Order i: list) {
                ViewOrder viewOrder = new ViewOrder();
                viewOrder.setDateOrder(i.getDateOrder());
                viewOrder.setNamePersonOrder(i.getBuyer().getUsername());
                viewOrder.setPayment(i.getPayment().getName());
                viewOrder.setTotalPrice(i.getTotalPrice());
                AddressOrder addressOrder= addressOrderRepository.findAddressOrderByOrderIdAndUserId(i.getId(),user.getId());
                if(addressOrder!=null) {
                    viewOrder.setAddressOrder(addressOrder.getAddress() + "," + addressOrder.getProvince_city());
                }
                List<InfoProduct> infoProducts= new ArrayList<>();
                List<Item> list1= itemRepository.findAllByUserIdAndOrderId(user.getId(),i.getId());
                for (Item item : list1) {
                    if (item != null) {

                        InfoProduct infoProduct= new InfoProduct();
                        if(item.getType()!=null) {
                            infoProduct.setTypeOrder("Color: " + item.getType().getColor() + "," + "Size: " + item.getType().getSize());
                        } infoProduct.setNameShop(item.getProduct().getUser().getUsername());
                        infoProduct.setNumberProduct(item.getQuantity());
                        infoProduct.setNameProduct(item.getProduct().getName());
                        UrlImg urlImg= urlImgRepository.findUrlImgByProductId(item.getProduct().getId());
                        if(urlImg!=null) {
                            infoProduct.setUrl(urlImg.getUrl());
                        }
                        infoProduct.setPrice(item.getProduct().getPrice());
                        infoProducts.add(infoProduct);

                    }
                }
                viewOrder.setProducts(infoProducts);
                listView.add(viewOrder);
            }
                return  ResponseEntity.ok(listView);
            }

            return ResponseEntity.ok("List Item empty!");


        }

    }
    @Override
    public ResponseEntity<?> getInfoOrderByOrderedSales(String username)
    {
        User user = userRepository.findUserByUsername(username);
        if(user== null)
        {
            return ResponseEntity.ok("not logged in");
        }
        else
        {  List<ViewOrder1> listView= new ArrayList<>();
            List<Order> list= salesRepository.findAllByUserIdAndStatus(user.getId(),EStatus.ordered);
            if(list!=null)
            {  for(Order i: list) {
                ViewOrder1 viewOrder = new ViewOrder1();
                viewOrder.setIdOrder(i.getId());
                viewOrder.setDateOrder(i.getDateOrder());
                viewOrder.setNamePersonOrder(i.getBuyer().getUsername());
                viewOrder.setPayment(i.getPayment().getName());
                viewOrder.setTotalPrice(i.getTotalPrice());
                AddressOrder addressOrder= addressOrderRepository.findAddressOrderByOrderId(i.getId());
                if(addressOrder!=null) {
                    viewOrder.setAddressOrder(addressOrder.getAddress() + "," + addressOrder.getProvince_city());
                }
                List<InfoProduct> infoProducts= new ArrayList<>();
                List<Item> list1= itemRepository.findAllByOrderId(i.getId());
                for (Item item : list1) {
                    if (item != null) {
                        InfoProduct infoProduct= new InfoProduct();
                        if(item.getType()!=null) {
                            infoProduct.setTypeOrder("Color: " + item.getType().getColor() + "," + "Size: " + item.getType().getSize());
                        } infoProduct.setNameShop(item.getProduct().getUser().getUsername());
                        infoProduct.setNumberProduct(item.getQuantity());
                        infoProduct.setNameProduct(item.getProduct().getName());
                        UrlImg urlImg= urlImgRepository.findUrlImgByProductId(item.getProduct().getId());
                        if(urlImg!=null) {
                            infoProduct.setUrl(urlImg.getUrl());
                        }
                        infoProduct.setPrice(item.getProduct().getPrice());
                        infoProducts.add(infoProduct);

                    }
                }
                viewOrder.setProducts(infoProducts);
                listView.add(viewOrder);
            }
                return  ResponseEntity.ok(listView);
            }

            return ResponseEntity.ok("List Item empty!");


        }



    }
    @Override
    public ResponseEntity<?> getInfoOrderByTransportSales(String username)
    {
        User user = userRepository.findUserByUsername(username);
        if(user== null)
        {
            return ResponseEntity.ok("not logged in");
        }
        else
        {  List<ViewOrder1> listView= new ArrayList<>();
            List<Order> list= salesRepository.findAllByUserIdAndStatus(user.getId(),EStatus.transport);
            if(list!=null)
            {  for(Order i: list) {
                ViewOrder1 viewOrder = new ViewOrder1();
                viewOrder.setIdOrder(i.getId());
                viewOrder.setDateOrder(i.getDateOrder());
                viewOrder.setNamePersonOrder(i.getBuyer().getUsername());
                viewOrder.setPayment(i.getPayment().getName());
                viewOrder.setTotalPrice(i.getTotalPrice());
                AddressOrder addressOrder= addressOrderRepository.findAddressOrderByOrderId(i.getId());
                if(addressOrder!=null) {
                    viewOrder.setAddressOrder(addressOrder.getAddress() + "," + addressOrder.getProvince_city());
                }
                List<InfoProduct> infoProducts= new ArrayList<>();
                List<Item> list1= itemRepository.findAllByOrderId(i.getId());
                for (Item item : list1) {
                    if (item != null) {

                        InfoProduct infoProduct= new InfoProduct();
                        if(item.getType()!=null) {
                            infoProduct.setTypeOrder("Color: " + item.getType().getColor() + "," + "Size: " + item.getType().getSize());
                        }infoProduct.setNameShop(item.getProduct().getUser().getUsername());
                        infoProduct.setNumberProduct(item.getQuantity());
                        infoProduct.setNameProduct(item.getProduct().getName());
                        UrlImg urlImg= urlImgRepository.findUrlImgByProductId(item.getProduct().getId());
                        if(urlImg!=null) {
                            infoProduct.setUrl(urlImg.getUrl());
                        }
                        infoProduct.setPrice(item.getProduct().getPrice());
                        infoProducts.add(infoProduct);

                    }
                }
                viewOrder.setProducts(infoProducts);
                listView.add(viewOrder);
            }
                return  ResponseEntity.ok(listView);
            }

            return ResponseEntity.ok("List Item empty!");


        }



    }
    @Override
    public ResponseEntity<?> getInfoOrderByReceivedSales(String username)
    {
        User user = userRepository.findUserByUsername(username);
        if(user== null)
        {
            return ResponseEntity.ok("not logged in");
        }
        else
        {  List<ViewOrder1> listView= new ArrayList<>();
            List<Order> list= salesRepository.findAllByUserIdAndStatus(user.getId(),EStatus.received);
            if(list!=null)
            {  for(Order i: list) {
                ViewOrder1 viewOrder = new ViewOrder1();
                viewOrder.setIdOrder(i.getId());
                viewOrder.setDateOrder(i.getDateOrder());
                viewOrder.setNamePersonOrder(i.getBuyer().getUsername());
                viewOrder.setPayment(i.getPayment().getName());
                viewOrder.setTotalPrice(i.getTotalPrice());
                AddressOrder addressOrder= addressOrderRepository.findAddressOrderByOrderId(i.getId());
                if(addressOrder!=null) {
                    viewOrder.setAddressOrder(addressOrder.getAddress() + "," + addressOrder.getProvince_city());
                }
                List<InfoProduct> infoProducts= new ArrayList<>();
                List<Item> list1= itemRepository.findAllByOrderId(i.getId());
                for (Item item : list1) {
                    if (item != null) {

                        InfoProduct infoProduct= new InfoProduct();
                        if(item.getType()!=null) {
                            infoProduct.setTypeOrder("Color: " + item.getType().getColor() + "," + "Size: " + item.getType().getSize());
                        }infoProduct.setNameShop(item.getProduct().getUser().getUsername());
                        infoProduct.setNumberProduct(item.getQuantity());
                        infoProduct.setNameProduct(item.getProduct().getName());
                        infoProduct.setPrice(item.getProduct().getPrice());
                        UrlImg urlImg= urlImgRepository.findUrlImgByProductId(item.getProduct().getId());
                        if(urlImg!=null) {
                            infoProduct.setUrl(urlImg.getUrl());
                        }
                        infoProducts.add(infoProduct);

                    }
                }
                viewOrder.setProducts(infoProducts);
                listView.add(viewOrder);
            }
                return  ResponseEntity.ok(listView);
            }

            return ResponseEntity.ok("List Item empty!");


        }



    }
    @Override
public ResponseEntity<?> updateStatusSales(String username, UpdateStatusOrder updateStatusOrder)
{

    User user = userRepository.findUserByUsername(username);
    if(user== null)
    {
        return ResponseEntity.ok("not logged in");
    }
    else
    {
        Order order= orderRepository.findOrderById(updateStatusOrder.getIdOrder());
        order.setStatus(statusRepository.findStatusById(updateStatusOrder.getIdStatus()));
        orderRepository.save(order);
        return ResponseEntity.ok("update success");

    }

}
    @Override
    public List<Payment> getPayment()
    {
        return paymentRepository.findAll();
    }
    @Override
    public List<Status> getStatusAll()
    {
        return statusRepository.findAll();
    }


}
