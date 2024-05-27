package org.likelion.likelionrecrud.order.api;

import lombok.RequiredArgsConstructor;
import org.likelion.likelionrecrud.order.api.dto.response.OrderInfoResDto;
import org.likelion.likelionrecrud.order.api.dto.response.OrderListResDto;
import org.likelion.likelionrecrud.order.api.dto.request.OrderSaveReqDto;
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

    //create
    @PostMapping // order/save => rest api url에 동사 x
    public ResponseEntity<String> orderSave(@RequestBody OrderSaveReqDto orderSaveReqDto) {
        orderService.orderSave(orderSaveReqDto);
        return new ResponseEntity<>("주문 저장!!", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<OrderListResDto> getOrderByMember(@PathVariable("memberId") Long memberId) {
        List<OrderInfoResDto> orderInfoResDtoList = orderService.findOrderInfoByMemberId(memberId);
        return ResponseEntity.ok(OrderListResDto.from(orderInfoResDtoList));
    }
    @PostMapping("/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok("주문이 취소되었습니다!");
    }

    @DeleteMapping("{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable("orderId") Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok("주문이 삭제되었습니다!");
    }

    @ExceptionHandler(IllegalArgumentException.class)   // 예외 처리를 위한 어노테이션, 처리하고자 하는 예외 클래스를 지정하고 설정한 응답 반환
    public ResponseEntity<String> handleError(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);   // bad_request: 클라이언트의 요청이 잘못된 형식이거나 부적절할 때

    }
}
