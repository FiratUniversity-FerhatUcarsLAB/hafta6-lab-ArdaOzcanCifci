
import java.util.Scanner;

public class Proje2_SinemaBileti{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- SİNEMA BİLET HESAPLAMA ---");
        System.out.print("Günü seçiniz (1:Pzt, 2:Sal, 3:Çar, 4:Per, 5:Cum, 6:Cmt, 7:Paz): ");
        int gun = scanner.nextInt();
        System.out.print("Saati giriniz (0-23 arası tam sayı, örn: 14): ");
        int saat = scanner.nextInt();
        System.out.print("Yaşınızı giriniz: ");
        int yas = scanner.nextInt();
        System.out.println("Meslek seçiniz (1=Öğrenci, 2=Öğretmen, 3=Diğer): ");
        int meslek = scanner.nextInt();
        System.out.println("Film türünü seçiniz (1=2D, 2=3D, 3=IMAX, 4=4DX): ");
        int filmTuru = scanner.nextInt();
        generateTicketInfo(gun, saat, yas, meslek, filmTuru);
    }

    public static boolean isWeekend(int gun) {
        boolean haftaSonuMu = false;
        switch (gun) {
            case 6:
            case 7:
                haftaSonuMu = true;
                break;
            default:
                haftaSonuMu = false;
        }
        return haftaSonuMu;
    }

    public static boolean isMatinee(int saat) {
        return saat < 12;
    }

    public static double calculateBasePrice(int gun, int saat) {
        boolean haftasonu = isWeekend(gun);
        boolean matine = isMatinee(saat);

        if (haftasonu) {
            return matine ? 55.0 : 85.0;
        } else {
            return matine ? 45.0 : 65.0;
        }
    }

    public static double calculateDiscount(int yas, int meslek, int gun) {
        double indirimOrani = 0.0;

        switch (meslek) {
            case 1:
                if (gun >= 1 && gun <= 4) {
                    indirimOrani = 0.20;
                } else {
                    indirimOrani = 0.15;
                }
                return indirimOrani; 

            case 2: 
                if (gun == 3) {
                    indirimOrani = 0.35;
                    return indirimOrani;
                }
                break; 
            
            case 3:
                break;
        }

        if (yas > 65) {
            indirimOrani = 0.30;
        } else if (yas < 12) {
            indirimOrani = 0.25;
        }

        return indirimOrani;
    }

    public static double getFormatExtra(int filmTuru) {
        double ekstraUcret = 0.0;
        switch (filmTuru) {
            case 1:
                ekstraUcret = 0.0;
                break;
            case 2: 
                ekstraUcret = 25.0;
                break;
            case 3: 
                ekstraUcret = 35.0;
                break;
            case 4:
                ekstraUcret = 50.0;
                break;
            default:
                System.out.println("Hatalı film türü seçimi!");
                break;
        }
        return ekstraUcret;
    }

    public static double calculateFinalPrice(int gun, int saat, int yas, int meslek, int filmTuru) {
        double temelFiyat = calculateBasePrice(gun, saat);
        double indirimOrani = calculateDiscount(yas, meslek, gun);
        double ekstraUcret = getFormatExtra(filmTuru);
        double indirimTutari = temelFiyat * indirimOrani;
        double toplamFiyat = (temelFiyat - indirimTutari) + ekstraUcret;

        return toplamFiyat;
    }

    public static void generateTicketInfo(int gun, int saat, int yas, int meslek, int filmTuru) {
        double odenecekTutar = calculateFinalPrice(gun, saat, yas, meslek, filmTuru);
        double temelFiyat = calculateBasePrice(gun, saat);
        double ekstra = getFormatExtra(filmTuru);
        double indirimOrani = calculateDiscount(yas, meslek, gun);

        System.out.println("\n--- BİLET DETAYLARI ---");
        System.out.println("Temel Fiyat: " + temelFiyat + " TL");
        
        if (indirimOrani > 0) {
            System.out.println("Uygulanan İndirim: %" + (int)(indirimOrani * 100));
            System.out.println("İndirim Tutarı: -" + (temelFiyat * indirimOrani) + " TL");
        } else {
            System.out.println("İndirim: Yok");
        }

        System.out.println("Format Ekstrası: +" + ekstra + " TL");
        System.out.println("-------------------------");
        System.out.println("TOPLAM TUTAR: " + odenecekTutar + " TL");
    }
}
