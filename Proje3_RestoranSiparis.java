
import java.util.Scanner;

public class Proje3_RestoranSiparis{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- AKILLI RESTORAN SİPARİŞ SİSTEMİ ---");
        
        System.out.println("ANA YEMEKLER: 1-Izgara Tavuk(85), 2-Adana Kebap(120), 3-Levrek(110), 4-Mantı(65)");
        System.out.print("Seçiminiz (Yoksa 0): ");
        int anaYemekSecim = scanner.nextInt();

        System.out.println("BAŞLANGIÇLAR: 1-Çorba(25), 2-Humus(45), 3-Sigara Böreği(55)");
        System.out.print("Seçiminiz (Yoksa 0): ");
        int baslangicSecim = scanner.nextInt();

        System.out.println("İÇECEKLER: 1-Kola(15), 2-Ayran(12), 3-Meyve Suyu(35), 4-Limonata(25)");
        System.out.print("Seçiminiz (Yoksa 0): ");
        int icecekSecim = scanner.nextInt();

        System.out.println("TATLILAR: 1-Künefe(65), 2-Baklava(55), 3-Sütlaç(35)");
        System.out.print("Seçiminiz (Yoksa 0): ");
        int tatliSecim = scanner.nextInt();

        System.out.print("Saat kaç? (0-23 arası tam sayı): ");
        int saat = scanner.nextInt();

        System.out.print("Hafta içi mi? (Evet için true, Hayır için false yazın): ");
        boolean haftaIci = scanner.nextBoolean();

        System.out.print("Öğrenci misiniz? (Evet için true, Hayır için false yazın): ");
        boolean ogrenciDurumu = scanner.nextBoolean();

        double anaYemekFiyat = getMainDishPrice(anaYemekSecim);
        double baslangicFiyat = getAppetizerPrice(baslangicSecim);
        double icecekFiyat = getDrinkPrice(icecekSecim);
        double tatliFiyat = getDessertPrice(tatliSecim);

        if (isHappyHour(saat) && icecekFiyat > 0) {
            System.out.println("INFO: Happy Hour saati (14:00-17:00)! İçeceğe %20 indirim uygulandı.");
            icecekFiyat = icecekFiyat * 0.80; 
        }

        double toplamTutar = anaYemekFiyat + baslangicFiyat + icecekFiyat + tatliFiyat;

        boolean anaVar = anaYemekFiyat > 0;
        boolean icecekVar = icecekFiyat > 0;
        boolean tatliVar = tatliFiyat > 0;
        boolean isCombo = isComboOrder(anaVar, icecekVar, tatliVar);

        boolean ogrenciIndirimiGecerli = (ogrenciDurumu && haftaIci);

        double indirimMiktari = calculateDiscount(toplamTutar, isCombo, ogrenciIndirimiGecerli, saat);
        
        double odenecekTutar = toplamTutar - indirimMiktari;

        double bahsis = calculateServiceTip(odenecekTutar);

        System.out.println("\n--- HESAP DETAYLARI ---");
        System.out.println("Ürün Toplamı: " + toplamTutar + " TL");
        if(isCombo) System.out.println("+ Combo Menü İndirimi uygulandı.");
        if(toplamTutar > 200) System.out.println("+ 200 TL Üzeri İndirimi uygulandı.");
        if(ogrenciIndirimiGecerli) System.out.println("+ Öğrenci İndirimi uygulandı.");
        
        System.out.println("Toplam İndirim: -" + indirimMiktari + " TL");
        System.out.println("Ara Toplam: " + odenecekTutar + " TL");
        System.out.println("Önerilen Bahşiş (%10): " + bahsis + " TL");
        System.out.println("--------------------------------");
        System.out.println("GENEL TOPLAM (Bahşiş Dahil): " + (odenecekTutar + bahsis) + " TL");
    }

    public static double getMainDishPrice(int secim) {
        double fiyat = 0;
        switch (secim) {
            case 1: fiyat = 85; break;
            case 2: fiyat = 120; break;
            case 3: fiyat = 110; break;
            case 4: fiyat = 65; break;  
            default: fiyat = 0; break;
        }
        return fiyat;
    }

    public static double getAppetizerPrice(int secim) {
        double fiyat = 0;
        switch (secim) {
            case 1: fiyat = 25; break;
            case 2: fiyat = 45; break; 
            case 3: fiyat = 55; break; 
            default: fiyat = 0; break;
        }
        return fiyat;
    }

    public static double getDrinkPrice(int secim) {
        double fiyat = 0;
        switch (secim) {
            case 1: fiyat = 15; break; 
            case 2: fiyat = 12; break; 
            case 3: fiyat = 35; break;
            case 4: fiyat = 25; break; 
            default: fiyat = 0; break;
        }
        return fiyat;
    }

    public static double getDessertPrice(int secim) {
        double fiyat = 0;
        switch (secim) {
            case 1: fiyat = 65; break; 
            case 2: fiyat = 55; break; 
            case 3: fiyat = 35; break; 
            default: fiyat = 0; break;
        }
        return fiyat;
    }

    public static boolean isComboOrder(boolean anaVar, boolean icecekVar, boolean tatliVar) {
        return anaVar && icecekVar && tatliVar;
    }

    public static boolean isHappyHour(int saat) {
        return (saat >= 14 && saat <= 17);
    }

    public static double calculateDiscount(double tutar, boolean combo, boolean ogrenci, int saat) {
        double toplamIndirim = 0.0;

        if (combo) {
            toplamIndirim += tutar * 0.15;
        }

        if (tutar > 200) {
            toplamIndirim += tutar * 0.10;
        }

        if (ogrenci) {
            toplamIndirim += tutar * 0.10;
        }

        return toplamIndirim;
    }

    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10;
    }
}
