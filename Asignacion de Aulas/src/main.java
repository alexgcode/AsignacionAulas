/**
 * Created by N551 on 06/06/2016.
 */

import java.util.Random;
public class main {


    static individuo[] pd = new individuo[5];

    static String[] cursos = {"CI-01","CI-02","AC-01","IA-01","IO-01","AB-01","AB-02","BC-01","DE-01","FG-01"
                                ,"HI-01","HI-02","JK-01","LM-01","NM-01","OQ-01","OQ-02","RS-01","TU-01","VW-01"};
    static String[] aulas = {"A-101","A-102","A-103","A-104",
                        "A-201","A-202","A-203","A-204"};
    static String[] horarios={"8:00 - 9:00","10:00 - 11:00","9:00 - 10:00","11:00 - 12:00"};
    static String[] dias={"L","M","X","J","V"};

    static int indice;


    static Random rnd =new Random();

    public static int compare(String[][] crom){            // cuenta # de repeticiones
    int c=0;
        for (int i=0;i<cursos.length;i++){
            for(int n=1;n<cursos.length -i;n++){
                if(crom[i][1].equals(crom[i+n][1])){
                    if(crom[i][2].equals(crom[i+n][2])){
                        if(crom[i][3].equals(crom[i+n][3])){
                            c=c+1;
                        }
                    }
                }
            }
        }
        return c;
    }

    public static void mostrar_cromosoma(String[][] cromosoma){

        for(int i=0;i<cursos.length;i++){                        //muestra cromosomas
            for(int j=0;j<4;j++){                               //     i = fila     ,       J = columna
                System.out.print(cromosoma[i][j]+" | ");
            }
            System.out.println("");
            //System.out.println("N° de choques: " + pd[i].getNro_choques());
        }

    }


    public static individuo mejor(individuo[] pd){              //elitismo
        individuo mejor=null;
        int nmenor=cursos.length*4;
        for(int i=0;i<pd.length;i++){
            if(pd[i]!=null) {
                if (pd[i].getNro_choques() < nmenor) {
                    nmenor = pd[i].getNro_choques();
                    mejor = pd[i];
                    indice = i;
                }
            }
        }
        mejor.setNro_choques(compare(mejor.getCromosoma()));
        return mejor;
    }


    public static int[] mejores(individuo[] pd, individuo best){
        int[] mejores=new int[2];          //indice de los mejores en el padre
        individuo[] padres = new individuo[pd.length];

        for (int x=0;x<pd.length;x++){
            padres[x] = pd[x];
        }

        int cont=0;
        for(int i=0;i<padres.length;i++){
            if(padres[i]!=null) {
                if (cont < 2) {
                    if (padres[i] == best) {

                        mejores[cont] = i;
                        padres[i] = null;
                        best=mejor(padres);
                        cont++;
                        System.out.println("contador: " + cont);
                    }
                }
            }
        }


        return mejores;
    }




    public static String[][] crear_cromo(){
        String[][] cro=new String[cursos.length][4];
        for (int i = 0; i < cursos.length; i++) {                   //creacion de cromosomas (soluciones) x filas
            cro[i][0] = cursos[i];
            cro[i][1] = dias[rnd.nextInt(5)];
            cro[i][2] = aulas[rnd.nextInt(8)];
            cro[i][3] = horarios[rnd.nextInt(4)];
        }
        return cro;
    }

    public static individuo crear_ind(){               //crear de individuo
        individuo ind=new individuo();
        ind.setCromosoma(crear_cromo());
        ind.setNro_choques(compare(ind.getCromosoma()));
        return ind;
    }

    public static individuo[] cruce(int[] mejores, individuo[] padres){

        individuo[] hijos= new individuo[2];

        String[][] c1= padres[mejores[0]].getCromosoma();        //cromosomas del padre 1
        String[][] c2= padres[mejores[1]].getCromosoma();       //cromosomas del padre 2


        String[][] h1= new String[cursos.length][4];
        String[][] h2= new String[cursos.length][4];


        int nro_cruce=3;


        //---------------HIjo 1-------------------
        System.out.println("-------hijo 1--------");
        for(int i=0;i<h1.length;i++){
                for (int j = 0; j < 4; j++) {
                    if (i < nro_cruce) {
                        h1[i][j] = c1[i][j];
                    } else {
                        h1[i][j] = c2[i][j];
                    }
                }
        }

        //-----------------HIjo 2--------------------------
        System.out.println("---------hijo2----------");
        for(int i=0;i<h2.length;i++){
            for (int j = 0; j < 4; j++) {
                if (i < nro_cruce) {

                    h2[i][j] = c2[i][j];
                } else {
                    h2[i][j] = c1[i][j];
                }
            }
        }

        //----------------------------------
        individuo hijo1= new individuo(h1,compare(h1));
        individuo hijo2 = new individuo(h2,compare(h2));

        hijos[0]=hijo1;
        hijos[1]=hijo2;

        System.out.println("Cruce exitoso");
        return hijos;
    }


    public static void mutacion(individuo[] padres){

        String[][] c1 = padres[0].getCromosoma();
        String[][] c2 = padres[1].getCromosoma();
        String[][] c3 = padres[2].getCromosoma();
        String[][] c4 = padres[3].getCromosoma();
        String[][] c5 = padres[4].getCromosoma();

        for(int i=0;i<c1.length;i++){
                if(rnd.nextInt(100)>70){
                    c1[i][1] = dias[rnd.nextInt(5)];
                    c1[i][2] = aulas[rnd.nextInt(8)];
                    c1[i][3] = horarios[rnd.nextInt(4)];
                }
        }
        for(int i=0;i<c2.length;i++){
                if(rnd.nextInt(100)>70){
                    c2[i][1] = dias[rnd.nextInt(5)];
                    c2[i][2] = aulas[rnd.nextInt(8)];
                    c2[i][3] = horarios[rnd.nextInt(4)];
                }
        }
        for(int i=0;i<c1.length;i++){
                if(rnd.nextInt(100)>70){
                    c3[i][1] = dias[rnd.nextInt(5)];
                    c3[i][2] = aulas[rnd.nextInt(8)];
                    c3[i][3] = horarios[rnd.nextInt(4)];
                }
        }
        for(int i=0;i<c1.length;i++){
                if(rnd.nextInt(100)>70){
                    c4[i][1] = dias[rnd.nextInt(5)];
                    c4[i][2] = aulas[rnd.nextInt(8)];
                    c4[i][3] = horarios[rnd.nextInt(4)];
                }
        }
        for(int i=0;i<c1.length;i++){
            for(int j=0;j<c1.length;j++){
                if(rnd.nextInt(100)>70){
                    c5[i][1] = dias[rnd.nextInt(5)];
                    c5[i][2] = aulas[rnd.nextInt(8)];
                    c5[i][3] = horarios[rnd.nextInt(4)];
                }
            }
        }

        padres[0].setCromosoma(c1);
        padres[1].setCromosoma(c2);
        padres[2].setCromosoma(c3);
        padres[3].setCromosoma(c4);
        padres[4].setCromosoma(c5);

        System.out.println("mutacion exitosa");
    }

    public static void insercion_hijos(int[] mejores,individuo[] hijos,individuo[] padres){

        padres[mejores[0]]=hijos[0];
        padres[mejores[1]]=hijos[1];


        System.out.println("insercion exitosa");
    }

    public  static void mostrar_padre(individuo[] pd){
        for(int i=0;i<pd.length;i++){
            System.out.print("------------");
            System.out.println("Matriz " + i);
            mostrar_cromosoma(pd[i].getCromosoma());
            System.out.println("N° de choques: " + pd[i].getNro_choques());
        }
    }

    public static void main(String[]args){




        for(int i=0;i<pd.length;i++){                                      //crea 5 individuos
            pd[i] = crear_ind();                      //llena vector padre(conjunto de individuos)
        }


        System.out.println("******************************");

        int generacion=0;

        mostrar_padre(pd);

        while(mejor(pd).getNro_choques()!=0){

                individuo best = mejor(pd);
                System.out.println(" ### el mejor individuo: Matriz " + indice);

                if(pd[indice]==null){
                    System.out.println("matriz pd nula");
                }else {
                    System.out.println("Matriz pd llena");
                }

                int[] mj;
                mj=mejores(pd,best);

                System.out.println();
                if(mj!=null){
                    System.out.print("### mejores matrices : ");
                    for(int i=0;i<mj.length;i++)
                        System.out.print(mj[i]+" , ");
                }else{
                    System.out.println("no hay mejores");
                }
                System.out.println("");

                individuo[] hijos= cruce(mj,pd);
                mutacion(pd);
                insercion_hijos(mj,hijos,pd);


            generacion++;
        }

        System.out.println("-----------------ultima generacion---------------");
        mostrar_padre(pd);
        System.out.println("---------mejor solucion: Matriz "+ indice +"-----------");
        mostrar_cromosoma(pd[indice].getCromosoma());
        System.out.println("N° de choques: " + pd[indice].getNro_choques());
        System.out.println("generacion: "+generacion);
        System.out.println("********FIN*************");
        System.exit(0);
    }
}
