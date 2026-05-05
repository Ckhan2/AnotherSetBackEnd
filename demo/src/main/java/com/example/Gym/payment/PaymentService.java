package com.example.Gym.payment;

import com.example.Gym.member.MemberRepository;
import com.example.Gym.payment.dto.PaymentRequest;
import com.example.Gym.payment.dto.PaymentResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PaymentService {
   private final PaymentRepository paymentRepository;

   private final MemberRepository memberRepository;
   public PaymentService(PaymentRepository paymentRepository, MemberRepository memberRepository) {
       this.paymentRepository = paymentRepository;
       this.memberRepository = memberRepository;
   }
   @Transactional(readOnly = true)
   public List<PaymentResponse> findByMemberId(Long memberId){
    if(!memberRepository.existsById(memberId)) {
        throw new IllegalArgumentException("Member with id " + memberId + " does not exist.");
    }
    return paymentRepository.findByMemberIdOrderByPaymentDateDesc(memberId).stream()
    .map(PaymentResponse::from).toList();

   }
   @Transactional(readOnly = true)
    public PaymentResponse findById(Long id) {
        Payment payment = paymentRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Payment with id " + id + " does not exist."));
        return PaymentResponse.from(payment);
    }
    @Transactional
    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        if(!memberRepository.existsById(paymentRequest.getMemberId())) {
            throw new IllegalArgumentException("Member with id " + paymentRequest.getMemberId() + " does not exist.");
        }
        Payment payment = new Payment();
        payment.setMemberId(paymentRequest.getMemberId());
        payment.setAmount(paymentRequest.getAmount());
        payment.setCurrency(paymentRequest.getCurrency());
        payment.setPaymentMethod(paymentRequest.getPaymentMethod());
        payment.setPaymentStatus(paymentRequest.getPaymentStatus());
        payment.setDescription(paymentRequest.getDescription());
        payment.setPaymentDate(paymentRequest.getPaymentDate());
        payment.setReferenceId(paymentRequest.getReferenceId());
        return PaymentResponse.from(paymentRepository.save(payment));
    }
    @Transactional
    public void delete(Long id){
        if(!paymentRepository.existsById(id)) {
            throw new IllegalArgumentException("Payment with id " + id + " does not exist.");
        }
        paymentRepository.deleteById(id);
    }


}