 
package javaapplication22;

import java.util.LinkedList;
import java.util.List;

class Product {

    private static int id = 0;
    private String name;
    
    public Product() {
	this.name = "Product<" + id + ">";
	id++;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Product.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
	return name;
    }

}

 class ProductionLine {

    private List<Product> products;

    public ProductionLine() {
	products = new LinkedList<Product>();
    }

    public int size() {
	return products.size();
    }

    public void append(Product p) {
	products.add(p);
    }

    public Product retrieve() {
        
	return products.remove(0);
    }
    
    public Boolean isEmpty()
	{
		return (size() == 0);
	}
   

}

class Producer implements Runnable {

     ProductionLine queue;

    public Producer(ProductionLine queue) {
	this.queue = queue;
    }

    public void run() {
	int count = 0;
	while (count < 10) {
	    synchronized(queue) {
		if (queue.size() < 10) {
		    Product p = new Product();
		    System.out.println("Produced: " + p);
		    queue.append(p);
		    count++;
		}
	    }
	    
	}
    }
}

public class ProductLineApplication {

    
    public static void main(String[] args) {
      	ProductionLine queue = new ProductionLine();
	Thread[] producers = new Thread[4];
for (int i = 0; i < producers.length; i++) {
	    producers[i] = new Thread(new Producer(queue));
	    producers[i].start();
	}

        
        
    }
    
}
