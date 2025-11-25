
import java.util.Scanner;


public class Proje1_NotSistemi {

    // Kurucu Metot (Constructor)
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Vize notu girin : ");
        int vize = input.nextInt();
        System.out.print("Final notu girin : ");
        int finalNotu = input.nextInt();
        System.out.print("Ödev notu girin : ");
        int odev = input.nextInt();
        double ortalama = calculateAverage(vize, finalNotu , odev);
        String gecmeDurumu = isPassingGrade(ortalama) ? "**Geçti**" : "**Kaldı**";
        char harfNotu = getLetterGrade(ortalama);
        String onurListesi = isHonorList(ortalama, vize, finalNotu, odev) ? "Evet" : "Hayır";
        String butunlemeHakki = hasRetakeRight(ortalama) ? "Evet" : "Hayır";

        System.out.println("--- Öğrenci Not Değerlendirme Raporu ---");
        System.out.println("Vize Notu           : " + vize);
        System.out.println("Final Notu          : " + finalNotu);
        System.out.println("Ödev Notu           : " + odev);
        System.out.println("----------------------------------------");
        System.out.printf("Hesaplanan Ortalama : %.2f\n", ortalama); 
        System.out.println("Harf Notu           : " + harfNotu);
        System.out.println("Geçme Durumu        : " + gecmeDurumu);
        System.out.println("Onur Listesi Kriteri: " + onurListesi);
        System.out.println("Bütünleme Hakkı     : " + butunlemeHakki);
        System.out.println("----------------------------------------");
    }

    // --- Metotlar ---
    public static double calculateAverage(int vize, int finalNotu, int odev) {
        double avg = (vize * 0.30) + (finalNotu * 0.40) + (odev * 0.30);
        return avg; 
    }

    public static boolean isPassingGrade(double ortalama) {
        return ortalama >= 50;
    }

    public static char getLetterGrade(double ortalama) {
        if (ortalama >= 90) {
            return 'A';
        } else if (ortalama >= 80) {
            return 'B';
        } else if (ortalama >= 70) {
            return 'C';
        } else if (ortalama >= 60) {
            return 'D';
        } else if (ortalama >= 50) {
            return 'E'; 
        } else {
            return 'F';
        }
    }

    public static boolean isHonorList(double ortalama, int vize, int finalNotu, int odev) {
        boolean ortalamaKriteri = ortalama >= 85;
        boolean bireyselNotKriteri = (vize >= 70) && (finalNotu >= 70) && (odev >= 70);
        return ortalamaKriteri && bireyselNotKriteri;
    }

    public static boolean hasRetakeRight(double ortalama) {
        return ortalama >= 40 && ortalama < 50;
    }
}
