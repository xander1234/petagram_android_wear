package co.com.pets.db;

import android.content.ContentValues;
import android.content.Context;


import java.util.ArrayList;

import co.com.pets.R;
import co.com.pets.pojo.Mascota;



public class ConstructorMascotas {

    private Context context;

    public ConstructorMascotas(Context context) {
        this.context = context;
    }



    public void insertarMascotas(BaseDatos db){

        ContentValues contentValues = new ContentValues();

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_ID,1);//nombre campo
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE,"Cerdo");//nombre campo
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.cerdo);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_LIKES,0);
        db.insertarMascota(contentValues);


        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_ID,2);//nombre campo,valor
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE,"Perro");//nombre campo,valor
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO,R.drawable.perro);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_LIKES,0);
        db.insertarMascota(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_ID,3);//nombre campo
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE,"Elefante");//nombre campo
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO,R.drawable.elefante);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_LIKES,0);
        db.insertarMascota(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_ID,4);//nombre campo
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE,"Gato");//nombre campo
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO,R.drawable.gato);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_LIKES,0);
        db.insertarMascota(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_ID,5);//nombre campo
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE,"Leon");//nombre campo
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO,R.drawable.leon);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_LIKES,0);
        db.insertarMascota(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_ID,6);//nombre campo
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE,"Oso Panda");//nombre campo
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO,R.drawable.panda_bear);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_LIKES,0);
        db.insertarMascota(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_ID,7);//nombre campo
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE,"Vaca");//nombre campo
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO,R.drawable.vaca);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_LIKES,0);
        db.insertarMascota(contentValues);
    }

    public ArrayList<Mascota> obtenerMascotas(){

        BaseDatos db = new BaseDatos(context);
        insertarMascotas(db);
        return db.obtenerTodasLasMascotas();
    }

    public void editarLike(Mascota mascota,int i ){
        BaseDatos db = new BaseDatos(context);
        db.generarUpdate(mascota,i);
    }

    public int obtenerLikesMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikes(mascota);
    }

    public ArrayList<Mascota> obtenerPreferidas(){
        BaseDatos db = new BaseDatos(context);
        insertarMascotas(db);
        return db.obtenerMascotaFavorita();
    }
}
