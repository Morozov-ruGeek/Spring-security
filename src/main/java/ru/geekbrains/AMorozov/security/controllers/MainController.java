package ru.geekbrains.AMorozov.security.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/")
    public String getHomePage() {
        return "homePage";
    }

    @GetMapping("/admin_panel")
    public String adminPage() {
        return "adminPanel";
    }

    @GetMapping("/add_user")
    public String addUserPage() {
        return "addUserPage";
    }

    @GetMapping("/users")
    public String viewUserPage() {
        return "viewUserPage";
    }

    @GetMapping("/products/add")
    public String addProductPage() {
        return "addProductPage";
    }

    @GetMapping("/products/delete")
    public String deleteProductPage() {
        return "deleteProductPage";
    }

    @GetMapping("/change_authorities")
    public String changeAuthoritiesPage() {
        return "changeAuthoritiesPage";
    }

    @GetMapping("/manager_panel")
    public String managerPanelPage() {
        return "managerPanelPage";
    }

    @GetMapping("/products")
    public String productsPage() {
        return "productsPage";
    }

    @GetMapping("/products/edit_price")
    public String productsEditPage() {
        return "productsEditPage";
    }

    @GetMapping("/profile")
    public String usersPanel() {
        return "usersPanel";
    }

}
