package com.main;

import java.util.List;
import java.util.Scanner;

import com.bean.Category;
import com.bean.Product;
import com.dao.InventoryManager;

public class Main {
	public static void main(String[] args) {
		InventoryManager im=new InventoryManager();
		Scanner sc=new Scanner(System.in);
		char ch;
        do {
        	 System.out.println("Please select an option:");
             System.out.println("1. Add a product");
             System.out.println("2. Remove a product");
             System.out.println("3. Update inventory");
             System.out.println("4. Display inventory");
             System.out.println("5. Display total value");
             System.out.println("6. Search for a product by name");
             System.out.println("7. Search for a product by category");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    addProduct(sc,im);
                    break;
                case 2:
                	removeProduct(sc,im);
                	break;
                case 3:
                	updateInventory(sc, im);
                	break;
                case 4:
                    displayInventory(im);
                    break;
                case 5:
                    displayTotalValue(im);
                    break;
                case 6:
                    searchByName(sc, im);
                    break;
                case 7:
                    searchByCategory(sc, im);
                    break;
                
                
                default:
                    System.out.println("Invalid Input..");
            }
            System.out.print("Do you want to continue (Y/N): ");
            ch = sc.next().charAt(0);
        } while (ch == 'y' || ch == 'Y');
        sc.close();
    }




	private static void addProduct(Scanner sc, InventoryManager im) {
		System.out.print("Enter the product name: ");
		String name=sc.nextLine();
		System.out.println("Enter the category id: ");
		int id=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter the category name: ");
		String categoryName=sc.nextLine();
		Category category=new Category(id,categoryName);
		System.out.println("Enter the price: ");
		double price=sc.nextInt();
		Product product=new Product(id, name, category, price);
		im.addProduct(product);
		System.out.println("Product added successfully..");
		
	}
	private static void removeProduct(Scanner sc, InventoryManager im) {
		System.out.println("Enter the product name:");
        String name = sc.nextLine();
        List<Product> searchResults = im.searchByName(name);
        if (searchResults.isEmpty()) {
            System.out.println("Product not found.");
        } else if (searchResults.size() == 1) {
            Product product = searchResults.get(0);
            im.removeProduct(product);
            System.out.println("Product removed successfully.");
        } else {
            System.out.println("Multiple products found. Please refine your search.");
        }

	}
	private static void updateInventory(Scanner sc, InventoryManager im) {
		System.out.println("Enter the product name:");
        String name = sc.nextLine();

        List<Product> searchResults = im.searchByName(name);
        if (searchResults.isEmpty()) {
            System.out.println("Product not found.");
        } else if (searchResults.size() == 1) {
            Product product = searchResults.get(0);

            System.out.println("Enter the new stock level:");
            int quantity = sc.nextInt();
            sc.nextLine(); // Consume the newline character after reading the integer

            im.updateInventory(product, quantity);
            System.out.println("Stock level updated successfully.");
        } else {
            System.out.println("Multiple products found. Please refine your search.");
        }
	}

	private static void searchByCategory(Scanner sc, InventoryManager im) {
		 System.out.print("Enter the category name:");
	        String categoryName = sc.nextLine();
	        System.out.print("Enter the category id: ");
			int id=sc.nextInt();
	        Category category = new Category(id, categoryName);

	        List<Product> searchResults = im.searchByCategory(category);
	        if (searchResults.isEmpty()) {
	            System.out.println("No products found in the specified category.");
	        } else {
	            System.out.println("Category Search Results:");
	            for (Product product : searchResults) {
	                System.out.println("Product: " + product.getName() +
	                        ", Category: " + product.getCategory().getName());
	            }
	        }
	}

	private static void searchByName(Scanner sc, InventoryManager im) {
		System.out.print("Enter the product name:");
        String name = sc.nextLine();

        List<Product> searchResults = im.searchByName(name);
        if (searchResults.isEmpty()) {
            System.out.println("Product not found.");
        } else {
            System.out.println("Search Results:");
            for (Product product : searchResults) {
                System.out.println("Product: " + product.getName() +
                        ", Category: " + product.getCategory().getName());
            }
        }
   }
	private static void displayTotalValue(InventoryManager im) {
		im.displayTotalValue();
	}
	private static void displayInventory(InventoryManager im) {
		im.display();
	}

	
	
}
