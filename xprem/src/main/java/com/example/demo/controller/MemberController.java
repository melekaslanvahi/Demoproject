package com.example.demo.controller;

import com.example.demo.models.*;
import com.example.demo.dto.MemberDTO;
//import org.springframework.com.example.demo.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.MemberService;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@RestController
@RequestScope
@RequestMapping("members")
@CrossOrigin
@Validated
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    public MemberController( MemberService memberService) {
        this.memberService = memberService;
    }

    /*
    @RequestMapping("/")
    public String index() {
        return "Merhaba Spring Boot! Ben member com.example.demo.controller!";
    }
     */

    @GetMapping(value = "findAll")
    public ResponseEntity<List<MemberDTO>> findAll() {

        List<MemberDTO> memberDtos =  memberService.findAll();
        return new ResponseEntity<>(memberDtos, HttpStatus.OK);
    }

    @GetMapping(value = "profile/{id}")
    public ResponseEntity<MemberDTO> profile(@PathVariable("id") Long id) {

        MemberDTO memberDto =  memberService.findById( id );
        return new ResponseEntity<>(memberDto, HttpStatus.OK);
    }

    @PostMapping(value = "signUp")
    public ResponseEntity<MemberDTO> signUp(@RequestBody @Validated SignUpRequest signUpRequest) {

        MemberDTO memberDTO = memberService.signUp( signUpRequest );
        return new ResponseEntity<>(memberDTO,HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<EndMembershipResponse> endMembership(@PathVariable Long id) {
        EndMembershipResponse response = memberService.endMembership( id );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "getPayment")
    public ResponseEntity<GetPaymentResponse> getPayment(@RequestBody @Validated GetPaymentRequest getPaymentRequest) {

        GetPaymentResponse getPaymentResponse = memberService.getPayment( getPaymentRequest );
        return new ResponseEntity<>(getPaymentResponse,HttpStatus.OK);
    }



    /*
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberController(MemberRepository memberRepository,
                               BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    */

    /*
    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public void signUp(@RequestBody SignupDTO signupDTO  ) {
        Member member = new Member();
        member.setEmail( signupDTO.getEmail() );
        member.setPassword(bCryptPasswordEncoder.encode(signupDTO.getPassword()));
        memberRepository.save(member);
    }
    */


}
