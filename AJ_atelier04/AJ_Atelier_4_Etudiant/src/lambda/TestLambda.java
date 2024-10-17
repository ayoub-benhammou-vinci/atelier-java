package lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class TestLambda {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(153, 22, 4567, 50, 209, 34, 1040);

        List<Integer> result;


        //TODO: Enlever les commentaires et remplacez les ??? par des expressions lambda appropriées

        //Trouve tous les entiers de la liste qui sont plus grands que 200
        result = Lambda.allMatches(list, (number1) -> number1 >= 200);
        System.out.println(result);

        //Trouve tous les entiers pairs de la liste
        result = Lambda.allMatches(list, (number1) -> number1 % 2 == 0);
        System.out.println(result);

        //Trouve tous les entiers de la liste dont le premier chiffre est 1
        result = Lambda.allMatches(list, (number1) -> number1.toString().substring(0,1).equals("1"));
        System.out.println(result);

        //Retourne une liste contenant les entiers de la liste originale multipliés par 2
        result = Lambda.transformAll(list, (number1) -> (number1*2));
        System.out.println(result);

        //Retourne une liste contenant les entiers de la liste originale auxquels on a soustrait 25
        result = Lambda.transformAll(list, (number1) -> (number1 - 25));
        System.out.println(result);

        //TODO: une fois arrivé au point 1.3, enlevez les commentaires et compélétez
        //      en remplaçant les ??? par des expression lambda appropriées

        List<String> list2 = Arrays.asList("hello", "bonjour", "goeiedag", "hallo", "hej");

        //Trouve toutes les String de la liste qui commencent par "h"
        List<String> result2 = Lambda.allMatches(list2, (st) -> st.charAt(0) == 'h');
        System.out.println(result2);

        //Retourner une liste qui contient la taille de chacune des String de la liste originale
        List<Integer> result3 = Lambda.transformAll(list2, String::length);
        System.out.println(result3);

        System.out.println("Exercice 5");

        System.out.println("Spécial Integer : ");
        Stream<Integer> listInteger;

        //Trouve tous les entiers de la liste qui sont plus grands que 200
        System.out.println();
        listInteger = Lambda.filter(list, (number1) -> number1 >= 200);
        listInteger.forEach((i) -> {
            System.out.print(i + ",");
        });

        //Trouve tous les entiers pairs de la liste
        System.out.println();
        listInteger = Lambda.filter(list, (number1) -> number1 % 2 == 0);
        listInteger.forEach((i) -> {
            System.out.print(i + ",");
        });

        //Trouve tous les entiers de la liste dont le premier chiffre est 1
        System.out.println();
        listInteger = Lambda.filter(list, (number1) -> number1.toString().substring(0,1).equals("1"));
        listInteger.forEach((i) -> {
            System.out.print(i + ",");
        });

        //Retourne une liste contenant les entiers de la liste originale multipliés par 2
        System.out.println();
        listInteger = Lambda.map(list, (number1) -> (number1*2));
        listInteger.forEach((i) -> {
            System.out.print(i + ",");
        });

        //Retourne une liste contenant les entiers de la liste originale auxquels on a soustrait 25
        System.out.println();
        listInteger = Lambda.map(list, (number1) -> (number1 - 25));
        listInteger.forEach((i) -> {
            System.out.print(i + ",");
        });

        System.out.println("Spécial String");
        Stream<String> listString;

        //Trouve toutes les String de la liste qui commencent par "h"
        System.out.println();
        listString = Lambda.filter(list2, (st) -> st.charAt(0) == 'h');
        listString.forEach((s) -> {
            System.out.print(s + ",");
        });

        //Retourner une liste qui contient la taille de chacune des String de la liste originale
        System.out.println();
        listInteger = Lambda.map(list2, String::length);
        listInteger.forEach((i) -> {
            System.out.print(i + ",");
        });





    }
}
