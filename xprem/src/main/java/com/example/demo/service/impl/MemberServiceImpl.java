package com.example.demo.service.impl;

import com.example.demo.customException.*;
import com.example.demo.domain.entity.Member;
import com.example.demo.dto.ApiErrorCodes;
import com.example.demo.dto.MemberDTO;
import com.example.demo.models.*;
import com.example.demo.service.CreditCardClient;
import com.example.demo.utils.Constants;
import org.bouncycastle.jcajce.provider.symmetric.AES;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;
import com.example.demo.utils.EMemberShipStatus;
import com.example.demo.utils.EMemberShipType;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl  implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<MemberDTO> findAll() {

        List<Member> members = memberRepository.findAll();
        List<MemberDTO> memberDTOS = new ArrayList<>();

        if (!CollectionUtils.isEmpty(members)) {
            members.forEach(eachMember -> {
                MemberDTO dto = createMemberDTO(eachMember);
                memberDTOS.add(dto);
            });
        }

        return memberDTOS;
    }

    /**
     *
     * @param eachMember
     * @return
     */
    private MemberDTO createMemberDTO(Member eachMember) {

        MemberDTO dto = modelMapper.map( eachMember, MemberDTO.class );
        if ( eachMember.getMembershipDate() != null)
            dto.setMembershipDate(eachMember.getMembershipDate().format( DateTimeFormatter.ISO_OFFSET_DATE ).toString() );
        dto.setMembershipStatus(EMemberShipStatus.getById(eachMember.getMembershipStatus()).getName());
        dto.setMembershipType(EMemberShipType.getById(eachMember.getMembershipType()).getName());

        if (EMemberShipType.getById(eachMember.getMembershipType()).getCode() == EMemberShipType.PREMIUM.getCode()){
            if ( eachMember.getMembershipEndDate() != null ) {
                dto.setMemberShipEndDate(eachMember.getMembershipEndDate().format(DateTimeFormatter.ISO_OFFSET_DATE).toString());
            }
        }

        /*
        MemberDTO dto = new MemberDTO();
        dto.setId(eachMember.getId());
        dto.setCreatedDate(eachMember.getCreatedDate());
        dto.setEmail(eachMember.getEmail());
        if ( eachMember.getMembershipDate() != null)
            dto.setMembershipDate(eachMember.getMembershipDate().format( DateTimeFormatter.ISO_OFFSET_DATE ).toString() );

        dto.setName(eachMember.getName());
        dto.setSurname(eachMember.getSurname());
        dto.setMembershipStatus(EMemberShipStatus.getById(eachMember.getMembershipStatus()).getName());
        EMemberShipType etype = EMemberShipType.getById(eachMember.getMembershipStatus());
        dto.setMembershipType(etype.getName());
        if (etype.getCode() == EMemberShipType.PREMIUM.getCode()){
            if ( eachMember.getMembershipEndDate() != null ) {
                dto.setMemberShipEndDate(eachMember.getMembershipEndDate().format(DateTimeFormatter.ISO_OFFSET_DATE).toString());
            }
        }
         */

        return dto;
    }

    @Override
    public MemberDTO findById(Long id) {

        Optional<Member> member = memberRepository.findById(id);

        if (member.isEmpty()) {
            new NotPremiumMemberException(ApiErrorCodes.MISSING_MEMBER.getErrorMessage());
        }


        return createMemberDTO(member.get());
    }

    @Override
    public GetPaymentResponse getPayment(GetPaymentRequest request) {

        GetPaymentResponse result = new GetPaymentResponse();
        result.setSuccess( false );

        Optional<Member> member = memberRepository.findById( request.getUserId() );

        if ( member.isEmpty() )
           throw new MemberNotFoundException( ApiErrorCodes.MISSING_MEMBER.getErrorMessage() );

        // already premium member control
        if (EMemberShipType.PREMIUM.getCode() == member.get().getMembershipType() ){
            if( member.get().getMembershipEndDate() != null && member.get().getMembershipEndDate().isAfter( OffsetDateTime.now().minusDays(1) ) ){
                throw new AlreadyPremiumMemberException( ApiErrorCodes.ALREADY_PREMIUM.getErrorMessage() );
            }
        }

        GetPaymentFromCardRequest getPaymentFromCardRequest = new GetPaymentFromCardRequest();
        getPaymentFromCardRequest.setName( request.getName() );
        getPaymentFromCardRequest.setSurname( request.getSurname() );
        getPaymentFromCardRequest.setCardExpireDate( request.getCardExpireDate() );
        getPaymentFromCardRequest.setCardNumber( request.getCardNumber() );
        getPaymentFromCardRequest.setCvv( request.getCvv() );
        getPaymentFromCardRequest.setUserId( request.getUserId() );
        getPaymentFromCardRequest.setEmail(member.get().getEmail() );

        final CreditCardClient creditCardClient = new CreditCardClient();

        //ResponseEntity<GetPaymentFromCardResponse> response = creditCardService.getPaymentFromCard( getPaymentFromCardRequest );
        ResponseEntity<GetPaymentFromCardResponse> response = creditCardClient.callGetPaymentFromCard(getPaymentFromCardRequest);

        if( response != null && response.getStatusCode() == HttpStatus.OK ){

            if ( response.getBody().isPaymentReceived() ){
                member.get().setMembershipEndDate( OffsetDateTime.now().plusMonths(1) );
                member.get().setMembershipType( EMemberShipType.PREMIUM.getCode() );
                member.get().setUpdatedDate( OffsetDateTime.now() );
                member.get().setMembershipStatus( EMemberShipStatus.CONTINUE.getCode() );
                memberRepository.save( member.get() );
                result.setSuccess( true );
                result.setMessage(Constants.SUCCESS_PREMIUM);
            }
        } else{
            throw new NotPaymentException( ApiErrorCodes.NOT_PAYMENT.getErrorMessage() );
        }

        return result;
    }

    @Override
    public EndMembershipResponse endMembership(Long id) {

        Optional<Member> member = memberRepository.findById(id);

        if (member.isEmpty()) {
            new NotPremiumMemberException(ApiErrorCodes.MISSING_MEMBER.getErrorMessage());
        }

        Member tempMember = member.get();

        if(tempMember.getMembershipStatus() == EMemberShipStatus.END.getCode() )
            throw new AlreadyEndPremiumMemberException( ApiErrorCodes.ALREADY_END_PREMIUM.getErrorMessage() );

        tempMember.setMembershipStatus( EMemberShipStatus.END.getCode() );
        tempMember.setUpdatedDate( OffsetDateTime.now() );
        memberRepository.save( tempMember );

        EndMembershipResponse response = new EndMembershipResponse();
        response.setSuccess( true );
        response.setMessage( Constants.END_PREMIUM );
        return response;
    }

    @Override
    public MemberDTO signUp(SignUpRequest request) {

        Optional<Member> memberOptional = memberRepository.findByEmail( request.getEmail() );
        if ( !memberOptional.isEmpty() )
            throw new AlreadyMemberDefinedException( ApiErrorCodes.ALREADY_MEMBER_DEFINED.getErrorMessage() );

        Member member = new Member();
        member.setMembershipType( EMemberShipType.STANDART.getCode() );
        member.setMembershipDate( OffsetDateTime.now() );
        member.setMembershipStatus( EMemberShipStatus.CONTINUE.getCode() );
        member.setCreatedDate(OffsetDateTime.now());
        member.setEmail( request.getEmail() );
        member.setPassword( request.getPassword() );
        member.setName( request.getName() );
        member.setSurname( request.getSurname() );

        Member createdMember = memberRepository.save( member );
        MemberDTO memberDTO = modelMapper.map( createdMember, MemberDTO.class );

        return memberDTO;
    }
}
