package com.blueocn.api.controller.ui.validate;

import com.blueocn.api.controller.ui.AbstractUIController;
import com.blueocn.api.response.ValidateResponse;
import com.blueocn.api.service.UserService;
import com.blueocn.api.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.blueocn.api.support.Constants.LOGIN_URI;

/**
 * Title: ValidateController
 * Description: 激活验证控制器
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-01-14 21:35
 */
@Controller
@RequestMapping("validate")
public class ValidateController extends AbstractUIController {

    @Autowired
    private ValidateService validateService;

    @Autowired
    private UserService userService;

    @RequestMapping("mail/{validateCode}")
    public String validateMail(@PathVariable("validateCode") String validateCode, Model model,
        RedirectAttributes redirect) {

        ValidateResponse response = validateService.isCodeValidate(validateCode);
        if (response.isSuccess()) {
            if (userService.activeUser(response.getUserId())) {
                redirect.addFlashAttribute("message", "用户激活成功");
            } else {
                redirect.addFlashAttribute("message", "用户激活失败, 状态不正确");
            }
            return "redirect:" + LOGIN_URI;
        } else {
            setPageTitle(model, "邮箱验证失败");
            model.addAttribute("userId", response.getUserId());
            model.addAttribute("validateCode", validateService.generateCode(response.getUserId()));
            return "validate/mailFailed";
        }
    }
}
