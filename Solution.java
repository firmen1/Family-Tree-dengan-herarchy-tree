// Nama     = Firman Afrialdy
// NIM      = 205150200111034 
import java.util.*;
class person{
    Vector <person>keturunan= new Vector<person>();
    String nama;
    String jenisKelamin;
    int level;
    person(){}
    person(String nama){
        this.nama= nama;
    }
    public void add(String nama){
        keturunan.add(new person(nama));
    }
    public void setJenisKelamin(String jk){
        this.jenisKelamin=jk;
    }
    public void setLevel(int level){
        this.level = level;
    }
}
class pohonKeluarga{
    person root;
    pohonKeluarga(){
        root=null;
    }
    public void male(String nama){
        Vector<person> p= anak(nama, root);
            if(p.size()!=0){
                for(person x : p){
                    x.setJenisKelamin("Laki-laki");
                }
            }else{
                System.out.println("dia siapa ?");
            }
    }
    public void female(String nama){
        Vector<person> p= anak(nama, root);
            if(p.size()!=0){
                for(person x : p){
                    x.setJenisKelamin("Perempuan");
                }
            }else{
                System.out.println("dia siapa ?");
            }
    }
    public void kepalaKeluarga(String nama){
        if(root==null){
            root = new person(nama);
        }else{
            System.out.println("Sudah ada kepala keluarga bernama " + root.nama);
        }
    }
    public void orangTua(String ortu, String anak){
        if(root==null){
            kepalaKeluarga(ortu);
            orangTua(ortu, anak);
        }else{
            Vector<person> p= anak(ortu, root);
            if(p.size()!=0){
                for(person x : p){
                    x.add(anak);
                }
            }else{
                System.out.println("bapaknya siapa ?");
            }
        }
        depth(root, 1);
    }
    public Vector<person> anak(String ortu, person akar){
        Vector<person> subs = new Vector<person>();
        if(akar.nama!=null){
            if(akar.nama.equals(ortu)){
                subs.add(akar);
            }else{
                for(int i=0;i<akar.keturunan.size();i++){
                    Vector<person> temu = anak(ortu, akar.keturunan.get(i));
                    for(person x:temu){
                        subs.add(x);
                    }
                }
            }
        }
        return subs;
    }
    public void printPohon(person root, int level){
        String garis="|--";
        for(int i=0;i<level;i++){
            System.out.print(garis);
        }
        System.out.println(root.nama);
        Vector<person> keturunan = root.keturunan;
        for(person p: keturunan){
            printPohon(p, level+1);
        }
    }
    public person findParent(person ortu, person anak){
        for(person p:ortu.keturunan){
            if(p.equals(anak)){
                return ortu;
            }else{
                for (person child: ortu.keturunan) {
                    person result = findParent(child, anak);
                    if (result != null) {
                        return result;
                    }
                }                
            }
        }
        return null;        
    }
    public boolean findAnak(person ortu, person anak){
        for(person p: ortu.keturunan){
            if(p.equals(anak))return true;
        }
        return false;
    }
    public String hubungan(String a, String b){
        person orang1 = anak(a, root).get(0);
        person orang2 = anak(b, root).get(0);
        int beda = Math.abs(orang1.level-orang2.level);
        String hubungan="";
        switch (beda) {
            case 0:
            if(findParent(root, orang1)==findParent(root, orang2)){
                hubungan="saudara kandung";
                System.out.println("status hubungan " + a + " terhadap " + b + " adalah " + hubungan);
            }else{
                hubungan="saudara sepupu";
                System.out.println("status hubungan " + a + " terhadap " + b + " adalah " + hubungan);
            }
            break;
            case 1:
            if(findAnak(orang1, orang2)){
                if(orang1.jenisKelamin.equals("Laki-laki")){
                    hubungan = "ayah";
                    System.out.println(a + " adalah "+ hubungan +" dari "+b );
                }else{
                    hubungan = "ibu";
                    System.out.println(a + " adalah "+ hubungan +" dari "+b );
                }
                break;
            }
            if(findAnak(orang2, orang1)){
                if(orang2.jenisKelamin.equals("Laki-laki")){
                    hubungan = "ayah";
                    System.out.println(b + " adalah "+ hubungan +" dari "+a );
                }else{
                    hubungan = "ibu";
                    System.out.println(b + " adalah "+ hubungan +" dari "+a );
                }
                break;
            }
            if(!findAnak(orang1, orang2)&&orang1.level<orang2.level){
                if(orang1.jenisKelamin.equals("Laki-laki")){
                    hubungan = "paman";
                    System.out.println(a + " adalah "+ hubungan +" dari "+b );
                }else{
                    hubungan = "bibi";
                    System.out.println(a + " adalah "+ hubungan +" dari "+b );
                }
                break;
            }
            if(!findAnak(orang2, orang1)){
                if(orang2.jenisKelamin.equals("Laki-laki")){
                    hubungan = "paman";
                    System.out.println(b + " adalah "+ hubungan +" dari "+a );
                }else{
                    hubungan = "bibi";
                    System.out.println(b + " adalah "+ hubungan +" dari "+a );
                }
                break;
            }
            break;
            case 2:
            if(findParent(root, findParent(root, orang2))==orang1){
                if(orang1.jenisKelamin.equals("Laki-laki")){
                    hubungan = "kakek kandung";
                    System.out.println(a + " adalah "+ hubungan +" dari "+b );
                }else{
                    hubungan = "nenek kandung";
                    System.out.println(a + " adalah "+ hubungan +" dari "+b );
                }
                break;
            }
            if(findParent(root, findParent(root, orang1))==orang2){
                if(orang2.jenisKelamin.equals("Laki-laki")){
                    hubungan = "kakek kandung";
                    System.out.println(b + " adalah "+ hubungan +" dari "+a );
                }else{
                    hubungan = "nenek kandung";
                    System.out.println(b + " adalah "+ hubungan +" dari "+a );
                }
                break;
            }
            if(findParent(root, findParent(root, orang2))!=orang1 && orang1.level<orang2.level){
                if(orang1.jenisKelamin.equals("Laki-laki")){
                    hubungan = "kakek jauh";
                    System.out.println(a + " adalah "+ hubungan +" dari "+b );
                }else{
                    hubungan = "nenek jauh";
                    System.out.println(a + " adalah "+ hubungan +" dari "+b );
                }
                break;
            }
            if(findParent(root, findParent(root, orang1))!=orang2){
                if(orang1.jenisKelamin.equals("Laki-laki")){
                    hubungan = "kakek jauh";
                    System.out.println(b + " adalah "+ hubungan +" dari "+a );
                }else{
                    hubungan = "nenek jauh";
                    System.out.println(b + " adalah "+ hubungan +" dari "+a );
                }
                break;
            }
            break;
            case 3:
            case 4:
            hubungan="buyut cicit";
            if(findParent(root, findParent(root, orang1))==orang2){
                if(orang1.jenisKelamin.equals("Laki-laki")){
                    hubungan = "kakek buyut";
                    System.out.println(a + " adalah "+ hubungan +" dari "+b );
                }else{
                    hubungan = "nenek buyut";
                    System.out.println(a + " adalah "+ hubungan +" dari "+b );
                }
                break;
            }
            if(findParent(root, findParent(root, orang2))==orang1){
                if(orang1.jenisKelamin.equals("Laki-laki")){
                    hubungan = "kakek buyut";
                    System.out.println(b + " adalah "+ hubungan +" dari "+a );
                }else{
                    hubungan = "nenek buyut";
                    System.out.println(b + " adalah "+ hubungan +" dari "+a );
                }
                break;
            }
            break;
            default:break;
        }
        return null;
    }
    public void depth(person akar, int level){
        Vector<person> keturunan = akar.keturunan;
        for(person p: keturunan){
            p.setLevel(level);
            depth(p, level+1);
        }
    }
}
class Solution {
    public static void main(String[] args){
        pohonKeluarga p = new pohonKeluarga();
        Scanner key = new Scanner(System.in);
        // p.kepalaKeluarga("Sutedjo");
        p.orangTua("Sutedjo", "Bambang");
        p.orangTua("Sutedjo", "Bolang");
        p.orangTua("Sutedjo", "Ucok");
        p.orangTua("Bolang", "Cika");
        p.orangTua("Bambang", "Manikmaya");
        p.orangTua("Manikmaya", "Bungsu sulung");
        p.orangTua("Manikmaya", "Bungsu bungsu");
        p.orangTua("Bungsu bungsu", "Salah");
        p.orangTua("Ucok", "Madun");
        p.orangTua("Ucok", "Nisa");
        p.orangTua("Ucok", "Dea");
        p.orangTua("Dea", "Laksmana");
        p.orangTua("Madun", "Lita");
        p.male("Sutedjo");
        p.female("Nisa");
        p.female("Dea");
        p.male("Bambang");
        p.male("Bolang");
        p.male("Ucok");
        p.female("Manikmaya");
        p.female("Cika");
        p.male("Madun");
        p.female("Bungsu sulung");
        p.male("Bungsu bungsu");
        p.female("Lita");
        p.female("Cika");
        p.male("Salah");
        p.male("Laksmana");
        p.printPohon(p.root, 0);
        p.hubungan("Cika", "Bolang");
        p.hubungan("Sutedjo", "Lita");
        p.hubungan("Cika", "Lita");
        p.hubungan("Bambang", "Dea");
        p.hubungan("Bambang", "Laksmana");
        p.hubungan("Bungsu bungsu", "Bungsu sulung");
        p.hubungan("Ucok", "Lita");
        p.hubungan("Salah", "Manikmaya");
        p.hubungan("Bungsu sulung", "Manikmaya");
        p.hubungan("Manikmaya", "Sutedjo");
        p.hubungan("Laksmana", "Manikmaya");
        key.close();
    }
}
