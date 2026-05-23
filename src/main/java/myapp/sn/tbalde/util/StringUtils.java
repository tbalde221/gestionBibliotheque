package sn.tbalde.util;

public class StringUtils {
    private static void validateString(String texte, String message) {
        if (texte == null || texte.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void validateString(String texte) {
        if (texte == null || texte.isBlank()) {
            throw new IllegalArgumentException("Attribute Invalid");
        }
    }

    public static void validateNom(String nom) {
        String message = "Nom Invalid";
        validateString(nom, message);
    }

    public static void validatePrenom(String prenom) {
        String message = "Prenom Invalid";
        validateString(prenom, message);
    }

    public static String capitalize(String texte) {

        if (texte == null || texte.isEmpty()) {
            return texte;
        }

        return texte.substring(0, 1).toUpperCase()
                + texte.substring(1).toLowerCase();
    }

}
