package domaine;

import com.sun.source.tree.Tree;

import java.util.*;

public class Livre {
    private Map<Plat.Type, SortedSet<Plat>> plats = new TreeMap<>(new Comparator<Plat.Type>() {
        @Override
        public int compare(Plat.Type o1, Plat.Type o2) {
            return Integer.compare(o1.getNumero(), o2.getNumero());
        }
    });

    public boolean ajouterPlat(Plat plat) {

        if(!this.plats.containsKey(plat.getType())){
            this.plats.put(plat.getType(), new TreeSet<>(new Comparator<Plat>() {
                @Override
                public int compare(Plat o1, Plat o2) {
                    int compare = o1.getNiveauDeDifficulte().compareTo(o2.getNiveauDeDifficulte());
                    if(compare == 0){
                        return o1.getNom().compareToIgnoreCase(o2.getNom());
                    }
                    return compare;
                }
            }));
        }

        //Si le type de plat existe, voir à l'intérieur du type de plat correspondant
        for (Map.Entry<Plat.Type, SortedSet<Plat>> typeSortedSetEntry : plats.entrySet()) {
            if(typeSortedSetEntry.getKey().equals(plat.getType())){
                SortedSet<Plat> recoverPlats = typeSortedSetEntry.getValue();
                if(!recoverPlats.contains(plat)){
                    recoverPlats.add(plat);
                    return true;
                }
            }
        }

        //Dans le cas ou le plat existe déjà
        return false;


    }

    public boolean supprimerPlat(Plat plat){
        //Vérifier si le type de plat existe
        if(!this.plats.containsKey(plat.getType())) return false;

        //Si Ok, on check maintenant si il est dedans (Version plus simplifié)
        for (Map.Entry<Plat.Type, SortedSet<Plat>> typeSortedSetEntry : plats.entrySet()) {
            if(typeSortedSetEntry.getKey().equals(plat.getType())){
                SortedSet<Plat> recoverPlat = typeSortedSetEntry.getValue();
                if(recoverPlat.contains(plat)){
                    recoverPlat.remove(plat);
                    return true;
                }
            }
        }

        //Dans le cas ou le plat n'existe pas (impossible de le supprimer)
        return false;
    }


    public SortedSet<Plat> getPlatsParType(Plat.Type type) {
        // L'ensemble renvoyé ne doit pas être modifiable !
        return Collections.unmodifiableSortedSet(this.plats.get(type));
    }
    
    public boolean contient(Plat plat){
        //Cas simple
        if(!this.plats.containsKey(plat.getType())) return false;
        
        //Dans ce cas, cela voudrait dire qu'il y a bien son type dans le SortedSet
        for (Map.Entry<Plat.Type, SortedSet<Plat>> typeSortedSetEntry : plats.entrySet()) {
            if(typeSortedSetEntry.getKey().equals(plat.getType())){
                SortedSet<Plat> listePlatTrouve = typeSortedSetEntry.getValue();
                return listePlatTrouve.contains(plat);
                //Le comparator le fait déjà
                /*for (Plat platTrouve : listePlatTrouve) {
                    if (platTrouve.getNiveauDeDifficulte().equals(plat.getNiveauDeDifficulte())) {
                        if(platTrouve.getNom().equals(plat.getNom())){
                            return true;
                        }
                    }
                }
                 */
            }
        }

        return false;
    }

    public Set<Plat> tousLesPlats(){
        Set<Plat> tousLesPlats = new TreeSet<>(new Comparator<Plat>() {
            @Override
            public int compare(Plat o1, Plat o2) {
                int compareType = o1.getType().compareTo(o2.getType());
                if(compareType != 0) return compareType;

                int compareDiffculte = o1.getNiveauDeDifficulte().compareTo(o2.getNiveauDeDifficulte());
                if(compareDiffculte != 0) return compareDiffculte;

                int compareNom = o1.getNom().compareToIgnoreCase(o2.getNom());
                return compareNom;
            }
        });

        for (SortedSet<Plat> plat : plats.values()) {
            tousLesPlats.addAll(plat);
        }

        return tousLesPlats;
    }


    public String toString(){
        StringBuilder text = new StringBuilder();
        for (Plat.Type type : plats.keySet()) {
            text.append(type.getNom()).append("\n=====\n");
            for (Plat plat : plats.get(type)) {
                text.append(plat.getNom()).append("\n");
            }

        }

        //StringBuilder permet de faire des modifications sur la String travailler
        return text.toString();
    }




}
