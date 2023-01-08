package com.company;

import java.io.File;
import java.text.Normalizer;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Podaj Folder Treningowy:");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.next();


        File file = new File(path);
        Entry entry = EntryService.fillWithDirectory(file);

        System.out.println("Podaj parametr uczenia");
        Scanner scanner1 = new Scanner(System.in);
        scanner1.useLocale(Locale.US);
        double alpha = scanner1.nextDouble();

        System.out.println("Podaj ilosc iteracji po zbiorze treningowym");
        Scanner scanner2 = new Scanner(System.in);

        int iteration = scanner.nextInt();


        List<Perceptron> perceptrons = new ArrayList<>();
        int i=0;
        for (String str : entry.getInstances()) {
            Perceptron perceptron = new Perceptron(entry,alpha,entry.getInstances()[i]);
            perceptron.learn(iteration);
            perceptrons.add(perceptron);
            i++;
        }


        System.out.println("                            ");
        System.out.println("                            ");
        System.out.println("                            ");
        System.out.println("                            ");
        System.out.println("                            ");
        System.out.println("----------------------------");
        System.out.println("Trening przeprowadzony pozytywnie");
        System.out.println("----------------------------");
        System.out.println("                            ");
        System.out.println("                            ");
        System.out.println("                            ");
        System.out.println("                            ");
        System.out.println("                            ");


        System.out.println("Podaj Folder Testowy:");
        Scanner scanner3 = new Scanner(System.in);
        String path3 = scanner.next();


        for(Perceptron perceptron : perceptrons) {
            perceptron.test(EntryService.fillWithDirectory(new File(path3)));
        }


        System.out.println("                            ");
        System.out.println("                            ");
        System.out.println("----------------------------");
        System.out.println("Teraz możesz podać swoje zdania testowe");
        System.out.println("----------------------------");
        System.out.println("                            ");
        System.out.println("                            ");
        Scanner input = new Scanner(System.in);
        String in = "";
        while (true){

            in = input.nextLine();
            System.out.println(in);
            for (Perceptron p :perceptrons) {
                p.test(in);
            }
        }








    }
}
