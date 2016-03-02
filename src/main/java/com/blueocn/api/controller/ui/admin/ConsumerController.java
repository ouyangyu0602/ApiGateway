package com.blueocn.api.controller.ui.admin;

import com.blueocn.api.controller.ui.AbstractUIController;
import com.blueocn.api.kong.model.Consumer;
import com.blueocn.api.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Title: CustomerController
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-22 10:27
 */
@Controller
@RequestMapping("admin/consumer")
public class ConsumerController extends AbstractUIController {

    @Autowired
    private ConsumerService consumerService;

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add() {
        return "admin/consumer/edit";
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list() {
        return "admin/consumer/list";
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") String id, Model model) {
        Consumer consumer = consumerService.query(id);
        if (consumer != null) {
            model.addAttribute("consumer", consumer);
        }
        return "admin/consumer/edit";
    }
}
