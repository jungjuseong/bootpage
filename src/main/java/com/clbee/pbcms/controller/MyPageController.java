package com.clbee.pbcms.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.clbee.pbcms.util.MyPasswordEncoder;
import lombok.AllArgsConstructor;
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
import com.clbee.pbcms.vo.LicenseList;
import com.clbee.pbcms.vo.LicenseSubList;
import com.clbee.pbcms.vo.LicenseVO;
import com.clbee.pbcms.vo.MemberVO;

@Controller
@AllArgsConstructor
public class MyPageController {

    private MemberService memberService;
    private CompanyService companyService;
    private JavaMailSender mailSender;
    private MessageSource messageSource;
    private LocaleResolver localeResolver;
    private GroupViewMenuService groupViewMenuService;
    private LicenseService licenseService;

    @RequestMapping(value="/mypage/password.html", method=RequestMethod.GET)
    public ModelAndView mypagePasswordGET( String userId ){
        ModelAndView modelAndView = new ModelAndView();

        //20180516 : lsy - temp if/else
        MyUserDetails activeUser = (MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        for(GrantedAuthority auth : authentication.getAuthorities()) {
            if(auth.getAuthority().equals("ROLE_COMPANY_MEMBER") || auth.getAuthority().equals("ROLE_INDIVIDUAL_MEMBER") || auth.getAuthority().equals("ROLE_USER")) {
                //20180515 : lsy - GroupViewMenu Util Create
                Map<String, Object> menuList = new HashMap<String, Object>();
                menuList = groupViewMenuService.selectViewMenu(activeUser.getMemberVO().getGroupName(), menuList);

                modelAndView.addObject("menuLarge", menuList.get("menuLarge"));
                //20180515 : lsy - GroupViewMenu Util Create - end
            }
        }
        //20180516 : lsy - temp if/else - end

        modelAndView.setViewName("06_mypage/mypage_password");
        return modelAndView;
    }

    @RequestMapping(value="/mypage/modify.html", method=RequestMethod.POST)
    public ModelAndView mypageModifyPOST( HttpServletRequest request,  MemberVO formVO, CompanyVO formComVO, String modify_gb ){
        ModelAndView modelAndView = new ModelAndView();

        MyUserDetails activeUser = (MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if("modify_password".equals(modify_gb)){
            if(activeUser.getPassword().equals(MyPasswordEncoder.changeSHA256(formVO.getUserPw()))){
                /*modelAndView.addObject("ReConfirmPassword", userPw);*/
                MemberVO dbVOPassword  = memberService.findByCustomInfo( "userId", activeUser.getUsername() );
                CompanyVO companyVO = companyService.findByCustomInfo("companySeq", activeUser.getMemberVO().getCompanySeq());
                modelAndView.addObject("companyVO", companyVO);
                modelAndView.addObject("memberVO", dbVOPassword);
                modelAndView.setViewName("06_mypage/mypage_modify");
            }else {
                modelAndView.addObject("validPassword", false);
                modelAndView.setViewName("/inc/dummy");
            }
        }else{

            formVO.setUserPw(MyPasswordEncoder.changeSHA256(formVO.getUserPw()));

            if("5".equals(formVO.getUserStatus())) {
                formVO.setEmailChkSession(MyPasswordEncoder.changeSHA256(formVO.getUserId()));
                String from=messageSource.getMessage("send.email.ID", null, localeResolver.resolveLocale(request));

                String subject=messageSource.getMessage("member.control.007", null, localeResolver.resolveLocale(request));
                try {
                    MimeMessage message = mailSender.createMimeMessage();
                    MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
                    messageHelper.setTo(formVO.getEmail());

                    messageHelper.setText(messageSource.getMessage("member.control.008", null, localeResolver.resolveLocale(request)) + "\n"+ "http://"+messageSource.getMessage("basic.Info.IP", null, localeResolver.resolveLocale(request)) + "/member/join/ok.html?validId=" +
                            MyPasswordEncoder.changeSHA256(formVO.getUserId()));
                    messageHelper.setFrom(from);
                    messageHelper.setSubject(subject);
                    mailSender.send(message);
                } catch(Exception e){
                    System.out.println(e);
                }
            }

            System.out.println("formComVO.zipCode : " + formComVO.getZipcode());
            formVO.setChgDt(new Date());
            formVO.setChgIp(request.getRemoteAddr());
            memberService.updateMemberInfo( formVO, formVO.getUserSeq());

            MemberVO dbVOModify = memberService.findByCustomInfo( "userId", formVO.getUserId() );
            companyService.updateCompanyInfo(formComVO, dbVOModify.getCompanySeq());
            CompanyVO dbComVOModify = companyService.findByCustomInfo("companySeq", dbVOModify.getCompanySeq());
            modelAndView.addObject("modifySuccess", true);
            modelAndView.addObject("companyVO", dbComVOModify);
            modelAndView.addObject("memberVO", dbVOModify);
            modelAndView.setViewName("06_mypage/mypage_modify");
        }

        //20180516 : lsy - temp if/else
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        for(GrantedAuthority auth : authentication.getAuthorities()) {
            if(auth.getAuthority().equals("ROLE_COMPANY_MEMBER") || auth.getAuthority().equals("ROLE_INDIVIDUAL_MEMBER") || auth.getAuthority().equals("ROLE_USER")) {
                //20180515 : lsy - GroupViewMenu Util Create
                Map<String, Object> menuList = new HashMap<String, Object>();
                menuList = groupViewMenuService.selectViewMenu(activeUser.getMemberVO().getGroupName(), menuList);

                modelAndView.addObject("menuLarge", menuList.get("menuLarge"));
                //20180515 : lsy - GroupViewMenu Util Create - end
            }
        }
        //20180516 : lsy - temp if/else - end

        return modelAndView;
    }

    @RequestMapping(value="/mypage/modifyCustom.html", method=RequestMethod.POST)
    public @ResponseBody String mypaOST( String userPw ){
        MyUserDetails activeUser = (MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return activeUser.getMemberVO().getEmail();
    }

    //??
    @RequestMapping(value="/mypage/modify.html", method=RequestMethod.GET)
    public String mypagePOST( String userPw ){
        /* �ùٸ��� ���� �����Դϴ�. */

        throw new ResourceNotFoundException();
        /*return "06_mypage/mypage_modify";*/
    }

    //??
    @RequestMapping(value="/mypage/withDrawal.html", method=RequestMethod.GET)
    public String mypageWithDrawal( String userPw ){
        /* �ùٸ��� ���� �����Դϴ�. */

        return "06_mypage/mypage_withdrawal";
        /*return "06_mypage/mypage_modify";*/
    }

    @RequestMapping(value="/mypage/withDrawal.html", method=RequestMethod.POST)
    public @ResponseBody int mypageWithDrawalPOST( String userSeq, String companySeq ){

        int intUserSeq = Integer.parseInt(userSeq);
        int intCompanySeq = Integer.parseInt(companySeq);
        int companyResult = 1;

        if(intCompanySeq != 0)/* ���ȸ���̶�� �ǹ� */{
            CompanyVO updateComVO = new CompanyVO();
            updateComVO.setCompanyStatus("1"); // Ż��
            updateComVO.setWithdrawalDt(new Date());
            companyResult = companyService.updateCompanyInfo(updateComVO, intCompanySeq);
        }

        MemberVO updateMemVO = new MemberVO();
        updateMemVO.setUserStatus("1");	// Ż���
        updateMemVO.setWithdrawalDt(new Date());
        int memberResult = memberService.updateMemberInfo(updateMemVO, intUserSeq);

        /* 탈퇴처리 -> 삭제되는것 아님 */
        if(companyResult == 1 && memberResult == 1) {
            return 1;
        }else {
            return 0;
        }
    }

    @RequestMapping(value="/my/license.html", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView mypageLicenseManagement( LicenseList listLicense, LicenseSubList licenseUseDevice, HttpServletRequest request, HttpSession session ){
        ModelAndView modelAndView = new ModelAndView();

        MyUserDetails activeUser = (MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        for(GrantedAuthority auth : authentication.getAuthorities()) {
            if(auth.getAuthority().equals("ROLE_COMPANY_MEMBER") || auth.getAuthority().equals("ROLE_INDIVIDUAL_MEMBER") || auth.getAuthority().equals("ROLE_USER")) {
                Map<String, Object> menuList = new HashMap<String, Object>();
                menuList = groupViewMenuService.selectViewMenu(activeUser.getMemberVO().getGroupName(), menuList);

                modelAndView.addObject("menuLarge", menuList.get("menuLarge"));
            }
        }

        int useLicense = licenseService.checkUseLicense(activeUser.getMemberVO().getUserSeq());

        if(useLicense == 0) {
            modelAndView.setViewName("06_mypage/my_license_regist");
        }else if(useLicense == 1) {
            listLicense = licenseService.selectMyList(listLicense, activeUser.getMemberVO().getUserSeq());
            modelAndView.addObject("licenseList", listLicense);

            if(request.getParameter("currentPage") == null || licenseUseDevice.getCurrentPage() == 0) {
                licenseUseDevice.setCurrentPage(1);
            }
            licenseUseDevice.setLicenseSeq(listLicense.getList().get(0).getLicenseSeq());
            licenseUseDevice = licenseService.selectLicenseUseDevice(licenseUseDevice);

            modelAndView.addObject("searchValue", licenseUseDevice.getSearchValue());
            modelAndView.addObject("currentPage", licenseUseDevice.getCurrentPage());
            modelAndView.addObject("licenseUseDevice", licenseUseDevice);

            modelAndView.setViewName("06_mypage/my_license_list");
        }

        return modelAndView;
    }

    @RequestMapping(value="/my/deleteLicenseUseDevice.html", method=RequestMethod.POST)
    public @ResponseBody int mypageDeleteLicenseUseDevicePost( HttpServletRequest request, HttpSession session ){
        return licenseService.deleteLicenseUseDevice(Integer.parseInt(request.getParameter("licensesubSeq")));
    }

    @RequestMapping(value="/my/licenseRegistCheck.html", method=RequestMethod.POST)
    public @ResponseBody int mypageLicenseRegistCheck( HttpServletRequest request, HttpSession session ){
        return licenseService.licenseRegistCheck(request.getParameter("licenseNum"));
    }

    @RequestMapping(value="/my/licenseRegist.html", method=RequestMethod.POST)
    public ModelAndView mypageLicenseRegist( HttpServletRequest request, HttpSession session ){
        ModelAndView modelAndView = new ModelAndView();

        try {
            MyUserDetails activeUser = (MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = context.getAuthentication();

            if(request.getParameter("licenseSeq") != null && !request.getParameter("licenseSeq").equals("")) {
                licenseService.licenseExpire(Integer.parseInt(request.getParameter("licenseSeq")));
            }

            licenseService.licenseRegist(activeUser.getMemberVO().getUserSeq(), request.getParameter("licenseNum"));

            for(GrantedAuthority auth : authentication.getAuthorities()) {
                if(auth.getAuthority().equals("ROLE_COMPANY_MEMBER") || auth.getAuthority().equals("ROLE_INDIVIDUAL_MEMBER") || auth.getAuthority().equals("ROLE_USER")) {
                    Map<String, Object> menuList = new HashMap<String, Object>();
                    menuList = groupViewMenuService.selectViewMenu(activeUser.getMemberVO().getGroupName(), menuList);

                    modelAndView.addObject("menuLarge", menuList.get("menuLarge"));
                }
            }

            modelAndView.setViewName("redirect:/my/license.html");
        }catch(Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(value="/my/licenseRenew.html", method=RequestMethod.GET)
    public ModelAndView mypageLicenseRenewGet( HttpServletRequest request, HttpSession session ){
        ModelAndView modelAndView = new ModelAndView();

        try {
            MyUserDetails activeUser = (MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = context.getAuthentication();

            for(GrantedAuthority auth : authentication.getAuthorities()) {
                if(auth.getAuthority().equals("ROLE_COMPANY_MEMBER") || auth.getAuthority().equals("ROLE_INDIVIDUAL_MEMBER") || auth.getAuthority().equals("ROLE_USER")) {
                    Map<String, Object> menuList = new HashMap<String, Object>();
                    menuList = groupViewMenuService.selectViewMenu(activeUser.getMemberVO().getGroupName(), menuList);

                    modelAndView.addObject("menuLarge", menuList.get("menuLarge"));
                }
            }

            List<LicenseVO> license = null;
            license = licenseService.selectLicenseForRenew(activeUser.getMemberVO().getUserSeq());

            modelAndView.addObject("licenseSeq", license.get(0).getLicenseSeq());
            modelAndView.setViewName("06_mypage/my_license_regist");
        }catch(Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

}
