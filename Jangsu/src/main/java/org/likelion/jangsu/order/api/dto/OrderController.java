package org.likelion.jangsu.order.api.dto;

import lombok.RequiredArgsConstructor;
import org.likelion.jangsu.order.api.dto.request.OrderSaveReqDto;
import org.likelion.jangsu.order.api.dto.response.OrderInfoResDto;
import org.likelion.jangsu.order.api.dto.response.OrderListResDto;
import org.likelion.jangsu.order.application.OrderService;
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
    @PostMapping("/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok("주문이 취소되었습니다.");
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<OrderListResDto> getOrdersByMember(@PathVariable("memberId") Long memberId) {
        List<OrderInfoResDto> orderInfoResDto = orderService.findOrderInfoByMemberId(memberId);
        return ResponseEntity.ok(OrderListResDto.from(orderInfoResDto));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable("orderId") Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok("주문이 삭제되었습니다.");
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}