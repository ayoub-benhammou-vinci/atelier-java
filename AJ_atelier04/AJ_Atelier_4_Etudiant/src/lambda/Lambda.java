package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Lambda {

    /**
     * Retourne une liste contenant uniquement les Integer qui correspondent
     * au predicat match
     * @param list La liste d'Integer originale
     * @param match le predicat à respecter
     * @return une liste contenant les integer qui respectent match
     */
    public static <T> List<T> allMatches(List<T> list, Predicate<T> match) {
        List<T> listSelected = new ArrayList<T>();

        for (T t : list) {
            if(match.test(t)){
                listSelected.add(t);
            }
        }

        return listSelected;
    }

    /**
     * Retourne une liste contenant tous les éléments de la liste originale, transformés
     * par la fonction transform
     * @param list La liste d'Integer originale
     * @param transform la fonction à appliquer aux éléments
     * @return une liste contenant les integer transformés par transform
     */
    public static <T, R> List<R> transformAll(List<T> list, Function<T, R> transform) {
        List<R> listTransformed = new ArrayList<R>();

        for (T t : list) {
            R elementTransformed = transform.apply(t);
            listTransformed.add(elementTransformed);
        }

        return listTransformed;
    }

    public static <T> Stream<T> filter(List<T> list, Predicate<T> match) {
        Stream<T> listSelected = list.stream().filter(match);
        return listSelected;
    }

    public static <T, R> Stream<R> map(List<T> list, Function<T, R> transform) {
        Stream<R> listTransformed = list.stream().map(transform);
        return listTransformed;
    }




}
