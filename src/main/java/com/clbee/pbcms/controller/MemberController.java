package com.clbee.pbcms.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.clbee.pbcms.util.MyPasswordEncoder;
import com.clbee.pbcms.util.RandomStringBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import com.clbee.pbcms.service.CompanyService;
import com.clbee.pbcms.service.GroupService;
import com.clbee.pbcms.service.GroupViewMenuService;
import com.clbee.pbcms.service.LicenseService;
import com.clbee.pbcms.service.MemberService;
import com.clbee.pbcms.util.ResourceNotFoundException;
import com.clbee.pbcms.security.MyUserDetails;
import com.clbee.pbcms.vo.CompanyVO;
import com.clbee.pbcms.vo.GroupUserVO;
import com.clbee.pbcms.vo.LicenseList;
import com.clbee.pbcms.vo.LicenseSubList;
import com.clbee.pbcms.vo.LicenseVO;
import com.clbee.pbcms.vo.MemberVO;

@Slf4j
@Controller
@AllArgsConstructor
public class MemberController {

    private MemberService memberService;
    private CompanyService companyService;
    private JavaMailSender mailSender;
    private MessageSource messageSource;
    private LocaleResolver localeResolver;
    private GroupService groupService;

    @GetMapping(value = "/member/join/ok.html")
    public ModelAndView memberJoinOk( HttpServletRequest request ) {
        ModelAndView mav = new ModelAndView();
        Random random = new Random();

        String validId = request.getParameter("validId");
        MemberVO memberVO = memberService.findByCustomInfo("emailChkSession", validId);
//		System.out.println(validId);

        int limitUser =  Integer.parseInt(messageSource.getMessage("limit.user.count", null, localeResolver.resolveLocale(request)));

        if(memberVO == null){
            mav.addObject("msg", messageSource.getMessage("app.control.006", null, localeResolver.resolveLocale(request)));
            mav.addObject("type", "href");
            mav.addObject("url", "/index.html");
            mav.setViewName("inc/dummy");
            return mav;
        }
        int permitUser = memberService.selectCountWithPermisionUserByCompanySeq(memberVO.getCompanySeq());
        if( permitUser >= limitUser ){
            mav.setViewName("07_member/member_join_ok");
            mav.addObject("emailChk", false);
            return mav;
        }
        int result = memberService.verifyIfExists("emailChkSession", validId);

        switch (result){
            case 0 :
                System.out.println("i'm in case 0");
                throw new ResourceNotFoundException();
            case 1 :
                System.out.println("i'm in case 1");

                if("1".equals(memberVO.getCompanyGb())){
                    int companySeq = memberVO.getCompanySeq();
                    CompanyVO companyVO = companyService.findByCustomInfo("companySeq", companySeq);
                    memberVO.setUserStatus("4");
                    companyVO.setCompanyStatus("4");
                    memberVO.setEmailChkDt(new Date());
                    memberVO.setEmailChkGb("Y");
                    memberVO.setEmailChkSession(MyPasswordEncoder.changeSHA256(String.valueOf(System.currentTimeMillis()+random.nextInt())));
                    memberService.updateMemberInfo(memberVO, memberVO.getUserSeq());
                    companyService.updateCompanyInfo(companyVO, companySeq);
                }else {
                    memberVO.setUserStatus("4");
                    memberVO.setEmailChkDt(new Date());
                    memberVO.setEmailChkGb("Y");

                    memberVO.setEmailChkSession(MyPasswordEncoder.changeSHA256(String.valueOf(System.currentTimeMillis()+random.nextInt())));
                    memberService.updateMemberInfo(memberVO, memberVO.getUserSeq());
                }
                mav.setViewName("07_member/member_join_ok");
                mav.addObject("emailChk", true);
                return mav;
            case 2 :
                System.out.println("There are two value duplicated with email_chk_session");
                throw new ResourceNotFoundException();
        }
        mav.setViewName("07_member/member_join_ok");
        mav.addObject("emailChk", true);
        return mav;
    }

    @GetMapping(value = "/member/join.html")
    public ModelAndView home( HttpServletRequest request ) {
        ModelAndView mav = new ModelAndView();

        List<GroupUserVO> groupList = groupService.getSelectListGroup(0);

        mav.addObject("groupList", groupList);
        mav.setViewName("07_member/member_join");

        return mav;
    }

    @PostMapping(value = "/member/join.html")
    public String join( MemberVO memberVO, CompanyVO companyVO, HttpServletRequest request ) {

        /*request.getParameter(arg0)*/
        memberVO.setEmailChkSession(MyPasswordEncoder.changeSHA256(memberVO.getUserId()));
        memberVO.setUserPw(MyPasswordEncoder.changeSHA256(memberVO.getUserPw()));
        memberVO.setRegIp(request.getRemoteAddr());
        if("1".equals(memberVO.getCompanyGb())){
            int companySeq = companyService.insertCompanyInfoWithProcedure(companyVO);
            memberVO.setCompanySeq(companySeq);
        }
        memberVO.setRegDt(new Date());
        memberService.addMember(memberVO);
        System.out.println("sending Email@@@@@@@@@@@@@@@@@@");

        String from = messageSource.getMessage("send.email.ID", null, localeResolver.resolveLocale(request));
        //message : PageCreator ���� �����Դϴ�.
        String subject = messageSource.getMessage("member.control.007", null, localeResolver.resolveLocale(request));
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            messageHelper.setTo(memberVO.getEmail());
            //message : �ش� URL�� �����Ͻø� ���� ������ �Ϸ� �˴ϴ�.
            messageHelper.setText(messageSource.getMessage("member.control.008", null, localeResolver.resolveLocale(request)) +"\n"+ "http://" + messageSource.getMessage("basic.Info.IP", null, localeResolver.resolveLocale(request))+"/member/join/ok.html?validId="
                    + MyPasswordEncoder.changeSHA256(memberVO.getUserId()));
            messageHelper.setFrom(from);
            messageHelper.setSubject(subject);
            mailSender.send(message);
        } catch(Exception e){
            System.out.println(e);
        }
        return "redirect:/index.html";
    }

    @PostMapping(value = "/member/userIdValidation")
    public @ResponseBody int userIdValidation( String inputUserId ){
        return memberService.verifyIfExists("userId", inputUserId);
    }

    @PostMapping(value = "/member/emailValidation")
    public @ResponseBody int emailValidation( String inputEmail ){
        return memberService.verifyIfExists("email", inputEmail);
    }

    @PostMapping(value = "/member/userStatusValidation")
    public @ResponseBody int userStatusValidation(String userId, String userPw){

        log.info("userStateValidation: " + userId + "," + userPw);

        MemberVO memberVO = memberService.findByUserName(userId);
        if(memberVO == null) {
            return 6;
        }
        else if(!"".equals(userId) && !"".equals(userPw)) {
            int loginResult = memberService.logInVerify(userId, MyPasswordEncoder.changeSHA256(userPw));
            log.info("loginResult: " + loginResult);

            if ( loginResult < 0) return 6;
            else if(loginResult == 1){
                if("4".equals(memberVO.getUserStatus())) {
                    MemberVO updatedVO = new MemberVO();
                    updatedVO.setLoginDt(new Date());
                    updatedVO.setUserStartDt(memberVO.getUserStartDt());
                    updatedVO.setUserEndDt(memberVO.getUserEndDt());
                    memberService.updateMemberInfo(updatedVO, memberVO.getUserSeq());
                    return 4;
                }
                else {
                    return Integer.parseInt(memberVO.getUserStatus());
                }
            }
            else if( loginResult == 2) {
                return 7;
            }
            else {
                return Integer.parseInt(memberVO.getUserStatus());
            }
        }else {
            return 6;
        }
    }

    @PostMapping(value = "/member/findid.html")
    public @ResponseBody String findId( MemberVO memberVO, HttpServletRequest request ) {

        String firstName = request.getParameter("fm_first_name");
        String lastName  = request.getParameter("fm_last_name");
        String email     = request.getParameter("fm_email1");

        memberVO.setFirstName(firstName);
        memberVO.setLastName(lastName);
        memberVO.setEmail(email);

        MemberVO memberRow = null;
        Integer memCnt = 0;

        memCnt = memberService.selectMemberCount(memberVO);
        memberRow = memberService.selectMemberSuccessYn(memberVO);

        if(memCnt == 1){
            String from=messageSource.getMessage("send.email.ID", null, localeResolver.resolveLocale(request));
            //message : ��û�Ͻ� ���̵� �Դϴ�.
            String subject=messageSource.getMessage("member.control.001", null, localeResolver.resolveLocale(request));
            //Random random = new Random();
            //random.nextInt();
            try {
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
                messageHelper.setTo(memberRow.getEmail());
                //message : ���� ��û�Ͻ� ���̵��
                //message : �Դϴ�. �����մϴ�.
                messageHelper.setText(memberRow.getLastName()+memberRow.getFirstName()+messageSource.getMessage("member.control.002", null, localeResolver.resolveLocale(request))+memberRow.getUserId()+messageSource.getMessage("member.control.003", null, localeResolver.resolveLocale(request)) );
                messageHelper.setFrom(from);
                messageHelper.setSubject(subject);
                mailSender.send(message);
            } catch(Exception e){
                System.out.println(e);
            }

            return "successTrue";
        } else {
            // ���� ������
            return "successFalse";
        }
    }

    @PostMapping(value = "/member/findpwd.html")
    public @ResponseBody String findPwd( MemberVO memberVO, HttpServletRequest request ) {
        String ranStr   = null;
        String userId  = request.getParameter("fm_user_id");
        String email    = request.getParameter("fm_email2");

        ranStr =  new RandomStringBuilder()
                .putLimitedChar(RandomStringBuilder.ALPHABET)
                .putLimitedChar(RandomStringBuilder.NUMBER)
                .putExcludedChar(RandomStringBuilder.SPECIAL)
                .setLength(12).build();

        memberVO.setUserId(userId);
        memberVO.setEmail(email);
        memberVO.setUserPw(ranStr);

        MemberVO memberRow = null;
        Integer memCnt = 0;

        memCnt = memberService.selectMemberCount_(memberVO);
        memberRow = memberService.selectMemberSuccessYn_(memberVO);

        //System.out.println(memCnt+"=====");
        if(memCnt == 1){
            String from=messageSource.getMessage("send.email.ID", null, localeResolver.resolveLocale(request));
            //message : ��û�Ͻ� ��й�ȣ �Դϴ�.
            String subject=messageSource.getMessage("member.control.004", null, localeResolver.resolveLocale(request));

            try {
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
                messageHelper.setTo(memberRow.getEmail());
                //message : ���� ��û�Ͻ� ��й�ȣ��
                //message : �Դϴ�. �����մϴ�.
                messageHelper.setText(memberRow.getLastName()+memberRow.getFirstName()+messageSource.getMessage("member.control.005", null, localeResolver.resolveLocale(request))+ranStr+messageSource.getMessage("member.control.006", null, localeResolver.resolveLocale(request)) );
                messageHelper.setFrom(from);
                messageHelper.setSubject(subject);
                mailSender.send(message);
                memberService.updateMemberPw(memberVO);
            } catch(Exception e){
                System.out.println(e);
            }
            // ���� ������
            return "True!";
        } else {
            // ���� ������
            return "False!";
        }
    }

}
