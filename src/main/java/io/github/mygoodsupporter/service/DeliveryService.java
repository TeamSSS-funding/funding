package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.dao.DeliveryDAO;
import io.github.mygoodsupporter.domain.Address;
import io.github.mygoodsupporter.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryDAO deliveryDAO;

    // 배송지 추가
    public int deliveryWrite(Address address) {
        return deliveryDAO.insert(address);
    }

    // 배송지 목록 조회
    public List<Address> addressList(Address address) {
        return deliveryDAO.list(address);
    }
}
