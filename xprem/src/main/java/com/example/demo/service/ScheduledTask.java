package com.example.demo.service;

import com.example.demo.domain.entity.Member;
import com.example.demo.dto.MemberDTO;
import com.example.demo.models.*;
import com.example.demo.repository.MemberRepository;
import com.example.demo.utils.Constants;
import com.example.demo.utils.EMemberShipStatus;
import com.example.demo.utils.EMemberShipType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.OffsetDateTime;
import java.util.*;

@Component
public class ScheduledTask {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    public ScheduledTask(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * it executes every day at 8 pm
     */
    //@Scheduled(cron = "*/10 * * * * *")
    @Scheduled(cron = "0 0 8 * * *" )
    public void automaticGetPayment(){

        OffsetDateTime tomorrow = OffsetDateTime.now().plusDays(1);
        List<Member> memberList = memberRepository.getPaymentOfAutomaticly(EMemberShipType.PREMIUM.getCode(), EMemberShipStatus.CONTINUE.getCode(), tomorrow );

        GetPaymentAutomaticlyRequest request = new GetPaymentAutomaticlyRequest();
        List<MemberInfo> memberInfos = new ArrayList<MemberInfo>();
        Map<Long, Member> userMap = new HashMap<>();

        if( ! CollectionUtils.isEmpty( memberList )) {

            memberList.forEach(item -> {
                MemberInfo memberInfo = new MemberInfo();
                memberInfo.setEmail(item.getEmail());
                memberInfo.setUserId(item.getId());
                memberInfos.add(memberInfo);
                userMap.put( item.getId(), item );
            });

            request.setMembersInfos(memberInfos);

            final CreditCardClient creditCardClient = new CreditCardClient();

            ResponseEntity<GetPaymentAutomaticlyResponse> response = creditCardClient.callGetPaymentFromCardAutomaticly(request);

            if (response != null && response.getStatusCode() == HttpStatus.OK) {

                if (response.getBody() != null) {
                    List<GetPaymentMemberInfo> getPaymentMemberInfos = response.getBody().getGetPaymentMemberInfos();
                    if (!CollectionUtils.isEmpty(getPaymentMemberInfos)) {

                        getPaymentMemberInfos.forEach(temp -> {
                            if (temp.isReceivedPayment()) {

                                Member member = userMap.get( temp.getUserId() );
                                member.setMembershipEndDate(OffsetDateTime.now().plusMonths(1));
                                member.setMembershipType(EMemberShipType.PREMIUM.getCode());
                                member.setUpdatedDate(OffsetDateTime.now());
                                member.setMembershipStatus(EMemberShipStatus.CONTINUE.getCode());
                                memberRepository.save(member);
                            }
                        });
                    }
                }
            }
        }
    }
}
