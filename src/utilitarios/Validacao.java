package utilitarios;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validacao {
    private static final String REGEX_NOME = "^[A-Za-zÀ-ÖØ-öø-ÿ ]+$";
    private static final String REGEX_CPF = "^\\d{11}$";
    private static final String REGEX_EMAIL = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public static boolean validarNome(String nome) {
        Pattern pattern = Pattern.compile(REGEX_NOME);
        Matcher matcher = pattern.matcher(nome);
        return matcher.matches();
    }

    public static boolean validarCPF(String cpf) {
        Pattern pattern = Pattern.compile(REGEX_CPF);
        Matcher matcher = pattern.matcher(cpf);
        return matcher.matches();
    }

    public static boolean validarEmail(String email) {
        Pattern pattern = Pattern.compile(REGEX_EMAIL);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
