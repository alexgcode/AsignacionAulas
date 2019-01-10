/**
 * Created by N551 on 06/06/2016.
 */
public class individuo {

    String Cromosoma[][];         // [curso, dia, aula asignada, horas]
                                                    //5 filas, 4 columnas
    int nro_choques;        //objetivo : nro_choques = 0


    public individuo(String[][] cromosoma, int nro_choques) {
        this.Cromosoma = cromosoma;
        this.nro_choques = nro_choques;
    }

    public individuo() {
    }

    public void mostrar_cromo(String[][] cr){
        for(int i=0;i<cr.length;i++){
            for(int j=0;j<4;j++){
                System.out.print(cr[i][j]+" | ");
            }
            System.out.println("");
        }
    }

    public String[][] getCromosoma() {
        return Cromosoma;
    }

    public void setCromosoma(String[][] cromosoma) {
        Cromosoma = cromosoma;
    }


    public int getNro_choques() {
        return nro_choques;
    }

    public void setNro_choques(int nro_choques) {
        this.nro_choques = nro_choques;
    }
}
