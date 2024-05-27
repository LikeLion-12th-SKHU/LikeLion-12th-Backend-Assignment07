package org.likelion.likelionrecrud.order.api;

import lombok.RequiredArgsConstructor;
import org.likelion.likelionrecrud.item.api.dto.request.ItemUpdateReqDto;
import org.likelion.likelionrecrud.order.api.request.OrderSaveReqDto;
import org.likelion.likelionrecrud.order.api.response.OrderInfoResDto;
import org.likelion.likelionrecrud.order.api.response.OrderListResDto;
import org.likelion.likelionrecrud.order.application.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> orderSave(@RequestBody OrderSaveReqDto orderSaveReqDto) {
        orderService.orderSave(orderSaveReqDto);
        return new ResponseEntity<>("주문 저장!", HttpStatus.CREATED);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<OrderListResDto> getOrdersByMember(@PathVariable("memberId") Long memberId) {
        List<OrderInfoResDto> orderInfoResDto = orderService.findOrderInfoByMemberId(memberId);
        return ResponseEntity.ok(OrderListResDto.from(orderInfoResDto));
    }

    @PatchMapping("/{orderId}")
    public  ResponseEntity<String> cancelOrder(@PathVariable("orderId")Long orderId){
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok("주문이 취소되었습니다.");
    }

    @GetMapping("{orderId}")
    public ResponseEntity<String> delete(@PathVariable("orderId") Long orderId){
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok("주문이 삭제되었습니다!");
    }

}
