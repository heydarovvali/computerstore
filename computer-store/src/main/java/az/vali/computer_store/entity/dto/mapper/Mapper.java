package az.vali.computer_store.entity.dto.mapper;

import az.vali.computer_store.entity.*;
import az.vali.computer_store.entity.dto.*;
import az.vali.computer_store.status.OrderStatus;

public class Mapper {
    public static BasketDto entityToBasketDto(Basket basket) {  // cliente vacib melumatlari gostermek ucun basketin xususiyyetlerini azaldib vacib melumatlarini qeyd edirik
        BasketDto basketDto = new BasketDto();
        basketDto.setId(basket.getId());
        basketDto.setComputers(basket.getComputers());
        basketDto.setUser(basket.getUser());
        return basketDto;
    }

    public static Basket basketDtoToEntity(BasketDto basketDto) {  // istifadeciye maraqli deyil basket ne vaxt yaradilib ve bitme tarixi ne vaxtdir, en vacib hansi komputerleri elave etdiyidir.
        Basket basket = new Basket();
        basket.setId(basketDto.getId());
        basket.setComputers(basketDto.getComputers());
        return basket;
    }

    public static ComputerDto entityToComputerDto(Computer computer) {
        ComputerDto computerDto = new ComputerDto();
        computerDto.setId(computer.getId());
        computerDto.setName(computer.getName());
        computerDto.setBrand(computer.getBrand());
        computerDto.setPrice(computer.getPrice());
        return computerDto;
    }

    public static Computer computerDtoToEntity(ComputerDto computerDto) {
        Computer computer = new Computer();
        computer.setId(computerDto.getId());
        computer.setName(computerDto.getName());
        computer.setBrand(computerDto.getBrand());
        computer.setPrice(computerDto.getPrice());
        return computer;
    }

    public static CourierDto entityToCourierDto(Courier courier) {
        CourierDto courierDto = new CourierDto();
        courierDto.setId(courier.getId());
        courierDto.setName(courier.getName());
        courierDto.setCourierStatus(courier.getCourierStatus());
        return courierDto;
    }

    public static Courier courierToDtoEntity(CourierDto courierDto) {
        Courier courier = new Courier();
        courier.setId(courierDto.getId());
        courier.setName(courierDto.getName());
        courier.setCourierStatus(courierDto.getCourierStatus());
        return courier;
    }

    public static OrderDto entityToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setComputers(order.getComputers());
        orderDto.setCourier(order.getCourier());
        orderDto.setUser(order.getUser());
        return orderDto;
    }

    public static Order orderToDtoEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setComputers(orderDto.getComputers());
        order.setUser(orderDto.getUser());
        order.setCourier(orderDto.getCourier());
        return order;
    }

    public static SellerDto entityToSellerDto(Seller seller) {
        SellerDto sellerDto = new SellerDto();
        sellerDto.setId(seller.getId());
        sellerDto.setTelephoneNumber(seller.getTelephoneNumber());
        return sellerDto;
    }

    public static Seller sellerToDtoEntity(SellerDto sellerDto) {
        Seller seller = new Seller();
        seller.setId(sellerDto.getId());
        seller.setTelephoneNumber(sellerDto.getTelephoneNumber());
        return seller;
    }
}

