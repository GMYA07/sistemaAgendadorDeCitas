package www.sistema.agendador.sistemaagendadorcitas.src;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Utilidades {

    public static String generarHash(String pass) {
        try {
            // Crear un digest de SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Aplicar el hash a la contraseña
            byte[] hashBytes = digest.digest(pass.getBytes());

            // Convertir el hash a formato hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al aplicar SHA-256", e);
        }
    }

    public static String generadorId(Integer tipoId){
        String idGenerado = "";
        // Definir la semilla
        long semilla = System.currentTimeMillis(); // Puedes usar cualquier valor como semilla
        // Crear un generador de números aleatorios con la semilla
        Random random = new Random(semilla);
        // Generar un número aleatorio entre 100 y 999 (3 cifras)
        int numeroAleatorio = 100 + random.nextInt(900); // 900 es el rango para obtener un número de 3 cifras

        if (tipoId == 0){
            idGenerado = "DC"+numeroAleatorio;
            return idGenerado;
        }else if (tipoId == 1){
            idGenerado = "PT"+numeroAleatorio;
            return idGenerado;
        }else {
            return "error";
        }

    }
}
