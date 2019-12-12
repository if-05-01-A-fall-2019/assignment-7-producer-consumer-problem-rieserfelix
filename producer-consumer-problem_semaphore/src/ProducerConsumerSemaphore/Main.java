/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProducerConsumerSemaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author dafel
 */
public class Main {
     public static void main(String[] args) {
         
        ProducerConsumer producerConsumer = new ProducerConsumer();
        ExecutorService executerServ = Executors.newFixedThreadPool(2);;

        executerServ.execute(new Runnable() {
            @Override
            public void run()
            {
                try {
                    producerConsumer.produce();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executerServ.execute(new Runnable() {
            @Override
            public void run()
            {
                try {
                    producerConsumer.consume();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executerServ.shutdown();
    }
}
