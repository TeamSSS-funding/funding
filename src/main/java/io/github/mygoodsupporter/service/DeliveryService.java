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

    // 배송지 수정 화면 출력
    public Address addressUpdate(int number) {
        return deliveryDAO.select(number);
    }

    // 배송지 수정 처리
    public int editProcess(Address address) {
        return deliveryDAO.update(address);
    }

    // 배송지 삭제
    public int addressDelete(int number) {
        return deliveryDAO.delete(number);
    }
}
