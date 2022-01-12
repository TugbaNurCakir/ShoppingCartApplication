package com.kodluyoruz;

import java.util.Scanner;

public class Main {
    /**
     * This is the main class for the application, interacting
     * with the customer, adding to the cart different products to
     * be purchased and finally calculating the total amount to be paid
     */

    /**
     * Allows a cashier to enter the details for a product
     * to be purchased by a customer
     * @param cart the shopping cart of a given customer
     */
    public static void askCustomer(Cart cart){
        // Code to read from console the product name, seller,
        // price, number of products, discount and
        // if Buy2Take3 applies.
        // Then create a product of the correct type
        // and add it to the shopping cart
        String ans = "";
        do {
            System.out.println("Enter 1 to buy a product.");
            System.out.println("Enter 0 to checkout and proceed with the payment");
            Scanner scanner = new Scanner(System.in);
            ans = scanner.nextLine();
            if(ans.equals("1")){
                System.out.println("Product Name: ");
                String productName = scanner.nextLine();

                System.out.print("Seller Name: ");
                String seller = scanner.nextLine();

                System.out.println("How many price: ");
                double productPrice = scanner.nextDouble();

                System.out.println("How many: ");
                int count = scanner.nextInt();

                System.out.print("Discount (enter 0 if no discount applies): ");
                int discount = scanner.nextInt();

                System.out.print("Does Buy2Take3 apply? Y/N: ");
                String buy2take3 =scanner.next();

                if(discount!=0 && buy2take3.equals("Y")){
                    throw new IllegalArgumentException("You cannot benefit from two promotions.");
                }
                //If there is a discount, add it to the cart as a discounted product
                if(discount!=0){
                    Product product =new Product(seller,productName, productPrice);
                    DiscountedProduct discountedProduct = new DiscountedProduct(product, discount);

                    if(count==1){
                        cart.addProduct(discountedProduct);
                    }
                    else{
                        cart.addProduct(discountedProduct, count);
                    }
                }
                //If there is a buy2take3, add it to the cart as a buy2take3 product
                if(buy2take3.equals("Y")){
                    Product product = new Product(seller,productName, productPrice);
                    Buy2Take3Product buy2Take3Product = new Buy2Take3Product(product);
                    if(count==1){
                        cart.addProduct(buy2Take3Product);
                    }
                    else{
                        cart.addProduct(buy2Take3Product, count);
                    }
                }

            }

        }while(!ans.equals("0"));

        for (Product item: cart.getProducts()) {
            if(item instanceof DiscountedProduct){
                System.out.println(item.toString()+" "+item.getPrice(cart)+"TL."+" Sold by "+item.getSeller());//check which product it is and print
            }else{
                System.out.println(item.getName()+" "+item.getPrice(cart)+" TL."+"Sold by "+item.getSeller());
            }

        }
        System.out.println("In total you have to pay "+cart.totalPrice()+" TL");
    }


    // Main method to interact with a customer
    public static void main(String[] args) {
        System.out.println("Welcome to kodluyoruz shop");
        System.out.println("What’s your name?");

        Scanner scanner = new Scanner(System.in);

        String customer = scanner.nextLine();
        System.out.println("Hi " + customer + ". Please choose one of the following options:");

        Cart cart = new Cart();
        askCustomer(cart);
        //Implement the user interface here
        // Ask the user to choose 0 (buy product) or
        // 1 (checkout), depending on what they want to do.
        // Use the method askCustomer
    }
}
