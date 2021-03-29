package com.example.demo.service.impl;

import com.example.demo.customException.NotPremiumMemberException;
import com.example.demo.domain.entity.Member;
import com.example.demo.domain.entity.OnlineSupport;
import com.example.demo.dto.ApiErrorCodes;
import com.example.demo.models.OnlineSupportRequest;
import com.example.demo.models.OnlineSupportResponse;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.OnlineSupportRepository;
import com.example.demo.service.OnlineSupportService;
import com.example.demo.utils.EMemberShipType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class OnlineSupportServiceImpl implements OnlineSupportService {

    @Autowired
    private OnlineSupportRepository onlineSupportRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public OnlineSupportServiceImpl(OnlineSupportRepository onlineSupportRepository, ModelMapper modelMapper, MemberRepository memberRepository) {
        this.onlineSupportRepository = onlineSupportRepository;
        this.modelMapper = modelMapper;
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public OnlineSupportResponse addOnlineSupport(OnlineSupportRequest onlineSupportRequest) {

        boolean giveError = false;

        Optional<Member> member = memberRepository.findById( onlineSupportRequest.getUser() );

        if( member.isEmpty() ) {
            throw new IllegalArgumentException(ApiErrorCodes.BAD_MEMBER.getErrorMessage());
        }

        // member ship type is not premium and member ship end date is not ended
        if( EMemberShipType.getById( member.get().getMembershipType() ).getCode() == EMemberShipType.PREMIUM.getCode() ){

            OffsetDateTime today = OffsetDateTime.now();
            if( member.get().getMembershipEndDate() == null || member.get().getMembershipEndDate().isBefore( today ) ) {

                giveError = true;
            }
        } else{
            giveError = true;
        }

        if( giveError ){
            throw new NotPremiumMemberException( ApiErrorCodes.NOT_PREMIUM.getErrorMessage() );
        }

        OnlineSupport onlineSupport = modelMapper.map(onlineSupportRequest, OnlineSupport.class);
        onlineSupportRepository.save(onlineSupport);
        return modelMapper.map(onlineSupport, OnlineSupportResponse.class);
    }
}
