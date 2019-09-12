package cn.kgc.ssm.controller;

import cn.kgc.ssm.bean.User;
import cn.kgc.ssm.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FilenameFilter;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @RequestMapping("/toLogin")
    public String toLogin(){return "login";}

    @RequestMapping("/dologin.html")
    public String dologin(@RequestParam("userCode") String userCode,
                          @RequestParam("userPassword")String userPassword,
                          Model model, HttpSession session){
        User user = userService.getUser(userCode);
        if (user == null){
            model.addAttribute("error","用户名不存在");
            return "login";
        }else {
            if (!user.getUserPassword().equals(userPassword)){
                model.addAttribute("error","密码错误");
                return "login";
            }
            session.setAttribute("userSession",user);
            return "frame";
        }
    }
    @RequestMapping("/userList")
    public String userList(@RequestParam(value = "userName",required = false)String userName,
                           @RequestParam(value = "userRole",required = false)Integer userRole,
                           @RequestParam(value = "pageIndex",required = false)Integer pageIndex,
                           Model model){
        if (pageIndex==null){
            pageIndex=1;
        }
        Page<Object>pages= PageHelper.startPage(pageIndex,5);
        List<User> userList = userService.getUserList(userName, userRole);
        if (userList!=null){
            model.addAttribute("userList",userList);
            model.addAttribute("pages",pages);
            model.addAttribute("userName",userName);
            model.addAttribute("userRole",userRole);
            return "userlist";
        }
        return "frame";
    }
    @RequestMapping("/useradd")
    public String useradd(){
        return "useradd";
    }

    @RequestMapping(value = "/useraddsave",method = RequestMethod.POST)
    public String useraddsave(User user, HttpSession session,
                              HttpServletRequest request,
                              @RequestParam(value = "attachs",required = false)
                              MultipartFile file){
        String idPicPath = null;

        if (file.isEmpty()){
            //
            String oldFileName = file.getOriginalFilename();

            String suffix=
        }
        User u= (User)session.getAttribute("userSession");
        user.setCreatedBy(u.getId());
        user.setCreationDate(new Date());
        if (userService.addUser(user)==1){
            return "redirect:/user/userList";
        }
        return "useradd";
    }
    @RequestMapping("/usermodify")
    public String usermodify(@RequestParam("uid") Integer uid,Model model){
        User user = userService.getUserById(uid);
        model.addAttribute("user",user);
        return "usermodify";
    }
    @RequestMapping(value = "/usermodifysave",method = RequestMethod.POST)
    public String usermodifysave(User user,HttpSession session){
        User u = (User) session.getAttribute("userSession");
        user.setModifyBy(u.getId());
        user.setModifyDate(new Date());
        if (userService.updateUserById(user)==1){
            return "redirect:/user/userList";
        }
        return "usermodify";

    }

}
