package flapkap.vendingmachine.api.v1;

import flapkap.vendingmachine.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends AbstractController implements UsersApi {
    @Autowired
    UserService userService;
}
