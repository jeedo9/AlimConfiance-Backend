

package com.alimconfiance.utils;
public class FormatFilterQueryString {

    public static String formatFilterQueryString(final String restaurantLocation, final String hygieneLevel, final String sortFilter) {
        // Cas où restaurantLocation est vide et hygieneLevel est "Tous les niveaux"
        if (restaurantLocation == null || restaurantLocation.isEmpty()) {
            if ("Tous les niveaux".equals(hygieneLevel) && (sortFilter != null && !sortFilter.isEmpty())) {
                return formatSortFilterQueryString(sortFilter);
            } else if (!"Tous les niveaux".equals(hygieneLevel)) {
                // Cas où hygieneLevel est différent de "Tous les niveaux" et pas de restaurantLocation
                return " and synthese_eval_sanit=\"" + hygieneLevel + "\"" + formatSortFilterQueryString(sortFilter);
            }
            return "";
        }

        // Cas où restaurantLocation contient une virgule (un couple ville/code postal)
        if (restaurantLocation.contains(", ")) {
            String[] locations = restaurantLocation.split(", ");
            String city = locations[0];
            String depCode = locations[1];

            // Cas avec hygieneLevel égal à "Tous les niveaux"
            if ("Tous les niveaux".equals(hygieneLevel)) {
                return " and (libelle_commune like \"" + city + "\" or dep_code like \"" + depCode + "\")" + formatSortFilterQueryString(sortFilter);
            } else {
                // Cas où hygieneLevel est différent de "Tous les niveaux"
                return " and (libelle_commune like \"" + city + "\" or dep_code like \"" + depCode + "\")" +
                        " and synthese_eval_sanit=\"" + hygieneLevel + "\"" + formatSortFilterQueryString(sortFilter);
            }
        }

        // Cas où restaurantLocation est une seule valeur (ville ou code postal)
        if ("Tous les niveaux".equals(hygieneLevel)) {
            return " and (libelle_commune like \"" + restaurantLocation + "\" or dep_code like \"" + restaurantLocation + "\")" + formatSortFilterQueryString(sortFilter);
        } else {
            return " and (libelle_commune like \"" + restaurantLocation + "\" or dep_code like \"" + restaurantLocation + "\")" +
                    " and synthese_eval_sanit=\"" + hygieneLevel + "\"" + formatSortFilterQueryString(sortFilter);
        }
    }


    private static String formatSortFilterQueryString(final String sortFilter) {
        if (sortFilter != null && !sortFilter.isEmpty()) {
            StringBuilder queryKey = new StringBuilder("&order_by=");
            if ("bestRated".equals(sortFilter)) {
                queryKey.append("synthese_eval_sanit desc");
            } else {
                queryKey.append("date_inspection desc");
            }
            return queryKey.toString();
        }
        return "";
    }
}