package dev.onesnzeroes.shoppingcart;

import dev.onesnzeroes.shoppingcart.domain.ShoppingCart;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();

        System.out.println("Give language code");
        String langCode = scanner.nextLine();
        System.out.println("Give country code");
        String countryCode = scanner.nextLine();
        Locale locale = new Locale(langCode, countryCode);

        ResourceBundle bundle = ResourceBundle.getBundle("messages_"+langCode, locale);

        System.out.println(bundle.getString("itemAmount"));
        int amount = Integer.parseInt(scanner.nextLine());
        for(int i = 0; i < amount; i++){

            System.out.print(bundle.getString("itemPrice"));
            double price = scanner.nextDouble();

            System.out.print(bundle.getString("quantity"));
            int quantity = scanner.nextInt();

            cart.addItem(price,quantity);
        }
        System.out.println(bundle.getString("total")+ " " + cart.getTotal());
        scanner.close();
    }
}