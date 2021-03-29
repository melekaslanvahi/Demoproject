package com.example.demo.service.impl;

import com.example.demo.customException.CreditCardNotValidException;
import com.example.demo.domain.entity.CreditCard;
import com.example.demo.dto.ApiErrorCodes;
import com.example.demo.model.*;
import com.example.demo.producer.CallerPersonProducer;
import com.example.demo.repository.CreditCardRepository;
import com.example.demo.service.CreditCardService;
import com.example.demo.utils.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.OffsetDateTime;
import java.util.*;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    CallerPersonProducer callerPersonProducer;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public CreditCardServiceImpl(CreditCardRepository creditCardRepository, ModelMapper modelMapper, CallerPersonProducer callerPersonProducer) {
        this.creditCardRepository = creditCardRepository;
        this.modelMapper = modelMapper;
        this.callerPersonProducer = callerPersonProducer;
    }

    @Override
    public GetPaymentFromCardResponse getPaymentFromCard(GetPaymentFromCardRequest request) {

        GetPaymentFromCardResponse response = new GetPaymentFromCardResponse();
        boolean exist = false;

        Optional<CreditCard> creditCardDb = null;

        // if card is valid
        if ( isCardValid( request ) ) {
            // controls if the user has already a card or not
            try {
                creditCardDb = creditCardRepository.findByUserId(request.getUserId());
                if ( !creditCardDb.isEmpty() )
                    exist =true;
            }catch ( Exception e){
                exist = false;
            }

            if ( !exist ){
                CreditCard creditCard = new CreditCard();
                creditCard.setCardNumber( request.getCardNumber() );
                creditCard.setCardExpireDate(request.getCardExpireDate());
                creditCard.setCvv( request.getCvv() );
                creditCard.setName(request.getName());
                creditCard.setSurname(request.getSurname());
                creditCard.setUserId( request.getUserId() );
                creditCard.setCreatedDate(OffsetDateTime.now());
                creditCardRepository.save( creditCard );

                /*
                CreditCard creditCard = modelMapper.map(request, CreditCard.class);
                creditCardRepository.save(creditCard);
                response.setPaymentReceived( true );
                */
                // TODO: mail atma islemini queue'ye ekle

            } else{
                creditCardDb.get().setCardNumber( request.getCardNumber() );
                creditCardDb.get().setCardExpireDate(request.getCardExpireDate());
                creditCardDb.get().setCvv( request.getCvv() );
                creditCardDb.get().setName(request.getName());
                creditCardDb.get().setSurname(request.getSurname());
                creditCardRepository.save( creditCardDb.get() );
            }
            response.setPaymentReceived( true );
            addToQueue( request ); // adds the queue for send email
        } else{
            throw new CreditCardNotValidException( ApiErrorCodes.NOT_VALID_CARD.getErrorMessage() );
        }

        return response;
    }

    @Override
    public GetPaymentAutomaticlyResponse getPaymentAutomaticly(GetPaymentAutomaticlyRequest request) {

        List<GetPaymentFromCardRequest> paymentFromCardRequests = new ArrayList<GetPaymentFromCardRequest>();
        Map<Long,String> memberInfoMap = new HashMap<>();

        GetPaymentAutomaticlyResponse returnResponse = new GetPaymentAutomaticlyResponse();
        List<GetPaymentMemberInfo> getPaymentMemberInfos = new ArrayList<GetPaymentMemberInfo>();

        List<Long> userIdList = new ArrayList<>();

        if( !CollectionUtils.isEmpty( request.getMembersInfos() ) ) {
            request.getMembersInfos().forEach(memberInfo -> {
                userIdList.add(memberInfo.getUserId());
                memberInfoMap.put( memberInfo.getUserId(), memberInfo.getEmail() );

            });

            if (!CollectionUtils.isEmpty(userIdList)) {
                List<CreditCard> creditCards = creditCardRepository.findCreditCardByUsers( userIdList );

                if ( !CollectionUtils.isEmpty( creditCards ) ){
                    creditCards.forEach( item -> {
                        String email = memberInfoMap.get( item.getUserId() );
                        GetPaymentFromCardRequest req = new GetPaymentFromCardRequest();
                        modelMapper.map( item, req );
                        req.setEmail( email );
                        paymentFromCardRequests.add( req );
                    } );

                    paymentFromCardRequests.forEach( tempRequest -> {

                        GetPaymentMemberInfo eachMemberInfo = new GetPaymentMemberInfo();

                        try {
                            eachMemberInfo.setUserId( tempRequest.getUserId() );
                            GetPaymentFromCardResponse response = getPaymentFromCard(tempRequest); // mail queue'ye burda eklenir !
                            eachMemberInfo.setReceivedPayment( response.isPaymentReceived() );

                        } catch (Exception ex){
                            eachMemberInfo.setReceivedPayment( false );
                        }

                        getPaymentMemberInfos.add( eachMemberInfo );

                    } );

                    returnResponse.setGetPaymentMemberInfos( getPaymentMemberInfos );
                }
            }
        }

        return returnResponse;
    }

    private boolean isCardValid(GetPaymentFromCardRequest request){

        if ( !validateCardExpiryDate( request.getCardExpireDate() ) )
            return false;

        if ( !validateCreditCardNumber( request.getCardNumber() ) )
            return false;

        return true;

    }

    private boolean validateCardExpiryDate(String expiryDate) {
        return expiryDate.matches("(?:0[1-9]|1[0-2])/[0-9]{2}");
    }

    private static boolean validateCreditCardNumber(String str) {

        int[] ints = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            ints[i] = Integer.parseInt(str.substring(i, i + 1));
        }
        for (int i = ints.length - 2; i >= 0; i = i - 2) {
            int j = ints[i];
            j = j * 2;
            if (j > 9) {
                j = j % 10 + 1;
            }
            ints[i] = j;
        }
        int sum = 0;
        for (int i = 0; i < ints.length; i++) {
            sum += ints[i];
        }
        if (sum % 10 == 0) {
            System.out.println(str + " is a valid credit card number");
            return true;
        } else {
            System.out.println(str + " is an invalid credit card number");
            return false;
        }
    }

    private void addToQueue( GetPaymentFromCardRequest request ){

        CallerPerson callerPerson = new CallerPerson();
        callerPerson.setEmail( request.getEmail() );
        callerPerson.setSubject(Constants.EMAIL_SUBJECT);
        callerPerson.setMessage( Constants.EMAIL_MESSAGE );
        callerPerson.setCreatedAt( OffsetDateTime.now() );
        callerPerson.setSeen(Boolean.FALSE);
        callerPerson.setCallerPersonId(UUID.randomUUID().toString());
        callerPersonProducer.sendToQueue( callerPerson );
    }
}
