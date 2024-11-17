package www.sistema.agendador.sistemaagendadorcitas.src;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Time;
import java.text.SimpleDateFormat;
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
        // Crear un generador de números aleatorios
        Random random = new Random();
        // Generar un número aleatorio entre 100 y 999 (3 cifras)
        int numeroAleatorio = 100 + random.nextInt(999); // 900 es el rango para obtener un número de 3 cifras

        String numeroFormatoNuevo = String.format("%3d",numeroAleatorio);

        if (tipoId == 0){
            idGenerado = "DC"+numeroAleatorio;
            return idGenerado;
        }else if (tipoId == 1){
            idGenerado = "PT"+numeroAleatorio;
            return idGenerado;
        }else if(tipoId == 2) {
            // Generar un número aleatorio entre 100 y 999 (3 cifras)
            numeroAleatorio = 100 + random.nextInt(9999999); // 900 es el rango para obtener un número de 3 cifras
            numeroFormatoNuevo = String.format("%7d",numeroAleatorio);
            idGenerado = "E"+numeroAleatorio;
            return idGenerado;
        }else if (tipoId == 3){
            idGenerado = "C"+numeroAleatorio;
            return idGenerado;
        }else {
            return "error";
        }
    }

    public static Time convertirHora (String horaString){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        try {
            // Convertir String a Date
            java.util.Date date = sdf.parse(horaString);

            // Convertir Date a SQL Time (sin segundos)
            Time horaCita = new Time(date.getTime());

            // Ahora 'time' es un objeto java.sql.Time que puedes almacenar en la base de datos
            return horaCita;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
