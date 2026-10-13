package MultiplicacionD;

import java.util.Scanner;

public class MultiplicacionDes {

    public static String binario(int numero) {
        String texto = Integer.toBinaryString(numero); // Convierte un número a binario

        while (texto.length() < 4) {
            texto = "0" + texto;
        }

        return texto;
    }

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        String datoM;
        String datoQ;

        do {
            System.out.print("Ingresa M (4 bits): ");
            datoM = entrada.nextLine();
        } while (!datoM.matches("[01]{4}"));

        do {
            System.out.print("Ingresa Q (4 bits): ");
            datoQ = entrada.nextLine();
        } while (!datoQ.matches("[01]{4}"));

        int M = Integer.parseInt(datoM, 2); // El 2 significa pasar el binario a decimal 
        int Q = Integer.parseInt(datoQ, 2);
        int A = 0;
        int C = 0;

        System.out.println("\nDATOS INICIALES");
        System.out.println("M = " + datoM);
        System.out.println("Q = " + datoQ);
        System.out.println("A = 0000");
        System.out.println("C = 0");

        System.out.printf("%-6s %-6s %-25s %-14s %-14s%n",
                "Paso", "Q", "A = A + M", "C:A:Q", "Despues");

        for (int paso = 1; paso <= 4; paso++) {

            int bit = Q % 2; // Decimal / 2 
            String operacion;

            if (bit == 1) {

                String binA = binario(A); // Cadena binaria
                int acarreo = 0;
                String suma = "";

                for (int i = 3; i >= 0; i--) {

                    int bitA = binA.charAt(i) - '0';
                    int bitM = datoM.charAt(i) - '0';

                    int total = bitA + bitM + acarreo;
                    int resultadoBit = total % 2;

                    acarreo = total / 2;

                    suma = resultadoBit + suma;
                }

                C = acarreo;
                A = Integer.parseInt(suma, 2); //Binario a entero 

                operacion = binA + " + " + datoM + " = " + suma;

            } else {

                C = 0;
                operacion = binario(A) + " (sin suma)";
            }

            String estado = C + ":" + binario(A) + ":" + binario(Q);

            int ultimoA = A % 2;
            int nuevoA = C * 8 + A / 2;
            int nuevoQ = ultimoA * 8 + Q / 2;

            System.out.printf("%-6d %-6d %-25s %-14s %d:%s:%s%n",
                    paso, bit, operacion, estado,
                    0, binario(nuevoA), binario(nuevoQ));

            A = nuevoA;
            Q = nuevoQ;
            C = 0;
        }

        String resultado = binario(A) + binario(Q);

        System.out.println("\nRESULTADO BINARIO: " + resultado);
        System.out.println("RESULTADO DECIMAL: "
                + Integer.parseInt(resultado, 2));

        entrada.close();
    }
}

