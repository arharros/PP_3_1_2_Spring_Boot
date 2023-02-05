package ru.kata.pp_3_1_2_spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.pp_3_1_2_spring_boot.model.User;
import ru.kata.pp_3_1_2_spring_boot.services.UsersServices;
import javax.servlet.http.HttpServletRequest;

@Controller
public class UsersController {

    private UsersServices usersServices;

    @Autowired
    public void setUsersServices(UsersServices usersServices) {
        this.usersServices = usersServices;
    }


    @GetMapping(value = "/users")
    public String pageOfUsers(ModelMap model) {
        model.addAttribute("list_of_users", usersServices.listOfUsers());
        return "/pages/users";
    }

    @RequestMapping(value = "/new_user")
    public String pageAddNewUser() {
        return "/pages/new_user";
    }

    @PostMapping(value = "/new_user/save")
    public String saveUser(HttpServletRequest request) {
        User newUser = new User();
        newUser.setName(request.getParameter("name"));
        newUser.setLastName(request.getParameter("last_name"));
        newUser.setEmail(request.getParameter("email"));
        usersServices.addUser(newUser);
        return "redirect:/users";
    }

    @GetMapping(value = "/user_info")
    public String userInfoById(HttpServletRequest request, Model model) {
        long userId = Long.parseLong(request.getParameter("id"));
        if (usersServices.findUserById(userId) != null) {
            model.addAttribute("user_by_id", usersServices.findUserById(userId));
            return "/pages/user_info";
        } else {
            return "/pages/user_not_found";
        }
    }

    @RequestMapping("/delete")
    public String deleteUserById(@RequestParam(name = "id") long id) {
        usersServices.deleteUserById(id);
        return "redirect:/users";
    }

    @GetMapping("/edit_user")
    public String editUser(@RequestParam(name = "id") long id, Model model) {
        model.addAttribute("user_info", usersServices.findUserById(id));
        return "/pages/edit_user";
    }

    @PostMapping("/edit_user/update")
    public String updateUserInfo(HttpServletRequest request) {
        User userForEdit = new User();
        userForEdit.setIdUser(Long.parseLong(request.getParameter("user_id")));
        userForEdit.setName(request.getParameter("name"));
        userForEdit.setLastName(request.getParameter("last_name"));
        userForEdit.setEmail(request.getParameter("email"));
        usersServices.updateUserById(userForEdit.getIdUser(), userForEdit);
        return "redirect:/users";
    }
}
