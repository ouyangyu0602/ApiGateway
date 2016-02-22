package com.blueocn.api.controller.ui.consumer;

import com.blueocn.api.controller.ui.AbstractUIController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Title: CustomerController
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-22 10:27
 */
@Controller
@RequestMapping("consumer")
public class ConsumerController extends AbstractUIController {

    @RequestMapping("add")
    public String add() {
        return "consumer/add";
    }

    @RequestMapping("list")
    public String list() {
        return "consumer/list";
    }
}
