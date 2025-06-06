package com.ll.dopdang.domain.payment.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

import com.ll.dopdang.domain.payment.entity.PaymentType;

/**
 * 결제 주문 정보를 저장하기 위한 DTO 레코드
 */
public record PaymentOrderInfo(
	PaymentType paymentType,
	Long referenceId,
	String orderId,
	Integer quantity,
	Long memberId
) implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * Map에서 PaymentOrderInfo 객체를 생성합니다.
	 *
	 * @param map 변환할 Map 객체
	 * @return PaymentOrderInfo 객체
	 */
	public static PaymentOrderInfo fromMap(Map<String, Object> map) {
		PaymentType paymentType = PaymentType.valueOf(map.get("paymentType").toString());
		Long referenceId = Long.valueOf(map.get("referenceId").toString());
		String orderId = (String)map.get("orderId");
		Integer quantity = map.get("quantity") != null ? Integer.valueOf(map.get("quantity").toString()) : null;
		Long memberId = map.get("memberId") != null ? Long.valueOf(map.get("memberId").toString()) : null;

		return new PaymentOrderInfo(paymentType, referenceId, orderId, quantity, memberId);
	}
}
