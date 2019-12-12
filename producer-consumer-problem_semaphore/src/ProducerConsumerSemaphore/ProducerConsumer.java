package ProducerConsumerSemaphore;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

public class ProducerConsumer {
    
    static final int MAX = 10;
    static Semaphore prod = new Semaphore(1);
    static Semaphore cons = new Semaphore(0);
    static LinkedList<Integer> stack = new LinkedList<Integer>();
    Random rInt = new Random();
    
    public void consume() throws InterruptedException{
        while(true){
            try {
                if (stack.size() == 0) {
                    cons.acquire();
                }
                
            } catch (InterruptedException ex) {
                System.out.println("An error occured:"+ex.getMessage());
            }

            System.out.println("cons. removed: " + stack.remove(stack.size() -1));

            if (stack.size() < MAX) {
                prod.release();
            }
            
            Thread.sleep(1000);
        }


    }

    public void produce() throws InterruptedException{
        int item;
        while(true){
            item = rInt.nextInt(1000+1);
            Thread.sleep(2000);
            
            try {
                if (stack.size() == MAX) {
                    prod.acquire();
       
                }
                
            } catch (InterruptedException ex) {
                System.out.println("An error occured:"+ex.getMessage());
            }

            stack.add(item);
            
            System.out.println("producer added: " + item);
            
            if (stack.size() >= 1) {
                cons.release();
                
            }    
        }
        
    }
    
}

