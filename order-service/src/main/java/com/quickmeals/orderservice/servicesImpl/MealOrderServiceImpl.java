package com.quickmeals.orderservice.servicesImpl;

import com.quickmeals.orderservice.customtypes.MealOrderStatus;
import com.quickmeals.orderservice.dtos.MealOrderDto;
import com.quickmeals.orderservice.entities.MealOrder;
import com.quickmeals.orderservice.repository.MealOrderRepository;
import com.quickmeals.orderservice.services.MealOrderService;

import java.util.ArrayList;
import java.util.List;

public class MealOrderServiceImpl implements MealOrderService {
    private final MealOrderRepository mealOrderRepository;
    private final EntityDtoConverter entityDtoConverter;

    public MealOrderServiceImpl(MealOrderRepository mealOrderRepository, EntityDtoConverter entityDtoConverter) {
        this.mealOrderRepository = mealOrderRepository;
        this.entityDtoConverter = entityDtoConverter;
    }

    @Override
    public Boolean createNewMealOrder(MealOrderDto mealOrderDto) {
        MealOrder mealOrder = mealOrderRepository.save(entityDtoConverter.convertDtoToMealOrder(mealOrderDto));
        return mealOrder.getOrderId() != null;
    }

    @Override
    public List<MealOrderDto> getAllMealOrders() {
        List<MealOrderDto> allOrders = new ArrayList<>();
        mealOrderRepository.findAll().forEach(mealOrder ->
                allOrders.add(entityDtoConverter.convertMealOderToDto(mealOrder)));
        return allOrders;
    }

    @Override
    public List<MealOrderDto> filterOrdersByCustomer(Integer userId) {
        List<MealOrderDto> customerOrderList = new ArrayList<>();
        mealOrderRepository.findOrdersByRequestingCustomerId(userId).orElseThrow()
                .forEach(mealOrder -> customerOrderList.add(entityDtoConverter.convertMealOderToDto(mealOrder)));
        return customerOrderList;
    }

    @Override
    public List<MealOrderDto> filterOrdersByVendor(Integer userId) {
        List<MealOrderDto> vendorOrderList = new ArrayList<>();
        mealOrderRepository.findOrderBySelectedVendorId(userId).orElseThrow()
                .forEach(mealOrder -> vendorOrderList.add(entityDtoConverter.convertMealOderToDto(mealOrder)));
        return vendorOrderList;
    }

    @Override
    public Boolean updateOrderStatus(Integer orderId, MealOrderStatus orderStatus) {
        if (mealOrderRepository.findById(orderId).isPresent()) {
            MealOrder mealOrder = mealOrderRepository.findById(orderId).orElseThrow();
            mealOrder.setOrderStatus(orderStatus);
            mealOrderRepository.save(mealOrder);
            return true;
        }else return null;
    }

    @Override
    public MealOrderStatus queryMealOrderStatus(Integer orderId) {
        return mealOrderRepository.findById(orderId).orElseThrow().getOrderStatus();
    }

    @Override
    public Boolean cancelOrder(Integer orderId) {
        if (mealOrderRepository.findById(orderId).isPresent()) {
            MealOrder mealOrder = mealOrderRepository.getReferenceById(orderId);
            if (mealOrder.getOrderStatus() == MealOrderStatus.WAITING_APPROVAL) {
                mealOrder.setOrderStatus(MealOrderStatus.CANCELED);
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean deleteOrder(Integer orderId) {
        if (mealOrderRepository.findById(orderId).isPresent()) {
            mealOrderRepository.deleteById(orderId);
            return true;
        }
        return false;
    }
}
